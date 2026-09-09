package com.bcs.zsg.sales.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class CustTourHistVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idInv;
	private Long idTourDep;
	private String code;
	private String attnTo;
	private String tourCode;
	private Date dtDep;
	private String salesPerson;
	private String passengers;
	private String tourPkgtypeCd;
	private InvoiceVO invVO;
	
	public InvoiceVO getInvVO() {
		return invVO;
	}
	public void setInvVO(InvoiceVO invVO) {
		this.invVO = invVO;
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
	 * @return the attnTo
	 */
	public String getAttnTo() {
		return attnTo;
	}
	/**
	 * @param attnTo the attnTo to set
	 */
	public void setAttnTo(String attnTo) {
		this.attnTo = attnTo;
	}
	/**
	 * @return the tourCode
	 */
	public String getTourCode() {
		return tourCode;
	}
	/**
	 * @param tourCode the tourCode to set
	 */
	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}
	/**
	 * @return the dtDep
	 */
	public Date getDtDep() {
		return dtDep;
	}
	/**
	 * @param dtDep the dtDep to set
	 */
	public void setDtDep(Date dtDep) {
		this.dtDep = dtDep;
	}
	/**
	 * @return the salesPerson
	 */
	public String getSalesPerson() {
		return salesPerson;
	}
	/**
	 * @param salesPerson the salesPerson to set
	 */
	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}
	/**
	 * @return the passengers
	 */
	public String getPassengers() {
		return passengers;
	}
	/**
	 * @param passengers the passengers to set
	 */
	public void setPassengers(String passengers) {
		this.passengers = passengers;
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
	/**
	 * @return the idInv
	 */
	public Long getIdInv() {
		return idInv;
	}
	/**
	 * @param idInv the idInv to set
	 */
	public void setIdInv(Long idInv) {
		this.idInv = idInv;
	}
	/**
	 * @return the tourPkgtypeCd
	 */
	public String getTourPkgtypeCd() {
		return tourPkgtypeCd;
	}
	/**
	 * @param tourPkgtypeCd the tourPkgtypeCd to set
	 */
	public void setTourPkgtypeCd(String tourPkgtypeCd) {
		this.tourPkgtypeCd = tourPkgtypeCd;
	}
	
	
	
}
