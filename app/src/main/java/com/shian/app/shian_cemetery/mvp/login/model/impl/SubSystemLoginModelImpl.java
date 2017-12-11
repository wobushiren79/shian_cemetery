package com.shian.app.shian_cemetery.mvp.login.model.impl;

import android.content.Context;

import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.mvp.base.OnGetDataListener;
import com.shian.app.shian_cemetery.mvp.login.model.ISubSystemLoginModel;


/**
 * Created by zm.
 */

public class SubSystemLoginModelImpl implements ISubSystemLoginModel {
    @Override
    public void subSystemStoreLogin(Context context, String loginKey, OnGetDataListener listener) {
        MHttpManagerFactory.getSystemManager().loginStoreSystem(context, loginKey, listener);
    }

    @Override
    public void subSystemCemeteryLogin(Context context, String loginKey, OnGetDataListener listener) {
        MHttpManagerFactory.getSystemManager().loginCemeterySystem(context, loginKey, listener);
    }
}
