package com.shian.app.shian_cemetery.http.params;

import com.shian.app.shian_cemetery.http.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/4/17.
 */

public class HpSaveBurialDataParams extends BaseHttpParams {
    private long orderId;
    private String detail;//落葬情況
    private String buryRate;//落葬率
    private String remark;//備註
    private String signFileIds;//簽名圖片

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBuryRate() {
        return buryRate;
    }

    public void setBuryRate(String buryRate) {
        this.buryRate = buryRate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSignFileIds() {
        return signFileIds;
    }

    public void setSignFileIds(String signFileIds) {
        this.signFileIds = signFileIds;
    }
}
