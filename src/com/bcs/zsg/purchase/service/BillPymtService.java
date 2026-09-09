package com.bcs.zsg.purchase.service;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.bcs.zsg.acct.vo.AcctCatVO;
import com.bcs.zsg.acct.vo.AcctSubCatVO;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO; 
import com.bcs.zsg.purchase.vo.BillPaymentVO;
import com.bcs.zsg.purchase.vo.ExOrderBillAttachmentVO;
import com.bcs.zsg.purchase.vo.ExOrderBillPersonVO;
import com.bcs.zsg.purchase.vo.ExOrderBillVO;
import com.bcs.zsg.purchase.vo.ExOrderDetailsVO;
import com.bcs.zsg.purchase.vo.ExOrderVO;
import com.bcs.zsg.purchase.vo.PayeeViewVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.purchase.vo.SupplierVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;

public interface BillPymtService {

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderVO> getExOrderList(Long companyId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderBillVO> getBillList(Long companyId) throws BusinessException;
	
	/**
	 * 
	 * @param status 
	 * @param companyId 
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderBillVO> getBillListByStatus(Long companyId, String status) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderBillVO> getBillListById(Long supplierId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctTransVO> getGLList(Long billId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public AcctVO getGLByBank(Long acctId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderDetailsVO> getEODetailsList(Long eoId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctSubCatVO> getLiabilityList(Long liaId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctVO> getAcctList(Long expendId, Long assetId,Long purchaseId, Long companyId) throws BusinessException;
	
	/**
	 * 
	 * @param sysPrefix 
	 * @param idCompany 
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctTransVO> getAcctTransList(String sysNo, String sysCode, Long idCompany) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<BillPaymentVO> getBillPymtList(Long eoBillId) throws BusinessException;
	
	/**
	 * 
	 * @param exOrderVO 
	 * @param 
	 * @throws BusinessException
	 * @throws QueueException 
	 */
	public void insertSingleBill(ExOrderVO exOrderVO, ExOrderBillVO exOrderBillVO, AcctTransVO acctTransVO, 
		List<BillPaymentVO> billPaymentList, Long companyId, SupplierVO supplierVO) throws BusinessException, QueueException;
	
	/**
	 * 
	 * @param 
	 * @throws BusinessException
	 * @throws QueueException 
	 */
	public void updateSingleBill(Long oldExOrderID,ExOrderVO exOrderVO,ExOrderBillVO exOrderBillVO, AcctTransVO acctTransVO, List<AcctTransVO> acctTransViewList, 
		List<BillPaymentVO> billPaymentList, List<BillPaymentVO> billPaymentViewList, SupplierVO supplierVO) throws BusinessException, QueueException;
	
	/**
	 * 
	 * @param exOrderVO 
	 * @param 
	 * @throws BusinessException
	 */
	public void delBill(ExOrderVO exOrderVO, ExOrderBillVO exOrderBillVO, AcctTransVO acctTransVO, List<BillPaymentVO> billPaymentList) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public SupplierVO getSupplier(Long supplierId, Long companyId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public BankAcctVO getBankId(Long acctId) throws BusinessException;
	
	/**
	 * 
	 * @param companyId 
	 * @return
	 * @throws BusinessException
	 */
	public AcctTransVO getAcctTrans(String sysNo, Long companyId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public ExOrderVO getEOById(Long idEO, Long idCompany) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public PersonVO getPerson(Long personId) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public AcctCatVO getLiability() throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public AcctCatVO getExpenditure() throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public AcctCatVO getAsset() throws BusinessException;
	public AcctCatVO getPurchase() throws BusinessException;
	
	public AcctCatVO getIncome() ;
 

	public List<BillPaymentVO> getBillPymtbyCode(String code) ;
	public BillPaymentVO getBillPymt(Long id) ;
	public List<CashBookVO> getEOPymtList(Long eoId) ;

	public ExOrderBillVO getEOBillbyCode(String billCode) ;

	/**
	 * 
	 * @param expendId
	 * @param assetId
	 * @param assetId2 
	 * @param query
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctVO> getAcctListByAccCode(Long idCompany, Long expendId, Long assetId, String query) throws BusinessException;
	
	/**
	 * 
	 * @param expendId
	 * @param assetId
	 * @param companyId
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctVO> getAcctListByComp(Long expendId, Long assetId, Long companyId) throws BusinessException;

	/**
	 * 
	 * @param liaId
	 * @param companyId
	 * @return
	 */
	public List<AcctSubCatVO> getLiabilityList(Long liaId, Long companyId) ;

	/**
	 * 
	 * @param supplierId
	 * @param companyId
	 * @param includeInactive
	 * @return
	 */
	public SupplierVO getSupplier(Long supplierId, Long companyId, int includeInactive) ;

	/**
	 * 
	 * @param supplierId
	 * @return
	 */
	public int getSupplierInBills(Long supplierId) ;

	/**
	 * 
	 * @param companyId
	 * @return
	 */
	public List<AcctVO> getAPAccount(Long companyId) ;

	/**
	 * 
	 * @param billList
	 * @param cashBookVO
	 * @param bankPymtVO
	 * @param totalAmt
	 * @throws BusinessException
	 */
	public void billPaymentMultipleV2(List<ExOrderBillVO> billList, CashBookVO cashBookVO, SystemNumberGenerationVO bankPymtVO, double totalAmt ) throws BusinessException ;

	/**
	 * 
	 * @param idBill
	 * @return
	 * @throws BusinessException
	 */
	public ExOrderBillVO getEOBillById(Long idBill) throws BusinessException;

	/**
	 * 
	 * @param idEO
	 * @return
	 * @throws BusinessException
	 */
	public ExOrderBillVO getEOBillByEOId(Long idEO) throws BusinessException;

	/**
	 * 
	 * @param first
	 * @param pageSize
	 * @param filters
	 * @param idCompany
	 * @param idSupplier
	 * @return
	 * @throws BusinessException
	 */
	public int getBillListSize(int first, int pageSize, Map<String, String> filters, Long idCompany, Long idSupplier, Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param first
	 * @param pageSize
	 * @param sortOrder 
	 * @param sortField 
	 * @param filters
	 * @param idCompany
	 * @param idSupplier
	 * @return
	 * @throws BusinessException
	 */
	public List<ExOrderBillVO> getBillList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters, Long idCompany, Long idSupplier, Map<String, Object> params) throws BusinessException;

	/**
	 * Get total bill amount / amount paid
	 * @param idCompany
	 * @param idSupplier
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, Double> getTotalAmt(Long idCompany, Long idSupplier) throws BusinessException;

	/**
	 * 
	 * @param idCompany 
	 * @param billList
	 * @param bankPmntVO
	 * @param cashBookVO
	 * @param supplierVO 
	 * @return
	 * @throws BusinessException
	 */
	public void saveAttachPayment(Long idCompany, List<ExOrderBillVO> billList, CashBookVO bankPmntVO, CashBookVO cashBookVO, SupplierVO supplierVO) throws BusinessException;

	public void updateJournalToBill(Long idBill, Long idJournal) throws BusinessException;

	public List<ExOrderBillPersonVO> getEoBillPersonList(Long eoBillId) throws BusinessException;

	public PayeeViewVO getPayeeViewVO(Long idPayee, String payeeType) throws BusinessException;

	public void addDeleteBillAttachment(ExOrderBillAttachmentVO billAttachmentVO, boolean isDelete) throws BusinessException;

	public ExOrderBillVO getBillDetails(Long idBill) throws BusinessException;

	public boolean getBillEOExisted(Long idCompany, Long idExOrder, Long idEoBill) throws BusinessException;
}
