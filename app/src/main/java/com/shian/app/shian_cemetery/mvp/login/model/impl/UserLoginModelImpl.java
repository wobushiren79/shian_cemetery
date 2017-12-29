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
import com.shian.app.shian_cemetery.mvp.login.presenter.ISubSystemLoginPresenter;
import com.shian.app.shian_cemetery.mvp.login.presenter.impl.SubSystemLoginPresenterImpl;
import com.shian.app.shian_cemetery.mvp.login.view.ISubSystemLoginView;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.tools.SharePerfrenceUtils;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class UserLoginModelImpl implements IUserLoginModel, ISubSystemLoginView {
    private ISubSystemLoginPresenter subSystemLoginPresenter;
    private Context context;

    private boolean isLoginCemetery = false;
    private boolean isLoginGoods = false;
    private boolean isLoginOrderCenter = false;

    private OnGetDataListener<SystemLoginResultBean> listener;
    private SystemLoginResultBean result;
    @Override
    public void loginSystem(final Context context, SystemLoginBean params, final OnGetDataListener<SystemLoginResultBean> listener) {
        this.context = context;
        this.listener = listener;
        AppData.cookieStore.clear();
        MHttpManagerFactory.getSystemManager().loginSystem(context, params, new HttpResponseHandler<SystemLoginResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(SystemLoginResultBean result) {
                //登陆子系统
                UserLoginModelImpl.this.result = result;
                subSystemLoginPresenter = new SubSystemLoginPresenterImpl(UserLoginModelImpl.this);
                subSystemLoginPresenter.loginStoreSystem();
                subSystemLoginPresenter.loginOrderCenterSystem();
                subSystemLoginPresenter.loginCemeterySystem();
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

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void loginCemeterySuccess() {
        isLoginCemetery = true;
        checkLoginAllSystem();
    }

    @Override
    public void loginCemeteryFail() {
        isLoginCemetery = true;
        checkLoginAllSystem();
    }

    @Override
    public void loginGoodsSuccess() {
        isLoginGoods = true;
        checkLoginAllSystem();
    }

    @Override
    public void loginGoodsFail() {
        isLoginGoods = true;
        checkLoginAllSystem();
    }

    @Override
    public void loginOrderCenterSuccess() {
        isLoginOrderCenter = true;
        checkLoginAllSystem();
    }

    @Override
    public void loginOrderCenterFail() {
        isLoginOrderCenter = true;
        checkLoginAllSystem();
    }

    private synchronized boolean checkLoginAllSystem() {
        if (isLoginCemetery && isLoginOrderCenter && isLoginGoods) {
            listener.getDataSuccess(result);
            return true;
        } else {
            return false;
        }
    }

}
