package com.bcs.zsg.product.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class TourPackageRemarksVO extends BaseVO{

	private static final long serialVersionUID = 1L;
	
	private Long idTourDep;
	private String remarksDetails;
	private Date timestamp;
	
	/**
	 * @return the remarksDetails
	 */
	public String getRemarksDetails() {
		return remarksDetails;
	}
	
	/**a
	 * @param remarksDetails the remarksDetails to set
	 */
	public void setRemarksDetails(String remarksDetails) {
		this.remarksDetails = remarksDetails;
	}
	
	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

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
}
