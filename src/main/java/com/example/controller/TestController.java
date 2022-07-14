package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
        String inPath = "/Users/qiush7engkeji/Desktop/工作记录/电网/附件2.pdf";
        String pngPath = "/Users/qiush7engkeji/Desktop/工作记录/电网/";
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
        function2();
        return "";
    }

    /**
     * 直接通过文件名getPath来获取路径
     * @throws IOException
     */
    public void function2(){
        String path = this.getClass().getClassLoader().getResource("template/附件2.doc").getPath();
        System.out.println(path);
        String filePath = null;//如果路径中带有中文会被URLEncoder,因此这里需要解码
        try {
            filePath = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(filePath);
    }
}
