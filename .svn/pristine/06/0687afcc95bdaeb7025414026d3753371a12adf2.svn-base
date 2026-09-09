package com.bcs.zsg.bank.dao;

import java.util.List;

import com.bcs.zsg.bank.vo.BankReconVO;
import com.bcs.zsg.common.dao.BaseCommonDAOLM;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;

public interface ReconciliationDAO extends BaseCommonDAOLM {

	/**
	 * 
	 * @param searchParamVO
	 * @param bankIdList 
	 * @return
	 * @throws BusinessException
	 */
	public List<BankReconVO> getBankReconList(SearchParamVO searchParamVO, List<Long> bankIdList) throws BusinessException;

	/**
	 * 
	 * @param searchParamVO
	 * @param bankReconVO 
	 * @return
	 * @throws BusinessException
	 */
	public BankReconVO getBankReconCashBookBal(SearchParamVO searchParamVO, BankReconVO bankReconVO) throws BusinessException;

	/**
	 * 
	 * @param searchParamVO
	 * @param bankReconVO
	 * @return
	 * @throws BusinessException
	 */
	public double getPrevBankReconCashBookBal(SearchParamVO searchParamVO, BankReconVO bankReconVO) throws BusinessException;

	/**
	 * 
	 * @param bankReconVO
	 * @param searchParamVO 
	 * @return
	 * @throws BusinessException
	 */
	public BankReconVO getBankRecon(BankReconVO bankReconVO, SearchParamVO searchParamVO) throws BusinessException;

}
