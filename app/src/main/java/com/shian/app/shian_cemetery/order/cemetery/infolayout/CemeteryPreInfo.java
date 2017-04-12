package com.shian.app.shian_cemetery.order.cemetery.infolayout;

import android.content.Context;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.staticdata.SelectDictCode;
import com.shian.app.shian_cemetery.view.dataview.cemetery.EditTextViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.SpinnerViewNormal;

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


    public CemeteryPreInfo(Context context, long orderId, long bespeakId) {
        super(context, orderId, bespeakId, R.layout.layout_cemetery_info_pre);
    }

    @Override
    public void getData() {

    }

    @Override
    public void saveData() {

    }

    @Override
    public void initView() {
        mWriteOrderNumber = (EditTextViewNormal) view.findViewById(R.id.write_ordernumber);
        mWriteTombType = (SpinnerViewNormal) view.findViewById(R.id.write_tombtype);
        mWriteTombAttr = (SpinnerViewNormal) view.findViewById(R.id.write_tombattr);
        mWritePlanPrice = (EditTextViewNormal) view.findViewById(R.id.write_planprice);
        mWriteDealPrice = (EditTextViewNormal) view.findViewById(R.id.write_dealprice);
        mWritePayInfo = (SpinnerViewNormal) view.findViewById(R.id.write_payinfo);
    }

    @Override
    public void initData() {
        mWriteTombType.initSpinner(SelectDictCode.TOMB_TYPE);
        mWriteTombAttr.initSpinner(SelectDictCode.TOMB_ATTRIBUTE);
        mWritePayInfo.initSpinner(SelectDictCode.ORDER_PAY_PURPOSE);
    }
}
