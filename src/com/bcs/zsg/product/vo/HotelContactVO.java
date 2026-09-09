package com.bcs.zsg.product.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class HotelContactVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idHotel;
	private String typeCd;
	private String number;

	/**
	 * @return the idHotel
	 */
	public Long getIdHotel() {
		return idHotel;
	}

	/**
	 * @param idHotel the idHotel to set
	 */
	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
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
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
}
