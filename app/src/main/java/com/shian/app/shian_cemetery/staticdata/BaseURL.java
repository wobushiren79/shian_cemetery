package com.shian.app.shian_cemetery.staticdata;

/**
 * Created by Administrator on 2017/4/7.
 */

public class BaseURL {

    //    登陆地址
        public static final String Login_BaseUrl = "https://platform.shianlife.cn";
//        public static final String Login_BaseUrl = "http://192.168.0.59:8099/ki4so-web";
//    public static final String Login_BaseUrl = "http://192.168.0.50:8199/platform";
//    public static final String Login_BaseUrl = "http://prd-platform.xicp.cn";
    //单项地址
        public static final String Store_BaseUrl = "https://goods.shianlife.cn";
//    public static final String Store_BaseUrl = "http://192.168.0.57:8080/goods";
//    public static final String Store_BaseUrl = "http://prd-goods.xicp.cn";
//分单地址
public static final String OrderCenter_BaseUrl = "https://order.shianlife.cn";
    //公墓地址
      public static final String Cemetery_BaseUrl = "http://120.76.246.249:8000";
//      public static final String Cemetery_BaseUrl = "http://115.28.163.211:7088/shianlife-advisor-cemetery-1.0-SNAPSHOT";
//    public static final String Cemetery_BaseUrl = "http://192.168.0.50:8098/advisor";
//        public static final String Cemetery_BaseUrl = "http://192.168.0.59:8081/app";
//    public static final String Cemetery_BaseUrl = "http://192.168.0.200:8120";


    //子系统-单项  登陆地址
    public static final String Login_Store_Url = Store_BaseUrl + "/login_sys_api";
    //子系统-分单  登陆地址
    public static final String Login_OrderCenter_Url = OrderCenter_BaseUrl + "/login_subsystem_api";
    //子系统-公墓  登陆地址
    public static final String Login_Cemetery_Url = Cemetery_BaseUrl + "/login_subsystem_api";

    public static final String OSSURL = "http://shianlife123.oss-cn-qingdao.aliyuncs.com/";
    public static final String PHP_BaseUrl = "http://app.e-funeral.cn";

    /**
     * 图片上传
     */
    public static final String FILE_UPDATA = Login_BaseUrl + "/file/upload";

    public static final String siftsPHPURL = PHP_BaseUrl + "/home/index/sifts";//精选
    public static final String helpsPHPURL = PHP_BaseUrl + "/home/index/helps";//帮助
    public static final String dynamicsPHPURL = PHP_BaseUrl + "/home/index/dynamics";//动态
    public static final String phonePHPURL = PHP_BaseUrl + "/home/index/phone";//通讯宝
    public static final String DiDichannel = "55455";//滴滴渠道号
}
