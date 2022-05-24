package com.example.FileToPdf;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
}
