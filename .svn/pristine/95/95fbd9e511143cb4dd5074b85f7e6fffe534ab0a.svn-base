package com.bcs.zsg.sales.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderViewVO;
import com.bcs.zsg.purchase.vo.ExOrderVO;
import com.bcs.zsg.sales.vo.CustDetailsVO;
import com.bcs.zsg.sales.vo.InvoiceAttachmentVO;
import com.bcs.zsg.sales.vo.InvoiceItemVO;
import com.bcs.zsg.sales.vo.InvoicePaxVO;
import com.bcs.zsg.sales.vo.InvoicePaymentAttachmentVO;
import com.bcs.zsg.sales.vo.InvoicePaymentVO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.sales.vo.InvoiceVoucherVO;
import com.bcs.zsg.sales.vo.PersonEmailVO;

public interface InvoiceDAO extends BaseDAO{

	/**
	 * 
	 * @throws BusinessException
	 */
	public List<InvoiceVO> getInvoiceList(Long companyId, String docTypeCd) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public InvoiceVO getInvoiceDetails(InvoiceVO invoiceVO) throws BusinessException;
	
	/**
	 * 
	 * @param invoiceId
	 * @throws BusinessException
	 */
	public List<InvoicePaymentVO> getInvPmntList(Long invoiceId) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public List<CustDetailsVO> getCustomerList(String searchType, String searchValueType, String searchValue, Long companyId) throws BusinessException;

	/**
	 * 
	 * @throws BusinessException
	 */
	public List<CustDetailsVO> getPassangerList(String searchType, String searchValueType, String searchValue, Long companyId) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public List<ExOrderVO> getExOrderList() throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public List<InvoiceAndExchangeOrderViewVO> getInvEOItemViewList(Long companyId, boolean acctMgr, String invoiceCatCd, String invItemCat, List<String> excludeInvItemCatList) throws BusinessException;

	/**
	 * 
	 * @param idBooking
	 * @return
	 * @throws BusinessException
	 */
	public InvoiceVO getInvoiceByBooking(Long idBooking) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public CustDetailsVO getCustDetails(Long custId) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public CustDetailsVO getCustDetails(Long custId, boolean needDetail, boolean needLangMeal) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void deleteAcctTrans(InvoiceVO invoiceVO, AcctTransVO acctTransVO) throws BusinessException;
	
	/**
	 * 
	 * @param companyId 
	 * @throws BusinessException
	 */
	public AcctTransVO getAcctTransVO(String prefixInv, String invId, String invItemId, Long companyId) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void updateBooking(Long bookingId, String pmntStaus, String status, Long salerId, Long customerId, String orderTypeCd, Long tfairSalerId) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public boolean getBookingInvExist(Long bookingId) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public InvoiceVO getInvoice(Long bookingId) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public InvoiceVO getInvoiceById(Long id) throws BusinessException ;

	/**
	 * 
	 * @throws BusinessException
	 */ 
	public List<InvoicePaxVO> getInvoicePaxListWithPkgId(Long pkgId) throws BusinessException ;

	/**
	 * 
	 * @throws BusinessException
	 */
	public void insertInvoiceHistory(Long invId, String actionCd, String reason) throws BusinessException ;

	/**
	 * 
	 * @throws BusinessException
	 */
	public void setPerSubInfo(CustDetailsVO custDetailsVO) throws BusinessException;

	/**
	 * 
	 * @throws BusinessException
	 */
	public String getInvoiceStatus(Long id) throws BusinessException;
	
	public List<InvoiceVO> getInvoiceDisplayList(Long companyId,String docTypeCd) throws BusinessException ;
	
	public List<InvoiceVO> getCreditNoteDisplayList(Long companyId) throws BusinessException ;
	
