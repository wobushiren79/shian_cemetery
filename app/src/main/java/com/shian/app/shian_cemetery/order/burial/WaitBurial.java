package com.shian.app.shian_cemetery.order.burial;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BurialDateType;
import com.shian.app.shian_cemetery.appenum.BurialOrderDateEnum;
import com.shian.app.shian_cemetery.common.bean.BurialBuildDataBean;
import com.shian.app.shian_cemetery.order.burial.list.BurialListLayout;
import com.shian.app.shian_cemetery.tools.TimeUtils;
import com.shian.app.shian_cemetery.view.customlayout.tabchange.TitleTabChange;

/**
 * Created by Administrator on 2017/4/5.
 */

public class WaitBurial extends BaseBurialTitleView {
    View view;
    TitleTabChange mTitleTabChange;
    RelativeLayout mRLContent;
    BurialOrderDateEnum[] dateTitle = {
            BurialOrderDateEnum.TODAY,
            BurialOrderDateEnum.TOMORROW,
            BurialOrderDateEnum.THISMONTH,
            BurialOrderDateEnum.CUSTOM
    };
    private int setteleType;
    private int burialType;


    public WaitBurial(Context context, int setteleType, int burialType) {
        this(context, null);
        this.setteleType = setteleType;
        this.burialType = burialType;
    }

    public WaitBurial(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_order_waitburial, this);
        initView();
        initData();
    }

    @Override
    public void refesh() {

    }

    private void initView() {
        mTitleTabChange = (TitleTabChange) view.findViewById(R.id.titletab);
        mRLContent = (RelativeLayout) view.findViewById(R.id.rl_content);
    }

    private void initData() {
        for (int i = 0; i < dateTitle.length; i++) {
            BurialOrderDateEnum data = dateTitle[i];
            mTitleTabChange.addTab(data.getDate(),
                    data.getCode(),
                    getResources().getDimensionPixelSize(R.dimen.dimen_32dp),
                    getResources().getColor(R.color.zhy_text_color_1),
                    getResources().getColor(R.color.zhy_text_color_2),
                    getResources().getColor(R.color.zhy_text_color_1));
            mTitleTabChange.setCallBack(tabCallBack);
        }
        mTitleTabChange.setCode(BurialOrderDateEnum.TODAY.getCode());
    }

    TitleTabChange.TabCallBack tabCallBack = new TitleTabChange.TabCallBack() {
        @Override
        public void TabChange(int code, String title) {
            int dateType = BurialDateType.YEAR.getCode();
            int year = -1;
            int month = -1;
            int day = -1;
            if (code == BurialOrderDateEnum.TODAY.getCode()) {
                //今天
                dateType = BurialDateType.DAY.getCode();
            } else if (code == BurialOrderDateEnum.TOMORROW.getCode()) {
                //明天
                dateType = BurialDateType.DAY.getCode();
                year = Integer.valueOf(TimeUtils.getNextDay("yyyy"));
                month = Integer.valueOf(TimeUtils.getNextDay("MM"));
                day = Integer.valueOf(TimeUtils.getNextDay("dd"));
            } else if (code == BurialOrderDateEnum.THISMONTH.getCode()) {
                //这个月
                dateType = BurialDateType.MONTH.getCode();
            } else if (code == BurialOrderDateEnum.CUSTOM.getCode()) {
                //自定义
                dateType = BurialDateType.DAY.getCode();
            }
            mRLContent.removeAllViews();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            BurialBuildDataBean dataBean = new BurialBuildDataBean();
            dataBean.setBurialType(burialType);
            dataBean.setSetteleType(setteleType);
            dataBean.setDateType(dateType);
            dataBean.setYear(year);
            dataBean.setMonth(month);
            dataBean.setDay(day);

            BurialListLayout listLayout = new BurialListLayout(getContext(), dataBean);
            listLayout.setLayoutParams(layoutParams);

            if (code == BurialOrderDateEnum.CUSTOM.getCode()) {
                listLayout.setSearch(true);
            } else {
                listLayout.setSearch(false);
            }
            mRLContent.addView(listLayout);
        }
    };
}
