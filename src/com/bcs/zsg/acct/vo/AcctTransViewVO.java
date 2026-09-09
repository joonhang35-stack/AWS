package com.bcs.zsg.acct.vo;

import java.util.Date;

public class AcctTransViewVO extends AcctTransVO {

	private static final long serialVersionUID = 1L;

	
	private AcctViewVO acctViewVO;
	
	private Long accttransId;
	private Double beginBal;
	private Double currBal;
	private Double bfBal;
	private Double reducingBal;
	//for view purpose
	private String paymentDesc;
	private String depositDesc;
	private String tempPayment;
	private String tempDeposit;
	private Long acctPaymentId;
	private Long acctDepositId;
	private Date fromDate;
	private Date toDate;
	private double totDebit;
	private double totCredit;
	private double amount;
	private String glCode;
	private String tourCd;
	private String pbComment;
	private Integer qty;
	private String invoiceNo;
	private String invNo;
	private String psNo;
	private String jrnlNo;
	
	public AcctViewVO getAcctViewVO() {
		return acctViewVO;
	}
	public void setAcctViewVO(AcctViewVO acctViewVO) {
		this.acctViewVO = acctViewVO;
	}
	public Long getAccttransId() {
		return accttransId;
	}
	public void setAccttransId(Long accttransId) {
		this.accttransId = accttransId;
	}
	public String getPaymentDesc() {
		return paymentDesc;
	}
	public void setPaymentDesc(String paymentDesc) {
		this.paymentDesc = paymentDesc;
	}
	public String getDepositDesc() {
		return depositDesc;
	}
	public void setDepositDesc(String depositDesc) {
		this.depositDesc = depositDesc;
	}
	public String getTempPayment() {
		return tempPayment;
	}
	public void setTempPayment(String tempPayment) {
		this.tempPayment = tempPayment;
	}
	public String getTempDeposit() {
		return tempDeposit;
	}
	public void setTempDeposit(String tempDeposit) {
		this.tempDeposit = tempDeposit;
	}
	public Long getAcctPaymentId() {
		return acctPaymentId;
	}
	public void setAcctPaymentId(Long acctPaymentId) {
		this.acctPaymentId = acctPaymentId;
	}
	public Long getAcctDepositId() {
		return acctDepositId;
	}
	public void setAcctDepositId(Long acctDepositId) {
		this.acctDepositId = acctDepositId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Double getBeginBal() {
		return beginBal;
	}
	public void setBeginBal(Double beginBal) {
		this.beginBal = beginBal;
	}
	public Double getCurrBal() {
		return currBal;
	}
	public void setCurrBal(Double currBal) {
		this.currBal = currBal;
	}
	public Double getBfBal() {
		return bfBal;
	}
	public void setBfBal(Double bfBal) {
		this.bfBal = bfBal;
	}
	/**
	 * @return the totDebit
	 */
	public double getTotDebit() {
		return totDebit;
	}
	/**
	 * @param totDebit the totDebit to set
	 */
	public void setTotDebit(double totDebit) {
		this.totDebit = totDebit;
	}
	/**
	 * @return the totCredit
	 */
	public double getTotCredit() {
		return totCredit;
	}
	/**
	 * @param totCredit the totCredit to set
	 */
	public void setTotCredit(double totCredit) {
		this.totCredit = totCredit;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Double getReducingBal() {
		return reducingBal;
	}
	public void setReducingBal(Double reducingBal) {
		this.reducingBal = reducingBal;
	}
	public String getGlCode() {
		return glCode;
	}
	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}
	public String getTourCd() {
		return tourCd;
	}
	public void setTourCd(String tourCd) {
		this.tourCd = tourCd;
	}
	public String getPbComment() {
		return pbComment;
	}
	public void setPbComment(String pbComment) {
		this.pbComment = pbComment;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public String getPsNo() {
		return psNo;
	}
	public void setPsNo(String psNo) {
		this.psNo = psNo;
	}
	public String getJrnlNo() {
		return jrnlNo;
	}
	public void setJrnlNo(String jrnlNo) {
		this.jrnlNo = jrnlNo;
	}
}
