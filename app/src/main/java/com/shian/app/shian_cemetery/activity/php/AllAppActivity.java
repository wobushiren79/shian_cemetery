package com.shian.app.shian_cemetery.activity.php;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.adapter.baseadapter.AllAppAdapter;
import com.shian.app.shian_cemetery.appenum.APPEnum;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.common.view.ScrollGridView;
import com.shian.app.shian_cemetery.view.customlayout.mainadvertisement.MainAdvertisementLayout;
import com.shian.app.shian_cemetery.view.customlayout.mainapp.AppAdvertisementLayout;


public class AllAppActivity extends BaseActivity {

    ScrollGridView mPlatformGridView;
    ScrollGridView mToolsGridView;
    ScrollGridView mOtherGridView;

    AppAdvertisementLayout appAdvertisementLayout;

    APPEnum[] platformData = {
            APPEnum.ZSPROJECT,
            APPEnum.CEMETERY,
            APPEnum.VRCEMETERY,
            APPEnum.BEFORECONTRACT
    };
    APPEnum[] toolsData = {
            APPEnum.NAVIGATION,
            APPEnum.CALENDAR,
            APPEnum.CALCULATOR,
            APPEnum.DIDI,
            APPEnum.COMMUNICATION
    };
    APPEnum[] otherData = {
            APPEnum.INTEGRALMALL
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_app);

        setTitle("全部应用",BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        initView();
    }

    private void initView() {
        mPlatformGridView = (ScrollGridView) findViewById(R.id.platform_gridview);
        mToolsGridView = (ScrollGridView) findViewById(R.id.tools_gridview);
        mOtherGridView = (ScrollGridView) findViewById(R.id.other_gridview);

        appAdvertisementLayout = (AppAdvertisementLayout) findViewById(R.id.appadvertisementlayout);

        mPlatformGridView.setAdapter(new AllAppAdapter(AllAppActivity.this, platformData));
        mToolsGridView.setAdapter(new AllAppAdapter(AllAppActivity.this, toolsData));
        mOtherGridView.setAdapter(new AllAppAdapter(AllAppActivity.this, otherData));

        appAdvertisementLayout.setCallBack(new MainAdvertisementLayout.CallBack() {
            @Override
            public void loadingComplete() {
                appAdvertisementLayout.setVisibility(View.VISIBLE);
                TranslateAnimation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
                animation.setDuration(500);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animation.cancel();
                        appAdvertisementLayout.clearAnimation();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                appAdvertisementLayout.startAnimation(animation);
            }

            @Override
            public void cancelPic() {

            }
        });
    }

}
