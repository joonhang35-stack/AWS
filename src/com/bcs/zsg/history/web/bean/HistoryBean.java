package com.bcs.zsg.history.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.FunctionCDConstant;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.common.helper.LookupUtils;
import com.bcs.zsg.component.security.bo.SecurityBO;
import com.bcs.zsg.component.security.helper.SecurityConstant;
import com.bcs.zsg.component.security.vo.RoleFunctionViewVO;
import com.bcs.zsg.component.security.vo.UserRoleViewVO;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.history.bo.HistoryBO;
import com.bcs.zsg.history.vo.InvoiceHistoryViewVO;
import com.bcs.zsg.history.vo.TourDepHistoryViewAllVO;
import com.bcs.zsg.history.vo.TourDepHistoryViewVO;
import com.bcs.zsg.history.vo.TourPkgAllHistoryViewVO;
import com.bcs.zsg.history.vo.TourPkgCurHistoryViewVO;
import com.bcs.zsg.component.common.vo.RefDataVO;

public class HistoryBean extends AppBackingBean {

	private static final long serialVersionUID = 1L;

	@Autowired
	private transient HistoryBO historyBO;
	@Autowired
	private transient SecurityBO securityBO;
	
	protected List<TourPkgCurHistoryViewVO> tourPkgHistoryViewList;
	protected List<TourPkgAllHistoryViewVO> tourPkgHistorySelectList;
	
	protected List<TourDepHistoryViewAllVO> tourDepHistoryViewAllList;
	
	protected List<InvoiceHistoryViewVO> invoiceHistoryViewAllList;
	protected InvoiceHistoryViewVO invoiceHistoryViewVO;
	
	private List<RefDataVO> categoryList;

	private LazyDataModel<InvoiceHistoryViewVO> lazyDMInvoiceHistory;
	private LazyDataModel<TourDepHistoryViewVO> lazyDMTourDepHistory;

    private SelectItem[] actionCdOptions;
    private SelectItem[] invoiceStatusOptions;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		//tourDepHistoryViewList = new ArrayList<TourDepHistoryViewVO>();
		tourDepHistoryViewAllList = new ArrayList<TourDepHistoryViewAllVO>();
		
