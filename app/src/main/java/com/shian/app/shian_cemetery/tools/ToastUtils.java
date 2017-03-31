package com.shian.app.shian_cemetery.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ToastUtils {
    public static void showShortToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
