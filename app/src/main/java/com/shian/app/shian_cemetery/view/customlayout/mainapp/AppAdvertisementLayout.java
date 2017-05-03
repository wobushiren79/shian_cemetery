package com.shian.app.shian_cemetery.view.customlayout.mainapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.WebActivity;
import com.shian.app.shian_cemetery.appenum.AdvertisementEnum;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.phpparams.HpAdvertisementGetParams;
import com.shian.app.shian_cemetery.http.phpresult.PHPHrGetAdvertisement;
import com.shian.app.shian_cemetery.staticdata.BaseURL;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.view.customlayout.mainadvertisement.MainAdvertisementLayout;

import okhttp3.Request;


/**
 * Created by Administrator on 2017/3/11.
 */

public class AppAdvertisementLayout extends LinearLayout {
    View view;
    ImageView mIVConent;
    Button mBTCancel;

    private MainAdvertisementLayout.CallBack callBack;


    private PHPHrGetAdvertisement result;

    public AppAdvertisementLayout(Context context) {
        this(context, null);
    }

    public AppAdvertisementLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_mainadvertisement_layout, this);
        initView();
        getData();
    }

    private void initView() {
        mBTCancel = (Button) view.findViewById(R.id.bt_cancel);
        mIVConent = (ImageView) view.findViewById(R.id.iv_content);

        mBTCancel.setVisibility(GONE);
    }

    /**
     * 获取数据
     */
    private void getData() {
        HpAdvertisementGetParams params = new HpAdvertisementGetParams();
        params.setType(AdvertisementEnum.APP.getCode());
        params.setNumber(1);
        params.setPagerNumber(0);
        MHttpManagerFactory.getPHPManager().getAdvertisement(getContext(), params, new HttpResponseHandler<PHPHrGetAdvertisement>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(PHPHrGetAdvertisement result) {
                AppAdvertisementLayout.this.result = result;
                mBTCancel.setOnClickListener(onClickListener);
                mIVConent.setOnClickListener(onClickListener);
                ImageLoader.getInstance().displayImage(BaseURL.PHPURL + result.getItems().get(0).getBanner(), mIVConent, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        callBack.loadingComplete();
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                });
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    public void setCallBack(MainAdvertisementLayout.CallBack callBack) {
        this.callBack = callBack;
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mBTCancel) {
                callBack.cancelPic();
            } else if (v == mIVConent) {
                if (result != null) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(IntentName.INTENT_URL, result.getItems().get(0).getUrl());
                    getContext().startActivity(intent);
                }
            }
        }
    };

    public interface CallBack {
        void loadingComplete();

        void cancelPic();
    }
}
