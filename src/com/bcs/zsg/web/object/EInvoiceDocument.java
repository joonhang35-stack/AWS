package com.bcs.zsg.web.object;

import java.io.Serializable;
import java.util.Date;

public class EInvoiceDocument implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// for Submit Documents API
	public String format;
	public String document;
	public String documentHash;
	public String codeNumber;

	// for Search Documents API
	public String buyerName;
	public String buyerTIN;
	public String cancelDateTime;
	public String createdByUserId;
	public String dateTimeIssued;
	public String dateTimeReceived;
	public String dateTimeValidated;
	public String documentCurrency;
	public String documentStatusReason;
	public String intermediaryName;
	public String intermediaryTIN;
	public String internalId;
	public String issuerID;
	public String issuerIDType;
	public String issuerTIN;
	public String longId;
	public String receiverID;
	public String receiverIDType;
	public String receiverName;
	public String receiverTIN;
	public String rejectRequestDateTime;
	public String status;
	public String submissionChannel;
	public String submissionUid;
	public String supplierName;
	public String supplierTIN;
	public String totalDiscount;
	public String totalExcludingTax;
	public String totalNetAmount;
	public String totalPayableAmount;
	public String typeName;
	public String typeVersionName;
	public String uuid;
	public EInvoiceDocumentValidationResult validationResults;
	
	// for Get Document Details API
	private String issuerTin;
	private String issuerName;
	private String receiverId;
	
	// for Get Recent Documents API
	private String intermediaryROB;
	private double netAmount;
	private String submitterROB;
	private double total;
	private double totalSales;
	
	// for Search Documents API - type conversion
	public Date cancelDateTimeDt;
	public Date dateTimeIssuedDt;
	public Date dateTimeReceivedDt;
	public Date dateTimeValidatedDt;
	public Date rejectRequestDateTimeDt;
	
	public void printValidationResults() {
		if (validationResults != null) {
			if(validationResults.getValidationSteps() != null) {
				for (EInvoiceValidationStepResult eInvoiceValidationStepResult : validationResults.getValidationSteps()) {
					if (eInvoiceValidationStepResult.getError() != null) {
						System.out.println(eInvoiceValidationStepResult.getError().getError());
						for (EInvoiceStandardError eInvoiceStandardError : eInvoiceValidationStepResult.getError().getInnerError()) {
							System.out.println(eInvoiceStandardError.getError());
//							System.out.println();
						}
					}
				}
			}
		}
	}

	public String getIssuerTin() {
		return issuerTin;
	}

	public void setIssuerTin(String issuerTin) {
		this.issuerTin = issuerTin;
	}

	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public EInvoiceDocumentValidationResult getValidationResults() {
		return validationResults;
	}

	public void setValidationResults(EInvoiceDocumentValidationResult validationResults) {
		this.validationResults = validationResults;
	}

	public Date getCancelDateTimeDt() {
		return cancelDateTimeDt;
	}

	public void setCancelDateTimeDt(Date cancelDateTimeDt) {
		this.cancelDateTimeDt = cancelDateTimeDt;
	}

	public Date getDateTimeIssuedDt() {
		return dateTimeIssuedDt;
	}

	public void setDateTimeIssuedDt(Date dateTimeIssuedDt) {
		this.dateTimeIssuedDt = dateTimeIssuedDt;
	}

	public Date getDateTimeReceivedDt() {
		return dateTimeReceivedDt;
	}

	public void setDateTimeReceivedDt(Date dateTimeReceivedDt) {
		this.dateTimeReceivedDt = dateTimeReceivedDt;
	}

	public Date getDateTimeValidatedDt() {
		return dateTimeValidatedDt;
	}

	public void setDateTimeValidatedDt(Date dateTimeValidatedDt) {
		this.dateTimeValidatedDt = dateTimeValidatedDt;
	}

	public Date getRejectRequestDateTimeDt() {
		return rejectRequestDateTimeDt;
	}

	public void setRejectRequestDateTimeDt(Date rejectRequestDateTimeDt) {
		this.rejectRequestDateTimeDt = rejectRequestDateTimeDt;
	}

	public String getBuyerTIN() {
		return buyerTIN;
	}

	public void setBuyerTIN(String buyerTIN) {
		this.buyerTIN = buyerTIN;
	}

	public String getCancelDateTime() {
		return cancelDateTime;
	}

	public void setCancelDateTime(String cancelDateTime) {
		this.cancelDateTime = cancelDateTime;
	}

	public String getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(String createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public String getDateTimeIssued() {
		return dateTimeIssued;
	}

	public void setDateTimeIssued(String dateTimeIssued) {
		this.dateTimeIssued = dateTimeIssued;
	}

	public String getDateTimeReceived() {
		return dateTimeReceived;
	}

	public void setDateTimeReceived(String dateTimeReceived) {
		this.dateTimeReceived = dateTimeReceived;
	}

	public String getDateTimeValidated() {
		return dateTimeValidated;
	}

	public void setDateTimeValidated(String dateTimeValidated) {
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

	public String getRejectRequestDateTime() {
		return rejectRequestDateTime;
	}

	public void setRejectRequestDateTime(String rejectRequestDateTime) {
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

	public String getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(String totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public String getTotalExcludingTax() {
		return totalExcludingTax;
	}

	public void setTotalExcludingTax(String totalExcludingTax) {
		this.totalExcludingTax = totalExcludingTax;
	}

	public String getTotalNetAmount() {
		return totalNetAmount;
	}

	public void setTotalNetAmount(String totalNetAmount) {
		this.totalNetAmount = totalNetAmount;
	}

	public String getTotalPayableAmount() {
		return totalPayableAmount;
	}

	public void setTotalPayableAmount(String totalPayableAmount) {
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

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getDocumentHash() {
		return documentHash;
	}

	public void setDocumentHash(String documentHash) {
		this.documentHash = documentHash;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	public String getSubmitterROB() {
		return submitterROB;
	}

	public void setSubmitterROB(String submitterROB) {
		this.submitterROB = submitterROB;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}

	public String getIntermediaryROB() {
		return intermediaryROB;
	}

	public void setIntermediaryROB(String intermediaryROB) {
		this.intermediaryROB = intermediaryROB;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
}
