package com.bcs.zsg.bank.dao;

import java.util.List;

import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.LookupItemVO;

public interface BankAdjustDAO extends BaseDAO {

	public List<LookupItemVO> getLookUpItemBTTList(String bankTransType) throws BusinessException;

	public AcctVO getAcctList(Long acctId) throws BusinessException;

	public List<AcctTransViewVO> getAcctTransViewTableList(String sysNo,
			String typeCd, String sysCode, Long compId) throws BusinessException;



	public List<CashBookVO> getCashBookBankAdjustList(String bankAdjust, Long bankId, SearchParamVO searchParamVO) throws BusinessException;




}
