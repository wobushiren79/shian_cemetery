package com.shian.app.shian_cemetery.adapter.baseadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.APPEnum;
import com.shian.app.shian_cemetery.view.customlayout.mainapp.MainAPPItems;


/**
 * Created by Administrator on 2017/3/11.
 */

public class AllAppAdapter extends BaseAdapter {
    APPEnum[] dataList;
    Context contexts;
    public AllAppAdapter(Context contexts, APPEnum[]dataList) {
        this.dataList = dataList;
        this.contexts=contexts;
    }

    @Override
    public int getCount() {
        return dataList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, contexts.getResources().getDimensionPixelOffset(R.dimen.dimen_132dp));
        MainAPPItems items = new MainAPPItems(contexts);
        items.setLayoutParams(layoutParams);
        items.setData(dataList[position].getName(), dataList[position].getPicId(), dataList[position].getUrl());
        return items;
    }
}
