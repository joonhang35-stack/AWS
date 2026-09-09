package com.bcs.zsg.product.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.CampaignCountryPackageVO;
import com.bcs.zsg.product.vo.CampaignCountryVO;
import com.bcs.zsg.product.vo.CampaignVO;
import com.bcs.zsg.product.vo.TourPackageVO;

public interface CampaignDAO extends BaseDAO {

	public List<CampaignCountryVO> getCampaignCountryList(Long idCampaign) throws BusinessException;

	public void terminateCampaignCountry(Long idCampaign) throws BusinessException;

	public void terminateCampaignCountryPkg(Long idCampaign) throws BusinessException;

	public List<CampaignCountryPackageVO> getCampaignCountryPkgList(Long idCampaignCountry) throws BusinessException;

	public int getTourPkgListSize(Map<String, Object> params) throws BusinessException;

	public List<TourPackageVO> getTourPkgList(Map<String, Object> params) throws BusinessException;

	public List<CampaignVO> getCampaignList(Long idCompany) throws BusinessException;

	public List<CampaignVO> getCampaignList(Long idCompany, Date dtFrom, Date dtTo) throws BusinessException;
	
}
