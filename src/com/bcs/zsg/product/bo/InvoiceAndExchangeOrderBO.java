package com.bcs.zsg.product.bo;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderVO;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderViewVO;

public interface InvoiceAndExchangeOrderBO 
{

	
	public void addInvoiceAndExchangeOrder(
			InvoiceAndExchangeOrderVO invoiceAndExchangeOrderVO) throws BusinessException;

	public List<InvoiceAndExchangeOrderViewVO> getInvoiceAndExchangeOrderList() throws BusinessException;

	public void deleteSystemNumberGeneration(
			InvoiceAndExchangeOrderVO invoiceAndExchangeOrderVO) throws BusinessException;

	public void updateInvoiceAndExchangeOrder(
			InvoiceAndExchangeOrderVO invoiceAndExchangeOrderVO) throws BusinessException;

	public List<InvoiceAndExchangeOrderViewVO> getInvoiceAndExchangeOrderSearchList(
			Long companyId)throws BusinessException;

	public List<InvoiceAndExchangeOrderVO> getInvoiceAndExchangeOrderList(
			Long id) throws BusinessException;

	public List<InvoiceAndExchangeOrderVO> getInvoiceAndExchangeOrderList(Long id, InvoiceAndExchangeOrderVO searchFilter) throws BusinessException;

	public InvoiceAndExchangeOrderVO getInvoiceAndExchangeOrderVO(Long id);

	public InvoiceAndExchangeOrderVO getInvoiceAndExchangeOrderVO(String code);

	public List<InvoiceAndExchangeOrderVO> getInvoiceAndExchangeOrderList(Long id, InvoiceAndExchangeOrderVO searchFilter,
			boolean sortByCode, boolean excludeDefault) throws BusinessException;
}
