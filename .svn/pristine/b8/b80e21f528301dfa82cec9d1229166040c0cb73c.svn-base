package com.bcs.zsg.sales.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bcs.zsg.acct.helper.TaxableAmount;
import com.bcs.zsg.core.vo.BaseVO;

public class InvoicePaymentVO extends BaseVO implements TaxableAmount {
	private static final long serialVersionUID = 1L;

	private Long invId;
	private Long issuerId;
	private String pmntIssuedBy; // payment issued by name
	private Long bankId;
	private Date pmntDt;
	private String code;
	private String pmntTypeCd;
	private String pmntTypeName; // payment type name
	private String refNo;
	private String pmntFor;
	private String recievedFr;
	private String amountWord; // payment amount wording
	private Double amount;
	private String invPmnt; // payment amount wording
	private Long idCashBook;
	private String tourCode;
	private Long invoiceId;
	private String invoiceNo;
	private String psNo; // for deposit list displaying purpose
	private String invoiceDocTypeCd;
	private String invoiceDocTypeStatus;
	private Date cashBookDt;
	private boolean finPeriod;
	private Long creditNoteId;
	private Long idParentPmnt;
	private boolean hideDeposit;
	private Integer seq;
	private boolean isPrint = false;
	private String invPmntTooltip; // for showing Invoice Payment History Tooltip
	
	private List<InvoicePaymentAttachmentVO> invoicePaymentAttachmentList = new ArrayList<InvoicePaymentAttachmentVO>();
	
	// For Print out purpose
	private String companyName;
	private String companyShortName;
	private Long custId;
	private String bankName;
	
	private boolean checked;
	
