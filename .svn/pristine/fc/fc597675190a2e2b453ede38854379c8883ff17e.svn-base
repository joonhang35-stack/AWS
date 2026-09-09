package com.bcs.zsg.maintenance.web.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.CollectionUtils;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.bo.CustomerProfileUpdateBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.SupplierProfileUpdateVO;
import com.bcs.zsg.purchase.bo.PurchaseBO;
import com.bcs.zsg.purchase.vo.SupplierVO;

public class SupplierDataExportBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected transient RegionBO regionBO;
	@Autowired
	protected transient PurchaseBO purchaseBO;
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	private transient CustomerProfileUpdateBO customerProfileUpdateBO;
	
	private LazyDataModel<SupplierVO> lazySupplDataModel;
	
	protected TrackingLogUtils trackingLogUtils;
	
	private List<SupplierVO> selectedSupplList;
	private List<String> columnList;
	private List<SupplierProfileUpdateVO> supplierProfileUpdateList;
	private List<String> unableSentEmailSupplList;

	@Override
	public void resetForm() {
		selectedSupplList = new ArrayList<SupplierVO>();
		supplierProfileUpdateList = new ArrayList<SupplierProfileUpdateVO>();
		unableSentEmailSupplList = new ArrayList<String>();
	}
	
	public void init() {
		try {
			searchParamVO = new SearchParamVO();
			searchParamVO.setObj1("CSV");
			searchParamVO.setObj2("");
			
			trackingLogUtils = new TrackingLogUtils(this.getClass());
			
			loadSupplier();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public String getIdentityStr(String s) {
		return LookupItemUtils.getLookupItemDesc(CommonConstant.LOOKUP_CAT_CD_ID_TYPE, s.substring(0, s.indexOf("|"))) + ": " + s.substring(s.indexOf("|") + 1);
	}
	
	public String getContactStr(String s) {
		return LookupItemUtils.getLookupItemDesc(CommonConstant.LOOKUP_CAT_CD_CNTC_TYPE, s.substring(0, s.indexOf("|"))) + ": " + s.substring(s.indexOf("|") + 1);
	}
	
	/**
	 * Load supplier list
	 * @throws BusinessException
	 */
	private void loadSupplier() throws BusinessException {
		resetForm();
		lazySupplDataModel = new LazySupplierDataModel();
	}
	
	/**
	 * Printing Supplier Listing Report
	 */
	public void printSupplierListing() {
		try {
			trackingLogUtils.startLogs();
			
			String xlsOrCsv = (String) searchParamVO.getObj1();
			
			if (searchParamVO.getFromDate() == null && searchParamVO.getToDate() == null) {
				if (selectedSupplList.size() == 0) throw new BusinessException("ERR_NO_RECORD_SELECTED");
			}
			
			HashMap<String, Object> map = reportTitle();
			
			if (searchParamVO.getFromDate() == null && searchParamVO.getToDate() == null) {
				StringBuilder supplIDs = new StringBuilder();
				for (SupplierVO vo : selectedSupplList) {
					if (supplIDs.length() > 0) supplIDs.append(",").append(vo.getId());
					else supplIDs.append(vo.getId());
				}
				
				// select customer
				searchParamVO.setObj7(supplIDs.toString());
			}

			List<SupplierVO> list = (List<SupplierVO>) purchaseBO.getSupplierList(getSessionInfoBean().getCompanyVO().getId(), searchParamVO);
			map.put("eplList", list);
			getShowColumns(map, columnList, searchParamVO.getObj2().toString());
			
			if (CollectionUtils.isEmpty(list)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			
			String jasperFileName = CommonConstant.JAS_RPT_SUPPL_DATA_EXPORT;
			String reportName = CommonConstant.PDF_RPT_SUPPL_DATA_EXPORT;
			
			
			if (xlsOrCsv.equals("CSV")) {
				List<Map<String, String>> reportMap = new ArrayList<>();
				reportMap = getSupplierMap(list, map, (String) searchParamVO.getObj2());
				ReportUtils.printReportCSV(reportMap, reportName);
			}
//			else {
//				JasperPrint jasperPrint = ReportUtils.getJasperPrint(list, map, jasperFileName);
//				ReportUtils.printReportExcel(jasperPrint, reportName);
//			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printSupplierListing");
		}
	}
	
	/**
	 * Prepare Report Title
	 */
	public HashMap<String, Object> reportTitle() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			CompanyVO companyVO = new CompanyVO();
			companyVO = corporateProfileBO.getCompanyDetails(getSessionInfoBean().getCompanyVO().getId());

			StringBuilder address = new StringBuilder();
			map.put("companyName", companyVO.getName());
			map.put("slogan", companyVO.getSlogan());

			if (!companyVO.getCompanyAddressVO().getAddress1().equals(""))
				address.append(companyVO.getCompanyAddressVO().getAddress1())
						.append(" ");
			if (!companyVO.getCompanyAddressVO().getAddress2().equals(""))
				address.append(companyVO.getCompanyAddressVO().getAddress2())
						.append(" ");
			if (!companyVO.getCompanyAddressVO().getAddress3().equals(""))
				address.append(companyVO.getCompanyAddressVO().getAddress3())
						.append(" ");
			if (!companyVO.getCompanyAddressVO().getCity().equals(""))
				address.append(companyVO.getCompanyAddressVO().getCity())
						.append(" ");
			if (!companyVO.getCompanyAddressVO().getState().equals(""))
				address.append(companyVO.getCompanyAddressVO().getState())
						.append(" ");
			if (!companyVO.getCompanyAddressVO().getPostcode().equals(""))
				address.append(companyVO.getCompanyAddressVO().getPostcode())
						.append(" ");
			String companyName = regionBO.getCountryById(
					companyVO.getCompanyAddressVO().getCountryid())
					.getCountry();
			if (!companyName.equals(""))
				address.append(companyName);
			map.put("address", address);

			String contact = "";
			for (CompanyContactVO vo : companyVO.getCompanyContactList()) {
				contact += LookupItemUtils.getLookupItemDesc(
						CommonConstant.LOOKUP_CAT_CD_CNTC_TYPE,
						vo.getTypecodecontact())
						+ ": " + vo.getNumber() + "  ";
			}
			if (!contact.equals(""))
				contact = "Tel: " + contact;
			map.put("contact", contact);

		} catch (Throwable t) {
			errorResult(t);
		}
		return map;
	}
	
	public void getShowColumns(HashMap<String, Object> map, List<String> columnList, String type) {
		map.put("Registrationno", false);
		map.put("GSTregistrationno", false);
		map.put("Email", false);
		map.put("Contact", false);
		map.put("Address", false);
		
		for (String str : columnList) {
			if ("Registrationno".equals(str)) map.put("Registrationno", true);
			if ("GSTregistrationno".equals(str)) map.put("GSTregistrationno", true);
			if ("Email".equals(str)) map.put("Email", true);
			if ("Contact".equals(str)) map.put("Contact", true);
			if ("Address".equals(str)) map.put("Address", true);
		}
	}
	

	public List<Map<String, String>> getSupplierMap(List<SupplierVO> supplMap, HashMap<String, Object> columns, String type) {
		List<Map<String, String>> supplList = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");

		map.put("A", "Supplier No");
		map.put("B", "Supplier Type");
		map.put("C", "Supplier Name");
		if ((boolean) columns.get("Registrationno")) map.put("D", "Registration No");
		if ((boolean) columns.get("GSTregistrationno")) map.put("E", "GST Registration No");
		if ((boolean) columns.get("Email")) map.put("F", "Email");
		if ((boolean) columns.get("Contact")) map.put("G", "Contact");
		if ((boolean) columns.get("Address")) map.put("H", "Address");
		supplList.add(map);

		for (SupplierVO vo : supplMap) {
			map = new HashMap<>();
			map.put("A", escapeSpecialCharacters(vo.getSysNo()));
			map.put("B", escapeSpecialCharacters(vo.getSupplierType()));
			map.put("C", escapeSpecialCharacters(vo.getFullName()));
			if ((boolean) columns.get("Registrationno")) map.put("D", escapeSpecialCharacters(vo.getRegNo()));
			if ((boolean) columns.get("GSTregistrationno")) map.put("E", escapeSpecialCharacters(vo.getGstRegNo()));
			if ((boolean) columns.get("Email")) map.put("F", escapeSpecialCharacters(vo.getEmail()));
			if ((boolean) columns.get("Contact")) map.put("G", escapeSpecialCharacters(vo.getContactNo()));
			if ((boolean) columns.get("Address")) map.put("H", escapeSpecialCharacters(vo.getAdd1()));
			supplList.add(map);
		}
		return supplList;
	}
	
	public void processEmailSupplierProfileUpdate() {
		try {
			if (CollectionUtils.isNotEmpty(supplierProfileUpdateList)) {
				customerProfileUpdateBO.updateSupplierProfiles(supplierProfileUpdateList, getSessionInfo().getUserVO());
				// Show warning if there are suppliers without email
		        if (CollectionUtils.isNotEmpty(unableSentEmailSupplList)) {
		            String unableToSendMessage = "Unable to send emails to the following suppliers: " + 
		                String.join(", ", unableSentEmailSupplList);
		            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, unableToSendMessage, unableToSendMessage);
		            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		        } else {
		        	successResult();
		        }
			}
			resetForm();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
		
	}
	
	public void supplierEmailProfileUpdate() {
		if (CollectionUtils.isNotEmpty(selectedSupplList)) {
			for (SupplierVO selectedSupplierVO : selectedSupplList) {
				if (StringUtils.isNotBlank(selectedSupplierVO.getEmail())) {
					SupplierProfileUpdateVO customerProfileUpdateVO = new SupplierProfileUpdateVO();
					customerProfileUpdateVO.setIdSupplier(selectedSupplierVO.getId());
					customerProfileUpdateVO.setUuid(UUID.randomUUID().toString());
					customerProfileUpdateVO.setEmail(selectedSupplierVO.getEmail());
					supplierProfileUpdateList.add(customerProfileUpdateVO);
				} else {
					unableSentEmailSupplList.add(selectedSupplierVO.getFullName());
				}
			}
		}
	}
	
	public String escapeSpecialCharacters(String data) {
		if (data == null) data = "";
		
	    String escapedData = data.replaceAll("\\R", " ");
	    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
	        data = data.replace("\"", "\"\"");
	        escapedData = "\"" + data + "\"";
	    }
	    return escapedData;
	}
	
	class LazySupplierDataModel extends LazyDataModel<SupplierVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		List<SupplierVO> datasource;
		
		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<SupplierVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			List<SupplierVO> data = new ArrayList<SupplierVO>();
			try {
				trackingLogUtils.startLogs();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("idCompany", getSessionInfoBean().getCompanyVO().getId());
				params.put("classes", true);
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				
				int size = purchaseBO.getSupplierListSize(params);
				System.out.println("################size: " + size);
				setRowCount(size);
				if (size > 0) {
					data = purchaseBO.getSupplierList(params);
					datasource = data;
				}
				
				if (selectedSupplList != null && selectedSupplList.size() > 0) {
					selectedObjs = selectedSupplList.toArray(new Object[selectedSupplList.size()]);
				}
				
			} catch (Throwable t) {
				t.printStackTrace();
				errorResult(t);
			} finally {
				trackingLogUtils.endLogs("LazySupplierDataModel");
			}
			return data;
		}
		
		@Override
		public void setRowIndex(int rowIndex) {
			if (rowIndex == -1 || getPageSize() == 0) {
				super.setRowIndex(-1);
			} else super.setRowIndex(rowIndex % getPageSize());
		}
	}
    
    @Override
    public void setSelectedObjs(Object[] selectedObjs) {
		this.selectedObjs = selectedObjs;
		if (selectedSupplList == null) selectedSupplList = new ArrayList<SupplierVO>();
    	if (selectedObjs != null) {
    		boolean existed = false;
    		for (Object obj : selectedObjs) {
        		SupplierVO supplVO = (SupplierVO) obj;
        		existed = false;
        		for (SupplierVO selectedVO : selectedSupplList) {
        			if (selectedVO.getId().equals(supplVO.getId())) {
        				existed = true;
        				break;
        			}
        		}
        		if (!existed) selectedSupplList.add(supplVO);
        	}
    	}
    	List<SupplierVO> dataList = (List<SupplierVO>) lazySupplDataModel.getWrappedData();
    	if (dataList != null && dataList.size() > 0) {
    		List<SupplierVO> removeList = new ArrayList<SupplierVO>();
    		for (SupplierVO data : dataList) {
    			for (Object obj : selectedObjs) {
    				SupplierVO custVO = (SupplierVO) obj;
    	    		if (data.getId().equals(custVO.getId())) {
    	    			removeList.add(data);
    	    			break;
    	    		}
    	    	}
    		}
    		if(removeList.size() > 0) dataList.removeAll(removeList);
    		removeList = new ArrayList<SupplierVO>();
    		for (SupplierVO data : dataList) {
    			for (SupplierVO selectedVO : selectedSupplList) {
    	    		if (data.getId().equals(selectedVO.getId())) {
    	    			removeList.add(selectedVO);
    	    			break;
    	    		}
    	    	}
    		}
    		if(removeList.size() > 0) selectedSupplList.removeAll(removeList);
    	}
	}

	public LazyDataModel<SupplierVO> getLazySupplDataModel() {
		return lazySupplDataModel;
	}

	public void setLazySupplDataModel(LazyDataModel<SupplierVO> lazySupplDataModel) {
		this.lazySupplDataModel = lazySupplDataModel;
	}

	public List<String> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}

	public List<SupplierProfileUpdateVO> getSupplierProfileUpdateList() {
		return supplierProfileUpdateList;
	}

	public void setsupplierProfileUpdateList(List<SupplierProfileUpdateVO> supplierProfileUpdateList) {
		this.supplierProfileUpdateList = supplierProfileUpdateList;
	}

	public List<String> getUnableSentEmailSupplList() {
		return unableSentEmailSupplList;
	}

	public void setUnableSentEmailSupplList(List<String> unableSentEmailSupplList) {
		this.unableSentEmailSupplList = unableSentEmailSupplList;
	}
}
