package com.bcs.zsg.product.vo;

import java.util.List;

import com.bcs.zsg.core.vo.BaseVO;

public class CampaignCountryVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	// system properties
	private Long idCampaign;
	private String name;
	private Integer seq;
	
	// other properties
	private List<CampaignCountryPackageVO> campaignCountryPackageList;

	public List<CampaignCountryPackageVO> getCampaignCountryPackageList() {
		return campaignCountryPackageList;
	}

	public void setCampaignCountryPackageList(List<CampaignCountryPackageVO> campaignCountryPackageList) {
		this.campaignCountryPackageList = campaignCountryPackageList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Long getIdCampaign() {
		return idCampaign;
	}

	public void setIdCampaign(Long idCampaign) {
		this.idCampaign = idCampaign;
	}
}
