package com.shian.app.shian_cemetery.view.customlayout.tabchange;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;

/**
 * Created by Administrator on 2017/4/3.
 */

public class TabItem extends LinearLayout {
    View view;

    TextView mTVTitle;
    TextView mTVBottom;

    boolean isCheck = false;

    int textCheckColor = Color.WHITE;
    int unTextCheckColor = Color.WHITE;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_title_tab_change_item, this);
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
        mTVBottom = (TextView) view.findViewById(R.id.tv_bottom);
    }


    public void changeState(boolean state) {
        if (state) {
            mTVBottom.setVisibility(VISIBLE);
            mTVTitle.setTextColor(textCheckColor);
            isCheck = true;
        } else {
            mTVBottom.setVisibility(INVISIBLE);
            mTVTitle.setTextColor(unTextCheckColor);
            isCheck = false;
        }
    }

    /**
     * 设置大小
     *
     * @param size
     */
    public void setTitleSize(float size) {
        mTVTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
    }

    /**
     * 设置字体颜色
     *
     * @param checkColor
     * @param unCheckColor
     */
    public void setTitleColor(int checkColor, int unCheckColor) {
        textCheckColor = checkColor;
        unTextCheckColor = unCheckColor;
        if (isCheck) {
            mTVTitle.setTextColor(checkColor);
        } else {
            mTVTitle.setTextColor(unCheckColor);
        }
    }

    /**
     * 设置底部颜色
     *
     * @param color
     */
    public void setBottomColor(int color) {
        mTVBottom.setBackgroundColor(color);
    }

    /**
     * 设置标题内容
     *
     * @param content
     */
    public void setTitleContent(String content) {
        mTVTitle.setText(content);
    }

}
