package com.example.util;

import com.spire.doc.*;
import com.spire.doc.Document;
import com.spire.doc.documents.DocumentObjectType;
import com.spire.doc.documents.Paragraph;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：yangan
 * @date ：2022/6/2 上午10:56
 * @description： 读取文件内容
 * @version: 1.0
 */
public class ReadFileUtil {

    /**
     * @param fileName 文件名称
     * @return 读取文件的内容写入map中，文件内容存放格式  ${NAME},内容
     */
    public static Map<String, String> readFileToMap(String fileName){
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        // 将占位符文件放在static目录下
        Resource resource = new ClassPathResource("static/"+fileName);
        Map<String, String> hasMap = null;
        try {
            String absolutePath = resource.getFile().getAbsolutePath();
            fis = new FileInputStream(absolutePath);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            hasMap = new HashMap<>();
            String line = "";
            String[] arrs = null;
            while ((line = br.readLine()) != null) {
                arrs = line.split(",");
                hasMap.put(arrs[0],arrs[1]);
            }
            br.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (UnsupportedEncodingException e) {
            System.out.println("不支持该编码格式");
        } catch (IOException e) {
            System.out.println("读取失败");
        }
        return hasMap;
    }

    /**
     * 读取word中表格内容
     * @param file
     * @return
     */
    public static List<String> doDocx(MultipartFile file){
        //用于存放预览信息
        List<String> list = new ArrayList<String>();
        try {
            //获取文件流
            InputStream is = file.getInputStream();
            //获取文件对象
            XWPFDocument doc = new XWPFDocument(is);
            //获取文件内容对象
            XWPFParagraph[]paras = doc.getParagraphs().toArray(new XWPFParagraph[0]);
            String tempTitle = null;
            for (XWPFParagraph graph : paras) {
                //获取所有的表格对象
                List tables =doc.getTables();
                //因我文档中只有一个所以这里没有去遍历，直接获取指定表格对象
                XWPFTable table = (XWPFTable) tables.get(0);
                //迭代行，默认从0开始
                for (int j = 0; j < table.getRows().size(); j++) {
                    //当前行
                    XWPFTableRow tr = table.getRow(j);
                    //用于存放一行数据，不需要可以不用
                    String rowText = "";
                    //迭代列，默认从0开始
                    for (int x = 0; x < tr.getTableCells().size(); x++) {
                        //取得单元格
                        XWPFTableCell td = tr.getCell(x);
                        //取得单元格的内容
                        String text = td.getText();
                        //自己用“ ”区分两列数据，根据自己需求 可以省略
                        if (isBlank(rowText)){
                            rowText = text;
                        }else {
                            rowText = rowText + " " + text;
                        }
                    }
                    list.add(rowText);
                }
            }
            return list;
        } catch (IOException e) {
            System.out.println("文件不存在");
        }
        return list;
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

    //读取段落
    public static void spireParaghDoc(String path) {
        Document doc = new Document(path);
        for (int i = 0; i < doc.getSections().getCount(); i++) {
            Section p = doc.getSections().get(i);
            for (int j = 0; j < p.getParagraphs().getCount(); j++) {
                Paragraph paragraph = p.getParagraphs().get(j);
                System.out.println(paragraph.getText());
            }
        }
    }

    //读取表格
    public static void spireForTableOfDoc(String path) {
        Document doc = new Document(path);
        for (int i = 0; i < doc.getSections().getCount(); i++) {
            Section n = doc.getSections().get(i);
            for (int j = 0; j < n.getBody().getChildObjects().getCount(); j++) {
                DocumentObject obj = n.getBody().getChildObjects().get(j);
                if (obj.getDocumentObjectType() == DocumentObjectType.Table) {
                    Table table = (Table) obj;
                    for (int k = 0; k < table.getRows().getCount(); k++) {
                        TableRow rows = table.getRows().get(k);
                        for (int p = 0; p < rows.getCells().getCount(); p++) {
                            for (int h = 0; h < rows.getCells().get(p).getParagraphs().getCount(); h++) {
                                Paragraph f = rows.getCells().get(p).getParagraphs().get(h);
                                System.out.println(f.getText());
                                //readFileToMap
                            }
                        }
                    }
                }
            }
        }
    }

    //读取段落
    public static void spireParaghDoc2(String path) {
        Document doc = new Document(path);
        for (int i = 0; i < doc.getSections().getCount(); i++) {
            Section p = doc.getSections().get(i);
            for (int j = 0; j < p.getParagraphs().getCount(); j++) {
                Paragraph paragraph = p.getParagraphs().get(j);
                System.out.println(paragraph.getText());
            }
        }
    }

}
