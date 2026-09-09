package com.bcs.zsg.maintenance.service;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CityVO;
import com.bcs.zsg.maintenance.vo.RegionVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public interface RegionService {
	public void addRegion(RegionVO regionVO) throws BusinessException;

	public void addCountry(CountryVO countryVO) throws BusinessException;

	public void addCity(CityVO cityVO) throws BusinessException;

	public void updateRegion(RegionVO regionVO) throws BusinessException;

	public void updateCountry(CountryVO countryVO) throws BusinessException;

	public void updateCity(CityVO cityVO) throws BusinessException;

	public void deleteRegion(RegionVO regionVO) throws BusinessException;

	public void deleteCountry(CountryVO countryVO) throws BusinessException;

	public void deleteCity(CityVO cityVO) throws BusinessException;

	public List<RegionVO> getRegionListParam(Map<String, Object> params, RegionVO regionVO) throws BusinessException;

	public List<RegionVO> getRegionList() throws BusinessException;

	public List<CountryVO> getCountryList() throws BusinessException;

	public List<CityVO> getCityList() throws BusinessException;

	public CountryVO getCountryById(Long countryId) throws BusinessException;

	public int getRegionListSize(Map<String, Object> params, RegionVO regionVO) throws BusinessException;

	public int getCountryListSize(Map<String, Object> params, RegionVO regionVO) throws BusinessException;

	public List<CountryVO> getCountryListParam(Map<String, Object> params, RegionVO regionVO) throws BusinessException;

	public int getCityListSize(Map<String, Object> params, RegionVO regionVO) throws BusinessException;

	public List<CityVO> getCityListParam(Map<String, Object> params, RegionVO regionVO) throws BusinessException;

	public List<CountryVO> getActualCountryList() throws BusinessException;

	public void updateActualCountry(Long idCountry, boolean isActualCountry) throws BusinessException;

	public CountryVO getCountryByCountryCd(String countryCd) throws BusinessException;

}
