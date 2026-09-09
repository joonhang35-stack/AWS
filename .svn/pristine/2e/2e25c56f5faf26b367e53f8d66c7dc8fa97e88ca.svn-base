package com.bcs.zsg.bank.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.bo.ChartOfAcctBO;
import com.bcs.zsg.acct.helper.AccountHelper;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.bank.bo.BankAcctBO;
import com.bcs.zsg.bank.bo.DepositBO;
import com.bcs.zsg.bank.bo.PaymentBO;
import com.bcs.zsg.bank.vo.AcctTransViewTempVO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.bank.vo.CashBookViewVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.security.bo.SecurityBO;
import com.bcs.zsg.component.security.vo.UserRoleViewVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.db.bterp.vo.AmountCalcViewVO;
import com.bcs.zsg.db.bterp.vo.gst.TaxCodeVO;
import com.bcs.zsg.gst.bo.GSTBO;
import com.bcs.zsg.gst.helper.GSTType;
import com.bcs.zsg.maintenance.bo.ListingTableViewBO;
import com.bcs.zsg.maintenance.helper.ConstantListingTableView;
import com.bcs.zsg.maintenance.helper.MaintConstant;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.ListingTableViewColumnsVO;
import com.bcs.zsg.maintenance.vo.ListingTableViewVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.product.vo.TourCatVO;
import com.bcs.zsg.sales.bo.CustomerBO;
import com.bcs.zsg.sales.bo.InvoiceBO;
import com.bcs.zsg.sales.helper.SalesConstant;
import com.bcs.zsg.sales.vo.CustDetailsVO;
import com.bcs.zsg.sales.vo.CustomerVO;
import com.bcs.zsg.sales.vo.InvPmntAuthVO;
import com.bcs.zsg.sales.vo.InvoicePaymentVO;
import com.bcs.zsg.sales.vo.InvoiceVO;


