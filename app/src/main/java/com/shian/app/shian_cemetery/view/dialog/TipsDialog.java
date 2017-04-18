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

    TextView tv_title;
    TextView tv_btn1;
    TextView tv_btn2;

    String title;

    String btn1Text;

    String btn2Text;

    OnClickListener topClickListener;

    OnClickListener bottomClickListener;

    public TipsDialog(Context context) {
        super(context, R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_tips, null);
        setContentView(view);
        initView();
        tv_title.setText(title);
        tv_btn1.setText(btn1Text);
        tv_btn2.setText(btn2Text);
    }

    private void initView() {
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_btn1 = (TextView) view.findViewById(R.id.tv_btn1);
        tv_btn2 = (TextView) view.findViewById(R.id.tv_btn2);

        tv_btn1.setOnClickListener(onClickListener);
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
     * 设置按钮1
     *
     * @param text
     * @param onClickListener
     */
    public void setTopButton(String text, OnClickListener onClickListener) {
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
