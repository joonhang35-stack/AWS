package com.bcs.zsg.product.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FeedbackVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idFeedback;
    private Long idTourPkg;
    private Long idTourDeparture;
    private Long customerId;
    private String feedbackStatus;
    private String customerAnswer;
    private String sectionType;
    private String questionCode;
    private String questionTextEn;
    private String answerType;
    private String answerLabelEn;
    private Integer scoreValue;
    private Double overallScore;
    private String remarks;
    private String answerText;
    private Integer numNights;
    private String tourCode;
    private String customerEmail;
    private String customerName;
    private String seqNo;


    private Date departureDate;
    private String opPic;
    private String paymentStatusCode;
    private String bookingStatusCode;
    private String customerStatusCode;
    private Long personId;


    private String  tokenUuid;

    // Invoice
    private Long    invId;
    private Long    idPs;
    private Long    idInv;
    private Long    parentInvId;
    private Long    parentInvTourBookingId;
    private String  invCode;
    private String  psNo;
    private String  docTypeCd;
    private String  docTypeStatus;
    private String  attnTo;

    // Tour Departure
    private String  tourDepCode;
    private String  docId;

    // Tour Package
    private String  tourPkgTypeCd;
    private String  tourPkgCode;

    // Customer
    private Long    customerPersonId;
    private Long    customerCorporateId;
    private String  corporateName;
    private String  customerStatusCd;

    // Person
    private String  salutationCd;
    private String  title;
    private String  firstName;
    private String  middleName;
    private String  lastName;

    // Tour Booking
    private Long    tourBookingId;
    private String  tourBookingTypeCd;
    private String  tourBookingPmntStatusCd;
    private String  tourBookingStatusCd;

    // Op Pic
    private String  opPicNames;
    
    private String comments;
    private String commentsHos;
    private String attachment;

    private Long idAttachment;
    private String attachmentUrl;
    private String filename;
    private String type;
    
