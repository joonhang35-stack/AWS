package com.bcs.zsg.apiresponse.vo;

public enum ApiStatus {
	SUCCESS("SUCCESS"),
	ERROR("ERROR");
	
	private String value;

	public String getValue() {
		return value;
	}

	private ApiStatus(String value) {
		this.value = value;
	}
}