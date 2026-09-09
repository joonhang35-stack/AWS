package com.bcs.zsg.sales.web.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.bo.ChartOfAcctBO;
import com.bcs.zsg.acct.bo.EInvoiceBO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.apiresponse.vo.ApiResponseVO;
import com.bcs.zsg.apiresponse.vo.CRMClaimVoucherResponseVO;
import com.bcs.zsg.apiresponse.vo.CRMClaimedVoucherResponseVO;
import com.bcs.zsg.apiresponse.vo.CRMRedeemCatalogVO;
import com.bcs.zsg.apiresponse.vo.CRMVoucherVO;
import com.bcs.zsg.cfg.sec.bo.UserBO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.common.helper.AppConfigConstant;
import com.bcs.zsg.common.helper.CRMCommonConstant;
import com.bcs.zsg.common.helper.CRMUtils;
import com.bcs.zsg.common.helper.CalculationUtils;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.DatesUtils;
import com.bcs.zsg.common.helper.EInvoiceConstant;
import com.bcs.zsg.common.helper.EInvoiceUtils;
import com.bcs.zsg.common.helper.FileUploadUtils;
import com.bcs.zsg.common.helper.FunctionUtils;
import com.bcs.zsg.common.helper.KeycloakUtils;
import com.bcs.zsg.common.helper.LookupItemConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.vo.FileUploadVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.security.bo.SecurityBO;
import com.bcs.zsg.component.security.vo.UserRoleViewVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.crm.bo.CRMMembershipBO;
import com.bcs.zsg.crm.bo.CustomerPosInfoBO;
import com.bcs.zsg.crm.helper.CRMProperties;
import com.bcs.zsg.crm.sec.vo.CRMMembershipVO;
import com.bcs.zsg.crm.sec.vo.KeycloakUserVO;
import com.bcs.zsg.crm.vo.CustomerPointTierVO;
import com.bcs.zsg.crm.vo.CustomerPosInfoVO;
import com.bcs.zsg.crm.vo.PointExpiryVO;
import com.bcs.zsg.maintenance.bo.AccountCodeBO;
import com.bcs.zsg.maintenance.bo.CustomerProfileUpdateBO;
import com.bcs.zsg.maintenance.bo.MalaysiaStateBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.bo.SystemNumberGenerationBO;
import com.bcs.zsg.maintenance.bo.VisibleListingConfigBO;
import com.bcs.zsg.maintenance.helper.MaintConstant;
import com.bcs.zsg.maintenance.vo.CustomerProfileUpdateVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.MalaysiaStateVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.maintenance.vo.VisibleListingConfigVO;
import com.bcs.zsg.product.bo.AirlineBO;
import com.bcs.zsg.product.bo.CruiseBO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.product.vo.AirlineVO;
import com.bcs.zsg.product.vo.CruiseVO;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderViewVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.CorAddressVO;
import com.bcs.zsg.purchase.vo.CorContactVO;
import com.bcs.zsg.purchase.vo.CorPersonInChargeVO;
import com.bcs.zsg.purchase.vo.CorporateVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.purchase.vo.IdentityVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.sales.bo.CustomerBO;
import com.bcs.zsg.sales.bo.InvoiceBO;
import com.bcs.zsg.sales.bo.OnlineBookingBO;
import com.bcs.zsg.sales.helper.IdentityUtils;
import com.bcs.zsg.sales.helper.SalesConstant;
import com.bcs.zsg.sales.vo.CustDetailsVO;
import com.bcs.zsg.sales.vo.CustTourHistVO;
import com.bcs.zsg.sales.vo.CustomerRemarksVO;
import com.bcs.zsg.sales.vo.CustomerVO;
import com.bcs.zsg.sales.vo.IdentityDetailVO;
import com.bcs.zsg.sales.vo.InvPmntAuthVO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.sales.vo.InvoiceVoucherVO;
import com.bcs.zsg.sales.vo.OnlineBookingCustomerVO;
import com.bcs.zsg.sales.vo.OnlineBookingPassengerVO;
import com.bcs.zsg.sales.vo.OnlineCustomerProfileUpdateVO;
import com.bcs.zsg.sales.vo.PersonAttachmentVO;
import com.bcs.zsg.sales.vo.PersonClassVO;
import com.bcs.zsg.sales.vo.PersonComplicationVO;
import com.bcs.zsg.sales.vo.PersonContactVO;
import com.bcs.zsg.sales.vo.PersonEmailVO;
import com.bcs.zsg.sales.vo.PersonLangVO;
import com.bcs.zsg.sales.vo.PersonMealVO;

