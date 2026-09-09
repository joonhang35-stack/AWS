package com.bcs.zsg.crm.vo;

import com.google.gson.annotations.Expose;

public class CRMRequestVO {
	
	@Expose
	private int totalCount;
	@Expose
	private Object data;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
