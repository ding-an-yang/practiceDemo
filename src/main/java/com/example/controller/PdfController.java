package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

import static com.example.util.FileUtils.docToPdf;


/**
 * @author Honey
 * @Date 2021/9/9
 * @Description
 */
@RestController
public class PdfController {

    @RequestMapping(value = "/docToPdf", method = RequestMethod.GET)
    public void preview(@RequestParam("file") MultipartFile file,
                        HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        String prefix=".pdf";
        String fileName="demo";
        String userAgent = request.getHeader("User-Agent");
        InputStream fileInputStream =null;
        try {
            response.setContentType("application/pdf");
            //#getFileInputStream()方法根据自己需求实际情况，或许需要转换的word或excel文件输入流
            fileInputStream = file.getInputStream();
            //word文档
            //调用工具类将word转换pdf
            docToPdf(fileInputStream, response.getOutputStream());
            prefix = ".pdf";
            request.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "inline; filename=" + fileName + prefix);
            response.setHeader("filename", fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}