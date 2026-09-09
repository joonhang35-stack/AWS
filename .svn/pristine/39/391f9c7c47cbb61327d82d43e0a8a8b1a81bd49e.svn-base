package com.bcs.zsg.maintenance.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.ForeignExRateService;
import com.bcs.zsg.maintenance.vo.ForeignExRateVO;
import com.bcs.zsg.purchase.vo.CountryVO;




public class ForeignExRateBOImpl implements ForeignExRateBO 
{
	@Autowired
	private ForeignExRateService foreignExRateService;

	

	@Override
	public List<ForeignExRateVO> getForeignExRateList()
			throws BusinessException {
		// TODO Auto-generated method stub
		return foreignExRateService.getForeignExRateList();
	}

	@Override
	public void deleteForeignExRate(ForeignExRateVO foreignExRateVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		foreignExRateService.deleteForeignExRate(foreignExRateVO);
	}

	@Override
	public void updateForeignExRate(ForeignExRateVO foreignExRateVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		foreignExRateService.updateForeignExRate(foreignExRateVO);
	}

	@Override
	public List<CountryVO> getCountyList() throws BusinessException {
		// TODO Auto-generated method stub
		return foreignExRateService.getCountryList();
	}

	

	
}
