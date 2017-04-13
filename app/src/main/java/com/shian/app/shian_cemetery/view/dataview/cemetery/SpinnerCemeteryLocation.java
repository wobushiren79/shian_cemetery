package com.shian.app.shian_cemetery.view.dataview.cemetery;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.CemeteryLocationEnum;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.bean.CemeteryStructureModel;
import com.shian.app.shian_cemetery.http.params.HpCemeteryStructureParams;
import com.shian.app.shian_cemetery.http.result.HrGetCemeteryStructure;
import com.shian.app.shian_cemetery.tools.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/4/13.
 */

public class SpinnerCemeteryLocation extends BaseWriteView {
    View view;
    SpinnerViewNormal mWriteCemeteryName;
    SpinnerViewNormal mWriteLocationGarden;
    SpinnerViewNormal mWriteLocationArea;
    SpinnerViewNormal mWriteLocationRow;
    SpinnerViewNormal mWriteLocationNum;

    HrGetCemeteryStructure resultCemeteryName;
    HrGetCemeteryStructure resultLocationGarden;
    HrGetCemeteryStructure resultLocationArea;
    HrGetCemeteryStructure resultLocationRow;
    HrGetCemeteryStructure resultLocationNum;

    public SpinnerCemeteryLocation(Context context) {
        this(context, null);
    }

