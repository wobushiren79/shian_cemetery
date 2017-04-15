package com.shian.app.shian_cemetery.http.bean;

/**
 * Created by Administrator on 2017/4/14.
 */

public class BurialLocationModel {
    private long cemeteryId;//,公墓ID
    private String cemeteryName;//111
    private String tombName;//  苑名称
    private String parkName;//区名臣
    private long row;//排
    private long num;//號
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

    public long getRow() {
        return row;
    }

    public void setRow(long row) {
        this.row = row;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public long getStauts() {
        return stauts;
    }

    public void setStauts(long stauts) {
        this.stauts = stauts;
    }
}
