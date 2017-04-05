package com.shian.app.shian_cemetery.appenum;

/**
 * Created by Administrator on 2017/4/3.
 */

public enum BurialTabEnum {
    WaitSettele("等待立碑", 1),
    WaitBuried("等待下葬", 2),
    BuriedOver("安葬结束", 3);

    private String title;
    private int code;

    BurialTabEnum(String title, int code) {
        this.title = title;
        this.code=code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
