package com.shian.app.shian_cemetery.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;


public class TipsDialog extends Dialog {

    private DisplayMetrics outMetrics;
    private View view;
    TextView tv_top;
    TextView tv_title;
    TextView tv_btn1;
    TextView tv_btn2;
    String top = "确认提交？";
    String title;

    String btn1Text;

    String btn2Text;

    OnClickListener topClickListener;

    OnClickListener bottomClickListener;
    boolean isBtn1V = false;
    boolean isBtn2V = false;

    public TipsDialog(Context context) {
        super(context, R.style.tipsDialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_tips, null);
        setContentView(view);
        initView();

        if (isBtn1V)
            tv_btn1.setVisibility(View.VISIBLE);
        else
            tv_btn1.setVisibility(View.GONE);
        if (isBtn2V)
            tv_btn2.setVisibility(View.VISIBLE);
        else
            tv_btn2.setVisibility(View.GONE);
        tv_top.setText(top);
        tv_title.setText(title);
        tv_btn1.setText(btn1Text);
        tv_btn2.setText(btn2Text);
    }

    private void initView() {
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_btn1 = (TextView) view.findViewById(R.id.tv_btn1);
        tv_btn2 = (TextView) view.findViewById(R.id.tv_btn2);
        tv_top = (TextView) view.findViewById(R.id.tv_top);
        tv_btn1.setOnClickListener(onClickListener);
        tv_btn2.setOnClickListener(onClickListener);
    }

    /**
     * 设置标题栏
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 设置头
     *
     * @param top
     */
    public void setTop(String top) {
        this.top = top;
    }

    /**
     * 设置按钮1
     *
     * @param text
     * @param onClickListener
     */
    public void setTopButton(String text, OnClickListener onClickListener) {
        isBtn1V = true;
        btn1Text = text;
        topClickListener = onClickListener;
    }

    /**
     * 设置按钮2
     *
     * @param text
     * @param onClickListener
     */
    public void setBottomButton(String text, OnClickListener onClickListener) {
        isBtn2V = true;
        btn2Text = text;
        bottomClickListener = onClickListener;
    }


    void topClick() {
        cancel();
        if (topClickListener != null) {
            topClickListener.onClick(this, 0);
        }
    }

    void bottomClick() {
        cancel();
        if (bottomClickListener != null) {
            bottomClickListener.onClick(this, 0);
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == tv_btn1) {
                topClick();
            } else {
                bottomClick();
            }
        }
    };

    @Override
    public void show() {
        super.show();
//        outMetrics = new DisplayMetrics();
//        getWindow().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
//        LayoutParams params = getWindow().getAttributes();
//        params.width = (int) (outMetrics.widthPixels * 0.67);
//        params.height = (int) (outMetrics.heightPixels * 0.41);
////        params.height = LayoutParams.WRAP_CONTENT;
//        params.gravity = Gravity.CENTER;
//        getWindow().setAttributes(params);

    }

}
