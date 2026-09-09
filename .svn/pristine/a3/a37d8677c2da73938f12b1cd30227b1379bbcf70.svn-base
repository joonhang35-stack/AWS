package com.bcs.zsg.maintenance.web.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.ForeignExRateBO;
import com.bcs.zsg.maintenance.vo.ForeignExRateVO;

import com.bcs.zsg.purchase.vo.CountryVO;



public class ForeignExRateBean extends AppBackingBean 
{
	private static final long serialVersionUID = 1L;
	@Autowired
	private transient ForeignExRateBO foreignExRateBO;

	private ForeignExRateVO foreignExRateVO;

	private List<ForeignExRateVO> foreignExRateList;
	private List<CountryVO> countryList;

	@Override
	public void resetForm() 
	{
		foreignExRateVO = new ForeignExRateVO();	
	}

	public void init() throws BusinessException 
	{
		try {
			resetForm();
			loadForeignExRate();

		}catch (Throwable t) {
			errorResult(t);
		}
	}

	private void loadForeignExRate() throws BusinessException {
		try {
			foreignExRateList=foreignExRateBO.getForeignExRateList();
			countryList=foreignExRateBO.getCountyList();
			resetForm();

		}catch (Throwable t) {
			errorResult(t);
		}
	}

	public void onForeignExRateSelected(ForeignExRateVO foreignExRateVO) throws BusinessException {
		try {
			this.foreignExRateVO = foreignExRateVO;

		}catch (Throwable t) {
			errorResult(t);
		}
	}

	public void deleteForeignExRate() throws BusinessException{
		try {
			foreignExRateBO.deleteForeignExRate(foreignExRateVO);
			loadForeignExRate();

		} catch (Throwable t){
			errorResult(t);
		}
	}

	public void editForeignExRate() throws BusinessException{
		try {
			foreignExRateBO.updateForeignExRate(foreignExRateVO);
			loadForeignExRate();
			resetForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public ForeignExRateVO getForeignExRateVO() 
	{
		return foreignExRateVO;
	}

	public void setForeignExRateVO(ForeignExRateVO foreignExRateVO) 
	{
		this.foreignExRateVO = foreignExRateVO;
	}



	public List<ForeignExRateVO> getForeignExRateList() {
		return foreignExRateList;
	}

	public void setForeignExRateList(List<ForeignExRateVO> foreignExRateList) {
		this.foreignExRateList = foreignExRateList;
	}

	public List<CountryVO> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryVO> countryList) {
		this.countryList = countryList;
	}
}
