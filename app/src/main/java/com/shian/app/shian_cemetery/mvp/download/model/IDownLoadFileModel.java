package com.shian.app.shian_cemetery.mvp.download.model;

import android.content.Context;

import com.shian.app.shian_cemetery.mvp.base.OnDownLoadDataListener;
import com.shian.app.shian_cemetery.mvp.base.OnGetDataListener;
import com.shian.app.shian_cemetery.mvp.download.bean.DownLoadFileBean;

/**
 * Created by zm.
 */

public interface IDownLoadFileModel {
    void startDownLoadFile(Context context, DownLoadFileBean params, OnDownLoadDataListener listener);
}
