package com.bcs.zsg.purchase.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class CorPersonInChargeVO  extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long corporateId;
	private String salutation;
	private String title;
	private String lastName;
	private String middleName;
	private String givenName;
	private String nickName;
	private String sex;

	private String officeNo;
	private String OfficeExt;
	private String mobileNo;
	private String email;
	
	public Long getCorporateId() {
		return corporateId;
	}
	
	public void setCorporateId(Long corporateId) {
		this.corporateId = corporateId;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOfficeExt() {
		return OfficeExt;
	}

	public void setOfficeExt(String officeExt) {
		OfficeExt = officeExt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
