package com.shian.app.shian_cemetery.http.imp.impl;

import com.shian.app.shian_cemetery.http.base.HttpRequestExecutor;
import com.shian.app.shian_cemetery.http.imp.MAccountManager;

/**
 * Created by Administrator on 2017/4/3.
 */

public class MAccountManagerImpl implements MAccountManager {

    public HttpRequestExecutor excutor = new HttpRequestExecutor();
    private static MAccountManager manager;

    private MAccountManagerImpl() {
    }


    public static MAccountManager getInstance() {
        if (manager == null) {
            manager = new MAccountManagerImpl();
        }
        return manager;
    }

}
