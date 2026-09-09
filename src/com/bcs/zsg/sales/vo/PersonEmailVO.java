package com.bcs.zsg.sales.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class PersonEmailVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	
	private Long idPerson;
	private String email;
	private Boolean isPrimary;
	
	public Long getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	
}
