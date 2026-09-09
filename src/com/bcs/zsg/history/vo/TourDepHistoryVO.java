package com.bcs.zsg.history.vo;

import com.bcs.zsg.product.vo.TourDepartureVO;

public class TourDepHistoryVO extends TourDepartureVO {

	private static final long serialVersionUID = 1L;

	private Long idHist;
	private String actionCd;

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
	 * @return the actionCd
	 */
	public String getActionCd() {
		return actionCd;
	}

	/**
	 * @param actionCd the actionCd to set
	 */
	public void setActionCd(String actionCd) {
		this.actionCd = actionCd;
	}
}
