package com.example.util;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * @author Honey
 * @Date 2021/9/9
 * @Description
 */
@Component
public class ReplaceWord {

    public XWPFDocument ttt(Map<String, String> map, String fileName) {
        Resource resource = new ClassPathResource("static/" + fileName);
        InputStream inputStream = null;
        String absolutePath = null;
        try {
            absolutePath = resource.getFile().getAbsolutePath();
            System.out.println(absolutePath);
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(absolutePath));

            Iterator<XWPFParagraph> paragraphsIterator = document.getParagraphsIterator();
            while (paragraphsIterator.hasNext()) {
                XWPFParagraph next = paragraphsIterator.next();
                List<XWPFRun> runs = next.getRuns();
                for (int i = 0; i < runs.size(); i++) {
                    XWPFRun xwpfRun = runs.get(i);
                    String text = xwpfRun.getText(xwpfRun.getTextPosition());
                    System.out.println(text);
                    if (text == null || text.trim().equals("")) {
                        continue;
                    }
                    //替换
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (text.contains(key)) {
                            text = text.replace(key, value);
                            xwpfRun.setText(text, 0);
                            break;
                        }
                    }

                }
            }
            return document;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * doc类型的word用HWPFDocument类进行解析
     * docx类型的word用XWPFDocument类进行解析
     * @param srcPath word模板数据源路径
     * @param destPath word导出路径
     * @param map 关键字键值对映射
     * @throws Exception
     */
    public static void replaceWord(String srcPath, String destPath,Map<String, String> map) throws Exception {
        FileOutputStream out = null;
        FileInputStream input = null;
        try {
            if(srcPath != null && srcPath.indexOf(".docx") < 1) {
                System.out.println(srcPath);
                input = new FileInputStream(new File(srcPath));
                HWPFDocument document = new HWPFDocument(input);
                Range range = document.getRange();
                for(Map.Entry<String, String> entry : map.entrySet()) {
                    if (entry.getValue() == null) {
                        entry.setValue("  ");
                    }
                    range.replaceText(entry.getKey(), entry.getValue());
                }
                ByteArrayOutputStream ostream = new ByteArrayOutputStream();
                out = new FileOutputStream(new File(destPath));
                document.write(out);
                out.write(ostream.toByteArray());
                out.flush();
            }else {
                XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));
                // 替换段落中的指定文字
                Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
                while (itPara.hasNext()) {
                    XWPFParagraph paragraph = itPara.next();
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String oneparaString = run.getText(run.getTextPosition());
                        if (isBlank(oneparaString)){
                            continue;
                        }
                        for (Map.Entry<String, String> entry :
                                map.entrySet()) {
                            oneparaString = oneparaString.replace(entry.getKey(), entry.getValue());
                        }
                        run.setText(oneparaString, 0);
                    }

                }
                ByteArrayOutputStream ostream = new ByteArrayOutputStream();
                out = new FileOutputStream(new File(destPath));
                document.write(out);
                out.write(ostream.toByteArray());
                out.flush();
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                out.close();
            }
            if(input != null) {
                input.close();
            }
        }
    }

    public static boolean isBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i=0; i<strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }


    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfDictionary dict = reader.getPageN(1);
        PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
        if (object instanceof PRStream) {
            PRStream stream = (PRStream) object;
            byte[] data = PdfReader.getStreamBytes(stream);
            stream.setData(new String(data).replace("{0}", "one for all").getBytes());
        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();

        if (Desktop.isDesktopSupported()) {
            File myFile = new File(dest);
            Desktop.getDesktop().open(myFile);
        }
    }
}
