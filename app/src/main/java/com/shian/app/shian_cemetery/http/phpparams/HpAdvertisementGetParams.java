package com.shian.app.shian_cemetery.http.phpparams;

import com.shian.app.shian_cemetery.http.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/4/19.
 */

public class HpAdvertisementGetParams extends BaseHttpParams {

    private int type;
    private int number;
    private int pagerNumber;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPagerNumber() {
        return pagerNumber;
    }

    public void setPagerNumber(int pagerNumber) {
        this.pagerNumber = pagerNumber;
    }
}
