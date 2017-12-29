package com.shian.app.shian_cemetery.mvp.download.model.impl;

import android.content.Context;

import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.FileHttpResponseHandler;
import com.shian.app.shian_cemetery.mvp.base.OnDownLoadDataListener;
import com.shian.app.shian_cemetery.mvp.download.bean.DownLoadFileBean;
import com.shian.app.shian_cemetery.mvp.download.bean.DownLoadFileResultBean;
import com.shian.app.shian_cemetery.mvp.download.model.IDownLoadFileModel;

import java.io.File;

/**
 * Created by zm.
 */

public class DownLoadFileModelImpl implements IDownLoadFileModel {

    @Override
    public void startDownLoadFile(Context context, DownLoadFileBean params, final OnDownLoadDataListener listener) {
        String downloadUrl=params.getDownloadUrl();
        MHttpManagerFactory.getFileManager().downloadFile(context, downloadUrl, new FileHttpResponseHandler<File>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(File file) {
                DownLoadFileResultBean resultBean=new DownLoadFileResultBean();
                resultBean.setDownloadFile(file);
                listener.getDataSuccess(resultBean);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }

            @Override
            public void onProgress(long total, float progress) {
                listener.downloadInProgress(total,progress);
            }
        });
    }

}
