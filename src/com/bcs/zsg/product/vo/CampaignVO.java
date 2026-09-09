package com.bcs.zsg.product.vo;

import java.util.Date;
import java.util.List;

import com.bcs.zsg.core.vo.BaseVO;

public class CampaignVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	// database properties
	private Long idCompany;
	private String name;
	private String campaignStatus;
	private Date startDt;
	private Date endDt;
	private String startTime;
	private String endTime;
	private String code;
	private String description;
	private String tourType;
	
	// others
	private List<CampaignCountryVO> campaignCountryList;

	public List<CampaignCountryVO> getCampaignCountryList() {
		return campaignCountryList;
	}

	public void setCampaignCountryList(List<CampaignCountryVO> campaignCountryList) {
		this.campaignCountryList = campaignCountryList;
	}

	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCampaignStatus() {
		return campaignStatus;
	}

	public void setCampaignStatus(String campaignStatus) {
		this.campaignStatus = campaignStatus;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public String getTourType() {
		return tourType;
	}

	public void setTourType(String tourType) {
		this.tourType = tourType;
	}

}
