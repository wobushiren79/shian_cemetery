package com.shian.app.shian_cemetery.appenum;

/**
 * Created by Administrator on 2017/4/19.
 */

public enum AdvertisementEnum {
    LOADING(1, "登录广告"),
    MAIN(2, "首页广告"),
    CONTENT(3, "类容广告"),
    APP(4, "应用广告");

    private int code;
    private String name;

    AdvertisementEnum(int code, String name) {
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
