package com.shian.app.shian_cemetery.order.burial.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.adapter.baseadapter.BurialListPullAdapter;
import com.shian.app.shian_cemetery.tools.Utils;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;

/**
 * Created by Administrator on 2017/4/5.
 */

public class BurialListLayout extends LinearLayout {
    View view;
    //    RecyclerView mListView;
    //    BurialListAdapter mBurialListAdapter;
    RelativeLayout mRLSeach;
    TextView mTVSeachTime;
    BurialListPullAdapter mBurialListPullAdapter;
    PullToRefreshListView mPullListView;

    boolean isShow = false;//是否显示搜索栏

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
//        mListView = (RecyclerView) view.findViewById(R.id.listview);
        mPullListView = (PullToRefreshListView) view.findViewById(R.id.listview);
        mRLSeach = (RelativeLayout) view.findViewById(R.id.rl_search);
        mTVSeachTime = (TextView) view.findViewById(R.id.tv_seach_time);

        mRLSeach.setOnClickListener(onClickListener);
    }

    private void initData() {
        mBurialListPullAdapter = new BurialListPullAdapter(getContext());
        mPullListView.setAdapter(mBurialListPullAdapter);
        mPullListView.setOnScrollListener(onScrollListener);
//        mBurialListAdapter = new BurialListAdapter(getContext());
//        mListView.setAdapter(mBurialListAdapter);
//        mListView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mListView.setOnScrollListener(onScrollListener);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mRLSeach) {
                Utils.showDatePicker(getContext(), mTVSeachTime);
            }
        }
    };

    /**
     * 滑动监听
     */
    AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (isShow) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    mRLSeach.setVisibility(VISIBLE);
                    mRLSeach.startAnimation(getInAnim());
                } else if (scrollState == SCROLL_STATE_DRAGGING) {
                    mRLSeach.setVisibility(GONE);
//                    mLLSeach.startAnimation(getOutAnim());
                }
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    };
//    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
//        @Override
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
//            if (isShow) {
//                if (newState == SCROLL_STATE_IDLE) {
//                    mRLSeach.setVisibility(VISIBLE);
//                    mRLSeach.startAnimation(getInAnim());
//                } else if (newState == SCROLL_STATE_DRAGGING) {
//                    mRLSeach.setVisibility(GONE);
////                    mLLSeach.startAnimation(getOutAnim());
//                }
//            }
//        }
//
//        @Override
//        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//            super.onScrolled(recyclerView, dx, dy);
//        }
//    };


    /**
     * 是否显示搜索栏
     *
     * @param isShow
     */
    public void setSearch(boolean isShow) {
        this.isShow = isShow;
        if (isShow) {
            mRLSeach.setVisibility(VISIBLE);
        } else {
            mRLSeach.setVisibility(GONE);
        }
    }

    /**
     * 进入动画
     */
    private Animation getInAnim() {
        TranslateAnimation translateAnimation = new TranslateAnimation
                (Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, -1f,
                        Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setDuration(100);
        return translateAnimation;
    }

    /**
     * 出动画
     */
    private Animation getOutAnim() {
        TranslateAnimation translateAnimation = new TranslateAnimation
                (Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, -1f);
        translateAnimation.setDuration(500);
        return translateAnimation;
    }

}
