package com.shian.app.shian_cemetery.tools;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;

import com.yongchun.library.view.ImageSelectorActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/4/5.
 */

public class Utils {
    /**
     * 状态栏相关工具类
     */
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));
                //底部导航栏
                window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 时间选择
     *
     * @param context
     * @param textView
     */
    public static void showDatePicker(Context context, final TextView textView) {
        Calendar c = Calendar.getInstance();
        final int[] yearTemp = {c.get(Calendar.YEAR)};
        final int[] monthOfYearTemp = {c.get(Calendar.MONTH)};
        final int[] dayOfMonthTemp = {c.get(Calendar.DAY_OF_MONTH)};
        DatePicker datePicker = new DatePicker(context);
        datePicker.init(yearTemp[0], monthOfYearTemp[0], dayOfMonthTemp[0], new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                yearTemp[0] = year;
                monthOfYearTemp[0] = monthOfYear;
                dayOfMonthTemp[0] = dayOfMonth;
            }
        });
        AlertDialog dialog = new AlertDialog
                .Builder(context)
                .setTitle("选择日期")
                .setView(datePicker)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(yearTemp[0] + "年" + (monthOfYearTemp[0] + 1) + "月" + dayOfMonthTemp[0] + "日");
                        dialog.cancel();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
        dialog.show();
        return;
    }

}
