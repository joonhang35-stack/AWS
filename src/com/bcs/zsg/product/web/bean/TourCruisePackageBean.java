package com.bcs.zsg.product.web.bean;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.DatesUtils;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.helper.MaintConstant;
import com.bcs.zsg.maintenance.vo.CityVO;
import com.bcs.zsg.maintenance.vo.RegionVO;
import com.bcs.zsg.product.bo.CruiseCabinBO;
import com.bcs.zsg.product.bo.TourCruiseCabinBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.CruiseCabinVO;
import com.bcs.zsg.product.vo.TourCatViewVO;
import com.bcs.zsg.product.vo.TourCruiseCabinVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourPackageCountryVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.sales.vo.BookingChargeItemVO;
import com.bcs.zsg.sales.vo.BookingVO;
import com.bcs.zsg.sales.vo.BookingViewVO;

public class TourCruisePackageBean extends TourPackageBean {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient CruiseCabinBO cruiseCabinBO;
	@Autowired
	private transient TourCruiseCabinBO tourCruiseCabinBO;

	private List<TourCruiseCabinVO> tourCabinList;
	private List<CruiseCabinVO> cabinList;
	
	private TourCruiseCabinVO tourCabinVO;
	
	private TourCruiseCabinVO tourCabinBookingVO;
	private boolean tourCabinAdd;
	
	public void resetTourCabinForm() {
		tourCabinVO = new TourCruiseCabinVO();
		tourCabinVO.setPaxPerCabin(4);
		loadCabinList();
	}
	
	public void resetTourCabinBookingForm() {
		tourCabinBookingVO = new TourCruiseCabinVO();
		tourCabinAdd = true;
	}
	