public class DepositBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient PaymentBO paymentBO;
	@Autowired
	private transient BankAcctBO bankAcctBO;
	@Autowired
	private transient ChartOfAcctBO chartOfAcctBO;
	@Autowired
	private transient DepositBO depositBO;
	@Autowired
	protected transient InvoiceBO invoiceBO;
	@Autowired
	private transient SecurityBO securityBO;
	@Autowired
	private transient CustomerBO customerBO;
	@Autowired
	private transient GSTBO gstBO;
	@Autowired
	private transient ListingTableViewBO listingTableViewBO;
	
	protected boolean custAdd;

	private AcctTransVO acctTransVO;
	private  CashBookVO cashBookVO;
	private CashBookViewVO cashBookViewVO;
	private BankAcctVO bankAcctVO;
	private  SystemNumberGenerationVO sysNumGenVO;
	private SystemNumberGenerationVO sysGenCodeVO;
	private CompanyVO companyVO;
	private AcctViewVO acctViewVO;
	private TourCatVO tourCatVO;
	private AcctTransViewVO acctTransViewVO;
	private AcctTransViewTempVO acctTransViewTempVO;
	private AcctVO acctIdVO;
	private AcctVO acctVO;
	private AcctTransViewVO acctTransCashBookVO;
	private LookupItemVO lookupItemBT;
	private LookupItemVO lookupItemCBT;
	private BankAcctVO bankAcctSearchVO;
	private CustDetailsVO custDetailsVO;
	private CustDetailsVO custDetailsNameVO;
	private InvoiceVO invoiceVO;
	private InvPmntAuthVO invPmntAuthVO;
	private ListingTableViewVO listingTableViewVO;

	private List<AcctTransVO> acctTransList;
	private List<AcctTransViewVO> acctTransListView;
	private List<AcctTransViewVO> acctTransSysNoList;
	private List<SystemNumberGenerationVO> sysNumGenList;
	private List<BankAcctViewVO> bankAcctViewList;
	private List<AcctTransViewVO> addTransList;
	private List<AcctTransViewVO> updTransist;
	private List<AcctTransViewVO> delTransList;
	private List<AcctTransVO> updCompTranslist;
	private List<AcctViewVO> acctViewList;
	private List<AcctVO> acctCodeList;
	private List<CashBookVO> cashBookDepositList;
	private List<AcctVO> acctAutoCompleteList;
	private List<CustDetailsVO> passangerDetailsList;
	private List<InvoicePaymentVO> invPaymentList;
	private Map<Integer, Boolean> listingTableViewMap;
	
	private Double preveditBal;
	private String searchType;
	private String searchValueType;
	private String searchValue;
	private Double prevBal;
	private Double totalBal=0.00;
	private Double balance;
	
	private Double editAmount;
	
	private AddUpdDelVO invPmntAUDList;
	/* Lazy data model */
	private LazyDataModel<CashBookVO> ldmDeposit;
	private List<CashBookVO> listFullExportDepositVO;
	private Map<String, Object> depositListFilterParam;
	private TrackingLogUtils trackingLogUtils;
	
	private boolean isAddAcctTrans, isGSTPeriod;
	private String prefixValue;
	
	private AmountCalcViewVO amtCalcViewVO;
	private List<TaxCodeVO> inputTaxCodeVOList;
	private List<TaxCodeVO> outputTaxCodeVOList;
	
	private final String destinationPaymentAttachmentPath = LookupItemUtils.getGlobalConfigValue(MaintConstant.GLOBAL_CD_GLOBAL, MaintConstant.GLOBAL_CD_PATH_INV_PMNT_ATTACH);
	
	@Override
	public void resetForm() {
		this.resetFilteredObjList();
		acctTransVO = new AcctTransVO();
		cashBookVO= new CashBookVO();
		cashBookViewVO=new CashBookViewVO();
		companyVO=new CompanyVO();
		acctViewVO=new AcctViewVO();
		bankAcctVO=new BankAcctVO();
		tourCatVO=new TourCatVO();
		acctViewVO=new AcctViewVO();
		acctTransViewTempVO=new AcctTransViewTempVO(); 
		custDetailsVO=new CustDetailsVO();
		custDetailsNameVO=new CustDetailsVO();
		invoiceVO=new InvoiceVO();
		resetAcctTransForm();
		resetAcctTransViewForm();
		resetAcctTransList();
		resetPassangerListForm();
		totalBal=0.00;
		cashBookVO.setInvPymtList(new ArrayList<InvoicePaymentVO>());
		invPmntAUDList = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
		acctTransListView=new ArrayList<AcctTransViewVO>();
		
		amtCalcViewVO = new AmountCalcViewVO();
		amtCalcViewVO.setIdTaxAcct(getSessionInfoBean().getCompanyVO().getIdAcctGST());
		amtCalcViewVO.setIdTaxNonClaimableAcct(getSessionInfoBean().getCompanyVO().getIdAcctNonClaimableGST());
		
		setOriObj(null);
	}

	public void resetAcctTransForm(){
		acctTransVO = new AcctTransVO();
	}
	
	public void resetAcctTransViewForm(){
		acctTransViewVO = new AcctTransViewVO();
		for (AcctViewVO vo : acctViewList) {
			if (vo.getId().equals(getSessionInfoBean().getCompanyVO().getIdAcctSales())) {
				acctTransViewVO.setAcctId(vo.getId());
				acctTransViewVO.setCode(vo.getCode());
				acctTransViewVO.setDesc(vo.getDesc());
				acctTransViewVO.setAcctViewVO(vo);
				acctTransViewVO.setTaxCode(vo.getTaxCode());
				acctTransViewVO.setTaxRate(vo.getTaxRate());
				break;
			}
		}
	}

	public void resetAcctTransList() {
		addTransList= new ArrayList<AcctTransViewVO>();
		updTransist = new ArrayList<AcctTransViewVO>();
		delTransList = new ArrayList<AcctTransViewVO>();
	}

	public void resetCompanyAcctTransList() {
		updCompTranslist= new ArrayList<AcctTransVO>();
	}

	public void resetCashBookForm() throws BusinessException {
		cashBookVO = new CashBookVO();
	}

	public void resetSysNumGen() throws BusinessException{
		sysNumGenVO= new SystemNumberGenerationVO();
	}

	/**
	 * Reset passanger list form
	 * @throws BusinessException
	 */
	public void resetPassangerListForm() {
		setCustAdd(true);
		setSearchType("");
		setSearchValueType("");
		setSearchValue("");
		setPassangerDetailsList(new ArrayList<CustDetailsVO>());
	}
	
	public void init() throws BusinessException {
		try {
			trackingLogUtils = new TrackingLogUtils(this.getClass());
			
			initSearchParam();
			searchParamVO.setFromDate(new Date());
			searchParamVO.setToDate(searchParamVO.getFromDate());
			acctTransVO = new AcctTransVO();
			cashBookVO = new CashBookVO();
			acctViewVO=new AcctViewVO();
			acctViewList = chartOfAcctBO.getAcctViewList(getSessionInfoBean().getCompanyVO().getId(), null);

			resetForm();
			resetAcctTransForm();
			loadDeposit();
			loadListingTableView();
			prefixValue = LookupItemUtils.getSysNumGenVO(getSessionInfoBean().getCompanyVO().getId(), CommonConstant.SYS_NUM_CD_BANK_DEPS).getPrefixid();
			// GST List
			inputTaxCodeVOList = gstBO.getTaxCodeList(GSTType.INPUT);
			outputTaxCodeVOList = gstBO.getTaxCodeList(GSTType.OUTPUT);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	private void loadDeposit() throws BusinessException {
		bankAcctViewList = bankAcctBO.getBankAcctList(getSessionInfoBean().getCompanyVO().getId());
		if(CollectionUtils.isNotEmpty(bankAcctViewList)) {	
			List<UserRoleViewVO> userRoleList = securityBO.getUserRoleListByUser(this.getSessionInfoBean().getUserVO().getUuid());
			invPmntAuthVO = new InvPmntAuthVO();
			if (CollectionUtils.isNotEmpty(userRoleList)) {
				for (UserRoleViewVO userRoleVO : userRoleList) {
					if (userRoleVO.getRoleCode().equals(SalesConstant.BANK_DEPOSIT_DEL)) {
						invPmntAuthVO.setPmntDel(true);
					} else if (userRoleVO.getRoleCode().equalsIgnoreCase(SalesConstant.ACCT_MANAGER_ROLE)) {
						invPmntAuthVO.setAccountManager(true);
					}
				}
			}
			//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//transDate = dateFormat.parse(dateFormat.format(new Date()));
			lookupItemBT = paymentBO.getlookupItemBT("cash_deps");
			lookupItemCBT = paymentBO.getlookupItemCBT("gnrl_deps");
			ldmDeposit = new LazyDepositDataModel();
			resetForm();
		}
	}

	public void onAcctTransSelected(AcctTransViewVO vo) throws BusinessException{
		try {
			
			acctTransViewVO = vo;
			isAddAcctTrans = false;

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param event
	 * @throws BusinessException
	 */
	public void handleInvSelect(SelectEvent event) {
		try {
			InvoicePaymentVO tempVO = (InvoicePaymentVO) event.getObject(); 
			boolean found= false;
			
			for(int i = 0; i < cashBookVO.getInvPymtList().size() ; i++) {
				if(cashBookVO.getInvPymtList().get(i).getId().toString().equals(tempVO.getId().toString())) {
					found = true;
				} 
			}
			
			if(!found) {
				cashBookVO.getInvPymtList().add(tempVO);
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	} 
	
	/**
	 * 
	 */
	public void loadInvoicePayment() {
		this.resetFilteredObjList();
		invPaymentList = (new ArrayList<InvoicePaymentVO>());
		trackingLogUtils.startLogs();
		try{
			invPaymentList = depositBO.getInvoicePaymentListNoCashbook(getSessionInfoBean().getCompanyVO().getId());
			
		} catch(Exception t) {
			t.printStackTrace();
		}
		trackingLogUtils.endLogs("loadInvoicePayment");
	}
	
	/**
	 * 
	 * @param invoicePaymentVO
	 */
	public void delInvPmnt(InvoicePaymentVO invoicePaymentVO) {  
		invPmntAUDList.getDelList().add(invoicePaymentVO );
		cashBookVO.getInvPymtList().remove(invoicePaymentVO); 
	}
	
	/**
	 * Delete account trans
	 * @param acctTransViewVO
	 */
	public void delAcctTrans(AcctTransViewVO acctTransViewVO) {
		try {
			cashBookVO.getAcctTransList().remove(acctTransViewVO);
			//totalBal = totalAcctTransBal();
			handleAmountChange();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Edit account trans
	 * @throws BusinessException
	 */
	public void editAcctTrans() {
		try {
			if(acctTransViewVO.getAmount() == 0.00) throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_CREDIT_REQUIRED);
			
			resetAcctTransViewForm();
			//totalBal = totalAcctTransBal();
			//acctTransViewVO.setCredit(cashBookVO.getDebit() - totalBal);
			handleAmountChange();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	
	/**
	 * Add account trans
	 * @throws BusinessException
	 */
	public void addAcctTrans() {
		try {
			if(acctTransViewVO.getAmount() == 0.00) throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_CREDIT_REQUIRED);
			
			if(null == cashBookVO.getAcctTransList()) cashBookVO.setAcctTransList(new ArrayList<AcctTransViewVO>());
			acctTransViewVO.setType(lookupItemCBT.getCode());
			cashBookVO.getAcctTransList().add(acctTransViewVO);
			
			resetAcctTransViewForm();
			//totalBal = totalAcctTransBal();
			//acctTransViewVO.setCredit(cashBookVO.getDebit() - totalBal);
			handleAmountChange();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
		
		onAddAcctTrans();
	}

	/**
	 * 
	 */
	public void addDeposit() {
		try {
			trackingLogUtils.startLogs();
			
			// check financial closed / GST submitted
			checkFinGSTClosed(CommonConstant.ACTION_CD_ADD, null, cashBookVO.getDtTrans(), invPmntAuthVO.isAccountManager());
			
			if (cashBookVO.getDebit().doubleValue() == 0) throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_CREDIT_REQUIRED);
			if (new BigDecimal(cashBookVO.getDebit().toString()).compareTo(amtCalcViewVO.getTotalAmountIncludeTax()) != 0)
				throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_NOT_EQUAL);
			
			// check invoice payment
			bankAcctSearchVO = paymentBO.getBankAcctSearchList(cashBookVO.getIdBank());
			acctVO = paymentBO.getAcctSearchList(bankAcctSearchVO.getIdAcct());
			
			//double total = Math.round(totalAcctTransBal() * 100.0) / 100.0;
			if (CollectionUtils.isNotEmpty(cashBookVO.getInvPymtList())) {	
				for(int i = 0 ; i < cashBookVO.getInvPymtList().size() ; i++) {
					cashBookVO.getInvPymtList().get(i).setInvPmnt("1");
					invPmntAUDList.getUpdList().add(cashBookVO.getInvPymtList().get(i));
				}
			}
			cashBookVO.setAmountCalcViewVO(amtCalcViewVO);
			depositBO.addDeposit(cashBookVO,getSessionInfoBean().getCompanyVO().getId(),sysNumGenVO,sysNumGenList,bankAcctSearchVO,lookupItemBT,lookupItemCBT,acctVO,invPmntAUDList);
			onDepositSelected(cashBookVO);
			successResult();
				
			cashBookVO.setTemp(bankAcctSearchVO.getName()); 
			cashBookVO.setIdBank(bankAcctSearchVO.getId());
 
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("addDeposit");
		}
	}

	public void onDepositSelected(CashBookVO vo) {
		try {
			trackingLogUtils.startLogs();
			
			cashBookVO = vo;
			setOriObj(vo.clone());
			
			isGSTPeriod = gstBO.isGSTPeriod(getSessionInfoBean().getCompanyVO().getId(), cashBookVO.getDtTrans());
			
			acctTransCashBookVO = depositBO.getAcctTransCashBook(cashBookVO.getSysNo(),cashBookVO.getTransTypeCd(),cashBookVO.getSysCode(),getSessionInfoBean().getCompanyVO().getId());
			cashBookVO.setAcctTransList(paymentBO.getAcctTransViewTableList(cashBookVO.getSysNo(),cashBookVO.getTypeCd(),cashBookVO.getSysCode(),BaseConstant.STATUS_ACTIVE,getSessionInfoBean().getCompanyVO().getId()));
			bankAcctSearchVO = paymentBO.getBankAcctSearchList(cashBookVO.getIdBank());
			//totalBal = totalAcctTransBal();
			cashBookVO.setInvPymtList(depositBO.getInvoicePaymentListByCashbook(cashBookVO.getId()));
			getFinPeriodClosedStatus();
			if (cashBookVO.getIdCustomer() != null) {
				CustomerVO custVO = customerBO.getCustomer(cashBookVO.getIdCustomer());
				if (custVO != null) this.cashBookVO.setCustCode(custVO.getCode());
			}
			
			for (AcctTransViewVO acctTransViewVO : cashBookVO.getAcctTransList()) {
				if (acctTransViewVO.getCredit() > 0) acctTransViewVO.setAmount(acctTransViewVO.getCredit());
				else acctTransViewVO.setAmount(-acctTransViewVO.getDebit());
			}
			
			updateTaxableAmount(true);
			
		}catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("onDepositSelected");
		}
	}

	public void deleteDeposit() {
		try {
			trackingLogUtils.startLogs();
			
			// Unable to delete this record due to financial period closed.
			if (cashBookVO.isDateInFinPeriodClosed()) throw new BusinessException(CommonErrConstant.ERR_ACCT_FIN_PERIOD_CLOSED_CANNOT_DEL);
						
			deleteDepositList();
			search();
			resetForm();
			successResult();

		} catch (Throwable t){
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("deleteDeposit");
		}
	}

	private void deleteDepositList() throws BusinessException {
		paymentBO.deletePaymentList(cashBookVO, getSessionInfoBean().getCompanyVO().getId());
		depositBO.deleteDeposit(cashBookVO.getId().toString(), getSessionInfoBean().getCompanyVO().getId());
	}

	public void editDeposit() {
		try {
			trackingLogUtils.startLogs();
			
			CashBookVO oriVO = (CashBookVO) getOriObj();
			checkFinGSTClosed(CommonConstant.ACTION_CD_UPD, oriVO.getDtTrans(), cashBookVO.getDtTrans(), invPmntAuthVO.isAccountManager());
			
			if (cashBookVO.getDebit().doubleValue() == 0) throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_CREDIT_REQUIRED);
			if (new BigDecimal(cashBookVO.getDebit().toString()).compareTo(amtCalcViewVO.getTotalAmountIncludeTax()) != 0)
				throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_NOT_EQUAL);
			
			//bankAcctSearchVO = paymentBO.getBankAcctSearchList(cashBookVO.getIdBank());
			acctTransViewVO.setSysNo(cashBookVO.getSysNo());
			cashBookVO.setAmountCalcViewVO(amtCalcViewVO);
			depositBO.updateDeposit(cashBookVO, acctTransCashBookVO, bankAcctSearchVO, getSessionInfoBean().getCompanyVO().getId());
			onDepositSelected(cashBookVO);
			successResult();
			
			/*double total = Math.round(totalAcctTransBal() * 100.0) / 100.0;
			if(total == cashBookVO.getDebit().doubleValue()) {
				acctTransViewVO.setSysNo(cashBookVO.getSysNo());
				depositBO.updateDeposit(cashBookVO, acctTransCashBookVO, bankAcctSearchVO);
				onDepositSelected(cashBookVO);
				successResult();

			} else throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_NOT_EQUAL);*/
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("editDeposit");
		}
	}
	
	/*private void calInvPmnt() throws BusinessException {
		if (CollectionUtils.isNotEmpty(cashBookVO.getInvPymtList())) {
			double totAmt = 0;
			for (InvoicePaymentVO vo : cashBookVO.getInvPymtList()) {
				totAmt += vo.getAmount();
			}
			if (totAmt < cashBookVO.getDebit().doubleValue()) throw new BusinessException(CommonErrConstant.ERR_DEPOSIT_LESS_INV_PMNT);
		}
	}*/
	
	public void cancel() throws BusinessException{
		try{
			acctTransViewVO.setAcctViewVO(acctTransViewTempVO.getAcctViewVO());
			acctTransViewVO.setAmount(preveditBal);
			acctTransViewVO.setDesc(acctTransViewTempVO.getDesc());
			acctTransViewVO.setCode(acctTransViewTempVO.getTempAcct());

		}catch(Throwable t){
			errorResult(t);
		}
	}

	public void handleAcctSelect(SelectEvent event) throws BusinessException{
		try {
			AcctViewVO acctViewVO = (AcctViewVO) event.getObject();
			acctTransViewVO.setCode(acctViewVO.getCode() + (StringUtils.isNotEmpty(acctViewVO.getSubCode()) ? "-" + acctViewVO.getSubCode() : ""));
			acctTransViewVO.setDesc(acctViewVO.getDesc() + (StringUtils.isNotEmpty(acctViewVO.getSubDesc()) ? ", " + acctViewVO.getSubDesc() : ""));
			acctTransViewVO.setAcctViewVO(acctViewVO);
			acctTransViewVO.setAcctId(acctViewVO.getId());
			
			handleTaxCodeSelection(acctViewVO.getTaxCode(), acctViewVO.getTaxRate() == null ? null : (double) acctViewVO.getTaxRate());
			
			/*if (StringUtils.isEmpty(acctViewVO.getSubCode())) acctTransViewVO.setCode(acctViewVO.getCode());
			else acctTransViewVO.setCode(acctViewVO.getCode() + "-" + acctViewVO.getSubCode());
			if (StringUtils.isEmpty(acctViewVO.getSubDesc())) 
			{
				acctTransViewVO.setDesc(acctViewVO.getDesc());
			}
			else
			{
				acctTransViewVO.setDesc(acctViewVO.getDesc()+","+acctViewVO.getSubDesc());
			}*/

		} catch (Throwable t) {
			errorResult(t);
		}
	}


	public void changeDesc() throws BusinessException{
		try{
			for(AcctViewVO vo:acctViewList) {
				if (acctTransViewVO.getCode().equals(vo.getCode())) {
					acctTransViewVO.setDesc(vo.getDesc());
					acctTransViewVO.setAcctViewVO(vo);
					acctTransViewVO.setAcctId(vo.getId());
					handleTaxCodeSelection(vo.getTaxCode(), vo.getTaxRate() == null ? null : (double) vo.getTaxRate());
					break;
					
				} else if (acctTransViewVO.getCode().equalsIgnoreCase(vo.getCode() + "-" + vo.getSubCode())) {
					acctTransViewVO.setDesc(vo.getDesc() + "," + vo.getSubDesc());
					acctTransViewVO.setAcctViewVO(vo);
					acctTransViewVO.setAcctId(vo.getId());
					handleTaxCodeSelection(vo.getTaxCode(), vo.getTaxRate() == null ? null : (double) vo.getTaxRate());
					break;
				}
			}
		}catch(Throwable t){
			errorResult(t);
		}
	}
	
	/**
	 * Search function
	 */
	public void search() {
		try {
			searchParamVO.setObj2(null);
			final DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("idForm:idPaymentTable");
			
			d.setFirst(0);
			searchParamVO.setObj4(null);
			
		} catch (Throwable t) {
			errorResult(t);
		}
		
	}
	
	/**
	 * Search all function
	 */
	public void searchAll() {
		try {
			searchParamVO.setObj2("AllDates");
			final DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("idForm:idPaymentTable");
			d.setFirst(0);
			searchParamVO.setObj4(null);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * On add deposit
	 */
	public void onAddDeposit() {
			if(searchParamVO.getObj1() != null) {	
				Long bankId = Long.parseLong((String) searchParamVO.getObj1());
				for(BankAcctViewVO vo : bankAcctViewList) {
					if (vo.getId().equals(bankId)) {
						cashBookVO.setIdBank(bankId);
						break;
					}
				}
			} else cashBookVO.setIdBank(bankAcctViewList.get(0).getId());
			cashBookVO.setInvPymtList(new ArrayList<InvoicePaymentVO>());
			cashBookVO.setDtTrans(new Date());
			invPmntAUDList = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
		}	
	
	/**
	 * 
	 * @param query
	 * @return
	 */
	public List<String> complete(String query) {  
		List<String> results = new ArrayList<String>();   
		try{ 
        	String code = null;
			for (int i = 0 ; i < acctViewList.size() ; i++) {
				if (StringUtils.isEmpty(acctViewList.get(i).getSubCode())) code = acctViewList.get(i).getCode();
				else code = acctViewList.get(i).getCode() + "-" + acctViewList.get(i).getSubCode();
				
				if (code.startsWith(query)) results.add(code);
        	}
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return results;
    }  
	
	/**
	 * On passanger selected
	 * @param event
	 */
	public void handlePassangerSelect(SelectEvent event) {
		try {
			CustDetailsVO vo = invoiceBO.getCustDetails(((CustomerVO) event.getObject()).getId());
			if (vo.getCustType().equals("P")) {
				cashBookVO.setPayee(vo.getContPersonName());
				
			} else if (vo.getCustType().equals("C")) {
				cashBookVO.setPayee(vo.getCompanyName());
			}
			
			cashBookVO.setIdCustomer(vo.getCustId());
			cashBookVO.setCustCode(vo.getCode());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 */
	public void handlePassangerClear() {
		try {
			cashBookVO.setPayee(null);
			cashBookVO.setIdCustomer(null);
			cashBookVO.setCustCode(null);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 */
	public void passangerSearch() {
		try {
			setCustAdd(true);
			setPassangerDetailsList(customerBO.getCustomerListSearch(this.getSessionInfoBean().getCompanyVO().getId()));
			this.resetFilteredObjList();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * New deposit form
	 */
	public void newDepositForm() {
		resetForm();
		onAddDeposit();
		
	}
	
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void onAddAcctTrans() {
		try{
			resetAcctTransViewForm();
			handleAmountChange();
			
			acctTransViewVO.setAmount(new BigDecimal(cashBookVO.getDebit().toString()).subtract(amtCalcViewVO.getTotalAmountIncludeTax()).doubleValue());
			isAddAcctTrans = true;
			handleAmountChange();
			
		} catch(Throwable t){
			errorResult(t);
		}
	}
	
	/*
	 * 
	 * @return
	 */
	private double totalAcctTransBal() {
		BigDecimal total = new BigDecimal("0.0");
		if (CollectionUtils.isNotEmpty(cashBookVO.getAcctTransList())) {
			for (AcctTransViewVO vo : cashBookVO.getAcctTransList()) {
				//total = total.add(new BigDecimal(vo.getCredit().toString()).subtract(new BigDecimal(vo.getDebit().toString())));
				total = total.add(new BigDecimal(String.valueOf(vo.getAmount())));
			}
		}
		return total.doubleValue();
	}

	public void printReportDepositList(String xlsOrPDf) {
		try {
			listFullExportDepositVO = new ArrayList<CashBookVO>();
			searchParamVO.setObj4("print");
			if(depositListFilterParam == null) {
				depositListFilterParam = new HashMap<String, Object>();
				depositListFilterParam.put("companyId", getSessionInfoBean().getCompanyVO().getId());
				depositListFilterParam.put("filters", new HashMap<String, String>());
				depositListFilterParam.put("sortOrder", CommonConstant.SORT_ASC);
				depositListFilterParam.put("searchParam", searchParamVO);
				depositListFilterParam.put("typeCd", CommonConstant.SYS_NUM_CD_BANK_DEPS);
			}
			depositListFilterParam.remove("first");
			depositListFilterParam.put("first", 0);
			depositListFilterParam.remove("pageSize");
			depositListFilterParam.put("pageSize", 10000000);	//Use max page size for query
			
			listFullExportDepositVO = (List<CashBookVO>) depositBO.getDepositList(depositListFilterParam);

			for(CashBookVO tmpVO : listFullExportDepositVO) {
				tmpVO.setSysPrefix(tmpVO.getSysPrefix() + "-" + tmpVO.getSysNo());
			}
			SimpleDateFormat ftDate = new SimpleDateFormat("dd-MMM-yyyy");
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(searchParamVO.getObj1() != null) { map.put("bankName", getBankName()); } else { map.put("bankName", "All"); }
			map.put("dateFrom", ftDate.format(searchParamVO.getFromDate()).toString());
			map.put("dateTo", ftDate.format(searchParamVO.getToDate()).toString());
			map.put("cashBookList", filteredObjList);
			
			JasperPrint jasperPrint = ReportUtils.getJasperPrint(listFullExportDepositVO, map, CommonConstant.JAS_RPT_BNK_DEPOSIT_LIST);
//			ReportUtils.printReport(jasperPrint, ("bank_deposit" +(searchParamVO.getObj1() ==null  ? "" : "_" + map.get("bankName"))+ "_" + map.get("dateFrom") + "_" + map.get("dateTo")).replace("-", "_"));
			String reportName = CommonConstant.PDF_RPT_BNK_DEPOSIT_LIST + ((searchParamVO.getObj1() ==null  ? "" : "_" + map.get("bankName"))+ "_" + map.get("dateFrom") + "_" + map.get("dateTo")).replace("-", "_");
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	private String getBankName() {
			if(searchParamVO.getObj1()!=null) 
			{
				if(!searchParamVO.getObj1().toString().equals("All"))
				{
					Long bankId = Long.parseLong((String) searchParamVO.getObj1());
					for(BankAcctViewVO vo : bankAcctViewList) {
						if (vo.getId().equals(bankId)) {
							return vo.getName();
						}
					}
				}	
			}	
			
		return "";
	}
	
	/**
	 * Get Financial Period Closed Status
	 */ 
	public void getFinPeriodClosedStatus() {
		try {
			cashBookVO.setDateInFinPeriodClosed(LookupItemUtils.getFinAndGSTPeriodClosedStatus(this.getSessionInfoBean().getCompanyVO().getId(), cashBookVO.getDtTrans(), invPmntAuthVO.isAccountManager()));
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	public void handleGSTSelect(SelectEvent event) {
		try {
			TaxCodeVO taxCodeVO = (TaxCodeVO) event.getObject();
			handleTaxCodeSelection(taxCodeVO.getCode(), (double) taxCodeVO.getRate());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Amount change = reverse calculation
	 */
	public void handleAmountChange() {
		try {
			acctTransViewVO = (AcctTransViewVO) AccountHelper.calcTaxableAmount(acctTransViewVO, false, 0, 0);
			amtCalcViewVO = AccountHelper.computeAmountTotalBasedItem(amtCalcViewVO, false, cashBookVO.getAcctTransList(), null, false);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * GST Amount change = GST + Amount
	 */
	public void handleGSTAmtChange() {
		try {
			BigDecimal amount = new BigDecimal(String.valueOf(acctTransViewVO.getAmount()));
			BigDecimal taxAmount = new BigDecimal(acctTransViewVO.getTaxAmount().toString());
			
			acctTransViewVO.setAmountIncludeTax(amount.add(taxAmount).doubleValue());
			amtCalcViewVO = AccountHelper.computeAmountTotalBasedItem(amtCalcViewVO, false, acctTransVO.getAcctTransList(), null, false);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 */
	public void handleTaxCodeClear() {
		try {
			handleTaxCodeSelection(null, null);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Reverse GST calculation
	 */
	public void handleAmountInclTaxChange() {
		try {
			acctTransViewVO = (AcctTransViewVO) AccountHelper.calcAmountBaseOnTotalTaxableAmount(acctTransViewVO);
			amtCalcViewVO = AccountHelper.computeAmountTotalBasedItem(amtCalcViewVO, false, cashBookVO.getAcctTransList(), null, false);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	private void handleTaxCodeSelection(String taxCode, Double taxRate) throws BusinessException {
		if(getSessionInfoBean().getCompanyVO().getIdAcctGST() != null && taxCode != null) {
			isGSTPeriod = gstBO.isGSTPeriod(getSessionInfoBean().getCompanyVO().getId(), cashBookVO.getDtTrans());
			
			if(isGSTPeriod) {
				if(taxCode != null && taxRate == null) {
					TaxCodeVO taxCodeVO = gstBO.getTaxCode(taxCode);
					acctTransViewVO.setTaxCode(taxCodeVO.getCode());
					acctTransViewVO.setTaxRate(taxCodeVO.getRate());
					
				} else {
					acctTransViewVO.setTaxCode(taxCode);
					acctTransViewVO.setTaxRate(taxRate.floatValue());
				}
				
				handleAmountChange();
			}
		} else {
			acctTransViewVO.setTaxCode(null);
			acctTransViewVO.setTaxRate(null);
			acctTransViewVO.setTaxAmount(0.00);
			acctTransViewVO.setAmountIncludeTax(acctTransViewVO.getAmount());
		}
	}
	
	private void updateTaxableAmount(boolean isCalGLItems) throws BusinessException {
		//amtCalcViewVO = AccountHelper.computeAmountTotalBasedItem(amtCalcViewVO, true, cashBookVO.getAcctTransList(), null, false);
		if (isCalGLItems) calculateGLItemsAmount(cashBookVO.getAcctTransList());
		amtCalcViewVO = AccountHelper.computeAmountTotalBasedItem(amtCalcViewVO, false, cashBookVO.getAcctTransList(), null, false);
	}
	
	/**
	 * 
	 * @param list
	 * @throws BusinessException
	 */
	private void calculateGLItemsAmount(List<AcctTransViewVO> list) throws BusinessException {
		for (AcctTransViewVO vo : list) {
			//vo.setTaxAmount(vo.getAmount() < 0 ? -vo.getTaxAmount() : vo.getTaxAmount());
			
			BigDecimal amount = new BigDecimal(String.valueOf(vo.getAmount()));
			BigDecimal taxAmount = new BigDecimal(vo.getTaxAmount().toString());
			
			vo.setAmountIncludeTax(amount.add(taxAmount).doubleValue());
		}
	}
	
	/**
	 * 
	 * @param document
	 */
	public void postProcessXLS(Object document) {
		try {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        sheet.shiftRows(0, sheet.getLastRowNum(), 2);
        HSSFRow header = sheet.createRow(0);
        
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);

        }
       
		} catch(Throwable t) {
			errorResult(t);
		}
    }
	
	public void handleChangeColumnsViewClick() {
		// Get Data Table Columns
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("idForm:idPaymentTable");
		List<UIColumn> column = dataTable.getColumns();
		int index = 1;
		List<ListingTableViewColumnsVO> columnList = new ArrayList<>();
		for (UIColumn uiColumn : column) {
			boolean isExists = false;
			for (ListingTableViewColumnsVO columnsVO : listingTableViewVO.getListingTableViewColumnsVOList()) {
				if (StringUtils.equals(columnsVO.getColumnsName(), uiColumn.getHeaderText())) {
					columnsVO.setSeqNo(index);
					
					columnList.add(columnsVO);
					isExists = true;
					break;
				}
			}
			
			if (!isExists) {
				ListingTableViewColumnsVO columnsVO = new ListingTableViewColumnsVO();
				columnsVO.setColumnsName(uiColumn.getHeaderText());
				columnsVO.setSeqNo(index);
				columnsVO.setVisible(true);
				columnList.add(index - 1, columnsVO);
			}
			index++;
		} 
		
		listingTableViewVO.setListingTableViewColumnsVOList(columnList);
	}
	
	public void updateListingTableView() {
		try {
			listingTableViewBO.updateListingTableView(listingTableViewVO);
			
			loadListingTableView();
			successResult();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void loadListingTableView() {
		listingTableViewMap = new HashMap<>();
		try {
			listingTableViewVO = listingTableViewBO.getListingTableView(ConstantListingTableView.LISTING_TYPE_BANK_DPT, getSessionInfo().getUserVO().getId());
			
			if (listingTableViewVO == null) {
				listingTableViewVO = new ListingTableViewVO();
				listingTableViewVO.setIdUser(getSessionInfo().getUserVO().getId());
				listingTableViewVO.setListingType(ConstantListingTableView.LISTING_TYPE_BANK_DPT);
				
				listingTableViewVO.setListingTableViewColumnsVOList(new ArrayList<ListingTableViewColumnsVO>()); 
			}
			
			for (ListingTableViewColumnsVO columnsVO : listingTableViewVO.getListingTableViewColumnsVOList()) {
				listingTableViewMap.put(columnsVO.getSeqNo(), columnsVO.getVisible());
			}
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	/**********************
	 * Lazy loading model *
	 **********************/
	class LazyDepositDataModel extends LazyDataModel<CashBookVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<CashBookVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				trackingLogUtils.startLogs();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("companyId", getSessionInfoBean().getCompanyVO().getId());
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				params.put("searchParam", searchParamVO);
				params.put("typeCd", CommonConstant.SYS_NUM_CD_BANK_DEPS);
				setRowCount(depositBO.getDepositListSize(params));
				updateFilterParam(params);
				if (getRowCount() > 0) {
		            return depositBO.getDepositList(params); 
				}
				
			} catch (Throwable t) {
				t.printStackTrace();
				errorResult(t);
			} finally {
				trackingLogUtils.endLogs("LazyDepositDataModel");
			}
			return null;
		}
		
	}
	
	private void updateFilterParam(Map<String, Object> params) {
		this.depositListFilterParam = params;
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/
	
	public AcctTransVO getAcctTransVO() {
		return acctTransVO;
	}

	public void setAcctTransVO(AcctTransVO acctTransVO) {
		this.acctTransVO = acctTransVO;
	}

	public CashBookVO getCashBookVO() {
		return cashBookVO;
	}

	public void setCashBookVO(CashBookVO cashBookVO) {
		this.cashBookVO = cashBookVO;
	}

	public SystemNumberGenerationVO getSysNumGenVO() {
		return sysNumGenVO;
	}

	public void setSysNumGenVO(SystemNumberGenerationVO sysNumGenVO) {
		this.sysNumGenVO = sysNumGenVO;
	}

	public List<AcctTransVO> getAcctTransList() {
		return acctTransList;
	}

	public void setAcctTransList(List<AcctTransVO> acctTransList) {
		this.acctTransList = acctTransList;
	}
	
	public List<SystemNumberGenerationVO> getSysNumGenList() {
		return sysNumGenList;
	}

	public void setSysNumGenList(List<SystemNumberGenerationVO> sysNumGenList) {
		this.sysNumGenList = sysNumGenList;
	}

	public List<BankAcctViewVO> getBankAcctViewList() {
		return bankAcctViewList;
	}

	public void setBankAcctViewList(List<BankAcctViewVO> bankAcctViewList) {
		this.bankAcctViewList = bankAcctViewList;
	}
	
	public BankAcctVO getBankAcctVO() {
		return bankAcctVO;
	}

	public void setBankAcctVO(BankAcctVO bankAcctVO) {
		this.bankAcctVO = bankAcctVO;
	}

	public CompanyVO getCompanyVO() {
		return companyVO;
	}

	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}


	public AcctViewVO getAcctViewVO() {
		return acctViewVO;
	}

	public void setAcctViewVO(AcctViewVO acctViewVO) {
		this.acctViewVO = acctViewVO;
	}

	public Double getPrevBal() {
		return prevBal;
	}

	public void setPrevBal(Double prevBal) {
		this.prevBal = prevBal;
	}

	
	public Double getTotalBal() {
		return totalBal;
	}

	public void setTotalBal(Double totalBal) {
		this.totalBal = totalBal;
	}

	public Double getPreveditBal() {
		return preveditBal;
	}

	public void setPreveditBal(Double preveditBal) {
		this.preveditBal = preveditBal;
	}

	/**
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<AcctTransViewVO> getAddTransList() {
		return addTransList;
	}

	public void setAddTransList(List<AcctTransViewVO> addTransList) {
		this.addTransList = addTransList;
	}

	public List<AcctTransViewVO> getUpdTransist() {
		return updTransist;
	}

	public void setUpdTransist(List<AcctTransViewVO> updTransist) {
		this.updTransist = updTransist;
	}

	public List<AcctTransViewVO> getDelTransList() {
		return delTransList;
	}

	public void setDelTransList(List<AcctTransViewVO> delTransList) {
		this.delTransList = delTransList;
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

	public List<AcctVO> getAcctCodeList() {
		return acctCodeList;
	}

	public void setAcctCodeList(List<AcctVO> acctCodeList) {
		this.acctCodeList = acctCodeList;
	}


	public AcctTransViewVO getAcctTransViewVO() {
		return acctTransViewVO;
	}

	public void setAcctTransViewVO(AcctTransViewVO acctTransViewVO) {
		this.acctTransViewVO = acctTransViewVO;
	}

	public List<AcctTransViewVO> getAcctTransListView() {
		return acctTransListView;
	}

	public void setAcctTransListView(List<AcctTransViewVO> acctTransListView) {
		this.acctTransListView = acctTransListView;
	}

	public List<AcctTransVO> getUpdCompTranslist() {
		return updCompTranslist;
	}

	public void setUpdCompTranslist(List<AcctTransVO> updCompTranslist) {
		this.updCompTranslist = updCompTranslist;
	}

	public AcctVO getAcctIdVO() {
		return acctIdVO;
	}

	public void setAcctIdVO(AcctVO acctIdVO) {
		this.acctIdVO = acctIdVO;
	}

	public AcctTransViewVO getAcctTransCashBookVO() {
		return acctTransCashBookVO;
	}

	public void setAcctTransCashBookVO(AcctTransViewVO acctTransCashBookVO) {
		this.acctTransCashBookVO = acctTransCashBookVO;
	}

	public List<CashBookVO> getCashBookDepositList() {
		return cashBookDepositList;
	}

	public void setCashBookDepositList(List<CashBookVO> cashBookDepositList) {
		this.cashBookDepositList = cashBookDepositList;
	}

	public LookupItemVO getLookupItemBT() {
		return lookupItemBT;
	}

	public void setLookupItemBT(LookupItemVO lookupItemBT) {
		this.lookupItemBT = lookupItemBT;
	}

	public LookupItemVO getLookupItemCBT() {
		return lookupItemCBT;
	}

	public void setLookupItemCBT(LookupItemVO lookupItemCBT) {
		this.lookupItemCBT = lookupItemCBT;
	}

	public List<AcctTransViewVO> getAcctTransSysNoList() {
		return acctTransSysNoList;
	}

	public void setAcctTransSysNoList(List<AcctTransViewVO> acctTransSysNoList) {
		this.acctTransSysNoList = acctTransSysNoList;
	}

	public BankAcctVO getBankAcctSearchVO() {
		return bankAcctSearchVO;
	}

	public void setBankAcctSearchVO(BankAcctVO bankAcctSearchVO) {
		this.bankAcctSearchVO = bankAcctSearchVO;
	}

	public CustDetailsVO getCustDetailsVO() {
		return custDetailsVO;
	}

	public void setCustDetailsVO(CustDetailsVO custDetailsVO) {
		this.custDetailsVO = custDetailsVO;
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

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchValueType() {
		return searchValueType;
	}

	public void setSearchValueType(String searchValueType) {
		this.searchValueType = searchValueType;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public AcctTransViewTempVO getAcctTransViewTempVO() {
		return acctTransViewTempVO;
	}

	public void setAcctTransViewTempVO(AcctTransViewTempVO acctTransViewTempVO) {
		this.acctTransViewTempVO = acctTransViewTempVO;
	}

	public CashBookViewVO getCashBookViewVO() {
		return cashBookViewVO;
	}

	public void setCashBookViewVO(CashBookViewVO cashBookViewVO) {
		this.cashBookViewVO = cashBookViewVO;
	}

	public SystemNumberGenerationVO getSysGenCodeVO() {
		return sysGenCodeVO;
	}

	public void setSysGenCodeVO(SystemNumberGenerationVO sysGenCodeVO) {
		this.sysGenCodeVO = sysGenCodeVO;
	}
	/**
	 * @return the custDetailsNameVO
	 */
	public CustDetailsVO getCustDetailsNameVO() {
		return custDetailsNameVO;
	}

	/**
	 * @param custDetailsNameVO the custDetailsNameVO to set
	 */
	public void setCustDetailsNameVO(CustDetailsVO custDetailsNameVO) {
		this.custDetailsNameVO = custDetailsNameVO;
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

	public List<CustDetailsVO> getPassangerDetailsList() {
		return passangerDetailsList;
	}

	public void setPassangerDetailsList(List<CustDetailsVO> passangerDetailsList) {
		this.passangerDetailsList = passangerDetailsList;
	}

	/**
	 * @return the invPmntAuthVO
	 */
	public InvPmntAuthVO getInvPmntAuthVO() {
		return invPmntAuthVO;
	}

	/**
	 * @param invPmntAuthVO the invPmntAuthVO to set
	 */
	public void setInvPmntAuthVO(InvPmntAuthVO invPmntAuthVO) {
		this.invPmntAuthVO = invPmntAuthVO;
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
	 * @return the editAmount
	 */
	public Double getEditAmount() {
		return editAmount;
	}

	/**
	 * @param editAmount the editAmount to set
	 */
	public void setEditAmount(Double editAmount) {
		this.editAmount = editAmount;
	}
	
	/**
	 * @return the custAdd
	 */
	public boolean isCustAdd() {
		return custAdd;
	}

	/**
	 * @param custAdd the custAdd to set
	 */
	public void setCustAdd(boolean custAdd) {
		this.custAdd = custAdd;
	}

	public List<InvoicePaymentVO> getInvPaymentList() {
		return invPaymentList;
	}

	public void setInvPaymentList(List<InvoicePaymentVO> invPaymentList) {
		this.invPaymentList = invPaymentList;
	}

	/**
	 * @return the ldmDeposit
	 */
	public LazyDataModel<CashBookVO> getLdmDeposit() {
		return ldmDeposit;
	}

	/**
	 * @return the isAddAccTrans
	 */
	public boolean isAddAcctTrans() {
		return isAddAcctTrans;
	}

	public String getPrefixValue() {
		return prefixValue;
	}

	public void setPrefixValue(String prefixValue) {
		this.prefixValue = prefixValue;
	}

	/**
	 * @return the amtCalcViewVO
	 */
	public AmountCalcViewVO getAmtCalcViewVO() {
		return amtCalcViewVO;
	}

	/**
	 * @return the inputTaxCodeVOList
	 */
	public List<TaxCodeVO> getInputTaxCodeVOList() {
		return inputTaxCodeVOList;
	}

	/**
	 * @return the outputTaxCodeVOList
	 */
	public List<TaxCodeVO> getOutputTaxCodeVOList() {
		return outputTaxCodeVOList;
	}

	public boolean isGSTPeriod() {
		return isGSTPeriod;
	}

	public void setGSTPeriod(boolean isGSTPeriod) {
		this.isGSTPeriod = isGSTPeriod;
	}

	public ListingTableViewVO getListingTableViewVO() {
		return listingTableViewVO;
	}

	public void setListingTableViewVO(ListingTableViewVO listingTableViewVO) {
		this.listingTableViewVO = listingTableViewVO;
	}

	public Map<Integer, Boolean> getListingTableViewMap() {
		return listingTableViewMap;
	}
	
	public void setListingTableViewMap(Map<Integer, Boolean> listingTableViewMap) {
		this.listingTableViewMap = listingTableViewMap;
	}

	public String getDestinationPaymentAttachmentPath() {
		return destinationPaymentAttachmentPath;
	}
	
}
