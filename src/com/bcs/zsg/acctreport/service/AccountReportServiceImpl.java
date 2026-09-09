package com.bcs.zsg.acctreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acctreport.dao.AccountReportDAO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.TourThemeVO;

public class AccountReportServiceImpl implements AccountReportService {

	@Autowired
	private transient AccountReportDAO acctRptDAO;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acctreport.service.AccountReportService#getAccountReport(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO, java.lang.String)
	 */
	@Override
	public List<?> getAccountReport(Long idCompany, SearchParamVO searchParamVO, String reportType) throws BusinessException {
		if (reportType.equals("DA")) {
			return acctRptDAO.getCustomerListing(idCompany, searchParamVO);
		} else if (reportType.equals("DB")) {
			return acctRptDAO.getCustomerPaymentHistory(idCompany, searchParamVO);
		} else if (reportType.equals("DC")) {
			return acctRptDAO.getInvSalesPersonByMonth(idCompany, searchParamVO);
		} else if (reportType.equals("DD")) {
			return acctRptDAO.getInvCountryByMonth(idCompany, searchParamVO);
		} else if (reportType.equals("DE")) {
			return acctRptDAO.getCreditNoteList(idCompany, searchParamVO);
		} else if (reportType.equals("DF")) {
			return acctRptDAO.getARDetailAgingByCustomer(idCompany, searchParamVO);
		} else if (reportType.equals("DG")) {
			return acctRptDAO.getDepositReceived(idCompany, searchParamVO);
		} else if (reportType.equals("CA")) {
			return acctRptDAO.getBillDetailBySupplier(idCompany, searchParamVO);
		} else if (reportType.equals("CC")) {
			return acctRptDAO.getBillUnpaidDetailBySupplier(idCompany, searchParamVO);
		} else if (reportType.equals("CB")) {
			return acctRptDAO.getSupplierList(idCompany, searchParamVO);
		} else if (reportType.equals("CD")) {
			return acctRptDAO.getBankPaymentByPayeeList(idCompany, searchParamVO);
		} else if (reportType.equals("CE")) {
			return acctRptDAO.getAPDetailAgingBySupplier(idCompany, searchParamVO);
		} else if (reportType.equals("CF")) {
			return acctRptDAO.getPurchasesInAdvance(idCompany, searchParamVO);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acctreport.service.AccountReportService#getTourThemeList()
	 */
	@Override
	public List<TourThemeVO> getTourThemeList() throws BusinessException {
		return acctRptDAO.getTourThemeList();
	}
}
