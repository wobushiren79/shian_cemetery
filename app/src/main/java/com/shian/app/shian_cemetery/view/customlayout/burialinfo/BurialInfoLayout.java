package com.shian.app.shian_cemetery.view.customlayout.burialinfo;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.BaseHttpParams;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.result.HrGetBurialNumber;
import com.shian.app.shian_cemetery.http.result.HrUserInfo;
import com.shian.app.shian_cemetery.staticdata.BaseURL;
import com.shian.app.shian_cemetery.tools.TimeUtils;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/4/3.
 */

public class BurialInfoLayout extends LinearLayout {
    View view;

    TextView mTVTime;
    RoundedImageView mIVIcon;
    TextView mTVName;
    TextView mTVTodayNum;
    TextView mTVMonthWaitNum;
    TextView mTVMonthReadyNum;

    LinearLayout mLLNumber;

    public BurialInfoLayout(Context context) {
        this(context, null);
    }

    public BurialInfoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_burial_info, this);
        initView();
        initData();
        getData();
    }

    private void initView() {
        mTVTime = (TextView) view.findViewById(R.id.tv_time);
        mIVIcon = (RoundedImageView) view.findViewById(R.id.iv_icon);
        mTVName = (TextView) view.findViewById(R.id.tv_name);

        mTVTodayNum = (TextView) view.findViewById(R.id.tv_today_num);
        mTVMonthWaitNum = (TextView) view.findViewById(R.id.tv_month_wait_num);
        mTVMonthReadyNum = (TextView) view.findViewById(R.id.tv_month_ready_num);
        mLLNumber = (LinearLayout) view.findViewById(R.id.ll_burial_num);

    }

    private void initData() {
        mTVTime.setText(TimeUtils.getSystemTime("yyyy.MM.dd"));

    }

    private void getData() {
        MHttpManagerFactory.getAccountManager().getBurialDataNumber(getContext(), new HttpResponseHandler<HrGetBurialNumber>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetBurialNumber result) {
                Long beriedThisMonth=result.getBeriedThisMonth();
                Long unberyThisMonth=result.getUnberyThisMonth();
                Long unberyToday=result.getUnberyToday();
                mTVTodayNum.setText(unberyToday+"");
                mTVMonthWaitNum.setText(unberyThisMonth+"");
                mTVMonthReadyNum.setText(beriedThisMonth+"");
            }

            @Override
            public void onError(String message) {

            }
        });

//        MHttpManagerFactory.getAccountManager().getUserInfo(getContext(), new HttpResponseHandler<HrUserInfo>() {
//
//            @Override
//            public void onStart(Request request, int id) {
//
//            }
//
//            @Override
//            public void onSuccess(final HrUserInfo result) {
//                if (!result.getHeadImg().equals(""))
//                    ImageLoader.getInstance().displayImage(BaseURL.OSSURL + result.getHeadImg(), mIVIcon);
//                mTVName.setText(result.getName());
//            }
//
//            @Override
//            public void onError(String message) {
//
//            }
//        });

    }

}
