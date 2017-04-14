package com.shian.app.shian_cemetery.http.bean;

/**
 * Created by Administrator on 2017/4/14.
 */

public class BurialListDataModel {
    private long id;//訂單ID
    private BurialInfoModel buryInfo;//安葬信息
    private BurialDeadInfoModel deadInfo;//死者信息
    private BurialLocationModel tombPosition;//安葬墓位

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BurialInfoModel getBuryInfo() {
        return buryInfo;
    }

    public void setBuryInfo(BurialInfoModel buryInfo) {
        this.buryInfo = buryInfo;
    }

    public BurialDeadInfoModel getDeadInfo() {
        return deadInfo;
    }

    public void setDeadInfo(BurialDeadInfoModel deadInfo) {
        this.deadInfo = deadInfo;
    }

    public BurialLocationModel getTombPosition() {
        return tombPosition;
    }

    public void setTombPosition(BurialLocationModel tombPosition) {
        this.tombPosition = tombPosition;
    }
}
