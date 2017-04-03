package com.shian.app.shian_cemetery.appenum;

/**
 * Created by Administrator on 2017/4/3.
 */

public enum BurialTabEnum {
    WaitSettele("等待立碑"),
    WaitBuried("等待下葬"),
    BuriedOver("安葬结束");

    private String title;
    BurialTabEnum(String title) {
    this.title=title;
     }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
