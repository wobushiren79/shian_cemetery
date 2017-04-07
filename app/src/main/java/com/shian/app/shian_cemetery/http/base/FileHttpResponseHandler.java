package com.shian.app.shian_cemetery.http.base;

public interface FileHttpResponseHandler<T>
{
    public void onStart();

    public void onSuccess(T t);

    public void onError(String message);

    public void onProgress(long total, float progress);
}
