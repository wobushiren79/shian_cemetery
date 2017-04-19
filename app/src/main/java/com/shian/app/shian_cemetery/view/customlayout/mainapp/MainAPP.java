package com.shian.app.shian_cemetery.view.customlayout.mainapp;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.php.AllAppActivity;
import com.shian.app.shian_cemetery.appenum.APPEnum;
import com.shian.app.shian_cemetery.common.view.ScrollGridView;


/**
 * Created by Administrator on 2017/3/6.
 */

public class MainAPP extends LinearLayout {
    View view;
    ScrollGridView mGridView;
    ImageView mIVMore;


    APPEnum[] appNameList = {
            APPEnum.ZSPROJECT,
            APPEnum.CEMETERY,
            APPEnum.BEFORECONTRACT,
            APPEnum.NAVIGATION,
            APPEnum.CALENDAR,
            APPEnum.ALL
    };

    public MainAPP(Context context) {
        super(context);
    }

    public MainAPP(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_main_app_layout, this);
        initData();
        initView();
    }


    private void initData() {

    }

    private void initView() {
        mIVMore = (ImageView) view.findViewById(R.id.iv_more);
        mGridView = (ScrollGridView) view.findViewById(R.id.gridview);

        mIVMore.setOnClickListener(onClickListener);
        mGridView.setAdapter(adapter);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVMore) {
                Intent intent = new Intent(getContext(), AllAppActivity.class);
                getContext().startActivity(intent);
            }
        }
    };

    BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return appNameList.length;
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
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.dimen_132dp));
            MainAPPItems items = new MainAPPItems(getContext());
            items.setLayoutParams(layoutParams);
            items.setData(appNameList[position].getName(), appNameList[position].getPicId(), appNameList[position].getUrl());
            return items;
        }
    };
}
