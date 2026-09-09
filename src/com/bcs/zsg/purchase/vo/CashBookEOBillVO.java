package com.bcs.zsg.purchase.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class CashBookEOBillVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	
	private Long idCashBook;
	private Long idEOBill;
	private Double amount;
	private Double amountPaid;

	/**
	 * @return the idCashBook
	 */
	public Long getIdCashBook() {
		return idCashBook;
	}

	/**
	 * @param idCashBook the idCashBook to set
	 */
	public void setIdCashBook(Long idCashBook) {
		this.idCashBook = idCashBook;
	}

	/**
	 * @return the idEOBill
	 */
	public Long getIdEOBill() {
		return idEOBill;
	}

	/**
	 * @param idEOBill the idEOBill to set
	 */
	public void setIdEOBill(Long idEOBill) {
		this.idEOBill = idEOBill;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the amountPaid
	 */
	public Double getAmountPaid() {
		return amountPaid;
	}

	/**
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}
}
