package com.bcs.zsg.product.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.product.dao.CampaignDAO;
import com.bcs.zsg.product.vo.CampaignCountryPackageVO;
import com.bcs.zsg.product.vo.CampaignCountryVO;
import com.bcs.zsg.product.vo.CampaignVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;

public class CampaignServiceImpl implements CampaignService {
	
	@Autowired
	private CampaignDAO campaignDAO;
	
	@Override
	public List<CampaignVO> getCampaignList(Long idCompany) throws BusinessException {
		return campaignDAO.getCampaignList(idCompany);
	}
	
	@Override
	public List<CampaignVO> getCampaignList(Long idCompany, Date dtFrom, Date dtTo) throws BusinessException {
		return campaignDAO.getCampaignList(idCompany, dtFrom, dtTo);
	}
	
	@Override
	public CampaignVO addCampaign(CampaignVO campaignVO, Long idCompany) throws BusinessException, QueueException {
		campaignVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
		
//		SystemNumberGenerationVO sVo = new SystemNumberGenerationVO();
//		sVo.setCode(CommonConstant.SYS_NUM_CD_CAMPAIGN);
		campaignVO.setIdCompany(idCompany);
//		sVo = SysNumGenUtil.getSysNumber(SysNumGenUtil.getIdx(sVo));
//		Long code = sVo.getNextnumber();
//		campaignVO.setCode(sVo.getPrefixid() + code.toString());
		campaignDAO.insert(campaignVO);
//		if (campaignVO.getSelectedTourList() != null) {
//			for (TourDepartureVO vo : campaignVO.getSelectedTourList()) {
//				campaignDAO.updateTourDepCampaignId(vo.getId(), campaignVO.getId());
//			}
//		}
//		if (campaignVO.getTourDepDiscountList() != null) {
//			for (CampaignTourDepDiscountVO vo : campaignVO.getTourDepDiscountList()) {
//				vo.setIdCampaign(campaignVO.getId());
//				campaignDAO.insert(vo, getUserInfo());
//			}
//		}
//		
//		campaignDAO.updateCampaignTourDepDiscountLevel(campaignVO.getId());
//		campaignDAO.updatePopulateCampaignTourDepDiscount(campaignVO.getId());
		
		return campaignVO;
	}

	@Override
	public CampaignVO updCampaign(CampaignVO campaignVO, Long idCompany) throws BusinessException {
		campaignDAO.update(campaignVO);
		
		terminateSubInfo(campaignVO.getId());
		
		if (CollectionUtils.isNotEmpty(campaignVO.getCampaignCountryList())) {
			for (CampaignCountryVO campaignCountryVO : campaignVO.getCampaignCountryList()) {
				if (campaignCountryVO.getId() != null) {
					campaignDAO.update(campaignCountryVO);
				} else {
					campaignCountryVO.setIdCampaign(campaignVO.getId());
					campaignCountryVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
					campaignDAO.insert(campaignCountryVO);
				}
				
				if(CollectionUtils.isNotEmpty(campaignCountryVO.getCampaignCountryPackageList())) {
					for (CampaignCountryPackageVO pkgVO : campaignCountryVO.getCampaignCountryPackageList()) {
						if (pkgVO.getId() != null) {
							campaignDAO.update(pkgVO);
						} else {
							pkgVO.setIdCampaignCountry(campaignCountryVO.getId());
							pkgVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
							campaignDAO.insert(pkgVO);
						}
					}
				}
			}
		}
		
		return campaignVO;
	}
	
	@Override
	public void delCampaign(CampaignVO campaignVO) throws BusinessException {
		terminateSubInfo(campaignVO.getId());
		
		campaignVO.setStatusCode(BaseConstant.STATUS_DELETED);
		campaignDAO.update(campaignVO);
	}
	
	private void terminateSubInfo(Long idCampaign) throws BusinessException {
		campaignDAO.terminateCampaignCountry(idCampaign);
		campaignDAO.terminateCampaignCountryPkg(idCampaign);
	}
	
	@Override
	public CampaignVO getCampaignDetails(CampaignVO campaignVO) throws BusinessException {
		campaignVO.setCampaignCountryList(getCampaignCountryList(campaignVO.getId()));
		
		if (CollectionUtils.isNotEmpty(campaignVO.getCampaignCountryList())) {
			for (CampaignCountryVO vo : campaignVO.getCampaignCountryList()) {
				vo.setCampaignCountryPackageList(getCampaignCountryPkgList(vo.getId()));
			}
		}
		return campaignVO;
	}
	
	@Override
	public List<CampaignCountryVO> getCampaignCountryList(Long idCampaign) throws BusinessException {
		return campaignDAO.getCampaignCountryList(idCampaign);
	}
	
	@Override
	public List<CampaignCountryPackageVO> getCampaignCountryPkgList(Long idCampaignCountry) throws BusinessException {
		return campaignDAO.getCampaignCountryPkgList(idCampaignCountry);
	}
	
	@Override
	public int getTourPkgListSize(Map<String, Object> params) throws BusinessException {
		return campaignDAO.getTourPkgListSize(params);
	}

	@Override
	public List<TourPackageVO> getTourPkgList(Map<String, Object> params) throws BusinessException {
		return campaignDAO.getTourPkgList(params);
	}
}