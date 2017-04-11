package com.shian.app.shian_cemetery.activity.cemetery;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.params.HpCemeteryIdParams;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkData;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.staticdata.SelectData;
import com.shian.app.shian_cemetery.view.dataview.cemetery.EditTextViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.MapSelectViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.SpinnerViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.TimeSelectViewNormal;

import okhttp3.Request;

public class TalkFailActivity extends BaseActivity {
    SpinnerViewNormal mWritePlanBuyType;
    EditTextViewNormal mWritePlanBuyMoney;
    SpinnerViewNormal mWriteDeadState1;
    SpinnerViewNormal mWriteDeadState2;
    MapSelectViewNormal mWriteAslLocation;
    SpinnerViewNormal mWriteRelation;
    EditTextViewNormal mWriteTalkPoint;
    SpinnerViewNormal mWriteResult;
    SpinnerViewNormal mWriteTraffic;
    TimeSelectViewNormal mWriteMeetTime;
    EditTextViewNormal mWritePersonNum;
    MapSelectViewNormal mWriteMeetLocation;
    EditTextViewNormal mWriteRemark;

    LinearLayout mLLOtherInfo;

    HrGetCemeteryTalkData resultData;
    long beSpeakId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_fail);
        setTitle("洽谈失败", BaseTitleEnum.BACKNORMALTITLE.getTitleType());

        initView();
        initData();
        getData();
    }


    private void initView() {
        mWritePlanBuyType = (SpinnerViewNormal) findViewById(R.id.write_planbuytype);
        mWritePlanBuyMoney = (EditTextViewNormal) findViewById(R.id.write_planbuymoney);
        mWriteDeadState1 = (SpinnerViewNormal) findViewById(R.id.write_deadman_state_1);
        mWriteDeadState2 = (SpinnerViewNormal) findViewById(R.id.write_deadman_state_2);
        mWriteAslLocation = (MapSelectViewNormal) findViewById(R.id.write_asllocation);
        mWriteRelation = (SpinnerViewNormal) findViewById(R.id.write_relation);
        mWriteTalkPoint = (EditTextViewNormal) findViewById(R.id.write_talkpoint);
        mWriteResult = (SpinnerViewNormal) findViewById(R.id.write_result);
        mWriteTraffic = (SpinnerViewNormal) findViewById(R.id.write_traffic);
        mWriteMeetTime = (TimeSelectViewNormal) findViewById(R.id.write_time);
        mWritePersonNum = (EditTextViewNormal) findViewById(R.id.write_personnum);
        mWriteMeetLocation = (MapSelectViewNormal) findViewById(R.id.write_meetlocation);
        mWriteRemark = (EditTextViewNormal) findViewById(R.id.write_remark);

        mLLOtherInfo = (LinearLayout) findViewById(R.id.ll_other_info);
    }

    private void initData() {
        beSpeakId = getIntent().getLongExtra(IntentName.INTENT_BESPEAKID, -1);

        mWritePlanBuyType.initSpinner(SelectData.CEMETERY_TYPE);
        mWriteDeadState1.initSpinner(SelectData.CEMETERY_STATE);
        mWriteDeadState2.initSpinner(SelectData.CEMETERY_STATE);
        mWriteRelation.initSpinner(SelectData.CEMETERY_RELATION);
        mWriteResult.initSpinner(SelectData.CEMETERY_RESULT);
        mWriteTraffic.initSpinner(SelectData.CEMETERY_TRAFFICEWAY);
    }

    /**
     * 获取数据
     */
    private void getData() {
        HpCemeteryIdParams params = new HpCemeteryIdParams();
        params.setBespeakId(beSpeakId);
        MHttpManagerFactory.getAccountManager().getCemeteryTalkInfo(TalkFailActivity.this, params, new HttpResponseHandler<HrGetCemeteryTalkData>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetCemeteryTalkData result) {
                resultData = result;
                setData();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    /**
     * 设置数据
     */
    private void setData() {
        if (resultData.getPlanBuyCemetery() != null)
            mWritePlanBuyType.setData(resultData.getPlanBuyCemetery());
        if (resultData.getUserOneState() != null)
            mWriteDeadState1.setData(resultData.getUserOneState());
        if (resultData.getUserTwoState() != null)
            mWriteDeadState2.setData(resultData.getUserTwoState());
        if (resultData.getRelation() != null)
            mWriteRelation.setData(resultData.getRelation());
        if (resultData.getTrafficWay() != null)
            mWriteTraffic.setData(resultData.getTrafficWay());
        if (resultData.getPersonNum() != null)
            mWritePersonNum.setData(resultData.getPersonNum());
        if (resultData.getOrderTime() != null)
            mWriteMeetTime.setData(resultData.getOrderTime());
        if (resultData.getOrderLocation() != null)
            mWriteMeetLocation.setData(resultData.getOrderLocation());
        if (resultData.getRemark() != null) {
            mWriteRemark.setData(resultData.getRemark());
        }
        mWriteResult.setData(resultData.getTalkResult());
    }

}
