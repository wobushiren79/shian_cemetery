package com.shian.app.shian_cemetery.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zm.
 */

public class PicUtils {

    public static final int picMax = 20000000;//图片最大大小
    public static final float scaledPic = 2;//图片缩小大小

    public static File scaledFile(File file) {
        Bitmap bitmap = null;
        boolean isDecode = false;
        int scaled = 1;
        try {
            while (!isDecode) {
                try {
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), getBitmapOption(scaled));
                    isDecode = true;
                } catch (Exception e) {
                    scaled++;
                }
            }
        } catch (Exception e) {
            return file;
        }

        //檢測是否超過大小
        while (isOverMaxImage(bitmap)) {
            bitmap = scaledBitmap(bitmap, scaledPic);
        }
        saveBitmapFile(bitmap, file);
        return file;
    }

    /**
     * 缩放图片
     *
     * @param bitmap
     * @param scaled
     * @return
     */
    public static Bitmap scaledBitmap(Bitmap bitmap, float scaled) {
        int newWidth = (int) (bitmap.getWidth() / scaled);
        int newHeight = (int) (bitmap.getHeight() / scaled);
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
        return newBitmap;
    }

    /**
     * 查看图片是否超过大小
     */
    public static boolean isOverMaxImage(Bitmap image) {
        if (image.getByteCount() > picMax) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 把batmap 转file
     *
     * @param bitmap
     */
    public static File saveBitmapFile(Bitmap bitmap, File file) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    private static BitmapFactory.Options getBitmapOption(int inSampleSize) {
        System.gc();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inSampleSize = inSampleSize;
        return options;
    }
}
