package com.bcs.zsg.acctreport.web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.bo.ChartOfAcctBO;
import com.bcs.zsg.acctreport.bo.BankReportBO;
import com.bcs.zsg.bank.vo.BankAcctVO;
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
import net.sf.jasperreports.engine.JasperPrint;

public class BankReportBean extends AppBackingBean{

	private static final long serialVersionUID = 1L;
	private BankAcctVO bankAcctVO;
	private TrackingLogUtils trackingLogUtils;
	private String reportType;
	private static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");
	private List<String> yearList;
	private static final int YEAR_PERIOD = 5;
	@Autowired
	private transient BankReportBO bankReportBO;
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	private transient RegionBO regionBO;
	@Autowired
	private transient ChartOfAcctBO chartOfAcctBO;
	
	@Override
	public void resetForm() {
		bankAcctVO = new BankAcctVO();
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
	
	public void printReport(String xlsOrPDf) {
		try {
			if (reportType.equals("BA")) {
				printBankReport(xlsOrPDf);
			} 
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void printBankReport(String xlsOrPDf) {
		try {
			trackingLogUtils.startLogs();
				
			HashMap<String, Object> map = reportTitle();
			map.put("dateFrom", searchParamVO.getFromDate());
			map.put("dateTo", searchParamVO.getToDate());	
			List<BankAcctVO>bankAcctList = bankReportBO.getBankAcctList();

			if (CollectionUtils.isEmpty(bankAcctList)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			String jasperFileName = CommonConstant.JAS_RPT_BANK_ACCT;
			String reportName = CommonConstant.PDF_RPT_BANK_ACCT;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(bankAcctList, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());

		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printBankAccountReport");
		}
	}
	
	
	public void handleSelectReportType() {
		try {
			// Initialize year list
			String year = YEAR_FORMAT.format(new Date());
			setYearList(getCurrentYearList(year));
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
		} catch (Throwable t) {
			errorResult(t);
		}
	}
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
	
	public BankAcctVO getBankAcctVO() {
		return bankAcctVO;
	}

	public void setBankAcctVO(BankAcctVO bankAcctVO) {
		this.bankAcctVO = bankAcctVO;
	}

	public TrackingLogUtils getTrackingLogUtils() {
		return trackingLogUtils;
	}

	public void setTrackingLogUtils(TrackingLogUtils trackingLogUtils) {
		this.trackingLogUtils = trackingLogUtils;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public List<String> getYearList() {
		return yearList;
	}

	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}
}
