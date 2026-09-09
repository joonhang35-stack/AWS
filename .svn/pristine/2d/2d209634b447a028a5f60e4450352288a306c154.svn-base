package com.bcs.zsg.sales.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.dao.SalesSmmryDAO;
import com.bcs.zsg.sales.vo.SalesSmmryVO;

@Service
public class SalesSmmryServiceImpl implements SalesSmmryService {

	@Autowired
	private SalesSmmryDAO salesSmmryDAO;

	@Override
	public int getStaffSalesSmmryInvListSize(Map<String, Object> params) throws BusinessException {
		return salesSmmryDAO.getStaffSalesSmmryInvListSize(params);
	}

	@Override
	public List<SalesSmmryVO> getStaffSalesSmmryInvList(Map<String, Object> params) throws BusinessException {
		return salesSmmryDAO.getStaffSalesSmmryInvList(params);
	}
	
	@Override
	public List<SalesSmmryVO> getStaffSalesSmmryInvDetailList(Long idCompany, Long salerId, SearchParamVO searchParamVO) throws BusinessException {
		return salesSmmryDAO.getStaffSalesSmmryInvDetailList(idCompany, salerId, searchParamVO);
	}
	
	@Override
	public int getStaffSalesSmmryBookingListSize(Map<String, Object> params) throws BusinessException {
		return salesSmmryDAO.getStaffSalesSmmryBookingListSize(params);
	}

	@Override
	public List<SalesSmmryVO> getStaffSalesSmmryBookingList(Map<String, Object> params) throws BusinessException {
		return salesSmmryDAO.getStaffSalesSmmryBookingList(params);
	}
	
	@Override
	public List<SalesSmmryVO> getStaffSalesSmmryBookingDetailList(Long idCompany, Long salerId, SearchParamVO searchParamVO) throws BusinessException {
		return salesSmmryDAO.getStaffSalesSmmryBookingDetailList(idCompany, salerId, searchParamVO);
	}
	
	@Override
	public int getRegionSalesSmmryInvListSize(Map<String, Object> params) throws BusinessException {
		return salesSmmryDAO.getRegionSalesSmmryInvListSize(params);
	}

	@Override
	public List<SalesSmmryVO> getRegionSalesSmmryInvList(Map<String, Object> params) throws BusinessException {
		return salesSmmryDAO.getRegionSalesSmmryInvList(params);
	}
	
	@Override
	public List<SalesSmmryVO> getRegionSalesSmmryInvDetailList(Long idCompany, Long regionId, SearchParamVO searchParamVO) throws BusinessException {
		return salesSmmryDAO.getRegionSalesSmmryInvDetailList(idCompany, regionId, searchParamVO);
	}
	
	@Override
	public int getRegionSalesSmmryBookingListSize(Map<String, Object> params) throws BusinessException {
		return salesSmmryDAO.getRegionSalesSmmryBookingListSize(params);
	}

	@Override
	public List<SalesSmmryVO> getRegionSalesSmmryBookingList(Map<String, Object> params) throws BusinessException {
		return salesSmmryDAO.getRegionSalesSmmryBookingList(params);
	}
	
	@Override
	public List<SalesSmmryVO> getRegionSalesSmmryBookingDetailList(Long idCompany, Long regionId, SearchParamVO searchParamVO) throws BusinessException {
		return salesSmmryDAO.getRegionSalesSmmryBookingDetailList(idCompany, regionId, searchParamVO);
	}
	
	@Override
	public List<SalesSmmryVO> getSalesSmmryByMonthsList(Long idCompany) throws BusinessException {
		return salesSmmryDAO.getSalesSmmryByMonthsList(idCompany);
	}
}
