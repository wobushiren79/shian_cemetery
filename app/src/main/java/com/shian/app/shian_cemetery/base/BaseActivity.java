package com.shian.app.shian_cemetery.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2017/3/1.
 */

public class BaseActivity extends Activity {

    public DisplayMetrics metrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseAppliction) getApplicationContext()).addActivity(this);
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((BaseAppliction) getApplicationContext()).removeActivity(this);
    }
}
