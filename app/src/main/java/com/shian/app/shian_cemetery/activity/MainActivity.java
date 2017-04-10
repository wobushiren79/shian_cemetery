package com.shian.app.shian_cemetery.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.appenum.MainChangeItemEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.base.BaseFragment;
import com.shian.app.shian_cemetery.view.customlayout.mainchange.MainChangeLayout;

public class MainActivity extends BaseActivity {

    MainChangeLayout mMainChangeLayout;

    MainChangeItemEnum[] MainChangeData = {
            MainChangeItemEnum.MAIN,
            MainChangeItemEnum.ORDER,
//            MainChangeItemEnum.FIND,
//            MainChangeItemEnum.MY,
            MainChangeItemEnum.CEMETERYORDER
    };

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTranscation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }


    private void initView() {
        mMainChangeLayout = (MainChangeLayout) findViewById(R.id.main_change_layout);
        mMainChangeLayout.setChangeCallBack(changeCallBack);
        setMianData();
    }

    private void initData() {
        mFragmentManager = getSupportFragmentManager();
    }

    private void showFragment(BaseFragment fragment) {
        mTranscation = mFragmentManager.beginTransaction();
        if (fragment == null) {
            fragment = new BaseFragment();
        }
        mTranscation.replace(R.id.fl_fragment, fragment);
        mTranscation.commit();
    }

    /**
     * 改变
     */
    MainChangeLayout.MainChangeCallBack changeCallBack = new MainChangeLayout.MainChangeCallBack() {
        @Override
        public void onClick(int itemId) {
            for (MainChangeItemEnum dataEnum : MainChangeData) {
                if (dataEnum.getItemId() == itemId) {
                    showFragment(dataEnum.getFragment());
                    //设置标题内容
                    if (dataEnum.getItemId() == MainChangeItemEnum.ORDER.getItemId()) {
                        setTitle(dataEnum.getTitle(), BaseTitleEnum.TABTITLE.getTitleType());
                    } else {
                        setTitle(dataEnum.getTitle(), BaseTitleEnum.NORMALTITLE.getTitleType());
                    }
                }
            }
        }
    };

    /**
     * 设置菜单数据
     */
    private void setMianData() {
        for (int i = 0; i < MainChangeData.length; i++) {
            mMainChangeLayout.addMainData
                    (MainChangeData[i].getTitle(),
                            MainChangeData[i].getUnCheckIconId(),
                            MainChangeData[i].getCheckIconId(),
                            MainChangeData[i].getItemId());
        }
        mMainChangeLayout.setState(MainChangeItemEnum.MAIN.getItemId(), true);
    }


}
