package com.shian.app.shian_cemetery.base;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.shian.app.shian_cemetery.common.local.LocationService;

import com.shian.app.shian_cemetery.http.base.SSLSocketFactoryCompat;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/3/1.
 */

public class BaseApplication extends Application {
    private static BaseApplication baseApplication = null;

    List<Activity> listActivity = new ArrayList<>();

    public DisplayImageOptions options;
    public ImageLoaderConfiguration config;
    public LocationService locationService;

    /**
     * acitivity关闭时候，删除activity列表中的activity对象
     */
    public void removeActivity(Activity a) {
        listActivity.remove(a);
    }

    /**
     * 向activiy列表中添加对象
     */
    public void addActivity(Activity a) {
        listActivity.add(a);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initOkHttp();
        initImageLoader();
        initMap();

    }

    /**
     * 初始化地图
     */
    private void initMap() {
        /***
         * 初始化定位sdk，建议在Application中创建
         */        //百度地图初始化
        locationService = new LocationService(getApplicationContext());
        SDKInitializer.initialize(getApplicationContext());
    }

    /**
     * 单例
     *
     * @return
     */
    public static BaseApplication getApplication() {
        if (baseApplication == null) {
            baseApplication = new BaseApplication();
        }
        return baseApplication;
    }

    /**
     * 初始化imageloader
     */
    private void initImageLoader() {
        //---------DisplayImageOptions设置-------------------------------------

        options = new DisplayImageOptions.Builder()// 开始构建, 显示的图片的各种格式
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .cacheInMemory(true)// 开启内存缓存
                .cacheOnDisk(true) // 开启硬盘缓存
                .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少；避免使用RoundedBitmapDisplayer.他会创建新的ARGB_8888格式的Bitmap对象；
                .displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
                .displayer(new SimpleBitmapDisplayer())// 正常显示一张图片　
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型;使用.bitmapConfig(Bitmap.config.RGB_565)代替ARGB_8888;
                .considerExifParams(true)// 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY)// 缩放级别
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//这两种配置缩放都推荐
                .build();// 构建完成（参数可以不用设置全，根据需要来配置）
        //--------------- ImageLoaderConfiguration配置----------------------

        config = new ImageLoaderConfiguration.Builder(getApplicationContext())// 开始构建 ,图片加载配置
                .threadPriority(Thread.NORM_PRIORITY - 2)// 设置线程优先级
                .threadPoolSize(3)// 线程池内加载的数量 ;减少配置之中线程池的大小，(.threadPoolSize).推荐1-5；
                .denyCacheImageMultipleSizesInMemory()// 设置加载的图片有多样的
                .tasksProcessingOrder(QueueProcessingType.LIFO)// 图片加载任务顺序
                .memoryCache(new WeakMemoryCache())//使用.memoryCache(new WeakMemoryCache())，不要使用.cacheInMemory();
                .memoryCacheExtraOptions(480, 800) // 即保存的每个缓存文件的最大长宽
                .memoryCacheSizePercentage(60)// 图片内存占应用的60%；
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())//使用HASHCODE对UIL进行加密命名
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())// 将保存的时候的URI名称用MD5 加密
                .diskCacheSize(50 * 1024 * 1024) // 缓存设置大小为50 Mb
//                .diskCache(new UnlimitedDiskCache(cacheDir))// 自定义缓存路径
                .diskCacheFileCount(100) // 缓存的文件数量
                .denyCacheImageMultipleSizesInMemory()// 自动缩放
                .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .memoryCacheExtraOptions(480, 800)//设置缓存图片时候的宽高最大值，默认为屏幕宽高;保存的每个缓存文件的最大长宽
                .defaultDisplayImageOptions(options)// 如果需要打开缓存机制，需要自己builde一个option,可以是DisplayImageOptions.createSimple()
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 初始化Okhttp
     */
    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//                .addInterceptor(new LoggerInterceptor("TAG"));
        try {
            // 自定义一个信任所有证书的TrustManager，添加SSLSocketFactory的时候要用到
            final X509TrustManager trustAllCert =
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    };
            final SSLSocketFactory sslSocketFactory = new SSLSocketFactoryCompat(trustAllCert);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            builder.sslSocketFactory(sslSocketFactory, trustAllCert);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        OkHttpClient okHttpClient = builder.connectTimeout(60000L, TimeUnit.MILLISECONDS)
                .readTimeout(60000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * 退出APP
     */
    public void exitAPP() {
        for (int i = 0; i < listActivity.size(); i++) {
            listActivity.get(i).finish();
        }
//        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
