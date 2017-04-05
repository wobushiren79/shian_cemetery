package com.shian.app.shian_cemetery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;

/**
 * Created by Administrator on 2017/4/5.
 */

public class BurialListAdapter extends RecyclerView.Adapter<BurialListAdapter.ViewHolder> {
    Context context;

    public BurialListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BurialListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, context.getResources().getDimensionPixelOffset(R.dimen.dimen_132dp));
        View view = LayoutInflater.from(context).inflate(R.layout.item_burial_list, parent, false);
        view.setLayoutParams(layoutParams);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BurialListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
