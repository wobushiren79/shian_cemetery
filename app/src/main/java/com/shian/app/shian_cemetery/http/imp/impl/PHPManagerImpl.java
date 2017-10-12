package com.shian.app.shian_cemetery.http.imp.impl;

import android.content.Context;

import com.shian.app.shian_cemetery.http.base.BaseHttpParams;
import com.shian.app.shian_cemetery.http.base.BaseManagerImpl;
import com.shian.app.shian_cemetery.http.base.HttpRequestExecutor;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.imp.PHPManager;
import com.shian.app.shian_cemetery.http.phpparams.HpGetVersion;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetAdvertisement;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetDynamic;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetHotIssue;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetSiftListData;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetVersion;
import com.shian.app.shian_cemetery.staticdata.BaseURL;


/**
 * Created by Administrator on 2017/3/4.
 */

public class PHPManagerImpl extends BaseManagerImpl implements PHPManager {
    private static PHPManager manager;

    private PHPManagerImpl() {
        super();
        baseUrl = BaseURL.PHPURL;
    }
    

    public static PHPManager getInstance() {
        if (manager == null) {
            manager = new PHPManagerImpl();
        }
        return manager;
    }


    @Override
    public void getAdvertisement(Context context, BaseHttpParams params, HttpResponseHandler<PHPHrGetAdvertisement> handler) {
        requestGet(context, "Home/index/advertising", PHPHrGetAdvertisement.class,
                params, handler, false);
    }

    @Override
    public void getDynamicInfo(Context context, BaseHttpParams params, HttpResponseHandler<PHPHrGetDynamic> handler) {
        requestGet(context, "Home/index/dynamic", PHPHrGetDynamic.class,
                params, handler, false);
    }

    @Override
    public void getSiftListData(Context context, BaseHttpParams params, HttpResponseHandler<PHPHrGetSiftListData> handler) {
        requestGet(context, "Home/index/sift", PHPHrGetSiftListData.class,
                params, handler, false);
    }

    @Override
    public void setSiftData(Context context, BaseHttpParams params, HttpResponseHandler<Object> handler) {
        requestGet(context, "Home/index/siftuser", Object.class,
                params, handler, false);
    }

    @Override
    public void getHotIssue(Context context, BaseHttpParams params, HttpResponseHandler<PHPHrGetHotIssue> handler) {
        requestGet(context, "Home/index/help", PHPHrGetHotIssue.class,
                params, handler, false);
    }

    @Override
    public void setOpinion(Context context, BaseHttpParams params, HttpResponseHandler<Object> handler, boolean isDialog) {
        requestGet(context, "Home/index/opinion", Object.class,
                params, handler, isDialog);
    }

    @Override
    public void getVersion(Context context, HpGetVersion params, HttpResponseHandler<PHPHrGetVersion> handler, boolean isDialog) {
        requestGet(context, "Home/index/edition", PHPHrGetVersion.class,
                params, handler, isDialog);
    }

}
