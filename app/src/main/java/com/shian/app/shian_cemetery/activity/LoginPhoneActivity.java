package com.shian.app.shian_cemetery.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.view.customlayout.loadingbutton.LoadingButton;

/**
 * Created by Administrator on 2017/3/31.
 */

public class LoginPhoneActivity extends BaseActivity {
    RelativeLayout mRLContent;
    EditText mETUserName;
    EditText mETPassWord;
    Button mBTPhoneCode;
    LoadingButton mLBSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);

        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mRLContent = (RelativeLayout) findViewById(R.id.rl_content);
        mETUserName = (EditText) findViewById(R.id.et_login_username);
        mETPassWord = (EditText) findViewById(R.id.et_login_password);
        mBTPhoneCode = (Button) findViewById(R.id.bt_phonecode);
        mLBSubmit = (LoadingButton) findViewById(R.id.btn_login);

        mBTPhoneCode.setOnClickListener(onClickListener);
        mLBSubmit.setOnClickListener(onClickListener);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mLBSubmit) {
                ToastUtils.showShortToast(LoginPhoneActivity.this,"该功能还未开放");
            } else if (v == mBTPhoneCode) {
                ToastUtils.showShortToast(LoginPhoneActivity.this,"该功能还未开放");
            }
        }
    };
}
