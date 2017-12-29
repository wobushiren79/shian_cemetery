package com.shian.app.shian_cemetery.http.imp.impl;

import android.content.Context;
import android.util.Log;

import com.shian.app.shian_cemetery.http.base.BaseManagerImpl;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.imp.SystemManager;
import com.shian.app.shian_cemetery.mvp.base.OnGetDataListener;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginBean;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginOutBean;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginOutResultBean;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginResultBean;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.staticdata.BaseURL;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.view.dialog.CustomDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import okhttp3.Call;

/**
 * Created by zm.
 */

public class SystemManagerImpl extends BaseManagerImpl implements SystemManager {
    private static SystemManagerImpl manager;
    private CustomDialog customDialog;

    private SystemManagerImpl() {
        super();
        baseUrl = BaseURL.Login_BaseUrl;
    }


    public static SystemManagerImpl getInstance() {
        if (manager == null) {
            manager = new SystemManagerImpl();
        }
        return manager;
    }


    @Override
    public void loginSystem(Context context, SystemLoginBean params, HttpResponseHandler<SystemLoginResultBean> handler) {
        requestPost(context, "applogin", SystemLoginResultBean.class, params, handler);
    }

    @Override
    public void loginOutSystem(Context context, SystemLoginOutBean params, HttpResponseHandler<SystemLoginOutResultBean> handler) {
        requestPost(context, "app_logout", SystemLoginOutResultBean.class, params, handler);
    }

    @Override
    public void loginStoreSystem(final Context context, String loginKey, HttpResponseHandler handler) {
        String storeUrl = BaseURL.Login_Store_Url + "?" + loginKey;
        loginSubSystem(context, storeUrl, handler);
    }

    @Override
    public void loginOrderCenterSystem(Context context, String loginKey, HttpResponseHandler handler) {
        String storeUrl = BaseURL.Login_OrderCenter_Url + "?" + loginKey;
        loginSubSystem(context, storeUrl, handler);
    }

    @Override
    public void loginCemeterySystem(Context context, String loginKey, HttpResponseHandler handler) {
        String cemeteryUrl = BaseURL.Login_Cemetery_Url + "?" + loginKey;
        loginSubSystem(context, cemeteryUrl, handler);
    }



    private void loginSubSystem(final Context context, String loginUrl, final HttpResponseHandler handler) {
        if (customDialog == null || !customDialog.isShowing()) {
            customDialog = new CustomDialog(context);
            customDialog.show();
        }
        Log.v("tag", "loginUrl:" + loginUrl);
        GetBuilder getBuilder = OkHttpUtils.get();
        getBuilder.url(loginUrl);
        getBuilder.addHeader("client-Type", "wechatapp");
        RequestCall requestCall = getBuilder.build();
        requestCall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.showShortToast(context, e.getMessage());
                customDialog.cancel();
                if (customDialog != null)
                    customDialog.cancel();
                handler.onError(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.v("tag", "storeResponse:" + response);
                if (customDialog != null)
                    customDialog.cancel();
                handler.onSuccess(response);
            }
        });
    }


}
