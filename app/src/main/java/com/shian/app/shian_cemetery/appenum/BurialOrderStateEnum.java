package com.shian.app.shian_cemetery.appenum;

/**
 * Created by zm.
 */

public enum BurialOrderStateEnum {
    singleburial(0, "安葬"),
    multiburial(1, "合葬");

    private int code;
    private String name;

    BurialOrderStateEnum(int code, String name) {
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
