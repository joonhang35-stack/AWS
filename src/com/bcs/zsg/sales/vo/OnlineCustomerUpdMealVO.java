package com.bcs.zsg.sales.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class OnlineCustomerUpdMealVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long idOnlineCustProfileUpd;
    private String mealCd;
    
	public Long getIdOnlineCustProfileUpd() {
		return idOnlineCustProfileUpd;
	}
	public void setIdOnlineCustProfileUpd(Long idOnlineCustProfileUpd) {
		this.idOnlineCustProfileUpd = idOnlineCustProfileUpd;
	}
	public String getMealCd() {
		return mealCd;
	}
	public void setMealCd(String mealCd) {
		this.mealCd = mealCd;
	}
}
