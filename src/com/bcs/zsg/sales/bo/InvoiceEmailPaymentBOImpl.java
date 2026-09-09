package com.bcs.zsg.sales.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.service.InvoiceEmailPaymentService;
import com.bcs.zsg.sales.vo.InvoiceEmailPaymentVO;

public class InvoiceEmailPaymentBOImpl implements InvoiceEmailPaymentBO {
	
	@Autowired
	private InvoiceEmailPaymentService invoiceEmailPaymentService;
	
	@Override
	public int getInvoiceEmailPaymentListSize(Map<String, Object> params) throws BusinessException {
		return invoiceEmailPaymentService.getInvoiceEmailPaymentListSize(params);
	}
	
	@Override
	public List<InvoiceEmailPaymentVO> getInvoiceEmailPaymentList(Map<String, Object> params) throws BusinessException {
		return invoiceEmailPaymentService.getInvoiceEmailPaymentList(params);
	}

	@Override
	public InvoiceEmailPaymentVO getInvoiceEmailPayment(Map<String, Object> params) throws BusinessException {
		return invoiceEmailPaymentService.getInvoiceEmailPayment(params);
	}

	@Override
	public void updateInvoiceEmailPayment(InvoiceEmailPaymentVO vo, UserVO userVO) throws BusinessException {
		invoiceEmailPaymentService.updateInvoiceEmailPayment(vo, userVO);
	}
	
	@Override
	public void updateInvoiceEmailPaymentBySystem(InvoiceEmailPaymentVO vo) throws BusinessException {
		invoiceEmailPaymentService.updateInvoiceEmailPaymentBySystem(vo);
	}

	@Override
	public void deleteInvoiceEmailPayment(InvoiceEmailPaymentVO vo) throws BusinessException {
		invoiceEmailPaymentService.deleteInvoiceEmailPayment(vo);
	}

}
