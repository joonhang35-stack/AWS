package com.bcs.zsg.product.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class TourPackageRoomPriceVO extends BaseVO{

	private static final long serialVersionUID = 1L;
	
	private Long pkgId;
	private String roomName;
	private Long priceFrom;
	private Long priceTo;  
	
	/**
	 * @return the remarksDetails
	 */
	public String getRoomName() {
		return roomName;
	}
	
	/**a
	 * @param remarksDetails the remarksDetails to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	  
	/**
	 * @return the pkgId
	 */
	public Long getPkgId() {
		return pkgId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setPkgId(Long pkgId) {
		this.pkgId = pkgId;
	}
	

	/**
	 * @return the pkgId
	 */
	public Long getPriceFrom() {
		return priceFrom;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setPriceFrom(Long priceFrom) {
		this.priceFrom = priceFrom;
	}
	/**
	 * @return the pkgId
	 */
	public Long getPriceTo() {
		return priceTo;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setPriceTo(Long priceTo) {
		this.priceTo = priceTo;
	}
	
}
