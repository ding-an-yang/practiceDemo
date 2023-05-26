package com.example.test;


import com.example.model.Students;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.util.ExcelUtils.*;

/**
 * @author ：yangan
 * @date ：2023/5/23 下午3:58
 * @description：
 * @version:
 */
public class MainTest {
    public static void main(String[] args) {
        //定义表格数据
        String[] header0 = {"信息统计表"};
        String[] header1 = {"某某大学", "学生信息统计"};
        String[] header2 = {"学号", "姓名", "性别", "班级", "成绩"};
        String[][] data =
                {
                        new String[]{"0105", "李雷", "男", "1", "88"},
                        new String[]{"0721", "赵文", "女", "7", "92"},
                        new String[]{"1131", "陈华", "女", "11", "91"},
                        new String[]{"0418", "宋野", "男", "4", "95"},
                        new String[]{"0513", "韩梅", "女", "5", "94"},
                };
//        createWordExcel(header0, header1, header2, data);
        List<Object> dataList = new ArrayList<>();
        String fileName = "生成文件.docx";

        Students student1 = new Students();
        student1.setStu_id("123");
        student1.setData("20000fan");
        student1.setCompanyName("dsgafsaffa");
        student1.setStu_name("dasfgrrrrsadaaaaaa");

        Students student2 = new Students();
        student2.setStu_id("12322");
        student2.setData("20000fan22");
        student2.setCompanyName("dsgafsaffa22");
        student2.setStu_name("dasfgrrrrsadaaaaaa22");

        Students student3 = new Students();
        student3.setStu_id("1232233");
        student3.setData("20000fan2233");
        student3.setCompanyName("dsgafsaffa2233");
        student3.setStu_name("dasfgrrrrsadaaaaaa2233");

        dataList.add(student1);
        dataList.add(student2);
        dataList.add(student3);

        createWord(fileName, dataList);

    }

}