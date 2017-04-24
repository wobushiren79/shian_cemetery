package com.shian.app.shian_cemetery.activity;

import android.content.Intent;
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
import com.shian.app.shian_cemetery.fragment.CemeteryOrderFragment;
import com.shian.app.shian_cemetery.fragment.FindFragment;
import com.shian.app.shian_cemetery.fragment.HomeFragment;
import com.shian.app.shian_cemetery.fragment.MyFragment;
import com.shian.app.shian_cemetery.fragment.OrderFragment;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.view.customlayout.mainchange.MainChangeLayout;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import okhttp3.Request;

public class MainActivity extends BaseActivity {

    MainChangeLayout mMainChangeLayout;

    MainChangeItemEnum[] MainChangeData = {
            MainChangeItemEnum.MAIN,
            MainChangeItemEnum.ORDER,
            MainChangeItemEnum.CEMETERYORDER,
            MainChangeItemEnum.FIND,
            MainChangeItemEnum.MY

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
        MHttpManagerFactory.getAccountManager().loginOut(this, new HttpResponseHandler<Object>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(String message) {

            }
        });
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
            BaseFragment baseFragment = null;
            if (itemId == MainChangeItemEnum.CEMETERYORDER.getItemId()) {
                baseFragment = new CemeteryOrderFragment();
                setTitle(MainChangeItemEnum.CEMETERYORDER.getTitle(), BaseTitleEnum.NORMALTITLE.getTitleType());
            } else if (itemId == MainChangeItemEnum.FIND.getItemId()) {
                baseFragment = new FindFragment();
                setTitle(MainChangeItemEnum.FIND.getTitle(), BaseTitleEnum.NORMALTITLE.getTitleType());
            } else if (itemId == MainChangeItemEnum.MAIN.getItemId()) {
                baseFragment = new HomeFragment();
                setTitle(MainChangeItemEnum.MAIN.getTitle(), BaseTitleEnum.NORMALTITLE.getTitleType());
            } else if (itemId == MainChangeItemEnum.MY.getItemId()) {
                baseFragment = new MyFragment();
                setTitle(MainChangeItemEnum.MY.getTitle(), BaseTitleEnum.NORMALTITLE.getTitleType());
            } else if (itemId == MainChangeItemEnum.ORDER.getItemId()) {
                baseFragment = new OrderFragment();
                setTitle(MainChangeItemEnum.ORDER.getTitle(),0);
            }
            showFragment(baseFragment);
              String a="";
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
