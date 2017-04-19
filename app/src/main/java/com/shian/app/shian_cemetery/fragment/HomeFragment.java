package com.shian.app.shian_cemetery.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.base.BaseFragment;
import com.shian.app.shian_cemetery.view.customlayout.burialinfo.BurialInfoLayout;
import com.shian.app.shian_cemetery.view.customlayout.mainadvertisement.MainAdvertisementLayout;
import com.shian.app.shian_cemetery.view.customlayout.mainapp.MainAPP;
import com.shian.app.shian_cemetery.view.customlayout.maindynamic.MainDynamic;

public class HomeFragment extends BaseFragment {
    View view;
    BurialInfoLayout mBurialInfo;
    MainAdvertisementLayout mMainAdvertisementLayout;//主页广告布局
    MainDynamic mMainDynamicLayout;//重要动态
    MainAPP mMainAPP;//我的APP


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null, false);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView() {
        mBurialInfo = (BurialInfoLayout) view.findViewById(R.id.burialinfo);
        mMainAdvertisementLayout = (MainAdvertisementLayout) view.findViewById(R.id.mainadvertisement_layout);
        mMainDynamicLayout = (MainDynamic) view.findViewById(R.id.maindynamic_layout);
        mMainAPP = (MainAPP) view.findViewById(R.id.mainapp_layout);

        mMainAdvertisementLayout.setCallBack(advertisermentLayoutCallBack);
        mMainDynamicLayout.setCallBack(mainDynamicCallBack);
    }

    /**
     * 广告加载完毕与取消动画
     */
    MainAdvertisementLayout.CallBack advertisermentLayoutCallBack = new MainAdvertisementLayout.CallBack() {
        @Override
        public void loadingComplete() {
            mMainAdvertisementLayout.setVisibility(View.VISIBLE);

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
                    mMainAdvertisementLayout.clearAnimation();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mMainAdvertisementLayout.startAnimation(animation);
        }

        @Override
        public void cancelPic() {
            AlphaAnimation animation = new AlphaAnimation(1, 0);
            animation.setDuration(500);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation.cancel();
                    mMainAdvertisementLayout.clearAnimation();
                    mMainAdvertisementLayout.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mMainAdvertisementLayout.startAnimation(animation);
        }
    };


    MainDynamic.CallBack mainDynamicCallBack = new MainDynamic.CallBack() {
        @Override
        public void loadingComplete() {
            mMainDynamicLayout.setVisibility(View.VISIBLE);
        }
    };
}
