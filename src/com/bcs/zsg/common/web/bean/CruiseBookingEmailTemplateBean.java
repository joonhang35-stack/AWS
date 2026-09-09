package com.bcs.zsg.common.web.bean;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.bo.AppSettingBO;
import com.bcs.zsg.maintenance.helper.ConstantAppSetting;
import com.bcs.zsg.maintenance.vo.AppSettingVO;
import com.bcs.zsg.product.bo.CruiseBO;
import com.bcs.zsg.product.bo.TourCruiseCabinBO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.CruiseScheduleChargeVO;
import com.bcs.zsg.product.vo.CruiseScheduleItemVO;
import com.bcs.zsg.product.vo.CruiseScheduleVO;
import com.bcs.zsg.product.vo.TourCruiseCabinDiscountVO;
import com.bcs.zsg.product.vo.TourCruiseCabinVO;
import com.bcs.zsg.product.vo.TourDepItemVO;
import com.bcs.zsg.product.vo.TourDepartureDiscountVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourDepartureViewVO;
import com.bcs.zsg.product.vo.TourItineryVO;
import com.bcs.zsg.product.vo.TourPackageItineryVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.sales.bo.BookingBO;
import com.bcs.zsg.sales.bo.CustomerBO;
import com.bcs.zsg.sales.bo.InvoiceBO;
import com.bcs.zsg.sales.vo.BookingChargeItemVO;
import com.bcs.zsg.sales.vo.BookingViewVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class CruiseBookingEmailTemplateBean extends AppBackingBean {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient TourPackageBO tourPkgBO;
	@Autowired
	private transient CruiseBO cruiseBO;
	@Autowired
	private transient BookingBO bookingBO;
	@Autowired
	private transient InvoiceBO invoiceBO;
	@Autowired
	private transient CustomerBO customerBO;
	@Autowired
	private transient AppSettingBO appSettingBO;
	@Autowired
	private transient TourCruiseCabinBO tourCruiseCabinBO;
	
	private TourPackageVO tourPkgVO;
	private TourDepartureVO tourDepVO;
	
	private BookingViewVO bookingViewVO;
	private InvoiceVO invoiceVO;
	private String department;
	
	private String tourDepId;
	
	List<TourCruiseCabinVO> tourCruiseCabinList;
	List<BookingChargeItemVO> bookingChargeItemList;
	List<BookingChargeItemVO> bookingChargeItemListFull;
	List<BookingChargeItemVO> bookingChargeItemListBiz;
	List<BookingChargeItemVO> bookingChargeItemListGrnd;
	List<BookingChargeItemVO> discountList; // Discount
	List<BookingChargeItemVO> depositList; // Deposit Required
	List<BookingChargeItemVO> miscChargesList; // Miscellaneous Charges
	List<BookingChargeItemVO> tourMiscChargesList; // Miscellaneous Charges - TOUR
	List<BookingChargeItemVO> commList; // AGENT COMM
	List<BookingChargeItemVO> additionalTaxList; // ADDITIONAL TAX
	List<CruiseScheduleItemVO> cruiseScheduleItemList;
	
	// FARE PARTICULAR
	private double subTotal = 0.00;
	private double totalAmt = 0.00;
	private double ttlAgentComm = 0.00;
	private int adultTourPax = 0;
	private int adultGroundPax = 0;
	private int childTourPax = 0;
	private int childGroundPax = 0;
	
	private String templateType = "";
	
	private Date today = new Date();
	
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
			String withOutPax = (String) req.getParameter("withOutPax");
			
			Long idCompany = getSessionInfoBean().getCompanyVO().getId();
			tourCruiseCabinList = tourCruiseCabinBO.getTourCruiseCabinList(Long.valueOf(tourDepId), idCompany);
			
			bookingChargeItemList = new ArrayList<BookingChargeItemVO>();
			subTotal = 0.00;
			totalAmt = 0.00;
			
			department = getSessionInfoBean().getEmployeeVO().getDepartment();
			if (department.contains("wholesale")) department = "wholesale";
			
			AppSettingVO appSettingVO = appSettingBO.getAppSettingByCode(ConstantAppSetting.ADMIN_TOUR_DEP_ITINERARY_FROM_CMS);
			
			if (StringUtils.isNotEmpty(withOutPax) && "Y".equals(withOutPax)) {
				templateType = "ENQUIRYWITHOUTPAX";
				
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
				
				tourDepVO.setCruiseVO(cruiseBO.getCruise(tourDepVO.getIdCruise()));
				// set cruise fee charges
				CruiseScheduleVO cruiseScheduleVO = cruiseBO.getCruiseSchedule(tourDepVO);
				tourDepVO.setCruiseScheduleVO(cruiseScheduleVO);
				// get tour package info
				tourPkgVO = tourPkgBO.getTourPackageById(tourDepVO.getIdTourPkg());
				
				additionalTaxList = new ArrayList<BookingChargeItemVO>();
				commList = new ArrayList<BookingChargeItemVO>();
				bookingViewVO = new BookingViewVO();
				
				setEnquiryWithoutPaxBookingChargeItemList();
				
				double miscAdt = 0;
				double trvlIns = 0;
				double visa = 0;
				double ac = 0;
				double tipping = 0;
				double deviation = 0;
				double portCharges = 0;
				double extraMisc = 0;
				int tktValidity = 0;
				
				if (cruiseScheduleVO != null) {
					
					trvlIns = cruiseScheduleVO.getAmtTrvlIns();
					visa = cruiseScheduleVO.getAmtVisa();
					ac = cruiseScheduleVO.getAmtAC();
					portCharges = cruiseScheduleVO.getAmtAptAdt();
					tipping = cruiseScheduleVO.getAmtTipping();
					deviation = cruiseScheduleVO.getAmtDeviation();
					tktValidity = cruiseScheduleVO.getTktValidity();
					
					List<CruiseScheduleChargeVO> chargeList = cruiseBO.getCruiseScheduleChargeList(cruiseScheduleVO.getId());
					if (CollectionUtils.isNotEmpty(chargeList)) {
						cruiseScheduleVO.setExtraItemChargeList(chargeList);
						for (CruiseScheduleChargeVO chargeVO : chargeList) {
							if (chargeVO.getIsMisc()) {
								extraMisc += chargeVO.getAmount();
								BookingChargeItemVO vo = new BookingChargeItemVO();
								vo.setDesc(chargeVO.getTypeDesc());
								vo.setAmount(chargeVO.getAmount());
								additionalTaxList.add(vo);
							}
						}
					}
				}
				
				miscAdt = portCharges + trvlIns + visa + ac + tipping + extraMisc;
				
				tourDepVO.setMiscAdt(miscAdt);
				tourDepVO.setCruiseAptAdt(portCharges);
				tourDepVO.setCruiseTrvlIns(trvlIns);
				tourDepVO.setCruiseVisa(visa);
				tourDepVO.setCruiseAC(ac);
				tourDepVO.setCruiseTipping(tipping);
				tourDepVO.setCruiseDeviation(deviation);
				tourDepVO.setCruiseExtra(extraMisc);
				tourDepVO.setCruiseTotAdt(miscAdt);
				tourDepVO.setTktValidity(tktValidity);
				
				bookingViewVO.setCruiseVisa(visa);
				bookingViewVO.setCruiseAC(ac);
				bookingViewVO.setCruiseTipping(tipping);
				bookingViewVO.setCruiseTrvlIns(trvlIns);
				bookingViewVO.setCruiseAptAdt(portCharges);
				bookingViewVO.setCruiseTotAdt(miscAdt);
				
				BookingChargeItemVO bciVO;
				
				// AGENT COMM - AC
				if (tourDepVO.getCnaAdt() > 0) {
					bciVO = new BookingChargeItemVO();
					bciVO.setDesc("AC");
					bciVO.setAmount(tourDepVO.getCnaAdt());
					bciVO.setTtlAmt(bciVO.getAmount());
					commList.add(bciVO);
				}
				
				// AGENT COMM - SI
				if (tourDepVO.getCsiAdt() > 0) {
					bciVO = new BookingChargeItemVO();
					bciVO.setDesc("SI");
					bciVO.setAmount(tourDepVO.getCsiAdt());
					bciVO.setTtlAmt(bciVO.getAmount());
					commList.add(bciVO);
				}
				
				cruiseScheduleItemList = cruiseScheduleVO.getCruiseScheduleItemList();
				
				// https://applevacationsmy.monday.com/boards/1957151896/pulses/2010746151
				// Request: Please make it not to show flight scheduled on website, aws and email if [Show Flight Schedule] = NO
				if (!tourDepVO.getIsShowCruise()) {
					if (tourDepVO.getCruiseScheduleVO() != null && CollectionUtils.isNotEmpty(tourDepVO.getCruiseScheduleVO().getCruiseScheduleItemList())) {
						tourDepVO.getCruiseScheduleVO().setCruiseScheduleItemList(new ArrayList<CruiseScheduleItemVO>());
					}
					if (CollectionUtils.isNotEmpty(cruiseScheduleItemList)) {
						cruiseScheduleItemList = new ArrayList<CruiseScheduleItemVO>();
					}
				}
			
			} else if (StringUtils.isEmpty(bookingId)) {
				templateType = "ENQUIRY";
				
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
				
				tourDepVO.setCruiseVO(cruiseBO.getCruise(tourDepVO.getIdCruise()));
				// set cruise fee charges
				CruiseScheduleVO cruiseScheduleVO = cruiseBO.getCruiseSchedule(tourDepVO);
				tourDepVO.setCruiseScheduleVO(cruiseScheduleVO);
				// get tour package info
				tourPkgVO = tourPkgBO.getTourPackageById(tourDepVO.getIdTourPkg());
				
//				subTotal = 0.00;
//				totalAmt = 0.00;
				ttlAgentComm = 0.00;
				int ttlPax = 0;
//				adultTourPax = 0;
//				adultGroundPax = 0;
//				childTourPax = 0;
//				childGroundPax = 0;
//				bookingChargeItemList = new ArrayList<BookingChargeItemVO>();
				additionalTaxList = new ArrayList<BookingChargeItemVO>();
				miscChargesList = new ArrayList<BookingChargeItemVO>();
				depositList = new ArrayList<BookingChargeItemVO>();
				discountList = new ArrayList<BookingChargeItemVO>();
				commList = new ArrayList<BookingChargeItemVO>();
				bookingViewVO = new BookingViewVO();
				
				BookingChargeItemVO bciVO = new BookingChargeItemVO();
				
				String cabinStr = (String) req.getParameter("cabinPax");
				System.out.println("cabinPax: " + cabinStr);
				
				setEnquiryBookingChargeItemList(cabinStr, tourDepVO); // use tourDepVO.paxConfirmed to store total pax
				ttlPax = tourDepVO.getPaxConfirmed();
				
				System.out.println("ttlPax: " + ttlPax);
				
				double miscAdt = 0;
//				double miscChd = 0;
				double portCharges = 0;
				double trvlIns = 0;
				double visa = 0;
				double ac = 0;
				double tipping = 0;
				double deviation = 0;
				double extraMisc = 0;
				int tktValidity = 0;
				
				if (cruiseScheduleVO != null) {
					portCharges = cruiseScheduleVO.getAmtAptAdt();
					trvlIns = cruiseScheduleVO.getAmtTrvlIns();
					visa = cruiseScheduleVO.getAmtVisa();
					ac = cruiseScheduleVO.getAmtAC();
					tipping = cruiseScheduleVO.getAmtTipping();
					deviation = cruiseScheduleVO.getAmtDeviation();
					tktValidity = cruiseScheduleVO.getTktValidity();
					
					List<CruiseScheduleChargeVO> chargeList = cruiseBO.getCruiseScheduleChargeList(cruiseScheduleVO.getId());
					if (CollectionUtils.isNotEmpty(chargeList)) {
						cruiseScheduleVO.setExtraItemChargeList(chargeList);
						for (CruiseScheduleChargeVO chargeVO : chargeList) {
							if (chargeVO.getIsMisc()) {
								extraMisc += chargeVO.getAmount();
								BookingChargeItemVO vo = new BookingChargeItemVO();
								vo.setDesc(chargeVO.getTypeDesc());
								vo.setAmount(chargeVO.getAmount());
								additionalTaxList.add(vo);
							}
						}
					}
				}
				
				miscAdt = portCharges + trvlIns + visa + ac + tipping + extraMisc; // cruise only have one misc
				
				tourDepVO.setMiscAdt(miscAdt);
//				tourDepVO.setMiscChd(miscChd);
				tourDepVO.setCruiseAptAdt(portCharges);
				tourDepVO.setCruiseTrvlIns(trvlIns);
				tourDepVO.setCruiseVisa(visa);
				tourDepVO.setCruiseAC(ac);
				tourDepVO.setCruiseTipping(tipping);
				tourDepVO.setCruiseDeviation(deviation);
				tourDepVO.setCruiseExtra(extraMisc);
				tourDepVO.setCruiseTotAdt(miscAdt);
//				tourDepVO.setCruiseTotChd(miscChd);
				tourDepVO.setTktValidity(tktValidity);
//				tourDepVO.setGrdMiscAdtChd(grdMiscAdtChd);
				
				bookingViewVO.setCruiseVisa(visa);
				bookingViewVO.setCruiseAC(ac);
				bookingViewVO.setCruiseTipping(tipping);
				bookingViewVO.setCruiseTrvlIns(trvlIns);
				bookingViewVO.setCruiseAptAdt(portCharges);
				bookingViewVO.setCruiseTotAdt(miscAdt);
//				bookingViewVO.setCruiseTotChd(miscChd);
				
				totalAmt += subTotal;
				
				// Deposit
				if (ttlPax > 0) {
					bciVO = new BookingChargeItemVO();
					bciVO.setDesc("AMOUNT");
					
					if (tourPkgVO.getIsDepositPercent()) {
						bciVO.setAmount(totalAmt * tourPkgVO.getDeposit() / 100);
						bciVO.setQuantity(1);
						bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
					} else {
						bciVO.setAmount(tourPkgVO.getDeposit());
						bciVO.setQuantity(ttlPax);
						bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
					}
					depositList.add(bciVO);
					
					// AGENT COMM - AC
					if ( tourDepVO.getCnaAdt() > 0) {
						bciVO = new BookingChargeItemVO();
						bciVO.setDesc("AC");
						bciVO.setAmount( tourDepVO.getCnaAdt());
						bciVO.setQuantity(ttlPax);
						bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
						ttlAgentComm += bciVO.getTtlAmt();
						commList.add(bciVO);
					}
					
					// AGENT COMM - SI
					if ( tourDepVO.getCsiAdt() > 0) {
						bciVO = new BookingChargeItemVO();
						bciVO.setDesc("SI");
						bciVO.setAmount( tourDepVO.getCsiAdt());
						bciVO.setQuantity(ttlPax);
						bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
						ttlAgentComm += bciVO.getTtlAmt();
						commList.add(bciVO);
					}
				}
				
				cruiseScheduleItemList = cruiseScheduleVO.getCruiseScheduleItemList();
				
				// https://applevacationsmy.monday.com/boards/1957151896/pulses/2010746151
				// Request: Please make it not to show flight scheduled on website, aws and email if [Show Flight Schedule] = NO
				if (!tourDepVO.getIsShowCruise()) {
					if (tourDepVO.getCruiseScheduleVO() != null && CollectionUtils.isNotEmpty(tourDepVO.getCruiseScheduleVO().getCruiseScheduleItemList())) {
						tourDepVO.getCruiseScheduleVO().setCruiseScheduleItemList(new ArrayList<CruiseScheduleItemVO>());
					}
					if (CollectionUtils.isNotEmpty(cruiseScheduleItemList)) {
						cruiseScheduleItemList = new ArrayList<CruiseScheduleItemVO>();
					}
				}
				
			// Booking information
			} else if (StringUtils.isNotEmpty(bookingId)) {
				templateType = "BOOKING";
				
				bookingViewVO = bookingBO.getBookingView(Long.parseLong(bookingId));
				bookingViewVO.setBookingChargeItemList(new ArrayList<BookingChargeItemVO>(bookingViewVO.getBookingChargeItemSet()));
				bookingViewVO.setCustomerVO(customerBO.getCustomer(bookingViewVO.getIdCust()));
				bookingViewVO.getTourDepViewVO().setCruiseVO(cruiseBO.getCruise(bookingViewVO.getTourDepViewVO().getIdCruise()));
				bookingViewVO.getTourDepViewVO().setCruiseScheduleVO(cruiseBO.getCruiseSchedule(bookingViewVO.getTourDepViewVO()));
				
				// get itinerary from CMS
				if (BaseConstant.YES.equals(appSettingVO.getValue())) {
					if (bookingViewVO.getTourDepViewVO().getTourPkgDailyCms() != null) {
						List<TourPackageItineryVO> itineraryList = tourPkgBO.getTourPkgItineryListByIdTourPkgDailyItinerary(bookingViewVO.getTourDepViewVO().getTourPkgDailyCms());
						if (!CollectionUtils.isEmpty(itineraryList)) {
							bookingViewVO.getTourDepViewVO().setTourItineryList(new ArrayList<TourItineryVO>());
							
							for (TourPackageItineryVO itineraryVO : itineraryList) {
								TourItineryVO tourItineryVO = new TourItineryVO();
								tourItineryVO.setName(itineraryVO.getName());
								tourItineryVO.setLangCd(itineraryVO.getLangCd());
								tourItineryVO.setPath(itineraryVO.getPath());
								tourItineryVO.setTypeCd(itineraryVO.getTypeCd());
								bookingViewVO.getTourDepViewVO().getTourItineryList().add(tourItineryVO);
							}
						}
					}
				} else {
					bookingViewVO.getTourDepViewVO().setTourItineryList(tourPkgBO.getTourItineryList(bookingViewVO.getTourDepViewVO().getId()));
					
					if (CollectionUtils.isEmpty(bookingViewVO.getTourDepViewVO().getTourItineryList())) {
						bookingViewVO.getTourDepViewVO().setTourItineryList(new ArrayList<TourItineryVO>());
					
						for (TourPackageItineryVO itineryPkgVO : tourPkgBO.getTourPkgItineryList(bookingViewVO.getTourDepViewVO().getTourPkgVO().getId())) {
							boolean isMatch = false;
							for (TourItineryVO itineryVO : bookingViewVO.getTourDepViewVO().getTourItineryList()) {
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
								bookingViewVO.getTourDepViewVO().getTourItineryList().add(tourItineryVO);
							}
						}
					}
				}
				
				setMiscAdtChd(bookingViewVO.getTourDepViewVO(), bookingViewVO);
				invoiceVO = invoiceBO.getInvoice(bookingViewVO.getId());
			}
			
			System.out.println("templateType: " + templateType);
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void calDiscountList(TourDepartureVO tourDepVO, int ftTwn, int ftSgl, int ftCtw, int ftCwb, int ftCnb, boolean isDiscountPercent) throws BusinessException {
		// Tour Departure Discount
		List<TourDepartureDiscountVO> discountVO = tourPkgBO.getTourDepDiscount(tourDepVO.getId());
		
		if (isDiscountPercent) {
			if (CollectionUtils.isNotEmpty(discountVO) && discountVO.get(0).getDiscountPax() > 0) {
				BookingChargeItemVO bciVO = new BookingChargeItemVO();
				bciVO.setDesc("DISCOUNT");
				bciVO.setAmount(-1 * subTotal * discountVO.get(0).getDiscountAmt() / 100);
				bciVO.setQuantity(1);
				bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
				totalAmt += bciVO.getTtlAmt();
				discountList.add(bciVO);
			}
		} else {
			double discount1 = 0.00;
			double discount2 = 0.00;
			int disc1Pax = 0;
			int disc2Pax = 0;
			if (CollectionUtils.isNotEmpty(discountVO)) {
				int count = 1;
				
				for (TourDepartureDiscountVO vo : discountVO) {
					double discountAmt = vo.getDiscountAmt();
					int discountPax = vo.getDiscountPax();
					
					if (count == 1) {
						discount1 = discountAmt;
						disc1Pax = discountPax;
					} else if (count == 2) {
						discount2 = discountAmt;
						disc2Pax = discountPax;
					}
					count++;
				}
			}
			
			int totalDisc1Pax = 0;
			int totalDisc2Pax = 0;
			int totalPax = ftTwn + ftSgl + ftCtw + ftCwb + ftCnb;
			int totalDiscPax = disc1Pax + disc2Pax;
			int remaining = 0;
			
			if (disc1Pax != 0) {
				if (disc2Pax != 0) {
					if (totalPax > totalDiscPax) {
						totalDisc1Pax = disc1Pax;
						totalDisc2Pax = disc2Pax;
					} else if (totalPax == totalDiscPax) {
						totalDisc1Pax = disc1Pax;
						totalDisc2Pax = disc2Pax;
					} else if (totalDiscPax > totalPax) {
						if(disc1Pax > totalPax){
							totalDisc1Pax = totalPax;
						} else if (disc1Pax == totalPax) {
							totalDisc1Pax = disc1Pax;
						} else if (totalPax > disc1Pax) {
							remaining = totalPax - disc1Pax;
							disc2Pax = remaining;
							totalDisc1Pax = disc1Pax;
							totalDisc2Pax = disc2Pax;
						}
					}
				} else {
					if (totalPax > disc1Pax) {
						totalDisc1Pax = disc1Pax;
					} else if (totalPax == disc1Pax) {
						totalDisc1Pax = disc1Pax;
					} else if (disc1Pax > totalPax) {
						totalDisc1Pax = totalPax;
					}
				}
			} else {
				if (disc2Pax != 0) {
					if (totalPax > disc2Pax){
						totalDisc2Pax = disc2Pax;
					} else if(totalPax == disc2Pax) {
						totalDisc2Pax = disc2Pax;
					} else if(disc2Pax > totalPax) {
						totalDisc2Pax = totalPax;
					}
				} else {
					totalDisc1Pax = 0;
					totalDisc2Pax = 0;
				}
			}
			
			BookingChargeItemVO bciVO = new BookingChargeItemVO();
			if (totalDisc1Pax > 0) {
				bciVO = new BookingChargeItemVO();
				bciVO.setDesc("DISCOUNT");
				bciVO.setAmount(-1 * discount1);
				bciVO.setQuantity(totalDisc1Pax);
				bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
				totalAmt += bciVO.getTtlAmt();
				discountList.add(bciVO);
			}
			
			if (totalDisc2Pax > 0) {
				bciVO = new BookingChargeItemVO();
				bciVO.setDesc("DISCOUNT");
				bciVO.setAmount(-1 * discount2);
				bciVO.setQuantity(totalDisc2Pax);
				bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
				totalAmt += bciVO.getTtlAmt();
				discountList.add(bciVO);
			}
		}
	}
	
	public void calDiscountList(TourDepartureVO tourDepVO, int ftTwn, int ftSgl, int ftTri, int ftQua, int ftQui, int ftTriCwb, int ftQuaCwb, int ftCtw, int ftCwb, int ftCnb, int ftInft,
			int bzTwn, int bzSgl, int bzTri, int bzQua, int bzQui, int bzTriCwb, int bzQuaCwb, int bzCtw, int bzCwb, int bzCnb, int bzInft, boolean isDiscountPercent) throws BusinessException {
		// Tour Departure Discount
		List<TourDepartureDiscountVO> discountVO = tourPkgBO.getTourDepDiscount(tourDepVO.getId());
		
		if (isDiscountPercent) {
			if (CollectionUtils.isNotEmpty(discountVO) && discountVO.get(0).getDiscountPax() > 0) {
				BookingChargeItemVO bciVO = new BookingChargeItemVO();
				bciVO.setDesc("DISCOUNT");
				bciVO.setAmount(-1 * subTotal * discountVO.get(0).getDiscountAmt() / 100);
				bciVO.setQuantity(1);
				bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
				totalAmt += bciVO.getTtlAmt();
				discountList.add(bciVO);
			}
		} else {
			double discount1 = 0.00;
			double discount2 = 0.00;
			int disc1Pax = 0;
			int disc2Pax = 0;
			if (CollectionUtils.isNotEmpty(discountVO)) {
				int count = 1;
				
				for (TourDepartureDiscountVO vo : discountVO) {
					double discountAmt = vo.getDiscountAmt();
					int discountPax = vo.getDiscountPax();
					
					if (count == 1) {
						discount1 = discountAmt;
						disc1Pax = discountPax;
					} else if (count == 2) {
						discount2 = discountAmt;
						disc2Pax = discountPax;
					}
					count++;
				}
			}
			
			int totalDisc1Pax = 0;
			int totalDisc2Pax = 0;
			int totalPax = ftTwn + ftSgl + ftTri + ftQua + ftQui + ftTriCwb + ftQuaCwb + ftCtw + ftCwb + ftCnb + ftInft;
			totalPax += bzTwn + bzSgl + bzTri + bzQua + bzQui + bzTriCwb + bzQuaCwb + bzCtw + bzCwb + bzCnb + bzInft;
			int totalDiscPax = disc1Pax + disc2Pax;
			int remaining = 0;
			
			if (disc1Pax != 0) {
				if (disc2Pax != 0) {
					if (totalPax > totalDiscPax) {
						totalDisc1Pax = disc1Pax;
						totalDisc2Pax = disc2Pax;
					} else if (totalPax == totalDiscPax) {
						totalDisc1Pax = disc1Pax;
						totalDisc2Pax = disc2Pax;
					} else if (totalDiscPax > totalPax) {
						if(disc1Pax > totalPax){
							totalDisc1Pax = totalPax;
						} else if (disc1Pax == totalPax) {
							totalDisc1Pax = disc1Pax;
						} else if (totalPax > disc1Pax) {
							remaining = totalPax - disc1Pax;
							disc2Pax = remaining;
							totalDisc1Pax = disc1Pax;
							totalDisc2Pax = disc2Pax;
						}
					}
				} else {
					if (totalPax > disc1Pax) {
						totalDisc1Pax = disc1Pax;
					} else if (totalPax == disc1Pax) {
						totalDisc1Pax = disc1Pax;
					} else if (disc1Pax > totalPax) {
						totalDisc1Pax = totalPax;
					}
				}
			} else {
				if (disc2Pax != 0) {
					if (totalPax > disc2Pax){
						totalDisc2Pax = disc2Pax;
					} else if(totalPax == disc2Pax) {
						totalDisc2Pax = disc2Pax;
					} else if(disc2Pax > totalPax) {
						totalDisc2Pax = totalPax;
					}
				} else {
					totalDisc1Pax = 0;
					totalDisc2Pax = 0;
				}
			}
			
			BookingChargeItemVO bciVO = new BookingChargeItemVO();
			if (totalDisc1Pax > 0) {
				bciVO = new BookingChargeItemVO();
				bciVO.setDesc("DISCOUNT");
				bciVO.setAmount(-1 * discount1);
				bciVO.setQuantity(totalDisc1Pax);
				bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
				totalAmt += bciVO.getTtlAmt();
				discountList.add(bciVO);
			}
			
			if (totalDisc2Pax > 0) {
				bciVO = new BookingChargeItemVO();
				bciVO.setDesc("DISCOUNT");
				bciVO.setAmount(-1 * discount2);
				bciVO.setQuantity(totalDisc2Pax);
				bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
				totalAmt += bciVO.getTtlAmt();
				discountList.add(bciVO);
			}
		}
	}
	
	public void setMiscAdtChd(TourDepartureViewVO tourDepVO, BookingViewVO bookingVO) throws BusinessException {
		if (CollectionUtils.isNotEmpty(bookingVO.getBookingChargeItemList())) {
			List<CruiseScheduleChargeVO> chargeList = cruiseBO.getCruiseScheduleChargeList(tourDepVO.getIdCruiseSchedule());
			List<TourDepItemVO> tourDepItemList = tourPkgBO.getTourDepItemList(tourDepVO.getId(), getSessionInfoBean().getCompanyVO().getId());
			double extraMisc = 0;
			
			subTotal = 0.00;
			totalAmt = 0.00;
			ttlAgentComm = 0.00;
			adultTourPax = 0;
			adultGroundPax = 0;
			childTourPax = 0;
			childGroundPax = 0;
			boolean isFare = false;
			bookingChargeItemList = new ArrayList<BookingChargeItemVO>();
			additionalTaxList = new ArrayList<BookingChargeItemVO>();
			miscChargesList = new ArrayList<BookingChargeItemVO>();
			depositList = new ArrayList<BookingChargeItemVO>();
			discountList = new ArrayList<BookingChargeItemVO>();
			commList = new ArrayList<BookingChargeItemVO>();
			tourMiscChargesList = new ArrayList<BookingChargeItemVO>();
			
			for (BookingChargeItemVO vo : bookingVO.getBookingChargeItemList()) {
				System.out.println("vo.getCode: " + vo.getCode());
				isFare = false;
				
				if (vo.getCode().startsWith(ProductConstant.TOUR_DEP_ITM_CD_DISC)) {
					bookingVO.setDiscount(vo.getAmount());
					
					vo.setTtlAmt(vo.getAmount() * vo.getQuantity());
					if (vo.getQuantity() > 0) {
						totalAmt += vo.getTtlAmt();
						discountList.add(vo);
					}
				} else if (ProductConstant.CRUISE_ITM_CD_PORT_CHARGES.equals(vo.getCode())) {
					bookingVO.setCruiseAptAdt(vo.getAmount());
				} else if (ProductConstant.CRUISE_ITM_CD_APT_ADT.equals(vo.getCode())) {
					bookingVO.setCruiseAptAdt(vo.getAmount());
				} else if (ProductConstant.CRUISE_ITM_CD_APT_CHD.equals(vo.getCode())) {
					bookingVO.setCruiseAptChd(vo.getAmount());
				} else if (ProductConstant.CRUISE_ITM_CD_FUEL_ADT.equals(vo.getCode())) {
					bookingVO.setCruiseYqAdt(vo.getAmount());
				} else if (ProductConstant.CRUISE_ITM_CD_FUEL_CHD.equals(vo.getCode())) {
					bookingVO.setCruiseYqChd(vo.getAmount());
				} else if (ProductConstant.CRUISE_ITM_CD_TRVL_INS.equals(vo.getCode())) {
					bookingVO.setCruiseTrvlIns(vo.getAmount());
				} else if (ProductConstant.CRUISE_ITM_CD_VISA.equals(vo.getCode())) {
					bookingVO.setCruiseVisa(vo.getAmount());
				} else if (ProductConstant.CRUISE_ITM_CD_AC.equals(vo.getCode())) {
					bookingVO.setCruiseAC(vo.getAmount());
				} else if (ProductConstant.CRUISE_ITM_CD_TIPPING.equals(vo.getCode())) {
					bookingVO.setCruiseTipping(vo.getAmount());
				} else if (ProductConstant.CRUISE_ITM_CD_DEVIATION.equals(vo.getCode())) {
					bookingVO.setCruiseDeviation(vo.getAmount());
				} else {
					isFare = true;
//					
//					if (CollectionUtils.isNotEmpty(chargeList)) {
//						for (CruiseScheduleChargeVO chargeVO : chargeList) {
//							if (vo.getCode().equals(chargeVO.getTypeCd())) {
//								if (chargeVO.getIsMisc()) {
//									extraMisc += vo.getAmount();
//									additionalTaxList.add(vo);
//								}
//								break;
//							}
//						}
//					}
					if (ProductConstant.TOUR_DEP_ITM_TYPE_CRUISE.equals(vo.getTypeCd()) && vo.getIsMisc()) {
						extraMisc += vo.getAmount();
						additionalTaxList.add(vo);
					}
					
					if (CollectionUtils.isNotEmpty(tourDepItemList)) {
						for (TourDepItemVO itemVO : tourDepItemList) {
							if (ProductConstant.TOUR_DEP_ITM_TYPE_TOUR.equals(itemVO.getTypeCd()) && vo.getCode().equals(itemVO.getCode())) {
								if (itemVO.getIsMisc()) {
									extraMisc += vo.getAmount();
									BookingChargeItemVO chargeVO = new BookingChargeItemVO();
									chargeVO.setDesc(vo.getDesc());
									chargeVO.setAmount(vo.getAmount());
									tourMiscChargesList.add(chargeVO);
								}
								break;
							}
						}
					}
				}
				
				if ((vo.getQuantity() > 0 || vo.getQuantity3() > 0 || vo.getQuantity4() > 0 || vo.getQuantitySgl() > 0 || vo.getQuantity3Chd() > 0|| vo.getQuantity4Chd() > 0 || vo.getQuantityInf() > 0) && isFare) {
					
					if (vo.getCabinQty() > 0 && ProductConstant.TOUR_DEP_ITM_TYPE_TOUR.equals(vo.getTypeCd())) {
						if (vo.getQuantity() > 0) {
							BookingChargeItemVO tmp = new BookingChargeItemVO();
							BeanUtils.copyProperties(vo, tmp);
							tmp.setAmount(vo.getAmount());
							tmp.setQuantity(vo.getQuantity());
							tmp.setDesc(vo.getDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_TWN);
							tmp.setTtlAmt((new BigDecimal(String.valueOf(vo.getAmount())))
									.multiply(new BigDecimal(String.valueOf(vo.getQuantity()))).doubleValue());
							subTotal += tmp.getTtlAmt();
							totalAmt += tmp.getTtlAmt();
							adultTourPax += tmp.getQuantity();
							bookingChargeItemList.add(tmp);
						}
						if (vo.getQuantity3() > 0) {
							BookingChargeItemVO tmp = new BookingChargeItemVO();
							BeanUtils.copyProperties(vo, tmp);
							tmp.setAmount(vo.getAmount3());
							tmp.setQuantity(vo.getQuantity3());
							tmp.setDesc(vo.getDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_3RD);
							tmp.setTtlAmt((new BigDecimal(String.valueOf(vo.getAmount3())))
									.multiply(new BigDecimal(String.valueOf(vo.getQuantity3()))).doubleValue());
							subTotal += tmp.getTtlAmt();
							totalAmt += tmp.getTtlAmt();
							adultTourPax += tmp.getQuantity();
							bookingChargeItemList.add(tmp);
						}
						if (vo.getQuantity4() > 0) {
							BookingChargeItemVO tmp = new BookingChargeItemVO();
							BeanUtils.copyProperties(vo, tmp);
							tmp.setAmount(vo.getAmount4());
							tmp.setQuantity(vo.getQuantity4());
							tmp.setDesc(vo.getDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_4TH);
							tmp.setTtlAmt((new BigDecimal(String.valueOf(vo.getAmount4())))
									.multiply(new BigDecimal(String.valueOf(vo.getQuantity4()))).doubleValue());
							subTotal += tmp.getTtlAmt();
							totalAmt += tmp.getTtlAmt();
							adultTourPax += tmp.getQuantity();
							bookingChargeItemList.add(tmp);
						}
						if (vo.getQuantitySgl() > 0) {
							BookingChargeItemVO tmp = new BookingChargeItemVO();
							BeanUtils.copyProperties(vo, tmp);
							tmp.setAmount(vo.getAmountSgl());
							tmp.setQuantity(vo.getQuantitySgl());
							tmp.setDesc(vo.getDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_SGL);
							tmp.setTtlAmt((new BigDecimal(String.valueOf(vo.getAmountSgl())))
									.multiply(new BigDecimal(String.valueOf(vo.getQuantitySgl()))).doubleValue());
							subTotal += tmp.getTtlAmt();
							totalAmt += tmp.getTtlAmt();
							adultTourPax += tmp.getQuantity();
							bookingChargeItemList.add(tmp);
						}
						if (vo.getQuantity3Chd() > 0) {
							BookingChargeItemVO tmp = new BookingChargeItemVO();
							BeanUtils.copyProperties(vo, tmp);
							tmp.setAmount(vo.getAmount3Chd());
							tmp.setQuantity(vo.getQuantity3Chd());
							tmp.setDesc(vo.getDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_3RD_CHD);
							tmp.setTtlAmt((new BigDecimal(String.valueOf(vo.getAmount3Chd())))
									.multiply(new BigDecimal(String.valueOf(vo.getQuantity3Chd()))).doubleValue());
							subTotal += tmp.getTtlAmt();
							totalAmt += tmp.getTtlAmt();
							childTourPax += tmp.getQuantity();
							bookingChargeItemList.add(tmp);
						}
						if (vo.getQuantity4Chd() > 0) {
							BookingChargeItemVO tmp = new BookingChargeItemVO();
							BeanUtils.copyProperties(vo, tmp);
							tmp.setAmount(vo.getAmount4Chd());
							tmp.setQuantity(vo.getQuantity4Chd());
							tmp.setDesc(vo.getDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_4TH_CHD);
							tmp.setTtlAmt((new BigDecimal(String.valueOf(vo.getAmount4Chd())))
									.multiply(new BigDecimal(String.valueOf(vo.getQuantity4Chd()))).doubleValue());
							subTotal += tmp.getTtlAmt();
							totalAmt += tmp.getTtlAmt();
							childTourPax += tmp.getQuantity();
							bookingChargeItemList.add(tmp);
						}
						if (vo.getQuantityInf() > 0) {
							BookingChargeItemVO tmp = new BookingChargeItemVO();
							BeanUtils.copyProperties(vo, tmp);
							tmp.setAmount(vo.getAmountInf());
							tmp.setQuantity(vo.getQuantityInf());
							tmp.setDesc(vo.getDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_INFT);
							tmp.setTtlAmt((new BigDecimal(String.valueOf(vo.getAmountInf())))
									.multiply(new BigDecimal(String.valueOf(vo.getQuantityInf()))).doubleValue());
							subTotal += tmp.getTtlAmt();
							totalAmt += tmp.getTtlAmt();
							childTourPax += tmp.getQuantity();
							bookingChargeItemList.add(tmp);
						}
					}
				}
			}

			bookingVO.setCruiseExtra(extraMisc);
			bookingVO.setCruiseTotAdt(bookingVO.getCruiseAptAdt() + bookingVO.getCruiseYqAdt() + bookingVO.getCruiseTrvlIns() +
					bookingVO.getCruiseVisa() + bookingVO.getCruiseTipping() + extraMisc + bookingVO.getCruiseAC() + bookingVO.getCruiseDeviation());
			bookingVO.setCruiseTotChd(bookingVO.getCruiseAptChd() + bookingVO.getCruiseYqChd() + bookingVO.getCruiseTrvlIns() +
					bookingVO.getCruiseVisa() + bookingVO.getCruiseTipping() + extraMisc + bookingVO.getCruiseAC() + bookingVO.getCruiseDeviation());
			tourDepVO.setGrdMiscAdtChd(bookingVO.getCruiseTrvlIns() +
					bookingVO.getCruiseVisa() + bookingVO.getCruiseTipping());
			
			BookingChargeItemVO bciVO = new BookingChargeItemVO();
			// Adult - Tour
			if (adultTourPax > 0) {
				bciVO.setDesc("ADULT - MISC");
				bciVO.setAmount(bookingVO.getCruiseTotAdt());
				bciVO.setQuantity(adultTourPax);
				bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
				totalAmt += bciVO.getTtlAmt();
				miscChargesList.add(bciVO);
			}
			// Child - Tour
			if (childTourPax > 0) {
				bciVO = new BookingChargeItemVO();
				bciVO.setDesc("CHILD - MISC");
				bciVO.setAmount(bookingVO.getCruiseTotAdt());
				bciVO.setQuantity(childTourPax);
				bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
				totalAmt += bciVO.getTtlAmt();
				miscChargesList.add(bciVO);
			}
			
			// Deposit Required
			if (adultTourPax > 0 || childTourPax > 0) {
				int totalPax = adultTourPax + childTourPax;
				bciVO = new BookingChargeItemVO();
				bciVO.setDesc("AMOUNT");
				
				if (tourDepVO.getTourPkgVO().getIsDepositPercent()) {
					bciVO.setAmount(totalAmt * tourDepVO.getTourPkgVO().getDeposit() / 100);
					bciVO.setQuantity(1);
					bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
				} else {
					bciVO.setAmount(tourDepVO.getTourPkgVO().getDeposit());
					bciVO.setQuantity(totalPax);
					bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
				}
				depositList.add(bciVO);
				
				// AGENT COMM - AC
				if (tourDepVO.getCnaAdt() > 0) {
					bciVO = new BookingChargeItemVO();
					bciVO.setDesc("AC");
					bciVO.setAmount(tourDepVO.getCnaAdt());
					bciVO.setQuantity(totalPax);
					bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
					ttlAgentComm += bciVO.getTtlAmt();
					commList.add(bciVO);
				}
				
				// AGENT COMM - SI
				if (tourDepVO.getCsiAdt() > 0) {
					bciVO = new BookingChargeItemVO();
					bciVO.setDesc("SI");
					bciVO.setAmount(tourDepVO.getCsiAdt());
					bciVO.setQuantity(totalPax);
					bciVO.setTtlAmt(bciVO.getAmount() * bciVO.getQuantity());
					ttlAgentComm += bciVO.getTtlAmt();
					commList.add(bciVO);
				}
			}
			CruiseScheduleVO cruiseScheduleVO = cruiseBO.getCruiseSchedule(tourPkgBO.getTourDepById(bookingViewVO.getTourDepViewVO().getId()));
			cruiseScheduleItemList = cruiseScheduleVO.getCruiseScheduleItemList();
			
			cruiseScheduleItemList = cruiseScheduleVO.getCruiseScheduleItemList();
			
			// https://applevacationsmy.monday.com/boards/1957151896/pulses/2010746151
			// Request: Please make it not to show flight scheduled on website, aws and email if [Show Flight Schedule] = NO
			if (!tourDepVO.getIsShowCruise()) {
				if (tourDepVO.getCruiseScheduleVO() != null && CollectionUtils.isNotEmpty(tourDepVO.getCruiseScheduleVO().getCruiseScheduleItemList())) {
					tourDepVO.getCruiseScheduleVO().setCruiseScheduleItemList(new ArrayList<CruiseScheduleItemVO>());
				}
				if (CollectionUtils.isNotEmpty(cruiseScheduleItemList)) {
					cruiseScheduleItemList = new ArrayList<CruiseScheduleItemVO>();
				}
			}
		}
	}

	public void setEnquiryWithoutPaxBookingChargeItemList() {
		for(TourCruiseCabinVO vo : tourCruiseCabinList) {
			BookingChargeItemVO itemVO = new BookingChargeItemVO();
			
			itemVO.setCode(vo.getCruiseCabinCd());
			itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_TWN);
			itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
			itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
			
			itemVO.setAmount(vo.getPriceTwn());
			itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
			subTotal += itemVO.getTtlAmt();
			bookingChargeItemList.add(itemVO);
						
			itemVO = new BookingChargeItemVO();
			
			itemVO.setCode(vo.getCruiseCabinCd());
			itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_3RD);
			itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
			itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
			
			itemVO.setAmount(vo.getPrice3());
			itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
			subTotal += itemVO.getTtlAmt();
			bookingChargeItemList.add(itemVO);
		
			itemVO = new BookingChargeItemVO();
			
			itemVO.setCode(vo.getCruiseCabinCd());
			itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_4TH);
			itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
			itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
			
			itemVO.setAmount(vo.getPrice4());
			itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
			subTotal += itemVO.getTtlAmt();
			bookingChargeItemList.add(itemVO);
					
			itemVO = new BookingChargeItemVO();
			
			itemVO.setCode(vo.getCruiseCabinCd());
			itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_SGL);
			itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
			itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
			
			itemVO.setAmount(vo.getPriceSgl());
			itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
			subTotal += itemVO.getTtlAmt();
			bookingChargeItemList.add(itemVO);	
			
			itemVO = new BookingChargeItemVO();
			
			itemVO.setCode(vo.getCruiseCabinCd());
			itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_3RD_CHD);
			itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
			itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
			
			itemVO.setAmount(vo.getPrice3Chd());
			itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
			subTotal += itemVO.getTtlAmt();
			bookingChargeItemList.add(itemVO);
		
			itemVO = new BookingChargeItemVO();
			
			itemVO.setCode(vo.getCruiseCabinCd());
			itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_4TH_CHD);
			itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
			itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
			
			itemVO.setAmount(vo.getPrice4Chd());
			itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
			subTotal += itemVO.getTtlAmt();
			bookingChargeItemList.add(itemVO);
					
			itemVO = new BookingChargeItemVO();
			
			itemVO.setCode(vo.getCruiseCabinCd());
			itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_INFT);
			itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
			itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
			
			itemVO.setAmount(vo.getPriceInf());
			itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
			subTotal += itemVO.getTtlAmt();
			bookingChargeItemList.add(itemVO);			
		}    
	}

	public void setEnquiryBookingChargeItemList(String cabinStr, TourDepartureVO tourDepVO) throws JsonParseException, JsonMappingException, IOException, BusinessException {
		
		if(cabinStr == null) {
			return;
		}
		
		int ttlPax = 0;
		
		// decode cabin string from url
		ObjectMapper objectMapper = new ObjectMapper();
		String[][] cabinList = objectMapper.readValue(cabinStr, String[][].class);
		for (String[] row : cabinList) {
			String cruiseCabinCd = row[2];
	        String paxTwn = row[3];
	        String pax3 = row[4];
	        String pax4 = row[5];
            String paxSgl = row[6];
	        String pax3Chd = row[7];
	        String pax4Chd = row[8];
            String paxInf = row[9];
            String cabinQty = row[10];
	            
            for(TourCruiseCabinVO vo : tourCruiseCabinList) {
            	if(vo.getCruiseCabinCd().equals(cruiseCabinCd)) {						
					if(Integer.valueOf(paxTwn) > 0) {
						BookingChargeItemVO itemVO = new BookingChargeItemVO();
						
						itemVO.setCode(vo.getCruiseCabinCd());
						itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_TWN);
						itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
						itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
						
						itemVO.setQuantity(Integer.valueOf(paxTwn));
						itemVO.setAmount(vo.getPriceTwn());
						itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
						subTotal += itemVO.getTtlAmt();
						bookingChargeItemList.add(itemVO);
						ttlPax += Integer.valueOf(paxTwn);
					}
						
					if(Integer.valueOf(pax3) > 0) {
						BookingChargeItemVO itemVO = new BookingChargeItemVO();
						
						itemVO.setCode(vo.getCruiseCabinCd());
						itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_3RD);
						itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
						itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
						
						itemVO.setQuantity(Integer.valueOf(pax3));
						itemVO.setAmount(vo.getPrice3());
						itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
						subTotal += itemVO.getTtlAmt();
						bookingChargeItemList.add(itemVO);
						ttlPax += Integer.valueOf(pax3);
					}
					
					if(Integer.valueOf(pax4) > 0) {
						BookingChargeItemVO itemVO = new BookingChargeItemVO();
						
						itemVO.setCode(vo.getCruiseCabinCd());
						itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_4TH);
						itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
						itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
						
						itemVO.setQuantity(Integer.valueOf(pax4));
						itemVO.setAmount(vo.getPrice4());
						itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
						subTotal += itemVO.getTtlAmt();
						bookingChargeItemList.add(itemVO);
						ttlPax += Integer.valueOf(pax4);
					}
						
					if(Integer.valueOf(paxSgl) > 0) {
						BookingChargeItemVO itemVO = new BookingChargeItemVO();
						
						itemVO.setCode(vo.getCruiseCabinCd());
						itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_SGL);
						itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
						itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
						
						itemVO.setQuantity(Integer.valueOf(paxSgl));
						itemVO.setAmount(vo.getPriceSgl());
						itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
						subTotal += itemVO.getTtlAmt();
						bookingChargeItemList.add(itemVO);
						ttlPax += Integer.valueOf(paxSgl);
					}
					
					if(Integer.valueOf(pax3Chd) > 0) {
						BookingChargeItemVO itemVO = new BookingChargeItemVO();
						
						itemVO.setCode(vo.getCruiseCabinCd());
						itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_3RD);
						itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
						itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
						
						itemVO.setQuantity(Integer.valueOf(pax3Chd));
						itemVO.setAmount(vo.getPrice3Chd());
						itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
						subTotal += itemVO.getTtlAmt();
						bookingChargeItemList.add(itemVO);
						ttlPax += Integer.valueOf(pax3Chd);
					}
					
					if(Integer.valueOf(pax4Chd) > 0) {
						BookingChargeItemVO itemVO = new BookingChargeItemVO();
						
						itemVO.setCode(vo.getCruiseCabinCd());
						itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_4TH);
						itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
						itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
						
						itemVO.setQuantity(Integer.valueOf(pax4Chd));
						itemVO.setAmount(vo.getPrice4Chd());
						itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
						subTotal += itemVO.getTtlAmt();
						bookingChargeItemList.add(itemVO);
						ttlPax += Integer.valueOf(pax4Chd);
					}
					
					if(Integer.valueOf(paxInf) > 0) {
						BookingChargeItemVO itemVO = new BookingChargeItemVO();
						
						itemVO.setCode(vo.getCruiseCabinCd());
						itemVO.setDesc(vo.getCruiseCabinDesc() + " - " + ProductConstant.BOOKING_CHARGE_ITEM_PAX_INFT);
						itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_TOUR);
						itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
						
						itemVO.setQuantity(Integer.valueOf(paxInf));
						itemVO.setAmount(vo.getPriceInf());
						itemVO.setTtlAmt(itemVO.getQuantity() * itemVO.getAmount());
						subTotal += itemVO.getTtlAmt();
						bookingChargeItemList.add(itemVO);
						ttlPax += Integer.valueOf(paxInf);
					}

					List<TourCruiseCabinDiscountVO> discountList = tourCruiseCabinBO
							.getTourCruiseCabinDiscountList(vo.getIdTourCruiseCabin());
					int remainingPax = vo.getPaxTwn() + vo.getPax3() + vo.getPax4() + vo.getPaxSgl() + vo.getPaxInf();

					for (TourCruiseCabinDiscountVO discountVO : discountList) {
						BookingChargeItemVO itemVO = new BookingChargeItemVO();

						itemVO.setIdTourCruiseCabin(vo.getIdTourCruiseCabin());
						itemVO.setCode(ProductConstant.TOUR_DEP_ITM_CD_DISC + "_" + vo.getCruiseCabinCd());
						itemVO.setDesc("DISCOUNT " + vo.getCruiseCabinDesc());
						itemVO.setTypeCd(ProductConstant.TOUR_DEP_ITM_TYPE_CRUISE);
						itemVO.setAmount(0.0);

						if (remainingPax <= 0) {
							itemVO.setQuantity(0);
//								insertVO(itemVO);
							bookingChargeItemList.add(itemVO);
							break;
						}

						int availableDiscountPax = discountVO.getDiscountPax();
						double availableDiscountAmt = discountVO.getDiscountAmt();

						if (availableDiscountPax <= 0 || availableDiscountAmt <= 0) {
							itemVO.setQuantity(0);
//								insertVO(itemVO);
							bookingChargeItemList.add(itemVO);
							continue;
						}

						itemVO.setAmount(
								availableDiscountAmt > 0 ? -discountVO.getDiscountAmt() : discountVO.getDiscountAmt());

						if (availableDiscountPax >= remainingPax) {
							discountVO.setDiscountPax(discountVO.getDiscountPax() - remainingPax);
							itemVO.setQuantity(remainingPax);

							remainingPax = 0;
						} else {
							remainingPax = remainingPax - availableDiscountPax;

							discountVO.setDiscountPax(discountVO.getDiscountPax() - availableDiscountPax);
							itemVO.setQuantity(availableDiscountPax);
						}
						bookingChargeItemList.add(itemVO);
					}
				}
            }
		}
		
		tourDepVO.setPaxConfirmed(ttlPax);
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

	public String getTourDepId() {
		return tourDepId;
	}

	public void setTourDepId(String tourDepId) {
		this.tourDepId = tourDepId;
	}

	public List<BookingChargeItemVO> getDiscountList() {
		return discountList;
	}

	public void setDiscountList(List<BookingChargeItemVO> discountList) {
		this.discountList = discountList;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public List<BookingChargeItemVO> getAdditionalTaxList() {
		return additionalTaxList;
	}

	public void setAdditionalTaxList(List<BookingChargeItemVO> additionalTaxList) {
		this.additionalTaxList = additionalTaxList;
	}

	public List<BookingChargeItemVO> getMiscChargesList() {
		return miscChargesList;
	}

	public void setMiscChargesList(List<BookingChargeItemVO> miscChargesList) {
		this.miscChargesList = miscChargesList;
	}

	public List<BookingChargeItemVO> getDepositList() {
		return depositList;
	}

	public void setDepositList(List<BookingChargeItemVO> depositList) {
		this.depositList = depositList;
	}

	public double getTtlAgentComm() {
		return ttlAgentComm;
	}

	public void setTtlAgentComm(double ttlAgentComm) {
		this.ttlAgentComm = ttlAgentComm;
	}

	public List<BookingChargeItemVO> getCommList() {
		return commList;
	}

	public void setCommList(List<BookingChargeItemVO> commList) {
		this.commList = commList;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public List<CruiseScheduleItemVO> getCruiseScheduleItemList() {
		return cruiseScheduleItemList;
	}

	public void setCruiseScheduleItemList(List<CruiseScheduleItemVO> cruiseScheduleItemList) {
		this.cruiseScheduleItemList = cruiseScheduleItemList;
	}

	public List<BookingChargeItemVO> getBookingChargeItemList() {
		return bookingChargeItemList;
	}

	public void setBookingChargeItemList(List<BookingChargeItemVO> bookingChargeItemList) {
		this.bookingChargeItemList = bookingChargeItemList;
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public List<BookingChargeItemVO> getBookingChargeItemListFull() {
		return bookingChargeItemListFull;
	}

	public List<BookingChargeItemVO> getBookingChargeItemListGrnd() {
		return bookingChargeItemListGrnd;
	}

	public List<BookingChargeItemVO> getBookingChargeItemListBiz() {
		return bookingChargeItemListBiz;
	}
	
	public List<BookingChargeItemVO> getTourMiscChargesList() {
		return tourMiscChargesList;
	}

	public void setTourMiscChargesList(List<BookingChargeItemVO> tourMiscChargesList) {
		this.tourMiscChargesList = tourMiscChargesList;
	}
}
