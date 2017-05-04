package com.shian.app.shian_cemetery.view.dataview.burial;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.ImagePreviewActivity;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.FileHttpResponseHandler;
import com.shian.app.shian_cemetery.http.result.HrUploadFile;
import com.shian.app.shian_cemetery.staticdata.BaseURL;
import com.shian.app.shian_cemetery.staticdata.IntentName;


import java.util.ArrayList;


/**
 * Created by Administrator on 2017/4/7.
 */
public class PhotoUpDataLayout extends BaseDataLayout {
    View view;

    TextView mTVUpData;
    ImageView mIVPic;
    ProgressBar mProgressBar;

    CallBack callBack;

    public boolean isFirstLoad = true;
    public boolean isLoading = false;
    public String fileUrl;
    private String fileName = "photoName";

    public PhotoUpDataLayout(Context context) {
        this(context, null);
    }

    public PhotoUpDataLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_data_photo_updata, this);

        initView();
        initData();
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private void initView() {
        mTVUpData = (TextView) view.findViewById(R.id.tv_updata);
        mIVPic = (ImageView) view.findViewById(R.id.iv_pic);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar);

        mTVUpData.setOnClickListener(onClickListener);
        mIVPic.setOnClickListener(onClickListener);
        mIVPic.setOnLongClickListener(onLongClickListener);
    }

    private void initData() {

    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mTVUpData == v) {
                upPic();
            } else if (mIVPic == v) {
                showPic();
            }
        }
    };


    OnLongClickListener onLongClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (mIVPic == v) {
                changeAndDelPic();
            }
            return true;
        }
    };

    /**
     * 展示图片
     */
    private void showPic() {
        if (fileUrl != null) {
            Intent in = new Intent(getContext(), ImagePreviewActivity.class);
            in.putExtra(IntentName.INTENT_URL, BaseURL.OSSURL + fileUrl);
            getContext().startActivity(in);
        }
    }

    /**
     * 修改或删除图片
     */
    private void changeAndDelPic() {
        if (fileUrl != null) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setItems(new String[]{"修改", "删除"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    if (which == 0) {
                        upPic();
                    } else {
                        if (callBack != null)
                            callBack.remove();
                    }
                }
            });
            dialog.show();
        }
    }

    /**
     * 上传图片
     */
    private void upPic() {
        ((BaseActivity) getContext()).showPhotoPicker();
        ((BaseActivity) getContext()).setOnPhotoPickerListener(new BaseActivity.OnPhotoPickerListener() {
            @Override
            public void onPhoto(ArrayList<String> paths) {
                fileUrl = null;
                uploadFile(mIVPic, fileName, paths.get(0));
                ImageLoader.getInstance().displayImage(
                        "file://" + paths.get(0), mIVPic);
                mProgressBar.setVisibility(VISIBLE);
                mTVUpData.setVisibility(GONE);
            }
        });
    }


    private void uploadFile(final ImageView ib, final String fileName, String path) {
        isLoading = true;
        MHttpManagerFactory.getFileManager().upLoadFile(getContext(), fileName, path,
                new FileHttpResponseHandler<HrUploadFile>() {

                    @Override
                    public void onSuccess(HrUploadFile t) {
                        mProgressBar.setVisibility(GONE);
                        fileUrl = (String) t.getNameMap().get(fileName);

                        if (callBack != null && isFirstLoad)
                            callBack.add();
                        isFirstLoad = false;
                        isLoading = false;
                    }

                    @Override
                    public void onStart() {

                    }


                    @Override
                    public void onError(String message) {
                        isLoading = false;
                    }

                    @Override
                    public void onProgress(long total, float progress) {

                    }

                });
    }

    public interface CallBack {
        void remove();

        void add();
    }
}
