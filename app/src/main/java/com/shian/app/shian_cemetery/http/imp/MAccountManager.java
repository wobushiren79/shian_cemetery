package com.shian.app.shian_cemetery.http.imp;

import android.content.Context;

import com.shian.app.shian_cemetery.http.base.BaseHttpParams;
import com.shian.app.shian_cemetery.http.base.HttpManager;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.params.HpBurialDataListParams;
import com.shian.app.shian_cemetery.http.params.HpBurialIdParams;
import com.shian.app.shian_cemetery.http.params.HpCarBuildOrder;
import com.shian.app.shian_cemetery.http.params.HpCemeteryIdParams;
import com.shian.app.shian_cemetery.http.params.HpCemeteryStructureParams;
import com.shian.app.shian_cemetery.http.params.HpCetemeryAcceptParams;
import com.shian.app.shian_cemetery.http.params.HpCetemeryRejectParams;
import com.shian.app.shian_cemetery.http.params.HpGetDictSelectParams;
import com.shian.app.shian_cemetery.http.params.HpLoginParams;
import com.shian.app.shian_cemetery.http.params.HpSaveBurialDataParams;
import com.shian.app.shian_cemetery.http.params.HpSaveCemeteryBuildData;
import com.shian.app.shian_cemetery.http.params.HpSaveCemeteryTalkDataParams;
import com.shian.app.shian_cemetery.http.params.HpSaveCemeteryTalkSuccessContract;
import com.shian.app.shian_cemetery.http.params.HpSaveCemeteryTalkSuccessAgentMan;
import com.shian.app.shian_cemetery.http.params.HpSaveCemeteryTalkSuccessDeadMan;
import com.shian.app.shian_cemetery.http.params.HpSaveSetteleDataParams;
import com.shian.app.shian_cemetery.http.result.HrGetBurialDetails;
import com.shian.app.shian_cemetery.http.result.HrGetBurialListData;
import com.shian.app.shian_cemetery.http.result.HrGetBurialNumber;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryStructure;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkData;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkSuccessContract;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkSuccessAgentMan;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkSuccessDeadMan;
import com.shian.app.shian_cemetery.http.result.HrGetDictSelectData;
import com.shian.app.shian_cemetery.http.result.HrLoginResult;
import com.shian.app.shian_cemetery.http.result.HrOrderIdResult;
import com.shian.app.shian_cemetery.http.result.HrUserInfo;

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
     * 退出登陆
     *
     * @param context
     * @param handler
     */
    public void loginOut(Context context,
                         HttpResponseHandler<Object> handler);

    /**
     * 获取个人信息
     *
     * @param context
     * @param handler
     */
    public void getUserInfo(Context context,
                            HttpResponseHandler<HrUserInfo> handler);

    /**
     * 字典查询
     *
     * @param context
     * @param params
     * @param handler
     */
    void getDictSelect(Context context, HpGetDictSelectParams params,
                       HttpResponseHandler<HrGetDictSelectData> handler);

    /**
     * 获取公墓墓位结构
     */
    void getCemeteryStructure(Context context, HpCemeteryStructureParams params, HttpResponseHandler<HrGetCemeteryStructure> handler);

    /**
     * 公墓系统接单
     *
     * @param context
     * @param params
     * @param handler
     */
    void acceptCemetery(Context context, HpCetemeryAcceptParams params,
                        HttpResponseHandler<Object> handler);

    /**
     * 公墓系统拒单
     *
     * @param context
     * @param params
     * @param handler
     */
    void rejectCemetery(Context context, HpCetemeryRejectParams params,
                        HttpResponseHandler<Object> handler);

    /**
     * 获取公墓洽谈信息
     *
     * @param context
     * @param handler
     */
    void getCemeteryTalkInfo(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkData> handler);

    /**
     * 保存公墓洽谈信息
     *
     * @param context
     * @param params
     * @param handler
     */
    void saveCemeteryTalkInfo(Context context, HpSaveCemeteryTalkDataParams params, HttpResponseHandler<Object> handler);


    /**
     * 获取公墓洽谈成功合同信息
     */
    void getCemeteryTalkSuccessContract(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessContract> handler);

    /**
     * 保存公墓洽谈成功合同信息
     */
    void saveCemeteryTalkSuccessContract(Context context, HpSaveCemeteryTalkSuccessContract params, HttpResponseHandler<HrOrderIdResult> handler);

    /**
     * 获取公墓洽谈成功往生者信息
     */
    void getCemeteryTalkSuccessDeadMan(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessDeadMan> handler);

    /**
     * 保存公墓洽谈成功往生者信息
     */
    void saveCemeteryTalkSuccessDeadMan(Context context, HpSaveCemeteryTalkSuccessDeadMan params, HttpResponseHandler<Object> handler);

    /**
     * 获取公墓洽谈成功逝者信息
     */
    void getCemeteryTalkSuccessAgentMan(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessAgentMan> handler);

    /**
     * 保存公墓洽谈成功逝者信息
     */
    void saveCemeteryTalkSuccessAgentMan(Context context, HpSaveCemeteryTalkSuccessAgentMan params, HttpResponseHandler<Object> handler);

    /**
     * 获取安葬工订单数量
     *
     * @param context
     * @param handler
     */
    void getBurialDataNumber(Context context, HttpResponseHandler<HrGetBurialNumber> handler);

    /**
     * 获取安葬工列表
     *
     * @param context
     * @param params
     * @param handler
     */
    void getBurialDataList(Context context, HpBurialDataListParams params, HttpResponseHandler<HrGetBurialListData> handler);

    /**
     * 获取安葬工订单详情
     *
     * @param context
     * @param params
     * @param handler
     */
    void getBurialDetails(Context context, HpBurialIdParams params, HttpResponseHandler<HrGetBurialDetails> handler);

    /**
     * 保存立碑资料
     *
     * @param context
     * @param params
     * @param handler
     */
    void saveSetteleData(Context context, HpSaveSetteleDataParams params, HttpResponseHandler<Object> handler);
    /**
     * 保存安葬资料
     *
     * @param context
     * @param params
     * @param handler
     */
    void saveBurialData(Context context, HpSaveBurialDataParams params, HttpResponseHandler<Object> handler);

    /**
     * 保存公墓预约单信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveCemeteryBuildData(Context context, HpSaveCemeteryBuildData params, HttpResponseHandler<Object> handler);


    /**
     * 保存派车单信息
     * @param context
     * @param params
     * @param handler
     */
    void saveCarBuildData(Context context, HpCarBuildOrder params,HttpResponseHandler<Object> handler);


}
