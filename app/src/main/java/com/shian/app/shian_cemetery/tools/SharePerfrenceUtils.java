package com.shian.app.shian_cemetery.tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.shian.app.shian_cemetery.mvp.login.bean.UserLoginConfig;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/7.
 */

public class SharePerfrenceUtils {
    private static final String C_sShare_Login_F = "Login_Share_f";

    private static final String C_sShareLogin_username = "share_username";
    private static final String C_sShareLogin_password = "share_password";
    private static final String C_sShareLogin_isAutoLogin = "share_isAutoLogin";
    private static final String C_sShareLogin_isRemeberPassword = "share_isRePassword";
    private static final String C_sShareLogin_orderUser = "share_orderUser";
    private static final String C_sSession_Share = "share_session";

    /**
     * 保存sessionID
     *
     * @param context
     * @param sessionId
     */
    public static void setSessionShare(Context context, String sessionId) {
        SharedPreferences.Editor editor = context.getSharedPreferences(C_sShare_Login_F, MODE_PRIVATE).edit();
        editor.putString(C_sSession_Share, sessionId);
        editor.commit();
    }

    /**
     * 获取sessionID
     *
     * @param context
     */
    public static String getSessionShare(Context context) {
        SharedPreferences share = context.getSharedPreferences(C_sShare_Login_F, MODE_PRIVATE);
        String sessionID = share.getString(C_sSession_Share, "");
        return sessionID;
    }

    /**
     * 設置自動登陸狀態
     *
     * @param c
     * @param isAuto
     */
    public static void setShareAutoLogin(Context c, boolean isAuto) {
        SharedPreferences.Editor editor = c.getSharedPreferences(C_sShare_Login_F, MODE_PRIVATE).edit();
        editor.putBoolean(C_sShareLogin_isAutoLogin, isAuto);
        editor.commit();
    }

    /**
     * 保存账号信息
     *
     * @param context
     * @param username
     * @param password
     * @param isRemeber
     * @param isAuto
     */
    public static void setLoginShare(Context context, String username, String password, boolean isRemeber, boolean isAuto) {
        SharedPreferences.Editor editor = context.getSharedPreferences(C_sShare_Login_F, MODE_PRIVATE).edit();
        editor.putString(C_sShareLogin_username, username);
        editor.putString(C_sShareLogin_password, password);
        editor.putBoolean(C_sShareLogin_isRemeberPassword, isRemeber);
        editor.putBoolean(C_sShareLogin_isAutoLogin, isAuto);
        editor.commit();
    }


    /**
     * 获取账号信息
     *
     * @param content
     * @return
     */
    public static ShareLogin getLoginShare(Context content) {
        SharedPreferences share = content.getSharedPreferences(C_sShare_Login_F, MODE_PRIVATE);
        String username = share.getString(C_sShareLogin_username, "");
        String password = share.getString(C_sShareLogin_password, "");
        boolean isRember = share.getBoolean(C_sShareLogin_isRemeberPassword,
                false);
        boolean isAuto = share.getBoolean(C_sShareLogin_isAutoLogin, false);
        int orderUser = share.getInt(C_sShareLogin_orderUser, -1);
        ShareLogin loginS = new ShareLogin();
        loginS.setUsername(username);
        loginS.setPassword(password);
        loginS.setRemeberPassword(isRember);
        loginS.setAutoLogin(isAuto);
        loginS.setOrderUser(orderUser);
        return loginS;
    }
    /**
     * 获取账号信息
     *
     * @param content
     * @return
     */
    public static UserLoginConfig getLoginShareSys(Context content) {
        SharedPreferences share = content.getSharedPreferences(C_sShare_Login_F, MODE_PRIVATE);
        String username = share.getString(C_sShareLogin_username, "");
        String password = share.getString(C_sShareLogin_password, "");
        boolean isRember = share.getBoolean(C_sShareLogin_isRemeberPassword,
                false);
        boolean isAuto = share.getBoolean(C_sShareLogin_isAutoLogin, false);
        UserLoginConfig loginS = new UserLoginConfig();
        loginS.setUserName(username);
        loginS.setPassWord(password);
        loginS.setKeepAccount(isRember);
        loginS.setAutoLogin(isAuto);
        return loginS;
    }
    /**
     * 获取账号登录模块
     *
     * @param content
     * @return
     */
    public static int getOrderUser(Context content) {
        SharedPreferences share = content.getSharedPreferences(C_sShare_Login_F, MODE_PRIVATE);
        int orderUser = share.getInt(C_sShareLogin_orderUser, -1);
        return orderUser;
    }

    /**
     * 设置账号登录模块
     *
     * @param content
     * @return
     */
    public static void setOrderUser(Context content, int orderUser) {
        SharedPreferences.Editor editor = content.getSharedPreferences(C_sShare_Login_F, MODE_PRIVATE).edit();
        editor.putInt(C_sShareLogin_orderUser, orderUser);
        editor.commit();
    }


    public static class ShareLogin {
        private String username;
        private String password;
        private boolean isAutoLogin;
        private boolean isRemeberPassword;
        private int orderUser;

        public int getOrderUser() {
            return orderUser;
        }

        public void setOrderUser(int orderUser) {
            this.orderUser = orderUser;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isAutoLogin() {
            return isAutoLogin;
        }

        public void setAutoLogin(boolean isAutoLogin) {
            this.isAutoLogin = isAutoLogin;
        }

        public boolean isRemeberPassword() {
            return isRemeberPassword;
        }

        public void setRemeberPassword(boolean isRemeberPassword) {
            this.isRemeberPassword = isRemeberPassword;
        }

    }
}
