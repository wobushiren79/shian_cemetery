package com.shian.app.shian_cemetery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.AdvertisementEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.phpmodel.AdvertisementData;
import com.shian.app.shian_cemetery.http.phpparams.HpAdvertisementGetParams;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetAdvertisement;
import com.shian.app.shian_cemetery.staticdata.BaseURL;
import com.shian.app.shian_cemetery.staticdata.IntentName;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Request;

public class LoginAdvertActivity extends BaseActivity implements View.OnClickListener {

    ImageView ivContent;
    Button btJump;
    RelativeLayout rlContent;
    public final static int LOGIN = 0;//結束之後需登陸
    public final static int MAIN = 1;//结束之后无需登录
    private int type = -1;
    private boolean isForceOver = false;//是否强制结束
    private int advertSleepTime = 5000;//廣告暫停時間
    private AdvertisementData advertData;
    private Timer timerIntent;//定时跳转

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_advert);

        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isForceOver = true;
    }

    private void initView() {
        ivContent = (ImageView) findViewById(R.id.iv_advert);
        btJump = (Button) findViewById(R.id.bt_jump);
        rlContent = (RelativeLayout) findViewById(R.id.ll_advert);
        btJump.setOnClickListener(this);
        ivContent.setOnClickListener(this);
    }

    private void initData() {
        type = getIntent().getIntExtra(IntentName.INTENT_ADEVERT, -1);
        getData();
    }

    private void getData() {
        HpAdvertisementGetParams params = new HpAdvertisementGetParams();
        params.setType(AdvertisementEnum.LOADING.getCode());
        params.setNumber(1);
        params.setPagerNumber(0);
        MHttpManagerFactory.getPHPManager().getAdvertisement(this, params, new HttpResponseHandler<PHPHrGetAdvertisement>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(PHPHrGetAdvertisement result) {
                if (result.getItems() != null && result.getItems().size() > 0) {
                    rlContent.setVisibility(View.VISIBLE);
                    advertData = result.getItems().get(0);
                    ImageLoader.getInstance().displayImage(BaseURL.PHP_BaseUrl + result.getItems().get(0).getBanner(), ivContent);
                    startThread();

                } else {
                    checkAndJump();
                }
            }


            @Override
            public void onError(String message) {
                checkAndJump();
            }
        });
    }


    private void startThread() {
        timerIntent = new Timer();
        timerIntent.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isForceOver) {
                    checkAndJump();
                }
            }
        }, advertSleepTime);
    }


    @Override
    public void onClick(View v) {
        if (v == btJump) {
            if (timerIntent != null)
                timerIntent.cancel();
            isForceOver = true;
            checkAndJump();
        } else if (v == ivContent) {
            if (advertData == null)
                return;
            if (timerIntent != null)
                timerIntent.cancel();
            isForceOver = true;
            checkAndJump();
            Intent intent = new Intent(this, WebActivity.class);
            intent.putExtra(IntentName.INTENT_URL, advertData.getUrl());
            startActivity(intent);
        }
    }

    private void checkAndJump() {
        if (type == LOGIN) {
            jumpLogin();
        } else if (type == MAIN) {
            jumpMain();
        }
        finish();
    }

    private void jumpMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void jumpLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}
