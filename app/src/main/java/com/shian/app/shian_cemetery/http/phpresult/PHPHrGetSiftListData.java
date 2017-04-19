package com.shian.app.shian_cemetery.http.phpresult;


import com.shian.app.shian_cemetery.http.phpmodel.SiftListData;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */

public class PHPHrGetSiftListData {
    private List<SiftListData> items;

    public List<SiftListData> getItems() {
        return items;
    }

    public void setItems(List<SiftListData> items) {
        this.items = items;
    }
}
