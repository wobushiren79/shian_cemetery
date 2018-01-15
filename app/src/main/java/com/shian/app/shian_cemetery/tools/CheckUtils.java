package com.shian.app.shian_cemetery.tools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.shian.app.shian_cemetery.appenum.AppRolePermition;
import com.shian.app.shian_cemetery.appenum.OrderUserEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.staticdata.AppData;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zm.
 */

public class CheckUtils {

    /**
     * 是否是邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 是否是电话号码
     *
     * @param input
     * @return
     */
    public static boolean isPhoneNumber(CharSequence input) {
        if (input == null) {
            return false;
        } else {
            String regex = "(\\+\\d+)?1[3458]\\d{9}$";
            return Pattern.matches(regex, input);
        }
    }


    /**
     * 检查权限
     *
     * @param permition
     * @param permitionList
     * @return
     */
    public static boolean checkPermition(String permition, List<String> permitionList) {
        boolean isPermition = false;
        for (String data : permitionList) {
            if (data.equals(permition)) {
                isPermition = true;
                break;
            }
        }
        return isPermition;
    }

    /**
     * 权限检测
     */
    public static boolean getPermissionToReadUserContacts(Context context, String[] permissions, String toastContent, int requestCode) {
        /**
         * 1)使用ContextCompat.chefkSelfPermission(),因为Context.permission
         * 只在棒棒糖系统中使用
         * 2）总是检查权限（即使权限被授予）因为用户可能会在设置中移除你的权限*/
        boolean isPermission = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                //权限为获取，检查用户是否被询问过并且拒绝了，如果是这样的话，给予更多
                //解释
//                if (ActivityCompat.shouldShowRequestPermissionRationale(scanForActivity(context), permission)) {
//                    //在界面上展示为什么需要該權限
//                    Toast.makeText(context, toastContent, Toast.LENGTH_SHORT).show();
//                }
                //发起请求获得用户许可,可以在此请求多个权限
                isPermission = false;
            }
        }
        if (isPermission) {
            return isPermission;
        } else {
            ActivityCompat.requestPermissions((Activity) context, permissions, requestCode);
            return isPermission;
        }
    }
    /**
     * 判断是否有网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

}
