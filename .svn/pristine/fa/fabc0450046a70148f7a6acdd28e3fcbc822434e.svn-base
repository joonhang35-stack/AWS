package com.bcs.zsg.sales.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.cfg.sec.bo.UserBO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.ListingTableViewBO;
import com.bcs.zsg.maintenance.helper.ConstantListingTableView;
import com.bcs.zsg.maintenance.vo.ListingTableViewColumnsVO;
import com.bcs.zsg.maintenance.vo.ListingTableViewVO;
//import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.product.bo.TourCatBO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.TourCatViewVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.sales.bo.BookingBO;
import com.bcs.zsg.sales.vo.BookingVO;
import com.bcs.zsg.sales.vo.CustDetailsVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class SalesSupportBean extends BookingBean {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected transient TourPackageBO tourPackageBO;
	@Autowired
	protected transient BookingBO bookingBO;
	@Autowired
	protected transient TourCatBO tourCatBO;
	@Autowired
	protected transient UserBO userBO;
	@Autowired
	private transient ListingTableViewBO listingTableViewBO;
	
	private LazyTourDepartureSalesSupportDataModel lazyTourDepSalesSuppDataModel;
	private List<TourDepartureVO> tourDepList;
	
	private TourDepartureVO searchTourDepFilter;
	private List<TourCatViewVO> tourCatViewList;
	private List<TourThemeVO> tourThemeList;
	private List<EmployeeViewVO> employeeList;
	private List<CountryVO> countryList;
	
	private Map<Integer, Boolean> listingTableViewMap;
	private ListingTableViewVO listingTableViewVO;
	
	private Date deadlineOfTourDep;
	
	// For Invoice references
	private String prefixValue;
	private InvoiceVO invRefVO;
	private AcctVO invRefAcctVO;
	private CustDetailsVO invRefCustDetailsVO;
	protected List<AcctViewVO> acctViewList;
	
	public void init() {
		try {
			trackingLogUtils = new TrackingLogUtils(this.getClass());
			
			resetForm();
			
			initSearchParam();
			
			SearchParamVO searchParamVO = new SearchParamVO();
			searchParamVO.setObj1(ProductConstant.TYPE_TOUR);
			searchParamVO.setCompanyVO(getSessionInfoBean().getCompanyVO());
			// get category list
			tourCatViewList = tourCatBO.getTourCatViewList(searchParamVO);
			
			// query employee list
			employeeList = userBO.getEmployeeViewList(getSessionInfoBean().getCompanyVO().getId(), null);
			
			// For Invoice references
			prefixValue = LookupItemUtils.getSysNumGenVO(getSessionInfoBean().getCompanyVO().getId(), CommonConstant.SYS_NUM_CD_INVC).getPrefixid();
//			SystemNumberGenerationVO invSNGVO = systemNumberGenerationBO.getSystemNumberGeneration(CommonConstant.SYS_NUM_CD_INVC, this.getSessionInfoBean().getCompanyVO().getId());
//			setPrefixValue(invSNGVO.getPrefixid());
			setCountryList(customerBO.getCountryList());
			
			loadListingTableView();
			
		} catch (Throwable t) {
			errorResult(t);
		}
		loadTourDepartureList();
	}
	
	@Override
	public void resetForm() {
		searchTourDepFilter = new TourDepartureVO();
	}
	
	public void saveTourDepartureSalesSupp(TourDepartureVO vo) {
		try {
			tourPackageBO.saveTourDepSalesSupp(vo);
			vo.setPicName(getUserName(vo.getIdPicUser()));
			vo.setAddEdit(false);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void editTourDep(TourDepartureVO vo) {
		if (vo.getIdPicUser() == null) {
			vo.setIdSupplier(vo.getIdPicUser()); // To store pic user for restore
			vo.setIdPicUser(this.getSessionInfoBean().getEmployeeVO().getId());
		}
		vo.setAddEdit(true);
	}
	
	
	public void cancelTourDep(TourDepartureVO vo) {
		if (vo.getIdSupplier() != null) {
			vo.setIdPicUser(vo.getIdSupplier());
			vo.setPicName(getUserName(vo.getIdPicUser()));
			vo.setIdSupplier(null);
		}
		vo.setAddEdit(false);
	}
	
	/**
	 * Get staff name
	 */
	public String getUserName(Long id) {
		try {
			if (id != null) {
				for (EmployeeViewVO vo : employeeList) {
					if (vo.getId().longValue() == id.longValue()) {
						return vo.getUserVO().getName();
					}
				}
			}
		} catch (Throwable t) {
			errorResult(t);
		}
		return "";
	}
	
	public void updateListingTableView() {
		try {
			listingTableViewBO.updateListingTableView(listingTableViewVO);
			
			loadListingTableView();
			successResult();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void loadListingTableView() {
		listingTableViewMap = new HashMap<>();
		try {
			listingTableViewVO = listingTableViewBO.getListingTableView(ConstantListingTableView.LISTING_TYPE_SALES_SUPP, getSessionInfo().getUserVO().getId());
			
			if (listingTableViewVO == null) {
				listingTableViewVO = new ListingTableViewVO();
				listingTableViewVO.setIdUser(getSessionInfo().getUserVO().getId());
				listingTableViewVO.setListingType(ConstantListingTableView.LISTING_TYPE_SALES_SUPP);
				
				listingTableViewVO.setListingTableViewColumnsVOList(new ArrayList<ListingTableViewColumnsVO>()); 
			}
			
			for (ListingTableViewColumnsVO columnsVO : listingTableViewVO.getListingTableViewColumnsVOList()) {
				listingTableViewMap.put(columnsVO.getSeqNo(), columnsVO.getVisible());
			}
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void handleChangeColumnsViewClick() {
		// Get Data Table Columns
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("idForm:idFilterTourDepList");
		List<UIColumn> column = dataTable.getColumns();
		int index = 1;
		List<ListingTableViewColumnsVO> columnList = new ArrayList<>();
		for (UIColumn uiColumn : column) {
			boolean isExists = false;
			for (ListingTableViewColumnsVO columnsVO : listingTableViewVO.getListingTableViewColumnsVOList()) {
				if (StringUtils.equals(columnsVO.getColumnsName(), uiColumn.getHeaderText())) {
					columnsVO.setSeqNo(index);
					
					columnList.add(columnsVO);
					isExists = true;
					break;
				}
			}
			
			if (!isExists) {
				ListingTableViewColumnsVO columnsVO = new ListingTableViewColumnsVO();
				columnsVO.setColumnsName(uiColumn.getHeaderText());
				columnsVO.setSeqNo(index);
				columnsVO.setVisible(true);
				columnList.add(index - 1, columnsVO);
			}
			index++;
		} 
		
		listingTableViewVO.setListingTableViewColumnsVOList(columnList);
	}
	
	public void loadTourDepartureList() {
		try {
			trackingLogUtils.startLogs();
			
			searchTourDepFilter.setIdCompany(this.getSessionInfoBean().getCompanyVO().getId());
			searchTourDepFilter.setIdPicUser(this.getSessionInfoBean().getEmployeeVO().getId()); // For filter personal use
			tourDepList = tourPackageBO.getTourDepSalesSuppList(searchTourDepFilter, employeeList);
			
			UIViewRoot uIViewRoot = FacesContext.getCurrentInstance().getViewRoot();
			if (uIViewRoot != null) {
				DataTable dataTable = (DataTable) uIViewRoot.findComponent("idForm:idFilterTourDepList");
				dataTable.reset();
				dataTable.setValueExpression("sortBy", null);
			}
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("SalesSupportBean.loadTourDepartureList");
		}
	}
	
	/**
	 * Handle tour category selection
	 */
	public void handleTourCatSelect() {
		try {
//			if (searchTourDepFilter.getIdTourCat() != null) {
//				searchParamVO.setObj2(Long.toString(searchTourDepFilter.getIdTourCat()));
//				tourThemeList = tourPackageBO.getTourThemeList(searchParamVO, 2);
//			}
			if (CollectionUtils.isNotEmpty(searchTourDepFilter.getIdTourCatList())) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("idTourCatList", searchTourDepFilter.getIdTourCatList());
				
				if (searchTourDepFilter.getIdTourCatList().size() > 0)
					tourThemeList = tourPackageBO.getTourThemeList(params);
				else
					tourThemeList = new ArrayList<TourThemeVO>();
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void handleFilterClearAll() {
		try {
			searchTourDepFilter.setIdTourCat(null);
			searchTourDepFilter.setIdTourCatList(new ArrayList<Long>()); // Tour Theme
			searchTourDepFilter.setSectorList(new ArrayList<String>()); // Tour Theme
			searchTourDepFilter.setCode(null);
			searchTourDepFilter.setPackageDesc(null);
			searchTourDepFilter.setFromDate(null);
			searchTourDepFilter.setToDate(null);
			searchTourDepFilter.setTourStatusList(new ArrayList<String>());
			searchTourDepFilter.setNumDays(null); // Days till Departure
			searchTourDepFilter.setQuantity(null);
			searchTourDepFilter.setFullPmntStatus(null);
			searchTourDepFilter.setPassportStatus(null);
			searchTourDepFilter.setVisaStatus(null);
			searchTourDepFilter.setPicName(null);
			searchTourDepFilter.setIdPicSearch(null);
			searchTourDepFilter.setPersonalRecord(false); // Personal
			searchTourDepFilter.setInactiveRecord(false); // Show Archive
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleShowBookingListFromSearch(TourDepartureVO vo) {
//		Calendar calendar = DatesUtils.getPreviousDate(vo.getDtDep(), vo.getDeadline());
//		deadlineOfTourDep = calendar.getTime();
		deadlineOfTourDep = vo.getDtDeadline();
		super.handleShowBookingListFromSearch(vo);
	}
	
	public void onCopyClick() {  
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Details copied!",  null);  
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	/**
	 * Get country name
	 */
	public String getCountryName(Long id) {
		try {
			if (CollectionUtils.isNotEmpty(countryList)) {
				for (CountryVO vo : countryList){
					if (vo.getId().equals(id)) {
						return vo.getCountry();
					}
				}
			}
		} catch (Throwable t) {
			errorResult(t);
		}

		return "";
	}
	
	/**
	 * Get account code
	 */
	public String getAcctCd(Long id) {
		try {
			for (AcctViewVO vo : acctViewList){
				if (vo.getId().longValue() == id.longValue()) {
					if (vo.getSubCode().equals("")) {
						return vo.getCode();
					} else {
						return vo.getCode() + "-" + vo.getSubCode();
					}
				}
			}

		} catch (Throwable t) {
			errorResult(t);
		}

		return "";
	}
	
	public String getAcctDesc(Long id) {
		try {
			for (AcctViewVO vo : acctViewList){
				if (vo.getId().longValue() == id.longValue()) {
					if (vo.getSubCode().equals("")) {
						return vo.getDesc();
					} else {
						return vo.getDesc() + "-" + vo.getSubDesc();
					}
				}
			}

		} catch (Throwable t) {
			errorResult(t);
		}

		return "";
	}
	
	public LazyTourDepartureSalesSupportDataModel getLazyTourDepSalesSuppDataModel() {
		return lazyTourDepSalesSuppDataModel;
	}
	
	public List<TourDepartureVO> getTourDepList() {
		return tourDepList;
	}
	
	public TourDepartureVO getSearchTourDepFilter() {
		return searchTourDepFilter;
	}
	
	public List<TourCatViewVO> getTourCatViewList() {
		return tourCatViewList;
	}

	public List<TourThemeVO> getTourThemeList() {
		return tourThemeList;
	}
	
	public List<EmployeeViewVO> getEmployeeList() {
		return employeeList;
	}
	
	public List<BookingVO> getBookingList() {
		return bookingList;
	}
	
	public BookingVO getBookingVO() {
		return bookingVO;
	}
	
	public ListingTableViewVO getListingTableViewVO() {
		return listingTableViewVO;
	}

	public void setListingTableViewVO(ListingTableViewVO listingTableViewVO) {
		this.listingTableViewVO = listingTableViewVO;
	}
	
	public Map<Integer, Boolean> getListingTableViewMap() {
		return listingTableViewMap;
	}

	public void setListingTableViewMap(Map<Integer, Boolean> listingTableViewMap) {
		this.listingTableViewMap = listingTableViewMap;
	}
	
	public Date getDeadlineOfTourDep() {
		return deadlineOfTourDep;
	}

	public void setDeadlineOfTourDep(Date deadlineOfTourDep) {
		this.deadlineOfTourDep = deadlineOfTourDep;
	}
	
	public InvoiceVO getInvRefVO() {
		return invRefVO;
	}

	public void setInvRefVO(InvoiceVO invRefVO) {
		this.invRefVO = invRefVO;
	}

	public AcctVO getInvRefAcctVO() {
		return invRefAcctVO;
	}

	public void setInvRefAcctVO(AcctVO invRefAcctVO) {
		this.invRefAcctVO = invRefAcctVO;
	}

	public CustDetailsVO getInvRefCustDetailsVO() {
		return invRefCustDetailsVO;
	}

	public void setInvRefCustDetailsVO(CustDetailsVO invRefCustDetailsVO) {
		this.invRefCustDetailsVO = invRefCustDetailsVO;
	}
	
	public String getPrefixValue() {
		return prefixValue;
	}

	public void setPrefixValue(String prefixValue) {
		this.prefixValue = prefixValue;
	}

	class LazyTourDepartureSalesSupportDataModel extends LazyDataModel<TourDepartureVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		private List<TourDepartureVO> tourDepList;
		
		@Override
		public List<TourDepartureVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			List<TourDepartureVO> list = new ArrayList<TourDepartureVO>();
			int rowCount = 0;
			
			if (CollectionUtils.isNotEmpty(tourDepList)) {
				String filterCode = filters.get("code");
				boolean isCode = true;
				
				for (int i = 0; i < tourDepList.size(); i++) {
					try {
						TourDepartureVO vo = tourDepList.get(i);
						
						if (!filters.isEmpty()) {
							isCode = true;
							if (filterCode != null && !vo.getCode().startsWith(filterCode)) isCode = false;
							
							if (isCode) {
								if (rowCount >= first && list.size() < pageSize) {
									
									list.add(vo);
								}
								++rowCount;
							}
						} else {
							if (i >= first && list.size() < pageSize) list.add(vo);
							++rowCount;
						}
					} catch (Exception e) {
						break;
					}
				}
			}
			setRowCount(rowCount);
			return list;
		}

		public void setTourDepList(List<TourDepartureVO> tourDepList) {
			this.tourDepList = tourDepList;
		}
		
	}
}
