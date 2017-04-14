package com.shian.app.shian_cemetery.http.params;


import com.shian.app.shian_cemetery.http.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/2/9.
 */

public class HpCemeteryStructureParams extends BaseHttpParams {
    private int itemType;//公墓结构项类型，值：0,公墓。1,苑，2,区，3排，4 号
    private long itemId;//
    private long parkIdTemp;//园区id，注：仅类型为4墓位号时，才启用园区id

    public long getParkIdTemp() {
        return parkIdTemp;
    }

    public void setParkIdTemp(long parkIdTemp) {
        this.parkIdTemp = parkIdTemp;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}