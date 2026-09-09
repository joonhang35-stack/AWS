package com.bcs.zsg.maintenance.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class CityVO extends BaseVO{
	private static final long serialVersionUID = 1L;
	
	private Long idCountry;
	private String code;
	private String name;
	
	private String countryName;
	
	public Long getIdCountry() {
		return idCountry;
	}
	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
