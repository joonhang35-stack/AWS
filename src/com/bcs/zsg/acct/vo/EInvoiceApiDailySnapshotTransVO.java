package com.bcs.zsg.acct.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class EInvoiceApiDailySnapshotTransVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	
	// basic info
	private Long idCompany;
	private Date dtTrans;
	private String existsInLocalStatus;
	private String processStatus;

	// E-Invoice Get Recent Documents API properties
	private String buyerName;
	private String buyerTIN;
	private Date cancelDateTime;
	private String createdByUserId;
	private Date dateTimeIssued;
	private Date dateTimeReceived;
	private Date dateTimeValidated;
	private String documentCurrency;
	private String documentStatusReason;
	private String intermediaryName;
	private String intermediaryROB;
	private String intermediaryTIN;
	private String internalId;
	private String issuerID;
	private String issuerIDType;
	private String issuerTIN;
	private String longId;
	private Double netAmount;
	private String receiverID;
	private String receiverIDType;
	private String receiverName;
	private String receiverTIN;
	private Date rejectRequestDateTime;
	private String status;
	private String submissionChannel;
	private String submissionUid;
	private String submitterROB;
	private String supplierName;
	private String supplierTIN;
	private Double total;
	private Double totalDiscount;
	private Double totalSales;
	private String typeName;
	private String typeVersionName;
	
	public Long getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}
	public Date getDtTrans() {
		return dtTrans;
	}
	public void setDtTrans(Date dtTrans) {
		this.dtTrans = dtTrans;
	}
	public String getExistsInLocalStatus() {
		return existsInLocalStatus;
	}
	public void setExistsInLocalStatus(String existsInLocalStatus) {
		this.existsInLocalStatus = existsInLocalStatus;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerTIN() {
		return buyerTIN;
	}
	public void setBuyerTIN(String buyerTIN) {
		this.buyerTIN = buyerTIN;
	}
	public Date getCancelDateTime() {
		return cancelDateTime;
	}
	public void setCancelDateTime(Date cancelDateTime) {
		this.cancelDateTime = cancelDateTime;
	}
	public String getCreatedByUserId() {
		return createdByUserId;
	}
	public void setCreatedByUserId(String createdByUserId) {
		this.createdByUserId = createdByUserId;
	}
	public Date getDateTimeIssued() {
		return dateTimeIssued;
	}
	public void setDateTimeIssued(Date dateTimeIssued) {
		this.dateTimeIssued = dateTimeIssued;
	}
	public Date getDateTimeReceived() {
		return dateTimeReceived;
	}
	public void setDateTimeReceived(Date dateTimeReceived) {
		this.dateTimeReceived = dateTimeReceived;
	}
	public Date getDateTimeValidated() {
		return dateTimeValidated;
	}
	public void setDateTimeValidated(Date dateTimeValidated) {
		this.dateTimeValidated = dateTimeValidated;
	}
	public String getDocumentCurrency() {
		return documentCurrency;
	}
	public void setDocumentCurrency(String documentCurrency) {
		this.documentCurrency = documentCurrency;
	}
	public String getDocumentStatusReason() {
		return documentStatusReason;
	}
	public void setDocumentStatusReason(String documentStatusReason) {
		this.documentStatusReason = documentStatusReason;
	}
	public String getIntermediaryName() {
		return intermediaryName;
	}
	public void setIntermediaryName(String intermediaryName) {
		this.intermediaryName = intermediaryName;
	}
	public String getIntermediaryROB() {
		return intermediaryROB;
	}
	public void setIntermediaryROB(String intermediaryROB) {
		this.intermediaryROB = intermediaryROB;
	}
	public String getIntermediaryTIN() {
		return intermediaryTIN;
	}
	public void setIntermediaryTIN(String intermediaryTIN) {
		this.intermediaryTIN = intermediaryTIN;
	}
	public String getInternalId() {
		return internalId;
	}
	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}
	public String getIssuerID() {
		return issuerID;
	}
	public void setIssuerID(String issuerID) {
		this.issuerID = issuerID;
	}
	public String getIssuerIDType() {
		return issuerIDType;
	}
	public void setIssuerIDType(String issuerIDType) {
		this.issuerIDType = issuerIDType;
	}
	public String getIssuerTIN() {
		return issuerTIN;
	}
	public void setIssuerTIN(String issuerTIN) {
		this.issuerTIN = issuerTIN;
	}
	public String getLongId() {
		return longId;
	}
	public void setLongId(String longId) {
		this.longId = longId;
	}
	public Double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}
	public String getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}
	public String getReceiverIDType() {
		return receiverIDType;
	}
	public void setReceiverIDType(String receiverIDType) {
		this.receiverIDType = receiverIDType;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverTIN() {
		return receiverTIN;
	}
	public void setReceiverTIN(String receiverTIN) {
		this.receiverTIN = receiverTIN;
	}
	public Date getRejectRequestDateTime() {
		return rejectRequestDateTime;
	}
	public void setRejectRequestDateTime(Date rejectRequestDateTime) {
		this.rejectRequestDateTime = rejectRequestDateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubmissionChannel() {
		return submissionChannel;
	}
	public void setSubmissionChannel(String submissionChannel) {
		this.submissionChannel = submissionChannel;
	}
	public String getSubmissionUid() {
		return submissionUid;
	}
	public void setSubmissionUid(String submissionUid) {
		this.submissionUid = submissionUid;
	}
	public String getSubmitterROB() {
		return submitterROB;
	}
	public void setSubmitterROB(String submitterROB) {
		this.submitterROB = submitterROB;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierTIN() {
		return supplierTIN;
	}
	public void setSupplierTIN(String supplierTIN) {
		this.supplierTIN = supplierTIN;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public Double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(Double totalSales) {
		this.totalSales = totalSales;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeVersionName() {
		return typeVersionName;
	}
	public void setTypeVersionName(String typeVersionName) {
		this.typeVersionName = typeVersionName;
	}
}
