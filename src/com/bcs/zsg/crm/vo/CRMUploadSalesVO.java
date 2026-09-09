package com.bcs.zsg.crm.vo;

import java.util.List;
import com.bcs.zsg.core.vo.BaseVO;

public class CRMUploadSalesVO extends BaseVO {
	
	private static final long serialVersionUID = 1L;
	
	private String invoiceNo;
	private String invoiceDate;
	private String isCancellation;
	private String customerId;
	private double invoiceAmount;
	private double invoicePayment;
	private double invoiceNetPayment;
	private String departureDate;
	private String paymentDate;
	private String invoiceCategory;
	private String productCode;
	private String tourCode;
	private String tourName;
	private String bookingType;
	private List<CRMUploadSalesPassengerVO> passengerList;
	
	public String getInvoiceNo() {
		return invoiceNo;
	}
	
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	public String getInvoiceDate() {
		return invoiceDate;
	}
	
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	public String getIsCancellation() {
		return isCancellation;
	}
	
	public void setIsCancellation(String isCancellation) {
		this.isCancellation = isCancellation;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public double getInvoiceAmount() {
		return invoiceAmount;
	}
	
	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	
	public double getInvoicePayment() {
		return invoicePayment;
	}
	
	public void setInvoicePayment(double invoicePayment) {
		this.invoicePayment = invoicePayment;
	}
	
	public String getDepartureDate() {
		return departureDate;
	}
	
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	
	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getInvoiceCategory() {
		return invoiceCategory;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getTourCode() {
		return tourCode;
	}

	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public List<CRMUploadSalesPassengerVO> getPassengerList() {
		return passengerList;
	}
	
	public void setPassengerList(List<CRMUploadSalesPassengerVO> passengerList) {
		this.passengerList = passengerList;
	}

	public double getInvoiceNetPayment() {
		return invoiceNetPayment;
	}

	public void setInvoiceNetPayment(double invoiceNetPayment) {
		this.invoiceNetPayment = invoiceNetPayment;
	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}
}
