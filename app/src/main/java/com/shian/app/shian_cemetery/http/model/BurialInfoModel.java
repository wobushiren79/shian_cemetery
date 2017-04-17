package com.shian.app.shian_cemetery.http.model;

/**
 * Created by Administrator on 2017/4/14.
 */

public class BurialInfoModel {
    private long buryDatePre;// 客户预期安葬日期
    private int buryStatus;//安葬状态 0未安葬  1 已安葬
    private String detail;//落葬详情
    private long stoneDatePre;//客户预计立碑日期
    private int stoneStatus;//立碑状态  0未立碑， 1已立碑

    public long getBuryDatePre() {
        return buryDatePre;
    }

    public void setBuryDatePre(long buryDatePre) {
        this.buryDatePre = buryDatePre;
    }

    public int getBuryStatus() {
        return buryStatus;
    }

    public void setBuryStatus(int buryStatus) {
        this.buryStatus = buryStatus;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getStoneDatePre() {
        return stoneDatePre;
    }

    public void setStoneDatePre(long stoneDatePre) {
        this.stoneDatePre = stoneDatePre;
    }

    public int getStoneStatus() {
        return stoneStatus;
    }

    public void setStoneStatus(int stoneStatus) {
        this.stoneStatus = stoneStatus;
    }
}
