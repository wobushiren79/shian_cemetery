package com.shian.app.shian_cemetery.activity.php;

import android.os.Bundle;
import android.os.Handler;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.adapter.baseadapter.FindAdapter;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.appenum.FindEnum;
import com.shian.app.shian_cemetery.appenum.SystemTypeEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
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

public class MyCollectionActivity extends BaseActivity {

    PullToRefreshListView mListView;

    FindAdapter findAdapter;
    List<SiftListData> listDatas = new ArrayList<>();

    int number = 10;
    int pagerNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);

        setTitle("我的收藏", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        initView();
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

    private void initView() {
        mListView = (PullToRefreshListView) findViewById(R.id.pull_listview);

        findAdapter = new FindAdapter(MyCollectionActivity.this, listDatas);
        mListView.setAdapter(findAdapter);
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(onRefreshListener2);
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

    private void getData(final boolean isClean) {
        if (AppData.systemLoginInfo == null || AppData.systemLoginInfo.getUserId() == null) {
            ToastUtils.showShortToast(this, "还未登录,请重新登录");
            Utils.jumpLogin(this);
            return;
        }
        HpFindGetParams params = new HpFindGetParams();
        params.setType(FindEnum.COLLECTIONFIND.getCode());
        params.setUserid(AppData.systemLoginInfo.getUserId());
        params.setNumber(number);
        params.setPagerNumber(pagerNumber);
        params.setUserType(SystemTypeEnum.cemetery.getCode());
        MHttpManagerFactory.getPHPManager().getSiftListData(MyCollectionActivity.this, params, new HttpResponseHandler<PHPHrGetSiftListData>() {


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
