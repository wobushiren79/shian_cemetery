package com.shian.app.shian_cemetery.http.imp;

import android.content.Context;

import com.shian.app.shian_cemetery.http.base.HttpManager;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
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

public interface MAccountManager extends HttpManager {
    /**
     * 公墓系统登陆
     *
     * @param context
     * @param params
     * @param handler
     */
    void loginCemetery(Context context, HpLoginParams params,
                       HttpResponseHandler<HrLoginResult> handler);

    /**
     * 公墓系统接单
     *
     * @param context
     * @param params
     * @param handler
     */
    public void acceptCemetery(Context context, HpCetemeryAcceptParams params,
                               HttpResponseHandler<Object> handler);

    /**
     * 公墓系统拒单
     *
     * @param context
     * @param params
     * @param handler
     */
    public void rejectCemetery(Context context, HpCetemeryRejectParams params,
                               HttpResponseHandler<Object> handler);

    /**
     * 获取公墓洽谈信息
     *
     * @param context
     * @param handler
     */
    public void getCemeteryTalkInfo(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkData> handler);

    /**
     * 保存公墓洽谈信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveCemeteryTalkInfo(Context context, HpSaveCemeteryTalkData params, HttpResponseHandler<Object> handler);
}
