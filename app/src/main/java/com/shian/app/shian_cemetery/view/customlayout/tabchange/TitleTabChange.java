package com.shian.app.shian_cemetery.view.customlayout.tabchange;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.tools.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */

public class TitleTabChange extends LinearLayout {
    View view;
    HorizontalScrollView scrollView;
    LinearLayout mLLContent;

    List<TabItem> listItems = new ArrayList<>();
    TabCallBack callBack;

    public TitleTabChange(Context context) {
        this(context, null);
    }

    public TitleTabChange(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_title_tab_change, this);
        initView();
    }

    private void initView() {
        scrollView = (HorizontalScrollView) view.findViewById(R.id.scrollView);
        mLLContent = (LinearLayout) view.findViewById(R.id.ll_content);
    }

    public void setCallBack(TabCallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 增加内容
     *
     * @param title
     * @param textSize
     * @param checkColor
     * @param unCheckColor
     */
    public void addTab(String title, int code, float textSize, int checkColor, int unCheckColor, int bottomColor) {
        final TabItem tabItem = new TabItem(getContext());
        tabItem.setCode(code);
        tabItem.setBottomColor(bottomColor);
        tabItem.setTitleContent(title);
        tabItem.setTitleSize(textSize);
        tabItem.setTitleColor(checkColor, unCheckColor);
        tabItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listItems.size(); i++) {
                    TabItem item = listItems.get(i);
                    if (item == tabItem) {
                        item.changeState(true);
                        if (callBack != null)
                            callBack.TabChange(item.getCode(), item.getTitle());
                    } else {
                        item.changeState(false);
                    }
                }
            }
        });
        mLLContent.addView(tabItem);
        listItems.add(tabItem);
    }

    /**
     * 设置选中一项
     *
     * @param code
     */
    public void setCode(int code) {
        for (int i = 0; i < listItems.size(); i++) {
            TabItem tabItem = listItems.get(i);
            if (tabItem.getCode() == code) {
                tabItem.changeState(true);
                if (callBack != null)
                    callBack.TabChange(tabItem.getCode(), tabItem.getTitle());
            } else {
                tabItem.changeState(false);
            }
        }
    }

    public interface TabCallBack {
        void TabChange(int code, String title);
    }
}
