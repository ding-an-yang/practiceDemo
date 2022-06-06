package com.example.FileToPdf;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * @author ：yangan
 * @date ：2022/5/31 下午4:00
 * @description：
 * @version: word转pdf工具类
 */
public class WordToPdfPOI {
    public static void main(String[] args) throws Exception {
        try {

            //读取word文档
            XWPFDocument document = null;
            try (InputStream in = Files.newInputStream(Paths.get("/Users/qiush7engkeji/Desktop/project/ideaProject/test/电力交易合同-浙江省模板.docx"))) {
                document = new XWPFDocument(in);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //将word转成pdf
            fr.opensagres.poi.xwpf.converter.pdf.PdfOptions options = fr.opensagres.poi.xwpf.converter.pdf.PdfOptions.create();
            try (OutputStream outPDF = Files.newOutputStream(Paths.get("/Users/qiush7engkeji/Desktop/project/ideaProject/test/电力交易合同-浙江省模板.pdf"))) {
                PdfConverter.getInstance().convert(document, outPDF, options);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

