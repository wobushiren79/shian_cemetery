package com.shian.app.shian_cemetery.http.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.shian.app.shian_cemetery.activity.LoginActivity;
import com.shian.app.shian_cemetery.staticdata.BaseURL;
import com.shian.app.shian_cemetery.tools.ObjectMapperFactory;
import com.shian.app.shian_cemetery.tools.SharePerfrenceUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.view.dialog.CustomDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import org.codehaus.jackson.JsonNode;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/4/3.
 */

public class HttpRequestExecutor {

    Map<String, String> header = new HashMap<>();
    CustomDialog dialog;

    public HttpRequestExecutor() {
        header.put("systemType", "2");
        header.put("Content-Type", "application/json");
    }

    public void setCookie(String cookie) {
        header.put("Cookie", "sid=" + cookie);
    }

    /**
     * post请求
     *
     * @param context
     * @param method
     * @param data
     * @param params
     * @param responseHandler
     * @param <T>
     */
    public <T> void requestPost(final Context context,
                                final String method,
                                final Class<T> data,
                                final BaseHttpParams params,
                                final HttpResponseHandler<T> responseHandler) {
        if (!isNetworkConnected(context)) {
            onErrorCallBack(responseHandler, "网络未连接", context);
            return;
        }
        if (!method.contains("doLogin")) {
            String session = SharePerfrenceUtils.getSessionShare(context);
            setCookie(session);
        }

        PostStringBuilder getBuilder = OkHttpUtils.postString();
        getBuilder.url(BaseURL.JAVA_URL + "/" + method);
        getBuilder.headers(header);
        getBuilder.content(params.getContentJson());
        Log.i("tag", BaseURL.JAVA_URL + "/" + method);
        Log.e("tag", params.getContentJson());
        RequestCall requestCall = getBuilder.build();
        requestCall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                String errorMessage = e.getMessage();
                if (errorMessage != null) {
                    Log.e("tag", errorMessage);
                }
                onErrorCallBack(responseHandler, errorMessage, context);
                if (dialog != null)
                    dialog.cancel();
                dialog = null;
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("tag", response);
                dataToJson(context, response, data, responseHandler);
                if (dialog != null)
                    dialog.cancel();
                dialog = null;
            }


            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
                if (responseHandler != null) {
                    responseHandler.onStart(request, id);
                }
            }
        });
    }


    /**
     * POST请求
     *
     * @param context
     * @param method
     * @param data
     * @param params
     * @param responseHandler
     * @param isShowDialog
     * @param <T>
     */
    public <T> void requestPost(final Context context,
                                final String method,
                                final Class<T> data,
                                final BaseHttpParams params,
                                final HttpResponseHandler<T> responseHandler,
                                final boolean isShowDialog) {
        if (isShowDialog && dialog == null) {
            dialog = new CustomDialog(context);
            dialog.show();
        }
        requestPost(context, method, data, params, responseHandler);
    }

    /**
     * get请求
     *
     * @param context
     * @param method
     * @param data
     * @param params
     * @param responseHandler
     * @param <T>
     */
    public <T> void requestGet(final Context context,
                               final String method,
                               final Class<T> data,
                               final BaseHttpParams params,
                               final HttpResponseHandler<T> responseHandler) {
        if (!isNetworkConnected(context)) {
            onErrorCallBack(responseHandler, "网络未连接", context);
            return;
        }
        if (!method.contains("doLogin")) {
            String session = SharePerfrenceUtils.getSessionShare(context);
            setCookie(session);
        }
        GetBuilder getBuilder = OkHttpUtils.get();
        getBuilder.url(BaseURL.JAVA_URL + "/" + method);
        getBuilder.headers(header);
        getBuilder.params(params.getMapParams());
        Log.i("tag", BaseURL.JAVA_URL + "/" + method);
        Log.e("tag", params.getContentJson());
        RequestCall requestCall = getBuilder.build();
        requestCall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                String errorMessage = e.getMessage();
                if (errorMessage != null) {
                    Log.e("tag", errorMessage);
                }
                onErrorCallBack(responseHandler, errorMessage, context);
                if (dialog != null)
                    dialog.cancel();
                dialog = null;
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("tag", response);
                dataToJson(context, response, data, responseHandler);
                if (dialog != null)
                    dialog.cancel();
                dialog = null;
            }


            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
                if (responseHandler != null) {
                    responseHandler.onStart(request, id);
                }
            }
        });
    }

    public <T> void requestGet(final Context context,
                               final String method,
                               final Class<T> data,
                               final BaseHttpParams params,
                               final HttpResponseHandler<T> responseHandler,
                               final boolean isShowDialog) {
        if (isShowDialog && dialog == null) {
            dialog = new CustomDialog(context);
            dialog.show();
        }
        requestGet(context, method, data, params, responseHandler);
    }


    /**
     * PHPget请求
     *
     * @param context
     * @param method
     * @param data
     * @param params
     * @param responseHandler
     */
    public <T> void requestPHPGet(final Context context,
                                  final String method,
                                  final Class<T> data,
                                  final BaseHttpParams params,
                                  final HttpResponseHandler<T> responseHandler, final boolean isShowDialog) {
        if (!isNetworkConnected(context)) {
            onErrorCallBack(responseHandler, "网络未连接", context);
            return;
        }
        if (isShowDialog && dialog == null) {
            dialog = new CustomDialog(context);
            dialog.show();
        }
        GetBuilder getBuilder = OkHttpUtils.get();
        getBuilder.url(BaseURL.PHPURL + "/" + method);
        getBuilder.headers(header);
        getBuilder.params(params.getMapParams());
        Log.i("tag", BaseURL.PHPURL + "/" + method);
        Log.e("tag", params.getContentJson());
        RequestCall requestCall = getBuilder.build();
        requestCall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                String errorMessage = e.getMessage();
                if (errorMessage != null) {
                    Log.e("tag", errorMessage);
                }
                onErrorCallBack(responseHandler, errorMessage, context);
                if (dialog != null)
                    dialog.cancel();
                dialog = null;
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("tag", response);
                dataToJson(context, response, data, responseHandler);
                if (dialog != null)
                    dialog.cancel();
                dialog = null;
            }


            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
                if (responseHandler != null) {
                    responseHandler.onStart(request, id);
                }
            }
        });
    }

    /**
     * 判断是否有网络
     *
     * @param context
     * @return
     */
    private boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 异常回调
     *
     * @param response
     * @param error
     * @param context
     */
    private <T> void onErrorCallBack(HttpResponseHandler<T> response, String error,
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

    /**
     * 数据处理
     *
     * @param context
     * @param response
     * @param data
     * @param responseHandler
     * @param <T>
     */
    private <T> void dataToJson(Context context, String response, final Class<T> data, HttpResponseHandler<T> responseHandler) {
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
                } else if ("1009".equals(code)) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    onErrorCallBack(responseHandler, errorMsg, context);
                }
            } catch (Exception e) {
                onErrorCallBack(responseHandler, "", context);
            }
        }
    }

}