    public SpinnerCemeteryLocation(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.zhy_write_spinnercemeterylocation, this);
        initView();
        initData();
    }

    private void initView() {
        mWriteCemeteryName = (SpinnerViewNormal) view.findViewById(R.id.write_cemetery_name);
        mWriteLocationGarden = (SpinnerViewNormal) view.findViewById(R.id.write_location_garden);
        mWriteLocationArea = (SpinnerViewNormal) view.findViewById(R.id.write_location_area);
        mWriteLocationRow = (SpinnerViewNormal) view.findViewById(R.id.write_location_row);
        mWriteLocationNum = (SpinnerViewNormal) view.findViewById(R.id.write_location_num);
    }

    private void initData() {
        mWriteCemeteryName.initSpinner();
        mWriteCemeteryName.setSpinnerCallBack(spinnerCallBack);
        mWriteLocationGarden.initSpinner();
        mWriteLocationGarden.setSpinnerCallBack(spinnerCallBack);
        mWriteLocationArea.initSpinner();
        mWriteLocationArea.setSpinnerCallBack(spinnerCallBack);
        mWriteLocationRow.initSpinner();
        mWriteLocationRow.setSpinnerCallBack(spinnerCallBack);
        mWriteLocationNum.initSpinner();
        mWriteLocationNum.setSpinnerCallBack(spinnerCallBack);
    }

    SpinnerViewNormal.SpinnerCallBack spinnerCallBack = new SpinnerViewNormal.SpinnerCallBack() {


        @Override
        public void itemSelected(int position, String name, SpinnerViewNormal viewNormal) {
            if (viewNormal == mWriteCemeteryName) {
                mWriteLocationGarden.clearData();
                mWriteLocationArea.clearData();
                mWriteLocationNum.clearData();
                mWriteLocationRow.clearData();
                resultLocationGarden = null;
                resultLocationArea = null;
                resultLocationRow = null;
                resultLocationNum = null;
            } else if (viewNormal == mWriteLocationGarden) {
                mWriteLocationArea.clearData();
                mWriteLocationNum.clearData();
                mWriteLocationRow.clearData();
                resultLocationArea = null;
                resultLocationRow = null;
                resultLocationNum = null;
            } else if (viewNormal == mWriteLocationArea) {
                mWriteLocationNum.clearData();
                mWriteLocationRow.clearData();
                resultLocationRow = null;
                resultLocationNum = null;
            } else if (viewNormal == mWriteLocationRow) {
                mWriteLocationNum.clearData();
                resultLocationNum = null;
            } else if (viewNormal == mWriteLocationNum) {

            }
        }

        @Override
        public void check(SpinnerViewNormal view) {
            long locationId = -1;
            long parkIdTemp = -1;
            if (view == mWriteCemeteryName) {
                getDataInfo(CemeteryLocationEnum.CEMETERYNAME.getCode(), locationId, parkIdTemp, mWriteCemeteryName);
            } else if (view == mWriteLocationGarden) {
                if (resultCemeteryName == null) {
                    ToastUtils.showShortToast(getContext(), "请先选择公墓");
                    return;
                }
                locationId = resultCemeteryName.getItems().get(mWriteCemeteryName.getSelectPosition()).getId();
                getDataInfo(CemeteryLocationEnum.LOCATIONGARDEN.getCode(), locationId, parkIdTemp, mWriteLocationGarden);
            } else if (view == mWriteLocationArea) {
                if (resultLocationGarden == null) {
                    ToastUtils.showShortToast(getContext(), "请先选择苑");
                    return;
                }
                locationId = resultLocationGarden.getItems().get(mWriteLocationGarden.getSelectPosition()).getId();
                getDataInfo(CemeteryLocationEnum.LOCATIONAREA.getCode(), locationId, parkIdTemp, mWriteLocationArea);
            } else if (view == mWriteLocationRow) {
                if (resultLocationArea == null) {
                    ToastUtils.showShortToast(getContext(), "请先选择区");
                    return;
                }
                locationId = resultLocationArea.getItems().get(mWriteLocationArea.getSelectPosition()).getId();
                getDataInfo(CemeteryLocationEnum.LOCATIONROW.getCode(), locationId, parkIdTemp, mWriteLocationRow);
            } else if (view == mWriteLocationNum) {
                if (resultLocationRow == null) {
                    ToastUtils.showShortToast(getContext(), "请先选择排");
                    return;
                }
                locationId = resultLocationRow.getItems().get(mWriteLocationRow.getSelectPosition()).getId();
                parkIdTemp = resultLocationArea.getItems().get(mWriteLocationArea.getSelectPosition()).getId();
                getDataInfo(CemeteryLocationEnum.LOCATIONNUM.getCode(), locationId, parkIdTemp, mWriteLocationNum);
            }
        }


    };

    /**
     * 获取网络数据
     *
     * @param itemType
     * @param locationId
     * @param parkIdTemp
     * @param spinnerViewNormal
     */
    private void getDataInfo(int itemType, long locationId, long parkIdTemp, final SpinnerViewNormal spinnerViewNormal) {
        HpCemeteryStructureParams params = new HpCemeteryStructureParams();
        params.setItemId(locationId);
        params.setItemType(itemType);
        //查号的时候传区ID
        if (itemType == CemeteryLocationEnum.LOCATIONNUM.getCode()) {
            params.setParkIdTemp(parkIdTemp);
        }
        MHttpManagerFactory.getAccountManager().getCemeteryStructure(getContext(), params, new HttpResponseHandler<HrGetCemeteryStructure>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetCemeteryStructure result) {
                if (result == null || result.getItems().size() == 0) {
                    ToastUtils.showShortToast(getContext(), "没有找到数据");
                    return;
                }

                if (spinnerViewNormal == mWriteCemeteryName) {
                    resultCemeteryName = result;
                } else if (spinnerViewNormal == mWriteLocationGarden) {
                    resultLocationGarden = result;
                } else if (spinnerViewNormal == mWriteLocationArea) {
                    resultLocationArea = result;
                } else if (spinnerViewNormal == mWriteLocationRow) {
                    resultLocationRow = result;
                } else if (spinnerViewNormal == mWriteLocationNum) {
                    resultLocationNum = result;
                }

                String[] data = new String[result.getItems().size()];
                for (int i = 0; i < result.getItems().size(); i++) {
                    data[i] = result.getItems().get(i).getName();
                }
                spinnerViewNormal.initSpinner(data);
                spinnerViewNormal.setMaskVis(View.GONE);
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    /**
     * 获取地址数据
     */
    public long getData(int type) {
        long dataID = -1;
        try {
            if (type == CemeteryLocationEnum.CEMETERYNAME.getCode()) {
                dataID = resultCemeteryName.getItems().get(mWriteCemeteryName.getSelectPosition()).getId();
            } else if (type == CemeteryLocationEnum.LOCATIONGARDEN.getCode()) {
                dataID = resultLocationGarden.getItems().get(mWriteLocationGarden.getSelectPosition()).getId();
            } else if (type == CemeteryLocationEnum.LOCATIONAREA.getCode()) {
                dataID = resultLocationArea.getItems().get(mWriteLocationArea.getSelectPosition()).getId();
            } else if (type == CemeteryLocationEnum.LOCATIONROW.getCode()) {
                dataID = resultLocationRow.getItems().get(mWriteLocationRow.getSelectPosition()).getId();
            } else if (type == CemeteryLocationEnum.LOCATIONNUM.getCode()) {
                dataID = resultLocationNum.getItems().get(mWriteLocationNum.getSelectPosition()).getId();
            }
        } catch (Exception e) {
            dataID = -1;
        }
        return dataID;
    }
}
