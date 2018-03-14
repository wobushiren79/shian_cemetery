package com.shian.app.shian_cemetery.http;


import com.shian.app.shian_cemetery.http.imp.FileManager;
import com.shian.app.shian_cemetery.http.imp.MAccountManager;
import com.shian.app.shian_cemetery.http.imp.PHPManager;
import com.shian.app.shian_cemetery.http.imp.SystemManager;
import com.shian.app.shian_cemetery.http.imp.impl.FileManagerImpl;
import com.shian.app.shian_cemetery.http.imp.impl.MAccountManagerImpl;
import com.shian.app.shian_cemetery.http.imp.impl.PHPManagerImpl;
import com.shian.app.shian_cemetery.http.imp.impl.SystemManagerImpl;

/**
 * 接口工厂
 *
 * @author Administrator
 */
public class MHttpManagerFactory {
    /**
     * 获取账户接口manager
     *
     * @return
     */
    public static MAccountManager getAccountManager() {
        return MAccountManagerImpl.getInstance();
    }

    public static FileManager getFileManager() {
        return FileManagerImpl.getInstance();
    }

    public static PHPManager getPHPManager() {
        return PHPManagerImpl.getInstance();
    }

    //登录
    public static SystemManager getSystemManager() {
        return SystemManagerImpl.getInstance();
    }

}
