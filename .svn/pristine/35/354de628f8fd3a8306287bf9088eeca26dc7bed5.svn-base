package com.bcs.zsg.crm.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.crm.vo.InvoicePosSalesItemVO;
import com.bcs.zsg.crm.vo.InvoicePosSalesVO;
import com.bcs.zsg.sales.vo.CustomerVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public interface InvoicePosSalesDAO extends BaseDAO {

	public InvoicePosSalesVO getInvoicePosSales(InvoiceVO invoiceVO) throws BusinessException;

	public InvoicePosSalesVO getInvoicePosSalesExist(InvoiceVO invoiceVO) throws BusinessException;

	public List<InvoicePosSalesVO> getInvoicePosSalesList() throws BusinessException;

	public CustomerVO getCustomerVO(Long idCustomer) throws BusinessException;
	
	public List<InvoicePosSalesItemVO> getInvoicePosSalesItemList(Long idPosSales) throws BusinessException;

	public void updateInvoicePosSalesStatus(List<InvoicePosSalesVO> invoicePosSalesList) throws BusinessException;

	public List<InvoicePosSalesVO> getInvoicePosSalesList(Map<String, Object> params) throws BusinessException;

	public int getInvoicePosSalesListSize(Map<String, Object> params) throws BusinessException;
	
}
