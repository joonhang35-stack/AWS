package com.bcs.zsg.crm.bo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.crm.service.CRMUploadSalesService;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class CRMUploadSalesBOImpl implements CRMUploadSalesBO {
	
	@Autowired
	private CRMUploadSalesService crmUploadSalesService;
	
	@Override
	public void processCRMUploadSales(InvoiceVO invoiceVO) throws BusinessException, IOException {
		crmUploadSalesService.processCRMUploadSales(invoiceVO);
	}
}
