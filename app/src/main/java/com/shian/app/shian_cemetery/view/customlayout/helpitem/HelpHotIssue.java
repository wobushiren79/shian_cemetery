package com.shian.app.shian_cemetery.view.customlayout.helpitem;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.WebActivity;
import com.shian.app.shian_cemetery.activity.php.HotIssueListActivity;
import com.shian.app.shian_cemetery.appenum.HelpEnum;
import com.shian.app.shian_cemetery.common.view.ScrollListView;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.phpmodel.HotIssueData;
import com.shian.app.shian_cemetery.http.phpparams.HpHelpGetParams;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetHotIssue;
import com.shian.app.shian_cemetery.staticdata.BaseURL;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.view.customlayout.maindynamic.MainDynamic;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/3/14.
 */

public class HelpHotIssue extends LinearLayout {
    View view;

    ScrollListView mListView;
    ImageView mIVMore;
    TextView mTVTitle;

    private int showNumber = 5;//动态通知数量
    private MainDynamic.CallBack callBack;

    List<HotIssueData> listData = new ArrayList<>();

    public HelpHotIssue(Context context) {
        this(context, null);
    }

    public HelpHotIssue(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_maindynamic_layout, this);

        initView();
        getData();
    }

    public void setCallBack(MainDynamic.CallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 获取数据
     */
    private void getData() {
        HpHelpGetParams params = new HpHelpGetParams();
        params.setNumber(showNumber);
        params.setPagerNumber(0);
        MHttpManagerFactory.getPHPManager().getHotIssue(getContext(), params, new HttpResponseHandler<PHPHrGetHotIssue>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(PHPHrGetHotIssue result) {
                if (result.getItems().size() > 0) {
                    listData = result.getItems();
                    callBack.loadingComplete();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void initView() {
        mListView = (ScrollListView) view.findViewById(R.id.listView);
        mIVMore = (ImageView) view.findViewById(R.id.iv_more);
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);

        mIVMore.setOnClickListener(onClickListener);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(onItemClickListener);

        mTVTitle.setText("热门问题");
    }


    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getContext(), WebActivity.class);
            intent.putExtra("url", BaseURL.helpsPHPURL + "?id=" + listData.get(position).getId());
            getContext().startActivity(intent);
        }
    };

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVMore) {
                Intent intent = new Intent(getContext(), HotIssueListActivity.class);
                intent.putExtra(IntentName.INTENT_TITLE, HelpEnum.ALL.getName());
                intent.putExtra(IntentName.INTENT_CODE, HelpEnum.ALL.getCode());
                getContext().startActivity(intent);
            }
        }
    };

    BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return listData.size();
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
                convertView = View.inflate(getContext(), R.layout.view_main_dynamic_layout_items, null);
                holder = new ViewHolder();
                holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
                holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                holder.tvTop = (TextView) convertView.findViewById(R.id.tv_top);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, HelpHotIssue.this.getResources().getDimensionPixelOffset(R.dimen.dimen_72dp));
            convertView.setLayoutParams(layoutParams);

            HotIssueData data = listData.get(position);
            holder.tvContent.setText(data.getTitle());
            holder.tvTime.setVisibility(GONE);
            if (position == 0) {
                holder.tvTop.setVisibility(VISIBLE);
            } else {
                holder.tvTop.setVisibility(GONE);
            }
            return convertView;
        }

        class ViewHolder {
            TextView tvContent;
            TextView tvTime;
            TextView tvTop;
        }
    };

    public interface CallBack {

        void loadingComplete();

    }
}
