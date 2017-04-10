package com.shian.app.shian_cemetery.adapter.baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.CemeteryOrderStateEnum;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.bean.CemeteryOrderModel;
import com.shian.app.shian_cemetery.http.params.HpCetemeryAcceptParams;
import com.shian.app.shian_cemetery.http.params.HpCetemeryRejectParams;
import com.shian.app.shian_cemetery.tools.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/4/10.
 */

public class CemeteryTalkListPullAdatper extends BaseAdapter {
    Context context;
    List<CemeteryOrderModel> listData = new ArrayList<>();

    public CemeteryTalkListPullAdatper(Context context, List<CemeteryOrderModel> listData) {
        this.context = context;
        this.listData = listData;
    }

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
        final ViewHolder holder;
        int itemType = getItemViewType(position);
        //-------------------------------------------------------------------------------------------
        if (convertView == null) {
            holder = new ViewHolder();
            switch (itemType) {
                case 0:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_cemetery_talk_list_1, null);
                    holder.tvCustomerName = (TextView) convertView.findViewById(R.id.tv_customername);
                    holder.tvCustomerPhone = (TextView) convertView.findViewById(R.id.tv_customerphone);
                    holder.tvCustomerLocation = (TextView) convertView.findViewById(R.id.tv_customerlocation);
                    holder.tvMeetTime = (TextView) convertView.findViewById(R.id.tv_meettime);
                    holder.tvCemeteryLocation = (TextView) convertView.findViewById(R.id.tv_cemeterylocation);
                    holder.tvAccept = (TextView) convertView.findViewById(R.id.tv_accept);
                    holder.tvReject = (TextView) convertView.findViewById(R.id.tv_reject);
                    break;
                case 1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_cemetery_talk_list_2, null);
                    break;
                case 2:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_cemetery_talk_list_3, null);
                    break;
            }

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //-------------------------------------------------------------------------------------------
        final CemeteryOrderModel data = listData.get(position);
        //-------------------------------------------------------------------------------------------
        /**
         * 点击事件
         */
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == holder.tvAccept) {
                    acceptedOrder(data);
                } else if (v == holder.tvReject) {
                    rejectOrder(data);
                }
            }
        };
        //-------------------------------------------------------------------------------------------

        switch (itemType) {
            case 0:
                holder.tvCustomerName.setText(data.getCustomerName());
                holder.tvCustomerPhone.setText(data.getCustomerMobile());
                holder.tvCustomerLocation.setText(data.getCustomerLocation());
                holder.tvMeetTime.setText(data.getPromiseTime());
                holder.tvCemeteryLocation.setText(data.getPlanCemeteryLocation());

                holder.tvAccept.setOnClickListener(onClickListener);
                holder.tvReject.setOnClickListener(onClickListener);
                break;
            case 1:
                break;
            case 2:
                break;
        }
        //-------------------------------------------------------------------------------------------


        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        CemeteryOrderModel data = listData.get(position);
        if (data.getBespeakStatus() == CemeteryOrderStateEnum.unProcess.getCode()) {
            return 0;
        } else if (data.getBespeakStatus() == CemeteryOrderStateEnum.accepted.getCode() ||
                data.getBespeakStatus() == CemeteryOrderStateEnum.talkFail.getCode()) {
            return 1;
        } else {
            return 2;
        }


    }

    class ViewHolder {
        TextView tvCustomerName;
        TextView tvCustomerPhone;
        TextView tvCustomerLocation;
        TextView tvMeetTime;
        TextView tvCemeteryLocation;

        TextView tvAccept;
        TextView tvReject;
    }

    /**
     * 接单
     */
    private void acceptedOrder(CemeteryOrderModel model) {
        HpCetemeryAcceptParams params = new HpCetemeryAcceptParams();
        params.setBespeakAssignId(model.getBespeakAssignId());
        params.setBespeakId(model.getBespeakId());
        MHttpManagerFactory.getAccountManager().acceptCemetery(context,
                params, new HttpResponseHandler<Object>() {

                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(Object result) {
//                        refresh();
                        ToastUtils.showShortToast(context, "接单成功");
                    }


                    @Override
                    public void onError(String message) {
//                        ToastUtils.show(getContext(), "接单失败");
                    }
                });

    }

    /**
     * 拒单
     */
    private void rejectOrder(CemeteryOrderModel model) {
//        HpCetemeryRejectParams params = new HpCetemeryRejectParams();
//        params.setBespeakId(model.getBespeakId());
//        params.setBespeakAssignId(model.getBespeakAssignId());
//        MHttpManagerFactory.getAccountManager().rejectCemetery(context,
//                params, new HttpResponseHandler<Object>() {
//
//                    @Override
//                    public void onStart(Request request, int id) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Object result) {
//                        refresh();
//                        ToastUtils.showShortToast(context, "拒单成功");
//                    }
//
//
//                    @Override
//                    public void onError(String message) {
////                      ToastUtils.show(getContext(), "拒单失败");
//                    }
//                });
        ToastUtils.showShortToast(context, "拒单失败");
    }
}
