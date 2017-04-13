package com.shian.app.shian_cemetery.order.cemetery.infolayout;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.CemeteryLocationEnum;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.params.HpCemeteryIdParams;
import com.shian.app.shian_cemetery.http.params.HpSaveCemeteryTalkSuccessContract;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryTalkSuccessContract;
import com.shian.app.shian_cemetery.http.result.HrOrderIdResult;
import com.shian.app.shian_cemetery.staticdata.SelectDictCode;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.view.dataview.cemetery.EditTextViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.SpinnerCemeteryLocation;
import com.shian.app.shian_cemetery.view.dataview.cemetery.SpinnerViewNormal;

import okhttp3.Request;

/**
 * Created by Administrator
 */

public class CemeteryPreInfo extends BaseCemeteryInfo {

    EditTextViewNormal mWriteOrderNumber;
    SpinnerViewNormal mWriteTombType;
    SpinnerViewNormal mWriteTombAttr;
    EditTextViewNormal mWritePlanPrice;
    EditTextViewNormal mWriteDealPrice;
    SpinnerViewNormal mWritePayInfo;
    EditTextViewNormal mWritePayMoney;
    EditTextViewNormal mWriteCemeteryReception;
    EditTextViewNormal mWriteFreeGift;
    EditTextViewNormal mWriteChoiceService;
    EditTextViewNormal mWriteRemark;
    SpinnerCemeteryLocation mWriteLocationDetails;

    TextView mTVSubmit;
    TextView mTVBack;

    public CemeteryPreInfo(Context context, long orderId, long bespeakId) {
        super(context, orderId, bespeakId, R.layout.layout_cemetery_info_pre);
    }

    @Override
    public void getData() {
        HpCemeteryIdParams params = new HpCemeteryIdParams();
        params.setBespeakId(beSpeakId);
        MHttpManagerFactory.getAccountManager().getCemeteryTalkSuccessContract
                (getContext(), params, new HttpResponseHandler<HrGetCemeteryTalkSuccessContract>() {
                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(HrGetCemeteryTalkSuccessContract result) {

                    }

                    @Override
                    public void onError(String message) {

                    }
                });
    }

    @Override
    public void saveData() {
        HpSaveCemeteryTalkSuccessContract params = new HpSaveCemeteryTalkSuccessContract();

        long cemeteryNameId = mWriteLocationDetails.getData(CemeteryLocationEnum.CEMETERYNAME.getCode());
        long locationGardenId = mWriteLocationDetails.getData(CemeteryLocationEnum.LOCATIONGARDEN.getCode());
        long locationAreaId = mWriteLocationDetails.getData(CemeteryLocationEnum.LOCATIONAREA.getCode());
        long locationRowId = mWriteLocationDetails.getData(CemeteryLocationEnum.LOCATIONROW.getCode());
        long locationNumId = mWriteLocationDetails.getData(CemeteryLocationEnum.LOCATIONNUM.getCode());
        if (cemeteryNameId == -1) {
            ToastUtils.showShortToast(getContext(), "还没有选择公墓");
            return;
        }
        if (locationGardenId == -1) {
            ToastUtils.showShortToast(getContext(), "还没有选择苑");
            return;
        }
        if (locationAreaId == -1) {
            ToastUtils.showShortToast(getContext(), "还没有选择区");
            return;
        }
        if (locationRowId == -1) {
            ToastUtils.showShortToast(getContext(), "还没有选择排");
            return;
        }
        if (locationNumId == -1) {
            ToastUtils.showShortToast(getContext(), "还没有选择号");
            return;
        }

        params.setBespeakId(beSpeakId);
        params.setOrderedId(orderId);

        params.setCemeteryId(cemeteryNameId);
        params.setTombId(locationGardenId);
        params.setParkId(locationAreaId);
        params.setRowNumber(locationRowId);
        params.setTombPositionId(locationNumId);

        params.setOrderNum(mWriteOrderNumber.getData());
        params.setCemeteryType(mWriteTombType.getData());
        params.setCemeteryProperties(mWriteTombAttr.getData());
        params.setPlanSale(mWritePlanPrice.getData());
        params.setSaleMoney(mWriteDealPrice.getData());
//        params.setPayState(mWritePayInfo.getData());
        params.setMoneyPay(mWritePayMoney.getData());
        params.setCemeteryReceive(mWriteCemeteryReception.getData());
        params.setFreeService(mWriteFreeGift.getData());
        params.setChoiceService(mWriteChoiceService.getData());
        params.setRemark(mWriteRemark.getData());
        MHttpManagerFactory.getAccountManager().saveCemeteryTalkSuccessContract(getContext(), params, new HttpResponseHandler<HrOrderIdResult>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrOrderIdResult result) {
                if (callBack != null)
                    callBack.next(new CemeteryDeadManInfo(getContext(), beSpeakId, result.getOrderId()));
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void initView() {
        mWriteOrderNumber = (EditTextViewNormal) view.findViewById(R.id.write_ordernumber);
        mWriteTombType = (SpinnerViewNormal) view.findViewById(R.id.write_tombtype);
        mWriteTombAttr = (SpinnerViewNormal) view.findViewById(R.id.write_tombattr);
        mWritePlanPrice = (EditTextViewNormal) view.findViewById(R.id.write_planprice);
        mWriteDealPrice = (EditTextViewNormal) view.findViewById(R.id.write_dealprice);
        mWritePayInfo = (SpinnerViewNormal) view.findViewById(R.id.write_payinfo);
        mWritePayMoney = (EditTextViewNormal) view.findViewById(R.id.write_paymoney);
        mWriteCemeteryReception = (EditTextViewNormal) view.findViewById(R.id.write_cemeteryreception);
        mWriteFreeGift = (EditTextViewNormal) view.findViewById(R.id.write_freegift);
        mWriteChoiceService = (EditTextViewNormal) view.findViewById(R.id.write_choiceservice);
        mWriteRemark = (EditTextViewNormal) view.findViewById(R.id.write_remark);
        mWriteLocationDetails = (SpinnerCemeteryLocation) view.findViewById(R.id.write_locationdetails);

        mTVBack = (TextView) view.findViewById(R.id.tv_back);
        mTVSubmit = (TextView) view.findViewById(R.id.tv_submit);

        mTVSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        mTVBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null)
                    callBack.next(null);
            }
        });
    }

    @Override
    public void initData() {
        mWriteTombType.initSpinner(SelectDictCode.TOMB_TYPE);
        mWriteTombAttr.initSpinner(SelectDictCode.TOMB_ATTRIBUTE);
        mWritePayInfo.initSpinner(SelectDictCode.ORDER_PAY_PURPOSE);
    }


}
