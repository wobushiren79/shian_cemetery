package com.shian.app.shian_cemetery.view.dataview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;

/**
 * Created by Administrator on 2017/4/6.
 */

public class TextReadLayout extends BaseDataLayout {
    View view;
    TextView mTVTitle;
    TextView mTVContent;

    public TextReadLayout(Context context) {
        this(context, null);
    }

    public TextReadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_data_text_read, this);
        initView();
        initData();
    }


    private void initView() {
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
        mTVContent = (TextView) view.findViewById(R.id.tv_content);
    }


    private void initData() {
        mTVTitle.setText(titleName);
    }

    public void setData(String content) {
        mTVContent.setText(content);
    }
}
