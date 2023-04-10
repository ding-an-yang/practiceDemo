package com.example.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.example.util.FileUtils.*;

@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * pdf转图片 png jpg 格式
     * @return
     */
    @PostMapping(value = "test2")
    public String PDFtoJPN(@RequestParam("file") MultipartFile file){
        String inPath = "/Users/qiush7engkeji/Desktop/工作记录/电网/附件2.pdf";
        String pngPath = "/Users/qiush7engkeji/Desktop/工作记录/电网/img/";
        //tranfer2(file, pngPath,5f);
//        String filePath = "/Users/qiush7engkeji/Desktop/工作记录/电网/常见问题(2).pdf";
//        String imagePath = "/Users/qiush7engkeji/Desktop/工作记录/电网/img/";
//        pdfToImagesFile(filePath, imagePath, 90);
        long start = System.currentTimeMillis();
        pdfToImageFile(file, pngPath, 180f);
        long end = System.currentTimeMillis();
        return "转换完成！" + (end-start) / 1000.0;
//        return null;
    }

    @PostMapping(value = "/wordTopdf")
    public String wordToPdf(@RequestParam("files") MultipartFile[] files){
        String inFile = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/test1/2.docx";
        String outFile = "/Users/qiush7engkeji/Desktop/project/ideaProject/test/替换.docx";
        //transfer(inFile, outFile);
        //copyFile("/Users/qiush7engkeji/Desktop/project/ideaProject/test/read.pdf", outFile);
        docToPdf(files);
        function2();
        return "";
    }

    @ApiOperation("下载模板")
    @GetMapping(value = "/downloadModel")
    public String downModel(HttpServletResponse response, HttpServletRequest request){
        // 读取文件流
        //InputStream is = this.getClass().getClassLoader().getResourceAsStream("/template/product.xlsx");
        // 获取文件的路径
        String path = this.getClass().getClassLoader().getResource("template/product.xlsx").getPath();
        //设置文件路径
        File file = new File(path);
        //获取文件名称
        String fileName = "product模板-2023.03.17" + ".xlsx";
        //判断文件是否存在
        if (file.exists()) {
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            response.setContentType("application/vnd.ms-excel");
            try {
                response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "utf8"));// 设置文件名
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                //file.delete();
            } catch (Exception e) {
                e.printStackTrace();
                return "下载失败";
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {
            return "模板不存在";
        }
        return "模板下载完成";
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
