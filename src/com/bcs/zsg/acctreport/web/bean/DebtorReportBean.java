package com.bcs.zsg.acctreport.web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.bo.ChartOfAcctBO;
import com.bcs.zsg.acct.bo.FinancialPeriodBO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.acct.vo.FinancialPeriodVO;
import com.bcs.zsg.acct.vo.JournalVO;
import com.bcs.zsg.acctreport.bo.AccountReportBO;
import com.bcs.zsg.cfg.sec.bo.UserBO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.purchase.bo.PurchaseBO;
import com.bcs.zsg.purchase.vo.SupplierVO;
import com.bcs.zsg.sales.bo.InvoiceBO;
import com.bcs.zsg.sales.vo.CustDetailsVO;
import com.bcs.zsg.sales.vo.CustomerVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class DebtorReportBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient ChartOfAcctBO chartOfAcctBO;
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	private transient RegionBO regionBO;
	@Autowired
	private transient FinancialPeriodBO finPeriodBO;
	@Autowired
	protected transient InvoiceBO invoiceBO;
	@Autowired
	protected transient PurchaseBO purchaseBO;
	@Autowired
	private transient AccountReportBO acctRptBO;
	@Autowired
	private transient TourPackageBO tourPkgBO;
	@Autowired
	private transient UserBO userBO;

	private String reportType;
	private String orderType;
	private String supplierType;
	private boolean glCodeChecked;
	private boolean ignorePagination = true;
	private List<String> yearList;
	private String[] selectedRegions;   
	private CustDetailsVO custDetailsVO;
	private TourDepartureVO tourDepVO;
	protected SupplierVO supplierVO;
	private List<SupplierVO> supplierList;
	private List<JournalVO> journalList;
	private List<TourDepartureVO> tourList;
	private List<EmployeeViewVO> employeeList;
	private List<TourThemeVO> tourThemeList;
	private static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");
	private static final int YEAR_PERIOD = 11;
	private TrackingLogUtils trackingLogUtils;

	@Override
	public void resetForm() {
		custDetailsVO = new CustDetailsVO();
		supplierVO = new SupplierVO();
		supplierList = new ArrayList<SupplierVO>();
		journalList = new ArrayList<JournalVO>();
		// bankAcctViewList = new ArrayList<BankAcctViewVO>();
	}

	public void init() throws BusinessException {
		try {
			trackingLogUtils = new TrackingLogUtils(this.getClass());

			// Initialize search param
			initSearchParam();

			resetForm();
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Load tour departure list
	 */
	public void loadEmployeeList() {
		try {
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Report Type select event
	 */
	public void handleSelectReportType() {
		try {
			// Initialize year list
			String year = YEAR_FORMAT.format(new Date());
			yearList = getCurrentYearList(year);
			String periodYear = chartOfAcctBO.getFinPeriodYear(getSessionInfoBean().getCompanyVO().getId());
			
			if (StringUtils.isNotEmpty(periodYear)) {
				searchParamVO.setObj1(periodYear);
			} else {
				searchParamVO.setObj1(year);
			}
			
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			// set from date
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			searchParamVO.setObj2(cal.getTime());  // Invoice Date From
			// set to date
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			searchParamVO.setObj3(cal.getTime());  // Invoice Date To

			List<FinancialPeriodVO> finPeriodList = finPeriodBO.getFinPeriodList(getSessionInfoBean().getCompanyVO().getId(), (String) searchParamVO.getObj1());
			
			if (reportType.equals("DC") ||
				reportType.equals("DD") ||
				reportType.equals("DE") ||
				reportType.equals("DF") ||
				reportType.equals("DG")) {
				if (reportType.equals("DG")) {
					searchParamVO.setObj4(null);
					searchParamVO.setObj5(null);
				}
			} else if (reportType.equals("DB")) {
				searchParamVO.setObj4(searchParamVO.getObj2()); // Departure Date From
				searchParamVO.setObj5(searchParamVO.getObj3()); // Departure Date To
			} else {
				if (CollectionUtils.isNotEmpty(finPeriodList)) {
					searchParamVO.setObj2(finPeriodList.get(0).getDtStart());
					searchParamVO.setObj3(finPeriodList.get(finPeriodList.size() - 1).getDtEnd());
				} else {
					searchParamVO.setObj2(null);
					searchParamVO.setObj3(null);
				}
			}
			
			if (reportType.equals("DC")) {
				employeeList = userBO.getEmployeeViewList(this.getSessionInfoBean().getCompanyVO().getId());
			}
			
			if (reportType.equals("DD")) {
				tourThemeList = acctRptBO.getTourThemeList();
			}
			

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Report Type select event
	 */
	public void handleSelectYear() {
		try {
			List<FinancialPeriodVO> finPeriodList = finPeriodBO.getFinPeriodList(getSessionInfoBean().getCompanyVO().getId(), (String) searchParamVO.getObj1());
			
			if (CollectionUtils.isNotEmpty(finPeriodList)) {
				searchParamVO.setObj2(finPeriodList.get(0).getDtStart());
				searchParamVO.setObj3(finPeriodList.get(finPeriodList.size() - 1).getDtEnd());
			} else {
				searchParamVO.setObj2(null);
				searchParamVO.setObj3(null);
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Printing Report
	 */
	public void printReport(String xlsOrPDf) {
		try {
			if (reportType.equals("DA")) {
				printCustomerListing(xlsOrPDf);
			} else if (reportType.equals("DB")) {
				printCustomerPaymentHistory(xlsOrPDf);
			} else if (reportType.equals("DC")) {
				printInvSalesPersonByMonth(xlsOrPDf);
			} else if (reportType.equals("DD")) {
				printInvCountryByMonth(xlsOrPDf);
			} else if (reportType.equals("DE")) {
				printCreditNoteList(xlsOrPDf);
			} else if (reportType.equals("DF")) {
				printARDetailAgingByCustomer(xlsOrPDf);
			} else if (reportType.equals("DG")) {
				printDepositReceived(xlsOrPDf);
			}

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/*************************************************************************************************************************************
	 * Debtor Report Print *
	 *************************************************************************************************************************************/
	/**
	 * Printing Customer Listing Report
	 */
	public void printCustomerListing(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
			
			HashMap<String, Object> map = reportTitle();
			map.put("fromDate", (Date) searchParamVO.getObj2());
			map.put("toDate", (Date) searchParamVO.getObj3());
			
			String reportTitle = "", reportSubName = "";
			if (orderType.equalsIgnoreCase("CoN")) {
				reportTitle = "COMPANY NAME";
				reportSubName = "CompanyName";
			} else if (orderType.equalsIgnoreCase("CuN")) {
				reportTitle = "CUSTOMER NAME";
				reportSubName = "CustomerName";
			} else {
				reportTitle = "CUSTOMER CODE";
				reportSubName = "CustomerCode";
			}
			map.put("sorting", reportTitle);

			if (custDetailsVO.getCustId() != null) {
				searchParamVO.setObj4(custDetailsVO.getCustId());
			} else {
				searchParamVO.setObj4(null);
			}

			// Order type
			searchParamVO.setObj7(orderType);

			List<CustomerVO> list = (List<CustomerVO>) acctRptBO.getAccountReport(getSessionInfoBean().getCompanyVO().getId(), searchParamVO, reportType);

			if (CollectionUtils.isEmpty(list)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			String jasperFileName = CommonConstant.JAS_RPT_CUST_LIST_BY_SORTING;
			String reportName = CommonConstant.PDF_RPT_CUST_LIST_BY_SORTING.replace("[SORTING]", reportSubName);

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(list, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());

		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printCustomerListingByCompanyNmRpt");
		}
	}
	
	/**
	 * Printing Customer Payment History
	 */
	public void printCustomerPaymentHistory(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
			
			HashMap<String, Object> map = reportTitle();
			map.put("IS_IGNORE_PAGINATION", ignorePagination);
			map.put("invDateFr", (Date) searchParamVO.getObj2());
			map.put("invDateTo", (Date) searchParamVO.getObj3());
			map.put("depDateFr", (Date) searchParamVO.getObj4());
			map.put("depDateTo", (Date) searchParamVO.getObj5());

			if (custDetailsVO.getCustId() != null) {
				searchParamVO.setObj6(custDetailsVO.getCustId());
			} else {
				searchParamVO.setObj6(null);
			}

			List<InvoiceVO> list = (List<InvoiceVO>) acctRptBO.getAccountReport(getSessionInfoBean().getCompanyVO().getId(), searchParamVO, reportType);

			if (CollectionUtils.isEmpty(list)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			String jasperFileName = CommonConstant.JAS_RPT_CUST_PMNT_HIST;
			String reportName = CommonConstant.PDF_RPT_CUST_PMNT_HIST;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(list, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printCustomerPaymentHistory");
		}
	}
	
	/**
	 * Printing Invoice Salesperson Analysis by Month
	 */
	public void printInvSalesPersonByMonth(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
			
			HashMap<String, Object> map = reportTitle();
			map.put("invDateFr", (Date) searchParamVO.getObj2());
			map.put("invDateTo", (Date) searchParamVO.getObj3());

			if (tourDepVO != null) {
				searchParamVO.setObj6(tourDepVO.getId());
			} else {
				searchParamVO.setObj6(null);
			}

			List<InvoiceVO> list = (List<InvoiceVO>) acctRptBO.getAccountReport(getSessionInfoBean().getCompanyVO().getId(), searchParamVO, reportType);

			if (CollectionUtils.isEmpty(list)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			String jasperFileName = CommonConstant.JAS_RPT_INV_SLSPRSN_MONTH;
			String reportName = CommonConstant.PDF_RPT_INV_SLSPRSN_MONTH;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(list, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());

		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printInvSalesPersonByMonth");
		}
	}
	
	/**
	 * Printing Invoice Country Analysis by Month
	 */
	public void printInvCountryByMonth(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
			
			HashMap<String, Object> map = reportTitle();
			map.put("invDateFr", (Date) searchParamVO.getObj2());
			map.put("invDateTo", (Date) searchParamVO.getObj3());

			if (selectedRegions.length > 0) {
				searchParamVO.setObj6(StringUtils.join(selectedRegions, ","));
			} else {
				searchParamVO.setObj6(null);
			}

			List<InvoiceVO> list = (List<InvoiceVO>) acctRptBO.getAccountReport(getSessionInfoBean().getCompanyVO().getId(), searchParamVO, reportType);

			if (CollectionUtils.isEmpty(list)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			String jasperFileName = CommonConstant.JAS_RPT_INV_COUNTRY_MONTH;
			String reportName = CommonConstant.PDF_RPT_INV_COUNTRY_MONTH;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(list, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());

		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printInvCountryByMonth");
		}
	}
	
	/**
	 * Printing Credit Note List
	 */
	public void printCreditNoteList(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
			
			HashMap<String, Object> map = reportTitle();
			map.put("invDateFr", (Date) searchParamVO.getObj2());
			map.put("invDateTo", (Date) searchParamVO.getObj3());

			List<InvoiceVO> list = (List<InvoiceVO>) acctRptBO.getAccountReport(getSessionInfoBean().getCompanyVO().getId(), searchParamVO, reportType);

			if (CollectionUtils.isEmpty(list)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			String jasperFileName = CommonConstant.JAS_RPT_CN_LIST;
			String reportName = CommonConstant.PDF_RPT_CN_LIST;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(list, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());

		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printCreditNoteList");
		}
	}
	
	/**
	 * Printing A/R Details Aging By Customer
	 */
	public void printARDetailAgingByCustomer(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
			
			HashMap<String, Object> map = reportTitle();
			map.put("toDate", (Date) searchParamVO.getObj3());

			if (custDetailsVO.getCustId() != null) {
				searchParamVO.setObj4(custDetailsVO.getCustId());
			} else {
				searchParamVO.setObj4(null);
			}
			
			List<InvoiceVO> list = (List<InvoiceVO>) acctRptBO.getAccountReport(getSessionInfoBean().getCompanyVO().getId(), searchParamVO, reportType);

			if (CollectionUtils.isEmpty(list)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			String jasperFileName = CommonConstant.JAS_RPT_AR_DETAIL_AGING_BY_CUST;
			String reportName = CommonConstant.PDF_RPT_AR_DETAIL_AGING_BY_CUST;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(list, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else if (xlsOrPDf.equals("CSV")) {
//				ReportUtils.printReportCSV(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printAPDetailAgingByCustomer");
		}
	}
	
	/**
	 * Printing Deposit Received
	 */
	public void printDepositReceived(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
			
			HashMap<String, Object> map = reportTitle();
			map.put("IS_IGNORE_PAGINATION", ignorePagination);
			map.put("fromDate", (Date) searchParamVO.getObj2());
			map.put("toDate", (Date) searchParamVO.getObj3());

			if (custDetailsVO.getCustId() != null) {
				searchParamVO.setObj6(custDetailsVO.getCustId());
			} else {
				searchParamVO.setObj6(null);
			}
			
			List<AcctViewVO> list = (List<AcctViewVO>) acctRptBO.getAccountReport(getSessionInfoBean().getCompanyVO().getId(), searchParamVO, reportType);
			
			if (CollectionUtils.isEmpty(list)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			/*String custCd = "";
			BigDecimal balanceDue = new BigDecimal("0.00");
			BigDecimal ttlbalanceDue = new BigDecimal("0.00");
			for (AcctViewVO vo : list) {
				if (!custCd.equals(vo.getCustCd())) {
					balanceDue = new BigDecimal("0.00");
					balanceDue = balanceDue.add(new BigDecimal(new DecimalFormat("#0.00").format(vo.getBalance()))).
						add(new BigDecimal(new DecimalFormat("#0.00").format(vo.getBalanceDue())));
					ttlbalanceDue = ttlbalanceDue.add(new BigDecimal(new DecimalFormat("#0.00").format(vo.getBalance()))).
							add(new BigDecimal(new DecimalFormat("#0.00").format(vo.getBalanceDue())));
				} else {
					balanceDue = balanceDue.add(new BigDecimal(new DecimalFormat("#0.00").format(vo.getBalanceDue())));
					ttlbalanceDue = ttlbalanceDue.add(new BigDecimal(new DecimalFormat("#0.00").format(vo.getBalanceDue())));
				}
				custCd = vo.getCustCd();
			}
			map.put("ttlbalanceDue", ttlbalanceDue.doubleValue());*/
			
			String jasperFileName = CommonConstant.JAS_RPT_DEP_RECEIVED;
			String reportName = CommonConstant.PDF_RPT_DEP_RECEIVED;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(list, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());

		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printDepositReceived");
		}
	}
	
	/**
	 * On selected customer details
	 */
	public void handleCustSelect(SelectEvent event) {
		try {
			custDetailsVO = invoiceBO.getCustDetails(((CustomerVO) event.getObject()).getId());

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Clear customer details
	 */
	public void custClear() {
		try {
			custDetailsVO = new CustDetailsVO();

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/*
	 * Load tour list
	 */
	public void loadTour() throws BusinessException{
		tourList = tourPkgBO.getTourDepListV2();
	}
	
	/**
	 * tour code selected
	 * @param event
	 */
	public void handleTourCodeSelect(SelectEvent event){
		try{
			tourDepVO = (TourDepartureVO) event.getObject();  
			
		}catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Clear tour code details
	 */
	public void clearTourCode() {
		try {
			tourDepVO = new TourDepartureVO();

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
			companyVO = corporateProfileBO
					.getCompanyDetails(getSessionInfoBean().getCompanyVO()
							.getId());

			StringBuilder address = new StringBuilder();
			map.put("companyName", companyVO.getName());
			map.put("slogan", companyVO.getSlogan());

			if (!companyVO.getCompanyAddressVO().getAddress1().equals(""))
				address.append(companyVO.getCompanyAddressVO().getAddress1())
						.append(" ");
			if (!companyVO.getCompanyAddressVO().getAddress2().equals(""))
				address.append(companyVO.getCompanyAddressVO().getAddress2())
						.append(" ");
			if (!companyVO.getCompanyAddressVO().getAddress3().equals(""))
				address.append(companyVO.getCompanyAddressVO().getAddress3())
						.append(" ");
			if (!companyVO.getCompanyAddressVO().getCity().equals(""))
				address.append(companyVO.getCompanyAddressVO().getCity())
						.append(" ");
			if (!companyVO.getCompanyAddressVO().getState().equals(""))
				address.append(companyVO.getCompanyAddressVO().getState())
						.append(" ");
			if (!companyVO.getCompanyAddressVO().getPostcode().equals(""))
				address.append(companyVO.getCompanyAddressVO().getPostcode())
						.append(" ");
			String companyName = regionBO.getCountryById(
					companyVO.getCompanyAddressVO().getCountryid())
					.getCountry();
			if (!companyName.equals(""))
				address.append(companyName);
			map.put("address", address);

			String contact = "";
			for (CompanyContactVO vo : companyVO.getCompanyContactList()) {
				contact += LookupItemUtils.getLookupItemDesc(
						CommonConstant.LOOKUP_CAT_CD_CNTC_TYPE,
						vo.getTypecodecontact())
						+ ": " + vo.getNumber() + "  ";
			}
			if (!contact.equals(""))
				contact = "Tel: " + contact;
			map.put("contact", contact);

		} catch (Throwable t) {
			errorResult(t);
		}
		return map;
	}

	/**
	 * Get and Validation Financial Period
	 */
	public void getFinancialPeriod() throws BusinessException {
		List<FinancialPeriodVO> finPeriodList = finPeriodBO.getFinPeriodList(
				getSessionInfoBean().getCompanyVO().getId(),
				(String) searchParamVO.getObj1());

		if (CollectionUtils.isEmpty(finPeriodList))
			throw new BusinessException(
					CommonErrConstant.ERR_ACCT_CHART_NOT_FIN_PERIOD);

		Date startDate = finPeriodList.get(0).getDtStart();
		Date endDate = finPeriodList.get(finPeriodList.size() - 1).getDtEnd();

		Date fromDateSlted = (Date) searchParamVO.getObj2();
		Date toDateSlted = (Date) searchParamVO.getObj3();

		searchParamVO.setFromDate(startDate);
		searchParamVO.setToDate(endDate);

		if (!chartOfAcctBO.isAcctBeginBalExisted(getSessionInfoBean()
				.getCompanyVO().getId(), searchParamVO)) {
			throw new BusinessException(
					CommonErrConstant.ERR_ACCT_CHART_NOT_BEGIN_BAL);
		}

		if (fromDateSlted.after(toDateSlted)) {
			throw new BusinessException(
					CommonErrConstant.ERR_ACCT_CHART_DT_GREATER);
		} else if (fromDateSlted.before(startDate)) {
			throw new BusinessException(
					CommonErrConstant.ERR_ACCT_CHART_DT_FR_NOT_IN_RANGE);
		} else if (fromDateSlted.after(endDate)) {
			throw new BusinessException(
					CommonErrConstant.ERR_ACCT_CHART_DT_FR_NOT_IN_RANGE);
		} else if (toDateSlted.before(startDate)) {
			throw new BusinessException(
					CommonErrConstant.ERR_ACCT_CHART_DT_TO_NOT_IN_RANGE);
		} else if (toDateSlted.after(endDate)) {
			throw new BusinessException(
					CommonErrConstant.ERR_ACCT_CHART_DT_TO_NOT_IN_RANGE);
		}

		// return startDate;
	}

	/**********
	 * HELPER *
	 **********/

	/**
	 * 
	 * @param year
	 * @param date
	 * @return
	 */
	private List<String> getCurrentYearList(String year) {
		yearList = new ArrayList<String>();

		int y = Integer.parseInt(year);
		for (int i = YEAR_PERIOD; i > 0; i--) {
			yearList.add(String.valueOf(y - i));
		}
		for (int i = 0; i <= YEAR_PERIOD; i++) {
			yearList.add(String.valueOf(y + i));
		}
		return yearList;
	}

	/*******************
	 * Getter & Setter *
	 *******************/

	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType
	 *            the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the yearList
	 */
	public List<String> getYearList() {
		return yearList;
	}

	/**
	 * @param yearList
	 *            the yearList to set
	 */
	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}

	/**
	 * @return the custDetailsVO
	 */
	public CustDetailsVO getCustDetailsVO() {
		return custDetailsVO;
	}

	/**
	 * @param custDetailsVO
	 *            the custDetailsVO to set
	 */
	public void setCustDetailsVO(CustDetailsVO custDetailsVO) {
		this.custDetailsVO = custDetailsVO;
	}

	/**
	 * @return the supplierVO
	 */
	public SupplierVO getSupplierVO() {
		return supplierVO;
	}

	/**
	 * @param supplierVO
	 *            the supplierVO to set
	 */
	public void setSupplierVO(SupplierVO supplierVO) {
		this.supplierVO = supplierVO;
	}

	/**
	 * @return the supplierList
	 */
	public List<SupplierVO> getSupplierList() {
		return supplierList;
	}

	/**
	 * @param supplierList
	 *            the supplierList to set
	 */
	public void setSupplierList(List<SupplierVO> supplierList) {
		this.supplierList = supplierList;
	}

	/**
	 * @return the journalList
	 */
	public List<JournalVO> getJournalList() {
		return journalList;
	}

	/**
	 * @param journalList
	 *            the journalList to set
	 */
	public void setJournalList(List<JournalVO> journalList) {
		this.journalList = journalList;
	}

	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * @param orderType
	 *            the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
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

	/**
	 * @return the glCodeChecked
	 */
	public boolean isGlCodeChecked() {
		return glCodeChecked;
	}

	/**
	 * @param glCodeChecked the glCodeChecked to set
	 */
	public void setGlCodeChecked(boolean glCodeChecked) {
		this.glCodeChecked = glCodeChecked;
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
	 * @return the selectedRegions
	 */
	public String[] getSelectedRegions() {
		return selectedRegions;
	}

	/**
	 * @param selectedRegions the selectedRegions to set
	 */
	public void setSelectedRegions(String[] selectedRegions) {
		this.selectedRegions = selectedRegions;
	}

	/**
	 * @return the tourThemeList
	 */
	public List<TourThemeVO> getTourThemeList() {
		return tourThemeList;
	}

	/**
	 * @param tourThemeList the tourThemeList to set
	 */
	public void setTourThemeList(List<TourThemeVO> tourThemeList) {
		this.tourThemeList = tourThemeList;
	}

	/**
	 * @return the supplierType
	 */
	public String getSupplierType() {
		return supplierType;
	}

	/**
	 * @param supplierType the supplierType to set
	 */
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public boolean isIgnorePagination() {
		return ignorePagination;
	}

	public void setIgnorePagination(boolean ignorePagination) {
		this.ignorePagination = ignorePagination;
	}
}
