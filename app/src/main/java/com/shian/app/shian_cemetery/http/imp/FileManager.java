package com.shian.app.shian_cemetery.http.imp;

import android.content.Context;

import com.shian.app.shian_cemetery.http.base.FileHttpResponseHandler;
import com.shian.app.shian_cemetery.http.base.HttpManager;
import com.shian.app.shian_cemetery.http.result.HrUploadFile;


public interface FileManager extends HttpManager {
	public void upLoadFile(Context context, String file, String path, FileHttpResponseHandler<HrUploadFile> handler);
}
