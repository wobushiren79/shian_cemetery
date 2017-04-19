package com.shian.app.shian_cemetery.appenum;

/**
 * Created by Administrator on 2017/4/19.
 */

public enum FindEnum {
    DEFAULTFIND(1, "默认"),
    COLLECTIONFIND(2, "收藏");

    private int code;
    private String name;

    FindEnum(int code, String name) {
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
