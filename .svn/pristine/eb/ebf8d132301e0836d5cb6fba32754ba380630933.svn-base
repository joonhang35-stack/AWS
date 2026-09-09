package com.bcs.zsg.sales.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.report.SalesCommissionReportVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public interface InvoiceReportDAO extends BaseDAO{

	/**
	 * 
	 * @param companyId
	 * @param searchParamVO
	 * @throws BusinessException
	 */
	public List<InvoiceVO> getInvoiceReportListA(Long companyId, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * 
	 * @param companyId
	 * @param searchParamVO
	 * @throws BusinessException
	 */
	public List<InvoiceVO> getInvoiceReportListB(Long companyId, SearchParamVO searchParamVO) throws BusinessException;
	
	public List<InvoiceVO> getSalesReportList(Long idTourDep, Long companyId, Map<String, Object> params) throws BusinessException;

	public List<SalesCommissionReportVO> getSalesCommissionReportList(Map<String, Object> params) throws BusinessException;
}
