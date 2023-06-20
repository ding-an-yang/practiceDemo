package com.example.util;

import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.TableRowHeightType;
import com.spire.doc.documents.VerticalAlignment;
import com.spire.doc.fields.TextRange;

import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigInteger;

import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * @author ：yangan
 * @date ：2023/5/23 下午3:01
 * @description：Excel文件的相关操作
 * @version: 1.0.0
 */
public class ExcelUtils {

    public static void createWordExcel(String[] header0, String[] header1, String[] header2, String[][] data) {
        //创建一个Document对象
        com.spire.doc.Document document = new com.spire.doc.Document();
        //添加一个节
        com.spire.doc.Section section = document.addSection();
        //添加表格
        com.spire.doc.Table table = section.addTable(true);
        // 以二级表头为准
        table.resetCells(data.length + 3, header2.length);

        // 合并表头的单元格
        table.applyHorizontalMerge(0, 0, 4);
        table.applyHorizontalMerge(1, 0, 1);
        table.applyHorizontalMerge(1, 2, 4);

        com.spire.doc.TableRow row0 = table.getRows().get(0);
        row0.isHeader(true);
        row0.setHeight(20);
        row0.setHeightType(TableRowHeightType.Exactly);
        // 设置表格的背景颜色
        //row.getRowFormat().setBackColor(Color.gray);
        for (int i = 0; i < header0.length; i++) {
            row0.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            Paragraph p = row0.getCells().get(i).addParagraph();
            p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
            TextRange txtRange = p.appendText(header0[i]);
            txtRange.getCharacterFormat().setBold(true);
        }

        //将第一行设置为表格标题
        com.spire.doc.TableRow row1 = table.getRows().get(1);
        row1.isHeader(true);
        row1.setHeight(18);
        row1.setHeightType(TableRowHeightType.Exactly);
        // 设置表格的背景颜色
        //row.getRowFormat().setBackColor(Color.gray);
        for (int i = 0; i < header1.length; i++) {
            row1.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            Paragraph p = row1.getCells().get(i).addParagraph();
            p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
            TextRange txtRange = p.appendText(header1[i]);
            txtRange.getCharacterFormat().setBold(true);
        }

        //将第一行设置为表格标题
        com.spire.doc.TableRow row2 = table.getRows().get(2);
        row2.isHeader(true);
        row2.setHeight(15);
        row2.setHeightType(TableRowHeightType.Exactly);
        // 设置表格的背景颜色
        //row.getRowFormat().setBackColor(Color.gray);
        for (int i = 0; i < header2.length; i++) {
            row2.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            Paragraph p = row2.getCells().get(i).addParagraph();
            p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
            TextRange txtRange = p.appendText(header2[i]);
            txtRange.getCharacterFormat().setBold(true);
        }

        //将数据添加到其余行
        for (int r = 0; r < data.length; r++) {
            com.spire.doc.TableRow dataRow = table.getRows().get(r + 3);
            dataRow.setHeight(20);
            dataRow.setHeightType(TableRowHeightType.Exactly);
            dataRow.getRowFormat().setBackColor(Color.white);
            for (int c = 0; c < data[r].length; c++) {
                dataRow.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
                dataRow.getCells().get(c).addParagraph().appendText(data[r][c]);
            }
        }
        //设置单元格的背景颜色
//        for (int j = 1; j < table.getRows().getCount(); j++) {
//            if (j % 2 == 0) {
//                TableRow row2 = table.getRows().get(j);
//                for (int f = 0; f < row2.getCells().getCount(); f++) {
//                    row2.getCells().get(f).getCellFormat().setBackColor(new Color(173, 216, 230));
//                }
//            }
//        }
        //保存结果文件
        document.saveToFile("result.docx", com.spire.doc.FileFormat.Docx_2013);
    }

    public static void createWord(String fileName, List<Object> dataList){
        // 创建一个空白的Word（.docx）文档
        XWPFDocument document= new XWPFDocument();
        // 生成的文件名
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 添加文档标题
//        writeWordTitle(document, title, titleColor, titleFontSize);

        // 文档中写入内容
//        writeWordText(document, text, textColor, textFontSize);

        String picturePath = "/Users/qiush7engkeji/Desktop/WorkRecords/电网/img/罚单.jpg";
        // 插入图片
        writeWordPicture(document, picturePath);

        //换行
        wrap(document, 2);

        //工作经历表格
        XWPFTable xwpfTable = document.createTable();
        //列宽自动分割
        CTTblWidth comTableWidth = xwpfTable.getCTTbl().addNewTblPr().addNewTblW();
        comTableWidth.setType(STTblWidth.DXA);
        comTableWidth.setW(BigInteger.valueOf(8000));

        //表格第一行（表头）
        XWPFTableRow comTableRowZero = xwpfTable.getRow(0);
        comTableRowZero.getCell(0).setText("信息统计表");
        comTableRowZero.addNewTableCell().setText("信息统计表");
        comTableRowZero.addNewTableCell().setText("信息统计表");
        comTableRowZero.addNewTableCell().setText("信息统计表");

        // 列合并
        mergeHorizontal(xwpfTable, 0, 0, 3);

        createWriteTable(xwpfTable, dataList);

        // 合并行
//        mergeVertically(xwpfTable, 2, 2, 3);

        try {
            document.write(out);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("create_table document written success.");
    }

    /**
     * 向表格中写入数据
     * @param xwpfTable 表格对象
     * @param dataList 表格填充数据
     */
    public static void createWriteTable(XWPFTable xwpfTable, List<Object> dataList){
        for (Object data : dataList) {
            XWPFTableRow comTableRow = xwpfTable.createRow();
            Class<?> aClass = data.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (int i = 0;i < declaredFields.length;i++) {
                if (!declaredFields[i].isAccessible()) {
                    declaredFields[i].setAccessible(true);
                }
                try {
                    comTableRow.getCell(i).setText(declaredFields[i].get(data).toString());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                textCenter(comTableRow.getCell(i));
            }
        }
    }

    /**
     * 页眉页脚设置
     * @param document 文档对象
     * @param headerText 页眉
     * @param footerText 页脚
     */
    public static void setHeaderParagraph(XWPFDocument document, String headerText, String footerText){
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);

        //添加页眉
        CTP ctpHeader = CTP.Factory.newInstance();
        CTR ctrHeader = ctpHeader.addNewR();
        CTText ctHeader = ctrHeader.addNewT();
        ctHeader.setStringValue(headerText);
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
        //设置为右对齐
        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
        parsHeader[0] = headerParagraph;
        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);

        //添加页脚
        CTP ctpFooter = CTP.Factory.newInstance();
        CTR ctrFooter = ctpFooter.addNewR();
        CTText ctFooter = ctrFooter.addNewT();
        ctFooter.setStringValue(footerText);
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
        parsFooter[0] = footerParagraph;
        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
    }

