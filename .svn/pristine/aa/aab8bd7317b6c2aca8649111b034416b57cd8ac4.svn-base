package com.bcs.zsg.sales.vo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bcs.zsg.acct.helper.EInvoiceProperties;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.EInvoiceDocumentVO;
import com.bcs.zsg.common.helper.EInvoiceConstant;
import com.bcs.zsg.common.vo.GenAddUpdDelVO;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.product.vo.AirlineScheduleItemVO;
import com.bcs.zsg.purchase.vo.EOInvoiceVO;
import com.bcs.zsg.web.object.EInvoiceDocument;

import oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType;

public class InvoiceVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long companyId;
	private Long customerId;
	private String custCd;
	private Long custCdLong;
	private Long acctId;
	private Long bookingId;
	private Long idParentInvTourBooking; // booking id for display purpose only, id_tour_booking of parent pax stmt.
											// When split, will copy bookingId to idParentInvTourBooking
	private Date invoiceDt;
	private Date psDt;
	private String prefix;
	private String code;
	private String psNo;
	private String rnNo;
	private Long codeLong;
	private String docTypeCd;
	private String docTypeStatus;
	private String typeCd;
	private String attnTo;
	private String cnInvNo;
	private String cnPsNo;
	private Date cnInvoiceDt;
	private String pmntTypeCd;
	private String pmntTypeName; // Payment type name
	private Long salerId;
	private String salerName; // Saler name
	private Long tfairSalerId;
	private Boolean isLockSP = true;
	private Boolean isApplePoint = true;
	private String tfairSalerName; // TFair Saler name
	private Long tourDepId;
	private String tourCd; // Tour code
	private Long issuerId;
	private String issuedBy; // Issued by name
	private Long eoRefId;
	private String catCd;
	private String idDepartment;
	private String orderCd;
	private String deliveryCd;
	private Date invoiceDue;
	private String gdsBookingRef;
	private Date departureDt;
	private Double amount;
	private Double amtPaid;
	private Double balance;
	private Double refundAmt = 0.0;
	private Double amtAftRefund = 0.0;
	private String balanceWord; // Balance in wording
	private String reason;
	private String comments;
	private String statusCd;
	private String bookingStatusCd;
	private Boolean isInvPaid;
	private String subjLine;
	private String internalRemarks;
	private String noteItems;
	private List<String> noteItemsList = new ArrayList<String>();
	private boolean isDateInFinPeriodClosed;
	private boolean isInvoiceDateChangeable = true;
	private Integer numOfPax;
	private String region;
	private String referenceNoList;
	private String referenceNoListPrev;
	private String salerDept;
	// CN - Invoice payment Search use
	private Long invPmntId;
	private String refNo;
	private int validPassportStatus; // 0: Invalid, 1:Valid
	private int visaCopiesStatus; // 0: Received, 1: Non, 2: Non visa required
	private double totalCurrency;

	private String tourDpInvRmk;
	private boolean trvWarrantChecked;

	private AcctVO acctVO;
	private CustDetailsVO custDetailsVO;
	private CompanyVO companyVO;
	private BookingVO bookingVO;

	private List<InvoiceItemVO> invoiceItemList = new ArrayList<InvoiceItemVO>();
	private List<InvoiceItemVO> invoiceItemExcludeList = new ArrayList<InvoiceItemVO>();
	private List<InvoiceItemVO> invoiceItemTaxList = new ArrayList<InvoiceItemVO>();
	private List<InvoicePaxVO> invoicePaxList = new ArrayList<InvoicePaxVO>();
	private List<InvoicePaymentVO> invoicePaymentList = new ArrayList<InvoicePaymentVO>();
	private List<EOInvoiceVO> eoInvList = new ArrayList<EOInvoiceVO>();
	private List<CustomerVO> customerList = new ArrayList<CustomerVO>();

	private List<InvoiceReferenceVO> invoiceRefVOList = new ArrayList<InvoiceReferenceVO>();
	private GenAddUpdDelVO<InvoiceReferenceVO> genAUDINvoiceRefVOList = new GenAddUpdDelVO<>();

	private List<InvoiceAttachmentVO> invoiceAttachmentVOList = new ArrayList<InvoiceAttachmentVO>();
	private GenAddUpdDelVO<InvoiceAttachmentVO> genAUDInvoiceAttachmentVOList = new GenAddUpdDelVO<>();

	private List<OnlineBookingPassengerVO> onlineBookingPaxList = new ArrayList<OnlineBookingPassengerVO>();

	private String custName;
	private String custSalutation;
	private String coName;
	private String tickets;

	private Long idAcctGST;
	private Long idAcctRounding;
	private double taxAmt;
	private double roundingAmt;
	private double taxIncAmt;
	private double taxExcAmt;

	// Report Used (A/P Detail Aging By Customer)
	private Double currentAmt = 0.0;
	private Double days130Amt = 0.0;
	private Double days3160Amt = 0.0;
	private Double days6190Amt = 0.0;
	private Double days91120Amt = 0.0;
	private Double days121150Amt = 0.0;
	private Double days150AftAmt = 0.0;
	private Double advPaidAmt = 0.0;
	private Integer totalUPoint;
	private Integer totalVoucherPoint;

	private boolean isIncludeSST = false;

	private boolean termAndCondChecked = true;

	private String termAndCond;

	private String bankDetails;

	private String invoiceNo;
	private List<AirlineScheduleItemVO> airlineScheduleItemList;

	// For Transfer Stamping Use
	private Date transferredDate;
	private String transferredBy;
	private String originalTc;
	private String formattedTransferredDate;
	
	// pax stmt
	private InvoiceVO cnInv;
	private String cnDocNoForDisplay; // for display purpose in add_creditnote

	// for split
	private List<String> selectedPaxIds; // to record selected pax id in split panel
	private Long idParentInv;
	private Integer subPsRunningNumber;
	private String parentPsNo;
	private List<InvoiceVO> subInvoiceList;
	private InvoicePaxVO[] selectedInvPax; // for split pax
	private List<InvoicePaxVO> savedSelectedInvPax;
	/**
	 * invoice pax/passenger list for split selection
	 */
	private List<InvoicePaxVO> passengerListForSplit;
	
	// E-Invoice API submission result
	private String eInvoiceSubmissionUid; // e invoice api submission uid (one submission might include multiple
											// documents)
	private String eInvoiceDocumentUuid; // e invoice document uuid
	private String eInvoiceStatus; // e invoice document submission status
	private String eInvoiceErr; // e invoice submission error
	private Boolean isConsolEInv;
	private String eInvoiceRnSubmissionUid; // refund note
	private String eInvoiceRnDocumentUuid;
	private String eInvoiceRnStatus;
	private String eInvoiceTypeDesc;
	private Date eInvoiceDtIssued; 
	
	private EInvoiceDocument eInvoiceDocument;
	private EInvoiceDocument eInvoiceRnDocument;
	private EInvoiceDocumentVO eInvoiceDocumentVO;
	private EInvoiceDocumentVO eInvoiceRnDocumentVO;
	private String eInvoiceValidationLink; // {envbaseurl}/uuid-of-document/share/longid
	private String eInvoiceRnValidationLink; // {envbaseurl}/uuid-of-document/share/longid
	private InputStream qrCode;
	private InputStream rnQrCode; // refund note e inv qr code
	private Double tourFareAmt;
	private Double airTicketAmt;
	private Double voucherAmt;
	private Double disbursementAmt;
	
	private InvoiceType invoiceType; // to record submitted info and write into db
	private InvoiceType rnInvoiceType; // to record submitted info and write into db
	
	private Boolean hasRefund = false;
	private Boolean showCcRfdBadge = false;
	private Boolean showTrvlInsBadge = false;
	
	// POS
	private Double prevPOSTransAmt;
	
	// E-Invoice Scheduler
	private Long idPendingEInvoice;
	private boolean skipAutoSubmit = false;
	
	// CRM
	private String postingSalesStatus;
	private String postingSalesRemarks;
	private List<InvoiceVoucherVO> voucherList;

