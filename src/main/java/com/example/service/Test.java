package com.example.service;

import com.example.model.Students;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
    public static void main(String[] args) {

        //{"id":"1001","name":"晓春","sex":"男","hobby":{"hobby1":"游泳","hobby2":"打篮球"}}

        String  params="{'students':[{'stu_id':'1001','stu_name':'十一郎'},"
                + "{'stu_id':'1002','stu_name':'十二郎'}],'flag':'1',"
                + "'teacher':{'tea_id':'2001','tea_name':'晓春'}}";

        //通过fromObject将json字符串翻译成JSON对象(JSONObject)
        JSONObject jsonObject = JSONObject.fromObject(params);
        //假如返回值为简单的类型，也就是单个值，使用getString("key")获取
        String flag=jsonObject.getString("flag");//结果就是简单的值类型
        System.out.println("===flag值为==="+flag);

        //如果结果是一个json对象，使用getJSONObject("key")
        JSONObject teacherObject=jsonObject.getJSONObject("teacher");
        //通过toBean方法将json字符串翻译成java中的Teaher对象并打印出结果
//        Teacher tea = (Teacher) JSONObject.toBean(teacherObject, Teacher.class);
//        System.out.println("=====老师的名字====="+tea.getTea_name());

        //如果返回值为多个对象组成的json数组，使用getJSONArray("key")
        //上面的students类型就是json数组如：students:[{},{},{}]
        JSONArray studentsjsonArray = jsonObject.getJSONArray("students");//返回的是非单一的json对象
        //通过循环遍历数据
        for (int i=0;i<studentsjsonArray.size();i++) {
            //将获取的单个json字符串翻译成JSONObject
            JSONObject a=JSONObject.fromObject(studentsjsonArray.get(i).toString());
            //将json对象翻译成Student对象
            Students stu = (Students) JSONObject.toBean(a, Students.class);
            //打印出学生的姓名
            System.out.println("=====学生姓名====="+stu.getStu_name());
        }
    }
}