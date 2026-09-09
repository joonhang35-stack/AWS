package com.bcs.zsg.sales.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.service.SalesSmmryService;
import com.bcs.zsg.sales.vo.SalesSmmryVO;


public class SalesSmmryBOImpl implements SalesSmmryBO {
	
	@Autowired SalesSmmryService salesSmmryService;

	@Override
	public int getStaffSalesSmmryInvListSize(Map<String, Object> params) throws BusinessException {
		return salesSmmryService.getStaffSalesSmmryInvListSize(params);
	}

	@Override
	public List<SalesSmmryVO> getStaffSalesSmmryInvList(Map<String, Object> params) throws BusinessException {
		return salesSmmryService.getStaffSalesSmmryInvList(params);
	}
	
	@Override
	public List<SalesSmmryVO> getStaffSalesSmmryInvDetailList(Long idCompany, Long salerId, SearchParamVO searchParamVO) throws BusinessException {
		return salesSmmryService.getStaffSalesSmmryInvDetailList(idCompany, salerId, searchParamVO);
	}
	
	@Override
	public int getStaffSalesSmmryBookingListSize(Map<String, Object> params) throws BusinessException {
		return salesSmmryService.getStaffSalesSmmryBookingListSize(params);
	}

	@Override
	public List<SalesSmmryVO> getStaffSalesSmmryBookingList(Map<String, Object> params) throws BusinessException {
		return salesSmmryService.getStaffSalesSmmryBookingList(params);
	}
	
	@Override
	public List<SalesSmmryVO> getStaffSalesSmmryBookingDetailList(Long idCompany, Long salerId, SearchParamVO searchParamVO) throws BusinessException {
		return salesSmmryService.getStaffSalesSmmryBookingDetailList(idCompany, salerId, searchParamVO);
	}
	
	@Override
	public int getRegionSalesSmmryInvListSize(Map<String, Object> params) throws BusinessException {
		return salesSmmryService.getRegionSalesSmmryInvListSize(params);
	}

	@Override
	public List<SalesSmmryVO> getRegionSalesSmmryInvList(Map<String, Object> params) throws BusinessException {
		return salesSmmryService.getRegionSalesSmmryInvList(params);
	}
	
	@Override
	public List<SalesSmmryVO> getRegionSalesSmmryInvDetailList(Long idCompany, Long regionId, SearchParamVO searchParamVO) throws BusinessException {
		return salesSmmryService.getRegionSalesSmmryInvDetailList(idCompany, regionId, searchParamVO);
	}
	
	@Override
	public int getRegionSalesSmmryBookingListSize(Map<String, Object> params) throws BusinessException {
		return salesSmmryService.getRegionSalesSmmryBookingListSize(params);
	}

	@Override
	public List<SalesSmmryVO> getRegionSalesSmmryBookingList(Map<String, Object> params) throws BusinessException {
		return salesSmmryService.getRegionSalesSmmryBookingList(params);
	}
	
	@Override
	public List<SalesSmmryVO> getRegionSalesSmmryBookingDetailList(Long idCompany, Long regionId, SearchParamVO searchParamVO) throws BusinessException {
		return salesSmmryService.getRegionSalesSmmryBookingDetailList(idCompany, regionId, searchParamVO);
	}
	
	@Override
	public List<SalesSmmryVO> getSalesSmmryByMonthsList(Long idCompany) throws BusinessException {
		return salesSmmryService.getSalesSmmryByMonthsList(idCompany);
	}
}
