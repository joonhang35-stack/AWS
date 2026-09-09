package com.bcs.zsg.sales.web.bean;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.bo.UserBO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.DateUtils;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.product.bo.TourCatBO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.TourCatViewVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.sales.bo.CounterSalesReportBO;
import com.bcs.zsg.sales.helper.CounterSalesReportConstant;
import com.bcs.zsg.sales.vo.CounterSalesReportVO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public class CounterSalesReportBean extends AppBackingBean {
	
	private static final long serialVersionUID = 1L;
	
	private List<String> yearList;
	private List<String> idRegionList;
	private List<String> idCountryList;
	private List<String> idProductList;
	private List<String> idDepartmentList;
	
	protected List<TourCatViewVO> tourCatViewVOList;
	protected List<TourThemeVO> tourCountryVOList;
	private List<TourThemeVO> tourThemeVOList;
	private List<CounterSalesReportVO> counterSalesReportVOList;
	protected List<EmployeeViewVO> employeeList;
	
	private String reportType;
	private String salesPerson;
	private String dateRange;
	private String year;
	private int quarter;
	private Date monthYear;
	private Date dateFrom, dateTo;
	
	private static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");
	private static final int YEAR_PERIOD = 11;
	
	@Autowired
	private transient TourCatBO tourCatBO;
	@Autowired
	private transient TourPackageBO tourPackageBO;
	@Autowired
	private transient CounterSalesReportBO counterSalesReportBO;
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	private transient RegionBO regionBO;
	@Autowired
	private transient UserBO userBO;

	@Override
	public void resetForm() {
		dateRange = CounterSalesReportConstant.DATE_RANGE_DAILY;
		dateFrom = new Date();
		dateTo = new Date();
		reportType = CounterSalesReportConstant.REPORT_TYPE_INDIVIDUAL_SALES;
		year = YEAR_FORMAT.format(new Date());
		quarter = 1;
	}
	
	public void init() {
		resetForm();
		
		loadYearList();
		loadRegionList();
		loadCountryList();
		loadEmployeeList();
	}
	
	public void loadYearList() {
		String currentYear = YEAR_FORMAT.format(new Date());
		yearList = getCurrentYearList(currentYear);
	}
	
	public void loadRegionList() {
		try {
			searchParamVO = new SearchParamVO();
			searchParamVO.setObj1(ProductConstant.TYPE_TOUR);
			tourCatViewVOList = tourCatBO.getTourCatViewList(searchParamVO);
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void loadCountryList() {
		try {
			SearchParamVO searchParamVO = new SearchParamVO();
			searchParamVO.setObj3("seqNo");
			tourCountryVOList = tourPackageBO.getTourThemeList(searchParamVO, null);
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void loadProductList() {
		try {
			tourThemeVOList = new ArrayList<>();
			
			for (String country : idCountryList) {
				tourThemeVOList.addAll(tourPackageBO.getTourThemeList(new SearchParamVO(), Long.valueOf(country)));
			}
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void loadEmployeeList() {
		try {
			employeeList = userBO.getEmployeeViewList(this.getSessionInfoBean().getCompanyVO().getId());
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void search() {
		try {
			refreshDateFromTo();
			
			Map<String, Object> params = new HashMap<>();
			params.put("dateFrom", dateFrom);
			params.put("dateTo", dateTo);
			counterSalesReportVOList = counterSalesReportBO.getCounterSalesReportList(params);
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void print(String printType) {
		try {
			refreshDateFromTo();
			
			Map<String, Object> params = new HashMap<>();
			params.put("dateFrom", dateFrom);
			params.put("dateTo", dateTo);
			
			if (StringUtils.isNotBlank(salesPerson)) 
				params.put("salesPerson", salesPerson);
			
			if (CollectionUtils.isNotEmpty(idRegionList)) 
				params.put("idRegionList", idRegionList);
			
			if (CollectionUtils.isNotEmpty(idDepartmentList)) 
				params.put("idDepartmentList", idDepartmentList);
			
			// Get product list -START-
			List<String> idTourThemeList = new ArrayList<>();
			if (CollectionUtils.isEmpty(idProductList)) {
				if (CollectionUtils.isNotEmpty(tourThemeVOList) && CollectionUtils.isNotEmpty(idCountryList)) {
					for (TourThemeVO tourThemeVO : tourThemeVOList) {
						idTourThemeList.add(tourThemeVO.getId().toString());
					}
				}
			} else {
				idTourThemeList.addAll(idProductList);
			}
			
			if (CollectionUtils.isNotEmpty(idTourThemeList))
				params.put("idTourThemeList", idTourThemeList);
			// Get product list -END-
			
			if (StringUtils.equals(reportType, CounterSalesReportConstant.REPORT_TYPE_REGION_SALES))
				params.put("orderByRegion", true);
			else if (StringUtils.equals(reportType, CounterSalesReportConstant.REPORT_TYPE_INDIVIDUAL_SALES))
				params.put("orderBySalesPerson", true);
			else if (StringUtils.equals(reportType, CounterSalesReportConstant.REPORT_TYPE_DEPARTMENT_SALES))
				params.put("orderByDepartment", true);
			
			counterSalesReportVOList = counterSalesReportBO.getCounterSalesReportList(params);
			
			if (CollectionUtils.isEmpty(counterSalesReportVOList)) throw new BusinessException("ERR_NO_RESULT");
			
			String jasperFileName = "";
			String reportName = "";
			
			if (StringUtils.equals(reportType, CounterSalesReportConstant.REPORT_TYPE_REGION_SALES)) {
				jasperFileName = CommonConstant.JAS_RPT_COUNTER_SALES_REGION;
				reportName = CommonConstant.PDF_RPT_COUNTER_SALES_REGION;
				
			} else if (StringUtils.equals(reportType, CounterSalesReportConstant.REPORT_TYPE_INDIVIDUAL_SALES)) {
				jasperFileName = CommonConstant.JAS_RPT_COUNTER_SALES_SALES_PERSON;
				reportName = CommonConstant.PDF_RPT_COUNTER_SALES_SALES_PERSON;
				
			} else if (StringUtils.equals(reportType, CounterSalesReportConstant.REPORT_TYPE_DEPARTMENT_SALES)) {
				jasperFileName = CommonConstant.JAS_RPT_COUNTER_SALES_DEPARTMENT;
				reportName = CommonConstant.PDF_RPT_COUNTER_SALES_DEPARTMENT;
				
			} else {
				jasperFileName = CommonConstant.JAS_RPT_COUNTER_SALES;
				reportName = CommonConstant.PDF_RPT_COUNTER_SALES;
				
			}
			
			HashMap<String, Object> map = reportHeaderCompany();
			map.put("invDateFrom", dateFrom);
			map.put("invDateTo", dateTo);

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(counterSalesReportVOList, map, jasperFileName);
			if (printType.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				//Convert cell type as report (eg: '350.00 to 350.00)
				jasperPrint.setProperty("net.sf.jasperreports.export.xls.detect.cell.type", "true");
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(
					"cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());
		} catch (BusinessException | JRException | IOException e) {
			errorResult(e);
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
	
	public void handleCountryChange() {
		loadProductList();
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

	public List<String> getYearList() {
		return yearList;
	}

	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
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

	public List<TourThemeVO> getTourThemeVOList() {
		return tourThemeVOList;
	}

	public void setTourThemeVOList(List<TourThemeVO> tourThemeVOList) {
		this.tourThemeVOList = tourThemeVOList;
	}

	public List<TourThemeVO> getTourCountryVOList() {
		return tourCountryVOList;
	}

	public void setTourCountryVOList(List<TourThemeVO> tourCountryVOList) {
		this.tourCountryVOList = tourCountryVOList;
	}

	public List<CounterSalesReportVO> getCounterSalesReportVOList() {
		return counterSalesReportVOList;
	}

	public void setCounterSalesReportVOList(List<CounterSalesReportVO> counterSalesReportVOList) {
		this.counterSalesReportVOList = counterSalesReportVOList;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public List<EmployeeViewVO> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeViewVO> employeeList) {
		this.employeeList = employeeList;
	}

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public List<TourCatViewVO> getTourCatViewVOList() {
		return tourCatViewVOList;
	}

	public void setTourCatViewVOList(List<TourCatViewVO> tourCatViewVOList) {
		this.tourCatViewVOList = tourCatViewVOList;
	}

	public List<String> getIdCountryList() {
		return idCountryList;
	}

	public void setIdCountryList(List<String> idCountryList) {
		this.idCountryList = idCountryList;
	}

	public List<String> getIdProductList() {
		return idProductList;
	}

	public void setIdProductList(List<String> idProductList) {
		this.idProductList = idProductList;
	}

	public List<String> getIdDepartmentList() {
		return idDepartmentList;
	}

	public void setIdDepartmentList(List<String> idDepartmentList) {
		this.idDepartmentList = idDepartmentList;
	}

	public List<String> getIdRegionList() {
		return idRegionList;
	}

	public void setIdRegionList(List<String> idRegionList) {
		this.idRegionList = idRegionList;
	}
}
