package com.shian.app.shian_cemetery.tools;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/4/3.
 */

public class TimeUtils {
    /**
     * 获取系统时间
     * @param dataFormat  时间格式(yyyy年 MM月 dd日 HH小时 mm分 ss秒)
     * @return
     */
    public static String getSystemTime(String dataFormat) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(dataFormat);
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }
}
