package com.shian.app.shian_cemetery.http.params;

import com.shian.app.shian_cemetery.http.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/4/14.
 */

public class HpBurialDataListParams extends BaseHttpParams {
    private String statusType; // 状态查询类型 如010
    private int stoneStatus;//      立碑状态 0未立碑 1立碑
    private int buryStatus;//0,安葬状态 0未安葬， 1安葬
    private  int multyBuryStatus; // 0未合葬 1已合葬
    private int dateType; //0,日期类型 0是天 1是月 2是年
    private String date; // 2017-04-13 15:27:51 日期参数
    private int pageNum;//页数
    private int pageSize;//一页条数


    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public int getMultyBuryStatus() {
        return multyBuryStatus;
    }

    public void setMultyBuryStatus(int multyBuryStatus) {
        this.multyBuryStatus = multyBuryStatus;
    }

    public int getStoneStatus() {
        return stoneStatus;
    }

    public void setStoneStatus(int stoneStatus) {
        this.stoneStatus = stoneStatus;
    }

    public int getBuryStatus() {
        return buryStatus;
    }

    public void setBuryStatus(int buryStatus) {
        this.buryStatus = buryStatus;
    }

    public int getDateType() {
        return dateType;
    }

    public void setDateType(int dateType) {
        this.dateType = dateType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
