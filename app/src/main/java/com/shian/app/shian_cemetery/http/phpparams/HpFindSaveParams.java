package com.shian.app.shian_cemetery.http.phpparams;

import com.shian.app.shian_cemetery.http.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/4/19.
 */

public class HpFindSaveParams extends BaseHttpParams {


    private int type;
    private long userid;
    private int siftid;
    private int userType;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public int getSiftid() {
        return siftid;
    }

    public void setSiftid(int siftid) {
        this.siftid = siftid;
    }
}
