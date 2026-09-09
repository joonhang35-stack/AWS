package com.bcs.zsg.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.InvoiceAndExchangeOrderService;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderVO;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderViewVO;

public class InvoiceAndExchangeOrderBOImpl implements InvoiceAndExchangeOrderBO 
{
	@Autowired
	private InvoiceAndExchangeOrderService invoiceAndExchangeOrderService;

	@Override
	public void addInvoiceAndExchangeOrder(
			InvoiceAndExchangeOrderVO invoiceAndExchangeOrderVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		invoiceAndExchangeOrderService.addInvoiceAndExchangeOrder(invoiceAndExchangeOrderVO);
	}

	@Override
	public List<InvoiceAndExchangeOrderViewVO> getInvoiceAndExchangeOrderList()
			throws BusinessException {
		// TODO Auto-generated method stub
		return invoiceAndExchangeOrderService.getInvoiceAndExchangeOrderList();
	}

	@Override
	public void deleteSystemNumberGeneration(
			InvoiceAndExchangeOrderVO invoiceAndExchangeOrderVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		invoiceAndExchangeOrderService.deleteSystemNumberGeneration(invoiceAndExchangeOrderVO);
	}

	@Override
	public void updateInvoiceAndExchangeOrder(
			InvoiceAndExchangeOrderVO invoiceAndExchangeOrderVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		invoiceAndExchangeOrderService.updateInvoiceAndExchangeOrder(invoiceAndExchangeOrderVO);
	}

	@Override
	public List<InvoiceAndExchangeOrderViewVO> getInvoiceAndExchangeOrderSearchList(
			Long companyId) throws BusinessException {
		// TODO Auto-generated method stub
		return invoiceAndExchangeOrderService.getInvoiceAndExchangeOrderSearchList(companyId);
	}

	@Override
	public List<InvoiceAndExchangeOrderVO> getInvoiceAndExchangeOrderList(Long id) throws BusinessException {
		return invoiceAndExchangeOrderService.getInvoiceAndExchangeOrderList(id);
	}
	
	@Override
	public List<InvoiceAndExchangeOrderVO> getInvoiceAndExchangeOrderList(Long id, InvoiceAndExchangeOrderVO searchFilter) throws BusinessException {
		return invoiceAndExchangeOrderService.getInvoiceAndExchangeOrderList(id, searchFilter);
	}
	
	@Override
	public InvoiceAndExchangeOrderVO getInvoiceAndExchangeOrderVO(Long id) {
		return invoiceAndExchangeOrderService.getInvoiceAndExchangeOrderVO(id);
	}
	
	@Override
	public InvoiceAndExchangeOrderVO getInvoiceAndExchangeOrderVO(String code) {
		return invoiceAndExchangeOrderService.getInvoiceAndExchangeOrderVO(code);
	}
	
	@Override
	public List<InvoiceAndExchangeOrderVO> getInvoiceAndExchangeOrderList(Long id, InvoiceAndExchangeOrderVO searchFilter,
			boolean sortByCode, boolean excludeDefault) throws BusinessException {
		return invoiceAndExchangeOrderService.getInvoiceAndExchangeOrderList(id, searchFilter, sortByCode, excludeDefault);
	}

}
