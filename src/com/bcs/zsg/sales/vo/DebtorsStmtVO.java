package com.bcs.zsg.sales.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class DebtorsStmtVO extends BaseVO{
	private static final long serialVersionUID = 1L;
	
	
	private Integer rowNumber;
	private String custCode;
	private String invoiceCode;
	private Date invoiceDt;
	private String eoNo;
	private String attnTo;
	private Double invAmt;
	private Double invDue;
	private Double invPaid;
	private Date dueDt;
	private String custName;
	private String custSalutation;
	
	// Pax Statement
	private String psNo;
	private String docTypeCd;
	
	//Debtor Statement Rpt
	private String pcTypeCd;
	private String companyName;
	private String transType;
	private String docNo;
	private String refNo;
	private Double debit;
	private Double credit;
	private Double balance;
	
	private Double beginningBal;
	private Double current;
	private Double days01_30;
	private Double days31_60;
	private Double days61_90;
	private Double days91_120;
	private Double days120After;
	
	public String getPsNo() {
		return psNo;
	}
	public void setPsNo(String psNo) {
		this.psNo = psNo;
	}
	public String getDocTypeCd() {
		return docTypeCd;
	}
	public void setDocTypeCd(String docTypeCd) {
		this.docTypeCd = docTypeCd;
	}
	public Integer getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public Date getInvoiceDt() {
		return invoiceDt;
	}
	public void setInvoiceDt(Date invoiceDt) {
		this.invoiceDt = invoiceDt;
	}

	public String getAttnTo() {
		return attnTo;
	}
	public void setAttnTo(String attnTo) {
		this.attnTo = attnTo;
	}
	public Double getInvAmt() {
		return invAmt;
	}
	public void setInvAmt(Double invAmt) {
		this.invAmt = invAmt;
	}
	public Double getInvDue() {
		return invDue;
	}
	public void setInvDue(Double invDue) {
		this.invDue = invDue;
	}
	public Double getInvPaid() {
		return invPaid;
	}
	public void setInvPaid(Double invPaid) {
		this.invPaid = invPaid;
	}
	public Date getDueDt() {
		return dueDt;
	}
	public void setDueDt(Date dueDt) {
		this.dueDt = dueDt;
	}
	public String getEoNo() {
		return eoNo;
	}
	public void setEoNo(String eoNo) {
		this.eoNo = eoNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustSalutation() {
		return custSalutation;
	}
	public void setCustSalutation(String custSalutation) {
		this.custSalutation = custSalutation;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public Double getDebit() {
		return debit;
	}
	public void setDebit(Double debit) {
		this.debit = debit;
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	public String getPcTypeCd() {
		return pcTypeCd;
	}
	public void setPcTypeCd(String pcTypeCd) {
		this.pcTypeCd = pcTypeCd;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getBeginningBal() {
		return beginningBal;
	}
	public void setBeginningBal(Double beginningBal) {
		this.beginningBal = beginningBal;
	}
	public Double getCurrent() {
		return current;
	}
	public void setCurrent(Double current) {
		this.current = current;
	}
	public Double getDays01_30() {
		return days01_30;
	}
	public void setDays01_30(Double days01_30) {
		this.days01_30 = days01_30;
	}
	public Double getDays31_60() {
		return days31_60;
	}
	public void setDays31_60(Double days31_60) {
		this.days31_60 = days31_60;
	}
	public Double getDays61_90() {
		return days61_90;
	}
	public void setDays61_90(Double days61_90) {
		this.days61_90 = days61_90;
	}
	public Double getDays91_120() {
		return days91_120;
	}
	public void setDays91_120(Double days91_120) {
		this.days91_120 = days91_120;
	}
	public Double getDays120After() {
		return days120After;
	}
	public void setDays120After(Double days120After) {
		this.days120After = days120After;
	}
}
