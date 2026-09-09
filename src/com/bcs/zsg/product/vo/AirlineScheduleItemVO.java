package com.bcs.zsg.product.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class AirlineScheduleItemVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idAirlineSchedule;
	private String typeCd;
	private String fromAirportCd;
	private String toAirportCd;
	private String flightCd;
	private String etd;
	private String eta;
	private Date departureDate;
	private Date arrivalDate;
	private Boolean isNextDay;
	private Integer seq;
//	private Integer tktValidity;

	private String airline;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
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
	 * @return the fromAirportCd
	 */
	public String getFromAirportCd() {
		return fromAirportCd;
	}

	/**
	 * @param fromAirportCd the fromAirportCd to set
	 */
	public void setFromAirportCd(String fromAirportCd) {
		this.fromAirportCd = fromAirportCd;
	}

	/**
	 * @return the toAirportCd
	 */
	public String getToAirportCd() {
		return toAirportCd;
	}

	/**
	 * @param toAirportCd the toAirportCd to set
	 */
	public void setToAirportCd(String toAirportCd) {
		this.toAirportCd = toAirportCd;
	}

	/**
	 * @return the flightCd
	 */
	public String getFlightCd() {
		return flightCd;
	}

	/**
	 * @param flightCd the flightCd to set
	 */
	public void setFlightCd(String flightCd) {
		this.flightCd = flightCd;
	}

	/**
	 * @return the etd
	 */
	public String getEtd() {
		return etd;
	}

	/**
	 * @param etd the etd to set
	 */
	public void setEtd(String etd) {
		this.etd = etd;
	}

	/**
	 * @return the eta
	 */
	public String getEta() {
		return eta;
	}

	/**
	 * @param eta the eta to set
	 */
	public void setEta(String eta) {
		this.eta = eta;
	}

	/**
	 * @return the isNextDay
	 */
	public Boolean getIsNextDay() {
		return isNextDay;
	}

	/**
	 * @param isNextDay the isNextDay to set
	 */
	public void setIsNextDay(Boolean isNextDay) {
		this.isNextDay = isNextDay;
	}

//	/**
//	 * @return the tktValidity
//	 */
//	public Integer getTktValidity() {
//		return tktValidity;
//	}
//
//	/**
//	 * @param tktValidity the tktValidity to set
//	 */
//	public void setTktValidity(Integer tktValidity) {
//		this.tktValidity = tktValidity;
//	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

}
