package com.shian.app.shian_cemetery.view.headlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;

/**
 * Created by Administrator on 2017/3/31.
 */

public class NormalTitle extends LinearLayout {
    View view;
    TextView mTVTitle;
    ImageView mIVMessage;

    public NormalTitle(Context context) {
        this(context,null);
    }

    public NormalTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_title_normal, this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
        mIVMessage = (ImageView) view.findViewById(R.id.iv_message);
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title) {
        mTVTitle.setText(title);
    }

}
