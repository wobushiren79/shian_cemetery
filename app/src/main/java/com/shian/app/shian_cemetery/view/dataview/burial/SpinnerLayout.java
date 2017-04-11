package com.shian.app.shian_cemetery.view.dataview.burial;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.view.dataview.burial.BaseDataLayout;

/**
 * Created by Administrator on 2017/4/6.
 */

public class SpinnerLayout extends BaseDataLayout {
    View view;

    TextView mTVTitle;
    TextView mTVHint;

    public SpinnerLayout(Context context) {
        this(context, null);
    }

    public SpinnerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_data_spinner, this);
        initView();
        initData();
    }

    private void initView() {
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
        mTVHint = (TextView) view.findViewById(R.id.tv_hint);
    }

    private void initData() {
        mTVTitle.setText(titleName);
        if (isShowHint) {
            mTVHint.setVisibility(VISIBLE);
        } else {
            mTVHint.setVisibility(GONE);
        }
        mTVHint.setText(hintText);
    }
}
