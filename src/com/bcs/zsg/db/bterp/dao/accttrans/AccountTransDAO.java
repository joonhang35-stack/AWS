package com.bcs.zsg.db.bterp.dao.accttrans;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;

public interface AccountTransDAO extends BaseDAO {

	public List<AcctTransVO> getAccountTransList();
	public List<AcctTransVO> getAccountTransList(Map<String, Object> params);
	public List<AcctTransVO> getAccountTransListFilterID(Map<String, Object> param, Object[] ids, boolean filterIN);

	public AcctTransVO getAccountTrans();
	public AcctTransVO getAccountTrans(Map<String, Object> params);
	public AcctTransVO getAccountTransFilterReferenceID(Map<String, Object> params);
	
	public AcctTransVO getAccountTransSumDebitCreditByAcctId(Map<String, Object> params) throws BusinessException;
	public AcctTransVO getAccountBfBalance(Map<String, Object> params) throws BusinessException;

	public List<AcctTransVO> getAccountTransListGeneralLedger(Map<String, Object> params) throws BusinessException;
	/**
	 * 
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public int getAccountTransListSize(Map<String, Object> params) throws BusinessException;
	
	/**
	 * Get General Ledger Balance
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getGLBalanceList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	public List<AcctViewVO> getGLBalanceSmmyExtdList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Account Transaction List
	 * @param idCompany
	 * @param searchParamVO
	 * @param acctViewVO,
	 * @param acctId
	 * @param acctCatCd
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctTransViewVO> getAcctTransViewList(Long idCompany, SearchParamVO searchParamVO, AcctViewVO acctViewVO, Long acctId, String acctCatCd, Session session) throws BusinessException;
	
	public List<AcctTransViewVO> getAcctTransViewSmmyExtdList(Long idCompany, SearchParamVO searchParamVO, AcctViewVO acctViewVO, Long acctId, String acctCatCd, Session session) throws BusinessException;
	/**
	 * Update Account Transaction Customer Name in Destination
	 * @param idCompany
	 * @param customerId
	 * @return
	 * @throws BusinessException
	 */
	public void updateAcctTransDestinationCust(Long idCompany, Long customerId) throws BusinessException;
	public void updateAllCustNmToRelatedTbl(Long idCompany) throws BusinessException;
	
	/**
	 * Update Account Transaction Supplier Name in Destination
	 * @param idCompany
	 * @param customerId
	 * @return
	 * @throws BusinessException
	 */
	public void updateAcctTransDestinationSpplr(Long idCompany, Long supplierId) throws BusinessException;
	
	/**
	 * Update Account Transaction Acct Id in acct_Id
	 * @param idCompany
	 * @param customerId
	 * @return
	 * @throws BusinessException
	 */
	public void updateAcctTransAcctIdSupplr(Long idCompany, Long supplierId) throws BusinessException;
	
	
	/**
	 * Get Account Transaction List (Tax & Rounding)
	 * @param idCompany
	 * @param customerId
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctTransVO> getAcctTransTaxRndList(Long idCompany, String sysCode, String sysNo) throws BusinessException;
	
	
}
