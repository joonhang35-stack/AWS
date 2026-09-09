package com.bcs.zsg.sales.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class SalesSmmryVO extends BaseVO{
	private static final long serialVersionUID = 1L;
	
	// Staff - Inv
	private Integer rowNumber;
	private Long salerId;
	private String salerName; // Saler name
	private Integer invCount;
	private Double invAmt;
	private Double invPaid;
	private Double balance;
	
	// Details
	private String code;
	private Date invoiceDt;
	private Date departureDt;
	private Long customerId;
	private Long tourDepId;
	private String tourCd; // Tour code
	private String typeCd;
	private String custName;
	private String custSalutation;
	private String coName;
	
	// Staff - Booking
	private Integer kiv;
	private Integer depPaid;
	private Integer paidinfull;
	private Integer expired;
	private Integer cancelled;
	
	private Long idCompany;
	private String pmntStatusCd;
	private Date dtExp;
	private String tourCode;
	private String SalutationCd;
	private String LastName;
	private String FirstName;
	
	// Region - Inv
	private Long regionId;
	private String regionName;
	
	// By Months
	private String years;
	private String months;
	
	public Integer getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}
	public Long getSalerId() {
		return salerId;
	}
	public void setSalerId(Long salerId) {
		this.salerId = salerId;
	}
	public String getSalerName() {
		return salerName;
	}
	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}
	public Integer getInvCount() {
		return invCount;
	}
	public void setInvCount(Integer invCount) {
		this.invCount = invCount;
	}
	public Double getInvAmt() {
		return invAmt;
	}
	public void setInvAmt(Double invAmt) {
		this.invAmt = invAmt;
	}
	public Double getInvPaid() {
		return invPaid;
	}
	public void setInvPaid(Double invPaid) {
		this.invPaid = invPaid;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getInvoiceDt() {
		return invoiceDt;
	}
	public void setInvoiceDt(Date invoiceDt) {
		this.invoiceDt = invoiceDt;
	}
	public Date getDepartureDt() {
		return departureDt;
	}
	public void setDepartureDt(Date departureDt) {
		this.departureDt = departureDt;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getTourDepId() {
		return tourDepId;
	}
	public void setTourDepId(Long tourDepId) {
		this.tourDepId = tourDepId;
	}
	public String getTourCd() {
		return tourCd;
	}
	public void setTourCd(String tourCd) {
		this.tourCd = tourCd;
	}
	public String getTypeCd() {
		return typeCd;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustSalutation() {
		return custSalutation;
	}
	public void setCustSalutation(String custSalutation) {
		this.custSalutation = custSalutation;
	}
	public String getCoName() {
		return coName;
	}
	public Integer getKiv() {
		return kiv;
	}
	public void setKiv(Integer kiv) {
		this.kiv = kiv;
	}
	public Integer getDepPaid() {
		return depPaid;
	}
	public void setDepPaid(Integer depPaid) {
		this.depPaid = depPaid;
	}
	public Integer getPaidinfull() {
		return paidinfull;
	}
	public void setPaidinfull(Integer paidinfull) {
		this.paidinfull = paidinfull;
	}
	public Integer getExpired() {
		return expired;
	}
	public void setExpired(Integer expired) {
		this.expired = expired;
	}
	public Integer getCancelled() {
		return cancelled;
	}
	public void setCancelled(Integer cancelled) {
		this.cancelled = cancelled;
	}
	public void setCoName(String coName) {
		this.coName = coName;
	}
	public Long getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}
	public String getPmntStatusCd() {
		return pmntStatusCd;
	}
	public void setPmntStatusCd(String pmntStatusCd) {
		this.pmntStatusCd = pmntStatusCd;
	}
	public Date getDtExp() {
		return dtExp;
	}
	public void setDtExp(Date dtExp) {
		this.dtExp = dtExp;
	}
	public String getTourCode() {
		return tourCode;
	}
	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}
	public String getSalutationCd() {
		return SalutationCd;
	}
	public void setSalutationCd(String salutationCd) {
		SalutationCd = salutationCd;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
}
