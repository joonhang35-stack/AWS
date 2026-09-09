package com.bcs.zsg.product.bo;

import java.util.List;

import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.history.vo.TourDepHistoryVO;
import com.bcs.zsg.history.vo.TourPackageHistoryVO;
import com.bcs.zsg.maintenance.vo.CityVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.RegionVO;
import com.bcs.zsg.product.service.TourPackageService;
import com.bcs.zsg.product.vo.AirlineScheduleVO;
import com.bcs.zsg.product.vo.CruiseScheduleVO;
import com.bcs.zsg.product.vo.HotelVO;
import com.bcs.zsg.product.vo.RoomTypeVO;
import com.bcs.zsg.product.vo.TourCatVO;
import com.bcs.zsg.product.vo.TourCruiseCabinVO;
import com.bcs.zsg.product.vo.TourDepItemVO;
import com.bcs.zsg.product.vo.TourDepartureDiscountVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourDepartureViewVO;
import com.bcs.zsg.product.vo.TourHotelVO;
import com.bcs.zsg.product.vo.TourImageVO;
import com.bcs.zsg.product.vo.TourItineryVO;
import com.bcs.zsg.product.vo.TourPackageAttributeVO;
import com.bcs.zsg.product.vo.TourPackageCommisionVO;
import com.bcs.zsg.product.vo.TourPackageDailyChecklistVO;
import com.bcs.zsg.product.vo.TourPackageDailyItineraryItemVO;
import com.bcs.zsg.product.vo.TourPackageDailyItineraryVO;
import com.bcs.zsg.product.vo.TourPackageItineryVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.product.vo.TourThemeCountryVO;
import com.bcs.zsg.product.vo.TourPackageRemarksVO;
import com.bcs.zsg.product.vo.TourPackageRoomPriceVO;
import com.bcs.zsg.product.vo.TourPackageTagVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.sales.vo.BookingChargeItemVO;
import com.bcs.zsg.sales.vo.BookingVO;
import com.bcs.zsg.sales.vo.BookingViewVO;
import com.bcs.zsg.sales.vo.InvoiceItemVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

import java.util.Date;  

public class TourPackageBOImpl implements TourPackageBO {

