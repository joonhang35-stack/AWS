package com.bcs.zsg.sales.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.service.DebtorsStmtService;
import com.bcs.zsg.sales.vo.DebtorsStmtVO;


public class DebtorsStmtBOImpl implements DebtorsStmtBO {
	
	@Autowired DebtorsStmtService debtorsStmtService;

	@Override
	public int getDebtorsListSize(Map<String, Object> params)
			throws BusinessException {
		// TODO Auto-generated method stub
		return debtorsStmtService.getDebtorsListSize(params);
	}

	@Override
	public List<DebtorsStmtVO> getDebtorsList(Map<String, Object> params)
			throws BusinessException {
		// TODO Auto-generated method stub
		return debtorsStmtService.getDebtorsList(params);
	}

	@Override
	public List<DebtorsStmtVO> getDebtorStmtList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return debtorsStmtService.getDebtorStmtList(idCompany, searchParamVO);
	}
	
	@Override
	public DebtorsStmtVO getDebtorStmtTtl(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return debtorsStmtService.getDebtorStmtTtl(idCompany, searchParamVO);
	}
}
