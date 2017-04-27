package com.shian.app.shian_cemetery.activity.cemetery;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.fragment.CemeteryOrderFragment;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.model.CemeteryStructureModel;
import com.shian.app.shian_cemetery.http.params.HpCemeteryStructureParams;
import com.shian.app.shian_cemetery.http.params.HpSaveCemeteryBuildData;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryStructure;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.view.dataview.cemetery.EditTextViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.MapSelectViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.SpinnerViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.TimeSelectViewNormal;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

import static com.shian.app.shian_cemetery.staticdata.SelectDictCode.CONSULT_TRAFFICWAY;


public class BuildNewOrderActivity extends BaseActivity {
    Button mBTSubmit;

    EditTextViewNormal mUserName;
    EditTextViewNormal mUserPhone;
    EditTextViewNormal mPersonNum;
    TimeSelectViewNormal mMeetTime;
    SpinnerViewNormal mTraffic;
    SpinnerViewNormal mCemeteryName;
    MapSelectViewNormal mUserLocation;

    List<CemeteryStructureModel> ctemeryNameList = new ArrayList<>();


    boolean rolesBuild = false;
    boolean rolesTalk = false;

    HpSaveCemeteryBuildData params = new HpSaveCemeteryBuildData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_order);
        setTitle("新建预约单", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        initView();
        initData();
    }

    private void initView() {
        mBTSubmit = (Button) findViewById(R.id.bt_submit);

        mUserName = (EditTextViewNormal) findViewById(R.id.write_username);
        mUserPhone = (EditTextViewNormal) findViewById(R.id.write_userphone);
        mPersonNum = (EditTextViewNormal) findViewById(R.id.write_personnum);
        mMeetTime = (TimeSelectViewNormal) findViewById(R.id.write_meettime);
        mTraffic = (SpinnerViewNormal) findViewById(R.id.write_traffic);
        mCemeteryName = (SpinnerViewNormal) findViewById(R.id.write_cemeteryname);
        mUserLocation = (MapSelectViewNormal) findViewById(R.id.write_userlocation);

        mBTSubmit.setOnClickListener(onClickListener);
    }

    private void initData() {
        mUserLocation.setNumView(0);
        mTraffic.initSpinner(CONSULT_TRAFFICWAY);

        setRoles();
        getData();
    }


    public void getData() {
        HpCemeteryStructureParams params = new HpCemeteryStructureParams();
        params.setItemId(-1);
        params.setItemType(0);
        MHttpManagerFactory.getAccountManager().getCemeteryStructure(BuildNewOrderActivity.this, params, new HttpResponseHandler<HrGetCemeteryStructure>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetCemeteryStructure result) {
                if (result == null || result.getItems().size() == 0) {
                    ToastUtils.showShortToast(BuildNewOrderActivity.this, "没有找到数据");
                    return;
                }
                if (result != null) {
                    ctemeryNameList = result.getItems();
                    String[] list = new String[ctemeryNameList.size()];
                    for (int i = 0; i < list.length; i++) {
                        list[i] = ctemeryNameList.get(i).getName();
                    }
                    mCemeteryName.initSpinner(list);
                }
            }

            @Override
            public void onError(String message) {
            }
        });
    }

    private void setRoles() {
        //赋予权限
//        for (int i = 0; i < CemeteryFragment.LOGIN_ROLES_LIST.size(); i++) {
//            if (CemeteryFragment.LOGIN_ROLES_LIST.get(i) == 0) {
//                rolesBuild = true;
//                rolesTalk = true;
//            }
//            if (CemeteryFragment.LOGIN_ROLES_LIST.get(i) == 2) {
//                rolesBuild = true;
//            }
//            if (CemeteryFragment.LOGIN_ROLES_LIST.get(i) == 3) {
//                rolesTalk = true;
//            }
//        }
//
//        if (rolesTalk && rolesBuild) {
//            mBTSubmit.setText("进入洽谈");
//        } else if (!rolesTalk && rolesBuild) {
//            mBTSubmit.setText("提交");
//        }
        mBTSubmit.setText("提交");
        mBTSubmit.setVisibility(View.VISIBLE);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBTSubmit) {
                dataSubmit();
            }
        }
    };

    private void dataSubmit() {
        String dataName = mUserName.getData();
        String dataPhone = mUserPhone.getData();
        String dataTime = mMeetTime.getData();
        String dataLocation = mCemeteryName.getData();
        String dataTraffic = mTraffic.getData();
        String dataPersonNum = mPersonNum.getData();
        String dataUserLocation = mUserLocation.getData();
        long cemeteryId = ctemeryNameList.get(mCemeteryName.getSelectPosition()).getId();
//        if (dataName.isEmpty()) {
//            ToastUtils.show(BuildNewOrderActivity.this, "客户姓名不能为空");
//            return;
//        }
//        if (!Utils.isPhoneNumber(dataPhone)) {
//            ToastUtils.show(BuildNewOrderActivity.this, "联系电话格式错误");
//            return;
//        }
//        if (dataTime.isEmpty()) {
//            ToastUtils.show(BuildNewOrderActivity.this, "预约时间不能为空");
//            return;
//        }
//        if (dataPersonNum.isEmpty()) {
//            ToastUtils.show(BuildNewOrderActivity.this, "参观人数不能为空");
//            return;
//        }
        if (dataLocation.isEmpty()) {
            ToastUtils.showShortToast(BuildNewOrderActivity.this, "预约参观公墓不能为空");
            return;
        }
//        if (dataTraffic.isEmpty()) {
//            ToastUtils.show(BuildNewOrderActivity.this, "交通方式不能为空");
//            return;
//        }
//        if (dataUserLocation.isEmpty()) {
//            ToastUtils.show(BuildNewOrderActivity.this, "客户地址不能为空");
//            return;
//        }

        if (rolesBuild && rolesTalk) {
            params.setSubmitType(1);
        } else if (!rolesTalk && rolesBuild) {
            params.setSubmitType(0);
        }
        params.setCustomerName(dataName);
        params.setCustomerMobile(dataPhone);
        params.setPromiseTime(dataTime);
        params.setPlanCemeteryLocation(dataLocation);
        params.setTrafficWay(dataTraffic);
        params.setPersonNum(dataPersonNum);
        params.setCustomerLocation(dataUserLocation);
        params.setPlanCemeteryId(cemeteryId);
        MHttpManagerFactory.getAccountManager().saveCemeteryBuildData(BuildNewOrderActivity.this, params, new HttpResponseHandler<Object>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.showShortToast(BuildNewOrderActivity.this, "创建成功");
                CemeteryOrderFragment.isRefesh = true;
                finish();
            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
