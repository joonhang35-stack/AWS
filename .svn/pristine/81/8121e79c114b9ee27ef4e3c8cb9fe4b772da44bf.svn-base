package com.bcs.zsg.sales.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.dao.InvoicePaymentReportDAO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class InvoicePaymentReportServiceImpl implements InvoicePaymentReportService{
	
	@Autowired
	private transient InvoicePaymentReportDAO invoicePaymentReportDAO;
	
	@Override
	public List<InvoiceVO> getInvoicePaymentReportList(Long companyId, Map<String, Object> params) throws BusinessException{
		return invoicePaymentReportDAO.getInvoicePaymentReportList(companyId, params);
	}
}
