package com.bcs.zsg.sales.bo;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.DebtorsStmtVO;

public interface DebtorsStmtBO {

	public int getDebtorsListSize(Map<String, Object> params) throws BusinessException;

	public List<DebtorsStmtVO> getDebtorsList(Map<String, Object> params) throws BusinessException;

	public List<DebtorsStmtVO> getDebtorStmtList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	public DebtorsStmtVO getDebtorStmtTtl(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
}
