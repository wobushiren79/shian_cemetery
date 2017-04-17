package com.shian.app.shian_cemetery.appenum;

/**
 * Created by Administrator on 2017/4/17.
 */

public enum BurialDateType {
    DAY(0, "天"),
    MONTH(1, "月"),
    YEAR(2, "年");

    private int code;
    private String name;

    BurialDateType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
