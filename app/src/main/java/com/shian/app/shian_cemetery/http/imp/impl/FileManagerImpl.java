package com.shian.app.shian_cemetery.http.imp.impl;

import android.content.Context;
import android.util.Log;


import com.shian.app.shian_cemetery.http.base.FileHttpResponseHandler;
import com.shian.app.shian_cemetery.http.imp.FileManager;
import com.shian.app.shian_cemetery.http.result.HrUploadFile;
import com.shian.app.shian_cemetery.staticdata.BaseURL;
import com.shian.app.shian_cemetery.tools.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

public class FileManagerImpl implements FileManager {
    private static FileManager manager;
    Map<String, String> header = new HashMap<>();


    private FileManagerImpl() {
        header.put("systemType", "2");
    }

    public void setCookie(String cookie) {
        header.put("Cookie", "sid=" + cookie);
    }

    public static FileManager getInstance() {
        if (manager == null) {
            manager = new FileManagerImpl();
        }
        return manager;
    }

    @Override
    public void upLoadFile(final Context context, String fileName, String path,
                           final FileHttpResponseHandler<HrUploadFile> response) {
        File file = new File(path);
        OkHttpUtils
                .postFile()
                .url(BaseURL.FILE_UPDATA)
                .file(file)
                .headers(header)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        if (response != null) {
                            response.onStart();
                        }
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        if (response != null) {
                            response.onProgress(total, progress);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.logV(response);
                    }


                    @Override
                    public void onError(Call call, Exception e, int id) {
                        String errorMessage = e.getMessage();
                        Log.e("tag", errorMessage);
                        if (response != null) {
                            response.onError(errorMessage);
                        }
                    }
                });
    }

}
