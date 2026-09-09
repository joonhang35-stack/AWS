package com.bcs.zsg.product.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class TourPackageTagVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idTourPkg;
	private String description;
	
	public Long getIdTourPkg() {
		return idTourPkg;
	}
	
	public void setIdTourPkg(Long idTourPkg) {
		this.idTourPkg = idTourPkg;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
