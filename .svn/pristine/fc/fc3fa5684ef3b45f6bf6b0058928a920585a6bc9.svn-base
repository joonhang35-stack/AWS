package com.bcs.zsg.acct.vo;

import java.util.Date;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class PendingEInvoiceVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idCompany;
	/**
	 * Date scheduled for E-Invoice processing
	 */
	private Date dtProcess;
	
	// system properties
	private String sysDocType;
	private String sysPrefix;
	private Long refId;
	private String refNo;
	
	// E-Invoice API properties
	private String documentUuid; // uuid
	private String submissionUid; // submissionUid
	
	// status properties
	/**
	 * Processing status before submission
	 * P = Pending
	 * S = Successfully validated and submitted to E-Invoice
	 * F = Failed validation or submission process
	 */
	private String processStatus;
	/**
	 * Submission result status
	 * P = Pending
	 * S = Successfully accepted by E-Invoice (Accepted documents)
	 * F = Failed submission (Rejected documents)
	 */
	private String submitStatus;
	/**
	 * validation failure reason / rejected reason etc.
	 */
	private String reason;
	
	// others
	private InvoiceVO invoiceVO;
	
	public void setAllFail(String reason) {
		setProcessStatus(CommonConstant.EMAIL_PMNT_FAILED);
		setSubmitStatus(CommonConstant.EMAIL_PMNT_FAILED);
		setReason(reason);
	}
	
	public InvoiceVO getInvoiceVO() {
		return invoiceVO;
	}

	public void setInvoiceVO(InvoiceVO invoiceVO) {
		this.invoiceVO = invoiceVO;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * {@link PendingEInvoiceVO#processStatus}
	 * @see PendingEInvoiceVO#processStatus
	 */
	public String getProcessStatus() {
		return processStatus;
	}

	/**
	 * {@link PendingEInvoiceVO#processStatus}
	 * @see PendingEInvoiceVO#processStatus
	 */
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	/**
	 * {@link PendingEInvoiceVO#submitStatus}
	 * @see PendingEInvoiceVO#submitStatus
	 */
	public String getSubmitStatus() {
		return submitStatus;
	}

	/**
	 * {@link PendingEInvoiceVO#submitStatus}
	 * @see PendingEInvoiceVO#submitStatus
	 */
	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}

	/**
	 * {@link PendingEInvoiceVO#dtProcess}
	 * @see PendingEInvoiceVO#dtProcess
	 */
	public Date getDtProcess() {
		return dtProcess;
	}

	/**
	 * {@link PendingEInvoiceVO#dtProcess}
	 * @see PendingEInvoiceVO#dtProcess
	 */
	public void setDtProcess(Date dtProcess) {
		this.dtProcess = dtProcess;
	}
}
