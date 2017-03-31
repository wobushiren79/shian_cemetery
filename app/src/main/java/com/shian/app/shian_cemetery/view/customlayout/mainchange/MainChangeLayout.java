package com.shian.app.shian_cemetery.view.customlayout.mainchange;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shian.app.shian_cemetery.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MainChangeLayout extends LinearLayout {
    View view;

    LinearLayout mLLMain;

    List<MainChangeItem> listItem = new ArrayList<>();
    MainChangeCallBack changeCallBack;

    public MainChangeLayout(Context context) {
        this(context, null);
    }

    public MainChangeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(getContext(), R.layout.layout_main_change, this);

        initView();
    }

    private void initView() {
        mLLMain = (LinearLayout) view.findViewById(R.id.ll_main_change);
    }

    public void setChangeCallBack(MainChangeCallBack changeCallBack) {
        this.changeCallBack = changeCallBack;
    }

    /**
     * 添加数据
     *
     * @param title
     * @param unCheckiconId
     * @param checkiconId
     */
    public void addMainData(String title, int unCheckiconId, int checkiconId, int itemId) {
        LayoutParams itemLayoutParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemLayoutParams.weight = 1;

        final MainChangeItem mainChangeItem = new MainChangeItem(getContext());
        mainChangeItem.setLayoutParams(itemLayoutParams);
        mainChangeItem.setData(title, unCheckiconId, checkiconId, itemId);
        mainChangeItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listItem.size(); i++) {
                    MainChangeItem item = listItem.get(i);
                    item.setState(false);
                    if (mainChangeItem == item) {
                        mainChangeItem.setState(true);
                        if (changeCallBack != null)
                            changeCallBack.onClick(mainChangeItem.getItemId());
                    }
                }
            }
        });


        mLLMain.addView(mainChangeItem);
        listItem.add(mainChangeItem);
    }

    /**
     * 设置具体一项状态
     *
     * @param itemId
     */
    public void setState(int itemId, boolean state) {
        for (int i = 0; i < listItem.size(); i++) {
            if (listItem.get(i).getItemId() == itemId) {
                listItem.get(i).setState(state);
                if (changeCallBack != null)
                    changeCallBack.onClick(listItem.get(i).getItemId());
            }
        }
    }

    /**
     * 事件监听
     */
    public interface MainChangeCallBack {
        void onClick(int itemId);
    }

}