    /**
     * 设置生成Word文档的标题
     * @param xwpfDocument Word文档对象
     * @param title 标题
     * @param titleColor 标题文字颜色 六位颜色码值 如黑色 000000
     * @param titleFontSize 文字的大小设置
     */
    public static void writeWordTitle(XWPFDocument xwpfDocument, String title, String titleColor, Integer titleFontSize){
        // 添加标题
        XWPFParagraph titleParagraph = xwpfDocument.createParagraph();
        // 设置表头居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        // 文字表头设置
        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText(title);
        titleParagraphRun.setColor(titleColor);
        titleParagraphRun.setFontSize(titleFontSize);
    }

    /**
     * 写入Word文档内容
     * @param xwpfDocument Word文档对象
     * @param text 段落内容
     * @param textColor 段落文字颜色 六位颜色码值 如黑色 000000
     * @param textFontSize 文字的大小设置
     */
    public static void writeWordText(XWPFDocument xwpfDocument, String text, String textColor, Integer textFontSize){
        XWPFParagraph firstParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = firstParagraph.createRun();
        xwpfRun.setText(text);
        xwpfRun.setColor(textColor);
        xwpfRun.setFontSize(textFontSize);
        //设置段落背景颜色
//        CTShd cTShd = xwpfRun.getCTR().addNewRPr().addNewShd();
//        cTShd.setVal(STShd.CLEAR);
//        cTShd.setFill("97FFFF");
    }

    public static void writeWordPicture(XWPFDocument xwpfDocument, String picturePath){
        File imageFile = new File(picturePath);
        FileInputStream fis = null;
        XWPFParagraph paragraph = xwpfDocument.createParagraph();
        XWPFRun run = paragraph.createRun();
        try {
            fis = new FileInputStream(imageFile);
            byte[] imageData = IOUtils.toByteArray(fis);
            xwpfDocument.addPictureData(imageData, XWPFDocument.PICTURE_TYPE_JPEG);
            run.addPicture(fis, XWPFDocument.PICTURE_TYPE_JPEG, "罚单.jpg", Units.toEMU(100), Units.toEMU(100));
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 行合并
     * @param table 合并表格
     * @param coll 合并为几列
     * @param fromRow 起始行
     * @param toRow 结束行
     */
    public static void mergeVertically(XWPFTable table, Integer coll, Integer fromRow, Integer toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(coll);
            if (rowIndex == fromRow) {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
        // 合并后垂直居中
        table.getRow(fromRow).getCell(coll).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
    }

    /**
     * 列合并
     * @param xwpfTable 合并表格
     * @param row 第几行需要合并
     * @param fromCell 起始列
     * @param toCell 结束列
     */
    public static void mergeHorizontal(XWPFTable xwpfTable, Integer row, Integer fromCell, Integer toCell) {
        XWPFTableCell cell = null;
        // 循环合并列
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            cell = xwpfTable.getRow(row).getCell(cellIndex);
            if (Objects.equals(cellIndex, fromCell)) {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
            // 表格文字居中
            XWPFParagraph cellParagraph = cell.getParagraphArray(0);
            cellParagraph.setAlignment(ParagraphAlignment.CENTER);
        }
    }

    /**
     * 设置单元格的内容居中显示
     * @param cell 单元格对象
     */
    public static void textCenter(XWPFTableCell cell){
        XWPFParagraph cellParagraph = cell.getParagraphArray(0);
        cellParagraph.setAlignment(ParagraphAlignment.CENTER);
    }

    /**
     *  增加换行
     * @param xwpfDocument XWPFDocument
     * @param total 换行数
     */
    public static void wrap(XWPFDocument xwpfDocument, Integer total){
        XWPFParagraph paragraph = xwpfDocument.createParagraph();
        XWPFRun paragraphRun = paragraph.createRun();
        for (int i = 0;i < total; i++) {
            paragraphRun.setText("\r");
        }
    }
}