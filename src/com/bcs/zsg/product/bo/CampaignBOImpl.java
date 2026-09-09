package com.bcs.zsg.product.bo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.CampaignService;
import com.bcs.zsg.product.vo.CampaignCountryPackageVO;
import com.bcs.zsg.product.vo.CampaignCountryVO;
import com.bcs.zsg.product.vo.CampaignVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;

public class CampaignBOImpl implements CampaignBO {
	
	@Autowired
	private CampaignService campaignService;

	@Override
	public List<CampaignVO> getCampaignList(Long idCompany) throws BusinessException {
		return campaignService.getCampaignList(idCompany);
	}
	
	@Override
	public List<CampaignVO> getCampaignList(Long idCompany, Date dtFrom, Date dtTo) throws BusinessException {
		return campaignService.getCampaignList(idCompany, dtFrom, dtTo);
	}
	
	@Override
	public CampaignVO addCampaign(CampaignVO campaignVO, Long idCompany) throws BusinessException, QueueException {
		return campaignService.addCampaign(campaignVO, idCompany);
	}
	
	@Override
	public CampaignVO updCampaign(CampaignVO campaignVO, Long idCompany) throws BusinessException {
		return campaignService.updCampaign(campaignVO, idCompany);
	}
	
	@Override
	public List<CampaignCountryVO> getCampaignCountryList(Long idCampaign) throws BusinessException {
		return campaignService.getCampaignCountryList(idCampaign);
	}
	
	@Override
	public CampaignVO getCampaignDetails(CampaignVO campaignVO) throws BusinessException {
		return campaignService.getCampaignDetails(campaignVO);
	}
	
	@Override
	public List<CampaignCountryPackageVO> getCampaignCountryPkgList(Long idCampaignCountry) throws BusinessException {
		return campaignService.getCampaignCountryPkgList(idCampaignCountry);
	}
	
	@Override
	public int getTourPkgListSize(Map<String, Object> params) throws BusinessException {
		return campaignService.getTourPkgListSize(params);
	}

	@Override
	public List<TourPackageVO> getTourPkgList(Map<String, Object> params) throws BusinessException {
		return campaignService.getTourPkgList(params);
	}
	
	@Override
	public void delCampaign(CampaignVO campaignVO) throws BusinessException {
		campaignService.delCampaign(campaignVO);
	}
}