public class CustomerBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	protected transient CustomerBO customerBO;
	@Autowired
	protected transient RegionBO regionBO;
	@Autowired
	protected transient InvoiceBO invBO;
	@Autowired
	protected transient AccountCodeBO accountCodeBO;
	@Autowired 
	protected transient TourPackageBO tourPkgBO;
	@Autowired
	protected transient AirlineBO airlineBO;
	@Autowired
	protected transient CruiseBO cruiseBO;
	@Autowired
	protected transient UserBO userBO;
	@Autowired
	protected transient ChartOfAcctBO chartOfAcctBO;
	@Autowired
	protected transient SecurityBO securityBO;
	@Autowired
	protected transient SystemNumberGenerationBO systemNumberGenerationBO;
	@Autowired
	protected transient OnlineBookingBO onlineBookingBO;
	@Autowired
	private transient VisibleListingConfigBO visibleListingConfigBO;
	@Autowired
	private transient MalaysiaStateBO malaysiaStateBO;
	@Autowired
	private transient EInvoiceBO eInvoiceBO;
	@Autowired
	private transient InvoiceBO invoiceBO;
	@Autowired
	private transient CustomerProfileUpdateBO customerProfileUpdateBO;
	@Autowired
	private transient CustomerPosInfoBO customerPosInfoBO;
	@Autowired
	private transient CRMMembershipBO crmMembershipBO;
	protected CustomerVO customerVO;
	protected OnlineBookingCustomerVO obcVO;
	protected IdentityVO identityVO;
	protected IdentityVO identityCloneVO;
	protected PersonLangVO personLangVO;
	protected PersonMealVO personMealVO;
	protected PersonContactVO personContactVO;
	protected CorContactVO corContactVO;
	protected PersonClassVO personClassVO;
	protected PersonComplicationVO personComplicationVO;
	protected CustomerRemarksVO remarksVO;
	protected CustTourHistVO custTourHistVO;
	protected PersonAttachmentVO personAttachmentVO;
	protected PersonEmailVO personEmailVO;
	protected InvPmntAuthVO invPmntAuthVO;
	protected IdentityVO nricIdVO;
	protected IdentityVO passportIdVO;
	
	// --------- CRM InnoQB - Start -----------/
	
	// Member (Point Tier)
	protected CustomerPointTierVO customerPointTierVO;
	protected CRMMembershipVO crmMembershipVO;
	// Redemption (Voucher)
	private CRMVoucherVO crmVoucherVO;
	private List<CRMVoucherVO> voucherList;
	
	private int targetRedemptionPoints;
	
	// --------- CRM InnoQB - End -----------/
	
	protected boolean identityAdd;
	protected boolean languageAdd;
	protected boolean mealAdd;
	protected boolean contactAdd;
	protected boolean classAdd;
	protected boolean complicationAdd;
	protected boolean remarksAdd;
	protected boolean attachmentAdd;
	protected boolean emailAdd;
	
	//protected AddUpdDelVO identityVOList;
	//protected AddUpdDelVO languageVOList;
	//protected AddUpdDelVO mealVOList;
	//protected AddUpdDelVO contactVOList;
	//protected AddUpdDelVO classVOList;
	//protected AddUpdDelVO complicationVOList;
	//protected AddUpdDelVO remarksVOList;
	
	protected List<CustomerVO> customerList;
	protected List<CountryVO> countryList;
	protected List<CustTourHistVO> custTourHistList;
	protected List<EmployeeViewVO> employeeList;
	protected List<MalaysiaStateVO> malaysiaStateList;
	
	protected List<AcctViewVO> acctViewList;
	protected List<InvoiceAndExchangeOrderViewVO> invEOItemViewList;
	protected List<AirlineVO> airlineList;
	protected List<CruiseVO> cruiseList;
	
	protected FileUploadUtils fileUploadUtils;
	protected TrackingLogUtils trackingLogUtils;
	protected final String destinationPassportPath = LookupItemUtils.getGlobalConfigValue(MaintConstant.GLOBAL_CD_GLOBAL, MaintConstant.GLOBAL_CD_PATH_PASS);
	protected final String destinationVisaPath = LookupItemUtils.getGlobalConfigValue(MaintConstant.GLOBAL_CD_GLOBAL, MaintConstant.GLOBAL_CD_PATH_VISA);
	protected final String destinationAttachmentPath = LookupItemUtils.getGlobalConfigValue(MaintConstant.GLOBAL_CD_GLOBAL, MaintConstant.GLOBAL_CD_PATH_CUST_ATTACH);
	private final String URL_SALES_INV = "/app/sales/inv";
	private final String URL_SALES_PAX_STATEMENT = "/app/sales/paxStatement";
	
	protected String uploadedFile;
	
	protected String fileName;
	protected String strOnLoad;
	protected boolean isFromInv;
	private String prefixValue;
	
	protected CustDetailsVO custDetailsVO;
	protected TourDepartureVO tourDepVO;
	protected TourPackageVO tourPkgVO;
	protected AcctVO acctVO;
	protected InvoiceVO invoiceVO;
	
	private boolean deepCopy;
	
	// For Customer List Filtering
	private String activeStatus;
	private boolean allowUpdateCusStatus;
	
	private Long tmpIdBooking;
	protected Boolean isB2BAgent;
	
	private LazyDataModel<CustomerVO> lazyCustDataModel;
	private transient UploadedFile uploadedCrmIdCsvFile;
	private byte[] crmIdCsvFileBytes;
	private String crmIdCsvFileName;
	
	private OnlineCustomerProfileUpdateVO onlineCustomerProfileUpdateVO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		customerVO = new CustomerVO();
		customerVO.setPersonVO(new PersonVO());
		customerVO.setBillAddressVO(new AddressVO());
		customerVO.setMailAddressVO(new AddressVO());
		customerVO.setCorBillAddressVO(new CorAddressVO());
		customerVO.setCorMailAddressVO(new CorAddressVO());
		customerVO.setCorporateVO(new CorporateVO());
		customerVO.setPcTypeCd("P");
		
		customerVO.setIdentityList(new ArrayList<IdentityVO>());
		customerVO.setPersonLangList(new ArrayList<PersonLangVO>());
		customerVO.setPersonMealList(new ArrayList<PersonMealVO>());
		customerVO.setPersonContactList(new ArrayList<PersonContactVO>());
		customerVO.setPersonAttachmentList(new ArrayList<PersonAttachmentVO>());
		customerVO.setCorContactList(new ArrayList<CorContactVO>());
		customerVO.setPersonClassList(new ArrayList<PersonClassVO>());
		customerVO.setPersonComplicationList(new ArrayList<PersonComplicationVO>());
		customerVO.setCustRemarksList(new ArrayList<CustomerRemarksVO>());
		customerVO.setLangCdList(new ArrayList<String>());
		customerVO.setMealList(new ArrayList<String>());
		customerVO.setClassList(new ArrayList<String>());
		customerVO.setCompliList(new ArrayList<String>());
		customerVO.getPersonVO().setEmailList(new ArrayList<PersonEmailVO>());
		customerVO.setCorPicList(new ArrayList<CorPersonInChargeVO>());
		
		identityVO = new IdentityVO();
		identityCloneVO = new IdentityVO();
		identityVO.setIdentityDetailVO(new IdentityDetailVO());
		identityCloneVO.setIdentityDetailVO(new IdentityDetailVO());
		personLangVO = new PersonLangVO();
		personMealVO = new PersonMealVO();
		personContactVO = new PersonContactVO();
		corContactVO = new CorContactVO();
		personClassVO = new PersonClassVO();
		personComplicationVO = new PersonComplicationVO();
		remarksVO = new CustomerRemarksVO();
		personAttachmentVO = new PersonAttachmentVO();
		personEmailVO = new PersonEmailVO();
		nricIdVO = new IdentityVO();
		nricIdVO.setIdType(CommonConstant.LOOKUP_ITM_ID_NRIC);
		nricIdVO.setIdentityDetailVO(new IdentityDetailVO());
		passportIdVO = new IdentityVO();
		passportIdVO.setIdType(CommonConstant.LOOKUP_ITM_ID_PASSPRT);
		passportIdVO.setIdentityDetailVO(new IdentityDetailVO());
		if (passportIdVO.getIdentityDetailVO().getIdCountry() == null) passportIdVO.getIdentityDetailVO().setIdCountry(CommonConstant.DEF_COUNTRY_ID);
		
		customerVO.setAddEdit(true);
		
		//identityVOList = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
		//languageVOList = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
		//mealVOList = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
		//contactVOList = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
		//classVOList = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
		//complicationVOList = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
		//remarksVOList = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
		
		// Default country
		customerVO.getPersonVO().setCountryId(CommonConstant.DEF_COUNTRY_ID);
		customerVO.getBillAddressVO().setCountryId(CommonConstant.DEF_COUNTRY_ID);
		customerVO.getMailAddressVO().setCountryId(CommonConstant.DEF_COUNTRY_ID);
		customerVO.getCorBillAddressVO().setCountryId(CommonConstant.DEF_COUNTRY_ID);
		customerVO.getCorMailAddressVO().setCountryId(CommonConstant.DEF_COUNTRY_ID);
		deepCopy = false;
		
		//Default countryCode
		personContactVO.setPerMobileCountryId(CommonConstant.DEF_COUNTRY_ID);
		personContactVO.setPerOfficeCountryId(CommonConstant.DEF_COUNTRY_ID);
		personContactVO.setPerHomeCountryId(CommonConstant.DEF_COUNTRY_ID);
		personContactVO.setPerFaxCountryId(CommonConstant.DEF_COUNTRY_ID);
		personContactVO.setPerOtherCountryId(CommonConstant.DEF_COUNTRY_ID);
		corContactVO.setCorMobileCountryId(CommonConstant.DEF_COUNTRY_ID);
		corContactVO.setCorOfficeCountryId(CommonConstant.DEF_COUNTRY_ID);
		corContactVO.setCorHomeCountryId(CommonConstant.DEF_COUNTRY_ID);
		corContactVO.setCorFaxCountryId(CommonConstant.DEF_COUNTRY_ID);
		customerVO.getPersonVO().setEcIdCountryCd(CommonConstant.DEF_COUNTRY_ID);
		// file upload utils
		resetFileUploadForm();
		testPostingSalesInvoice();
	}
	public void testPostingSalesInvoice() {
	    try {
	        CRMUtils utils = new CRMUtils();
	        utils.postingSalesInvoice();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void resetTourHistForm(){
		custTourHistVO = new CustTourHistVO();
		custTourHistList=new ArrayList<CustTourHistVO>();
		
		
	}
	public void resetFileUploadForm() {
		fileName = "";
		fileUploadUtils = new FileUploadUtils(AppConfigConstant.uploadPath);
	}
	
	/**
	 * Reset identity form
	 * @throws BusinessException
	 */
	public void resetIdentityForm() throws BusinessException {
		setIdentityAdd(true);
		identityVO = new IdentityVO();
		identityCloneVO = new IdentityVO();
		identityVO.setIdentityDetailVO(new IdentityDetailVO());
		identityCloneVO.setIdentityDetailVO(new IdentityDetailVO());
		identityVO.getIdentityDetailVO().setIdCountry(CommonConstant.DEF_COUNTRY_ID);
		uploadedFile = "";
		fileName = "";
	}
	
	/**
	 * Reset language form
	 * @throws BusinessException
	 */
	public void resetLanguageForm() throws BusinessException {
		setLanguageAdd(true);
		personLangVO = new PersonLangVO();
	}
	
	/**
	 * Reset meal form
	 * @throws BusinessException
	 */
	public void resetMealForm() throws BusinessException {
		setMealAdd(true);
		personMealVO = new PersonMealVO();
	}
	
	/**
	 * Reset contact form
	 * @throws BusinessException
	 */
	public void resetContactForm() throws BusinessException {
		setContactAdd(true);
		personContactVO = new PersonContactVO();
		corContactVO = new CorContactVO();
	}
	
	/**
	 * Reset class form
	 * @throws BusinessException
	 */
	public void resetClassForm() throws BusinessException {
		setClassAdd(true);
		personClassVO = new PersonClassVO();
	}
	
	/**
	 * Reset complication form
	 * @throws BusinessException
	 */
	public void resetComplicationForm() throws BusinessException {
		setComplicationAdd(true);
		personComplicationVO = new PersonComplicationVO();
	}
	
	/**
	 * Reset remarks form
	 * @throws BusinessException
	 */
	public void resetRemarksForm() throws BusinessException {
		setRemarksAdd(true);
		remarksVO = new CustomerRemarksVO();
	}
	
	public void resetAttachmentForm() throws BusinessException {
		setAttachmentAdd(true);
		personAttachmentVO = new PersonAttachmentVO();
	}
	
	public void resetAddEmailForm() throws BusinessException {
		setEmailAdd(true);
		personEmailVO = new PersonEmailVO();
	}

	/**
	 * Customer maintenance
	 * flag - 0 = from cust page / 1 = from invoice page
	 */
	public void init(int flag) {
		trackingLogUtils = new TrackingLogUtils(this.getClass());
//		trackingLogUtils.startLogs();
		try {
			activeStatus = CommonConstant.STATUS_CD_ACTIVE; // Default Filter `Active`
			allowUpdateCusStatus = false;
			
			// Check Contain these specific role or not, if got only allow to show and edit
			List<UserRoleViewVO> userRoleList = securityBO.getUserRoleListByUser(this.getSessionInfoBean().getUserVO().getUuid());
			if (CollectionUtils.isNotEmpty(userRoleList)) {
				for (UserRoleViewVO userRoleVO : userRoleList) {
					if (userRoleVO.getRoleCode().equals(SalesConstant.CUSTOMER_STATUS_UPD_ROLE)) {
						allowUpdateCusStatus = true;
					}
				}
			}
			
			if (flag == 1) isFromInv = true;
			loadCustomer();
			countryList = regionBO.getActualCountryList();
			malaysiaStateList = malaysiaStateBO.getStateList();
			
			loadAuthority();
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
//			trackingLogUtils.endLogs("CustomerBean.init");
		}
	}
	
	/**
	 * Load customer list
	 * @throws BusinessException
	 */
	private void loadCustomer() throws BusinessException {
		resetForm();
		//if (!isFromInv) customerList = customerBO.getCustomerList(this.getSessionInfoBean().getCompanyVO().getId());
		//if (!isFromInv) lazyCustDataModel = new LazyCustomerDataModel();
		lazyCustDataModel = new LazyCustomerDataModel();
	}

	/**
	 * Add Customer
	 */
	public void addCustomer(String customerContext) {
		try {
			trackingLogUtils.startLogs();
			
			// check duplicate customer
			checkDuplicateCustomer(customerContext);
			
			EInvoiceUtils.validateCustomerContact(customerVO, corContactVO, personContactVO);
			EInvoiceUtils.validateCustomer(customerVO);
			
			customerVO.setCorContactList(new ArrayList<CorContactVO>());
			customerVO.setPersonContactList(new ArrayList<PersonContactVO>());
			customerVO.setCustRemarksList(new ArrayList<CustomerRemarksVO>());
			customerVO.setCompanyId(this.getSessionInfoBean().getCompanyVO().getId());
			convertNameToUpperCase(customerVO);
			
			CountryVO corMobileCountry = regionBO.getCountryById(corContactVO.getCorMobileCountryId());
			CountryVO corOfficeCountry = regionBO.getCountryById(corContactVO.getCorOfficeCountryId());
			CountryVO corHomeCountry = regionBO.getCountryById(corContactVO.getCorHomeCountryId());
			CountryVO corFaxCountry = regionBO.getCountryById(corContactVO.getCorFaxCountryId());
			// add corporate contact
			if (corMobileCountry != null) addCorContactIfNotEmpty(SalesConstant.MOBILE_TYPE_CD, corContactVO.getCorMobileNo(), corMobileCountry.getCountryCd(), corMobileCountry.getId(), corContactVO.getCorMobileAttn());
			if (corOfficeCountry != null) addCorContactIfNotEmpty(SalesConstant.OFFICE_TYPE_CD, corContactVO.getCorOfficeNo(), corOfficeCountry.getCountryCd(), corOfficeCountry.getId(), corContactVO.getCorOfficeAttn());
			if (corHomeCountry != null) addCorContactIfNotEmpty(SalesConstant.HOME_TYPE_CD, corContactVO.getCorHomeNo(), corHomeCountry.getCountryCd(), corHomeCountry.getId(), corContactVO.getCorHomeAttn());
			if (corFaxCountry != null) addCorContactIfNotEmpty(SalesConstant.FAX_TYPE_CD, corContactVO.getCorFaxNo(), corFaxCountry.getCountryCd(), corFaxCountry.getId(), corContactVO.getCorFaxAttn());

			CountryVO perMobileCountry = regionBO.getCountryById(personContactVO.getPerMobileCountryId());
			CountryVO perOfficeCountry = regionBO.getCountryById(personContactVO.getPerOfficeCountryId());
			CountryVO perHomeCountry = regionBO.getCountryById(personContactVO.getPerHomeCountryId());
			CountryVO perFaxCountry = regionBO.getCountryById(personContactVO.getPerFaxCountryId());
			CountryVO perOtherCountry = regionBO.getCountryById(personContactVO.getPerOtherCountryId());

			// add person contact
			if (perMobileCountry != null) addPersonContactIfNotEmpty(SalesConstant.MOBILE_TYPE_CD, personContactVO.getPerMobileNumber(), perMobileCountry.getCountryCd(), perMobileCountry.getId(), personContactVO.getPerMobileAttention());
			if (perOfficeCountry != null) addPersonContactIfNotEmpty(SalesConstant.OFFICE_TYPE_CD, personContactVO.getPerOfficeNumber(), perOfficeCountry.getCountryCd(), perOfficeCountry.getId(), personContactVO.getPerOfficeAttention());
			if (perHomeCountry != null) addPersonContactIfNotEmpty(SalesConstant.HOME_TYPE_CD, personContactVO.getPerHomeNumber(), perHomeCountry.getCountryCd(), perHomeCountry.getId(), personContactVO.getPerHomeAttention());
			if (perFaxCountry != null) addPersonContactIfNotEmpty(SalesConstant.FAX_TYPE_CD, personContactVO.getPerFaxNumber(), perFaxCountry.getCountryCd(), perFaxCountry.getId(), personContactVO.getPerFaxAttention());
			if (perOtherCountry != null) addPersonContactIfNotEmpty(SalesConstant.OTHER_NO_TYPE_CD, personContactVO.getPerOtherNumber(), perOtherCountry.getCountryCd(), perOtherCountry.getId(), personContactVO.getPerOtherAttention());

			CountryVO ecCountry = regionBO.getCountryById(customerVO.getPersonVO().getEcIdCountryCd());
			customerVO.getPersonVO().setEcCountryCd(ecCountry.getCountryCd());
			
			if(StringUtils.isNotEmpty(remarksVO.getRemarksDetails())) {
				CustomerRemarksVO vo = new CustomerRemarksVO();
				vo.setRemarksDetails(remarksVO.getRemarksDetails());
				vo.setTimestamp(new Date());
				customerVO.getCustRemarksList().add(vo);
			}
			
			// Check duplicate Primary Email and if there is at least a Primary Email
			if (CollectionUtils.isNotEmpty(customerVO.getPersonVO().getEmailList())) {
				int primaryCount = 0;
				boolean allPrimaryFalse = true;
				String emailRegex = CommonConstant.EMAIL_FORMAT;
				Pattern emailPattern = Pattern.compile(emailRegex);
				for (PersonEmailVO personEmailVO : customerVO.getPersonVO().getEmailList()) {
					if (personEmailVO.getIsPrimary()) {
						primaryCount++;
						allPrimaryFalse = false;
						if (primaryCount > 1) {
							throw new BusinessException(CommonErrConstant.ERR_PRIMARY_EMAIL_MORE_THAN_ONE);
						}
					}
					Matcher emailMatcher = emailPattern.matcher(personEmailVO.getEmail());
					if (!emailMatcher.matches()) {
						throw new BusinessException(CommonErrConstant.ERR_WRONG_FORMAT_EMAIL);
					}
				}
//				if (allPrimaryFalse == true) {
//					throw new BusinessException(CommonErrConstant.ERR_MISSING_PRIMARY_EMAIL);
//				}
				if (CommonConstant.BILLING_PERSON.equals(customerContext) && primaryCount == 0) {
			        throw new BusinessException(CommonErrConstant.ERR_MISSING_PRIMARY_EMAIL);
			    }
			}
			// Check if the email format for the primary email text is correct or not
			if (StringUtils.isNotBlank(customerVO.getPersonVO().getEmail())) {
				String emailRegex = CommonConstant.EMAIL_FORMAT;
				Pattern emailPattern = Pattern.compile(emailRegex);
				Matcher emailMatcher = emailPattern.matcher(customerVO.getPersonVO().getEmail());
				if (!emailMatcher.matches()) {
					throw new BusinessException(CommonErrConstant.ERR_WRONG_FORMAT_EMAIL);
				}
			}
			
			checkLocalCustomerIdentity();
			
			if (nricIdVO != null && StringUtils.isNotEmpty(nricIdVO.getIdNo())) {
				IdentityVO idttyVO = new IdentityVO();
				idttyVO.setIdType(CommonConstant.LOOKUP_ITM_ID_NRIC);
				idttyVO.setIdNo(nricIdVO.getIdNo());
				idttyVO.setIdentityDetailVO(new IdentityDetailVO());
				customerVO.getIdentityList().add(idttyVO);
			}
			
			if (passportIdVO != null && StringUtils.isNotEmpty(passportIdVO.getIdNo())) {
				IdentityVO idttyVO = new IdentityVO();
				idttyVO.setIdType(CommonConstant.LOOKUP_ITM_ID_PASSPRT);
				idttyVO.setIdNo(passportIdVO.getIdNo());
				idttyVO.setIdentityDetailVO(passportIdVO.getIdentityDetailVO());
				idttyVO.getIdentityDetailVO().setId(null);
				idttyVO.getIdentityDetailVO().setPersonIdentityId(null);
				customerVO.getIdentityList().add(idttyVO);
			}
			
			for(IdentityVO identityVO : customerVO.getIdentityList()) {
				if ((StringUtils.isNotEmpty(identityVO.getIdentityDetailVO().getScannedPath()) &&
					StringUtils.isEmpty(identityVO.getIdentityDetailVO().getFilePath()))
						|| (StringUtils.isEmpty(identityVO.getIdentityDetailVO().getScannedPath()) &&
							StringUtils.isNotEmpty(identityVO.getIdentityDetailVO().getFilePath()))) {
					throw new BusinessException(CommonErrConstant.ERR_PASSENGER_FILE_PATH_MISSING);
				}
			}
			
			customerBO.insertCustomer(customerVO);
			if (customerVO.getPcTypeCd().equals("P")) customerPosting(customerVO);
			
			// upload file
			if (fileUploadUtils.getMap().size() > 0) {
				fileUploadUtils.uploadFile();
			}
			//loadCustomer();
			/*custDetailsVO = invBO.getCustDetails(customerVO.getId());
			invoiceVO = new InvoiceVO();
			invoiceVO.setCustomerId(custDetailsVO.getCustId());
			invoiceVO.setAttnTo(custDetailsVO.getContPersonName());
			if (StringUtils.isNotEmpty(custDetailsVO.getNickName())) {
				invoiceVO.setAttnTo(custDetailsVO.getContPersonName() + " (" + custDetailsVO.getNickName() + ")");
			}
			invoiceVO.setCustDetailsVO(custDetailsVO);*/
			if (isFromInv) custDetailsVO = invBO.getCustDetails(customerVO.getId());
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("addCustomer");
		}
	}
	
	/**
	 * Edit Customer
	 */
	public void updateCustomer(String customerContext) {
		try {
			trackingLogUtils.startLogs();
			
			EInvoiceUtils.validateCustomerContact(customerVO, corContactVO, personContactVO);
			EInvoiceUtils.validateCustomer(customerVO);
			
			customerVO.setCompanyId(this.getSessionInfoBean().getCompanyVO().getId());
			convertNameToUpperCase(customerVO);
			customerVO.setCorContactList(new ArrayList<CorContactVO>());
			customerVO.setPersonContactList(new ArrayList<PersonContactVO>());
			customerVO.setCustRemarksList(new ArrayList<CustomerRemarksVO>());
			
			CountryVO corMobileCountry = regionBO.getCountryById(corContactVO.getCorMobileCountryId());
			CountryVO corOfficeCountry = regionBO.getCountryById(corContactVO.getCorOfficeCountryId());
			CountryVO corHomeCountry = regionBO.getCountryById(corContactVO.getCorHomeCountryId());
			CountryVO corFaxCountry = regionBO.getCountryById(corContactVO.getCorFaxCountryId());
			// add corporate contact
			if (corMobileCountry != null) addCorContactIfNotEmpty(SalesConstant.MOBILE_TYPE_CD, corContactVO.getCorMobileNo(), corMobileCountry.getCountryCd(), corMobileCountry.getId(), corContactVO.getCorMobileAttn());
			if (corOfficeCountry != null) addCorContactIfNotEmpty(SalesConstant.OFFICE_TYPE_CD, corContactVO.getCorOfficeNo(), corOfficeCountry.getCountryCd(), corOfficeCountry.getId(), corContactVO.getCorOfficeAttn());
			if (corHomeCountry != null) addCorContactIfNotEmpty(SalesConstant.HOME_TYPE_CD, corContactVO.getCorHomeNo(), corHomeCountry.getCountryCd(), corHomeCountry.getId(), corContactVO.getCorHomeAttn());
			if (corFaxCountry != null) addCorContactIfNotEmpty(SalesConstant.FAX_TYPE_CD, corContactVO.getCorFaxNo(), corFaxCountry.getCountryCd(), corFaxCountry.getId(), corContactVO.getCorFaxAttn());
			
			CountryVO perMobileCountry = regionBO.getCountryById(personContactVO.getPerMobileCountryId());
			CountryVO perOfficeCountry = regionBO.getCountryById(personContactVO.getPerOfficeCountryId());
			CountryVO perHomeCountry = regionBO.getCountryById(personContactVO.getPerHomeCountryId());
			CountryVO perFaxCountry = regionBO.getCountryById(personContactVO.getPerFaxCountryId());
			CountryVO perOtherCountry = regionBO.getCountryById(personContactVO.getPerOtherCountryId());
			// add person contact
			if (perMobileCountry != null) addPersonContactIfNotEmpty(SalesConstant.MOBILE_TYPE_CD, personContactVO.getPerMobileNumber(), perMobileCountry.getCountryCd(), perMobileCountry.getId(), personContactVO.getPerMobileAttention());
			if (perOfficeCountry != null) addPersonContactIfNotEmpty(SalesConstant.OFFICE_TYPE_CD, personContactVO.getPerOfficeNumber(), perOfficeCountry.getCountryCd(), perOfficeCountry.getId(), personContactVO.getPerOfficeAttention());
			if (perHomeCountry != null) addPersonContactIfNotEmpty(SalesConstant.HOME_TYPE_CD, personContactVO.getPerHomeNumber(), perHomeCountry.getCountryCd(), perHomeCountry.getId(), personContactVO.getPerHomeAttention());
			if (perFaxCountry != null) addPersonContactIfNotEmpty(SalesConstant.FAX_TYPE_CD, personContactVO.getPerFaxNumber(), perFaxCountry.getCountryCd(), perFaxCountry.getId(), personContactVO.getPerFaxAttention());
			if (perOtherCountry != null) addPersonContactIfNotEmpty(SalesConstant.OTHER_NO_TYPE_CD, personContactVO.getPerOtherNumber(), perOtherCountry.getCountryCd(), perOtherCountry.getId(), personContactVO.getPerOtherAttention());

			CountryVO ecCountry = regionBO.getCountryById(customerVO.getPersonVO().getEcIdCountryCd());
			customerVO.getPersonVO().setEcCountryCd(ecCountry.getCountryCd());

			if(StringUtils.isNotEmpty(remarksVO.getRemarksDetails())) {
				CustomerRemarksVO vo = new CustomerRemarksVO();
				vo.setRemarksDetails(remarksVO.getRemarksDetails());
				vo.setTimestamp(new Date());
				customerVO.getCustRemarksList().add(vo);
			}

			// Check duplicate Primary Email and check if there is at least a Primary Email
			if (CollectionUtils.isNotEmpty(customerVO.getPersonVO().getEmailList())) {
				int primaryCount = 0;
				boolean allPrimaryFalse = true;
				String emailRegex = CommonConstant.EMAIL_FORMAT;
				Pattern emailPattern = Pattern.compile(emailRegex);
				for (PersonEmailVO personEmailVO : customerVO.getPersonVO().getEmailList()) {
					if (personEmailVO.getIsPrimary()) {
						primaryCount++;
						allPrimaryFalse = false;
						if (primaryCount > 1) {
							throw new BusinessException(CommonErrConstant.ERR_PRIMARY_EMAIL_MORE_THAN_ONE);
						}
					}
					Matcher emailMatcher = emailPattern.matcher(personEmailVO.getEmail());
					if (!emailMatcher.matches()) {
						throw new BusinessException(CommonErrConstant.ERR_WRONG_FORMAT_EMAIL);
					}
				}
//				if (allPrimaryFalse == true) {
//					throw new BusinessException(CommonErrConstant.ERR_MISSING_PRIMARY_EMAIL);
//				}
				if (CommonConstant.BILLING_PERSON.equals(customerContext) && primaryCount == 0) {
			        throw new BusinessException(CommonErrConstant.ERR_MISSING_PRIMARY_EMAIL);
			    }
			}
			// Check if the email format for the primary email text is correct or not
			if (StringUtils.isNotBlank(customerVO.getPersonVO().getEmail())) {
				String emailRegex = CommonConstant.EMAIL_FORMAT;
				Pattern emailPattern = Pattern.compile(emailRegex);
				Matcher emailMatcher = emailPattern.matcher(customerVO.getPersonVO().getEmail());
				if (!emailMatcher.matches()) {
					throw new BusinessException(CommonErrConstant.ERR_WRONG_FORMAT_EMAIL);
				}
			}
			
			checkLocalCustomerIdentity();
			
			/********* Code Refactoring - Start *********/
			// (remove code above if new code stable 2025.08.05)
			IdentityUtils.syncNricPassport(nricIdVO, passportIdVO, customerVO.getIdentityList());
			/********* Code Refactoring - End *********/
			
			for(IdentityVO identityVO : customerVO.getIdentityList()) {
				if ((StringUtils.isNotEmpty(identityVO.getIdentityDetailVO().getScannedPath()) &&
					StringUtils.isEmpty(identityVO.getIdentityDetailVO().getFilePath()))
						|| (StringUtils.isEmpty(identityVO.getIdentityDetailVO().getScannedPath()) &&
							StringUtils.isNotEmpty(identityVO.getIdentityDetailVO().getFilePath()))) {
					throw new BusinessException(CommonErrConstant.ERR_PASSENGER_FILE_PATH_MISSING);
				}
			}
			
			// check duplicate customer
			checkDuplicateCustomer(customerContext);
			
			// TIN validation
			if (customerVO.getPcTypeCd().equals("C") && StringUtils.isNotBlank(customerVO.getCorporateVO().getTaxIdNo())
					|| customerVO.getPcTypeCd().equals("P") && StringUtils.isNotBlank(customerVO.getPersonVO().getTaxIdNo())) {
//				String accessToken = eInvoiceUtils.getAccessToken(getSessionInfoBean().getCompanyVO(), true);
				String accessToken = eInvoiceBO.getAccessToken(getSessionInfoBean().getCompanyVO(), true);
				if (!EInvoiceUtils.validateTaxpayerTin(customerVO, accessToken)) {
					throw new BusinessException(CommonErrConstant.ERR_E_INV_ERR_MSG, null, new String[] {"TIN not tally with identity."});
				}
			}
			customerBO.updateCustomer(customerVO);
			if (customerVO.getPcTypeCd().equals("P")) customerPosting(customerVO);
			/*customerBO.updObjList(customerVO, identityVOList);
			customerBO.updObjList(customerVO, languageVOList);
			customerBO.updObjList(customerVO, mealVOList);
			customerBO.updObjList(customerVO, contactVOList);
			customerBO.updObjList(customerVO, classVOList);
			customerBO.updObjList(customerVO, complicationVOList);
			customerBO.updObjList(customerVO, remarksVOList);*/
			
			/* Comment cause slowness will apply manually update
			customerBO.updateAcctTransDestinationCust(customerVO.getCompanyId(), customerVO.getId());
			*/
			
			// for audit trial history purpose
			customerBO.insertCustomerHistory(customerVO.getId(), CommonConstant.ACTION_CD_UPD, "[CUSTOMER] Update customer");
			
			// upload file
			fileUploadUtils.deleteFiles();
			if (fileUploadUtils.getMap().size() > 0) {
				fileUploadUtils.uploadFile();
			}
			
			// for invoice page use
			if (isFromInv) custDetailsVO = invBO.getCustDetails(customerVO.getId());
			
			//loadCustomer();
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("updateCustomer");
		}
	}
	
	/**
	 * Delete Customer
	 */
	public void deleteCustomer() {
		try {
			trackingLogUtils.startLogs();
			customerVO.setUpdatedBy(getSessionInfoBean().getUserVO().getName());
			customerBO.deleteCustomer(customerVO);
			//loadCustomer();
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("deleteCustomer");
		}
	}
	
	/**
	 * 
	 */
	public void updateCustomerStatus(CustomerVO vo, String status) {
		try {
			trackingLogUtils.startLogs();
			vo.setStatusCode(status);
			vo.setUpdatedBy(getSessionInfoBean().getUserVO().getName());
			customerBO.updateCustomerStatus(vo);
			resetForm();
			successResult();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("updateCustomerStatus");
		}
	}
	
	/**
	 * Update Account Transaction Destination for Customer
	 */
	public void updateAcctTransDestinationCust(Long idCust) {
		try {
			trackingLogUtils.startLogs();
			
			customerBO.updateAcctTransDestinationCust(this.getSessionInfoBean().getCompanyVO().getId(), idCust);
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("updateAcctTransDestinationCust");
		}
	}
	
	/**
	 * Update All Customer Name to Related Tables
	 */
	public void updateAllCustNmToRelatedTbl() {
		try {
			trackingLogUtils.startLogs();
			
			customerBO.updateAllCustNmToRelatedTbl(this.getSessionInfoBean().getCompanyVO().getId());
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("updateAllCustNmToRelatedTbl");
		}
	}
	
	public void unlockCustomer(Long idCust, boolean isLock) {
		try {
			trackingLogUtils.startLogs();
			
			customerBO.unlockCustomer(idCust, !isLock);
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("unlockCustomer");
		}
	}
	
	/**
	 * 
	 * @param vo
	 * Convert Customer Name To Upper Case
	 */
	public void convertNameToUpperCase(CustomerVO vo) {
		try {
			vo.getPersonVO().setLastName(vo.getPersonVO().getLastName().toUpperCase().trim());
			vo.getPersonVO().setGivenName(vo.getPersonVO().getGivenName().toUpperCase().trim());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add Identity
	 */
	public void addIdentity() {
		try {
			// Check Identity existed
			if (isIdentityExisted()) {
				if (identityVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_NRIC)) throw new BusinessException(CommonErrConstant.ERR_CUST_ID_TYPE_NIRC_EXISTED);
				else if (identityVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_PASSPRT)) throw new BusinessException(CommonErrConstant.ERR_CUST_ID_TYPE_PASS_EXISTED);
				else if (identityVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_VISA)) throw new BusinessException(CommonErrConstant.ERR_CUST_ID_TYPE_VISA_EXISTED);
				else if (identityVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_ONE_CARD)) throw new BusinessException(CommonErrConstant.ERR_CUST_ID_TYPE_ONE_CARD_EXISTED);
				else if (identityVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_APPLE)) throw new BusinessException(CommonErrConstant.ERR_CUST_ID_TYPE_APPLE_MEMBERSHIP_EXISTED);
				else throw new BusinessException(CommonErrConstant.ERR_CUST_ID_EXISTED);
				
			} else if (isRequiredScannedCopy()) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Scanned copy upload required.", "Scanned copy upload required.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
			} else {
				calculateAgeByNRIC();
				
				identityVO.setUuid(UUID.randomUUID().toString());
//				identityVO.getIdentityDetailVO().setScannedPath(fileName);
				if(!customerVO.isAddEdit()) identityVO.setPersonId(customerVO.getPersonVO().getId());
				customerVO.getIdentityList().add(identityVO);
				//identityVOList.getAddList().add(identityVO);
				resetIdentityForm();
				successResult();
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add Identity
	 */
	public boolean isIdentityExisted() {
		try {
//			System.out.println("CYY nricIdVO: " + nricIdVO.getId() + " " + nricIdVO.getIdNo());
//			System.out.println("CYY passportIdVO: " + passportIdVO.getId() + " " + passportIdVO.getIdNo());
			
			if (identityVO.getId() == null) {
				// newly added identity from "Add ID"
				if (StringUtils.equals(identityVO.getIdNo(), nricIdVO.getIdNo())) {
					return true;
				}
				
				if (StringUtils.equals(identityVO.getIdNo(), passportIdVO.getIdNo())) {
					return true;
				}
			}
			
			for (IdentityVO vo : customerVO.getIdentityList()) {
//				System.out.println("CYY vo: " + vo.getId() + " " + vo.getIdNo());
				if ((vo.getId() != null) && vo.getId().equals(identityVO.getId())) continue;
				if ((identityVO.getUuid() == null && (vo.getUuid() != identityVO.getUuid())) &&
					vo.getIdType().equalsIgnoreCase(identityVO.getIdType()) && vo.getIdNo().equalsIgnoreCase(identityVO.getIdNo())) {
					return true;
				}
				
				// add checking to current NRIC & passport idVO
//				if (nricIdVO != null && !vo.getId().equals(nricIdVO.getId()) && StringUtils.equals(nricIdVO.getIdNo(), vo.getIdNo())) {
//					return true;
//				}
//				
//				if (passportIdVO != null && !vo.getId().equals(passportIdVO.getId()) && StringUtils.equals(passportIdVO.getIdNo(), vo.getIdNo())) {
//					return true;
//				}
			}
			if (customerBO.isIdentityExisted(identityVO, this.getSessionInfoBean().getCompanyVO().getId())) {
				return true;
			}
		} catch (Throwable t) {
			errorResult(t);
		}
		
		return false;
	}
	
	public boolean isRequiredScannedCopy() {
		try {
			if (StringUtils.equals(identityVO.getIdType(), CommonConstant.LOOKUP_ITM_ID_PASSPRT) || StringUtils.equals(identityVO.getIdType(), CommonConstant.LOOKUP_ITM_ID_VISA)) {
				// Check if all field is empty then scanned path is required
				if (StringUtils.equals(identityVO.getIdType(), CommonConstant.LOOKUP_ITM_ID_PASSPRT)) {
					if (StringUtils.isNotBlank(identityVO.getIdNo()) || 
							(identityVO.getIdentityDetailVO() != null && (identityVO.getIdentityDetailVO().getDtExpired() != null || identityVO.getIdentityDetailVO().getDtIssued() != null))) {
						return false;
					}
				}
				
				if (StringUtils.equals(identityVO.getIdType(), CommonConstant.LOOKUP_ITM_ID_VISA)) {
					if (StringUtils.isNotBlank(identityVO.getIdNo()) || 
							(identityVO.getIdentityDetailVO() != null && (StringUtils.isNotBlank(identityVO.getIdentityDetailVO().getTypeCd()) || StringUtils.isNotBlank(identityVO.getIdentityDetailVO().getEntryCd()) ||
								identityVO.getIdentityDetailVO().getDtExpired() != null || identityVO.getIdentityDetailVO().getDtIssued() != null))) {
						return false;
					}
				}
				
				if (StringUtils.isEmpty(identityVO.getIdentityDetailVO().getScannedPath())) {
					return true;
				}
			}
		} catch (Throwable t) {
			errorResult(t);
		}
		
		return false;
	}
	
	/**
	 * Edit Identity
	 */
	public void updateIdentity() {
		try {
			// Check Identity existed
			if (isIdentityExisted()) {
				identityVO.setIdNo(identityCloneVO.getIdNo());
				if (identityVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_NRIC)) throw new BusinessException(CommonErrConstant.ERR_CUST_ID_TYPE_NIRC_EXISTED);
				else if (identityVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_PASSPRT)) throw new BusinessException(CommonErrConstant.ERR_CUST_ID_TYPE_PASS_EXISTED);
				else if (identityVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_VISA)) throw new BusinessException(CommonErrConstant.ERR_CUST_ID_TYPE_VISA_EXISTED);
				else if (identityVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_ONE_CARD)) throw new BusinessException(CommonErrConstant.ERR_CUST_ID_TYPE_ONE_CARD_EXISTED);
				else if (identityVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_APPLE)) throw new BusinessException(CommonErrConstant.ERR_CUST_ID_TYPE_APPLE_MEMBERSHIP_EXISTED);
				else throw new BusinessException(CommonErrConstant.ERR_CUST_ID_EXISTED);
				
			} else if (isRequiredScannedCopy()) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Scanned copy upload required.", "Scanned copy upload required.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
			} else {
				calculateAgeByNRIC();
				
				//identityVO.getIdentityDetailVO().setScannedPath(fileName);
				//identityVOList.getUpdList().add(identityVO);
				resetIdentityForm();
				successResult();
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void syncNRIC() {
		try {
//			updateNricInIdentityList();
			calculateAgeByNRIC(nricIdVO);
		} catch (Throwable t) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid NRIC.", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			errorResult(t);
		}
	}
	
	public void syncPassportNo() {
		try {
//			updatePassportInIdentityList();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void updateNricInIdentityList() {
		if (customerVO != null && CollectionUtils.isNotEmpty(customerVO.getIdentityList())) {
			for (IdentityVO identityVO : customerVO.getIdentityList()) {
				if(StringUtils.equals(identityVO.getIdType(), CommonConstant.LOOKUP_ITM_ID_NRIC)) {
					identityVO.setIdNo(nricIdVO.getIdNo());
				}
				return;
			}
		}
	}
	
	public void updatePassportInIdentityList() {
		if (customerVO != null && CollectionUtils.isNotEmpty(customerVO.getIdentityList())) {
			for (IdentityVO identityVO : customerVO.getIdentityList()) {
				if(StringUtils.equals(identityVO.getIdType(), CommonConstant.LOOKUP_ITM_ID_PASSPRT)) {
					identityVO.setIdNo(passportIdVO.getIdNo());
				}
				return;
			}
		}
	}
	
	private void calculateAgeByNRIC() throws ParseException {
		calculateAgeByNRIC(null);
	}
	
	private void calculateAgeByNRIC(IdentityVO idttyVO) throws ParseException {
		IdentityVO idVO = identityVO;
		if (idttyVO != null) idVO = idttyVO;
		if (StringUtils.equals(idVO.getIdType(), CommonConstant.LOOKUP_ITM_ID_NRIC)) {
			if (StringUtils.isNotBlank(idVO.getIdNo())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
	            Date dob = sdf.parse(idVO.getIdNo());
	            if (idVO.getIdNo().length() >= 6) dob = sdf.parse(idVO.getIdNo().substring(0, 6));

	            // Extract the year, month, and day
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(dob);
	            int year = cal.get(Calendar.YEAR) % 100;
	            int month = cal.get(Calendar.MONTH);
	            int day = cal.get(Calendar.DAY_OF_MONTH);

	            // Set the century based on certain conditions
	            if (year > Calendar.getInstance().get(Calendar.YEAR) % 100) {
	                year += 1900; // Assume it belongs to the 20th century
	            } else {
	                year += 2000; // Assume it belongs to the 21st century
	            }

	            // Set the corrected date of birth
	            cal.set(year, month, day);
	            dob = cal.getTime();

	            customerVO.getPersonVO().setDob(dob);

	            calculateAge();
			}
		}
	}
	
	/**
	 * Delete Identity
	 */
	public void deleteIdentity(IdentityVO vo) {
		try {
			String destinationPath = "";
			if(vo.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_PASSPRT)) {
				destinationPath = destinationPassportPath;
			} else {
				destinationPath = destinationVisaPath;
			}
			
			fileUploadUtils.getDelList().add(destinationPath + File.separator + vo.getIdentityDetailVO().getFilePath());
			//fileUploadUtils.deleteFile(destinationPath, vo.getIdentityDetailVO().getScannedPath());
			if (fileUploadUtils.getMap().size() > 0) {
				// remove file from map (cache)
				fileUploadUtils.deleteFileFromMap(vo.getIdentityDetailVO().getScannedPath());
			}
			
			customerVO.getIdentityList().remove(vo);
			//identityVOList.getDelList().add(vo);
			resetIdentityForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add Language
	 */
	public void addLanguage() {
		try {
			if(!customerVO.isAddEdit()) {
				personLangVO.setPersonId(customerVO.getPersonVO().getId());
			}
			customerVO.getPersonLangList().add(personLangVO);
			//languageVOList.getAddList().add(personLangVO);
			
			resetLanguageForm();
			
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Edit Language
	 */
	public void updateLanguage() {
		try {
			//languageVOList.getUpdList().add(personLangVO);
			
			resetLanguageForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete Language
	 */
	public void deleteLanguage(PersonLangVO vo) {
		try {
			customerVO.getPersonLangList().remove(vo);
			//languageVOList.getDelList().add(vo);
			
			resetLanguageForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add Meal
	 */
	public void addMeal() {
		try {
			if(!customerVO.isAddEdit()) {
				personMealVO.setPersonId(customerVO.getPersonVO().getId());
			}
			customerVO.getPersonMealList().add(personMealVO);
			//mealVOList.getAddList().add(personMealVO);
			
			resetMealForm();
			
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Edit Meal
	 */
	public void updateMeal() {
		try {
			//mealVOList.getUpdList().add(personMealVO);
			
			resetMealForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete Meal
	 */
	public void deleteMeal(PersonMealVO vo) {
		try {
			customerVO.getPersonMealList().remove(vo);
			//mealVOList.getDelList().add(vo);
			
			resetMealForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add Contact
	 */
	public void addContact() {
		try {
			if (customerVO.getPcTypeCd().equals("C")) {
				if(!customerVO.isAddEdit()) {
					corContactVO.setCorporateId(customerVO.getCorporateVO().getId());
					//customerBO.insertObject(corContactVO);
				}
				corContactVO.setContactNo(corContactVO.getAreaCode() + "-" + corContactVO.getpNumber());
				customerVO.getCorContactList().add(corContactVO);
				//contactVOList.getAddList().add(corContactVO);
			} else {
				if(!customerVO.isAddEdit()) {
					personContactVO.setPersonId(customerVO.getPersonVO().getId());
					//customerBO.insertObject(personContactVO);
				}
				personContactVO.setNumber(personContactVO.getAreaCode() + "-" + personContactVO.getpNumber());
				customerVO.getPersonContactList().add(personContactVO);
				//contactVOList.getAddList().add(personContactVO);
			}
			resetContactForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Edit Contact
	 */
	public void updateContact() {
		try {
			//if(!customerVO.isAddEdit()) {
				/*if (customerVO.getPcTypeCd().equals("C")) {
					//customerBO.updateObject(corContactVO);
					contactVOList.getUpdList().add(corContactVO);
				} else {
					//customerBO.updateObject(personContactVO);
					contactVOList.getUpdList().add(personContactVO);
				}*/
			//}
			if (customerVO.getPcTypeCd().equals("C")) {
				corContactVO.setContactNo(corContactVO.getAreaCode() + "-" + corContactVO.getpNumber());
			} else {
				personContactVO.setNumber(personContactVO.getAreaCode() + "-" + personContactVO.getpNumber());
			}
			
			resetContactForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete Contact
	 */
	public void deleteContact(PersonContactVO vo) {
		try {
			customerVO.getPersonContactList().remove(vo);
			//contactVOList.getDelList().add(vo);
			resetContactForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete Corporate Contact
	 */
	public void deleteCorpContact(CorContactVO vo) {
		try {
			customerVO.getCorContactList().remove(vo);
			//contactVOList.getDelList().add(vo);
			
			resetContactForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add Class
	 */
	public void addClass() {
		try {
			if(!customerVO.isAddEdit()) {
				personClassVO.setPersonId(customerVO.getPersonVO().getId());
			}
			customerVO.getPersonClassList().add(personClassVO);
			//classVOList.getAddList().add(personClassVO);
			resetClassForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Edit Class
	 */
	public void updateClass() {
		try {
			//classVOList.getUpdList().add(personClassVO);
			resetClassForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete Class
	 */
	public void deleteClass(PersonClassVO vo) {
		try {
			customerVO.getPersonClassList().remove(vo);
			//classVOList.getDelList().add(vo);
			resetClassForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add Complication
	 */
	public void addComplication() {
		try {
			if(!customerVO.isAddEdit()) {
				personComplicationVO.setPersonId(customerVO.getPersonVO().getId());
			}
			customerVO.getPersonComplicationList().add(personComplicationVO);
			//complicationVOList.getAddList().add(personComplicationVO);
			
			resetComplicationForm();
			
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Edit Complication
	 */
	public void updateComplication() {
		try {
			//complicationVOList.getUpdList().add(personComplicationVO);
			resetComplicationForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete Complication
	 */
	public void deleteComplication(PersonComplicationVO vo) {
		try {
			customerVO.getPersonComplicationList().remove(vo);
			//complicationVOList.getDelList().add(vo);
			resetComplicationForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add Remarks
	 */
	public void addRemarks() {
		try {
			remarksVO.setTimestamp(new Date());
			if(!customerVO.isAddEdit()) {
				remarksVO.setCustId(customerVO.getId());
			}
			customerVO.getCustRemarksList().add(remarksVO);
			//remarksVOList.getAddList().add(remarksVO);
			resetRemarksForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Edit Remarks
	 */
	public void updateRemarks() {
		try {
			//remarksVOList.getUpdList().add(remarksVO);
			resetRemarksForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete Remarks
	 */
	public void deleteRemarks(CustomerRemarksVO vo) {
		try {
			customerVO.getCustRemarksList().remove(vo);
			//remarksVOList.getDelList().add(vo);
			resetRemarksForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void addPersonAttachment() {
		try {
			if (personAttachmentVO.getUploadedFile() != null) {
				fileUploadUtils.uploadSingleFile(personAttachmentVO.getUploadedFile(), destinationAttachmentPath, personAttachmentVO.getFilePath(), null, null, false);

				personAttachmentVO.setPersonId(customerVO.getPersonVO().getId());
				personAttachmentVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
				customerBO.insertObject(personAttachmentVO);
				
				if (customerVO.getPersonAttachmentList() == null) customerVO.setPersonAttachmentList(new ArrayList<PersonAttachmentVO>());
				customerVO.getPersonAttachmentList().add(personAttachmentVO);
			}
			resetAttachmentForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void deletePersonAttachment() {
		try {
			fileUploadUtils.deleteFile(destinationAttachmentPath, personAttachmentVO.getFilePath());
			personAttachmentVO.setStatusCode(BaseConstant.STATUS_DELETED);
			customerBO.updateObject(personAttachmentVO);
			customerVO.getPersonAttachmentList().remove(personAttachmentVO);
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onAddEmail() {
		setEmailAdd(true);
	    if (customerVO.getPersonVO().getEmailList() == null || customerVO.getPersonVO().getEmailList().isEmpty()) {
	        personEmailVO.setIsPrimary(true);
	    } else {
	        personEmailVO.setIsPrimary(false);
	    }
	}
	
	public void addEmail() {
		try {
			if (customerVO.getPersonVO().getEmailList() == null) {
				customerVO.getPersonVO().setEmailList(new ArrayList<PersonEmailVO>());
			}
			customerVO.getPersonVO().getEmailList().add(personEmailVO);
			String newEmail = personEmailVO.getEmail();
			boolean newIsPrimary = personEmailVO.getIsPrimary();
			if (CollectionUtils.isNotEmpty(customerVO.getPersonVO().getEmailList())) {
				for (PersonEmailVO personEmailVO : customerVO.getPersonVO().getEmailList()) {
					// Newly added email if is primary, other email will be set non-primary
					if (!personEmailVO.getEmail().equals(newEmail) && newIsPrimary == true) {
						personEmailVO.setIsPrimary(false);
					}
					// If the newly added email is primary, the email text replaced by the primary email
					if (personEmailVO.getIsPrimary() == true) {
						customerVO.getPersonVO().setEmail(personEmailVO.getEmail());
					}
				}
			}
			resetAddEmailForm();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void updateEmail() {
		try {
			String newEmail = personEmailVO.getEmail();
			boolean newIsPrimary = personEmailVO.getIsPrimary();
			if (CollectionUtils.isNotEmpty(customerVO.getPersonVO().getEmailList())) {
				for (PersonEmailVO personEmailVO : customerVO.getPersonVO().getEmailList()) {
					// Newly updated email if is primary, other email will be set non-primary
					if (!personEmailVO.getEmail().equals(newEmail) && newIsPrimary == true) {
						personEmailVO.setIsPrimary(false);
					}
					// If the newly updated email is primary, the email text replaced by the primary email
					if (personEmailVO.getIsPrimary() == true) {
						customerVO.getPersonVO().setEmail(personEmailVO.getEmail());
					}
				}
			}
			resetAddEmailForm();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void deleteEmail(PersonEmailVO vo) {
		try {
			if (vo.getIsPrimary() != null && vo.getIsPrimary() == true) {
				if (StringUtils.isNotEmpty(customerVO.getPersonVO().getEmail()) &&
						StringUtils.equals(customerVO.getPersonVO().getEmail(), vo.getEmail())) {
					customerVO.getPersonVO().setEmail("");
				}
			}
			customerVO.getPersonVO().getEmailList().remove(vo);
			resetAddEmailForm();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	
	public void addPic() {
		try {
			if (CollectionUtils.isEmpty(customerVO.getCorPicList())) customerVO.setCorPicList(new ArrayList<CorPersonInChargeVO>());
			
			customerVO.getCorPicList().add(new CorPersonInChargeVO());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void deletePic(CorPersonInChargeVO vo) {
		try {
			customerVO.getCorPicList().remove(vo);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleAttachmentFileUpload(FileUploadEvent event) {
		try {
			String fileName = event.getFile().getFileName();
			fileName = fileName.replace(" ", "_");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			fileName = sdf.format(new Date()) + "_" + fileName;
			
			personAttachmentVO.setName(fileName);
			personAttachmentVO.setScannedPath(fileName);
			personAttachmentVO.setFilePath(UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(fileName));
			personAttachmentVO.setUploadedFile(event.getFile());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void delAttachmentScannedCopy() {
		try {
			personAttachmentVO.setName(null);
			personAttachmentVO.setScannedPath(null);
			personAttachmentVO.setFilePath(null);
			personAttachmentVO.setUploadedFile(null);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 *  Add is false 
	 */
	public void booleanAdd() {
		try {
			customerVO.setAddEdit(false);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete scanned copy
	 */
	public void delScannedCopy() {
		try {
			String destinationPath = "";
			if(identityVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_PASSPRT)) {
				destinationPath = destinationPassportPath;
			} else {
				destinationPath = destinationVisaPath;
			}
			
			fileUploadUtils.getDelList().add(destinationPath + File.separator + identityVO.getIdentityDetailVO().getFilePath());
			//fileUploadUtils.deleteFile(destinationPath, identityVO.getIdentityDetailVO().getScannedPath());
			if (fileUploadUtils.getMap().size() > 0) {
				fileUploadUtils.deleteFileFromMap(identityVO.getIdentityDetailVO().getScannedPath());
			}
			
			uploadedFile = "";
			identityVO.getIdentityDetailVO().setScannedPath(null);
			identityVO.getIdentityDetailVO().setFilePath(null);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void delScannedPassport() {
		try {
			String destinationPath = "";
			if(passportIdVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_PASSPRT)) {
				destinationPath = destinationPassportPath;
			} else {
				destinationPath = destinationVisaPath;
			}
			
			fileUploadUtils.getDelList().add(destinationPath + File.separator + passportIdVO.getIdentityDetailVO().getFilePath());
			if (fileUploadUtils.getMap().size() > 0) {
				fileUploadUtils.deleteFileFromMap(passportIdVO.getIdentityDetailVO().getScannedPath());
			}
			
			passportIdVO.setUploadedFile("");
			passportIdVO.getIdentityDetailVO().setScannedPath(null);
			passportIdVO.getIdentityDetailVO().setFilePath(null);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	
	
	public void onAddCustomer() {
		resetForm();
	}
	
	public void handleCustomerType() {
		try {
			customerVO.setLanguageName("");
			customerVO.setMealName("");
			customerVO.setClassName("");
			customerVO.setComplicationName("");
			customerVO.setLangCdList(new ArrayList<String>());
			customerVO.setMealList(new ArrayList<String>());
			customerVO.setClassList(new ArrayList<String>());
			customerVO.setCompliList(new ArrayList<String>());
			customerVO.setBillAddressVO(new AddressVO());
			customerVO.setMailAddressVO(new AddressVO());
			customerVO.setCorBillAddressVO(new CorAddressVO());
			customerVO.setCorMailAddressVO(new CorAddressVO());
			customerVO.getPersonVO().setCountryId(CommonConstant.DEF_COUNTRY_ID);
			customerVO.getBillAddressVO().setCountryId(CommonConstant.DEF_COUNTRY_ID);
			customerVO.getMailAddressVO().setCountryId(CommonConstant.DEF_COUNTRY_ID);
			customerVO.getCorBillAddressVO().setCountryId(CommonConstant.DEF_COUNTRY_ID);
			customerVO.getCorMailAddressVO().setCountryId(CommonConstant.DEF_COUNTRY_ID);
			customerVO.setCorPicList(new ArrayList<CorPersonInChargeVO>());
			
			nricIdVO = new IdentityVO();
			nricIdVO.setIdType(CommonConstant.LOOKUP_ITM_ID_NRIC);
			nricIdVO.setIdentityDetailVO(new IdentityDetailVO());
			passportIdVO = new IdentityVO();
			passportIdVO.setIdType(CommonConstant.LOOKUP_ITM_ID_PASSPRT);
			passportIdVO.setIdentityDetailVO(new IdentityDetailVO());
			if (passportIdVO.getIdentityDetailVO().getIdCountry() == null) passportIdVO.getIdentityDetailVO().setIdCountry(CommonConstant.DEF_COUNTRY_ID);
			
			deepCopy = false;
		}catch(Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleLanguageChange() {
		try {
			if (StringUtils.isNotBlank(customerVO.getLanguageName())) {
				customerVO.setLanguageName("");
			}
			if (CollectionUtils.isNotEmpty(customerVO.getLangCdList())) {
				List<LookupItemVO> languageList = customerBO.getLookUpItemList(CommonConstant.LOOKUP_CAT_CD_LANG_DILC);
				for(LookupItemVO vo : languageList) {
					for(String code : customerVO.getLangCdList()) {
						if(StringUtils.equals(vo.getCode(), code))
							customerVO.setLanguageName(StringUtils.isBlank(customerVO.getLanguageName()) ? vo.getDescription() : customerVO.getLanguageName() + ", " + vo.getDescription());
					}
				}
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleMealChange() {
		try {
			if (StringUtils.isNotBlank(customerVO.getMealName())) {
				customerVO.setMealName("");
			}
			if (CollectionUtils.isNotEmpty(customerVO.getMealList())) {
				List<LookupItemVO> mealList = customerBO.getLookUpItemList(CommonConstant.LOOKUP_CAT_CD_MEAL_PREF);
				for(LookupItemVO vo : mealList) {
					for(String code : customerVO.getMealList()) {
						if(StringUtils.equals(vo.getCode(), code))
							customerVO.setMealName(StringUtils.isBlank(customerVO.getMealName()) ? vo.getDescription() : customerVO.getMealName() + ", " + vo.getDescription());
					}
				}
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleClassChange() {
		try {
			if(StringUtils.isNotBlank(customerVO.getClassName())) {
				customerVO.setClassName("");
			}
			if(CollectionUtils.isNotEmpty(customerVO.getClassList())) {
				List<LookupItemVO> classList = customerBO.getLookUpItemList(CommonConstant.LOOKUP_CAT_CD_CUST_CLS);
				for(LookupItemVO vo : classList) {
					for(String code : customerVO.getClassList()) {
						if(StringUtils.equals(vo.getCode(), code))
							customerVO.setClassName(StringUtils.isBlank(customerVO.getClassName()) ? vo.getDescription() : customerVO.getClassName() + ", " + vo.getDescription());
					}
				}
			}
		}catch(Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleComplicationChange() {
		try {
			if(StringUtils.isNotBlank(customerVO.getComplicationName())) {
				customerVO.setComplicationName("");
			}
			if(CollectionUtils.isNotEmpty(customerVO.getCompliList())) {
				List<LookupItemVO> compliList = customerBO.getLookUpItemList(CommonConstant.LOOKUP_CAT_CD_CUST_CMPL);
				for(LookupItemVO vo : compliList) {
					for(String code : customerVO.getCompliList()) {
						if(StringUtils.equals(vo.getCode(), code))
							customerVO.setComplicationName(StringUtils.isBlank(customerVO.getComplicationName()) ? vo.getDescription() : customerVO.getComplicationName() + ", " + vo.getDescription());
					}
				}
			}
		}catch(Throwable t) {
			errorResult(t);
		}
	}
	/**
	 * 
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		try {
			// rename the valid file name
			fileName = event.getFile().getFileName();
			fileName = fileName.replace(" ", "_");
			String destinationPath = "";
			
			if(identityVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_PASSPRT)) {
				destinationPath = destinationPassportPath;
			} else {
				destinationPath = destinationVisaPath;
			}
			
			identityVO.getIdentityDetailVO().setFilePath(UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(fileName));
			
			// file validation
			// fileUploadUtils.uploadFileValidation(destinationPath, fileName);
			fileUploadUtils.getMap().put(fileName, new FileUploadVO(event.getFile(), destinationPath, identityVO.getIdentityDetailVO().getFilePath()));
			
			uploadedFile = fileName;
			identityVO.getIdentityDetailVO().setScannedPath(fileName);
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success! ", event.getFile().getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleFileUploadPassport(FileUploadEvent event) {
		try {
			// rename the valid file name
			fileName = event.getFile().getFileName();
			fileName = fileName.replace(" ", "_");
			String destinationPath = "";
			
			if(passportIdVO.getIdType().equals(CommonConstant.LOOKUP_ITM_ID_PASSPRT)) {
				destinationPath = destinationPassportPath;
			} else {
				destinationPath = destinationVisaPath;
			}
			
			passportIdVO.getIdentityDetailVO().setFilePath(UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(fileName));
			
			// file validation
			// fileUploadUtils.uploadFileValidation(destinationPath, fileName);
			fileUploadUtils.getMap().put(fileName, new FileUploadVO(event.getFile(), destinationPath, passportIdVO.getIdentityDetailVO().getFilePath()));
			
			passportIdVO.setUploadedFile(fileName);
			passportIdVO.getIdentityDetailVO().setScannedPath(fileName);
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success! ", event.getFile().getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 *  Calculate Age
	 */
	public void calculateAge() {
		if (customerVO.getPersonVO().getDob() != null) {
			Calendar dob = Calendar.getInstance();
			dob.setTime(customerVO.getPersonVO().getDob());
			Calendar today = Calendar.getInstance();

			Integer age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
			
			if(today.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR)) {
				age--;
			}
			
			//set the age variable
			customerVO.getPersonVO().setAge(age);
		} else {
			customerVO.getPersonVO().setAge(0);
		}
	}
	
	public Integer calculateAgeByIdNo(String idNo) {
		return CalculationUtils.calculateAgeByIdNo(idNo);
	}
	
	/**
	 * 
	 *  filterSearchIdentity
	 */
	public String filterSearchIdentity(List<IdentityVO> identityList) {
		String returnStr = "";
		if (identityList.size() > 0) {
			for (int i=0; identityList.size() > i; i++) {
				IdentityVO vo = identityList.get(i);
				if (i == 0) {
					returnStr += vo.getIdNo();
				} else {
					returnStr += " " + vo.getIdNo();
				}
			}
		}
		return returnStr;
	}
	
	/**
	 * On Customer selected
	 * @param event
	 */
	public void onCustomerSelected(CustomerVO vo, Boolean addEdit) {
		System.out.println("CustomerBean.onCustomerSelected(CustomerVO vo, Boolean addEdit)");
		try {
			trackingLogUtils.startLogs();
			
			customerVO.setAddEdit(addEdit);
			if (!addEdit) {
				this.setCustomerVO(customerBO.getCustomerDetail(vo.getId()));
				handleLanguageChange();
				handleMealChange();
				handleClassChange();
				handleComplicationChange();
				
				if (!CRMProperties.isHide()) {
					handleCustomerPointTier(vo.getId());
					handleCustomerVoucher(customerVO);
					crmMembershipVO = crmMembershipBO.getCRMMembership(customerVO.getId());
				}
				
				calculateAge();
				
				if (customerVO.getPersonVO() != null && StringUtils.isBlank(customerVO.getPersonVO().getSalutation())) {
					customerVO.getPersonVO().setSalutation(LookupItemConstant.SALUTATION_EMPTY_CD);	
				}
				
				if (customerVO.getPersonVO().getEcIdCountryCd() == null) {
					customerVO.getPersonVO().setEcIdCountryCd(CommonConstant.DEF_COUNTRY_ID);
				}
					
				for (IdentityVO idVO : customerVO.getIdentityList()) {
					if (StringUtils.equals(idVO.getIdType(), CommonConstant.LOOKUP_ITM_ID_NRIC)) {
						nricIdVO = (IdentityVO) idVO.clone();
						nricIdVO.setIdNo(idVO.getIdNo());
						calculateAgeByNRIC(nricIdVO);
						continue;
					}
					if (StringUtils.equals(idVO.getIdType(), CommonConstant.LOOKUP_ITM_ID_PASSPRT)) {
						passportIdVO = (IdentityVO) idVO.clone();
						passportIdVO.setIdNo(idVO.getIdNo());
						if (idVO.getIdentityDetailVO() != null) {
							passportIdVO.setIdentityDetailVO((IdentityDetailVO) idVO.getIdentityDetailVO().clone());
							passportIdVO.setUploadedFile(passportIdVO.getIdentityDetailVO().getScannedPath());
							continue;
						} else {
							passportIdVO.setIdentityDetailVO(new IdentityDetailVO());
							passportIdVO.getIdentityDetailVO().setIdCountry(CommonConstant.DEF_COUNTRY_ID);
						}
					}
				}
				
				for (PersonContactVO perContVO : customerVO.getPersonContactList()) {
					if (StringUtils.equals(perContVO.getTypeCd(), SalesConstant.MOBILE_TYPE_CD)) {
						if (perContVO.getIdCountryCd() != null) {
						    personContactVO.setPerMobileCountryId(perContVO.getIdCountryCd());
						} else {
							personContactVO.setPerMobileCountryId(CommonConstant.DEF_COUNTRY_ID);
						}
						personContactVO.setPerMobileNumber(perContVO.getNumber());
						personContactVO.setPerMobileCountryCd(perContVO.getCountryCd());
						personContactVO.setPerMobileAttention(perContVO.getAttention());
					}
					if (StringUtils.equals(perContVO.getTypeCd(), SalesConstant.OFFICE_TYPE_CD)) {
						if (perContVO.getIdCountryCd() != null) {
						    personContactVO.setPerOfficeCountryId(perContVO.getIdCountryCd());
						} else {
							personContactVO.setPerOfficeCountryId(CommonConstant.DEF_COUNTRY_ID);
						}
						personContactVO.setPerOfficeNumber(perContVO.getNumber());
						personContactVO.setPerOfficeCountryCd(perContVO.getCountryCd());
						personContactVO.setPerOfficeAttention(perContVO.getAttention());
					}
					if (StringUtils.equals(perContVO.getTypeCd(), SalesConstant.HOME_TYPE_CD)) {
						if (perContVO.getIdCountryCd() != null) {
						    personContactVO.setPerHomeCountryId(perContVO.getIdCountryCd());
						} else {
							personContactVO.setPerHomeCountryId(CommonConstant.DEF_COUNTRY_ID);
						}		
						personContactVO.setPerHomeNumber(perContVO.getNumber());
						personContactVO.setPerHomeCountryCd(perContVO.getCountryCd());
						personContactVO.setPerHomeAttention(perContVO.getAttention());
					}
					if (StringUtils.equals(perContVO.getTypeCd(), SalesConstant.FAX_TYPE_CD)) {
						if (perContVO.getIdCountryCd() != null) {
						    personContactVO.setPerFaxCountryId(perContVO.getIdCountryCd());
						} else {
							personContactVO.setPerFaxCountryId(CommonConstant.DEF_COUNTRY_ID);
						}						
						personContactVO.setPerFaxNumber(perContVO.getNumber());
						personContactVO.setPerFaxCountryCd(perContVO.getCountryCd());
						personContactVO.setPerFaxAttention(perContVO.getAttention());
					}
					if (StringUtils.equals(perContVO.getTypeCd(), SalesConstant.OTHER_NO_TYPE_CD)) {
						if (perContVO.getIdCountryCd() != null) {
						    personContactVO.setPerOtherCountryId(perContVO.getIdCountryCd());
						} else {
							personContactVO.setPerOtherCountryId(CommonConstant.DEF_COUNTRY_ID);
						}						
						personContactVO.setPerOtherNumber(perContVO.getNumber());
						personContactVO.setPerOtherCountryCd(perContVO.getCountryCd());
						personContactVO.setPerOtherAttention(perContVO.getAttention());
					}
				}
				for(CorContactVO corContVO : customerVO.getCorContactList()) {
					if (StringUtils.equals(corContVO.getContactType(), SalesConstant.MOBILE_TYPE_CD)) {
						if (corContVO.getIdCountryCd() != null) {
						    corContactVO.setCorMobileCountryId(corContVO.getIdCountryCd());
						} else {
							corContactVO.setCorMobileCountryId(CommonConstant.DEF_COUNTRY_ID);
						}
						corContactVO.setCorMobileNo(corContVO.getContactNo());
						corContactVO.setCorMobileCountryCd(corContVO.getCountryCd());
						corContactVO.setCorMobileAttn(corContVO.getAttention());
					}
					if (StringUtils.equals(corContVO.getContactType(), SalesConstant.OFFICE_TYPE_CD)) {
						if (corContVO.getIdCountryCd() != null) {
						    corContactVO.setCorOfficeCountryId(corContVO.getIdCountryCd());
						} else {
							corContactVO.setCorOfficeCountryId(CommonConstant.DEF_COUNTRY_ID);
						}
						corContactVO.setCorOfficeNo(corContVO.getContactNo());
						corContactVO.setCorOfficeCountryCd(corContVO.getCountryCd());
						corContactVO.setCorOfficeAttn(corContVO.getAttention());
					}
					if (StringUtils.equals(corContVO.getContactType(), SalesConstant.HOME_TYPE_CD)) {
						if (corContVO.getIdCountryCd() != null) {
						    corContactVO.setCorHomeCountryId(corContVO.getIdCountryCd());
						} else {
							corContactVO.setCorHomeCountryId(CommonConstant.DEF_COUNTRY_ID);
						}
						corContactVO.setCorHomeNo(corContVO.getContactNo());
						corContactVO.setCorHomeCountryCd(corContVO.getCountryCd());
						corContactVO.setCorHomeAttn(corContVO.getAttention());
					}
					if (StringUtils.equals(corContVO.getContactType(), SalesConstant.FAX_TYPE_CD)) {
						if (corContVO.getIdCountryCd() != null) {
						    corContactVO.setCorFaxCountryId(corContVO.getIdCountryCd());
						} else {
							corContactVO.setCorFaxCountryId(CommonConstant.DEF_COUNTRY_ID);
						}
						corContactVO.setCorFaxNo(corContVO.getContactNo());
						corContactVO.setCorFaxCountryCd(corContVO.getCountryCd());
						corContactVO.setCorFaxAttn(corContVO.getAttention());
					}
				}
				for(CustomerRemarksVO custRemarkVO : customerVO.getCustRemarksList()) {
					remarksVO.setRemarksDetails(custRemarkVO.getRemarksDetails());
				}
				for(AddressVO addressVO : customerVO.getAddressList()) {
					if(StringUtils.isNotBlank(addressVO.getAddrType())) {
						if(addressVO.getAddrType().equals(CommonConstant.BILL_ADDR)) {
							customerVO.getBillAddressVO().setCompanyName(StringUtils.isEmpty(addressVO.getCompanyName()) ? "" : addressVO.getCompanyName());
							customerVO.getBillAddressVO().setAddr1(addressVO.getAddr1());
							customerVO.getBillAddressVO().setAddr2(addressVO.getAddr2());
							customerVO.getBillAddressVO().setAddr3(addressVO.getAddr3());
							customerVO.getBillAddressVO().setCity(addressVO.getCity());
							customerVO.getBillAddressVO().setState(addressVO.getState());
							customerVO.getBillAddressVO().setPostcode(addressVO.getPostcode());
							customerVO.getBillAddressVO().setCountryId(addressVO.getCountryId());
						}
						if(addressVO.getAddrType().equals(CommonConstant.MAIL_ADDR)) {
							customerVO.getMailAddressVO().setCompanyName(StringUtils.isEmpty(addressVO.getCompanyName()) ? "" : addressVO.getCompanyName());
							customerVO.getMailAddressVO().setAddr1(addressVO.getAddr1());
							customerVO.getMailAddressVO().setAddr2(addressVO.getAddr2());
							customerVO.getMailAddressVO().setAddr3(addressVO.getAddr3());
							customerVO.getMailAddressVO().setCity(addressVO.getCity());
							customerVO.getMailAddressVO().setState(addressVO.getState());
							customerVO.getMailAddressVO().setPostcode(addressVO.getPostcode());
							customerVO.getMailAddressVO().setCountryId(addressVO.getCountryId());
						}
					}else {
						customerVO.getBillAddressVO().setAddr1(addressVO.getAddr1());
						customerVO.getBillAddressVO().setAddr2(addressVO.getAddr2());
						customerVO.getBillAddressVO().setAddr3(addressVO.getAddr3());
						customerVO.getBillAddressVO().setCity(addressVO.getCity());
						customerVO.getBillAddressVO().setState(addressVO.getState());
						customerVO.getBillAddressVO().setPostcode(addressVO.getPostcode());
						customerVO.getBillAddressVO().setCountryId(addressVO.getCountryId());
					}
				}
				for(CorAddressVO corAddressVO : customerVO.getCorAddressList()) {
					if(StringUtils.isNotBlank(corAddressVO.getAddrType())) {
						if(corAddressVO.getAddrType().equals(CommonConstant.BILL_ADDR)) {
							customerVO.getCorBillAddressVO().setAddr1(corAddressVO.getAddr1());
							customerVO.getCorBillAddressVO().setAddr2(corAddressVO.getAddr2());
							customerVO.getCorBillAddressVO().setAddr3(corAddressVO.getAddr3());
							customerVO.getCorBillAddressVO().setCity(corAddressVO.getCity());
							customerVO.getCorBillAddressVO().setState(corAddressVO.getState());
							customerVO.getCorBillAddressVO().setPostcode(corAddressVO.getPostcode());
							customerVO.getCorBillAddressVO().setCountryId(corAddressVO.getCountryId());
						}

						if(corAddressVO.getAddrType().equals(CommonConstant.MAIL_ADDR)) {
							customerVO.getCorMailAddressVO().setAddr1(corAddressVO.getAddr1());
							customerVO.getCorMailAddressVO().setAddr2(corAddressVO.getAddr2());
							customerVO.getCorMailAddressVO().setAddr3(corAddressVO.getAddr3());
							customerVO.getCorMailAddressVO().setCity(corAddressVO.getCity());
							customerVO.getCorMailAddressVO().setState(corAddressVO.getState());
							customerVO.getCorMailAddressVO().setPostcode(corAddressVO.getPostcode());
							customerVO.getCorMailAddressVO().setCountryId(corAddressVO.getCountryId());
						}
					}else {
						customerVO.getCorBillAddressVO().setAddr1(corAddressVO.getAddr1());
						customerVO.getCorBillAddressVO().setAddr2(corAddressVO.getAddr2());
						customerVO.getCorBillAddressVO().setAddr3(corAddressVO.getAddr3());
						customerVO.getCorBillAddressVO().setCity(corAddressVO.getCity());
						customerVO.getCorBillAddressVO().setState(corAddressVO.getState());
						customerVO.getCorBillAddressVO().setPostcode(corAddressVO.getPostcode());
						customerVO.getCorBillAddressVO().setCountryId(corAddressVO.getCountryId());
					}
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("onCustomerSelected");
		}
	}
	
	
	/**
	 * 
	 * @param id
	 */
	public void onCustomerSelected(boolean addNewCust, Long id) {
		System.out.println("CustomerBean.onCustomerSelected(boolean addNewCust, Long id)");
		try {
			if(id == null) {
				resetForm();
				calculateAge();
			}else {
				setCustomerVO(customerBO.getCustomerDetail(id));
				handleLanguageChange();
				handleMealChange();
				handleClassChange();
				handleComplicationChange();
				if (!CRMProperties.isHide()) {
					handleCustomerPointTier(id);
					handleCustomerVoucher(customerVO);
					crmMembershipVO = crmMembershipBO.getCRMMembership(customerVO.getId());
				}
				
				if (customerVO.getPersonVO().getEcIdCountryCd() == null) {
					customerVO.getPersonVO().setEcIdCountryCd(CommonConstant.DEF_COUNTRY_ID);
				}
				
				for (IdentityVO idVO : customerVO.getIdentityList()) {
					if (StringUtils.equals(idVO.getIdType(), CommonConstant.LOOKUP_ITM_ID_NRIC)) {
						nricIdVO = (IdentityVO) idVO.clone();
						nricIdVO.setIdNo(idVO.getIdNo());
						calculateAgeByNRIC(nricIdVO);
						continue;
					}
					if (StringUtils.equals(idVO.getIdType(), CommonConstant.LOOKUP_ITM_ID_PASSPRT)) {
						passportIdVO = (IdentityVO) idVO.clone();
						passportIdVO.setIdNo(idVO.getIdNo());
						if (idVO.getIdentityDetailVO() != null) {
							passportIdVO.setIdentityDetailVO((IdentityDetailVO) idVO.getIdentityDetailVO().clone());
							passportIdVO.setUploadedFile(passportIdVO.getIdentityDetailVO().getScannedPath());
							continue;
						} else {
							passportIdVO.setIdentityDetailVO(new IdentityDetailVO());
							passportIdVO.getIdentityDetailVO().setIdCountry(CommonConstant.DEF_COUNTRY_ID);
						}
					}
				}
				for (PersonContactVO perContVO : customerVO.getPersonContactList()) {
					if (StringUtils.equals(perContVO.getTypeCd(), SalesConstant.MOBILE_TYPE_CD)) {
						if (perContVO.getIdCountryCd() != null) {
						    personContactVO.setPerMobileCountryId(perContVO.getIdCountryCd());
						} else {
							personContactVO.setPerMobileCountryId(CommonConstant.DEF_COUNTRY_ID);
						}
						personContactVO.setPerMobileNumber(perContVO.getNumber());
						personContactVO.setPerMobileCountryCd(perContVO.getCountryCd());
						personContactVO.setPerMobileAttention(perContVO.getAttention());
					}
					if (StringUtils.equals(perContVO.getTypeCd(), SalesConstant.OFFICE_TYPE_CD)) {
						if (perContVO.getIdCountryCd() != null) {
						    personContactVO.setPerOfficeCountryId(perContVO.getIdCountryCd());
						} else {
							personContactVO.setPerOfficeCountryId(CommonConstant.DEF_COUNTRY_ID);
						}	
						personContactVO.setPerOfficeNumber(perContVO.getNumber());
						personContactVO.setPerOfficeCountryCd(perContVO.getCountryCd());
						personContactVO.setPerOfficeAttention(perContVO.getAttention());
					}
					if (StringUtils.equals(perContVO.getTypeCd(), SalesConstant.HOME_TYPE_CD)) {
						if (perContVO.getIdCountryCd() != null) {
						    personContactVO.setPerHomeCountryId(perContVO.getIdCountryCd());
						} else {
							personContactVO.setPerHomeCountryId(CommonConstant.DEF_COUNTRY_ID);
						}
						personContactVO.setPerHomeNumber(perContVO.getNumber());
						personContactVO.setPerHomeCountryCd(perContVO.getCountryCd());
						personContactVO.setPerHomeAttention(perContVO.getAttention());
					}
					if (StringUtils.equals(perContVO.getTypeCd(), SalesConstant.FAX_TYPE_CD)) {
						if (perContVO.getIdCountryCd() != null) {
						    personContactVO.setPerFaxCountryId(perContVO.getIdCountryCd());
						} else {
							personContactVO.setPerFaxCountryId(CommonConstant.DEF_COUNTRY_ID);
						}
						personContactVO.setPerFaxNumber(perContVO.getNumber());
						personContactVO.setPerFaxCountryCd(perContVO.getCountryCd());
						personContactVO.setPerFaxAttention(perContVO.getAttention());
					}
					if (StringUtils.equals(perContVO.getTypeCd(), SalesConstant.OTHER_NO_TYPE_CD)) {
						if (perContVO.getIdCountryCd() != null) {
						    personContactVO.setPerOtherCountryId(perContVO.getIdCountryCd());
						} else {
							personContactVO.setPerOtherCountryId(CommonConstant.DEF_COUNTRY_ID);
						}						
						personContactVO.setPerOtherNumber(perContVO.getNumber());
						personContactVO.setPerOtherCountryCd(perContVO.getCountryCd());
						personContactVO.setPerOtherAttention(perContVO.getAttention());
					}
				}
				for(CorContactVO corContVO : customerVO.getCorContactList()) {
					if (StringUtils.equals(corContVO.getContactType(), SalesConstant.MOBILE_TYPE_CD)) {
						if (corContVO.getIdCountryCd() != null) {
						    corContactVO.setCorMobileCountryId(corContVO.getIdCountryCd());
						} else {
							corContactVO.setCorMobileCountryId(CommonConstant.DEF_COUNTRY_ID);
						}
						corContactVO.setCorMobileNo(corContVO.getContactNo());
						corContactVO.setCorMobileCountryCd(corContVO.getCountryCd());
						corContactVO.setCorMobileAttn(corContVO.getAttention());
					}
					if (StringUtils.equals(corContVO.getContactType(), SalesConstant.OFFICE_TYPE_CD)) {
						if (corContVO.getIdCountryCd() != null) {
						    corContactVO.setCorOfficeCountryId(corContVO.getIdCountryCd());
						} else {
							corContactVO.setCorOfficeCountryId(CommonConstant.DEF_COUNTRY_ID);
						}
						corContactVO.setCorOfficeNo(corContVO.getContactNo());
						corContactVO.setCorOfficeCountryCd(corContVO.getCountryCd());
						corContactVO.setCorOfficeAttn(corContVO.getAttention());
					}
					if (StringUtils.equals(corContVO.getContactType(), SalesConstant.HOME_TYPE_CD)) {
						if (corContVO.getIdCountryCd() != null) {
						    corContactVO.setCorHomeCountryId(corContVO.getIdCountryCd());
						} else {
							corContactVO.setCorHomeCountryId(CommonConstant.DEF_COUNTRY_ID);
						}
						corContactVO.setCorHomeNo(corContVO.getContactNo());
						corContactVO.setCorHomeCountryCd(corContVO.getCountryCd());
						corContactVO.setCorHomeAttn(corContVO.getAttention());
					}
					if (StringUtils.equals(corContVO.getContactType(), SalesConstant.FAX_TYPE_CD)) {
						if (corContVO.getIdCountryCd() != null) {
						    corContactVO.setCorFaxCountryId(corContVO.getIdCountryCd());
						} else {
							corContactVO.setCorFaxCountryId(CommonConstant.DEF_COUNTRY_ID);
						}
						corContactVO.setCorFaxNo(corContVO.getContactNo());
						corContactVO.setCorFaxCountryCd(corContVO.getCountryCd());
						corContactVO.setCorFaxAttn(corContVO.getAttention());
					}
				}
				for(CustomerRemarksVO custRemarkVO : customerVO.getCustRemarksList()) {
					remarksVO.setRemarksDetails(custRemarkVO.getRemarksDetails());
				}
				for(AddressVO addressVO : customerVO.getAddressList()) {
					if(StringUtils.isNotBlank(addressVO.getAddrType())) {
						if(addressVO.getAddrType().equals(CommonConstant.BILL_ADDR)) {
							customerVO.getBillAddressVO().setCompanyName(StringUtils.isEmpty(addressVO.getCompanyName()) ? "" : addressVO.getCompanyName());
							customerVO.getBillAddressVO().setAddr1(addressVO.getAddr1());
							customerVO.getBillAddressVO().setAddr2(addressVO.getAddr2());
							customerVO.getBillAddressVO().setAddr3(addressVO.getAddr3());
							customerVO.getBillAddressVO().setCity(addressVO.getCity());
							customerVO.getBillAddressVO().setState(addressVO.getState());
							customerVO.getBillAddressVO().setPostcode(addressVO.getPostcode());
							customerVO.getBillAddressVO().setCountryId(addressVO.getCountryId());
						}
						if(addressVO.getAddrType().equals(CommonConstant.MAIL_ADDR)) {
							customerVO.getMailAddressVO().setCompanyName(StringUtils.isEmpty(addressVO.getCompanyName()) ? "" : addressVO.getCompanyName());
							customerVO.getMailAddressVO().setAddr1(addressVO.getAddr1());
							customerVO.getMailAddressVO().setAddr2(addressVO.getAddr2());
							customerVO.getMailAddressVO().setAddr3(addressVO.getAddr3());
							customerVO.getMailAddressVO().setCity(addressVO.getCity());
							customerVO.getMailAddressVO().setState(addressVO.getState());
							customerVO.getMailAddressVO().setPostcode(addressVO.getPostcode());
							customerVO.getMailAddressVO().setCountryId(addressVO.getCountryId());
						}
					}else {
						customerVO.getBillAddressVO().setAddr1(addressVO.getAddr1());
						customerVO.getBillAddressVO().setAddr2(addressVO.getAddr2());
						customerVO.getBillAddressVO().setAddr3(addressVO.getAddr3());
						customerVO.getBillAddressVO().setCity(addressVO.getCity());
						customerVO.getBillAddressVO().setState(addressVO.getState());
						customerVO.getBillAddressVO().setPostcode(addressVO.getPostcode());
						customerVO.getBillAddressVO().setCountryId(addressVO.getCountryId());
					}
				}
				for(CorAddressVO corAddressVO : customerVO.getCorAddressList()) {
					if(StringUtils.isNotBlank(corAddressVO.getAddrType())) {
						if(corAddressVO.getAddrType().equals(CommonConstant.BILL_ADDR)) {
							customerVO.getCorBillAddressVO().setAddr1(corAddressVO.getAddr1());
							customerVO.getCorBillAddressVO().setAddr2(corAddressVO.getAddr2());
							customerVO.getCorBillAddressVO().setAddr3(corAddressVO.getAddr3());
							customerVO.getCorBillAddressVO().setCity(corAddressVO.getCity());
							customerVO.getCorBillAddressVO().setState(corAddressVO.getState());
							customerVO.getCorBillAddressVO().setPostcode(corAddressVO.getPostcode());
							customerVO.getCorBillAddressVO().setCountryId(corAddressVO.getCountryId());
						}

						if(corAddressVO.getAddrType().equals(CommonConstant.MAIL_ADDR)) {
							customerVO.getCorMailAddressVO().setAddr1(corAddressVO.getAddr1());
							customerVO.getCorMailAddressVO().setAddr2(corAddressVO.getAddr2());
							customerVO.getCorMailAddressVO().setAddr3(corAddressVO.getAddr3());
							customerVO.getCorMailAddressVO().setCity(corAddressVO.getCity());
							customerVO.getCorMailAddressVO().setState(corAddressVO.getState());
							customerVO.getCorMailAddressVO().setPostcode(corAddressVO.getPostcode());
							customerVO.getCorMailAddressVO().setCountryId(corAddressVO.getCountryId());
						}
					}else {
						customerVO.getCorBillAddressVO().setAddr1(corAddressVO.getAddr1());
						customerVO.getCorBillAddressVO().setAddr2(corAddressVO.getAddr2());
						customerVO.getCorBillAddressVO().setAddr3(corAddressVO.getAddr3());
						customerVO.getCorBillAddressVO().setCity(corAddressVO.getCity());
						customerVO.getCorBillAddressVO().setState(corAddressVO.getState());
						customerVO.getCorBillAddressVO().setPostcode(corAddressVO.getPostcode());
						customerVO.getCorBillAddressVO().setCountryId(corAddressVO.getCountryId());
					}
				}
				calculateAge();
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleCustomerPointTier(Long idCustomer) throws BusinessException {
		try {
			Map <String, Object> params = new HashMap<>();
			params.put("idCustomer", idCustomer);
			if (CRMUtils.getCustomerPointTier(params) != null) this.customerPointTierVO = CRMUtils.getCustomerPointTier(params);
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void handleVoucherCatalog(String voucherCategoryName) throws BusinessException {
		try {
			ApiResponseVO<CRMRedeemCatalogVO> resp = CRMUtils.getRedemptionCatalog();
			setVoucherList(resp.getData().getFlatTypes());
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void handleCustomerVoucher(Long idCustomer, List<InvoiceVoucherVO> voucherList) throws BusinessException {
		try {
			System.out.println("CYY idCustomer: " + idCustomer);
			if (customerVO == null)	customerVO = customerBO.getCustomer(idCustomer);
			if (customerVO.getId() == null)	customerVO.setId(idCustomer);
			handleCustomerVoucher(customerVO);
			
			if (CollectionUtils.isNotEmpty(voucherList)) {
				for (CRMVoucherVO crmVoucherVO : customerVO.getClaimedVoucherList()) {
					crmVoucherVO.setInCart(false);
					for (InvoiceVoucherVO invoiceVoucherVO : voucherList) {
						if (crmVoucherVO.getId().equals(invoiceVoucherVO.getVoucherId())) {
							crmVoucherVO.setInCart(true);
						}
					}
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void handleCustomerVoucher(CustomerVO customerVO) throws BusinessException {
		try {
			ApiResponseVO<CRMClaimedVoucherResponseVO> responseVO = CRMUtils.getClaimedVoucher(customerVO.getId());
			if (responseVO.isSuccess()) {
				customerVO.setClaimedVoucherList(responseVO.getData().getData());
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void handleCustomerVoucherHistory(Long idCustomer) throws BusinessException {
		try {
			ApiResponseVO<CRMClaimedVoucherResponseVO> responseVO = CRMUtils.getVoucherHistory(idCustomer);
			if (responseVO.isSuccess()) {
				setVoucherList(responseVO.getData().getData());
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void onClaimVoucher(CRMVoucherVO vo) throws BusinessException {
		try {
			setCrmVoucherVO(vo);
			if (customerPointTierVO != null) {
				if (vo.getMinPoints() != null) {
					int pointsRequired = vo.getMinPoints().intValue();
					if (pointsRequired > customerPointTierVO.getTotalPointsBalance()) {
						throw new BusinessException(CommonErrConstant.ERR_CRM_INSUFFICIENT_POINT);
					}
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void claimVoucher() throws BusinessException {
		try {
			System.out.println("CustomerBean.claimVoucher()");
			ApiResponseVO<CRMClaimVoucherResponseVO> responseVO = CRMUtils.claimVoucher(customerVO.getId(), crmVoucherVO.getRedemptionCode());
		
			if (responseVO.isSuccess()) {
				handleCustomerPointTier(customerVO.getId());
				handleCustomerVoucher(customerVO);
				
				successResult();
			} else {
				throw new BusinessException(CommonErrConstant.ERR_CRM_API_FAILED, null, FunctionUtils.generateArrayFromStr(responseVO.getError().getMessage()));
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void claimRebateVoucher() throws BusinessException {
		try {
			System.out.println("CustomerBean.claimRebateVoucher()");
			ApiResponseVO<CRMClaimVoucherResponseVO> responseVO = CRMUtils.claimVoucher(customerVO.getId(), crmVoucherVO.getRedemptionCode(), targetRedemptionPoints);
		
			if (responseVO.isSuccess()) {
				handleCustomerPointTier(customerVO.getId());
				handleCustomerVoucher(customerVO);
				
				successResult();
			} else {
				throw new BusinessException(CommonErrConstant.ERR_CRM_API_FAILED, null, FunctionUtils.generateArrayFromStr(responseVO.getError().getMessage()));
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void registerNewWebLoginAccount() {
		try {
			
			if (StringUtils.isBlank(customerVO.getCrmId())) {
				customerPosting(customerVO);
				CustomerPosInfoVO posInfo = customerPosInfoBO.getCustomerPosInfo(customerVO);
				if (StringUtils.equalsIgnoreCase(posInfo.getPostingStatus(), CRMCommonConstant.CRM_INV_POSTING_SUCCESS)) {
					
				} else {
					throw new BusinessException(CommonErrConstant.ERR_CRM_ACCT_REQUIRED);
				}
			}
			
			if (customerVO == null || customerVO.getPersonVO() == null) {
				throw new BusinessException(CommonErrConstant.ERR_EMPTY_EMAIL_LIST);
			}
			if (CollectionUtils.isEmpty(customerVO.getPersonVO().getEmailList())) {
				throw new BusinessException(CommonErrConstant.ERR_EMPTY_EMAIL_LIST);
			}
			
			String email = "";
			for (PersonEmailVO emailVO : customerVO.getPersonVO().getEmailList()) {
				if (emailVO.getIsPrimary()) {
					email = emailVO.getEmail();
				}
			}
			
			if (StringUtils.isBlank(email)) {
				throw new BusinessException(CommonErrConstant.ERR_MISSING_PRIMARY_EMAIL);
			}
			
			// mobile
			if (StringUtils.isNotBlank(personContactVO.getPerMobileCountryCd()) && StringUtils.isNotBlank(personContactVO.getPerMobileNumber())) {
			}
			// office
			else if (StringUtils.isNotBlank(personContactVO.getPerOfficeCountryCd()) && StringUtils.isNotBlank(personContactVO.getPerOfficeNumber())) {
			}
			// home
			else if (StringUtils.isNotBlank(personContactVO.getPerHomeCountryCd()) && StringUtils.isNotBlank(personContactVO.getPerHomeNumber())) {
			}
			// fax
			else if (StringUtils.isNotBlank(personContactVO.getPerFaxCountryCd()) && StringUtils.isNotBlank(personContactVO.getPerFaxNumber())) {
			} else {
				throw new BusinessException(CommonErrConstant.ERR_CUST_NO_CONTACT);
			}
			
			KeycloakUserVO userVO = new KeycloakUserVO();
			userVO.setFirstName(customerVO.getPersonVO().getGivenName());
			userVO.setLastName(customerVO.getPersonVO().getLastName());
			userVO.setEmail(email);
			userVO.setUsername(email);
			userVO.setEnabled(true);
			userVO.setEmailVerified(true);
			String keycloakUserId = KeycloakUtils.addKeycloakUser(userVO, getSessionInfoBean().getCompanyVO());
			crmMembershipBO.insertCRMMembership(customerVO, personContactVO, keycloakUserId, userVO);
			
			KeycloakUtils.forgetPassword(keycloakUserId, getSessionInfoBean().getCompanyVO());
			
			onCustomerSelected(customerVO, false);
			successResult();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void onOnlineBookingCustomerSelected(Long idBooking) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			
			OnlineBookingCustomerVO obcVO = onlineBookingBO.getOnlineBookingCustomerByBookingId(idBooking);
			
			params.put("idBooking", idBooking);
			params.put("idCustomer", obcVO.getId());
			OnlineBookingPassengerVO obpVO = onlineBookingBO.getOnlineBookingPassenger(params);
			
			tmpIdBooking = idBooking;
			customerVO.getPersonVO().setSalutation(obcVO.getSalutationCd());
			customerVO.getPersonVO().setTitle(obcVO.getTitleCd());
			customerVO.getPersonVO().setGivenName(obcVO.getGivenName());
			customerVO.getPersonVO().setLastName(obcVO.getSurname());
			customerVO.getPersonVO().setNickName(obcVO.getNickName());
			customerVO.getPersonVO().setTaxIdNo(obcVO.getTaxIdNo());
			customerVO.getPersonVO().setCountryId(obcVO.getIdCountry());
			customerVO.getPersonVO().setSex(obcVO.getSexCd());
			customerVO.getPersonVO().setEmail(obcVO.getEmail());
			customerVO.getPersonVO().setEcSurname(obcVO.getEcSurname());
			customerVO.getPersonVO().setEcGivenName(obcVO.getEcGivenName());
			customerVO.getPersonVO().setEcIdCountryCd(obcVO.getEcIdCountryCd());
			if (customerVO.getPersonVO().getEcIdCountryCd() == null) {
				customerVO.getPersonVO().setEcIdCountryCd(CommonConstant.DEF_COUNTRY_ID);
			}
			customerVO.getPersonVO().setEcCountryCd(obcVO.getEcCountryCd());
			customerVO.getPersonVO().setEcContacts(obcVO.getEcContacts());
			customerVO.getPersonVO().setEcEmail(obcVO.getEcEmail());
			customerVO.getPersonVO().setEcRelationship(obcVO.getEcRelationship());
			customerVO.getPersonVO().setNric(obcVO.getNric());
			customerVO.getPersonVO().setPassportNo(obcVO.getPassportNo());

			customerVO.setPcTypeCd("P");
			customerVO.setCompanyId((long) 1);
			
			customerVO.getBillAddressVO().setCountryId(obcVO.getIdCountry());
			customerVO.getBillAddressVO().setAddrType(CommonConstant.BILL_ADDR);
			customerVO.getBillAddressVO().setAddr1(obcVO.getAddressLine1());
			customerVO.getBillAddressVO().setAddr2(obcVO.getAddressLine2());
			customerVO.getBillAddressVO().setState(obcVO.getState());
			customerVO.getBillAddressVO().setPostcode(obcVO.getPostcode());
			
			customerVO.getMailAddressVO().setCountryId(obcVO.getIdCountry());
			customerVO.getMailAddressVO().setAddrType(CommonConstant.MAIL_ADDR);
			customerVO.getMailAddressVO().setAddr1(obcVO.getAddressLine1());
			customerVO.getMailAddressVO().setAddr2(obcVO.getAddressLine2());
			customerVO.getMailAddressVO().setState(obcVO.getState());
			customerVO.getMailAddressVO().setPostcode(obcVO.getPostcode());
			
			personContactVO.setTypeCd(SalesConstant.MOBILE_TYPE_CD);
			personContactVO.setIdCountryCd(obcVO.getIdCountryCd());
			if (personContactVO.getIdCountryCd() == null) {
				personContactVO.setIdCountryCd(CommonConstant.DEF_COUNTRY_ID);
			}
			personContactVO.setCountryCd(obcVO.getCountryCd());
			personContactVO.setNumber(obcVO.getContactNo());
			List<PersonContactVO> personContactList = new ArrayList<PersonContactVO>();
			personContactList.add(personContactVO);
			customerVO.setPersonContactList(personContactList);
			
			List<IdentityVO> custIdentityList = new ArrayList<IdentityVO>();
			IdentityVO identityNRICVO = null;
			IdentityVO identityPassportVO = null;
			if (StringUtils.isNotEmpty(obcVO.getNric())) {
				identityNRICVO = new IdentityVO();
				identityNRICVO.setIdType(CommonConstant.LOOKUP_ITM_ID_NRIC);
				identityNRICVO.setIdNo(obcVO.getNric());
				
				custIdentityList.add(identityNRICVO);
				customerVO.setIdentityList(custIdentityList);
			}
			
			if (StringUtils.isNotEmpty(obcVO.getPassportNo())) {
				identityPassportVO = new IdentityVO();
				identityPassportVO.setIdType(CommonConstant.LOOKUP_ITM_ID_PASSPRT);
				identityPassportVO.setIdNo(obcVO.getPassportNo());
				
				custIdentityList.add(identityPassportVO);
				customerVO.setIdentityList(custIdentityList);
			}
			
			if (StringUtils.isNotEmpty(obcVO.getEmail())) {
				PersonEmailVO personEmailVO = new PersonEmailVO();
				personEmailVO.setEmail(obcVO.getEmail());
				personEmailVO.setIsPrimary(true);
				
				if (CollectionUtils.isEmpty(customerVO.getPersonVO().getEmailList())) {
					customerVO.getPersonVO().setEmailList(new ArrayList<PersonEmailVO>());
				}
				customerVO.getPersonVO().getEmailList().add(personEmailVO);
			}
			
			if (obpVO != null) {
				customerVO.setOnlineBookingPaxVO(obpVO);
				customerVO.getPersonVO().setDob(obpVO.getDob());
				
				List<IdentityVO> identityList = new ArrayList<IdentityVO>();
				if (StringUtils.isNotEmpty(obcVO.getPassportNo())) {
					if (identityPassportVO != null) identityList.add(identityPassportVO);
				} else if (StringUtils.isNotEmpty(obpVO.getPassportNo()) && StringUtils.isEmpty(obcVO.getPassportNo())) {
					identityVO = new IdentityVO();
					identityVO.setIdType(CommonConstant.LOOKUP_ITM_ID_PASSPRT);
					identityVO.setIdNo(obpVO.getPassportNo());
					
					identityVO.setIdentityDetailVO(new IdentityDetailVO());
					identityVO.getIdentityDetailVO().setDtExpired(obpVO.getDtExpiry());
					identityVO.getIdentityDetailVO().setIdCountry(obpVO.getIdCountry());
					identityList.add(identityVO);
					
					customerVO.getPersonVO().setPassportNo(obpVO.getPassportNo());
				}
				if (StringUtils.isNotEmpty(obcVO.getNric())) {
					if (identityNRICVO != null) identityList.add(identityNRICVO);
				} else if (StringUtils.isNotEmpty(obpVO.getIcNo()) && StringUtils.isEmpty(obcVO.getNric())) {
					identityVO = new IdentityVO();
					identityVO.setIdType(CommonConstant.LOOKUP_ITM_ID_NRIC);
					identityVO.setIdNo(obpVO.getIcNo());
					
					identityVO.setIdentityDetailVO(new IdentityDetailVO());
					identityVO.getIdentityDetailVO().setIdCountry(obpVO.getIdCountry());
					identityList.add(identityVO);
					
					customerVO.getPersonVO().setNric(obpVO.getIcNo());
				}
				customerVO.setIdentityList(identityList);
				
				List<CustomerRemarksVO> remarkList = new ArrayList<CustomerRemarksVO>();
				if (StringUtils.isNotEmpty(obpVO.getRemarks())) {
					remarksVO = new CustomerRemarksVO();
					remarksVO.setRemarks(obpVO.getRemarks());
					remarksVO.setTimestamp(obpVO.getCreatedDate());
					remarkList.add(remarksVO);
				}
				customerVO.setCustRemarksList(remarkList);
			}

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/*public void convertToCustomer() {
		try {
			trackingLogUtils.startLogs();
			
			customerBO.insertCustomer(customerVO);
			invoiceVO = invBO.getInvoice(tmpIdBooking);
			invoiceVO.setCustomerId(customerVO.getId());
			invBO.updateCustomerId(invoiceVO);
			
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("convertToCustomer");
		}
	}*/
	
	/**
	 * On Customer Tour History Selected
	 * @param event
	 */
	public void onCustTourHistSelected(CustomerVO vo, Boolean addEdit) {
		try {
			customerVO.setAddEdit(addEdit);
			custTourHistList=new ArrayList<CustTourHistVO>();
			
			// Load Visible Config
			Map<String, Object> params = new HashMap<>();
			params.put("listingType", CommonConstant.LISTING_TYPE_INV_CAT);
			List<VisibleListingConfigVO> visibleConfigList = visibleListingConfigBO.getVisibleListingConfigListGroupByCatCdListingtype(params);
			
			
			
			// Config of Role that allow to view certain list by category code
			List<String> notViewableCatCode = new ArrayList<>();
			if (CollectionUtils.isNotEmpty(visibleConfigList)) {
				boolean isVisible = false;
				for (VisibleListingConfigVO configVO : visibleConfigList) {
					isVisible = false;
					
					if (configVO.getIdRoleList() != null) {
						for (String idRole : configVO.getIdRoleList()) {
							for (UserRoleViewVO roleVO : getSessionInfo().getUserVO().getRoleList()) {
								if (roleVO.getRoleUUID().equals(idRole)) {
									isVisible = true;
									break;
								}
							}
						}
					}
					
					if (configVO.getDepartmentList() != null) {
						for (String dept : configVO.getDepartmentList()) {
							if (getSessionInfoBean().getEmployeeVO().getDepartment().equalsIgnoreCase(dept)) {
								isVisible = true;
								break;
							}
						}
					}
					
					if (!isVisible) notViewableCatCode.add(configVO.getCatCd());
				}
			}
			params.clear();
			if (CollectionUtils.isNotEmpty(notViewableCatCode)) params.put("notViewableCatCode", notViewableCatCode);
			
			custTourHistList=customerBO.getTourHistList(vo,getSessionInfoBean().getCompanyVO().getId(), params);
			if (!addEdit) {
				
				this.setCustomerVO(customerBO.getCustomerDetail(vo.getId()));
			}
			SystemNumberGenerationVO invSNGVO = new SystemNumberGenerationVO();
			invSNGVO = systemNumberGenerationBO.getSystemNumberGeneration(CommonConstant.SYS_NUM_CD_INVC, this.getSessionInfoBean().getCompanyVO().getId());
			invSNGVO.setCode(CommonConstant.SYS_NUM_CD_INVC);
			setPrefixValue(invSNGVO.getPrefixid());

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	/**
	 * On Identity selected
	 * @param event
	 */
	public void onIdentitySelected(IdentityVO vo) {
		try {
			setIdentityAdd(false);
			identityCloneVO = (IdentityVO) vo.clone();
			setIdentityVO(vo);
			
			uploadedFile = vo.getIdentityDetailVO().getScannedPath();
			fileName = vo.getIdentityDetailVO().getScannedPath();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * On Language selected
	 * @param event
	 */
	public void onLanguageSelected(PersonLangVO vo) {
		try {
			setLanguageAdd(false);
			setPersonLangVO(vo);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * On Meal selected
	 * @param event
	 */
	public void onMealSelected(PersonMealVO vo) {
		try {
			setMealAdd(false);
			setPersonMealVO(vo);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * On Contact selected
	 * @param event
	 */
	public void onContactSelected(PersonContactVO vo) {
		try {
			setContactAdd(false);
			if (StringUtils.isNotEmpty(vo.getNumber())) {
				String[] contact = vo.getNumber().split("-", 2);
				if (contact.length > 1) {
					vo.setAreaCode(contact[0]);
					vo.setpNumber(contact[1]);
				} else {
					vo.setpNumber(contact[0]);
				}
			}
			setPersonContactVO(vo);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * On Corporate Contact selected
	 * @param event
	 */
	public void onCorpContactSelected(CorContactVO vo) {
		try {
			setContactAdd(false);
			if (StringUtils.isNotEmpty(vo.getContactNo())) {
				String[] contact = vo.getContactNo().split("-", 2);
				if (contact.length > 1) {
					vo.setAreaCode(contact[0]);
					vo.setpNumber(contact[1]);
				} else {
					vo.setpNumber(contact[0]);
				}
			}
			setCorContactVO(vo);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * On Class selected
	 * @param event
	 */
	public void onClassSelected(PersonClassVO vo) {
		try {
			setClassAdd(false);
			setPersonClassVO(vo);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * On Complication selected
	 * @param event
	 */
	public void onComplicationSelected(PersonComplicationVO vo) {
		try {
			setComplicationAdd(false);
			setPersonComplicationVO(vo);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * On Remarks selected
	 * @param event
	 */
	public void onRemarksSelected(CustomerRemarksVO vo) {
		try {
			setRemarksAdd(false);
			setRemarksVO(vo);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onEmailSelected(PersonEmailVO vo) {
		try {
			setEmailAdd(false);
			setPersonEmailVO(vo);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onVoucherSelected(CRMVoucherVO vo) {
		try {
			setCrmVoucherVO(vo);
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public String getCountryCd(Long idCountry) {
		String countryCd = "";
		for (CountryVO vo : countryList) {
			if (vo.getId().equals(idCountry)) {
				countryCd = vo.getCountryCd();
			}
		}
		return countryCd;
	}
	
	public String getCountryName(Long idCountry) {
		String countryName = "";
		for (CountryVO vo : countryList) {
			if (vo.getId().equals(idCountry)) {
				countryName = vo.getCountry();
			}
		}
		return countryName;
	}
	
	public String getIdentityStr(String s) {
		if (StringUtils.isNotBlank(s)) {
			int idx = s.indexOf("|");
			if (idx == -1)	return s;
			return LookupItemUtils.getLookupItemDesc(CommonConstant.LOOKUP_CAT_CD_ID_TYPE, s.substring(0, s.indexOf("|"))) + ": " + s.substring(s.indexOf("|") + 1);
		} else {
			return s;
		}
	}
	
	public String getContactStr(String s) {
		if (StringUtils.isNotBlank(s)) {
			int idx = s.indexOf("|");
			if (idx == -1)	return s;
			return LookupItemUtils.getLookupItemDesc(CommonConstant.LOOKUP_CAT_CD_CNTC_TYPE, s.substring(0, idx)) + ": " + s.substring(idx + 1);
		} else {
			return s;
		}
	}
	
	public String getContactStrWithCountryPair(String contact, String countryCodes) {
	    if (StringUtils.isNotBlank(contact)) {
	        int idx = contact.indexOf("|");
	        if (idx == -1) return contact;
	        
	        String contactType = contact.substring(0, idx);
	        String contactNumber = contact.substring(idx + 1);
	        String countryCode = "";
	        
	        if (StringUtils.isNotBlank(countryCodes)) {
	            String[] countryArray = countryCodes.split(",");
	            for (String countryCodeItem : countryArray) {
	                if (StringUtils.isNotBlank(countryCodeItem)) {
	                    int idxCountryCode = countryCodeItem.indexOf("|");
	                    if (idxCountryCode != -1 && contactType.equals(countryCodeItem.substring(0, idxCountryCode))) {
	                        countryCode = countryCodeItem.substring(idxCountryCode + 1);
	                        break; 
	                    }
	                }
	            }
	        }
	        String phoneNumber = FunctionUtils.phoneNumber(countryCode, contactNumber);
	        
	        return LookupItemUtils.getLookupItemDesc(CommonConstant.LOOKUP_CAT_CD_CNTC_TYPE, contactType) 
	            + ": " + phoneNumber;
	    }
	    return contact;
	}
	
	/**
	 * 
	 * @author hueyshian
	 *
	 */
	class LazyCustomerDataModel extends LazyDataModel<CustomerVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<CustomerVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			List<CustomerVO> data = new ArrayList<CustomerVO>();
			try {
				trackingLogUtils.startLogs();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("idCompany", getSessionInfoBean().getCompanyVO().getId());
				params.put("classes", true);
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				int size = customerBO.getCustomerListSize(params);
				if (CommonConstant.LAZY_ROW_COUNT < size) {
					size = CommonConstant.LAZY_ROW_COUNT;
				}
				setRowCount(size);
				if (size > 0) data = customerBO.getCustomerList(params);
				
			} catch (Throwable t) {
				errorResult(t);
			} finally {
				trackingLogUtils.endLogs("LazyCustomerDataModel");
			}
			return data;
		}
		
		@Override
		public void setRowIndex(int rowIndex) {
			/*
			 * The following is in ancestor (LazyDataModel):
			 * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
			 */
			if (rowIndex == -1 || getPageSize() == 0) {
				super.setRowIndex(-1);
			} else super.setRowIndex(rowIndex % getPageSize());
		}
	}
	
	/**
	 * Handle tour departure selection
	 * @param vo
	 */
	public void handleTourDepSelect(Long idTourDep) {
		try {
			tourDepVO=new TourDepartureVO();
			tourPkgVO=new TourPackageVO();
			tourDepVO=accountCodeBO.getTourDepById(idTourDep);
			tourPkgVO=accountCodeBO.getTourPkgById(tourDepVO.getIdTourPkg());
			tourDepVO.setTourDepItemList(tourPkgBO.getTourDepItemList(tourDepVO.getId(), getSessionInfoBean().getCompanyVO().getId()));
			tourDepVO.setTourHotelList(tourPkgBO.getTourHotelList(tourDepVO.getId()));
			tourDepVO.setAirlineScheduleVO(airlineBO.getAirlineSchedule(tourDepVO));
			tourPkgBO.setMiscAdtChd(tourDepVO, tourDepVO.getAirlineScheduleVO(), null);
			tourDepVO.setTourItineryList(tourPkgBO.getTourItineryList(idTourDep));
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleInvCodeSelect(Long idInv){
		try{
			
//			sendRedirect(URL_SALES_INV + "?invId=" + idInv);
			
			// search existed invoice to determine go for invoice or pax statement
			InvoiceVO existedInvVO = invoiceBO.getInvoiceById(Long.valueOf(idInv));
			if (existedInvVO != null) {
				if (StringUtils.isNotBlank(existedInvVO.getDocTypeCd())) {
					if (existedInvVO.getDocTypeCd().equals(SalesConstant.DOC_TYPE_CD_PAX_STMT)) {
						sendRedirect(URL_SALES_PAX_STATEMENT.concat("?invId=").concat(String.valueOf(idInv)));
					} else {
						sendRedirect(URL_SALES_INV.concat("?invId=").concat(String.valueOf(idInv)));
					}
				} else {
					sendRedirect(URL_SALES_PAX_STATEMENT.concat("?invId=").concat(String.valueOf(idInv)));
				}
			} else {
				sendRedirect(URL_SALES_PAX_STATEMENT.concat("?invId=").concat(String.valueOf(idInv)));
			}
			
		}catch(Throwable t){
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void handleCopySelect() {
		try {
			if(deepCopy) {
				if(StringUtils.isNotBlank(customerVO.getBillAddressVO().getAddr1()) && customerVO.getBillAddressVO().getCountryId()!=null) {
					customerVO.getMailAddressVO().setCompanyName(customerVO.getBillAddressVO().getCompanyName());
					customerVO.getMailAddressVO().setAddr1(customerVO.getBillAddressVO().getAddr1());
					customerVO.getMailAddressVO().setAddr2(customerVO.getBillAddressVO().getAddr2());
					customerVO.getMailAddressVO().setAddr3(customerVO.getBillAddressVO().getAddr3());
					customerVO.getMailAddressVO().setCity(customerVO.getBillAddressVO().getCity());
					customerVO.getMailAddressVO().setState(customerVO.getBillAddressVO().getState());
					customerVO.getMailAddressVO().setPostcode(customerVO.getBillAddressVO().getPostcode());
					customerVO.getMailAddressVO().setCountryId(customerVO.getBillAddressVO().getCountryId());
				}
				
				if(StringUtils.isNotBlank(customerVO.getCorBillAddressVO().getAddr1()) && customerVO.getCorBillAddressVO().getCountryId()!=null) {
					customerVO.getCorMailAddressVO().setAddr1(customerVO.getCorBillAddressVO().getAddr1());
					customerVO.getCorMailAddressVO().setAddr2(customerVO.getCorBillAddressVO().getAddr2());
					customerVO.getCorMailAddressVO().setAddr3(customerVO.getCorBillAddressVO().getAddr3());
					customerVO.getCorMailAddressVO().setCity(customerVO.getCorBillAddressVO().getCity());
					customerVO.getCorMailAddressVO().setState(customerVO.getCorBillAddressVO().getState());
					customerVO.getCorMailAddressVO().setPostcode(customerVO.getCorBillAddressVO().getPostcode());
					customerVO.getCorMailAddressVO().setCountryId(customerVO.getCorBillAddressVO().getCountryId());
				}
			}else {
				if(StringUtils.isNotBlank(customerVO.getMailAddressVO().getAddr1()) && customerVO.getMailAddressVO().getCountryId()!=null) {
					customerVO.setMailAddressVO(new AddressVO());
					customerVO.getMailAddressVO().setCountryId(CommonConstant.DEF_COUNTRY_ID);
				}
				
				if(StringUtils.isNotBlank(customerVO.getCorMailAddressVO().getAddr1()) && customerVO.getCorMailAddressVO().getCountryId()!=null) {
					customerVO.setCorMailAddressVO(new CorAddressVO());
					customerVO.getCorMailAddressVO().setCountryId(CommonConstant.DEF_COUNTRY_ID);
				}
			}
		}catch(Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Get staff name
	 */
	public String getUserName(Long id) {
		try {
			for (EmployeeViewVO vo : employeeList){
				if (vo.getId().longValue() == id.longValue()) {
					return vo.getUserVO().getName();
				}
			}

		} catch (Throwable t) {
			errorResult(t);
		}

		return "";
	}
	
	public void handleNationalitySelected() {
		if (customerVO.getPersonVO().getCountryId() != 129) {
			if (StringUtils.isBlank(customerVO.getPersonVO().getTaxIdNo())) {
				customerVO.getPersonVO().setTaxIdNo(EInvoiceConstant.E_INV_GNRL_TIN_F_BUYER);
			}
			
			if (StringUtils.isBlank(customerVO.getBillAddressVO().getCity())) {
				customerVO.getBillAddressVO().setCity(EInvoiceConstant.NOT_APPLICABLE);
			}
			
			if (StringUtils.isBlank(customerVO.getBillAddressVO().getState()) && customerVO.getBillAddressVO().getCountryId() != 129) {
				customerVO.getBillAddressVO().setState(EInvoiceConstant.NOT_APPLICABLE);
			}
		}
	}
	
	public void handleBillAddressState() {
		customerVO.getBillAddressVO().setState("");
		
		if (customerVO.getBillAddressVO().getCountryId() != 129) {
			if (StringUtils.isBlank(customerVO.getPersonVO().getTaxIdNo())) {
				customerVO.getPersonVO().setTaxIdNo(EInvoiceConstant.E_INV_GNRL_TIN_F_BUYER);
			}
			
			if (StringUtils.isBlank(customerVO.getBillAddressVO().getState())) {
				customerVO.getBillAddressVO().setState(EInvoiceConstant.NOT_APPLICABLE);
			}
			
			if (StringUtils.isBlank(customerVO.getBillAddressVO().getCity())) {
				customerVO.getBillAddressVO().setCity(EInvoiceConstant.NOT_APPLICABLE);
			}
		}
	}
	
	public void handleMailAddressState() {
		customerVO.getMailAddressVO().setState("");
	}
	
	public void handleCorpBillAddressState() {
		customerVO.getCorBillAddressVO().setState("");
		
		if (customerVO.getCorBillAddressVO().getCountryId() != 129) {
			if (StringUtils.isBlank(customerVO.getCorporateVO().getTaxIdNo())) {
				customerVO.getCorporateVO().setTaxIdNo(EInvoiceConstant.E_INV_GNRL_TIN_F_BUYER);
			}
			
			if (StringUtils.isBlank(customerVO.getCorBillAddressVO().getState())) {
				customerVO.getCorBillAddressVO().setState(EInvoiceConstant.NOT_APPLICABLE);
			}
			
			if (StringUtils.isBlank(customerVO.getCorBillAddressVO().getCity())) {
				customerVO.getCorBillAddressVO().setCity(EInvoiceConstant.NOT_APPLICABLE);
			}
			
			if (StringUtils.isBlank(customerVO.getCorporateVO().getRegNo())) {
				customerVO.getCorporateVO().setRegNo(EInvoiceConstant.NOT_APPLICABLE);
			}
		}
	}
	
	public void handleCorpMailAddressState() {
		customerVO.getCorMailAddressVO().setState("");
	}
	
	public void processEmailCustomerProfileUpdate() {
		try {
			List<CustomerProfileUpdateVO> customerProfileUpdateList = new ArrayList<CustomerProfileUpdateVO>();
			
			if (StringUtils.isNotBlank(customerVO.getPersonVO().getEmail())) {
				CustomerProfileUpdateVO custProfileUpdateVO = new CustomerProfileUpdateVO();
				custProfileUpdateVO.setIdCustomer(customerVO.getId());
				custProfileUpdateVO.setUuid(UUID.randomUUID().toString());
				custProfileUpdateVO.setEmail(customerVO.getPersonVO().getEmail());
				customerProfileUpdateList.add(custProfileUpdateVO);
				
				String sendMessage = "Email sent to " + customerVO.getPersonVO().getEmail();
	            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, sendMessage, sendMessage);
	            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				
				customerProfileUpdateBO.updateCustomerProfiles(customerProfileUpdateList, getSessionInfo().getUserVO());
				
				successResult();
			} else {
				PersonVO psVO = customerVO.getPersonVO();
				String custName = (psVO.getSalutation() != null ? psVO.getSalutation().toUpperCase() : "") + 
						(psVO.getLastName() != null  ? " " + psVO.getLastName() : "") +
						(psVO.getGivenName() != null  ? " " + psVO.getGivenName() : "");
				List<String> unableSentEmailCustList = new ArrayList<String>();
				unableSentEmailCustList.add(custName);
				String unableToSendMessage = "Unable to send email to the customer " + String.join(", ", unableSentEmailCustList) + ", email can not be blank";
	            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, unableToSendMessage, unableToSendMessage);
	            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
		
	}
	
	// Get now and before 100 year (E.g 1925-2025)
	public String getYearRange() {
	     return DatesUtils.getYearRange();
	}
	
	/**********
	 * HELPER *
	 **********/
	
	/**
	 * check duplicate customer
	 * @throws BusinessException
	 */
	protected void checkDuplicateCustomer(String customerContext) throws BusinessException {
		custDetailsVO = new CustDetailsVO();
		custDetailsVO.setPersonContactVO(personContactVO);
		custDetailsVO.setPersonId(customerVO.getPersonVO().getId());
		if (customerVO.getPersonVO().getEmailList() != null  && !customerVO.getPersonVO().getEmailList().isEmpty()) {
			custDetailsVO.setEmailList(customerVO.getPersonVO().getEmailList());
		} else {
			custDetailsVO.setEmailList(null);
		}
		if ("P".equals(customerVO.getPcTypeCd())) {
			custDetailsVO.setIdentityList(customerVO.getIdentityList());
			custDetailsVO.setFirstName(customerVO.getPersonVO().getGivenName());
			custDetailsVO.setLastName(customerVO.getPersonVO().getLastName());
			
			if (nricIdVO != null && StringUtils.isNotEmpty(nricIdVO.getIdNo())) {
				custDetailsVO.setNricIdVO(nricIdVO);
			} else {
				custDetailsVO.setNricIdVO(null);
			}
			if (passportIdVO != null && StringUtils.isNotEmpty(passportIdVO.getIdNo())) {
				custDetailsVO.setPassportIdVO(passportIdVO);
			} else {
				custDetailsVO.setPassportIdVO(null);
			}
			
			custDetailsVO.setCustType(customerVO.getPcTypeCd());
			int custExisted = customerBO.isCustomerExisted(custDetailsVO, getSessionInfoBean().getCompanyVO().getId());			
			if (custExisted == 1) throw new BusinessException(CommonErrConstant.ERR_CUST_NAME_EXISTED);
			else if (custExisted == 2) throw new BusinessException(CommonErrConstant.ERR_CUST_ID_EXISTED);
		}
		boolean emailExist = customerBO.isEmailExisted(custDetailsVO, getSessionInfoBean().getCompanyVO().getId());
		if(emailExist) throw new BusinessException(CommonErrConstant.ERR_CUST_EMAIL_EXISTED);
		if (CommonConstant.CUSTOMER_PROFILE.equals(customerContext)) {
			boolean phoneExist = customerBO.isPhoneExisted(custDetailsVO, getSessionInfoBean().getCompanyVO().getId(), false);
			if(phoneExist) throw new BusinessException(CommonErrConstant.ERR_CUST_PHONE_EXISTED);
		} else {
			boolean phoneExist = customerBO.isPhoneExisted(custDetailsVO, getSessionInfoBean().getCompanyVO().getId());
			if(phoneExist) throw new BusinessException(CommonErrConstant.ERR_CUST_PHONE_EXISTED);
		}
		
	}
	
	private void checkLocalCustomerIdentity() throws BusinessException {
		if ("P".equals(customerVO.getPcTypeCd())) {
			if (customerVO.getPersonVO() != null && customerVO.getPersonVO().getCountryId() != null) {
				if (customerVO.getPersonVO().getCountryId().equals(CommonConstant.DEF_COUNTRY_ID) &&
						StringUtils.isEmpty(nricIdVO.getIdNo())) {
					throw new BusinessException(CommonErrConstant.ERR_CUST_MALAYSIAN_NIRC_REQUIRED);
				} else if (!customerVO.getPersonVO().getCountryId().equals(CommonConstant.DEF_COUNTRY_ID) &&
						StringUtils.isEmpty(passportIdVO.getIdNo())) {
					throw new BusinessException(CommonErrConstant.ERR_CUST_NON_MALAYSIAN_PSS_PRT_REQUIRED);
				}
			}
		}
	}
	
	private void checkMalaysianPostcode() throws BusinessException {
		if ("P".equals(customerVO.getPcTypeCd())) {
			if (customerVO.getBillAddressVO() != null && customerVO.getBillAddressVO().getCountryId() == 129) {
				if (StringUtils.isNotBlank(customerVO.getBillAddressVO().getPostcode())
						&& customerVO.getBillAddressVO().getPostcode().length() > 5) {
					throw new BusinessException(CommonErrConstant.ERR_CUST_MYS_POSTCODE_MAX5);
				}
			} else if (customerVO.getCorBillAddressVO() != null
					&& customerVO.getCorBillAddressVO().getCountryId() == 129) {
				if (StringUtils.isNotBlank(customerVO.getCorBillAddressVO().getPostcode())
						&& customerVO.getCorBillAddressVO().getPostcode().length() > 5) {
					throw new BusinessException(CommonErrConstant.ERR_CUST_MYS_POSTCODE_MAX5);
				}
			}
		}
	}
	
	/**
	 * Load authority
	 * @throws BusinessException
	 */
	private void loadAuthority() throws BusinessException {
		List<UserRoleViewVO> userRoleList = this.getUserInfo().getRoleList();
		
		// salesBean.invPmntAuthVO.salesManager or salesBean.invPmntAuthVO.operationsRole or salesBean.invPmntAuthVO.asstSalesManager
		invPmntAuthVO = new InvPmntAuthVO();
		if (CollectionUtils.isNotEmpty(userRoleList)) {
			for (UserRoleViewVO userRoleVO : userRoleList) {
				if (userRoleVO.getRoleCode().equalsIgnoreCase(SalesConstant.SALES_MANAGER_ROLE)) {
					invPmntAuthVO.setSalesManager(true);
				} else if (userRoleVO.getRoleCode().equalsIgnoreCase(SalesConstant.ASST_SALES_MANAGER_ROLE)) {
					invPmntAuthVO.setAsstSalesManager(true);
				} else if (StringUtils.equalsIgnoreCase(userRoleVO.getRoleCode(), SalesConstant.OPERATIONS_ROLE)) {
					invPmntAuthVO.setOperationsRoles(true);
					invPmntAuthVO.setOperationsRole(true);
				} else if (StringUtils.equalsIgnoreCase(userRoleVO.getRoleCode(), SalesConstant.OPERATIONS_ROLE) || 
						StringUtils.equalsIgnoreCase(userRoleVO.getRoleCode(), SalesConstant.OPERATIONS_MICE_ROLE) || 
						StringUtils.equalsIgnoreCase(userRoleVO.getRoleCode(), SalesConstant.OPERATIONS_MUSLIM_ROLE)) {
					invPmntAuthVO.setOperationsRoles(true);
				}
			}
		}
	}
	
	public void corpRegNoFillNA(CorporateVO corporateVO) {
		corporateVO.setRegNo(EInvoiceConstant.NOT_APPLICABLE);
		corporateVO.setTaxIdNo(EInvoiceConstant.E_INV_GNRL_TIN_F_BUYER);
	}

	public void customerPosting(CustomerVO customerVO) throws BusinessException { 
		trackingLogUtils.startLogs();
		if (CRMProperties.isCustomerPosting()) { 
			CustomerPosInfoVO resultVO = null;
			String apiMessage = null;
			try {
//				if (StringUtils.isNotBlank(latestCustomerVO.getCrmId())) { 
//					CustomerPosInfoVO posInfo = customerPosInfoBO.getCustomerPosInfo(customerVO); 
//					resultVO = CRMUtils.updateCustomerData(posInfo);
//					apiMessage = resultVO.getRemarks();
//				} else { 
//					customerPosInfoBO.insertCustomerPosInfo(customerVO); 
//					CustomerPosInfoVO posInfo = customerPosInfoBO.getCustomerPosInfo(customerVO);
//					resultVO = CRMUtils.postingCustomerData(posInfo); 
//					apiMessage = resultVO.getRemarks();
//				}
				if (StringUtils.isBlank(customerVO.getCrmId())) {
				    customerPosInfoBO.insertCustomerPosInfo(customerVO);
				}
				CustomerPosInfoVO posInfo = customerPosInfoBO.getCustomerPosInfo(customerVO);
				resultVO = CRMUtils.postingCustomerData(posInfo);
				apiMessage = resultVO.getRemarks();
				if (resultVO != null && StringUtils.isBlank(resultVO.getCrmId())) { 
//					CustomerPosInfoVO posInfo = customerPosInfoBO.getCustomerPosInfo(customerVO);
					CustomerVO customer = customerBO.getCustomerDetail(resultVO.getIdCustomer());
					if (customer != null && StringUtils.isNotBlank(customer.getCrmId())) {
						resultVO.setCrmId(customer.getCrmId());
						resultVO.setPostingStatus(CRMCommonConstant.CRM_INV_POSTING_SUCCESS);
					}
				}
				if (resultVO != null) {
					List<CustomerPosInfoVO> updList = new ArrayList<>(); 
					updList.add(resultVO); 
					customerPosInfoBO.updateCustomerPosInfoStatus(updList); 
					if (StringUtils.isNotBlank(resultVO.getCrmId())) customerBO.updateCrmId(resultVO.getIdCustomer(), resultVO.getCrmId()); 
				}
				if (CRMCommonConstant.CRM_INV_POSTING_FAILED.equals(resultVO.getPostingStatus())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, resultVO.getRemarks(), ""));
				}
			} catch (Exception e) {
				BusinessException be = new BusinessException(CommonErrConstant.ERR_CRM_API_FAILED, null, new String[]{resultVO.getRemarks()});
				be.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, resultVO.getRemarks(), ""));
			} finally {
				trackingLogUtils.endLogs("customerPosting");
			}
		} 
	}
	
	public void addCorContactIfNotEmpty(String contactType, String contactNo, String countryCd, Long idCountry, String attention) {
		if (StringUtils.isNotEmpty(contactNo)) {
			CorContactVO vo = new CorContactVO(contactType, contactNo, countryCd, idCountry, attention);
			customerVO.getCorContactList().add(vo);
		}
	}
	
	public void addPersonContactIfNotEmpty(String typeCd, String number, String countryCd, Long idCountry, String attention) {
		if (StringUtils.isNotEmpty(number)) {
			PersonContactVO vo = new PersonContactVO(typeCd, number, countryCd, idCountry, attention);
			customerVO.getPersonContactList().add(vo);
		}
	}
	
	public void handleShowOnlineCustProfileUpdList(CustomerVO customerVO) {
		try {
			if (CollectionUtils.isEmpty(customerVO.getOnlineCustProfileUpdList())) {
				throw new BusinessException(CommonErrConstant.ERR_CRM_UPD_REQ_EMPTY);
			}
			
			if (customerVO.getOnlineCustProfileUpdList().size() > 1) {
				throw new BusinessException(CommonErrConstant.ERR_CRM_MULP_UPD_REQ);
			}
			
			onlineCustomerProfileUpdateVO = customerVO.getOnlineCustProfileUpdList().get(0);
			onlineCustomerProfileUpdateVO = customerBO.getOnlineCustomerProfileUpdateDetails(onlineCustomerProfileUpdateVO.getId());
			
			for (MalaysiaStateVO vo : malaysiaStateList) {
				if (StringUtils.equalsIgnoreCase(vo.getCode(), onlineCustomerProfileUpdateVO.getBillAddrState())) {
					onlineCustomerProfileUpdateVO.setBillAddrStateName(vo.getName());
				}
				if (StringUtils.equalsIgnoreCase(vo.getCode(), onlineCustomerProfileUpdateVO.getMailAddrState())) {
					onlineCustomerProfileUpdateVO.setMailAddrStateName(vo.getName());
				}
			}
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void applyOnlineCustProfUpdReq(OnlineCustomerProfileUpdateVO reqVO, CustomerVO custVO) {
		try {
			customerBO.applyOnlineCustProfUpdReq(reqVO, custVO.getId());
			
			// for audit trial history purpose
			customerBO.insertCustomerHistory(custVO.getId(), CommonConstant.ACTION_CD_UPD, "[CUSTOMER] Apply online customer profile update");
			
			onCustomerSelected(custVO, false);
			successResult();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void rejectOnlineCustProfUpdReq(OnlineCustomerProfileUpdateVO reqVO, CustomerVO custVO) {
		try {
			customerBO.rejectOnlineCustProfUpdReq(reqVO, custVO.getId());
			
			onCustomerSelected(custVO, false);
			successResult();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	/*******************
	 * GETTER & SETTER *
	 *******************/
	
	/**
	 * @return the customerVO
	 */
	public CustomerVO getCustomerVO() {
		return customerVO;
	}

	/**
	 * @param customerVO the customerVO to set
	 */
	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}

	/**
	 * @return the customerList
	 */
	public List<CustomerVO> getCustomerList() {
		return customerList;
	}

	/**
	 * @param customerList the customerList to set
	 */
	public void setCustomerList(List<CustomerVO> customerList) {
		this.customerList = customerList;
	}

	/**
	 * @return the identityVO
	 */
	public IdentityVO getIdentityVO() {
		return identityVO;
	}

	/**
	 * @param identityVO the identityVO to set
	 */
	public void setIdentityVO(IdentityVO identityVO) {
		this.identityVO = identityVO;
	}

	/**
	 * @return the identityAdd
	 */
	public boolean isIdentityAdd() {
		return identityAdd;
	}

	/**
	 * @param identityAdd the identityAdd to set
	 */
	public void setIdentityAdd(boolean identityAdd) {
		this.identityAdd = identityAdd;
		if (identityVO.getIdentityDetailVO().getIdCountry() == null) {
			identityVO.getIdentityDetailVO().setIdCountry(CommonConstant.DEF_COUNTRY_ID);
		}
	}

	/**
	 * @return the personLangVO
	 */
	public PersonLangVO getPersonLangVO() {
		return personLangVO;
	}

	/**
	 * @param personLangVO the personLangVO to set
	 */
	public void setPersonLangVO(PersonLangVO personLangVO) {
		this.personLangVO = personLangVO;
	}

	/**
	 * @return the languageAdd
	 */
	public boolean isLanguageAdd() {
		return languageAdd;
	}

	/**
	 * @param languageAdd the languageAdd to set
	 */
	public void setLanguageAdd(boolean languageAdd) {
		this.languageAdd = languageAdd;
	}

	/**
	 * @return the personMealVO
	 */
	public PersonMealVO getPersonMealVO() {
		return personMealVO;
	}

	/**
	 * @param personMealVO the personMealVO to set
	 */
	public void setPersonMealVO(PersonMealVO personMealVO) {
		this.personMealVO = personMealVO;
	}

	/**
	 * @return the mealAdd
	 */
	public boolean isMealAdd() {
		return mealAdd;
	}

	/**
	 * @param mealAdd the mealAdd to set
	 */
	public void setMealAdd(boolean mealAdd) {
		this.mealAdd = mealAdd;
	}

	/**
	 * @return the personContactVO
	 */
	public PersonContactVO getPersonContactVO() {
		return personContactVO;
	}

	/**
	 * @param personContactVO the personContactVO to set
	 */
	public void setPersonContactVO(PersonContactVO personContactVO) {
		this.personContactVO = personContactVO;
	}

	/**
	 * @return the contactAdd
	 */
	public boolean isContactAdd() {
		return contactAdd;
	}

	/**
	 * @param contactAdd the contactAdd to set
	 */
	public void setContactAdd(boolean contactAdd) {
		this.contactAdd = contactAdd;
	}

	/**
	 * @return the personClassVO
	 */
	public PersonClassVO getPersonClassVO() {
		return personClassVO;
	}

	/**
	 * @param personClassVO the personClassVO to set
	 */
	public void setPersonClassVO(PersonClassVO personClassVO) {
		this.personClassVO = personClassVO;
	}

	/**
	 * @return the personComplicationVO
	 */
	public PersonComplicationVO getPersonComplicationVO() {
		return personComplicationVO;
	}

	/**
	 * @param personComplicationVO the personComplicationVO to set
	 */
	public void setPersonComplicationVO(PersonComplicationVO personComplicationVO) {
		this.personComplicationVO = personComplicationVO;
	}

	/**
	 * @return the classAdd
	 */
	public boolean isClassAdd() {
		return classAdd;
	}

	/**
	 * @param classAdd the classAdd to set
	 */
	public void setClassAdd(boolean classAdd) {
		this.classAdd = classAdd;
	}

	/**
	 * @return the complicationAdd
	 */
	public boolean isComplicationAdd() {
		return complicationAdd;
	}

	/**
	 * @param complicationAdd the complicationAdd to set
	 */
	public void setComplicationAdd(boolean complicationAdd) {
		this.complicationAdd = complicationAdd;
	}

	/**
	 * @return the countryList
	 */
	public List<CountryVO> getCountryList() {
		return countryList;
	}

	/**
	 * @param countryList the countryList to set
	 */
	public void setCountryList(List<CountryVO> countryList) {
		this.countryList = countryList;
	}

	/**
	 * @return the remarksVO
	 */
	public CustomerRemarksVO getRemarksVO() {
		return remarksVO;
	}

	/**
	 * @param remarksVO the remarksVO to set
	 */
	public void setRemarksVO(CustomerRemarksVO remarksVO) {
		this.remarksVO = remarksVO;
	}

	/**
	 * @return the remarksAdd
	 */
	public boolean isRemarksAdd() {
		return remarksAdd;
	}

	/**
	 * @param remarksAdd the remarksAdd to set
	 */
	public void setRemarksAdd(boolean remarksAdd) {
		this.remarksAdd = remarksAdd;
	}

	/**
	 * @return the personAttachmentVO
	 */
	public PersonAttachmentVO getPersonAttachmentVO() {
		return personAttachmentVO;
	}

	/**
	 * @param personAttachmentVO the personAttachmentVO to set
	 */
	public void setPersonAttachmentVO(PersonAttachmentVO personAttachmentVO) {
		this.personAttachmentVO = personAttachmentVO;
	}

	/**
	 * @return the attachmentAdd
	 */
	public boolean isAttachmentAdd() {
		return attachmentAdd;
	}

	/**
	 * @param attachmentAdd the attachmentAdd to set
	 */
	public void setAttachmentAdd(boolean attachmentAdd) {
		this.attachmentAdd = attachmentAdd;
	}

	/**
	 * @return the corContactVO
	 */
	public CorContactVO getCorContactVO() {
		return corContactVO;
	}

	/**
	 * @param corContactVO the corContactVO to set
	 */
	public void setCorContactVO(CorContactVO corContactVO) {
		this.corContactVO = corContactVO;
	}
	
	/**
	 * @return the destinationPassportPath
	 */
	public String getDestinationPassportPath() {
		return destinationPassportPath;
	}

	/**
	 * @return the destinationVisaPath
	 */
	public String getDestinationVisaPath() {
		return destinationVisaPath;
	}

	public String getDestinationAttachmentPath() {
		return destinationAttachmentPath;
	}

	/**
	 * @return the strOnLoad
	 */
	public String getStrOnLoad() {
		return strOnLoad;
	}

	/**
	 * @param strOnLoad the strOnLoad to set
	 */
	public void setStrOnLoad(String strOnLoad) {
		this.strOnLoad = strOnLoad;
	}

	/**
	 * @return the uploadedFile
	 */
	public String getUploadedFile() {
		return uploadedFile;
	}

	/**
	 * @param uploadedFile the uploadedFile to set
	 */
	public void setUploadedFile(String uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	/**
	 * @return the custDetailsVO
	 */
	public CustDetailsVO getCustDetailsVO() {
		return custDetailsVO;
	}

	/**
	 * @param custDetailsVO the custDetailsVO to set
	 */
	public void setCustDetailsVO(CustDetailsVO custDetailsVO) {
		this.custDetailsVO = custDetailsVO;
	}

	/**
	 * @return the lazyDataModel
	 */
	public LazyDataModel<CustomerVO> getLazyCustDataModel() {
		return lazyCustDataModel;
	}

	/**
	 * @param lazyDataModel the lazyDataModel to set
	 */
	public void setLazyCustDataModel(LazyDataModel<CustomerVO> lazyCustDataModel) {
		this.lazyCustDataModel = lazyCustDataModel;
	}
	
	/**
	 * @return the custTourHistList
	 */
	public List<CustTourHistVO> getCustTourHistList() {
		return custTourHistList;
	}

	/**
	 * @param custTourHistList the custTourHistList to set
	 */
	public void setCustTourHistList(List<CustTourHistVO> custTourHistList) {
		this.custTourHistList = custTourHistList;
	}
	
	/**
	 * @return the custTourHistVO
	 */
	public CustTourHistVO getCustTourHistVO() {
		return custTourHistVO;
	}

	/**
	 * @param custTourHistVO the custTourHistVO to set
	 */
	public void setCustTourHistVO(CustTourHistVO custTourHistVO) {
		this.custTourHistVO = custTourHistVO;
	}
	
	/**
	 * @return the tourPkgVO
	 */
	public TourPackageVO getTourPkgVO() {
		return tourPkgVO;
	}

	/**
	 * @param tourPkgVO the tourPkgVO to set
	 */
	public void setTourPkgVO(TourPackageVO tourPkgVO) {
		this.tourPkgVO = tourPkgVO;
	}

	/**
	 * @return the tourDepVO
	 */
	public TourDepartureVO getTourDepVO() {
		return tourDepVO;
	}

	/**
	 * @param tourDepVO the tourDepVO to set
	 */
	public void setTourDepVO(TourDepartureVO tourDepVO) {
		this.tourDepVO = tourDepVO;
	}
	
	/**
	 * @return the employeeList
	 */
	public List<EmployeeViewVO> getEmployeeList() {
		return employeeList;
	}

	/**
	 * @param employeeList the employeeList to set
	 */
	public void setEmployeeList(List<EmployeeViewVO> employeeList) {
		this.employeeList = employeeList;
	}
	
	/**
	 * @return the acctViewList
	 */
	public List<AcctViewVO> getAcctViewList() {
		return acctViewList;
	}

	/**
	 * @param acctViewList the acctViewList to set
	 */
	public void setAcctViewList(List<AcctViewVO> acctViewList) {
		this.acctViewList = acctViewList;
	}

	/**
	 * @return the invEOItemViewList
	 */
	public List<InvoiceAndExchangeOrderViewVO> getInvEOItemViewList() {
		return invEOItemViewList;
	}

	/**
	 * @param invEOItemViewList the invEOItemViewList to set
	 */
	public void setInvEOItemViewList(
			List<InvoiceAndExchangeOrderViewVO> invEOItemViewList) {
		this.invEOItemViewList = invEOItemViewList;
	}

	/**
	 * @return the airlineList
	 */
	public List<AirlineVO> getAirlineList() {
		return airlineList;
	}

	/**
	 * @param airlineList the airlineList to set
	 */
	public void setAirlineList(List<AirlineVO> airlineList) {
		this.airlineList = airlineList;
	}
	

	/**
	 * @return the invoiceVO
	 */
	public InvoiceVO getInvoiceVO() {
		return invoiceVO;
	}

	/**
	 * @param invoiceVO the invoiceVO to set
	 */
	public void setInvoiceVO(InvoiceVO invoiceVO) {
		this.invoiceVO = invoiceVO;
	}

	/**
	 * @return the acctVO
	 */
	public AcctVO getAcctVO() {
		return acctVO;
	}

	/**
	 * @param acctVO the acctVO to set
	 */
	public void setAcctVO(AcctVO acctVO) {
		this.acctVO = acctVO;
	}

	/**
	 * @return the prefixValue
	 */
	public String getPrefixValue() {
		return prefixValue;
	}

	/**
	 * @param prefixValue the prefixValue to set
	 */
	public void setPrefixValue(String prefixValue) {
		this.prefixValue = prefixValue;
	}

	public boolean getDeepCopy() {
		return deepCopy;
	}

	public void setDeepCopy(boolean deepCopy) {
		this.deepCopy = deepCopy;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public boolean getAllowUpdateCusStatus() {
		return allowUpdateCusStatus;
	}

	public void setAllowUpdateCusStatus(boolean allowUpdateCusStatus) {
		this.allowUpdateCusStatus = allowUpdateCusStatus;
	}

	public List<MalaysiaStateVO> getMalaysiaStateList() {
		return malaysiaStateList;
	}

	public void setMalaysiaStateList(List<MalaysiaStateVO> malaysiaStateList) {
		this.malaysiaStateList = malaysiaStateList;
	}

	public boolean isEmailAdd() {
		return emailAdd;
	}

	public void setEmailAdd(boolean emailAdd) {
		this.emailAdd = emailAdd;
	}

	public PersonEmailVO getPersonEmailVO() {
		return personEmailVO;
	}

	public void setPersonEmailVO(PersonEmailVO personEmailVO) {
		this.personEmailVO = personEmailVO;
	}
	
	public InvPmntAuthVO getInvPmntAuthVO() {
		return invPmntAuthVO;
	}

	public IdentityVO getNricIdVO() {
		return nricIdVO;
	}

	public void setNricIdVO(IdentityVO nricIdVO) {
		this.nricIdVO = nricIdVO;
	}

	public IdentityVO getPassportIdVO() {
		return passportIdVO;
	}

	public void setPassportIdVO(IdentityVO passportIdVO) {
		this.passportIdVO = passportIdVO;
	}

	public List<CruiseVO> getCruiseList() {
		return cruiseList;
	}

	public void setCruiseList(List<CruiseVO> cruiseList) {
		this.cruiseList = cruiseList;
	}
	
	public Boolean getIsB2BAgent() {
		return isB2BAgent;
	}

	public void setIsB2BAgent(Boolean isB2BAgent) {
		this.isB2BAgent = isB2BAgent;
	}
	
	public CustomerPointTierVO getCustomerPointTierVO() {
	    return customerPointTierVO;
	}

	public void setCustomerPointTierVO(CustomerPointTierVO customerPointTierVO) {
	    this.customerPointTierVO = customerPointTierVO;
	}
	
	public List<CRMVoucherVO> getVoucherList() {
		return voucherList;
	}
	
	public void setVoucherList(List<CRMVoucherVO> voucherList) {
		this.voucherList = voucherList;
	}
	
	public CRMVoucherVO getCrmVoucherVO() {
		return crmVoucherVO;
	}
	
	public void setCrmVoucherVO(CRMVoucherVO crmVoucherVO) {
		this.crmVoucherVO = crmVoucherVO;
	}
	
	public String getPointExpiry() {
	    if (customerPointTierVO == null || customerPointTierVO.getPointsByExpiry() == null) {
	        return "";
	    }

	    StringBuilder sb = new StringBuilder();

	    for (PointExpiryVO item : customerPointTierVO.getPointsByExpiry()) {
	    	sb.append("Points: ")
	          .append(item.getPoints())
	          .append(", Expiry Date: ")
	          .append(item.getExpiryDate())
	          .append("\n");
	    }

	    return sb.toString();
	}

	public void handleUploadCrmIdCsv(FileUploadEvent event) {
		try {
			UploadedFile file = event.getFile();
			if (file != null) {
				this.crmIdCsvFileBytes = file.getContents();
				if (this.crmIdCsvFileBytes == null || this.crmIdCsvFileBytes.length == 0) {
					this.crmIdCsvFileBytes = org.apache.commons.io.IOUtils.toByteArray(file.getInputstream());
				}
				this.crmIdCsvFileName = file.getFileName();
				//System.out.println("[DEBUG] handleUploadCrmIdCsv - File uploaded: " + crmIdCsvFileName + " (" + (crmIdCsvFileBytes != null ? crmIdCsvFileBytes.length : 0) + " bytes)");
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "File Uploaded", crmIdCsvFileName + " is uploaded. Click Update to apply changes.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Throwable t) {
			System.out.println("Error in handleUploadCrmIdCsv: " + t.getMessage());
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void resetCrmIdCsvForm() {
		this.uploadedCrmIdCsvFile = null;
		this.crmIdCsvFileBytes = null;
		this.crmIdCsvFileName = null;
	}

	public void onUpdateCrmIdFromCsv() {
		try {
			if (crmIdCsvFileBytes != null && crmIdCsvFileBytes.length > 0) {
				//System.out.println("[DEBUG] onUpdateCrmIdFromCsv processing file: " + crmIdCsvFileName + " (" + crmIdCsvFileBytes.length + " bytes)");
				BufferedReader reader = new BufferedReader(new InputStreamReader(new java.io.ByteArrayInputStream(crmIdCsvFileBytes), "UTF-8"));
				String line;
				int count = 0;
				int lineNumber = 0;
				int crmIdColIdx = -1;
				int extRefIdColIdx = -1;

				while ((line = reader.readLine()) != null) {
					lineNumber++;
					if (StringUtils.isBlank(line)) {
						continue;
					}

					String[] tokens = line.split(",", -1);

					if (lineNumber == 1) {
						for (int i = 0; i < tokens.length; i++) {
							String header = tokens[i].trim().replaceAll("^\"|\"$", "").replaceAll("\uFEFF", "");
							if ("Id".equalsIgnoreCase(header)) {
								crmIdColIdx = i;
							} else if ("ExternalReferenceId".equalsIgnoreCase(header)) {
								extRefIdColIdx = i;
							}
						}
						//System.out.println("[DEBUG] CSV Header row parsed: crmIdColIdx=" + crmIdColIdx + ", extRefIdColIdx=" + extRefIdColIdx);
						if (crmIdColIdx == -1 || extRefIdColIdx == -1) {
							if (tokens.length >= 2) {
								crmIdColIdx = 0;
								extRefIdColIdx = 1;
							}
						}
						continue;
					}

					if (crmIdColIdx >= 0 && extRefIdColIdx >= 0 && tokens.length > Math.max(crmIdColIdx, extRefIdColIdx)) {
						String crmId = tokens[crmIdColIdx].trim().replaceAll("^\"|\"$", "");
						String extRefIdStr = tokens[extRefIdColIdx].trim().replaceAll("^\"|\"$", "");

						if (StringUtils.isNotBlank(crmId) && StringUtils.isNotBlank(extRefIdStr)) {
							try {
								Long customerId = Long.parseLong(extRefIdStr);
								// System.out.println("[DEBUG] Updating customer.id=" + customerId + " with crmId=" + crmId);
								customerBO.updateCrmIdCSV(customerId, crmId);
								count++;
							} catch (NumberFormatException nfe) {
								System.out.println("[DEBUG] Skipping row " + lineNumber + ": Invalid ExternalReferenceId " + extRefIdStr);
							}
						}
					}
				}
				reader.close();

				// System.out.println("[DEBUG] onUpdateCrmIdFromCsv finished. Total customers updated: " + count);
				this.crmIdCsvFileBytes = null;
				this.crmIdCsvFileName = null;

				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success! ", count + " customer crmId(s) updated successfully.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				System.out.println("[DEBUG] onUpdateCrmIdFromCsv: crmIdCsvFileBytes is null or empty");
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No CSV file uploaded.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Throwable t) {
			System.out.println("[DEBUG] Error in onUpdateCrmIdFromCsv: " + t.getMessage());
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public boolean isHideCRM() {
	    return CRMProperties.isHide();
	}
	
	public CRMMembershipVO getCrmMembershipVO() {
	    return crmMembershipVO;
	}

	public void setCrmMembershipVO(CRMMembershipVO crmMembershipVO) {
		this.crmMembershipVO = crmMembershipVO;
	}
	public int getTargetRedemptionPoints() {
		return targetRedemptionPoints;
	}
	public void setTargetRedemptionPoints(int targetRedemptionPoints) {
		this.targetRedemptionPoints = targetRedemptionPoints;
	}

	public UploadedFile getUploadedCrmIdCsvFile() {
		return uploadedCrmIdCsvFile;
	}

	public void setUploadedCrmIdCsvFile(UploadedFile uploadedCrmIdCsvFile) {
		this.uploadedCrmIdCsvFile = uploadedCrmIdCsvFile;
	}

	public String getCrmIdCsvFileName() {
		return crmIdCsvFileName;
	}

	public void setCrmIdCsvFileName(String crmIdCsvFileName) {
		this.crmIdCsvFileName = crmIdCsvFileName;
	}

	public byte[] getCrmIdCsvFileBytes() {
		return crmIdCsvFileBytes;
	}

	public void setCrmIdCsvFileBytes(byte[] crmIdCsvFileBytes) {
		this.crmIdCsvFileBytes = crmIdCsvFileBytes;
	}
	
	public OnlineCustomerProfileUpdateVO getOnlineCustomerProfileUpdateVO() {
		return onlineCustomerProfileUpdateVO;
	}
	public void setOnlineCustomerProfileUpdateVO(OnlineCustomerProfileUpdateVO onlineCustomerProfileUpdateVO) {
		this.onlineCustomerProfileUpdateVO = onlineCustomerProfileUpdateVO;
	}
	public boolean isCustomerPosting() {
	    return CRMProperties.isCustomerPosting();
	}
}
