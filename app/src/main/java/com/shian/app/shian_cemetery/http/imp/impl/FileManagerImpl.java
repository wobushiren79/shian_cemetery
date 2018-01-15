package com.shian.app.shian_cemetery.http.imp.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;


import com.shian.app.shian_cemetery.activity.LoginActivity;
import com.shian.app.shian_cemetery.base.BaseApplication;
import com.shian.app.shian_cemetery.http.base.FileHttpResponseHandler;
import com.shian.app.shian_cemetery.http.imp.FileManager;
import com.shian.app.shian_cemetery.http.result.HrUploadFile;
import com.shian.app.shian_cemetery.staticdata.BaseURL;
import com.shian.app.shian_cemetery.tools.LogUtils;
import com.shian.app.shian_cemetery.tools.ObjectMapperFactory;
import com.shian.app.shian_cemetery.tools.PicUtils;
import com.shian.app.shian_cemetery.tools.SharePerfrenceUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.squareup.picasso.OkHttpDownloader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import org.codehaus.jackson.JsonNode;

import java.io.File;
import java.io.IOException;
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
                           final FileHttpResponseHandler<HrUploadFile> responseHandler) {
        File file = new File(path);
//        file = PicUtils.scaledFile(file);
        String session = SharePerfrenceUtils.getSessionShare(context);
        setCookie(session);
        OkHttpUtils
                .post()
                .addFile(fileName, path, file)
                .addHeader("Cookie", "sid=" + session)
                .addHeader("systemType", "2")
                .url(BaseURL.FILE_UPDATA)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        if (responseHandler != null) {
                            responseHandler.onStart();
                        }
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        if (responseHandler != null) {
                            responseHandler.onProgress(total, progress);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.logV(response);
                        dataToJson(context, response, HrUploadFile.class, responseHandler);
                    }


                    @Override
                    public void onError(Call call, Exception e, int id) {
                        String errorMessage = e.getMessage();
                        Log.e("tag", errorMessage + "");
                        if (responseHandler != null) {
                            responseHandler.onError(errorMessage);
                        }
                    }
                });
    }

    @Override
    public RequestCall downloadFile(Context context, String downloadUrl, String fileName, final FileHttpResponseHandler<File> responseHandler) {
        RequestCall call = OkHttpUtils
                .get()
                .url(downloadUrl)
                .addHeader("Accept-Encoding", "identity")
                .build();
        call.execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName) {
            @Override
            public void onError(Call call, Exception e, int id) {
                responseHandler.onError(call.toString());
            }

            @Override
            public void onResponse(File response, int id) {
                responseHandler.onSuccess(response);
            }

            @Override
            public void inProgress(float progress, long total, int id) {
                responseHandler.onProgress(total, progress);
            }

        });
        return call;
    }
    /**
     * 数据处理
     *
     * @param context
     * @param response
     * @param data
     * @param responseHandler
     * @param <T>
     */
    private <T> void dataToJson(Context context, String response, final Class<T> data, FileHttpResponseHandler<T> responseHandler) {
        if (response != null) {
            try {
                JsonNode node = ObjectMapperFactory.getInstance().readTree(new String(response));
                String code = node.findValue("code").toString();
                String errorMsg = node.findValue("message").toString();
                if ("1000".equals(code)) {
                    JsonNode jn = node.findValue("content");
                    if (jn == null)
                        responseHandler.onSuccess(null);
                    else {
                        T result = ObjectMapperFactory.getInstance().readValue(
                                jn, data);
                        responseHandler.onSuccess(result);
                    }
                } else if ("1009".equals(code)||"9999".equals(code)) {
                    if (context instanceof Activity) {
                        BaseApplication.getApplication().exitAPP();
                    }
                    Intent in = new Intent(context, LoginActivity.class);
                    context.startActivity(in);
                } else {
                    onErrorCallBack(responseHandler, errorMsg, context);
                }
            } catch (Exception e) {
                onErrorCallBack(responseHandler, "", context);
            }
        }
    }

    /**
     * 异常回调
     *
     * @param response
     * @param error
     * @param context
     */
    private <T> void onErrorCallBack(FileHttpResponseHandler<T> response, String error,
                                     Context context) {
        if (response != null && ((context instanceof Activity) && !((Activity) context)
                .isFinishing()) && error != null) {
            if (showToast(context, error)) {
                response.onError(error);
            }
        }
    }

    /**
     * 错误提示
     *
     * @param ctx
     * @param msg
     * @return
     */
    private boolean showToast(Context ctx, String msg) {
        boolean flag = true;
        if (!"".equals(msg))
            ToastUtils.showShortToast(ctx, msg);
        return flag;
    }
}
