package com.shian.app.shian_cemetery.mvp.login.model.impl;

import android.content.Context;


import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.mvp.base.OnGetDataListener;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginBean;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginOutBean;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginOutResultBean;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginResultBean;
import com.shian.app.shian_cemetery.mvp.login.bean.UserLoginConfig;
import com.shian.app.shian_cemetery.mvp.login.model.IUserLoginModel;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.tools.SharePerfrenceUtils;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class UserLoginModelImpl implements IUserLoginModel {


    @Override
    public void loginSystem(Context context, SystemLoginBean params, final OnGetDataListener<SystemLoginResultBean> listener) {
        AppData.cookieStore.clear();
        MHttpManagerFactory.getSystemManager().loginSystem(context, params, new HttpResponseHandler<SystemLoginResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(SystemLoginResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }

    @Override
    public void loginOutSystem(Context context, SystemLoginOutBean params, final OnGetDataListener<SystemLoginOutResultBean> listener) {
        MHttpManagerFactory.getSystemManager().loginOutSystem(context, params, new HttpResponseHandler<SystemLoginOutResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(SystemLoginOutResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }


    @Override
    public void saveLoginConfig(Context context, UserLoginConfig loginConfig) {
        SharePerfrenceUtils.setLoginShare(context, loginConfig.getUserName(), loginConfig.getPassWord(), loginConfig.isKeepAccount(), loginConfig.isAutoLogin());
    }

    @Override
    public UserLoginConfig getLoginConfig(Context context) {
        return SharePerfrenceUtils.getLoginShareSys(context);
    }


}
