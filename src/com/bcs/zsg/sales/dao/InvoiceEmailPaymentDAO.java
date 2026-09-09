package com.bcs.zsg.sales.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.InvoiceEmailPaymentVO;

public interface InvoiceEmailPaymentDAO extends BaseDAO {
	public int getInvoiceEmailPaymentListSize(Map<String, Object> params) throws BusinessException;
	public List<InvoiceEmailPaymentVO> getInvoiceEmailPaymentList(Map<String, Object> params) throws BusinessException;
	public InvoiceEmailPaymentVO getInvoiceEmailPayment(Map<String, Object> params) throws BusinessException;
}
