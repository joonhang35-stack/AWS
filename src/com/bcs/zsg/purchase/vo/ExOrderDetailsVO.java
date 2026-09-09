package com.bcs.zsg.purchase.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class ExOrderDetailsVO  extends BaseVO{
	private static final long serialVersionUID = 1L;
	
	/* 
	 * Exchange Order Details
	 */
	
	private String desc;
	private String currency;
	private Double price;
	private Double amount;
	private Double totalAmt;
	private Double exRate;
	private Double foreCurAmt;
	private Double totalForeCurAmt;
	private Integer qty;
	private Long exOrderId;
	private Long currencyId;
	private Integer seq;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}
	public Long getExOrderId() {
		return exOrderId;
	}
	public void setExOrderId(Long exOrderId) {
		this.exOrderId = exOrderId;
	}
	public Double getExRate() {
		return exRate;
	}
	public void setExRate(Double exRate) {
		this.exRate = exRate;
	}
	public Double getForeCurAmt() {
		return foreCurAmt;
	}
	public void setForeCurAmt(Double foreCurAmt) {
		this.foreCurAmt = foreCurAmt;
	}
	public Long getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getTotalForeCurAmt() {
		return totalForeCurAmt;
	}
	public void setTotalForeCurAmt(Double totalForeCurAmt) {
		this.totalForeCurAmt = totalForeCurAmt;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
}
