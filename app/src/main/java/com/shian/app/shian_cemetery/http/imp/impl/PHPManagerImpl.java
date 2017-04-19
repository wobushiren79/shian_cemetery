package com.shian.app.shian_cemetery.http.imp.impl;

import android.content.Context;

import com.shian.app.shian_cemetery.http.base.BaseHttpParams;
import com.shian.app.shian_cemetery.http.base.HttpRequestExecutor;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.imp.PHPManager;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetAdvertisement;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetDynamic;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetHotIssue;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetSiftListData;


/**
 * Created by Administrator on 2017/3/4.
 */

public class PHPManagerImpl implements PHPManager {
    public HttpRequestExecutor excutor = new HttpRequestExecutor();
    private static PHPManager manager;

    private PHPManagerImpl() {
    }

    public static PHPManager getInstance() {
        if (manager == null) {
            manager = new PHPManagerImpl();
        }
        return manager;
    }


    @Override
    public void getAdvertisement(Context context, BaseHttpParams params, HttpResponseHandler<PHPHrGetAdvertisement> handler) {
        excutor.requestPHPGet(context, "Home/index/advertising", PHPHrGetAdvertisement.class,
                params, handler, false);
    }

    @Override
    public void getDynamicInfo(Context context, BaseHttpParams params, HttpResponseHandler<PHPHrGetDynamic> handler) {
        excutor.requestPHPGet(context, "Home/index/dynamic", PHPHrGetDynamic.class,
                params, handler, false);
    }

    @Override
    public void getSiftListData(Context context, BaseHttpParams params, HttpResponseHandler<PHPHrGetSiftListData> handler) {
        excutor.requestPHPGet(context, "Home/index/sift", PHPHrGetSiftListData.class,
                params, handler, false);
    }

    @Override
    public void setSiftData(Context context, BaseHttpParams params, HttpResponseHandler<Object> handler) {
        excutor.requestPHPGet(context, "Home/index/siftuser", Object.class,
                params, handler, false);
    }

    @Override
    public void getHotIssue(Context context, BaseHttpParams params, HttpResponseHandler<PHPHrGetHotIssue> handler) {
        excutor.requestPHPGet(context, "Home/index/help", PHPHrGetHotIssue.class,
                params, handler, false);
    }

    @Override
    public void setOpinion(Context context, BaseHttpParams params, HttpResponseHandler<Object> handler, boolean isDialog) {
        excutor.requestPHPGet(context, "Home/index/opinion", Object.class,
                params, handler, isDialog);
    }

}