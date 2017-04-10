package com.shian.app.shian_cemetery.adapter.baseadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.burial.BurialActivity;
import com.shian.app.shian_cemetery.activity.burial.SetteleActivity;

/**
 * Created by Administrator on 2017/4/10.
 */

public class BurialListPullAdapter extends BaseAdapter {
    Context context;
    public BurialListPullAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 100;
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
        final ViewHolder holder;
        int viewType = getItemViewType(position);
//-----------------------------------------------------------------
        if (convertView == null) {
            holder = new ViewHolder();
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, context.getResources().getDimensionPixelOffset(R.dimen.dimen_142dp));
            switch (viewType) {
                case 0:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_burial_list_2, parent, false);
                    convertView.setLayoutParams(layoutParams);

                    holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                    holder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
                    break;
                case 1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_burial_list_1, parent, false);
                    convertView.setLayoutParams(layoutParams);

                    holder.swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipelayout);
                    holder.tvSettele = (TextView) convertView.findViewById(R.id.tv_settele);
                    holder.tvBurial = (TextView) convertView.findViewById(R.id.tv_burial);
                    holder.tvLocation1 = (TextView) convertView.findViewById(R.id.tv_location_1);
                    holder.tvLocation2 = (TextView) convertView.findViewById(R.id.tv_location_2);
                    holder.tvLocation3 = (TextView) convertView.findViewById(R.id.tv_location_3);
                    holder.tvLocation4 = (TextView) convertView.findViewById(R.id.tv_location_4);
                    holder.tvSetteleState = (TextView) convertView.findViewById(R.id.tv_settele_state);
                    holder.tvName1 = (TextView) convertView.findViewById(R.id.tv_name_1);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//-----------------------------------------------------------------
        switch (viewType) {
            case 0:
                //----------------------------------------------------------------------------------------
                holder.tvNum.setText("合计：3");
                holder.tvTime.setText("04/22");
                //----------------------------------------------------------------------------------------
                break;
            case 1:
                //----------------------------------------------------------------------------------------
                //点击滑动事件
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.swipeLayout.getOpenStatus() == SwipeLayout.Status.Close) {
                            holder.swipeLayout.open();
                        } else {
                            holder.swipeLayout.close();
                        }
                    }
                });
                //立碑
                holder.tvSettele.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, SetteleActivity.class);
                        context.startActivity(intent);
                    }
                });
                //安葬
                holder.tvBurial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, BurialActivity.class);
                        context.startActivity(intent);
                    }
                });
                //数据设置
                holder.tvLocation1.setText("相思園");
                holder.tvLocation2.setText("A区");
                holder.tvLocation3.setText("10排");
                holder.tvLocation4.setText("4号");
                holder.tvSetteleState.setText("(未安碑)");
                holder.tvSetteleState.setTextColor(context.getResources().getColor(R.color.zhy_text_color_4));
                holder.tvName1.setText("张三");
                //----------------------------------------------------------------------------------------
                break;
        }
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    class ViewHolder {
        //布局1控件------------------------
        SwipeLayout swipeLayout;
        TextView tvSettele;
        TextView tvBurial;
        TextView tvLocation1;
        TextView tvLocation2;
        TextView tvLocation3;
        TextView tvLocation4;
        TextView tvSetteleState;
        TextView tvName1;
        //布局2控件------------------------
        TextView tvTime;
        TextView tvNum;
    }
}
