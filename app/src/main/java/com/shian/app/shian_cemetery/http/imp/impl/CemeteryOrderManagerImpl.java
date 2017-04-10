package com.shian.app.shian_cemetery.http.imp.impl;

import android.content.Context;

import com.shian.app.shian_cemetery.http.base.HttpRequestExecutor;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.imp.CemeteryOrderManager;
import com.shian.app.shian_cemetery.http.params.HpGetOrderListParams;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryListData;


/**
 * Created by Administrator on 2017/1/18.
 */

public class CemeteryOrderManagerImpl implements CemeteryOrderManager {

    private static volatile CemeteryOrderManagerImpl manager;

    private HttpRequestExecutor excutor;
    private String[] getOrderListMethod = {"marketing/talks/overall/list", "cemetery/ordered/list/afterMarket", "cemetery/ordered/list/afterMarket"};

    private CemeteryOrderManagerImpl() {
        excutor = new HttpRequestExecutor();
    }

    public static CemeteryOrderManagerImpl getInstance() {
        if (manager == null) {
            manager = new CemeteryOrderManagerImpl();
        }
        return manager;
    }

    /**
     * 获取订单列表
     *
     * @param context
     * @param params
     * @param orderType 0-洽谈列表 1-售后列表 2服务结束列表
     * @param response
     */
    @Override
    public void getOrderList(Context context, HpGetOrderListParams params, int orderType,
                             HttpResponseHandler<HrGetCemeteryListData> response) {
        excutor.requestPost(context, getOrderListMethod[orderType], HrGetCemeteryListData.class, params, response);
    }
}
