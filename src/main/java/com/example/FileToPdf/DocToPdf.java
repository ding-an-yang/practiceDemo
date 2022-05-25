package com.example.FileToPdf;

import com.aspose.slides.PdfOptions;
import com.aspose.words.*;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.example.FileToPdf.License.getWordLicense;
import static com.example.FileToPdf.Out.outMessage;

/**
 * word文档转pdf格式
 */
public class DocToPdf {


    public static String docToPdf(String inPath) {
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getWordLicense()) {
            System.out.println(inPath + ",解析水印失败，请重试");
            return "";
        }
        String PDFFile = "";
        try {
            long old = System.currentTimeMillis();
            PDFFile = createPDFFile(inPath);
            // 新建一个pdf文档
            File file = new File(PDFFile + ".pdf");
            FileOutputStream os = new FileOutputStream(file);
            // 验证License 是将要被转化的word文档
            Document doc = new Document(inPath);
            System.out.println("开始解析word文档" + inPath);
            TableCollection tables = doc.getFirstSection().getBody().getTables();
            for (Table table : tables) {
                RowCollection rows = table.getRows();
                table.setAllowAutoFit(false);
                for (Row row : rows) {
                    CellCollection cells = row.getCells();
                    for (Cell cell : cells) {
                        CellFormat cellFormat = cell.getCellFormat();
                        cellFormat.setFitText(false);
                        cellFormat.setWrapText(true);
                    }
                }
            }
            doc.save(os, SaveFormat.PDF);
            // 全面支持DOC, DOCX,
            // OOXML, RTF HTML,
            // OpenDocument,
            // PDF, EPUB, XPS,
            // SWF 相互转换
            long now = System.currentTimeMillis();
            os.close();
            outMessage(inPath, PDFFile + ".pdf", now, old);
        } catch (Exception e) {
            System.out.println(inPath + "转换失败，请重试");
            e.printStackTrace();
        }
        return PDFFile + ".pdf";
    }

    public static String createPDFFile(String wordFile) throws IOException {
        File file = new File(wordFile);
        //判断是否为文件夹
        if (file.isDirectory()) {
            System.out.println("请传入文件");
        }
        String parent = file.getParent();
        String name = file.getName();
        name = name.substring(0, name.lastIndexOf("."));
        return parent + "/" + name;
    }

    /**
     * 将word的内容转为html返回字符串，图片全部转为base64编码。
     * @param inPath
     * @return
     */
    public static String wordToHtml(String inPath) {
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getWordLicense()) {
            System.out.println(inPath + ",解析水印失败，请重试");
            return "";
        }
        ByteArrayOutputStream htmlStream = new ByteArrayOutputStream();
        String htmlText = "";
        try {
            Document doc = new Document(inPath);
            HtmlSaveOptions opts = new HtmlSaveOptions(SaveFormat.HTML);
            opts.setExportXhtmlTransitional(true);
            opts.setExportImagesAsBase64(true);
            opts.setExportPageSetup(true);
            doc.save(htmlStream,opts);
            htmlText = new String(htmlStream.toByteArray(), StandardCharsets.UTF_8);
            htmlStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return htmlText;
    }

    /**
     * Word文档转换
     *
     * @param inputFile
     * @param pdfFile
     */
    public static boolean word2PDF(String inputFile, String pdfFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("F:\\poi笔记.docx");
        XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
        //PdfOptions pdfOptions = PdfOptions.class;

        FileOutputStream fileOutputStream = new FileOutputStream("F:\\poi笔记.pdf");
        //PdfConverter.getInstance().convert(xwpfDocument,fileOutputStream,new PdfOptions());
        fileInputStream.close();
        fileOutputStream.close();

        return false;
    }


}
