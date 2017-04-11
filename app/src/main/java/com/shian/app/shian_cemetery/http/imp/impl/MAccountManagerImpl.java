package com.shian.app.shian_cemetery.http.imp.impl;

import android.content.Context;

import com.shian.app.shian_cemetery.http.base.HttpRequestExecutor;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.imp.MAccountManager;
import com.shian.app.shian_cemetery.http.params.HpCemeteryIdParams;
import com.shian.app.shian_cemetery.http.params.HpCetemeryAcceptParams;
import com.shian.app.shian_cemetery.http.params.HpCetemeryRejectParams;
import com.shian.app.shian_cemetery.http.params.HpLoginParams;
import com.shian.app.shian_cemetery.http.params.HpSaveCemeteryTalkData;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkData;
import com.shian.app.shian_cemetery.http.result.HrLoginResult;

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

    @Override
    public void loginCemetery(Context context, HpLoginParams params, HttpResponseHandler<HrLoginResult> handler) {
        excutor.requestPost(context, "doLogin/cemetery", HrLoginResult.class, params, handler);
    }

    @Override
    public void acceptCemetery(Context context, HpCetemeryAcceptParams params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "marketing/talk/accept", Object.class, params,
                handler);
    }


    @Override
    public void rejectCemetery(Context context, HpCetemeryRejectParams params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "marketing/talk/reject", Object.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkInfo(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkData> handler) {
        excutor.requestPost(context, "marketing/talk/getTalkPlan", HrGetCemeteryTalkData.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkInfo(Context context, HpSaveCemeteryTalkData params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "marketing/talk/saveTalkPlan", Object.class, params,
                handler);
    }
}