	public void saveTourCabin() {
		try {
			
			if (tourCabinVO.getUuid() == null) {
				tourCabinVO.setUuid(new Random().toString());
				tourCabinList.add(tourCabinVO);
			}

			resetTourCabinForm();

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void deleteTourCabin(TourCruiseCabinVO vo) {
		try {
			if (CollectionUtils.isNotEmpty(tourCabinList)) {
				if (vo.getId() != null) {
					if (tourCruiseCabinBO.isTourCruiseCabinBooked(vo.getId())) throw new BusinessException(CommonErrConstant.ERR_TOUR_DEP_CRUISE_CABIN_CANNOT_DELETE);
					
					tourCabinList.remove(vo);
				} else tourCabinList.remove(vo);
			}

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handlePriceTwnInput() {
		try {
			if (tourCabinVO.getPriceTwn() != 0) {
				for (CruiseCabinVO cruiseCabinVO : cabinList) {
					if (cruiseCabinVO.getCode().equals(tourCabinVO.getCruiseCabinCd())) {
						tourCabinVO.setPriceSgl(tourCabinVO.getPriceTwn() * cruiseCabinVO.getFareSgl());
						tourCabinVO.setPriceInf(tourCabinVO.getPriceTwn() * cruiseCabinVO.getFareInf());
						break;
					}
				}
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	@Override
	public void reloadTourDepList() {
		try {
			trackingLogUtils.startLogs();
			isFromSearchFiltering = false;
			tourDepList = tourPkgBO.getTourDepListCruise(tourPkgVO.getId(), getSessionInfoBean().getCompanyVO());
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("reloadTourDepList");
		}
	}
	
	public void handleFilterTourDepList() {
		trackingLogUtils.startLogs();
		try {
			tourFilterDepList = new ArrayList<>();
			if (StringUtils.isNotBlank(searchTourDepFilter.getCode()) ||
				StringUtils.isNotBlank(searchTourDepFilter.getPackageDesc()) || 
				searchTourDepFilter.getFromDate() != null || 
				searchTourDepFilter.getToDate() != null || 
				CollectionUtils.isNotEmpty(searchTourDepFilter.getTourStatusList()) || 
				CollectionUtils.isNotEmpty(searchTourDepFilter.getTourLangList()) || 
				searchTourDepFilter.getPriceFrom() != null ||
				searchTourDepFilter.getPriceTo() != null || 
				searchTourDepFilter.getBalanceCabin() != null || 
				CollectionUtils.isNotEmpty(searchTourDepFilter.getSelectedSeason()) || 
				CollectionUtils.isNotEmpty(searchTourDepFilter.getDepartDateBadgeList()) || 
				CollectionUtils.isNotEmpty(searchTourDepFilter.getSectorTypeList()) || 
				CollectionUtils.isNotEmpty(searchTourDepFilter.getSectorList())) {
				tourFilterDepList = tourPkgBO.getFilterTourDepListCruise(searchTourDepFilter, getSessionInfoBean().getCompanyVO().getId());
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("LoadFilterTourDepList");
		}
	}
	
//	public void handleFilterTourDepList() {
//		try {
//			trackingLogUtils.startLogs();
//			
//			if (StringUtils.isNotBlank(filterTourCode) || StringUtils.isNotBlank(filterTitle) || filterDepDateFrom != null || filterDepDateTo != null)
//				tourFilterDepList = tourPkgBO.getFilterTourDepListCruise(filterDepDateFrom, filterDepDateTo, filterTourCode,
//						filterTitle, filterInactiveRcrd, filterTourStatusCd, getSessionInfoBean().getCompanyVO().getId());
//		
//		} catch (Throwable t) {
//			errorResult(t);
//		} finally {
//			trackingLogUtils.endLogs("LoadFilterTourDepList");
//		}
//	}

	public void addTourDep() {
		try {	
			tourDepVO.setTourCruiseCabinList(tourCabinList);
			tourDepVO.setSeatAllot(0);
			tourDepVO.setTourManagerPax(0);
			tourDepVO.setReservedSeat(0);
			super.addTourDep();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void updTourDep() {
		tourDepVO.setTourCruiseCabinList(tourCabinList);
		super.updTourDep();
		for (int i = 0; i < tourCabinList.size(); i++) {
			try {
				tourPkgBO.updateTourBookingChargeItem(tourCabinList.get(i));
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void handleAddTourDepSelect() {
		try {
			super.resetTourDepForm();
			super.handleAddTourDepSelect();
			
			// set some parameters
//			tourDepVO.setIdTourPkg(tourPkgVO.getId());
//			tourDepVO.setCnaAdt(tourPkgVO.getCnaAdt());
//			tourDepVO.setCpaAdt(tourPkgVO.getCpaAdt());
//			tourDepVO.setCsiAdt(tourPkgVO.getCsiAdt());
//			tourDepVO.setTfairDiscount(tourPkgVO.getTfairDiscount());
//			tourDepVO.setNumDays(tourPkgVO.getNumDays());
//			tourDepVO.setNumNights(tourPkgVO.getNumNights());
//			tourDepVO.setTourPackageRemarksList(new ArrayList<TourPackageRemarksVO>());
//			tourDepVO.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
//			tourDepVO.setAccCodeStatusCd(ProductConstant.TOUR_DEP_STATUS_CD_PENDING);
//			tourDepVO.setCode(tourPkgVO.getCode());
//			tourDepVO.setLangCode(tourPkgVO.getLangCode());
//			tourDepVO.setIsVisaRequired(tourPkgVO.getIsVisaRequired());
//			tourDepVO.setDeadline(tourPkgVO.getDeadline());
//			tourDepVO.setOpPic(tourPkgVO.getOpPic());
//			if (tourDepVO.getOpPic() != null && tourDepVO.getOpPic().length() > 0) {
//				tourDepVO.setOpPicList(Arrays.asList(tourDepVO.getOpPic().replaceAll(",$", "").split(",", -1)));
//			}
//			handleOpPicChange("DEP");
//			
//			tourDepVO.setTourDepartureDiscountVOList(new ArrayList<TourDepartureDiscountVO>());
//			tourDepVO.setGenAUDTourDepDiscountVO(new GenAddUpdDelVO<TourDepartureDiscountVO>());
//			tourDepVO.setTourPkgDailyItineraryVOList(tourPkgBO.getTourPkgDailyItineraryList(tourDepVO.getIdTourPkg()));
//			
//			if (tourPkgVO.getTfairDiscount() > 0) {
//				TourDepartureDiscountVO discountVO = new TourDepartureDiscountVO();
//				discountVO.setDiscountAmt(tourPkgVO.getTfairDiscount());
//				discountVO.setDiscountPax(0);
//				
//				GenUpdateVOList.processAddUpdDelVO(tourDepVO.getGenAUDTourDepDiscountVO(), tourDepVO.getTourDepartureDiscountVOList(), discountVO, ConstantScreenAction.ADD);
//			}
//			
//			// handle date year restriction
//			String year = tourPkgVO.getYear();
//	        int yearInt = Integer.parseInt(year);
//	        Calendar minCal = Calendar.getInstance();
//	        minCal.set(yearInt, 0, 1); // Set to first day of the specified year
//	        super.setMinDate(minCal.getTime());
//	        Calendar maxCal = Calendar.getInstance();
//	        maxCal.set(yearInt, 11, 31); // Set to last day of the specified year
//	        super.setMaxDate(maxCal.getTime());
//	        
//	        isTourStatusChanged = false;
//	        isTourDepLockChanged = false;
//			setRemarksAdd(true);
//			tourRemarksVO = new TourPackageRemarksVO();
//			loadAirlineList();
//			
//			loadSalesCommConfig(tourPkgVO.getIdSalesCommConf());

			tourCabinList = new ArrayList<TourCruiseCabinVO>();
			
			System.out.println("TourCruisePackageBean.handleAddTourDepSelect()");
			System.out.println("CYY cruiseList.size(): " + cruiseList.size());
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void handleTourDepSelect(TourDepartureVO vo) {
		super.handleTourDepSelect(vo);
		
		try {
			if (tourDepVO != null) {
				tourCabinList = tourCruiseCabinBO.getTourCruiseCabinList(vo.getId(), getSessionInfoBean().getCompanyVO().getId());
				if (tourCabinList == null) tourCabinList = new ArrayList<TourCruiseCabinVO>();
				
				tourDepVO.setTourCruiseCabinList(tourCabinList);
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void handleTourDepSelectFromSearch(TourDepartureVO vo) {
		super.handleTourDepSelectFromSearch(vo);
		
		try {
			if (tourDepVO != null) {
				tourCabinList = tourCruiseCabinBO.getTourCruiseCabinList(vo.getId(), getSessionInfoBean().getCompanyVO().getId());
				if (tourCabinList == null) tourCabinList = new ArrayList<TourCruiseCabinVO>();
				
				tourDepVO.setTourCruiseCabinList(tourCabinList);
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleTourPkgSelectChild(TourPackageVO vo, TourThemeVO tThemeVO) {
		try {
			trackingLogUtils.startLogs();
			
			tourPkgVO = vo;
			tourThemeVO = tThemeVO;
			reloadTourDepList();
			tourPkgVO.setTourBadgeTagList(tourPkgBO.getPackageTagList(tourPkgVO.getId(), CommonConstant.LOOKUP_CAT_CD_TOUR_BADGE));
			tourPkgVO.setTravelStyleList(tourPkgBO.getPackageTagList(tourPkgVO.getId(), CommonConstant.LOOKUP_CAT_CD_TRAVEL_STYLE));
			// get room price list
			tourPkgVO.setTourPackageRoomPriceList(tourPkgBO.getTourPackageRoomPrice(tourPkgVO.getId()));

			tourPkgVO.setTourPkgCountryVO(new TourPackageCountryVO());
			loadContinentList();	
			loadCountryList();
			loadCityList();
			
			tourPkgVO.getTourPkgCountryVO().setIdRegionList(tourPkgBO.getTourPackageCountryList(tourPkgVO.getId(), CommonConstant.TYPE_CD_REGION));
			tourPkgVO.getTourPkgCountryVO().setIdCountryList(tourPkgBO.getTourPackageCountryList(tourPkgVO.getId(), CommonConstant.TYPE_CD_COUNTRY));
			tourPkgVO.getTourPkgCountryVO().setIdCityList(tourPkgBO.getTourPackageCountryList(tourPkgVO.getId(), CommonConstant.TYPE_CD_CITY));
			
			if(CollectionUtils.isNotEmpty(tourPkgVO.getTourPkgCountryVO().getIdRegionList())) {
				tourPkgVO.getTourPkgCountryVO().setRegionName("");
				for(RegionVO regionVO : super.getFullRegionList()) {
					for (String region : tourPkgVO.getTourPkgCountryVO().getIdRegionList()){
						if(regionVO.getId().equals(Long.valueOf(region))) {
							tourPkgVO.getTourPkgCountryVO().setRegionName(StringUtils.isBlank(tourPkgVO.getTourPkgCountryVO().getRegionName()) ? regionVO.getRegionname() : tourPkgVO.getTourPkgCountryVO().getRegionName() + ", " + regionVO.getRegionname()); 
						}
					}
				}
			}
			if(CollectionUtils.isNotEmpty(tourPkgVO.getTourPkgCountryVO().getIdCountryList())) {
				tourPkgVO.getTourPkgCountryVO().setCountryName("");
				for(CountryVO countryVO : super.getFullCountryList()) {
					for (String country : tourPkgVO.getTourPkgCountryVO().getIdCountryList()){
						if(countryVO.getId().equals(Long.valueOf(country))) {
							tourPkgVO.getTourPkgCountryVO().setCountryName(StringUtils.isBlank(tourPkgVO.getTourPkgCountryVO().getCountryName()) ? countryVO.getCountry() : tourPkgVO.getTourPkgCountryVO().getCountryName() + ", " + countryVO.getCountry()); 
						}
					}
				}
			}
			if(CollectionUtils.isNotEmpty(tourPkgVO.getTourPkgCountryVO().getIdCityList())) {
				tourPkgVO.getTourPkgCountryVO().setCityName("");
				for(CityVO cityVO : super.getFullCityList()) {
					for (String city : tourPkgVO.getTourPkgCountryVO().getIdCityList()){
						if(cityVO.getId().equals(Long.valueOf(city))) {
							tourPkgVO.getTourPkgCountryVO().setCityName(StringUtils.isBlank(tourPkgVO.getTourPkgCountryVO().getCityName()) ? cityVO.getName() : tourPkgVO.getTourPkgCountryVO().getCityName() + ", " + cityVO.getName()); 
						}
					}
				}
			}
			
			for (TourCatViewVO tourCatView : tourCatViewList) {
				if (tourThemeVO.getIdTourCat().equals(tourCatView.getId())) {
					tourCatViewVO = tourCatView;
					break;
				}
			}
			// get hotel list
			hotelList = hotelBO.getHotelList(tourCatViewVO.getIdRegion());
			
			if (BaseConstant.YES.equals(tourPkgDepFilter)) {
				searchTourDep.setIdTourPkg(tourPkgVO.getId());
				// get cms itinerary
				tourPkgVO.setTourPkgDailyItineraryVOList(tourPkgBO.getTourPkgDailyItineraryList(tourPkgVO.getId()));
			}
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("handleTourPkgSelectChild");
		}
	}
	
	public void loadCabinListByCruiseId(Long cruiseId) {
	    try {
	        if (cruiseId != null) {
	            Map<String, Object> params = new HashMap<String, Object>();
	            params.put("idCruise", cruiseId);
	            cabinList = cruiseCabinBO.getCabinList(params);
	            
	        }
	    } catch (Throwable t) {
	        errorResult(t);
	    }
	}
	
	@Override
	public void handleCruiseSelect() {
	    try {
	        loadCabinListByCruiseId(tourDepVO.getIdCruise());
	        
	        super.handleCruiseSelect();
	        
	    } catch (Throwable t) {
	        errorResult(t);
	    }
	}
	
	private boolean validateDateFormat(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		
		try {
			sdf.parse(date);
		} catch (ParseException e) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Add tour booking
	 */
	public void addBooking() {
		try {
			trackingLogUtils.startLogs();
			
			if (ProductConstant.TYPE_CRUISE.equals(tourPkgVO.getTypeCd())) {
				bookingVO.setTypeCd(ProductConstant.TYPE_CRUISE);
			} else {
				bookingVO.setTypeCd(ProductConstant.TYPE_TOUR);
			}
			
			// check booking pax balance
			checkBookingCabinBalance(0);
			
			// set expiry date
			String kivExp = LookupItemUtils.getGlobalConfigValue(MaintConstant.GLOBAL_CD_GLOBAL, MaintConstant.GLOBAL_CD_KIV_EXP);
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(kivExp));
			bookingVO.setDtExp(cal.getTime());
			
			// add booking
			bookingBO.addCruiseBooking(bookingVO, tourDepVO);
			tourPkgBO.updateCruiseTourStatus(tourDepVO.getId()); // update tour pkg status depend all cabin status
			// get tour departure list
			if (isFromSearchFiltering) handleFilterTourDepList();
			else reloadTourDepList();
			resetBookingForm();
			successResult();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("cruise - addBooking");
		}
	}
	
	/**
	 * Update booking
	 */
	public void updBooking() {
		try {
			trackingLogUtils.startLogs();
			
			// check booking pax balance
			checkBookingCabinBalance(1);
			
			bookingBO.updCruiseBooking(bookingVO, tourDepVO);
			tourPkgBO.updateCruiseTourStatus(tourDepVO.getId()); // update tour pkg status depend all cabin status
			bookingList = bookingBO.getBookingCruiseList(tourDepVO);
			
			if (isFromSearchFiltering) {
				handleFilterTourDepList();
			} else {
				// get tour departure list
				reloadTourDepList();
				// reload tour departure
				for (TourDepartureVO vo : tourDepList) {
					if (tourDepVO.getId().longValue() == vo.getId().longValue()) {
						tourDepVO = vo;
						break;
					}
				}
			}
			resetBookingForm();
			successResult();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("cruise - updBooking");
		}
	}
	
	/**
	 * Cancel booking
	 */
	public void cancelBooking() {
		try {
			trackingLogUtils.startLogs();
			
			bookingBO.cancelCruiseBooking(bookingVO, tourDepVO);
			tourPkgBO.updateCruiseTourStatus(tourDepVO.getId()); // update tour pkg status depend all cabin status
			
			if (fromBookingPage) {
				loadBookingViewList();
				
			} else {
				bookingList = bookingBO.getBookingList(tourDepVO);
				// get tour departure list
				if(isFromSearchFiltering) handleFilterBookingTourDepList();
				else reloadTourDepList();
			}
			resetBookingForm();
			successResult();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("cancelBooking");
		}
	}
	
	public void handleShowBookingList(TourDepartureVO vo) {
		try {
			trackingLogUtils.startLogs();
			tourDepVO = tourPkgBO.getTourDepById(vo.getId());
			if (vo.getDtDep() != null && vo.getDeadline() != null)
				tourDepVO.setDtDeadline(DatesUtils.getPreviousDate(vo.getDtDep(), vo.getDeadline()).getTime());

			handleOpPicShow();
			
			bookingList = bookingBO.getBookingCruiseList(vo);
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("[TourCruisePackageBean] handleShowBookingList");
		}
	}
	
	public void removeTourCruise() {
		bookingVO.getTourCruiseCabinList().remove(0);
	}
	
	public void onAddBookingCruiseCabin() {
		try {
			resetTourCabinBookingForm();
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onBookingCruiseCabinSelected(TourCruiseCabinVO vo) {
		try {
			tourCabinAdd = false;
			tourCabinBookingVO = vo;
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void saveBookingCruiseCabin() {
		try {
			checkingValidCabin();
			 
			if (tourCabinAdd) {
				for (TourCruiseCabinVO vo : bookingVO.getTourCruiseCabinList()) {
					if (tourCabinBookingVO.getIdTourCruiseCabin().equals(vo.getIdTourCruiseCabin())) {
						throw new BusinessException(CommonErrConstant.ERR_BOOKING_CABIN_EXISTED);
					}
				}
				bookingVO.getTourCruiseCabinList().add(tourCabinBookingVO);
			}
			calculateCabin();
			resetTourCabinBookingForm();
			
		} catch (Throwable t) {
			errorResult(t);
			t.printStackTrace();
		}
	}
	
	public void checkingValidCabin() throws BusinessException {
		int totalCabin = 0;
		if (tourCabinBookingVO.getPaxTwn() > 0 && (tourCabinBookingVO.getPaxTwn() % 2) != 0) {
			throw new BusinessException(CommonErrConstant.ERR_BOOKING_PAX_EVEN);
		}
		
		if (tourCabinBookingVO.getPaxTwn() > 0) totalCabin += (int) Math.floor(tourCabinBookingVO.getPaxTwn() / 2);
		if (tourCabinBookingVO.getPaxSgl() > 0) totalCabin += tourCabinBookingVO.getPaxSgl();
		if (totalCabin == 0) throw new BusinessException(CommonErrConstant.ERR_BOOKING_NO_CABIN);
	}
	
	/**
	 * Delete Cruise Cabin
	 */
	public void deleteBookingCruiseCabin(TourCruiseCabinVO vo) {
		try {
			bookingVO.getTourCruiseCabinList().remove(vo);
			calculateCabin();
			resetTourCabinBookingForm();

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleCabinTypeSelect() {
		try {
			for (TourCruiseCabinVO vo : tourDepVO.getTourCruiseCabinList()) {
				if (tourCabinBookingVO.getIdTourCruiseCabin().equals(vo.getId())) {
					tourCabinBookingVO = new TourCruiseCabinVO();
					tourCabinBookingVO.setIdTourCruiseCabin(vo.getId());
					tourCabinBookingVO.setIdTourDep(tourDepVO.getId());
					tourCabinBookingVO.setCruiseCabinCd(vo.getCruiseCabinCd());
					tourCabinBookingVO.setCruiseCabinDesc(vo.getCruiseCabinDesc());
					tourCabinBookingVO.setCabinAllotment(vo.getCabinAllotment());
					tourCabinBookingVO.setPaxPerCabin(vo.getPaxPerCabin());
					tourCabinBookingVO.setPriceTwn(vo.getPriceTwn());
					tourCabinBookingVO.setPrice3(vo.getPrice3());
					tourCabinBookingVO.setPrice4(vo.getPrice4());
					tourCabinBookingVO.setPriceSgl(vo.getPriceSgl());
					tourCabinBookingVO.setPriceInf(vo.getPriceInf());
					tourCabinBookingVO.setDiscountLvl1(vo.getDiscountLvl1());
					tourCabinBookingVO.setDiscountLvl1Pax(vo.getDiscountLvl1Pax());
					tourCabinBookingVO.setDiscountLvl2(vo.getDiscountLvl2());
					tourCabinBookingVO.setDiscountLvl2Pax(vo.getDiscountLvl2Pax());
					break;
				}
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void setBookingVO(BookingVO vo) throws BusinessException {
		super.setBookingVO(vo);
		setCruiseMiscAdtChd();
	}
	
	public void setBookingViewVO(BookingViewVO bookingViewVO) {
		super.setBookingViewVO(bookingViewVO);
		setCruiseMiscAdtChd();
	}

	public void showBookingVO(BookingVO thisBookingVO) {
		super.showBookingVO(thisBookingVO);
		setCruiseMiscAdtChd();
	}
	

	public double getCalculatedTotalMisc() {
	    double total = 0.0;
	    if (bookingVO != null && CollectionUtils.isNotEmpty(bookingVO.getBookingChargeItemList())) {
	        for (BookingChargeItemVO item : bookingVO.getBookingChargeItemList()) {
	            if (item.getIsMisc() && (ProductConstant.TOUR_DEP_ITM_TYPE_TOUR.equals(item.getTypeCd()) || ProductConstant.TOUR_DEP_ITM_TYPE_CRUISE.equals(item.getTypeCd()))) {
	                total += item.getAmount();
	            }
	        }
	    }
	    return total;
	}

	
	public void setCruiseMiscAdtChd() {
		try {
			tourDepVO.setTourCruiseCabinList(tourCruiseCabinBO.getTourCruiseCabinList(tourDepVO.getId(), getSessionInfoBean().getCompanyVO().getId()));
			bookingVO.setTourCruiseCabinList(new ArrayList<TourCruiseCabinVO>());
			
			TourCruiseCabinVO tourCabinVO = null;
			for (BookingChargeItemVO bookingChrg : bookingVO.getBookingChargeItemList()) {
				if (ProductConstant.TOUR_DEP_ITM_TYPE_TOUR.equals(bookingChrg.getTypeCd()) && bookingChrg.getCabinQty() != 0) {
					tourCabinVO = new TourCruiseCabinVO();
					tourCabinVO.setIdTourCruiseCabin(bookingChrg.getIdTourCruiseCabin());
					tourCabinVO.setIdTourDep(tourDepVO.getId());
					tourCabinVO.setCruiseCabinCd(bookingChrg.getCode());
					tourCabinVO.setCruiseCabinDesc(bookingChrg.getDesc());
					tourCabinVO.setPriceTwn(bookingChrg.getAmount());
					tourCabinVO.setPrice3(bookingChrg.getAmount3());
					tourCabinVO.setPrice4(bookingChrg.getAmount4());
					tourCabinVO.setPriceSgl(bookingChrg.getAmountSgl());
					tourCabinVO.setPrice3Chd(bookingChrg.getAmount3Chd());
					tourCabinVO.setPrice4Chd(bookingChrg.getAmount4Chd());
					tourCabinVO.setPriceInf(bookingChrg.getAmountInf());
					tourCabinVO.setPaxTwn(bookingChrg.getQuantity());
					tourCabinVO.setPax3(bookingChrg.getQuantity3());
					tourCabinVO.setPax4(bookingChrg.getQuantity4());
					tourCabinVO.setPaxSgl(bookingChrg.getQuantitySgl());
					tourCabinVO.setPax3Chd(bookingChrg.getQuantity3Chd());
					tourCabinVO.setPax4Chd(bookingChrg.getQuantity4Chd());
					tourCabinVO.setPaxInf(bookingChrg.getQuantityInf());
					tourCabinVO.setTtlCabin(bookingChrg.getCabinQty());
					
					double ttlTwn = tourCabinVO.getPriceTwn() * tourCabinVO.getPaxTwn();
					double ttl3 = tourCabinVO.getPrice3() * tourCabinVO.getPax3();
					double ttl4 = tourCabinVO.getPrice4() * tourCabinVO.getPax4();
					double ttlSgl = tourCabinVO.getPriceSgl() * tourCabinVO.getPaxSgl();
					double ttl3Chd = tourCabinVO.getPrice3Chd() * tourCabinVO.getPax3Chd();
					double ttl4Chd = tourCabinVO.getPrice4Chd() * tourCabinVO.getPax4Chd();
					double ttlInf = tourCabinVO.getPriceInf() * tourCabinVO.getPaxInf();
					tourCabinVO.setTotalPrice(ttlTwn + ttl3 + ttl4 + ttlSgl + ttl3Chd + ttl4Chd + ttlInf);
					
					bookingVO.getTourCruiseCabinList().add(tourCabinVO);
				}
			}
			
			for (BookingChargeItemVO bookingChrg : bookingVO.getBookingChargeItemList()) {
				if (ProductConstant.TOUR_DEP_ITM_TYPE_CRUISE.equals(bookingChrg.getTypeCd()) &&
						bookingChrg.getCode().startsWith(ProductConstant.TOUR_DEP_ITM_CD_DISC)) {
					
					for (TourCruiseCabinVO cabinVO : bookingVO.getTourCruiseCabinList()) {
						if (bookingChrg.getIdTourCruiseCabin().equals(cabinVO.getIdTourCruiseCabin())) {
							double totalDiscount = (-bookingChrg.getAmount() * bookingChrg.getQuantity());
							if (cabinVO.getCalcDiscount() == null) cabinVO.setCalcDiscount(BigDecimal.ZERO);
							cabinVO.setCalcDiscount(cabinVO.getCalcDiscount().add(new BigDecimal(totalDiscount)));
						}
					}
				}
			}
			calculateCabin();
			
			//if (bakBookingVO.getTourCruiseCabinList() == null) bakBookingVO.setTourCruiseCabinList(new ArrayList<TourCruiseCabinVO>());
			bakBookingVO.setTourCruiseCabinList(new ArrayList<TourCruiseCabinVO>());
			for (TourCruiseCabinVO item : bookingVO.getTourCruiseCabinList()) {
				bakBookingVO.getTourCruiseCabinList().add((TourCruiseCabinVO) item.clone());
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void calculateCabin() throws BusinessException {
		int totalCabin = 0;
		int totalCabinBook = 0;
		int totalPax = 0;
		int totalHeadCount = 0;
		
		BigDecimal totalAmt = BigDecimal.ZERO;
		BigDecimal totalDiscount = BigDecimal.ZERO;
		BigDecimal totalPortCharges = BigDecimal.ZERO;
		
		for (TourCruiseCabinVO vo : bookingVO.getTourCruiseCabinList()) {
			totalCabin = 0;
			totalPax = 0;
			if (vo.getPaxTwn() > 0) {
				totalAmt = totalAmt.add(new BigDecimal(String.valueOf(vo.getPaxTwn())).multiply(new BigDecimal(String.valueOf(vo.getPriceTwn()))));
				totalPortCharges = totalPortCharges.add(new BigDecimal(String.valueOf(vo.getPaxTwn())).multiply(new BigDecimal(tourDepVO.getMiscAdt())));
				totalPax += vo.getPaxTwn();
			}
			if (vo.getPaxSgl() > 0) {
				totalAmt = totalAmt.add(new BigDecimal(String.valueOf(vo.getPaxSgl())).multiply(new BigDecimal(String.valueOf(vo.getPriceSgl()))));
				totalPortCharges = totalPortCharges.add(new BigDecimal(String.valueOf(vo.getPaxSgl())).multiply(new BigDecimal(tourDepVO.getMiscAdt())));
				totalPax += vo.getPaxSgl();
			}
			if (vo.getPax3() > 0) {
				totalAmt = totalAmt.add(new BigDecimal(String.valueOf(vo.getPax3())).multiply(new BigDecimal(String.valueOf(vo.getPrice3()))));
				totalPortCharges = totalPortCharges.add(new BigDecimal(String.valueOf(vo.getPax3())).multiply(new BigDecimal(tourDepVO.getMiscAdt())));
				totalPax += vo.getPax3();
			}
			if (vo.getPax4() > 0) {
				totalAmt = totalAmt.add(new BigDecimal(String.valueOf(vo.getPax4())).multiply(new BigDecimal(String.valueOf(vo.getPrice4()))));
				totalPortCharges = totalPortCharges.add(new BigDecimal(String.valueOf(vo.getPax4())).multiply(new BigDecimal(tourDepVO.getMiscAdt())));
				totalPax += vo.getPax4();
			}
			if (vo.getPaxInf() > 0) {
				totalAmt = totalAmt.add(new BigDecimal(String.valueOf(vo.getPaxInf())).multiply(new BigDecimal(String.valueOf(vo.getPriceInf()))));
				totalPortCharges = totalPortCharges.add(new BigDecimal(String.valueOf(vo.getPaxInf())).multiply(new BigDecimal(tourDepVO.getMiscAdt())));
				totalPax += vo.getPaxInf();
			}
			if (vo.getPax3Chd() > 0) {
				totalAmt = totalAmt.add(new BigDecimal(String.valueOf(vo.getPax3Chd())).multiply(new BigDecimal(String.valueOf(vo.getPrice3Chd()))));
				totalPortCharges = totalPortCharges.add(new BigDecimal(String.valueOf(vo.getPax3Chd())).multiply(new BigDecimal(tourDepVO.getMiscAdt())));
				totalPax += vo.getPax3Chd();
			}
			if (vo.getPax4Chd() > 0) {
				totalAmt = totalAmt.add(new BigDecimal(String.valueOf(vo.getPax4Chd())).multiply(new BigDecimal(String.valueOf(vo.getPrice4Chd()))));
				totalPortCharges = totalPortCharges.add(new BigDecimal(String.valueOf(vo.getPax4Chd())).multiply(new BigDecimal(tourDepVO.getMiscAdt())));
				totalPax += vo.getPax4Chd();
			}
			
			if (vo.getPaxTwn() > 0) totalCabin += (int) Math.floor(vo.getPaxTwn() / 2);
			if (vo.getPaxSgl() > 0) totalCabin += vo.getPaxSgl();
			vo.setTtlCabin(totalCabin);
			totalCabinBook += totalCabin;
			totalHeadCount += totalPax;
			
			if (vo.getCalcDiscount() != null) totalDiscount = totalDiscount.add(vo.getCalcDiscount());
			else {
				if (totalPax > 0) {
					int remainPax = 0;
					
					if (vo.getDiscountLvl1() > 0) {
						if (vo.getDiscountLvl1Pax() > 0) {
							if (totalPax <= vo.getDiscountLvl1Pax()) totalDiscount = totalDiscount.add(new BigDecimal(vo.getDiscountLvl1()).multiply(new BigDecimal(totalPax)));
							else {
								remainPax = totalPax - vo.getDiscountLvl1Pax();
								totalDiscount = totalDiscount.add(new BigDecimal(vo.getDiscountLvl1()).multiply(new BigDecimal(vo.getDiscountLvl1Pax())));
							}
						} else remainPax = totalPax;
					}
					
					if (remainPax > 0) {
						if (vo.getDiscountLvl2() > 0 && vo.getDiscountLvl2Pax() > 0) {
							if (remainPax <= vo.getDiscountLvl2Pax()) totalDiscount = totalDiscount.add(new BigDecimal(vo.getDiscountLvl2()).multiply(new BigDecimal(remainPax)));
							else totalDiscount = totalDiscount.add(new BigDecimal(vo.getDiscountLvl2()).multiply(new BigDecimal(vo.getDiscountLvl2Pax())));
						}
					}
				}
			}
		}
		
		double deposit = (new BigDecimal(String.valueOf(totalHeadCount)).multiply(new BigDecimal(String.valueOf(tourPkgVO.getDeposit())))).doubleValue();
		bookingVO.setTotalAmount(totalAmt.add(totalPortCharges).doubleValue());
		bookingVO.setDiscountAmount(totalDiscount.doubleValue());
		bookingVO.setDepositAmount(deposit);
		bookingVO.setAmountPayable(totalAmt.add(totalPortCharges).subtract(totalDiscount).doubleValue());
		bookingVO.setTotalCabin(totalCabinBook);
		bookingVO.setTotalHeadCount(totalHeadCount);
	}
	
	private int checkBookingCabinBalance(int i) throws BusinessException {
		switch (i) {
		case 0:
			// check whether over seat to book
			for (TourCruiseCabinVO tVO : tourDepVO.getTourCruiseCabinList()) {
				int balance = 0;
				for (TourCruiseCabinVO bVO : bookingVO.getTourCruiseCabinList()) {
					if (bVO.getCabinAllotment() > 0) {
						if (tVO.getId().equals(bVO.getIdTourCruiseCabin())) {
							int currBook = bVO.getTtlCabin();
							balance = tVO.getCabinAllotment() - tVO.getTotalConfirmedCabin() - tVO.getTotalKivCabin();
							if (currBook > balance) 
								throw new BusinessException(CommonErrConstant.ERR_BOOKING_OVER_CABIN_BALANCE, null, new String[]{tVO.getCruiseCabinCd()});
							return balance - currBook;
						}
					}
				}
			}
			return -1;
		case 1:
			// check whether over seat to book
			for (TourCruiseCabinVO tVO : tourDepVO.getTourCruiseCabinList()) {
				if (tVO.getCabinAllotment() > 0) {
					int balance = 0;
					for (TourCruiseCabinVO bVO : bookingVO.getTourCruiseCabinList()) {
						if (bVO.getCabinAllotment() > 0) {
							if (tVO.getId().equals(bVO.getIdTourCruiseCabin())) {
								int prevBook = 0;
								for (TourCruiseCabinVO preVO : bakBookingVO.getTourCruiseCabinList()) {
									if (preVO.getIdTourCruiseCabin().equals(bVO.getIdTourCruiseCabin())) {
										prevBook = preVO.getTtlCabin();
										break;
									}
								}
								int currBook = bVO.getTtlCabin();
								balance = tVO.getCabinAllotment() - tVO.getTotalConfirmedCabin() - tVO.getTotalKivCabin() + prevBook - currBook;
								if (balance < 0)
									throw new BusinessException(CommonErrConstant.ERR_BOOKING_OVER_CABIN_BALANCE, null, new String[]{tVO.getCruiseCabinCd()});
								return balance;
							}
						}
					}
				}
			}
			return -1;
		}
		return -1;
	}
	
	private void loadCabinList() {
		try {
			if (cabinList == null) { 
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("idCruise", tourPkgVO.getIdCruise());
				cabinList = cruiseCabinBO.getCabinList(params);
			}
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void setTourDepVO(TourDepartureVO vo) {
		tourDepVO = vo;
	}

	public List<TourCruiseCabinVO> getTourCabinList() {
		return tourCabinList;
	}

	public List<CruiseCabinVO> getCabinList() {
		return cabinList;
	}

	public TourCruiseCabinVO getTourCabinVO() {
		return tourCabinVO;
	}

	public void setTourCabinVO(TourCruiseCabinVO tourCabinVO) {
		this.tourCabinVO = tourCabinVO;
		loadCabinList();
	}

	public TourCruiseCabinVO getTourCabinBookingVO() {
		return tourCabinBookingVO;
	}
}
