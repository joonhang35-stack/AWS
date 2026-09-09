package com.bcs.zsg.db.bterp.dao.acct;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;

public interface AccountDAO extends BaseDAO {

	public List<AcctVO> getAcctList(Long idCompany, String strAutoComplete) throws BusinessException;
	public List<AcctVO> getAcctListAutoComplete(Long idCompany, String strAutoComplete) throws BusinessException;
	public List<AcctVO> getAcctListAutoComplete(Long idCompany, String strAutoComplete, 
												boolean restrictNoSubCode) throws BusinessException;
		
	public AcctVO getAcctVO(Long idAcct) throws BusinessException;
	public boolean isAcctValid(AcctVO acctVO) throws BusinessException;
	

	public int getAcctListSize(Map<String, Object> params) throws BusinessException;
	public List<AcctVO> getAcctList(Map<String, Object> params) throws BusinessException;

	public AcctVO getAccountLiabilityById(Long Id);
	
}
