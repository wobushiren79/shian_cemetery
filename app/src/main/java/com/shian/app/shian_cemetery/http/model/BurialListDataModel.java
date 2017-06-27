package com.shian.app.shian_cemetery.http.model;

/**
 * Created by Administrator on 2017/4/14.
 */

public class BurialListDataModel {
    private long id;//訂單ID
//    private BurialInfoModel buryInfo;//安葬信息
    private BurialRecord buryRecord;//安葬信息
//    private BurialDeadInfoModel deadInfo;//死者信息
    private String deadNames;
    private BurialLocationModel tombPosition;//安葬墓位
    private BurialOrderModel order;

    public String getDeadNames() {
        return deadNames;
    }

    public void setDeadNames(String deadNames) {
        this.deadNames = deadNames;
    }

    public BurialOrderModel getOrder() {
        return order;
    }

    public void setOrder(BurialOrderModel order) {
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BurialRecord getBuryRecord() {
        return buryRecord;
    }

    public void setBuryRecord(BurialRecord buryRecord) {
        this.buryRecord = buryRecord;
    }

//    public BurialDeadInfoModel getDeadInfo() {
//        return deadInfo;
//    }
//
//    public void setDeadInfo(BurialDeadInfoModel deadInfo) {
//        this.deadInfo = deadInfo;
//    }

    public BurialLocationModel getTombPosition() {
        return tombPosition;
    }

    public void setTombPosition(BurialLocationModel tombPosition) {
        this.tombPosition = tombPosition;
    }
}
