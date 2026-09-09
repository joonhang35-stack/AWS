package com.bcs.zsg.acct.dao;

import java.util.Date;
import java.util.List;

import com.bcs.zsg.acct.vo.FinancialPeriodLockVO;
import com.bcs.zsg.acct.vo.FinancialPeriodVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public interface FinancialPeriodDAO extends BaseDAO {

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
	 * @param year
	 * @throws BusinessException
	 */
	public void delFinPeriod(Long idCompany, String year) throws BusinessException;

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
	 * @param year
	 * @return
	 * @throws BusinessException
	 */
	public boolean isClosedPeriodExisted(Long idCompany, String year) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public FinancialPeriodLockVO getFinPeriodLock(Long idCompany) throws BusinessException;
	
	/**
	 * 
	 * @param companyVO
	 * @param userName
	 * @param finPeriodVO
	 * @throws BusinessException
	 */
	public void calculateFinPeriodBalance(CompanyVO companyVO, String userName, FinancialPeriodVO finPeriodVO) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @param year
	 * @param month
	 * @param option 
	 * @return
	 * @throws BusinessException
	 */
	public boolean isMonthIncludedPrevPeriod(Long idCompany, String year, int month, int option) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	public boolean isDateInFinPeriodClosed(Long idCompany, Date date) throws BusinessException;
	
	/**
	 * 
	 * @param idCompany
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 * @throws BusinessException
	 */
	public FinancialPeriodVO getFinPeriodLock(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException;
	
	/**
	 * 
	 * @param idCompany
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	public Integer getFinPeriodClosedStatus(Long idCompany, Date date) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	public String getGSTProcessStatus(Long idCompany, Date date, boolean isDateBetween) throws BusinessException;
}
