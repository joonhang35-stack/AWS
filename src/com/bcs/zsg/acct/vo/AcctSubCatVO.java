package com.bcs.zsg.acct.vo;

public class AcctSubCatVO extends AcctCatVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idAcctCat;
	private Long idCompany;

	/**
	 * @return the idAcctCat
	 */
	public Long getIdAcctCat() {
		return idAcctCat;
	}

	/**
	 * @param idAcctCat the idAcctCat to set
	 */
	public void setIdAcctCat(Long idAcctCat) {
		this.idAcctCat = idAcctCat;
	}

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
}
