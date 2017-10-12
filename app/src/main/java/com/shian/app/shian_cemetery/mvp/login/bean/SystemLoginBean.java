package com.shian.app.shian_cemetery.mvp.login.bean;


import com.shian.app.shian_cemetery.http.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class SystemLoginBean extends BaseHttpParams {
    private String userName;//	账户名
    private String userPwd;//	密钥

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
