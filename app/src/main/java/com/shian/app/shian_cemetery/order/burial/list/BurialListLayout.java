package com.shian.app.shian_cemetery.order.burial.list;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.adapter.BurialListAdapter;

/**
 * Created by Administrator on 2017/4/5.
 */

public class BurialListLayout extends LinearLayout {
    View view;
    RecyclerView mListView;
    LinearLayout mLLSeach;
    BurialListAdapter mBurialListAdapter;

    public BurialListLayout(Context context) {
        this(context, null);
    }

    public BurialListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_burial_list, this);
        initView();
        initData();
    }

    private void initView() {
        mListView = (RecyclerView) view.findViewById(R.id.listview);
        mLLSeach = (LinearLayout) view.findViewById(R.id.ll_search);
    }

    private void initData() {
        mBurialListAdapter = new BurialListAdapter(getContext());
        mListView.setAdapter(mBurialListAdapter);
        mListView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * 是否显示搜索栏
     *
     * @param isShow
     */
    public void setSearch(boolean isShow) {
        if (isShow) {
            mLLSeach.setVisibility(VISIBLE);
        } else {
            mLLSeach.setVisibility(GONE);
        }
    }


}
