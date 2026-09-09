package com.bcs.zsg.sales.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.sales.service.InvoiceReportService;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.report.SalesCommissionReportVO;

public class InvoiceReportBOImpl implements InvoiceReportBO {

	@Autowired
	private InvoiceReportService invoiceReportService;

	/* (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.InvoiceBO#getInvoiceReportList(java.lang.Long, java.lang.String, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<InvoiceVO> getInvoiceReportList(Long companyId, String reportType, SearchParamVO searchParamVO) throws BusinessException {
		return invoiceReportService.getInvoiceReportList(companyId, reportType, searchParamVO);
	}

	@Override
	public List<InvoiceVO> getSalesReportList(Long idTourDep, Long companyId, Map<String, Object> params) throws BusinessException {
		return invoiceReportService.getSalesReportList(idTourDep, companyId, params);
	}

	@Override
	public List<SalesCommissionReportVO> getSalesCommissionReportList(Map<String, Object> params) throws BusinessException {
		return invoiceReportService.getSalesCommissionReportList(params);
	}
}