//    private String servicesBooking;
//    private String servicesLeader;
//    private String servicesGuide;
//    private String servicesDriver;
//    private String transportCoach;
//    private String hotelAccom;
//    private String mealsArr;
//    private String overallImp;
//    private String marketingNewspaper;
//    private String marketingFamily;
//    private String marketingRadio;
//    private String marketingSocial;
//    private String newspaperChina;
//    private String newspaperMulu;
//    private String newspaperSinChew;
//    private String newspaperStar;
    private boolean summaryRow;
    private boolean selected;
    
    
    public Long getIdFeedback() { 
    	return idFeedback; 
	}
    public void setIdFeedback(Long idFeedback) { 
        this.idFeedback = idFeedback; 
    }

    public Long getIdTourPkg() {
        return idTourPkg;
    }

    public void setIdTourPkg(Long idTourPkg) {
        this.idTourPkg = idTourPkg;
    }

    public Long getIdTourDeparture() {
        return idTourDeparture;
    }

    public void setIdTourDeparture(Long idTourDeparture) {
        this.idTourDeparture = idTourDeparture;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public String getCustomerAnswer() {
        return customerAnswer;
    }

    public void setCustomerAnswer(String customerAnswer) {
        this.customerAnswer = customerAnswer;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getQuestionTextEn() {
        return questionTextEn;
    }

    public void setQuestionTextEn(String questionTextEn) {
        this.questionTextEn = questionTextEn;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getAnswerLabelEn() {
        return answerLabelEn;
    }

    public void setAnswerLabelEn(String answerLabelEn) {
        this.answerLabelEn = answerLabelEn;
    }

    public Integer getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(Integer scoreValue) {
        this.scoreValue = scoreValue;
    }

    public Double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Integer getNumNights() {
        return numNights;
    }

    public void setNumNights(Integer numNights) {
        this.numNights = numNights;
    }

    public String getTourCode() {
        return tourCode;
    }

    public void setTourCode(String tourCode) {
        this.tourCode = tourCode;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getOpPic() {
        return opPic;
    }

    public void setOpPic(String opPic) {
        this.opPic = opPic;
    }

    public String getPaymentStatusCode() {
        return paymentStatusCode;
    }

    public void setPaymentStatusCode(String paymentStatusCode) {
        this.paymentStatusCode = paymentStatusCode;
    }

    public String getBookingStatusCode() {
        return bookingStatusCode;
    }

    public void setBookingStatusCode(String bookingStatusCode) {
        this.bookingStatusCode = bookingStatusCode;
    }

    public String getCustomerStatusCode() {
        return customerStatusCode;
    }

    public void setCustomerStatusCode(String customerStatusCode) {
        this.customerStatusCode = customerStatusCode;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }


    public String getTokenUuid() {
        return tokenUuid;
    }
    public void setTokenUuid(String tokenUuid) {
        this.tokenUuid = tokenUuid;
    }


    // Invoice
    public Long getInvId() {
        return invId;
    }
    public void setInvId(Long invId) {
        this.invId = invId;
    }

    public Long getIdPs() {
        return idPs;
    }
    public void setIdPs(Long idPs) {
        this.idPs = idPs;
    }

    public Long getIdInv() {
        return idInv;
    }
    public void setIdInv(Long idInv) {
        this.idInv = idInv;
    }

    public Long getParentInvId() {
        return parentInvId;
    }
    public void setParentInvId(Long parentInvId) {
        this.parentInvId = parentInvId;
    }

    public Long getParentInvTourBookingId() {
        return parentInvTourBookingId;
    }
    public void setParentInvTourBookingId(Long parentInvTourBookingId) {
        this.parentInvTourBookingId = parentInvTourBookingId;
    }

    public String getInvCode() {
        return invCode;
    }
    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    public String getPsNo() {
        return psNo;
    }
    public void setPsNo(String psNo) {
        this.psNo = psNo;
    }

    public String getDocTypeCd() {
        return docTypeCd;
    }
    public void setDocTypeCd(String docTypeCd) {
        this.docTypeCd = docTypeCd;
    }

    public String getDocTypeStatus() {
        return docTypeStatus;
    }
    public void setDocTypeStatus(String docTypeStatus) {
        this.docTypeStatus = docTypeStatus;
    }

    public String getAttnTo() {
        return attnTo;
    }
    public void setAttnTo(String attnTo) {
        this.attnTo = attnTo;
    }

    // Tour Departure
    public String getTourDepCode() {
        return tourDepCode;
    }
    public void setTourDepCode(String tourDepCode) {
        this.tourDepCode = tourDepCode;
    }

    public String getDocId() {
        return docId;
    }
    public void setDocId(String docId) {
        this.docId = docId;
    }

    // Tour Package
    public String getTourPkgTypeCd() {
        return tourPkgTypeCd;
    }
    public void setTourPkgTypeCd(String tourPkgTypeCd) {
        this.tourPkgTypeCd = tourPkgTypeCd;
    }

    public String getTourPkgCode() {
        return tourPkgCode;
    }
    public void setTourPkgCode(String tourPkgCode) {
        this.tourPkgCode = tourPkgCode;
    }

    // Customer
    public Long getCustomerPersonId() {
        return customerPersonId;
    }
    public void setCustomerPersonId(Long customerPersonId) {
        this.customerPersonId = customerPersonId;
    }

    public Long getCustomerCorporateId() {
        return customerCorporateId;
    }
    public void setCustomerCorporateId(Long customerCorporateId) {
        this.customerCorporateId = customerCorporateId;
    }

    public String getCorporateName() {
        return corporateName;
    }
    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getCustomerStatusCd() {
        return customerStatusCd;
    }
    public void setCustomerStatusCd(String customerStatusCd) {
        this.customerStatusCd = customerStatusCd;
    }

    // Person
    public String getSalutationCd() {
        return salutationCd;
    }
    public void setSalutationCd(String salutationCd) {
        this.salutationCd = salutationCd;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Tour Booking
    public Long getTourBookingId() {
        return tourBookingId;
    }
    public void setTourBookingId(Long tourBookingId) {
        this.tourBookingId = tourBookingId;
    }

    public String getTourBookingTypeCd() {
        return tourBookingTypeCd;
    }
    public void setTourBookingTypeCd(String tourBookingTypeCd) {
        this.tourBookingTypeCd = tourBookingTypeCd;
    }

    public String getTourBookingPmntStatusCd() {
        return tourBookingPmntStatusCd;
    }
    public void setTourBookingPmntStatusCd(String tourBookingPmntStatusCd) {
        this.tourBookingPmntStatusCd = tourBookingPmntStatusCd;
    }

    public String getTourBookingStatusCd() {
        return tourBookingStatusCd;
    }
    public void setTourBookingStatusCd(String tourBookingStatusCd) {
        this.tourBookingStatusCd = tourBookingStatusCd;
    }

    // Op Pic
    public String getOpPicNames() {
        return opPicNames;
    }
    public void setOpPicNames(String opPicNames) {
        this.opPicNames = opPicNames;
    }

	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCommentsHos() {
        return commentsHos;
    }
    public void setCommentsHos(String commentsHos) {
        this.commentsHos = commentsHos;
    }

    public String getAttachment() {
        return attachment;
    }
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    private String attachmentHos;
    public String getAttachmentHos() {
        return attachmentHos;
    }
    public void setAttachmentHos(String attachmentHos) {
        this.attachmentHos = attachmentHos;
    }


    public Long getIdAttachment() {
        return idAttachment;
    }
    public void setIdAttachment(Long idAttachment) {
        this.idAttachment = idAttachment;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }
    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    private Long idFeedbackQuestion;
    private Long idFeedbackAnswer;

    public Long getIdFeedbackQuestion() {
        return idFeedbackQuestion;
    }
    public void setIdFeedbackQuestion(Long idFeedbackQuestion) {
        this.idFeedbackQuestion = idFeedbackQuestion;
    }

    public Long getIdFeedbackAnswer() {
        return idFeedbackAnswer;
    }
    public void setIdFeedbackAnswer(Long idFeedbackAnswer) {
        this.idFeedbackAnswer = idFeedbackAnswer;
    }
//
//	public String getServicesBooking() { return servicesBooking; }
//	public void setServicesBooking(String servicesBooking) { this.servicesBooking = servicesBooking; }
//
//	public String getServicesLeader() { return servicesLeader; }
//	public void setServicesLeader(String servicesLeader) { this.servicesLeader = servicesLeader; }
//
//	public String getServicesGuide() { return servicesGuide; }
//	public void setServicesGuide(String servicesGuide) { this.servicesGuide = servicesGuide; }
//
//	public String getServicesDriver() { return servicesDriver; }
//	public void setServicesDriver(String servicesDriver) { this.servicesDriver = servicesDriver; }
//
//	public String getTransportCoach() { return transportCoach; }
//	public void setTransportCoach(String transportCoach) { this.transportCoach = transportCoach; }
//
//	public String getHotelAccom() { return hotelAccom; }
//	public void setHotelAccom(String hotelAccom) { this.hotelAccom = hotelAccom; }
//
//	public String getMealsArr() { return mealsArr; }
//	public void setMealsArr(String mealsArr) { this.mealsArr = mealsArr; }
//
//	public String getOverallImp() { return overallImp; }
//	public void setOverallImp(String overallImp) { this.overallImp = overallImp; }
//
//	public String getMarketingNewspaper() { return marketingNewspaper; }
//	public void setMarketingNewspaper(String marketingNewspaper) { this.marketingNewspaper = marketingNewspaper; }
//
//	public String getMarketingFamily() { return marketingFamily; }
//	public void setMarketingFamily(String marketingFamily) { this.marketingFamily = marketingFamily; }
//
//	public String getMarketingRadio() { return marketingRadio; }
//	public void setMarketingRadio(String marketingRadio) { this.marketingRadio = marketingRadio; }
//
//	public String getMarketingSocial() { return marketingSocial; }
//	public void setMarketingSocial(String marketingSocial) { this.marketingSocial = marketingSocial; }
//
//	public String getNewspaperChina() { return newspaperChina; }
//	public void setNewspaperChina(String newspaperChina) { this.newspaperChina = newspaperChina; }
//
//	public String getNewspaperMulu() { return newspaperMulu; }
//	public void setNewspaperMulu(String newspaperMulu) { this.newspaperMulu = newspaperMulu; }
//
//	public String getNewspaperSinChew() { return newspaperSinChew; }
//	public void setNewspaperSinChew(String newspaperSinChew) { this.newspaperSinChew = newspaperSinChew; }
//
//	public String getNewspaperStar() { return newspaperStar; }
//	public void setNewspaperStar(String newspaperStar) { this.newspaperStar = newspaperStar; }

	public boolean isSummaryRow() { 
		return summaryRow; 
	}
	public void setSummaryRow(boolean summaryRow) { 
		this.summaryRow = summaryRow; 
	}

	public boolean isSelected() { 
		return selected; 
	}
	public void setSelected(boolean selected) { 
		this.selected = selected; 
	}

	// Manual Entry questions helper class
	public static class ManualQuestionAnswer implements Serializable {
		private static final long serialVersionUID = 1L;
		private Long questionId;
		private Integer seqNo;
		private String questionText;
		private String answerType;
		private List<AnswerOption> options;
		private Long selectedAnswerId;
		private List<Long> selectedAnswerIds;
		private String answerText;
		private String remarks;

		public Long getQuestionId() { 
			return questionId; 
		}
		
		public void setQuestionId(Long questionId) { 
			this.questionId = questionId;
		}
		
		public Integer getSeqNo() {
			return seqNo; 
		}
		
		public void setSeqNo(Integer seqNo) {
			this.seqNo = seqNo; 
		}
		
		public String getQuestionText() { 
			return questionText; 
		}
		
		public void setQuestionText(String questionText) {
			this.questionText = questionText;
		}
		
		public String getAnswerType() { 
			return answerType; 
		}
		
		public void setAnswerType(String answerType) {
			this.answerType = answerType; 
		}
		
		public List<AnswerOption> getOptions() { 
			return options; 
		}
		
		public void setOptions(List<AnswerOption> options) { 
			this.options = options;
		}
		
		public Long getSelectedAnswerId() {
			return selectedAnswerId; 
		}
		
		public void setSelectedAnswerId(Long selectedAnswerId) { 
			this.selectedAnswerId = selectedAnswerId; 
		}
		
		public List<Long> getSelectedAnswerIds() { 
			return selectedAnswerIds; 
		}
		
		public void setSelectedAnswerIds(List<Long> selectedAnswerIds) { 
			this.selectedAnswerIds = selectedAnswerIds; 
		}
		
		public String getAnswerText() {
			return answerText;
		}
		
		public void setAnswerText(String answerText) {
			this.answerText = answerText; 
		}
		
		public String getRemarks() { 
			return remarks; 
		}
		
		public void setRemarks(String remarks) {
			this.remarks = remarks; 
		}
	}

	public static class AnswerOption implements Serializable {
		private static final long serialVersionUID = 1L;
		private Long answerId;
		private String label;
		private Integer scoreValue;

		public AnswerOption(Long answerId, String label, Integer scoreValue) {
			this.answerId = answerId;
			this.label = label;
			this.scoreValue = scoreValue;
		}

		public Long getAnswerId() { 
			return answerId;
		}
		
		public String getLabel() {
			return label;
		}
		
		public Integer getScoreValue() { 
			return scoreValue; 
		}
	}
}