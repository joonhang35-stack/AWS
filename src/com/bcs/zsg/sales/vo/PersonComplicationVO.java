package com.bcs.zsg.sales.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class PersonComplicationVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long personId;
	private String complicationCd;
	
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
	 * @return the complicationCd
	 */
	public String getComplicationCd() {
		return complicationCd;
	}

	/**
	 * @param complicationCd the complicationCd to set
	 */
	public void setComplicationCd(String complicationCd) {
		this.complicationCd = complicationCd;
	}
	
}
