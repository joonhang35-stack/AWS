package com.bcs.zsg.acct.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.service.AccountService;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.core.exception.BusinessException;

public class AccountBOImpl implements AccountBO {

	@Autowired
	private AccountService accountService;
	
	@Override
	public AcctVO getAcctVO(Long idAcct) throws BusinessException {
		return accountService.getAcctVO(idAcct);
	}
	
	@Override
	public int getAccountListSize(Map<String, Object> params) throws BusinessException {
		return accountService.getAccountListSize(params);
	}

	@Override
	public List<AcctVO> getAccountList(Map<String, Object> params) throws BusinessException {
		return accountService.getAccountList(params);
	}
}
