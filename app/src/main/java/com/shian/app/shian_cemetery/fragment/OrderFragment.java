package com.shian.app.shian_cemetery.fragment;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BurialTabEnum;
import com.shian.app.shian_cemetery.base.BaseFragment;
import com.shian.app.shian_cemetery.order.burial.BuriedOver;
import com.shian.app.shian_cemetery.order.burial.WaitBurial;
import com.shian.app.shian_cemetery.order.burial.WaitSettele;
import com.shian.app.shian_cemetery.staticdata.ReceiverAction;
import com.shian.app.shian_cemetery.receiver.TitleTabChangeReceiver;
import com.shian.app.shian_cemetery.tools.LogUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;

/**
 * Created by Administrator on 2017/3/31.
 */

public class OrderFragment extends BaseFragment {
    View view;
    LinearLayout mLLOrder;

    TitleTabChangeReceiver tabChangeReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, null, false);
        initView();
        initData();
        changeContent(new WaitBurial(getContext()));
        return view;
    }


    private void initView() {
        mLLOrder = (LinearLayout) view.findViewById(R.id.ll_order);
    }

    private void initData() {
        tabChangeReceiver = new TitleTabChangeReceiver();
        tabChangeReceiver.setTabChangeReceiverCallBack(receiverCallBack);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ReceiverAction.TITLE_TAB_CHANGE_RECEIVER);
        getContext().registerReceiver(tabChangeReceiver, intentFilter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 标题切换广播
     */
    TitleTabChangeReceiver.TitleTabChangeReceiverCallBack receiverCallBack = new TitleTabChangeReceiver.TitleTabChangeReceiverCallBack() {
        @Override
        public void orderFragmentTitleChange(int code, String name) {
            if (code == BurialTabEnum.WaitSettele.getCode()) {
                changeContent(new WaitSettele(getContext()));
            } else if (code == BurialTabEnum.WaitBuried.getCode()) {
                changeContent(new WaitBurial(getContext()));
            } else if (code == BurialTabEnum.BuriedOver.getCode()) {
                changeContent(new BuriedOver(getContext()));
            }
        }
    };

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
