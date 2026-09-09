package com.bcs.zsg.sales.bo;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.InvoiceEmailPaymentVO;

public interface InvoiceEmailPaymentBO {
	
	public int getInvoiceEmailPaymentListSize(Map<String, Object> params) throws BusinessException;
	public List<InvoiceEmailPaymentVO> getInvoiceEmailPaymentList(Map<String, Object> params) throws BusinessException;
	public InvoiceEmailPaymentVO getInvoiceEmailPayment(Map<String, Object> params) throws BusinessException;
	
	public void updateInvoiceEmailPayment(InvoiceEmailPaymentVO vo, UserVO userVO) throws BusinessException;
	public void updateInvoiceEmailPaymentBySystem(InvoiceEmailPaymentVO vo) throws BusinessException;
	public void deleteInvoiceEmailPayment(InvoiceEmailPaymentVO vo) throws BusinessException;
}
