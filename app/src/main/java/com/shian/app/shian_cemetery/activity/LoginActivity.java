package com.shian.app.shian_cemetery.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.OrderUserEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpRequestExecutor;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.params.HpLoginParams;
import com.shian.app.shian_cemetery.http.result.HrLoginResult;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.tools.SharePerfrenceUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.view.customlayout.loadingbutton.LoadingButton;

import okhttp3.Request;

public class LoginActivity extends BaseActivity {
    LoadingButton mLoadingButton;
    EditText mETUserName;
    EditText mETPassWord;

    CheckBox mCBKeep;
    CheckBox mCBAuto;

    TextView mTVPhoneLoading;

    RadioGroup mRG;
    RadioButton mRBBurial;
    RadioButton mRBCemeteryTalk;

    RelativeLayout mRLContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
        startAnim();
    }

    private void initView() {
        mLoadingButton = (LoadingButton) findViewById(R.id.btn_login);
        mETUserName = (EditText) findViewById(R.id.et_login_username);
        mETPassWord = (EditText) findViewById(R.id.et_login_password);
        mCBKeep = (CheckBox) findViewById(R.id.cb_login_re);
        mCBAuto = (CheckBox) findViewById(R.id.cb_login_auto);
        mTVPhoneLoading = (TextView) findViewById(R.id.btn_login_web);
        mRBBurial = (RadioButton) findViewById(R.id.rb_state1);
        mRBCemeteryTalk = (RadioButton) findViewById(R.id.rb_state2);
        mRG = (RadioGroup) findViewById(R.id.rg);
        mRLContent = (RelativeLayout) findViewById(R.id.rl_content);

        mLoadingButton.setOnClickListener(onClickListener);
        mTVPhoneLoading.setOnClickListener(onClickListener);
    }

    private void initData() {
        SharePerfrenceUtils.ShareLogin shareLogin = SharePerfrenceUtils.getLoginShare(LoginActivity.this);
        if (shareLogin.isRemeberPassword()) {
            mETUserName.setText(shareLogin.getUsername());
            mETPassWord.setText(shareLogin.getPassword());
            mCBKeep.setChecked(true);
        }
        if (shareLogin.isAutoLogin()) {
            mCBAuto.setChecked(true);
        }
        if (shareLogin.getOrderUser() == OrderUserEnum.Burial.getCode()) {
            mRBBurial.setChecked(true);
        } else if (shareLogin.getOrderUser() == OrderUserEnum.Cemetery.getCode()) {
            mRBCemeteryTalk.setChecked(true);
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mLoadingButton) {
                checkInfo();
            } else if (v == mTVPhoneLoading) {
                jumpPhoneLoading();
            }
        }
    };

    /**
     * 检查信息
     */
    private void checkInfo() {
        if (mETUserName.getText().toString().equals("")) {
            ToastUtils.showLongToast(LoginActivity.this, "账号还未填写");
            return;
        }
        if (mETPassWord.getText().toString().equals("")) {
            ToastUtils.showLongToast(LoginActivity.this, "密码还未填写");
            return;
        }
        login(mETUserName.getText().toString(), mETPassWord.getText().toString());
    }


    /**
     * 登陆
     */
    private void login(final String username, final String password) {
        //登录状态为公墓类型
        mLoadingButton.setLoading();
        HpLoginParams params = new HpLoginParams();
        params.setPassword(mETPassWord.getText().toString());
        params.setUsername(mETUserName.getText().toString());
        params.setSystemType("0");
//        params.setChannelId("0");
        MHttpManagerFactory.getAccountManager().loginCemetery(this, params, new HttpResponseHandler<HrLoginResult>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrLoginResult result) {
                AppData.UserLoginResult = result;
                mLoadingButton.setComplete();
                SharePerfrenceUtils.setSessionShare(LoginActivity.this, AppData.UserLoginResult.getSessionId());
                int orderUser = -1;
                if (mRBBurial.isChecked()) {
                    orderUser = OrderUserEnum.Burial.getCode();
                } else if (mRBCemeteryTalk.isChecked()) {
                    orderUser = OrderUserEnum.Cemetery.getCode();
                }
                SharePerfrenceUtils.setLoginShare(LoginActivity.this, username, password, mCBKeep.isChecked(), mCBAuto.isChecked(), orderUser);
                ToastUtils.showShortToast(getBaseContext(), "登陆成功");
                jumpMain();
            }

            @Override
            public void onError(String message) {
                mLoadingButton.setNormal();
            }
        });
    }

    /**
     * 跳转主界面
     */
    private void jumpMain() {
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

    /**
     * 动画
     */
    private void startAnim() {
        TranslateAnimation translateAnimation = new TranslateAnimation
                (Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                        Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setDuration(1000);
        mRLContent.setAnimation(translateAnimation);
        translateAnimation.start();
    }
}
