package com.bcs.zsg.purchase.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.dao.ChartOfAcctDAO;
import com.bcs.zsg.acct.helper.AccountHelper;
import com.bcs.zsg.acct.service.AccountService;
import com.bcs.zsg.acct.vo.AcctCatVO;
import com.bcs.zsg.acct.vo.AcctSubCatVO;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.EInvoiceConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.db.bterp.dao.acct.AccountDAO;
import com.bcs.zsg.db.bterp.dao.accttrans.AccountTransDAO;
import com.bcs.zsg.db.bterp.dao.view.supplier.SupplierViewDAO;
import com.bcs.zsg.maintenance.dao.SystemNumberGenerationDAO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.product.dao.TourPackageDAO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.purchase.dao.BillPymtDAO;
import com.bcs.zsg.purchase.dao.PurchaseEODAO;
import com.bcs.zsg.purchase.vo.BillPaymentVO;
import com.bcs.zsg.purchase.vo.CashBookEOBillVO;
import com.bcs.zsg.purchase.vo.ExOrderBillAttachmentVO;
import com.bcs.zsg.purchase.vo.ExOrderBillPersonVO;
import com.bcs.zsg.purchase.vo.ExOrderBillVO;
import com.bcs.zsg.purchase.vo.ExOrderDetailsVO;
import com.bcs.zsg.purchase.vo.ExOrderVO;
import com.bcs.zsg.purchase.vo.PayeeViewVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.purchase.vo.SupplierVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;
import com.bcs.zsg.zextra.backend.helper.SysNumGenUtil;

public class BillPymtServiceImpl implements BillPymtService {
	
	@Autowired
	private BillPymtDAO billPymtDAO;
	@Autowired
	private PurchaseEODAO purchaseEODAO;
	@Autowired
	private SupplierViewDAO supplierViewDAO;
	@Autowired
	private AccountDAO acctDAO;
	@Autowired
	private AccountTransDAO acctTransDAO;
	
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private TourPackageDAO tourPkgDAO;
	@Autowired
	private SystemNumberGenerationDAO sysNumGenDAO;
	@Autowired
	private ChartOfAcctDAO chartOfAcctDAO;
	
