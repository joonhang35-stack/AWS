package com.bcs.zsg.product.dao;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderVO;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderViewVO;



public interface InvoiceAndExchangeOrderDAO extends BaseDAO 
{

	public List<InvoiceAndExchangeOrderViewVO> getInvoiceAndExchangeOrderList() throws BusinessException;

	public List<InvoiceAndExchangeOrderViewVO> getInvoiceAndExchangeOrderSearchList(
			Long companyId) throws BusinessException;

	public List<InvoiceAndExchangeOrderVO> getInvoiceAndExchangeOrderList(Long id, InvoiceAndExchangeOrderVO searchFilter) throws BusinessException;

	public InvoiceAndExchangeOrderVO getInvoiceAndExchangeOrderVO(String code);

	public InvoiceAndExchangeOrderVO getInvoiceAndExchangeOrderVO(Long id);
}