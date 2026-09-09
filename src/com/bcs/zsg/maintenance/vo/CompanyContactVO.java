package com.bcs.zsg.maintenance.vo;


import com.bcs.zsg.core.vo.BaseVO;

public class CompanyContactVO extends BaseVO {
	
	private static final long serialVersionUID = 1L;

	private Long companyid;
	private  String typecodecontact;
	private String number;

	public Long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public String getTypecodecontact() {
		return typecodecontact;
	}

	public void setTypecodecontact(String typecodecontact) {
		this.typecodecontact = typecodecontact;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
