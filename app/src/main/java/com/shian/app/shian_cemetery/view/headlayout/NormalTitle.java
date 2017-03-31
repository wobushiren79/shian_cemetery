package com.shian.app.shian_cemetery.view.headlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.shian.app.shian_cemetery.R;

/**
 * Created by Administrator on 2017/3/31.
 */

public class NormalTitle extends LinearLayout {
    View view;

    public NormalTitle(Context context) {
        super(context);
    }

    public NormalTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_title_normal, this);
    }

    /**
     * 初始化
     */
    private void initView() {

    }


}
