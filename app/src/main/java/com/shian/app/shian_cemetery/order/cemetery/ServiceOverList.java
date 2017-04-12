package com.shian.app.shian_cemetery.order.cemetery;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.shian.app.shian_cemetery.R;

/**
 * Created by Administrator on 2017/4/10.
 */

public class ServiceOverList extends BaseCemeteryOrderList{
    View view;

    public ServiceOverList(Context context) {
        this(context,null);
    }

    public ServiceOverList(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_cemetery_order_serviceover, this);
    }

    @Override
    public void refesh() {

    }
}
