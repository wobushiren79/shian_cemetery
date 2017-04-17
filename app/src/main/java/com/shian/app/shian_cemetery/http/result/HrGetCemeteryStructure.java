package com.shian.app.shian_cemetery.http.result;


import com.shian.app.shian_cemetery.http.model.CemeteryStructureModel;

import java.util.List;

/**
 * Created by Administrator on 2017/2/9.
 */

public class HrGetCemeteryStructure {
    List<CemeteryStructureModel> items;

    public List<CemeteryStructureModel> getItems() {
        return items;
    }

    public void setItems(List<CemeteryStructureModel> items) {
        this.items = items;
    }
}
