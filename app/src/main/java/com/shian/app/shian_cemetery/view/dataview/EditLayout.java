package com.shian.app.shian_cemetery.view.dataview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;

/**
 * Created by Administrator on 2017/4/6.
 */

public class EditLayout extends BaseDataLayout {
    View view;

    TextView mTVTitle;
    EditText mETContent;

    public EditLayout(Context context) {
        this(context, null);
    }

    public EditLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_data_edit, this);
        initView();
        initData();
    }

    private void initView() {
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
    }

    private void initData() {
        mTVTitle.setText(titleName);
    }
}
