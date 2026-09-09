package com.bcs.zsg.purchase.bo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.vo.AcctCatVO;
import com.bcs.zsg.acct.vo.AcctSubCatVO;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.purchase.service.BillPymtService;
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

public class BillPymtBOImpl implements BillPymtBO {

	@Autowired
	private BillPymtService billPaymentService;
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public List<ExOrderVO> getExOrderList(Long companyId) throws BusinessException {
		return billPaymentService.getExOrderList(companyId);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public List<ExOrderBillVO> getBillList(Long companyId) throws BusinessException {
		return billPaymentService.getBillList(companyId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getBillListByStatus(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<ExOrderBillVO> getBillListByStatus(Long companyId, String status) throws BusinessException {
		return billPaymentService.getBillListByStatus(companyId, status);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public List<ExOrderBillVO> getBillListById(Long supplierId) throws BusinessException {
		return billPaymentService.getBillListById(supplierId);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public List<AcctTransVO> getGLList(Long billId) throws BusinessException {
		return billPaymentService.getGLList(billId);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public AcctVO getGLByBank(Long acctId) throws BusinessException {
		return billPaymentService.getGLByBank(acctId);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public List<ExOrderDetailsVO> getEODetailsList(Long eoId) throws BusinessException {
		return billPaymentService.getEODetailsList(eoId);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public List<AcctSubCatVO> getLiabilityList(Long liaId) throws BusinessException {
		return billPaymentService.getLiabilityList(liaId);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public List<AcctVO> getAcctList(Long expendId, Long assetId,Long purchaseId, Long companyId) throws BusinessException {
		return billPaymentService.getAcctList(expendId, assetId,purchaseId, companyId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getAcctTransList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<AcctTransVO> getAcctTransList(String sysNo, String sysCode, Long idCompany) throws BusinessException {
		return billPaymentService.getAcctTransList(sysNo, sysCode, idCompany);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public List<BillPaymentVO> getBillPymtList(Long eoBillId) throws BusinessException {
		return billPaymentService.getBillPymtList(eoBillId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPaymentBO#insert
	 */
	@Override
	public void insertSingleBill(ExOrderVO exOrderVO, ExOrderBillVO exOrderBillVO, AcctTransVO acctTransVO, 
		List<BillPaymentVO> billPaymentList, Long companyId, SupplierVO supplierVO) throws BusinessException, QueueException {
		checkTaxCodeGroup(exOrderBillVO, acctTransVO);
		billPaymentService.insertSingleBill(exOrderVO, exOrderBillVO, acctTransVO, billPaymentList, companyId, supplierVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPaymentBO#update
	 */
	@Override
	public void updateSingleBill(Long oldExOrderID,ExOrderVO exOrderVO,ExOrderBillVO exOrderBillVO, AcctTransVO acctTransVO, List<AcctTransVO> acctTransViewList, 
		List<BillPaymentVO> billPaymentList, List<BillPaymentVO> billPaymentViewList, SupplierVO supplierVO) throws BusinessException, QueueException {
		checkTaxCodeGroup(exOrderBillVO, acctTransVO);
		billPaymentService.updateSingleBill(oldExOrderID,exOrderVO,exOrderBillVO, acctTransVO, acctTransViewList, billPaymentList, billPaymentViewList, supplierVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPaymentBO#delete
	 */
	@Override
	public void delBill(ExOrderVO exOrderVO, ExOrderBillVO exOrderBillVO, AcctTransVO acctTransVO, List<BillPaymentVO> billPaymentList) throws BusinessException {
		billPaymentService.delBill(exOrderVO, exOrderBillVO, acctTransVO, billPaymentList);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public SupplierVO getSupplier(Long supplierId, Long companyId) throws BusinessException {
		return billPaymentService.getSupplier(supplierId, companyId);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public BankAcctVO getBankId(Long acctId) throws BusinessException {
		return billPaymentService.getBankId(acctId);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public AcctTransVO getAcctTrans(String sysNo, Long companyId) throws BusinessException {
		return billPaymentService.getAcctTrans(sysNo, companyId);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public ExOrderVO getEOById(Long idEO, Long idCompany) throws BusinessException {
		return billPaymentService.getEOById(idEO,idCompany);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public PersonVO getPerson(Long personId) throws BusinessException {
		return billPaymentService.getPerson(personId);
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public AcctCatVO getLiability() throws BusinessException {
		return billPaymentService.getLiability();
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public AcctCatVO getExpenditure() throws BusinessException {
		return billPaymentService.getExpenditure();
	}
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public AcctCatVO getAsset() throws BusinessException {
		return billPaymentService.getAsset();
	}

	public AcctCatVO getIncome() {
		return billPaymentService.getIncome();
	}
	public AcctCatVO getPurchase() throws BusinessException {
		return billPaymentService.getPurchase();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getBillPymtbyCode(java.lang.String)
	 */
	@Override
	public List<BillPaymentVO> getBillPymtbyCode(String code) {
		return billPaymentService.getBillPymtbyCode(code);

	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getBillPymt(java.lang.Long)
	 */
	@Override
	public BillPaymentVO getBillPymt(Long id) {
		return billPaymentService.getBillPymt(id);

	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getEOPymtList(java.lang.Long)
	 */
	@Override
	public List<CashBookVO> getEOPymtList(Long eoId) {
		return billPaymentService.getEOPymtList(eoId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getEOBillbyCode(java.lang.String)
	 */
	@Override
	public ExOrderBillVO getEOBillbyCode(String billCode) {
		return billPaymentService.getEOBillbyCode(billCode);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getAcctListByAccCode(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@Override
	public List<AcctVO> getAcctListByAccCode(Long idCompany, Long expendId, Long assetId, String query) throws BusinessException {
		return billPaymentService.getAcctListByAccCode(idCompany, expendId, assetId, query);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getAcctListByComp(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<AcctVO> getAcctListByComp(Long expendId, Long assetId, Long companyId) throws BusinessException {
		return billPaymentService.getAcctListByComp(expendId, assetId, companyId);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getLiabilityList(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<AcctSubCatVO> getLiabilityList(Long liaId, Long companyId) {
		return billPaymentService.getLiabilityList(liaId, companyId); 
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getSupplier(java.lang.Long, java.lang.Long, int)
	 */
	@Override
	public SupplierVO getSupplier(Long supplierId, Long companyId, int includeInactive) {
		return billPaymentService.getSupplier(supplierId, companyId, includeInactive);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getSupplierInBills(java.lang.Long)
	 */
	@Override
	public int getSupplierInBills(Long supplierId) {
		return billPaymentService.getSupplierInBills(  supplierId); 
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getAPAccount(java.lang.Long)
	 */
	@Override
	public List<AcctVO> getAPAccount(Long companyId) {
		return billPaymentService.getAPAccount(companyId);  
	}
	
	@Override
	public void billPaymentMultipleV2(List<ExOrderBillVO> billList, CashBookVO cashBookVO, SystemNumberGenerationVO bankPymtVO, double totalAmt ) throws BusinessException {
		  billPaymentService.billPaymentMultipleV2(billList, cashBookVO, bankPymtVO, totalAmt);  
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getEOBillById(java.lang.Long)
	 */
	@Override
	public ExOrderBillVO getEOBillById(Long idBill) throws BusinessException {
		return billPaymentService.getEOBillById(idBill);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getEOBillByEOId(java.lang.Long)
	 */
	@Override
	public ExOrderBillVO getEOBillByEOId(Long idEO) throws BusinessException {
		return billPaymentService.getEOBillByEOId(idEO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getBillListSize(int, int, java.util.Map, java.lang.Long, java.lang.Long)
	 */
	@Override
	public int getBillListSize(int first, int pageSize, Map<String, String> filters, Long idCompany, Long idSupplier, Map<String, Object> params) throws BusinessException {
		return billPaymentService.getBillListSize(first, pageSize, filters, idCompany, idSupplier, params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getBillList(int, int, java.util.Map, java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<ExOrderBillVO> getBillList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters, Long idCompany, Long idSupplier, Map<String, Object> params) throws BusinessException {
		return billPaymentService.getBillList(first, pageSize, sortField, sortOrder, filters, idCompany, idSupplier, params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#getTotalAmt(java.lang.Long, java.lang.Long)
	 */
	@Override
	public Map<String, Double> getTotalAmt(Long idCompany, Long idSupplier) throws BusinessException {
		return billPaymentService.getTotalAmt(idCompany, idSupplier);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.bo.BillPymtBO#saveAttachPayment(java.lang.Long, java.util.List, com.bcs.zsg.bank.vo.CashBookVO, com.bcs.zsg.bank.vo.CashBookVO, com.bcs.zsg.purchase.vo.SupplierVO)
	 */
	@Override
	public void saveAttachPayment(Long idCompany, List<ExOrderBillVO> billList, CashBookVO bankPmntVO, CashBookVO cashBookVO, SupplierVO supplierVO) throws BusinessException {
		billPaymentService.saveAttachPayment(idCompany, billList, bankPmntVO, cashBookVO, supplierVO);
	}
	
	/**
	 * Check whether tax code missing or not
	 * @param exOrderBillVO
	 * @param acctTransVO
	 * @throws BusinessException
	 */
	private void checkTaxCodeGroup(ExOrderBillVO exOrderBillVO, AcctTransVO acctTransVO) throws BusinessException {
		// check account trans missing tax code
		int flag = 0;
		for (AcctTransVO vo : acctTransVO.getAcctTransList()) {
			if (StringUtils.isEmpty(vo.getTaxCode())) {
				flag = 1;
				break;
			}
		}
		if (flag == 0) exOrderBillVO.setTaxCodeGroup(CommonConstant.TAX_CODE_GROUP_Y);
		else exOrderBillVO.setTaxCodeGroup(CommonConstant.TAX_CODE_GROUP_N);
	}
	
	@Override
	public void updateJournalToBill(Long idBill, Long idJournal) throws BusinessException {
		billPaymentService.updateJournalToBill(idBill, idJournal);
	}

	@Override
	public List<ExOrderBillPersonVO> getEoBillPersonList(Long eoBillId) throws BusinessException {
		return billPaymentService.getEoBillPersonList(eoBillId);
	}
	
	@Override
	public PayeeViewVO getPayeeViewVO(Long idPayee, String payeeType) throws BusinessException {
		return billPaymentService.getPayeeViewVO(idPayee, payeeType);
	}
	
	@Override
	public void addDeleteBillAttachment(ExOrderBillAttachmentVO billAttachmentVO, boolean isDelete) throws BusinessException {
		billPaymentService.addDeleteBillAttachment(billAttachmentVO, isDelete);
	}
	
	@Override
	public ExOrderBillVO getBillDetails(Long idBill) throws BusinessException {
		return billPaymentService.getBillDetails(idBill);
	}
	
	@Override
	public boolean getBillEOExisted(Long idCompany, Long idExOrder, Long idEoBill) throws BusinessException {
		return billPaymentService.getBillEOExisted(idCompany, idExOrder, idEoBill);
	}
	
}
