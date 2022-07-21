package com.example.controller;

import com.aliyun.ocr_api20210707.models.RecognizeBusinessLicenseResponseBody;
import com.example.component.AliOCRClinent;
import com.example.util.FileUtils;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.*;
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

    /**
     * 文件下载
     */
    @PostMapping(value = "/getConfirmLetter")
    public void getConfirmLetter() {
        InputStream inputStream = this.getClass().getResourceAsStream("/template/附件2.pdf");
        String fileName = "确认邀请函.pdf";
        FileUtils.exportFile(fileName, inputStream);
    }

    @PostMapping(value = "/ocr")
    public RecognizeBusinessLicenseResponseBody testOcr() {
        try {
            return AliOCRClinent.testOcr();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}