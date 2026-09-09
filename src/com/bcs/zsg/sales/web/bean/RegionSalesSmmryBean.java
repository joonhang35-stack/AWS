

package com.bcs.zsg.sales.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.bo.TourCatBO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.TourCatViewVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.sales.bo.SalesSmmryBO;
import com.bcs.zsg.sales.vo.SalesSmmryVO;

public class RegionSalesSmmryBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient SalesSmmryBO salesSmmryBO;
	@Autowired
	protected transient TourCatBO tourCatBO;
	@Autowired
	protected transient TourPackageBO tourPkgBO;
	
	private LazyDataModel<SalesSmmryVO> salesSmmryList;
	protected List<TourCatViewVO> tourCatViewList;
	protected List<TourThemeVO> tourThemeList;
	private List<SalesSmmryVO> salesSmmryDetailList;
	private SearchParamVO sParamVO;
	private String smmryType;
	private String regionName;
	
	private TrackingLogUtils trackingLogUtils;
	
	@Override
	public void resetForm() {
		searchParamVO = new SearchParamVO();
		sParamVO = new SearchParamVO();
		salesSmmryDetailList =  new ArrayList<SalesSmmryVO>();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// set from date
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		searchParamVO.setFromDate(cal.getTime());
		// set to date
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		searchParamVO.setToDate(cal.getTime());
	}

	public void init(String type) throws BusinessException {
		trackingLogUtils = new TrackingLogUtils(this.getClass());
		
		initSearchParam();
		resetForm();
		
		smmryType = type;
		
		loadTourCatList();
		//loadSalesSmmry();
	}
	
	private void loadSalesSmmry() throws BusinessException {
		try {
			if (smmryType.equals("Inv")) {
				
				salesSmmryList = new LazyRegionSalesSmmryInvDataModel();
			} else if (smmryType.equals("Booking")) {
				salesSmmryList = new LazyRegionSalesSmmryBookingDataModel();
			}
			
		}catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Load Tour Category list
	 */
	public void loadTourCatList() {
		try {
			searchParamVO.setObj1(ProductConstant.TYPE_TOUR);
			searchParamVO.setCompanyVO(getSessionInfoBean().getCompanyVO());
			
			tourCatViewList = tourCatBO.getTourCatViewList(searchParamVO);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**********************
	 * Lazy loading model *
	 **********************/
	class LazyRegionSalesSmmryInvDataModel extends LazyDataModel<SalesSmmryVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<SalesSmmryVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				trackingLogUtils.startLogs();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("companyId", getSessionInfoBean().getCompanyVO().getId());
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				params.put("searchParam", searchParamVO);
				setRowCount(salesSmmryBO.getRegionSalesSmmryInvListSize(params));
				if (getRowCount() > 0){return salesSmmryBO.getRegionSalesSmmryInvList(params);}
				
			} catch (Throwable t) {
				errorResult(t);
			} finally {
				trackingLogUtils.endLogs("LazyRegionSalesSmmryInvDataModel");
			}
			
			return null;
		}
	}
	
	/**********************
	 * Lazy loading model *
	 **********************/
	class LazyRegionSalesSmmryBookingDataModel extends LazyDataModel<SalesSmmryVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<SalesSmmryVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				trackingLogUtils.startLogs();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("companyId", getSessionInfoBean().getCompanyVO().getId());
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				params.put("searchParam", searchParamVO);
				setRowCount(salesSmmryBO.getRegionSalesSmmryBookingListSize(params));
				if (getRowCount() > 0){return salesSmmryBO.getRegionSalesSmmryBookingList(params);}
				
			} catch (Throwable t) {
				errorResult(t);
			} finally {
				trackingLogUtils.endLogs("LazyRegionSalesSmmryBookingDataModel");
			}
			return null;
		}
	}
	
	/**
	 * Search function
	 */
	public void search() {
		try {
			sParamVO.setFromDate(searchParamVO.getFromDate());
			sParamVO.setToDate(searchParamVO.getToDate());
			
			final DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("salesSmmryForm:idSalesSmmryTable");
			d.setFirst(0);
			
			loadSalesSmmry();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	
	}
	
	/**
	 * Search function
	 */
	public void handleSalesSmmrySelect(SelectEvent event) {
		try {
			trackingLogUtils.startLogs();
			
			final DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("idSalesSmmryListForm:idSalesSmmryListTable");
			d.reset();
			
			SalesSmmryVO salesSmmryVO = (SalesSmmryVO) event.getObject();
			if (smmryType.equals("Inv")) {
				salesSmmryDetailList = salesSmmryBO.getRegionSalesSmmryInvDetailList(getSessionInfoBean().getCompanyVO().getId(), salesSmmryVO.getRegionId(), sParamVO);
			} else if (smmryType.equals("Booking")) {
				salesSmmryDetailList = salesSmmryBO.getRegionSalesSmmryBookingDetailList(getSessionInfoBean().getCompanyVO().getId(), salesSmmryVO.getRegionId(), sParamVO);
			}
			if (salesSmmryDetailList.size() > 0) {
				regionName = salesSmmryDetailList.get(0).getRegionName();
			}
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("loadRegionSalesSmmry");
		}
	
	}
	
	/**
	 * Handle tour category selection
	 */
	public void handleTourCatSelect() {
		try {
			tourThemeList = tourPkgBO.getTourThemeList(searchParamVO, 2);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public LazyDataModel<SalesSmmryVO> getSalesSmmryList() {
		return salesSmmryList;
	}

	public void setSalesSmmryList(LazyDataModel<SalesSmmryVO> salesSmmryList) {
		this.salesSmmryList = salesSmmryList;
	}

	public List<TourCatViewVO> getTourCatViewList() {
		return tourCatViewList;
	}

	public void setTourCatViewList(List<TourCatViewVO> tourCatViewList) {
		this.tourCatViewList = tourCatViewList;
	}
	
	public List<TourThemeVO> getTourThemeList() {
		return tourThemeList;
	}

	public void setTourThemeList(List<TourThemeVO> tourThemeList) {
		this.tourThemeList = tourThemeList;
	}

	public List<SalesSmmryVO> getSalesSmmryDetailList() {
		return salesSmmryDetailList;
	}

	public void setSalesSmmryDetailList(List<SalesSmmryVO> salesSmmryDetailList) {
		this.salesSmmryDetailList = salesSmmryDetailList;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
}

