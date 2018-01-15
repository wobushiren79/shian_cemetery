package com.shian.app.shian_cemetery.http.imp;

import android.content.Context;

import com.shian.app.shian_cemetery.http.base.FileHttpResponseHandler;
import com.shian.app.shian_cemetery.http.base.HttpManager;
import com.shian.app.shian_cemetery.http.result.HrUploadFile;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;


public interface FileManager extends HttpManager {
	public void upLoadFile(Context context, String file, String path, FileHttpResponseHandler<HrUploadFile> handler);

	/**
	 * 文件下载
	 * @param context
	 * @param downloadUrl
	 * @param responseHandler
	 */
	public RequestCall downloadFile(Context context, String downloadUrl, String fileName, final FileHttpResponseHandler<File> responseHandler);
}
