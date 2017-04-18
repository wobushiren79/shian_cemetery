package com.shian.app.shian_cemetery.activity;

import android.content.Intent;
import android.os.Bundle;

import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.params.HpLoginParams;
import com.shian.app.shian_cemetery.http.result.HrLoginResult;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.tools.SharePerfrenceUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;

import okhttp3.Request;

public class SplashActivity extends BaseActivity {

    private int SLEEPTIME = 3000;//屏幕休眠时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sleepActivity();

    }

    /**
     * 休眠3秒
     */
    private void sleepActivity() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(SLEEPTIME);
                    initData();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //自定登陆
        SharePerfrenceUtils.ShareLogin shareLogin = SharePerfrenceUtils.getLoginShare(SplashActivity.this);
        if (shareLogin.isAutoLogin()) {
            login(shareLogin.getUsername(), shareLogin.getPassword(), shareLogin.getOrderUser());
        } else {
            jumpLogin();
        }
    }

    /**
     * 登陆
     */
    private void login(final String username, final String password, final int orderUser) {
        //登录状态为公墓类型
        HpLoginParams params = new HpLoginParams();
        params.setUsername(username);
        params.setPassword(password);
        params.setSystemType("2");
//        params.setChannelId("0");
        MHttpManagerFactory.getAccountManager().loginCemetery(this, params, new HttpResponseHandler<HrLoginResult>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrLoginResult result) {
                ToastUtils.showShortToast(getBaseContext(), "登陆成功");
                AppData.UserLoginResult = result;
                SharePerfrenceUtils.setSessionShare(SplashActivity.this, result.getSessionId());
                jumpMain();
            }


            @Override
            public void onError(String message) {
                ToastUtils.showShortToast(getBaseContext(), "登陆失败");
                SharePerfrenceUtils.setLoginShare(SplashActivity.this, username, password, true, false, orderUser);
                jumpLogin();
            }
        });
    }

    /**
     * 跳转登陆界面
     */
    private void jumpLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 跳转主界面
     */
    private void jumpMain() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
