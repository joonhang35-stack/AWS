package com.bcs.zsg.acct.bo;

import java.util.Date;
import java.util.List;

import com.bcs.zsg.acct.vo.AcctBalVO;
import com.bcs.zsg.acct.vo.AcctBalViewVO;
import com.bcs.zsg.acct.vo.AcctCatVO;
import com.bcs.zsg.acct.vo.AcctSubCatVO;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.acct.vo.JournalVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;

public interface ChartOfAcctBO {

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
	 * @param vo
	 * @throws BusinessException
	 */
	public void deleteVO(BaseVO vo) throws BusinessException;
	
	/**
	 * Get account categories
	 * @param idCompany 
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctCatVO> getAcctCatList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * Get account sub categories
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
	 * Add account category
	 * @param acctCatVO
	 * @throws BusinessException
	 */
	public void addAcctCat(AcctCatVO acctCatVO) throws BusinessException;

	/**
	 * Add account sub category
	 * @param acctSubCatVO
	 * @throws BusinessException
	 */
	public void addAcctSubCat(AcctSubCatVO acctSubCatVO) throws BusinessException;

	/**
	 * 
	 * @param acctSubCatVO
	 * @throws BusinessException
	 */
	public void delAcctSubCat(AcctSubCatVO acctSubCatVO) throws BusinessException;

	/**
	 * 
	 * @param acctVO
	 * @param acctBalVO 
	 * @throws BusinessException
	 */
	public void addAcct(AcctVO acctVO, AcctBalVO acctBalVO) throws BusinessException;

	/**
	 * 
	 * @param acctVO
	 * @param acctBalVO 
	 * @throws BusinessException
	 */
	public void delAcct(AcctVO acctVO, AcctBalVO acctBalVO) throws BusinessException;

	/**
	 * 
	 * @param acctVO
	 * @param prevAcctVO 
	 * @param acctBalVO
	 * @throws BusinessException
	 */
	public void updAcct(AcctVO acctVO, AcctVO prevAcctVO, AcctBalVO acctBalVO) throws BusinessException;

	/**
	 * 
	 * @param acctBalVO
	 * @throws BusinessException
	 */
	public void updBeginBal(AcctBalVO acctBalVO) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @param flag 
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getAcctViewList(Long idCompany, String flag) throws BusinessException;
	
	/**
	 * 
	 * @param idCompany
	 * @param flag 
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getAcctViewList(Long idCompany, String flag, SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public AcctBalViewVO getAcctTotalBeginBal(Long idCompany) throws BusinessException;
	
	/**
	 * 
	 * @param idAcct
	 * @return
	 * @throws BusinessException
	 */
	public AcctVO getAcctVO(Long idAcct) throws BusinessException;
	
	/**
	 * 
	 * @param func : CommonConstant.FUNC_ADD / CommonConstant.FUNC_UPD / CommonConstant.FUNC_DEL
	 * @param acctTransVO
	 * @throws BusinessException
	 */
	public void audAcctTrans(String func, AcctTransVO acctTransVO) throws BusinessException;
	
	public void audAcctTransEmailPayment(String func, AcctTransVO acctTransVO) throws BusinessException;
	
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
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctBalVO> getAcctBeginBalList(Long idAcct, SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * 
	 * @param idAcct
	 * @param idBank 
	 * @param idCompany 
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public double getBankBeginBal(Long idBank, Long idAcct, Long idCompany, SearchParamVO searchParamVO) throws BusinessException;

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
	 * Get Trading Profit Loss - Others (Report)
	 * @param idCompany 
	 * @param searchParamVO 
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getTrdgPftLssList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Trading Profit Loss - Audit (Report)
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
	 * Get Creditor Ledger (Report)
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
	 * Get Journal List
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<JournalVO> getJournalList(Long idCompany) throws BusinessException;
	
	/**
	 * Get Journal Entry (Report)
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