	public List<InvoiceVO> getInvoiceListSelection(Long companyId, String docTypeCd) throws BusinessException ;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public int getInvoiceListSize(Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public List<InvoiceVO> getInvoiceList(Map<String, Object> params) throws BusinessException;
	
	/**
	 * 
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public int getInvoiceHistoryListSize(Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public List<InvoiceVO> getInvoiceHistoryList(Map<String, Object> params) throws BusinessException;
	
	
	public List<InvoiceVO> getInvoiceListForPOSUpload(Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @param idInv
	 * @return
	 * @throws BusinessException
	 */
	public List<InvoiceItemVO> getInvoiceItemList(Long idInv) throws BusinessException;

	/**
	 * 
	 * @param invoiceVO
	 * @param statusCrDr
	 * @param sysCd
	 * @throws BusinessException
	 */
	public void terminateInvoiceItems(InvoiceVO invoiceVO, String sysCd) throws BusinessException;

	/**
	 * 
	 * @param idInv
	 * @throws BusinessException
	 */
	public void deleteInvoiceEOLink(Long idInv, Long idEO) throws BusinessException;

	/**
	 * 
	 * @param table
	 * @param idInv
	 * @throws BusinessException
	 */
	public void terminateInvoiceSubInfo(String table, Long idInv) throws BusinessException;

	/**
	 * 
	 * @param statusCrDr
	 * @param sysCd
	 * @param code
	 * @param companyId
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctTransVO> getAcctTransListByInv(String sysCd, String code, Long companyId) throws BusinessException;
	
	/**
	 * 
	 * @param uuid
	 * @param code
	 * @param docTypeCd
	 * @throws BusinessException
	 */
	public void updateInvoiceCode(String uuid, String code, String docTypeCd) throws BusinessException;
	public void updateInvoiceCode(String uuid, String code, String docTypeCd, String rnNo) throws BusinessException;
	
	/**
	 * 
	 * @param uuid
	 * @param code
	 * @throws BusinessException
	 */
	public void updateInvoicePmntCode(String uuid, String code) throws BusinessException;
	
	/**
	 * 
	 * @param invoiceVO
	 * @throws BusinessException
	 */
	public List<InvoiceItemVO> getInvItemList(InvoiceVO invoiceVO) throws BusinessException;
	
	/**
	 * 
	 * @param invoiceVO
	 * @throws BusinessException
	 */
	public List<InvoicePaymentVO> getInvPmntList(InvoiceVO invoiceVO) throws BusinessException;
	
	/**
	 * 
	 * @param invoiceVO
	 * @return
	 * @throws BusinessException
	 */
	public List<InvoiceVO> getInvoiceListNoCN(InvoiceVO invoiceVO) throws BusinessException;
	
	/**
	 * 
	 * @param sysCd
	 * @param code
	 * @param companyId
	 * @param typeCd
	 * @return
	 * @throws BusinessException
	 */
	public AcctTransVO getAcctTransByInv(String sysCd, String code, Long companyId, String typeCd) throws BusinessException;

	/**
	 * 
	 * @param companyId
	 * @return
	 * @throws BusinessException
	 */
	public List<EmployeeViewVO> getEmployeeViewList(Long companyId) throws BusinessException;
	
	public List<EmployeeViewVO> getFullEmployeeViewList(Long companyId) throws BusinessException;
	
	/**
	 * 
	 * @param companyId
	 * @param creditNoteNo
	 * @param creditNoId
	 * @param invPmntId
	 * @return
	 * @throws BusinessException
	 */
	public void updateInvoicePaymentRef(Long companyId, String creditNoteNo, Long creditNoId, Long invPmntId) throws BusinessException;

	/**
	 * 
	 * @param invoiceVO
	 * @throws BusinessException
	 */
	public void updateCashBookInvoices(InvoiceVO invoiceVO) throws BusinessException;
	
	/**
	 * 
	 * @param invoiceVO
	 * @return
	 * @throws BusinessException
	 */
	public InvoicePaymentVO getInvPmnt(InvoiceVO invoiceVO) throws BusinessException;

	public InvoicePaymentVO getInvPaymentById(Long id) throws BusinessException;

	public List<InvoiceAttachmentVO> getInvoiceAttachmentList(Long invId) throws BusinessException;
	
	List<InvoicePaymentAttachmentVO> getInvPmntAttachmentList(Long idInvPmnt) throws BusinessException;
	
	public String getInvPmntHistTooltip(Long invId, Long histId) throws BusinessException;
	
	public InvoiceVO getInvoiceByCode(String code) throws BusinessException ;
	
	public List<PersonEmailVO> getPersonEmailListByPersonId(Long personId) throws BusinessException;
	
	public void updateStatus(InvoiceVO invoiceVO) throws BusinessException;

	public void updateInvoiceDocTypeStatus(Long idInv, String statusCd) throws BusinessException;

	public List<InvoiceVO> getInvoiceByParentInvoiceId(Long idParentInv) throws BusinessException;

	public void updateInvoiceSubPsRunningNumber(Long idInv, Integer subPsRunningNumber) throws BusinessException;

	public InvoiceVO getInvoice(Map<String, Object> params) throws BusinessException;

	public List<InvoiceVO> getSubInvoiceList(Long idParentInv) throws BusinessException;

	public List<InvoiceVO> getInvoiceListByParams(Map<String, Object> params) throws BusinessException;

	public void updateInvoiceEInvoiceData(Long idInv, String docUuid, String submissionUid, String status)
			throws BusinessException;

	/**
	 * @param eInvoiceDocumentUuid - not null
	 * @param eInvoiceStatus
	 * @throws BusinessException
	 * @deprecated
	 */
	@Deprecated
	public void updateEInvoiceStatus(String eInvoiceDocumentUuid, String eInvoiceStatus) throws BusinessException;

	public void updateRefundNoteEInvoiceStatus(String eInvoiceDocumentUuid, String eInvoiceStatus) throws BusinessException;

	public void updateRfndNoteCode(Long id, String docTypeCd, String rnNo) throws BusinessException;

	public InvoiceVO getInvoiceByBookingId(Long bookingId) throws BusinessException;

	public Boolean isInvoiceBeforeEInvoice(Long idInv, LocalDateTime targetDateTime) throws BusinessException;

	public InvoiceVO getInvoiceByCode(String code, String docTypeCd) throws BusinessException;

	public void updatePsPaymentRef(Long companyId, String creditNoteNo, Long creditNoId, Long invPmntId)
			throws BusinessException;

	public void updateInvoiceAmount(InvoiceVO invoiceVO, Boolean updVersion, Boolean updRemarks) throws BusinessException;

	public List<InvoiceVO> getCreditNoteDisplayList(Map<String, Object> params) throws BusinessException;

	public List<InvoicePaymentVO> getSubInvPmntList(Long idParentPmnt) throws BusinessException;

	public List<InvoicePaymentVO> getChildInvPmntList(Long idParentPmnt) throws BusinessException;

	public List<InvoicePaymentVO> getInvPmntListByIdCreditNote(Long idCreditNote) throws BusinessException;

	public int getCreditNoteDisplayListSize(Map<String, Object> params) throws BusinessException;

	public List<InvoiceVO> getInvoiceList(Map<String, Object> params, Long idCompany) throws BusinessException;
	
	public List<InvoicePaxVO> getInvoicePaxListWithDepId(Map<String, Object> params) throws BusinessException;

	public List<InvoiceVoucherVO> getInvoiceVoucherList(Long idInv) throws BusinessException;
}
