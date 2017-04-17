package com.shian.app.shian_cemetery.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/4/3.
 */

public class TimeUtils {
    /**
     * 获取系统时间
     *
     * @param dataFormat 时间格式(yyyy年 MM月 dd日 HH小时 mm分 ss秒)
     * @return
     */
    public static String getSystemTime(String dataFormat) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(dataFormat);
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }


    /**
     * 時間戳轉換
     *
     * @param time
     * @return
     */
    public static String formatTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        return format.format(new Date(time));
    }

    public static String formatTime(long time, String data) {
        SimpleDateFormat format = new SimpleDateFormat(data, Locale.CHINA);
        return format.format(new Date(time));
    }


    /**
     * 获取当前系统下一天日期
     *
     * @param dataFormat 时间格式(yyyy年 MM月 dd日 HH小时 mm分 ss秒)
     * @return
     */
    public static String getNextDay(String dataFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
        return sdf.format(date);
    }
}
