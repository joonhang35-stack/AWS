package com.bcs.zsg.db.bterp.dao.view.accttransview;

import java.util.List;

import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;

public interface AccountTransViewDAO extends BaseDAO {

	/**
	 * 
	 * @param idCompany
	 * @param acctTransViewVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctTransViewVO> getAccountTransViewList(AcctTransViewVO acctTransViewVO) throws BusinessException;

	/**
	 * 
	 * @param idCompany
	 * @param acctViewVO
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctTransViewVO> getAccountTransViewList(Long idCompany, AcctViewVO acctViewVO, SearchParamVO searchParamVO) 
			throws BusinessException;
	
}
