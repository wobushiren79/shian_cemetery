package com.shian.app.shian_cemetery.view.dataview;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.FileHttpResponseHandler;
import com.shian.app.shian_cemetery.http.result.HrUploadFile;
import com.shian.app.shian_cemetery.tools.LogUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.tools.Utils;
import com.yongchun.library.view.ImagePreviewActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/7.
 */

public class PhotoUpDataLayout extends BaseDataLayout {
    View view;

    TextView mTVUpData;
    ImageView mIVPic;

    public PhotoUpDataLayout(Context context) {
        super(context);
    }

    public PhotoUpDataLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_data_photo_updata, this);

        initView();
        initData();
    }


    private void initView() {
        mTVUpData = (TextView) view.findViewById(R.id.tv_updata);
        mIVPic = (ImageView) view.findViewById(R.id.iv_pic);

        mTVUpData.setOnClickListener(onClickListener);
    }

    private void initData() {

    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mTVUpData == v) {
                upPic();
            }
        }
    };

    /**
     * 上传图片
     */
    private void upPic() {
        ((BaseActivity) getContext()).showPhotoPicker();
        ((BaseActivity) getContext()).setOnPhotoPickerListener(new BaseActivity.OnPhotoPickerListener() {
            @Override
            public void onPhoto(ArrayList<String> paths) {
                uploadFile(mIVPic, paths.get(0));
                ImageLoader.getInstance().displayImage(
                        "file://" + paths.get(0), mIVPic);
                mTVUpData.setVisibility(GONE);
            }
        });
    }

    /**
     * 查看图片
     *
     * @param url
     */
    private void showPic(String url) {
        Intent in = new Intent(getContext(), ImagePreviewActivity.class);
        in.putExtra("url", url);
        getContext().startActivity(in);
    }

    private void uploadFile(final ImageView ib, String path) {
//        final ProgressBar pbVIew = (ProgressBar) ((ViewGroup) ib.getParent())
//                .getChildAt(1);
        MHttpManagerFactory.getFileManager().upLoadFile(getContext(), "", path,
                new FileHttpResponseHandler<HrUploadFile>() {

                    @Override
                    public void onSuccess(HrUploadFile t) {
//                        ToastUtils.show(PgzxActivity.this, "上传成功");
//                        AddAddition add = new HpSaveCustomerContract.AddAddition();
//                        add.setFileName(file);
//                        add.setFileUrl(t.getNameMap().get(file).toString());
//                        addList.add(add);
//                        pbVIew.setVisibility(View.GONE);
                    }

                    @Override
                    public void onStart() {

                    }


                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onProgress(long total, float progress) {
                        LogUtils.logV("progress"+progress+" total"+total);
//                        pbVIew.setVisibility(View.VISIBLE);
//                        pbVIew.setProgress((int) (progress / total * 100));
                    }

                });
    }
}
