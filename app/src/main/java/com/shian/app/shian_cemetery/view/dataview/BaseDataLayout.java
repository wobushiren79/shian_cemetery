package com.shian.app.shian_cemetery.view.dataview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.shian.app.shian_cemetery.R;

/**
 * Created by Administrator on 2017/4/6.
 */

public class BaseDataLayout extends LinearLayout {
    protected TypedArray typedArray = null;
    protected String titleName = "";
    protected String hintText = "";
    protected boolean isShowHint = false;

    public BaseDataLayout(Context context) {
        this(context, null);
    }

    public BaseDataLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.DataReadAttr);
        getAttrs();
    }

    protected void getAttrs() {
        titleName = typedArray.getString(R.styleable.DataReadAttr_titleName);
        hintText = typedArray.getString(R.styleable.DataReadAttr_hintContent);
        isShowHint = typedArray.getBoolean(R.styleable.DataReadAttr_isShowHint, false);
        typedArray.recycle();
    }
}
