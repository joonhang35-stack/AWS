package com.bcs.zsg.maintenance.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class CompanyNameVO extends BaseVO {
	
	private static final long serialVersionUID = 1L;
	
	private Long idCompany;
	private String name;
	private String shortName;
	private Date validFrom;
	private Date validTo;
	private String letterHeadUrl;
	
	public Long getIdCompany() {
		return idCompany;
	}
	
	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getValidFrom() {
		return validFrom;
	}
	
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	
	public Date getValidTo() {
		return validTo;
	}
	
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getLetterHeadUrl() {
		return letterHeadUrl;
	}
	
	public void setLetterHeadUrl(String letterHeadUrl) {
		this.letterHeadUrl = letterHeadUrl;
	}
}
