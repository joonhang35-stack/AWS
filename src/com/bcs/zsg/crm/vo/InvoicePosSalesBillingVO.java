package com.bcs.zsg.crm.vo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvoicePosSalesBillingVO {
	
	private static final long serialVersionUID = 1L;
	
	@Expose 
	private String crmId;
	@Expose
	private Long refCustomerId;
	@Expose
	private String customerNo;
	@Expose
	private String billingName;
	@Expose
	private String companyName;
	@Expose
	private String billingAddr1;
	@Expose
	private String billingAddr2;
	@Expose
	private String billingAddr3;
	@Expose
	private String city;
	@Expose
	private String state;
	@Expose
	private String postcode;
	@Expose
	private String country;
	@Expose
	private String taxNo;
	@Expose
	private String email;
	
	@Expose @SerializedName("contactDetails")
	private List<ContactDetailsVO> contactDetailsList;
	
	public String getCrmId() {
		return crmId;
	}

	public void setCrmId(String crmId) {
		this.crmId = crmId;
	}

	public Long getRefCustomerId() {
		return refCustomerId;
	}

	public void setRefCustomerId(Long refCustomerId) {
		this.refCustomerId = refCustomerId;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getBillingName() {
		return billingName;
	}

	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBillingAddr1() {
		return billingAddr1;
	}

	public void setBillingAddr1(String billingAddr1) {
		this.billingAddr1 = billingAddr1;
	}

	public String getBillingAddr2() {
		return billingAddr2;
	}

	public void setBillingAddr2(String billingAddr2) {
		this.billingAddr2 = billingAddr2;
	}

	public String getBillingAddr3() {
		return billingAddr3;
	}

	public void setBillingAddr3(String billingAddr3) {
		this.billingAddr3 = billingAddr3;
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

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ContactDetailsVO> getContactDetailsList() {
		return contactDetailsList;
	}

	public void setContactDetailsList(List<ContactDetailsVO> contactDetailsList) {
		this.contactDetailsList = contactDetailsList;
	}
	
}
