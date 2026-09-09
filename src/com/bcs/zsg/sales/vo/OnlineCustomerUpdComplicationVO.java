package com.bcs.zsg.sales.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class OnlineCustomerUpdComplicationVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long idOnlineCustProfileUpd;
    private String complicationCd;
    
	public Long getIdOnlineCustProfileUpd() {
		return idOnlineCustProfileUpd;
	}
	public void setIdOnlineCustProfileUpd(Long idOnlineCustProfileUpd) {
		this.idOnlineCustProfileUpd = idOnlineCustProfileUpd;
	}
	public String getComplicationCd() {
		return complicationCd;
	}
	public void setComplicationCd(String complicationCd) {
		this.complicationCd = complicationCd;
	}

}
