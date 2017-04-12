package com.shian.app.shian_cemetery.appenum;

/**
 * Created by Administrator on 2017/4/5.
 */

public enum OrderBurialDateEnum {

    TODAY("今日", 1),
    TOMORROW("明日", 2),
    THISMONTH("本月", 3),
    CUSTOM("自定义", 4);

    private String date;
    private int code;

    OrderBurialDateEnum(String date, int code) {
        this.date = date;
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
