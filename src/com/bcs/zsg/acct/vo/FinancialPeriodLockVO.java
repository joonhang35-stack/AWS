package com.bcs.zsg.acct.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class FinancialPeriodLockVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long idCompany;
	private Date dtStart;
	private Date dtEnd;

	/**
	 * @return the idCompany
	 */
	public Long getIdCompany() {
		return idCompany;
	}

	/**
	 * @param idCompany the idCompany to set
	 */
	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
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
}
