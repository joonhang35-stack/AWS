package com.bcs.zsg.bank.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class CashBookBalVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Double totalDebit;
	private Double totalCredit;

	/**
	 * @return the totalDebit
	 */
	public Double getTotalDebit() {
		return totalDebit;
	}

	/**
	 * @param totalDebit the totalDebit to set
	 */
	public void setTotalDebit(Double totalDebit) {
		this.totalDebit = totalDebit;
	}

	/**
	 * @return the totalCredit
	 */
	public Double getTotalCredit() {
		return totalCredit;
	}

	/**
	 * @param totalCredit the totalCredit to set
	 */
	public void setTotalCredit(Double totalCredit) {
		this.totalCredit = totalCredit;
	}
}
