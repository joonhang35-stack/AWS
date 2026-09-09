package com.bcs.zsg.bank.web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.AjaxBehaviorEvent;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.component.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.bo.ChartOfAcctBO;
import com.bcs.zsg.acct.vo.AcctBalVO;
import com.bcs.zsg.bank.bo.CashBookBO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.CashBookBalVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.DatesUtils;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class CashBookBean extends AppBackingBean {

	private static final long serialVersionUID = 1L;

	@Autowired
	private transient CashBookBO cashBookBO;
	@Autowired
	private transient ChartOfAcctBO chartOfAcctBO;
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	private transient RegionBO regionBO;
	
	private CashBookVO cashBookVO;
	private CashBookBalVO cashBookBalVO;
	private BankAcctViewVO bankAcctViewVO;
	
	private List<BankAcctViewVO> bankAcctViewList;
	private List<CashBookVO> cashBookList;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		cashBookVO = new CashBookVO();
	}

	/**
	 * Initialization
	 */
	public void init() {
		try {
			searchParamVO = new SearchParamVO();
			searchParamVO.setFromDate(DatesUtils.getDayTimeStart(new Date()).getTime());
			searchParamVO.setToDate(DatesUtils.getDayTimeEnd(new Date()).getTime());
			resetForm();
			bankAcctViewList = cashBookBO.getBankAcctList(getSessionInfoBean().getCompanyVO().getId());
			
			if(CollectionUtils.isNotEmpty(bankAcctViewList)) {
				searchParamVO.setObj1(bankAcctViewList.get(0).getId().toString());
				search();
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	/**
	 * Search
	 */
	public void search() {
		try {
			resetFilteredObjList();
			
			cashBookList = cashBookBO.getCashBookList(searchParamVO);
			cashBookBalVO = cashBookBO.getCashBookBal(searchParamVO);
			
			for (BankAcctViewVO vo : bankAcctViewList) {
				if (vo.getId().longValue() == Long.parseLong((String) searchParamVO.getObj1())) {
					bankAcctViewVO = vo;
					double beginBal = chartOfAcctBO.getBankBeginBal(vo.getId(), vo.getAcctViewVO().getId(), vo.getIdCompany(), searchParamVO);
					AcctBalVO acctBalVO = new AcctBalVO();
					acctBalVO.setDebitBeginBal((beginBal >= 0) ? beginBal : 0);
					acctBalVO.setCreditBeginBal((beginBal < 0) ? -beginBal : 0);
					bankAcctViewVO.setBeginBalVO(acctBalVO);
					
					break;
				}
			}
			
			double balance = 0;
			if (CollectionUtils.isNotEmpty(cashBookList)) {
				if (bankAcctViewVO.getBeginBalVO().getDebitBeginBal().doubleValue() > 0) balance = bankAcctViewVO.getBeginBalVO().getDebitBeginBal();
				else if (bankAcctViewVO.getBeginBalVO().getCreditBeginBal().doubleValue() > 0) balance = -bankAcctViewVO.getBeginBalVO().getCreditBeginBal();
				
				for (CashBookVO vo : cashBookList) {
					if (!BaseConstant.STATUS_TERMINATED.equals(vo.getStatusCode())) {
						/*if (vo.getDebit().doubleValue() > 0) balance += vo.getDebit();
						else if (vo.getCredit().doubleValue() > 0) balance -= vo.getCredit();*/
						balance += vo.getDebit();
						balance -= vo.getCredit();
						vo.setBalance(balance);
					}
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	/**
	 * Update cash book
	 */
	public void updCashBook() {
		try {
			cashBookBO.updCashBook(cashBookVO);
			search();
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param vo
	 */
	public void handleCashBookSelect(CashBookVO vo) {
		try {
			cashBookVO = vo;
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param flag
	 * @param vo
	 */
	public void tickBooleanCheckBox(CashBookVO vo) {
		try {
			cashBookBO.updCashBook(vo);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param statusCode
	 */
	public String getStatusStr(String statusCode) {
		if (BaseConstant.STATUS_TERMINATED.equals(statusCode)) return "Void";
		else return "";
	}
	
	/**
	 * Printing Bank Book
	 */
	public void printBankBookReport(String xlsOrPDf) {
		try {
			List<CashBookVO> reportList = new ArrayList<CashBookVO>();
			if (CollectionUtils.isEmpty(filteredObjList)) {
				cloneBankBookListForReport(reportList, cashBookList);
			} else {
				cloneBankBookListForReport(reportList, (List<CashBookVO>)(List<?>)filteredObjList);
			}
			HashMap<String, Object> map = reportTitle();
			map.put("fromDate", (Date) searchParamVO.getFromDate());
			map.put("toDate", (Date) searchParamVO.getToDate());
			map.put("acctNo", bankAcctViewVO.getAcctNo());
			map.put("bankName", bankAcctViewVO.getName());
			map.put("beginBalance", (bankAcctViewVO.getBeginBalVO().getDebitBeginBal() > 0.00 ? 
					bankAcctViewVO.getBeginBalVO().getDebitBeginBal() :
					bankAcctViewVO.getBeginBalVO().getCreditBeginBal()));
			map.put("cashBookList", reportList);
			
			String jasperFileName = CommonConstant.JAS_RPT_BANK_BOOK;
			String reportName = CommonConstant.PDF_RPT_BANK_BOOK;

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
	
	private void cloneBankBookListForReport(List<CashBookVO> reportList, List<CashBookVO> listCashBookVO) {
		for(Object tmpCashBookVO: listCashBookVO) {
			CashBookVO cashBookVOCloned = (CashBookVO) ((CashBookVO) tmpCashBookVO).clone();
			cashBookVOCloned.setSysPrefix(cashBookVOCloned.getSysPrefix() + "-" + cashBookVOCloned.getSysNo().toString());
			cashBookVOCloned.setTransTypeCd(LookupItemUtils.getLookupItemDesc("bank_tran", cashBookVOCloned.getTransTypeCd()));
			
			if (!cashBookVOCloned.getStatusCode().equals("T")) {
				reportList.add(cashBookVOCloned);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void printCashBookReport() {
		try {
			
			List<CashBookVO> reportList = new ArrayList<CashBookVO>();
			if (CollectionUtils.isEmpty(filteredObjList)) {
				cloneCashBookListForReport(reportList, cashBookList);
			} else {
				cloneCashBookListForReport(reportList, (List<CashBookVO>)(List<?>)filteredObjList);
			}
			
			SimpleDateFormat ftDate = new SimpleDateFormat("dd-MMM-yyyy");
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("bankName", bankAcctViewVO.getName());
			map.put("beginBalance", (bankAcctViewVO.getBeginBalVO().getDebitBeginBal() > 0.00 ? 
					bankAcctViewVO.getBeginBalVO().getDebitBeginBal(): bankAcctViewVO.getBeginBalVO().getCreditBeginBal()));
			map.put("dateFrom", ftDate.format(searchParamVO.getFromDate()).toString());
			map.put("dateTo", ftDate.format(searchParamVO.getToDate()).toString());
			map.put("cashBookList", reportList);
			
			JasperPrint jasperPrint = ReportUtils.getJasperPrint(reportList, map, CommonConstant.JAS_RPT_CASHBOOK_LIST);
			ReportUtils.printReport(jasperPrint, ("cashbook_" + map.get("bankName") + "_" + map.get("dateFrom") + "_" + map.get("dateTo")).replace("-", "_"));
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	private void cloneCashBookListForReport(List<CashBookVO> reportList, List<CashBookVO> listCashBookVO) {
		for(Object tmpCashBookVO: listCashBookVO) {
			CashBookVO cashBookVOCloned = (CashBookVO) ((CashBookVO) tmpCashBookVO).clone();
			cashBookVOCloned.setSysPrefix(cashBookVOCloned.getSysPrefix() + "-" + cashBookVOCloned.getSysNo().toString());
			cashBookVOCloned.setTransTypeCd(LookupItemUtils.getLookupItemDesc("bank_tran", cashBookVOCloned.getTransTypeCd()));
			
			reportList.add(cashBookVOCloned);
		}
	}
	/**
	 * 
	 * @param event
	 */
	public void onSorting(AjaxBehaviorEvent event) {
		try {
			DataTable table = (DataTable) event.getSource();
			Map<String, String> map = new HashMap<String, String>();
			map.put("sortCol", table.getSortColumn().getClientId().replace("idCashBookForm:idCashBookList:", ""));
			map.put("sortOrder", table.getSortOrder());
			searchParamVO.setObj3(map);
			search();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/
	
	/**
	 * @return the cashBookVO
	 */
	public CashBookVO getCashBookVO() {
		return cashBookVO;
	}

	/**
	 * @param cashBookVO the cashBookVO to set
	 */
	public void setCashBookVO(CashBookVO cashBookVO) {
		this.cashBookVO = cashBookVO;
	}

	/**
	 * @return the cashBookBalVO
	 */
	public CashBookBalVO getCashBookBalVO() {
		return cashBookBalVO;
	}

	/**
	 * @param cashBookBalVO the cashBookBalVO to set
	 */
	public void setCashBookBalVO(CashBookBalVO cashBookBalVO) {
		this.cashBookBalVO = cashBookBalVO;
	}

	/**
	 * @return the bankAcctViewVO
	 */
	public BankAcctViewVO getBankAcctViewVO() {
		return bankAcctViewVO;
	}

	/**
	 * @param bankAcctViewVO the bankAcctViewVO to set
	 */
	public void setBankAcctViewVO(BankAcctViewVO bankAcctViewVO) {
		this.bankAcctViewVO = bankAcctViewVO;
	}

	/**
	 * @return the bankAcctViewList
	 */
	public List<BankAcctViewVO> getBankAcctViewList() {
		return bankAcctViewList;
	}

	/**
	 * @param bankAcctViewList the bankAcctViewList to set
	 */
	public void setBankAcctViewList(List<BankAcctViewVO> bankAcctViewList) {
		this.bankAcctViewList = bankAcctViewList;
	}

	/**
	 * @return the cashBookList
	 */
	public List<CashBookVO> getCashBookList() {
		return cashBookList;
	}

	/**
	 * @param cashBookList the cashBookList to set
	 */
	public void setCashBookList(List<CashBookVO> cashBookList) {
		this.cashBookList = cashBookList;
	}

}
