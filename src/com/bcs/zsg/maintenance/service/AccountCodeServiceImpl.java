package com.bcs.zsg.maintenance.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.maintenance.dao.AccountCodeDAO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.product.vo.TourDepItemVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.maintenance.vo.AccountCodeConfigVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class AccountCodeServiceImpl implements AccountCodeService {
	@Autowired
	private AccountCodeDAO accountCodeDAO;

	@Override
	public List<TourDepItemVO> getTourDepItemVOList(Long tourDepId)
			throws BusinessException {
		return accountCodeDAO.getTourDepItemVOList(tourDepId);
	}


	@Override
	public AcctVO getAccountVO(Long idAcct)
			throws BusinessException {
		// TODO Auto-generated method stub
		return accountCodeDAO.getAccountVO(idAcct);
	}


	public TourDepartureVO getTourDepById(Long idTourDep) throws BusinessException {

	return accountCodeDAO.getTourDepById(idTourDep);
	}



	public  TourPackageVO getTourPkgById(Long idTourPkg) throws BusinessException {

	return accountCodeDAO.getTourPkgById(idTourPkg);
	}



	public TourThemeVO getTourThemeById(Long idTourTheme) throws BusinessException {
		return accountCodeDAO.getTourThemeById(idTourTheme);
	}

	public CountryVO getCountryById(Long idCountry) throws BusinessException {
		return accountCodeDAO.getCountryById(idCountry);
	}

	public List<CountryVO> getCountryList() throws BusinessException {

	return accountCodeDAO.getCountryList();
	}

	public List<CompanyVO> getCompanyList(String secUser) throws BusinessException {

	return accountCodeDAO.getCompanyList(secUser);
	}

	public List<TourDepartureVO> getTourDepList(Long idTourPkg) throws BusinessException {

	return accountCodeDAO.getTourDepList(idTourPkg);
	}

	public List<AcctVO> getAccCodeSubCode(String strAutoComplete,String companyId) throws BusinessException {
		return accountCodeDAO.getAccCodeSubCode(strAutoComplete,  companyId);
	}

	public AcctVO getAccountVObyAccCodeSubCode(Long idCompany, String strTemp) throws BusinessException  {
		return accountCodeDAO.getAccountVObyAccCodeSubCode(idCompany, strTemp);
	}


	@Override
	public void updateVO(BaseVO vo) throws BusinessException {
		accountCodeDAO.update(vo);
	}


	public List<TourDepartureVO> getTourDepListWithOrder(Long idTourPkg,String orderBy) throws BusinessException {
		return accountCodeDAO.getTourDepListWithOrder( idTourPkg, orderBy);

	}


	public List<AcctVO> getAccCodeSubCodeList(int id_acct_cat)  throws BusinessException {
		return accountCodeDAO.getAccCodeSubCodeList( id_acct_cat);
	}


	public List<TourDepItemVO> getTourDepItemNotComplete(Long idTourDep) throws BusinessException {
		return accountCodeDAO.getTourDepItemNotComplete( idTourDep);

	}


	public List<TourDepartureVO> getTourDepListWithItem() throws BusinessException {
		return accountCodeDAO.getTourDepListWithItem();

	}

	public List<TourDepartureVO> getTourDepListAccountCode(SearchParamVO searchParamVO) throws BusinessException {
		return accountCodeDAO.getTourDepListAccountCode(searchParamVO);
	}

	@Override
	public List<TourDepartureVO> getTourDepCruiseListWithItem() throws BusinessException {
		return accountCodeDAO.getTourDepCruiseListWithItem();
	}


	/**
	 * Account Code Config
	 */

	@Override
	public List<AccountCodeConfigVO> getAccountCodeConfigList() throws BusinessException {

		List<AccountCodeConfigVO> tempList = accountCodeDAO.getAccountCodeConfigList();

		Collections.sort(tempList, new Comparator<AccountCodeConfigVO>() {
			@Override
			public int compare(AccountCodeConfigVO u1, AccountCodeConfigVO u2) {
				int count = 0;

				if (u1.getGrouping() > u2.getGrouping())
					count = 1;
				else if (u1.getGrouping() < u2.getGrouping())
					count = -1;

				return count;
			}
		});

		return tempList;
	}

	@Override
	public void updateAccountCodeConfig(List<AccountCodeConfigVO> accountCodeConfigVOList) throws BusinessException {
		for (AccountCodeConfigVO vo : accountCodeConfigVOList) {
			if (vo.getId() == null) {
				vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
				if (vo.getCode()==null) {
					vo.setCode(UUID.randomUUID().toString());
				}
				accountCodeDAO.insert(vo);
			} else {
				accountCodeDAO.update(vo);
			}
		}
	}


	@Override
	public void deleteAccountCodeConfig(AccountCodeConfigVO accountCodeConfigVO) throws BusinessException {
		accountCodeConfigVO.setStatusCode(BaseConstant.STATUS_DELETED);
		accountCodeDAO.update(accountCodeConfigVO);
	}

	@Override
	public List<AccountCodeConfigVO> getAccountCodeConfigList(String typeCode) throws BusinessException {
		List<AccountCodeConfigVO> tempList = accountCodeDAO.getAccountCodeConfigList(typeCode);

		Collections.sort(tempList, new Comparator<AccountCodeConfigVO>() {
			@Override
			public int compare(AccountCodeConfigVO u1, AccountCodeConfigVO u2) {
				int count = 0;

				if (u1.getGrouping() > u2.getGrouping())
					count = 1;
				else if (u1.getGrouping() < u2.getGrouping())
					count = -1;

				return count;
			}
		});
		return tempList;
	}

	@Override
	public AccountCodeConfigVO getAccountCodeConfig(Map<String, Object> params) throws BusinessException {
		return accountCodeDAO.getAccountCodeConfig(params);
	}
}
