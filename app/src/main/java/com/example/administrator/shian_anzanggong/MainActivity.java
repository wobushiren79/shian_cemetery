package com.example.administrator.shian_anzanggong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.shian_anzanggong.base.BaseActivity;
import com.example.administrator.shian_anzanggong.base.BaseAppliction;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                        Toast.makeText(MainActivity.this, "response:"+response, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
