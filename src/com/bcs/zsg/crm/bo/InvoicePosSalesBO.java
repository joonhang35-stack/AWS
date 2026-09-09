package com.bcs.zsg.crm.bo;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.crm.vo.InvoicePosSalesBillingVO;
import com.bcs.zsg.crm.vo.InvoicePosSalesItemVO;
import com.bcs.zsg.crm.vo.InvoicePosSalesVO;

public interface InvoicePosSalesBO {
	
	public void processInvoicePosSales(InvoiceVO invoiceVO) throws BusinessException;
	
	public List<InvoicePosSalesVO> getInvoicePosSalesList() throws BusinessException;

	public InvoicePosSalesBillingVO getBillingDetails(Long idCustomer) throws BusinessException;
	
	public List<InvoicePosSalesItemVO> getInvoicePosSalesItemList(Long idPosSales) throws BusinessException;

	public void updateInvoicePosSalesStatus(List<InvoicePosSalesVO> invoicePosSalesList) throws BusinessException;
	
	public List<InvoicePosSalesVO> getInvoicePosSalesList(Map<String, Object> params) throws BusinessException;

	public int getInvoicePosSalesListSize(Map<String, Object> params) throws BusinessException;
	
}
