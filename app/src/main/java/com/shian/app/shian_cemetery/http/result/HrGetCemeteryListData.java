package com.shian.app.shian_cemetery.http.result;


import com.shian.app.shian_cemetery.http.bean.CemeteryOrderModel;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */

public class HrGetCemeteryListData {
    List<CemeteryOrderModel> items;

    public List<CemeteryOrderModel> getItems() {
        return items;
    }

    public void setItems(List<CemeteryOrderModel> items) {
        this.items = items;
    }
}
