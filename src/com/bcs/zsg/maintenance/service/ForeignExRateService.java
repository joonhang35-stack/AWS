package com.bcs.zsg.maintenance.service;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.ForeignExRateVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public interface ForeignExRateService 
{

	public List<ForeignExRateVO> getForeignExRateList() throws BusinessException;

	public void deleteForeignExRate(ForeignExRateVO foreignExRateVO) throws BusinessException;

	public void updateForeignExRate(ForeignExRateVO foreignExRateVO) throws BusinessException;

	public List<CountryVO> getCountryList() throws BusinessException;
	

}
