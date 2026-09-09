package com.bcs.zsg.product.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class TourCatVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long idAcct;
	private Long idRegion;
	private String typeCd;
	private String desc;
	// for view purpose
	private String acctCd;

	/**
	 * @return the idAcct
	 */
	public Long getIdAcct() {
		return idAcct;
	}

	/**
	 * @param idAcct the idAcct to set
	 */
	public void setIdAcct(Long idAcct) {
		this.idAcct = idAcct;
	}

	/**
	 * @return the idRegion
	 */
	public Long getIdRegion() {
		return idRegion;
	}

	/**
	 * @param idRegion the idRegion to set
	 */
	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
	}

	/**
	 * @return the typeCd
	 */
	public String getTypeCd() {
		return typeCd;
	}

	/**
	 * @param typeCd the typeCd to set
	 */
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the acctCd
	 */
	public String getAcctCd() {
		return acctCd;
	}

	/**
	 * @param acctCd the acctCd to set
	 */
	public void setAcctCd(String acctCd) {
		this.acctCd = acctCd;
	}
}
