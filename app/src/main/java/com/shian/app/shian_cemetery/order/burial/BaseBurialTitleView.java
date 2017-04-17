package com.shian.app.shian_cemetery.order.burial;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/4/17.
 */

public abstract class BaseBurialTitleView extends LinearLayout {
    public BaseBurialTitleView(Context context) {
        super(context);
    }

    public BaseBurialTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract void refesh();
}
