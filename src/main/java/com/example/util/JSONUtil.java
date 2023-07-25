package com.example.util;

import com.alibaba.fastjson.JSON;
import com.example.dto.DriveExamQuestionsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import springfox.documentation.spring.web.json.Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author ：yangan
 * @date ：2023/7/21 上午9:42
 * @description：
 * @version:
 */
public class JSONUtil {
    static void readWithClassLoader() throws IOException {
        String fileName = "our-20230720.json";
        ClassLoader  classLoader =  JSONUtil.class.getClassLoader();
        BufferedReader reader = null;
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder content = new StringBuilder();
        String line = reader.readLine();
        int i = 0;
        while (line != null) {//line != null
//            if (line.contains("Media:")){
//                if (line.substring(6).equals("null")){
//                    System.out.println("站位符");
//                }
//            }
            if (line.contains("Explain:")){
                System.out.println(line.substring(8));
            }
            content.append(line);
            line = reader.readLine();
            i++;
        }
        System.out.println(i);
//        return content.toString();
    }


    public static void main(String[] args) throws IOException {
        //String s = StringEscapeUtils.unescapeJava(readWithClassLoader());
//        DriveExamQuestionsDTO driveExamQuestionsDTO = JSON.parseObject(readWithClassLoader(), DriveExamQuestionsDTO.class);
//        System.out.println(driveExamQuestionsDTO);
        readWithClassLoader();
//        System.out.println(readWithClassLoader());
    }

}