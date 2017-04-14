package com.shian.app.shian_cemetery.http.bean;

/**
 * Created by Administrator on 2017/4/14.
 */

public class BurialDeadInfoModel {
    private String deadmanOneName;//"死者1",  死者名字
    private String deadmanTwoName;// "死者2"

    public String getDeadmanOneName() {
        return deadmanOneName;
    }

    public void setDeadmanOneName(String deadmanOneName) {
        this.deadmanOneName = deadmanOneName;
    }

    public String getDeadmanTwoName() {
        return deadmanTwoName;
    }

    public void setDeadmanTwoName(String deadmanTwoName) {
        this.deadmanTwoName = deadmanTwoName;
    }
}
