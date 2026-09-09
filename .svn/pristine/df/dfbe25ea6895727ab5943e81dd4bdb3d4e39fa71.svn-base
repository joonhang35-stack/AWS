package com.bcs.zsg.acct.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.dao.ChartOfAcctDAO;
import com.bcs.zsg.acct.vo.AcctBalVO;
import com.bcs.zsg.acct.vo.AcctBalViewVO;
import com.bcs.zsg.acct.vo.AcctCatVO;
import com.bcs.zsg.acct.vo.AcctSubCatVO;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.acct.vo.JournalVO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderVO;
import com.bcs.zsg.product.vo.TourDepItemVO;
import com.bcs.zsg.sales.vo.BookingChargeItemVO;
import com.bcs.zsg.sales.vo.InvoiceItemVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class ChartOfAcctServiceImpl implements ChartOfAcctService {

	@Autowired
	private transient ChartOfAcctDAO chartOfAcctDAO;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#insertVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void insertVO(BaseVO vo) throws BusinessException {
		chartOfAcctDAO.insert(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#updateVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void updateVO(BaseVO vo) throws BusinessException {
		chartOfAcctDAO.update(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#deleteVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void deleteVO(BaseVO vo) throws BusinessException {
		chartOfAcctDAO.delete(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#getAcctCatList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctCatVO> getAcctCatList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		List<AcctCatVO> acctCatList = chartOfAcctDAO.getAcctCatList();
		
		//Fast Method
		//searchParamVO.setObj1("ALL");
		List<AcctViewVO> acctViewVOList = chartOfAcctDAO.getAcctViewList(idCompany, (long) 1, null, searchParamVO);
		
		if (CollectionUtils.isNotEmpty(acctCatList)) {

			for (AcctCatVO acctCatVO : acctCatList) {
				List<AcctViewVO> acctViewVOCatList = new ArrayList<AcctViewVO> ();
				for(int i = acctViewVOList.size() - 1 ; i >= 0 ; i--) {
					if(acctCatVO.getId().equals(acctViewVOList.get(i).getIdAcctCat())) {
						acctViewVOCatList.add(acctViewVOList.get(i));
						acctViewVOList.remove(i);
					}
				}
				
				Collections.sort(acctViewVOCatList, new Comparator<AcctViewVO>() {
                    @Override
                    public int compare(AcctViewVO obj1, AcctViewVO obj2) {
                       return (obj1.getCode().concat(obj1.getSubCode() == null ? "" : obj1.getSubCode()))
                                    .compareTo((obj2.getCode().concat(obj2.getSubCode() == null ? "" : obj2.getSubCode())));

                    }
                });
                
				acctCatVO.setAcctViewList(acctViewVOCatList);
				acctCatVO.setAcctSubCatList(chartOfAcctDAO.getAcctSubCatList(acctCatVO.getId(), idCompany));
				//acctCatVO.setAcctSubCatList(new ArrayList<AcctSubCatVO>(acctCatVO.getAcctSubCatSet()));
				//acctCatVO.setAcctSubCatSet(null);
			}
		}
		return acctCatList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#getAcctSubCatList(java.lang.Long)
	 */
	@Override
	public List<AcctSubCatVO> getAcctSubCatList(Long idAcctCat, Long idCompany) throws BusinessException {
		return chartOfAcctDAO.getAcctSubCatList(idAcctCat, idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#getAcctList(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<AcctVO> getAcctList(Long idCompany, Long idAcctCat) throws BusinessException {
		return chartOfAcctDAO.getAcctList(idCompany, idAcctCat);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#getAcctListByAcctCat(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<AcctVO> getAcctListByAcctCat(Long idCompany, Long idAcctCat) throws BusinessException {
		return chartOfAcctDAO.getAcctListByAcctCat(idCompany, idAcctCat);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#getAcctViewList(java.lang.Long)
	 */
	@Override
	public List<AcctViewVO> getAcctViewList(Long idCompany, String flag) throws BusinessException {
		return chartOfAcctDAO.getAcctViewList(idCompany, null, flag, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#getAcctViewList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getAcctViewList(Long idCompany, String flag, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getAcctViewList(idCompany, null, flag, searchParamVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#getAcctTotalBeginBal(java.lang.Long)
	 */
	@Override
	public AcctBalViewVO getAcctTotalBeginBal(Long idCompany) throws BusinessException {
		return chartOfAcctDAO.getAcctTotalBeginBal(idCompany);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#getAcctVO(java.lang.Long)
	 */
	@Override
	public AcctVO getAcctVO(Long idAcct) throws BusinessException {
		return chartOfAcctDAO.getAcctVO(idAcct);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#isAcctCatValid(com.bcs.zsg.acct.vo.AccountCatVO)
	 */
	@Override
	public boolean isAcctCatValid(AcctCatVO acctCatVO) throws BusinessException {
		return chartOfAcctDAO.isAcctCatValid(acctCatVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#isAcctSubCatValid(com.bcs.zsg.acct.vo.AccountSubCatVO)
	 */
	@Override
	public boolean isAcctSubCatValid(AcctSubCatVO acctSubCatVO) throws BusinessException {
		return chartOfAcctDAO.isAcctSubCatValid(acctSubCatVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#isAcctValid(com.bcs.zsg.acct.vo.AccountVO)
	 */
	@Override
	public boolean isAcctValid(AcctVO acctVO) throws BusinessException {
		return chartOfAcctDAO.isAcctValid(acctVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#isMainAcctValid(com.bcs.zsg.acct.vo.AcctVO)
	 */
	@Override
	public boolean isMainAcctValid(AcctVO acctVO) throws BusinessException {
		return chartOfAcctDAO.isMainAcctValid(acctVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#isSubAcctExisted(com.bcs.zsg.acct.vo.AccountVO)
	 */
	@Override
	public boolean isSubAcctExisted(AcctVO acctVO) throws BusinessException {
		return chartOfAcctDAO.isSubAcctExisted(acctVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#isAcctSubCatInUsed(com.bcs.zsg.acct.vo.AcctSubCatVO)
	 */
	@Override
	public boolean isAcctSubCatInUsed(AcctSubCatVO acctSubCatVO) throws BusinessException {
		return chartOfAcctDAO.isAcctSubCatInUsed(acctSubCatVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#isAcctUsed(com.bcs.zsg.acct.vo.AcctVO, com.bcs.zsg.acct.vo.AcctBalVO)
	 */
	@Override
	public boolean isAcctUsed(AcctVO acctVO, AcctBalVO acctBalVO) throws BusinessException {
		long idAcct = acctVO.getId();
		if (chartOfAcctDAO.isAcctUsed(AcctTransVO.class, idAcct) || chartOfAcctDAO.isAcctUsed(BankAcctVO.class, idAcct) ||
				chartOfAcctDAO.isAcctUsed(InvoiceAndExchangeOrderVO.class, idAcct) || chartOfAcctDAO.isAcctUsed(InvoiceVO.class, idAcct) ||
				chartOfAcctDAO.isAcctUsed(InvoiceItemVO.class, idAcct) || chartOfAcctDAO.isAcctUsed(BookingChargeItemVO.class, idAcct) ||
				chartOfAcctDAO.isAcctUsed(TourDepItemVO.class, idAcct)) return true;
		
		/*if (chartOfAcctDAO.isAcctUsed(AcctBalVO.class, idAcct)) {
			return false;
		}*/
		
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#deleteAcctBal(com.bcs.zsg.acct.vo.AcctVO)
	 */
	@Override
	public void deleteAcctBal(AcctVO acctVO) throws BusinessException {
		chartOfAcctDAO.deleteAcctBal(acctVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#getAcctBalVO(java.lang.Long)
	 */
	@Override
	public AcctBalVO getAcctBeginBalVO(Long idAcct) throws BusinessException {
		return chartOfAcctDAO.getAcctBeginBalVO(idAcct);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#getAcctBalList(java.lang.Long)
	 */
	@Override
	public List<AcctBalVO> getAcctBalList(Long idAcct) throws BusinessException {
		return chartOfAcctDAO.getAcctBalList(idAcct, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#getAcctBalList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctBalVO> getAcctBeginBalList(Long idAcct, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getAcctBeginBalList(idAcct, searchParamVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#getBankBeginBal(java.lang.Long, java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public double getBankBeginBal(Long idBank, Long idAcct, Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getBankBeginBal(idBank, idAcct, idCompany, searchParamVO);
	}
	
	/**********
	 * HELPER *
	 **********/
	
	/*
	 * Re-calculate account current balance
	 * @param acctTransVO
	 */
	private void  recalculateAcctBal(AcctTransVO acctTransVO) throws BusinessException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(acctTransVO.getTransDt());
		// set from date
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date fromDate = cal.getTime();
		// set to date
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date toDate = cal.getTime();
		
		AcctBalVO acctBalVO = chartOfAcctDAO.getAcctBal(acctTransVO.getAcctId(), fromDate);
		if (acctBalVO == null) {
			cal.setTime(fromDate);
			cal.add(Calendar.MONTH, -1);
			AcctBalVO prevAcctBalVO = chartOfAcctDAO.getAcctBal(acctTransVO.getAcctId(), cal.getTime());
			if (prevAcctBalVO == null) {
				prevAcctBalVO = new AcctBalVO();
			}
			acctBalVO = new AcctBalVO();
			acctBalVO.setIdAcct(acctTransVO.getAcctId());
			acctBalVO.setDtBeginBal(fromDate);
			acctBalVO.setDebitBeginBal(prevAcctBalVO.getDebitCloseBal());
			acctBalVO.setCreditBeginBal(prevAcctBalVO.getCreditBeginBal());
			acctBalVO.setDtCloseBal(acctBalVO.getDtBeginBal());
			acctBalVO.setDebitCloseBal(acctBalVO.getDebitBeginBal());
			acctBalVO.setCreditCloseBal(acctBalVO.getCreditCloseBal());
			acctBalVO.setDtCurrentBal(acctBalVO.getDtBeginBal());
			acctBalVO.setDebitCurrentBal(acctBalVO.getDebitBeginBal());
			acctBalVO.setCreditCurrentBal(acctBalVO.getCreditBeginBal());
			insertVO(acctBalVO);
		}
		
		double totDebit = chartOfAcctDAO.getTotAcctTransDC(0, acctTransVO.getCompanyId(), acctTransVO.getAcctId(), fromDate, toDate) + acctBalVO.getDebitBeginBal();
		double totCredit = chartOfAcctDAO.getTotAcctTransDC(1, acctTransVO.getCompanyId(), acctTransVO.getAcctId(), fromDate, toDate) + acctBalVO.getCreditBeginBal();
		double totAmount = totDebit - totCredit;
		
		Date date = new Date();
		acctBalVO.setDtCloseBal(date);
		acctBalVO.setDtCurrentBal(date);
		updateAcctBal(acctBalVO, totAmount);
		
		// get balance list if there is greater than from date
		cal.setTime(fromDate);
		cal.add(Calendar.MONTH, 1);
		List<AcctBalVO> acctBalList = chartOfAcctDAO.getAcctBalList(acctTransVO.getAcctId(), cal.getTime());
		// re-caculate all balance
		if (CollectionUtils.isNotEmpty(acctBalList)) {
			AcctBalVO prevAcctBalVO = null;
			
			for (int i = 0 ; i < acctBalList.size() ; i++) {
				AcctBalVO vo = acctBalList.get(i);
				
				if (i == 0) {
					vo.setDebitBeginBal(acctBalVO.getDebitBeginBal());
					vo.setCreditBeginBal(acctBalVO.getCreditBeginBal());
					
				} else {
					vo.setDebitBeginBal(prevAcctBalVO.getDebitBeginBal());
					vo.setCreditBeginBal(prevAcctBalVO.getCreditBeginBal());
				}
				
				cal.setTime(vo.getDtCurrentBal());
				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59);
				
				totDebit = chartOfAcctDAO.getTotAcctTransDC(0, acctTransVO.getCompanyId(), acctTransVO.getAcctId(), vo.getDtBeginBal(), cal.getTime()) + vo.getDebitBeginBal();
				totCredit = chartOfAcctDAO.getTotAcctTransDC(1, acctTransVO.getCompanyId(), acctTransVO.getAcctId(), vo.getDtBeginBal(), cal.getTime()) + vo.getCreditBeginBal();
				totAmount = totDebit - totCredit;
				updateAcctBal(acctBalVO, totAmount);
				
				prevAcctBalVO = vo;
			}
		}
	}
	
	/*
	 * 
	 * @param acctBalVO
	 * @param totAmount
	 * @throws BusinessException
	 */
	private void updateAcctBal(AcctBalVO acctBalVO, double totAmount) throws BusinessException {
		if (totAmount > 0) {
			acctBalVO.setDebitCloseBal(totAmount);
			acctBalVO.setCreditCloseBal(0.0);
			acctBalVO.setDebitCurrentBal(totAmount);
			acctBalVO.setCreditCurrentBal(0.0);
			
		} else if (totAmount < 0) {
			acctBalVO.setDebitCloseBal(0.0);
			acctBalVO.setCreditCloseBal(-1 * totAmount);
			acctBalVO.setDebitCurrentBal(0.0);
			acctBalVO.setCreditCurrentBal(-1 * totAmount);
			
		} else {
			acctBalVO.setDebitCloseBal(totAmount);
			acctBalVO.setCreditCloseBal(totAmount);
			acctBalVO.setDebitCurrentBal(totAmount);
			acctBalVO.setCreditCurrentBal(totAmount);
		}
		updateVO(acctBalVO);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#updAcctTransDt(java.lang.String, java.lang.String, java.util.Date)
	 */
	@Override
	public void updAcctTransDt(String sysCd, String sysNo, Date transDt, Long companyId) throws BusinessException {
		chartOfAcctDAO.updAcctTransDt(sysCd, sysNo, transDt, companyId);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getTrdgPftLssList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getTrdgPftLssList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getTrdgPftLssList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getTrdgPftLssAdtList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getTrdgPftLssAdtList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getTrdgPftLssAdtList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getTrialBalanceList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getTrialBalanceList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getTrialBalanceList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getTrialBalanceListYear(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getTrialBalanceListYear(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getTrialBalanceListYear(idCompany, searchParamVO);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getFinPeriod1stDt(java.lang.Long)
	 */
	@Override
	public Date getFinPeriod1stDt(Long idCompany) throws BusinessException {
		return chartOfAcctDAO.getFinPeriod1stDt(idCompany);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#isClosedAcct(java.lang.Long, java.lang.Long)
	 */
	@Override
	public boolean isClosedAcct(Long idCompany, Long idAcct) throws BusinessException {
		return chartOfAcctDAO.isClosedAcct(idCompany, idAcct);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#isAcctBeginBalExisted(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public boolean isAcctBeginBalExisted(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.isAcctBeginBalExisted(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getFinPeriodYear(java.lang.Long)
	 */
	@Override
	public String getFinPeriodYear(Long idCompany) throws BusinessException {
		return chartOfAcctDAO.getFinPeriodYear(idCompany);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getFinPeriodYearList(java.lang.Long)
	 */
	@Override
	public List<String> getFinPeriodYearList(Long idCompany) throws BusinessException {
		return chartOfAcctDAO.getFinPeriodYearList(idCompany);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getBalanceSheetList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getBalanceSheetList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getBalanceSheetList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getBalanceSheetDetailsList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getBalanceSheetDetailsList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getBalanceSheetDetailsList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getBeginningBalanceList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getBeginningBalanceList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getBeginningBalanceList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getDebtorLedgerList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getDebtorLedgerList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getDebtorLedgerList(idCompany, searchParamVO);
	}
	@Override
	public List<AcctViewVO> getDebtorLedgerList2(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getDebtorLedgerList2(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getDebtorSummaryList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getDebtorSummaryList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getDebtorSummaryList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getCreditorLedgerList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getCreditorLedgerList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getCreditorLedgerList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getCreditorSummaryList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getCreditorSummaryList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getCreditorSummaryList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getJournalList(java.lang.Long)
	 */
	@Override
	public List<JournalVO> getJournalList(Long idCompany) throws BusinessException {
		return chartOfAcctDAO.getJournalList(idCompany);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getJournalEntryList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<JournalVO> getJournalEntryList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getJournalEntryList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctService#getSalesInAdvanceList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getSalesInAdvanceList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getSalesInAdvanceList(idCompany, searchParamVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.ChartOfAcctService#getChartOfAccountList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getChartOfAccountList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctDAO.getChartOfAccountList(idCompany, searchParamVO);
	}
}
