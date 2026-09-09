package com.bcs.zsg.maintenance.vo;



public class LookupItemViewVO extends LookupItemVO {
	

	private static final long serialVersionUID = 1L;

	private LookupCategoryVO lookupCategoryVO;

	
	//view purpose
	
	private String tempDescription;
	
	public LookupCategoryVO getLookupCategoryVO() {
		return lookupCategoryVO;
	}

	public void setLookupCategoryVO(LookupCategoryVO lookupCategoryVO) {
		this.lookupCategoryVO = lookupCategoryVO;
	}

	public String getTempDescription() {
		return tempDescription;
	}

	public void setTempDescription(String tempDescription) {
		this.tempDescription = tempDescription;
	}
	
}
