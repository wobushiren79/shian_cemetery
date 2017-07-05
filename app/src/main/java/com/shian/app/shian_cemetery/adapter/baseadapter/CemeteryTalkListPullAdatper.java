package com.shian.app.shian_cemetery.adapter.baseadapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.car.CarOrderActivity;
import com.shian.app.shian_cemetery.activity.car.CarOrderDetailsActivity;
import com.shian.app.shian_cemetery.activity.cemetery.InfoDetailsActivity;
import com.shian.app.shian_cemetery.activity.cemetery.TalkFailActivity;
import com.shian.app.shian_cemetery.activity.cemetery.TalkSuccessActivity;
import com.shian.app.shian_cemetery.appenum.CemeteryBeSpeakStateEnum;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.model.CemeteryOrderModel;
import com.shian.app.shian_cemetery.http.params.HpCetemeryAcceptParams;
import com.shian.app.shian_cemetery.http.params.HpCetemeryRejectParams;
import com.shian.app.shian_cemetery.staticdata.AppData;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.tools.Utils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/4/10.
 */

public class CemeteryTalkListPullAdatper extends BaseAdapter {
    Context context;
    List<CemeteryOrderModel> listData = new ArrayList<>();
    CallBack callBack;

    public CemeteryTalkListPullAdatper(Context context, List<CemeteryOrderModel> listData) {
        this.context = context;
        this.listData = listData;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
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
                    holder.ivPhone = (ImageView) convertView.findViewById(R.id.iv_phone);
                    holder.tvState = (TextView) convertView.findViewById(R.id.tv_state);
                    holder.tvSource = (TextView) convertView.findViewById(R.id.tv_source);
                    holder.tvSalesConsultant = (TextView) convertView.findViewById(R.id.tv_cemeteryconsultant);
                    holder.tvCar = (TextView) convertView.findViewById(R.id.tv_car);
                    break;
                case 1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_cemetery_talk_list_2, null);
                    holder.tvCustomerName = (TextView) convertView.findViewById(R.id.tv_customername);
                    holder.tvCustomerPhone = (TextView) convertView.findViewById(R.id.tv_customerphone);
                    holder.tvCustomerLocation = (TextView) convertView.findViewById(R.id.tv_customerlocation);
                    holder.tvMeetTime = (TextView) convertView.findViewById(R.id.tv_meettime);
                    holder.tvCemeteryLocation = (TextView) convertView.findViewById(R.id.tv_cemeterylocation);
                    holder.tvTalkSuccess = (TextView) convertView.findViewById(R.id.tv_talksuccess);
                    holder.tvTalkFail = (TextView) convertView.findViewById(R.id.tv_talkfail);
                    holder.tvTraffic = (TextView) convertView.findViewById(R.id.tv_traffic);
                    holder.tvRemark = (TextView) convertView.findViewById(R.id.tv_remark);
                    holder.ivPhone = (ImageView) convertView.findViewById(R.id.iv_phone);
                    holder.tvState = (TextView) convertView.findViewById(R.id.tv_state);
                    holder.tvSource = (TextView) convertView.findViewById(R.id.tv_source);
                    holder.tvSalesConsultant = (TextView) convertView.findViewById(R.id.tv_cemeteryconsultant);
                    holder.tvCar = (TextView) convertView.findViewById(R.id.tv_car);
                    break;
                case 2:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_cemetery_talk_list_3, null);
                    holder.tvAgentManName = (TextView) convertView.findViewById(R.id.tv_agentmanname);
                    holder.tvAgentManPhone = (TextView) convertView.findViewById(R.id.tv_agentmanphone);
                    holder.tvDeadManName = (TextView) convertView.findViewById(R.id.tv_deadmanname);
                    holder.tvCemeteryName = (TextView) convertView.findViewById(R.id.tv_cemeteryname);
                    holder.tvLocationName = (TextView) convertView.findViewById(R.id.tv_locationname);
                    holder.tvDetails = (TextView) convertView.findViewById(R.id.tv_details);
                    holder.tvState = (TextView) convertView.findViewById(R.id.tv_state);
                    holder.ivPhone = (ImageView) convertView.findViewById(R.id.iv_phone);
                    holder.tvSource = (TextView) convertView.findViewById(R.id.tv_source);
                    holder.tvSalesConsultant = (TextView) convertView.findViewById(R.id.tv_cemeteryconsultant);
                    holder.tvCar = (TextView) convertView.findViewById(R.id.tv_car);
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
                    //接单
                    acceptedOrder(data);
                } else if (v == holder.tvReject) {
                    //拒单
                    rejectOrder(data);
                } else if (v == holder.tvTalkFail) {
                    //洽谈失败
                    talkFail(data);
                } else if (v == holder.tvTalkSuccess) {
                    //洽谈成功
                    talkSuccess(data);
                } else if (v == holder.tvDetails) {
                    //订单详情
                    orderInfo(data);
                }
            }
        };
        //-------------------------------------------------------------------------------------------
        /**
         * 设置状态
         */
        if (holder.tvState != null)
            setState(holder, data);
        //-------------------------------------------------------------------------------------------
        switch (itemType) {
            case 0:
                holder.tvCustomerName.setText(data.getCustomerName());
                holder.tvCustomerPhone.setText(data.getCustomerMobile());
                holder.tvCustomerLocation.setText(data.getCustomerLocation());
                holder.tvMeetTime.setText(data.getPromiseTime());
                holder.tvCemeteryLocation.setText(data.getPlanCemeteryLocation());
                holder.tvSource.setText(data.getSourceClassText());
                holder.tvSalesConsultant.setText(data.getSalesConsultant());
                if (data.getBespeakStatus() == CemeteryBeSpeakStateEnum.undistributed.getCode()
                        || data.getBespeakStatus() == CemeteryBeSpeakStateEnum.talkFail.getCode()
                        || data.getBespeakStatus() == CemeteryBeSpeakStateEnum.unassigned.getCode()) {
                    holder.tvAccept.setVisibility(View.GONE);
                    holder.tvReject.setVisibility(View.GONE);
                } else {
                    holder.tvAccept.setVisibility(View.VISIBLE);
                    holder.tvReject.setVisibility(View.VISIBLE);
                }
                holder.tvAccept.setOnClickListener(onClickListener);
                holder.tvReject.setOnClickListener(onClickListener);
                makePhone(holder.ivPhone, data);
                takeCar(holder.tvCar, data);
                break;

            case 1:
                holder.tvCustomerName.setText(data.getCustomerName());
                holder.tvCustomerPhone.setText(data.getCustomerMobile());
                holder.tvCustomerLocation.setText(data.getCustomerLocation());
                holder.tvMeetTime.setText(data.getPromiseTime());
                holder.tvCemeteryLocation.setText(data.getPlanCemeteryLocation());
                holder.tvTraffic.setText(data.getTrafficWay());
                holder.tvRemark.setText(data.getRemarks());
                holder.tvSource.setText(data.getSourceClassText());
                holder.tvSalesConsultant.setText(data.getSalesConsultant());
                holder.tvTalkSuccess.setOnClickListener(onClickListener);
                holder.tvTalkFail.setOnClickListener(onClickListener);
                makePhone(holder.ivPhone, data);
                takeCar(holder.tvCar, data);
                break;
            case 2:
                holder.tvAgentManName.setText(data.getAgentmanName());
                holder.tvAgentManPhone.setText(data.getAgentmanMoblie());
                holder.tvDeadManName.setText(data.getDeadmanName());
                holder.tvCemeteryName.setText(data.getChoiceCemeteryName());
                holder.tvLocationName.setText(data.getDetailsLocation());
                holder.tvSource.setText(data.getSourceClassText());
                holder.tvSalesConsultant.setText(data.getSalesConsultant());
                holder.tvDetails.setOnClickListener(onClickListener);
                makePhone(holder.ivPhone, data);
                takeCar(holder.tvCar, data);
                break;
        }
        //-------------------------------------------------------------------------------------------


        return convertView;
    }

    /**
     * 设置状态
     *
     * @param holder
     */
    private void setState(ViewHolder holder, CemeteryOrderModel data) {
        CemeteryBeSpeakStateEnum[] beSpeakState = {
                CemeteryBeSpeakStateEnum.undistributed,
                CemeteryBeSpeakStateEnum.unassigned,
                CemeteryBeSpeakStateEnum.unProcess,
                CemeteryBeSpeakStateEnum.accepted,
                CemeteryBeSpeakStateEnum.talkFail,
                CemeteryBeSpeakStateEnum.talkSuccess,
                CemeteryBeSpeakStateEnum.serviceOver,
                CemeteryBeSpeakStateEnum.ready
        };
        for (CemeteryBeSpeakStateEnum state : beSpeakState) {
            if (data.getBespeakStatus() == state.getCode()) {
                holder.tvState.setText(state.getText());
                return;
            }
        }
    }

    private void takeCar(TextView tvCar, final CemeteryOrderModel data) {
        if (data.getIsSentCar() == 0) {
            tvCar.setVisibility(View.GONE);
//            tvCar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, CarOrderActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable(IntentName.INTENT_DATA, data);
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//                    if (callBack != null)
//                        callBack.refresh();
//                }
//            });
        } else {
            tvCar.setVisibility(View.VISIBLE);
            tvCar.setText("用车记录");
            tvCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CarOrderDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(IntentName.INTENT_DATA, data);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        CemeteryOrderModel data = listData.get(position);
        if (data.getBespeakStatus() == CemeteryBeSpeakStateEnum.undistributed.getCode()
                || data.getBespeakStatus() == CemeteryBeSpeakStateEnum.talkFail.getCode()
                || data.getBespeakStatus() == CemeteryBeSpeakStateEnum.unassigned.getCode()) {
            return 0;
        } else if (data.getBespeakStatus() == CemeteryBeSpeakStateEnum.unProcess.getCode()) {
            return 0;
        } else if (data.getBespeakStatus() == CemeteryBeSpeakStateEnum.accepted.getCode()
                || data.getBespeakStatus() == CemeteryBeSpeakStateEnum.talkAgain.getCode()
                || data.getBespeakStatus() == CemeteryBeSpeakStateEnum.talkSuccess.getCode()
                || data.getBespeakStatus() == CemeteryBeSpeakStateEnum.ready.getCode()) {
            if (data.getIsEditInfo() == 1) {
                return 1;
            } else {
                return 2;
            }
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
        TextView tvTraffic;
        TextView tvRemark;
        TextView tvSource;
        TextView tvSalesConsultant;
        TextView tvAgentManName;
        TextView tvAgentManPhone;
        TextView tvDeadManName;
        TextView tvCemeteryName;
        TextView tvLocationName;
        TextView tvCar;
        ImageView ivPhone;

        TextView tvAccept;
        TextView tvReject;
        TextView tvTalkSuccess;
        TextView tvTalkFail;
        TextView tvDetails;

        TextView tvState;
    }

    /**
     * 打电话
     */
    private void makePhone(View v, CemeteryOrderModel model) {
        Utils.call(v, model.getCustomerMobile());
    }

    /**
     * 接单
     */
    private void acceptedOrder(CemeteryOrderModel model) {
        HpCetemeryAcceptParams params = new HpCetemeryAcceptParams();
        params.setBespeakAssignId(model.getBespeakAssignId());
        params.setBespeakId(model.getBespeakId());
        params.setCemeteryId(model.getCemeteryId());
        params.setUserId(AppData.UserLoginResult.getUserId());
        MHttpManagerFactory.getAccountManager().acceptCemetery(context,
                params, new HttpResponseHandler<Object>() {

                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(Object result) {
                        if (callBack != null)
                            callBack.refresh();
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
        HpCetemeryRejectParams params = new HpCetemeryRejectParams();
        params.setBespeakId(model.getBespeakId());
        params.setBespeakAssignId(model.getBespeakAssignId());
        MHttpManagerFactory.getAccountManager().rejectCemetery(context,
                params, new HttpResponseHandler<Object>() {

                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(Object result) {
                        if (callBack != null)
                            callBack.refresh();
                        ToastUtils.showShortToast(context, "拒单成功");
                    }


                    @Override
                    public void onError(String message) {
                        ToastUtils.showShortToast(context, "拒单失败");
                    }
                });
    }

    /**
     * 洽谈失败
     */
    private void talkFail(CemeteryOrderModel model) {
        Intent intent = new Intent(context, TalkFailActivity.class);
        intent.putExtra(IntentName.INTENT_BESPEAKID, model.getBespeakId());
        context.startActivity(intent);
    }

    /**
     * 洽谈成功
     */
    private void talkSuccess(CemeteryOrderModel model) {
        Intent intent = new Intent(context, TalkSuccessActivity.class);
        intent.putExtra(IntentName.INTENT_BESPEAKID, model.getBespeakId());
        intent.putExtra(IntentName.INTENT_ORDERID, model.getOrderId());
        intent.putExtra(IntentName.INTENT_CEMETERY_INFO_STEPS, model.getInfoStatus());
        context.startActivity(intent);
    }

    /**
     * 查看详情界面
     *
     * @param model
     */
    private void orderInfo(CemeteryOrderModel model) {
        Intent intent = new Intent(context, InfoDetailsActivity.class);
        intent.putExtra(IntentName.INTENT_BESPEAKID, model.getBespeakId());
        intent.putExtra(IntentName.INTENT_ORDERID, model.getOrderId());
        context.startActivity(intent);
    }

    public interface CallBack {
        void refresh();
    }
}
