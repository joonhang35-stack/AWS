package com.bcs.zsg.product.bo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.CampaignCountryPackageVO;
import com.bcs.zsg.product.vo.CampaignCountryVO;
import com.bcs.zsg.product.vo.CampaignVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;

public interface CampaignBO {

	public CampaignVO addCampaign(CampaignVO campaignVO, Long idCompany) throws BusinessException, QueueException;

	public CampaignVO updCampaign(CampaignVO campaignVO, Long idCompany) throws BusinessException;

	public List<CampaignCountryVO> getCampaignCountryList(Long idCampaign) throws BusinessException;

	public CampaignVO getCampaignDetails(CampaignVO campaignVO) throws BusinessException;

	public List<CampaignCountryPackageVO> getCampaignCountryPkgList(Long idCampaignCountry) throws BusinessException;

	public int getTourPkgListSize(Map<String, Object> params) throws BusinessException;

	public List<TourPackageVO> getTourPkgList(Map<String, Object> params) throws BusinessException;

	public void delCampaign(CampaignVO campaignVO) throws BusinessException;

	public List<CampaignVO> getCampaignList(Long idCompany) throws BusinessException;

	public List<CampaignVO> getCampaignList(Long idCompany, Date dtFrom, Date dtTo) throws BusinessException;
}
