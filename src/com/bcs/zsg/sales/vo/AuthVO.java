package com.bcs.zsg.sales.vo;

import com.bcs.zsg.core.vo.BaseVO;
public class AuthVO extends BaseVO{
	private static final long serialVersionUID = 1L;
	

	private Boolean billPmntDel;

	/**
	 * @return the billPmntDel
	 */
	public Boolean getBillPmntDel() {
		return billPmntDel;
	}
	/**
	 * @param billPmntDel the billPmntDel to set
	 */
	public void setBillPmntDel(Boolean billPmntDel) {
		this.billPmntDel = billPmntDel;
	}

}
