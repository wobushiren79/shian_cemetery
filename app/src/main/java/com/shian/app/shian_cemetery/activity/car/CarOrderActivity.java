package com.shian.app.shian_cemetery.activity.car;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.appenum.CarBusiTypeEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.model.CemeteryOrderModel;
import com.shian.app.shian_cemetery.http.params.HpCarBuildOrder;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.staticdata.SelectDictCode;
import com.shian.app.shian_cemetery.tools.CheckUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.view.dataview.cemetery.EditTextViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.MapSelectViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.SpinnerViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.TimeSelectViewNormal;

import okhttp3.Request;

public class CarOrderActivity extends BaseActivity implements View.OnClickListener {
    TimeSelectViewNormal mUseTime;
    EditTextViewNormal mSubmitPerson;
    EditTextViewNormal mSubmitPersonPhone;
    EditTextViewNormal mUsePerson;
    EditTextViewNormal mUsePersonPhone;
    EditTextViewNormal mPersonNum;
    MapSelectViewNormal mGetLocation;
    MapSelectViewNormal mArriveLocation;
    EditTextViewNormal mRemark;
    SpinnerViewNormal mUseReason;
    TextView mSubmit;
    private CemeteryOrderModel data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_order);
        initView();
        initData();
    }

    private void initData() {
        data = (CemeteryOrderModel) getIntent().getSerializableExtra(IntentName.INTENT_DATA);
        setTitle("申请用车", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        if (data != null) {
            mUsePerson.setData(data.getCustomerName());
            mUsePerson.setData(data.getCustomerMobile());
        }
        if (AppData.UserLoginResult.getUserData() != null) {
            mSubmitPerson.setData(AppData.UserLoginResult.getUserData().getName());
            mSubmitPersonPhone.setData(AppData.UserLoginResult.getUserData().getMobile());
        }
    }

    private void initView() {
        mUseTime = (TimeSelectViewNormal) findViewById(R.id.car_use_time);
        mSubmitPerson = (EditTextViewNormal) findViewById(R.id.car_send_person);
        mSubmitPersonPhone = (EditTextViewNormal) findViewById(R.id.car_send_person_phone);
        mUsePerson = (EditTextViewNormal) findViewById(R.id.car_user_person);
        mUsePersonPhone = (EditTextViewNormal) findViewById(R.id.car_user_person_phone);
        mUseReason = (SpinnerViewNormal) findViewById(R.id.car_use_reason);
        mPersonNum = (EditTextViewNormal) findViewById(R.id.car_personnum);
        mGetLocation = (MapSelectViewNormal) findViewById(R.id.car_get_location);
        mArriveLocation = (MapSelectViewNormal) findViewById(R.id.car_arrive_location);
        mRemark = (EditTextViewNormal) findViewById(R.id.car_remark);

        mSubmit = (TextView) findViewById(R.id.tv_submit);

        mSubmit.setOnClickListener(this);
        mSubmitPersonPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
        mUsePersonPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
        mUseReason.initSpinner(SelectDictCode.USE_CAR_REASON);

        mGetLocation.setNumView(0);
        mArriveLocation.setNumView(1);

    }


    @Override
    public void onClick(View v) {
        if (v == mSubmit) {
            submit();
        }
    }

    /**
     * 提交
     */
    private void submit() {
        if (data == null) {
            ToastUtils.showShortToast(this, "数据错误请重新登陆");
            return;
        }
        if (mUseTime.getData().isEmpty()) {
            ToastUtils.showShortToast(this, "预约用车时间不能为空");
            return;
        }
        if (mSubmitPerson.getData().isEmpty()) {
            ToastUtils.showShortToast(this, "申请人不能为空");
            return;
        }
        if (mSubmitPersonPhone.getData().isEmpty()) {
            ToastUtils.showShortToast(this, "申请人电话不能为空");
            return;
        }
        if (!CheckUtils.isPhoneNumber(mSubmitPersonPhone.getData())) {
            ToastUtils.showShortToast(this, "申请人电话格式错误");
            return;
        }
        if (mUsePerson.getData().isEmpty()) {
            ToastUtils.showShortToast(this, "用车人不能为空");
            return;
        }
        if (mUsePersonPhone.getData().isEmpty()) {
            ToastUtils.showShortToast(this, "用车人电话不能为空");
            return;
        }
        if (!CheckUtils.isPhoneNumber(mUsePersonPhone.getData())) {
            ToastUtils.showShortToast(this, "用车人电话格式错误");
            return;
        }
        if (mUseReason.getData().isEmpty()) {
            ToastUtils.showShortToast(this, "用车理由不能为空");
            return;
        }
        if (mPersonNum.getData().isEmpty()) {
            ToastUtils.showShortToast(this, "用车人数不能为空");
            return;
        }
        if (mGetLocation.getData().isEmpty()) {
            ToastUtils.showShortToast(this, "预约地不能为空");
            return;
        }
        if (mArriveLocation.getData().isEmpty()) {
            ToastUtils.showShortToast(this, "目的地不能为空");
            return;
        }

        HpCarBuildOrder params = new HpCarBuildOrder();
        params.setBusiType(CarBusiTypeEnum.cemetery_bespeakid.getText());
        params.setBusiId(data.getBespeakId());
        params.setProposerId(AppData.UserLoginResult.getUserId());
        params.setProposerName(mSubmitPerson.getData());
        params.setProposerMobile(mSubmitPersonPhone.getData());
        params.setConnecterName(mUsePerson.getData());
        params.setConnecterMobile(mUsePersonPhone.getData());
        params.setPurpose(mUseReason.getData());
        params.setSeats(mPersonNum.getData());
        params.setSource(mGetLocation.getData());
        params.setSourceLongitude(mGetLocation.getLongitude());
        params.setSourceLatitude(mGetLocation.getLatitude());
        params.setTarget(mArriveLocation.getData());
        params.setTargetLongitude(mArriveLocation.getLongitude());
        params.setTargetLatitude(mArriveLocation.getLatitude());
        params.setAppointmentTime(mUseTime.getData() + ":00");
        params.setRemark(mRemark.getData());
        MHttpManagerFactory.getAccountManager().saveCarBuildData(this, params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.showShortToast(CarOrderActivity.this, "申请成功");
                finish();
            }

            @Override
            public void onError(String message) {
                ToastUtils.showShortToast(CarOrderActivity.this, "申请失败");
            }
        });
    }
}
