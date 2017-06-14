package com.shian.app.shian_cemetery.order.burial.list;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.adapter.baseadapter.BurialListPullAdapter;
import com.shian.app.shian_cemetery.appenum.BurialDateTypeEnum;
import com.shian.app.shian_cemetery.common.bean.BurialBuildDataBean;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.params.HpBurialDataListParams;
import com.shian.app.shian_cemetery.http.result.HrGetBurialListData;
import com.shian.app.shian_cemetery.tools.TimeUtils;
import com.shian.app.shian_cemetery.tools.Utils;

import java.util.Calendar;

import okhttp3.Request;

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
    PullToRefreshExpandableListView mPullListView;

    boolean isShow = false;//是否显示搜索栏
    BurialBuildDataBean dataBean;
    CallBack callBack;

    private int pageNum = 1;
    private int pageSize = 10;

    private int year;
    private int month;
    private int day;

    public BurialListLayout(Context context, BurialBuildDataBean dataBean) {
        super(context);
        this.dataBean = dataBean;
        view = View.inflate(context, R.layout.layout_burial_list, this);
        initView();
        initData();
        setDate(Integer.valueOf(TimeUtils.getSystemTime("yyyy")),
                Integer.valueOf(TimeUtils.getSystemTime("MM")),
                Integer.valueOf(TimeUtils.getSystemTime("dd")));
        setDate(dataBean.getYear(), dataBean.getMonth(), dataBean.getDay());
        getData(true);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 刷新
     */
    public void refresh() {
        pageNum = 1;
        mPullListView.setRefreshing(true);
        getData(true);
    }

    /**
     * 设置时间
     *
     * @param year
     * @param month
     * @param day
     */
    public void setDate(int year, int month, int day) {
        if (year != -1)
            this.year = year;
        if (month != -1)
            this.month = month;
        if (day != -1)
            this.day = day;
    }


    private void initView() {
//        mListView = (RecyclerView) view.findViewById(R.id.listview);
        mPullListView = (PullToRefreshExpandableListView) view.findViewById(R.id.listview);
        mRLSeach = (RelativeLayout) view.findViewById(R.id.rl_search);
        mTVSeachTime = (TextView) view.findViewById(R.id.tv_seach_time);

        mRLSeach.setOnClickListener(onClickListener);
    }

    private void initData() {
        mBurialListPullAdapter = new BurialListPullAdapter(getContext());
        mPullListView.getRefreshableView().setAdapter(mBurialListPullAdapter);
        mPullListView.setOnScrollListener(onScrollListener);
        mPullListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullListView.setOnRefreshListener(onRefreshListener2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pageNum = 1;
                mPullListView.setRefreshing(true);
                getData(true);
            }
        }, 500);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mRLSeach) {
                showDatePicker(getContext(), mTVSeachTime);
            }
        }
    };

    /**
     * 时间选择
     *
     * @param context
     * @param textView
     */
    private void showDatePicker(Context context, final TextView textView) {
        Calendar c = Calendar.getInstance();
        final int[] yearTemp = {c.get(Calendar.YEAR)};
        final int[] monthOfYearTemp = {c.get(Calendar.MONTH)};
        final int[] dayOfMonthTemp = {c.get(Calendar.DAY_OF_MONTH)};
        DatePicker datePicker = new DatePicker(context);
        datePicker.init(yearTemp[0], monthOfYearTemp[0], dayOfMonthTemp[0], new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                yearTemp[0] = year;
                monthOfYearTemp[0] = monthOfYear;
                dayOfMonthTemp[0] = dayOfMonth;
            }
        });
        AlertDialog dialog = new AlertDialog
                .Builder(context)
                .setTitle("选择日期")
                .setView(datePicker)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(yearTemp[0] + "年" + (monthOfYearTemp[0] + 1) + "月" + dayOfMonthTemp[0] + "日");
                        year = yearTemp[0];
                        month = (monthOfYearTemp[0] + 1);
                        day = dayOfMonthTemp[0];
                        dataBean.setDateType(BurialDateTypeEnum.DAY.getCode());
                        getData(true);
                        dialog.cancel();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
        dialog.show();
        return;
    }

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

    /**
     * 上下拉刷新
     */
    PullToRefreshBase.OnRefreshListener2 onRefreshListener2 = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            pageNum = 1;
            getData(true);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            pageNum++;
            getData(false);
        }
    };


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

    /**
     * 获取数据
     */
    private void getData(final boolean isNew) {
        HpBurialDataListParams params = new HpBurialDataListParams();
        params.setStatusType(dataBean.getStatusType());
        params.setStoneStatus(dataBean.getSetteleType());
        params.setBuryStatus(dataBean.getBurialType());
        params.setMultyBuryStatus(dataBean.getMultyBurialType());
        params.setDateType(dataBean.getDateType());
        params.setDate(year + "-" + month + "-" + day + " 00:00:00");
        params.setPageNum(pageNum);
        params.setPageSize(pageSize);
        MHttpManagerFactory.getAccountManager().getBurialDataList(getContext(), params, new HttpResponseHandler<HrGetBurialListData>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetBurialListData result) {
                mPullListView.onRefreshComplete();
                if (isNew) {
                    mBurialListPullAdapter.setData(result);
                } else {
                    if (result.getPages() < pageNum) {
                        pageNum--;
                    } else {
                        mBurialListPullAdapter.addData(result);
                    }
                }
                //展开下拉
                int groupCount = mBurialListPullAdapter.getGroupCount();
                for (int i = 0; i < groupCount; i++) {
                    mPullListView.getRefreshableView().expandGroup(i);
                }
            }

            @Override
            public void onError(String message) {
                mPullListView.onRefreshComplete();
            }
        });
    }

    public interface CallBack {
        void refesh();
    }
}
