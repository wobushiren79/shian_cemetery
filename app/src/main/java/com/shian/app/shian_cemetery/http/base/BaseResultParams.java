package com.shian.app.shian_cemetery.http.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/3.
 */

public class BaseResultParams<T> implements Serializable {
    private int code;
    private String message;
    private String serialNum;
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }


}

