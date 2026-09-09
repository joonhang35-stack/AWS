package com.bcs.zsg.sales.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.report.SalesCommissionReportVO;
import com.bcs.zsg.sales.dao.InvoiceReportDAO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class InvoiceReportServiceImpl implements InvoiceReportService {

	@Autowired
	private InvoiceReportDAO invoiceReportDAO;

	/* (non-Javadoc)
	 * @see com.bcs.zsg.sales.service.InvoiceService#getInvoiceReportList(java.lang.Long, java.lang.String, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<InvoiceVO> getInvoiceReportList(Long companyId, String reportType, SearchParamVO searchParamVO) throws BusinessException {
		if (reportType.equals("A")) {
			return invoiceReportDAO.getInvoiceReportListA(companyId, searchParamVO);
		} else {
			return invoiceReportDAO.getInvoiceReportListB(companyId, searchParamVO);
		}
	}
	
	@Override
	public List<InvoiceVO> getSalesReportList(Long idTourDep, Long companyId, Map<String, Object> params) throws BusinessException {
		return invoiceReportDAO.getSalesReportList(idTourDep, companyId, params);
	}

	@Override
	public List<SalesCommissionReportVO> getSalesCommissionReportList(Map<String, Object> params) throws BusinessException {
		return invoiceReportDAO.getSalesCommissionReportList(params);
	}
}
