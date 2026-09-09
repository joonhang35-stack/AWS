package com.bcs.zsg.sales.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class PersonLangVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long personId;
	private String langCd;
	
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
	 * @return the langCd
	 */
	public String getLangCd() {
		return langCd;
	}
	
	/**
	 * @param langCd the langCd to set
	 */
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	
}
