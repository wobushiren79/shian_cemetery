package com.shian.app.shian_cemetery.order.burial;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/4/17.
 */

public abstract class BaseBurialTitleView extends LinearLayout {

    String statusType;
    int setteleType;
    int burialType;
    int multyBurialType;

    public BaseBurialTitleView(Context context, String statusType, int setteleType, int burialType, int multyBurialType) {
        super(context);
        this.setteleType = setteleType;
        this.burialType = burialType;
        this.multyBurialType = multyBurialType;
        this.statusType = statusType;
    }

    public abstract void refesh();
}
