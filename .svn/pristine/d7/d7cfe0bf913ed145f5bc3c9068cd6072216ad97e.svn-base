package com.bcs.zsg.sales.web.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.bo.UserBO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.helper.ConstantPOSUpload;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.product.bo.TourCatBO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.TourCatViewVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.sales.bo.InvoiceReportBO;
import com.bcs.zsg.sales.vo.InvoiceVO;

import net.sf.jasperreports.engine.JasperPrint;

public class InvoiceReportBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	protected transient InvoiceReportBO invoiceReportBO;
	@Autowired
	private transient UserBO userBO;
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	private transient RegionBO regionBO;
	@Autowired
	private transient TourCatBO tourCatBO;
	@Autowired
	private transient TourPackageBO tourPackageBO;
	
	private String reportType;
	private String invoiceType;
	private String documentType;
	
	private List<EmployeeViewVO> employeeList;
	private List<String> idOrderSourceList;
	private List<String> idRegionList;
	private List<String> idCountryList;
	private List<String> idProductList;
	private List<String> idDepartmentList;
	private List<String> idInvCatList;
	private List<TourCatViewVO> tourCatViewVOList;
	private List<TourThemeVO> tourCountryVOList;
	private List<TourThemeVO> tourThemeVOList;
	private List<TourDepartureVO> tourDepVOList;
	
	private TourDepartureVO tourDepVO;
	
	private boolean uPointOnly;
	private boolean aPointOnly;
	private boolean includeCancelled = true;
	private boolean includeVoided = true;
	
	@Override
	public void resetForm() {
		tourDepVO = new TourDepartureVO();
		documentType = "ALL";
	}

	public void init() throws BusinessException {
		try {
			// Initialize search param
			initSearchParam();
			
			loadEmployeeList();
			loadRegionList();
			loadCountryList();
			resetForm();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void init(String invoiceType) throws BusinessException {
		try {
			// Initialize search param
			initSearchParam();
			
			loadEmployeeList();
			loadRegionList();
			loadCountryList();
			resetForm();
			
			this.invoiceType = invoiceType;

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Load tour departure list
	 */
	public void loadEmployeeList() {
		try {
			employeeList = userBO.getEmployeeViewList(this.getSessionInfoBean().getCompanyVO().getId());
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/*
	 * Load tour list
	 */
	public void loadTour() throws BusinessException{
		tourDepVOList = tourPackageBO.getTourDepListV2();
	}
	
	public void handleTourCodeSelect(SelectEvent event){
		tourDepVO = (TourDepartureVO) event.getObject();
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
	
	public void handleCountryChange() {
		loadProductList();
	}
	
	/**
	 * Report Type select event
	 */
	public void handleSelectReportType() {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			// set from date
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			searchParamVO.setObj1(cal.getTime());
			if(StringUtils.equals(reportType, "A") || StringUtils.equals(reportType, "B"))
				searchParamVO.setObj3(cal.getTime());
			// set to date
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			searchParamVO.setObj2(cal.getTime());
			if(StringUtils.equals(reportType, "A") || StringUtils.equals(reportType, "B")) {
				searchParamVO.setObj4(cal.getTime());
				searchParamVO.setObj5(null);
			}
			
			if(StringUtils.equals(reportType, "C")) {
				searchParamVO.setObj1(null);
				searchParamVO.setObj2(null);
				searchParamVO.setObj3(null);
				searchParamVO.setObj4(null);
			}
			
			includeCancelled = true;
			includeVoided = true;
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Printing Customer - Invoice List By T/Code & Sales Person Report
	 */
	public void printInvoiceListARpt(String xlsOrPDf) {
		try {
			String reportType = "A";
			
			HashMap<String, Object> map = reportHeaderCompany();
			map.put("isExcel", xlsOrPDf.equals("XLS"));
			map.put("title", (StringUtils.equals(invoiceType, "1U") ? "C/N" : "INVOICE")  + " LIST BY TOUR CODE & SALES PERSON");
			map.put("invDateFr", (Date) searchParamVO.getObj1());
			map.put("invDateTo", (Date) searchParamVO.getObj2());
			map.put("depDateFr", (Date) searchParamVO.getObj3());
			map.put("depDateTo", (Date) searchParamVO.getObj4());
			map.put("salerId", (String) searchParamVO.getObj5());
			
			if (StringUtils.equals(invoiceType, "1U")) {
				searchParamVO.setObj6(ConstantPOSUpload.DOC_TYPE_CD_IN_HOUSE_1U);
				searchParamVO.setObj7("C");
			}
			
			searchParamVO.setObj9(includeCancelled);
			searchParamVO.setObj10(includeVoided);
			
			List<InvoiceVO> invoiceReportList = invoiceReportBO.getInvoiceReportList(getSessionInfoBean().getCompanyVO().getId(), reportType, searchParamVO);
			
			if (CollectionUtils.isEmpty(invoiceReportList)) throw new BusinessException("ERR_NO_RESULT");
			
			String jasperFileName = CommonConstant.JAS_RPT_INV_RPT_A;
			String reportName = CommonConstant.PDF_RPT_INV_RPT_A;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(invoiceReportList, map, jasperFileName);
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
	 * Printing Customer - Invoice List By Inv Date & T/Code & Dep Date
	 */
	public void printInvoiceListBRpt(String xlsOrPDf) {
		try {
			String reportType = "B";
			
			HashMap<String, Object> map = reportHeaderCompany();
			map.put("isExcel", xlsOrPDf.equals("XLS"));
			map.put("title", (StringUtils.equals(invoiceType, "1U") ? "C/N" : "INVOICE") + " LIST BY INVOICE DATE & TOUR CODE & DEPARTURE DATE");
			map.put("invDateFr", (Date) searchParamVO.getObj1());
			map.put("invDateTo", (Date) searchParamVO.getObj2());
			
			if (StringUtils.equals(invoiceType, "1U")) {
				searchParamVO.setObj6(ConstantPOSUpload.DOC_TYPE_CD_IN_HOUSE_1U);
				searchParamVO.setObj7("C");
			}
			
			searchParamVO.setObj9(includeCancelled);
			searchParamVO.setObj10(includeVoided);
			
			List<InvoiceVO> invoiceReportList = invoiceReportBO.getInvoiceReportList(getSessionInfoBean().getCompanyVO().getId(), reportType, searchParamVO);
			
			if (CollectionUtils.isEmpty(invoiceReportList)) throw new BusinessException("ERR_NO_RESULT");
			
			String jasperFileName = CommonConstant.JAS_RPT_INV_RPT_B;
			String reportName = CommonConstant.PDF_RPT_INV_RPT_B;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(invoiceReportList, map, jasperFileName);
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
	
	public void printSalesListingRpt(String xlsOrPDf) {
		try {
			String docTitle = "";
			if(documentType.equals("INV")) {
				docTitle = " - Invoice";
			} else if (documentType.equals("PS")) {
				docTitle = " - Pax Statement";
			}
			
			HashMap<String, Object> map = reportHeaderCompany();
			map.put("title", "Sales Listing Report" + docTitle + " With Date Range: ");
			map.put("invDateFrom", (Date) searchParamVO.getObj1());
			map.put("invDateTo", (Date) searchParamVO.getObj2());
			map.put("isExcel", xlsOrPDf.equals("XLS"));
			map.put("documentType", documentType);
			
			Map<String, Object> params = new HashMap<>();
			params.put("dateFrom", (Date) searchParamVO.getObj1());
			params.put("dateTo", (Date) searchParamVO.getObj2());
			params.put("depDateFr", (Date) searchParamVO.getObj3());
			params.put("depDateTo", (Date) searchParamVO.getObj4());
			params.put("documentType", documentType);
			
			if (searchParamVO.getObj5() != null) 
				params.put("salesPerson", (String) searchParamVO.getObj5());
			
			if (CollectionUtils.isNotEmpty(idRegionList)) 
				params.put("idRegionList", idRegionList);
			
			if (CollectionUtils.isNotEmpty(idInvCatList)) 
				params.put("idInvCatList", idInvCatList);
			
			if (CollectionUtils.isNotEmpty(idDepartmentList)) 
				params.put("idDepartmentList", idDepartmentList);
			
			if (CollectionUtils.isNotEmpty(idOrderSourceList))
				params.put("idOrderSourceList", idOrderSourceList);
			
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
			
			params.put("includeCancelled", includeCancelled);
			params.put("includeVoided", includeVoided);
			
			params.put("uPointOnly", uPointOnly);
			params.put("aPointOnly", aPointOnly);
			
			List<InvoiceVO> invoiceReportList = invoiceReportBO.getSalesReportList(tourDepVO.getId(), getSessionInfoBean().getCompanyVO().getId(), params);
			
			if (CollectionUtils.isEmpty(invoiceReportList)) throw new BusinessException("ERR_NO_RESULT");
			
			String jasperFileName = CommonConstant.JAS_RPT_SALES_LISTING;
			if (xlsOrPDf.equals("XLS")) {
				if(documentType.equals("INV") || documentType.equals("PS")) {
					jasperFileName = CommonConstant.JAS_RPT_SALES_TYPE_LISTING_XLS;
				} else {
					jasperFileName = CommonConstant.JAS_RPT_SALES_LISTING_XLS;
				}
			}
			String reportName = CommonConstant.PDF_RPT_SALES_LISTING;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(invoiceReportList, map, jasperFileName);
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
	
	public void clearTourCode() {
		tourDepVO = new TourDepartureVO();
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
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
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

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public List<String> getIdOrderSourceList() {
		return idOrderSourceList;
	}

	public void setIdOrderSourceList(List<String> idOrderSourceList) {
		this.idOrderSourceList = idOrderSourceList;
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
	
	public List<String> getIdInvCatList() {
		return idInvCatList;
	}

	public void setIdInvCatList(List<String> idInvCatList) {
		this.idInvCatList = idInvCatList;
	}

	public List<TourCatViewVO> getTourCatViewVOList() {
		return tourCatViewVOList;
	}

	public void setTourCatViewVOList(List<TourCatViewVO> tourCatViewVOList) {
		this.tourCatViewVOList = tourCatViewVOList;
	}

	public List<TourThemeVO> getTourCountryVOList() {
		return tourCountryVOList;
	}

	public void setTourCountryVOList(List<TourThemeVO> tourCountryVOList) {
		this.tourCountryVOList = tourCountryVOList;
	}

	public List<TourThemeVO> getTourThemeVOList() {
		return tourThemeVOList;
	}

	public void setTourThemeVOList(List<TourThemeVO> tourThemeVOList) {
		this.tourThemeVOList = tourThemeVOList;
	}

	public boolean isuPointOnly() {
		return uPointOnly;
	}

	public void setuPointOnly(boolean uPointOnly) {
		this.uPointOnly = uPointOnly;
	}

	public boolean isaPointOnly() {
		return aPointOnly;
	}

	public void setaPointOnly(boolean aPointOnly) {
		this.aPointOnly = aPointOnly;
	}

	public List<TourDepartureVO> getTourDepVOList() {
		return tourDepVOList;
	}

	public void setTourDepVOList(List<TourDepartureVO> tourDepVOList) {
		this.tourDepVOList = tourDepVOList;
	}

	public TourDepartureVO getTourDepVO() {
		return tourDepVO;
	}

	public void setTourDepVO(TourDepartureVO tourDepVO) {
		this.tourDepVO = tourDepVO;
	}

	public boolean isIncludeCancelled() {
		return includeCancelled;
	}

	public void setIncludeCancelled(boolean includeCancelled) {
		this.includeCancelled = includeCancelled;
	}

	public boolean isIncludeVoided() {
		return includeVoided;
	}

	public void setIncludeVoided(boolean includeVoided) {
		this.includeVoided = includeVoided;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
}
