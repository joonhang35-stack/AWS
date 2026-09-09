package com.bcs.zsg.gst.web.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.gst.GSTReportVO;
import com.bcs.zsg.db.bterp.vo.gst.TaxCodeVO;
import com.bcs.zsg.gst.bo.GSTBO;
import com.bcs.zsg.gst.helper.GSTType;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class GSTReportBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient GSTBO gstBO;
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	private transient RegionBO regionBO;
	
	private List<TaxCodeVO> inputOutputTaxCodeList;
	private String reportType;
	private TrackingLogUtils trackingLogUtils;
	
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

	@Override
	public void resetForm() {
		inputOutputTaxCodeList = new ArrayList<TaxCodeVO>();
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
			searchParamVO.setObj2(cal.getTime());
			// set to date
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			searchParamVO.setObj3(cal.getTime());
			
			if (reportType.equals("A") || reportType.equals("C")) {
				setInputOutputTaxCodeList(gstBO.getTaxCodeList(GSTType.OUTPUT));
			} else if (reportType.equals("B") || reportType.equals("D")) {
				setInputOutputTaxCodeList(gstBO.getTaxCodeList(GSTType.INPUT));
			}
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Printing GST Output Tax Summary Report
	 */
	public void printGSTOutputTaxSummaryRpt(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
			
			HashMap<String, Object> map = reportTitle();
			map.put("fromDate", (Date) searchParamVO.getObj2());
			map.put("toDate", (Date) searchParamVO.getObj3());
			map.put("rptTitle", "GST Output Tax Summary");
			
			searchParamVO.setFromDate((Date) searchParamVO.getObj2());
			searchParamVO.setToDate((Date) searchParamVO.getObj3());
			
			List<GSTReportVO> gstOutputTaxList = gstBO.getGSTTaxList(getSessionInfoBean().getCompanyVO().getId(), searchParamVO, "OUTPUT");
			
			if (CollectionUtils.isEmpty(gstOutputTaxList)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			List<GSTReportVO> gstOutputTaxTotalList = gstBO.getGSTTaxList(getSessionInfoBean().getCompanyVO().getId(), searchParamVO, "OUTPUT_SMMRY");
			map.put("gstTaxSummaryList", gstOutputTaxTotalList);
			
			String jasperFileName = CommonConstant.JAS_RPT_GST_TAX_SMMRY;
			String reportName = CommonConstant.PDF_RPT_GST_OUTPUT_TAX_SMMRY;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(gstOutputTaxList, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(
					"cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printGSTOutputTaxSummaryRpt");
		}
	}
	
	/**
	 * Printing GST Output Tax Summary Report
	 */
	public void printGSTInputTaxSummaryRpt(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
			
			HashMap<String, Object> map = reportTitle();
			map.put("fromDate", (Date) searchParamVO.getObj2());
			map.put("toDate", (Date) searchParamVO.getObj3());
			map.put("rptTitle", "GST Input Tax Summary");
			
			searchParamVO.setFromDate((Date) searchParamVO.getObj2());
			searchParamVO.setToDate((Date) searchParamVO.getObj3());
			
			List<GSTReportVO> gstInputTaxList = gstBO.getGSTTaxList(getSessionInfoBean().getCompanyVO().getId(), searchParamVO, "INPUT");
			
			if (CollectionUtils.isEmpty(gstInputTaxList)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			List<GSTReportVO> gstInputTaxTotalList = gstBO.getGSTTaxList(getSessionInfoBean().getCompanyVO().getId(), searchParamVO, "INPUT_SMMRY");
			map.put("gstTaxSummaryList", gstInputTaxTotalList);
			
			String jasperFileName = CommonConstant.JAS_RPT_GST_TAX_SMMRY;
			String reportName = CommonConstant.PDF_RPT_GST_INPUT_TAX_SMMRY;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(gstInputTaxList, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(
					"cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printGSTInputTaxSummaryRpt");
		}
	}
	
	/**
	 * Printing GST Output Tax Summary Report
	 */
	public void printGSTOutputTaxSummaryAllRpt(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
			
			HashMap<String, Object> map = reportTitle();
			map.put("fromDate", (Date) searchParamVO.getObj2());
			map.put("toDate", (Date) searchParamVO.getObj3());
			
			searchParamVO.setFromDate((Date) searchParamVO.getObj2());
			searchParamVO.setToDate((Date) searchParamVO.getObj3());
			
			List<GSTReportVO> gstOutputTaxList = gstBO.getGSTTaxList(getSessionInfoBean().getCompanyVO().getId(), searchParamVO, "OUTPUT_ALL");
			
			if (CollectionUtils.isEmpty(gstOutputTaxList)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			String jasperFileName = CommonConstant.JAS_RPT_GST_OUTPUT_TAX_SMMRY_ALL;
			String reportName = CommonConstant.PDF_RPT_GST_OUTPUT_TAX_SMMRY_ALL;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(gstOutputTaxList, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(
					"cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printGSTOutputTaxSummaryAllRpt");
		}
	}
	
	/**
	 * Printing GST Output Tax Summary Report
	 */
	public void printGSTInputTaxSummaryAllRpt(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
			
			HashMap<String, Object> map = reportTitle();
			map.put("fromDate", (Date) searchParamVO.getObj2());
			map.put("toDate", (Date) searchParamVO.getObj3());
			
			searchParamVO.setFromDate((Date) searchParamVO.getObj2());
			searchParamVO.setToDate((Date) searchParamVO.getObj3());
			
			List<GSTReportVO> gstInputTaxList = gstBO.getGSTTaxList(getSessionInfoBean().getCompanyVO().getId(), searchParamVO, "INPUT_ALL");
			
			if (CollectionUtils.isEmpty(gstInputTaxList)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			String jasperFileName = CommonConstant.JAS_RPT_GST_INPUT_TAX_SMMRY_ALL;
			String reportName = CommonConstant.PDF_RPT_GST_INPUT_TAX_SMMRY_ALL;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(gstInputTaxList, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(
					"cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printGSTInputTaxSummaryAllRpt");
		}
	}
	
	/**
	 * Printing GST Summary for Acquisition & Input Tax
	 */
	public void printGSTSmmryAcquisitionInputTaxRpt(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
			
			HashMap<String, Object> map = reportTitle();
			map.put("fromDate", (Date) searchParamVO.getObj2());
			map.put("toDate", (Date) searchParamVO.getObj3());
			
			searchParamVO.setFromDate((Date) searchParamVO.getObj2());
			searchParamVO.setToDate((Date) searchParamVO.getObj3());
			
			List<GSTReportVO> gstSummaryList = gstBO.getGSTSmmryAcqInputTaxList(getSessionInfoBean().getCompanyVO().getId(), searchParamVO);
			
			if (CollectionUtils.isEmpty(gstSummaryList)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			String jasperFileName = CommonConstant.JAS_RPT_GST_SMMRY_ACQ_INPUT;
			String reportName = CommonConstant.PDF_RPT_GST_SMMRY_ACQ_INPUT;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(gstSummaryList, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(
					"cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printGSTSmmryAcquisitionInputTaxRpt");
		}
	}
	
	/**
	 * Printing GST Yearly Report
	 */
	public void printGSTYearlyRpt(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
			
			HashMap<String, Object> map = reportTitle();
			map.put("fromDate", (Date) searchParamVO.getObj2());
			map.put("toDate", (Date) searchParamVO.getObj3());
			
			searchParamVO.setFromDate((Date) searchParamVO.getObj2());
			searchParamVO.setToDate((Date) searchParamVO.getObj3());
			
			List<GSTReportVO> gstSummaryList = gstBO.getGSTYearlyList(getSessionInfoBean().getCompanyVO().getId(), searchParamVO);
			
			if (CollectionUtils.isEmpty(gstSummaryList)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			String jasperFileName = CommonConstant.JAS_RPT_GST_YEARLY;
			String reportName = CommonConstant.PDF_RPT_GST_YEARLY;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(gstSummaryList, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(
					"cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printGSTYearlyRpt");
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
	 * @return the inputOutputTaxCodeList
	 */
	public List<TaxCodeVO> getInputOutputTaxCodeList() {
		return inputOutputTaxCodeList;
	}

	/**
	 * @param inputOutputTaxCodeList the inputOutputTaxCodeList to set
	 */
	public void setInputOutputTaxCodeList(List<TaxCodeVO> inputOutputTaxCodeList) {
		this.inputOutputTaxCodeList = inputOutputTaxCodeList;
	}
}