package com.shian.app.shian_cemetery.view.dataview.cemetery;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;


/**
 * Created by Administrator
 */

public class EditTextViewNormal extends BaseWriteView {
    View view;
    TextView mTVTitleName;
    TextView mTVIsImportant;
    EditText mETInput;


    public EditTextViewNormal(Context context) {
        this(context, null);
    }

    public EditTextViewNormal(Context context, AttributeSet attrs) {
        super(context, attrs);

        view = View.inflate(context, R.layout.zhy_write_edittextview_normal, this);
        initView();
        initData();
    }

    private void initView() {
        mTVIsImportant = (TextView) view.findViewById(R.id.tv_important);
        mTVTitleName = (TextView) view.findViewById(R.id.tv_titlename);
        mETInput = (EditText) view.findViewById(R.id.et_input);
    }

    private void initData() {
        mTVTitleName.setText(titleName);
//        mETInput.setInputType(inputType);
        if (isImportant) {
            mTVIsImportant.setVisibility(VISIBLE);
        } else {
            mTVIsImportant.setVisibility(INVISIBLE);
        }
        if (isLonger) {
            mETInput.setLines(3);
        }
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
        initData();
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
        initData();
    }

    public int getInputType() {
        return inputType;
    }


    /**
     * 设置输入类型
     * @param inputType
     */
    public void setInputType(int inputType) {
        mETInput.setInputType(inputType);
    }

    public void setData(String name) {
        mETInput.setText(name);
        initData();
    }

    public String getData() {
        return mETInput.getText().toString();
    }

    /**
     * 是否禁止点击
     *
     * @param isD
     */
    public void setDisable(boolean isD) {
        mETInput.setEnabled(isD);
    }

    public void setTextColor(int color) {
        mETInput.setTextColor(color);
    }
}
