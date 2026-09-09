package com.bcs.zsg.product.vo;

import java.util.List;

import com.bcs.zsg.core.vo.BaseVO;

public class AirlineVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private String code;
	private String desc;
	private String descZh;
	private String typeCode;
	private List<TourDepartureViewVO> tourDepViewList;
	private List<TicketingVO> ticketingList;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the tourDepViewList
	 */
	public List<TourDepartureViewVO> getTourDepViewList() {
		return tourDepViewList;
	}

	/**
	 * @param tourDepViewList the tourDepViewList to set
	 */
	public void setTourDepViewList(List<TourDepartureViewVO> tourDepViewList) {
		this.tourDepViewList = tourDepViewList;
	}

	/**
	 * @return the ticketingList
	 */
	public List<TicketingVO> getTicketingList() {
		return ticketingList;
	}

	/**
	 * @param ticketingList the ticketingList to set
	 */
	public void setTicketingList(List<TicketingVO> ticketingList) {
		this.ticketingList = ticketingList;
	}

	public String getDescZh() {
		return descZh;
	}

	public void setDescZh(String descZh) {
		this.descZh = descZh;
	}
}
