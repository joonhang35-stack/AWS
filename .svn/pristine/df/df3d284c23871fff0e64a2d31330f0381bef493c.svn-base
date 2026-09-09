package com.bcs.zsg.bank.web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.bo.ChartOfAcctBO;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.bank.bo.BankAcctBO;
import com.bcs.zsg.bank.bo.CashBookBO;
import com.bcs.zsg.bank.bo.DepositBO;
import com.bcs.zsg.bank.bo.PaymentBO;
import com.bcs.zsg.bank.bo.RefundBO;
import com.bcs.zsg.bank.vo.AcctTransViewTempVO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.bank.vo.CashBookViewVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.ConstantWebPage;
import com.bcs.zsg.common.helper.EnglishNumberToWords;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.common.web.bean.primefaces.PFDataTableLazy;
import com.bcs.zsg.component.security.bo.SecurityBO;
import com.bcs.zsg.component.security.vo.UserRoleViewVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.bo.SystemNumberGenerationBO;
import com.bcs.zsg.maintenance.helper.MaintConstant;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.product.helper.LazyDMTourDep;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.sales.bo.CustomerBO;
import com.bcs.zsg.sales.bo.InvoiceBO;
import com.bcs.zsg.sales.helper.SalesConstant;
import com.bcs.zsg.sales.vo.CustDetailsVO;
import com.bcs.zsg.sales.vo.CustomerVO;
import com.bcs.zsg.sales.vo.InvPmntAuthVO;
import com.bcs.zsg.sales.vo.InvoicePaymentVO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.sales.web.bean.InvoiceBean;
import com.bcs.zsg.zextra.backend.helper.SysNumGenUtil;

import net.sf.jasperreports.engine.JasperPrint;

public class RefundBean extends AppBackingBean implements PFDataTableLazy {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient RefundBO refundBO;
	@Autowired
	private transient BankAcctBO bankAcctBO;
	@Autowired
	private transient ChartOfAcctBO chartOfAcctBO;
	@Autowired
	private transient PaymentBO paymentBO;
	@Autowired
	private transient DepositBO depositBO;
	@Autowired
	protected transient InvoiceBO invoiceBO;
	@Autowired
	protected transient CustomerBO customerBO;
	@Autowired
	private transient SecurityBO securityBO;
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	private transient SystemNumberGenerationBO sysNumGenBO;
	@Autowired
	private transient CashBookBO cashBookBO;
	@Autowired
	private transient TourPackageBO tourPkgBO;
	@Autowired
	private transient RegionBO regionBO;

	protected boolean custAdd;
	
	private AcctTransVO acctTransVO;
	private CashBookVO cashBookVO;
	private CashBookViewVO cashBookViewVO;
	private BankAcctVO bankAcctVO;
	private SystemNumberGenerationVO sysNumGenVO;
	private SystemNumberGenerationVO sysGenCodeVO;
	private CompanyVO companyVO;
	private AcctViewVO acctViewVO;

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
	private List<CashBookVO> cashBookRefundList;
	//private List<CustDetailsVO> custDetailsList;
	private List<AcctVO> acctAutoCompleteList;
	private List<CustDetailsVO> passangerDetailsList;
	private List<InvoicePaymentVO> invPaymentList;
	private List<TourDepartureVO> tourList;
	
	private LazyDMTourDep<TourDepartureVO> lazyDataModelTourDep;
	
	private Double preveditBal;

	private String searchType;
	private String searchValueType;
	private String searchValue;

	private Double prevBal;
	private Double totalBal=0.00;
	private Double balance;
	//private Long bankId;
	private String prefixValue;
	
	@Override
	public void resetForm() {
		//this.resetFilteredObjList();
		acctTransVO = new AcctTransVO();
		cashBookVO = new CashBookVO();
		cashBookViewVO = new CashBookViewVO();
		companyVO = new CompanyVO();
		acctViewVO = new AcctViewVO();
		bankAcctVO = new BankAcctVO();
		custDetailsVO = new CustDetailsVO();
		custDetailsNameVO = new CustDetailsVO();
		invoiceVO = new InvoiceVO();
		acctTransViewTempVO = new AcctTransViewTempVO();
		resetAcctTransForm();
		resetAcctTransViewForm();
		resetAcctTransList();
		resetCustomerListForm();
		totalBal=0.00;
		cashBookVO.setInvPymtList(new ArrayList<InvoicePaymentVO>());
		acctTransListView=new ArrayList<AcctTransViewVO>();
		
		if (searchParamVO.getObj2() == null) search();
		else searchAll();
		
		setOriObj(null);
	}

	public void resetAcctTransForm() {
		acctTransVO = new AcctTransVO();
	}

