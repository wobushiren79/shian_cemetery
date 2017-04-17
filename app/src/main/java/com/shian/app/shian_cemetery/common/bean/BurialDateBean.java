package com.shian.app.shian_cemetery.common.bean;

import com.shian.app.shian_cemetery.http.model.BurialListDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */

public class BurialDateBean {
    private List<BurialListDataModel> orderData ;
    private int day;//具体天数
    private int month;//具体月数

    public List<BurialListDataModel> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<BurialListDataModel> orderData) {
        this.orderData = orderData;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }


    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}