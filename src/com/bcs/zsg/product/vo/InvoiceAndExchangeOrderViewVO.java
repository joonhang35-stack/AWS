package com.bcs.zsg.product.vo;


import com.bcs.zsg.acct.vo.AcctVO;

import com.bcs.zsg.maintenance.vo.CompanyVO;

public class InvoiceAndExchangeOrderViewVO extends InvoiceAndExchangeOrderVO {

	private static final long serialVersionUID = 1L;

	private CompanyVO companyVO;
	private AcctVO acctVO;
	public CompanyVO getCompanyVO() {
		return companyVO;
	}
	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}
	public AcctVO getAcctVO() {
		return acctVO;
	}
	public void setAcctVO(AcctVO acctVO) {
		this.acctVO = acctVO;
	}
	
}
