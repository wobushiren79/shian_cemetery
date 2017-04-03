package com.shian.app.shian_cemetery.http.base;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/4/3.
 */

public abstract class HttpResponseHandler<T> {
    public abstract void onStart(Request request, int id);

    public abstract void onSuccess(T result);

    public abstract void onError(String message);

    public void onOffLine(T result) {
    }
}