package com.bcs.zsg.einvoice.vo;

import java.util.Date;

public class ApiDocumentSummaryVO {

	private Date cancelDateTime;
	private String createdByUserId;
	private Date dateTimeIssued;
	private Date dateTimeReceived;
	private Date dateTimeValidated;
	private String documentStatusReason;
	private String internalId;
	private String issuerName;
	private String issuerTin;
	private String longId;
	private String receiverId;
	private String receiverName;
	private Date rejectRequestDateTime;
	private String status;
	private String submissionUid;
	private Double totalDiscount;
	private Double totalExcludingTax;
	private Double totalNetAmount;
	private Double totalPayableAmount;
	private String typeName;
	private String typeVersionName;
	private String uuid;
	
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
	public String getDocumentStatusReason() {
		return documentStatusReason;
	}
	public void setDocumentStatusReason(String documentStatusReason) {
		this.documentStatusReason = documentStatusReason;
	}
	public String getInternalId() {
		return internalId;
	}
	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}
	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	public String getIssuerTin() {
		return issuerTin;
	}
	public void setIssuerTin(String issuerTin) {
		this.issuerTin = issuerTin;
	}
	public String getLongId() {
		return longId;
	}
	public void setLongId(String longId) {
		this.longId = longId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
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
	public String getSubmissionUid() {
		return submissionUid;
	}
	public void setSubmissionUid(String submissionUid) {
		this.submissionUid = submissionUid;
	}
	public Double getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public Double getTotalExcludingTax() {
		return totalExcludingTax;
	}
	public void setTotalExcludingTax(Double totalExcludingTax) {
		this.totalExcludingTax = totalExcludingTax;
	}
	public Double getTotalNetAmount() {
		return totalNetAmount;
	}
	public void setTotalNetAmount(Double totalNetAmount) {
		this.totalNetAmount = totalNetAmount;
	}
	public Double getTotalPayableAmount() {
		return totalPayableAmount;
	}
	public void setTotalPayableAmount(Double totalPayableAmount) {
		this.totalPayableAmount = totalPayableAmount;
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
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
