package com.example.util;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * @author Honey
 * @Date 2021/9/9
 * @Description
 */
@Component
public class ReplaceWord {

    public XWPFDocument ttt(Map<String, String> map, String fileName) {
        Resource resource = new ClassPathResource("static/" + fileName);
        InputStream inputStream = null;
        String absolutePath = null;
        try {
            absolutePath = resource.getFile().getAbsolutePath();
            System.out.println(absolutePath);
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(absolutePath));

            Iterator<XWPFParagraph> paragraphsIterator = document.getParagraphsIterator();
            while (paragraphsIterator.hasNext()) {
                XWPFParagraph next = paragraphsIterator.next();
                List<XWPFRun> runs = next.getRuns();
                for (int i = 0; i < runs.size(); i++) {
                    XWPFRun xwpfRun = runs.get(i);
                    String text = xwpfRun.getText(xwpfRun.getTextPosition());
                    System.out.println(text);
                    if (text == null || text.trim().equals("")) {
                        continue;
                    }
                    //替换
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (text.contains(key)) {
                            text = text.replace(key, value);
                            xwpfRun.setText(text, 0);
                            break;
                        }
                    }

                }
            }
            return document;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * doc类型的word用HWPFDocument类进行解析
     * docx类型的word用XWPFDocument类进行解析
     * @param srcPath word模板数据源路径
     * @param destPath word导出路径
     * @param map 关键字键值对映射
     * @throws Exception
     */
    public static void replaceWord(String srcPath, String destPath,Map<String, String> map) throws Exception {
        FileOutputStream out = null;
        FileInputStream input = null;
        try {
            if(srcPath != null && srcPath.indexOf(".docx") < 1) {
                System.out.println(srcPath);
                input = new FileInputStream(new File(srcPath));
                HWPFDocument document = new HWPFDocument(input);
                Range range = document.getRange();
                for(Map.Entry<String, String> entry : map.entrySet()) {
                    if (entry.getValue() == null) {
                        entry.setValue("  ");
                    }
                    range.replaceText(entry.getKey(), entry.getValue());
                }
                ByteArrayOutputStream ostream = new ByteArrayOutputStream();
                out = new FileOutputStream(new File(destPath));
                document.write(out);
                out.write(ostream.toByteArray());
                out.flush();
            }else {
                XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));
                // 替换段落中的指定文字
                Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
                while (itPara.hasNext()) {
                    XWPFParagraph paragraph = itPara.next();
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String oneparaString = run.getText(run.getTextPosition());
                        if (isBlank(oneparaString)){
                            continue;
                        }
                        for (Map.Entry<String, String> entry :
                                map.entrySet()) {
                            oneparaString = oneparaString.replace(entry.getKey(), entry.getValue());
                        }
                        run.setText(oneparaString, 0);
                    }

                }
                ByteArrayOutputStream ostream = new ByteArrayOutputStream();
                out = new FileOutputStream(new File(destPath));
                document.write(out);
                out.write(ostream.toByteArray());
                out.flush();
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                out.close();
            }
            if(input != null) {
                input.close();
            }
        }
    }

    public static void replaceTable(XWPFDocument doc, Map<String, Object> params){

        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun run : p.getRuns()) {
                            String text = run.getText(0);
                            if (!Objects.isNull(text)){
                                for (Map.Entry<String, Object> entry : params.entrySet()) {
                                    //获取map的key
                                    String key = entry.getKey();
                                    if (text.contains(key)) {
                                        Object value = entry.getValue();
                                        text = text.replace(key, String.valueOf(value));
                                    }
                                }
                            }
                            run.setText(text,0);
                        }
                    }
                }
            }
        }
    }

    public static void replaceTable2(InputStream is) throws IOException {
        // 处理doc格式 即office2003版本
        POIFSFileSystem pfs = new POIFSFileSystem(is);
        HWPFDocument hwpf = new HWPFDocument(pfs);
        Range range = hwpf.getRange();//得到文档的读取范围
        TableIterator it = new TableIterator(range);
        // 迭代文档中的表格
        // 如果有多个表格只读取需要的一个 set是设置需要读取的第几个表格，total是文件中表格的总数
        int set = 1, total = 4;
        int num = set;
        for (int i = 0; i < set - 1; i++) {
            it.hasNext();
            it.next();
        }
        while (it.hasNext()) {
            Table tb = (Table) it.next();
            //迭代行，默认从0开始,可以依据需要设置i的值,改变起始行数，也可设置读取到那行，只需修改循环的判断条件即可
            for (int i = 1; i < tb.numRows(); i++) {
                ArrayList<String> strings = new ArrayList<>();
                TableRow tr = tb.getRow(i);
                //迭代列，默认从0开始
                for (int j = 0; j < 4; j++) {
                    TableCell td = tr.getCell(j);//取得单元格

                    //取得单元格的内容
                    String text="";
                    for (int k = 0; k < td.numParagraphs(); k++) {
                        Paragraph para = td.getParagraph(k);
                        String s = para.text();
                        //去除后面的特殊符号
                        if (null != s && !"".equals(s)) {
                            s = s.substring(0, s.length() - 1);
                        }
                        text+=s;
                    }
//                            if (("").equals(text)) {
//                                String s = map.get("str" + j);
//                                text = s;
//                            }
//                            if (("十").equals(text)){
//                                System.out.println("===========");
//                            }
                    System.out.println(td.isMerged());
                    System.out.println(td.isFirstMerged());
                    System.out.println("------"+text);
                    System.out.println(td.isFirstVerticallyMerged());
                    System.out.println(td.isVerticallyMerged());

                    strings.add(text);
                }
//                        list.add(strings);
            }
            // 过滤多余的表格
            while (num < total) {
                it.hasNext();
                it.next();
                num += 1;
            }
        }

    }


    public static boolean isBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i=0; i<strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }


    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfDictionary dict = reader.getPageN(1);
        PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
        if (object instanceof PRStream) {
            PRStream stream = (PRStream) object;
            byte[] data = PdfReader.getStreamBytes(stream);
            stream.setData(new String(data).replace("{0}", "one for all").getBytes());
        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();

        if (Desktop.isDesktopSupported()) {
            File myFile = new File(dest);
            Desktop.getDesktop().open(myFile);
        }
    }
}