	@Autowired
	private AccountService accountService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getExOrderList()
	 */
	@Override
	public List<ExOrderVO> getExOrderList(Long companyId) throws BusinessException {
		List<ExOrderVO> exOrderList = billPymtDAO.getExOrderList(companyId);
		List<SupplierVO> supplierList = purchaseService.getSupplierList(companyId, false);
		
		for(int i = 0; i < exOrderList.size(); i++) {
			
			for(int j = 0; j < supplierList.size(); j++) {
				if(exOrderList.get(i).getSupplierId().equals(supplierList.get(j).getId())){
					exOrderList.get(i).setSuppFullName(supplierList.get(j).getFullName());
					exOrderList.get(i).setContactPerson(supplierList.get(j).getPersonVO().getLastName() + " " + supplierList.get(j).getPersonVO().getGivenName());
				}
			}
		}
		
		return exOrderList;
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getExOrderList()
	 */
	@Override
	public List<ExOrderBillVO> getBillList(Long companyId) throws BusinessException {
		return billPymtDAO.getBillList(companyId);
	}
	
	/**
	 * 
	 */
	@Override
	public List<ExOrderBillVO> getBillListByStatus(Long companyId, String status) throws BusinessException {
		return billPymtDAO.getBillListByStatus(companyId, status);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getExOrderList()
	 */
	@Override
	public List<ExOrderBillVO> getBillListById(Long supplierId) throws BusinessException {
		List<ExOrderBillVO> exOrderBillList = billPymtDAO.getBillListById(supplierId);
		List<ExOrderBillVO> delBillList = new ArrayList<ExOrderBillVO>();
		delBillList.addAll(exOrderBillList);
		
		for(ExOrderBillVO billVO : delBillList){
			double balance = 0.00;
			
			balance = billVO.getBillAmt() - billVO.getAmtPaid();
			if(balance == 0.0){
				exOrderBillList.remove(billVO);
			}
		}
		return exOrderBillList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getExOrderList()
	 */
	@Override
	public List<AcctTransVO> getGLList(Long billId) throws BusinessException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sys_no", billId);
		
		return acctTransDAO.getAccountTransList(params);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getExOrderList()
	 */
	@Override
	public AcctVO getGLByBank(Long acctId) throws BusinessException {
		return acctDAO.getAcctVO(acctId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getEODetailsList()
	 */
	@Override
	public List<ExOrderDetailsVO> getEODetailsList(Long eoId) throws BusinessException {
		return billPymtDAO.getEODetailsList(eoId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getExOrderList()
	 */
	@Override
	public List<AcctSubCatVO> getLiabilityList(Long liaId) throws BusinessException {
		return billPymtDAO.getLiabilityList(liaId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getExOrderList()
	 */
	@Override
	public List<AcctVO> getAcctList(Long expendId, Long assetId, Long purchaseId, Long companyId) throws BusinessException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idCompany", companyId);
		params.put("sortField", "code");
		params.put("sortOrder", SortOrder.ASCENDING);
		params.put("getAcctCatCols", true);
		params.put("getCreateUpdateCols", true);
		return acctDAO.getAcctList(params);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getAcctTransList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<AcctTransVO> getAcctTransList(String sysNo, String sysCode, Long idCompany) throws BusinessException {
		return billPymtDAO.getAcctTransList(sysNo, sysCode, idCompany);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getAcctTransList()
	 */
	@Override
	public List<BillPaymentVO> getBillPymtList(Long eoBillId) throws BusinessException {
		/*List<BillPaymentVO> billPymtList = billPymtDAO.getBillPymtList(eoBillId);
		
		for (BillPaymentVO vo : billPymtList) {
			BankAcctViewVO bankVO = billPymtDAO.getBankAcct(vo.getBankId());
			vo.setBankName(bankVO.getName());
			vo.setAmount(vo.getPmntAmount());
			vo.setBankAcct(bankVO.getAcctViewVO().getCode());
			//if (StringUtils.isNotEmpty(bankVO.getAcctViewVO().getSubCode())) vo.setBankAcct(vo.getBankAcct() + "-" + bankVO.getAcctViewVO().getSubCode());
		}
		return billPymtList;*/
		return billPymtDAO.getBillPymtList(eoBillId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#insert()
	 */
	@Override
	public void insertSingleBill(ExOrderVO exOrderVO, ExOrderBillVO exOrderBillVO, AcctTransVO acctTransVO, 
			List<BillPaymentVO> billPaymentList, Long companyId, SupplierVO supplierVO) throws BusinessException, QueueException {
		
		if (CommonConstant.DEF_ACCT_TYPE_SC.equalsIgnoreCase(exOrderBillVO.getSupplierType()) &&
				!StringUtils.isEmpty(exOrderBillVO.getCnNumber()) && billPymtDAO.isCNUsed(exOrderBillVO, companyId)) {
			throw new BusinessException(CommonErrConstant.ERR_BILL_PMNT_CN_USED);
		}
		
		//Double amtPaid = 0.00;
		
		SystemNumberGenerationVO purchaseBillSysNo = new SystemNumberGenerationVO();
		purchaseBillSysNo.setCode(CommonConstant.SYS_NUM_CD_PURC_BILL);
		purchaseBillSysNo.setIdCompany(acctTransVO.getCompanyId());
		purchaseBillSysNo = SysNumGenUtil.getSysNumber(SysNumGenUtil.getIdx(purchaseBillSysNo));
		
		Long eoBillSysNo = purchaseBillSysNo.getNextnumber();
		exOrderBillVO.setCode(eoBillSysNo.toString());
		exOrderBillVO.seteInvoiceStatus(EInvoiceConstant.E_INV_STATUS_NOT_SUBMIT);
		
		/*if (CollectionUtils.isNotEmpty(billPaymentList)) {
			for (BillPaymentVO vo : billPaymentList) {
				amtPaid += vo.getPmntAmount();
			}
		}
		exOrderBillVO.setAmtPaid(amtPaid);*/
		exOrderBillVO.setCompanyId(companyId);
		billPymtDAO.insert(exOrderBillVO);
		if (exOrderVO.getId() != null) billPymtDAO.update(exOrderVO);
		
		AcctVO acctVO = acctDAO.getAccountLiabilityById(acctTransVO.getAcctId());
		addEditCommonGLCreditor(acctTransVO, acctVO, exOrderVO, exOrderBillVO, purchaseBillSysNo, supplierVO);
		accountService.auditAcctTrans(CommonConstant.ACTION_CD_ADD, acctTransVO);
		
		if(CollectionUtils.isNotEmpty(acctTransVO.getAcctTransList())) {
			for (AcctTransVO vo : acctTransVO.getAcctTransList()) {
				addEditBillAccountTrans(CommonConstant.ACTION_CD_ADD, vo, acctTransVO, true);
			}
		}
		// rounding adjustment and gst account
		AccountHelper.roundingAndTaxUpdateBill(exOrderBillVO.getAmountCalcViewVO(), acctTransVO, false);
		// payment
		addEditBillPayemnt(billPaymentList, exOrderBillVO, acctVO, acctTransVO);
	}
	
	private String updateSalesPurchaseDestinationDescription(ExOrderBillVO exOrderBillVO, SupplierVO supplierVO) {
		//for Trade Creditors FORMAT :<Supplier Name> <Supplier No><Invoice Prefix>-<Invoice No 1>,<Invoice No 2>,<Invoice No 3>
		//for Sundry Creditors FORMAT:<Supplier Name> <Supplier No>Inv-<Invoice/Credit Note No>
		
		String source = exOrderBillVO.getSupplierName() + " " + supplierVO.getSysNo();
		if (StringUtils.equals(exOrderBillVO.getSupplierType(), "Trade Creditors")) {
			//Currently default all to Sundry FORMAT
			source += " Inv-" + exOrderBillVO.getCnNumber();
		} else {
			source += " Inv-" + exOrderBillVO.getCnNumber();
		}
		return source;
	}
	
	private String updateSalesPurchaseSourceDescription(ExOrderVO exOrderVO, ExOrderBillVO exOrderBillVO) {
		//for Trade Creditors FORMAT :<Bill Prefix>-<Bill Id> EO-<EO No 1>,<EO No 2>,<EO No 3><Reason for Expense>
		//for Sundry Creditors FORMAT:<Bill Prefix>-<Bill Id> <Reason for Expense>
		
		String source = "SB-" + exOrderBillVO.getCode();
		if (StringUtils.equals(exOrderBillVO.getSupplierType(), "Trade Creditors")) {
			if (exOrderBillVO.getEoId() != null) {
				source += " EO-" + exOrderVO.getCode();
			}
		}
		
		if (StringUtils.isNotBlank(exOrderBillVO.getPayeeDisplayName(true))) {
			source += " " + exOrderBillVO.getPayeeDisplayName(true);
		}
		
		source += " " + exOrderBillVO.getExReason();
		return source;
	}
	
	private String genBankPaymentSourceDescription(String bankPaymentNo, String bankName) {
		//FORMAT: <Bank Payment Prefix>-<Bank Payment No>-<Bank Name>
		return "BP-" + bankPaymentNo + " " + bankName;
	}
	
	private String genBankPaymentDestinationDescription(CashBookVO vo) {
		return genBankPaymentDestinationDescription(vo.getRemarks(), vo.getPayee());
	}
	private String genBankPaymentDestinationDescription(BillPaymentVO vo) {
		return genBankPaymentDestinationDescription(vo.getRemarks(), vo.getPayee());
	}
	private String genBankPaymentDestinationDescription(String memo, String payee) {
		//FORMAT: <MEMO> Pay- <PAYEE>
		return memo + " Pay-" + payee;
	}
	
	private void addEditBillPayemnt(List<BillPaymentVO> billPaymentList, ExOrderBillVO exOrderBillVO,  AcctVO acctVO, AcctTransVO acctTransVO) 
			throws BusinessException, QueueException {
		
		if (CollectionUtils.isNotEmpty(billPaymentList)) {
			CashBookVO cashBookVO = null;
			AcctTransVO creditPymtVO = null, debitSuppVO = null;
			SystemNumberGenerationVO bankPymtVO = null;
			
			for (BillPaymentVO vo : billPaymentList) {
				// add new payment
				if (vo.getId() == null) {
					bankPymtVO = new SystemNumberGenerationVO();
					bankPymtVO.setCode(CommonConstant.SYS_NUM_CD_BANK_PMNT);
					bankPymtVO.setIdCompany(acctTransVO.getCompanyId());
					bankPymtVO = SysNumGenUtil.getSysNumber(SysNumGenUtil.getIdx(bankPymtVO));
					
					// insert bill payment
					vo.setEoBillId(exOrderBillVO.getId());
					vo.setCode(bankPymtVO.getNextnumber().toString());
					vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
					vo.setChqAmount(vo.getPmntAmount());
					vo.setPmntType(CommonConstant.BILL_PMNT_TYPE_SGL);
					billPymtDAO.insert(vo);
					
					// insert cash book
					cashBookVO = genBillPaymentCashBook(null, 0.00, vo, bankPymtVO);
					billPymtDAO.insert(cashBookVO);
					
					// insert into acct_trans, credit user account
					creditPymtVO = genCommonBillPaymentCreditVO(acctTransVO, cashBookVO, vo, bankPymtVO, cashBookVO.getCredit());
					accountService.auditAcctTrans(CommonConstant.ACTION_CD_ADD, creditPymtVO);
					
					// insert into acct_trans, debit supplier
					debitSuppVO = genCommonPaymentSupplierDebitVO(acctTransVO, cashBookVO, vo, bankPymtVO, acctVO, 
																	creditPymtVO, cashBookVO.getCredit(), acctTransVO.getAcctId());
					accountService.auditAcctTrans(CommonConstant.ACTION_CD_ADD, debitSuppVO);
					
				// update existing payment
				} else {
					cashBookVO = billPymtDAO.getCashBook(exOrderBillVO.getCompanyId(), vo.getCode());
					cashBookVO.setIdBank(vo.getBankId());
					cashBookVO.setPayee(vo.getPayee());
					cashBookVO.setRefNo(vo.getRefNum());
					cashBookVO.setRemarks(vo.getRemarks());
					cashBookVO.setDtTrans(vo.getPymtDt());
					if (CommonConstant.BILL_PMNT_TYPE_SGL.equals(vo.getPmntType())) cashBookVO.setCredit(vo.getPmntAmount());
					
					//UPDATE for all Payment Section Creditor, Debtor
					List<AcctTransVO> acctTransList = getAcctTransList(cashBookVO.getSysNo(), cashBookVO.getSysCode(), exOrderBillVO.getCompanyId());
					if (CollectionUtils.isNotEmpty(acctTransList)) {
						for (AcctTransVO paymentAcctTransVO : acctTransList) {
							paymentAcctTransVO.setTransDt(vo.getPymtDt());
							paymentAcctTransVO.setRefNo(vo.getRefNum());
							paymentAcctTransVO.setSource(genBankPaymentSourceDescription(paymentAcctTransVO.getSysNo(), vo.getBankName()));
							paymentAcctTransVO.setDestination(genBankPaymentDestinationDescription(vo));
							
							if (CommonConstant.BILL_PMNT_TYPE_SGL.equals(vo.getPmntType())) {
								if(StringUtils.equals(paymentAcctTransVO.getType(), "bill_pymt")) {
									paymentAcctTransVO.setDesc(vo.getBankName());
									paymentAcctTransVO.setDebit(0.00);
									paymentAcctTransVO.setCredit(vo.getPmntAmount());
								} else {
									paymentAcctTransVO.setAcctId(exOrderBillVO.getAcctId());
									paymentAcctTransVO.setDesc(acctVO.getDesc());
									paymentAcctTransVO.setDebit(vo.getPmntAmount());
									paymentAcctTransVO.setCredit(0.00);
								}
							}
							accountService.auditAcctTrans(CommonConstant.ACTION_CD_UPD, paymentAcctTransVO);
						}
					}
					billPymtDAO.update(cashBookVO);

					if (CommonConstant.BILL_PMNT_TYPE_MUL.equals(vo.getPmntType())) {
						List<BillPaymentVO> billPmntList = billPymtDAO.getBillPymtbyCode(exOrderBillVO.getCompanyId(), vo.getCode(), CommonConstant.BILL_PMNT_TYPE_MUL);
						for (BillPaymentVO billPmntVO : billPmntList) {
							billPmntVO.setPymtDt(vo.getPymtDt());
							billPmntVO.setRemarks(vo.getRemarks());
							billPmntVO.setRefNum(vo.getRefNum());
							billPmntVO.setPayee(vo.getPayee());
							billPymtDAO.update(billPmntVO);
						}
					} else billPymtDAO.update(vo);
				}
			}
			cashBookVO = null;
			creditPymtVO = null;
			debitSuppVO = null;
			bankPymtVO = null;
		}
	}
	
	private CashBookVO genBillPaymentCashBook(CashBookVO currCashBookVO, double totalAmount, BillPaymentVO vo, SystemNumberGenerationVO bankPymtVO) {
		CashBookVO cashBookVO = new CashBookVO();
		
		if(currCashBookVO == null) {
			cashBookVO = new CashBookVO();
			cashBookVO.setIdBank(vo.getBankId());
			cashBookVO.setIdAcct(vo.getAcctId());
			cashBookVO.setPayee(vo.getPayee());
			cashBookVO.setDtTrans(vo.getPymtDt());
			cashBookVO.setRefNo(vo.getRefNum());
			cashBookVO.setRemarks(vo.getRemarks());
			cashBookVO.setCredit(vo.getPmntAmount());
		} else {
			cashBookVO = currCashBookVO;
			cashBookVO.setCredit(totalAmount);
		}
		
		cashBookVO.setSysCode(bankPymtVO.getCode());
		cashBookVO.setSysPrefix(bankPymtVO.getPrefixid());
		cashBookVO.setSysNo(bankPymtVO.getNextnumber().toString());
		cashBookVO.setTransTypeCd("bill_pymt");
		cashBookVO.setTypeCd("gnrl_pymt");
		cashBookVO.setDebit(0.00);
		cashBookVO.setIsClear(false);
		cashBookVO.setIsMark(false);
		cashBookVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
		return cashBookVO;
	}
	
	private AcctTransVO genCommonBillPaymentCreditVO(AcctTransVO acctTransVO, CashBookVO cashBookVO, 
												BillPaymentVO billPaymentVO, SystemNumberGenerationVO bankPymtVO,
												double totalAmount) {
		AcctTransVO creditPymtVO = new AcctTransVO();
		creditPymtVO.setAcctId(cashBookVO.getIdAcct());
		creditPymtVO.setTransDt(cashBookVO.getDtTrans());
		creditPymtVO.setRefNo(cashBookVO.getRefNo());
		creditPymtVO.setDestination(genBankPaymentDestinationDescription(cashBookVO));
		creditPymtVO.setDebit(0.00);
		creditPymtVO.setCredit(totalAmount);
		creditPymtVO.setType("bill_pymt");

		if(billPaymentVO != null) {
			creditPymtVO.setCompanyId(acctTransVO.getCompanyId());
			creditPymtVO.setDesc(billPaymentVO.getBankName());
			creditPymtVO.setCode(billPaymentVO.getBankAcct());
			genCommonPaymentVO(creditPymtVO, billPaymentVO, bankPymtVO);
		} else {
			creditPymtVO.setCompanyId(bankPymtVO.getIdCompany());
			creditPymtVO.setDesc(cashBookVO.getTemp());
			creditPymtVO.setCode(cashBookVO.getBankAcc());
			genCommonPaymentVO(creditPymtVO, cashBookVO, bankPymtVO);
		}
		return creditPymtVO;
	}
	
	private AcctTransVO genCommonPaymentSupplierDebitVO(AcctTransVO acctTransVO, CashBookVO cashBookVO, BillPaymentVO billPaymentVO, 
		SystemNumberGenerationVO bankPymtVO, AcctVO acctVO, AcctTransVO creditPymtVO, double totalAmount, Long acctID) {
		
		AcctTransVO debitSuppVO = new AcctTransVO();
		debitSuppVO.setAcctId(acctID);
		debitSuppVO.setTransDt(cashBookVO.getDtTrans());
		debitSuppVO.setRefNo(cashBookVO.getRefNo());
		debitSuppVO.setDebit(totalAmount);
		debitSuppVO.setCredit(0.00);
		debitSuppVO.setDesc(acctVO.getDesc());
		debitSuppVO.setCode(acctVO.getCode());
		debitSuppVO.setType("gnrl_pymt");
		debitSuppVO.setDestination(genBankPaymentDestinationDescription(cashBookVO));
		
		if(billPaymentVO != null) {
			//Single Bill Payment
			debitSuppVO.setCompanyId(acctTransVO.getCompanyId());
			genCommonPaymentVO(debitSuppVO, billPaymentVO, bankPymtVO);
			debitSuppVO.setRefId(creditPymtVO.getId());
		} else {
			//Multiple Bill Payment
			debitSuppVO.setCompanyId(bankPymtVO.getIdCompany());
			genCommonPaymentVO(debitSuppVO, cashBookVO, bankPymtVO);
		}
		return debitSuppVO;
	}

	private AcctTransVO genCommonPaymentVO(AcctTransVO acctTransVO, CashBookVO vo, SystemNumberGenerationVO bankPymtVO) {
		return genCommonPaymentVO(acctTransVO, vo.getTemp(), bankPymtVO);
	}
	
	private AcctTransVO genCommonPaymentVO(AcctTransVO acctTransVO, BillPaymentVO vo, SystemNumberGenerationVO bankPymtVO) {
		return genCommonPaymentVO(acctTransVO, vo.getBankName(), bankPymtVO);
	}
	
	private AcctTransVO genCommonPaymentVO(AcctTransVO acctTransVO, String strBankName, SystemNumberGenerationVO bankPymtVO) {
		acctTransVO.setSysCode(bankPymtVO.getCode());
		acctTransVO.setSysPrefix(bankPymtVO.getPrefixid());
		acctTransVO.setSysNo(bankPymtVO.getNextnumber().toString());
		acctTransVO.setSource(genBankPaymentSourceDescription(bankPymtVO.getNextnumber().toString(), strBankName));
		
		return acctTransVO;
	}
	
	private void addEditCommonGLCreditor(AcctTransVO acctTransVO, AcctVO acctVO, ExOrderVO exOrderVO, 
			ExOrderBillVO exOrderBillVO, SystemNumberGenerationVO sysNumGenVO, SupplierVO supplierVO) {
		if(sysNumGenVO != null) {
			//New Account Trans for GL Creditor
			acctTransVO.setSysCode(sysNumGenVO.getCode());
			acctTransVO.setSysPrefix(sysNumGenVO.getPrefixid());
			acctTransVO.setSysNo(exOrderBillVO.getCode().toString());
			acctTransVO.setRefNo(exOrderBillVO.getCode().toString());
		}
		
		acctTransVO.setCode(acctVO.getCode());
		acctTransVO.setDesc(acctVO.getDesc());
		acctTransVO.setTransDt(exOrderBillVO.getBillDt());
		if (exOrderBillVO.getBillAmt().doubleValue() < 0) {
			acctTransVO.setDebit(-exOrderBillVO.getBillAmt());
			acctTransVO.setCredit(0.00);
		} else {
			acctTransVO.setDebit(0.00);
			acctTransVO.setCredit(exOrderBillVO.getBillAmt());
		}
		acctTransVO.setSource(updateSalesPurchaseSourceDescription(exOrderVO, exOrderBillVO));
		acctTransVO.setDestination(updateSalesPurchaseDestinationDescription(exOrderBillVO, supplierVO));
		
	}
	
	private void addEditBillAccountTrans(String action, AcctTransVO vo, AcctTransVO acctTransVO, boolean isUpdateRefID) throws BusinessException {
		vo.setTransDt(acctTransVO.getTransDt());
		vo.setSysNo(acctTransVO.getSysNo());
		vo.setRefNo(acctTransVO.getRefNo());
		vo.setSource(acctTransVO.getSource());
		vo.setDestination(StringUtils.isNotBlank(vo.getRemarks()) ? acctTransVO.getDestination() + " / " + vo.getRemarks() : acctTransVO.getDestination());
		
		if(isUpdateRefID)
			vo.setRefId(acctTransVO.getId());
		
		if (vo.getTaxAmount() != null && vo.getTaxAmount().doubleValue() < 0.00)
			vo.setTaxAmount(-vo.getTaxAmount());
		
		if(StringUtils.equals(action, CommonConstant.ACTION_CD_ADD)) {
			vo.setCompanyId(acctTransVO.getCompanyId());
			vo.setSysCode(acctTransVO.getSysCode());
			vo.setSysPrefix(acctTransVO.getSysPrefix());
		}
		accountService.auditAcctTrans(action, vo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#update()
	 */
	@Override
	public void updateSingleBill(Long oldExOrderID, ExOrderVO exOrderVO, ExOrderBillVO exOrderBillVO, AcctTransVO acctTransVO, 
			List<AcctTransVO> acctTransViewList, List<BillPaymentVO> billPaymentList, 
			List<BillPaymentVO> billPaymentViewList, SupplierVO supplierVO) throws BusinessException, QueueException {
		
		if (CommonConstant.DEF_ACCT_TYPE_SC.equalsIgnoreCase(exOrderBillVO.getSupplierType()) &&
				StringUtils.isNotEmpty(exOrderBillVO.getCnNumber()) && billPymtDAO.isCNUsed(exOrderBillVO, exOrderBillVO.getCompanyId()))
			throw new BusinessException(CommonErrConstant.ERR_BILL_PMNT_CN_USED);
		
		Double amtPaid = 0.00;
		boolean isMultipleBill = false;
		
		if (CollectionUtils.isNotEmpty(billPaymentList)) {
			for (BillPaymentVO vo : billPaymentList) {
				amtPaid += vo.getPmntAmount();
			}
		}
		exOrderBillVO.setAmtPaid(amtPaid);
		
		AcctVO acctVO = acctDAO.getAccountLiabilityById(acctTransVO.getAcctId());
		addEditCommonGLCreditor(acctTransVO, acctVO, exOrderVO, exOrderBillVO, null, supplierVO);
		accountService.auditAcctTrans(CommonConstant.ACTION_CD_UPD, acctTransVO);
		
		// update GL
		//chartOfAcctDAO.terminateAcctTrans(acctTransVO.getSysCode(), acctTransVO.getSysNo(), "Debit", acctTransVO.getCompanyId());
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", acctTransVO.getCompanyId());
		params.put("sysCode", acctTransVO.getSysCode());
		params.put("sysNo", acctTransVO.getSysNo());
		params.put("statusCode", BaseConstant.STATUS_ACTIVE);
		params.put("refId", acctTransVO.getId());
		//params.put("credit", 0.0);
		
		List<AcctTransVO> acctTransItemList = accountService.getAccountTransList(params);
		
		if (CollectionUtils.isNotEmpty(acctTransVO.getAcctTransList())) {
			for (AcctTransVO vo : acctTransVO.getAcctTransList()) {
				if (vo.getId() == null) {
					// insert account transaction
					addEditBillAccountTrans(CommonConstant.ACTION_CD_ADD, vo, acctTransVO, true);
				} else {
					if (CollectionUtils.isNotEmpty(acctTransItemList)) {
						vo.setTransDt(acctTransVO.getTransDt());
						vo.setSource(acctTransVO.getSource());
						vo.setDestination(acctTransVO.getDestination());
						
						for (int i = acctTransItemList.size() - 1 ; i >= 0 ; i--) {
							AcctTransVO vo1 = acctTransItemList.get(i);
							
							if (vo1.getId().longValue() == vo.getId().longValue()) {
								vo1.setTaxCode(vo.getTaxCode());
								vo1.setTaxAmount(vo.getTaxAmount());
								vo1.setTaxRate(vo.getTaxRate());
								vo1.setAmount(vo.getAmount());
								vo1.setDebit(vo.getDebit());
								vo1.setCredit(vo.getCredit());
								vo1.setAcctId(vo.getAcctId());
								vo1.setCode(vo.getCode());
								vo1.setDesc(vo.getDesc());
								vo1.seteInvoiceClassCode(vo.geteInvoiceClassCode());
								vo1.setRemarks(vo.getRemarks());
								
								addEditBillAccountTrans(CommonConstant.ACTION_CD_UPD, vo1, vo, false);
								acctTransItemList.remove(i);
								break;
							}
						}
					} else addEditBillAccountTrans(CommonConstant.ACTION_CD_UPD, vo, acctTransVO, false);
				}
			}
		}
		
		if (CollectionUtils.isNotEmpty(acctTransItemList)) {
			for (AcctTransVO vo1 : acctTransItemList) {
				if (CommonConstant.SYS_NUM_CD_TAX.equals(vo1.getType()) || 
						CommonConstant.SYS_NUM_CD_NCT.equals(vo1.getType()) || 
						CommonConstant.SYS_NUM_CD_ROUNDING.equals(vo1.getType())) {
					continue;
				}
				addEditBillAccountTrans(CommonConstant.ACTION_CD_DEL, vo1, acctTransVO, false);
			}
		}
		
		// update rounding and gst
		AccountHelper.roundingAndTaxUpdateBill(exOrderBillVO.getAmountCalcViewVO(), acctTransVO, false);
		
		// add and update bill payment
		addEditBillPayemnt(billPaymentList, exOrderBillVO, acctVO, acctTransVO);
		
		// delete bill payment
		if (CollectionUtils.isNotEmpty(billPaymentViewList)) {
			CashBookVO cashBookVO = null;
			
			for (BillPaymentVO viewVO : billPaymentViewList) {
				for (int i = 0 ; i < billPaymentList.size() ; i++) {
					if (viewVO.getId().equals(billPaymentList.get(i).getId())) {
						viewVO.setNotDel(true);
						break;
					}
				}
				
				if (!viewVO.isNotDel()) {
					cashBookVO = billPymtDAO.getCashBook(exOrderBillVO.getCompanyId(), viewVO.getCode());
					cashBookVO.setStatusCode(BaseConstant.STATUS_TERMINATED);
					billPymtDAO.update(cashBookVO);
					
					List<AcctTransVO> acctTransList = getAcctTransList(cashBookVO.getSysNo(), cashBookVO.getSysCode(), exOrderBillVO.getCompanyId());
					for(int k = 0; k < acctTransList.size(); k++) {
						accountService.auditAcctTrans(CommonConstant.ACTION_CD_DEL, acctTransList.get(k));
					}
					// delete bill payment
					//billPymtDAO.delete(viewVO);
					
					// void all payment from multiple bills
					if (CommonConstant.BILL_PMNT_TYPE_MUL.equals(viewVO.getPmntType())) {
						isMultipleBill = true;
						List<BillPaymentVO> multiBillPmnts = billPymtDAO.getBillPymtbyCode(exOrderBillVO.getCompanyId(), viewVO.getCode(), CommonConstant.BILL_PMNT_TYPE_MUL);
						if (CollectionUtils.isNotEmpty(multiBillPmnts)) {
							ExOrderBillVO eoBillVO = null;
							for (BillPaymentVO pmntVO : multiBillPmnts) {
								eoBillVO = billPymtDAO.getEOBill(pmntVO.getEoBillId());
								eoBillVO.setAmtPaid(eoBillVO.getAmtPaid() - pmntVO.getPmntAmount());
								if (eoBillVO.getAmtPaid().doubleValue() > 0) eoBillVO.setStatus(CommonConstant.STATUS_CD_PAID);
								else {
									if (eoBillVO.getAmtPaid() < 0 && eoBillVO.getBillAmt() > 0) eoBillVO.setAmtPaid(0.0);
									eoBillVO.setStatus(CommonConstant.STATUS_CD_BILLED);
								}
								billPymtDAO.update(eoBillVO);
								
								pmntVO.setStatusCode(BaseConstant.STATUS_TERMINATED);
								billPymtDAO.update(pmntVO);
							}
						}
						
					// void payment from single bill
					} else {
						viewVO.setStatusCode(BaseConstant.STATUS_TERMINATED);
						billPymtDAO.update(viewVO);
					}
				}
			}
			cashBookVO = null;
		}
		
		if (CollectionUtils.isNotEmpty(exOrderBillVO.getEoBillPersonList())) {
			for (ExOrderBillPersonVO eoBillPersonVO : exOrderBillVO.getEoBillPersonList()) {
				if (eoBillPersonVO.getId() == null) {
					eoBillPersonVO.setEoBillId(exOrderBillVO.getId());
					eoBillPersonVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
					billPymtDAO.insert(eoBillPersonVO);
				} else {
					billPymtDAO.update(eoBillPersonVO);
				}
			}
		}
		
		if (!isMultipleBill) billPymtDAO.update(exOrderBillVO);
		
		// update ex order
		if (exOrderBillVO.getEoId() != null) {
			billPymtDAO.update(exOrderVO);
			
			// if eo id changed then update status to pendings
			if (oldExOrderID != null) {
				if (oldExOrderID.longValue() != exOrderBillVO.getEoId().longValue()) {
					ExOrderVO tempVO =  getEOById(oldExOrderID, exOrderBillVO.getCompanyId());
					tempVO.setAmtDue(tempVO.getTotalAmt());
					tempVO.setStatus(CommonConstant.STATUS_CD_PENDING);
					billPymtDAO.update(tempVO);
				}
			}
		} else {
			if (exOrderVO != null && exOrderVO.getId() != null) {
				exOrderVO.setStatus(CommonConstant.STATUS_CD_PENDING);
				billPymtDAO.update(exOrderVO);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#delete()
	 */
	@Override
	public void delBill(ExOrderVO exOrderVO, ExOrderBillVO exOrderBillVO, AcctTransVO acctTransVO, List<BillPaymentVO> billPaymentList) throws BusinessException {
		accountService.auditAcctTrans(CommonConstant.ACTION_CD_DEL, acctTransVO);
		
		if (CollectionUtils.isNotEmpty(acctTransVO.getAcctTransList())) {
			//chartOfAcctDAO.terminateAcctTrans(acctTransVO.getSysCode(), acctTransVO.getSysNo(), "Debit", exOrderBillVO.getCompanyId());
			for (AcctTransVO vo : acctTransVO.getAcctTransList()) {
				accountService.auditAcctTrans(CommonConstant.ACTION_CD_DEL, vo);
			}
		}
		
		List<AcctTransVO> acctTransTaxRndList = acctTransDAO.getAcctTransTaxRndList(exOrderBillVO.getCompanyId(), acctTransVO.getSysCode(), acctTransVO.getSysNo());
		for(AcctTransVO acctVO : acctTransTaxRndList) {
			accountService.auditAcctTrans(CommonConstant.ACTION_CD_DEL, acctVO);
		}
		
		// update exchange order status
		if (exOrderVO != null && exOrderVO.getId() != null) {
			exOrderVO.setStatus(CommonConstant.STATUS_CD_PENDING);
			exOrderVO.setAmtDue(exOrderVO.getTotalAmt());
			billPymtDAO.update(exOrderVO);
		}
		
		if (CollectionUtils.isNotEmpty(billPaymentList)) {
			CashBookVO cashBookVO = null;
			
			// delete multiple bills payment
			if (billPaymentList.get(0).getPmntType().equals(CommonConstant.BILL_PMNT_TYPE_MUL)) {
				List<ExOrderBillVO> exOrderBillList = billPymtDAO.getBillListByCode(billPaymentList.get(0).getCode());
				
				// cancel cash book
				cashBookVO = billPymtDAO.getCashBook(exOrderBillVO.getCompanyId(), billPaymentList.get(0).getCode());
				cashBookVO.setStatusCode(BaseConstant.STATUS_TERMINATED);
				billPymtDAO.update(cashBookVO);
				
				// delete account transactons
				List<AcctTransVO> acctTransList = getAcctTransList(cashBookVO.getSysNo(), cashBookVO.getSysCode(), exOrderBillVO.getCompanyId());
				for (AcctTransVO vo : acctTransList) {
					accountService.auditAcctTrans(CommonConstant.ACTION_CD_DEL, vo);
				}
				
				// cancel bills
				for (ExOrderBillVO vo : exOrderBillList) {
					if (vo.getId().equals(exOrderBillVO.getId())) vo.setStatus(CommonConstant.STATUS_CD_CANCELLED);
					else vo.setStatus("BL");
					vo.setAmtPaid(0.0);
					billPymtDAO.update(vo);
				}
				
				// terminate bills payment
				billPymtDAO.cancelAllPayments(billPaymentList.get(0));
				
			// single bill payment
			} else {
				for (BillPaymentVO vo : billPaymentList) {
					// cancel cash book
					cashBookVO = billPymtDAO.getCashBook(exOrderBillVO.getCompanyId(), vo.getCode());
					cashBookVO.setStatusCode(BaseConstant.STATUS_TERMINATED);
					billPymtDAO.update(cashBookVO);
					
					List<AcctTransVO> acctTransList = getAcctTransList(vo.getCode(), CommonConstant.SYS_NUM_CD_BANK_PMNT, exOrderBillVO.getCompanyId());
					for (AcctTransVO atVO : acctTransList) {
						accountService.auditAcctTrans(CommonConstant.ACTION_CD_DEL, atVO);
					}
				}
				// cancel current bill
				exOrderBillVO.setStatus(CommonConstant.STATUS_CD_CANCELLED);
				billPymtDAO.update(exOrderBillVO);
			}
		} else {
			exOrderBillVO.setStatus(CommonConstant.STATUS_CD_VOID);
			billPymtDAO.update(exOrderBillVO);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getSupplier()
	 */
	@Override
	public SupplierVO getSupplier(Long supplierId, Long companyId) throws BusinessException {
		return supplierViewDAO.getSupplier(supplierId, companyId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getSupplier()
	 */
	@Override
	public BankAcctVO getBankId(Long acctId) throws BusinessException {
		return billPymtDAO.getBankId(acctId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getAcctTrans()
	 */
	@Override
	public AcctTransVO getAcctTrans(String sysNo, Long companyId) throws BusinessException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sysNo", sysNo);
		params.put("companyId", companyId);
		params.put("sysCode", CommonConstant.SYS_NUM_CD_PURC_BILL);
		//params.put("statusCode", BaseConstant.STATUS_ACTIVE);
		
		return acctTransDAO.getAccountTransFilterReferenceID(params);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getPerson()
	 */
	@Override
	public ExOrderVO getEOById(Long idEO, Long idCompany) throws BusinessException {
		ExOrderVO exOrderVO = purchaseEODAO.getEOByEOId(idEO, idCompany);
		if(exOrderVO != null) {
			if(exOrderVO.getTourId() != 0) {
				exOrderVO.setTourDepVO(tourPkgDAO.getTourDepById(exOrderVO.getTourId()));
				
				if (exOrderVO.getTourDepVO() != null && exOrderVO.getTourDepVO().getIdTourPkg() != null) {
					TourPackageVO tourPkgVO = tourPkgDAO.getTourPackageById(exOrderVO.getTourDepVO().getIdTourPkg());
					exOrderVO.getTourDepVO().setTourPkgTypeCd(tourPkgVO.getTypeCd());
				}
			}
		}
		return exOrderVO;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getPerson()
	 */
	@Override
	public PersonVO getPerson(Long personId) throws BusinessException {
		return purchaseEODAO.getPerson(personId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getLiability()
	 */
	@Override
	public AcctCatVO getLiability() throws BusinessException {
		return billPymtDAO.getLiability();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getExpenditure()
	 */
	@Override
	public AcctCatVO getExpenditure() throws BusinessException {
		return billPymtDAO.getExpenditure();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getAsset()
	 */
	@Override
	public AcctCatVO getAsset() throws BusinessException {
		return billPymtDAO.getAsset();
	}

	public AcctCatVO getIncome() {
		return billPymtDAO.getIncome();
	}
	
	public AcctCatVO getPurchase() {
		return billPymtDAO.getPurchase();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#billPaymentMultipleV2(java.util.List, com.bcs.zsg.bank.vo.CashBookVO, com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO, double)
	 */
	@Override
	public void billPaymentMultipleV2(List<ExOrderBillVO> billList, CashBookVO cashBookVO, SystemNumberGenerationVO bankPymtVO, double totalAmt ) throws BusinessException {
		if (CollectionUtils.isNotEmpty(billList)) {
			cashBookVO = genBillPaymentCashBook(cashBookVO, totalAmt, null, bankPymtVO);
			billPymtDAO.insert(cashBookVO);
			
			// insert into acct_trans, credit user account
			AcctTransVO creditPymtVO = new AcctTransVO();
			creditPymtVO = genCommonBillPaymentCreditVO(null, cashBookVO, null, bankPymtVO, totalAmt);
			accountService.auditAcctTrans(CommonConstant.ACTION_CD_ADD, creditPymtVO);
			
			// insert into acct_trans, debit supplier
			AcctVO acctVO = acctDAO.getAccountLiabilityById(billList.get(0).getAcctId());
			AcctTransVO debitSuppVO = new AcctTransVO();
			debitSuppVO = genCommonPaymentSupplierDebitVO(null, cashBookVO, null, bankPymtVO, acctVO, creditPymtVO, 
														totalAmt, billList.get(0).getAcctId());
			accountService.auditAcctTrans(CommonConstant.ACTION_CD_ADD, debitSuppVO);
			
			BillPaymentVO billpaymentVO = null;
			ExOrderVO exOrderVO;
			for (ExOrderBillVO bill : billList) {
				ExOrderBillVO vo = billPymtDAO.getEOBill(bill.getId());
				vo.setStatus(CommonConstant.STATUS_CD_PAID);
				if(bill.getAmtAllocate() < (vo.getBillAmt() - vo.getAmtPaid())) vo.setAmtPaid(vo.getAmtPaid() + bill.getAmtAllocate());
				else vo.setAmtPaid(vo.getBillAmt());
				billPymtDAO.update(vo);
				
				billpaymentVO = new BillPaymentVO();
				billpaymentVO.setCode(bankPymtVO.getNextnumber().toString());
				billpaymentVO.setEoBillId(vo.getId());
				billpaymentVO.setBankId(cashBookVO.getIdBank());
				billpaymentVO.setPymtDt(cashBookVO.getDtTrans());
				billpaymentVO.setRefNum(cashBookVO.getRefNo());
				billpaymentVO.setRemarks(cashBookVO.getRemarks());
				billpaymentVO.setPmntAmount(bill.getAmtAllocate());
				billpaymentVO.setPayee(cashBookVO.getPayee());
				billpaymentVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
				billpaymentVO.setChqAmount(totalAmt);
				billpaymentVO.setPmntType(CommonConstant.BILL_PMNT_TYPE_MUL);
				billPymtDAO.insert(billpaymentVO);
				
				if (vo.getEoId() != null) {
					exOrderVO = billPymtDAO.getExOrder(vo.getEoId(), vo.getCompanyId());
					if (exOrderVO != null) {
						exOrderVO.setStatus(CommonConstant.STATUS_CD_PAID);
						exOrderVO.setAmtDue(exOrderVO.getTotalAmt() - vo.getAmtPaid());
						billPymtDAO.update(exOrderVO);
					}
				}
			}
			billpaymentVO = null;
		}
	}
	
	public List<BillPaymentVO> getBillPymtbyCode(String code) {
		return null;
	}
	
	public BillPaymentVO getBillPymt(Long id) {
		return billPymtDAO.getBillPymt(id);
	}
	
	public List<CashBookVO> getEOPymtList(Long eoId) {
		return billPymtDAO.getEOPymtList(eoId);
	}

	public ExOrderBillVO getEOBillbyCode(String billCode) {
		return billPymtDAO.getEOBillbyCode(billCode);
	}

	@Override
	public List<AcctVO> getAcctListByAccCode(Long idCompany, Long expendId, Long assetId, String query) throws BusinessException {
		return billPymtDAO.getAcctListByAccCode(idCompany, expendId, assetId, query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getAcctListByComp(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<AcctVO> getAcctListByComp(Long expendId, Long assetId, Long companyId) throws BusinessException {
		return billPymtDAO.getAcctListByComp(expendId, assetId, companyId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getLiabilityList(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<AcctSubCatVO> getLiabilityList(Long liaId, Long companyId) {
		return billPymtDAO.getLiabilityList(liaId,companyId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getSupplier(java.lang.Long, java.lang.Long, int)
	 */
	@Override
	public SupplierVO getSupplier(Long supplierId, Long companyId, int includeInactive) {
		return supplierViewDAO.getSupplier(supplierId,companyId,includeInactive);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getSupplierInBills(java.lang.Long)
	 */
	@Override
	public int getSupplierInBills(Long supplierId) {
		return billPymtDAO.getSupplierInBills(supplierId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getAPAccount(java.lang.Long)
	 */
	@Override
	public List<AcctVO> getAPAccount(Long companyId) {
		return billPymtDAO.getAPAccount(companyId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getEOBillById(java.lang.Long)
	 */
	@Override
	public ExOrderBillVO getEOBillById(Long idBill) throws BusinessException {
		return billPymtDAO.getEOBillById(idBill);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getEOBillByEOId(java.lang.Long)
	 */
	@Override
	public ExOrderBillVO getEOBillByEOId(Long idEO) throws BusinessException {
		ExOrderBillVO eoBillVO = billPymtDAO.getEOBillByEOId(idEO);
		if (eoBillVO != null) {
			eoBillVO.setBillPmntList(billPymtDAO.getBillPymtList(eoBillVO.getId()));
		}
		return eoBillVO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getBillListSize(int, int, java.util.Map, java.lang.Long, java.lang.Long)
	 */
	@Override
	public int getBillListSize(int first, int pageSize, Map<String, String> filters, Long idCompany, Long idSupplier, Map<String, Object> params) throws BusinessException {
		return billPymtDAO.getBillListSize(first, pageSize, filters, idCompany, idSupplier, params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getBillList(int, int, java.util.Map, java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<ExOrderBillVO> getBillList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters, Long idCompany, Long idSupplier, Map<String, Object> params) throws BusinessException {
		return billPymtDAO.getBillList(first, pageSize, sortField, sortOrder, filters, idCompany, idSupplier, params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#getTotalAmt(java.lang.Long, java.lang.Long)
	 */
	@Override
	public Map<String, Double> getTotalAmt(Long idCompany, Long idSupplier) throws BusinessException {
		return billPymtDAO.getTotalAmt(idCompany, idSupplier);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.BillPymtService#saveAttachPayment(java.lang.Long, java.util.List, com.bcs.zsg.bank.vo.CashBookVO, com.bcs.zsg.bank.vo.CashBookVO, com.bcs.zsg.purchase.vo.SupplierVO)
	 */
	@Override
	public void saveAttachPayment(Long idCompany, List<ExOrderBillVO> billList, CashBookVO bankPmntVO, CashBookVO cashBookVO, SupplierVO supplierVO) throws BusinessException {
		BigDecimal totalAmountPaid = new BigDecimal(cashBookVO.getCredit().toString()).setScale(2, RoundingMode.HALF_UP);
		BigDecimal totalBankPayment = new BigDecimal(bankPmntVO.getCredit().toString()).setScale(2, RoundingMode.HALF_UP);
		if (totalAmountPaid.compareTo(totalBankPayment) != 0) throw new BusinessException(CommonErrConstant.ERR_BILL_ATTACH_PMNT_NOT_TALLY);
		
		// delete cash book eo bill link
		//billPymtDAO.deleteCashBookEOBill(bankPmntVO.getId());
		
		BillPaymentVO billpaymentVO;
		ExOrderVO exOrderVO;
		CashBookEOBillVO cashBookEOBillVO;
		for (ExOrderBillVO bill : billList) {
			ExOrderBillVO vo = billPymtDAO.getEOBill(bill.getId());
			vo.setStatus(CommonConstant.STATUS_CD_PAID);
			if(bill.getAmtAllocate() < (vo.getBillAmt() - vo.getAmtPaid())) vo.setAmtPaid(vo.getAmtPaid() + bill.getAmtAllocate());
			else vo.setAmtPaid(vo.getBillAmt());
			billPymtDAO.update(vo);
			
			// add bill payment
			billpaymentVO = new BillPaymentVO();
			billpaymentVO.setCode(bankPmntVO.getSysNo());
			billpaymentVO.setEoBillId(vo.getId());
			billpaymentVO.setBankId(bankPmntVO.getIdBank());
			billpaymentVO.setPymtDt(bankPmntVO.getDtTrans());
			billpaymentVO.setRefNum(bankPmntVO.getRefNo());
			billpaymentVO.setRemarks(bankPmntVO.getRemarks());
			billpaymentVO.setPmntAmount(bill.getAmtAllocate());
			billpaymentVO.setPayee(bankPmntVO.getPayee());
			billpaymentVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
			billpaymentVO.setChqAmount(bankPmntVO.getCredit());
			billpaymentVO.setPmntType(CommonConstant.BILL_PMNT_TYPE_MUL);
			billPymtDAO.insert(billpaymentVO);
			
			// insert into cash book eo bill link
			cashBookEOBillVO = new CashBookEOBillVO();
			cashBookEOBillVO.setIdCashBook(bankPmntVO.getId());
			cashBookEOBillVO.setIdEOBill(vo.getId());
			cashBookEOBillVO.setAmount(vo.getBillAmt());
			cashBookEOBillVO.setAmountPaid(vo.getAmtAllocate());
			cashBookEOBillVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
			billPymtDAO.insert(cashBookEOBillVO);
			
			// update eo if attached
			if (vo.getEoId() != null) {
				exOrderVO = billPymtDAO.getExOrder(vo.getEoId(), vo.getCompanyId());
				exOrderVO.setStatus(CommonConstant.STATUS_CD_PAID);
				exOrderVO.setAmtDue(exOrderVO.getTotalAmt() - vo.getAmtPaid());
				billPymtDAO.update(exOrderVO);
			}
		}
		
		// update cash book
		bankPmntVO.setTransTypeCd("bill_pymt");
		bankPmntVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
		billPymtDAO.update(bankPmntVO);
		
		// get supplier account
		AcctVO supplierAcctVO = acctDAO.getAcctVO(billList.get(0).getAcctId());
		// update date for account trans
		List<AcctTransVO> acctTransList = getAcctTransList(bankPmntVO.getSysNo(), bankPmntVO.getSysCode(), idCompany);
		
		if (CollectionUtils.isNotEmpty(acctTransList)) {
			AcctTransVO acctTransMainVO = new AcctTransVO();
			AcctTransVO acctTransSubVO = new AcctTransVO();
			
			for (AcctTransVO paymentAcctTransVO : acctTransList) {
				//if (paymentAcctTransVO.getAcctId().equals(bankPmntVO.getIdAcct())) {
				if ("cash_with".equals(paymentAcctTransVO.getType()) || "bill_pymt".equals(paymentAcctTransVO.getType())) {
					paymentAcctTransVO.setTransDt(bankPmntVO.getDtTrans());
					paymentAcctTransVO.setType("bill_pymt");
					paymentAcctTransVO.setRefNo(bankPmntVO.getRefNo());
					paymentAcctTransVO.setDestination(bankPmntVO.getRemarks() + " To-" + bankPmntVO.getPayee());
					paymentAcctTransVO.setCredit(bankPmntVO.getCredit());
					paymentAcctTransVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
					acctTransMainVO = paymentAcctTransVO;
					
				} else paymentAcctTransVO.setStatusCode(BaseConstant.STATUS_TERMINATED);
				accountService.auditAcctTrans(CommonConstant.ACTION_CD_UPD, paymentAcctTransVO);
			}
			
			// add trade / sundry creditor account trans
			acctTransSubVO.setTransDt(acctTransMainVO.getTransDt());
			acctTransSubVO.setCompanyId(idCompany);
			acctTransSubVO.setAcctId(supplierAcctVO.getId());
			acctTransSubVO.setSysCode(acctTransMainVO.getSysCode());
			acctTransSubVO.setSysNo(acctTransMainVO.getSysNo());
			acctTransSubVO.setSysPrefix(acctTransMainVO.getSysPrefix());
			acctTransSubVO.setRefNo(acctTransMainVO.getRefNo());
			acctTransSubVO.setSource(acctTransMainVO.getSource());
			acctTransSubVO.setDestination(acctTransMainVO.getDestination());
			acctTransSubVO.setCode(supplierAcctVO.getCode());
			acctTransSubVO.setDesc(supplierAcctVO.getDesc());
			acctTransSubVO.setDebit(acctTransMainVO.getCredit());
			acctTransSubVO.setCredit(0.0);
			acctTransSubVO.setType("gnrl_pymt");
			acctTransSubVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
			accountService.auditAcctTrans(CommonConstant.ACTION_CD_ADD, acctTransSubVO);
		}
	}
	
	@Override
	public void updateJournalToBill(Long idBill, Long idJournal) throws BusinessException {
		billPymtDAO.updateJournalToBill(idBill, idJournal);
	}

	@Override
	public List<ExOrderBillPersonVO> getEoBillPersonList(Long eoBillId) throws BusinessException {
		return billPymtDAO.getEoBillPersonList(eoBillId);
	}
	
	@Override
	public PayeeViewVO getPayeeViewVO(Long idPayee, String payeeType) throws BusinessException {
		return billPymtDAO.getPayeeViewVO(idPayee, payeeType);
	}
	
	@Override
	public void addDeleteBillAttachment(ExOrderBillAttachmentVO billAttachmentVO, boolean isDelete) throws BusinessException {
		if (isDelete) purchaseEODAO.delete(billAttachmentVO);
		else {
			if (billAttachmentVO.getId() != null)
				purchaseEODAO.update(billAttachmentVO);
			else
				purchaseEODAO.insert(billAttachmentVO);
		}
	}
	
	@Override
	public ExOrderBillVO getBillDetails(Long idBill) throws BusinessException {
		ExOrderBillVO billVO = billPymtDAO.getEOBillById(idBill);
		billVO.setBillAttachmentList(billPymtDAO.getEOBillAttachmentList(idBill));
		return billVO;
	}

	@Override
	public boolean getBillEOExisted(Long idCompany, Long idExOrder, Long idEoBill) throws BusinessException {
		return billPymtDAO.getBillEOExisted(idCompany, idExOrder, idEoBill);
	}
}
