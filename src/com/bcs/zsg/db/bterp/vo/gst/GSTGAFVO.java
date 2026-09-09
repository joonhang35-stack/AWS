package com.bcs.zsg.db.bterp.vo.gst;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class GSTGAFVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	
	// Supply
	private String recordId;		//1. Record Identifier
	private String custName;		//2. Customer Name
	private String custBRN;			//3. Customer BRN
	private String custGSTNo;		//4. Customer GST No
	private Date invoiceDt;			//5. Invoice Date
	private String invoiceNo;		//6. Invoice No
	private String expDecNo;		//7. Export Declaration Number
	private String lineNo;			//8. Line Number
	private String description;		//9. Product Description
	private Double amount;			//10. Sales Value (in MYR)
	private Double taxAmount;		//11. Sales Value GST Amount
	private String taxCode;			//12. Tax Code
	private String country;			//13. Country
	private String curCode;			//14. Foreign Currency Code
	private Double curAmount;		//15. Sales Foreign Currency Amount
	private Double curTaxAmount;	//16. Sales Currency Sales Amount GST
	
	// Purchases
									//1. Record Identifier
	private String supplierName;	//2. Supplier Name
	private String supplierBRN;		//3. Supplier BRN
	private String supplierGSTNo;	//4. Supplier GST Number
									//5. Invoice Date
	private Date postingDt;			//6. Posting Date
									//7. Invoice No
	private String impDecNo;		//8. Import Declaration Number
									//9. Line Number
									//10. Product Description
									//11. Purchase Value (in MYR)
									//12. Purchase Value GST Amount
									//13. Tax Code
									//14. Foreign Currency Code
									//15. Purchase Foreign Currency Amount
									//16. Purchase Currency Purchase Amount GST
	
	// Ledger
									//1. Record Identifier
	private Date transDt;			//2. Transaction Date
	private String acctCd;			//3. Account ID
	private String acctType;		//4. Account Type 
	private String acctDesc;		//5. Account Name	INVENTORY
	private String transDesc;		//6. Transaction Description	INVENTORY
	private String entityName;		//7. Entity Name BLANK
	private String transId;			//8. Transaction ID	2945
	private String sourceDocId;		//9. Source Document ID	SIV16000276
	private String sourceType;		//10. Source Type			Invoice Payment
	private Double debit;			//11. Debit
	private Double credit;			//12. Credit
	private Double balance;			//13. Balance
	
	// Footer
									//1. Record Identifier
	private int purcCount;			//2. Purchase Count
	private Double purcTtlAmt;		//3. Purchase Amount Sum
	private Double purcTtlGSTAmt;	//4. Purchase GST Amount GST
	private int suppCount;			//5. Supply Count
	private Double suppTtlAmt;		//6. Supply Amount Sum
	private Double suppTtlGSTAmt;	//7. Supply GST Amount Sum
	private int ledgerCount;		//8. Ledger Count
	private Double debitTtl;		//9. Debit Sum
	private Double creditTtl;		//10. Credit Sum
	private Double balanceTtl;		//11. Closing Balance Sum
	
	/**
	 * @return the recordId
	 */
	public String getRecordId() {
		return recordId;
	}
	/**
	 * @param recordId the recordId to set
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	/**
	 * @return the custName
	 */
	public String curTaxAmount() {
		return custName;
	}
	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * @return the custBRN
	 */
	public String getCustBRN() {
		return custBRN;
	}
	/**
	 * @param custBRN the custBRN to set
	 */
	public void setCustBRN(String custBRN) {
		this.custBRN = custBRN;
	}
	/**
	 * @return the invoiceDt
	 */
	public Date getInvoiceDt() {
		return invoiceDt;
	}
	/**
	 * @param invoiceDt the invoiceDt to set
	 */
	public void setInvoiceDt(Date invoiceDt) {
		this.invoiceDt = invoiceDt;
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
	 * @return the lineNo
	 */
	public String getLineNo() {
		return lineNo;
	}
	/**
	 * @param lineNo the lineNo to set
	 */
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the taxAmount
	 */
	public Double getTaxAmount() {
		return taxAmount;
	}
	/**
	 * @param taxAmount the taxAmount to set
	 */
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	/**
	 * @return the taxCode
	 */
	public String getTaxCode() {
		return taxCode;
	}
	/**
	 * @param taxCode the taxCode to set
	 */
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the curCode
	 */
	public String getCurCode() {
		return curCode;
	}
	/**
	 * @param curCode the curCode to set
	 */
	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}
	/**
	 * @return the curAmount
	 */
	public Double getCurAmount() {
		return curAmount;
	}
	/**
	 * @param curAmount the curAmount to set
	 */
	public void setCurAmount(Double curAmount) {
		this.curAmount = curAmount;
	}
	/**
	 * @return the curTaxAmount
	 */
	public Double getCurTaxAmount() {
		return curTaxAmount;
	}
	/**
	 * @param curTaxAmount the curTaxAmount to set
	 */
	public void setCurTaxAmount(Double curTaxAmount) {
		this.curTaxAmount = curTaxAmount;
	}
	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}
	/**
	 * @param supplierName the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	/**
	 * @return the supplierBRN
	 */
	public String getSupplierBRN() {
		return supplierBRN;
	}
	/**
	 * @param supplierBRN the supplierBRN to set
	 */
	public void setSupplierBRN(String supplierBRN) {
		this.supplierBRN = supplierBRN;
	}
	/**
	 * @return the impDecNo
	 */
	public String getImpDecNo() {
		return impDecNo;
	}
	/**
	 * @param impDecNo the impDecNo to set
	 */
	public void setImpDecNo(String impDecNo) {
		this.impDecNo = impDecNo;
	}
	/**
	 * @return the postingDt
	 */
	public Date getPostingDt() {
		return postingDt;
	}
	/**
	 * @param postingDt the postingDt to set
	 */
	public void setPostingDt(Date postingDt) {
		this.postingDt = postingDt;
	}
	/**
	 * @return the custGSTNo
	 */
	public String getCustGSTNo() {
		return custGSTNo;
	}
	/**
	 * @param custGSTNo the custGSTNo to set
	 */
	public void setCustGSTNo(String custGSTNo) {
		this.custGSTNo = custGSTNo;
	}
	/**
	 * @return the expDecNo
	 */
	public String getExpDecNo() {
		return expDecNo;
	}
	/**
	 * @param expDecNo the expDecNo to set
	 */
	public void setExpDecNo(String expDecNo) {
		this.expDecNo = expDecNo;
	}
	/**
	 * @return the supplierGSTNo
	 */
	public String getSupplierGSTNo() {
		return supplierGSTNo;
	}
	/**
	 * @param supplierGSTNo the supplierGSTNo to set
	 */
	public void setSupplierGSTNo(String supplierGSTNo) {
		this.supplierGSTNo = supplierGSTNo;
	}
	/**
	 * @return the transDt
	 */
	public Date getTransDt() {
		return transDt;
	}
	/**
	 * @param transDt the transDt to set
	 */
	public void setTransDt(Date transDt) {
		this.transDt = transDt;
	}
	/**
	 * @return the acctCd
	 */
	public String getAcctCd() {
		return acctCd;
	}
	/**
	 * @param acctCd the acctCd to set
	 */
	public void setAcctCd(String acctCd) {
		this.acctCd = acctCd;
	}
	/**
	 * @return the acctType
	 */
	public String getAcctType() {
		return acctType;
	}
	/**
	 * @param acctType the acctType to set
	 */
	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	/**
	 * @return the acctDesc
	 */
	public String getAcctDesc() {
		return acctDesc;
	}
	/**
	 * @param acctDesc the acctDesc to set
	 */
	public void setAcctDesc(String acctDesc) {
		this.acctDesc = acctDesc;
	}
	/**
	 * @return the transDesc
	 */
	public String getTransDesc() {
		return transDesc;
	}
	/**
	 * @param transDesc the transDesc to set
	 */
	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
	}
	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	/**
	 * @return the transId
	 */
	public String getTransId() {
		return transId;
	}
	/**
	 * @param transId the transId to set
	 */
	public void setTransId(String transId) {
		this.transId = transId;
	}
	/**
	 * @return the sourceDocId
	 */
	public String getSourceDocId() {
		return sourceDocId;
	}
	/**
	 * @param sourceDocId the sourceDocId to set
	 */
	public void setSourceDocId(String sourceDocId) {
		this.sourceDocId = sourceDocId;
	}
	/**
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}
	/**
	 * @param sourceType the sourceType to set
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
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
	 * @return the purcCount
	 */
	public int getPurcCount() {
		return purcCount;
	}
	/**
	 * @param purcCount the purcCount to set
	 */
	public void setPurcCount(int purcCount) {
		this.purcCount = purcCount;
	}
	/**
	 * @return the purcTtlAmt
	 */
	public Double getPurcTtlAmt() {
		return purcTtlAmt;
	}
	/**
	 * @param purcTtlAmt the purcTtlAmt to set
	 */
	public void setPurcTtlAmt(Double purcTtlAmt) {
		this.purcTtlAmt = purcTtlAmt;
	}
	/**
	 * @return the purcTtlGSTAmt
	 */
	public Double getPurcTtlGSTAmt() {
		return purcTtlGSTAmt;
	}
	/**
	 * @param purcTtlGSTAmt the purcTtlGSTAmt to set
	 */
	public void setPurcTtlGSTAmt(Double purcTtlGSTAmt) {
		this.purcTtlGSTAmt = purcTtlGSTAmt;
	}
	/**
	 * @return the suppCount
	 */
	public int getSuppCount() {
		return suppCount;
	}
	/**
	 * @param suppCount the suppCount to set
	 */
	public void setSuppCount(int suppCount) {
		this.suppCount = suppCount;
	}
	/**
	 * @return the suppTtlAmt
	 */
	public Double getSuppTtlAmt() {
		return suppTtlAmt;
	}
	/**
	 * @param suppTtlAmt the suppTtlAmt to set
	 */
	public void setSuppTtlAmt(Double suppTtlAmt) {
		this.suppTtlAmt = suppTtlAmt;
	}
	/**
	 * @return the suppTtlGSTAmt
	 */
	public Double getSuppTtlGSTAmt() {
		return suppTtlGSTAmt;
	}
	/**
	 * @param suppTtlGSTAmt the suppTtlGSTAmt to set
	 */
	public void setSuppTtlGSTAmt(Double suppTtlGSTAmt) {
		this.suppTtlGSTAmt = suppTtlGSTAmt;
	}
	
	/**
	 * @return the ledgerCount
	 */
	public int getLedgerCount() {
		return ledgerCount;
	}
	/**
	 * @param ledgerCount the ledgerCount to set
	 */
	public void setLedgerCount(int ledgerCount) {
		this.ledgerCount = ledgerCount;
	}
	/**
	 * @return the debitTtl
	 */
	public Double getDebitTtl() {
		return debitTtl;
	}
	/**
	 * @param debitTtl the debitTtl to set
	 */
	public void setDebitTtl(Double debitTtl) {
		this.debitTtl = debitTtl;
	}
	/**
	 * @return the creditTtl
	 */
	public Double getCreditTtl() {
		return creditTtl;
	}
	/**
	 * @param creditTtl the creditTtl to set
	 */
	public void setCreditTtl(Double creditTtl) {
		this.creditTtl = creditTtl;
	}
	/**
	 * @return the balanceTtl
	 */
	public Double getBalanceTtl() {
		return balanceTtl;
	}
	/**
	 * @param balanceTtl the balanceTtl to set
	 */
	public void setBalanceTtl(Double balanceTtl) {
		this.balanceTtl = balanceTtl;
	}
}
