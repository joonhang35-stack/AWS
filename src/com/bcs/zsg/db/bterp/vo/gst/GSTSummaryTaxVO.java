package com.bcs.zsg.db.bterp.vo.gst;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class GSTSummaryTaxVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idGSTSummary;
	private String type;
	private String taxCode;
	private double amount;
	private double rate;
	private double taxAmount;
	
	//Used in determine period
	private Date dateFrom;
	private Date dateTo;
	
	public Long getIdGSTSummary() {
		return idGSTSummary;
	}
	public void setIdGSTSummary(Long idGSTSummary) {
		this.idGSTSummary = idGSTSummary;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	
}
