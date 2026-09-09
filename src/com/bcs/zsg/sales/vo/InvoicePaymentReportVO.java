package com.bcs.zsg.sales.vo;

import java.util.Date;

public class InvoicePaymentReportVO extends InvoiceVO{

	private static final long serialVersionUID = 1L;
	private Integer paxCount;
	private String deptCode;
	private String deptDesc;
	private Long idTourCat;
	private String tourCatDesc;
	private Date paymentDt;
	private String cashBookCd;
	private String cbPrefix;
	private String paymentType;
	private String refNo;
	private String payFor;
	private Double paymentAmount;
	private String issuerName;
	private String country;
	private String tourPkgName;

	public Integer getPaxCount() {
		return paxCount;
	}

	public void setPaxCount(Integer paxCount) {
		this.paxCount = paxCount;
	}

	public Long getIdTourCat() {
		return idTourCat;
	}

	public void setIdTourCat(Long idTourCat) {
		this.idTourCat = idTourCat;
	}

	public String getTourCatDesc() {
		return tourCatDesc;
	}

	public void setTourCatDesc(String tourCatDesc) {
		this.tourCatDesc = tourCatDesc;
	}

	public Date getPaymentDt() {
		return paymentDt;
	}

	public void setPaymentDt(Date paymentDt) {
		this.paymentDt = paymentDt;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getPayFor() {
		return payFor;
	}

	public void setPayFor(String payFor) {
		this.payFor = payFor;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public String getCashBookCd() {
		return cashBookCd;
	}

	public void setCashBookCd(String cashBookCd) {
		this.cashBookCd = cashBookCd;
	}

	public String getCbPrefix() {
		return cbPrefix;
	}

	public void setCbPrefix(String cbPrefix) {
		this.cbPrefix = cbPrefix;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public String getTourPkgName() {
		return tourPkgName;
	}

	public void setTourPkgName(String tourPkgName) {
		this.tourPkgName = tourPkgName;
	}
}