	@Autowired
	private TourPackageService tourPkgService;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#insertVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void insertVO(BaseVO vo) throws BusinessException {
		tourPkgService.insertVO(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#updateVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void updateVO(BaseVO vo) throws BusinessException {
		tourPkgService.updateVO(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#deleteVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void deleteVO(BaseVO vo) throws BusinessException {
		tourPkgService.deleteVO(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#addTourTheme(com.bcs.zsg.product.vo.TourThemeVO)
	 */
	@Override
	public void addTourTheme(TourThemeVO vo) throws BusinessException {
		tourPkgService.addTourTheme(vo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#updTourTheme(com.bcs.zsg.product.vo.TourThemeVO)
	 */
	@Override
	public void updTourTheme(TourThemeVO vo) throws BusinessException {
		tourPkgService.updTourTheme(vo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#delTourTheme(com.bcs.zsg.product.vo.TourThemeVO)
	 */
	@Override
	public void delTourTheme(TourThemeVO tourThemeVO) throws BusinessException {
		if (tourThemeVO.getIdParent() == null || tourThemeVO.getIdParent() == 0) {
			if (tourPkgService.isChildThemeExisted(tourThemeVO.getId())) throw new BusinessException(CommonErrConstant.ERR_TOUR_CHILD_THEME_USED);
			else if (tourPkgService.isTourPkgExisted(tourThemeVO.getId())) throw new BusinessException(CommonErrConstant.ERR_TOUR_THEME_PKG_USED);
		} else {
			if (tourPkgService.isTourPkgExisted(tourThemeVO.getId())) throw new BusinessException(CommonErrConstant.ERR_TOUR_THEME_PKG_USED);
		}
		//deleteVO(tourThemeVO);
		tourThemeVO.setStatusCode(CommonConstant.STATUS_CD_INACTIVE);
		updateVO(tourThemeVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#delTourPkg(com.bcs.zsg.product.vo.TourPackageVO)
	 */
	@Override
	public void delTourPkg(TourPackageVO tourPkgVO) throws BusinessException {
		if (tourPkgService.isTourDepExisted(null, tourPkgVO.getId())) throw new BusinessException(CommonErrConstant.ERR_TOUR_PKG_DEP_USED);
		tourPkgVO.setStatusCode(CommonConstant.STATUS_CD_INACTIVE);
		updateVO(tourPkgVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#addTourDep(com.bcs.zsg.product.vo.TourDepartureVO)
	 */
	@Override
	public void cloneTourDep(TourDepartureVO tourDepVO) throws BusinessException {
		tourPkgService.cloneTourDep(tourDepVO);
	}
	
	@Override
	public void cloneTourPkg(TourPackageVO tourPkgVO) throws BusinessException {
		addTourPkg(tourPkgVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#addTourPkg(com.bcs.zsg.product.vo.TourPackageVO)
	 */
	@Override
	public void addTourPkg(TourPackageVO tourPkgVO) throws BusinessException {
		tourPkgService.addTourPkg(tourPkgVO);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#addTourDep(com.bcs.zsg.product.vo.TourDepartureVO)
	 */
	@Override
	public void addTourDep(TourDepartureVO tourDepVO) throws BusinessException {
		tourPkgService.addTourDep(tourDepVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#updTourDep(com.bcs.zsg.product.vo.TourDepartureVO, com.bcs.zsg.common.vo.AddUpdDelVO, com.bcs.zsg.common.vo.AddUpdDelVO, com.bcs.zsg.common.vo.AddUpdDelVO)
	 */
	@Override
	public void updTourDep(TourDepartureVO tourDepVO, AddUpdDelVO itineryAUDVO, AddUpdDelVO tourHotelAUDVO, AddUpdDelVO tourDepItemAUDVO, AddUpdDelVO tourDepRemarks, List<List<TourDepItemVO>> delTourDepItemList) throws BusinessException {
		tourPkgService.updTourDep(tourDepVO, itineryAUDVO, tourHotelAUDVO, tourDepItemAUDVO, tourDepRemarks, delTourDepItemList);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#updTourDep(com.bcs.zsg.product.vo.TourDepartureVO, com.bcs.zsg.common.vo.AddUpdDelVO, com.bcs.zsg.common.vo.AddUpdDelVO, com.bcs.zsg.common.vo.AddUpdDelVO)
	 */
	@Override
	public void updTourFNEPackage(TourPackageVO tourPkgVO,AddUpdDelVO tourPkgRoomPrice) throws BusinessException {
		if (CollectionUtils.isNotEmpty(tourPkgRoomPrice.getAddList())) {
			for (Object obj : tourPkgRoomPrice.getAddList()) 
				{
				TourPackageRoomPriceVO vo = (TourPackageRoomPriceVO) obj;
				vo.setPkgId(tourPkgVO.getId());
				insertVO(vo);
				}
		}
		if (CollectionUtils.isNotEmpty(tourPkgRoomPrice.getDelList())) {
			for (Object obj : tourPkgRoomPrice.getDelList()) deleteVO((TourPackageRoomPriceVO) obj);
		}

		if (CollectionUtils.isNotEmpty(tourPkgRoomPrice.getUpdList())) {
			for (Object obj : tourPkgRoomPrice.getUpdList()) updateVO((TourPackageRoomPriceVO) obj);
		}
		 
	}
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#delTourDep(com.bcs.zsg.product.vo.TourDepartureVO)
	 */
	@Override
	public void delTourDep(TourDepartureVO tourDepVO) throws BusinessException {
		tourPkgService.delTourDep(tourDepVO);
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getRoomTypeList(com.bcs.zsg.common.vo.SearchParamVO, int)
	 */
	public List<RoomTypeVO> getRoomTypeList() throws BusinessException{
		return tourPkgService.getTourRoomTypeList();
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourThemeList(com.bcs.zsg.common.vo.SearchParamVO, int)
	 */
	@Override
	public List<TourThemeVO> getTourThemeList(SearchParamVO searchParamVO, int level) throws BusinessException {
		return tourPkgService.getTourThemeList(searchParamVO, level);
	}
	
	@Override
	public List<TourThemeVO> getTourThemeList(SearchParamVO searchParamVO, Long idParent) throws BusinessException {
		return tourPkgService.getTourThemeList(searchParamVO, idParent);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getSpecTourThemeList(com.bcs.zsg.product.vo.TourThemeVO)
	 */
	@Override
	public List<TourThemeVO> getSpecTourThemeList(TourThemeVO tourThemeVO) throws BusinessException {
		return tourPkgService.getSpecTourThemeList(tourThemeVO);
	}
	
	public List<TourThemeVO> getTourThemeList(Map<String, Object> params) throws BusinessException {
		return tourPkgService.getTourThemeList(params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourFreeNEasyList(java.lang.String)
	 */
	@Override
	public List<TourPackageVO> getTourFreeNEasyList(String typeCd) throws BusinessException {
		return tourPkgService.getTourFreeNEasyList(typeCd);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourDep(java.lang.Long)
	 */
	@Override
	public TourDepartureVO getTourDep(Long idTourPkg) throws BusinessException {
		return tourPkgService.getTourDep(idTourPkg);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourDepById(java.lang.Long)
	 */
	@Override
	public TourDepartureVO getTourDepById(Long idTourDep) throws BusinessException {
		return tourPkgService.getTourDepById(idTourDep);
	}
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourDepById(java.lang.Long)
	 */
	public TourDepartureVO getTourDepByCodeAndDepDt(String code, Date depDt) throws BusinessException {
		return tourPkgService.getTourDepByCodeAndDepDt(code,depDt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourItinery(java.lang.Long)
	 */
	@Override
	public TourItineryVO getTourItinery(Long idTourDep) throws BusinessException {
		return tourPkgService.getTourItinery(idTourDep);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getCountryList(java.lang.Long)
	 */
	@Override
	public List<CountryVO> getCountryList(Long idRegion) throws BusinessException {
		return tourPkgService.getCountryList(idRegion);
	}
	
	@Override
	public List<CityVO> getCityList(Long idCountry) throws BusinessException {
		return tourPkgService.getCityList(idCountry);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourDepList()
	 */
	@Override
	public List<TourDepartureVO> getTourDepList() throws BusinessException {
		return getTourDepList(null, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getFilterTourDepList()
	 */
	@Override
	public List<TourDepartureVO> getFilterTourDepList(Date dtFrom, Date dtTo, String code, String title, Boolean filterInactiveRcrd, String filterTourStatusCd) throws BusinessException {
		return tourPkgService.getFilterTourDepList(dtFrom, dtTo, code, title, filterInactiveRcrd, filterTourStatusCd);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getFilterBookingTourDepList()
	 */
	@Override
	public List<TourDepartureVO> getFilterBookingTourDepList(Date dtFrom, Date dtTo, String code, String title, CompanyVO companyVO, String filterTourStatusCd) throws BusinessException {
		return tourPkgService.getFilterBookingTourDepList(dtFrom, dtTo, code, title, companyVO, filterTourStatusCd);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourDepList(java.lang.Long, com.bcs.zsg.maintenance.vo.CompanyVO)
	 */
	@Override
	public List<TourDepartureVO> getTourDepList(Long idTourPkg, CompanyVO companyVO) throws BusinessException {
		return tourPkgService.getTourDepList(idTourPkg, companyVO);
	}
	

	public List<CompanyVO> getTourDepItemCompanyList(Long idTourDep) throws BusinessException{
		return tourPkgService.getTourDepItemCompanyList(idTourDep);
	
	}
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourDepItemList(java.lang.Long)
	 */
	@Override
	public List<TourDepItemVO> getTourDepItemList(Long idTourDep) throws BusinessException {
		return getTourDepItemList(idTourDep, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourDepItemList(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<TourDepItemVO> getTourDepItemList(Long idTourDep, Long idCompany) throws BusinessException {
		return tourPkgService.getTourDepItemList(idTourDep, idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#setMiscAdtChd(com.bcs.zsg.product.vo.TourDepartureVO, com.bcs.zsg.product.vo.AirlineScheduleVO, com.bcs.zsg.sales.vo.BookingVO)
	 */
	@Override
	public void setMiscAdtChd(TourDepartureVO tourDepVO, AirlineScheduleVO airlineScheduleVO, BookingVO bookingVO) throws BusinessException {
		if (airlineScheduleVO != null) tourPkgService.setMiscAdtChd(tourDepVO, airlineScheduleVO);
		else if (bookingVO != null) tourPkgService.setMiscAdtChd(tourDepVO, bookingVO);
	}
	
	public void setCruiseMiscAdtChd(TourDepartureVO tourDepVO, CruiseScheduleVO cruiseScheduleVO, BookingVO bookingVO) throws BusinessException {
		if (cruiseScheduleVO != null) tourPkgService.setCruiseMiscAdtChd(tourDepVO, cruiseScheduleVO);
		else if (bookingVO != null) tourPkgService.setCruiseMiscAdtChd(tourDepVO, bookingVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourItineryList(java.lang.Long)
	 */
	@Override
	public List<TourItineryVO> getTourItineryList(Long idTourDep) throws BusinessException {
		return tourPkgService.getTourItineryList(idTourDep);
	}
	
	@Override
	public List<TourPackageItineryVO> getTourPkgItineryList(Long idTourPkg) throws BusinessException {
		return tourPkgService.getTourPkgItineryList(idTourPkg);
	}
	
	@Override
	public List<TourPackageDailyItineraryVO> getTourPkgDailyItineraryList(Long idTourPkg) throws BusinessException {
		return tourPkgService.getTourPkgDailyItineraryList(idTourPkg);
	}
	
	@Override
	public TourPackageDailyItineraryVO getTourPkgDailyItinerary(Long id) throws BusinessException {
		return tourPkgService.getTourPkgDailyItinerary(id);
	}
	
	@Override
	public List<TourPackageDailyItineraryItemVO> getTourPkgDailyItineraryItemList(Long idTourPkgDailyItinerary, String langCd) throws BusinessException {
		return tourPkgService.getTourPkgDailyItineraryItemList(idTourPkgDailyItinerary, langCd);
	}
	
	@Override
	public List<TourPackageTagVO> getTourPackageTagList(Long idTourPkg) throws BusinessException {
		return tourPkgService.getTourPackageTagList(idTourPkg);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourHotelList(java.lang.Long)
	 */
	@Override
	public List<TourHotelVO> getTourHotelList(Long idTourDep) throws BusinessException {
		return getTourHotelList(idTourDep, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourHotelList(java.lang.Long, java.util.List)
	 */
	@Override
	public List<TourHotelVO> getTourHotelList(Long idTourDep, List<HotelVO> hotelList) throws BusinessException {
		return tourPkgService.getTourHotelList(idTourDep, hotelList);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourHotelList(java.lang.Long, java.util.List)
	 */
	@Override
	public  List<TourPackageRemarksVO> getTourPackageRemarks(Long idTourDep) throws BusinessException {
		return tourPkgService.getTourPackageRemarks(idTourDep);
	}
	
	@Override
	public  List<TourDepartureDiscountVO> getTourDepDiscount(Long idTourDep) throws BusinessException {
		return tourPkgService.getTourDepDiscount(idTourDep);
	}
  
	public List<TourPackageRoomPriceVO> getTourPackageRoomPrice(Long idPkg) throws BusinessException{
		return tourPkgService.getTourPackageRoomPrice(idPkg);
	}
	/* (non-Javadoc)
	 * @see com.bcs.zsg.sales.bo.CustomerBO#updObjList(com.bcs.zsg.sales.vo.CustomerVO, com.bcs.zsg.common.vo.AddUpdDelVO)
	 */
	@Override
	public void updObjList(TourPackageRemarksVO tourPackageRemarksVO, AddUpdDelVO objectVO) throws BusinessException {
		tourPkgService.updObjList(tourPackageRemarksVO, objectVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#isTourDepFirstTimeUpd(java.lang.Long)
	 */
	@Override
	public boolean isTourDepFirstTimeUpd(Long id) throws BusinessException {
		return tourPkgService.isTourDepFirstTimeUpd(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourDepHistory(java.lang.Long)
	 */
	@Override
	public TourDepHistoryVO getTourDepHistory(Long idTourDepHist) throws BusinessException {
		return tourPkgService.getTourDepHistory(idTourDepHist);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourDepList()
	 */
	@Override
	public List<TourDepartureViewVO> getTourDepViewList() throws BusinessException {
		return tourPkgService.getTourDepViewList();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourDepVIewById(java.lang.Long)
	 */
	@Override
	public TourDepartureViewVO getTourDepViewById(Long idTourDep) throws BusinessException {
		return tourPkgService.getTourDepViewById(idTourDep);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourPackageById(java.lang.Long)
	 */
	@Override
	public TourPackageVO getTourPackageById(Long idTourPkg) throws BusinessException {
		return tourPkgService.getTourPackageById(idTourPkg);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#importDefaultInvoiceItems(com.bcs.zsg.product.vo.TourDepartureVO, com.bcs.zsg.maintenance.vo.CompanyVO)
	 */
	@Override
	public List<TourDepItemVO> importDefaultInvoiceItems(TourDepartureVO tourDepVO, CompanyVO companyVO) throws BusinessException {
		return tourPkgService.importDefaultInvoiceItems(tourDepVO, companyVO);
	}
	

	@Override
	public TourThemeVO getTourThemeById(Long idTourTheme) throws BusinessException {
		return tourPkgService.getTourThemeById( idTourTheme);
		
	}

	@Override
	public TourCatVO getTourCatById(Long idTourCat) throws BusinessException {
		return tourPkgService.getTourCatById( idTourCat);
		
	}

	public List<TourDepartureVO> getTourDepListWithItems(Long idTourPkg) throws BusinessException {
		return tourPkgService.getTourDepListWithItems( idTourPkg);
		
	}
	
	@Override
	public int getTourDepListCountWithInvoicePax(Map<String, Object> params) throws BusinessException {
		return tourPkgService.getTourDepListCountWithInvoicePax(params);
	}

	@Override
	public List<TourDepartureVO> getTourDepListWithInvoicePax(Map<String, Object> params) throws BusinessException {
		return tourPkgService.getTourDepListWithInvoicePax(params);
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.TourPackageBO#insertTourDepHistory(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public void insertTourDepHistory(Long id, String actionCdDel, String reason) throws BusinessException {
		tourPkgService.insertTourDepHistory(id, actionCdDel, reason);
	}
	
	@Override
	public void insertTourPkgHistory(Long id, String actionCdDel, String reason) throws BusinessException {
		tourPkgService.insertTourPkgHistory(id, actionCdDel, reason);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getAvailableTourDepList(java.lang.Long, com.bcs.zsg.sales.vo.InvoiceVO)
	 */
	@Override
	public List<TourDepartureVO> getAvailableTourDepList(Long idCompany, InvoiceVO invoiceVO) throws BusinessException {
		return tourPkgService.getAvailableTourDepList(idCompany, invoiceVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#checkTourThemeAvailable(com.bcs.zsg.product.vo.TourThemeVO, com.bcs.zsg.product.vo.TourThemeVO)
	 */
	@Override
	public void checkTourThemeAvailable(TourThemeVO tourThemeVO, TourThemeVO bakTourThemeVO) throws BusinessException {
		tourPkgService.checkTourThemeAvailable(tourThemeVO, bakTourThemeVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#updateTourPkg(com.bcs.zsg.product.vo.TourPackageVO, com.bcs.zsg.history.vo.TourPackageHistoryVO)
	 */
	@Override
	public void updateTourPkg(TourPackageVO tourPkgVO, TourPackageHistoryVO tourPkgHistoryVO) throws BusinessException {
		tourPkgService.updateTourPkg(tourPkgVO, tourPkgHistoryVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#isInvEOIssued(com.bcs.zsg.product.vo.TourDepartureVO, com.bcs.zsg.sales.vo.BookingVO)
	 */
	@Override
	public boolean isInvEOIssued(TourDepartureVO tourDepVO, BookingVO bookingVO) throws BusinessException {
		return tourPkgService.isInvEOIssued(tourDepVO, bookingVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getEOTourDepList(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<TourDepartureVO> getEOTourDepList(Long idTourPkg, Long idAirlineSchedule) throws BusinessException {
		return tourPkgService.getEOTourDepList(idTourPkg, idAirlineSchedule);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourDepListV2()
	 */
	@Override
	public List<TourDepartureVO> getTourDepListV2() throws BusinessException {
		return tourPkgService.getTourDepListV2();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.TourPackageService#updateTourStatusFull(java.lang.Long)
	 */
	@Override
	public void updateTourStatusFull(Long idTourDep) throws BusinessException {
		tourPkgService.updateTourStatusFull(idTourDep);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourDepListWithPush(com.bcs.zsg.maintenance.vo.CompanyVO, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<TourThemeVO> getTourDepListWithPush(CompanyVO companyVO, SearchParamVO searchParamVO) throws BusinessException {
		return tourPkgService.getTourDepListWithPush(companyVO, searchParamVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.TourPackageService#getTourDepSeason(java.lang.Long)
	 */
	@Override
	public List<String> getTourDepSeason(Long idTourDep) throws BusinessException {
		return tourPkgService.getTourDepSeason(idTourDep);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getThemeCountryList(java.lang.Long)
	 */
	@Override
	public List<TourThemeCountryVO> getThemeCountryList(Long idTourTheme) throws BusinessException {
		return tourPkgService.getThemeCountryList(idTourTheme);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourPackageAttributeList(java.lang.Long)
	 */
	@Override
	public List<TourPackageAttributeVO> getTourPackageAttributeList(Long idTourPkg) throws BusinessException {
		return tourPkgService.getTourPackageAttributeList(idTourPkg);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourPackageCommisionList(java.lang.Long)
	 */
	@Override
	public List<TourPackageCommisionVO> getTourPackageCommisionList(Long idTourPkg) throws BusinessException {
		return tourPkgService.getTourPackageCommisionList(idTourPkg);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTagList(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<String> getPackageTagList(Long idTourPkg, String code) throws BusinessException {
		return tourPkgService.getPackageTagList(idTourPkg, code);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getDepartureTagList(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<String> getDepartureTagList(Long idTourDep, String code) throws BusinessException {
		return tourPkgService.getDepartureTagList(idTourDep, code);
	}
	
	@Override
	public List<CountryVO> getFullCountryList(Long idRegion) throws BusinessException{
		return tourPkgService.getFullCountryList(idRegion);
	}
	
	@Override
	public List<RegionVO> getFullRegionList() throws BusinessException{
		return tourPkgService.getFullRegionList();
	}
	
	@Override
	public List<String> getTourPackageCountryList(Long idTourPkg, String code) throws BusinessException{
		return tourPkgService.getTourPackageCountryList(idTourPkg, code);
	}
	
	@Override
	public List<TourImageVO> getTourImageList(Long idTourPkg) throws BusinessException{
		return tourPkgService.getTourImageList(idTourPkg);
	}

	@Override
	public int getTourDepListSize(Map<String, Object> params) throws BusinessException {
		return tourPkgService.getTourDepListSize(params);
	}

	@Override
	public List<TourDepartureVO> getTourDepList(Map<String, Object> params) throws BusinessException {
		return tourPkgService.getTourDepList(params);
	}

	@Override
	public List<TourPackageItineryVO> getTourPkgItineryListByIdTourPkgDailyItinerary(Long idTourPkgDailyItinerary)
			throws BusinessException {
		return tourPkgService.getTourPkgItineryListByIdTourPkgDailyItinerary(idTourPkgDailyItinerary);
	}

	@Override
	public List<TourDepartureVO> getTourDepListBySearch(TourDepartureVO searchTourDep, CompanyVO companyVO) throws BusinessException {
		return tourPkgService.getTourDepListBySearch(searchTourDep, companyVO);
	}

	@Override
	public List<TourDepartureVO> getTourDepListCruiseBySearch(TourDepartureVO searchTourDep, CompanyVO companyVO) throws BusinessException {
		return tourPkgService.getTourDepListCruiseBySearch(searchTourDep, companyVO);
	}
	
	@Override
	public void clonseTourPkgDailyItinerary(TourPackageDailyItineraryVO tourPkgDailyItineraryVO) throws BusinessException {
		tourPkgService.clonseTourPkgDailyItinerary(tourPkgDailyItineraryVO);
	}

	@Override
	public List<TourDepartureVO> getTourDepartureInsuranceList(SearchParamVO searchParamVO, Map<String, Object> params) throws BusinessException {
		return tourPkgService.getTourDepartureInsuranceList(searchParamVO, params);
	}

	public List<TourDepartureVO> getTourDepartureInsurancePaxList(SearchParamVO searchParamVO, Map<String, Object> params) throws BusinessException {
		return tourPkgService.getTourDepartureInsurancePaxList(searchParamVO, params);
	}
	
	@Override
	public void saveTourDepartureInsurance(TourDepartureVO tourDepartureVO) throws BusinessException {
		tourPkgService.saveTourDepartureInsurance(tourDepartureVO);
	}

	@Override
	public List<TourDepartureVO> getFilterTourDepList(TourDepartureVO searchTourDepFilter) throws BusinessException {
		return tourPkgService.getFilterTourDepList(searchTourDepFilter);
	}

	@Override
	public List<TourDepartureVO> getFilterBookingTourDepList(TourDepartureVO searchTourDepFilter, CompanyVO companyVO) throws BusinessException {
		return tourPkgService.getFilterBookingTourDepList(searchTourDepFilter, companyVO);
	}
	
	@Override
	public List<TourDepHistoryVO> getTourDepHistoryList(Long idTourDep) throws BusinessException {
		return tourPkgService.getTourDepHistoryList(idTourDep);
	}
	
	@Override
	public List<TourDepartureVO> getTourDepSalesSuppList(TourDepartureVO searchTourDepFilter, List<EmployeeViewVO> employeeList) throws BusinessException {
		return tourPkgService.getTourDepSalesSuppList(searchTourDepFilter, employeeList);
	}
	
	@Override
	public void saveTourDepSalesSupp(TourDepartureVO tourDepartureVO) throws BusinessException {
		tourPkgService.saveTourDepSalesSupp(tourDepartureVO);
	}

	@Override
	public TourPackageItineryVO getTourPackageItineraryVOByCode(Long tourPkgId, Long idTourPkgDailyCms) throws BusinessException {
		return tourPkgService.getTourPackageItineraryVOByCode(tourPkgId, idTourPkgDailyCms);
	}
	
	@Override
	public TourPackageDailyChecklistVO getTourPkgDailyChecklistVOById(Long idTourPkgDailyCms) throws BusinessException {
		return tourPkgService.getTourPkgDailyChecklistVOById(idTourPkgDailyCms);
	}
	
	@Override
	public List<TourDepartureVO> getTourDepListCruise(Long idTourPkg, CompanyVO companyVO) throws BusinessException {
		return tourPkgService.getTourDepListCruise(idTourPkg, companyVO);
	}

	public List<TourDepartureVO> getAgentTourDepListCruise(Long idTourPkg, CompanyVO companyVO) throws BusinessException {
		return tourPkgService.getAgentTourDepListCruise(idTourPkg, companyVO);
	}

	@Override
	public List<TourDepartureVO> getFilterTourDepListCruise(Date dtFrom, Date dtTo, String code, String title,
			Boolean filterInactiveRcrd, String filterTourStatusCd, Long idCompany) throws BusinessException {
		return tourPkgService.getFilterTourDepListCruise(dtFrom, dtTo, code, title, filterInactiveRcrd, filterTourStatusCd, idCompany);
	}
	
	@Override
	public List<TourDepartureVO> getFilterTourDepListCruise(TourDepartureVO searchTourDepFilter, Long idCompany) throws BusinessException {
		return tourPkgService.getFilterTourDepListCruise(searchTourDepFilter, idCompany);
	}
	
	@Override
	public void updateTourBookingChargeItem(TourCruiseCabinVO tourCruiseCabinVO) throws BusinessException {
		// TODO Auto-generated method stub
		tourPkgService.updateTourBookingChargeItem(tourCruiseCabinVO);
	}
	
	@Override
	public void updateCruiseTourStatus(Long idTourDep) throws BusinessException {
		tourPkgService.updateCruiseTourStatus(idTourDep);
	}
	
	@Override
	public TourDepItemVO getTourDepItem(Long idTourDep, String code, CompanyVO companyVO) throws BusinessException {
		return tourPkgService.getTourDepItem(idTourDep, code, companyVO);
	}

	@Override
	public List<TourDepartureVO> getFilterBookingTourDepListCruise(Date dtFrom, Date dtTo, String code, String title,
			Boolean filterInactiveRcrd, CompanyVO companyVO, String filterTourStatusCd) throws BusinessException {
		return tourPkgService.getFilterBookingTourDepListCruise(dtFrom, dtTo, code, title, filterInactiveRcrd, companyVO, filterTourStatusCd);
	}

	@Override
	public List<TourDepartureVO> getFilterBookingTourDepListCruise(TourDepartureVO searchTourDepFilter, CompanyVO companyVO) throws BusinessException {
		return tourPkgService.getFilterBookingTourDepListCruise(searchTourDepFilter, companyVO);
	}
	
	@Override
	public List<TourDepartureVO> getAvailableCruiseTourDepList(Long idCompany, InvoiceVO invoiceVO, BookingVO bookingVO) throws BusinessException {
		return tourPkgService.getAvailableCruiseTourDepList(idCompany, invoiceVO, bookingVO);
	}

	@Override
	public void updateTourDepsRemainTc(TourPackageVO tourPkgVO) throws BusinessException {
		tourPkgService.updateTourDepsRemainTc(tourPkgVO);
	}
	
	@Override
	public List<TourDepartureVO> getAgentTourDepList(Long idTourPkg, CompanyVO companyVO) throws BusinessException {
		return tourPkgService.getAgentTourDepList(idTourPkg, companyVO);
	}
	
	public List<TourDepartureVO> getAvailableTourDepList(Long idCompany, InvoiceVO invoiceVO, String tourTypeCode) throws BusinessException {
		return tourPkgService.getAvailableTourDepList(idCompany, invoiceVO, tourTypeCode);
	}
	
	@Override
	public void updateDocumentAirfare(Long idTourDep) throws BusinessException {
		tourPkgService.updateDocumentAirfare(idTourDep);
	}
	
	@Override
	public List<TourPackageVO> getTourPkgListForCMS(Map<String, Object> params) throws BusinessException {
		return tourPkgService.getTourPkgListForCMS(params);
	}
	
	@Override
	public void setCruiseShowInInv(InvoiceItemVO invItemVO, BookingViewVO bookingViewVO,
			BookingChargeItemVO bookingChargeItemVO, CompanyVO companyVO) throws BusinessException {
		tourPkgService.setCruiseShowInInv(invItemVO, bookingViewVO, bookingChargeItemVO, companyVO);
	}
}
