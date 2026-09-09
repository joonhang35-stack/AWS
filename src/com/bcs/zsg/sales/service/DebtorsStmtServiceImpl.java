package com.bcs.zsg.sales.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.dao.DebtorsStmtDAO;
import com.bcs.zsg.sales.vo.DebtorsStmtVO;

@Service
public class DebtorsStmtServiceImpl implements DebtorsStmtService {

	@Autowired
	private DebtorsStmtDAO debtorsStmtDao;

	@Override
	public int getDebtorsListSize(Map<String, Object> params)
			throws BusinessException {
		// TODO Auto-generated method stub
		return debtorsStmtDao.getDebtorsListSize(params);
	}

	@Override
	public List<DebtorsStmtVO> getDebtorsList(Map<String, Object> params)
			throws BusinessException {
		// TODO Auto-generated method stub
		return debtorsStmtDao.getDebtorsList(params);
	}

	@Override
	public List<DebtorsStmtVO> getDebtorStmtList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return debtorsStmtDao.getDebtorStmtList(idCompany, searchParamVO);
	}
	
	@Override
	public DebtorsStmtVO getDebtorStmtTtl(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return debtorsStmtDao.getDebtorStmtTtl(idCompany, searchParamVO);
	}
}
