

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

import com.bcs.zsg.cfg.sec.bo.UserBO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.bo.SalesSmmryBO;
import com.bcs.zsg.sales.vo.SalesSmmryVO;

public class StaffSalesSmmryBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient SalesSmmryBO salesSmmryBO;
	@Autowired
	private transient UserBO userBO;
	
	private LazyDataModel<SalesSmmryVO> salesSmmryList;
	private List<EmployeeViewVO> employeeList;
	private List<SalesSmmryVO> salesSmmryDetailList;
	private SearchParamVO sParamVO;
	private String smmryType;
	private String salerName;
	
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
		
		loadEmployeeList();
		loadSalesSmmry();
	}
	
	private void loadSalesSmmry() throws BusinessException {
		try {
			if (smmryType.equals("Inv")) {
				salesSmmryList = new LazySalesSmmryInvDataModel();
			} else if (smmryType.equals("Booking")) {
				salesSmmryList = new LazySalesSmmryBookingDataModel();
			}
			
		}catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Load Employee list
	 */
	public void loadEmployeeList() {
		try {
			employeeList = userBO.getEmployeeViewList(this.getSessionInfoBean().getCompanyVO().getId());
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	
	/**********************
	 * Lazy loading model *
	 **********************/
	class LazySalesSmmryInvDataModel extends LazyDataModel<SalesSmmryVO> implements Serializable {
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
				setRowCount(salesSmmryBO.getStaffSalesSmmryInvListSize(params));
				if (getRowCount() > 0){return salesSmmryBO.getStaffSalesSmmryInvList(params);}
				
			} catch (Throwable t) {
				errorResult(t);
			} finally {
				trackingLogUtils.endLogs("LazySalesSmmryInvDataModel");
			}
			return null;
		}
	}
	
	/**********************
	 * Lazy loading model *
	 **********************/
	class LazySalesSmmryBookingDataModel extends LazyDataModel<SalesSmmryVO> implements Serializable {
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
				setRowCount(salesSmmryBO.getStaffSalesSmmryBookingListSize(params));
				if (getRowCount() > 0){return salesSmmryBO.getStaffSalesSmmryBookingList(params);}
				
			} catch (Throwable t) {
				errorResult(t);
			} finally {
				trackingLogUtils.endLogs("LazySalesSmmryBookingDataModel");
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
			
			searchParamVO.setObj2("search");
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
				salesSmmryDetailList = salesSmmryBO.getStaffSalesSmmryInvDetailList(getSessionInfoBean().getCompanyVO().getId(), salesSmmryVO.getSalerId(), sParamVO);
			} else if (smmryType.equals("Booking")) {
				salesSmmryDetailList = salesSmmryBO.getStaffSalesSmmryBookingDetailList(getSessionInfoBean().getCompanyVO().getId(), salesSmmryVO.getSalerId(), sParamVO);
			}
			if (salesSmmryDetailList.size() > 0) {
				salerName = salesSmmryDetailList.get(0).getSalerName();
			}
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("handleSalesSmmrySelect");
		}
	}
	
	public LazyDataModel<SalesSmmryVO> getSalesSmmryList() {
		return salesSmmryList;
	}

	public void setSalesSmmryList(LazyDataModel<SalesSmmryVO> salesSmmryList) {
		this.salesSmmryList = salesSmmryList;
	}

	public List<EmployeeViewVO> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeViewVO> employeeList) {
		this.employeeList = employeeList;
	}

	public List<SalesSmmryVO> getSalesSmmryDetailList() {
		return salesSmmryDetailList;
	}

	public void setSalesSmmryDetailList(List<SalesSmmryVO> salesSmmryDetailList) {
		this.salesSmmryDetailList = salesSmmryDetailList;
	}

	public String getSalerName() {
		return salerName;
	}

	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}
}

