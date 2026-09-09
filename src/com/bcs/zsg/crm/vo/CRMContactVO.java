package com.bcs.zsg.crm.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CRMContactVO {
	private static final long serialVersionUID = 1L;
	@Expose
	@SerializedName("type")
	private String type;
	@Expose
	@SerializedName("number")
	private String number;
	@Expose
	@SerializedName("reportsTo")
	private String reportsTo;
	@Expose
	@SerializedName("status")
	private String status;
	@Expose
	@SerializedName("countryCode")
	private String countryCode;
	@Expose
	@SerializedName("countryId")
	private Long idCountry;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getReportsTo() {
		return reportsTo;
	}
	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public Long getIdCountry() {
		return idCountry;
	}
	
	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}
}
