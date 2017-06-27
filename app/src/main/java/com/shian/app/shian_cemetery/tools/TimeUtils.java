package com.shian.app.shian_cemetery.tools;

import java.text.ParseException;
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

    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // date类型转换为long类型
    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
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
