package com.bcs.zsg.bank.bo;

import java.util.List;

import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.BankReconVO;
import com.bcs.zsg.common.bo.BaseCommonLMBO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;

public interface ReconciliationBO extends BaseCommonLMBO {

	/**
	 * 
	 * @param bankReconVO
	 * @throws BusinessException
	 */
	public boolean insertVO(BaseVO vo) throws BusinessException;

	/**
	 * 
	 * @param vo
	 * @throws BusinessException
	 */
	public void updateVO(BaseVO vo) throws BusinessException;

	/**
	 * 
	 * @param searchParamVO
	 * @param bankAcctList 
	 * @return
	 * @throws BusinessException
	 */
	public List<BankReconVO> getBankReconList(SearchParamVO searchParamVO, List<BankAcctViewVO> bankAcctList) throws BusinessException;

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
