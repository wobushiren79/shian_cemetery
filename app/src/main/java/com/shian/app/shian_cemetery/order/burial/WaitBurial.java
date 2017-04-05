package com.shian.app.shian_cemetery.order.burial;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.OrderBurialDate;
import com.shian.app.shian_cemetery.order.burial.list.BurialListLayout;
import com.shian.app.shian_cemetery.view.customlayout.tabchange.TitleTabChange;

/**
 * Created by Administrator on 2017/4/5.
 */

public class WaitBurial extends LinearLayout {
    View view;
    TitleTabChange mTitleTabChange;
    RelativeLayout mRLContent;
    OrderBurialDate[] dateTitle = {
            OrderBurialDate.TODAY,
            OrderBurialDate.TOMORROW,
            OrderBurialDate.THISMONTH,
            OrderBurialDate.CUSTOM
    };

    public WaitBurial(Context context) {
        this(context, null);
    }

    public WaitBurial(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_order_waitburial, this);
        initView();
        initData();
    }

    private void initView() {
        mTitleTabChange = (TitleTabChange) view.findViewById(R.id.titletab);
        mRLContent = (RelativeLayout) view.findViewById(R.id.rl_content);
    }

    private void initData() {
        for (int i = 0; i < dateTitle.length; i++) {
            OrderBurialDate data = dateTitle[i];
            mTitleTabChange.addTab(data.getDate(),
                    data.getCode(),
                    getResources().getDimensionPixelSize(R.dimen.dimen_32dp),
                    getResources().getColor(R.color.zhy_text_color_1),
                    getResources().getColor(R.color.zhy_text_color_2),
                    getResources().getColor(R.color.zhy_text_color_1));
            mTitleTabChange.setCallBack(tabCallBack);
        }
        mTitleTabChange.setCode(OrderBurialDate.TODAY.getCode());
    }

    TitleTabChange.TabCallBack tabCallBack = new TitleTabChange.TabCallBack() {
        @Override
        public void TabChange(int code, String title) {
            mRLContent.removeAllViews();
            BurialListLayout listLayout = new BurialListLayout(getContext());
            if(code== OrderBurialDate.CUSTOM.getCode()){
                listLayout.setSearch(true);
            }else{
                listLayout.setSearch(false);
            }

            mRLContent.addView(listLayout);
        }
    };
}
