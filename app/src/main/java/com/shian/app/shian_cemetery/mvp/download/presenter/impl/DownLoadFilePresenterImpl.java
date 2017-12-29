package com.shian.app.shian_cemetery.mvp.download.presenter.impl;

import com.shian.app.shian_cemetery.mvp.base.OnDownLoadDataListener;
import com.shian.app.shian_cemetery.mvp.download.bean.DownLoadFileBean;
import com.shian.app.shian_cemetery.mvp.download.bean.DownLoadFileResultBean;
import com.shian.app.shian_cemetery.mvp.download.model.IDownLoadFileModel;
import com.shian.app.shian_cemetery.mvp.download.model.impl.DownLoadFileModelImpl;
import com.shian.app.shian_cemetery.mvp.download.presenter.IDownLoadFilePresenter;
import com.shian.app.shian_cemetery.mvp.download.view.IDownLoadFileView;

/**
 * Created by zm.
 */

public class DownLoadFilePresenterImpl implements IDownLoadFilePresenter {
    private IDownLoadFileModel downLoadFileModel;
    private IDownLoadFileView downLoadFileView;

    public DownLoadFilePresenterImpl(IDownLoadFileView downLoadFileView) {
        this.downLoadFileView = downLoadFileView;
        downLoadFileModel=new DownLoadFileModelImpl();
    }

    @Override
    public void startDownLoad() {
        if(downLoadFileView.getContext()==null){
            downLoadFileView.showToast("没有上下文对象");
            return;
        }
        if(downLoadFileView.getDownLoadFileUrl()==null){
            downLoadFileView.showToast("没有文件下载地址");
            return;
        }
        DownLoadFileBean params=new DownLoadFileBean();
        params.setDownloadUrl(downLoadFileView.getDownLoadFileUrl());

        downLoadFileModel.startDownLoadFile(downLoadFileView.getContext(), params, new OnDownLoadDataListener<DownLoadFileResultBean>() {
            @Override
            public void downloadInProgress(long total, float progress) {
                downLoadFileView.downloadInProgress(total,progress);
            }


            @Override
            public void getDataSuccess(DownLoadFileResultBean result) {
                downLoadFileView.downloadSuccess(result);
            }


            @Override
            public void getDataFail(String msg) {
                downLoadFileView.downloadFail(msg);
            }
        });
    }
}
