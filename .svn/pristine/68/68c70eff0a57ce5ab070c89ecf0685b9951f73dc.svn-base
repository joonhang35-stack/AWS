package com.bcs.zsg.sales.web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.DateUtils;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.sales.bo.InvoicePaymentReportBO;
import com.bcs.zsg.sales.helper.CounterSalesReportConstant;
import com.bcs.zsg.sales.vo.InvoiceVO;

import net.sf.jasperreports.engine.JasperPrint;

public class InvoicePaymentReportBean extends CounterSalesReportBean{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	private transient RegionBO regionBO;
	@Autowired
	private transient InvoicePaymentReportBO invoicePaymentReportBO;
	
	private List<String> idRegionList;
	private List<String> idCountryList;
	
	private String salesPerson;
	private String dateRange;
	private String year;
	private int quarter;
	private Date monthYear;
	private Date dateFrom, dateTo;
	private Date paymentDateFrom, paymentDateTo;
	
	private List<String> yearList;
	
	private static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");
	private static final int YEAR_PERIOD = 11;
	
	public void resetForm() {
		dateRange = CounterSalesReportConstant.DATE_RANGE_DAILY;
		dateFrom = new Date();
		dateTo = new Date();
		paymentDateFrom = new Date();
		paymentDateTo = new Date();
		year = YEAR_FORMAT.format(new Date());
		quarter = 1;
	}
	
	public void init() {
		try {
			// Initialize search param
			initSearchParam();
			
			resetForm();
			
			loadYearList();
			loadEmployeeList();
			loadRegionList();
			loadCountryList();
			
		} catch (Throwable t) {
			errorResult(t);
		}
		
	}
	
	public void loadYearList() {
		String currentYear = YEAR_FORMAT.format(new Date());
		yearList = getCurrentYearList(currentYear);
	}
	
	public void handleDateRangeChange() {
		if (StringUtils.equals(dateRange, CounterSalesReportConstant.DATE_RANGE_DAILY)) {
			dateFrom = new Date();
			
		} else if (StringUtils.equals(dateRange, CounterSalesReportConstant.DATE_RANGE_MONTHLY)) {
			monthYear = DateUtils.getStartDateByMonth(new Date());
			
		} else if (StringUtils.equals(dateRange, CounterSalesReportConstant.DATE_RANGE_QUARTERLY)) {
			quarter = 1;
			handleQuarterChange();
			
		} else if (StringUtils.equals(dateRange, CounterSalesReportConstant.DATE_RANGE_YEARLY)) {
			year = YEAR_FORMAT.format(new Date());
			dateFrom = DateUtils.getStartDateByYear(new Date());
			dateTo = DateUtils.getEndDateByYear(new Date());
			
		}
	}
	
