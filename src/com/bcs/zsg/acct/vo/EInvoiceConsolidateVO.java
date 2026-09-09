package com.bcs.zsg.acct.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class EInvoiceConsolidateVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long refId;
	private String refNo;
	private String documentUuid;
	private String submissionUid;
	private String longId;
	private String eInvoiceDocType;
	private String sysDocType;
	private String sysPrefix;

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

}
