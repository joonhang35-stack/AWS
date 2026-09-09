package com.bcs.zsg.maintenance.service;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.maintenance.vo.AccountCodeConfigVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.product.vo.TourDepItemVO; 
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.common.vo.SearchParamVO;

public interface AccountCodeService {

	public List<TourDepItemVO> getTourDepItemVOList(Long tourDepId) throws BusinessException; 
	public  AcctVO  getAccountVO(Long idAcct) throws BusinessException; 
	public TourDepartureVO getTourDepById(Long idTourDep) throws BusinessException ;
	public List<CountryVO> getCountryList() throws BusinessException ;
	public List<CompanyVO> getCompanyList(String secUser) throws BusinessException ;
	public List<TourDepartureVO> getTourDepList(Long idTourPkg) throws BusinessException ;
	public  TourPackageVO getTourPkgById(Long idTourPkg) throws BusinessException ;
	public TourThemeVO getTourThemeById(Long idTourTheme) throws BusinessException ;
	public CountryVO getCountryById(Long idCountry) throws BusinessException ;

	public List<AcctVO> getAccCodeSubCode(String strAutoComplete,String companyId) throws BusinessException ;
	public AcctVO getAccountVObyAccCodeSubCode(Long idCompany, String strTemp) throws BusinessException  ;
	public void updateVO(BaseVO vo) throws BusinessException;
	public List<TourDepartureVO> getTourDepListWithOrder(Long idTourPkg,String orderBy) throws BusinessException ;
	public List<AcctVO> getAccCodeSubCodeList(int id_acct_cat)  throws BusinessException ;
	public List<TourDepItemVO> getTourDepItemNotComplete(Long idTourDep) throws BusinessException ;
	public List<TourDepartureVO> getTourDepListWithItem() throws BusinessException ;
	public List<TourDepartureVO> getTourDepListAccountCode(SearchParamVO searchParamVO) throws BusinessException;

	public List<TourDepartureVO> getTourDepCruiseListWithItem() throws BusinessException;
	
	/**
	 * Account Code Config
	 */
	public List<AccountCodeConfigVO> getAccountCodeConfigList() throws BusinessException;
	public void updateAccountCodeConfig(List<AccountCodeConfigVO> accountCodeConfigVOList) throws BusinessException;
	public void deleteAccountCodeConfig(AccountCodeConfigVO accountCodeConfigVO) throws BusinessException;
	
	public List<AccountCodeConfigVO> getAccountCodeConfigList(String typeCode) throws BusinessException;
	public AccountCodeConfigVO getAccountCodeConfig(Map<String, Object> params) throws BusinessException;
	
}
