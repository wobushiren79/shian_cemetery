package com.shian.app.shian_cemetery.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.shian.app.shian_cemetery.staticdata.IntentName;

/**
 * Created by Administrator on 2017/4/5.
 */

public class TitleTabChangeReceiver extends BroadcastReceiver {
    TitleTabChangeReceiverCallBack tabChangeReceiverCallBack;

    public TitleTabChangeReceiver() {
    }


    public void setTabChangeReceiverCallBack(TitleTabChangeReceiverCallBack tabChangeReceiverCallBack) {
        this.tabChangeReceiverCallBack = tabChangeReceiverCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra(IntentName.INTENT_TITLE_TAB_CHANGE_RECEIVER_NAME);
        int code = intent.getIntExtra(IntentName.INTENT_TITLE_TAB_CHANGE_RECEIVER_CODE, -1);
        if (tabChangeReceiverCallBack != null) {
            tabChangeReceiverCallBack.orderFragmentTitleChange(code, name);
        }
    }

    public interface TitleTabChangeReceiverCallBack {
        void orderFragmentTitleChange(int code, String name);
    }
}
