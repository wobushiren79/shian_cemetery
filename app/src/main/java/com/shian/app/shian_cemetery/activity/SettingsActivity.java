package com.shian.app.shian_cemetery.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.burial.SetteleActivity;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.base.BaseAppliction;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.tools.SharePerfrenceUtils;
import com.shian.app.shian_cemetery.view.dialog.TipsDialog;

import okhttp3.Request;

public class SettingsActivity extends BaseActivity {

    TextView mTVLoginOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("设置", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        initView();
    }

    private void initView() {
        mTVLoginOut = (TextView) findViewById(R.id.tv_editorder);
        mTVLoginOut.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mTVLoginOut) {
                logout();
            }
        }
    };


    /**
     * 退出賬號
     */
    private void logout() {
        TipsDialog mDialog = new TipsDialog(this);
        mDialog.setTitle("是否退出当前账户");
        mDialog.setTopButton("是", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                MHttpManagerFactory.getAccountManager().loginOut(
                        getBaseContext(), new HttpResponseHandler<Object>() {

                            @Override
                            public void onStart(Request request, int id) {

                            }

                            @Override
                            public void onSuccess(Object result) {
                                // TODO Auto-generated method stub
                                SharePerfrenceUtils.setShareAutoLogin(SettingsActivity.this, false);
                                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }


                            @Override
                            public void onError(String message) {
                                // TODO Auto-generated method stub

                            }
                        });
            }
        });
        mDialog.setBottomButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mDialog.show();
    }
}
