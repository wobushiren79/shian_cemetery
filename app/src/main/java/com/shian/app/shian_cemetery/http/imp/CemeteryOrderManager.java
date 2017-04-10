package com.shian.app.shian_cemetery.http.imp;

import android.content.Context;

import com.shian.app.shian_cemetery.http.base.HttpManager;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.params.HpGetOrderListParams;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryListData;


/**
 * Created by Administrator on 2017/1/18.
 */

public interface CemeteryOrderManager extends HttpManager {

    /**
     * 获取订单列表
     *
     * @param context
     * @param params
     * @param orderType
     *            0-洽谈列表 1-售后列表 2服务结束列表
     * @param response
     */
    public void getOrderList(Context context, HpGetOrderListParams params, int orderType,
                             HttpResponseHandler<HrGetCemeteryListData> response);

}
