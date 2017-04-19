package com.shian.app.shian_cemetery.http.phpresult;


import com.shian.app.shian_cemetery.http.phpmodel.DynamicItemsInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */

public class PHPHrGetDynamic {

    private List<DynamicItemsInfo> items;

    public List<DynamicItemsInfo> getItems() {
        return items;
    }

    public void setItems(List<DynamicItemsInfo> items) {
        this.items = items;
    }
}
