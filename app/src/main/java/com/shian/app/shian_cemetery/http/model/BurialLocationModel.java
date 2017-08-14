package com.shian.app.shian_cemetery.http.model;

/**
 * Created by Administrator on 2017/4/14.
 */

public class BurialLocationModel {
    private long cemeteryId;//,公墓ID
    private String cemeteryName;//111
    private String tombName;//  苑名称
    private String parkName;//区名臣
    private String row;//排
    private String num;//號
    private long stauts;//

    public long getCemeteryId() {
        return cemeteryId;
    }

    public void setCemeteryId(long cemeteryId) {
        this.cemeteryId = cemeteryId;
    }

    public String getCemeteryName() {
        return cemeteryName;
    }

    public void setCemeteryName(String cemeteryName) {
        this.cemeteryName = cemeteryName;
    }

    public String getTombName() {
        return tombName;
    }

    public void setTombName(String tombName) {
        this.tombName = tombName;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public long getStauts() {
        return stauts;
    }

    public void setStauts(long stauts) {
        this.stauts = stauts;
    }
}
