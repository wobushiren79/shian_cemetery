package com.shian.app.shian_cemetery.http.result;

import com.shian.app.shian_cemetery.http.model.BurialDeadInfoModel;
import com.shian.app.shian_cemetery.http.model.BurialInfoModel;
import com.shian.app.shian_cemetery.http.model.BurialLocationModel;
import com.shian.app.shian_cemetery.http.model.BurialRecord;

/**
 * Created by Administrator on 2017/4/15.
 */

public class HrGetBurialDetails {
    private long id;//訂單ID
//    private BurialInfoModel buryInfo;//安葬信息
    private BurialRecord buryRecord;//安葬信息
    private BurialDeadInfoModel deadInfo;//死者信息
    private BurialLocationModel tombPosition;//安葬墓位

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
