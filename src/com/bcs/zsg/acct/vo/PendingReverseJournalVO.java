package com.bcs.zsg.acct.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class PendingReverseJournalVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	// db field
	private Long idPs;
	private Long idInv;
	private Long idJournal;
	
	// others
	private Long idCompany;
	private String psNo;
	private String invNo;
	private String journalNo;
	
	public String getPsNo() {
		return psNo;
	}
	public void setPsNo(String psNo) {
		this.psNo = psNo;
	}
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public String getJournalNo() {
		return journalNo;
	}
	public void setJournalNo(String journalNo) {
		this.journalNo = journalNo;
	}
	public Long getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}
	public Long getIdPs() {
		return idPs;
	}
	public void setIdPs(Long idPs) {
		this.idPs = idPs;
	}
	public Long getIdInv() {
		return idInv;
	}
	public void setIdInv(Long idInv) {
		this.idInv = idInv;
	}
	public Long getIdJournal() {
		return idJournal;
	}
	public void setIdJournal(Long idJournal) {
		this.idJournal = idJournal;
	}
}
