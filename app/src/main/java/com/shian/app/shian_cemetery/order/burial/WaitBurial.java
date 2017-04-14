package com.shian.app.shian_cemetery.order.burial;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.OrderBurialDateEnum;
import com.shian.app.shian_cemetery.order.burial.list.BurialListLayout;
import com.shian.app.shian_cemetery.view.customlayout.tabchange.TitleTabChange;

/**
 * Created by Administrator on 2017/4/5.
 */

public class WaitBurial extends LinearLayout {
    View view;
    TitleTabChange mTitleTabChange;
    RelativeLayout mRLContent;
    OrderBurialDateEnum[] dateTitle = {
            OrderBurialDateEnum.TODAY,
            OrderBurialDateEnum.TOMORROW,
            OrderBurialDateEnum.THISMONTH,
            OrderBurialDateEnum.CUSTOM
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
            OrderBurialDateEnum data = dateTitle[i];
            mTitleTabChange.addTab(data.getDate(),
                    data.getCode(),
                    getResources().getDimensionPixelSize(R.dimen.dimen_32dp),
                    getResources().getColor(R.color.zhy_text_color_1),
                    getResources().getColor(R.color.zhy_text_color_2),
                    getResources().getColor(R.color.zhy_text_color_1));
            mTitleTabChange.setCallBack(tabCallBack);
        }
        mTitleTabChange.setCode(OrderBurialDateEnum.TODAY.getCode());
    }

    TitleTabChange.TabCallBack tabCallBack = new TitleTabChange.TabCallBack() {
        @Override
        public void TabChange(int code, String title) {
            mRLContent.removeAllViews();
            RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            BurialListLayout listLayout = new BurialListLayout(getContext());

            listLayout.setLayoutParams(layoutParams);
            if(code== OrderBurialDateEnum.CUSTOM.getCode()){
                listLayout.setSearch(true);
            }else{
                listLayout.setSearch(false);
            }
            mRLContent.addView(listLayout);
        }
    };
}
