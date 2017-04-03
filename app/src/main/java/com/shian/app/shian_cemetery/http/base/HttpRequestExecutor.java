package com.shian.app.shian_cemetery.http.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shian.app.shian_cemetery.TestActivity;
import com.shian.app.shian_cemetery.activity.LoginActivity;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.base.BaseAppliction;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/4/3.
 */

public class HttpRequestExecutor {

    Map<String, String> header = new HashMap<>();

    public HttpRequestExecutor() {
        header.put("systemType", "2");
        header.put("Content-Type", "application/json");
    }

    public void setCookie(String cookie) {
        header.put("Cookie", "sid=" + cookie);
    }

    public <T> void requestPost(final Context context,
                               final String method,
                               final Class<T> data,
                               final BaseHttpParams params,
                               final HttpResponseHandler<T> responseHandler) {
        if (!isNetworkConnected(context)) {
            onErrorCallBack(responseHandler, "网络未连接", context);
            return;
        }
        PostFormBuilder getBuilder = OkHttpUtils.post();
        getBuilder.url(method);
        getBuilder.headers(header);
        getBuilder.params(params.getMapParams());
        RequestCall requestCall = getBuilder.build();
        requestCall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                String errorMessage = e.getMessage();
                if (errorMessage != null) {
                    Log.e("tag", errorMessage);
                }
                onErrorCallBack(responseHandler, errorMessage, context);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("tag", response);
                dataToJson(context, response, data, responseHandler);
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
        GetBuilder getBuilder = OkHttpUtils.get();
        getBuilder.url(method);
        getBuilder.headers(header);
        getBuilder.params(params.getMapParams());
        RequestCall requestCall = getBuilder.build();
        requestCall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                String errorMessage = e.getMessage();
                if (errorMessage != null) {
                    Log.e("tag", errorMessage);
                }
                onErrorCallBack(responseHandler, errorMessage, context);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("tag", response);
                dataToJson(context, response, data, responseHandler);
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
    private <T> void dataToJson(Context context, String response, Class<T> data, HttpResponseHandler<T> responseHandler) {
        if (response != null) {
            Gson gsonData = new Gson();
            BaseRequestParams requestParams = gsonData.fromJson(response, BaseRequestParams.class);
            String code = requestParams.getCode() + "";
            String errorMsg = requestParams.getMessage() + "";
            T content = data.cast(requestParams.getContent());
            if ("1000".equals(code)) {
                responseHandler.onSuccess(content);
            } else if ("1009".equals(code)) {
                if (context instanceof Activity) {
                    BaseAppliction.getApplication().exitAPP();
                }
                Intent in = new Intent(context, LoginActivity.class);
                context.startActivity(in);
            } else {
                onErrorCallBack(responseHandler, errorMsg, context);
            }
        }
    }

}
