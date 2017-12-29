package com.shian.app.shian_cemetery.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.mvp.download.bean.DownLoadFileResultBean;
import com.shian.app.shian_cemetery.mvp.download.presenter.IDownLoadFilePresenter;
import com.shian.app.shian_cemetery.mvp.download.presenter.impl.DownLoadFilePresenterImpl;
import com.shian.app.shian_cemetery.mvp.download.view.IDownLoadFileView;
import com.shian.app.shian_cemetery.tools.ToastUtils;

/**
 * Created by zm.
 */

/**
 * 文件下载Dialog
 */
public class DownLoadDialog extends Dialog implements IDownLoadFileView {
    private View mView;
    private ProgressBar mProgressBar;

    private IDownLoadFilePresenter downLoadFilePresenter;
    private String downloadUrl;

    public DownLoadDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void show() {
        super.show();
        downLoadFilePresenter.startDownLoad();
    }

    private void initView() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_download, null);
        mProgressBar = (ProgressBar) mView.findViewById(R.id.progressbar);

        setContentView(mView);
        setCancelable(false);
    }

    private void initData() {
        downLoadFilePresenter=new DownLoadFilePresenterImpl(this);
    }


    /**
     * 设置下载地址
     * @param downloadUrl
     */
    public void setDownLoadUrl(String downloadUrl){
        this.downloadUrl=downloadUrl;
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShortToast(getContext(), msg);
    }

    @Override
    public String getDownLoadFileUrl() {
        return  downloadUrl;
    }

    @Override
    public void downloadSuccess(DownLoadFileResultBean resultBean) {

    }

    @Override
    public void downloadFail(String msg) {

    }

    @Override
    public void downloadInProgress(long total, float progress) {
        mProgressBar.setProgress((int) (progress*100));
    }
}
