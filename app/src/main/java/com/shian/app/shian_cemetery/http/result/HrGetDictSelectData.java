package com.shian.app.shian_cemetery.http.result;

import com.shian.app.shian_cemetery.http.bean.DictSelectModel;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */

public class HrGetDictSelectData {
    private List<DictSelectModel> items;

    public List<DictSelectModel> getItems() {
        return items;
    }

    public void setItems(List<DictSelectModel> items) {
        this.items = items;
    }
}
