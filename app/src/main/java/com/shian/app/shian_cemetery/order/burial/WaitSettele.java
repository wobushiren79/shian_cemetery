package com.shian.app.shian_cemetery.order.burial;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BurialDateTypeEnum;
import com.shian.app.shian_cemetery.common.bean.BurialBuildDataBean;
import com.shian.app.shian_cemetery.order.burial.list.BurialListLayout;

/**
 * Created by Administrator on 2017/4/5.
 */

public class WaitSettele extends BaseBurialTitleView {
    View view;
    LinearLayout mLLContent;

    public WaitSettele(Context context, String statusType, int setteleType, int burialType, int multyBurialType) {
        super(context, statusType, setteleType, burialType, multyBurialType);
        view = View.inflate(context, R.layout.layout_order_buriedover, this);
        initView();
    }

    private void initView() {
        mLLContent = (LinearLayout) view.findViewById(R.id.ll_content);
        int dateType = BurialDateTypeEnum.DAY.getCode();
        int year = -1;
        int month = -1;
        int day = -1;

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        BurialBuildDataBean dataBean = new BurialBuildDataBean();
        dataBean.setStatusType(statusType);
        dataBean.setMultyBurialType(multyBurialType);
        dataBean.setBurialType(burialType);
        dataBean.setSetteleType(setteleType);
        dataBean.setDateType(dateType);
        dataBean.setYear(year);
        dataBean.setMonth(month);
        dataBean.setDay(day);
        BurialListLayout burialListLayout = new BurialListLayout(getContext(), dataBean);
        burialListLayout.setLayoutParams(layoutParams);
        mLLContent.addView(burialListLayout);
    }

    @Override
    public void refesh() {
        if (mLLContent.getChildAt(0) != null) {
            BurialListLayout listView = (BurialListLayout) mLLContent.getChildAt(0);
            listView.refresh();
        }
    }
}
