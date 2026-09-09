package com.bcs.zsg.db.bterp.vo;

import java.math.BigDecimal;

import com.bcs.zsg.core.vo.BaseVO;

public class AmountCalcViewVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idRoundingAcct;
	private Long idTaxAcct;
	private Long idTaxNonClaimableAcct;

	private BigDecimal totalAmountIncludeTax;
	private BigDecimal totalAmountPaid;
	private BigDecimal totalTax;

	private BigDecimal totalNonClaimableTax;

	private BigDecimal totalAmountAfterRounding;
	private BigDecimal totalRounding;
	private BigDecimal totalBalance;

	// for SST calculation
	private BigDecimal subtotal;
	private BigDecimal sstAmt;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public BigDecimal getSstAmt() {
		return sstAmt;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public void setSstAmt(BigDecimal sstAmt) {
		this.sstAmt = sstAmt;
	}

	public Long getIdRoundingAcct() {
		return idRoundingAcct;
	}

	public void setIdRoundingAcct(Long idRoundingAcct) {
		this.idRoundingAcct = idRoundingAcct;
	}

	public Long getIdTaxAcct() {
		return idTaxAcct;
	}

	public void setIdTaxAcct(Long idTaxAcct) {
		this.idTaxAcct = idTaxAcct;
	}

	public BigDecimal getTotalAmountIncludeTax() {
		return totalAmountIncludeTax;
	}

	public void setTotalAmountIncludeTax(BigDecimal totalAmountIncludeTax) {
		this.totalAmountIncludeTax = totalAmountIncludeTax;
	}

	public BigDecimal getTotalAmountPaid() {
		return totalAmountPaid;
	}

	public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
		this.totalAmountPaid = totalAmountPaid;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public BigDecimal getTotalAmountAfterRounding() {
		return totalAmountAfterRounding;
	}

	public void setTotalAmountAfterRounding(BigDecimal totalAmountAfterRounding) {
		this.totalAmountAfterRounding = totalAmountAfterRounding;
	}

	public BigDecimal getTotalRounding() {
		return totalRounding;
	}

	public void setTotalRounding(BigDecimal totalRounding) {
		this.totalRounding = totalRounding;
	}

	public BigDecimal getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}

	public Long getIdTaxNonClaimableAcct() {
		return idTaxNonClaimableAcct;
	}

	public void setIdTaxNonClaimableAcct(Long idTaxNonClaimableAcct) {
		this.idTaxNonClaimableAcct = idTaxNonClaimableAcct;
	}

	public BigDecimal getTotalNonClaimableTax() {
		return totalNonClaimableTax;
	}

	public void setTotalNonClaimableTax(BigDecimal totalNonClaimableTax) {
		this.totalNonClaimableTax = totalNonClaimableTax;
	}
}
