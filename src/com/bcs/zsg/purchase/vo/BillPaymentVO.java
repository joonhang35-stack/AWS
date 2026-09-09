package com.bcs.zsg.purchase.vo;

import java.util.Date;

import com.bcs.zsg.acct.helper.TaxableAmount;
import com.bcs.zsg.core.vo.BaseVO;

public class BillPaymentVO  extends BaseVO implements TaxableAmount {
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String refNum;
	private String payee;
	private String desc;
	private Date pymtDt;
	private Long id;
	private Long eoBillId;
	private Long bankId;
	private Double chqAmount = 0.0;
	private Double pmntAmount = 0.0;
	private Long acctId;
	private String bankName;
	private String bankAcct;
	private boolean isNotDel;
	private String pmntType;
	
	private String taxCode;
	private Float taxRate;
	private Double taxAmount=0.0;
	private double amountIncludeTax;
	private boolean isDateInPeriodClosed;

	public Double getPmntAmount() {
		return pmntAmount;
	}

	public void setPmntAmount(Double pmntAmount) {
		this.pmntAmount = pmntAmount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRefNum() {
		return refNum;
	}
	public void setRefNum(String refNum) {
		this.refNum = refNum;
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

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getPymtDt() {
		return pymtDt;
	}
	public void setPymtDt(Date pymtDt) {
		this.pymtDt = pymtDt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEoBillId() {
		return eoBillId;
	}
	public void setEoBillId(Long eoBillId) {
		this.eoBillId = eoBillId;
	}
	public Long getBankId() {
		return bankId;
	}
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	/**
	 * @return the acctId
	 */
	public Long getAcctId() {
		return acctId;
	}

	/**
	 * @param acctId the acctId to set
	 */
	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the bankAcct
	 */
	public String getBankAcct() {
		return bankAcct;
	}

	/**
	 * @param bankAcct the bankAcct to set
	 */
	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}

	/**
	 * @return the isNotDel
	 */
	public boolean isNotDel() {
		return isNotDel;
	}

	/**
	 * @param isNotDel the isNotDel to set
	 */
	public void setNotDel(boolean isNotDel) {
		this.isNotDel = isNotDel;
	}

	/**
	 * @return the chqAmount
	 */
	public Double getChqAmount() {
		return chqAmount;
	}

	/**
	 * @param chqAmount the chqAmount to set
	 */
	public void setChqAmount(Double chqAmount) {
		this.chqAmount = chqAmount;
	}

	/**
	 * @return the pmntType
	 */
	public String getPmntType() {
		return pmntType;
	}

	/**
	 * @param pmntType the pmntType to set
	 */
	public void setPmntType(String pmntType) {
		this.pmntType = pmntType;
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

	/**
	 * @return the amountIncludeTax
	 */
	public double getAmountIncludeTax() {
		return amountIncludeTax;
	}

	/**
	 * @param amountIncludeTax the amountIncludeTax to set
	 */
	public void setAmountIncludeTax(double amountIncludeTax) {
		this.amountIncludeTax = amountIncludeTax;
	}
	
	@Override
	public double getDAmount() {
		return getPmntAmount();
	}
	@Override
	public void setDAmount(double amount) {
		setPmntAmount(amount);
	}
	@Override
	public double getDTaxRate() {
		return getTaxRate();
	}
	@Override
	public double getDTaxAmount() {
		return getTaxAmount();
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

	/**
	 * @return the isDateInPeriodClosed
	 */
	public boolean isDateInPeriodClosed() {
		return isDateInPeriodClosed;
	}

	/**
	 * @param isDateInPeriodClosed the isDateInPeriodClosed to set
	 */
	public void setDateInPeriodClosed(boolean isDateInPeriodClosed) {
		this.isDateInPeriodClosed = isDateInPeriodClosed;
	}
}
