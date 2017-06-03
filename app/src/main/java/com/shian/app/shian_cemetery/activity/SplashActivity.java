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
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.tools.SharePerfrenceUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Request;

public class SplashActivity extends BaseActivity {

    private int SLEEPTIME = 3000;//屏幕休眠时间
    private Timer timerIntent;//定时跳转

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
        //自动登陆
        SharePerfrenceUtils.ShareLogin shareLogin = SharePerfrenceUtils.getLoginShare(SplashActivity.this);
        if (shareLogin.isAutoLogin()) {
            login(shareLogin.getUsername(), shareLogin.getPassword(), shareLogin.getOrderUser());
        } else {
            sleepActivity(1);
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
                AppData.UserLoginResult = result;
                SharePerfrenceUtils.setSessionShare(SplashActivity.this, result.getSessionId());
                sleepActivity(0);
            }


            @Override
            public void onError(String message) {
                ToastUtils.showShortToast(getBaseContext(), "登陆失败");
                SharePerfrenceUtils.setLoginShare(SplashActivity.this, username, password, true, false, orderUser);
                jumpActivity(1);
            }
        });
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


}
