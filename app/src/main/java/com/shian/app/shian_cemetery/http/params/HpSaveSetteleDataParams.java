package com.shian.app.shian_cemetery.http.params;

import com.shian.app.shian_cemetery.http.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/4/15.
 */

public class HpSaveSetteleDataParams extends BaseHttpParams {
    private long orderId;
    private String buriedFileIds;
    private String remark;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getBuriedFileIds() {
        return buriedFileIds;
    }

    public void setBuriedFileIds(String buriedFileIds) {
        this.buriedFileIds = buriedFileIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
