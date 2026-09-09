package com.bcs.zsg.product.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class TourThemeCountryVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idTourTheme;
	private Long idCountry;
	private String countryName;
	private String imageUrl; // For API

	public Long getIdTourTheme() {
		return idTourTheme;
	}

	public void setIdTourTheme(Long idTourTheme) {
		this.idTourTheme = idTourTheme;
	}

	public Long getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
