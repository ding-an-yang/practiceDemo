package com.example.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：yangan
 * @date ：2022/6/21 下午10:00
 * @description：类的相关获取操作
 * @version: 1.0
 */
public class ClassUtil {
    public static int count = 1;
    /**
     * java中遍历实体类，获取属性名和属性值封装进map中
     * @param model 传入的实体类对象 new Model()
     * @return 返回封装后的map
     */
    public static Map<String,String> readModel(Object model){
        Map<String,String> hasMap = new HashMap<>();
        BigDecimal bg = null;
        if (model == null){
            return null;
        }
        try {
            for (Field field : model.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                // 可能出行类型转换异常 field.get(model).toString()
                if (field.get(model) != null){
                    if (field.get(model) instanceof Double){
                        bg = new BigDecimal(String.valueOf(field.get(model)));
                        System.out.println(field.getName()+"\t"+bg.toString());
                        hasMap.put(field.getName(),(field.get(model).toString()));
                    }else {
                        System.out.println(field.getName()+"\t"+field.get(model).toString());
                        hasMap.put(field.getName(),(field.get(model).toString()));
                    }
                }
            }
        }catch (IllegalAccessException i){
            System.out.println("类型转换异常，请检查出入数据是否正确！");
        }
        return hasMap;
    }

    /**
     * 获取当前系统月 MM
     * @return
     */
    public static String getMonth(){
        DateFormat format = new SimpleDateFormat("MM");
        return format.format(new Date());
    }

    /**
     * 获取当前系统天 dd
     * @return
     */
    public static String getDay(){
        DateFormat format = new SimpleDateFormat("dd");
        return format.format(new Date());
    }

    /**
     * 日期 加 的 操作
     * @param num
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String addMonths(int num){
        Date curDate = new Date();// 获取当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);// 设置当前时间
        calendar.add(Calendar.MONTH, num); // 当前时间加指定数量的月份
        Date afterDate = calendar.getTime();// 获取加完月份后的日期
        return getTime(afterDate);
    }

    /**
     * 字符串日期格式化
     * @param date
     * @return
     */
    public static String getTime(Date date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 获取 yyyyMM 日期格式
     * @param time
     * @return
     */
    public static String getYyyyMM(String time){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        try {
            Date date = format.parse(time);
            return simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}