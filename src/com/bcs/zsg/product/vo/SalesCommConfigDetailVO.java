package com.bcs.zsg.product.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class SalesCommConfigDetailVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idSalesCommConf;
	private String fareRangetype;
	private Double frAmt = 0.0;
	private Double toAmt = 0.0;
	private Double spComm = 0.0;
	private Double refSpComm = 0.0;
	private Double ssComm = 0.0;
	private Double hodComm = 0.0;
	private Double opComm = 0.0;
	
	public Long getIdSalesCommConf() {
		return idSalesCommConf;
	}

	public void setIdSalesCommConf(Long idSalesCommConf) {
		this.idSalesCommConf = idSalesCommConf;
	}

	public String getFareRangetype() {
		return fareRangetype;
	}

	public void setFareRangetype(String fareRangetype) {
		this.fareRangetype = fareRangetype;
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
	
}
