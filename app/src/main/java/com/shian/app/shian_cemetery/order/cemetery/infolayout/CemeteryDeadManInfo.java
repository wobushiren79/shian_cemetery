package com.shian.app.shian_cemetery.order.cemetery.infolayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.cemetery.TalkSuccessActivity;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.params.HpCemeteryIdParams;
import com.shian.app.shian_cemetery.http.params.HpSaveCemeteryTalkSuccessDeadMan;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkSuccessDeadMan;
import com.shian.app.shian_cemetery.staticdata.SelectDictCode;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.view.dataview.cemetery.EditTextViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.SpinnerViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.TimeSelectViewNormal;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/4/12.
 */

public class CemeteryDeadManInfo extends BaseCemeteryInfo {
    EditTextViewNormal mUserName1;
    EditTextViewNormal mUserName2;
    EditTextViewNormal mUserAge1;
    EditTextViewNormal mUserAge2;
    SpinnerViewNormal mUserSex1;
    SpinnerViewNormal mUserSex2;
    SpinnerViewNormal mUserState1;
    SpinnerViewNormal mUserState2;
    EditTextViewNormal mUserCardId1;
    EditTextViewNormal mUserCardId2;
    TimeSelectViewNormal mUserDeadTime1;
    TimeSelectViewNormal mUserDeadTime2;
    EditTextViewNormal mRemark;

    TextView mTVNext;
    TextView mTVBack;

    public CemeteryDeadManInfo(Context context, long orderId, long bespeakId) {
        super(context, orderId, bespeakId, R.layout.layout_cemetery_info_deadman);
    }

    @Override
    public void getData() {
        if (beSpeakId == -1) {
            return;
        }
        HpCemeteryIdParams params = new HpCemeteryIdParams();
        params.setBespeakId(beSpeakId);
        params.setOrderId(orderId);
        MHttpManagerFactory.getAccountManager().getCemeteryTalkSuccessDeadMan(getContext(), params, new HttpResponseHandler<HrGetCemeteryTalkSuccessDeadMan>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetCemeteryTalkSuccessDeadMan result) {
                if (result.getDeadmanOneName() != null)
                    mUserName1.setData(result.getDeadmanOneName());
                if (result.getDeadmanTwoName() != null)
                    mUserName2.setData(result.getDeadmanTwoName());
                if (result.getDeadmanOneAge() != null)
                    mUserAge1.setData(result.getDeadmanOneAge());
                if (result.getDeadmanTwoAge() != null)
                    mUserAge2.setData(result.getDeadmanTwoAge());
                if (result.getDeadmanOneSex() != null)
                    mUserSex1.setData(result.getDeadmanOneSex());
                if (result.getDeadmanTwoSex() != null)
                    mUserSex2.setData(result.getDeadmanTwoSex());
                if (result.getDeadmanOneState() != null)
                    mUserState1.setData(result.getDeadmanOneState());
                if (result.getDeadmanTwoState() != null)
                    mUserState2.setData(result.getDeadmanTwoState());
                if (result.getDeadmanOneCardId() != null)
                    mUserCardId1.setData(result.getDeadmanOneCardId());
                if (result.getDeadmanTwoCardId() != null)
                    mUserCardId2.setData(result.getDeadmanTwoCardId());
                if (result.getDeadmanOneDeadTime() != null)
                    mUserDeadTime1.setData(result.getDeadmanOneDeadTime());
                if (result.getDeadmanTwoDeadTime() != null)
                    mUserDeadTime2.setData(result.getDeadmanTwoDeadTime());
                if (result.getRemark() != null)
                    mRemark.setData(result.getRemark());
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void saveData() {
        HpSaveCemeteryTalkSuccessDeadMan params = new HpSaveCemeteryTalkSuccessDeadMan();
        params.setBespeakId(beSpeakId);
        params.setOrderId(orderId);
//        params.setSaveType(changeState);
        params.setDeadmanOneName(mUserName1.getData());
        params.setDeadmanTwoName(mUserName2.getData());
        params.setDeadmanOneAge(mUserAge1.getData());
        params.setDeadmanTwoAge(mUserAge2.getData());
        params.setDeadmanOneSex(mUserSex1.getData());
        params.setDeadmanTwoSex(mUserSex2.getData());
        params.setDeadmanOneState(mUserState1.getData());
        params.setDeadmanTwoState(mUserState1.getData());
        params.setDeadmanOneCardId(mUserCardId1.getData());
        params.setDeadmanTwoCardId(mUserCardId2.getData());
        params.setDeadmanOneDeadTime(mUserDeadTime1.getData());
        params.setDeadmanTwoDeadTime(mUserDeadTime2.getData());
        params.setRemark(mRemark.getData());

        MHttpManagerFactory.getAccountManager().saveCemeteryTalkSuccessDeadMan(getContext(), params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                if (callBack != null)
                    callBack.next(new CemeteryAgentManInfo(getContext(), orderId, beSpeakId));
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
        mUserName1 = (EditTextViewNormal) view.findViewById(R.id.write_username_1);
        mUserName2 = (EditTextViewNormal) view.findViewById(R.id.write_username_2);
        mUserAge1 = (EditTextViewNormal) view.findViewById(R.id.write_userage_1);
        mUserAge2 = (EditTextViewNormal) view.findViewById(R.id.write_userage_2);
        mUserSex1 = (SpinnerViewNormal) view.findViewById(R.id.write_usersex_1);
        mUserSex2 = (SpinnerViewNormal) view.findViewById(R.id.write_usersex_2);
        mUserState1 = (SpinnerViewNormal) view.findViewById(R.id.write_userstate_1);
        mUserState2 = (SpinnerViewNormal) view.findViewById(R.id.write_userstate_2);
        mUserCardId1 = (EditTextViewNormal) view.findViewById(R.id.write_usercardid_1);
        mUserCardId2 = (EditTextViewNormal) view.findViewById(R.id.write_usercardid_2);
        mUserDeadTime1 = (TimeSelectViewNormal) view.findViewById(R.id.write_userbirth_1);
        mUserDeadTime2 = (TimeSelectViewNormal) view.findViewById(R.id.write_userbirth_2);
        mRemark = (EditTextViewNormal) view.findViewById(R.id.write_remark);

        mTVNext = (TextView) view.findViewById(R.id.tv_submit);
        mTVBack = (TextView) view.findViewById(R.id.tv_back);

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
                    callBack.next(new CemeteryPreInfo(getContext(), orderId, beSpeakId));
            }
        });
    }

    @Override
    public void initData() {
        mUserSex1.initSpinner(SelectDictCode.PERSONNEL_SEX);
        mUserSex2.initSpinner(SelectDictCode.PERSONNEL_SEX);
        mUserState1.initSpinner(SelectDictCode.DEAD_INFO_STATE);
        mUserState2.initSpinner(SelectDictCode.DEAD_INFO_STATE);
    }
}
