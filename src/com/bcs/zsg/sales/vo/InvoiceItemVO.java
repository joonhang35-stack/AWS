package com.bcs.zsg.sales.vo;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.bcs.zsg.acct.helper.TaxableAmount;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderVO;

public class InvoiceItemVO extends BaseVO implements TaxableAmount {
	private static final long serialVersionUID = 1L;

	private Long invId;
	private Long acctId;
	private Long idPreSalesAcct;
	private String salesAcctCd; // Sales account code
	private String salesAcctDesc; // Sales account description
	private Long invEOItemId;
	private String invItemCd; // Invoice item code
	private Long idInvVoucher;
	private String code;
	private String desc;
	private String subDesc;
	private Long airlineId; // For ticketing
	private Integer qty;
	private Double unitPrice;
	private Double itemTourFare;
	private Double itemPercentage;
	private Double netPrice; // For ticketing
	private Double currencyPrice; // For FIT/Cruise
	private Double amount;
	private Double airfare = 0.0;
	private Double tourFare;
	private Double taxIncAmt;
	private Double refundAmt = 0.0;
	private String invVoucherRef;
	private Integer seq;
	private boolean isSyncBooking = false;
	private Integer prevQty;
	private Boolean changedLock;
	private Boolean isLock = false;

	private Long idTax;
	private String taxCode;
	private Float taxRate;
	private Double taxAmount = 0.0;

	private String itemCode;
	private boolean showInInv;

	private String packageType;
	private InvoiceAndExchangeOrderVO invoiceAndExchangeOrderVO;
	private String classificationCode;
	private String typeCd;

	private long currencyId;
	private double exRate;
	
	public void convertToInvoiceItemVO(InvoiceVoucherVO invVoucherVO, String desc, BookingViewVO bookingViewVO, boolean isAllowMoreThanOne) throws BusinessException {
		
		System.out.println("CYY isAllowMoreThanOne: " + isAllowMoreThanOne);
		
		if (bookingViewVO.getQuantity() != null) {
			if (isAllowMoreThanOne)	invVoucherVO.setQty(bookingViewVO.getQuantity());
			invVoucherVO.calcAmount();
		}
		
		setDesc(desc);

		setInvVoucherRef(invVoucherVO.getVoucherId());
		setUnitPrice(invVoucherVO.getUnitPrice());
		setQty(invVoucherVO.getQty());
		setAmount(invVoucherVO.getAmount());
		setIsLock(true);
		
		if(CollectionUtils.isNotEmpty(bookingViewVO.getBookingChargeItemList())) {
			for (BookingChargeItemVO vo : bookingViewVO.getBookingChargeItemList()) {
				if (StringUtils.equalsIgnoreCase(vo.getCode(), ProductConstant.TOUR_DEP_ITM_CD_DISC)) {
					setAcctId(vo.getIdAcct());
				}
			}
		}
	}
	
	/**
	 * @return voucher id at CRM side
	 */
	public String getInvVoucherRef() {
		return invVoucherRef;
	}

	/**
	 * @param invVoucherRef - voucher id at CRM side
	 */
	public void setInvVoucherRef(String invVoucherRef) {
		this.invVoucherRef = invVoucherRef;
	}

	public Long getIdInvVoucher() {
		return idInvVoucher;
	}

	public void setIdInvVoucher(Long idInvVoucher) {
		this.idInvVoucher = idInvVoucher;
	}

	public String getTypeCd() {
		return typeCd;
	}

	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	public String getClassificationCode() {
		return classificationCode;
	}

	public void setClassificationCode(String classificationCode) {
		this.classificationCode = classificationCode;
	}

	public InvoiceAndExchangeOrderVO getInvoiceAndExchangeOrderVO() {
		return invoiceAndExchangeOrderVO;
	}

	public void setInvoiceAndExchangeOrderVO(InvoiceAndExchangeOrderVO invoiceAndExchangeOrderVO) {
		this.invoiceAndExchangeOrderVO = invoiceAndExchangeOrderVO;
	}

	public Long getIdPreSalesAcct() {
		return idPreSalesAcct;
	}

