package com.bcs.zsg.crm.vo;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.sales.helper.SalesConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvoicePosSalesVO extends BaseVO {
	
	private static final long serialVersionUID = 1L;
	
	private Long idCompany;
	@Expose @SerializedName("refSalesId")
	private Long idInvoice;
	@Expose @SerializedName("refcustomerId")
	private Long idCustomer;
	@Expose @SerializedName("refBookingId")
	private Long idBooking;
	@Expose @SerializedName("documentDate")
	private Date invoiceDt;
	@Expose @SerializedName("refInvoiceNo")
	private String invNo;
	@Expose @SerializedName("departureDate")
	private Date departureDt;
	@Expose @SerializedName("documentStatus")
	private String statusCode;
	@Expose @SerializedName("fullFillmentDate")
	private Date createdDate;
	
	@Expose @SerializedName("refPaxStatementNo")
	private String psNo;
	@Expose @SerializedName("documentType")
	private String docTypeCd;
	private String catCd;
	private String orderCd;
	private Double amount;
	@Expose @SerializedName("earningPointsAmt")
	private Double earningPointsAmt;
	private boolean is1utama = false;
	private String postingStatus;
	
	private String invoiceNo;
	@Expose @SerializedName("invoiceType")
	private String invoiceType;
	@Expose @SerializedName("currency")
	private String invoiceCurr = "MYR";
	@Expose @SerializedName("paymentStatus")
	private String paymentStatus;
	@Expose @SerializedName("salesChannel")
	private String salesChannel = "Retail";
	
	
	@Expose @SerializedName("billingDetails")
	private List<InvoicePosSalesBillingVO> invoicePosSalesBillingList;
	private List<InvoicePosSalesItemVO> invPosSalesItemList;
	@Expose @SerializedName("orderLine")
	private Set<InvoicePosSalesItemVO> invPosSalesItemSet;
	
	private String custCd;
	private String custSalutation;
	private String custName;
	private String coName;

	@Override
    public String getStatusCode() {
        return super.getStatusCode();
    }
	
	@Override
	public void setStatusCode(String statusCode) {
	    super.setStatusCode(statusCode);
        this.statusCode = statusCode;
	}
	
	@Override
    public Date getCreatedDate() {
        return super.getCreatedDate();
    }
	
	@Override
	public void setCreatedDate(Date createdDate) {
	    super.setCreatedDate(createdDate);
        this.createdDate = createdDate;
	}
	
	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Long getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(Long idBooking) {
		this.idBooking = idBooking;
	}

	public Date getInvoiceDt() {
		return invoiceDt;
	}

	public void setInvoiceDt(Date invoiceDt) {
		this.invoiceDt = invoiceDt;
	}

	public Long getIdInvoice() {
		return idInvoice;
	}

	public void setIdInvoice(Long idInvoice) {
		this.idInvoice = idInvoice;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public String getPsNo() {
		return psNo;
	}

	public void setPsNo(String psNo) {
		this.psNo = psNo;
	}

	public String getCatCd() {
		return catCd;
	}

	public void setCatCd(String catCd) {
		this.catCd = catCd;
	}

	public String getOrderCd() {
		return orderCd;
	}

	public void setOrderCd(String orderCd) {
		this.orderCd = orderCd;
	}

	public boolean isIs1utama() {
		return is1utama;
	}

	public void setIs1utama(boolean is1utama) {
		this.is1utama = is1utama;
	}

	public String getPostingStatus() {
		return postingStatus;
	}

	public void setPostingStatus(String postingStatus) {
		this.postingStatus = postingStatus;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<InvoicePosSalesItemVO> getInvPosSalesItemList() {
		return invPosSalesItemList;
	}

	public void setInvPosSalesItemList(List<InvoicePosSalesItemVO> invPosSalesItemList) {
		this.invPosSalesItemList = invPosSalesItemList;
	}

	public String getDocTypeCd() {
		return docTypeCd;
	}

	public void setDocTypeCd(String docTypeCd) {
		this.docTypeCd = docTypeCd;
		
		if (SalesConstant.DOC_TYPE_CD_PAX_STMT.equals(docTypeCd)) {
			setInvoiceType("Pax Statement");
			setInvoiceNo(psNo);
		} else {
			setInvoiceType("Invoice");
			setInvoiceNo(invNo);
		}
	}

	public Set<InvoicePosSalesItemVO> getInvPosSalesItemSet() {
		return invPosSalesItemSet;
	}

	public void setInvPosSalesItemSet(Set<InvoicePosSalesItemVO> invPosSalesItemSet) {
		this.invPosSalesItemSet = invPosSalesItemSet;
	}
	
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceCurr() {
		return invoiceCurr;
	}

	public void setInvoiceCurr(String invoiceCurr) {
		this.invoiceCurr = invoiceCurr;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	public Date getDepartureDt() {
		return departureDt;
	}

	public void setDepartureDt(Date departureDt) {
		this.departureDt = departureDt;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public List<InvoicePosSalesBillingVO> getInvoicePosSalesBillingList() {
		return invoicePosSalesBillingList;
	}

	public void setInvoicePosSalesBillingList(List<InvoicePosSalesBillingVO> invoicePosSalesBillingList) {
		this.invoicePosSalesBillingList = invoicePosSalesBillingList;
	}

	public String getCustCd() {
		return custCd;
	}

	public void setCustCd(String custCd) {
		this.custCd = custCd;
	}

	public String getCustSalutation() {
		return custSalutation;
	}

	public void setCustSalutation(String custSalutation) {
		this.custSalutation = custSalutation;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCoName() {
		return coName;
	}

	public void setCoName(String coName) {
		this.coName = coName;
	}

	public Double getEarningPointsAmt() {
		return earningPointsAmt;
	}

	public void setEarningPointsAmt(Double earningPointsAmt) {
		this.earningPointsAmt = earningPointsAmt;
	}
	
}
