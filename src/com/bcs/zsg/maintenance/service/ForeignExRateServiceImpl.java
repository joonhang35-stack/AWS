package com.bcs.zsg.maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.dao.ForeignExRateDAO;
import com.bcs.zsg.maintenance.vo.ForeignExRateVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public class ForeignExRateServiceImpl implements ForeignExRateService {
	@Autowired
	private ForeignExRateDAO foreignExRateDAO;

	@Override
	public List<ForeignExRateVO> getForeignExRateList()
			throws BusinessException {
		// TODO Auto-generated method stub
		return foreignExRateDAO.getForeignExRateList();
	}

	@Override
	public void deleteForeignExRate(ForeignExRateVO foreignExRateVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		foreignExRateDAO.delete(foreignExRateVO);
	}

	@Override
	public void updateForeignExRate(ForeignExRateVO foreignExRateVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		foreignExRateDAO.update(foreignExRateVO);
	}

	@Override
	public List<CountryVO> getCountryList() throws BusinessException {
		// TODO Auto-generated method stub
		return foreignExRateDAO.getCountryList();
	}

	

	
	
	
	
	
}
