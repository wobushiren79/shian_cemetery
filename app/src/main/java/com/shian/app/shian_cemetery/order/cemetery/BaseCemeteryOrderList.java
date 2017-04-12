package com.shian.app.shian_cemetery.order.cemetery;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/4/12.
 */

public abstract class BaseCemeteryOrderList extends LinearLayout {


    public BaseCemeteryOrderList(Context context) {
        super(context);
    }

    public BaseCemeteryOrderList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract void refesh();
}
