package com.bcs.zsg.sales.bo;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.SalesSmmryVO;

public interface SalesSmmryBO {

	public int getStaffSalesSmmryInvListSize(Map<String, Object> params) throws BusinessException;

	public List<SalesSmmryVO> getStaffSalesSmmryInvList(Map<String, Object> params) throws BusinessException;
	
	public List<SalesSmmryVO> getStaffSalesSmmryInvDetailList(Long idCompany, Long salerId, SearchParamVO searchParamVO) throws BusinessException;
	
	public int getStaffSalesSmmryBookingListSize(Map<String, Object> params) throws BusinessException;

	public List<SalesSmmryVO> getStaffSalesSmmryBookingList(Map<String, Object> params) throws BusinessException;
	
	public List<SalesSmmryVO> getStaffSalesSmmryBookingDetailList(Long idCompany, Long salerId, SearchParamVO searchParamVO) throws BusinessException;
	
	public int getRegionSalesSmmryInvListSize(Map<String, Object> params) throws BusinessException;

	public List<SalesSmmryVO> getRegionSalesSmmryInvList(Map<String, Object> params) throws BusinessException;
	
	public List<SalesSmmryVO> getRegionSalesSmmryInvDetailList(Long idCompany, Long regionId, SearchParamVO searchParamVO) throws BusinessException;
	
	public int getRegionSalesSmmryBookingListSize(Map<String, Object> params) throws BusinessException;

	public List<SalesSmmryVO> getRegionSalesSmmryBookingList(Map<String, Object> params) throws BusinessException;
	
	public List<SalesSmmryVO> getRegionSalesSmmryBookingDetailList(Long idCompany, Long regionId, SearchParamVO searchParamVO) throws BusinessException;
	
	public List<SalesSmmryVO> getSalesSmmryByMonthsList(Long idCompany) throws BusinessException;
}
