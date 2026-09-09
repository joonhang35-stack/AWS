package com.bcs.zsg.acct.dao;

import java.util.Date;
import java.util.List;

import com.bcs.zsg.acct.vo.AcctBalVO;
import com.bcs.zsg.acct.vo.AcctBalViewVO;
import com.bcs.zsg.acct.vo.AcctCatVO;
import com.bcs.zsg.acct.vo.AcctSubCatVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.acct.vo.JournalVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;

public interface ChartOfAcctDAO extends BaseDAO {

	/**
	 * Get account categories
	 * @return
	 */
	public List<AcctCatVO> getAcctCatList();

	/**
	 * 
	 * @param idAcctCat
	 * @param idCompany 
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctSubCatVO> getAcctSubCatList(Long idAcctCat, Long idCompany) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @param idAcctCat
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctVO> getAcctList(Long idCompany, Long idAcctCat) throws BusinessException;
	
	/**
	 * 
	 * @param idCompany
	 * @param idAcctCat
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctVO> getAcctListByAcctCat(Long idCompany, Long idAcctCat) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @param idAcctCat
	 * @param flag 
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getAcctViewList(Long idCompany, Long idAcctCat, String flag, SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public AcctBalViewVO getAcctTotalBeginBal(Long idCompany) throws BusinessException;

	/**
	 * 
	 * @param acctCatVO
	 * @return
	 * @throws BusinessException
	 */
	public boolean isAcctCatValid(AcctCatVO acctCatVO) throws BusinessException;

	/**
	 * 
	 * @param acctSubCatVO
	 * @return
	 * @throws BusinessException
	 */
	public boolean isAcctSubCatValid(AcctSubCatVO acctSubCatVO) throws BusinessException;

	/**
	 * 
	 * @param acctVO
	 * @return
	 * @throws BusinessException
	 */
	public boolean isAcctValid(AcctVO acctVO) throws BusinessException;

	/**
	 * 
	 * @param acctVO
	 * @return
	 * @throws BusinessException
	 */
	public boolean isMainAcctValid(AcctVO acctVO) throws BusinessException;

	/**
	 * 
	 * @param acctVO
	 * @return
	 * @throws BusinessException
	 */
	public boolean isSubAcctExisted(AcctVO acctVO) throws BusinessException;

	/**
	 * 
	 * @param acctSubCatVO
	 * @return
	 * @throws BusinessException
	 */
	public boolean isAcctSubCatInUsed(AcctSubCatVO acctSubCatVO) throws BusinessException;

	/**
	 * 
	 * @param idAcct
	 * @return
	 * @throws BusinessException
	 */
	public AcctVO getAcctVO(Long idAcct) throws BusinessException;

	/**
	 * 
	 * @param idAcct
	 * @return
	 * @throws BusinessException
	 */
	public AcctViewVO getAcctViewVO(Long idAcct) throws BusinessException;

	/**
	 * 
	 * @param idAcct
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	public AcctBalVO getAcctBal(Long idAcct, Date date) throws BusinessException;

	/**
	 * 
	 * @param idAcct
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctBalVO> getAcctBalList(Long idAcct, Date date) throws BusinessException;

	/**
	 * 
	 * @param i : 0 = debit / 1 = credit
	 * @param idCompany
	 * @param idAcct
	 * @param fromDate
	 * @param toDate 
	 * @return
	 * @throws BusinessException
	 */
	public double getTotAcctTransDC(int i, Long idCompany, Long idAcct, Date fromDate, Date toDate) throws BusinessException;

	/**
	 * 
	 * @param class1
	 * @param idAcct
	 * @return
	 * @throws BusinessException
	 */
	public boolean isAcctUsed(Class<?> class1, Long idAcct) throws BusinessException;

	/**
	 * 
	 * @param acctVO
	 * @throws BusinessException
	 */
	public void deleteAcctBal(AcctVO acctVO) throws BusinessException;

	/**
	 * 
	 * @param idAcct
	 * @return
	 * @throws BusinessException
	 */
	public AcctBalVO getAcctBeginBalVO(Long idAcct) throws BusinessException;
	
	/**
	 * 
	 * @param idAcct
	 * @param beginDate
	 * @return
	 */
	public AcctBalVO getAcctBeginBalByDate(Long idAcct, Date beginDate) throws BusinessException;

	/**
	 * 
	 * @param idAcct
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctBalVO> getAcctBeginBalList(Long idAcct, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * 
	 * @param idAcct
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctBalVO> getAcctBeginBalAllList(Long idAcct) throws BusinessException;

	/**
	 * 
	 * @param idBank
	 * @param idAcct
	 * @param idCompany 
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public double getBankBeginBal(Long idBank, Long idAcct, Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * 
	 * @param idCompany
	 * @param fromDate
	 * @return
	 * @throws BusinessException
	 */
	public Date getFirtFinPeriod(Long idCompany, Date fromDate) throws BusinessException;

	/**
	 * 
	 * @param sysCd
	 * @param sysNo
	 * @param transDt
	 * @param companyId 
	 * @throws BusinessException
	 */
	public void updAcctTransDt(String sysCd, String sysNo, Date transDt, Long companyId) throws BusinessException;

	/**
	 * 
	 * @param sysCd
	 * @param sysNo
	 * @param statusCrDr
	 * @param companyId
	 * @throws BusinessException
	 */
	public void terminateAcctTrans(String sysCd, String sysNo, String statusCrDr, Long companyId) throws BusinessException;

	/**
	 * Get Trial Balance - Others (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getTrdgPftLssList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Trial Balance - Audit (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getTrdgPftLssAdtList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * Get Trial Balance (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getTrialBalanceList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Trial Balance Year to Date (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getTrialBalanceListYear(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public Date getFinPeriod1stDt(Long idCompany) throws BusinessException;

	/**
	 * @param idCompany
	 * @param idAcct
	 * @return
	 * @throws BusinessException
	 */
	public boolean isClosedAcct(Long idCompany, Long idAcct) throws BusinessException;
	
	/**
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public boolean isAcctBeginBalExisted(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public String getFinPeriodYear(Long idCompany) throws BusinessException;
	
	/**
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public List<String> getFinPeriodYearList(Long idCompany) throws BusinessException;
	
	/**
	 * Get Balance Sheet (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getBalanceSheetList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * Get Balance Sheet Details (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getBalanceSheetDetailsList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Beginning Balance (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getBeginningBalanceList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Debtor Ledger (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getDebtorLedgerList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	public List<AcctViewVO> getDebtorLedgerList2(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * Get Debtor Summary (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getDebtorSummaryList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Creditor Summary (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getCreditorLedgerList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * Get Creditor Summary (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getCreditorSummaryList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Creditor Summary (Report)
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public List<JournalVO> getJournalList(Long idCompany) throws BusinessException;
	
	/**
	 * Get Creditor Summary (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<JournalVO> getJournalEntryList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Sales In Advance (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getSalesInAdvanceList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Chart Of Account (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getChartOfAccountList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
}