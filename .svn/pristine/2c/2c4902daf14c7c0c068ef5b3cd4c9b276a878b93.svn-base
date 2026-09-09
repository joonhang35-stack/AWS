package com.bcs.zsg.acct.bo;

import java.util.Date;
import java.util.List;

import com.bcs.zsg.acct.vo.FinancialPeriodLockVO;
import com.bcs.zsg.acct.vo.FinancialPeriodVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;

public interface FinancialPeriodBO {

	/**
	 * 
	 * @param vo
	 * @throws BusinessException
	 */
	public void insertVO(BaseVO vo) throws BusinessException;

	/**
	 * 
	 * @param vo
	 * @throws BusinessException
	 */
	public void updateVO(BaseVO vo) throws BusinessException;
	
	/**
	 * 
	 * @param idCompany 
	 * @param year
	 * @return
	 * @throws BusinessException
	 */
	public List<FinancialPeriodVO> getFinPeriodList(Long idCompany, String year) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @param searchParamVO
	 * @throws BusinessException
	 */
	public void addFinPeriod(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public FinancialPeriodVO getFinPeriod(Long idCompany) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @param userName
	 * @param finPeriodVO
	 * @throws BusinessException
	 */
	public void updFinPeriod(Long idCompany, String userName, FinancialPeriodVO finPeriodVO) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public FinancialPeriodLockVO getFinPeriodLock(Long idCompany) throws BusinessException;

	/**
	 * 
	 * @param finPeriodLockVO
	 * @throws BusinessException
	 */
	public void updateFinPeriodLock(FinancialPeriodLockVO finPeriodLockVO) throws BusinessException;

	public boolean isDateInFinPeriodClosed(Long idCompany, Date date) throws BusinessException;
}
