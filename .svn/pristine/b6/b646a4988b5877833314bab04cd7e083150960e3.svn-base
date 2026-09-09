package com.bcs.zsg.crm.web.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.crm.bo.InvoicePosSalesBO;
import com.bcs.zsg.crm.vo.InvoicePosSalesItemVO;
import com.bcs.zsg.crm.vo.InvoicePosSalesVO;

public class PostingSalesInvoiceBean extends AppBackingBean {

	@Autowired
	private transient InvoicePosSalesBO invoicePosSalesBO;
	
	TrackingLogUtils trackingLogUtils;

	private static final long serialVersionUID = 1L;
	
	private String invPrefixVal;
	private String psPrefixVal;

	public List<InvoicePosSalesVO> postingSalesList;
	public List<InvoicePosSalesItemVO> postingSalesItemList;
	public InvoicePosSalesVO invPosSalesVO;
	
	private LazyDataModel<InvoicePosSalesVO> lazyDataModel;

	public void init() {
		try {
			initSearchParam();
			searchParamVO.setObj1("ALL");
			searchParamVO.setObj2("ALL");
			resetForm();

			trackingLogUtils = new TrackingLogUtils(getClass());

			loadPostingSales();
			
			invPrefixVal = LookupItemUtils.getSysNumGenVO(getSessionInfoBean().getCompanyVO().getId(), CommonConstant.SYS_NUM_CD_INVC).getPrefixid();
			psPrefixVal = LookupItemUtils.getSysNumGenVO(getSessionInfoBean().getCompanyVO().getId(), CommonConstant.SYS_NUM_CD_PAX_STMT).getPrefixid();

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	@Override
	public void resetForm() {
		
	}

	public void loadPostingSales() throws BusinessException {
		lazyDataModel = new LazyIPSDataModel();
	}
	
	public void search() throws BusinessException {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("idCompany", getSessionInfoBean().getCompanyVO().getId());
//		params.put("dateFrom", searchParamVO.getFromDate());
//		params.put("dateTo", searchParamVO.getToDate());
//		
//		postingSalesList = invoicePosSalesBO.getInvoicePosSalesList(params);
		lazyDataModel = new LazyIPSDataModel();
	}

	public void onPostingSalesSelected(InvoicePosSalesVO invPosSalesVO) {
		try {
			
			trackingLogUtils.startLogs();

//			this.invPosSalesVO = invPosSalesVO;
			
			postingSalesItemList = invoicePosSalesBO.getInvoicePosSalesItemList(invPosSalesVO.getId());
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("onPostingSalesSelected");
		}
	}
	
	/**********************
	 * Lazy loading model *
	 **********************/
	
	class LazyIPSDataModel extends LazyDataModel<InvoicePosSalesVO> implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public LazyIPSDataModel() {
			
		}

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<InvoicePosSalesVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			trackingLogUtils.startLogs();
			
			
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("idCompany", getSessionInfoBean().getCompanyVO().getId());
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				
				if (searchParamVO.getFromDate() != null) params.put("dateFrom", searchParamVO.getFromDate());
	            if (searchParamVO.getToDate() != null) params.put("dateTo", searchParamVO.getToDate());
	            if (searchParamVO.getObj1() != null) params.put("invoiceType", searchParamVO.getObj1());
	            if (searchParamVO.getObj2() != null) params.put("postingStatus", searchParamVO.getObj2());
	            
				setRowCount(invoicePosSalesBO.getInvoicePosSalesListSize(params));
				if (0 < getRowCount()) return invoicePosSalesBO.getInvoicePosSalesList(params);
				
			} catch (Throwable t) {
				t.printStackTrace();
				errorResult(t);
			} finally {
				trackingLogUtils.endLogs("LazyIPSDataModel");
			}
			return null;
		}
		
		@Override
		public void setRowIndex(int rowIndex) {
			/*
			 * The following is in ancestor (LazyDataModel):
			 * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
			 */
			if (rowIndex == -1 || getPageSize() == 0) {
				super.setRowIndex(-1);
			} else super.setRowIndex(rowIndex % getPageSize());
		}
		
	}

	public List<InvoicePosSalesVO> getPostingSalesList() {
		return postingSalesList;
	}

	public void setPostingSalesList(List<InvoicePosSalesVO> postingSalesList) {
		this.postingSalesList = postingSalesList;
	}

	public InvoicePosSalesVO getInvPosSalesVO() {
		return invPosSalesVO;
	}

	public void setInvPosSalesVO(InvoicePosSalesVO invPosSalesVO) {
		this.invPosSalesVO = invPosSalesVO;
	}

	public String getInvPrefixVal() {
		return invPrefixVal;
	}

	public void setInvPrefixVal(String invPrefixVal) {
		this.invPrefixVal = invPrefixVal;
	}

	public String getPsPrefixVal() {
		return psPrefixVal;
	}

	public void setPsPrefixVal(String psPrefixVal) {
		this.psPrefixVal = psPrefixVal;
	}

	public LazyDataModel<InvoicePosSalesVO> getLazyDataModel() {
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<InvoicePosSalesVO> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public List<InvoicePosSalesItemVO> getPostingSalesItemList() {
		return postingSalesItemList;
	}

	public void setPostingSalesItemList(List<InvoicePosSalesItemVO> postingSalesItemList) {
		this.postingSalesItemList = postingSalesItemList;
	}
}
