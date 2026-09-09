package com.bcs.zsg.product.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class TourHotelVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long idTourDep;
	//private Long idHotel;
	private Integer nightNum;
	private Date fromDate;
	private Date toDate;
	// for view porpuse
	private HotelVO hotelVO;

	/**
	 * @return the idTourDep
	 */
	public Long getIdTourDep() {
		return idTourDep;
	}

	/**
	 * @param idTourDep the idTourDep to set
	 */
	public void setIdTourDep(Long idTourDep) {
		this.idTourDep = idTourDep;
	}

	/**
	 * @return the nightNum
	 */
	public Integer getNightNum() {
		return nightNum;
	}

	/**
	 * @param nightNum the nightNum to set
	 */
	public void setNightNum(Integer nightNum) {
		this.nightNum = nightNum;
	}

	/**
	 * @return the hotelVO
	 */
	public HotelVO getHotelVO() {
		if (hotelVO == null) hotelVO = new HotelVO();
		return hotelVO;
	}

	/**
	 * @param hotelVO the hotelVO to set
	 */
	public void setHotelVO(HotelVO hotelVO) {
		this.hotelVO = hotelVO;
	}

	/**
	 * @return
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
