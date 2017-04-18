package com.shian.app.shian_cemetery.appenum;

/**
 * Created by Administrator on 2017/4/18.
 */

public enum OrderUserEnum {
    Burial(0, "安葬工"),
    CemeterTalk(1, "公墓洽谈");


    private int code;
    private String name;

    OrderUserEnum(int code, String name) {
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
