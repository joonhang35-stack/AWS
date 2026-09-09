package com.bcs.zsg.acctreport.dao;

import java.util.List;

import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.purchase.vo.ExOrderBillVO;
import com.bcs.zsg.purchase.vo.SupplierVO;
import com.bcs.zsg.sales.vo.CustomerVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public interface AccountReportDAO extends BaseDAO {

	/**
	 * Get Customer Listing By Sorting (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<CustomerVO> getCustomerListing(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Customer Payment History (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<InvoiceVO> getCustomerPaymentHistory(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Invoice Salesperson Analysis by Month (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<InvoiceVO> getInvSalesPersonByMonth(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Invoice Country Analysis by Month (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<InvoiceVO> getInvCountryByMonth(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Credit Note List (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<InvoiceVO> getCreditNoteList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Bill Detail By Supplier (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderBillVO> getBillDetailBySupplier(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Bill Unpaid Detail By Supplier (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderBillVO> getBillUnpaidDetailBySupplier(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Supplier By Sorting (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<SupplierVO> getSupplierList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * Get Region List
	 * @return
	 * @throws BusinessException
	 */
	public List<TourThemeVO> getTourThemeList() throws BusinessException;
	
	/**
	 * Get Bank Payment By Payee (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<CashBookVO> getBankPaymentByPayeeList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get A/P Detail Aging By Supplier (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderBillVO> getAPDetailAgingBySupplier(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Purchases In Advance (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderBillVO> getPurchasesInAdvance(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get A/R Detail Aging By Customer (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<InvoiceVO> getARDetailAgingByCustomer(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * Get Deposit Received (Report)
	 * @param idCompany
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctViewVO> getDepositReceived(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
}