package com.example.administrator.shian_anzanggong.base;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */

public class BaseAppliction extends Application {
   List<Activity> listActivity=new ArrayList<>();

    /**
     * acitivity关闭时候，删除activity列表中的activity对象
     */
    public void removeActivity(Activity a)
    {
        listActivity.remove(a);
    }

    /**
     * 向activiy列表中添加对象
     */
    public void addActivity(Activity a)
    {
        listActivity.add(a);
    }
}
