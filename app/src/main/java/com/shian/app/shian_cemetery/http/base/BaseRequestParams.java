package com.shian.app.shian_cemetery.http.base;

/**
 * Created by Administrator on 2017/4/7.
 */

public class BaseRequestParams<T> extends BaseHttpParams{
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
