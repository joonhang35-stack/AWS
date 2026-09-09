package com.bcs.zsg.bank.dao;

import java.util.Date;
import java.util.List;

import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;

public interface BankAcctDAO extends BaseDAO {

	/**
	 * 
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public List<BankAcctViewVO> getBankAcctList(Long idCompany) throws BusinessException;

	/**
	 * 
	 * @param idAcct
	 * @return
	 * @throws BusinessException
	 */
	public double getBankCurrentBal(Long idAcct, Date fromDate) throws BusinessException;

}
