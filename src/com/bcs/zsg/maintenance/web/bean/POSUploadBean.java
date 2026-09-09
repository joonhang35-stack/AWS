package com.bcs.zsg.maintenance.web.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.POSUploadSalesBO;
import com.bcs.zsg.maintenance.vo.POSUploadSalesTransVO;
import com.bcs.zsg.maintenance.vo.POSUploadSalesVO;

public class POSUploadBean extends AppBackingBean {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient POSUploadSalesBO posUploadSalesBO;
	
	private LazyDataModel<POSUploadSalesVO> lazyDMPOSUploadSalesVO;
	private LazyDataModel<POSUploadSalesTransVO> lazyDMPOSUploadSalesTransVO;
	
	private String idPOSUploadSales;
	private Date uploadDate, todayDate;
	
	@Override
	public void resetForm() {
		uploadDate = new Date();
		todayDate = new Date(); 
	}
	
	public void init() {
		resetForm();
		loadPOSUploadSales();
	}
	
	public void loadPOSUploadSales() {
		lazyDMPOSUploadSalesVO = new LazyPOSUploadSalesDataModel();
	}
	
	public void loadPOSUploadSalesTrans() {
		lazyDMPOSUploadSalesTransVO = new LazyPOSUploadSalesTransDataModel();
	}
	
	public void onPOSUploadSalesSelected(String idPOSUploadSales) {
		this.idPOSUploadSales = idPOSUploadSales;
		loadPOSUploadSalesTrans();
	}
	
	public void upload() {
		try {
			posUploadSalesBO.processPOSUploadSales(uploadDate);
		} catch (BusinessException | IOException e) {
			errorResult(e);
		}
	}
	
	public void clearTestData() throws BusinessException {
		try {
			if (posUploadSalesBO.clearTestData())
				successResult();
		} catch (IOException e) {
			errorResult(e);
		}
	}
	
	/**********************
	 * Lazy loading model *
	 **********************/
	class LazyPOSUploadSalesDataModel extends LazyDataModel<POSUploadSalesVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<POSUploadSalesVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("companyId", getSessionInfoBean().getCompanyVO().getId());
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				params.put("type", "I");
				setRowCount(posUploadSalesBO.getPOSUploadSalesListCount(params));
				
				if (0 < getRowCount()) return posUploadSalesBO.getPOSUploadSalesList(params);
				
			} catch (Throwable t) {
				errorResult(t);
			}
			
			return null;
		}
	}
	
	class LazyPOSUploadSalesTransDataModel extends LazyDataModel<POSUploadSalesTransVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<POSUploadSalesTransVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				filters.put("idPOSUploadSales", idPOSUploadSales);
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("companyId", getSessionInfoBean().getCompanyVO().getId());
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				params.put("type", "I");
				setRowCount(posUploadSalesBO.getPOSUploadSalesTransListCount(params));
				
				if (0 < getRowCount()) return posUploadSalesBO.getPOSUploadSalesTransList(params);
				
			} catch (Throwable t) {
				errorResult(t);
			}
			
			return null;
		}
	}
	
	public LazyDataModel<POSUploadSalesVO> getLazyDMPOSUploadSalesVO() {
		return lazyDMPOSUploadSalesVO;
	}

	public void setLazyDMPOSUploadSalesVO(LazyDataModel<POSUploadSalesVO> lazyDMPOSUploadSalesVO) {
		this.lazyDMPOSUploadSalesVO = lazyDMPOSUploadSalesVO;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Date getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(Date todayDate) {
		this.todayDate = todayDate;
	}

	public LazyDataModel<POSUploadSalesTransVO> getLazyDMPOSUploadSalesTransVO() {
		return lazyDMPOSUploadSalesTransVO;
	}

	public void setLazyDMPOSUploadSalesTransVO(LazyDataModel<POSUploadSalesTransVO> lazyDMPOSUploadSalesTransVO) {
		this.lazyDMPOSUploadSalesTransVO = lazyDMPOSUploadSalesTransVO;
	}

	public String getIdPOSUploadSales() {
		return idPOSUploadSales;
	}

	public void setIdPOSUploadSales(String idPOSUploadSales) {
		this.idPOSUploadSales = idPOSUploadSales;
	}
}
