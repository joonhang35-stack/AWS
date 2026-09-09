package com.bcs.zsg.db.bterp.vo.gst;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class GSTVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private double openingBalance;
	private double closingBalance;
	
	private boolean amendment;
	private String gstNo;
	private String nameOfBussiness;
	private Date dateStart;
	private Date dateEnd;
	private Date datePayment;
	
	private double totalSupply;
	private double totalOutputTax;
	private double totalAcquisitions;
	private double totalInputTax;
	private double amountPayable;
	private double amountClaimable;
	private boolean carryForwardRefund;
	
	private double totalLocalZeroRatedSupplies;
	private double totalExportSupplies;
	private double totalExemptSupplies;
	private double totalSuppliesGrantedRelief;
	private double totalGoodsImportedApproved;
	private double totalSuspendedUnder14;
	private double totalCapitalGoodsAcquired;
	private double totalBadDebtRelief;
	private double totalBadDebtRecovered;
	
	private String msicCode1;
	private String msicCode2;
	private String msicCode3;
	private String msicCode4;
	private String msicCode5;
	
	private double msicOutputTax1;
	private double msicOutputTax2;
	private double msicOutputTax3;
	private double msicOutputTax4;
	private double msicOutputTax5;
	private double msicOutputTax6;
	private double msicOutputTax7;
	
	private double msicPercentage1;
	private double msicPercentage2;
	private double msicPercentage3;
	private double msicPercentage4;
	private double msicPercentage5;
	private double msicPercentage6;
	
	private String authorizedPerson;
	private String icNoNew;
	private String icNoOld;
	private String passportNo;
	private String nationality;
	private Date date;
	private String signature;
	private Date receivedDate;
	private Date postmarkDate;
	
	public boolean isAmendment() {
		return amendment;
	}
	public void setAmendment(boolean amendment) {
		this.amendment = amendment;
	}
	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	public String getNameOfBussiness() {
		return nameOfBussiness;
	}
	public void setNameOfBussiness(String nameOfBussiness) {
		this.nameOfBussiness = nameOfBussiness;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Date getDatePayment() {
		return datePayment;
	}
	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}
	public double getTotalSupply() {
		return totalSupply;
	}
	public void setTotalSupply(double totalSupply) {
		this.totalSupply = totalSupply;
	}
	public double getTotalOutputTax() {
		return totalOutputTax;
	}
	public void setTotalOutputTax(double totalOutputTax) {
		this.totalOutputTax = totalOutputTax;
	}
	public double getTotalAcquisitions() {
		return totalAcquisitions;
	}
	public void setTotalAcquisitions(double totalAcquisitions) {
		this.totalAcquisitions = totalAcquisitions;
	}
	public double getTotalInputTax() {
		return totalInputTax;
	}
	public void setTotalInputTax(double totalInputTax) {
		this.totalInputTax = totalInputTax;
	}
	public double getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(double amountPayable) {
		this.amountPayable = amountPayable;
	}
	public double getAmountClaimable() {
		return amountClaimable;
	}
	public void setAmountClaimable(double amountClaimable) {
		this.amountClaimable = amountClaimable;
	}
	public boolean isCarryForwardRefund() {
		return carryForwardRefund;
	}
	public void setCarryForwardRefund(boolean carryForwardRefund) {
		this.carryForwardRefund = carryForwardRefund;
	}
	public double getTotalLocalZeroRatedSupplies() {
		return totalLocalZeroRatedSupplies;
	}
	public void setTotalLocalZeroRatedSupplies(double totalLocalZeroRatedSupplies) {
		this.totalLocalZeroRatedSupplies = totalLocalZeroRatedSupplies;
	}
	public double getTotalExportSupplies() {
		return totalExportSupplies;
	}
	public void setTotalExportSupplies(double totalExportSupplies) {
		this.totalExportSupplies = totalExportSupplies;
	}
	public double getTotalExemptSupplies() {
		return totalExemptSupplies;
	}
	public void setTotalExemptSupplies(double totalExemptSupplies) {
		this.totalExemptSupplies = totalExemptSupplies;
	}
	public double getTotalSuppliesGrantedRelief() {
		return totalSuppliesGrantedRelief;
	}
	public void setTotalSuppliesGrantedRelief(double totalSuppliesGrantedRelief) {
		this.totalSuppliesGrantedRelief = totalSuppliesGrantedRelief;
	}
	public double getTotalGoodsImportedApproved() {
		return totalGoodsImportedApproved;
	}
	public void setTotalGoodsImportedApproved(double totalGoodsImportedApproved) {
		this.totalGoodsImportedApproved = totalGoodsImportedApproved;
	}
	public double getTotalSuspendedUnder14() {
		return totalSuspendedUnder14;
	}
	public void setTotalSuspendedUnder14(double totalSuspendedUnder14) {
		this.totalSuspendedUnder14 = totalSuspendedUnder14;
	}
	public double getTotalCapitalGoodsAcquired() {
		return totalCapitalGoodsAcquired;
	}
	public void setTotalCapitalGoodsAcquired(double totalCapitalGoodsAcquired) {
		this.totalCapitalGoodsAcquired = totalCapitalGoodsAcquired;
	}
	public double getTotalBadDebtRelief() {
		return totalBadDebtRelief;
	}
	public void setTotalBadDebtRelief(double totalBadDebtRelief) {
		this.totalBadDebtRelief = totalBadDebtRelief;
	}
	public double getTotalBadDebtRecovered() {
		return totalBadDebtRecovered;
	}
	public void setTotalBadDebtRecovered(double totalBadDebtRecovered) {
		this.totalBadDebtRecovered = totalBadDebtRecovered;
	}
	public String getMsicCode1() {
		return msicCode1;
	}
	public void setMsicCode1(String msicCode1) {
		this.msicCode1 = msicCode1;
	}
	public String getMsicCode2() {
		return msicCode2;
	}
	public void setMsicCode2(String msicCode2) {
		this.msicCode2 = msicCode2;
	}
	public String getMsicCode3() {
		return msicCode3;
	}
	public void setMsicCode3(String msicCode3) {
		this.msicCode3 = msicCode3;
	}
	public String getMsicCode4() {
		return msicCode4;
	}
	public void setMsicCode4(String msicCode4) {
		this.msicCode4 = msicCode4;
	}
	public String getMsicCode5() {
		return msicCode5;
	}
	public void setMsicCode5(String msicCode5) {
		this.msicCode5 = msicCode5;
	}
	public double getMsicOutputTax1() {
		return msicOutputTax1;
	}
	public void setMsicOutputTax1(double msicOutputTax1) {
		this.msicOutputTax1 = msicOutputTax1;
	}
	public double getMsicOutputTax2() {
		return msicOutputTax2;
	}
	public void setMsicOutputTax2(double msicOutputTax2) {
		this.msicOutputTax2 = msicOutputTax2;
	}
	public double getMsicOutputTax3() {
		return msicOutputTax3;
	}
	public void setMsicOutputTax3(double msicOutputTax3) {
		this.msicOutputTax3 = msicOutputTax3;
	}
	public double getMsicOutputTax4() {
		return msicOutputTax4;
	}
	public void setMsicOutputTax4(double msicOutputTax4) {
		this.msicOutputTax4 = msicOutputTax4;
	}
	public double getMsicOutputTax5() {
		return msicOutputTax5;
	}
	public void setMsicOutputTax5(double msicOutputTax5) {
		this.msicOutputTax5 = msicOutputTax5;
	}
	public double getMsicOutputTax6() {
		return msicOutputTax6;
	}
	public void setMsicOutputTax6(double msicOutputTax6) {
		this.msicOutputTax6 = msicOutputTax6;
	}
	public double getMsicOutputTax7() {
		return msicOutputTax7;
	}
	public void setMsicOutputTax7(double msicOutputTax7) {
		this.msicOutputTax7 = msicOutputTax7;
	}
	public double getMsicPercentage1() {
		return msicPercentage1;
	}
	public void setMsicPercentage1(double msicPercentage1) {
		this.msicPercentage1 = msicPercentage1;
	}
	public double getMsicPercentage2() {
		return msicPercentage2;
	}
	public void setMsicPercentage2(double msicPercentage2) {
		this.msicPercentage2 = msicPercentage2;
	}
	public double getMsicPercentage3() {
		return msicPercentage3;
	}
	public void setMsicPercentage3(double msicPercentage3) {
		this.msicPercentage3 = msicPercentage3;
	}
	public double getMsicPercentage4() {
		return msicPercentage4;
	}
	public void setMsicPercentage4(double msicPercentage4) {
		this.msicPercentage4 = msicPercentage4;
	}
	public double getMsicPercentage5() {
		return msicPercentage5;
	}
	public void setMsicPercentage5(double msicPercentage5) {
		this.msicPercentage5 = msicPercentage5;
	}
	public double getMsicPercentage6() {
		return msicPercentage6;
	}
	public void setMsicPercentage6(double msicPercentage6) {
		this.msicPercentage6 = msicPercentage6;
	}
	public String getAuthorizedPerson() {
		return authorizedPerson;
	}
	public void setAuthorizedPerson(String authorizedPerson) {
		this.authorizedPerson = authorizedPerson;
	}
	public String getIcNoNew() {
		return icNoNew;
	}
	public void setIcNoNew(String icNoNew) {
		this.icNoNew = icNoNew;
	}
	public String getIcNoOld() {
		return icNoOld;
	}
	public void setIcNoOld(String icNoOld) {
		this.icNoOld = icNoOld;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public Date getPostmarkDate() {
		return postmarkDate;
	}
	public void setPostmarkDate(Date postmarkDate) {
		this.postmarkDate = postmarkDate;
	}
	public double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}
	public double getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}
	
}
