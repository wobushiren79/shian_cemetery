package com.shian.app.shian_cemetery.base;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.view.headlayout.NormalTitle;
import com.shian.app.shian_cemetery.view.headlayout.TabTitle;

/**
 * Created by Administrator on 2017/3/1.
 */

public class BaseActivity extends FragmentActivity {

    public DisplayMetrics metrics = new DisplayMetrics();

    FrameLayout mFLContent;
    RelativeLayout mRLHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(R.layout.activity_base);
        initView();

        ((BaseAppliction) getApplicationContext()).addActivity(this);
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mFLContent = (FrameLayout) findViewById(R.id.fl_base);
        mRLHead = (RelativeLayout) findViewById(R.id.rl_head);
    }

    @Override
    public void setContentView(int layoutResID) {
        View v = LayoutInflater.from(this).inflate(layoutResID, null);
        mFLContent.addView(v, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    public void setContentView(View view) {
        // TODO Auto-generated method stub
        mFLContent.addView(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }


    /**
     * 设置普通标题
     *
     * @param titleName
     */
    public void setTitle(String titleName, int titleType) {
        mRLHead.removeAllViews();
        mRLHead.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (titleType == BaseTitleEnum.NORMALTITLE.getTitleType()) {
            //添加普通标题栏
            NormalTitle normalTitle = new NormalTitle(BaseActivity.this);
            normalTitle.setLayoutParams(layoutParams);
            normalTitle.setTitle(titleName);
            mRLHead.addView(normalTitle);
        } else if (titleType == BaseTitleEnum.TABTITLE.getTitleType()) {
            //添加tab标题栏
            TabTitle tabTitle = new TabTitle(BaseActivity.this);
            tabTitle.setLayoutParams(layoutParams);
            mRLHead.addView(tabTitle);
        } else {

        }
    }

    /**
     * 隐藏标题栏
     */
    public void setTitleVisible(int visible) {
        mRLHead.setVisibility(visible);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((BaseAppliction) getApplicationContext()).removeActivity(this);
    }
}
