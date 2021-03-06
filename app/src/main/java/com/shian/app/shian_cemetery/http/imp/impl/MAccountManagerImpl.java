package com.shian.app.shian_cemetery.http.imp.impl;

import android.content.Context;

import com.shian.app.shian_cemetery.http.base.BaseHttpParams;
import com.shian.app.shian_cemetery.http.base.BaseManagerImpl;
import com.shian.app.shian_cemetery.http.base.HttpRequestExecutor;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.imp.MAccountManager;
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
import com.shian.app.shian_cemetery.http.result.HrGetCarDetails;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryStructure;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkData;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkSuccessContract;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkSuccessAgentMan;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkSuccessDeadMan;
import com.shian.app.shian_cemetery.http.result.HrGetDictSelectData;
import com.shian.app.shian_cemetery.http.result.HrLoginResult;
import com.shian.app.shian_cemetery.http.result.HrOrderIdResult;
import com.shian.app.shian_cemetery.http.result.HrUserInfo;
import com.shian.app.shian_cemetery.staticdata.BaseURL;

/**
 * Created by Administrator on 2017/4/3.
 */

public class MAccountManagerImpl extends BaseManagerImpl implements MAccountManager {

    private static MAccountManager manager;

    private MAccountManagerImpl() {
        super();
        baseUrl = BaseURL.Cemetery_BaseUrl;
    }


    public static MAccountManager getInstance() {
        if (manager == null) {
            manager = new MAccountManagerImpl();
        }
        return manager;
    }

    @Override
    public void loginCemetery(Context context, HpLoginParams params, HttpResponseHandler<HrLoginResult> handler) {
        requestPost(context, "doLogin/marketing", HrLoginResult.class, params, handler);
    }

    @Override
    public void loginOut(Context context, HttpResponseHandler<Object> handler) {
        requestPost(context, "doLogout", Object.class, new BaseHttpParams(), handler);
    }

    @Override
    public void getUserInfo(Context context, HttpResponseHandler<HrUserInfo> handler) {
        requestPost(context, "user/info/get", HrUserInfo.class, new BaseHttpParams(),
                handler);
    }

    @Override
    public void getDictSelect(Context context, HpGetDictSelectParams params, HttpResponseHandler<HrGetDictSelectData> handler) {
        requestPost(context, "marketing/dict/items/list", HrGetDictSelectData.class, params, handler, true);
    }

    @Override
    public void getCemeteryStructure(Context context, HpCemeteryStructureParams params, HttpResponseHandler<HrGetCemeteryStructure> handler) {
        requestPost(context, "marketing/cemetery/structure/list", HrGetCemeteryStructure.class, params,
                handler, true);
    }

    @Override
    public void acceptCemetery(Context context, HpCetemeryAcceptParams params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/talk/accept", Object.class, params,
                handler, true);
    }


    @Override
    public void rejectCemetery(Context context, HpCetemeryRejectParams params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/talk/reject", Object.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkInfo(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkData> handler) {
        requestPost(context, "marketing/talk/getTalkPlan", HrGetCemeteryTalkData.class, params,
                handler, true);
    }

    @Override
    public void saveCemeteryTalkInfo(Context context, HpSaveCemeteryTalkDataParams params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/talk/saveTalkPlan", Object.class, params,
                handler, true);
    }

    @Override
    public void getCemeteryTalkSuccessContract(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessContract> handler) {
        requestPost(context, "marketing/order/buycemetery/get", HrGetCemeteryTalkSuccessContract.class, params,
                handler, true);
    }

    @Override
    public void saveCemeteryTalkSuccessContract(Context context, HpSaveCemeteryTalkSuccessContract params, HttpResponseHandler<HrOrderIdResult> handler) {
        requestPost(context, "marketing/order/buycemetery/save/or/update", HrOrderIdResult.class, params,
                handler, true);
    }

    @Override
    public void getCemeteryTalkSuccessDeadMan(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessDeadMan> handler) {
        requestPost(context, "marketing/order/dead/get", HrGetCemeteryTalkSuccessDeadMan.class, params,
                handler, true);
    }

    @Override
    public void saveCemeteryTalkSuccessDeadMan(Context context, HpSaveCemeteryTalkSuccessDeadMan params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/order/dead/save/or/update", Object.class, params,
                handler, true);
    }

    @Override
    public void getCemeteryTalkSuccessAgentMan(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessAgentMan> handler) {
        requestPost(context, "marketing/order/agentinfo/get", HrGetCemeteryTalkSuccessAgentMan.class, params,
                handler, true);
    }

    @Override
    public void saveCemeteryTalkSuccessAgentMan(Context context, HpSaveCemeteryTalkSuccessAgentMan params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/order/agentinfo/save/or/update", Object.class, params,
                handler, true);
    }

    @Override
    public void getBurialDataNumber(Context context, HttpResponseHandler<HrGetBurialNumber> handler) {
        requestPost(context, "marketing/bury/getUnburyCounts", HrGetBurialNumber.class, new BaseHttpParams(),
                handler);
    }

    @Override
    public void getBurialDataList(Context context, HpBurialDataListParams params, HttpResponseHandler<HrGetBurialListData> handler) {
        requestPost(context, "marketing/bury/getOrderDetailList", HrGetBurialListData.class, params,
                handler);
    }

    @Override
    public void getBurialDetails(Context context, HpBurialIdParams params, HttpResponseHandler<HrGetBurialDetails> handler) {
        requestGet(context, "marketing/bury/getOrderDetail", HrGetBurialDetails.class, params, handler, true);
    }

    @Override
    public void saveSetteleData(Context context, HpSaveSetteleDataParams params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/bury/updateBuriedPic", Object.class, params,
                handler, true);
    }

    @Override
    public void saveBurialData(Context context, HpSaveBurialDataParams params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/bury/updateSignFile", Object.class, params, handler, true);
    }

    @Override
    public void saveCemeteryBuildData(Context context, HpSaveCemeteryBuildData params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/bespeak/build/save", Object.class, params, handler, true);
    }

    @Override
    public void saveCarBuildData(Context context, HpCarBuildOrder params, HttpResponseHandler<Object> handler) {
        requestPost(context, "cars/apply/using", Object.class, params, handler, true);
    }

    @Override
    public void getCarBuildData(Context context, HpCarBuildOrder params, HttpResponseHandler<HrGetCarDetails> handler) {
        requestPost(context, "cars/apply/handle/info", HrGetCarDetails.class, params, handler, true);
    }


}
