package com.shian.app.shian_cemetery.order.cemetery.infolayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/4/12.
 */

public abstract class BaseCemeteryInfo extends LinearLayout {
    long orderId = -1;
    long bespeakId = -1;
    View view;
    CallBack callBack;

    public BaseCemeteryInfo(Context context, long orderId, long bespeakId, int layoutId) {
        super(context);
        this.orderId = orderId;
        this.bespeakId = bespeakId;
        view = View.inflate(context, layoutId, this);
        initView();
        initData();
        getData();
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 獲取數據
     */
    abstract void getData();

    /**
     * 保存數據
     */
    abstract void saveData();

    /**
     * 初始化控件
     */
    abstract void initView();

    /**
     * 初始化數據
     */
    abstract void initData();

    public interface CallBack {
        void next();

        void back();
    }
}
