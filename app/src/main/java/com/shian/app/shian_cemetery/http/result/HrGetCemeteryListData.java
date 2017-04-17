package com.shian.app.shian_cemetery.http.result;


import com.shian.app.shian_cemetery.http.model.CemeteryOrderModel;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */

public class HrGetCemeteryListData {
    List<CemeteryOrderModel> list;
    int pageNum;
    int pageSize;
    int pages;


    public List<CemeteryOrderModel> getList() {
        return list;
    }

    public void setList(List<CemeteryOrderModel> list) {
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
