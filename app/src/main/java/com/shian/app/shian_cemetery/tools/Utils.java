package com.shian.app.shian_cemetery.tools;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;

import com.shian.app.shian_cemetery.activity.LoginActivity;
import com.shian.app.shian_cemetery.appenum.APPTypeEnum;
import com.shian.app.shian_cemetery.appenum.UpDataImportantEnum;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.phpparams.HpGetVersion;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetVersion;
import com.shian.app.shian_cemetery.service.UpDataService;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.staticdata.BaseURL;
import com.shian.app.shian_cemetery.view.dialog.AppUpdateDialog;
import com.shian.app.shian_cemetery.view.dialog.TipsDialog;
import com.yongchun.library.view.ImageSelectorActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.Request;

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


    /**
     * 打电话
     *
     * @param v
     * @param phone
     */
    public static void call(final View v, final String phone) {
        if (!TextUtils.isEmpty(phone)) {
            v.setVisibility(View.VISIBLE);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vv) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                    if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    v.getContext().startActivity(intent);
                }
            });
        } else {
            v.setVisibility(View.GONE);
        }
    }

    /**
     * 保存到sdcard
     *
     * @param b
     * @return
     */
    public static String savePic(Bitmap b) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss",
                Locale.US);
        File outfile = new File("/sdcard/image");
        // 如果文件不存在，则创建一个新文件
        if (!outfile.isDirectory()) {
            try {
                outfile.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String fname = outfile + "/" + sdf.format(new Date()) + ".png";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fname);
            if (null != fos) {
                b.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fname;
    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "没有找到版本号";
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号代码
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int versioncode = info.versionCode;
            return versioncode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * 检测是否有更新 并执行下载
     */
    public static void checkUpData(final Context context, final boolean isToast) {
        HpGetVersion params = new HpGetVersion();
        params.setAppId(APPTypeEnum.CEMETERY.getCode());
        MHttpManagerFactory.getPHPManager().getVersion(context, params, new HttpResponseHandler<PHPHrGetVersion>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(final PHPHrGetVersion result) {
                try {
                    float versionOld = Utils.getVersionCode(context);
                    float versionNew = Float.valueOf(result.getItems().get(0).getVersionNum());
                    if (versionNew > versionOld) {
//                        TipsDialog dialog = new TipsDialog(context);
//                        dialog.setTop("新版本：" + result.getItems().get(0).getUpdataTitle());
//                        dialog.setTitle("" + result.getItems().get(0).getUpdataContent());
//                        dialog.setBottomButton("更新", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(context, UpDataService.class);
//                                intent.putExtra("updataUrl", BaseURL.PHPURL + result.getItems().get(0).getAppDownLoadUrl());
//                                context.startService(intent);
//                                dialog.cancel();
//                            }
//                        });
//                        if (Integer.valueOf(result.getItems().get(0).getIsImportant()) == UpDataImportantEnum.IMPORTANT.getCode()) {
//                            dialog.setCancelable(false);
//                        } else {
//                            dialog.setTopButton("取消", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.cancel();
//                                }
//                            });
//                        }
//                        dialog.show();
                        AppUpdateDialog dialog = new AppUpdateDialog(context, BaseURL.PHP_BaseUrl + result.getItems().get(0).getAppDownLoadUrl(), APPTypeEnum.PLATFORM.getName());
                        dialog.setTitleTest(result.getItems().get(0).getUpdataTitle());
                        dialog.setContentTest("" + result.getItems().get(0).getUpdataContent());
                        if (result.getItems().get(0).getIsImportant() == 1) {
                            dialog.setMustBeUpdate(true);
                        } else {
                            dialog.setMustBeUpdate(false);
                        }
                        dialog.show();
                    } else {
                        if (isToast) {
                            ToastUtils.showShortToast(context, "当前已是最新版" );
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShortToast(context, "版本号获取异常");
                }
            }

            @Override
            public void onError(String message) {

            }
        }, isToast);
    }
    /**
     * 跳转到登陆界面
     * @param context
     */
    public static void jumpLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
