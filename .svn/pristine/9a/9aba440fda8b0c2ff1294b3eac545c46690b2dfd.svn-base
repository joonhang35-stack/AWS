package com.bcs.zsg.acct.vo;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bcs.zsg.acct.helper.EInvoiceProperties;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.web.object.EInvoiceDocument;

public class EInvoiceDocumentVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	// E-Invoice API properties
	private String documentUuid; // uuid
	private String submissionUid; // submissionUid
	private String longId; // longId
	private String eInvoiceDocType; // typeName
	private Date dtIssued; // dateTimeIssued
	private Date dtReceived; // dateTimeReceived
	private Date dtCancel; // cancelDateTime
	private Date dtValidated; // dateTimeValidated
	private Date dtReject; // rejectRequestDateTime
	private String eInvoiceInternalId; // internalId
	private String docStatusReason; // documentStatusReason
	private String issuerName; // issuerName
	private String issuerTin; // issuerTin
	private String receiverName; // receiverName
	private String receiverId; // receiverId
	private Double totalDiscount; // totalDiscount
	private Double totalExcludingTax; // totalExcludingTax
	private Double totalNetAmount; // totalNetAmount
	private Double totalPayableAmount; // totalPayableAmount
	private String eInvoiceVersion; // typeVersionName
	private String eInvoiceCreatedBy; // createdByUserId
	
	// system properties
	private Long idCompany;
	private Long idCust;
	private Long idSupplier;
	private Long idEInvoiceBuyer;
	private Long idEInvoiceSupplier;
	private Long idRefEInvDoc; // id of reference e invoice document (for CN/RN use), linking to e_invoice_document table
	private Long idRefDoc; // id of reference e invoice document (for CN/RN use), linking to e_invoice_document table
	private String originalEInvRef;  // Original e-Invoice Reference Number (UUID)
	private String eInvoiceDocTypeCd;
	private String sysDocType;
	private String sysPrefix;
	private Long refId;
	private String refNo;
	private Boolean isConsolEInv;
	
	// other properties (not in database)
	private byte[] qrCode;	
	private String qrCodeStr;	
	private InputStream qrCodeInputStream;
	
	private List<EInvoiceDocumentItemVO> itemList;
	private EInvoiceBuyerVO eInvoiceBuyerVO;
	private EInvoiceSupplierVO eInvoiceSupplierVO;
	private InvoiceVO refInvoiceVO; // invoiceVO of sys code of Original e-Invoice Reference Number (UUID)
	
	private EInvoiceDocument eInvoiceDocument;

	public EInvoiceDocument geteInvoiceDocument() {
		return eInvoiceDocument;
	}

	public void seteInvoiceDocument(EInvoiceDocument eInvoiceDocument) {
		this.eInvoiceDocument = eInvoiceDocument;
	}

	public InvoiceVO getRefInvoiceVO() {
		return refInvoiceVO;
	}

	public void setRefInvoiceVO(InvoiceVO refInvoiceVO) {
		this.refInvoiceVO = refInvoiceVO;
	}

	public Long getIdRefDoc() {
		return idRefDoc;
	}

	public void setIdRefDoc(Long idRefDoc) {
		this.idRefDoc = idRefDoc;
	}

	public String getOriginalEInvRef() {
		return originalEInvRef;
	}

	public void setOriginalEInvRef(String originalEInvRef) {
		this.originalEInvRef = originalEInvRef;
	}

	public Long getIdRefEInvDoc() {
		return idRefEInvDoc;
	}

	public void setIdRefEInvDoc(Long idRefEInvDoc) {
		this.idRefEInvDoc = idRefEInvDoc;
	}

	public String geteInvoiceDocTypeCd() {
		return eInvoiceDocTypeCd;
	}

	public void seteInvoiceDocTypeCd(String eInvoiceDocTypeCd) {
		this.eInvoiceDocTypeCd = eInvoiceDocTypeCd;
	}

	public Long getIdEInvoiceBuyer() {
		return idEInvoiceBuyer;
	}

	public void setIdEInvoiceBuyer(Long idEInvoiceBuyer) {
		this.idEInvoiceBuyer = idEInvoiceBuyer;
	}

	public Long getIdEInvoiceSupplier() {
		return idEInvoiceSupplier;
	}

	public void setIdEInvoiceSupplier(Long idEInvoiceSupplier) {
		this.idEInvoiceSupplier = idEInvoiceSupplier;
	}

	public InputStream getQrCodeInputStream() {
		return qrCodeInputStream;
	}

	public void setQrCodeInputStream(InputStream qrCodeInputStream) {
		this.qrCodeInputStream = qrCodeInputStream;
	}

	public EInvoiceBuyerVO geteInvoiceBuyerVO() {
		return eInvoiceBuyerVO;
	}

	public void seteInvoiceBuyerVO(EInvoiceBuyerVO eInvoiceBuyerVO) {
		this.eInvoiceBuyerVO = eInvoiceBuyerVO;
	}

	public EInvoiceSupplierVO geteInvoiceSupplierVO() {
		return eInvoiceSupplierVO;
	}

	public void seteInvoiceSupplierVO(EInvoiceSupplierVO eInvoiceSupplierVO) {
		this.eInvoiceSupplierVO = eInvoiceSupplierVO;
	}

	public List<EInvoiceDocumentItemVO> getItemList() {
		return itemList;
	}

	public void setItemList(List<EInvoiceDocumentItemVO> itemList) {
		this.itemList = itemList;
	}

	public Long getIdCust() {
		return idCust;
	}

	public void setIdCust(Long idCust) {
		this.idCust = idCust;
	}

	public Long getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(Long idSupplier) {
		this.idSupplier = idSupplier;
	}

	public String geteInvoiceVersion() {
		return eInvoiceVersion;
	}

	public void seteInvoiceVersion(String eInvoiceVersion) {
		this.eInvoiceVersion = eInvoiceVersion;
	}

	public Date getDtIssued() {
		return dtIssued;
	}

	public void setDtIssued(Date dtIssued) {
		this.dtIssued = dtIssued;
	}

	public Date getDtReceived() {
		return dtReceived;
	}

	public void setDtReceived(Date dtReceived) {
		this.dtReceived = dtReceived;
	}

	public String geteInvoiceInternalId() {
		return eInvoiceInternalId;
	}

	public void seteInvoiceInternalId(String eInvoiceInternalId) {
		this.eInvoiceInternalId = eInvoiceInternalId;
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

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String geteInvoiceCreatedBy() {
		return eInvoiceCreatedBy;
	}

	public void seteInvoiceCreatedBy(String eInvoiceCreatedBy) {
		this.eInvoiceCreatedBy = eInvoiceCreatedBy;
	}

	public String getQrCodeStr() {
		return qrCodeStr;
	}

	public void setQrCodeStr(String qrCodeStr) {
		this.qrCodeStr = qrCodeStr;
	}

	public byte[] getQrCode() {
		return qrCode;
	}

	public void setQrCode(byte[] qrCode) {
		this.qrCode = qrCode;
	}

	public String getValidationLink() {
		if (StringUtils.isNotBlank(documentUuid) && StringUtils.isNotBlank(longId)) {
			return EInvoiceProperties.getEnvBaseUrl() + documentUuid + "/share/" + longId;
		}
		return null;
	}

	public Boolean getIsConsolEInv() {
		return isConsolEInv;
	}

	public void setIsConsolEInv(Boolean isConsolEInv) {
		this.isConsolEInv = isConsolEInv;
	}

	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	public String getDocumentUuid() {
		return documentUuid;
	}

	public void setDocumentUuid(String documentUuid) {
		this.documentUuid = documentUuid;
	}

	public String getSubmissionUid() {
		return submissionUid;
	}

	public void setSubmissionUid(String submissionUid) {
		this.submissionUid = submissionUid;
	}

	public String getLongId() {
		return longId;
	}

	public void setLongId(String longId) {
		this.longId = longId;
	}

	public String geteInvoiceDocType() {
		return eInvoiceDocType;
	}

	public void seteInvoiceDocType(String eInvoiceDocType) {
		this.eInvoiceDocType = eInvoiceDocType;
	}

	public String getSysDocType() {
		return sysDocType;
	}

	public void setSysDocType(String sysDocType) {
		this.sysDocType = sysDocType;
	}

	public String getSysPrefix() {
		return sysPrefix;
	}

	public void setSysPrefix(String sysPrefix) {
		this.sysPrefix = sysPrefix;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Date getDtCancel() {
		return dtCancel;
	}

	public void setDtCancel(Date dtCancel) {
		this.dtCancel = dtCancel;
	}

	public Date getDtValidated() {
		return dtValidated;
	}

	public void setDtValidated(Date dtValidated) {
		this.dtValidated = dtValidated;
	}

	public Date getDtReject() {
		return dtReject;
	}

	public void setDtReject(Date dtReject) {
		this.dtReject = dtReject;
	}

	public String getDocStatusReason() {
		return docStatusReason;
	}

	public void setDocStatusReason(String docStatusReason) {
		this.docStatusReason = docStatusReason;
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

}
