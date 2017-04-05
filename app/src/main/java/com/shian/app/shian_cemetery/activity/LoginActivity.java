package com.shian.app.shian_cemetery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.view.customlayout.loadingbutton.LoadingButton;

public class LoginActivity extends BaseActivity {
    LoadingButton mLoadingButton;
    EditText mETUserName;
    EditText mETPassWord;

    CheckBox mCBKeep;
    CheckBox mCBAuto;

    TextView mTVPhoneLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        mLoadingButton = (LoadingButton) findViewById(R.id.btn_login);
        mETUserName = (EditText) findViewById(R.id.et_login_username);
        mETPassWord = (EditText) findViewById(R.id.et_login_password);
        mCBKeep = (CheckBox) findViewById(R.id.cb_login_re);
        mCBAuto = (CheckBox) findViewById(R.id.cb_login_auto);
        mTVPhoneLoading = (TextView) findViewById(R.id.btn_login_web);

        mLoadingButton.setOnClickListener(onClickListener);
        mTVPhoneLoading.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mLoadingButton) {
                jumpMain();
            } else if (v == mTVPhoneLoading) {
                jumpPhoneLoading();
            }
        }
    };

    /**
     * 跳转主界面
     */
    private void jumpMain() {
//        mLoadingButton.setLoading();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 手机登陆
     */
    private void jumpPhoneLoading() {
        Intent intent = new Intent(LoginActivity.this, LoginPhoneActivity.class);
        startActivity(intent);
    }
}
