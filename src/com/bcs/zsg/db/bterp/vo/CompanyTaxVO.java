package com.bcs.zsg.db.bterp.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class CompanyTaxVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	
	private Long companyId;
	private String type;
	private String taxNumber;
	private Date validFrom;
	private Date validTo;
	private Date gstFilling;
	private String refundCarryForward;
	private String period;
	private String currency;
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTaxNumber() {
		return taxNumber;
	}
	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
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
	public Date getGstFilling() {
		return gstFilling;
	}
	public void setGstFilling(Date gstFilling) {
		this.gstFilling = gstFilling;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getRefundCarryForward() {
		return refundCarryForward;
	}
	public void setRefundCarryForward(String refundCarryForward) {
		this.refundCarryForward = refundCarryForward;
	}

}
