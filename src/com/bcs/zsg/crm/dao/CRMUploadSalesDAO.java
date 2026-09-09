package com.bcs.zsg.crm.dao;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.InvoiceVO;

public interface CRMUploadSalesDAO {
	
	public InvoiceVO getInvoicePaxVO(InvoiceVO invoiceVO) throws BusinessException;
}
