package com.bcs.zsg.bank.service;

import java.util.List;

import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.sales.vo.InvoicePaymentVO;

public interface RefundService {


	public void addRefund(CashBookVO cashBookVO, Long idCompany,SystemNumberGenerationVO sysNumGenVO,List<SystemNumberGenerationVO> sysNumGenList,BankAcctVO bankAcctSearchVO,LookupItemVO lookupItemBT, LookupItemVO lookupItemCBT, AcctVO acctVO) throws BusinessException;

	public void updTransList(List<AcctTransViewVO> addTransList,
			List<AcctTransViewVO> updTransist, List<AcctTransViewVO> delTransList,CashBookVO cashBookVO, Long newNum,BankAcctVO bankAcctSearchVO,AcctTransViewVO acctTransCashBookVO, LookupItemVO lookupItemCBT, SystemNumberGenerationVO sysGenCodeVO, List<AcctTransViewVO> acctTransListView, Long CompId) throws BusinessException;

	public void delInvPmnt(String cashBookId, Long idCompany) throws BusinessException;
	
	public List<InvoicePaymentVO> getInvoicePaymentListNoCashbook(Long CompId) throws BusinessException;
	
	public void updateInvoicePmnt(CashBookVO cashBookVO, Long CompId) throws BusinessException;
}
