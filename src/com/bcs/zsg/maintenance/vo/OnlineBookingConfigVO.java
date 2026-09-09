package com.bcs.zsg.maintenance.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class OnlineBookingConfigVO extends BaseVO {
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String description;
	private String value;
	private Long idCompany;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getIdCompany() {
		return idCompany;
	}
	
	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}
}
