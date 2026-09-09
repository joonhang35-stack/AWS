package com.bcs.zsg.acctreport.service;

import java.util.List;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.TourThemeVO;

public interface AccountReportService {
	
	/**
	 * Get Account Report
	 * @param idCompany
	 * @param searchParamVO
	 * @param reportType
	 * @return
	 * @throws BusinessException
	 */
	public List<?> getAccountReport(Long idCompany, SearchParamVO searchParamVO, String reportType) throws BusinessException;

	/**
	 * Get Region List
	 * @return
	 * @throws BusinessException
	 */
	public List<TourThemeVO> getTourThemeList() throws BusinessException;
}
