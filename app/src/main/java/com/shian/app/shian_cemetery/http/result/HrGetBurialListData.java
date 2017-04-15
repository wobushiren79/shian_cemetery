package com.shian.app.shian_cemetery.http.result;

import com.shian.app.shian_cemetery.http.bean.BurialListDataModel;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class HrGetBurialListData {
    private List<BurialListDataModel> list;
    private int total;

    public List<BurialListDataModel> getList() {
        return list;
    }

    public void setList(List<BurialListDataModel> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
