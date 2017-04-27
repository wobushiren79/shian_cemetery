package com.shian.app.shian_cemetery.appenum;


import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.cemetery.BuildNewOrderActivity;

/**
 * Created by Administrator on 2017/3/30.
 */

public enum BuildOrderEnum {

    BY("殡仪", R.drawable.zhy_build_order_by, BuildNewOrderActivity.class),
    GM("公墓", R.drawable.zhy_build_order_gm, BuildNewOrderActivity.class);

    private String name;
    private int iconId;
    private Class<?> activity;

    BuildOrderEnum(String name, int iconId, Class<?> activity) {
        this.name = name;
        this.iconId = iconId;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity(Class<?> activity) {
        this.activity = activity;
    }
}
