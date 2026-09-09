package com.bcs.zsg.product.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class TourPackageAttributeVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idTourPkg;
	private String tagType;
	private String code;
	private String description;

		
	public Long getIdTourPkg() {
		return idTourPkg;
	}
	
	public void setIdTourPkg(Long idTourPkg) {
		this.idTourPkg = idTourPkg;
	}
		
	public String getTagType() {
		return tagType;
	}
	
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	
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
	
}