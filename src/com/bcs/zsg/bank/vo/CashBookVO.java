package com.bcs.zsg.bank.vo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bcs.zsg.acct.helper.EInvoiceProperties;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.EInvoiceDocumentVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.db.bterp.vo.AmountCalcViewVO;
import com.bcs.zsg.sales.vo.InvoicePaymentVO;
import com.bcs.zsg.web.object.EInvoiceDocument;

import oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType;
public class CashBookVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private AcctTransVO acctTransVO;

	private Long idBank;
	private Long idSupplier;
	private Long idPayee;
	private String payeeType;
	private Long idCustomer;
	private Long idAcct;
	private Long tourDepId;
	private String tourCd; // Tour code
	private Date dtTrans;
	private String sysCode;
	private String sysPrefix; 
	private String sysNo;
	private String refNo;
	private String transTypeCd;
	private String payee;
	private Double debit=0.0;
	private Double credit=0.0;
	private boolean notDel;
	private Boolean isClear;
	private Boolean isMark;
	private Date dtClear;
	private Date dtMark;
	private String typeCd;
	private String groupNo;
	private Double balance;
	private Double billPmntAmount=0.0;
	private String adjmType;
	private String taxCodeGroup = CommonConstant.TAX_CODE_GROUP_N;
	
	// E-Invoice API submission result
	private String eInvoiceSubmissionUid; // e invoice api submission uid (one submission might include multiple
											// documents)
	private String eInvoiceDocumentUuid; // e invoice document uuid
	private String eInvoiceStatus; // e invoice document submission status
	private String eInvoiceErr; // e invoice document submission error
	
	private EInvoiceDocument eInvoiceDocument;
	private EInvoiceDocumentVO eInvoiceDocumentVO;
	
	private InvoiceType invoiceType; // to record submitted info and write into db

	private List<AcctTransViewVO> acctTransList;
	private List<InvoicePaymentVO> invPymtList;
	private String temp;
	private Long longSysNo;
	private Long idAcctTrans;
	private String custCode;
	private String suppCode;

	//for view purpose
	private Double creditPayment; 
	private Double debitDeposit;

	private String bankAcc;
	private String invoices;
	private String bankName;
 
	private Date paymentDt;
	private Date billDate;
	private boolean isDateInFinPeriodClosed;
	
	//for Rounding and Tax
	private AmountCalcViewVO amountCalcViewVO;
	
	private String payeeSalutatn;
	private String payeeSupportName; // for name in '()' (for user only now)
	
	public String geteInvoiceValidationLink() {
		if (eInvoiceDocument != null && StringUtils.isNotBlank(eInvoiceDocumentUuid) && StringUtils.isNotBlank(eInvoiceDocument.getLongId())) {
			return EInvoiceProperties.getEnvBaseUrl() + eInvoiceDocumentUuid + "/share/" + eInvoiceDocument.getLongId();
		} else if (eInvoiceDocumentVO != null && StringUtils.isNotBlank(eInvoiceDocumentUuid) && StringUtils.isNotBlank(eInvoiceDocumentVO.getLongId())) {
			return EInvoiceProperties.getEnvBaseUrl() + eInvoiceDocumentUuid + "/share/" + eInvoiceDocumentVO.getLongId();
		}
		
		return null;
	}
	
	public String getPayeeDisplayName() {
		return getPayeeDisplayName(false);
	}
	
	/**
	 * Returns the full display name of the payee, including salutation if available.
	 * @return a formatted display name combining the salutation (if present) and the payee's name
	 */
	public String getPayeeDisplayName(boolean isAcctTrans) {
		String sal = null;

	    if (StringUtils.isNotBlank(payeeSalutatn)) {
	        sal = LookupItemUtils.getLookupItemDesc(CommonConstant.LOOKUP_CAT_CD_SALUTATN, payeeSalutatn);
	    }
	    
	    if (StringUtils.isNotBlank(sal)) {
	        return sal + " " + payee;
	    } 
	    else if (StringUtils.isNotBlank(payeeSupportName) && !isAcctTrans) {
	        return payee + " (" + payeeSupportName + ")";
	    } 
	    else {
	        return payee;
	    }
	}

	public String getPayeeSalutatn() {
		return payeeSalutatn;
	}

	public void setPayeeSalutatn(String payeeSalutatn) {
		this.payeeSalutatn = payeeSalutatn;
	}

	public String getPayeeSupportName() {
		return payeeSupportName;
	}

	public void setPayeeSupportName(String payeeSupportName) {
		this.payeeSupportName = payeeSupportName;
	}

	public Long getIdPayee() {
		return idPayee;
	}

	public void setIdPayee(Long idPayee) {
		this.idPayee = idPayee;
	}

	public String getPayeeType() {
		return payeeType;
	}

	public void setPayeeType(String payeeType) {
		this.payeeType = payeeType;
	}

	public InvoiceType getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(InvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String geteInvoiceErr() {
		return eInvoiceErr;
	}
	public void seteInvoiceErr(String eInvoiceErr) {
		this.eInvoiceErr = eInvoiceErr;
	}
	public EInvoiceDocument geteInvoiceDocument() {
		return eInvoiceDocument;
	}
	public void seteInvoiceDocument(EInvoiceDocument eInvoiceDocument) {
		this.eInvoiceDocument = eInvoiceDocument;
	}
	public EInvoiceDocumentVO geteInvoiceDocumentVO() {
		return eInvoiceDocumentVO;
	}
	public void seteInvoiceDocumentVO(EInvoiceDocumentVO eInvoiceDocumentVO) {
		this.eInvoiceDocumentVO = eInvoiceDocumentVO;
	}
	public String geteInvoiceSubmissionUid() {
		return eInvoiceSubmissionUid;
	}
	public void seteInvoiceSubmissionUid(String eInvoiceSubmissionUid) {
		this.eInvoiceSubmissionUid = eInvoiceSubmissionUid;
	}
	public String geteInvoiceDocumentUuid() {
		return eInvoiceDocumentUuid;
	}
	public void seteInvoiceDocumentUuid(String eInvoiceDocumentUuid) {
		this.eInvoiceDocumentUuid = eInvoiceDocumentUuid;
	}
	public String geteInvoiceStatus() {
		return eInvoiceStatus;
	}
	public void seteInvoiceStatus(String eInvoiceStatus) {
		this.eInvoiceStatus = eInvoiceStatus;
	}
	public Date getPaymentDt() {
		return paymentDt;
	}
	public void setPaymentDt(Date paymentDt) {
		this.paymentDt = paymentDt;
	}

	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	/**
	 * @return the idBank
	 */
	public Long getIdBank() {
		return idBank;
	}

	/**
	 * @param idBank the idBank to set
	 */
	public void setIdBank(Long idBank) {
		this.idBank = idBank;
	}

	/**
	 * @return the dtTrans
	 */
	public Date getDtTrans() {
		return dtTrans;
	}

	/**
	 * @param dtTrans the dtTrans to set
	 */
	public void setDtTrans(Date dtTrans) {
		this.dtTrans = dtTrans;
	}

	/**
	 * @return the sysNo
	 */
	public String getSysNo() {
		return sysNo;
	}

	/**
	 * @param sysNo the sysNo to set
	 */
	public void setSysNo(String sysNo) {
		this.sysNo = sysNo;
		if (StringUtils.isNumeric(sysNo)) {
			setLongSysNo(Long.parseLong(this.sysNo));
		}
	}

	/**
	 * @return the refNo
	 */
	public String getRefNo() {
		return refNo;
	}

	/**
	 * @param refNo the refNo to set
	 */
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	/**
	 * @return the transTypeCd
	 */
	public String getTransTypeCd() {
		return transTypeCd;
	}

	/**
	 * @param transTypeCd the transTypeCd to set
	 */
	public void setTransTypeCd(String transTypeCd) {
		this.transTypeCd = transTypeCd;
	}

	/**
	 * @return the payee
	 */
	public String getPayee() {
		return payee;
	}

	/**
	 * @param payee the payee to set
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	}

	/**
	 * @return the debit
	 */
	public Double getDebit() {
		return debit;
	}

	/**
	 * @param debit the debit to set
	 */
	public void setDebit(Double debit) {
		this.debit = debit;
	}

	/**
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * @return the isClear
	 */
	public Boolean getIsClear() {
		return isClear;
	}

	/**
	 * @param isClear the isClear to set
	 */
	public void setIsClear(Boolean isClear) {
		this.isClear = isClear;
	}

	/**
	 * @return the isMark
	 */
	public Boolean getIsMark() {
		return isMark;
	}

	/**
	 * @param isMark the isMark to set
	 */
	public void setIsMark(Boolean isMark) {
		this.isMark = isMark;
	}

	/**
	 * @return the dtClear
	 */
	public Date getDtClear() {
		return dtClear;
	}
	/**
	 * @param dtClear the dtClear to set
	 */
	public void setDtClear(Date dtClear) {
		this.dtClear = dtClear;
	}
	/**
	 * @return the dtMark
	 */
	public Date getDtMark() {
		return dtMark;
	}
	/**
	 * @param dtMark the dtMark to set
	 */
	public void setDtMark(Date dtMark) {
		this.dtMark = dtMark;
	}
	/**
	 * @return the typeCd
	 */
	public String getTypeCd() {
		return typeCd;
	}

	/**
	 * @param typeCd the typeCd to set
	 */
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}



	public AcctTransVO getAcctTransVO() {
		return acctTransVO;
	}

	public void setAcctTransVO(AcctTransVO acctTransVO) {
		this.acctTransVO = acctTransVO;
	}

	public List<AcctTransViewVO> getAcctTransList() {
		return acctTransList;
	}

	public void setAcctTransList(List<AcctTransViewVO> acctTransList) {
		this.acctTransList = acctTransList;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public boolean isNotDel() {
		return notDel;
	}

	public void setNotDel(boolean notDel) {
		this.notDel = notDel;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public Double getBillPmntAmount() {
		return billPmntAmount;
	}

	public void setBillPmntAmount(Double billPmntAmount) {
		this.billPmntAmount = billPmntAmount;
	}

	public Double getCreditPayment() {
		return creditPayment;
	}

	public void setCreditPayment(Double creditPayment) {
		this.creditPayment = creditPayment;
	}

	public Double getDebitDeposit() {
		return debitDeposit;
	}

	public void setDebitDeposit(Double debitDeposit) {
		this.debitDeposit = debitDeposit;
	}

	public String getSysPrefix() {
		return sysPrefix;
	}

	public void setSysPrefix(String sysPrefix) {
		this.sysPrefix = sysPrefix;
	}


	public String getBankAcc() {
		return bankAcc;
	}

	public void setBankAcc(String bankAcc) {
		this.bankAcc = bankAcc;
	}

	/**
	 * @return the invoices
	 */
	public String getInvoices() {
		return invoices;
	}
	/**
	 * @param invoices the invoices to set
	 */
	public void setInvoices(String invoices) {
		this.invoices = invoices;
	}
	public Long getIdAcct() {
		return idAcct;
	}

	public void setIdAcct(Long idAcct) {
		this.idAcct = idAcct;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	/**
	 * @return the credit
	 */
	public Double getCredit() {
		return credit;
	}

	/**
	 * @param credit the credit to set
	 */
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	/**
	 * @return the longSysNo
	 */
	public Long getLongSysNo() {
		return longSysNo;
	}
	/**
	 * @param longSysNo the longSysNo to set
	 */
	public void setLongSysNo(Long longSysNo) {
		this.longSysNo = longSysNo;
	}
	/**
	 * @return the idSupplier
	 */
	public Long getIdSupplier() {
		return idSupplier;
	}
	/**
	 * @param idSupplier the idSupplier to set
	 */
	public void setIdSupplier(Long idSupplier) {
		this.idSupplier = idSupplier;
	}
	/**
	 * @return the idCustomer
	 */
	public Long getIdCustomer() {
		return idCustomer;
	}
	/**
	 * @param idCustomer the idCustomer to set
	 */
	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}
	public List<InvoicePaymentVO> getInvPymtList() {
		return invPymtList;
	}
	public void setInvPymtList(List<InvoicePaymentVO> invPymtList) {
		this.invPymtList = invPymtList;
	}
	/**
	 * @return the idAcctTrans
	 */
	public Long getIdAcctTrans() {
		return idAcctTrans;
	}
	/**
	 * @param idAcctTrans the idAcctTrans to set
	 */
	public void setIdAcctTrans(Long idAcctTrans) {
		this.idAcctTrans = idAcctTrans;
	}
	/**
	 * @return the custCode
	 */
	public String getCustCode() {
		return custCode;
	}
	/**
	 * @param custCode the custCode to set
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	/**
	 * @return the suppCode
	 */
	public String getSuppCode() {
		return suppCode;
	}
	/**
	 * @param suppCode the suppCode to set
	 */
	public void setSuppCode(String suppCode) {
		this.suppCode = suppCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the isDateInFinPeriodClosed
	 */
	public boolean isDateInFinPeriodClosed() {
		return isDateInFinPeriodClosed;
	}
	/**
	 * @param isDateInFinPeriodClosed the isDateInFinPeriodClosed to set
	 */
	public void setDateInFinPeriodClosed(boolean isDateInFinPeriodClosed) {
		this.isDateInFinPeriodClosed = isDateInFinPeriodClosed;
	}
	public AmountCalcViewVO getAmountCalcViewVO() {
		return amountCalcViewVO;
	}
	public void setAmountCalcViewVO(AmountCalcViewVO amountCalcViewVO) {
		this.amountCalcViewVO = amountCalcViewVO;
	}
	/**
	 * @return the adjmType
	 */
	public String getAdjmType() {
		return adjmType;
	}
	/**
	 * @param adjmType the adjmType to set
	 */
	public void setAdjmType(String adjmType) {
		this.adjmType = adjmType;
	}
	/**
	 * @return the taxCodeGroup
	 */
	public String getTaxCodeGroup() {
		return taxCodeGroup;
	}
	/**
	 * @param taxCodeGroup the taxCodeGroup to set
	 */
	public void setTaxCodeGroup(String taxCodeGroup) {
		this.taxCodeGroup = taxCodeGroup;
	}
	/**
	 * @return the tourDepId
	 */
	public Long getTourDepId() {
		return tourDepId;
	}
	/**
	 * @param tourDepId the tourDepId to set
	 */
	public void setTourDepId(Long tourDepId) {
		this.tourDepId = tourDepId;
	}
	/**
	 * @return the tourCd
	 */
	public String getTourCd() {
		return tourCd;
	}
	/**
	 * @param tourCd the tourCd to set
	 */
	public void setTourCd(String tourCd) {
		this.tourCd = tourCd;
	}
	
}
