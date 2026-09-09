package com.bcs.zsg.acct.service;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;

public interface GeneralLedgerService {

	/**
	 * 
	 * @param companyId
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctVO> getGLListWoBkSel(Long companyId) throws BusinessException;
	
	/**
	 * 
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public int getGeneralLedgerListSize(Map<String, Object> params) throws BusinessException;
	
	/**
	 * 
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctTransViewVO> getGeneralLedgerList(Map<String, Object> params) throws BusinessException;
	
	/**
	 * Get General Ledger Balance
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getGLBalanceList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	public List<AcctViewVO> getGLBalanceSmmyExtdList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
}
