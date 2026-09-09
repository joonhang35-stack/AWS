package com.bcs.zsg.db.bterp.vo.gst;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bcs.zsg.core.vo.BaseVO;

public class GSTSummaryVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idCompany;
	private Date dateFrom;
	private Date dateTo;
	private double openingBalance;
	private double outputTax;
	private double inputTax;
	private double outputAmount;
	private double inputAmount;
	private double amountPayable;
	private double amountClaimable;
	private double closingBalance;

	private String refundCarryForward;
	private String processed;
	
	private List<GSTSummaryTaxVO> inputClaimableVOList;		//For form view usage
	private List<GSTSummaryTaxVO> inputNonClaimableVOList;	//For form view usage
	private List<GSTSummaryTaxVO> outputVOList;				//For form view usage
	private List<GSTSummaryTaxVO> outputNonClaimableVOList;	//For form view usage
	private GSTVO gstVO;				//For form view usage
	
	public List<GSTSummaryTaxVO> getAllTaxCodeVOList() {
		if(inputClaimableVOList == null)
			return null;
		
		List<GSTSummaryTaxVO> newList = new ArrayList<GSTSummaryTaxVO>(inputClaimableVOList);
		newList.addAll(inputNonClaimableVOList);
		newList.addAll(outputVOList);
		newList.addAll(outputNonClaimableVOList);
		
		return newList;
	}
	
	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public double getOutputTax() {
		return outputTax;
	}

	public void setOutputTax(double outputTax) {
		this.outputTax = outputTax;
	}

	public double getInputTax() {
		return inputTax;
	}

	public void setInputTax(double inputTax) {
		this.inputTax = inputTax;
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

	public double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public String getRefundCarryForward() {
		return refundCarryForward;
	}

	public void setRefundCarryForward(String refundCarryForward) {
		this.refundCarryForward = refundCarryForward;
	}

	public String getProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}
	
	public double getOutputAmount() {
		return outputAmount;
	}

	public void setOutputAmount(double outputAmount) {
		this.outputAmount = outputAmount;
	}

	public double getInputAmount() {
		return inputAmount;
	}

	public void setInputAmount(double inputAmount) {
		this.inputAmount = inputAmount;
	}

	public List<GSTSummaryTaxVO> getInputClaimableVOList() {
		return inputClaimableVOList;
	}

	public void setInputClaimableVOList(List<GSTSummaryTaxVO> inputClaimableVOList) {
		this.inputClaimableVOList = inputClaimableVOList;
	}

	public List<GSTSummaryTaxVO> getInputNonClaimableVOList() {
		return inputNonClaimableVOList;
	}

	public void setInputNonClaimableVOList(
			List<GSTSummaryTaxVO> inputNonClaimableVOList) {
		this.inputNonClaimableVOList = inputNonClaimableVOList;
	}

	public List<GSTSummaryTaxVO> getOutputVOList() {
		return outputVOList;
	}

	public void setOutputVOList(List<GSTSummaryTaxVO> outputVOList) {
		this.outputVOList = outputVOList;
	}

	public GSTVO getGstVO() {
		return gstVO;
	}

	public void setGstVO(GSTVO gstVO) {
		this.gstVO = gstVO;
	}

	public List<GSTSummaryTaxVO> getOutputNonClaimableVOList() {
		return outputNonClaimableVOList;
	}

	public void setOutputNonClaimableVOList(List<GSTSummaryTaxVO> outputNonClaimableVOList) {
		this.outputNonClaimableVOList = outputNonClaimableVOList;
	}
}
