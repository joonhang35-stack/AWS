package com.bcs.zsg.maintenance.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CityVO;
import com.bcs.zsg.maintenance.vo.RegionVO;
import com.bcs.zsg.purchase.vo.CountryVO;
public interface RegionDAO extends BaseDAO 
{
	public List<RegionVO> getRegionListParam(Map<String, Object> params, RegionVO regionVO) throws BusinessException;
	public List<RegionVO> getRegionList() throws BusinessException;
	public List<CountryVO> getCountryList() throws BusinessException;
	public List<CityVO> getCityList() throws BusinessException;
	public CountryVO getCountryById(Long countryId) throws BusinessException ;
	public int getRegionListSize(Map<String, Object> params, RegionVO regionVO) throws BusinessException;
	public int getCountryListSize(Map<String, Object> params, RegionVO regionVO) throws BusinessException;
	public List<CountryVO> getCountryListParam(Map<String, Object> params,
			RegionVO regionVO) throws BusinessException;
	public int getCityListSize(Map<String, Object> params, RegionVO regionVO)
			throws BusinessException;
	public List<CityVO> getCityListParam(Map<String, Object> params,
			RegionVO regionVO) throws BusinessException;
	public CountryVO getCountryByThreeLetterCode(String threeLetterCode);
	public List<CountryVO> getActualCountryList() throws BusinessException;
	public void updateActualCountry(Long idCountry, boolean isActualCountry) throws BusinessException;
	public CountryVO getCountryByCountryCd(String countryCd) throws BusinessException;

}