package com.shian.app.shian_cemetery.http.result;

import java.util.ArrayList;

public class HrLoginResult {
    private String sessionId;
    private String lastAccessTime;
    private String startTimestamp;
    private ArrayList<Integer> roleIds;

    private long userId;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public ArrayList<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(ArrayList<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(String lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public String getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(String startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

}