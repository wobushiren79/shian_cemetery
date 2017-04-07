package com.shian.app.shian_cemetery.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/4/7.
 */

public class SharePerfrenceUtils {
    private static final String C_sShare_Login_F = "Login_Share_f";

    private static final String C_sShareLogin_username = "share_username";
    private static final String C_sShareLogin_password = "share_password";
    private static final String C_sShareLogin_isAutoLogin = "share_isAutoLogin";
    private static final String C_sShareLogin_isRemeberPassword = "share_isRePassword";
    private static final String C_sSession_Share = "share_session";

    /**
     * 保存sessionID
     * @param context
     * @param sessionId
     */
    public static void setSessionShare(Context context,String sessionId){
        SharedPreferences.Editor editor = context.getSharedPreferences(C_sShare_Login_F, -1).edit();
        editor.putString(C_sSession_Share, sessionId);
        editor.commit();
    }

    /**
     * 保存账号信息
     * @param c
     * @param username
     * @param password
     * @param isRemeber
     * @param isAuto
     */
    public static void setLoginShare(Context c, String username,
                                     String password, boolean isRemeber, boolean isAuto) {
        SharedPreferences.Editor editor = c.getSharedPreferences(C_sShare_Login_F, -1).edit();
        editor.putString(C_sShareLogin_username, username);
        editor.putString(C_sShareLogin_password, password);
        editor.putBoolean(C_sShareLogin_isRemeberPassword, isRemeber);
        editor.putBoolean(C_sShareLogin_isAutoLogin, isAuto);
        editor.commit();
    }

    /**
     * 获取账号信息
     * @param content
     * @return
     */
    public static ShareLogin getLoginShare(Context content) {
        SharedPreferences share = content.getSharedPreferences(C_sShare_Login_F, -1);
        String username = share.getString(C_sShareLogin_username, "");
        String password = share.getString(C_sShareLogin_password, "");
        boolean isRember = share.getBoolean(C_sShareLogin_isRemeberPassword,
                false);
        boolean isAuto = share.getBoolean(C_sShareLogin_isAutoLogin, false);
        ShareLogin loginS = new ShareLogin();
        loginS.setUsername(username);
        loginS.setPassword(password);
        loginS.setRemeberPassword(isRember);
        loginS.setAutoLogin(isAuto);
        return loginS;
    }

    public static class ShareLogin {
        private String username;
        private String password;
        private boolean isAutoLogin;
        private boolean isRemeberPassword;

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
