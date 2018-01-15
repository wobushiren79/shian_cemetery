package com.shian.app.shian_cemetery.mvp.download.model;

import android.content.Context;

import com.shian.app.shian_cemetery.mvp.base.OnDownLoadDataListener;
import com.shian.app.shian_cemetery.mvp.download.bean.DownLoadFileBean;
import com.zhy.http.okhttp.request.RequestCall;


/**
 * Created by zm.
 */

public interface IDownLoadFileModel {
    RequestCall startDownLoadFile(Context context, DownLoadFileBean params, OnDownLoadDataListener listener);
}
