package com.bcs.zsg.common.web.bean;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import java.io.File;
import java.io.FileOutputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.bcs.zsg.common.helper.LookupItemConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.bo.AppSettingBO;
import com.bcs.zsg.maintenance.helper.ConstantAppSetting;
import com.bcs.zsg.maintenance.vo.AppSettingVO;
import com.bcs.zsg.product.bo.AirlineBO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.product.vo.AirlineScheduleVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourItineryVO;
import com.bcs.zsg.product.vo.TourPackageItineryVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.sales.bo.BookingBO;
import com.bcs.zsg.sales.bo.CustomerBO;
import com.bcs.zsg.sales.bo.InvoiceBO;
import com.bcs.zsg.sales.helper.SalesConstant;
import com.bcs.zsg.sales.vo.BookingChargeItemVO;
import com.bcs.zsg.sales.vo.BookingViewVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class EmailTemplateBean extends AppBackingBean {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient TourPackageBO tourPkgBO;
	@Autowired
	private transient AirlineBO airlineBO;
	@Autowired
	private transient BookingBO bookingBO;
	@Autowired
	private transient InvoiceBO invoiceBO;
	@Autowired
	private transient CustomerBO customerBO;
	@Autowired
	private transient AppSettingBO appSettingBO;
	
	private TourPackageVO tourPkgVO;
	private TourDepartureVO tourDepVO;
	
	private BookingViewVO bookingViewVO;
	private InvoiceVO invoiceVO;
	private String department;
	
	private String mailServer,from,to,mailSubject,mailContent;
	private String template;
	private List<String> fileNameList;
	private String tourDepId;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		
	}
	
	/**
	 * Initialization
	 */
	public void init() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			HttpServletRequest req = (HttpServletRequest) ec.getRequest();
			
			tourDepId = (String) req.getParameter("tourDepId");
			String bookingId = (String) req.getParameter("bookingId");
			
			department = getSessionInfoBean().getEmployeeVO().getDepartment();
			if (department.contains("wholesale")) department = "wholesale";
			
			AppSettingVO appSettingVO = appSettingBO.getAppSettingByCode(ConstantAppSetting.ADMIN_TOUR_DEP_ITINERARY_FROM_CMS);
			
			if (StringUtils.isEmpty(bookingId)) {
				// get tour departure info
				tourDepVO = tourPkgBO.getTourDepById(Long.parseLong(tourDepId));
				
				// get itinerary from CMS
				if (BaseConstant.YES.equals(appSettingVO.getValue())) {
					if (tourDepVO.getTourPkgDailyCms() != null) {
						List<TourPackageItineryVO> itineraryList = tourPkgBO.getTourPkgItineryListByIdTourPkgDailyItinerary(tourDepVO.getTourPkgDailyCms());
						if (!CollectionUtils.isEmpty(itineraryList)) {
							tourDepVO.setTourItineryList(new ArrayList<TourItineryVO>());
							
							for (TourPackageItineryVO itineraryVO : itineraryList) {
								TourItineryVO tourItineryVO = new TourItineryVO();
								tourItineryVO.setName(itineraryVO.getName());
								tourItineryVO.setLangCd(itineraryVO.getLangCd());
								tourItineryVO.setPath(itineraryVO.getPath());
								tourItineryVO.setTypeCd(itineraryVO.getTypeCd());
								tourDepVO.getTourItineryList().add(tourItineryVO);
							}
						}
					}
				} else {
					tourDepVO.setTourItineryList(tourPkgBO.getTourItineryList(tourDepVO.getId()));
					
					if (CollectionUtils.isEmpty(tourDepVO.getTourItineryList()))
						tourDepVO.setTourItineryList(new ArrayList<TourItineryVO>());
					
					for (TourPackageItineryVO itineryPkgVO : tourPkgBO.getTourPkgItineryList(tourDepVO.getIdTourPkg())) {
						boolean isMatch = false;
						for (TourItineryVO itineryVO : tourDepVO.getTourItineryList()) {
							if (StringUtils.equals(itineryVO.getLangCd(), itineryPkgVO.getLangCd()) && StringUtils.equals(itineryVO.getTypeCd(), itineryPkgVO.getTypeCd())) {
								isMatch = true;
								break;
							}
						}
						
						if (!isMatch) {
							TourItineryVO tourItineryVO = new TourItineryVO();
							tourItineryVO.setName(itineryPkgVO.getName());
							tourItineryVO.setLangCd(itineryPkgVO.getLangCd());
							tourItineryVO.setPath(itineryPkgVO.getPath());
							tourItineryVO.setTypeCd(itineryPkgVO.getTypeCd());
							tourItineryVO.setCreatedBy(itineryPkgVO.getCreatedBy());
							tourItineryVO.setCreatedDate(itineryPkgVO.getCreatedDate());
							tourItineryVO.setIsFromPkg(true);
							tourDepVO.getTourItineryList().add(tourItineryVO);
						}
					}
				}
				tourDepVO.setTourHotelList(tourPkgBO.getTourHotelList(tourDepVO.getId()));
				tourDepVO.setAirlineVO(airlineBO.getAirline(tourDepVO.getIdAirline()));
				// set airline fee charges
				AirlineScheduleVO scheduleVO = airlineBO.getAirlineSchedule(tourDepVO);
				tourDepVO.setAirlineScheduleVO(scheduleVO);
				tourPkgBO.setMiscAdtChd(tourDepVO, scheduleVO, null);
				// get tour package info
				tourPkgVO = tourPkgBO.getTourPackageById(tourDepVO.getIdTourPkg());
			
			// booking information
			} else if (StringUtils.isNotEmpty(bookingId)) {
				bookingViewVO = bookingBO.getBookingView(Long.parseLong(bookingId));
				bookingViewVO.setBookingChargeItemList(new ArrayList<BookingChargeItemVO>(bookingViewVO.getBookingChargeItemSet()));
				bookingViewVO.setCustomerVO(customerBO.getCustomer(bookingViewVO.getIdCust()));
				bookingViewVO.getTourDepViewVO().setAirlineVO(airlineBO.getAirline(bookingViewVO.getTourDepViewVO().getIdAirline()));
				bookingViewVO.getTourDepViewVO().setTourItineryList(tourPkgBO.getTourItineryList(bookingViewVO.getTourDepViewVO().getId()));
				bookingViewVO.getTourDepViewVO().setAirlineScheduleVO(airlineBO.getAirlineSchedule(bookingViewVO.getTourDepViewVO()));
				bookingViewVO.getTourDepViewVO().setTktValidity(bookingViewVO.getTourDepViewVO().getAirlineScheduleVO().getTktValidity());
				
				tourPkgBO.setMiscAdtChd(bookingViewVO.getTourDepViewVO(), null, bookingViewVO);
				invoiceVO = invoiceBO.getInvoice(bookingViewVO.getId());
				
				/*double miscellaneous = 0;
				for (BookingChargeItemVO itemVO : bookingViewVO.getBookingChargeItemSet()) {
					if (ProductConstant.TOUR_DEP_ITM_CD_FT_SGL.equals(itemVO.getCode())) { bookingViewVO.setAmtFullSgl(itemVO.getAmount()); bookingViewVO.setPaxFullSgl(itemVO.getQuantity()); }
					else if (ProductConstant.TOUR_DEP_ITM_CD_FT_TWN.equals(itemVO.getCode())) { bookingViewVO.setAmtFullTwn(itemVO.getAmount()); bookingViewVO.setPaxFullTwn(itemVO.getQuantity()); }
					else if (ProductConstant.TOUR_DEP_ITM_CD_FT_CTW.equals(itemVO.getCode())) { bookingViewVO.setAmtFullCtw(itemVO.getAmount()); bookingViewVO.setPaxFullCtw(itemVO.getQuantity()); }
					else if (ProductConstant.TOUR_DEP_ITM_CD_FT_CWB.equals(itemVO.getCode())) { bookingViewVO.setAmtFullCeb(itemVO.getAmount()); bookingViewVO.setPaxFullCeb(itemVO.getQuantity()); }
					else if (ProductConstant.TOUR_DEP_ITM_CD_FT_CNB.equals(itemVO.getCode())) { bookingViewVO.setAmtFullCnb(itemVO.getAmount()); bookingViewVO.setPaxFullCnb(itemVO.getQuantity()); }
					else if (ProductConstant.TOUR_DEP_ITM_CD_FT_INFT.equals(itemVO.getCode())) { bookingViewVO.setAmtFullInft(itemVO.getAmount()); bookingViewVO.setPaxFullInft(itemVO.getQuantity()); }
					else if (ProductConstant.TOUR_DEP_ITM_CD_GA_SGL.equals(itemVO.getCode())) { bookingViewVO.setAmtGrndSgl(itemVO.getAmount()); bookingViewVO.setPaxGrndSgl(itemVO.getQuantity()); }
					else if (ProductConstant.TOUR_DEP_ITM_CD_GA_TWN.equals(itemVO.getCode())) { bookingViewVO.setAmtGrndTwn(itemVO.getAmount()); bookingViewVO.setPaxGrndTwn(itemVO.getQuantity()); }
					else if (ProductConstant.TOUR_DEP_ITM_CD_GA_CTW.equals(itemVO.getCode())) { bookingViewVO.setAmtGrndCtw(itemVO.getAmount()); bookingViewVO.setPaxGrndCtw(itemVO.getQuantity()); }
					else if (ProductConstant.TOUR_DEP_ITM_CD_GA_CWB.equals(itemVO.getCode())) { bookingViewVO.setAmtGrndCeb(itemVO.getAmount()); bookingViewVO.setPaxGrndCeb(itemVO.getQuantity()); }
					else if (ProductConstant.TOUR_DEP_ITM_CD_GA_CNB.equals(itemVO.getCode())) { bookingViewVO.setAmtGrndCnb(itemVO.getAmount()); bookingViewVO.setPaxGrndCnb(itemVO.getQuantity()); }
					else if (ProductConstant.TOUR_DEP_ITM_CD_GA_INFT.equals(itemVO.getCode())) { bookingViewVO.setAmtGrndInft(itemVO.getAmount()); bookingViewVO.setPaxGrndInft(itemVO.getQuantity()); }
					else if (ProductConstant.TOUR_DEP_ITM_CD_DISC.equals(itemVO.getCode())) { bookingViewVO.setDiscount(itemVO.getAmount()); }
					else if (ProductConstant.AIRLINE_ITM_CD_APT_ADT.equals(itemVO.getCode())) { bookingViewVO.setAirlineAptAdt(itemVO.getAmount()); }
					else if (ProductConstant.AIRLINE_ITM_CD_APT_CHD.equals(itemVO.getCode())) { bookingViewVO.setAirlineAptChd(itemVO.getAmount()); }
					else if (ProductConstant.AIRLINE_ITM_CD_FUEL_ADT.equals(itemVO.getCode())) { bookingViewVO.setAirlineYqAdt(itemVO.getAmount()); }
					else if (ProductConstant.AIRLINE_ITM_CD_FUEL_CHD.equals(itemVO.getCode())) { bookingViewVO.setAirlineYqChd(itemVO.getAmount()); }
					else {
						if (ProductConstant.TOUR_DEP_ITM_TYPE_AIRLINE.equals(itemVO.getTypeCd())) {
							miscellaneous += itemVO.getAmount();
						}
					}
				}
				bookingViewVO.setMiscellaneous(miscellaneous);*/
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	@SuppressWarnings("unused")
	public void sendMessage() throws Exception{
		try {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest req = (HttpServletRequest) ec.getRequest();
		
		tourDepVO = tourPkgBO.getTourDepById(Long.parseLong(tourDepId));
		tourDepVO.setTourItineryList(tourPkgBO.getTourItineryList(tourDepVO.getId()));
		MimeMessage msg = new MimeMessage((Session) null);
	    msg.addFrom(InternetAddress.parse(""));
	    msg.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(""));
	    msg.setRecipients(Message.RecipientType.CC,
	            InternetAddress.parse(""));
	    msg.setSubject("");
	    msg.setHeader("X-Unsent", "1");

	    MimeMultipart mmp = new MimeMultipart();
	    MimeBodyPart body = new MimeBodyPart();
	    body.setDisposition(MimePart.INLINE);
	    body.setContent(template, "text/html; charset=UTF-8");
	    mmp.addBodyPart(body);
	    
	    /*for(TourItineryVO itineryVO : tourDepVO.getTourItineryList()) {
	    	addAttachment(mmp, "C:/upload/" + itineryVO.getPath() + "/" + itineryVO.getName());
	    }
	    
	    mmp.addBodyPart(body);*/
	    for(TourItineryVO itineryVO : tourDepVO.getTourItineryList()) {
		    MimeBodyPart att = new MimeBodyPart();
		    att.attachFile("C:/upload" + itineryVO.getPath() + "/" + itineryVO.getName());
		    mmp.addBodyPart(att);
	    }

	    msg.setContent(mmp);
	    msg.saveChanges();

	    File resultEmail = File.createTempFile("test", ".eml");
	    try (FileOutputStream fs = new FileOutputStream(resultEmail)) {
	        msg.writeTo(fs);
	        fs.flush();
	        fs.getFD().sync();
	    }

	    System.out.println(resultEmail.getCanonicalPath());

	    ProcessBuilder pb = new ProcessBuilder();
	    pb.command("cmd.exe", "/C", "start", "outlook.exe",
	            "/eml", resultEmail.getCanonicalPath());
	    Process p = pb.start();
	    try {
	        p.waitFor();
	    } finally {
	        p.getErrorStream().close();
	        p.getInputStream().close();
	        p.getErrorStream().close();
	        p.destroy();
	    }}
		catch (Throwable t) {
			errorResult(t);
		}

	}
	
	/*private static void addAttachment(Multipart multipart, String filename) throws MessagingException
	{
	    DataSource source = new FileDataSource(filename);
	    BodyPart messageBodyPart = new MimeBodyPart();        
	    messageBodyPart.setDataHandler(new DataHandler(source));
	    messageBodyPart.setFileName(filename);
	    multipart.addBodyPart(messageBodyPart);
	}*/
	
	/**
	 * 
	 * @param pmntTypeCd
	 * @return
	 */
	public Date getExpiryDate(Date bookingDate, String pmntTypeCd) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(bookingDate);
		if (SalesConstant.BOOKING_PMNT_STATUS_KIV.equals(pmntTypeCd))
			cal.add(Calendar.DATE, Integer.parseInt(LookupItemUtils.getGlobalConfigValue(LookupItemConstant.GC_CATEGORY, LookupItemConstant.GC_KIV_EXP)));
		else if (LookupItemConstant.GC_PAID_EXP.equals(pmntTypeCd))
			cal.add(Calendar.DATE, Integer.parseInt(LookupItemUtils.getGlobalConfigValue(LookupItemConstant.GC_CATEGORY, LookupItemConstant.GC_PAID_EXP)));
		return cal.getTime();
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/
	
	/**
	 * @return the tourPkgVO
	 */
	public TourPackageVO getTourPkgVO() {
		return tourPkgVO;
	}

	/**
	 * @return the tourDepVO
	 */
	public TourDepartureVO getTourDepVO() {
		return tourDepVO;
	}

	/**
	 * @return the bookingViewVO
	 */
	public BookingViewVO getBookingViewVO() {
		return bookingViewVO;
	}

	/**
	 * @return the invoiceVO
	 */
	public InvoiceVO getInvoiceVO() {
		return invoiceVO;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	public String getMailServer() {
		return mailServer;
	}

	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public List<String> getFileNameList() {
		return fileNameList;
	}

	public void setFileNameList(List<String> fileNameList) {
		this.fileNameList = fileNameList;
	}

	public String getTourDepId() {
		return tourDepId;
	}

	public void setTourDepId(String tourDepId) {
		this.tourDepId = tourDepId;
	}

}
