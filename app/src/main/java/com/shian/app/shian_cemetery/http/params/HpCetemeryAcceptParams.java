package com.shian.app.shian_cemetery.http.params;


import com.shian.app.shian_cemetery.http.base.BaseHttpParams;


public class HpCetemeryAcceptParams extends BaseHttpParams {
    private long bespeakAssignId;//咨询指派ID
    private long bespeakId;//咨询ID
    private long cemeteryId;//公墓ID
    private long userId;//用户ID

    public long getCemeteryId() {
        return cemeteryId;
    }

    public void setCemeteryId(long cemeteryId) {
        this.cemeteryId = cemeteryId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBespeakAssignId() {
        return bespeakAssignId;
    }

    public void setBespeakAssignId(long bespeakAssignId) {
        this.bespeakAssignId = bespeakAssignId;
    }

    public long getBespeakId() {
        return bespeakId;
    }

    public void setBespeakId(long bespeakId) {
        this.bespeakId = bespeakId;
    }
}
