package com.shian.app.shian_cemetery.staticdata;

import com.shian.app.shian_cemetery.http.result.HrLoginResult;
import com.shian.app.shian_cemetery.mvp.login.bean.SystemLoginResultBean;

import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;

/**
 * Created by Administrator on 2017/4/7.
 */

public class AppData {
//    public static HrLoginResult UserLoginResult;
    //平台用户数据
    public static SystemLoginResultBean systemLoginInfo;
    //登陆系统KEY
    public static String System_Ki4so_Client_Ec;
    //cookie保存
    public static final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
    public static String LocalString;
    public static String LOCAL_PROVINCE = "";
    public static String LOCAL_CITY = "";
    public static String LOCAL_COUNTY = "";
    public static String LOCAL_STREET = "";
    public static String LOCAL_STREETNUM = "";
    public static String LOCAL_ADDRESS = "";
    public static double LOCAL_latitude = 30.6634450000;//纬度;
    public static double LOCAL_longitude = 104.0722210000;//经度;
}
