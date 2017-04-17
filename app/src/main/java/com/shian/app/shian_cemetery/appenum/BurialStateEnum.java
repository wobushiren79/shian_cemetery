package com.shian.app.shian_cemetery.appenum;

/**
 * Created by Administrator on 2017/4/17.
 */

public enum BurialStateEnum {
    NOT(0, "未下葬"),
    ALREADY(1, "已下葬");

    private int code;
    private String name;

    BurialStateEnum(int coed, String name) {
        this.code = coed;
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
