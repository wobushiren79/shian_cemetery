package com.shian.app.shian_cemetery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.adapter.pageradapter.CemeteryListViewPagerAdapter;
import com.shian.app.shian_cemetery.appenum.AppRolePermition;
import com.shian.app.shian_cemetery.appenum.BuildOrderEnum;
import com.shian.app.shian_cemetery.appenum.CemeteryTabEnum;
import com.shian.app.shian_cemetery.base.BaseFragment;
import com.shian.app.shian_cemetery.order.cemetery.BaseCemeteryOrderList;
import com.shian.app.shian_cemetery.order.cemetery.BuildList;
import com.shian.app.shian_cemetery.order.cemetery.ServiceOverList;
import com.shian.app.shian_cemetery.order.cemetery.TalkList;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.tools.CheckUtils;
import com.shian.app.shian_cemetery.tools.LogUtils;
import com.shian.app.shian_cemetery.tools.Utils;
import com.shian.app.shian_cemetery.view.customlayout.popupbutton.PopupButton;
import com.shian.app.shian_cemetery.view.customlayout.tabchange.TitleTabChange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/10.
 */

public class CemeteryOrderFragment extends BaseFragment {
    View view;
    TitleTabChange mTitleTabChange;
    ViewPager mViewPager;
    CemeteryListViewPagerAdapter mPagerAdapter;
    PopupButton mPopupButton;
    List<CemeteryTabEnum> tabData = new ArrayList<>();


    List<BaseCemeteryOrderList> listView;
    public static boolean isRefesh = false;
    int mIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cemetery_order, null, false);
        initView();
        initPermition();
        initData();
        return view;
    }

    /**
     * 初始化权限
     *
     * @return
     */
    private void initPermition() {
        //确认新建权限
//        if (!CheckUtils.checkPermition(AppRolePermition.ADVISOR.getCode(), AppData.UserLoginResult.getPermitionCodes())) {
//            mPopupButton.setVisibility(View.GONE);
//        } else {
//            mPopupButton.setVisibility(View.VISIBLE);
//            initPopupButton();
//            tabData.add(CemeteryTabEnum.BUILD);
//        }
        //确认洽谈权限
        if (CheckUtils.checkPermition(AppRolePermition.TALKER.getCode(), AppData.UserLoginResult.getPermitionCodes()))
            tabData.add(CemeteryTabEnum.TALKORDER);

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
        mPopupButton = (PopupButton) view.findViewById(R.id.popupbutton);
        mTitleTabChange.setCallBack(tabCallBack);

    }

    /**
     * 标题切换监听
     */
    TitleTabChange.TabCallBack tabCallBack = new TitleTabChange.TabCallBack() {
        @Override
        public void TabChange(int code, String title) {
            for (int i = 0; i < tabData.size(); i++) {
                CemeteryTabEnum data = tabData.get(i);
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
            mTitleTabChange.setCode(tabData.get(position).getCode());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void initData() {

        listView = new ArrayList<>();
        for (int i = 0; i < tabData.size(); i++) {
            CemeteryTabEnum data = tabData.get(i);
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
            } else if (data.getCode() == CemeteryTabEnum.BUILD.getCode()) {
                BuildList buildList = new BuildList(getContext());
                listView.add(buildList);
            }
        }
        if (tabData.size() > 0)
            mTitleTabChange.setCode(tabData.get(0).getCode());
        mPagerAdapter = new CemeteryListViewPagerAdapter(getContext(), listView);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 初始化popup
     */
    private void initPopupButton() {
        final BuildOrderEnum[] buildButtons = {
                BuildOrderEnum.GM
        };
        for (int i = 0; i < buildButtons.length; i++) {
            mPopupButton.addHorizontalButton(buildButtons[i].getName(), buildButtons[i].getIconId(), i);
        }

        mPopupButton.setCallBack(new PopupButton.PopupButtonCallBack() {
            @Override
            public void onClick(int positionButton) {
                for (int i = 0; i < buildButtons.length; i++) {
                    if (positionButton == i) {
                        mPopupButton.mainButton();
                        Intent intent = new Intent(getContext(), buildButtons[i].getActivity());
                        getContext().startActivity(intent);

                    }
                }
            }
        });
    }
}
