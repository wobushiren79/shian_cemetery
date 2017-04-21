package com.shian.app.shian_cemetery.activity.php;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.WebActivity;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.phpmodel.HotIssueData;
import com.shian.app.shian_cemetery.http.phpparams.HpHelpGetParams;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetHotIssue;
import com.shian.app.shian_cemetery.staticdata.BaseURL;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class HotIssueListActivity extends BaseActivity {


    int page = 0;
    int pageNumber = 10;
    PullToRefreshListView mListView;

    List<HotIssueData> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        setTitle("热门问题",BaseTitleEnum.BACKNORMALTITLE.getTitleType());

        initView();

        // 开始就呈现下拉状态
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mListView.setRefreshing(true);
                getData(true);
            }
        }, 500);
    }

    private void initView() {
        mListView = (PullToRefreshListView) findViewById(R.id.pull_listview);

        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(onRefreshListener2);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(onItemClickListener);
    }


    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(HotIssueListActivity.this, WebActivity.class);
            intent.putExtra("url", BaseURL.helpsPHPURL + "?id=" + data.get(position - 1).getId());
            startActivity(intent);
        }
    };

    PullToRefreshBase.OnRefreshListener2 onRefreshListener2 = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            page = 0;
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
    private void getData(final boolean isClean) {
        HpHelpGetParams params = new HpHelpGetParams();
        params.setNumber(pageNumber);
        params.setPagerNumber(page);
        MHttpManagerFactory.getPHPManager().getHotIssue(HotIssueListActivity.this, params, new HttpResponseHandler<PHPHrGetHotIssue>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(PHPHrGetHotIssue result) {
                if (isClean) {
                    data.clear();
                }
                data.addAll(result.getItems());
                adapter.notifyDataSetChanged();
                mListView.onRefreshComplete();
            }

            @Override
            public void onError(String message) {
                mListView.onRefreshComplete();
            }
        });
    }

    BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return data.size();
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
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(HotIssueListActivity.this, R.layout.layout_message_items, null);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
                holder.ivRead = (ImageView) convertView.findViewById(R.id.iv_read);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            HotIssueData item = data.get(position);
            holder.tvTitle.setText(item.getTitle());
            holder.tvTime.setText(item.getTime());
            holder.tvContent.setVisibility(View.GONE);
            holder.ivRead.setVisibility(View.GONE);

            return convertView;
        }

        class ViewHolder {
            TextView tvTitle;
            TextView tvTime;
            TextView tvContent;
            ImageView ivRead;
        }
    };
}