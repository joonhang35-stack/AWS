package com.bcs.zsg.sales.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class IdentityDetailVO  extends BaseVO{
	private static final long serialVersionUID = 1L;
	
	private Long personIdentityId;
	private Long idCountry;
	private String typeCd;
	private String entryCd;
	private Date dtIssued;
	private Date dtExpired;
	private String scannedPath;
	private String filePath;
	
	/**
	 * @return the personIdentityId
	 */
	public Long getPersonIdentityId() {
		return personIdentityId;
	}
	
	/**
	 * @param personIdentityId the personIdentityId to set
	 */
	public void setPersonIdentityId(Long personIdentityId) {
		this.personIdentityId = personIdentityId;
	}
	
	/**
	 * @return the idCountry
	 */
	public Long getIdCountry() {
		return idCountry;
	}
	
	/**
	 * @param idCountry the idCountry to set
	 */
	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
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
	 * @return the entryCd
	 */
	public String getEntryCd() {
		return entryCd;
	}
	
	/**
	 * @param entryCd the entryCd to set
	 */
	public void setEntryCd(String entryCd) {
		this.entryCd = entryCd;
	}
	
	/**
	 * @return the scannedPath
	 */
	public String getScannedPath() {
		return scannedPath;
	}
	
	/**
	 * @param scannedPath the scannedPath to set
	 */
	public void setScannedPath(String scannedPath) {
		this.scannedPath = scannedPath;
	}

	/**
	 * @return the dtIssued
	 */
	public Date getDtIssued() {
		return dtIssued;
	}

	/**
	 * @param dtIssued the dtIssued to set
	 */
	public void setDtIssued(Date dtIssued) {
		this.dtIssued = dtIssued;
	}

	/**
	 * @return the dtExpired
	 */
	public Date getDtExpired() {
		return dtExpired;
	}

	/**
	 * @param dtExpired the dtExpired to set
	 */
	public void setDtExpired(Date dtExpired) {
		this.dtExpired = dtExpired;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
