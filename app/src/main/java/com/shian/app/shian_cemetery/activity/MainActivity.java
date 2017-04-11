package com.shian.app.shian_cemetery.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.SDKInitializer;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.appenum.MainChangeItemEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.base.BaseAppliction;
import com.shian.app.shian_cemetery.base.BaseFragment;
import com.shian.app.shian_cemetery.common.local.LocationService;
import com.shian.app.shian_cemetery.staticdata.AppData;
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
    private LocationService locationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // -----------location config ------------
        locationService = ((BaseAppliction) getApplication()).locationService;
        // 获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        // 注册监听
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();
        // 定位SDK
        // start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationService.unregisterListener(mListener); // 注销掉监听
        locationService.stop(); // 停止定位服务
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
     * 定位监听
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                if (location.getAddrStr() == null) return;
                AppData.LocalString = location.getAddrStr();
                AppData.LOCAL_PROVINCE = location.getAddress().province;
                AppData.LOCAL_CITY = location.getAddress().city;
                AppData.LOCAL_COUNTY = location.getAddress().district;
                AppData.LOCAL_STREET = location.getAddress().street;
                AppData.LOCAL_STREETNUM = location.getAddress().streetNumber;
                AppData.LOCAL_ADDRESS = location.getAddress().address;
                AppData.LOCAL_latitude = location.getLatitude();
                AppData.LOCAL_longitude = location.getLongitude();
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }

    };
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
