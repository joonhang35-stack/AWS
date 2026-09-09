package com.bcs.zsg.maintenance.web.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.CustPointBO;
import com.bcs.zsg.maintenance.vo.CustPointVO;
import com.bcs.zsg.maintenance.vo.CustVoucherVO;
import com.bcs.zsg.sales.bo.CustomerBO;
import com.bcs.zsg.sales.bo.InvoiceBO;
import com.bcs.zsg.sales.vo.CustDetailsVO;
import com.bcs.zsg.sales.vo.CustomerVO;

public class CustPointBean extends AppBackingBean{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient CustPointBO custPointBO;
	@Autowired
	private transient CustomerBO customerBO;
	@Autowired
	private transient InvoiceBO invoiceBO;
	
	protected TrackingLogUtils trackingLogUtils;
	private Integer typeId;
	private Integer quantity;
	private CustPointVO custPointVO;
	private CustomerVO customerVO;
	private CustDetailsVO custDetailsVO;
	private List<CustVoucherVO> voucherList;
	private List<CustDetailsVO> custDetailsList;
	private boolean isRedeem;
	
	private LazyDataModel<CustomerVO> lazyCustDataModel;
	
	
	public void resetForm() {
		custPointVO = new CustPointVO();
		quantity = null;
	}
	
	public void init() {
		try {
			trackingLogUtils = new TrackingLogUtils(this.getClass());
			resetForm();
			retrieveVoucherList();
			loadCustomer();
		}catch(Throwable t) {
			errorResult(t);
		}
	}
	
	public void search() {
		try {
				custPointVO = custPointBO.getCustomerLoyaltyPoint(custPointVO.getCustomerId());
		} catch (IOException e) {
			errorResult(e);
		}
	}
	
	public void convertVoucher() {
		try {
				custPointVO.setTypeId(typeId);
				custPointVO.setQuantity(quantity);
				custPointBO.convertCustomerVoucher(custPointVO);
				search();
				retrieveVoucherList();
		}catch(Throwable t) {
			errorResult(t);
		}
	}
	
	public void redeemVoucher() {
		try {
				custPointBO.redeemCustomerVoucher(custPointVO);
				search();
				retrieveVoucherList();
		}catch(Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleRedeemSelect(CustVoucherVO vo) {
		try {
			if(CollectionUtils.isEmpty(custPointVO.getVoucherList()))
				custPointVO.setVoucherList(new ArrayList<CustVoucherVO>());
			if(vo.getRedeemConfirm()) {
				custPointVO.getVoucherList().add(vo);
			}else
				custPointVO.getVoucherList().remove(vo);
		}catch(Throwable t) {
			errorResult(t);
		}
	}
	
	public void retrieveVoucherList() {
		try {
			voucherList = new ArrayList<CustVoucherVO>();
			voucherList = custPointBO.getVoucherList();
		} catch (IOException e) {
			errorResult(e);
		}
	}

	
	/**
	 * Handle customer selection
	 */
	public void handleCustSelect(SelectEvent event) {
		try {
			custDetailsVO = new CustDetailsVO();
			custDetailsVO = invoiceBO.getCustDetails(((CustomerVO) event.getObject()).getId());
			custPointVO.setCustomerId(custDetailsVO.getCustId().intValue());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Load customer list
	 * @throws BusinessException
	 */
	private void loadCustomer() throws BusinessException {
		customerVO = new CustomerVO();
		lazyCustDataModel = new LazyCustomerDataModel();
	}
	
	class LazyCustomerDataModel extends LazyDataModel<CustomerVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<CustomerVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			List<CustomerVO> data = new ArrayList<CustomerVO>();
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
				int size = customerBO.getCustomerListSize(params);
				if (CommonConstant.LAZY_ROW_COUNT < size) {
					size = CommonConstant.LAZY_ROW_COUNT;
				}
				setRowCount(size);
				if (size > 0) data = customerBO.getCustomerList(params);
				
			} catch (Throwable t) {
				errorResult(t);
			} finally {
				trackingLogUtils.endLogs("LazyCustomerDataModel");
			}
			return data;
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
	
	public CustPointVO getCustPointVO() {
		return custPointVO;
	}

	public void setCustPointVO(CustPointVO custPointVO) {
		this.custPointVO = custPointVO;
	}

	public List<CustVoucherVO> getVoucherList() {
		return voucherList;
	}

	public void setVoucherList(List<CustVoucherVO> voucherList) {
		this.voucherList = voucherList;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public boolean getIsRedeem() {
		return isRedeem;
	}

	public void setRedeem(boolean isRedeem) {
		this.isRedeem = isRedeem;
	}

	public CustomerVO getCustomerVO() {
		return customerVO;
	}

	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}

	public List<CustDetailsVO> getCustDetailsList() {
		return custDetailsList;
	}

	public void setCustDetailsList(List<CustDetailsVO> custDetailsList) {
		this.custDetailsList = custDetailsList;
	}

	public CustDetailsVO getCustDetailsVO() {
		return custDetailsVO;
	}

	public void setCustDetailsVO(CustDetailsVO custDetailsVO) {
		this.custDetailsVO = custDetailsVO;
	}

	public LazyDataModel<CustomerVO> getLazyCustDataModel() {
		return lazyCustDataModel;
	}

	public void setLazyCustDataModel(LazyDataModel<CustomerVO> lazyCustDataModel) {
		this.lazyCustDataModel = lazyCustDataModel;
	}
}