	public void resetAcctTransViewForm() {
		acctTransViewVO = new AcctTransViewVO();
	}

	public void resetAcctTransList() {
		addTransList = new ArrayList<AcctTransViewVO>();
		updTransist = new ArrayList<AcctTransViewVO>();
		delTransList = new ArrayList<AcctTransViewVO>();
		
	}

	public void resetCompanyAcctTransList() {
		updCompTranslist = new ArrayList<AcctTransVO>();
	}

	public void resetCashBookForm() throws BusinessException {
		cashBookVO = new CashBookVO();
	}

	public void resetSysNumGen() throws BusinessException {
		sysNumGenVO = new SystemNumberGenerationVO();
	}

	public void resetCustomerListForm() {
		setCustAdd(true);
		setSearchType("");
		setSearchValueType("");
		setSearchValue("");
		//setCustDetailsList(new ArrayList<CustDetailsVO>());
	}

	public void init() throws BusinessException {
		try {
			initSearchParam();
			searchParamVO.setFromDate(new Date());
			searchParamVO.setToDate(searchParamVO.getFromDate());
			
			acctViewList = chartOfAcctBO.getAcctViewList(getSessionInfoBean().getCompanyVO().getId(), null);
			//acctTransListView = paymentBO.getAcctTransListView();
			resetAcctTransViewForm();
			resetAcctTransForm();
			loadRefund();
			resetForm();
			prefixValue = LookupItemUtils.getSysNumGenVO(getSessionInfoBean().getCompanyVO().getId(), CommonConstant.SYS_NUM_CD_RFND).getPrefixid();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	private void loadRefund() throws BusinessException {
		try {
			resetFilteredObjList();
			bankAcctViewList = bankAcctBO.getBankAcctList(getSessionInfoBean().getCompanyVO().getId());
			if (bankAcctViewList.size() != 0) {
				List<UserRoleViewVO> userRoleList = securityBO.getUserRoleListByUser(this.getSessionInfoBean().getUserVO().getUuid());
				
				invPmntAuthVO = new InvPmntAuthVO();
				if (CollectionUtils.isNotEmpty(userRoleList)) {
					for (UserRoleViewVO userRoleVO : userRoleList) {
						if (userRoleVO.getRoleCode().equals(SalesConstant.BANK_REFUND_DEL)) {
							invPmntAuthVO.setPmntDel(true);
						} else if (userRoleVO.getRoleCode().equalsIgnoreCase(SalesConstant.ACCT_MANAGER_ROLE)) {
							invPmntAuthVO.setAccountManager(true);
						}
					}
				}
				
				//SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//transDate = gmtDateFormat.parse(gmtDateFormat.format(new Date()));
				
				lookupItemBT = paymentBO.getlookupItemBT("refund");
				lookupItemCBT = paymentBO.getlookupItemCBT("gnrl_rfnd");
				//bankId = bankAcctViewList.get(0).getId();
				searchParamVO.setObj1(bankAcctViewList.get(0).getId().toString());
				//cashBookRefundList = paymentBO.getBankCashBookList(CommonConstant.SYS_NUM_CD_RFND, bankId, null);
				//resetForm();
			}

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onAcctTransSelected(AcctTransViewVO vo) {
		try {
			if (StringUtils.isEmpty(vo.getAcctViewVO().getSubCode()))
				vo.setTempAcct(vo.getAcctViewVO().getCode());
			else
				vo.setTempAcct(vo.getAcctViewVO().getCode() + "-"
						+ vo.getAcctViewVO().getSubCode());
			preveditBal = vo.getCredit();
			acctTransViewTempVO.setAcctViewVO(vo.getAcctViewVO());
			acctTransViewTempVO.setCredit(vo.getCredit());
			acctTransViewTempVO.setTempAcct(vo.getTempAcct());
			acctTransViewTempVO.setDesc(vo.getDesc());
			acctTransViewVO = vo;
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void onAcctTransSelectedInEdit(AcctTransViewVO vo) {
		try {
			if (StringUtils.isEmpty(vo.getAcctViewVO().getSubCode()))
				vo.setTempAcct(vo.getAcctViewVO().getCode());
			else
				vo.setTempAcct(vo.getAcctViewVO().getCode() + "-"
						+ vo.getAcctViewVO().getSubCode());
			preveditBal = vo.getDebit();
			acctTransViewTempVO.setAcctViewVO(vo.getAcctViewVO());
			acctTransViewTempVO.setCredit(vo.getCredit());
			acctTransViewTempVO.setTempAcct(vo.getTempAcct());
			acctTransViewTempVO.setDesc(vo.getDesc());
			acctTransViewVO = vo;
			acctTransViewVO.setAcctId(vo.getAcctViewVO().getId());
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void delAcctTransList(AcctTransViewVO acctTransViewVO) {
		try {
			cashBookVO.getAcctTransList().remove(acctTransViewVO);
			totalBal = prevBal - acctTransViewVO.getCredit();
			prevBal = totalBal;
			successResult();
			// resetAcctTransViewForm();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void editAcctTrans() throws BusinessException{
		try {
			if(acctTransViewVO.getCredit()==0.00)
			{
				throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_CREDIT_REQUIRED);
			}
			else
			{	
				prevBal = preveditBal;
				if (cashBookVO.getAcctTransList().size() == 1) {
				
					totalBal = acctTransViewVO.getCredit();
					prevBal = totalBal;
	
				} 
				else
				{
					
					if (acctTransViewVO.getCredit()<0.00) {
						balance = totalBal - prevBal;
						totalBal = balance + acctTransViewVO.getCredit();
						prevBal = totalBal;
	
					} else {
						if (prevBal > acctTransViewVO.getCredit()) {
							balance = prevBal - acctTransViewVO.getCredit();
							totalBal = totalBal - balance;
							prevBal = totalBal;
						} else {
							balance = acctTransViewVO.getCredit() - prevBal;
							totalBal = totalBal + balance;
							prevBal = totalBal;
						}
					}
				}
				resetAcctTransViewForm();
			}
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void editAcctTransInEdit() throws BusinessException{
		try {
			if(acctTransViewVO.getDebit()==0.00)
			{
				throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_CREDIT_REQUIRED);
			}
			else
			{	
				updTransist.add(acctTransViewVO);
				prevBal = preveditBal;
				if (null == acctTransListView)
					acctTransListView = new ArrayList<AcctTransViewVO>();
				if (acctTransListView.size() == 1) {
					
					totalBal = acctTransViewVO.getDebit();
					prevBal = totalBal;
				} else {
					
					if (acctTransViewVO.getDebit()<0.00) {
						balance = totalBal - prevBal;
						totalBal = balance + acctTransViewVO.getDebit();
						prevBal = totalBal;
					} else {
						if (prevBal >acctTransViewVO.getDebit()) {
							balance = prevBal - acctTransViewVO.getDebit();
							totalBal = totalBal - balance;
							prevBal = totalBal;
						} else {
							balance = acctTransViewVO.getDebit() - prevBal;
							totalBal = totalBal + balance;
							prevBal = totalBal;
						}
					}
				}
				resetAcctTransViewForm();
				successResult();
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void addAcctTrans() throws BusinessException {
		try {
			if(acctTransViewVO.getCredit()==0.00)
			{
				throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_CREDIT_REQUIRED);
			}
			else
			{	
				if (null == cashBookVO.getAcctTransList())
					cashBookVO.setAcctTransList(new ArrayList<AcctTransViewVO>());
				cashBookVO.getAcctTransList().add(acctTransViewVO);
				TotalBal();
				resetAcctTransViewForm();
				successResult();
				acctTransViewVO.setCredit(cashBookVO.getCredit()-totalBal);
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void addAddTransList() throws BusinessException{
		try {
			if(acctTransViewVO.getDebit()==0.00)
			{
				throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_CREDIT_REQUIRED);
			}
			else
			{	
				acctTransListView.add(acctTransViewVO);
				addTransList.add(acctTransViewVO);
				prevBal = totalBal;
				if (acctTransListView.size() == 1) {
					
					totalBal = acctTransViewVO.getDebit();
					prevBal = totalBal;
				} else {
					
					if (acctTransViewVO.getDebit()<0.00) {
						totalBal = prevBal + acctTransViewVO.getDebit();
						prevBal = totalBal;
					} else {
						totalBal = acctTransViewVO.getDebit() + prevBal;
						prevBal = totalBal;
					}
				}
				resetAcctTransViewForm();
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void TotalBal() {
		if (cashBookVO.getAcctTransList().size() == 1) {
		
			totalBal = acctTransViewVO.getCredit();
			prevBal = totalBal;
		} else {
			
			if (acctTransViewVO.getCredit()<0.00) {
				totalBal = prevBal + acctTransViewVO.getCredit();
				prevBal = totalBal;
			} else {
				totalBal = acctTransViewVO.getCredit() + prevBal;
				prevBal = totalBal;
			}
		}
	}

	/**
	 * 
	 * @throws BusinessException
	 */
	public void addRefund() throws BusinessException {
		try {
			// check financial closed / GST submitted
			checkFinGSTClosed(CommonConstant.ACTION_CD_ADD, null, cashBookVO.getDtTrans(), invPmntAuthVO.isAccountManager());
			
			Date tempDate = cashBookVO.getDtTrans();
			
			bankAcctSearchVO = paymentBO.getBankAcctSearchList(cashBookVO.getIdBank());
			acctVO = paymentBO.getAcctSearchList(bankAcctSearchVO.getIdAcct());
			SystemNumberGenerationVO vo = new SystemNumberGenerationVO();
			vo.setCode(CommonConstant.SYS_NUM_CD_RFND);
			vo.setIdCompany(getSessionInfoBean().getCompanyVO().getId());
			sysNumGenVO = SysNumGenUtil.getSysNumber(SysNumGenUtil.getIdx(vo));
			
			// cashBookVO.setPayee(custDetailsVO.getContPersonName());
			if(cashBookVO.getCredit()!=0.00) {	
				double total = Math.round(totalBal*100.0)/100.0;	
				if (total == cashBookVO.getCredit().doubleValue()) {
					refundBO.addRefund(cashBookVO, 
							getSessionInfoBean().getCompanyVO().getId(), sysNumGenVO, sysNumGenList,
							bankAcctSearchVO, lookupItemBT, lookupItemCBT,acctVO);
					// resetForm();
					// init();
					//search();
					resetForm();
					resetFilteredObjList();
					successResult();
					
				} else throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_NOT_EQUAL);
			} else throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_CREDIT_REQUIRED);
			
			cashBookVO.setDtTrans(tempDate);
			cashBookVO.setTemp(bankAcctSearchVO.getName());
			cashBookVO.setIdBank(bankAcctSearchVO.getId());

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void onRefundSelected(CashBookVO vo) {
		try {
			
			cashBookVO = cashBookBO.getCashBookById(vo.getId()); //vo;
			setOriObj(cashBookVO.clone());
			
			//updCompTranslist = billPymtBO.getAcctTransList(cashBookVO.getSysNo(), cashBookVO.getSysCode(), getSessionInfoBean().getCompanyVO().getId());
			acctTransCashBookVO = depositBO.getAcctTransCashBook(cashBookVO.getSysNo(), cashBookVO.getTransTypeCd(), cashBookVO.getSysCode(),getSessionInfoBean().getCompanyVO().getId());
			acctTransListView = paymentBO.getAcctTransViewTableList(cashBookVO.getSysNo(), cashBookVO.getTypeCd(), cashBookVO.getSysCode(), BaseConstant.STATUS_ACTIVE,getSessionInfoBean().getCompanyVO().getId());
			bankAcctSearchVO = paymentBO.getBankAcctSearchList(cashBookVO.getIdBank());
			
			for (int i = 0; i < acctTransListView.size(); i++) {
				if (i == 0) {
					totalBal = acctTransListView.get(i).getDebit();
				} else {
					totalBal = acctTransListView.get(i).getDebit() + totalBal;
				}
				acctCodeList = paymentBO.getAcctCodeList(acctTransListView.get(i).getAcctViewVO().getId());
			}
			
			cashBookVO.setTemp(bankAcctSearchVO.getName());
			cashBookVO.setInvPymtList(depositBO.getInvoicePaymentListByCashbook(cashBookVO.getId()));
			//cashBookVO.setDateInFinPeriodClosed(LookupItemUtils.isDateInFinPeriodClosed(getSessionInfoBean().getCompanyVO().getId(), cashBookVO.getDtTrans()));
			// Check Financial Period Closed
			getFinPeriodClosedStatus();
						
			if (cashBookVO.getIdCustomer() != null) {
				CustomerVO custVO = customerBO.getCustomer(cashBookVO.getIdCustomer());
				if (custVO != null) this.cashBookVO.setCustCode(custVO.getCode());
			}
			//custDetailsVO.setContPersonName(cashBookVO.getPayee());
			/*custDetailsList = invoiceBO.getCustomerList(searchType,
					searchValueType, searchValue, getSessionInfoBean()
							.getCompanyVO().getId());
			 */
			if (cashBookVO.getTourDepId() != null) {
				TourDepartureVO tourDepVO = tourPkgBO.getTourDepById(cashBookVO.getTourDepId());
				if (tourDepVO != null) cashBookVO.setTourCd(tourDepVO.getCode());
			}
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void deleteRefund() throws BusinessException {
		try {
			// Unable to delete this record due to financial period closed.
			if (cashBookVO.isDateInFinPeriodClosed()) throw new BusinessException(CommonErrConstant.ERR_ACCT_FIN_PERIOD_CLOSED_CANNOT_DEL);
			
			//deleteRefundList();
			paymentBO.deletePaymentList(cashBookVO, getSessionInfoBean().getCompanyVO().getId());
			refundBO.delInvPmnt(cashBookVO.getId().toString(), getSessionInfoBean().getCompanyVO().getId());
			search();
			resetForm();
			resetAcctTransViewForm();
			//init();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/*private void deleteRefundList() throws BusinessException {
		try {
			acctTransSysNoList = paymentBO.getAcctTransSysNoList(cashBookVO.getSysNo(), cashBookVO.getSysCode(),getSessionInfoBean().getCompanyVO().getId());
			paymentBO.deletePaymentList(cashBookVO, acctTransSysNoList);

		} catch (Throwable t) {
			errorResult(t);
		}
	}*/

	public void delAcctTransListInEdit(AcctTransViewVO vo)
			throws BusinessException {
		try {
			acctIdVO = paymentBO.getAcctDescList(vo.getAcctViewVO().getCode(),
					vo.getAcctViewVO().getSubCode(),getSessionInfoBean().getCompanyVO().getId());
			vo.setAcctId(acctIdVO.getId());
			delTransList.add(vo);
			acctTransListView.remove(vo);
			balance = vo.getDebit();
			totalBal = totalBal - balance;
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}

	}

	public void editRefund() {
		try {
			CashBookVO oriVO = (CashBookVO) getOriObj();
			checkFinGSTClosed(CommonConstant.ACTION_CD_UPD, oriVO.getDtTrans(), cashBookVO.getDtTrans(), invPmntAuthVO.isAccountManager());
			
			double total = Math.round(totalBal*100.0)/100.0;
			if (total == cashBookVO.getCredit().doubleValue()) {
				updateRefund();
				//search();
				resetForm();
				// init();
				resetFilteredObjList();
				successResult();

			} else throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_NOT_EQUAL);
		
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	private void updateRefund() throws BusinessException {
		try {
			acctTransViewVO.setSysNo(cashBookVO.getSysNo());
			bankAcctSearchVO = paymentBO.getBankAcctSearchList(cashBookVO
					.getIdBank());
			sysGenCodeVO=sysNumGenBO.getSystemNumberGeneration(cashBookVO.getSysCode(),getSessionInfoBean()
					.getCompanyVO().getId());
			
			//CashBook update is done together with Transaction List update
			refundBO.updTransList(addTransList, updTransist, delTransList, cashBookVO,getSessionInfoBean().getCompanyVO().getId(),
					bankAcctSearchVO, acctTransCashBookVO, lookupItemCBT,
					sysGenCodeVO, acctTransListView, getSessionInfoBean().getCompanyVO().getId());
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void cancel() throws BusinessException {
		try {
			acctTransViewVO.setAcctViewVO(acctTransViewTempVO.getAcctViewVO());
			acctTransViewVO.setCredit(acctTransViewTempVO.getCredit());
			acctTransViewVO.setDesc(acctTransViewTempVO.getDesc());
			acctTransViewVO.setCode(acctTransViewTempVO.getTempAcct());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void handleAcctSelect(SelectEvent event) {
		try {
			AcctViewVO acctViewVO = (AcctViewVO) event.getObject();
			if (StringUtils.isEmpty(acctViewVO.getSubCode())) {
				acctTransViewVO.setCode(acctViewVO.getCode());
			} else {
				acctTransViewVO.setCode(acctViewVO.getCode() + "-"
						+ acctViewVO.getSubCode());
			}

			if (StringUtils.isEmpty(acctViewVO.getSubDesc())) {

				acctTransViewVO.setDesc(acctViewVO.getDesc());
			} else {

				acctTransViewVO.setDesc(acctViewVO.getDesc() + ","
						+ acctViewVO.getSubDesc());
			}

			acctTransViewVO.setAcctViewVO(acctViewVO);
			acctTransViewVO.setAcctId(acctTransViewVO.getAcctViewVO().getId());
			// acctTransViewVO.setCode(acctTransViewVO.getTempAcct());

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void search() {
		try {
			/*for(BankAcctViewVO vo : bankAcctViewList) {
				if (vo.getId().equals(bankId)) {
					cashBookVO.setIdBank(vo.getId());
				}
			}*/
			resetFilteredObjList();
			searchParamVO.setObj2(null);
			if (!searchParamVO.getObj1().toString().equalsIgnoreCase(CommonConstant.SEARCH_ALL_BANKS)) cashBookVO.setIdBank(Long.parseLong(searchParamVO.getObj1().toString()));
			cashBookRefundList = paymentBO.getBankCashBookList(CommonConstant.SYS_NUM_CD_RFND, getSessionInfoBean().getCompanyVO().getId(), searchParamVO, null);
		
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void searchAll() {
		try {
			searchParamVO.setObj2(CommonConstant.SEARCH_ALL_DATES);
			if (!searchParamVO.getObj1().toString().equalsIgnoreCase(CommonConstant.SEARCH_ALL_BANKS)) cashBookVO.setIdBank(Long.parseLong(searchParamVO.getObj1().toString()));
			cashBookRefundList = paymentBO.getBankCashBookList(CommonConstant.SYS_NUM_CD_RFND, getSessionInfoBean().getCompanyVO().getId(), searchParamVO, null);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void changeDesc() {

		for (AcctViewVO vo : acctViewList) {

			if (acctTransViewVO.getCode().toString().equalsIgnoreCase(vo.getCode().toString())) 
			{
				acctTransViewVO.setDesc(vo.getDesc());
				if(vo.getSubCode().isEmpty())
				{
					acctTransViewVO.setAcctViewVO(vo);
					acctTransViewVO.setAcctId(vo.getId());
				}

			} 
			else if (acctTransViewVO.getCode().toString().equalsIgnoreCase(vo.getCode().toString() + "-"+ vo.getSubCode().toString())) 
			{
				if(vo.getSubDesc().toString().isEmpty())
				{
					acctTransViewVO.setDesc(vo.getDesc());
					acctTransViewVO.setAcctViewVO(vo);
					acctTransViewVO.setAcctId(vo.getId());
				}
				else
				{	
					acctTransViewVO.setDesc(vo.getDesc() + "," + vo.getSubDesc());
					acctTransViewVO.setAcctViewVO(vo);
					acctTransViewVO.setAcctId(vo.getId());
				}	
			}

		}

	}
	
	public void onAddRefund() {
		try {
			Long bankId = Long.parseLong((String) searchParamVO.getObj1());
			if (!CommonConstant.SEARCH_ALL_BANKS.equals(searchParamVO.getObj1())) {
				for(BankAcctViewVO vo : bankAcctViewList) {
					if (vo.getId().equals(bankId)) {
						cashBookVO.setIdBank(vo.getId());
						cashBookVO.setTemp(vo.getName());
						break;
					}
				}
			}
			cashBookVO.setDtTrans(new Date());
			cashBookVO.setInvPymtList(new ArrayList<InvoicePaymentVO>());
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public List<String> complete(String query) {  
        List<String> results = new ArrayList<String>();   
        try { 
        	acctAutoCompleteList = paymentBO.getAcctList(getSessionInfoBean().getCompanyVO().getId(), query);
        	
        	//KS 20140319 : Prevent NULL pointer 
        	if(acctAutoCompleteList != null) {
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
        	}
        } catch(Exception e) {
        	e.printStackTrace() ;
        }
        return results;  
    }  
	
	public void handleCustSelect (SelectEvent event) {
		try {
			CustDetailsVO vo = invoiceBO.getCustDetails(((CustomerVO) event.getObject()).getId());
			if (vo.getCustType().equals("P")) {
				cashBookVO.setPayee(vo.getContPersonName());
				
			} else if (vo.getCustType().equals("C")) {
				cashBookVO.setPayee(vo.getCompanyName());
			}
			
			cashBookVO.setIdCustomer(vo.getCustId());
			cashBookVO.setCustCode(vo.getCode());
			
			resetCustomerListForm();
			this.resetFilteredObjList();
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handlePassangerClear() {
		try {
			cashBookVO.setPayee(null);
			cashBookVO.setIdCustomer(null);
			cashBookVO.setCustCode(null);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void passangerSearch() {
		try {
			setCustAdd(true);
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void passangerEditSearch() {
		try {
			setCustAdd(false);
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onAddAcctTrans(Double credit,Double debit) throws BusinessException
	{
		try{
			resetAcctTransViewForm();
			if(credit!=0) acctTransViewVO.setCredit(credit-totalBal);
			if(debit!=0) acctTransViewVO.setDebit(debit-totalBal);
			
		}catch(Throwable t){
			errorResult(t);
		}
	}
	
	public void printRF(CashBookVO cashBookVO) {
		try {
			BankAcctVO bankAcctPrintVO = paymentBO.getBankAcctSearchList(cashBookVO.getIdBank());
			
			//SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd'_'HHmmss");
			CompanyVO company = corporateProfileBO.getCompanyDetails(getSessionInfoBean().getCompanyVO().getId());
			validateEffectiveCompanyName(company, cashBookVO.getDtTrans());
			Map<String, Object> map = new HashMap<>();
			map.put("companyName", company.getName());
			map.put("companyShortName", company.getShortName());
			map.put("slogan", company.getSlogan());
			map.put("address1", company.getCompanyAddressVO().getAddress1());
			map.put("address2", company.getCompanyAddressVO().getAddress2());
			map.put("address3", company.getCompanyAddressVO().getAddress3());
			map.put("city", company.getCompanyAddressVO().getCity());
			map.put("state", company.getCompanyAddressVO().getState());
			map.put("postcode", company.getCompanyAddressVO().getPostcode());
			InvoiceBean invoice = new InvoiceBean();
			map.put("countryName", invoice.getCountryName(company.getCompanyAddressVO().getCountryid()));
			map.put("date", new SimpleDateFormat("dd/MM/yyyy").format(cashBookVO.getDtTrans()));
			map.put("voucherNo", cashBookVO.getSysNo());
			map.put("chequeNo", cashBookVO.getRefNo());
			map.put("contPersonName",cashBookVO.getPayee());
			map.put("vAmount", cashBookVO.getCredit());
			map.put("vAmountWord", EnglishNumberToWords.withCents(cashBookVO.getCredit().doubleValue()));
			map.put("memo", cashBookVO.getRemarks());
			map.put("voucherName","REFUND VOUCHER");
			map.put("bankAcctName",bankAcctPrintVO.getName());
			map.put("tourCd", cashBookVO.getTourCd());
			
			String contact = ""; 
			for (CompanyContactVO vo : company.getCompanyContactList()) {
				contact += LookupItemUtils.getLookupItemDesc(CommonConstant.LOOKUP_CAT_CD_CNTC_TYPE, vo.getTypecodecontact()) + ": " + vo.getNumber() + "  ";
			}
			map.put("contact", contact);
			JasperPrint jasperPrint = ReportUtils.getJasperPrint(map, CommonConstant.JAS_RPT_PMNT_VOU);
			ReportUtils.printReport(jasperPrint, CommonConstant.PDF_RPT_RF);
			//supplierVO=new SupplierVO();
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void printRefundReport(String xlsOrPDf) {
		try {
			List<CashBookVO> reportList = new ArrayList<CashBookVO>();
			if (CollectionUtils.isEmpty(filteredObjList)) {
				cloneCashBookRefundListForReport(reportList, cashBookRefundList);
			} else {
				cloneCashBookRefundListForReport(reportList, (List<CashBookVO>)(List<?>)filteredObjList);
			}
			HashMap<String, Object> map = reportTitle();
			map.put("fromDate", (Date) searchParamVO.getFromDate());
			map.put("toDate", (Date) searchParamVO.getToDate());
			map.put("cashBookRefundList", reportList);
			
			String jasperFileName = CommonConstant.JAS_RPT_REFUND;
			String reportName = CommonConstant.PDF_RPT_REFUND;
			
			JasperPrint jasperPrint = ReportUtils.getJasperPrint(reportList, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Prepare Report Title
	 */
	public HashMap<String, Object> reportTitle() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			CompanyVO companyVO = new CompanyVO();
			companyVO = corporateProfileBO.getCompanyDetails(getSessionInfoBean().getCompanyVO().getId());
			
			StringBuilder address = new StringBuilder();
			map.put("companyName", companyVO.getName());
			map.put("slogan", companyVO.getSlogan());
			
			if (!companyVO.getCompanyAddressVO().getAddress1().equals("")) address.append(companyVO.getCompanyAddressVO().getAddress1()).append(" ");
			if (!companyVO.getCompanyAddressVO().getAddress2().equals("")) address.append(companyVO.getCompanyAddressVO().getAddress2()).append(" ");
			if (!companyVO.getCompanyAddressVO().getAddress3().equals("")) address.append(companyVO.getCompanyAddressVO().getAddress3()).append(" ");
			if (!companyVO.getCompanyAddressVO().getCity().equals("")) address.append(companyVO.getCompanyAddressVO().getCity()).append(" ");
			if (!companyVO.getCompanyAddressVO().getState().equals("")) address.append(companyVO.getCompanyAddressVO().getState()).append(" ");
			if (!companyVO.getCompanyAddressVO().getPostcode().equals("")) address.append(companyVO.getCompanyAddressVO().getPostcode()).append(" ");
			String companyName = regionBO.getCountryById(companyVO.getCompanyAddressVO().getCountryid()).getCountry();
			if (!companyName.equals("")) address.append(companyName);
			map.put("address", address);
	
			String contact = "";
			for (CompanyContactVO vo : companyVO.getCompanyContactList()) {
				contact += LookupItemUtils.getLookupItemDesc(CommonConstant.LOOKUP_CAT_CD_CNTC_TYPE, vo.getTypecodecontact()) + ": " + vo.getNumber() + "  ";
			}
			if (!contact.equals("")) contact = "Tel: " + contact;
			map.put("contact", contact);
			
		} catch (Throwable t) {
			errorResult(t);
		}
		return map;
	}
	
	private void cloneCashBookRefundListForReport(List<CashBookVO> reportList, List<CashBookVO> listCashBookVO) {
		for(Object tmpCashBookVO: listCashBookVO) {
			CashBookVO cashBookVOCloned = (CashBookVO) ((CashBookVO) tmpCashBookVO).clone();
			cashBookVOCloned.setSysPrefix("RA-" + cashBookVOCloned.getSysNo().toString());
		
			reportList.add(cashBookVOCloned);
		}
	}
	
	public void loadInvoicePayment() {
		this.resetFilteredObjList();
		invPaymentList = (new ArrayList<InvoicePaymentVO>());
		try{
			invPaymentList = refundBO.getInvoicePaymentListNoCashbook(getSessionInfoBean().getCompanyVO().getId());
			
		} catch(Exception t) {
			t.printStackTrace();
		}
	}
	
	public void delInvPmnt(InvoicePaymentVO invoicePaymentVO) {  
		cashBookVO.getInvPymtList().remove(invoicePaymentVO); 
	}
	
	public void handleInvSelect(SelectEvent event) throws BusinessException{
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
	 * tour code selected
	 * @param event
	 */
	public void handleTourCodeSelect(SelectEvent event){
		try{
			TourDepartureVO tourDepVO = (TourDepartureVO) event.getObject();  
			
			cashBookVO.setTourDepId(tourDepVO.getId());
			cashBookVO.setTourCd(tourDepVO.getCode());
			
		}catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/*
	 * Load tour list
	 */
	public void loadTour() throws BusinessException{
//		tourList = tourPkgBO.getTourDepListV2();
		String department = getSessionInfoBean().getEmployeeVO().getDepartment();
		
		Map<String, Object> params = null;
		if (StringUtils.equals(department, MaintConstant.DEPT_TYPE_ACCT)) {
			params = new HashMap<String, Object>();
			params.put("showCancelled", true);
		}
		
		lazyDataModelTourDep = new LazyDMTourDep<>(params);
		lazyDataModelTourDep.setCompanyId(getSessionInfoBean().getCompanyVO().getId());
	}
	
	/**
	 * clear tour code
	 */
	public void clearTourCode() {
		try {
			cashBookVO.setTourDepId(null);
			cashBookVO.setTourCd(null);
		} catch (Throwable t) {
			errorResult(t);
		}
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
	 * @param balance
	 *            the balance to set
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

	public List<CashBookVO> getcashBookRefundList() {
		return cashBookRefundList;
	}

	public void setcashBookRefundList(List<CashBookVO> cashBookRefundList) {
		this.cashBookRefundList = cashBookRefundList;
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

//	public List<CustDetailsVO> getCustDetailsList() {
//		return custDetailsList;
//	}
//
//	public void setCustDetailsList(List<CustDetailsVO> custDetailsList) {
//		this.custDetailsList = custDetailsList;
//	}

	public CustDetailsVO getCustDetailsVO() {
		return custDetailsVO;
	}

	public void setCustDetailsVO(CustDetailsVO custDetailsVO) {
		this.custDetailsVO = custDetailsVO;
	}

	public CustDetailsVO getCustDetailsNameVO() {
		return custDetailsNameVO;
	}

	public void setCustDetailsNameVO(CustDetailsVO custDetailsNameVO) {
		this.custDetailsNameVO = custDetailsNameVO;
	}

	public InvoiceVO getInvoiceVO() {
		return invoiceVO;
	}

	public void setInvoiceVO(InvoiceVO invoiceVO) {
		this.invoiceVO = invoiceVO;
	}

	public List<BankAcctViewVO> getBankAcctViewList() {
		return bankAcctViewList;
	}

	public void setBankAcctViewList(List<BankAcctViewVO> bankAcctViewList) {
		this.bankAcctViewList = bankAcctViewList;
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

	/**
	 * @return the bankId
	 */
//	public Long getBankId() {
//		return bankId;
//	}
//
//	/**
//	 * @param bankId the bankId to set
//	 */
//	public void setBankId(Long bankId) {
//		this.bankId = bankId;
//	}
	
	@Override
	public String getPaginatorTemplate() {
		return ConstantWebPage.PAGINATOR_TEMPLATE;
	}

	/**
	 * @return the prefixValue
	 */
	public String getPrefixValue() {
		return prefixValue;
	}
	
	public List<InvoicePaymentVO> getInvPaymentList() {
		return invPaymentList;
	}

	public void setInvPaymentList(List<InvoicePaymentVO> invPaymentList) {
		this.invPaymentList = invPaymentList;
	}

	/**
	 * @return the tourList
	 */
	public List<TourDepartureVO> getTourList() {
		return tourList;
	}

	/**
	 * @param tourList the tourList to set
	 */
	public void setTourList(List<TourDepartureVO> tourList) {
		this.tourList = tourList;
	}
	
	public LazyDataModel<TourDepartureVO> getLazyDataModelTourDep() {
		return lazyDataModelTourDep;
	}
}
