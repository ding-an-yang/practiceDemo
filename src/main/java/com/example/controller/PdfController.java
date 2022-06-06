package com.example.controller;


import com.example.util.ReplaceWord;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Honey
 * @Date 2021/9/9
 * @Description
 */
@RestController
public class PdfController {

    @Autowired
    private ReplaceWord generatePdf;

    @RequestMapping("/testExport")
    public void gen(HttpServletResponse response) throws Exception {
        OutputStream outputStream = response.getOutputStream();
        Map<String, String> map = new HashMap<>();
        String fileName = "";
        XWPFDocument ttt = generatePdf.ttt(map, fileName);
        StringBuilder contentDispositionValue = new StringBuilder();
        contentDispositionValue.append("attachment; filename=")
                .append("testFile.docx")
                .append(";")
                .append("filename*=")
                .append("utf-8''")
                .append("testFile.docx");

        response.setHeader("Content-disposition", contentDispositionValue.toString());
        ttt.write(outputStream);
    }
}