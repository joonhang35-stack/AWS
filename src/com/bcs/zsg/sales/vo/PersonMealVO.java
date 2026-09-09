package com.bcs.zsg.sales.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class PersonMealVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long personId;
	private String mealCd;
	
	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}
	
	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * @return the mealCd
	 */
	public String getMealCd() {
		return mealCd;
	}

	/**
	 * @param mealCd the mealCd to set
	 */
	public void setMealCd(String mealCd) {
		this.mealCd = mealCd;
	}

}
