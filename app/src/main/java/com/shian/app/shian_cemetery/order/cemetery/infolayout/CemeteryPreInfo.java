package com.shian.app.shian_cemetery.order.cemetery.infolayout;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.cemetery.TalkSuccessActivity;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
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
    EditTextViewNormal mWriteOrderNum;
    SpinnerViewNormal mWriteTombType;
    SpinnerViewNormal mWriteTombAttr;
    EditTextViewNormal mWritePlanPrice;
    EditTextViewNormal mWriteDealPrice;
    SpinnerViewNormal mWritePayInfo;
    EditTextViewNormal mWritePayMoney;
    EditTextViewNormal mWriteCemeteryReception;
    EditTextViewNormal mWriteCemeterySales;
    EditTextViewNormal mWriteFreeGift;
    EditTextViewNormal mWriteChoiceService;
    EditTextViewNormal mWriteRemark;
    SpinnerCemeteryLocation mWriteLocationDetails;

    TextView mTVSubmit;
    TextView mTVBack;

    LinearLayout mLLButton;


    public CemeteryPreInfo(Context context, long orderId, long bespeakId) {
        super(context, orderId, bespeakId, R.layout.layout_cemetery_info_pre, false);
    }

    public CemeteryPreInfo(Context context, long orderId, long bespeakId, boolean isShowMode) {
        super(context, orderId, bespeakId, R.layout.layout_cemetery_info_pre, isShowMode);
        setShowMode();
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
                        if (isShowMode) {
                            mWriteLocationDetails.setData(result, false);
                        } else {
                            mWriteLocationDetails.setData(result);
                        }
                        if (result.getOrderNum() != null)
                            mWriteOrderNum.setData(result.getOrderNum());
                        if (result.getOrderNumber() != null)
                            mWriteOrderNumber.setData(result.getOrderNumber());
                        if (result.getCemeteryType() != null)
                            mWriteTombType.setDataDict(result.getCemeteryType());
                        if (result.getTombUseProperty() != null)
                            mWriteTombAttr.setDataDict(result.getTombUseProperty());
                        if (result.getPlanSale() != null)
                            mWritePlanPrice.setData(result.getPlanSale());
                        if (result.getSaleMoney() != null)
                            mWriteDealPrice.setData(result.getSaleMoney());
                        if (result.getPayState() != null)
                            mWritePayInfo.setDataDict(result.getPayState());
                        if (result.getMoneyDeposit() != null)
                            mWritePayMoney.setData(result.getMoneyDeposit());
                        if (result.getCemeteryReceive() != null)
                            mWriteCemeteryReception.setData(result.getCemeteryReceive());
                        if (result.getFreeService() != null)
                            mWriteFreeGift.setData(result.getFreeService());
                        if (result.getChoiceService() != null)
                            mWriteChoiceService.setData(result.getChoiceService());
                        if (result.getRemark() != null)
                            mWriteRemark.setData(result.getRemark());
                        if (result.getCemeterySales() != null)
                            mWriteCemeterySales.setData(result.getCemeterySales());
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
        String locationRowName = mWriteLocationDetails.getRowName();
        if (cemeteryNameId == 0) {
            ToastUtils.showShortToast(getContext(), "还没有选择公墓");
            return;
        }
        if (locationGardenId == 0) {
            ToastUtils.showShortToast(getContext(), "还没有选择苑");
            return;
        }
        if (locationAreaId == 0) {
            ToastUtils.showShortToast(getContext(), "还没有选择区");
            return;
        }
        if (locationRowName == null) {
            ToastUtils.showShortToast(getContext(), "还没有选择排");
            return;
        }
        if (locationNumId == 0) {
            ToastUtils.showShortToast(getContext(), "还没有选择号");
            return;
        }
        if (mWritePayMoney.getData().equals("")) {
            ToastUtils.showShortToast(getContext(), "还没有输入商议定金");
            return;
        }


        params.setBespeakId(beSpeakId);
        params.setOrderId(orderId);

        params.setCemeteryId(cemeteryNameId);
        params.setTombId(locationGardenId);
        params.setParkId(locationAreaId);
        params.setRowNumber(locationRowName);
        params.setTombPositionId(locationNumId);

        params.setOrderNum(mWriteOrderNum.getData());
        params.setCemeteryType(mWriteTombType.getData());
        params.setTombUseProperty(mWriteTombAttr.getData());
        params.setPlanSale(mWritePlanPrice.getData());
        params.setSaleMoney(mWriteDealPrice.getData());
//        params.setPayState(mWritePayInfo.getData());
        params.setMoneyDeposit(mWritePayMoney.getData());
        params.setCemeteryReceive(mWriteCemeteryReception.getData());
        params.setFreeService(mWriteFreeGift.getData());
        params.setChoiceService(mWriteChoiceService.getData());
        params.setRemark(mWriteRemark.getData());
        params.setCemeterySales(mWriteCemeterySales.getData());
        MHttpManagerFactory.getAccountManager().saveCemeteryTalkSuccessContract(getContext(), params, new HttpResponseHandler<HrOrderIdResult>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrOrderIdResult result) {
                if (callBack != null)
                    callBack.next(new CemeteryDeadManInfo(getContext(), result.getOrderId(), beSpeakId));
                ToastUtils.showShortToast(getContext(), "提交成功");
            }

            @Override
            public void onError(String message) {
                ToastUtils.showShortToast(getContext(), "提交失败");
            }
        });
    }

    @Override
    public void initView() {
        mWriteOrderNumber = (EditTextViewNormal) view.findViewById(R.id.write_ordernumber);
        mWriteOrderNum = (EditTextViewNormal) view.findViewById(R.id.write_ordernum);
        mWriteTombType = (SpinnerViewNormal) view.findViewById(R.id.write_tombtype);
        mWriteTombAttr = (SpinnerViewNormal) view.findViewById(R.id.write_tombattr);
        mWritePlanPrice = (EditTextViewNormal) view.findViewById(R.id.write_planprice);
        mWriteDealPrice = (EditTextViewNormal) view.findViewById(R.id.write_dealprice);
        mWritePayInfo = (SpinnerViewNormal) view.findViewById(R.id.write_payinfo);
        mWritePayMoney = (EditTextViewNormal) view.findViewById(R.id.write_paymoney);
        mWriteCemeteryReception = (EditTextViewNormal) view.findViewById(R.id.write_cemeteryreception);
        mWriteCemeterySales = (EditTextViewNormal) view.findViewById(R.id.write_cemeterysales);
        mWriteFreeGift = (EditTextViewNormal) view.findViewById(R.id.write_freegift);
        mWriteChoiceService = (EditTextViewNormal) view.findViewById(R.id.write_choiceservice);
        mWriteRemark = (EditTextViewNormal) view.findViewById(R.id.write_remark);
        mWriteLocationDetails = (SpinnerCemeteryLocation) view.findViewById(R.id.write_locationdetails);

        mWriteOrderNumber.setDisable(false);
        mWriteDealPrice.setInputType(InputType.TYPE_CLASS_NUMBER);
        mTVBack = (TextView) view.findViewById(R.id.tv_back);
        mTVSubmit = (TextView) view.findViewById(R.id.tv_submit);
        mLLButton = (LinearLayout) findViewById(R.id.ll_button);
        mWritePlanPrice.setDisable(false);
        mWriteCemeterySales.setDisable(false);
        mWritePayMoney.setInputType(InputType.TYPE_CLASS_NUMBER);
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
        mWriteLocationDetails.setCallBack(new SpinnerCemeteryLocation.CallBack() {
            @Override
            public void changePrice(String price) {
                mWritePlanPrice.setData(price);
                mWriteDealPrice.setData(price);
            }
        });
    }

    @Override
    public void initData() {
        mWriteTombType.initSpinner(SelectDictCode.TOMB_TYPE);
        mWriteTombAttr.initSpinner(SelectDictCode.TOMB_USE_PROPERTY);
        mWritePayInfo.initSpinner(SelectDictCode.ORDER_PAY_PURPOSE);
        mWriteCemeteryReception.setDisable(false);
    }

    public void setShowMode() {
        mWriteOrderNumber.setDisable(false);
        mWriteOrderNum.setDisable(false);
        mWriteTombType.setEnabled(false);
        mWriteTombAttr.setEnabled(false);
        mWritePlanPrice.setDisable(false);
        mWriteDealPrice.setDisable(false);
        mWritePayInfo.setEnabled(false);
        mWritePayMoney.setDisable(false);
        mWriteCemeteryReception.setDisable(false);
        mWriteCemeterySales.setDisable(false);
        mWriteFreeGift.setDisable(false);
        mWriteChoiceService.setDisable(false);
        mWriteRemark.setDisable(false);
        mWriteLocationDetails.setDisable(false);
        mLLButton.setVisibility(GONE);
    }

}
