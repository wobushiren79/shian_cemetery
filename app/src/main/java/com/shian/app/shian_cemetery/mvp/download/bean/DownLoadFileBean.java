package com.shian.app.shian_cemetery.mvp.download.bean;

/**
 * Created by zm.
 */

public class DownLoadFileBean {
    private String downloadUrl;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
