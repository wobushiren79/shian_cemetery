package com.shian.app.shian_cemetery.http.imp;

import android.content.Context;

import com.shian.app.shian_cemetery.http.base.FileHttpResponseHandler;
import com.shian.app.shian_cemetery.http.base.HttpManager;
import com.shian.app.shian_cemetery.http.result.HrUploadFile;

import java.io.File;


public interface FileManager extends HttpManager {
	public void upLoadFile(Context context, String file, String path, FileHttpResponseHandler<HrUploadFile> handler);

	public void downloadFile(Context context,String downloadUrl, final FileHttpResponseHandler<File> responseHandler);
}
