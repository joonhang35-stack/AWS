package com.bcs.zsg.acct.bo;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.core.exception.BusinessException;

public interface AccountBO {

	/*	
	 * Lazy Load Model
	 */
	public AcctVO getAcctVO(Long idAcct) throws BusinessException;
	
	public int getAccountListSize(Map<String, Object> params) throws BusinessException;

	public List<AcctVO> getAccountList(Map<String, Object> params) throws BusinessException;

}
