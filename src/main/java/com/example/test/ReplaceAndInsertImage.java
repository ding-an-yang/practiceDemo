package com.example.test;

import com.aspose.words.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.example.util.License.getLicense;
import static com.example.util.License.getLicense2;

/**
 * @author ：yangan
 * @date ：2022/8/31 下午2:06
 * @description：
 * @version:
 */
public class ReplaceAndInsertImage implements IReplacingCallback {
    public String url;
    public ReplaceAndInsertImage(String url){
        this.url = url;
    }
    @Override
    public int replacing(ReplacingArgs e) throws Exception {
        //获取当前节点
        Node node = e.getMatchNode();
        //获取当前文档
        Document document =  (Document) node.getDocument();
        DocumentBuilder builder = new DocumentBuilder(document);
        //将光标移动到指定节点
        builder.moveTo(node);
        //插入图片
        builder.insertImage(url);
        return ReplaceAction.REPLACE;
    }

    /**
     * aspose word 替换文字/图片。
     * @param url  原文件路径
     * @param saveurl 保存路径
     * @param params {key:value,"$年份$":"2020","$签字1$":"$pic:d/qz1.jpg"} key 为要被替换的字符串，value 为替换为的字符串，替换为图片，则value为$pic:+图片路径
     * @return
     */
    public static boolean replace(String url,String saveurl,Map<String, String> params){
        if(!getLicense2()){
            return false;
        }
        File file = new File(url);
        if(!file.exists()){
            return false;
        }
        try {
            Document doc = new Document(url);
            Range range = doc.getRange();
            for(String key:params.keySet()){
                String value = params.get(key);
                if(value.startsWith("$pic:")){
                    value = value.substring(5);
                    key = key.replace("\\", "\\\\");
                    key = key.replace("$", "\\$");
                    key = key.replace("[", "\\[");
                    key = key.replace("]", "\\]");
                    key = key.replace("(", "\\(");
                    key = key.replace(")", "\\)");
                    key = key.replace("|", "\\|");
                    key = key.replace("+", "\\+");
                    key = key.replace("?", "\\?");
                    key = key.replace("*", "\\*");
                    range.replace( Pattern.compile(key),new ReplaceAndInsertImage(value) , false);
                }else{
                    range.replace(key, value, true, false);
                }
            }
            doc.save(saveurl);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("[$pic$]", "$pic:https://s4s-images.oss-cn-shanghai.aliyuncs.com/electricity/img/df7ba48520564ac58679c1921bd0e1bc.png");
//        replace("/Users/qiush7engkeji/Desktop/", "D:/test/aspose替换后.docx", params);
        replace("/Users/qiush7engkeji/Desktop/峰谷价附件2的副本.doc", "/Users/qiush7engkeji/Desktop/替换后.doc", params);
    }
}