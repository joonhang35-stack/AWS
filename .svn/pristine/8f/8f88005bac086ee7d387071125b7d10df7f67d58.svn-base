package com.bcs.zsg.bank.service;

import java.util.List;

import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;

public interface BankAdjustService {


	public void addBankAdjust(CashBookVO cashBookVO, Long idCompany, BankAcctVO bankAcctSearchVO, LookupItemVO lookupItemBT, LookupItemVO lookupItemCBT, LookupItemVO lookupItemGnDp, LookupItemVO lookupItemBTCD, AcctVO acctVO) throws BusinessException, QueueException;

	public List<LookupItemVO> getLookUpItemBTTList(String bankTransType) throws BusinessException;

	public AcctVO getAcctList(Long acctId) throws BusinessException;

	public void updateAdjust(CashBookVO cashBookVO, AcctTransViewVO acctTransCashBookVO, BankAcctVO bankAcctSearchVO, LookupItemVO lookupItemCBT, LookupItemVO lookupItemGnDp, LookupItemVO lookupItemBT, LookupItemVO lookupItemBTCD) throws BusinessException;

	public List<AcctTransViewVO> getAcctTransViewTableList(String sysNo,
			String typeCd, String sysCode, Long compId) throws BusinessException;

	public List<CashBookVO> getCashBookBankAdjustList(String bankAdjust, Long bankId, SearchParamVO searchParamVO) throws BusinessException;

}
