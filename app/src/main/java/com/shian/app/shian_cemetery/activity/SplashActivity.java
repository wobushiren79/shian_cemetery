package com.shian.app.shian_cemetery.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.params.HpLoginParams;
import com.shian.app.shian_cemetery.http.result.HrLoginResult;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginResultBean;
import com.shian.app.shian_cemetery.mvp.login.presenter.IUserLoginPresenter;
import com.shian.app.shian_cemetery.mvp.login.presenter.impl.UserLoginPresenterImpl;
import com.shian.app.shian_cemetery.mvp.login.view.IUserLoginView;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.tools.SharePerfrenceUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Request;

public class SplashActivity extends BaseActivity implements IUserLoginView {

    private int SLEEPTIME = 3000;//屏幕休眠时间
    private Timer timerIntent;//定时跳转
    private IUserLoginPresenter userLoginPresenter;

    private String userName;
    private String userPassWord;
    private Boolean isAuto;
    private Boolean isRe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initData();
    }

    /**
     * 休眠2秒
     */
    private void sleepActivity(final int type) {
        timerIntent = new Timer();
        timerIntent.schedule(new TimerTask() {
            @Override
            public void run() {
                jumpActivity(type);
            }
        }, SLEEPTIME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timerIntent != null)
            timerIntent.cancel();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        userLoginPresenter = new UserLoginPresenterImpl(this, null);
        userLoginPresenter.getLoginConfig();
    }

    /**
     * 跳转界面
     */
    private void jumpActivity(int type) {
        Intent intent = new Intent(SplashActivity.this, LoginAdvertActivity.class);
        if (type == 0) {
            intent.putExtra(IntentName.INTENT_ADEVERT, LoginAdvertActivity.MAIN);
        } else {
            intent.putExtra(IntentName.INTENT_ADEVERT, LoginAdvertActivity.LOGIN);
        }
        startActivity(intent);
        finish();
    }


    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassWord() {
        return userPassWord;
    }

    @Override
    public void setPassWord(String passWord) {
        this.userPassWord = passWord;
    }

    @Override
    public boolean getIsAutoLogin() {
        return isAuto;
    }

    @Override
    public void setIsAutoLogin(boolean isAutoLogin) {
        this.isAuto = isAutoLogin;
        if (isAuto)
            userLoginPresenter.loginSystem();
        else
            sleepActivity(1);
    }

    @Override
    public boolean getIsKeepAccount() {
        return isRe;
    }

    @Override
    public void setIsKeepAccount(boolean isKeepAccount) {
        this.isRe = isKeepAccount;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loginSystemSuccess(SystemLoginResultBean result) {

    }

    @Override
    public void loginSystemFail(String message) {
        ToastUtils.showShortToast(getBaseContext(), "登陆失败");
        jumpActivity(1);
    }

    @Override
    public void loginSubSystemSuccess() {
        sleepActivity(0);
    }
}
