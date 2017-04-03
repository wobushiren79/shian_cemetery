package com.shian.app.shian_cemetery.view.customlayout.burialinfo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.tools.TimeUtils;

/**
 * Created by Administrator on 2017/4/3.
 */

public class BurialInfoLayout extends LinearLayout {
    View view;

    TextView mTVTime;
    RoundedImageView mIVIcon;
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
    }


    private void initView() {

        mTVTime = (TextView) view.findViewById(R.id.tv_time);
        mIVIcon = (RoundedImageView) view.findViewById(R.id.iv_icon);
        mTVTodayNum = (TextView) view.findViewById(R.id.tv_today_num);
        mTVMonthWaitNum = (TextView) view.findViewById(R.id.tv_month_wait_num);
        mTVMonthReadyNum = (TextView) view.findViewById(R.id.tv_month_ready_num);
        mLLNumber= (LinearLayout) view.findViewById(R.id.ll_burial_num);

    }

    private void initData() {
        mTVTime.setText(TimeUtils.getSystemTime("yyyy.MM.dd"));

    }

}
