package com.bcs.zsg.sales.service;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.report.SalesCommissionReportVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public interface InvoiceReportService {

	/**
	 * 
	 * @param companyId
	 * @param reportType
	 * @param searchParamVO
	 * @throws BusinessException
	 */
	public List<InvoiceVO> getInvoiceReportList(Long companyId, String reportType, SearchParamVO searchParamVO) throws BusinessException;
	
	public List<InvoiceVO> getSalesReportList(Long idTourDep, Long companyId, Map<String, Object> params) throws BusinessException;

	public List<SalesCommissionReportVO> getSalesCommissionReportList(Map<String, Object> params) throws BusinessException;
}
