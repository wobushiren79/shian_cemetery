package com.shian.app.shian_cemetery.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.shian.app.shian_cemetery.tools.Utils;
import com.shian.app.shian_cemetery.view.headlayout.BackNormalTitle;
import com.shian.app.shian_cemetery.view.headlayout.NormalTitle;
import com.shian.app.shian_cemetery.view.headlayout.TabTitle;
import com.yongchun.library.view.ImageSelectorActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/1.
 */

public class BaseActivity extends FragmentActivity {

    public DisplayMetrics metrics = new DisplayMetrics();

    FrameLayout mFLContent;
    RelativeLayout mRLHead;

    private OnPhotoPickerListener mOnPhotoPickerListener;
    private static final int PICK_PHOTO = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(R.layout.activity_base);
        initView();

        ((BaseAppliction) getApplicationContext()).addActivity(this);
        Utils.setWindowStatusBarColor(this, R.color.zhy_title_color_1);//设置状态栏颜色
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
            addNormalTitle(titleName, layoutParams);
        } else if (titleType == BaseTitleEnum.TABTITLE.getTitleType()) {
            //添加tab标题栏
            addTabTitle(layoutParams);
        } else if (titleType == BaseTitleEnum.BACKNORMALTITLE.getTitleType()) {
            addBackTitle(titleName, layoutParams);

        }
    }

    /**
     * @param titleName
     * @param layoutParams
     */
    private void addBackTitle(String titleName, RelativeLayout.LayoutParams layoutParams) {
        BackNormalTitle backNormalTitle = new BackNormalTitle(BaseActivity.this);
        backNormalTitle.setLayoutParams(layoutParams);
        backNormalTitle.setTitle(titleName);
        mRLHead.addView(backNormalTitle);
    }

    /**
     * 添加tab标题
     *
     * @param layoutParams
     */
    private void addTabTitle(RelativeLayout.LayoutParams layoutParams) {
        TabTitle tabTitle = new TabTitle(BaseActivity.this);
        tabTitle.setLayoutParams(layoutParams);
        mRLHead.addView(tabTitle);
    }

    /**
     * 添加普通通标题
     *
     * @param titleName
     * @param layoutParams
     */
    private void addNormalTitle(String titleName, RelativeLayout.LayoutParams layoutParams) {
        NormalTitle normalTitle = new NormalTitle(BaseActivity.this);
        normalTitle.setLayoutParams(layoutParams);
        normalTitle.setTitle(titleName);
        mRLHead.addView(normalTitle);
    }

    /**
     * 隐藏标题栏
     */
    public void setTitleVisible(int visible) {
        mRLHead.setVisibility(visible);
    }

    /**
     * 照片选择
     */
    public void showPhotoPicker() {
//		Intent intent = new Intent(this, PhotoPickerActivity.class);
//		intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, true);
//		intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE,
//				PhotoPickerActivity.MODE_SINGLE);
//		intent.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, 1);
//		startActivityForResult(intent, PICK_PHOTO);
        Intent intent = new Intent(this, ImageSelectorActivity.class);
        intent.putExtra(ImageSelectorActivity.EXTRA_MAX_SELECT_NUM, 1);
        intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_MODE, 2);
        intent.putExtra(ImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        intent.putExtra(ImageSelectorActivity.EXTRA_ENABLE_PREVIEW, true);
        intent.putExtra(ImageSelectorActivity.EXTRA_ENABLE_CROP, false);
        startActivityForResult(intent, PICK_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> result = data
                        .getStringArrayListExtra(ImageSelectorActivity.REQUEST_OUTPUT);
                if (mOnPhotoPickerListener != null) {
                    mOnPhotoPickerListener.onPhoto(result);
                }
            }
        }
    }


    public void setOnPhotoPickerListener(OnPhotoPickerListener listener) {
        mOnPhotoPickerListener = listener;
    }

    public interface OnPhotoPickerListener {
        public void onPhoto(ArrayList<String> paths);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((BaseAppliction) getApplicationContext()).removeActivity(this);
    }
}
