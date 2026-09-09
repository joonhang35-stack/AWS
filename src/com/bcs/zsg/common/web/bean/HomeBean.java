package com.bcs.zsg.common.web.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.bo.HomeBO;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.sales.vo.BookingVO;
import com.bcs.zsg.sales.web.bean.BookingBean;

public class HomeBean extends BookingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient HomeBO homeBO;
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	protected transient TourPackageBO tourPkgBO;
	
	private CompanyVO companyVO;
	
	protected List<TourThemeVO> tourThemeList;
	protected List<TourPackageVO> tourPkgList;
	protected List<CountryVO> countryList;
	
	/**
	 * Initialization
	 */
	public void init() {
		try {
			if (getSessionInfoBean().getCompanyVO() == null || getSessionInfoBean().getCompanyVO().getId() == null) {
				// set employee info to session
				getSessionInfoBean().setEmployeeVO(homeBO.getEmployeeInfo(getSessionInfoBean().getUserVO().getUuid(), null));
				// set default company info to session
				companyVO = corporateProfileBO.getCompanyDetails(getSessionInfoBean().getEmployeeVO().getCompanyId());
				getSessionInfoBean().setCompanyVO(companyVO);
			}
			
			super.init(true);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			// set from date
			cal.add(Calendar.DAY_OF_MONTH, 7);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			searchParamVO.setFromDate(cal.getTime());
			// set to date
			cal.add(Calendar.MONTH, 2);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			searchParamVO.setToDate(cal.getTime());
			// temporary commented for login performance
			reloadTourDepList();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	/**
	 * Load tour departure
	 */
	public void reloadTourDepList() {
		trackingLogUtils.startLogs();
		try {
			searchParamVO.setObj10(getSessionInfoBean().getEmployeeVO().getId().toString());
			searchParamVO.setObj11(isB2BAgent);
			tourPkgList =  new ArrayList<TourPackageVO>();
			tourThemeList = tourPkgBO.getTourDepListWithPush(getSessionInfoBean().getCompanyVO(), searchParamVO);
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("reloadTourDepList");
		}
	}
	
	public void onChangeTab(TabChangeEvent event){
        
    }
	
	/**
	 * Handle tour departure selection
	 * @param vo
	 */
	public void handleTourDepSelect(TourDepartureVO vo) {
		try {
			super.handleTourDepSelect(vo);
			super.tourPkgVO = tourPkgBO.getTourPackageById(tourDepVO.getIdTourPkg());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Handle to show booking list
	 * @param tourDepVO
	 */
	public void handleShowBookingList(TourDepartureVO vo) {
		try {
			super.handleShowBookingList(vo);
			super.tourPkgVO = tourPkgBO.getTourPackageById(tourDepVO.getIdTourPkg());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param vo
	 */
	public void handleBookingSelect(BookingVO vo) {
		try {
			super.handleBookingSelect(vo);
			super.tourPkgVO = tourPkgBO.getTourPackageById(tourDepVO.getIdTourPkg());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Handle tour category selection
	 */
	public void handleTourCatSelect() {
		try {
			reloadTourDepList();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.sales.web.bean.BookingBean#resetForm()
	 */
	@Override
	public void resetForm() {}

	/*******************
	 * GETTER & SETTER *
	 *******************/
	
	/**
	 * @return the companyVO
	 */
	public CompanyVO getCompanyVO() {
		return companyVO;
	}

	/**
	 * @return the tourThemeList
	 */
	public List<TourThemeVO> getTourThemeList() {
		return tourThemeList;
	}

	/**
	 * @return the tourPkgList
	 */
	public List<TourPackageVO> getTourPkgList() {
		return tourPkgList;
	}

	/**
	 * @return the countryList
	 */
	public List<CountryVO> getCountryList() {
		return countryList;
	}
}
