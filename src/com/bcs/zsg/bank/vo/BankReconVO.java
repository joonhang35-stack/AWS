package com.bcs.zsg.bank.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class BankReconVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long idBank;
	private Date dtStart;
	private Date dtEnd;
	private String month;
	private String year;
	private Double statementCr = 0.0;
	private Double statementDr = 0.0;
	private Double statementBal = 0.0;
	private Double cashBookCr = 0.0;
	private Double cashBookDr = 0.0;
	private Double cashBookBal = 0.0;
	// for present purpose
	private Double cbCr = 0.0;
	private Double cbDr = 0.0;
	private Double cbBal = 0.0;
	private Double cbDiffCr = 0.0; // cash book diff credit
	private Double cbDiffDr = 0.0;
	private Double obCr = 0.0; // outstanding balance credit
	private Double obDr = 0.0;
	private Double obBal = 0.0;
	private Double usdBal = 0.0; // unsolved diff balance
	private int noOutsCr; // number of outstanding credit
	private int noOutsDr;
	private boolean isDateInFinPeriodClosed;

	/**
	 * @return the idBank
	 */
	public Long getIdBank() {
		return idBank;
	}

	/**
	 * @param idBank the idBank to set
	 */
	public void setIdBank(Long idBank) {
		this.idBank = idBank;
	}

	/**
	 * @return the dtStart
	 */
	public Date getDtStart() {
		return dtStart;
	}

	/**
	 * @param dtStart the dtStart to set
	 */
	public void setDtStart(Date dtStart) {
		this.dtStart = dtStart;
	}

	/**
	 * @return the dtEnd
	 */
	public Date getDtEnd() {
		return dtEnd;
	}

	/**
	 * @param dtEnd the dtEnd to set
	 */
	public void setDtEnd(Date dtEnd) {
		this.dtEnd = dtEnd;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the statementCr
	 */
	public Double getStatementCr() {
		return statementCr;
	}

	/**
	 * @param statementCr the statementCr to set
	 */
	public void setStatementCr(Double statementCr) {
		this.statementCr = statementCr;
	}

	/**
	 * @return the statementDr
	 */
	public Double getStatementDr() {
		return statementDr;
	}

	/**
	 * @param statementDr the statementDr to set
	 */
	public void setStatementDr(Double statementDr) {
		this.statementDr = statementDr;
	}

	/**
	 * @return the statementBal
	 */
	public Double getStatementBal() {
		return statementBal;
	}

	/**
	 * @param statementBal the statementBal to set
	 */
	public void setStatementBal(Double statementBal) {
		this.statementBal = statementBal;
	}

	/**
	 * @return the cashBookCr
	 */
	public Double getCashBookCr() {
		return cashBookCr;
	}

	/**
	 * @param cashBookCr the cashBookCr to set
	 */
	public void setCashBookCr(Double cashBookCr) {
		this.cashBookCr = cashBookCr;
	}

	/**
	 * @return the cashBookDr
	 */
	public Double getCashBookDr() {
		return cashBookDr;
	}

	/**
	 * @param cashBookDr the cashBookDr to set
	 */
	public void setCashBookDr(Double cashBookDr) {
		this.cashBookDr = cashBookDr;
	}

	/**
	 * @return the cashBookBal
	 */
	public Double getCashBookBal() {
		return cashBookBal;
	}

	/**
	 * @param cashBookBal the cashBookBal to set
	 */
	public void setCashBookBal(Double cashBookBal) {
		this.cashBookBal = cashBookBal;
	}

	/**
	 * @return the cbCr
	 */
	public Double getCbCr() {
		return cbCr;
	}

	/**
	 * @param cbCr the cbCr to set
	 */
	public void setCbCr(Double cbCr) {
		this.cbCr = cbCr;
	}

	/**
	 * @return the cbDr
	 */
	public Double getCbDr() {
		return cbDr;
	}

	/**
	 * @param cbDr the cbDr to set
	 */
	public void setCbDr(Double cbDr) {
		this.cbDr = cbDr;
	}

	/**
	 * @return the cbBal
	 */
	public Double getCbBal() {
		return cbBal;
	}

	/**
	 * @param cbBal the cbBal to set
	 */
	public void setCbBal(Double cbBal) {
		this.cbBal = cbBal;
	}

	/**
	 * @return the cbDiffCr
	 */
	public Double getCbDiffCr() {
		return cbDiffCr;
	}

	/**
	 * @param cbDiffCr the cbDiffCr to set
	 */
	public void setCbDiffCr(Double cbDiffCr) {
		this.cbDiffCr = cbDiffCr;
	}

	/**
	 * @return the cbDiffDr
	 */
	public Double getCbDiffDr() {
		return cbDiffDr;
	}

	/**
	 * @param cbDiffDr the cbDiffDr to set
	 */
	public void setCbDiffDr(Double cbDiffDr) {
		this.cbDiffDr = cbDiffDr;
	}

	/**
	 * @return the obCr
	 */
	public Double getObCr() {
		return obCr;
	}

	/**
	 * @param obCr the obCr to set
	 */
	public void setObCr(Double obCr) {
		this.obCr = obCr;
	}

	/**
	 * @return the obDr
	 */
	public Double getObDr() {
		return obDr;
	}

	/**
	 * @param obDr the obDr to set
	 */
	public void setObDr(Double obDr) {
		this.obDr = obDr;
	}

	/**
	 * @return the obBal
	 */
	public Double getObBal() {
		return obBal;
	}

	/**
	 * @param obBal the obBal to set
	 */
	public void setObBal(Double obBal) {
		this.obBal = obBal;
	}

	/**
	 * @return the usdBal
	 */
	public Double getUsdBal() {
		return usdBal;
	}

	/**
	 * @param usdBal the usdBal to set
	 */
	public void setUsdBal(Double usdBal) {
		this.usdBal = usdBal;
	}

	/**
	 * @return the noOutsCr
	 */
	public int getNoOutsCr() {
		return noOutsCr;
	}

	/**
	 * @param noOutsCr the noOutsCr to set
	 */
	public void setNoOutsCr(int noOutsCr) {
		this.noOutsCr = noOutsCr;
	}

	/**
	 * @return the noOutsDr
	 */
	public int getNoOutsDr() {
		return noOutsDr;
	}

	/**
	 * @param noOutsDr the noOutsDr to set
	 */
	public void setNoOutsDr(int noOutsDr) {
		this.noOutsDr = noOutsDr;
	}

	/**
	 * @return the isDateInFinPeriodClosed
	 */
	public boolean isDateInFinPeriodClosed() {
		return isDateInFinPeriodClosed;
	}

	/**
	 * @param isDateInFinPeriodClosed the isDateInFinPeriodClosed to set
	 */
	public void setDateInFinPeriodClosed(boolean isDateInFinPeriodClosed) {
		this.isDateInFinPeriodClosed = isDateInFinPeriodClosed;
	}
}
