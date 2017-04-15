package com.shian.app.shian_cemetery.view.dataview.burial;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.view.dataview.burial.BaseDataLayout;
import com.shian.app.shian_cemetery.view.dataview.cemetery.SpinnerViewNormal;

/**
 * Created by Administrator on 2017/4/6.
 */

public class SpinnerLayout extends BaseDataLayout {
    View view;

    TextView mTVTitle;
    TextView mTVHint;
    Spinner mSpinner;
    private ArrayAdapter<CharSequence> province_adapter;
    private SpinnerCallBack spinnerCallBack;

    public SpinnerLayout(Context context) {
        this(context, null);
    }

    public SpinnerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_data_spinner, this);
        initView();
        initData();
    }

    public SpinnerCallBack getSpinnerCallBack() {
        return spinnerCallBack;
    }

    private void initView() {
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
        mTVHint = (TextView) view.findViewById(R.id.tv_hint);
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
    }

    private void initData() {
        mTVTitle.setText(titleName);
        if (isShowHint) {
            mTVHint.setVisibility(VISIBLE);
        } else {
            mTVHint.setVisibility(GONE);
        }
        mTVHint.setText(hintText);
    }


    /**
     * 初始化数据
     *
     * @param array
     */
    public void initSpinner(String[] array) {
        province_adapter = new ArrayAdapter<CharSequence>(getContext(), R.layout.textview_spinner, array);
        initString();

    }


    /**
     * 初始化spinner
     */
    private void initString() {
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(province_adapter);
        mSpinner.setSelection(0);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerCallBack != null) {
                    spinnerCallBack.itemSelected(position, province_adapter.getItem(position).toString(), SpinnerLayout.this);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    /**
     * 监听
     */
    public interface SpinnerCallBack {
        void itemSelected(int position, String name, SpinnerLayout viewNormal);

        void check(SpinnerViewNormal view);
    }
}
