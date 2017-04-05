package com.shian.app.shian_cemetery.view.headlayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BurialTabEnum;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.staticdata.ReceiverAction;
import com.shian.app.shian_cemetery.tools.LogUtils;
import com.shian.app.shian_cemetery.view.customlayout.tabchange.TitleTabChange;

/**
 * Created by Administrator on 2017/4/3.
 */

public class TabTitle extends LinearLayout {
    View view;
    TitleTabChange titleTabChange;
    BurialTabEnum[] tabEna = {
            BurialTabEnum.WaitSettele,
            BurialTabEnum.WaitBuried,
            BurialTabEnum.BuriedOver
    };


    public TabTitle(Context context) {
        this(context, null);
    }

    public TabTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_title_tab, this);
        initView();
        initData();
    }


    private void initData() {
        for (int i = 0; i < tabEna.length; i++) {
            BurialTabEnum data = tabEna[i];
            titleTabChange.addTab(data.getTitle(),
                    data.getCode(),
                    getResources().getDimensionPixelSize(R.dimen.dimen_40dp),
                    Color.WHITE,
                    Color.WHITE,
                    Color.WHITE);
        }
        titleTabChange.setCode(BurialTabEnum.WaitBuried.getCode());
    }

    private void initView() {
        titleTabChange = (TitleTabChange) view.findViewById(R.id.titletab);

        titleTabChange.setCallBack(tabCallBack);
    }

    TitleTabChange.TabCallBack tabCallBack = new TitleTabChange.TabCallBack() {
        @Override
        public void TabChange(int code, String title) {
            Intent intent = new Intent();
            intent.setAction(ReceiverAction.TITLE_TAB_CHANGE_RECEIVER);
            intent.putExtra(IntentName.INTENT_TITLE_TAB_CHANGE_RECEIVER_CODE, code);
            intent.putExtra(IntentName.INTENT_TITLE_TAB_CHANGE_RECEIVER_NAME, title);
            getContext().sendBroadcast(intent);
        }
    };

}
