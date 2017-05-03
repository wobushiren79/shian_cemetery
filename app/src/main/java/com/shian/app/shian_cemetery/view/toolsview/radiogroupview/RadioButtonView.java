package com.shian.app.shian_cemetery.view.toolsview.radiogroupview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;

/**
 * Created by zm.
 */

public class RadioButtonView extends LinearLayout {
    RadioButtonBean data;
    View view;

    TextView mTVTitle;
    ImageView mIVSelect;

    CallBack callBack;

    public RadioButtonView(Context context, RadioButtonBean data) {
        super(context);
        this.data = data;
        view = View.inflate(context, R.layout.view_radio_button, this);

        initView();
        initData();
    }

    private void initView() {
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
        mIVSelect = (ImageView) view.findViewById(R.id.iv_select);

        view.setOnClickListener(onClickListener);
    }

    private void initData() {
        mTVTitle.setText(data.getName());
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public RadioButtonBean getData() {
        return data;
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == view) {
                if (callBack != null)
                    callBack.check(data);
            }
        }
    };

    /**
     * 设置点击状态
     *
     * @param isCheck
     */
    public void setCheck(boolean isCheck) {
        if (!isCheck) {
            mIVSelect.setImageResource(R.drawable.zhy_login_rb_uncheck);
        } else {
            mIVSelect.setImageResource(R.drawable.zhy_login_rb_check);
        }
    }

    public interface CallBack {
        void check(RadioButtonBean data);
    }
}
