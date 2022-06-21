package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.example.util.FileUtils.docToPdf;
import static com.example.util.FileUtils.pdfToImageFile;

@RestController
public class TestController {

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
