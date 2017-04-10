package com.shian.app.shian_cemetery.adapter.recyclerviewadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.burial.SetteleActivity;
import com.shian.app.shian_cemetery.activity.burial.BurialActivity;

/**
 * Created by Administrator on 2017/4/5.
 */

public class BurialListAdapter extends RecyclerView.Adapter<BurialListAdapter.ViewHolder> {
    Context context;

    public BurialListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BurialListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        ViewHolder holder = null;
        View view = null;
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, context.getResources().getDimensionPixelOffset(R.dimen.dimen_142dp));
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.item_burial_list_2, parent, false);
                view.setLayoutParams(layoutParams);
                holder = new ViewHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.item_burial_list_1, parent, false);
                view.setLayoutParams(layoutParams);
                holder = new ViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final BurialListAdapter.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                //----------------------------------------------------------------------------------------
                holder.tvNum.setText("合计：3");
                holder.tvTime.setText("04/22");
                //----------------------------------------------------------------------------------------
                break;
            case 1:
                //----------------------------------------------------------------------------------------
                //点击滑动事件
                holder.itemView.setOnClickListener(new View.OnClickListener() {
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

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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

        public ViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipelayout);
            tvSettele = (TextView) itemView.findViewById(R.id.tv_settele);
            tvBurial = (TextView) itemView.findViewById(R.id.tv_burial);

            tvLocation1 = (TextView) itemView.findViewById(R.id.tv_location_1);
            tvLocation2 = (TextView) itemView.findViewById(R.id.tv_location_2);
            tvLocation3 = (TextView) itemView.findViewById(R.id.tv_location_3);
            tvLocation4 = (TextView) itemView.findViewById(R.id.tv_location_4);
            tvSetteleState = (TextView) itemView.findViewById(R.id.tv_settele_state);

            tvName1 = (TextView) itemView.findViewById(R.id.tv_name_1);

            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvNum = (TextView) itemView.findViewById(R.id.tv_num);
        }
    }


}