	public void setIdPreSalesAcct(Long idPreSalesAcct) {
		this.idPreSalesAcct = idPreSalesAcct;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public boolean isShowInInv() {
		return showInInv;
	}

	public void setShowInInv(boolean showInInv) {
		this.showInInv = showInInv;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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
	 * @return the invEOItemId
	 */
	public Long getInvEOItemId() {
		return invEOItemId;
	}

	/**
	 * @param invEOItemId the invEOItemId to set
	 */
	public void setInvEOItemId(Long invEOItemId) {
		this.invEOItemId = invEOItemId;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the qty
	 */
	public Integer getQty() {
		return qty;
	}

	/**
	 * @param qty the qty to set
	 */
	public void setQty(Integer qty) {
		this.qty = qty;
	}

	/**
	 * @return the unitPrice
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
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
	 * @return the salesAcctCd
	 */
	public String getSalesAcctCd() {
		return salesAcctCd;
	}

	/**
	 * @param salesAcctCd the salesAcctCd to set
	 */
	public void setSalesAcctCd(String salesAcctCd) {
		this.salesAcctCd = salesAcctCd;
	}

	/**
	 * @return the invItemCd
	 */
	public String getInvItemCd() {
		return invItemCd;
	}

	/**
	 * @param invItemCd the invItemCd to set
	 */
	public void setInvItemCd(String invItemCd) {
		this.invItemCd = invItemCd;
	}

	/**
	 * @return the salesAcctDesc
	 */
	public String getSalesAcctDesc() {
		return salesAcctDesc;
	}

	/**
	 * @param salesAcctDesc the salesAcctDesc to set
	 */
	public void setSalesAcctDesc(String salesAcctDesc) {
		this.salesAcctDesc = salesAcctDesc;
	}

	/**
	 * @return the netPrice
	 */
	public Double getNetPrice() {
		return netPrice;
	}

	/**
	 * @param netPrice the netPrice to set
	 */
	public void setNetPrice(Double netPrice) {
		this.netPrice = netPrice;
	}

	/**
	 * @return the airlineId
	 */
	public Long getAirlineId() {
		return airlineId;
	}

	/**
	 * @param airlineId the airlineId to set
	 */
	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}

	/**
	 * @return the idTax
	 */
	public Long getIdTax() {
		return idTax;
	}

	/**
	 * @param idTax the idTax to set
	 */
	public void setIdTax(Long idTax) {
		this.idTax = idTax;
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
	 * @return the taxIncAmt
	 */
	public Double getTaxIncAmt() {
		return taxIncAmt;
	}

	/**
	 * @param taxIncAmt the taxIncAmt to set
	 */
	public void setTaxIncAmt(Double taxIncAmt) {
		this.taxIncAmt = taxIncAmt;
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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Override
	public double getDAmount() {
		return (getAmount() == null ? 0.00 : getAmount());
	}

	@Override
	public void setDAmount(double amount) {
		setAmount(amount);
	}

	@Override
	public double getDTaxRate() {
		return (getTaxRate() == null ? 0.00 : getTaxRate());
	}

	@Override
	public double getDTaxAmount() {
		return (getTaxAmount() == null ? 0.00 : getTaxAmount());
	}

	@Override
	public void setDTaxAmount(double taxAmount) {
		setTaxAmount(taxAmount);
	}

	@Override
	public double getDAmountIncludeTax() {
		return (getTaxIncAmt() == null ? 0.00 : getTaxIncAmt());
	}

	@Override
	public void setDAmountIncludeTax(double amountIncludeTax) {
		setTaxIncAmt(amountIncludeTax);
	}

	public boolean isSyncBooking() {
		return isSyncBooking;
	}

	public void setSyncBooking(boolean isSyncBooking) {
		this.isSyncBooking = isSyncBooking;
	}

	public Integer getPrevQty() {
		return prevQty;
	}

	public void setPrevQty(Integer prevQty) {
		this.prevQty = prevQty;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getIsLock() {
		return isLock;
	}

	public void setIsLock(Boolean isLock) {
		this.isLock = isLock;
	}

	public Boolean getChangedLock() {
		return changedLock;
	}

	public void setChangedLock(Boolean changedLock) {
		this.changedLock = changedLock;
	}

	public String getSubDesc() {
		return subDesc;
	}

	public void setSubDesc(String subDesc) {
		this.subDesc = subDesc;
	}

	public Double getAirfare() {
		return airfare;
	}

	public void setAirfare(Double airfare) {
		this.airfare = airfare;
	}

	public Double getTourFare() {
		return tourFare;
	}

	public void setTourFare(Double tourFare) {
		this.tourFare = tourFare;
	}

	public Double getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(Double refundAmt) {
		this.refundAmt = refundAmt;
	}

	public double getExRate() {
		return exRate;
	}

	public void setExRate(double exRate) {
		this.exRate = exRate;
	}

	public long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(long currencyId) {
		this.currencyId = currencyId;
	}

	public Double getCurrencyPrice() {
		return currencyPrice;
	}

	public void setCurrencyPrice(Double currencyPrice) {
		this.currencyPrice = currencyPrice;
	}

	public Double getItemTourFare() {
		return itemTourFare;
	}

	public void setItemTourFare(Double itemTourFare) {
		this.itemTourFare = itemTourFare;
	}

	public Double getItemPercentage() {
		return itemPercentage;
	}

	public void setItemPercentage(Double itemPercentage) {
		this.itemPercentage = itemPercentage;
	}
}
