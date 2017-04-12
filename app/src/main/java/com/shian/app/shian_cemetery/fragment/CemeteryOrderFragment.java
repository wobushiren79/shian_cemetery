package com.shian.app.shian_cemetery.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.adapter.pageradapter.CemeteryListViewPagerAdapter;
import com.shian.app.shian_cemetery.appenum.CemeteryTabEnum;
import com.shian.app.shian_cemetery.base.BaseFragment;
import com.shian.app.shian_cemetery.order.cemetery.BaseCemeteryOrderList;
import com.shian.app.shian_cemetery.order.cemetery.ServiceOverList;
import com.shian.app.shian_cemetery.order.cemetery.TalkList;
import com.shian.app.shian_cemetery.tools.LogUtils;
import com.shian.app.shian_cemetery.view.customlayout.tabchange.TitleTabChange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */

public class CemeteryOrderFragment extends BaseFragment {
    View view;
    TitleTabChange mTitleTabChange;
    ViewPager mViewPager;
    CemeteryListViewPagerAdapter mPagerAdapter;

    CemeteryTabEnum[] tabData = {
            CemeteryTabEnum.TALKORDER,
            CemeteryTabEnum.SERVICEOVER
    };

    List<BaseCemeteryOrderList> listView;
    public static boolean isRefesh = false;
    int mIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cemetery_order, null, false);
        initView();
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRefesh && listView.size() > 0)
            listView.get(mIndex).refesh();
        isRefesh = false;
    }

    private void initView() {
        mTitleTabChange = (TitleTabChange) view.findViewById(R.id.titletab);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);

        mTitleTabChange.setCallBack(tabCallBack);
    }

    /**
     * 标题切换监听
     */
    TitleTabChange.TabCallBack tabCallBack = new TitleTabChange.TabCallBack() {
        @Override
        public void TabChange(int code, String title) {
            for (int i = 0; i < tabData.length; i++) {
                CemeteryTabEnum data = tabData[i];
                if (data.getCode() == code)
                    mViewPager.setCurrentItem(i);
            }
        }
    };

    /**
     * 页卡滑动监听
     */
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mIndex = position;
            mTitleTabChange.setCode(tabData[position].getCode());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void initData() {
        listView = new ArrayList<>();
        for (int i = 0; i < tabData.length; i++) {
            CemeteryTabEnum data = tabData[i];
            mTitleTabChange.addTab(data.getTitle(),
                    data.getCode(), getResources().getDimensionPixelSize(R.dimen.dimen_32dp),
                    getResources().getColor(R.color.zhy_text_color_1),
                    getResources().getColor(R.color.zhy_text_color_2),
                    getResources().getColor(R.color.zhy_text_color_1));
            //初始化VIEWPAGER内容
            if (data.getCode() == CemeteryTabEnum.TALKORDER.getCode()) {
                TalkList talkList = new TalkList(getContext());
                listView.add(talkList);
            } else if (data.getCode() == CemeteryTabEnum.SERVICEOVER.getCode()) {
                ServiceOverList serviceOverList = new ServiceOverList(getContext());
                listView.add(serviceOverList);
            }
        }

        mTitleTabChange.setCode(CemeteryTabEnum.TALKORDER.getCode());
        mPagerAdapter = new CemeteryListViewPagerAdapter(getContext(), listView);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
