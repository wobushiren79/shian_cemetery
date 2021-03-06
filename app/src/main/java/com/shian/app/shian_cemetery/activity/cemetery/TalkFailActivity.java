package com.shian.app.shian_cemetery.activity.cemetery;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.fragment.CemeteryOrderFragment;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.params.HpCemeteryIdParams;
import com.shian.app.shian_cemetery.http.params.HpSaveCemeteryTalkDataParams;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkData;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.staticdata.SelectData;
import com.shian.app.shian_cemetery.staticdata.SelectDictCode;
import com.shian.app.shian_cemetery.tools.ToastUtils;
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
    TextView mTVSubmit;

    HrGetCemeteryTalkData resultData;
    long beSpeakId = -1;
    long orderId = -1;

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
        mTVSubmit = (TextView) findViewById(R.id.tv_submit);

        mTVSubmit.setOnClickListener(onClickListener);


        mWriteResult.setSpinnerCallBack(new SpinnerViewNormal.SpinnerCallBack() {

            @Override
            public void itemSelected(int position, String name, SpinnerViewNormal viewNormal) {
                //预约二次洽谈
                if (position == 0) {
                    mLLOtherInfo.setVisibility(View.GONE);
                } else {
                    mLLOtherInfo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void check(SpinnerViewNormal view) {

            }
        });
    }

    private void initData() {
        beSpeakId = getIntent().getLongExtra(IntentName.INTENT_BESPEAKID, -1);
        orderId = getIntent().getLongExtra(IntentName.INTENT_ORDERID, -1);

        mWritePlanBuyType.initSpinner(SelectDictCode.TOMB_TYPE);
        mWriteDeadState1.initSpinner(SelectDictCode.DEAD_INFO_STATE);
        mWriteDeadState2.initSpinner(SelectDictCode.DEAD_INFO_STATE);
        mWriteRelation.initSpinner(SelectDictCode.MAN_RELATION);
        mWriteResult.initSpinner(SelectData.CEMETERY_RESULT);
        mWriteTraffic.initSpinner(SelectDictCode.CONSULT_TRAFFICWAY);
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
     * 点击事件
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mTVSubmit) {
                saveData();
            }
        }
    };

    /**
     * 设置数据
     */
    private void setData() {
        if (resultData.getPlanBuyCemetery() != null)
            mWritePlanBuyType.setDataDict(resultData.getPlanBuyCemetery());
        if (resultData.getUserOneState() != null)
            mWriteDeadState1.setDataDict(resultData.getUserOneState());
        if (resultData.getUserTwoState() != null)
            mWriteDeadState2.setDataDict(resultData.getUserTwoState());
        if (resultData.getRelation() != null)
            mWriteRelation.setDataDict(resultData.getRelation());
        if (resultData.getTrafficWay() != null)
            mWriteTraffic.setDataDict(resultData.getTrafficWay());
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


    /**
     * 提交数据
     */
    private void saveData() {
        HpSaveCemeteryTalkDataParams params = new HpSaveCemeteryTalkDataParams();
        params.setBespeakId(beSpeakId);
        params.setOrderedId(orderId);
        params.setPlanBuyCemetery(mWritePlanBuyType.getData());
        params.setPlanBuyMoney(mWritePlanBuyMoney.getData());
        params.setUserOneState(mWriteDeadState1.getData());
        params.setUserTwoState(mWriteDeadState2.getData());
        params.setAshLocation(mWriteAslLocation.getData());
        params.setRelation(mWriteRelation.getData());
        params.setTalkPoint(mWriteTalkPoint.getData());
        params.setTalkResult(mWriteResult.getSelectPosition());
        if (mWriteResult.getSelectPosition() == 0) {

        } else {
            params.setOrderTime(mWriteMeetTime.getData());
            params.setPersonNum(mWritePersonNum.getData());
            params.setTrafficWay(mWriteTraffic.getData());
            params.setOrderLocation(mWriteMeetLocation.getData());
        }
        params.setRemark(mWriteRemark.getData());

        MHttpManagerFactory.getAccountManager().saveCemeteryTalkInfo(TalkFailActivity.this, params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                CemeteryOrderFragment.isRefesh = true;
                ToastUtils.showShortToast(TalkFailActivity.this, "提交数据成功");
                finish();
            }

            @Override
            public void onError(String message) {
                ToastUtils.showShortToast(TalkFailActivity.this, "提交数据失败");
            }
        });
    }

}
