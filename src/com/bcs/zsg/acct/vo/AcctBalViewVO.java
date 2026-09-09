package com.bcs.zsg.acct.vo;

public class AcctBalViewVO extends AcctBalVO {

	private static final long serialVersionUID = 1L;

	private AcctVO acctVO;
	
	private Double totalBeginDebit;
	private Double totalBeginCredit;

	/**
	 * @return the acctVO
	 */
	public AcctVO getAcctVO() {
		return acctVO;
	}

	/**
	 * @param acctVO the acctVO to set
	 */
	public void setAcctVO(AcctVO acctVO) {
		this.acctVO = acctVO;
	}

	/**
	 * @return the totalBeginDebit
	 */
	public Double getTotalBeginDebit() {
		return totalBeginDebit;
	}

	/**
	 * @param totalBeginDebit the totalBeginDebit to set
	 */
	public void setTotalBeginDebit(Double totalBeginDebit) {
		this.totalBeginDebit = totalBeginDebit;
	}

	/**
	 * @return the totalBeginCredit
	 */
	public Double getTotalBeginCredit() {
		return totalBeginCredit;
	}

	/**
	 * @param totalBeginCredit the totalBeginCredit to set
	 */
	public void setTotalBeginCredit(Double totalBeginCredit) {
		this.totalBeginCredit = totalBeginCredit;
	}
}
