package com.bcs.zsg.maintenance.bo;

import java.util.HashMap;
import java.util.List;

import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.CompanyTaxVO;
import com.bcs.zsg.maintenance.vo.CompanyAddressVO;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyNameVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public interface CorporateProfileBO {
	
	public List<CountryVO> getCountryList() throws BusinessException;

	public List<CompanyVO> getCompanyList() throws BusinessException;
	
	public void updContactList(List<CompanyContactVO> addContList,
			List<CompanyContactVO> updContList,
			List<CompanyContactVO> delContList, Long idCompany) throws BusinessException;
	
	public void updNameList(List<CompanyNameVO> addNameList, 
			List<CompanyNameVO> updNameList,
			List<CompanyNameVO> delNameList, Long idCompany) throws BusinessException;

	public void updateCompany(CompanyVO companyVO, CompanyTaxVO companyTaxVO, CompanyAddressVO companyAddressVO) throws BusinessException;

	public List<CompanyContactVO> getCompanyContactList(Long companyid) throws BusinessException;
	
	public List<CompanyNameVO> getCompanyNameList(Long companyid) throws BusinessException;
	
	public List<SystemNumberGenerationVO> getSysNumGenList(Long id) throws BusinessException;

	public CompanyTaxVO getCompanyTax(Long id) throws BusinessException;
	
	public CompanyAddressVO getCompanyAddress(Long id) throws BusinessException;
	
	public void addCompany(CompanyVO companyVO, CompanyTaxVO companyTaxVO) throws BusinessException;

	public AcctVO getAcctList(Long idAcctSales) throws BusinessException;

	public void deleteCompanyList(CompanyVO companyVO, CompanyTaxVO companyTaxVO,
			CompanyAddressVO companyAddressVO,
			List<CompanyContactVO> companycontactList, List<SystemNumberGenerationVO> sysNumGenList) throws BusinessException;
	
	public void addCompanyContact(CompanyContactVO companyContactVO) throws BusinessException;

	public void editContact(CompanyContactVO companyContactVO) throws BusinessException;

	public void deleteContact(CompanyContactVO companyContactVO) throws BusinessException;

	public void editCompany(CompanyVO companyVO) throws BusinessException;

	public void deleteCompany(CompanyVO companyVO) throws BusinessException;
	
	public AcctViewVO getAcctViewListId(String code, String subCode) throws BusinessException;

	public CompanyVO getCompanyDetails(Long companyId) throws BusinessException;

	public boolean getAcctIdCompanyList(Long id) throws BusinessException;

	public boolean getAcctSubCatIdCompList(Long id) throws BusinessException;

	public boolean getAcctTransIdCompList(Long id) throws BusinessException;

	public boolean getBankIdCompList(Long id) throws BusinessException;

	public boolean getCustIdCompList(Long id) throws BusinessException;

	public boolean getEmpIdCompList(Long id) throws BusinessException;

	public boolean getExOrderBillIdCompList(Long id) throws BusinessException;

	public boolean getFinancialPeriodIdCompList(Long id) throws BusinessException;

	public boolean getFinPeriodLockIdCompList(Long id) throws BusinessException;

	public boolean getInvIdCompList(Long id) throws BusinessException;

	public boolean getInvHisIdCompList(Long id) throws BusinessException;

	public boolean getBookingIdCompList(Long id) throws BusinessException;

	public boolean getTourDepItemIdCompList(Long id) throws BusinessException;

	public boolean getExOrderList(Long id) throws BusinessException;

	public boolean getInvoiceAndExchangeOrderList(Long id) throws BusinessException;

	public boolean getJournalList(Long id) throws BusinessException;

	public boolean getSupplierList(Long id) throws BusinessException;

	public HashMap<String, Object> getReportTitle(Long idCompany) throws BusinessException;

	public HashMap<String, Object> getReportTitle(CompanyVO companyVO) throws BusinessException;
	
}
