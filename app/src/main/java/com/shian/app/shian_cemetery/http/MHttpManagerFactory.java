package com.shian.app.shian_cemetery.http;


import com.shian.app.shian_cemetery.http.imp.FileManager;
import com.shian.app.shian_cemetery.http.imp.MAccountManager;
import com.shian.app.shian_cemetery.http.imp.impl.FileManagerImpl;
import com.shian.app.shian_cemetery.http.imp.impl.MAccountManagerImpl;

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
}
