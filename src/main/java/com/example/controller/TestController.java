package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.FileToPdf.DocToPdf.docToPdf;
import static com.example.FileTransitionImg.Pdf2Img.tranfer;

@RestController
public class TestController {

    @RequestMapping(value = "test")
    public String test() {
        String inPath = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/test的副本.docx";
        String pngPath = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/imges/";
        tranfer(inPath,pngPath,1f);
        return "转换完成！";
    }
}
