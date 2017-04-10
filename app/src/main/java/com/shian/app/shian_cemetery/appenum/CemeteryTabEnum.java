package com.shian.app.shian_cemetery.appenum;


/**
 * Created by Administrator on 2017/4/10.
 */

public enum CemeteryTabEnum {
    TALKORDER("洽谈", 1),
    SERVICEOVER("服务结束", 2);

    private String title;
    private int code;

    CemeteryTabEnum(String title, int code) {
        this.title = title;
        this.code = code;
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
