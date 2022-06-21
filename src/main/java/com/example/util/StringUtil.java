package com.example.util;

/**
 * @author ：yangan
 * @date ：2022/6/21 下午9:02
 * @description：字符串相关处理
 * @version: 1.0
 */
public class StringUtil {

    /**
     * 用于判断字符串是否为空
     * @param str 需要判断的字符串
     * @return "" null 返回true  其他为false，不为空
     */
    public static boolean isBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i=0; i<strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}