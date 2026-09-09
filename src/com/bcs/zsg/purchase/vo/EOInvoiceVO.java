package com.bcs.zsg.purchase.vo; 

import java.util.Date;
import com.bcs.zsg.core.vo.BaseVO;

public class EOInvoiceVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long eoId;
	private Long invId; 
	private String invDate;  
	private Date invDateView;  

	private String code;
	private String refCode;
	private String docTypeCd;
	private String customerID; 
	private String customerName; 
	private double salesAmount; 

	public String getDocTypeCd() {
		return docTypeCd;
	}
	public void setDocTypeCd(String docTypeCd) {
		this.docTypeCd = docTypeCd;
	}
	public String getInvDate() {
		return invDate;
	} 
	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}
	

	public String getCustomerID() {
		return customerID;
	} 
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	

	public String getCustomerName() {
		return customerName;
	} 
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	

	public double getSalesAmount() {
		return salesAmount;
	} 
	public void setSalesAmount(double salesAmount) {
		this.salesAmount = salesAmount;
	}
	
	/**
	 * @return the eoId
	 */
	public Long getEoId() {
		return eoId;
	}
	/**
	 * @param eoID the companyId to set
	 */
	public void setEoId(Long eoId) {
		this.eoId = eoId;
	}
	/**
	 * @return the invId
	 */
	public Long getInvId() {
		return invId;
	}
	/**
	 * @param InvId the InvId to set
	 */
	public void setInvId(Long invId) {
		this.invId = invId;
	}

	public Date getInvDateView() {
		return invDateView;
	}
	/**
	 * @param InvId the InvId to set
	 */
	public void setInvDateView(Date invDateView) {
		this.invDateView = invDateView;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRefCode() {
		return refCode;
	}
	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}
	

}
