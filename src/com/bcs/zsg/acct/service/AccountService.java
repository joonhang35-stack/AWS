package com.bcs.zsg.acct.service;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.core.exception.BusinessException;

public interface AccountService {

	/*
	 * ACCOUNT TABLE RELATED SERVICE
	 */
	/*	
	 * Lazy Load Model
	 */
	public int getAccountListSize(Map<String, Object> params) throws BusinessException;

	public List<AcctVO> getAccountList(Map<String, Object> params) throws BusinessException;


	/*
	 * ACCOUNT_TRANS TABLE RELATED SERVICE
	 */
	public boolean isAccountValid(AcctVO acctVO) throws BusinessException;
	
	public List<AcctVO> getAccountAutoCompleteList(Long idCompany, String strAutoCompleteValue) throws BusinessException;
	
	public AcctVO getAcctVO(Long idAcct) throws BusinessException;
	
	public AcctTransVO getAccountTrans(Map<String, Object> params) throws BusinessException;
	public List<AcctTransVO> getAccountTransList(Map<String, Object> params) throws BusinessException;
	public List<AcctTransVO> getAccountTransListFilterID(Map<String, Object> params, Object[] ids, boolean filterIN) 
			throws BusinessException;
	
	public void auditAcctTrans(String action, AcctTransVO acctTransVO) throws BusinessException;
	public void auditAcctTransEmailPayment(String action, AcctTransVO acctTransVO) throws BusinessException;
	
	/**
	 * 
	 * @param acctTransViewVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctTransViewVO> getAccountTransViewList(AcctTransViewVO acctTransViewVO) throws BusinessException;
	
	public void roundingAndTaxUpdate(AcctTransVO updateVO, AcctTransVO acctRef) throws BusinessException;
	
	public void roundingAndTaxUpdateRfnd(AcctTransVO updateVO, AcctTransVO acctRef) throws BusinessException; 
	
}
