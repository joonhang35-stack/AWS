package com.bcs.zsg.sales.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class BookingSalesCommVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idTourBooking;
	private Long idSalesCommConfDtl;
	private String tourType;
	private String fareRangetype;
	private Double frAmt = 0.0;
	private Double toAmt = 0.0;
	private Double tourFare = 0.0;
	private Double spComm = 0.0;
	private Double refSpComm = 0.0;
	private Double ssComm = 0.0;
	private Double hodComm = 0.0;
	private Double opComm = 0.0;
	
	public String getFareRangetype() {
		return fareRangetype;
	}
	
	public void setFareRangetype(String fareRangetype) {
		this.fareRangetype = fareRangetype;
	}

	public Long getIdTourBooking() {
		return idTourBooking;
	}

	public void setIdTourBooking(Long idTourBooking) {
		this.idTourBooking = idTourBooking;
	}

	public Long getIdSalesCommConfDtl() {
		return idSalesCommConfDtl;
	}

	public void setIdSalesCommConfDtl(Long idSalesCommConfDtl) {
		this.idSalesCommConfDtl = idSalesCommConfDtl;
	}

	public Double getFrAmt() {
		return frAmt;
	}

	public void setFrAmt(Double frAmt) {
		this.frAmt = frAmt;
	}

	public Double getToAmt() {
		return toAmt;
	}

	public void setToAmt(Double toAmt) {
		this.toAmt = toAmt;
	}

	public Double getSpComm() {
		return spComm;
	}

	public void setSpComm(Double spComm) {
		this.spComm = spComm;
	}

	public Double getRefSpComm() {
		return refSpComm;
	}

	public void setRefSpComm(Double refSpComm) {
		this.refSpComm = refSpComm;
	}

	public Double getSsComm() {
		return ssComm;
	}

	public void setSsComm(Double ssComm) {
		this.ssComm = ssComm;
	}

	public Double getHodComm() {
		return hodComm;
	}

	public void setHodComm(Double hodComm) {
		this.hodComm = hodComm;
	}

	public Double getOpComm() {
		return opComm;
	}

	public void setOpComm(Double opComm) {
		this.opComm = opComm;
	}

	public String getTourType() {
		return tourType;
	}

	public void setTourType(String tourType) {
		this.tourType = tourType;
	}

	public Double getTourFare() {
		return tourFare;
	}

	public void setTourFare(Double tourFare) {
		this.tourFare = tourFare;
	}
	
//	private Long idBooking;
//	private Long idSalesCommConfDtl;
//	private String fullFareRangetype;
//	private Double fullFrAmt = 0.0;
//	private Double fullToAmt = 0.0;
//	private Double fullSpComm = 0.0;
//	private Double fullRefSpComm = 0.0;
//	private Double fullSsComm = 0.0;
//	private Double fullHodComm = 0.0;
//	private Double fullOpComm = 0.0;
//	private String grndFareRangetype;
//	private Double grndFrAmt = 0.0;
//	private Double grndToAmt = 0.0;
//	private Double grndSpComm = 0.0;
//	private Double grndRefSpComm = 0.0;
//	private Double grndSsComm = 0.0;
//	private Double grndHodComm = 0.0;
//	private Double grndOpComm = 0.0;
	
	
	
}
