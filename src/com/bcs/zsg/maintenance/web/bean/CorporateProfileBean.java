package com.bcs.zsg.maintenance.web.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.bo.ChartOfAcctBO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.bank.bo.PaymentBO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.vo.GenAddUpdDelVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.GenUpdateVOList;
import com.bcs.zsg.component.helper.ConstantScreenAction;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.core.helper.DateUtils;
import com.bcs.zsg.db.bterp.vo.CompanyTaxVO;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.bo.MalaysiaStateBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.vo.CompanyAddressVO;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyNameVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.MalaysiaStateVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.product.vo.TourCatVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public class CorporateProfileBean extends AppBackingBean 
{
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	private transient ChartOfAcctBO chartOfAcctBO;
	@Autowired
	private transient PaymentBO paymentBO;
	@Autowired
	private transient RegionBO regionBO;
	@Autowired
	private transient MalaysiaStateBO malaysiaStateBO;
	
	private CompanyContactVO companyContactVO;
	private CompanyNameVO companyNameVO;
	private CompanyNameVO oriCompanyNameVO;
	private SystemNumberGenerationVO systemNumberGenerationVO;
	private CompanyVO companyVO;
	private CompanyTaxVO companyTaxVO;
	private CountryVO countryVO;
	private TourCatVO tourCatVO;
	private CompanyAddressVO compAddrSelectVO;
	private AcctVO acctIdAcctSalesVO;
	private AcctVO acctIdAcctTradeVO;
	private AcctVO acctIdAcctSundryVO;

	private List<CountryVO> countryList;
	private List<CompanyVO> companyList;
	private List<SystemNumberGenerationVO> sysNumGenList;
	private List<AcctViewVO> acctViewList;
	private List<AcctVO> acctDescList;
	private List<AcctVO> acctAutoCompleteList;
	private List<MalaysiaStateVO> malaysiaStateList;

	
	private Long idCompany;
	private boolean addCompany, addCompanyContact, addCompanyName;
	private String acctViewType;
	private GenAddUpdDelVO<CompanyContactVO> contactListAUD;
	private GenAddUpdDelVO<CompanyNameVO> nameListAUD;
	
	@Override
	public void resetForm() {
		this.resetFilteredObjList();
		resetCompany();
		companyTaxVO = new CompanyTaxVO();
		companyTaxVO.setRefundCarryForward(BaseConstant.NO);
		
		systemNumberGenerationVO = new SystemNumberGenerationVO();
		countryVO = new CountryVO();
		resetContactForm();
		resetContactList();
		resetNameForm();
		resetNameList();
	}

	private void resetCompany() {
		companyVO = new CompanyVO();
		companyVO.setCompanyAddressVO(new CompanyAddressVO());
	}
	
	public void resetContactForm() {
		companyContactVO = new CompanyContactVO();
	}

	public void resetContactList() {
		contactListAUD = new GenAddUpdDelVO<CompanyContactVO>();
	}
	
	public void resetNameForm() {
		companyNameVO = new CompanyNameVO();
	}
	
	public void resetNameList() {
		nameListAUD = new GenAddUpdDelVO<CompanyNameVO>();
	}

	public void init() throws BusinessException {
		try {
			loadCorporateProfile();
			countryList = regionBO.getActualCountryList();
			malaysiaStateList = malaysiaStateBO.getStateList();

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void loadCorporateProfile() {
		try {
			resetForm();
			companyList = corporateProfileBO.getCompanyList();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void saveCompany() throws BusinessException {
		try {
			
			if (!StringUtils.isEmpty(companyTaxVO.getTaxNumber()))
				if (companyTaxVO.getGstFilling() == null)
					throw new BusinessException(CommonErrConstant.ERR_GST_NO_FILLING_DATE);
			
			if (addCompany) {
				corporateProfileBO.addCompany(companyVO, companyTaxVO);
			} else {
				// Company Contact
				corporateProfileBO.updContactList(contactListAUD.getAddList(), contactListAUD.getUpdList(), 
						contactListAUD.getDelList(), idCompany);
				// Company Name
				corporateProfileBO.updNameList(nameListAUD.getAddList(), nameListAUD.getUpdList(), 
						nameListAUD.getDelList(), idCompany);
				
				corporateProfileBO.updateCompany(companyVO, companyTaxVO, companyVO.getCompanyAddressVO());
			}
			
			init();
			successResult();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void onShowAcctList(String acctViewType) {
		this.acctViewType = acctViewType;
	}
	
	public void updateCompany(boolean addCompany) throws BusinessException {
		this.addCompany = addCompany;
//		companyVO.setCountryId(CommonConstant.DEF_COUNTRY_ID);
		companyVO.getCompanyAddressVO().setCountryid(CommonConstant.DEF_COUNTRY_ID);
		if(malaysiaStateList == null) {
			malaysiaStateList = malaysiaStateBO.getStateList();
		}
		resetCompany();

		this.resetFilteredObjList();
		onSetCountry();
	}
	
	public void onCompanySelected(CompanyVO companyVO) throws BusinessException{
		this.addCompany = false;
		
		try {
			this.resetFilteredObjList();
			resetContactForm();
			resetContactList();
			resetNameForm();
			resetNameList();
			this.companyVO = companyVO;
			idCompany = companyVO.getId();
			acctViewList = chartOfAcctBO.getAcctViewList(companyVO.getId(), null);
			
			companyVO.setCompanyContactList(corporateProfileBO.getCompanyContactList(companyVO.getId()));
			companyVO.setCompanyNameList(corporateProfileBO.getCompanyNameList(companyVO.getId()));

			companyTaxVO = corporateProfileBO.getCompanyTax(companyVO.getId());
			if(companyTaxVO == null)
				companyTaxVO = new CompanyTaxVO();
			companyTaxVO.setRefundCarryForward(StringUtils.isEmpty(companyTaxVO.getRefundCarryForward()) ? BaseConstant.NO 
													: companyTaxVO.getRefundCarryForward());
			
			sysNumGenList = corporateProfileBO.getSysNumGenList(companyVO.getId());
			compAddrSelectVO = corporateProfileBO.getCompanyAddress(companyVO.getId());
			companyVO.setCompanyAddressVO(compAddrSelectVO);
			
			if(companyVO.getIdAcctSales() != null) {	
				acctIdAcctSalesVO = corporateProfileBO.getAcctList(companyVO.getIdAcctSales());

				if (StringUtils.isEmpty(acctIdAcctSalesVO.getSubCode())) {
					companyVO.setIdAcctSalesTemp(acctIdAcctSalesVO.getCode());
				}
				else {
					companyVO.setIdAcctSalesTemp(acctIdAcctSalesVO.getCode()+"-"+acctIdAcctSalesVO.getSubCode());
				}	
			}
			if(companyVO.getIdAcctTrade() != null) {	
				acctIdAcctTradeVO = corporateProfileBO.getAcctList(companyVO.getIdAcctTrade());
				if (StringUtils.isEmpty(acctIdAcctTradeVO.getSubCode())) {
					companyVO.setIdAcctTradeTemp(acctIdAcctTradeVO.getCode());
				}
				else {
					companyVO.setIdAcctTradeTemp(acctIdAcctTradeVO.getCode()+"-"+acctIdAcctTradeVO.getSubCode());
				}	
			}
			if(companyVO.getIdAcctSundry() != null) {	
				acctIdAcctSundryVO = corporateProfileBO.getAcctList(companyVO.getIdAcctSundry());
				if (StringUtils.isEmpty(acctIdAcctSundryVO.getSubCode())) {
					companyVO.setIdAcctSundryTemp(acctIdAcctSundryVO.getCode());
				}
				else {
					companyVO.setIdAcctSundryTemp(acctIdAcctSundryVO.getCode()+"-"+acctIdAcctSundryVO.getSubCode());
				}	
			}
			if(companyVO.getIdAcctGST() != null) {	
				AcctVO tmpAcctVO = corporateProfileBO.getAcctList(companyVO.getIdAcctGST());

				companyVO.setIdAcctGSTTemp(tmpAcctVO.getCode() + 
										(StringUtils.isEmpty(tmpAcctVO.getSubCode()) ? "" : "-" + tmpAcctVO.getSubCode()));
			}
			if(companyVO.getIdAcctNonClaimableGST() != null) {	
				AcctVO tmpAcctVO = corporateProfileBO.getAcctList(companyVO.getIdAcctNonClaimableGST());

				companyVO.setIdAcctNonClaimableGSTTemp(tmpAcctVO.getCode() + 
										(StringUtils.isEmpty(tmpAcctVO.getSubCode()) ? "" : "-" + tmpAcctVO.getSubCode()));
			}
			if(companyVO.getIdAcctRounding() != null) {	
				AcctVO tmpAcctVO = corporateProfileBO.getAcctList(companyVO.getIdAcctRounding());

				companyVO.setIdAcctRoundingTemp(tmpAcctVO.getCode() + 
										(StringUtils.isEmpty(tmpAcctVO.getSubCode()) ? "" : "-" + tmpAcctVO.getSubCode()));
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void deleteCompany() throws BusinessException {
		try {
			boolean acctIdCompany=corporateProfileBO.getAcctIdCompanyList(companyVO.getId());
			boolean acctSubCatIdComp=corporateProfileBO.getAcctSubCatIdCompList(companyVO.getId());
			boolean acctTransIdComp=corporateProfileBO.getAcctTransIdCompList(companyVO.getId());
			boolean bankIdComp=corporateProfileBO.getBankIdCompList(companyVO.getId());
			boolean customer=corporateProfileBO.getCustIdCompList(companyVO.getId());
			boolean employee=corporateProfileBO.getEmpIdCompList(companyVO.getId());
			boolean exOrderIdComp=corporateProfileBO.getExOrderList(companyVO.getId());
			boolean exOrderBillIdComp=corporateProfileBO.getExOrderBillIdCompList(companyVO.getId());
			boolean financialPeriodIdComp=corporateProfileBO.getFinancialPeriodIdCompList(companyVO.getId());
			boolean finPeriodLockIdComp=corporateProfileBO.getFinPeriodLockIdCompList(companyVO.getId());
			boolean invExItemIdComp=corporateProfileBO.getInvoiceAndExchangeOrderList(companyVO.getId());
			boolean invIdComp=corporateProfileBO.getInvIdCompList(companyVO.getId());
			boolean invHistIdComp=corporateProfileBO.getInvHisIdCompList(companyVO.getId());
			boolean jourIdComp=corporateProfileBO.getJournalList(companyVO.getId());
			boolean suppIdComp=corporateProfileBO.getSupplierList(companyVO.getId());
			boolean bookingIdComp=corporateProfileBO.getBookingIdCompList(companyVO.getId());
			boolean tourDepItemIdComp=corporateProfileBO.getTourDepItemIdCompList(companyVO.getId());
			
			if(acctIdCompany == true && acctSubCatIdComp==true && acctTransIdComp==true && bankIdComp==true &&
					customer==true && employee==true && exOrderIdComp==true && exOrderBillIdComp==true && 
					financialPeriodIdComp==true && finPeriodLockIdComp==true && invExItemIdComp==true && 
					invIdComp==true && invHistIdComp==true && jourIdComp==true && suppIdComp==true && 
					bookingIdComp==true && tourDepItemIdComp==true)
			{
				deleteCompanyList();
				init();
				successResult();
			}
			else {	
				FacesMessage msg  = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Company Already in use", ""); 
				if (msg != null) FacesContext.getCurrentInstance().addMessage(null, msg);
				RequestContext.getCurrentInstance().addCallbackParam("validateCompanyError", true);
		    }	 
			
		} catch (Throwable t){
			errorResult(t);
		}
	}

	public void deleteCompanyList() throws BusinessException {
		corporateProfileBO.deleteCompanyList(companyVO, companyTaxVO, companyVO.getCompanyAddressVO(), 
												companyVO.getCompanyContactList(), sysNumGenList);	
	}

	public void handleAcctSelect(SelectEvent event) throws BusinessException{
		try {
			AcctViewVO acctViewVO = (AcctViewVO) event.getObject();
			
			String accountCode = acctViewVO.getCode() + 
									(StringUtils.isEmpty(acctViewVO.getSubCode()) ? "" : "-" + acctViewVO.getSubCode());
			
			if(acctViewType.equals("SALES")) {
				companyVO.setIdAcctSalesTemp(accountCode);
				companyVO.setIdAcctSales(acctViewVO.getId());
				
			} else if(acctViewType.equals("TRADE")) {
				companyVO.setIdAcctTradeTemp(accountCode);
				companyVO.setIdAcctTrade(acctViewVO.getId());

			} else if(acctViewType.equals("SUNDRY")) {
				companyVO.setIdAcctSundryTemp(accountCode);
				companyVO.setIdAcctSundry(acctViewVO.getId());
				
			} else if(acctViewType.equals("GST")) {
				companyVO.setIdAcctGSTTemp(accountCode);
				companyVO.setIdAcctGST(acctViewVO.getId());
				
			} else if(acctViewType.equals("NON_CLAIMABLE_GST")) {
				companyVO.setIdAcctNonClaimableGSTTemp(accountCode);
				companyVO.setIdAcctNonClaimableGST(acctViewVO.getId());
				
			} else if(acctViewType.equals("ROUNDING")) {
				companyVO.setIdAcctRoundingTemp(accountCode);
				companyVO.setIdAcctRounding(acctViewVO.getId());
				
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void onContactSelected(CompanyContactVO vo) throws BusinessException{
		try {
			if(vo == null) {
				addCompanyContact = true;
				resetContactForm();
			} else {
				addCompanyContact = false;
				companyContactVO = vo;
			}
			vo = null;

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onNameSelected(CompanyNameVO vo) throws BusinessException{
		try {
			if(vo == null) {
				addCompanyName = true;
				resetNameForm();
			} else {
				addCompanyName = false;
				companyNameVO = vo;
				oriCompanyNameVO = SerializationUtils.clone(vo);
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void saveContact() throws BusinessException {
		if(null == companyVO.getCompanyContactList()) {
			companyVO.setCompanyContactList(new ArrayList<CompanyContactVO>());
		}
		
		if(addCompanyContact)
			updateContactListing("ADD", companyContactVO);
		else
			updateContactListing("UPD", companyContactVO);
	}
	
	public void saveName() throws Exception {
		if (CollectionUtils.isEmpty(companyVO.getCompanyNameList()))
			companyVO.setCompanyNameList(new ArrayList<CompanyNameVO>());
		
		for (CompanyNameVO vo : companyVO.getCompanyNameList()) {
			if (vo.getId() != null && companyNameVO.getId() != null && vo.getId() == companyNameVO.getId())
				continue;
			else if (companyNameVO.getVersion() != null && vo.getVersion() == companyNameVO.getVersion())
				continue;
			
			if (isOverlapPeriod(vo.getValidFrom(), vo.getValidTo(), 
					companyNameVO.getValidFrom(), companyNameVO.getValidTo())) {
				FacesMessage msg  = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date range overlap with other record", ""); 
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
				if (!addCompanyName) {
					companyNameVO.setValidFrom(oriCompanyNameVO.getValidFrom());
					companyNameVO.setValidTo(oriCompanyNameVO.getValidTo());
					GenUpdateVOList.processAddUpdDelVO(nameListAUD, companyVO.getCompanyNameList(), companyNameVO, ConstantScreenAction.UPD);
				}
				
				return;
			}
		}
		
		if (addCompanyName)
			GenUpdateVOList.processAddUpdDelVO(nameListAUD, companyVO.getCompanyNameList(), companyNameVO, ConstantScreenAction.ADD);
		else
			GenUpdateVOList.processAddUpdDelVO(nameListAUD, companyVO.getCompanyNameList(), companyNameVO, ConstantScreenAction.UPD);
		
		oriCompanyNameVO = SerializationUtils.clone(companyNameVO);
		successResult();
	}
	
	public void deleteContact(CompanyContactVO companyContactVO) throws BusinessException{
		updateContactListing("DEL", companyContactVO);
	}
	
	public void deleteName(CompanyNameVO companyNameVO) throws Exception{
		GenUpdateVOList.processAddUpdDelVO(nameListAUD, companyVO.getCompanyNameList(), companyNameVO, ConstantScreenAction.DEL);
	}
	
	public void updateContactListing(String action, CompanyContactVO vo) throws BusinessException{
		try {
			if(action.equals("ADD")) {
				contactListAUD.getAddList().add(vo);
				companyVO.getCompanyContactList().add(vo);
				
			} else if(action.equals("UPD")) {
				if(updateContactVOInList(contactListAUD.getAddList(), vo, false, false)) {
					//Item found in ADD list, any update does not need to add in UPDATE List
					System.out.println("updateContactVOInList(contactListAUD.getAddList(), vo, false, false)");
				} else {
					System.out.println("else");
					
					System.out.println("contactListAUD.getUpdList()");
					for (CompanyContactVO vo2 : contactListAUD.getUpdList()) {
						System.out.println(vo2.getId() + " " + vo2.getNumber());
					}
					
					System.out.println("companyVO.getCompanyContactList()");
					for (CompanyContactVO vo2 : companyVO.getCompanyContactList()) {
						System.out.println(vo2.getId() + " " + vo2.getNumber());
					}
					
					//If not found in update list, add in
					updateContactVOInList(contactListAUD.getUpdList(), vo, true, false);
					
					//Replace the updated in the main contact List
					updateContactVOInList(companyVO.getCompanyContactList(), vo, false, false);
					
					System.out.println("contactListAUD.getUpdList()");
					for (CompanyContactVO vo2 : contactListAUD.getUpdList()) {
						System.out.println(vo2.getId() + " " + vo2.getNumber());
					}
					
					System.out.println("companyVO.getCompanyContactList()");
					for (CompanyContactVO vo2 : companyVO.getCompanyContactList()) {
						System.out.println(vo2.getId() + " " + vo2.getNumber());
					}
				}
				
			} else if(action.equals("DEL")) {
				//Delete from add, update and main list if exists
				updateContactVOInList(contactListAUD.getAddList(), vo, false, true);
				updateContactVOInList(contactListAUD.getUpdList(), vo, false, true);
				updateContactVOInList(companyVO.getCompanyContactList(), vo, false, true);
				
				//Add to delete list if not exists and is not new
				if(vo.getId() != null)
					updateContactVOInList(contactListAUD.getDelList(), vo, true, false);
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
		resetContactForm();
	}
	
	private boolean updateContactVOInList(List<CompanyContactVO> contactList, CompanyContactVO contactVO, 
					boolean addIfNotExist, boolean deleteIfExist) {
		
		boolean isContactFoundInList = false;
		for(int i = contactList.size() - 1; i >= 0; i--) {
			
			if (contactVO.getTypecodecontact().equals(contactList.get(i).getTypecodecontact()) 
					&& contactVO.getNumber().equals(contactList.get(i).getNumber())) {

				if(deleteIfExist)
					contactList.remove(i);
				else
					contactList.set(i, (CompanyContactVO) contactVO.clone());
				isContactFoundInList = true;
					
				break;
			}
		}
		
		if(!isContactFoundInList && addIfNotExist && !deleteIfExist)
			contactList.add((CompanyContactVO) contactVO.clone());
		
		return isContactFoundInList;
	}
	
	public void onClearAcctSales() throws BusinessException{
		try{
			companyVO.setIdAcctSalesTemp(null);
			companyVO.setIdAcctSales(null);

		}catch(Throwable t){
			errorResult(t);
		}
	}

	public void onClearAcctTrade() throws BusinessException{
		try{
			companyVO.setIdAcctTradeTemp(null);
			companyVO.setIdAcctTrade(null);

		}catch(Throwable t){
			errorResult(t);
		}
	}

	public void onClearAcctSundry() throws BusinessException{
		try{
			companyVO.setIdAcctSundryTemp(null);
			companyVO.setIdAcctSundry(null);

		}catch(Throwable t){
			errorResult(t);
		}
	}
	public void onClearAcctGST() {
		companyVO.setIdAcctGSTTemp(null);
		companyVO.setIdAcctGST(null);
	}
	public void onClearAcctNonClaimableGST() {
		companyVO.setIdAcctNonClaimableGSTTemp(null);
		companyVO.setIdAcctNonClaimableGST(null);
	}
	public void onClearAcctRounding() {
		companyVO.setIdAcctRoundingTemp(null);
		companyVO.setIdAcctRounding(null);
	}
	
	public void onSetCountry() throws BusinessException{
		try {
			companyVO.setCountryid(CommonConstant.DEF_COUNTRY_ID);
			acctViewList = new ArrayList<AcctViewVO>();
			acctViewList = chartOfAcctBO.getAcctViewList(getSessionInfoBean().getCompanyVO().getId(), null);

		} catch(Throwable t) {
			errorResult(t);
		}
	}
	
	public List<String> complete(String query) throws BusinessException{  
        List<String> results = new ArrayList<String>();   
        try{ 
        	acctAutoCompleteList=paymentBO.getAcctList(getSessionInfoBean().getCompanyVO().getId(),query);
        	for (int i = 0; i <acctAutoCompleteList.size(); i++) 
        	{
	        	if(acctAutoCompleteList.get(i).getSubCode().isEmpty())
	        	{ 
	        		results.add(acctAutoCompleteList.get(i).getCode());
	        	}
	        	else
	        	{
	        		results.add(acctAutoCompleteList.get(i).getCode() + "-" + acctAutoCompleteList.get(i).getSubCode() );  
	        	}
	         }
        	
	       }catch(Exception e){
        	e.printStackTrace() ;
      
        }
        return results;  
    }  
	
	public static final boolean isOverlapPeriod(Date periodAStart, Date periodAEnd, 
				Date periodBStart, Date periodBEnd) {
		if (periodAStart != null && periodAEnd != null) {
			if (periodBStart != null && periodBEnd != null) {
				return periodAStart.getTime() <= periodBEnd.getTime() && periodBStart.getTime() <= periodAEnd.getTime();
				
			} else if (periodBStart != null) {
				return isWithinPeriod(periodBStart, periodAStart, periodAEnd);
			}
		} else if (periodAStart != null) {
			if (periodBStart != null && periodBEnd != null) {
				return isWithinPeriod(periodAStart, periodBStart, periodBEnd);
				
			} else if (periodBStart != null) {
				return DateUtils.isSameDay(periodAStart, periodBStart);
			}
		}
		
		return false;
	}
	
	public static final boolean isWithinPeriod(Date checkDate, Date periodStart, Date periodEnd) {
		return periodStart.getTime() <= checkDate.getTime() &&  periodEnd.getTime() >= checkDate.getTime(); 
	}

	/**
	 * @return the acctAutoCompleteList
	 */
	public List<AcctVO> getAcctAutoCompleteList() {
		return acctAutoCompleteList;
	}

	/**
	 * @param acctAutoCompleteList the acctAutoCompleteList to set
	 */
	public void setAcctAutoCompleteList(List<AcctVO> acctAutoCompleteList) {
		this.acctAutoCompleteList = acctAutoCompleteList;
	}

	public CompanyVO getCompanyVO() 
	{
		return companyVO;
	}

	public void setCompanyVO(CompanyVO companyVO) 
	{
		this.companyVO = companyVO;
	}
	
	public CompanyContactVO getCompanyContactVO() 
	{
		return companyContactVO;
	}

	public void setCompanyContactVO(CompanyContactVO companyContactVO) 
	{
		this.companyContactVO = companyContactVO;
	}

	public List<CountryVO> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryVO> countryList) {
		this.countryList = countryList;
	}

	public CountryVO getCountryVO() {
		return countryVO;
	}

	public void setCountryVO(CountryVO countryVO) {
		this.countryVO = countryVO;
	}

	public List<CompanyVO> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<CompanyVO> companyList) {
		this.companyList = companyList;
	}
	
	public List<AcctViewVO> getAcctViewList() {
		return acctViewList;
	}

	public void setAcctViewList(List<AcctViewVO> acctViewList) {
		this.acctViewList = acctViewList;
	}

	public TourCatVO getTourCatVO() 
	{
		return tourCatVO;
	}

	public void setTourCatVO(TourCatVO tourCatVO) 
	{
		this.tourCatVO = tourCatVO;
	}

	public List<AcctVO> getAcctDescList() {
		return acctDescList;
	}

	/**
	 * @param acctDescList the acctDescList to set
	 */
	public void setAcctDescList(List<AcctVO> acctDescList) {
		this.acctDescList = acctDescList;
	}

	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	public AcctVO getAcctIdAcctSalesVO() {
		return acctIdAcctSalesVO;
	}

	public void setAcctIdAcctSalesVO(AcctVO acctIdAcctSalesVO) {
		this.acctIdAcctSalesVO = acctIdAcctSalesVO;
	}

	public AcctVO getAcctIdAcctTradeVO() {
		return acctIdAcctTradeVO;
	}

	public void setAcctIdAcctTradeVO(AcctVO acctIdAcctTradeVO) {
		this.acctIdAcctTradeVO = acctIdAcctTradeVO;
	}

	public AcctVO getAcctIdAcctSundryVO() {
		return acctIdAcctSundryVO;
	}

	public void setAcctIdAcctSundryVO(AcctVO acctIdAcctSundryVO) {
		this.acctIdAcctSundryVO = acctIdAcctSundryVO;
	}

	public CompanyAddressVO getCompAddrSelectVO() {
		return compAddrSelectVO;
	}

	public void setCompAddrSelectVO(CompanyAddressVO compAddrSelectVO) {
		this.compAddrSelectVO = compAddrSelectVO;
	}

	public List<SystemNumberGenerationVO> getSysNumGenList() {
		return sysNumGenList;
	}

	public void setSysNumGenList(List<SystemNumberGenerationVO> sysNumGenList) {
		this.sysNumGenList = sysNumGenList;
	}

	public SystemNumberGenerationVO getSystemNumberGenerationVO() {
		return systemNumberGenerationVO;
	}

	public void setSystemNumberGenerationVO(SystemNumberGenerationVO systemNumberGenerationVO) {
		this.systemNumberGenerationVO = systemNumberGenerationVO;
	}

	public boolean isAddCompany() {
		return addCompany;
	}

	public void setAddCompany(boolean addCompany) {
		this.addCompany = addCompany;
	}

	public String getAcctViewType() {
		return acctViewType;
	}

	public void setAcctViewType(String acctViewType) {
		this.acctViewType = acctViewType;
	}

	public boolean isAddCompanyContact() {
		return addCompanyContact;
	}

	public void setAddCompanyContact(boolean addCompanyContact) {
		this.addCompanyContact = addCompanyContact;
	}

	public CompanyTaxVO getCompanyTaxVO() {
		return companyTaxVO;
	}

	public void setCompanyTaxVO(CompanyTaxVO companyTaxVO) {
		this.companyTaxVO = companyTaxVO;
	}

	public CompanyNameVO getCompanyNameVO() {
		return companyNameVO;
	}

	public void setCompanyNameVO(CompanyNameVO companyNameVO) {
		this.companyNameVO = companyNameVO;
	}

	public GenAddUpdDelVO<CompanyNameVO> getNameListAUD() {
		return nameListAUD;
	}

	public void setNameListAUD(GenAddUpdDelVO<CompanyNameVO> nameListAUD) {
		this.nameListAUD = nameListAUD;
	}

	public boolean getAddCompanyName() {
		return addCompanyName;
	}

	public void setAddCompanyName(boolean addCompanyName) {
		this.addCompanyName = addCompanyName;
	}

	public CompanyNameVO getOriCompanyNameVO() {
		return oriCompanyNameVO;
	}

	public void setOriCompanyNameVO(CompanyNameVO oriCompanyNameVO) {
		this.oriCompanyNameVO = oriCompanyNameVO;
	}

	public List<MalaysiaStateVO> getMalaysiaStateList() {
	    return malaysiaStateList;
	}

	public void setMalaysiaStateList(List<MalaysiaStateVO> malaysiaStateList) {
	    this.malaysiaStateList = malaysiaStateList;
	}
}
