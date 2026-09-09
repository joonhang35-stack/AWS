package com.bcs.zsg.crm.service;

import java.io.IOException;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.InvoiceVO;

public interface CRMUploadSalesService {
	
	public void processCRMUploadSales(Long idBooking) throws BusinessException, IOException;
	
	public void processCRMUploadSales(InvoiceVO invoiceVO) throws BusinessException, IOException;
}
