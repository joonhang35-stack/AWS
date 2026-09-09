package com.bcs.zsg.sales.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.service.InvoicePaymentReportService;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class InvoicePaymentReportBOImpl implements InvoicePaymentReportBO{
	@Autowired
	private transient InvoicePaymentReportService invoicePaymentReportService;
	
	@Override
	public List<InvoiceVO> getInvoicePaymentReportList(Long companyId, Map<String, Object> params) throws BusinessException{
		return invoicePaymentReportService.getInvoicePaymentReportList(companyId, params);
	}
}