	public void handleYearChange() {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.valueOf(year), 0, 1);
		dateFrom = DateUtils.getStartDateByYear(cal.getTime());
		dateTo = DateUtils.getEndDateByYear(cal.getTime());
	}
	
	public void handleQuarterChange() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		if (quarter == 1) {
			cal.set(Calendar.MONTH, 0);
			dateFrom = DateUtils.getStartDateByMonth(cal.getTime());
			cal.set(Calendar.MONTH, 2);
			dateTo = DateUtils.getEndDateByMonth(cal.getTime());
			
		} else if (quarter == 2) {
			cal.set(Calendar.MONTH, 3);
			dateFrom = DateUtils.getStartDateByMonth(cal.getTime());
			cal.set(Calendar.MONTH, 5);
			dateTo = DateUtils.getEndDateByMonth(cal.getTime());
			
		} else if (quarter == 3) {
			cal.set(Calendar.MONTH, 6);
			dateFrom = DateUtils.getStartDateByMonth(cal.getTime());
			cal.set(Calendar.MONTH, 8);
			dateTo = DateUtils.getEndDateByMonth(cal.getTime());
			
		} else if (quarter == 4) {
			cal.set(Calendar.MONTH, 9);
			dateFrom = DateUtils.getStartDateByMonth(cal.getTime());
			cal.set(Calendar.MONTH, 11);
			dateTo = DateUtils.getEndDateByMonth(cal.getTime());
		}
	}
	
	private List<String> getCurrentYearList(String year) {
		List<String> yearList = new ArrayList<String>();
		
		int y = Integer.parseInt(year);
		for (int i = YEAR_PERIOD ; i > 0 ; i--) {
			yearList.add(String.valueOf(y - i));
		}
		for (int i = 0 ; i <= YEAR_PERIOD ; i++) {
			yearList.add(String.valueOf(y + i));
		}
		return yearList;
	}
	
	public void refreshDateFromTo() {
		if (StringUtils.equals(dateRange, CounterSalesReportConstant.DATE_RANGE_DAILY)) {
			dateTo = dateFrom;
			
		} else if (StringUtils.equals(dateRange, CounterSalesReportConstant.DATE_RANGE_MONTHLY)) {
			dateFrom = DateUtils.getStartDateByMonth(monthYear);
			dateTo = DateUtils.getEndDateByMonth(monthYear);
			
		} else if (StringUtils.equals(dateRange, CounterSalesReportConstant.DATE_RANGE_YEARLY)) {
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.valueOf(year), 0, 1);
			dateFrom = DateUtils.getStartDateByYear(cal.getTime());
			dateTo = DateUtils.getEndDateByYear(cal.getTime());
		}
	}
	
	public void print(String xlsOrPDf) {
		try {
			refreshDateFromTo();
			HashMap<String, Object> map = reportHeaderCompany();
			map.put("title", "Invoice/Payment Report from");
			map.put("dateFr", dateFrom);
			map.put("dateTo", dateTo);
			
			Map<String, Object> params = new HashMap<>();
			params.put("idCompany", getSessionInfoBean().getCompanyVO().getId());
			params.put("dateFrom", dateFrom);
			params.put("dateTo", dateTo);
			params.put("paymentDateFrom", paymentDateFrom);
			params.put("paymentDateTo", paymentDateTo);
			
			if (StringUtils.isNotBlank(salesPerson)) 
				params.put("salesPerson", salesPerson);
			
			if (CollectionUtils.isNotEmpty(idRegionList)) 
				params.put("idRegionList", idRegionList);
			
			if (CollectionUtils.isNotEmpty(idCountryList))
				params.put("idCountryList", idCountryList);
			
			List<InvoiceVO> invoicePaymentReportList = invoicePaymentReportBO.getInvoicePaymentReportList(getSessionInfoBean().getCompanyVO().getId(), params);
			
			if (CollectionUtils.isEmpty(invoicePaymentReportList)) throw new BusinessException("ERR_NO_RESULT");
			
			String jasperFileName = CommonConstant.JAS_RPT_INVOICE_PAYMENT;
			String reportName = CommonConstant.PDF_RPT_INVOICE_PAYMENT;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(invoicePaymentReportList, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				//Convert cell type as report (eg: '350.00 to 350.00)
				jasperPrint.setProperty("net.sf.jasperreports.export.xls.detect.cell.type", "true");
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	/**
	 * Report Header Company Detail
	 */
	public HashMap<String, Object> reportHeaderCompany() {
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
	
	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	public Date getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(Date monthYear) {
		this.monthYear = monthYear;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public List<String> getYearList() {
		return yearList;
	}

	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}
	
	public List<String> getIdRegionList() {
		return idRegionList;
	}

	public void setIdRegionList(List<String> idRegionList) {
		this.idRegionList = idRegionList;
	}

	public List<String> getIdCountryList() {
		return idCountryList;
	}

	public void setIdCountryList(List<String> idCountryList) {
		this.idCountryList = idCountryList;
	}

	public Date getPaymentDateFrom() {
		return paymentDateFrom;
	}

	public void setPaymentDateFrom(Date paymentDateFrom) {
		this.paymentDateFrom = paymentDateFrom;
	}

	public Date getPaymentDateTo() {
		return paymentDateTo;
	}

	public void setPaymentDateTo(Date paymentDateTo) {
		this.paymentDateTo = paymentDateTo;
	}
}
