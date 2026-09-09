package com.bcs.zsg.bank.service;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.sales.vo.InvoicePaymentVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;

public interface DepositService {

	/**
	 * 
	 * @param cashBookVO
	 * @param transDate
	 * @param idCompany
	 * @param sysNumGenVO
	 * @param sysNumGenList
	 * @param bankAcctSearchVO
	 * @param lookupItemBT
	 * @param lookupItemCBT
	 * @param acctVO
	 * @param invPmntAUDList
	 * @throws BusinessException
	 */
	public void addDeposit(CashBookVO cashBookVO, Long idCompany,SystemNumberGenerationVO sysNumGenVO,List<SystemNumberGenerationVO> sysNumGenList,BankAcctVO bankAcctSearchVO,LookupItemVO lookupItemBT, LookupItemVO lookupItemCBT, AcctVO acctVO,AddUpdDelVO invPmntAUDList) throws BusinessException, QueueException;
	
	/**
	 * 
	 * @param sysNo
	 * @param transTypeCd
	 * @param sysPrefix
	 * @param compId
	 * @return
	 * @throws BusinessException
	 */
	public AcctTransViewVO getAcctTransCashBook(String sysNo, String transTypeCd, String sysCode, Long compId) throws  BusinessException;

	
	/**
	 * 
	 * @param idCashBook
	 * @return
	 * @throws BusinessException
	 */
	public List<InvoicePaymentVO> getInvoicePaymentListByCashbook(Long idCashBook) throws BusinessException;
	
	/**
	 * 
	 * @param CompId
	 * @return
	 * @throws BusinessException
	 */
	public List<InvoicePaymentVO> getInvoicePaymentListNoCashbook(Long CompId) throws BusinessException;

	/**
	 * 
	 * @param compId
	 * @param searchParamVO
	 * @return
	 */
	public List<InvoicePaymentVO> getInvoicePaymentListNoCashbook(Long compId, SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * 
	 * @param invPmntAUDList
	 * @param cashBookId
	 * @throws BusinessException
	 */
	public void updateDeposit( AddUpdDelVO invPmntAUDList,String cashBookId) throws BusinessException;
	
	/**
	 * 
	 * @param cashBookId
	 * @param cashBookId
	 * @throws BusinessException
	 */
	public void deleteDeposit( String cashBookId, Long idCompany) throws BusinessException;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public int getDepositListSize(Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public List<CashBookVO> getDepositList(Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param cashBookVO
	 * @param acctTransCashBookVO
	 * @param bankAcctSearchVO
	 * @param CompId
	 * @throws BusinessException
	 */
	public void updateDeposit(CashBookVO cashBookVO, AcctTransViewVO acctTransCashBookVO, BankAcctVO bankAcctSearchVO, Long CompId) throws BusinessException;
	
	/**
	 * 
	 * @param cashBookVO
	 * @param CompId
	 * @throws BusinessException
	 */
	public void updateInvoicePmnt(CashBookVO cashBookVO, Long CompId) throws BusinessException;

	/**
	 * 
	 * @param paymentIDs
	 * @param hideStatus 
	 * @throws BusinessException
	 */
	public void updateInvoicePaymentHideStatus(String paymentIDs, int hideStatus) throws BusinessException;

	/**
	 * 
	 * @param invPmntVO
	 * @param idCompany
	 * @throws BusinessException
	 */
	public void postToDeposit(List<InvoicePaymentVO> invPmntList, List<BankAcctViewVO> bankAcctViewList, CompanyVO companyVO) throws BusinessException, QueueException;
}
