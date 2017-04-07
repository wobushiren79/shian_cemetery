package com.shian.app.shian_cemetery.activity.burial;

import android.os.Bundle;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.view.dataview.TextReadLayout;

public class SetteleActivity extends BaseActivity {

    TextReadLayout mTRLocation;
    TextReadLayout mTRSetteleTime;
    TextReadLayout mTRUserName;
    TextReadLayout mTRBuildTime;
    TextReadLayout mTRBuildState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settele);
        setTitle("立碑", BaseTitleEnum.BACKNORMALTITLE.getTitleType());

        initView();
        initData();
    }

    private void initView() {
        mTRLocation = (TextReadLayout) findViewById(R.id.tr_location);
        mTRSetteleTime = (TextReadLayout) findViewById(R.id.tr_time);
        mTRUserName = (TextReadLayout) findViewById(R.id.tr_username);
        mTRBuildTime = (TextReadLayout) findViewById(R.id.tr_buildtime);
        mTRBuildState = (TextReadLayout) findViewById(R.id.tr_buildstate);

    }

    private void initData() {
        mTRLocation.setData("test");
        mTRSetteleTime.setData("test");
        mTRUserName.setData("test");
        mTRBuildTime.setData("test");
        mTRBuildState.setData("test");
    }

}
