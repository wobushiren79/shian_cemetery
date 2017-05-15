package com.shian.app.shian_cemetery.adapter.baseadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.burial.BurialActivity;
import com.shian.app.shian_cemetery.activity.burial.SetteleActivity;
import com.shian.app.shian_cemetery.appenum.AppRolePermition;
import com.shian.app.shian_cemetery.appenum.BurialStateEnum;
import com.shian.app.shian_cemetery.appenum.SetteleStateEnum;
import com.shian.app.shian_cemetery.common.bean.BurialDateBean;
import com.shian.app.shian_cemetery.common.local.Utils;
import com.shian.app.shian_cemetery.http.model.BurialListDataModel;
import com.shian.app.shian_cemetery.http.result.HrGetBurialListData;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.tools.CheckUtils;
import com.shian.app.shian_cemetery.tools.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */

public class BurialListPullAdapter extends BaseExpandableListAdapter {
    Context context;
    List<BurialListDataModel> listData = new ArrayList<>();
    List<BurialDateBean> listDay = new ArrayList<>();
    HrGetBurialListData resultData;

    public BurialListPullAdapter(Context context) {
        this.context = context;
    }

    /**
     * 重置数据
     *
     * @param resultData
     */
    public void setData(HrGetBurialListData resultData) {
        listData.clear();
        listDay.clear();

        this.resultData = resultData;
        this.listData = resultData.getList();
        dealTime();
        this.notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param result
     */
    public void addData(HrGetBurialListData result) {
        listDay.clear();
        this.listData.addAll(result.getList());
        dealTime();
        this.notifyDataSetChanged();
    }

    /**
     * 增加时间层
     */
    public void dealTime() {
        List<Integer> dayTemp = new ArrayList<>();
        for (BurialListDataModel data : listData) {
            int day = Integer.valueOf(TimeUtils.formatTime(data.getBuryInfo().getBuryDatePre(), "dd"));
            if (!dayTemp.contains(day)) {
                dayTemp.add(day);
            }
        }
        for (Integer dayF : dayTemp) {
            List<Integer> dayListTemp = new ArrayList<>();
            List<BurialListDataModel> orderTemp = new ArrayList<>();
            int month = 0;
            for (BurialListDataModel data : listData) {
                int dayC = Integer.valueOf(TimeUtils.formatTime(data.getBuryInfo().getBuryDatePre(), "dd"));
                month = Integer.valueOf(TimeUtils.formatTime(data.getBuryInfo().getBuryDatePre(), "MM"));
                if (dayF == dayC) {
                    dayListTemp.add(dayC);
                    orderTemp.add(data);
                }
            }
            BurialDateBean dateBean = new BurialDateBean();
            dateBean.setDay(dayF);
            dateBean.setMonth(month);
            dateBean.setOrderData(orderTemp);
            listDay.add(dateBean);
        }
    }

    @Override
    public int getGroupCount() {
        return listDay.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listDay.get(groupPosition).getOrderData().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, context.getResources().getDimensionPixelOffset(R.dimen.dimen_142dp));
            convertView = LayoutInflater.from(context).inflate(R.layout.item_burial_list_2, parent, false);
            convertView.setLayoutParams(layoutParams);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BurialDateBean data = listDay.get(groupPosition);
        holder.tvNum.setText("合计：" + data.getOrderData().size());
        holder.tvTime.setText(data.getMonth() + "/" + data.getDay());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, context.getResources().getDimensionPixelOffset(R.dimen.dimen_142dp));
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
            holder.llContentBack = (LinearLayout) convertView.findViewById(R.id.ll_contentback);
            holder.ivShadow = (ImageView) convertView.findViewById(R.id.iv_shadow);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final BurialListDataModel data = listDay.get(groupPosition).getOrderData().get(childPosition);
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
                intent.putExtra(IntentName.INTENT_ORDERID, data.getOrder().getOrderId());
                context.startActivity(intent);
            }
        });
        //安葬
        holder.tvBurial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BurialActivity.class);
                intent.putExtra(IntentName.INTENT_ORDERID, data.getOrder().getOrderId());
                context.startActivity(intent);
            }
        });
        //数据设置
        holder.tvLocation1.setText(data.getTombPosition().getTombName());
        holder.tvLocation2.setText(data.getTombPosition().getParkName());
        holder.tvLocation3.setText(data.getTombPosition().getRow() + "排");
        holder.tvLocation4.setText(data.getTombPosition().getNum() + "号");
        //立碑显示判定
        if (data.getBuryInfo().getStoneStatus() == SetteleStateEnum.NOT.getCode()) {
            holder.tvSetteleState.setText("(未安碑)");
            holder.tvSetteleState.setTextColor(context.getResources().getColor(R.color.zhy_text_color_4));
            holder.tvSettele.setVisibility(View.VISIBLE);
            holder.tvSettele.setTag(false);
        } else {
            holder.tvSetteleState.setText("(已安碑)");
            holder.tvSetteleState.setTextColor(context.getResources().getColor(R.color.zhy_text_color_6));
            holder.tvSettele.setVisibility(View.GONE);
            holder.tvSettele.setTag(true);
        }
        //安葬显示判定
        if (data.getBuryInfo().getBuryStatus() == BurialStateEnum.NOT.getCode()) {
            holder.tvBurial.setVisibility(View.VISIBLE);
            holder.llContentBack.setBackgroundResource(R.drawable.zhy_button_state_item_white);
            holder.tvBurial.setTag(false);
            holder.ivShadow.setVisibility(View.VISIBLE);
        } else {
            holder.tvBurial.setVisibility(View.GONE);
            holder.llContentBack.setBackgroundResource(R.drawable.zhy_button_state_item_gray);
            holder.tvBurial.setTag(true);
            holder.ivShadow.setVisibility(View.GONE);
        }
        //权限显示判定
        List<String> listPermition = AppData.UserLoginResult.getPermitionCodes();
        if (CheckUtils.checkPermition(AppRolePermition.BURIERBUILD.getCode(), listPermition)) {
            if (!(Boolean) holder.tvSettele.getTag()) {
                holder.tvSettele.setVisibility(View.VISIBLE);
            } else {
                holder.tvSettele.setVisibility(View.GONE);
            }
        } else {
            holder.tvSettele.setVisibility(View.GONE);
        }
        if (CheckUtils.checkPermition(AppRolePermition.BURIERBURYING.getCode(), listPermition)) {
            if (!(Boolean) holder.tvBurial.getTag()) {
                holder.tvBurial.setVisibility(View.VISIBLE);
            } else {
                holder.tvBurial.setVisibility(View.GONE);
            }
        } else {
            holder.tvBurial.setVisibility(View.GONE);
        }
        //名字设置
        StringBuilder deadManNames = new StringBuilder();
        if (data.getBuryInfo().getBuryOneName() != null) {
            deadManNames.append(data.getBuryInfo().getBuryOneName());
        }
        if (data.getBuryInfo().getBuryTwoName() != null && !data.getBuryInfo().getBuryTwoName().isEmpty()) {
            deadManNames.append(" | " + data.getBuryInfo().getBuryTwoName());
        }
        holder.tvName1.setText(deadManNames);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
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
        LinearLayout llContentBack;
        ImageView ivShadow;
        //布局2控件------------------------
        TextView tvTime;
        TextView tvNum;


    }


}
