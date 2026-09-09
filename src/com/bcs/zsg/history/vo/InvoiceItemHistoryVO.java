package com.bcs.zsg.history.vo;

import com.bcs.zsg.sales.vo.InvoiceItemVO;

public class InvoiceItemHistoryVO extends InvoiceItemVO {

	private static final long serialVersionUID = 1L;

	private Long idHist;
	private Long idRef;

	/**
	 * @return the idHist
	 */
	public Long getIdHist() {
		return idHist;
	}

	/**
	 * @param idHist the idHist to set
	 */
	public void setIdHist(Long idHist) {
		this.idHist = idHist;
	}

	/**
	 * @return the idRef
	 */
	public Long getIdRef() {
		return idRef;
	}

	/**
	 * @param idRef the idRef to set
	 */
	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}
}
