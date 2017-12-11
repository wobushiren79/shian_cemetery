package com.shian.app.shian_cemetery.mvp.login.model;

import android.content.Context;

import com.shian.app.shian_cemetery.mvp.base.OnGetDataListener;

/**
 * Created by zm.
 */

public interface ISubSystemLoginModel {
    void subSystemStoreLogin(Context context, String loginKey, OnGetDataListener listener);

    void subSystemCemeteryLogin(Context context, String loginKey, OnGetDataListener listener);
}
