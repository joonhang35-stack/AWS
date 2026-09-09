package com.bcs.zsg.acct.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.dao.ChartOfAcctDAO;
import com.bcs.zsg.acct.vo.AcctBalVO;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.bank.dao.BankAcctDAO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.common.helper.DatesUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.dao.accttrans.AccountTransDAO;
import com.bcs.zsg.db.bterp.dao.view.accttransview.AccountTransViewDAO;

public class GeneralLedgerServiceImpl implements GeneralLedgerService {
	
	@Autowired
	private BankAcctDAO bankAcctDAO;
	@Autowired
	private ChartOfAcctDAO chartOfAcctDAO;
	@Autowired
	private AccountTransDAO accountTransDAO;

	@Autowired
	private AccountTransViewDAO accountTransViewDAO;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.GeneralLedgerService#getGLListWoBkSel(java.lang.Long)
	 */
	@Override
	public List<AcctVO> getGLListWoBkSel(Long companyId) throws BusinessException {
		
		List<BankAcctViewVO> bankAcctList = new ArrayList<BankAcctViewVO>();
		List<AcctVO> glAcctList = new ArrayList<AcctVO>();
		List<AcctVO> glDelList = new ArrayList<AcctVO>();
		
		bankAcctList = bankAcctDAO.getBankAcctList(companyId);
		glAcctList = chartOfAcctDAO.getAcctList(companyId, null);
		glDelList.addAll(glAcctList);
		
		for(BankAcctViewVO bankAcctVO : bankAcctList){
			for(AcctVO glVO : glDelList){
				if(bankAcctVO.getAcctViewVO().getId() == glVO.getId()){
					glAcctList.remove(glVO);
				}
			}
		}
		return glAcctList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.GeneralLedgerService#getGeneralLedgerListSize(java.util.Map)
	 */
	@Override
	public int getGeneralLedgerListSize(Map<String, Object> params) throws BusinessException {
		return accountTransDAO.getAccountTransListSize(mapAccountTransVOParam(params));
	}
	
	private Map<String, Object> mapAccountTransVOParam(Map<String, Object> params) {
		AcctViewVO acctViewVO = (AcctViewVO) params.get("acctViewVO");
		params.put("idAcct", (acctViewVO == null ? null : acctViewVO.getId()));

		SearchParamVO searchParamVO = (SearchParamVO) params.get("searchParamVO");
		params.remove("searchParamVO");
		if(searchParamVO != null) {
			params.put("fromDate", searchParamVO.getObj3());
			params.put("toDate", searchParamVO.getObj4());
			
			params.put("fromFPDate", searchParamVO.getFromDate());
			params.put("toFPDate", searchParamVO.getToDate());
		}
		return params;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.GeneralLedgerService#getGeneralLedgerList(java.util.Map)
	 */
	@Override
	public List<AcctTransViewVO> getGeneralLedgerList(Map<String, Object> params) throws BusinessException {
		params = mapAccountTransVOParam(params);
		AcctViewVO acctViewVO = (AcctViewVO) params.get("acctViewVO");
		
		// Search All
		if (params.get("fromDate") == null) params.put("fromDate", (Date) params.get("fromFPDate"));
		if (params.get("toDate") == null) params.put("toDate", (Date) params.get("toFPDate"));
		
		List<AcctTransVO> glListDB = accountTransDAO.getAccountTransListGeneralLedger(params);
		List<AcctTransViewVO> glList = new ArrayList<AcctTransViewVO>();
		
		// Replace correct position Debit/Credit
		for (AcctTransVO vo : glListDB) {
			AcctTransViewVO tmpVO = new AcctTransViewVO();
			try {
				PropertyUtils.copyProperties(tmpVO, vo);
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				throw new BusinessException(e);
			}
			
			if(tmpVO.getDebit() < 0.00) {
				tmpVO.setCredit(tmpVO.getDebit() * -1);
				tmpVO.setDebit(0.00);
			}
			
			if(tmpVO.getCredit() < 0.00) {
				tmpVO.setDebit(tmpVO.getCredit() * -1);
				tmpVO.setCredit(0.00);
			}
			
			glList.add(tmpVO);
		}
		
		if (CollectionUtils.isNotEmpty(glList)) {
			//Gets the total of DEBIT and CREDIT amount from the DB
			AcctTransVO tmpAcctTransVO = accountTransDAO.getAccountTransSumDebitCreditByAcctId(params);
			
			glList.get(0).setTotDebit(tmpAcctTransVO.getDebit());
			glList.get(0).setTotCredit(tmpAcctTransVO.getCredit());
		}
		
		List<AcctBalVO> acctBalList = null;
		acctViewVO.setAcctBalVO(new AcctBalVO());
		// sort begin balance, closing balance and current balance
		//acctBalList = new ArrayList<AcctBalVO>(acctViewVO.getAcctBalSet());
		SearchParamVO searchParamVO = new SearchParamVO();
		searchParamVO.setFromDate((Date) params.get("fromFPDate"));
		searchParamVO.setToDate((Date) params.get("toFPDate"));
		acctBalList = chartOfAcctDAO.getAcctBeginBalList((Long) params.get("idAcct"), searchParamVO);
		
		Double debitBeginBal = 0.00;
		Double creditBeginBal = 0.00;
		
		// Selected date don't have begin balance
		if (acctBalList.size() == 0) {
			acctBalList = chartOfAcctDAO.getAcctBeginBalAllList((Long) params.get("idAcct"));
			
			// Condition Check - If the selected date is b4 the 1st begining bal
			if (acctBalList.size() != 0) {
				// check if last beginning balance date before from date, then calculate with account trans balance
				/*if (acctBalList.get(0).getDtBeginBal().before((Date) params.get("fromDate"))) {
					
					Calendar cal = DatesUtils.getDayTimeStart(acctBalList.get(0).getDtBeginBal());
					Date fromDate = cal.getTime();
					
					cal = DatesUtils.getDayTimeEnd((Date) params.get("fromDate"));
					cal.add(Calendar.DATE, -1);
					Date toDate = cal.getTime();
					
					Map<String, Object> tmpParams = new HashMap<String, Object>();
					tmpParams.put("idAcct", params.get("idAcct"));
					tmpParams.put("fromDate", fromDate);
					tmpParams.put("toDate", toDate);
					AcctTransVO tmpAcctTransVO = accountTransDAO.getAccountTransSumDebitCreditByAcctId(tmpParams);
					//System.out.println("------ fromDate / toDate = " + fromDate + " / " + toDate);
					
					double debit = acctBalList.get(0).getDebitBeginBal() + tmpAcctTransVO.getDebit();
					double credit = acctBalList.get(0).getCreditBeginBal() + tmpAcctTransVO.getCredit();
					//System.out.println("------ debit = " + acctBalList.get(0).getDebitBeginBal() + " + " + tmpAcctTransVO.getDebit());
					//System.out.println("------ credit = " + acctBalList.get(0).getCreditBeginBal() + " + " + tmpAcctTransVO.getCredit());
					if (debit - credit >= 0) {
						debitBeginBal = debit - credit;
						creditBeginBal = 0.00;
					} else {
						debitBeginBal = 0.00;
						creditBeginBal = credit - debit;
					}
				} else {*/
					debitBeginBal = acctBalList.get(0).getDebitBeginBal();
					creditBeginBal = acctBalList.get(0).getCreditBeginBal();
				
					Date date1 = (Date) params.get("fromDate");
					Date date2 = acctBalList.get(acctBalList.size() - 1).getDtBeginBal();
					if (date1.before(date2)) {
						debitBeginBal = 0.00;
						creditBeginBal = 0.00;
					}
				//}
			}
		} else {
			debitBeginBal = acctBalList.get(0).getDebitBeginBal();
			creditBeginBal = acctBalList.get(0).getCreditBeginBal();
		}

		acctViewVO.getAcctBalVO().setDebitBeginBal(debitBeginBal);
		acctViewVO.getAcctBalVO().setCreditBeginBal(creditBeginBal);
		
		Calendar cal = DatesUtils.getDayTimeStart(acctBalList.get(0).getDtBeginBal());
		Date fromDate = cal.getTime();
		
		cal = DatesUtils.getDayTimeEnd((Date) params.get("fromDate"));
		cal.add(Calendar.DATE, -1);
		Date toDate = cal.getTime();
		
		Map<String, Object> paramsSum = new HashMap<String, Object>();
		paramsSum.put("idCompany", params.get("idCompany"));
		paramsSum.put("idAcct", params.get("idAcct"));
		paramsSum.put("fromDate", fromDate);
		paramsSum.put("toDate", toDate);
		// finDate for filter account cat 'I', 'X', 'P', 'OI'
		paramsSum.put("finDate", searchParamVO.getFromDate());
		
		//Gets the total of DEBIT and CREDIT amount from the DB
		AcctTransVO tmpAcctTransVO = accountTransDAO.getAccountBfBalance(paramsSum);
		BigDecimal beginBalBak = new BigDecimal("0.00");
		BigDecimal bfBal = new BigDecimal("0.00");
		beginBalBak = new BigDecimal(tmpAcctTransVO.getDebit().toString()).subtract(new BigDecimal(tmpAcctTransVO.getCredit().toString()));
		
		bfBal = (new BigDecimal(debitBeginBal.toString()).subtract(new BigDecimal(creditBeginBal.toString()))).add(beginBalBak);
		
		if ("I".equals(tmpAcctTransVO.getTempAcct()) || "X".equals(tmpAcctTransVO.getTempAcct())
				 || "P".equals(tmpAcctTransVO.getTempAcct()) || "OI".equals(tmpAcctTransVO.getTempAcct())) {
			bfBal = BigDecimal.ZERO;
		}
		
		// begining and closing balance
		if (bfBal.compareTo(BigDecimal.ZERO) < 0) {
			acctViewVO.getAcctBalVO().setDebitCloseBal(0.0);
			acctViewVO.getAcctBalVO().setCreditCloseBal(-1 * bfBal.doubleValue());
		} else {
			acctViewVO.getAcctBalVO().setDebitCloseBal(bfBal.doubleValue());
			acctViewVO.getAcctBalVO().setCreditCloseBal(0.0);
		}
		
		// current balance
		BigDecimal currBal = new BigDecimal("0.00");
		double debitCurr = 0.0;
		double creditCurr = 0.0;
		if (CollectionUtils.isNotEmpty(glList)) {
			currBal = bfBal.add(new BigDecimal(String.valueOf(glList.get(0).getTotDebit())).subtract(new BigDecimal(String.valueOf(glList.get(0).getTotCredit()))));
			
			if (currBal.compareTo(BigDecimal.ZERO) < 0) {
				creditCurr = -1 * currBal.doubleValue();
			} else {
				debitCurr = currBal.doubleValue();
			}
		} else {
			if (bfBal.compareTo(BigDecimal.ZERO) < 0) {
				creditCurr = -1 * bfBal.doubleValue();
			} else {
				debitCurr = bfBal.doubleValue();
			}
		}
		
		acctViewVO.getAcctBalVO().setDebitCurrentBal(debitCurr);
		acctViewVO.getAcctBalVO().setCreditCurrentBal(creditCurr);
		
		return glList;
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.GeneralLedgerService#getGLBalanceList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getGLBalanceList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return accountTransDAO.getGLBalanceList(idCompany, searchParamVO);
	}
	
	@Override
	public List<AcctViewVO> getGLBalanceSmmyExtdList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return accountTransDAO.getGLBalanceSmmyExtdList(idCompany, searchParamVO);
	}
}
