package com.shian.app.shian_cemetery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.php.MyCollectionActivity;
import com.shian.app.shian_cemetery.adapter.baseadapter.FindAdapter;
import com.shian.app.shian_cemetery.appenum.FindEnum;
import com.shian.app.shian_cemetery.appenum.SystemTypeEnum;
import com.shian.app.shian_cemetery.base.BaseFragment;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.phpmodel.SiftListData;
import com.shian.app.shian_cemetery.http.phpparams.HpFindGetParams;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetSiftListData;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.tools.Utils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/3/5.
 */

public class FindFragment extends BaseFragment {
    View view;
    ImageView mIVCollection;
    PullToRefreshListView mListView;

    int number = 10;
    int pagerNumber = 0;

    FindAdapter findAdapter;
    List<SiftListData> listDatas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, null, false);
        listDatas.clear();
        initView();
        return view;
    }

    private void initView() {
        mListView = (PullToRefreshListView) view.findViewById(R.id.pull_listview);
        mIVCollection = (ImageView) view.findViewById(R.id.iv_collection);

        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(onRefreshListener2);
        findAdapter = new FindAdapter(getContext(), listDatas);
        mListView.setAdapter(findAdapter);
        mIVCollection.setOnClickListener(onClickListener);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        // 开始就呈现下拉状态
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mListView.setRefreshing(true);
                pagerNumber = 0;
                getData(true);
            }
        }, 500);
    }

    PullToRefreshBase.OnRefreshListener2 onRefreshListener2 = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            pagerNumber = 0;
            getData(true);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            pagerNumber++;
            getData(false);
        }
    };
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVCollection) {
                Intent intent = new Intent(getContext(), MyCollectionActivity.class);
                startActivity(intent);
            }
        }
    };

    private void getData(final boolean isClean) {
        if (AppData.systemLoginInfo == null || AppData.systemLoginInfo.getUserId() == null) {
            ToastUtils.showShortToast(getContext(), "数据错误，请重新登陆");
            Utils.jumpLogin(getContext());
            return;
        }
        HpFindGetParams params = new HpFindGetParams();
        params.setType(FindEnum.DEFAULTFIND.getCode());
        params.setUserid(AppData.systemLoginInfo.getUserId());
        params.setNumber(number);
        params.setPagerNumber(pagerNumber);
        params.setUserType(SystemTypeEnum.cemetery.getCode());
        MHttpManagerFactory.getPHPManager().getSiftListData(getContext(), params, new HttpResponseHandler<PHPHrGetSiftListData>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(PHPHrGetSiftListData result) {
                if (isClean) {
                    listDatas.clear();
                }
                listDatas.addAll(result.getItems());
                findAdapter.setData(listDatas);
                mListView.onRefreshComplete();
            }

            @Override
            public void onError(String message) {
                mListView.onRefreshComplete();
            }
        });
    }
}
