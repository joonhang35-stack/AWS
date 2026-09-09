package com.bcs.zsg.crm.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CRMLangVO {
	private static final long serialVersionUID = 1L;
	@Expose
	@SerializedName("type")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
