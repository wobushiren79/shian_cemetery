package com.shian.app.shian_cemetery.view.dataview.cemetery;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.shian.app.shian_cemetery.R;


/**
 * Created by Administrator on 2017/3/14.
 */

public abstract class BaseWriteView extends LinearLayout {
    protected TypedArray typedArray = null;
    protected String titleName = "";
    protected boolean isImportant;
    protected boolean isLonger;
    protected int inputType;//1普通，2数字,14邮箱
    protected int ems;


    public BaseWriteView(Context context) {
        this(context, null);
    }

    public BaseWriteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.DataReadAttr);
        getAttrs();
    }

    protected void getAttrs() {
        titleName = typedArray.getString(R.styleable.DataReadAttr_titleName);
        isImportant = typedArray.getBoolean(R.styleable.DataReadAttr_isImportant, false);
        inputType = typedArray.getInt(R.styleable.DataReadAttr_inputType, InputType.TYPE_CLASS_TEXT);
        isLonger = typedArray.getBoolean(R.styleable.DataReadAttr_isLonger,false);
        ems=typedArray.getInt(R.styleable.DataReadAttr_ems,7);
        typedArray.recycle();

    }


}
