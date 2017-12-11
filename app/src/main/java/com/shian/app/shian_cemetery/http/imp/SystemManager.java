package com.shian.app.shian_cemetery.http.imp;

import android.content.Context;

import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.mvp.base.OnGetDataListener;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginBean;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginOutBean;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginOutResultBean;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginResultBean;


/**
 * Created by zm.
 */

public interface SystemManager {

    /**
     * 登陸系統
     *
     * @param context
     * @param params
     * @param handler
     */
    void loginSystem(Context context, SystemLoginBean params, HttpResponseHandler<SystemLoginResultBean> handler);

    /**
     * 退出登陆
     *
     * @param context
     * @param params
     * @param handler
     */
    void loginOutSystem(Context context, SystemLoginOutBean params, HttpResponseHandler<SystemLoginOutResultBean> handler);

    /**
     * 单项系统登陆
     *
     * @param context
     * @param loginKey
     */
    void loginStoreSystem(Context context, String loginKey,OnGetDataListener listener);

    /**
     * 公墓系统登陆
     * @param context
     * @param loginKey
     */
    void loginCemeterySystem(Context context,String loginKey,OnGetDataListener listener);

}
