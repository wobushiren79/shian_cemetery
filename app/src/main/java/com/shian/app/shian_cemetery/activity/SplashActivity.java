package com.shian.app.shian_cemetery.activity;

import android.content.Intent;
import android.os.Bundle;

import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.R;

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
        try {
            Thread.sleep(SLEEPTIME);
            jumpLogin();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 跳转登陆界面
     */
    private void jumpLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
