package com.shian.app.shian_cemetery.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.common.view.PinchImageView;
import com.shian.app.shian_cemetery.http.base.BaseHttpParams;
import com.shian.app.shian_cemetery.tools.FilePathUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImagePreviewActivity extends BaseActivity implements View.OnLongClickListener {

    private String url;

    PinchImageView mImageView;

    Bitmap mBitmap;

    Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    if (mBitmap.getWidth() < metrics.widthPixels) {
                        mImageView.setImageBitmap(mBitmap);
                    } else {
                        Bitmap bmp = Bitmap.createScaledBitmap(mBitmap, metrics.widthPixels / 2, (mBitmap.getHeight() * metrics.widthPixels / mBitmap.getWidth()) / 2, true);
                        mImageView.setImageBitmap(bmp);
                    }
//                    mImageView.setImageBitmap(bitmap);

                    mImageView.setVisibility(View.VISIBLE);
                    break;
                case 1:

                    break;
            }
        }

        ;
    };

    HandlerThread mHandlerThread;

    Handler mThreadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageView = new PinchImageView(this);

        mImageView.setBackgroundResource(R.color.viewfinder_mask);
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        mImageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mFLContent.setBackgroundColor(getResources().getColor(R.color.main_blackground));
        setContentView(mImageView);
        setTitle("查看图片");
        url = getIntent().getStringExtra("url");
        mHandlerThread = new HandlerThread(getClass().getSimpleName());
        mHandlerThread.start();
        mThreadHandler = new Handler(mHandlerThread.getLooper()) {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case 0:
                        getBitmap();
                        break;
                    case 1:
                        saveBitmap();
                        break;
                }
            }
        };
        mThreadHandler.sendEmptyMessage(0);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(0);
        mThreadHandler.removeMessages(0);
        mThreadHandler.removeMessages(1);
        mHandlerThread.quit();
        if (mBitmap != null) {
//			mBitmap.recycle();
            mBitmap = null;
        }
        super.onDestroy();
    }

    protected void saveBitmap() {
        String fileName = url.substring(url.lastIndexOf("/"), url.length());
        File file = FilePathUtils.getDownloadFile("Pic", fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            ToastUtils.showShortToast(this, "保存成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ToastUtils.showShortToast(this, "无法保存图片！");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void getBitmap() {
        try {
            mBitmap = Picasso.with(this).load(url).get();
            mHandler.sendEmptyMessage(0);
            mImageView.setOnLongClickListener(this);
        } catch (IOException e) {
            ToastUtils.showShortToast(this, "获取图片失败！");
            e.printStackTrace();
        }

    }

    @Override
    public boolean onLongClick(View v) {
        AlertDialog.Builder builder = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            builder = new AlertDialog.Builder(this);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Holo_Dialog);
        } else {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog);
        }
        builder.setMessage("是否保存图片?");
        builder.setNegativeButton("保存", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                mThreadHandler.sendEmptyMessage(1);
            }
        });
        builder.setPositiveButton("取消", null);
        builder.create().show();
        return true;
    }

}
