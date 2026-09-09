package com.bcs.zsg.product.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class AirlineScheduleChargeVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long idAirlineSchedule;
	private String typeCd;
	private String typeDesc;
	private Double amount;
	private Boolean isMisc;
	private Boolean isSumIntoTotal;

	public Boolean getIsSumIntoTotal() {
		return isSumIntoTotal;
	}

	public void setIsSumIntoTotal(Boolean isSumIntoTotal) {
		this.isSumIntoTotal = isSumIntoTotal;
	}

	/**
	 * @return the idAirlineSchedule
	 */
	public Long getIdAirlineSchedule() {
		return idAirlineSchedule;
	}

	/**
	 * @param idAirlineSchedule the idAirlineSchedule to set
	 */
	public void setIdAirlineSchedule(Long idAirlineSchedule) {
		this.idAirlineSchedule = idAirlineSchedule;
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
	 * @return the typeDesc
	 */
	public String getTypeDesc() {
		return typeDesc;
	}

	/**
	 * @param typeDesc the typeDesc to set
	 */
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the isMisc
	 */
	public Boolean getIsMisc() {
		return isMisc;
	}

	/**
	 * @param isMisc the isMisc to set
	 */
	public void setIsMisc(Boolean isMisc) {
		this.isMisc = isMisc;
	}
}
