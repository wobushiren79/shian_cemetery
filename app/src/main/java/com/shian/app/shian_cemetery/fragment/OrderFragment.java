package com.shian.app.shian_cemetery.fragment;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BurialTabEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.base.BaseFragment;
import com.shian.app.shian_cemetery.order.burial.BaseBurialTitleView;
import com.shian.app.shian_cemetery.order.burial.BuriedOver;
import com.shian.app.shian_cemetery.order.burial.WaitBurial;
import com.shian.app.shian_cemetery.order.burial.WaitSettele;
import com.shian.app.shian_cemetery.staticdata.ReceiverAction;
import com.shian.app.shian_cemetery.receiver.TitleTabChangeReceiver;
import com.shian.app.shian_cemetery.tools.LogUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.view.headlayout.TabTitle;

/**
 * Created by Administrator on 2017/3/31.
 */

public class OrderFragment extends BaseFragment {
    View view;
    LinearLayout mLLOrder;
    TabTitle mTabTitle;
    //    TitleTabChangeReceiver tabChangeReceiver;
    public static boolean isRefesh = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, null, false);
        initView();
        initData();
        changeContent(new WaitBurial(getContext(), "010", 0, 0, 0));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRefesh && mLLOrder.getChildAt(0) != null) {
            BaseBurialTitleView burialTitleView = (BaseBurialTitleView) mLLOrder.getChildAt(0);
            burialTitleView.refesh();
        }
        isRefesh = false;
    }

    private void initView() {
        mLLOrder = (LinearLayout) view.findViewById(R.id.ll_order);
        mTabTitle = (TabTitle) view.findViewById(R.id.tabtitle);
        mTabTitle.setCallBack(tabCallBack);
    }

    TabTitle.CallBack tabCallBack = new TabTitle.CallBack() {
        @Override
        public void tabChange(int code, String title) {
            if (code == BurialTabEnum.WaitSettele.getCode()) {
                changeContent(new WaitSettele(getContext(), "100", 0, 0, 0));
            } else if (code == BurialTabEnum.WaitBuried.getCode()) {
                changeContent(new WaitBurial(getContext(), "010", 0, 0, 0));
            } else if (code == BurialTabEnum.BuriedOver.getCode()) {
                changeContent(new BuriedOver(getContext(), "010", 0, 1, 0));
            }
        }
    };


    private void initData() {
//        tabChangeReceiver = new TitleTabChangeReceiver();
//        tabChangeReceiver.setTabChangeReceiverCallBack(receiverCallBack);
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(ReceiverAction.TITLE_TAB_CHANGE_RECEIVER);
//        getContext().registerReceiver(tabChangeReceiver, intentFilter);
    }


//    /**
//     * 标题切换广播
//     */
//    TitleTabChangeReceiver.TitleTabChangeReceiverCallBack receiverCallBack = new TitleTabChangeReceiver.TitleTabChangeReceiverCallBack() {
//        @Override
//        public void orderFragmentTitleChange(int code, String name) {
//            if (code == BurialTabEnum.WaitSettele.getCode()) {
//                changeContent(new WaitSettele(getContext(), "100", 0, 0, 0));
//            } else if (code == BurialTabEnum.WaitBuried.getCode()) {
//                changeContent(new WaitBurial(getContext(), "010", 0, 0, 0));
//            } else if (code == BurialTabEnum.BuriedOver.getCode()) {
//                changeContent(new BuriedOver(getContext(), "010", 0, 1, 0));
//            }
//        }
//    };

    /**
     * 改变内容
     */
    private void changeContent(View view) {
        mLLOrder.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        mLLOrder.addView(view);
    }
}
