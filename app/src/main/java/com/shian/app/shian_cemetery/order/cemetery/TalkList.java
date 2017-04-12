package com.shian.app.shian_cemetery.order.cemetery;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.adapter.baseadapter.CemeteryTalkListPullAdatper;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.bean.CemeteryOrderModel;
import com.shian.app.shian_cemetery.http.imp.impl.CemeteryOrderManagerImpl;
import com.shian.app.shian_cemetery.http.params.HpGetOrderListParams;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryListData;
import com.shian.app.shian_cemetery.tools.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/4/10.
 */

public class TalkList extends BaseCemeteryOrderList {
    View view;

    PullToRefreshListView mPullListView;
    CemeteryTalkListPullAdatper mAdapter;

    int page = 1;
    int pageSize = 10;
    List<CemeteryOrderModel> listData = new ArrayList<>();

    public TalkList(Context context) {
        this(context, null);
    }

    public TalkList(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_cemetery_order_talk, this);
        initView();
        initData();
    }


    private void initView() {
        mPullListView = (PullToRefreshListView) view.findViewById(R.id.pull_listview);
        mPullListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullListView.setOnRefreshListener(onRefreshListener2);
        mPullListView.setOverScrollMode(OVER_SCROLL_NEVER);

        mAdapter = new CemeteryTalkListPullAdatper(getContext(), listData);
        mAdapter.setCallBack(callBack);
        mPullListView.setAdapter(mAdapter);
    }


    private void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullListView.setRefreshing(true);
                getData(true);
            }
        }, 500);
    }

    /**
     * adapter响应
     */
    CemeteryTalkListPullAdatper.CallBack callBack = new CemeteryTalkListPullAdatper.CallBack() {
        @Override
        public void refresh() {
            page = 1;
            getData(true);
        }
    };

    /**
     * 上下拉刷新
     */
    PullToRefreshBase.OnRefreshListener2 onRefreshListener2 = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            page = 1;
            getData(true);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            page++;
            getData(false);
        }
    };

    /**
     * 获取数据
     */
    private void getData(final boolean isNew) {
        HpGetOrderListParams params = new HpGetOrderListParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);
        CemeteryOrderManagerImpl.getInstance().getOrderList(getContext(), params, 0, new HttpResponseHandler<HrGetCemeteryListData>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetCemeteryListData result) {
                if (isNew) {
                    listData.clear();
                    listData.addAll(result.getItems());
                } else {
                    if (result.getItems().size() == 0) {
                        page--;
                    }
                    listData.addAll(result.getItems());
                }

                mAdapter.notifyDataSetChanged();
                mPullListView.onRefreshComplete();
            }

            @Override
            public void onError(String message) {
                page--;
                ToastUtils.showShortToast(getContext(), "获取列表失败");
                mPullListView.onRefreshComplete();
            }
        });
    }

    @Override
    public void refesh() {
        page = 1;
        getData(true);
    }
}
