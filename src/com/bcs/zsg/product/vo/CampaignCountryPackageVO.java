package com.bcs.zsg.product.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class CampaignCountryPackageVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	// system properties
	private Long idCampaignCountry;
	private Long idTourPkg;
	
	private Integer seq;
	// other properties
	private TourPackageVO tourPkgVO;

	public TourPackageVO getTourPkgVO() {
		return tourPkgVO;
	}

	public void setTourPkgVO(TourPackageVO tourPkgVO) {
		this.tourPkgVO = tourPkgVO;
	}

	public Long getIdCampaignCountry() {
		return idCampaignCountry;
	}

	public void setIdCampaignCountry(Long idCampaignCountry) {
		this.idCampaignCountry = idCampaignCountry;
	}

	public Long getIdTourPkg() {
		return idTourPkg;
	}

	public void setIdTourPkg(Long idTourPkg) {
		this.idTourPkg = idTourPkg;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

}
