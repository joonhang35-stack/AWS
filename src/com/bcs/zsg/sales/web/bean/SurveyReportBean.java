package com.bcs.zsg.sales.web.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.sales.bo.SurveyReportBO;
import com.bcs.zsg.sales.vo.SurveyVO;

import net.sf.jasperreports.engine.JasperPrint;

public class SurveyReportBean extends AppBackingBean{

	private static final long serialVersionUID = 1L;
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	private transient RegionBO regionBO;
	@Autowired
	private transient SurveyReportBO surveyReportBO;
	
	public void resetForm() {
		searchParamVO = new SearchParamVO();
	}
	
	public void init() {
		try {
			resetForm();
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			// set from date
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			searchParamVO.setObj1(cal.getTime());
			// set to date
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			searchParamVO.setObj2(cal.getTime());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void print(String xlsOrPDf) {
		try {

			HashMap<String, Object> map = reportHeaderCompany();
			map.put("title", "Survey List");
			map.put("dateFr", (Date) searchParamVO.getObj1());
			map.put("dateTo", (Date) searchParamVO.getObj2());
			
			Map<String, Object> params = new HashMap<>();
			params.put("dateFrom", (Date) searchParamVO.getObj1());
			params.put("dateTo", (Date) searchParamVO.getObj2());
			
			List<SurveyVO> surveyReportList = surveyReportBO.getSurveyReportList(params);
			
			if (CollectionUtils.isEmpty(surveyReportList)) throw new BusinessException("ERR_NO_RESULT");
			
			String jasperFileName = CommonConstant.JAS_RPT_SURVEY;
			String reportName = CommonConstant.PDF_RPT_SURVEY;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(surveyReportList, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				//Convert cell type as report (eg: '350.00 to 350.00)
				jasperPrint.setProperty("net.sf.jasperreports.export.xls.detect.cell.type", "true");
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
		} catch (Throwable t) {
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
}