	public String getInvoiceDocTypeStatus() {
		return invoiceDocTypeStatus;
	}
	public void setInvoiceDocTypeStatus(String invoiceDocTypeStatus) {
		this.invoiceDocTypeStatus = invoiceDocTypeStatus;
	}
	public String getPsNo() {
		return psNo;
	}
	public void setPsNo(String psNo) {
		this.psNo = psNo;
	}
	public String getInvoiceDocTypeCd() {
		return invoiceDocTypeCd;
	}
	public void setInvoiceDocTypeCd(String invoiceDocTypeCd) {
		this.invoiceDocTypeCd = invoiceDocTypeCd;
	}
	public Long getIdParentPmnt() {
		return idParentPmnt;
	}
	public void setIdParentPmnt(Long idParentPmnt) {
		this.idParentPmnt = idParentPmnt;
	}
	/**
	 * @return the invId
	 */
	public Long getInvId() {
		return invId;
	}
	/**
	 * @param invId the invId to set
	 */
	public void setInvId(Long invId) {
		this.invId = invId;
	}
	/**
	 * @return the issuerId
	 */
	public Long getIssuerId() {
		return issuerId;
	}
	/**
	 * @param issuerId the issuerId to set
	 */
	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}
	/**
	 * @return the bankId
	 */
	public Long getBankId() {
		return bankId;
	}
	/**
	 * @param bankId the bankId to set
	 */
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}
	/**
	 * @return the pmntDt
	 */
	public Date getPmntDt() {
		return pmntDt;
	}
	/**
	 * @param pmntDt the pmntDt to set
	 */
	public void setPmntDt(Date pmntDt) {
		this.pmntDt = pmntDt;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the pmntTypeCd
	 */
	public String getPmntTypeCd() {
		return pmntTypeCd;
	}
	/**
	 * @param pmntTypeCd the pmntTypeCd to set
	 */
	public void setPmntTypeCd(String pmntTypeCd) {
		this.pmntTypeCd = pmntTypeCd;
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
	 * @return the recievedFr
	 */
	public String getRecievedFr() {
		return recievedFr;
	}
	/**
	 * @param recievedFr the recievedFr to set
	 */
	public void setRecievedFr(String recievedFr) {
		this.recievedFr = recievedFr;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**
	 * @return the pmntIssuedBy
	 */
	public String getPmntIssuedBy() {
		return pmntIssuedBy;
	}
	/**
	 * @param pmntIssuedBy the pmntIssuedBy to set
	 */
	public void setPmntIssuedBy(String pmntIssuedBy) {
		this.pmntIssuedBy = pmntIssuedBy;
	}
	/**
	 * @return the amountWord
	 */
	public String getAmountWord() {
		return amountWord;
	}
	/**
	 * @param amountWord the amountWord to set
	 */
	public void setAmountWord(String amountWord) {
		this.amountWord = amountWord;
	}
	/**
	 * @return the pmntTypeName
	 */
	public String getPmntTypeName() {
		return pmntTypeName;
	}
	/**
	 * @param pmntTypeName the pmntTypeName to set
	 */
	public void setPmntTypeName(String pmntTypeName) {
		this.pmntTypeName = pmntTypeName;
	}
	/**
	 * @return the pmntFor
	 */
	public String getPmntFor() {
		return pmntFor;
	}
	/**
	 * @param pmntFor the pmntFor to set
	 */
	public void setPmntFor(String pmntFor) {
		this.pmntFor = pmntFor;
	}
	public String getInvPmnt() {
		return invPmnt;
	}
	public void setInvPmnt(String invPmnt) {
		this.invPmnt = invPmnt;
	}
	public Long getIdCashBook() {
		return idCashBook;
	}
	public void setIdCashBook(Long idCashBook) {
		this.idCashBook = idCashBook;
	}
	/**
	 * @return the tourCode
	 */
	public String getTourCode() {
		return tourCode;
	}
	/**
	 * @param tourCode the tourCode to set
	 */
	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}
	/**
	 * @return the invoiceId
	 */
	public Long getInvoiceId() {
		return invoiceId;
	}
	/**
	 * @param invoiceId the invoiceId to set
	 */
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	/**
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}
	/**
	 * @param invoiceNo the invoiceNo to set
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	/**
	 * @return the cashBookDt
	 */
	public Date getCashBookDt() {
		return cashBookDt;
	}
	/**
	 * @param cashBookDt the cashBookDt to set
	 */
	public void setCashBookDt(Date cashBookDt) {
		this.cashBookDt = cashBookDt;
	}
	
	/**
	 * @return the finPeriod
	 */
	public boolean isFinPeriod() {
		return finPeriod;
	}
	/**
	 * @param finPeriod the finPeriod to set
	 */
	public void setFinPeriod(boolean finPeriod) {
		this.finPeriod = finPeriod;
	}
	
	@Override
	public double getDAmount() {
		return getAmount();
	}
	@Override
	public void setDAmount(double amount) {
		setAmount(amount);
	}
	@Override
	public double getDTaxRate() {
		return 0.00;
	}
	@Override
	public double getDTaxAmount() {
		return 0.00;
	}
	@Override
	public void setDTaxAmount(double taxAmount) {
		
	}
	@Override
	public String getTaxCode() {
		return null;
	}

	@Override
	public double getDAmountIncludeTax() {
		return 0.00;
	}
	@Override
	public void setDAmountIncludeTax(double amountIncludeTax) {
	}

	/**
	 * @return the creditNoteId
	 */
	public Long getCreditNoteId() {
		return creditNoteId;
	}
	/**
	 * @param creditNoteId the creditNoteId to set
	 */
	public void setCreditNoteId(Long creditNoteId) {
		this.creditNoteId = creditNoteId;
	}
	/**
	 * @return the hideDeposit
	 */
	public boolean isHideDeposit() {
		return hideDeposit;
	}
	/**
	 * @param hideDeposit the hideDeposit to set
	 */
	public void setHideDeposit(boolean hideDeposit) {
		this.hideDeposit = hideDeposit;
	}

	/**
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyShortName() {
		return companyShortName;
	}
	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}
	/**
	 * @return the custId
	 */
	public Long getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isPrint() {
		return isPrint;
	}
	public void setPrint(boolean isPrint) {
		this.isPrint = isPrint;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getInvPmntTooltip() {
		return invPmntTooltip;
	}
	public void setInvPmntTooltip(String invPmntTooltip) {
		this.invPmntTooltip = invPmntTooltip;
	}
	
	public List<InvoicePaymentAttachmentVO> getInvoicePaymentAttachmentList() {
		return invoicePaymentAttachmentList;
	}
	
	public void setInvoicePaymentAttachmentList(List<InvoicePaymentAttachmentVO> invoicePaymentAttachmentList) {
		this.invoicePaymentAttachmentList = invoicePaymentAttachmentList;
	}
}
