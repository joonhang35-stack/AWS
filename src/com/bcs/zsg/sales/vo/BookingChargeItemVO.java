package com.bcs.zsg.sales.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class BookingChargeItemVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long idBooking;
	private Long idInvEoItem;
	private Long idAcct;
	private String code;
	private String desc;
	private Integer quantity;
	private Double amount;
	private Double airfare = 0.0;
	private String typeCd;
	private Double ttlAmt;
	private Boolean isMisc = false;
	private Boolean isLock = false;

	private Integer quantity3 = 0;
	private Integer quantity4 = 0;
	private Integer quantity3Chd = 0;
	private Integer quantity4Chd = 0;
	private Integer quantitySgl = 0;
	private Integer quantityInf = 0;
	private Integer cabinQty = 0;
	private Double amount3 = 0.00;
	private Double amount4 = 0.00;
	private Double amountSgl = 0.00;
	private Double amount3Chd = 0.00;
	private Double amount4Chd = 0.00;
	private Double amountInf = 0.00;
	private Long idTourCruiseCabin;

	public Integer getQuantity3Chd() {
		return quantity3Chd;
	}

	public Integer getQuantity4Chd() {
		return quantity4Chd;
	}

	public Double getAmount3Chd() {
		return amount3Chd;
	}

	public Double getAmount4Chd() {
		return amount4Chd;
	}

	public void setQuantity3Chd(Integer quantity3Chd) {
		this.quantity3Chd = quantity3Chd;
	}

	public void setQuantity4Chd(Integer quantity4Chd) {
		this.quantity4Chd = quantity4Chd;
	}

	public void setAmount3Chd(Double amount3Chd) {
		this.amount3Chd = amount3Chd;
	}

	public void setAmount4Chd(Double amount4Chd) {
		this.amount4Chd = amount4Chd;
	}

	public Long getIdTourCruiseCabin() {
		return idTourCruiseCabin;
	}

	public void setIdTourCruiseCabin(Long idTourCruiseCabin) {
		this.idTourCruiseCabin = idTourCruiseCabin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getQuantity3() {
		return quantity3;
	}

	public Integer getQuantity4() {
		return quantity4;
	}

	public Integer getQuantitySgl() {
		return quantitySgl;
	}

	public Integer getQuantityInf() {
		return quantityInf;
	}

	public Integer getCabinQty() {
		return cabinQty;
	}

	public Double getAmount3() {
		return amount3;
	}

	public Double getAmount4() {
		return amount4;
	}

	public Double getAmountSgl() {
		return amountSgl;
	}

	public Double getAmountInf() {
		return amountInf;
	}

	public void setQuantity3(Integer quantity3) {
		this.quantity3 = quantity3;
	}

	public void setQuantity4(Integer quantity4) {
		this.quantity4 = quantity4;
	}

	public void setQuantitySgl(Integer quantitySgl) {
		this.quantitySgl = quantitySgl;
	}

	public void setQuantityInf(Integer quantityInf) {
		this.quantityInf = quantityInf;
	}

	public void setCabinQty(Integer cabinQty) {
		this.cabinQty = cabinQty;
	}

	public void setAmount3(Double amount3) {
		this.amount3 = amount3;
	}

	public void setAmount4(Double amount4) {
		this.amount4 = amount4;
	}

	public void setAmountSgl(Double amountSgl) {
		this.amountSgl = amountSgl;
	}

	public void setAmountInf(Double amountInf) {
		this.amountInf = amountInf;
	}

	/**
	 * @return the idBooking
	 */
	public Long getIdBooking() {
		return idBooking;
	}

	/**
	 * @param idBooking the idBooking to set
	 */
	public void setIdBooking(Long idBooking) {
		this.idBooking = idBooking;
	}

	/**
	 * @return the idInvEoItem
	 */
	public Long getIdInvEoItem() {
		return idInvEoItem;
	}

	/**
	 * @param idInvEoItem the idInvEoItem to set
	 */
	public void setIdInvEoItem(Long idInvEoItem) {
		this.idInvEoItem = idInvEoItem;
	}

	/**
	 * @return the idAcct
	 */
	public Long getIdAcct() {
		return idAcct;
	}

	/**
	 * @param idAcct the idAcct to set
	 */
	public void setIdAcct(Long idAcct) {
		this.idAcct = idAcct;
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
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public Double getTtlAmt() {
		return ttlAmt;
	}

	public void setTtlAmt(Double ttlAmt) {
		this.ttlAmt = ttlAmt;
	}

	public Boolean getIsLock() {
		return isLock;
	}

	public void setIsLock(Boolean isLock) {
		this.isLock = isLock;
	}

	public Double getAirfare() {
		return airfare;
	}

	public void setAirfare(Double airfare) {
		this.airfare = airfare;
	}

	public Boolean getIsMisc() {
		return isMisc;
	}

	public void setIsMisc(Boolean isMisc) {
		this.isMisc = isMisc;
	}
}
