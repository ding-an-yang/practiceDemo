package com.example.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：yangan
 * @date ：2022/6/21 下午10:00
 * @description：类的相关获取操作
 * @version: 1.0
 */
public class ClassUtil {

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

}