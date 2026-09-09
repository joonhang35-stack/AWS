package com.bcs.zsg.purchase.vo;

import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.maintenance.vo.CityVO;

public class CountryVO  extends BaseVO {
	private static final long serialVersionUID = 1L;
	
	private String country;
	private String code;
	private String threeLetterCode;
	private Long regionId;
	private String image;
	private boolean isActualCountry; // country is real world country or not, to exclude business structure

	private boolean isOnline;
	//for temporary storage purpose
	private String path;
	private String prevImage;
	private String regionname;
	private String countryCd;
	
	private CityVO cityVO;
	
	public String getThreeLetterCode() {
		return threeLetterCode;
	}
	public void setThreeLetterCode(String threeLetterCode) {
		this.threeLetterCode = threeLetterCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public boolean getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPrevImage() {
		return prevImage;
	}
	public void setPrevImage(String prevImage) {
		this.prevImage = prevImage;
	}
	/**
	 * @return the regionname
	 */
	public String getRegionname() {
		return regionname;
	}
	/**
	 * @param regionname the regionname to set
	 */
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	public CityVO getCityVO() {
		return cityVO;
	}
	public void setCityVO(CityVO cityVO) {
		this.cityVO = cityVO;
	}
	public boolean getIsActualCountry() {
		return isActualCountry;
	}
	public void setIsActualCountry(boolean isActualCountry) {
		this.isActualCountry = isActualCountry;
	}
	public String getCountryCd() {
		return countryCd;
	}
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	

}
