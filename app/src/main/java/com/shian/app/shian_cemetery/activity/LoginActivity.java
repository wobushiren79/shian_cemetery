package com.shian.app.shian_cemetery.activity;

import android.content.Context;
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
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginResultBean;
import com.shian.app.shian_cemetery.mvp.login.presenter.IUserLoginPresenter;
import com.shian.app.shian_cemetery.mvp.login.presenter.impl.UserLoginPresenterImpl;
import com.shian.app.shian_cemetery.mvp.login.view.IUserLoginView;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.tools.SharePerfrenceUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.tools.Utils;
import com.shian.app.shian_cemetery.view.customlayout.loadingbutton.LoadingButton;
import com.shian.app.shian_cemetery.view.dialog.DownLoadDialog;

import okhttp3.Request;

public class LoginActivity extends BaseActivity implements IUserLoginView {
    LoadingButton mLoadingButton;
    EditText mETUserName;
    EditText mETPassWord;

    CheckBox mCBKeep;
    CheckBox mCBAuto;

    TextView mTVPhoneLoading;

    RelativeLayout mRLContent;
    private IUserLoginPresenter userLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
        startAnim();
        //检测更新
        Utils.checkUpData(this, false);
    }

    private void initView() {
        mLoadingButton = (LoadingButton) findViewById(R.id.btn_login);
        mETUserName = (EditText) findViewById(R.id.et_login_username);
        mETPassWord = (EditText) findViewById(R.id.et_login_password);
        mCBKeep = (CheckBox) findViewById(R.id.cb_login_re);
        mCBAuto = (CheckBox) findViewById(R.id.cb_login_auto);
        mTVPhoneLoading = (TextView) findViewById(R.id.btn_login_web);
        mRLContent = (RelativeLayout) findViewById(R.id.rl_content);

        mLoadingButton.setOnClickListener(onClickListener);
        mTVPhoneLoading.setOnClickListener(onClickListener);
    }

    private void initData() {
        userLoginPresenter = new UserLoginPresenterImpl(this, null);
        userLoginPresenter.getLoginConfig();
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
        mLoadingButton.setLoading();
        userLoginPresenter.loginSystem();
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

    @Override
    public String getUserName() {
        return mETUserName.getText().toString();
    }

    @Override
    public void setUserName(String userName) {
        mETUserName.setText(userName);
    }

    @Override
    public String getPassWord() {
        return mETPassWord.getText().toString();
    }

    @Override
    public void setPassWord(String passWord) {
        mETPassWord.setText(passWord);
    }

    @Override
    public boolean getIsAutoLogin() {
        return mCBAuto.isChecked();
    }

    @Override
    public void setIsAutoLogin(boolean isAutoLogin) {
        mCBAuto.setChecked(isAutoLogin);
    }

    @Override
    public boolean getIsKeepAccount() {
        return mCBKeep.isChecked();
    }

    @Override
    public void setIsKeepAccount(boolean isKeepAccount) {
        mCBKeep.setChecked(isKeepAccount);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loginSystemSuccess(SystemLoginResultBean result) {
        userLoginPresenter.saveLoginConfig();
        mLoadingButton.setComplete();
        jumpMain();
    }

    @Override
    public void loginSystemFail(String message) {
        mLoadingButton.setNormal();
        ToastUtils.showShortToast(this, message);
    }

}