		//invoiceHistoryViewList = new ArrayList<InvoiceHistoryViewVO>();
		invoiceHistoryViewAllList = new ArrayList<InvoiceHistoryViewVO>();
		invoiceHistoryViewVO = new InvoiceHistoryViewVO();
	}

	/**
	 * Initialization
	 */
	public void init() {
		try {
			
			// initialized search param
			initSearchParam();
			
			// initialized roles authority for categories
			initCategories();
			
			// set today's date
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			searchParamVO.setFromDate(cal.getTime());
			
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			searchParamVO.setToDate(cal.getTime());
			// get history list
			//historyList(getSessionInfoBean().getUserVO());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	@SuppressWarnings("unchecked")
	public void initCategories() {
		try {

			List<RefDataVO> refDataList = LookupUtils.getReferenceDataList(CommonConstant.AUDIT_TRL_CAT);
			List<String> strList = new ArrayList<String>();
			categoryList = new ArrayList<RefDataVO>();
			
			List<UserRoleViewVO> userRoleList = getSessionInfoBean().getUserVO().getRoleList();
			
			boolean isSU = false;
			
			for (UserRoleViewVO vo : userRoleList) {
				if (vo.getRoleCode().equals(SecurityConstant.ROLE_CD_SUPER_USER)) {
					isSU = true;
					break;
				}
			}
			
			if (isSU) {
				categoryList = refDataList;
			} else {
				for (UserRoleViewVO userRoleVO : userRoleList) {
					List<RoleFunctionViewVO> roleFunctionList = securityBO.getRoleFunctionList(userRoleVO.getRoleUUID());
					
					for (RoleFunctionViewVO roleFuncVO : roleFunctionList) {
						if (FunctionCDConstant.PRODUCT_TOUR.equals(roleFuncVO.getFunctionCode())) {
							strList.add(CommonConstant.AUDIT_TRL_CAT_TOUR_DEP);
						} else if (FunctionCDConstant.PRODUCT_FNE.equals(roleFuncVO.getFunctionCode())) {
							
						} else if (FunctionCDConstant.SALES_INV.equals(roleFuncVO.getFunctionCode())) {
							strList.add(CommonConstant.AUDIT_TRL_CAT_INV);
						}
					}
				}
				
				for (RefDataVO refDataVO : refDataList) {
					for (String strVO : strList) {
						if (refDataVO.getCode().equals(strVO)) {
							categoryList.add(refDataVO);
						}
					}
				}
				
				HashSet<RefDataVO> listToSet = new HashSet<RefDataVO>(categoryList);
				categoryList = new ArrayList<RefDataVO>(listToSet);
			}
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param userVO
	 * @throws Exception
	 */
	public void historyList(UserVO userVO) throws Exception {
		resetForm();

		if (((String) searchParamVO.getObj1()).equals(CommonConstant.AUDIT_TRL_CAT_INV)) {
			setLazyDMTourDepHistory(null);
			
			if(getLazyDMInvoiceHistory() == null)
				setLazyDMInvoiceHistory(new LazyHistoryInvoiceDataModel());
		} else if (((String) searchParamVO.getObj1()).equals(CommonConstant.AUDIT_TRL_CAT_TOUR_DEP)) {
			setLazyDMInvoiceHistory(null);
			
			if(getLazyDMTourDepHistory() == null)
				setLazyDMTourDepHistory(new LazyHistoryTourDepDataModel());
		} else {
			setLazyDMTourDepHistory(null);
			setLazyDMInvoiceHistory(null);
		}
	}
	
	/**
	 * History search
	 */
	public void search() {
		try {
			
			Calendar tempCal = Calendar.getInstance();
			tempCal.setTime(searchParamVO.getToDate());
			tempCal.set(Calendar.HOUR_OF_DAY, 23);
			tempCal.set(Calendar.MINUTE, 59);
			tempCal.set(Calendar.SECOND, 59);
			searchParamVO.setToDate(tempCal.getTime());
			historyList(getSessionInfoBean().getUserVO());
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	public void handleTourPkgHistorySelect(SelectEvent event) {
		try {
			TourPkgCurHistoryViewVO currentHistoryVO = (TourPkgCurHistoryViewVO) event.getObject();
			tourPkgHistorySelectList = historyBO.getTourPkgHistoryViewListByIdHist(currentHistoryVO.getIdHist());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	public void handleTourDepHistorySelect(SelectEvent event) {
		try {
			tourDepHistoryViewAllList = historyBO.getTourDepHistoryViewListById(((TourDepHistoryViewVO) event.getObject()).getIdHist());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	public void handleInvoiceHistorySelect(SelectEvent event) {
		try {
			invoiceHistoryViewAllList = historyBO.getInvoiceHistoryViewListById(((InvoiceHistoryViewVO) event.getObject()).getIdHist());
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	public void handleInvoiceHistoryAllSelect(SelectEvent event) {
		try {
			invoiceHistoryViewVO = (InvoiceHistoryViewVO) event.getObject();
			invoiceHistoryViewVO = historyBO.getInvoiceHistoryViewDetailById(invoiceHistoryViewVO.getId());
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	

	/**********************
	 * Lazy loading model *
	 **********************/
	class LazyHistoryInvoiceDataModel extends LazyDataModel<InvoiceHistoryViewVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<InvoiceHistoryViewVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				Map<String, Object> params = historyBO.generateHistoryViewParam(generateCommonLMData(first, pageSize, sortField, sortOrder, filters),
						InvoiceHistoryViewVO.class, getSessionInfoBean().getCompanyVO().getId(), searchParamVO);
				
				setRowCount(historyBO.getHistoryListSize(params));
				
				if (getRowCount() > 0) 
					return (List<InvoiceHistoryViewVO>) historyBO.getHistoryViewList(params);
				
			} catch (Throwable t) {
				t.printStackTrace();
				errorResult(t);
			}
			return null;
		}
		
	}

	class LazyHistoryTourDepDataModel extends LazyDataModel<TourDepHistoryViewVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<TourDepHistoryViewVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				Map<String, Object> params = historyBO.generateHistoryViewParam(generateCommonLMData(first, pageSize, sortField, sortOrder, filters),
						TourDepHistoryViewVO.class, getSessionInfoBean().getCompanyVO().getId(), searchParamVO);
				
				setRowCount(historyBO.getHistoryListSize(params));
				if (getRowCount() > 0) {
					return (List<TourDepHistoryViewVO>) historyBO.getHistoryViewList(params);
				}
			} catch (Throwable t) {
				t.printStackTrace();
				errorResult(t);
			}
			return null;
		}
		
	}
	
	private Map<String, Object> generateCommonLMData(int first, int pageSize, String sortField, SortOrder sortOrder, 
				Map<String, String> filters) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("first", first);
		params.put("pageSize", pageSize);
		params.put("sortField", sortField);
		params.put("sortOrder", sortOrder);
		params.put("filters", new HashMap<String, String>(filters));
		
		return params;
	}
	
	private SelectItem[] createFilterOptions(String[] data)  {  
        SelectItem[] options = new SelectItem[data.length + 1];  
  
        options[0] = new SelectItem("", "Select");  
        for(int i = 0; i < data.length; i++) {  
            options[i + 1] = new SelectItem(data[i], data[i]);  
        }  
  
        return options;  
    }
	
	/*******************
	 * GETTER & SETTER *
	 *******************/
	
	/**
	 * @return the tourPkgHistoryList
	 */
	public List<TourPkgCurHistoryViewVO> getTourPkgHistoryViewList() {
		return tourPkgHistoryViewList;
	}

	/**
	 * @return the tourPkgHistorySelectList
	 */
	public List<TourPkgAllHistoryViewVO> getTourPkgHistorySelectList() {
		return tourPkgHistorySelectList;
	}
	
	/**
	 * @return the tourDepHistoryViewAllList
	 */
	public List<TourDepHistoryViewAllVO> getTourDepHistoryViewAllList() {
		return tourDepHistoryViewAllList;
	}

	/**
	 * @return the categoryList
	 */
	public List<RefDataVO> getCategoryList() {
		return categoryList;
	}

	/**
	 * @param categoryList the categoryList to set
	 */
	public void setCategoryList(List<RefDataVO> categoryList) {
		this.categoryList = categoryList;
	}
	
	/**
	 * @return the invoiceHistoryViewAllList
	 */
	public List<InvoiceHistoryViewVO> getInvoiceHistoryViewAllList() {
		return invoiceHistoryViewAllList;
	}

	/**
	 * @param invoiceHistoryViewAllList the invoiceHistoryViewAllList to set
	 */
	public void setInvoiceHistoryViewAllList(
			List<InvoiceHistoryViewVO> invoiceHistoryViewAllList) {
		this.invoiceHistoryViewAllList = invoiceHistoryViewAllList;
	}

	/**
	 * @return the invoiceHistoryViewVO
	 */
	public InvoiceHistoryViewVO getInvoiceHistoryViewVO() {
		return invoiceHistoryViewVO;
	}

	/**
	 * @param invoiceHistoryViewVO the invoiceHistoryViewVO to set
	 */
	public void setInvoiceHistoryViewVO(InvoiceHistoryViewVO invoiceHistoryViewVO) {
		this.invoiceHistoryViewVO = invoiceHistoryViewVO;
	}

	public LazyDataModel<TourDepHistoryViewVO> getLazyDMTourDepHistory() {
		return lazyDMTourDepHistory;
	}

	public void setLazyDMTourDepHistory(LazyDataModel<TourDepHistoryViewVO> lazyDMTourDepHistory) {
		this.lazyDMTourDepHistory = lazyDMTourDepHistory;
	}

	public LazyDataModel<InvoiceHistoryViewVO> getLazyDMInvoiceHistory() {
		return lazyDMInvoiceHistory;
	}

	public void setLazyDMInvoiceHistory(LazyDataModel<InvoiceHistoryViewVO> lazyDMInvoiceHistory) {
		this.lazyDMInvoiceHistory = lazyDMInvoiceHistory;
	}
	
	public SelectItem[] getActionCodeOptions() {
		if(actionCdOptions == null)
			actionCdOptions = createFilterOptions(historyBO.getRefDataListValue(CommonConstant.ACT_TYPE_CD));
		return actionCdOptions;
	}
	
	public SelectItem[] getInvoiceStatusOptions() {
		if(invoiceStatusOptions == null)
			invoiceStatusOptions = createFilterOptions(historyBO.getRefDataListValue(CommonConstant.INV_STATUS));
		return invoiceStatusOptions;
	}
}