//	public String geteInvoiceValidationLink() {
//		return eInvoiceValidationLink;
//	}
	
	/**
	 * for green 'E' ready to submit e-invoice badge display of credit note
	 * @return
	 */
	public boolean getReadyToSubmitCN() {
		if (StringUtils.isNotBlank(cnInvNo) && getCnInv() != null) {
			if (StringUtils.equalsIgnoreCase(cnInv.geteInvoiceStatus(), EInvoiceConstant.E_INV_STATUS_VALID)) {
				if (Double.compare(cnInv.getAmtAftRefund(), 0.0) != 0) {
					if (StringUtils.equalsIgnoreCase(eInvoiceStatus, EInvoiceConstant.E_INV_STATUS_SUBMIT)) {
						return false; // CN submitted to e-invoice, no need submit again, return false
					} else if (StringUtils.equalsIgnoreCase(eInvoiceStatus, EInvoiceConstant.E_INV_STATUS_VALID)) {
						return false; // CN is a valid e-invoice, no need submit again, return false
					} else {
						return true;
					}
				}
				
				if (Double.compare(cnInv.getRefundAmt(), 0.0) != 0) {
					if (StringUtils.equalsIgnoreCase(eInvoiceRnStatus, EInvoiceConstant.E_INV_STATUS_SUBMIT)) {
						return false; // RN submitted to e-invoice, no need submit again, return false
					} else if (StringUtils.equalsIgnoreCase(eInvoiceRnStatus, EInvoiceConstant.E_INV_STATUS_VALID)) {
						return false; // RN is a valid e-invoice, no need submit again, return false
					} else {
						return true;
					}
				}
				
				return true; // e-invoice/RN e-invoice status = Not Submitted / Invalid, need submit
			} else {
				// CN Inv not valid e-invoice, return false
				return false;
			}
		} else {
			// no invoice attached, return false
			return false;
		}
	}
	
	/**
	 * @return the voucherList
	 */
	public List<InvoiceVoucherVO> getVoucherList() {
		return voucherList;
	}

	/**
	 * @param voucherList the voucherList to set
	 */
	public void setVoucherList(List<InvoiceVoucherVO> voucherList) {
		this.voucherList = voucherList;
	}

	public String getSalerDept() {
		return salerDept;
	}

	public void setSalerDept(String salerDept) {
		this.salerDept = salerDept;
	}

	public boolean isSkipAutoSubmit() {
		return skipAutoSubmit;
	}

	public void setSkipAutoSubmit(boolean skipAutoSubmit) {
		this.skipAutoSubmit = skipAutoSubmit;
	}

	public Double getDisbursementAmt() {
		return disbursementAmt;
	}

	public void setDisbursementAmt(Double disbursementAmt) {
		this.disbursementAmt = disbursementAmt;
	}

	public Long getIdPendingEInvoice() {
		return idPendingEInvoice;
	}

	public void setIdPendingEInvoice(Long idPendingEInvoice) {
		this.idPendingEInvoice = idPendingEInvoice;
	}

	public InputStream getQrCode() {
		return qrCode;
	}

	public InvoiceType getRnInvoiceType() {
		return rnInvoiceType;
	}

	public void setRnInvoiceType(InvoiceType rnInvoiceType) {
		this.rnInvoiceType = rnInvoiceType;
	}

	public InvoiceType getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(InvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Double getPrevPOSTransAmt() {
		return prevPOSTransAmt;
	}

	public void setPrevPOSTransAmt(Double prevPOSTransAmt) {
		this.prevPOSTransAmt = prevPOSTransAmt;
	}

	public Boolean getShowCcRfdBadge() {
		return showCcRfdBadge;
	}

	public void setShowCcRfdBadge(Boolean showCcRfdBadge) {
		this.showCcRfdBadge = showCcRfdBadge;
	}

	public Boolean getHasRefund() {
		return hasRefund;
	}

	public void setHasRefund(Boolean hasRefund) {
		this.hasRefund = hasRefund;
	}

	public String getCnDocNoForDisplay() {
		return cnDocNoForDisplay;
	}

	public void setCnDocNoForDisplay(String cnDocNoForDisplay) {
		this.cnDocNoForDisplay = cnDocNoForDisplay;
	}

	public InvoiceVO getCnInv() {
		return cnInv;
	}

	public void setCnInv(InvoiceVO cnInv) {
		this.cnInv = cnInv;
	}

	public String getCnPsNo() {
		return cnPsNo;
	}

	public void setCnPsNo(String cnPsNo) {
		this.cnPsNo = cnPsNo;
	}

	public InputStream getRnQrCode() {
		return rnQrCode;
	}

	public void setRnQrCode(InputStream rnQrCode) {
		this.rnQrCode = rnQrCode;
	}

	public Double getTourFareAmt() {
		return tourFareAmt;
	}

	public void setTourFareAmt(Double tourFareAmt) {
		this.tourFareAmt = tourFareAmt;
	}

	public Double getAirTicketAmt() {
		return airTicketAmt;
	}

	public void setAirTicketAmt(Double airTicketAmt) {
		this.airTicketAmt = airTicketAmt;
	}

	public Double getVoucherAmt() {
		return voucherAmt;
	}

	public void setVoucherAmt(Double voucherAmt) {
		this.voucherAmt = voucherAmt;
	}

	public void setQrCode(InputStream qrCode) {
		this.qrCode = qrCode;
	}

	public String geteInvoiceValidationLink() {
		if (eInvoiceDocument != null && StringUtils.isNotBlank(eInvoiceDocumentUuid) && StringUtils.isNotBlank(eInvoiceDocument.getLongId())) {
			return EInvoiceProperties.getEnvBaseUrl() + eInvoiceDocumentUuid + "/share/" + eInvoiceDocument.getLongId();
		} else if (eInvoiceDocumentVO != null && StringUtils.isNotBlank(eInvoiceDocumentUuid) && StringUtils.isNotBlank(eInvoiceDocumentVO.getLongId())) {
			return EInvoiceProperties.getEnvBaseUrl() + eInvoiceDocumentUuid + "/share/" + eInvoiceDocumentVO.getLongId();
		}
		
		return null;
	}
	
	public String geteInvoiceRnValidationLink() {
		if (eInvoiceRnDocument != null && StringUtils.isNotBlank(eInvoiceRnDocumentUuid) && StringUtils.isNotBlank(eInvoiceRnDocument.getLongId())) {
			return EInvoiceProperties.getEnvBaseUrl() + eInvoiceRnDocumentUuid + "/share/" + eInvoiceRnDocument.getLongId();
		} else if (eInvoiceRnDocumentVO != null && StringUtils.isNotBlank(eInvoiceRnDocumentUuid) && StringUtils.isNotBlank(eInvoiceRnDocumentVO.getLongId())) {
			return EInvoiceProperties.getEnvBaseUrl() + eInvoiceRnDocumentUuid + "/share/" + eInvoiceRnDocumentVO.getLongId();
		}
		
		return null;
	}

	public EInvoiceDocumentVO geteInvoiceDocumentVO() {
		return eInvoiceDocumentVO;
	}

	public void seteInvoiceDocumentVO(EInvoiceDocumentVO eInvoiceDocumentVO) {
		this.eInvoiceDocumentVO = eInvoiceDocumentVO;
	}

	public EInvoiceDocumentVO geteInvoiceRnDocumentVO() {
		return eInvoiceRnDocumentVO;
	}

	public void seteInvoiceRnDocumentVO(EInvoiceDocumentVO eInvoiceRnDocumentVO) {
		this.eInvoiceRnDocumentVO = eInvoiceRnDocumentVO;
	}

	public EInvoiceDocument geteInvoiceRnDocument() {
		return eInvoiceRnDocument;
	}

	public void seteInvoiceRnDocument(EInvoiceDocument eInvoiceRnDocument) {
		this.eInvoiceRnDocument = eInvoiceRnDocument;
	}

	public String geteInvoiceRnSubmissionUid() {
		return eInvoiceRnSubmissionUid;
	}

	public void seteInvoiceRnSubmissionUid(String eInvoiceRnSubmissionUid) {
		this.eInvoiceRnSubmissionUid = eInvoiceRnSubmissionUid;
	}

	public String geteInvoiceRnDocumentUuid() {
		return eInvoiceRnDocumentUuid;
	}

	public void seteInvoiceRnDocumentUuid(String eInvoiceRnDocumentUuid) {
		this.eInvoiceRnDocumentUuid = eInvoiceRnDocumentUuid;
	}

	public String geteInvoiceRnStatus() {
		return eInvoiceRnStatus;
	}

	public void seteInvoiceRnStatus(String eInvoiceRnStatus) {
		this.eInvoiceRnStatus = eInvoiceRnStatus;
	}

	public Boolean getIsConsolEInv() {
		return isConsolEInv;
	}

	public void setIsConsolEInv(Boolean isConsolEInv) {
		this.isConsolEInv = isConsolEInv;
	}

	public void seteInvoiceValidationLink(String eInvoiceValidationLink) {
		this.eInvoiceValidationLink = eInvoiceValidationLink;
	}

	public EInvoiceDocument geteInvoiceDocument() {
		return eInvoiceDocument;
	}

	public void seteInvoiceDocument(EInvoiceDocument eInvoiceDocument) {
		this.eInvoiceDocument = eInvoiceDocument;
	}

	public String geteInvoiceStatus() {
		return eInvoiceStatus;
	}

	public void seteInvoiceStatus(String eInvoiceStatus) {
		this.eInvoiceStatus = eInvoiceStatus;
	}

	private String bookingTypeCd;

	private Integer cabinQty;

	public Integer getCabinQty() {
		return cabinQty;
	}

	public void setCabinQty(Integer cabinQty) {
		this.cabinQty = cabinQty;
	}

	public String getBookingTypeCd() {
		return bookingTypeCd;
	}

	public void setBookingTypeCd(String bookingTypeCd) {
		this.bookingTypeCd = bookingTypeCd;
	}

	public String geteInvoiceSubmissionUid() {
		return eInvoiceSubmissionUid;
	}

	public void seteInvoiceSubmissionUid(String eInvoiceSubmissionUid) {
		this.eInvoiceSubmissionUid = eInvoiceSubmissionUid;
	}

	public String geteInvoiceDocumentUuid() {
		return eInvoiceDocumentUuid;
	}

	public void seteInvoiceDocumentUuid(String eInvoiceDocumentUuid) {
		this.eInvoiceDocumentUuid = eInvoiceDocumentUuid;
	}

	public List<InvoiceVO> getSubInvoiceList() {
		return subInvoiceList;
	}

	public void setSubInvoiceList(List<InvoiceVO> subInvoiceList) {
		this.subInvoiceList = subInvoiceList;
	}

	public String getParentPsNo() {
		return parentPsNo;
	}

	public void setParentPsNo(String parentPsNo) {
		this.parentPsNo = parentPsNo;
	}

	public Long getIdParentInvTourBooking() {
		return idParentInvTourBooking;
	}

	public void setIdParentInvTourBooking(Long idParentInvTourBooking) {
		this.idParentInvTourBooking = idParentInvTourBooking;
	}

	public Integer getSubPsRunningNumber() {
		return subPsRunningNumber;
	}

	public void setSubPsRunningNumber(Integer subPsRunningNumber) {
		this.subPsRunningNumber = subPsRunningNumber;
	}

	public Long getIdParentInv() {
		return idParentInv;
	}

	public void setIdParentInv(Long idParentInv) {
		this.idParentInv = idParentInv;
	}

	public List<String> getSelectedPaxIds() {
		return selectedPaxIds;
	}

	public void setSelectedPaxIds(List<String> selectedPaxIds) {
		this.selectedPaxIds = selectedPaxIds;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public List<AirlineScheduleItemVO> getAirlineScheduleItemList() {
		return airlineScheduleItemList;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public void setAirlineScheduleItemList(List<AirlineScheduleItemVO> airlineScheduleItemList) {
		this.airlineScheduleItemList = airlineScheduleItemList;
	}

	public String getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(String bankDetails) {
		this.bankDetails = bankDetails;
	}

	public String getTermAndCond() {
		return termAndCond;
	}

	public void setTermAndCond(String termAndCond) {
		this.termAndCond = termAndCond;
	}

	public boolean isTermAndCondChecked() {
		return termAndCondChecked;
	}

	public void setTermAndCondChecked(boolean termAndCondChecked) {
		this.termAndCondChecked = termAndCondChecked;
	}

	public boolean getIsIncludeSST() {
		return isIncludeSST;
	}

	public void setIncludeSST(boolean isIncludeSST) {
		this.isIncludeSST = isIncludeSST;
	}

	/**
	 * @return the companyId
	 */
	public String getCustSalutation() {
		return custSalutation;
	}

	/**
	 * @param trvWarrant the trvWarrant to set
	 */
	public void setCustSalutation(String custSalutation) {
		this.custSalutation = custSalutation;
	}

	/**
	 * @return the companyId
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * @param trvWarrant the trvWarrant to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * @return the companyId
	 */
	public boolean getTrvWarrantChecked() {
		return trvWarrantChecked;
	}

	/**
	 * @param trvWarrant the trvWarrant to set
	 */
	public void setTrvWarrantChecked(boolean trvWarrantChecked) {
		this.trvWarrantChecked = trvWarrantChecked;
	}

	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	 * @return the bookingId
	 */
	public Long getBookingId() {
		return bookingId;
	}

	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	/**
	 * @return the invoiceDt
	 */
	public Date getInvoiceDt() {
		return invoiceDt;
	}

	/**
	 * @param invoiceDt the invoiceDt to set
	 */
	public void setInvoiceDt(Date invoiceDt) {
		this.invoiceDt = invoiceDt;
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
		if (StringUtils.isNumeric(this.code)) {
			if (code != null)
				setCodeLong(Long.parseLong(this.code));
		}
	}

	/**
	 * @return the docTypeCd
	 */
	public String getDocTypeCd() {
		return docTypeCd;
	}

	/**
	 * @param docTypeCd the docTypeCd to set
	 */
	public void setDocTypeCd(String docTypeCd) {
		this.docTypeCd = docTypeCd;
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

	/**
	 * @return the attnTo
	 */
	public String getAttnTo() {
		return attnTo;
	}

	/**
	 * @param attnTo the attnTo to set
	 */
	public void setAttnTo(String attnTo) {
		this.attnTo = attnTo;
	}

	/**
	 * @return the salerId
	 */
	public Long getSalerId() {
		return salerId;
	}

	/**
	 * @param salerId the salerId to set
	 */
	public void setSalerId(Long salerId) {
		this.salerId = salerId;
	}

	/**
	 * @return the tourDepId
	 */
	public Long getTourDepId() {
		return tourDepId;
	}

	/**
	 * @param tourDepId the tourDepId to set
	 */
	public void setTourDepId(Long tourDepId) {
		this.tourDepId = tourDepId;
	}

	/**
	 * @return the issuerId
	 */
	public Long getIssuerId() {
		return issuerId;
	}

	/**
	 * @param issuerId the issuerId to set
	 */
	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}

	/**
	 * @return the eoRefId
	 */
	public Long getEoRefId() {
		return eoRefId;
	}

	/**
	 * @param eoRefId the eoRefId to set
	 */
	public void setEoRefId(Long eoRefId) {
		this.eoRefId = eoRefId;
	}

	/**
	 * @return the catCd
	 */
	public String getCatCd() {
		return catCd;
	}

	/**
	 * @param catCd the catCd to set
	 */
	public void setCatCd(String catCd) {
		this.catCd = catCd;
	}

	/**
	 * @return the orderCd
	 */
	public String getOrderCd() {
		return orderCd;
	}

	/**
	 * @param orderCd the orderCd to set
	 */
	public void setOrderCd(String orderCd) {
		this.orderCd = orderCd;
	}

	/**
	 * @return the deliveryCd
	 */
	public String getDeliveryCd() {
		return deliveryCd;
	}

	/**
	 * @param deliveryCd the deliveryCd to set
	 */
	public void setDeliveryCd(String deliveryCd) {
		this.deliveryCd = deliveryCd;
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
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * @return the isInvPaid
	 */
	public Boolean getIsInvPaid() {
		return isInvPaid;
	}

	/**
	 * @param isInvPaid the isInvPaid to set
	 */
	public void setIsInvPaid(Boolean isInvPaid) {
		this.isInvPaid = isInvPaid;
	}

	/**
	 * @return the pmntTypeCd
	 */
	public String getPmntTypeCd() {
		return pmntTypeCd;
	}

	/**
	 * @param pmntTypeCd the pmntTypeCd to set
	 */
	public void setPmntTypeCd(String pmntTypeCd) {
		this.pmntTypeCd = pmntTypeCd;
	}

	/**
	 * @return the departureDt
	 */
	public Date getDepartureDt() {
		return departureDt;
	}

	/**
	 * @param departureDt the departureDt to set
	 */
	public void setDepartureDt(Date departureDt) {
		this.departureDt = departureDt;
	}

	/**
	 * @return the subjLine
	 */
	public String getSubjLine() {
		return subjLine;
	}

	/**
	 * @param subjLine the subjLine to set
	 */
	public void setSubjLine(String subjLine) {
		this.subjLine = subjLine;
	}

	/**
	 * @return the invoiceItemList
	 */
	public List<InvoiceItemVO> getInvoiceItemList() {
		return invoiceItemList;
	}

	/**
	 * @param invoiceItemList the invoiceItemList to set
	 */
	public void setInvoiceItemList(List<InvoiceItemVO> invoiceItemList) {
		this.invoiceItemList = invoiceItemList;
	}

	/**
	 * @return the invoiceItemTaxList
	 */
	public List<InvoiceItemVO> getInvoiceItemTaxList() {
		return invoiceItemTaxList;
	}

	/**
	 * @param invoiceItemTaxList the invoiceItemTaxList to set
	 */
	public void setInvoiceItemTaxList(List<InvoiceItemVO> invoiceItemTaxList) {
		this.invoiceItemTaxList = invoiceItemTaxList;
	}

	/**
	 * @return the customerList
	 */
	public List<CustomerVO> getCustomerList() {
		return customerList;
	}

	/**
	 * @param customerList the customerList to set
	 */
	public void setCustomerList(List<CustomerVO> customerList) {
		this.customerList = customerList;
	}

	/**
	 * @return the invoicePaymentList
	 */
	public List<InvoicePaymentVO> getInvoicePaymentList() {
		return invoicePaymentList;
	}

	/**
	 * @param invoicePaymentList the invoicePaymentList to set
	 */
	public void setInvoicePaymentList(List<InvoicePaymentVO> invoicePaymentList) {
		this.invoicePaymentList = invoicePaymentList;
	}

	/**
	 * @return the salerName
	 */
	public String getSalerName() {
		return salerName;
	}

	/**
	 * @param salerName the salerName to set
	 */
	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}

	public Long getTfairSalerId() {
		return tfairSalerId;
	}

	public void setTfairSalerId(Long tfairSalerId) {
		this.tfairSalerId = tfairSalerId;
	}

	public String getTfairSalerName() {
		return tfairSalerName;
	}

	public void setTfairSalerName(String tfairSalerName) {
		this.tfairSalerName = tfairSalerName;
	}

	/**
	 * @return the tourCd
	 */
	public String getTourCd() {
		return tourCd;
	}

	/**
	 * @param tourCd the tourCd to set
	 */
	public void setTourCd(String tourCd) {
		this.tourCd = tourCd;
	}

	/**
	 * @return the issuedBy
	 */
	public String getIssuedBy() {
		return issuedBy;
	}

	/**
	 * @param issuedBy the issuedBy to set
	 */
	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	/**
	 * @return the amtPaid
	 */
	public Double getAmtPaid() {
		return amtPaid;
	}

	/**
	 * @param amtPaid the amtPaid to set
	 */
	public void setAmtPaid(Double amtPaid) {
		this.amtPaid = amtPaid;
	}

	/**
	 * @return the acctVO
	 */
	public AcctVO getAcctVO() {
		return acctVO;
	}

	/**
	 * @param acctVO the acctVO to set
	 */
	public void setAcctVO(AcctVO acctVO) {
		this.acctVO = acctVO;
	}

	/**
	 * @return the custDetailsVO
	 */
	public CustDetailsVO getCustDetailsVO() {
		return custDetailsVO;
	}

	/**
	 * @param custDetailsVO the custDetailsVO to set
	 */
	public void setCustDetailsVO(CustDetailsVO custDetailsVO) {
		this.custDetailsVO = custDetailsVO;
	}

	/**
	 * @return the invoicePaxList
	 */
	public List<InvoicePaxVO> getInvoicePaxList() {
		return invoicePaxList;
	}

	/**
	 * @param invoicePaxList the invoicePaxList to set
	 */
	public void setInvoicePaxList(List<InvoicePaxVO> invoicePaxList) {
		this.invoicePaxList = invoicePaxList;
	}

	/**
	 * @return the statusCd
	 */
	public String getStatusCd() {
		return statusCd;
	}

	/**
	 * @param statusCd the statusCd to set
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	/**
	 * @return the companyVO
	 */
	public CompanyVO getCompanyVO() {
		return companyVO;
	}

	/**
	 * @param companyVO the companyVO to set
	 */
	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}

	/**
	 * @return the balanceWord
	 */
	public String getBalanceWord() {
		return balanceWord;
	}

	/**
	 * @param balanceWord the balanceWord to set
	 */
	public void setBalanceWord(String balanceWord) {
		this.balanceWord = balanceWord;
	}

	/**
	 * @return the pmntTypeName
	 */
	public String getPmntTypeName() {
		return pmntTypeName;
	}

	/**
	 * @param pmntTypeName the pmntTypeName to set
	 */
	public void setPmntTypeName(String pmntTypeName) {
		this.pmntTypeName = pmntTypeName;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the tourDpInvRmk
	 */
	public String getTourDpInvRmk() {
		return tourDpInvRmk;
	}

	/**
	 * @param tourDpInvRmk the tourDpInvRmk to set
	 */
	public void setTourDpInvRmk(String tourDpInvRmk) {
		this.tourDpInvRmk = tourDpInvRmk;
	}

	/**
	 * @return the eoInvList
	 */
	public List<EOInvoiceVO> getEoInvList() {
		return eoInvList;
	}

	/**
	 * @param eoInvList the eoInvList to set
	 */
	public void setEoInvList(List<EOInvoiceVO> eoInvList) {
		this.eoInvList = eoInvList;
	}

	/**
	 * @return the gdsBookingRef
	 */
	public String getGdsBookingRef() {
		return gdsBookingRef;
	}

	/**
	 * @param gdsBookingRef the gdsBookingRef to set
	 */
	public void setGdsBookingRef(String gdsBookingRef) {
		this.gdsBookingRef = gdsBookingRef;
	}

	/**
	 * @return the invoiceDue
	 */
	public Date getInvoiceDue() {
		return invoiceDue;
	}

	/**
	 * @param invoiceDue the invoiceDue to set
	 */
	public void setInvoiceDue(Date invoiceDue) {
		this.invoiceDue = invoiceDue;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the cnInvNo
	 */
	public String getCnInvNo() {
		return cnInvNo;
	}

	/**
	 * @param cnInvNo the cnInvNo to set
	 */
	public void setCnInvNo(String cnInvNo) {
		this.cnInvNo = cnInvNo;
	}

	public Date getCnInvoiceDt() {
		return cnInvoiceDt;
	}

	public void setCnInvoiceDt(Date cnInvoiceDt) {
		this.cnInvoiceDt = cnInvoiceDt;
	}

	/**
	 * @return the codeLong
	 */
	public Long getCodeLong() {
		return codeLong;
	}

	/**
	 * @param codeLong the codeLong to set
	 */
	public void setCodeLong(Long codeLong) {
		this.codeLong = codeLong;
	}

	/**
	 * @return the coName
	 */
	public String getCoName() {
		return coName;
	}

	/**
	 * @param coName the coName to set
	 */
	public void setCoName(String coName) {
		this.coName = coName;
	}

	/**
	 * @return the custCd
	 */
	public String getCustCd() {
		return custCd;
	}

	/**
	 * @param custCd the custCd to set
	 */
	public void setCustCd(String custCd) {
		this.custCd = custCd;
		if (custCd != null)
			setCustCdLong(Long.parseLong(this.custCd));
	}

	/**
	 * @return the custCdLong
	 */
	public Long getCustCdLong() {
		return custCdLong;
	}

	/**
	 * @param custCdLong the custCdLong to set
	 */
	public void setCustCdLong(Long custCdLong) {
		this.custCdLong = custCdLong;
	}

	/**
	 * @return the tickets
	 */
	public String getTickets() {
		return tickets;
	}

	/**
	 * @param tickets the tickets to set
	 */
	public void setTickets(String tickets) {
		this.tickets = tickets;
	}

	/**
	 * @return the isDateInFinPeriodClosed
	 */
	public boolean isDateInFinPeriodClosed() {
		return isDateInFinPeriodClosed;
	}

	/**
	 * @param isDateInFinPeriodClosed the isDateInFinPeriodClosed to set
	 */
	public void setDateInFinPeriodClosed(boolean isDateInFinPeriodClosed) {
		this.isDateInFinPeriodClosed = isDateInFinPeriodClosed;
	}

	/**
	 * @return the bookingStatusCd
	 */
	public String getBookingStatusCd() {
		return bookingStatusCd;
	}

	/**
	 * @param bookingStatusCd the bookingStatusCd to set
	 */
	public void setBookingStatusCd(String bookingStatusCd) {
		this.bookingStatusCd = bookingStatusCd;
	}

	public double getRoundingAmt() {
		return roundingAmt;
	}

	public void setRoundingAmt(double roundingAmt) {
		this.roundingAmt = roundingAmt;
	}

	/**
	 * @return the taxAmt
	 */
	public double getTaxAmt() {
		return taxAmt;
	}

	/**
	 * @param taxAmt the taxAmt to set
	 */
	public void setTaxAmt(double taxAmt) {
		this.taxAmt = taxAmt;
	}

	/**
	 * @return the taxIncAmt
	 */
	public double getTaxIncAmt() {
		return taxIncAmt;
	}

	/**
	 * @param taxIncAmt the taxIncAmt to set
	 */
	public void setTaxIncAmt(double taxIncAmt) {
		this.taxIncAmt = taxIncAmt;
	}

	/**
	 * @return the taxExcAmt
	 */
	public double getTaxExcAmt() {
		return taxExcAmt;
	}

	/**
	 * @param taxExcAmt the taxExcAmt to set
	 */
	public void setTaxExcAmt(double taxExcAmt) {
		this.taxExcAmt = taxExcAmt;
	}

	/**
	 * @return the idAcctGST
	 */
	public Long getIdAcctGST() {
		return idAcctGST;
	}

	/**
	 * @param idAcctGST the idAcctGST to set
	 */
	public void setIdAcctGST(Long idAcctGST) {
		this.idAcctGST = idAcctGST;
	}

	/**
	 * @return the idAcctRounding
	 */
	public Long getIdAcctRounding() {
		return idAcctRounding;
	}

	/**
	 * @param idAcctRounding the idAcctRounding to set
	 */
	public void setIdAcctRounding(Long idAcctRounding) {
		this.idAcctRounding = idAcctRounding;
	}

	/**
	 * @return the isInvoiceDateChangeable
	 */
	public boolean isInvoiceDateChangeable() {
		return isInvoiceDateChangeable;
	}

	/**
	 * @param isInvoiceDateChangeable the isInvoiceDateChangeable to set
	 */
	public void setInvoiceDateChangeable(boolean isInvoiceDateChangeable) {
		this.isInvoiceDateChangeable = isInvoiceDateChangeable;
	}

	/**
	 * @return the numOfPax
	 */
	public Integer getNumOfPax() {
		return numOfPax;
	}

	/**
	 * @param numOfPax the numOfPax to set
	 */
	public void setNumOfPax(Integer numOfPax) {
		this.numOfPax = numOfPax;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the currentAmt
	 */
	public Double getCurrentAmt() {
		return currentAmt;
	}

	/**
	 * @param currentAmt the currentAmt to set
	 */
	public void setCurrentAmt(Double currentAmt) {
		this.currentAmt = currentAmt;
	}

	/**
	 * @return the days130Amt
	 */
	public Double getDays130Amt() {
		return days130Amt;
	}

	/**
	 * @param days130Amt the days130Amt to set
	 */
	public void setDays130Amt(Double days130Amt) {
		this.days130Amt = days130Amt;
	}

	/**
	 * @return the days3160Amt
	 */
	public Double getDays3160Amt() {
		return days3160Amt;
	}

	/**
	 * @param days3160Amt the days3160Amt to set
	 */
	public void setDays3160Amt(Double days3160Amt) {
		this.days3160Amt = days3160Amt;
	}

	/**
	 * @return the days6190Amt
	 */
	public Double getDays6190Amt() {
		return days6190Amt;
	}

	/**
	 * @param days6190Amt the days6190Amt to set
	 */
	public void setDays6190Amt(Double days6190Amt) {
		this.days6190Amt = days6190Amt;
	}

	/**
	 * @return the days91120Amt
	 */
	public Double getDays91120Amt() {
		return days91120Amt;
	}

	/**
	 * @param days91120Amt the days91120Amt to set
	 */
	public void setDays91120Amt(Double days91120Amt) {
		this.days91120Amt = days91120Amt;
	}

	/**
	 * @return the days121150Amt
	 */
	public Double getDays121150Amt() {
		return days121150Amt;
	}

	/**
	 * @param days121150Amt the days121150Amt to set
	 */
	public void setDays121150Amt(Double days121150Amt) {
		this.days121150Amt = days121150Amt;
	}

	/**
	 * @return the days150AftAmt
	 */
	public Double getDays150AftAmt() {
		return days150AftAmt;
	}

	/**
	 * @param days150AftAmt the days150AftAmt to set
	 */
	public void setDays150AftAmt(Double days150AftAmt) {
		this.days150AftAmt = days150AftAmt;
	}

	/**
	 * @return the advPaidAmt
	 */
	public Double getAdvPaidAmt() {
		return advPaidAmt;
	}

	/**
	 * @param advPaidAmt the advPaidAmt to set
	 */
	public void setAdvPaidAmt(Double advPaidAmt) {
		this.advPaidAmt = advPaidAmt;
	}

	/**
	 * @return the refNo
	 */
	public String getRefNo() {
		return refNo;
	}

	/**
	 * @param refNo the refNo to set
	 */
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	/**
	 * @return the invPmntId
	 */
	public Long getInvPmntId() {
		return invPmntId;
	}

	/**
	 * @param invPmntId the invPmntId to set
	 */
	public void setInvPmntId(Long invPmntId) {
		this.invPmntId = invPmntId;
	}

	public String getInternalRemarks() {
		return internalRemarks;
	}

	public void setInternalRemarks(String internalRemarks) {
		this.internalRemarks = internalRemarks;
	}

	public String getNoteItems() {
		return noteItems;
	}

	public void setNoteItems(String noteItems) {
		this.noteItems = noteItems;
	}

	public List<String> getNoteItemsList() {
		return noteItemsList;
	}

	public void setNoteItemsList(List<String> noteItemsList) {
		this.noteItemsList.clear();
		this.noteItemsList.addAll(noteItemsList);
	}

	public List<InvoiceReferenceVO> getInvoiceRefVOList() {
		return invoiceRefVOList;
	}

	public void setInvoiceRefVOList(List<InvoiceReferenceVO> invoiceRefVOList) {
		this.invoiceRefVOList = invoiceRefVOList;
	}

	public GenAddUpdDelVO<InvoiceReferenceVO> getGenAUDINvoiceRefVOList() {
		return genAUDINvoiceRefVOList;
	}

	public void setGenAUDINvoiceRefVOList(GenAddUpdDelVO<InvoiceReferenceVO> genAUDINvoiceRefVOList) {
		this.genAUDINvoiceRefVOList = genAUDINvoiceRefVOList;
	}

	public List<InvoiceAttachmentVO> getInvoiceAttachmentVOList() {
		return invoiceAttachmentVOList;
	}

	public void setInvoiceAttachmentVOList(List<InvoiceAttachmentVO> invoiceAttachmentVOList) {
		this.invoiceAttachmentVOList = invoiceAttachmentVOList;
	}

	public GenAddUpdDelVO<InvoiceAttachmentVO> getGenAUDInvoiceAttachmentVOList() {
		return genAUDInvoiceAttachmentVOList;
	}

	public void setGenAUDInvoiceAttachmentVOList(GenAddUpdDelVO<InvoiceAttachmentVO> genAUDInvoiceAttachmentVOList) {
		this.genAUDInvoiceAttachmentVOList = genAUDInvoiceAttachmentVOList;
	}

	public List<InvoiceItemVO> getInvoiceItemExcludeList() {
		return invoiceItemExcludeList;
	}

	public void setInvoiceItemExcludeList(List<InvoiceItemVO> invoiceItemExcludeList) {
		this.invoiceItemExcludeList = invoiceItemExcludeList;
	}

	public List<OnlineBookingPassengerVO> getOnlineBookingPaxList() {
		return onlineBookingPaxList;
	}

	public void setOnlineBookingPaxList(List<OnlineBookingPassengerVO> onlineBookingPaxList) {
		this.onlineBookingPaxList = onlineBookingPaxList;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(String idDepartment) {
		this.idDepartment = idDepartment;
	}

	public Integer getTotalUPoint() {
		return totalUPoint;
	}

	public void setTotalUPoint(Integer totalUPoint) {
		this.totalUPoint = totalUPoint;
	}

	public Integer getTotalVoucherPoint() {
		return totalVoucherPoint;
	}

	public void setTotalVoucherPoint(Integer totalVoucherPoint) {
		this.totalVoucherPoint = totalVoucherPoint;
	}

	public Integer getValidPassportStatus() {
		return validPassportStatus;
	}

	public void setValidPassportStatus(Integer validPassportStatus) {
		this.validPassportStatus = validPassportStatus;
	}

	public Integer getVisaCopiesStatus() {
		return visaCopiesStatus;
	}

	public void setVisaCopiesStatus(Integer visaCopiesStatus) {
		this.visaCopiesStatus = visaCopiesStatus;
	}

	public BookingVO getBookingVO() {
		return bookingVO;
	}

	public void setBookingVO(BookingVO bookingVO) {
		this.bookingVO = bookingVO;
	}

	public Boolean getIsLockSP() {
		return isLockSP;
	}

	public void setIsLockSP(Boolean isLockSP) {
		this.isLockSP = isLockSP;
	}

	public Date getTransferredDate() {
		return transferredDate;
	}

	public void setTransferredDate(Date transferredDate) {
		this.transferredDate = transferredDate;
	}

	public String getTransferredBy() {
		return transferredBy;
	}

	public void setTransferredBy(String transferredBy) {
		this.transferredBy = transferredBy;
	}

	public String getOriginalTc() {
		return originalTc;
	}

	public void setOriginalTc(String originalTc) {
		this.originalTc = originalTc;
	}

	public String getFormattedTransferredDate() {
		return formattedTransferredDate;
	}

	public void setFormattedTransferredDate(String formattedTransferredDate) {
		this.formattedTransferredDate = formattedTransferredDate;
	}

	public String getDocTypeStatus() {
		return docTypeStatus;
	}

	public void setDocTypeStatus(String docTypeStatus) {
		this.docTypeStatus = docTypeStatus;
	}

	public String getPsNo() {
		return psNo;
	}

	public void setPsNo(String psNo) {
		this.psNo = psNo;
	}

	public String getReferenceNoList() {
		return referenceNoList;
	}

	public void setReferenceNoList(String referenceNoList) {
		this.referenceNoList = referenceNoList;
	}

	public String getReferenceNoListPrev() {
		return referenceNoListPrev;
	}

	public void setReferenceNoListPrev(String referenceNoListPrev) {
		this.referenceNoListPrev = referenceNoListPrev;
	}

	public Double getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(Double refundAmt) {
		this.refundAmt = refundAmt;
	}

	public Double getAmtAftRefund() {
		return amtAftRefund;
	}

	public void setAmtAftRefund(Double amtAftRefund) {
		this.amtAftRefund = amtAftRefund;
	}

	public String getRnNo() {
		return rnNo;
	}

	public void setRnNo(String rnNo) {
		this.rnNo = rnNo;
	}

	public Date getPsDt() {
		return psDt;
	}

	public void setPsDt(Date psDt) {
		this.psDt = psDt;
	}

	public String geteInvoiceErr() {
		return eInvoiceErr;
	}

	public void seteInvoiceErr(String eInvoiceErr) {
		this.eInvoiceErr = eInvoiceErr;
	}

	public InvoicePaxVO[] getSelectedInvPax() {
		return selectedInvPax;
	}

	public void setSelectedInvPax(InvoicePaxVO[] selectedInvPax) {
		this.selectedInvPax = selectedInvPax;
	}

	public List<InvoicePaxVO> getSavedSelectedInvPax() {
		return savedSelectedInvPax;
	}

	public void setSavedSelectedInvPax(List<InvoicePaxVO> savedSelectedInvPax) {
		this.savedSelectedInvPax = savedSelectedInvPax;
	}

	public List<InvoicePaxVO> getPassengerListForSplit() {
		return passengerListForSplit;
	}

	public void setPassengerListForSplit(List<InvoicePaxVO> passengerListForSplit) {
		this.passengerListForSplit = passengerListForSplit;
	}

	public Boolean getShowTrvlInsBadge() {
		return showTrvlInsBadge;
	}

	public void setShowTrvlInsBadge(Boolean showTrvlInsBadge) {
		this.showTrvlInsBadge = showTrvlInsBadge;
	}

	public String geteInvoiceTypeDesc() {
		return eInvoiceTypeDesc;
	}

	public void seteInvoiceTypeDesc(String eInvoiceTypeDesc) {
		this.eInvoiceTypeDesc = eInvoiceTypeDesc;
	}

	public Date geteInvoiceDtIssued() {
		return eInvoiceDtIssued;
	}

	public void seteInvoiceDtIssued(Date eInvoiceDtIssued) {
		this.eInvoiceDtIssued = eInvoiceDtIssued;
	}

	public Boolean getIsApplePoint() {
		return isApplePoint;
	}

	public void setIsApplePoint(Boolean isApplePoint) {
		this.isApplePoint = isApplePoint;
	}

	public String getPostingSalesStatus() {
		return postingSalesStatus;
	}

	public void setPostingSalesStatus(String postingSalesStatus) {
		this.postingSalesStatus = postingSalesStatus;
	}

	public String getPostingSalesRemarks() {
		return postingSalesRemarks;
	}

	public void setPostingSalesRemarks(String postingSalesRemarks) {
		this.postingSalesRemarks = postingSalesRemarks;
	}

	public double getTotalCurrency() {
		return totalCurrency;
	}

	public void setTotalCurrency(double totalCurrency) {
		this.totalCurrency = totalCurrency;
	}
}
