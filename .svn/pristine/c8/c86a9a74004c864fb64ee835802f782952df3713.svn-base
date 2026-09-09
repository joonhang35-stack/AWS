package com.bcs.zsg.maintenance.bo;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.maintenance.service.AccountCodeService;
import com.bcs.zsg.product.vo.TourDepItemVO;  
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.maintenance.vo.AccountCodeConfigVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class AccountCodeBOImpl implements AccountCodeBO {
	
	@Autowired
	private AccountCodeService accountCodeService;
	
	
	@Override
	public List<TourDepItemVO> getTourDepItemVOList(Long tourDepId) throws BusinessException {
		return accountCodeService.getTourDepItemVOList(tourDepId);
	}
 
	public AcctVO getAccountVO(Long idAcct) throws BusinessException {
		return accountCodeService.getAccountVO(  idAcct);
	}
	
	@Override
	public TourDepartureVO getTourDepById(Long idTourDep) throws BusinessException {
		return accountCodeService.getTourDepById(  idTourDep); 
	}

	public  TourPackageVO getTourPkgById(Long idTourPkg) throws BusinessException {
		return accountCodeService.getTourPkgById(idTourPkg); 
	}

	public  TourThemeVO getTourThemeById(Long idTourTheme) throws BusinessException {
		return accountCodeService.getTourThemeById(idTourTheme); 
	}

	public CountryVO getCountryById(Long idCountry) throws BusinessException {
		return accountCodeService.getCountryById(idCountry); 
		
	}
	public List<CountryVO> getCountryList() throws BusinessException {
		return accountCodeService.getCountryList(); 
		
	}

	public List<CompanyVO> getCompanyList(String secUser) throws BusinessException {
		return accountCodeService.getCompanyList(secUser); 
		
	}

	public List<TourDepartureVO> getTourDepList(Long idTourPkg) throws BusinessException {
		return accountCodeService.getTourDepList(idTourPkg); 
		
	}
	
	public List<AcctVO> getAccCodeSubCode(String strAutoComplete,String companyId) throws BusinessException {
		return accountCodeService.getAccCodeSubCode(strAutoComplete,  companyId); 
		
	}

	public AcctVO getAccountVObyAccCodeSubCode(Long idCompany, String strTemp) throws BusinessException  { 
		return accountCodeService.getAccountVObyAccCodeSubCode(idCompany, strTemp); 
	} 

	@Override
	public void updAccountCode(AddUpdDelVO tourDepItemAUDVO) throws BusinessException {

		// update tour dep item
		if (CollectionUtils.isNotEmpty(tourDepItemAUDVO.getUpdList())) {
			for (Object obj : tourDepItemAUDVO.getUpdList()) updateVO((TourDepItemVO) obj);
		} 
		  
	}
	
	@Override
	public void updateVO(BaseVO vo) throws BusinessException {
		accountCodeService.updateVO(vo);
	}


	public List<TourDepartureVO> getTourDepListWithOrder(Long idTourPkg,String orderBy) throws BusinessException {
		return accountCodeService.getTourDepListWithOrder(idTourPkg,orderBy);
		
	}
	

	public List<AcctVO> getAccCodeSubCodeList(int id_acct_cat)  throws BusinessException {
		return accountCodeService.getAccCodeSubCodeList(id_acct_cat);
		
	}

	public List<TourDepItemVO> getTourDepItemNotComplete(Long idTourDep) throws BusinessException {
		return accountCodeService.getTourDepItemNotComplete(idTourDep);
	
	}

	public List<TourDepartureVO> getTourDepListWithItem() throws BusinessException {
		return accountCodeService.getTourDepListWithItem();
	}
	
	public List<TourDepartureVO> getTourDepListAccountCode(SearchParamVO searchParamVO) throws BusinessException {
		return accountCodeService.getTourDepListAccountCode(searchParamVO);
	}

	@Override
	public List<TourDepartureVO> getTourDepCruiseListWithItem() throws BusinessException {
		return accountCodeService.getTourDepCruiseListWithItem();
	}
	
	/**
	 * Account Code Config
	 */
	public List<AccountCodeConfigVO> getAccountCodeConfigList() throws BusinessException {
		return accountCodeService.getAccountCodeConfigList();
	}
	
	public void updateAccountCodeConfig(List<AccountCodeConfigVO> accountCodeConfigVOList) throws BusinessException {
		accountCodeService.updateAccountCodeConfig(accountCodeConfigVOList);
	}

	@Override
	public void deleteAccountCodeConfig(AccountCodeConfigVO accountCodeConfigVO) throws BusinessException {
		accountCodeService.deleteAccountCodeConfig(accountCodeConfigVO);
	}

	public List<AccountCodeConfigVO> getAccountCodeConfigList(String typeCode) throws BusinessException {
		return accountCodeService.getAccountCodeConfigList(typeCode);
	}
}

