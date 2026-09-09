 package com.bcs.zsg.acct.vo;

import java.util.Date;
import java.util.List;

import com.bcs.zsg.acct.helper.TaxableAmount;
import com.bcs.zsg.core.vo.BaseVO;

public class AcctTransVO extends BaseVO implements TaxableAmount {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sysCode;
	private String sysPrefix;
	private String sysNo;
	private String refNo;
	private String source;
	private String destination;
	private String desc;
	private String eInvoiceClassCode;
	private String type;
	private String code;
	private Double debit=0.0;
	private Double credit=0.0;
	private Date transDt;
	private boolean notDel;
	private Long companyId;
	private Long acctId;
	private Long refId;
	private String tempAcct;
	private List<AcctTransVO> acctTransList;
	private String acctDesc;
	
	private double amount;
	private String taxCode;
	private Float taxRate;
	private Double taxAmount=0.0;
	private double amountIncludeTax;

	public String geteInvoiceClassCode() {
		return eInvoiceClassCode;
	}
	public void seteInvoiceClassCode(String eInvoiceClassCode) {
		this.eInvoiceClassCode = eInvoiceClassCode;
	}
	/**
	 * @return the sysCode
	 */
	public String getSysCode() {
		return sysCode;
	}
	/**
	 * @param sysCode the sysCode to set
	 */
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public String getSysNo() {
		return sysNo;
	}
	public void setSysNo(String sysNo) {
		this.sysNo = sysNo;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getDebit() {
		return debit;
	}
	public void setDebit(Double debit) {
		this.debit = debit;
		if (debit.doubleValue() > 0) setAmount(debit);
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
		if (credit.doubleValue() > 0) setAmount(-credit);
	}
	public Date getTransDt() {
		return transDt;
	}
	public void setTransDt(Date transDt) {
		this.transDt = transDt;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getAcctId() {
		return acctId;
	}
	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}
	/**
	 * @return the refId
	 */
	public Long getRefId() {
		return refId;
	}
	/**
	 * @param refId the refId to set
	 */
	public void setRefId(Long refId) {
		this.refId = refId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<AcctTransVO> getAcctTransList() {
		return acctTransList;
	}
	public void setAcctTransList(List<AcctTransVO> acctTransList) {
		this.acctTransList = acctTransList;
	}

	public boolean isNotDel() {
		return notDel;
	}
	public void setNotDel(boolean notDel) {
		this.notDel = notDel;
	}

	public String getTempAcct() {
		return tempAcct;
	}
	public void setTempAcct(String tempAcct) {
		this.tempAcct = tempAcct;
	}
	public String getSysPrefix() {
		return sysPrefix;
	}
	public void setSysPrefix(String sysPrefix) {
		this.sysPrefix = sysPrefix;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
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
	 * @return the taxRate
	 */
	public Float getTaxRate() {
		return taxRate;
	}
	/**
	 * @param taxRate the taxRate to set
	 */
	public void setTaxRate(Float taxRate) {
		this.taxRate = taxRate;
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
		return getTaxRate() == null ? 0.00 : getTaxRate();
	}
	@Override
	public double getDTaxAmount() {
		return getTaxAmount() == null ? 0.00 : getTaxAmount();
	}
	@Override
	public void setDTaxAmount(double amount) {
		setTaxAmount(amount);
	}
	@Override
	public double getDAmountIncludeTax() {
		return amountIncludeTax;
	}
	@Override
	public void setDAmountIncludeTax(double amountIncludeTax) {
		setAmountIncludeTax(amountIncludeTax);
	}
	public double getAmountIncludeTax() {
		return amountIncludeTax;
	}
	public void setAmountIncludeTax(double amountIncludeTax) {
		this.amountIncludeTax = amountIncludeTax;
	}
	public String getAcctDesc() {
		return acctDesc;
	}
	public void setAcctDesc(String acctDesc) {
		this.acctDesc = acctDesc;
	}
}
