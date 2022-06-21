package com.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ：yangan
 * @date ：2022/6/21 下午8:47
 * @description：日期、时间相关获取或处理
 * @version: 1.0
 */
public class TimeUtil {

    /**
     * 获取某年某月的最后一天
     * @param date 传入日期格式 yyyyMM
     * @return 返回当月最后一天
     */
    public static String getLastDay(String date) {
        // 获取当月的天数(需完善)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date parse = null;
        try {
            parse = dateFormat.parse(date + "01");
        } catch (ParseException e) {
            System.out.println("日期转换异常");
            throw new RuntimeException(e);
        }
        String format = dateFormat.format(parse);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        calendar.add(Calendar.MONTH,1);//月增加1天
        calendar.add(Calendar.DAY_OF_MONTH,-1);//日期倒数一日,既得到本月最后一天
        Date voucherDate = calendar.getTime();
        String format1 = dateFormat.format(voucherDate);
        return format1.substring(format1.length()-2, format1.length());
    }
}