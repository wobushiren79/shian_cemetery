package com.shian.app.shian_cemetery.common.bean;

/**
 * Created by Administrator on 2017/4/17.
 */

public class BurialBuildDataBean {
    private  String statusType; // 状态查询类型 如010
    private int dateType;
    private int setteleType;
    private int burialType;
    private int multyBurialType;
    private int year;
    private int month;
    private int day;

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public int getMultyBurialType() {
        return multyBurialType;
    }

    public void setMultyBurialType(int multyBurialType) {
        this.multyBurialType = multyBurialType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDateType() {
        return dateType;
    }

    public void setDateType(int dateType) {
        this.dateType = dateType;
    }

    public int getSetteleType() {
        return setteleType;
    }

    public void setSetteleType(int setteleType) {
        this.setteleType = setteleType;
    }

    public int getBurialType() {
        return burialType;
    }

    public void setBurialType(int burialType) {
        this.burialType = burialType;
    }
}
