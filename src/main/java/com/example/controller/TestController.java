package com.example.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.example.FileToPdf.DocToPdf.*;
import static com.example.FileTransitionImg.Pdf2Img.*;

@RestController
public class TestController {

    /**
     * word转pdf，pdf转图片
     * word转pdf过程以流的形式，word中有表格和图片转出会有格式错乱问题
     * @return
     */
    @RequestMapping(value = "test")
    public String test() {
        String inPath = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/副本报表SQL.docx";
        String pngPath = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/imges/";
        tranfer(inPath,pngPath,1f);
        return "转换完成！";
    }

    /**
     * pdf转图片 png jpg 格式
     * @return
     */
    @RequestMapping(value = "test2")
    public String PDFtoJPN(@RequestParam("file") MultipartFile file){
        String inPath = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/副本报表SQL2.pdf";
        String pngPath = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/imges/";
        //tranfer2(file, pngPath,5f);
        long start = System.currentTimeMillis();
        pdfToImageFile(file, pngPath, 180f);
        long end = System.currentTimeMillis();
        return "转换完成！" + (end-start) / 1000.0;
    }

    @RequestMapping(value = "/wordTopdf")
    public String wordToPdf(@RequestParam("files") MultipartFile[] files){
        String inFile = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/test1/2.docx";
        String outFile = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/替换.docx";
        //transfer(inFile, outFile);
        //copyFile("/Users/qiush7engkeji/Desktop/project/ideaProject/test/read.pdf", outFile);
        docToPdf(files);
        return "";
    }
}
