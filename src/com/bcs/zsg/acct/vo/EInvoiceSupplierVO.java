package com.bcs.zsg.acct.vo;

public class EInvoiceSupplierVO extends EInvoiceBuyerVO {
	private static final long serialVersionUID = 1L;

	private String tourismTaxRegNo;
	private String msicCode;
	private String businessDesc;

	public String getTourismTaxRegNo() {
		return tourismTaxRegNo;
	}

	public void setTourismTaxRegNo(String tourismTaxRegNo) {
		this.tourismTaxRegNo = tourismTaxRegNo;
	}

	public String getMsicCode() {
		return msicCode;
	}

	public void setMsicCode(String msicCode) {
		this.msicCode = msicCode;
	}

	public String getBusinessDesc() {
		return businessDesc;
	}

	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}

}
