package com.bcs.zsg.purchase.vo; 

import java.util.Date;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class EOInvoiceViewVO extends BaseVO{
	private static final long serialVersionUID = 1L;

	private Long eoId;
	private Long invId; 

	private Date invDate; 
	private InvoiceVO invoiceVO; 
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



 

	/**
	 * @return the eoId
	 */
	public Date getInvDate() {
		return invDate;
	}
	/**
	 * @param eoID the companyId to set
	 */
	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}
}
