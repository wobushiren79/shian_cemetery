package com.shian.app.shian_cemetery.adapter.pageradapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.shian.app.shian_cemetery.order.cemetery.BaseCemeteryOrderList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */

public class CemeteryListViewPagerAdapter extends PagerAdapter {
    Context context;
    List<BaseCemeteryOrderList> views = new ArrayList<>();

    public CemeteryListViewPagerAdapter(Context context, List<BaseCemeteryOrderList> views) {
        this.context = context;
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return  view==object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position),0);
        return views.get(position);
    }
}
