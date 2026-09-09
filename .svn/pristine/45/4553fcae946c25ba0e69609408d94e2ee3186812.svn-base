package com.bcs.zsg.maintenance.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.RegionService;
import com.bcs.zsg.maintenance.vo.CityVO;
import com.bcs.zsg.maintenance.vo.RegionVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public class RegionBOImpl implements RegionBO {
	@Autowired
	private RegionService regionService;

	@Override
	public void addRegion(RegionVO regionVO) throws BusinessException {
		// TODO Auto-generated method stub
		regionService.addRegion(regionVO);
	}

	@Override
	public List<RegionVO> getRegionListParam(Map<String, Object> params, RegionVO regionVO) throws BusinessException {
		// TODO Auto-generated method stub
		return regionService.getRegionListParam(params, regionVO);
	}

	@Override
	public void addCountry(CountryVO countryVO) throws BusinessException {
		// TODO Auto-generated method stub
		regionService.addCountry(countryVO);
	}

	@Override
	public void addCity(CityVO cityVO) throws BusinessException {
		// TODO Auto-generated method stub
		regionService.addCity(cityVO);
	}

	@Override
	public void updateRegion(RegionVO regionVO) throws BusinessException {
		// TODO Auto-generated method stub
		regionService.updateRegion(regionVO);
	}

	@Override
	public void deleteRegion(RegionVO regionVO) throws BusinessException {
		// TODO Auto-generated method stub
		regionService.deleteRegion(regionVO);
	}

	@Override
	public void updateCountry(CountryVO countryVO) throws BusinessException {
		// TODO Auto-generated method stub
		regionService.updateCountry(countryVO);
	}

	@Override
	public void deleteCountry(CountryVO countryVO) throws BusinessException {
		// TODO Auto-generated method stub
		regionService.deleteCountry(countryVO);
	}

	@Override
	public void updateCity(CityVO cityVO) throws BusinessException {
		// TODO Auto-generated method stub
		regionService.updateCity(cityVO);
	}

	@Override
	public void deleteCity(CityVO cityVO) throws BusinessException {
		// TODO Auto-generated method stub
		regionService.deleteCity(cityVO);
	}

	@Override
	public List<RegionVO> getRegionList() throws BusinessException {
		// TODO Auto-generated method stub
		return regionService.getRegionList();
	}

	@Override
	public List<CountryVO> getCountryList() throws BusinessException {
		// TODO Auto-generated method stub
		return regionService.getCountryList();
	}

	@Override
	public List<CountryVO> getActualCountryList() throws BusinessException {
		// TODO Auto-generated method stub
		return regionService.getActualCountryList();
	}

	@Override
	public List<CityVO> getCityList() throws BusinessException {
		return regionService.getCityList();
	}

	@Override
	public CountryVO getCountryById(Long countryId) throws BusinessException {
		// TODO Auto-generated method stub
		return regionService.getCountryById(countryId);
	}

	@Override
	public int getRegionListSize(Map<String, Object> params, RegionVO regionVO) throws BusinessException {
		// TODO Auto-generated method stub
		return regionService.getRegionListSize(params, regionVO);
	}

	@Override
	public int getCountryListSize(Map<String, Object> params, RegionVO regionVO) throws BusinessException {
		// TODO Auto-generated method stub
		return regionService.getCountryListSize(params, regionVO);
	}

	@Override
	public int getCityListSize(Map<String, Object> params, RegionVO regionVO) throws BusinessException {
		// TODO Auto-generated method stub
		return regionService.getCityListSize(params, regionVO);
	}

	@Override
	public List<CountryVO> getCountryListParam(Map<String, Object> params, RegionVO regionVO) throws BusinessException {
		// TODO Auto-generated method stub
		return regionService.getCountryListParam(params, regionVO);
	}

	@Override
	public List<CityVO> getCityListParam(Map<String, Object> params, RegionVO regionVO) throws BusinessException {
		// TODO Auto-generated method stub
		return regionService.getCityListParam(params, regionVO);
	}

	@Override
	public void updateActualCountry(Long idCountry, boolean isActualCountry) throws BusinessException {
		regionService.updateActualCountry(idCountry, isActualCountry);
	}
	
	@Override
	public CountryVO getCountryByCountryCd(String countryCd) throws BusinessException {
		return regionService.getCountryByCountryCd(countryCd);
	}
}
