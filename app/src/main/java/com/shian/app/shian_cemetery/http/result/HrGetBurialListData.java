package com.shian.app.shian_cemetery.http.result;

import com.shian.app.shian_cemetery.http.model.BurialListDataModel;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class HrGetBurialListData {
    private List<BurialListDataModel> list;
    private int total;
    private int pageNum;
    private int pageSize;
    private int pages;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
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
