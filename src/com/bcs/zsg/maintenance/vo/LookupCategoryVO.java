package com.bcs.zsg.maintenance.vo;


import com.bcs.zsg.core.vo.BaseVO;

public class LookupCategoryVO extends BaseVO {
	
	private static final long serialVersionUID = 1L;

	private String code;
	private  String description;
	private String remarks;
	
	
	//private Set<LookupItemViewVO> lookupItemOMList;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**public Set<LookupItemViewVO> getLookupItemOMList() {
		return lookupItemOMList;
	}

	public void setLookupItemOMList(Set<LookupItemViewVO> lookupItemOMList) {
		this.lookupItemOMList = lookupItemOMList;
	}
	**/
		
	
	
}
