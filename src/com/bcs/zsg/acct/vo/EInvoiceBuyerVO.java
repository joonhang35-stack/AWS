package com.bcs.zsg.acct.vo;

import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.maintenance.vo.MalaysiaStateVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public class EInvoiceBuyerVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	
	// system properties
	private Long idEInvDoc;
	private String name;
	private String tin;
	private String identityType;
	private String identityValue;
	private String sstRegNo;
	private String addr1;
	private String addr2;
	private String addr3;
	private String postcode;
	private String city;
	private String state;
	private String country;
	private String email;
	private String contactNo;
	
	// other properties
	private CountryVO countryVO;
	private MalaysiaStateVO malaysiaStateVO;
	private String fullAddress;
	
	public MalaysiaStateVO getMalaysiaStateVO() {
		return malaysiaStateVO;
	}
	public void setMalaysiaStateVO(MalaysiaStateVO malaysiaStateVO) {
		this.malaysiaStateVO = malaysiaStateVO;
	}
	public CountryVO getCountryVO() {
		return countryVO;
	}
	public void setCountryVO(CountryVO countryVO) {
		this.countryVO = countryVO;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public Long getIdEInvDoc() {
		return idEInvDoc;
	}
	public void setIdEInvDoc(Long idEInvDoc) {
		this.idEInvDoc = idEInvDoc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public String getIdentityValue() {
		return identityValue;
	}
	public void setIdentityValue(String identityValue) {
		this.identityValue = identityValue;
	}
	public String getSstRegNo() {
		return sstRegNo;
	}
	public void setSstRegNo(String sstRegNo) {
		this.sstRegNo = sstRegNo;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getAddr3() {
		return addr3;
	}
	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

}
