package com.bcs.zsg.acct.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.service.AccountService;
import com.bcs.zsg.acct.service.GeneralLedgerService;
import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;

public class GeneralLedgerBOImpl implements GeneralLedgerBO {

	@Autowired
	private GeneralLedgerService generalLedgerService;

	@Autowired
	private AccountService accountService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.GeneralLedgerBO#getGLListWoBkSel()
	 */
	@Override
	public List<AcctVO> getGLListWoBkSel(Long companyId) throws BusinessException {
		return generalLedgerService.getGLListWoBkSel(companyId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.GeneralLedgerBO#getGeneralLedgerTransactionList(java.lang.Long, com.bcs.zsg.acct.vo.AcctTransViewVO)
	 */
	@Override
	public List<AcctTransViewVO> getGeneralLedgerTransactionList(Long idCompany, AcctTransViewVO acctTransViewVO) throws BusinessException {
		acctTransViewVO.setCompanyId(idCompany);
		List<AcctTransViewVO> transactionList = accountService.getAccountTransViewList(acctTransViewVO);
		List<AcctTransViewVO> transactionListCloned = new ArrayList<AcctTransViewVO>();
		
		BigDecimal totDebit = new BigDecimal("0");
		BigDecimal totCredit = new BigDecimal("0");
		for (AcctTransViewVO vo : transactionList) {
			//use a cloned object to ensure hibernate does not update. Can be REMOVED if DAO is changed to use other 
			// framework for getting list 
			
			//*** WARNING: this transactionList is not returned as AccountTransVO type due to the additional AcctViewVO
			//    record that is queried together in the returned list.
			AcctTransViewVO tmpVO = (AcctTransViewVO) vo.clone();
			if(tmpVO.getDebit() < 0.00) {
				tmpVO.setCredit(tmpVO.getDebit() * -1);
				tmpVO.setDebit(0.00);
			}
			
			if(tmpVO.getCredit() < 0.00) {
				tmpVO.setDebit(tmpVO.getCredit() * -1);
				tmpVO.setCredit(0.00);
			}
			transactionListCloned.add(tmpVO);
			
			totDebit = totDebit.add(new BigDecimal(tmpVO.getDebit().toString()));
			totCredit = totCredit.add(new BigDecimal(tmpVO.getCredit().toString()));
		}
		transactionListCloned.get(0).setTotDebit(totDebit.doubleValue());
		transactionListCloned.get(0).setTotCredit(totCredit.doubleValue());
		
		return transactionListCloned;
	}
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.GeneralLedgerBO#getGeneralLedgerListSize(java.util.Map)
	 */
	@Override
	public int getGeneralLedgerListSize(Map<String, Object> params) throws BusinessException {
		return generalLedgerService.getGeneralLedgerListSize(params);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.GeneralLedgerBO#getGeneralLedgerList(java.util.Map)
	 */
	@Override
	public List<AcctTransViewVO> getGeneralLedgerList(Map<String, Object> params) throws BusinessException {
		return generalLedgerService.getGeneralLedgerList(params);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.GeneralLedgerBO#getGLBalanceList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getGLBalanceList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return generalLedgerService.getGLBalanceList(idCompany, searchParamVO);
	}
	
	@Override
	public List<AcctViewVO> getGLBalanceSmmyExtdList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return generalLedgerService.getGLBalanceSmmyExtdList(idCompany, searchParamVO);
	}
}
