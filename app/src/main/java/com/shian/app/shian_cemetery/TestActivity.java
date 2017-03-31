package com.shian.app.shian_cemetery;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shian.app.shian_cemetery.base.BaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String url = "http://192.168.0.72/shianapp/home/index/index";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.v("this","response:"+response+" id:"+id);
                        Toast.makeText(TestActivity.this, "response:"+response, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
