package com.bcs.zsg.maintenance.vo;


import com.bcs.zsg.core.vo.BaseVO;

public class SubRegionVO extends BaseVO {
	
	private static final long serialVersionUID = 1L;

	private Long regionid;
	private String subregioncode;
	private  String subregionname;
	
	public Long getRegionid() {
		return regionid;
	}

	public void setRegionid(Long regionid) {
		this.regionid = regionid;
	}

	public String getSubregioncode() {
		return subregioncode;
	}

	public void setSubregioncode(String subregioncode) {
		this.subregioncode = subregioncode;
	}

	public String getSubregionname() {
		return subregionname;
	}

	public void setSubregionname(String subregionname) {
		this.subregionname = subregionname;
	}

}
