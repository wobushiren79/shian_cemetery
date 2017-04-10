package com.shian.app.shian_cemetery.http.params;


import com.shian.app.shian_cemetery.http.base.BaseHttpParams;

public class HpGetOrderListParams extends BaseHttpParams {
	
	int pageNum;
	int pageSize;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
