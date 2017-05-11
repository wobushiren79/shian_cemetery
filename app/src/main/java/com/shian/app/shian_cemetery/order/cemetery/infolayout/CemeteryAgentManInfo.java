package com.shian.app.shian_cemetery.order.cemetery.infolayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.params.HpCemeteryIdParams;
import com.shian.app.shian_cemetery.http.params.HpSaveCemeteryTalkSuccessAgentMan;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkSuccessAgentMan;
import com.shian.app.shian_cemetery.staticdata.SelectDictCode;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.view.dataview.cemetery.EditTextViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.MapSelectViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.SpinnerViewNormal;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/4/12.
 */

public class CemeteryAgentManInfo extends BaseCemeteryInfo {

    EditTextViewNormal mAgentManName;
    EditTextViewNormal mAgentManPhone;
    SpinnerViewNormal mRelation;
    MapSelectViewNormal mAgentManLocation;
    EditTextViewNormal mAgentManCardId;
    EditTextViewNormal mAgentManEmail;
    EditTextViewNormal mRemark;
    TextView mTVBack;
    TextView mTVNext;

    LinearLayout mLLButton;
    CallBack thisCallBack;

    public CemeteryAgentManInfo(Context context, long orderId, long bespeakId) {
        super(context, orderId, bespeakId, R.layout.layout_cemetery_info_agentman, false);
    }

    public void setThisCallBack(CallBack thisCallBack) {
        this.thisCallBack = thisCallBack;
    }

    @Override
    public void getData() {
        if (beSpeakId == -1) {
            return;
        }
        HpCemeteryIdParams params = new HpCemeteryIdParams();
        params.setBespeakId(beSpeakId);
        params.setOrderId(orderId);
        MHttpManagerFactory.getAccountManager().getCemeteryTalkSuccessAgentMan(getContext(), params, new HttpResponseHandler<HrGetCemeteryTalkSuccessAgentMan>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetCemeteryTalkSuccessAgentMan result) {
                if (result.getAgentmanName() != null)
                    mAgentManName.setData(result.getAgentmanName());
                if (result.getAgentmanPhone() != null)
                    mAgentManPhone.setData(result.getAgentmanPhone());
                if (result.getRelation() != null)
                    mRelation.setDataDict(result.getRelation());
                if (result.getAgentmanLocation() != null)
                    mAgentManLocation.setData(result.getAgentmanLocation());
                if (result.getAgentmanCardId() != null)
                    mAgentManCardId.setData(result.getAgentmanCardId());
                if (result.getAgentmanEmail() != null)
                    mAgentManEmail.setData(result.getAgentmanEmail());
                if (result.getRemark() != null)
                    mRemark.setData(result.getRemark());
                if (thisCallBack != null)
                    thisCallBack.initDataSuccess();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void saveData() {
        HpSaveCemeteryTalkSuccessAgentMan params = new HpSaveCemeteryTalkSuccessAgentMan();
        params.setBespeakId(beSpeakId);
//        params.setSaveType(changeState);
        params.setOrderId(orderId);
        params.setAgentmanName(mAgentManName.getData());
        params.setAgentmanPhone(mAgentManPhone.getData());
        params.setRelation(mRelation.getData());
        params.setAgentmanLocation(mAgentManLocation.getData());
        params.setAgentmanCardId(mAgentManCardId.getData());
        params.setAgentmanEmail(mAgentManEmail.getData());
        params.setRemark(mRemark.getData());
        MHttpManagerFactory.getAccountManager().saveCemeteryTalkSuccessAgentMan(getContext(), params, new HttpResponseHandler<Object>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                if (callBack != null)
                    callBack.next(null);
                ToastUtils.showShortToast(getContext(), "提交成功");
            }

            @Override
            public void onError(String message) {
                ToastUtils.showShortToast(getContext(), "提交失败");
            }
        });
    }

    @Override
    public void initView() {
        mAgentManName = (EditTextViewNormal) view.findViewById(R.id.write_agentmanname);
        mAgentManPhone = (EditTextViewNormal) view.findViewById(R.id.write_agentmanphone);
        mRelation = (SpinnerViewNormal) view.findViewById(R.id.write_relation);
        mAgentManLocation = (MapSelectViewNormal) view.findViewById(R.id.write_agentmanlocation);
        mAgentManCardId = (EditTextViewNormal) view.findViewById(R.id.write_agentmancardid);
        mAgentManEmail = (EditTextViewNormal) view.findViewById(R.id.write_agentmanemail);
        mRemark = (EditTextViewNormal) view.findViewById(R.id.write_remark);

        mTVNext = (TextView) view.findViewById(R.id.tv_submit);
        mTVBack = (TextView) view.findViewById(R.id.tv_back);
        mLLButton = (LinearLayout) view.findViewById(R.id.ll_button);

        mTVNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        mTVBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null)
                    callBack.next(new CemeteryDeadManInfo(getContext(), orderId, beSpeakId));
            }
        });
    }

    @Override
    public void initData() {
        mRelation.initSpinner(SelectDictCode.MAN_RELATION);
    }

    public void setShowMode() {
        mLLButton.setVisibility(GONE);
    }

    public interface CallBack {
        void initDataSuccess();
    }
}
