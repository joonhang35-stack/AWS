package com.bcs.zsg.bank.dao;

import java.util.List;

import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.InvoicePaymentVO;

public interface RefundDAO extends BaseDAO {

	public List<InvoicePaymentVO> getInvoicePaymentListNoCashbook(Long CompId) throws BusinessException;
	
	public void delInvPmnt(String cashBookId) throws BusinessException;
	
	public void updInvPmnt(CashBookVO cashBookVO) throws BusinessException;
}
