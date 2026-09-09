package com.bcs.zsg.sales.vo;

import java.util.Date;

public class SalesListingVO extends InvoiceVO{
	private static final long serialVersionUID = 1L;
	
	private Integer paxCount;
	private String deptCode;
	private String deptDesc;
	private Long idTourCat;
	private String tourCatDesc;
	private String orderCd;
	private String country;
	private String tourPkgName;
	private String catCd;
	private String airlineCd;
	private String bookingStatus;
	private String issuedByDeptDesc;
	private String contra;
	private Integer invPax;
	private Integer bookingPax;
	private String notTallyPax = "N";
	
	private String subPsNo;
	private Date subPsDt;
	private String psNo;
	private Date psDt;
	private String parentPsNo;
	private Date parentPsDt;
	private Date psDue;
	private String psCNNo;
	private Date psCNDt;
	
	private Date eInvoiceRnDtIssued; 

	public Integer getPaxCount() {
		return paxCount;
	}

	public void setPaxCount(Integer paxCount) {
		this.paxCount = paxCount;
	}

	public Integer getInvPax() {
		return invPax;
	}

	public void setInvPax(Integer invPax) {
		this.invPax = invPax;
	}
	
	public Integer getBookingPax() {
		return bookingPax;
	}

	public void setBookingPax(Integer bookingPax) {
		this.bookingPax = bookingPax;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public Long getIdTourCat() {
		return idTourCat;
	}

	public void setIdTourCat(Long idTourCat) {
		this.idTourCat = idTourCat;
	}

	public String getTourCatDesc() {
		return tourCatDesc;
	}

	public void setTourCatDesc(String tourCatDesc) {
		this.tourCatDesc = tourCatDesc;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTourPkgName() {
		return tourPkgName;
	}

	public void setTourPkgName(String tourPkgName) {
		this.tourPkgName = tourPkgName;
	}

	public String getCatCd() {
		return catCd;
	}

	public void setCatCd(String catCd) {
		this.catCd = catCd;
	}

	public String getAirlineCd() {
		return airlineCd;
	}

	public void setAirlineCd(String airlineCd) {
		this.airlineCd = airlineCd;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getOrderCd() {
		return orderCd;
	}

	public void setOrderCd(String orderCd) {
		this.orderCd = orderCd;
	}

	public String getIssuedByDeptDesc() {
		return issuedByDeptDesc;
	}

	public void setIssuedByDeptDesc(String issuedByDeptDesc) {
		this.issuedByDeptDesc = issuedByDeptDesc;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public String getNotTallyPax() {
		return notTallyPax;
	}

	public void setNotTallyPax(String notTallyPax) {
		this.notTallyPax = notTallyPax;
	}

	public String getSubPsNo() {
		return subPsNo;
	}

	public void setSubPsNo(String subPsNo) {
		this.subPsNo = subPsNo;
	}

	public Date getSubPsDt() {
		return subPsDt;
	}

	public void setSubPsDt(Date subPsDt) {
		this.subPsDt = subPsDt;
	}

	public String getPsNo() {
		return psNo;
	}

	public void setPsNo(String psNo) {
		this.psNo = psNo;
	}

	public Date getPsDt() {
		return psDt;
	}

	public void setPsDt(Date psDt) {
		this.psDt = psDt;
	}

	public Date getPsDue() {
		return psDue;
	}

	public void setPsDue(Date psDue) {
		this.psDue = psDue;
	}

	public String getPsCNNo() {
		return psCNNo;
	}

	public void setPsCNNo(String psCNNo) {
		this.psCNNo = psCNNo;
	}

	public Date getPsCNDt() {
		return psCNDt;
	}

	public void setPsCNDt(Date psCNDt) {
		this.psCNDt = psCNDt;
	}

	public String getParentPsNo() {
		return parentPsNo;
	}

	public void setParentPsNo(String parentPsNo) {
		this.parentPsNo = parentPsNo;
	}

	public Date getParentPsDt() {
		return parentPsDt;
	}

	public void setParentPsDt(Date parentPsDt) {
		this.parentPsDt = parentPsDt;
	}

	public Date geteInvoiceRnDtIssued() {
		return eInvoiceRnDtIssued;
	}

	public void seteInvoiceRnDtIssued(Date eInvoiceRnDtIssued) {
		this.eInvoiceRnDtIssued = eInvoiceRnDtIssued;
	}
	
}
