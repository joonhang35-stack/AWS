package com.bcs.zsg.maintenance.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.db.bterp.vo.CompanyTaxVO;
import com.bcs.zsg.gst.helper.TaxCodeType;
import com.bcs.zsg.maintenance.dao.CorporateProfileDAO;
import com.bcs.zsg.maintenance.dao.RegionDAO;
import com.bcs.zsg.maintenance.dao.SystemNumberGenerationDAO;
import com.bcs.zsg.maintenance.vo.CompanyAddressVO;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyNameVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public class CorporateProfileServiceImpl implements CorporateProfileService {
	@Autowired
	private CorporateProfileDAO corporateProfileDAO;
	@Autowired
	private SystemNumberGenerationDAO systemNumberGenerationDAO;
	@Autowired
	private RegionDAO regionDAO;
	
	private List<SystemNumberGenerationVO> sysGenDefaultList;

	@Override
	public void addCompany(CompanyVO companyVO, CompanyTaxVO companyTaxVO) throws BusinessException {
		
		Long companyId = (Long)corporateProfileDAO.insert(companyVO);
		
		// Company Name
		if(CollectionUtils.isNotEmpty(companyVO.getCompanyNameList())){
			for(CompanyNameVO companyNameVO : companyVO.getCompanyNameList()){
				companyNameVO.setIdCompany(companyId);
				corporateProfileDAO.insert(companyNameVO);
			}
		}
		
		// Company Contact
		if(CollectionUtils.isNotEmpty(companyVO.getCompanyContactList())){
			for(int i=0; i < companyVO.getCompanyContactList().size();i++){
				CompanyContactVO companyContactVO=new CompanyContactVO();
				companyContactVO.setCompanyid(companyId);
				companyContactVO.setTypecodecontact(companyVO.getCompanyContactList().get(i).getTypecodecontact());
				companyContactVO.setNumber(companyVO.getCompanyContactList().get(i).getNumber());
				corporateProfileDAO.insert(companyContactVO);
			}
		}
		
		// Company Tax
		companyTaxVO.setCompanyId(companyId);
		companyTaxVO.setType(TaxCodeType.GST.getValue());
		companyTaxVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
		
		corporateProfileDAO.insert(companyTaxVO);
		
		CompanyAddressVO companyAddressVO = companyVO.getCompanyAddressVO();
		companyAddressVO.setTypecodeaddress("OFF");
		companyAddressVO.setCompanyid(companyId);
		corporateProfileDAO.insert(companyAddressVO);
		
		sysGenDefaultList = systemNumberGenerationDAO.getSysGenDefaultList(1);
		for(int i=0;i < sysGenDefaultList.size();i++) {
			SystemNumberGenerationVO sysNumGenVO=new SystemNumberGenerationVO();
			sysNumGenVO.setIdCompany(companyId);
			sysNumGenVO.setCode(sysGenDefaultList.get(i).getCode());
			sysNumGenVO.setDescription(sysGenDefaultList.get(i).getDescription());
			sysNumGenVO.setNextnumber((long)1);
			sysNumGenVO.setPrefixid(sysGenDefaultList.get(i).getPrefixid());
			sysNumGenVO.setIsDefault(0);
			systemNumberGenerationDAO.insert(sysNumGenVO);
		}	
		
	}

	@Override
	public List<CountryVO> getCountryList() throws BusinessException {
		return corporateProfileDAO.getCountryList();
	}

	@Override
	public void addCompanyContact(CompanyContactVO companyContactVO)
			throws BusinessException {
		corporateProfileDAO.insert(companyContactVO);
	}

	@Override
	public void editContact(CompanyContactVO companyContactVO)
			throws BusinessException {
		corporateProfileDAO.update(companyContactVO);
	}

	@Override
	public void deleteContact(CompanyContactVO companyContactVO)
			throws BusinessException {
		corporateProfileDAO.delete(companyContactVO);
	}

	@Override
	public List<CompanyVO> getCompanyList() throws BusinessException {
		return corporateProfileDAO.getCompanyList();
	}

	@Override
	public void editCompany(CompanyVO companyVO) throws BusinessException {
		corporateProfileDAO.update(companyVO);
	}

	@Override
	public void deleteCompany(CompanyVO companyVO) throws BusinessException {
		corporateProfileDAO.delete(companyVO);
	}

	@Override
	public void updateCompany(CompanyVO companyVO, CompanyTaxVO companyTaxVO, CompanyAddressVO companyAddressVO) throws BusinessException {
		corporateProfileDAO.update(companyVO);

		if(companyTaxVO.getId() == null) {
			companyTaxVO.setCompanyId(companyVO.getId());
			companyTaxVO.setType(TaxCodeType.GST.getValue());
			companyTaxVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
			corporateProfileDAO.insert(companyTaxVO);
		} else
			corporateProfileDAO.update(companyTaxVO);
		
		corporateProfileDAO.update(companyAddressVO);
	}

	@Override
	public List<CompanyContactVO> getCompanyContactList(Long companyid)
			throws BusinessException {
		return corporateProfileDAO.getCompanyContactList(companyid);
	}
	
	@Override
	public List<CompanyNameVO> getCompanyNameList(Long companyid)
			throws BusinessException {
		return corporateProfileDAO.getCompanyNameList(companyid);
	}

	@Override
	public void deleteCompanyList(CompanyVO companyVO, CompanyTaxVO companyTaxVO,
			CompanyAddressVO companyAddressVO,
			List<CompanyContactVO> companycontactList, List<SystemNumberGenerationVO> sysNumGenList) throws BusinessException {
		
		for(int i = 0; i < sysNumGenList.size(); i++){
			SystemNumberGenerationVO sysNumGenVO = sysNumGenList.get(i);
			corporateProfileDAO.delete(sysNumGenVO);
		}
		
		corporateProfileDAO.delete(companyAddressVO);
		corporateProfileDAO.delete(companyTaxVO);
		
		for(CompanyContactVO vo : companycontactList)
			corporateProfileDAO.delete(vo);
		
		corporateProfileDAO.delete(companyVO);
	}

	@Override
	public AcctViewVO getAcctViewListId(String code,String subCode)
			throws BusinessException {
		return  corporateProfileDAO.getAcctViewListId(code,subCode);
	}

	@Override
	public AcctVO getAcctList(Long idAcctSales) throws BusinessException {
		return corporateProfileDAO.getAcctList(idAcctSales);
	}

	@Override
	public void updContactList(List<CompanyContactVO> addContList,
			List<CompanyContactVO> updContList,
			List<CompanyContactVO> delContList, Long idCompany) throws BusinessException {
		// add contact
		if (CollectionUtils.isNotEmpty(addContList)) {
			for (CompanyContactVO vo : addContList) {
				vo.setCompanyid(idCompany);
				corporateProfileDAO.insert(vo);
			}
		}
		// update contacts
		if (CollectionUtils.isNotEmpty(updContList)) {
			for (CompanyContactVO vo : updContList) {
				System.out.println("CYY vo.getId(): " + vo.getId());
				corporateProfileDAO.update(vo);
			}
		}
		// delete contacts
		if (CollectionUtils.isNotEmpty(delContList)) {
			for (CompanyContactVO vo : delContList) {
				corporateProfileDAO.delete(vo);
			}
		}
	}
	
	@Override
	public void updNameList(List<CompanyNameVO> addNameList, 
			List<CompanyNameVO> updNameList,
			List<CompanyNameVO> delNameList, Long idCompany) throws BusinessException {
		// add name
		if (CollectionUtils.isNotEmpty(addNameList)) {
			for (CompanyNameVO vo : addNameList) {
				vo.setIdCompany(idCompany);
				corporateProfileDAO.insert(vo);
			}
		}
		// update name
		if (CollectionUtils.isNotEmpty(updNameList)) {
			for (CompanyNameVO vo : updNameList) {
				corporateProfileDAO.update(vo);
			}
		}
		// delete name
		if (CollectionUtils.isNotEmpty(delNameList)) {
			for (CompanyNameVO vo : delNameList) {
				corporateProfileDAO.delete(vo);
			}
		}
	}

	@Override
	public CompanyTaxVO getCompanyTax(Long id) throws BusinessException {
		return corporateProfileDAO.getCompanyTax(id);
	}
	
	@Override
	public CompanyAddressVO getCompanyAddress(Long id) throws BusinessException {
		return corporateProfileDAO.getCompanyAddress(id);
	}

	@Override
	public List<SystemNumberGenerationVO> getSysNumGenList(Long id)
			throws BusinessException {
		return corporateProfileDAO.getSysNumGenList(id);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.service.CorporateProfileService#getCompanyDetails(java.lang.Long)
	 */
	@Override
	public CompanyVO getCompanyDetails(Long companyId) throws BusinessException {
		return corporateProfileDAO.getCompanyDetails(companyId);
	}

	@Override
	public boolean getAcctIdCompanyList(Long id) throws BusinessException {
		return corporateProfileDAO.getAcctIdCompanyList(id);
	}

	@Override
	public boolean getAcctSubCatIdCompList(Long id)
			throws BusinessException {
		return corporateProfileDAO.getAcctSubCatIdCompList(id);
	}

	@Override
	public boolean getAcctTransIdCompList(Long id)
			throws BusinessException {
		return corporateProfileDAO.getAcctTransIdCompList(id);
	}

	@Override
	public boolean getBankIdCompList(Long id) throws BusinessException {
		return corporateProfileDAO.getBankIdCompList(id);
	}

	

	@Override
	public boolean getCustIdCompList(Long id) throws BusinessException {
		return corporateProfileDAO.getCustIdCompList(id);
	}

	@Override
	public boolean getEmpIdCompList(Long id) throws BusinessException {
		return corporateProfileDAO.getEmpIdCompList(id);
	}

	@Override
	public boolean getExOrderBillIdCompList(Long id)
			throws BusinessException {
		return corporateProfileDAO.getExOrderBillIdCompList(id);
	}

	@Override
	public boolean getFinancialPeriodIdCompList(Long id)
			throws BusinessException {
		return corporateProfileDAO.getFinancialPeriodIdCompList(id);
	}

	@Override
	public boolean getFinPeriodLockIdCompList(Long id)
			throws BusinessException {
		return corporateProfileDAO.getFinPeriodLockIdCompList(id);
	}

	@Override
	public boolean getInvIdCompList(Long id) throws BusinessException {
		return corporateProfileDAO.getInvIdCompList(id);
	}

	@Override
	public boolean getInvHisIdCompList(Long id)
			throws BusinessException {
		return corporateProfileDAO.getInvHisIdCompList(id);
	}

	@Override
	public boolean getBookingIdCompList(Long id)
			throws BusinessException {
		return corporateProfileDAO.getBookingIdCompList(id);
	}

	@Override
	public boolean getTourDepItemIdCompList(Long id)
			throws BusinessException {
		return corporateProfileDAO.getTourDepItemIdCompList(id);
	}

	@Override
	public boolean getExOrderList(Long id) throws BusinessException {
		return corporateProfileDAO.getExOrderList(id);
	}

	@Override
	public boolean getInvoiceAndExchangeOrderList(Long id)
			throws BusinessException {
		return corporateProfileDAO.getInvoiceAndExchangeOrderList(id);
	}

	@Override
	public boolean getJournalList(Long id) throws BusinessException {
		return corporateProfileDAO.getJournalList(id);
	}

	@Override
	public boolean getSupplierList(Long id) throws BusinessException {
		return corporateProfileDAO.getSupplierList(id);
	}
	
	@Override
	public HashMap<String, Object> getReportTitle(Long idCompany) throws BusinessException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		CompanyVO companyVO = new CompanyVO();
		companyVO = getCompanyDetails(idCompany);
		
		StringBuilder address = new StringBuilder();
		map.put("companyName", companyVO.getName());
		map.put("slogan", companyVO.getSlogan());
		map.put("email", companyVO.getEmail());
		
		if (!companyVO.getCompanyAddressVO().getAddress1().equals("")) address.append(companyVO.getCompanyAddressVO().getAddress1()).append(" ");
		if (!companyVO.getCompanyAddressVO().getAddress2().equals("")) address.append(companyVO.getCompanyAddressVO().getAddress2()).append(" ");
		if (!companyVO.getCompanyAddressVO().getAddress3().equals("")) address.append(companyVO.getCompanyAddressVO().getAddress3()).append(" ");
		if (!companyVO.getCompanyAddressVO().getCity().equals("")) address.append(companyVO.getCompanyAddressVO().getCity()).append(" ");
		if (!companyVO.getCompanyAddressVO().getState().equals("")) address.append(companyVO.getCompanyAddressVO().getState()).append(" ");
		if (!companyVO.getCompanyAddressVO().getPostcode().equals("")) address.append(companyVO.getCompanyAddressVO().getPostcode()).append(" ");
		String companyName = regionDAO.getCountryById(companyVO.getCompanyAddressVO().getCountryid()).getCountry();
		if (!companyName.equals("")) address.append(companyName);
		map.put("address", address.toString());

		String contact = "";
		for (CompanyContactVO vo : companyVO.getCompanyContactList()) {
			contact += LookupItemUtils.getLookupItemDesc(CommonConstant.LOOKUP_CAT_CD_CNTC_TYPE, vo.getTypecodecontact()) + ": " + vo.getNumber() + "  ";
		}
		map.put("contact", contact);
			
		
		return map;
	}
	
	@Override
	public HashMap<String, Object> getReportTitle(CompanyVO companyVO) throws BusinessException {
		return getReportTitle(companyVO.getId());
	}

}
