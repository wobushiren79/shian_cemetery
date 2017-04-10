package com.shian.app.shian_cemetery.http.params;


import com.shian.app.shian_cemetery.http.base.BaseHttpParams;



public class HpCetemeryRejectParams extends BaseHttpParams {
    private long bespeakAssignId;//咨询指派ID
    private long bespeakId;//咨询ID

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
