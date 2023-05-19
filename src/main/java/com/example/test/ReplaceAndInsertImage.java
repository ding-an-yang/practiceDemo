package com.example.test;

import com.aspose.words.*;
import com.example.model.Placeholder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static com.example.util.FileUtils.copyFileByBuffer;
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
        if(!getLicense()){
            return false;
        }
        //getLicense2();
//        getLicense();
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
        char[] array = {'a', 'd', 'e', 'f', 'h'};
        char target = 'b';
        char c = binarySearch(array, target);
        System.out.println(c);
    }

    /**
     * 第一题，在一个排序后的非递减字符数列中，找出比目标字符大的最小字符，字符大小循环，比'z'大的最小是'a'。
     * @param chars
     * @param target
     * @return
     */
    public static char binarySearch(char[] chars, char target) {
        int left = 0;
        int right = chars.length - 1;
        char ans = 'a';
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (chars[mid] > target) {
                ans = chars[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}