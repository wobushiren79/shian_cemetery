package com.shian.app.shian_cemetery.appenum;

/**
 * Created by zm.
 */

public enum APPTypeEnum {
    ADVISER(1, "殡仪"),
    EXECUTOR(2, "执行"),
    CEMETERY(3, "公墓"),
    PLATFORM(4, "平台");

    private int code;
    private String name;

    APPTypeEnum(int code, String name) {
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
