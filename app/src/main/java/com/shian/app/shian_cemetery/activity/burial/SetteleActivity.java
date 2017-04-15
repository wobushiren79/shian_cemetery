package com.shian.app.shian_cemetery.activity.burial;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.fragment.OrderFragment;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.bean.BurialDeadInfoModel;
import com.shian.app.shian_cemetery.http.bean.BurialInfoModel;
import com.shian.app.shian_cemetery.http.bean.BurialLocationModel;
import com.shian.app.shian_cemetery.http.params.HpBurialIdParams;
import com.shian.app.shian_cemetery.http.params.HpSaveSetteleDataParams;
import com.shian.app.shian_cemetery.http.result.HrGetBurialDetails;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.tools.TimeUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.view.dataview.burial.EditLayout;
import com.shian.app.shian_cemetery.view.dataview.burial.PhotoUpDataLayout;
import com.shian.app.shian_cemetery.view.dataview.burial.TextReadLayout;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class SetteleActivity extends BaseActivity {
    TextReadLayout mTRLocation;
    TextReadLayout mTRSetteleTime;
    TextReadLayout mTRUserName;
    TextReadLayout mTRBuildTime;
    TextReadLayout mTRBuildState;

    TextView mTVSubmit;
    EditLayout mTRRemark;
    LinearLayout mLLPicContent;

    private BurialInfoModel buryInfo;//安葬信息
    private BurialDeadInfoModel deadInfo;//死者信息
    private BurialLocationModel tombPosition;//安葬墓位

    long orderId = 1;

    List<PhotoUpDataLayout> listPic = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settele);
        setTitle("立碑", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
//        orderId = getIntent().getLongExtra(IntentName.INTENT_ORDERID, -1);
        initView();
        getData();
    }

    private void initView() {
        mTRLocation = (TextReadLayout) findViewById(R.id.tr_location);
        mTRSetteleTime = (TextReadLayout) findViewById(R.id.tr_time);
        mTRUserName = (TextReadLayout) findViewById(R.id.tr_username);
        mTRBuildTime = (TextReadLayout) findViewById(R.id.tr_buildtime);
        mTRBuildState = (TextReadLayout) findViewById(R.id.tr_buildstate);
        mTRRemark = (EditLayout) findViewById(R.id.tr_remark);
        mTVSubmit = (TextView) findViewById(R.id.tv_submit);
        mLLPicContent = (LinearLayout) findViewById(R.id.ll_piccontent);
        mTVSubmit.setOnClickListener(onClickListener);

        addPicView();
    }

    private void addPicView() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.dimen_300dp));
        layoutParams.bottomMargin = getResources().getDimensionPixelOffset(R.dimen.dimen_24dp);
        final PhotoUpDataLayout picView = new PhotoUpDataLayout(SetteleActivity.this);
        picView.setCallBack(new PhotoUpDataLayout.CallBack() {
            @Override
            public void remove() {
                mLLPicContent.removeView(picView);
                listPic.remove(picView);
            }

            @Override
            public void add() {
                addPicView();
            }
        });
        picView.setLayoutParams(layoutParams);
        mLLPicContent.addView(picView);
        listPic.add(picView);
    }

    /**
     * 设置数据
     */
    private void initData() {
        if (tombPosition != null) {
            StringBuilder location = new StringBuilder();
            String cemeteryName = tombPosition.getCemeteryName() + "\n";
            String tombName = tombPosition.getTombName() + "";
            String parkName = tombPosition.getParkName() + "";
            String rowName = tombPosition.getRow() + "排";
            String numName = tombPosition.getNum() + "号";
            location.append(cemeteryName);
            location.append(tombName);
            location.append(parkName);
            location.append(rowName);
            location.append(numName);
            mTRLocation.setData(location.toString());
        }
        if (buryInfo != null) {
            if (buryInfo.getStoneStatus() == 1) {
                ToastUtils.showLongToast(SetteleActivity.this, "此订单已被操作");
                finish();
            }

            if (buryInfo.getBuryDatePre() != 0) {

            }
            if (buryInfo.getStoneDatePre() != 0) {
                mTRSetteleTime.setData(TimeUtils.formatTime(buryInfo.getStoneDatePre()));
            } else {
                mTRSetteleTime.setData("无详细日期");
            }
            mTRBuildTime.setData("test");
            mTRBuildState.setData("test");
        }
        if (deadInfo != null) {
            StringBuilder name = new StringBuilder();
            if (deadInfo.getDeadmanOneName() != null && !deadInfo.getDeadmanOneName().isEmpty()) {
                name.append(deadInfo.getDeadmanOneName());
            }
            if (deadInfo.getDeadmanTwoName() != null && !deadInfo.getDeadmanTwoName().isEmpty()) {
                name.append(" | " + deadInfo.getDeadmanTwoName());
            }
            mTRUserName.setData(name.toString());
        }
    }

    /**
     * 获取数据
     */
    private void getData() {
        HpBurialIdParams params = new HpBurialIdParams();
        params.setContent(orderId);
        MHttpManagerFactory.getAccountManager().getBurialDetails(this, params, new HttpResponseHandler<HrGetBurialDetails>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetBurialDetails result) {
                buryInfo = result.getBuryInfo();
                deadInfo = result.getDeadInfo();
                tombPosition = result.getTombPosition();
                initData();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    /**
     * 保存数据
     */
    public void saveData() {
        //图片检测
        List<String> picUrls = new ArrayList<>();
        for (PhotoUpDataLayout picView : listPic) {
            if (picView.isLoading) {
                ToastUtils.showLongToast(SetteleActivity.this, "图片正在上传中，请稍等");
                return;
            }
            if (picView.fileUrl != null) {
                picUrls.add(picView.fileUrl);
            }
        }
        if (picUrls.size() == 0) {
            ToastUtils.showLongToast(SetteleActivity.this, "还没有上传图片");
            return;
        }

        //图片地址组合
        StringBuilder picBuffer = new StringBuilder();
        for (int i = 0; i < picUrls.size(); i++) {
            if (i != 0)
                picBuffer.append(",");
            picBuffer.append(picUrls.get(i));
        }
        HpSaveSetteleDataParams params = new HpSaveSetteleDataParams();
        params.setOrderId(orderId);
        params.setBuriedFileIds(picBuffer.toString());
        params.setRemark(mTRRemark.getData());
        MHttpManagerFactory.getAccountManager().saveSetteleData(SetteleActivity.this, params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                finish();
                OrderFragment.isRefesh = true;
                ToastUtils.showShortToast(SetteleActivity.this, "提交成功");
            }

            @Override
            public void onError(String message) {
                ToastUtils.showShortToast(SetteleActivity.this, "提交失败");
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mTVSubmit) {
                saveData();
            }
        }
    };
}
