package com.bcs.zsg.product.helper;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.bcs.zsg.purchase.bo.PurchaseEOBO;
import com.bcs.zsg.purchase.vo.ExOrderVO;

/**********************
 * Lazy loading model *
 **********************/
@Service("LazyDMExOrder")
public class LazyDMExOrder <T extends ExOrderVO> extends LazyDataModel<T> implements Serializable {
	private final static Logger logger = LoggerFactory.getLogger(LazyDMExOrder.class);
	private static final long serialVersionUID = 1L;
	
	private long startTime;
	private long endTime;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private String className = "LazyDMExOrder";
	
	@Autowired
	protected transient PurchaseEOBO purchaseEOBO;
	
	private Long companyId;
	private List<T> datasource;
	private boolean clearFilter;
	private String status;
	
	public LazyDMExOrder() {
		this.clearFilter = false;
	}
	
	public LazyDMExOrder(boolean clearFilter) {
		this.clearFilter = clearFilter;
	}
	
	public LazyDMExOrder(String status) {
		this.status = status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		datasource = new ArrayList<T>();
		
		try {
			this.startTime = System.currentTimeMillis();
			
			// For clean the search filters when reopen list. But filtered then sorting will come back the filter
			if (clearFilter) filters.clear();
			
			if (companyId == null)
				throw new Exception("Search parameter is not valid. ");
			
			if (purchaseEOBO == null)
				SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("companyId", companyId);
			params.put("first", first);
			params.put("pageSize", pageSize);
			params.put("sortField", sortField);
			params.put("sortOrder", sortOrder);
			params.put("filters", filters);
			if (StringUtils.isNotEmpty(status)) params.put("status", status);
			setRowCount(purchaseEOBO.getEOListSize(params));
			if (0 < getRowCount()) {
				datasource = (List<T>) purchaseEOBO.getEOList(params);
				return datasource;
			}
			
		} catch (Throwable t) {
			logger.error("Error loading LazyDMExOrder", t);
			t.printStackTrace();
		} finally {
			clearFilter = false;
			this.endTime = System.currentTimeMillis();
			System.out.println("[Start: " + sdf.format(this.startTime) + "] [End: " + sdf.format(this.endTime) + "] " + className + " [Time Spent: " + (this.endTime - this.startTime) + "]");
			logger.error("[Start: " + sdf.format(this.startTime) + "] [End: " + sdf.format(this.endTime) + "] " + className + " [Time Spent: " + (this.endTime - this.startTime) + "]");
		}
		return datasource;
	}
	
	@Override
	public T getRowData(String rowKey) {
		for (T obj : datasource) {
			if (obj.getUuid().equals(rowKey))
				return obj;
		}
		return null;
	}
	
	@Override
	public Object getRowKey(T object) {
		return object.getId();
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

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
