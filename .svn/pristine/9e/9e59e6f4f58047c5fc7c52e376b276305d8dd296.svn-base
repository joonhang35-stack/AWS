package com.bcs.zsg.maintenance.bo;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.CompanyTaxVO;
import com.bcs.zsg.maintenance.service.CorporateProfileService;
import com.bcs.zsg.maintenance.vo.CompanyAddressVO;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyNameVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public class CorporateProfileBOImpl implements CorporateProfileBO 
{
	@Autowired
	private CorporateProfileService corporateProfileService;

	@Override
	public void addCompany(CompanyVO companyVO, CompanyTaxVO companyTaxVO) throws BusinessException {
		corporateProfileService.addCompany(companyVO, companyTaxVO);
	}

	@Override
	public List<CountryVO> getCountryList() throws BusinessException {
		return corporateProfileService.getCountryList();
	}

	@Override
	public void addCompanyContact(CompanyContactVO companyContactVO)
			throws BusinessException {
		corporateProfileService.addCompanyContact(companyContactVO);
	}

	@Override
	public void editContact(CompanyContactVO companyContactVO)
			throws BusinessException {
		corporateProfileService.editContact(companyContactVO);
	}

	@Override
	public void deleteContact(CompanyContactVO companyContactVO)
			throws BusinessException {
		corporateProfileService.deleteContact(companyContactVO);
	}

	@Override
	public List<CompanyVO> getCompanyList() throws BusinessException {
		return corporateProfileService.getCompanyList();
	}

	@Override
	public void editCompany(CompanyVO companyVO) throws BusinessException {
		corporateProfileService.editCompany(companyVO);
	}

	@Override
	public void deleteCompany(CompanyVO companyVO) throws BusinessException {
		corporateProfileService.deleteCompany(companyVO);
	}

	@Override
	public void updateCompany(CompanyVO companyVO, CompanyTaxVO companyTaxVO, CompanyAddressVO companyAddressVO) throws BusinessException{
		corporateProfileService.updateCompany(companyVO, companyTaxVO,companyAddressVO);
	}

	@Override
	public List<CompanyContactVO> getCompanyContactList(Long companyid)
			throws BusinessException {
		return corporateProfileService.getCompanyContactList(companyid);
	}
	
	@Override
	public List<CompanyNameVO> getCompanyNameList(Long companyid)
			throws BusinessException {
		return corporateProfileService.getCompanyNameList(companyid);
	}
	
	@Override
	public void deleteCompanyList(CompanyVO companyVO, CompanyTaxVO companyTaxVO,
			CompanyAddressVO companyAddressVO,
			List<CompanyContactVO> companycontactList,List<SystemNumberGenerationVO> sysNumGenList) throws BusinessException {
		corporateProfileService.deleteCompanyList(companyVO,companyTaxVO,companyAddressVO,companycontactList,sysNumGenList);
	}

	@Override
	public AcctViewVO getAcctViewListId(String code,String subCode)
			throws BusinessException {
		return corporateProfileService.getAcctViewListId(code,subCode);
	}

	@Override
	public AcctVO getAcctList(Long idAcctSales) throws BusinessException {
		return corporateProfileService.getAcctList(idAcctSales);
	}

	@Override
	public void updContactList(List<CompanyContactVO> addContList,
			List<CompanyContactVO> updContList,
			List<CompanyContactVO> delContList, Long idCompany) throws BusinessException {
		corporateProfileService.updContactList(addContList,updContList,delContList,idCompany);
	}
	
	@Override
	public void updNameList(List<CompanyNameVO> addNameList, 
			List<CompanyNameVO> updNameList,
			List<CompanyNameVO> delNameList, Long idCompany) throws BusinessException {
		corporateProfileService.updNameList(addNameList, updNameList, delNameList, idCompany);
	}
	
	@Override
	public CompanyTaxVO getCompanyTax(Long id) throws BusinessException {
		return corporateProfileService.getCompanyTax(id);
	}
	
	@Override
	public CompanyAddressVO getCompanyAddress(Long id) throws BusinessException {
		return corporateProfileService.getCompanyAddress(id);
	}

	@Override
	public List<SystemNumberGenerationVO> getSysNumGenList(Long id)
			throws BusinessException {
		return corporateProfileService.getSysNumGenList(id);
	}

	@Override
	public CompanyVO getCompanyDetails(Long companyId) throws BusinessException {
		return corporateProfileService.getCompanyDetails(companyId);
	}

	@Override
	public boolean getAcctIdCompanyList(Long id) throws BusinessException {
		return corporateProfileService.getAcctIdCompanyList(id);
	}

	@Override
	public boolean getAcctSubCatIdCompList(Long id)
			throws BusinessException {
		return corporateProfileService.getAcctSubCatIdCompList(id);
	}

	@Override
	public boolean getAcctTransIdCompList(Long id)
			throws BusinessException {
		return corporateProfileService.getAcctTransIdCompList(id);
	}

	@Override
	public boolean getBankIdCompList(Long id) throws BusinessException {
		return corporateProfileService.getBankIdCompList(id);
	}


	@Override
	public boolean getCustIdCompList(Long id) throws BusinessException {
		return corporateProfileService.getCustIdCompList(id);
	}

	@Override
	public boolean getEmpIdCompList(Long id) throws BusinessException {
		return corporateProfileService.getEmpIdCompList(id);
	}

	@Override
	public boolean getExOrderBillIdCompList(Long id)
			throws BusinessException {
		return corporateProfileService.getExOrderBillIdCompList(id);
	}

	@Override
	public boolean getFinancialPeriodIdCompList(Long id)
			throws BusinessException {
		return corporateProfileService.getFinancialPeriodIdCompList(id);
	}

	@Override
	public boolean getFinPeriodLockIdCompList(Long id)
			throws BusinessException {
		return corporateProfileService.getFinPeriodLockIdCompList(id);
	}

	@Override
	public boolean getInvIdCompList(Long id) throws BusinessException {
		return corporateProfileService.getInvIdCompList(id);
	}

	@Override
	public boolean getInvHisIdCompList(Long id)
			throws BusinessException {
		return corporateProfileService.getInvHisIdCompList(id);
	}

	@Override
	public boolean getBookingIdCompList(Long id)
			throws BusinessException {
		return corporateProfileService.getBookingIdCompList(id);
	}

	@Override
	public boolean getTourDepItemIdCompList(Long id)
			throws BusinessException {
		return corporateProfileService.getTourDepItemIdCompList(id);
	}

	@Override
	public boolean getExOrderList(Long id) throws BusinessException {
		return corporateProfileService.getExOrderList(id);
	}

	@Override
	public boolean getInvoiceAndExchangeOrderList(Long id)
			throws BusinessException {
		return corporateProfileService.getInvoiceAndExchangeOrderList(id);
	}

	@Override
	public boolean getJournalList(Long id) throws BusinessException {
		return corporateProfileService.getJournalList(id);
	}

	@Override
	public boolean getSupplierList(Long id) throws BusinessException {
		return corporateProfileService.getSupplierList(id);
	}

	@Override
	public HashMap<String, Object> getReportTitle(Long idCompany) throws BusinessException {
		return corporateProfileService.getReportTitle(idCompany);
	}
	
	@Override
	public HashMap<String, Object> getReportTitle(CompanyVO companyVO) throws BusinessException {
		return corporateProfileService.getReportTitle(companyVO);
	}	

}
