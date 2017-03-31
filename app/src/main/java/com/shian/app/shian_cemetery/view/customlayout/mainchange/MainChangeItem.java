package com.shian.app.shian_cemetery.view.customlayout.mainchange;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MainChangeItem extends LinearLayout {
    View view;

    ImageView mIVIcon;
    TextView mTVTitle;

    private int unCheckIconId = -1;
    private int checkIconId = -1;

    private int itemId = -1;
    private boolean state = false;

    public MainChangeItem(Context context) {
        this(context, null);
    }

    public MainChangeItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(getContext(), R.layout.layout_main_change_item, this);

        initView();
    }

    private void initView() {
        mIVIcon = (ImageView) view.findViewById(R.id.iv_icon);
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
    }

    /**
     * 设置图标与标题
     *
     * @param title
     * @param unCheckIconId
     * @param checkIconId
     */
    public void setData(String title, int unCheckIconId, int checkIconId, int itemId) {
        mTVTitle.setText(title);
        mIVIcon.setImageResource(unCheckIconId);
        this.checkIconId = checkIconId;
        this.unCheckIconId = unCheckIconId;
        this.itemId = itemId;
    }

    /**
     * 设置状态
     *
     * @param state false未点击  true点击
     */
    public void setState(boolean state) {
        if (!state) {
            mIVIcon.setImageResource(unCheckIconId);
            mTVTitle.setTextColor(getContext().getResources().getColor(R.color.zhy_text_color_3));
            this.state = state;
        } else {
            mIVIcon.setImageResource(checkIconId);
            mTVTitle.setTextColor(getContext().getResources().getColor(R.color.zhy_text_color_1));
            this.state = state;
        }
    }

    /**
     * 获取状态
     */
    public boolean getState() {
        return state;
    }

    /**
     * 获取子项ID
     * @return
     */
    public int getItemId() {
        return itemId;
    }

}

