package com.bcs.zsg.purchase.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class ContactVO  extends BaseVO{
	private static final long serialVersionUID = 1L;
	
	private String contactType;
	private String contactNo;
	private Long personId;
	
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	
}
