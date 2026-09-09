package com.bcs.zsg.acct.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class EInvoiceConsolidateNumGenVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idCompany;
	private String code;
	private Integer year;
	private Integer month;
	private Long nextNo;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Long getNextNo() {
		return nextNo;
	}

	public void setNextNo(Long nextNo) {
		this.nextNo = nextNo;
	}

}
