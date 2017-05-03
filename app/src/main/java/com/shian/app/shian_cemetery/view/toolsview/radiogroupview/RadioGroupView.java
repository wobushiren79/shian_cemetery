package com.shian.app.shian_cemetery.view.toolsview.radiogroupview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.OrderUserEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public class RadioGroupView extends LinearLayout {
    View view;
    LinearLayout mLLContent;
    List<RadioButtonBean> listData;
    List<RadioButtonView> listView = new ArrayList<>();
    RadioButtonBean checkData;

    public RadioGroupView(Context context, List<RadioButtonBean> listData) {
        super(context);
        view = View.inflate(context, R.layout.view_radio_group, this);
        this.listData = listData;
        initView();
        initData();
    }

    private void initView() {
        mLLContent = (LinearLayout) view.findViewById(R.id.ll_content);
    }

    private void initData() {
        for (RadioButtonBean data : listData) {
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.bottomMargin = getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_2dp);
            RadioButtonView radioView = new RadioButtonView(getContext(), data);
            radioView.setCallBack(callBack);
            radioView.setLayoutParams(layoutParams);
            mLLContent.addView(radioView);
            listView.add(radioView);
        }
        //初始化选第一个
        checkData = listData.get(0);
        listView.get(0).setCheck(true);
    }

    RadioButtonView.CallBack callBack = new RadioButtonView.CallBack() {
        @Override
        public void check(RadioButtonBean data) {
            int code = data.getCode();
            checkData(code);
        }
    };



    public void setData(int code) {
        checkData(code);
    }

    public RadioButtonBean getData() {
        return checkData;
    }


    /**
     * 检测数据
     * @param code
     */
    private void checkData(int code) {
        for (int i = 0; i < listView.size(); i++) {
            RadioButtonView radioView = listView.get(i);
            if (radioView.getData().getCode() == code) {
                radioView.setCheck(true);
                checkData = radioView.getData();
            } else {
                radioView.setCheck(false);
            }
        }
    }
}
