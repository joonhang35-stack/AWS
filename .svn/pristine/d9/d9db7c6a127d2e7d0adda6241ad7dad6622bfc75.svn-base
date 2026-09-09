package com.bcs.zsg.sales.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.FunctionUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.IpayConfigBO;
import com.bcs.zsg.maintenance.bo.SystemNumberGenerationBO;
import com.bcs.zsg.maintenance.vo.IpayConfigVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.sales.bo.InvoiceBO;
import com.bcs.zsg.sales.bo.OnlineBookingBO;
import com.bcs.zsg.sales.helper.SalesConstant;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.sales.vo.OnlineBookingPaymentVO;

public class OnlineBookingBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient OnlineBookingBO onlineBookingBO;
	@Autowired
	private transient IpayConfigBO ipayConfigBO;
	@Autowired
	private transient InvoiceBO invoiceBO;
	@Autowired
	private transient SystemNumberGenerationBO systemNumberGenerationBO;
	
	private LazyDataModel<OnlineBookingPaymentVO> lazyDMOnlineBookingPayment;
	private LazyDataModel<InvoiceVO> lazyDMInvoiceVO;
	
	private List<IpayConfigVO> ipayConfigVOList;
	
	private OnlineBookingPaymentVO onlineBookingPaymentVO;
	
	private String prefixValue;
	private final String URL_SALES_INV = "/app/sales/inv";
	private final String URL_SALES_PAX_STATEMENT = "/app/sales/paxStatement";

	@Override
	public void resetForm() {
		
	}
	
	public void init() {
		loadOnlineBookingPaymentList();
		
		try {
			SystemNumberGenerationVO invSNGVO = invSNGVO = systemNumberGenerationBO.getSystemNumberGeneration(CommonConstant.SYS_NUM_CD_INVC, this.getSessionInfoBean().getCompanyVO().getId());
			prefixValue = invSNGVO.getPrefixid();
			initPrefixVal();
			
		} catch (BusinessException e) {
			e.printStackTrace();
			errorResult(e);
		}
	}
	
	public void loadOnlineBookingPaymentList() {
		lazyDMOnlineBookingPayment = new LazyOnlineBookingPmntDataModel();
	}
	
	public void loadIpayConfigList() {
		try {
			ipayConfigVOList = ipayConfigBO.getIpayConfigVOList();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void loadInvoiceList() {
		lazyDMInvoiceVO = new LazyInvoiceDataModel();
	}
	
	public void navToDetail(OnlineBookingPaymentVO vo) {
		if (vo != null) {
			onlineBookingPaymentVO = vo;
		} else {
			onlineBookingPaymentVO = new OnlineBookingPaymentVO();
		}
	}
	
	public void save() throws BusinessException {
		onlineBookingBO.updateVO(onlineBookingPaymentVO, getSessionInfo().getUserVO().getName());
		successResult();
	}
	
	public void delete() throws BusinessException {
		onlineBookingBO.deleteOnlineBookingPayment(onlineBookingPaymentVO);
		loadOnlineBookingPaymentList();
		successResult();
	}
		
	public void handleInvNoSelect(SelectEvent event) throws BusinessException{
		InvoiceVO vo = (InvoiceVO) event.getObject();
		
		InvoiceVO tempVO = invoiceBO.getInvoiceById(vo.getId());
		SystemNumberGenerationVO invSNGVO = systemNumberGenerationBO.getSystemNumberGeneration(CommonConstant.SYS_NUM_CD_INVC, tempVO.getCompanyId());
		
		onlineBookingPaymentVO.setIdInvoiceAdminCharges(tempVO.getId());
		onlineBookingPaymentVO.setPrefixAdminCharges(invSNGVO.getPrefixid());
	}
	
	public void handleIpayConfigSelect(SelectEvent event) {
		IpayConfigVO ipayConfigVO = (IpayConfigVO) event.getObject();
		onlineBookingPaymentVO.setIdIpayConfig(ipayConfigVO.getId());
		onlineBookingPaymentVO.setMerchantCode(ipayConfigVO.getMerchantCode());
		onlineBookingPaymentVO.setMerchantKey(ipayConfigVO.getMerchantKey());
		onlineBookingPaymentVO.setIpayConfigComment(ipayConfigVO.getComment());
		onlineBookingPaymentVO.setAdminChargesPercentage(ipayConfigVO.getAdminChargesPercentage());
		handleAdminChargesPercentageChange();
	}
	
	public void handleAdminChargesPercentageChange() {
		onlineBookingPaymentVO.setAdminCharges(onlineBookingPaymentVO.getPaymentAmt().multiply(onlineBookingPaymentVO.getAdminChargesPercentage()).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP));
		calculateTotalPaymentAmt();
	}
	
	public void calculateTotalPaymentAmt() {
		onlineBookingPaymentVO.setTotalPaymentAmt(onlineBookingPaymentVO.getPaymentAmt().add(onlineBookingPaymentVO.getAdminCharges()));
	}
	
	public void removeInvoiceAdminCharges() {
		onlineBookingPaymentVO.setIdInvoiceAdminCharges(null);
		onlineBookingPaymentVO.setPrefixAdminCharges(null);
	}
	
	public void redirectToInvoice(OnlineBookingPaymentVO vo, String type) {
		try {
			String idInv = String.valueOf(vo.getIdInvoice());
			//if ("charges".equals(type)) idInv = String.valueOf(vo.getIdInvoiceAdminCharges());
			
			// search existed invoice to determine go for invoice or pax statement
			InvoiceVO existedInvVO = invoiceBO.getInvoiceById(Long.valueOf(idInv));
			if (existedInvVO != null) {
				if (StringUtils.isNotBlank(existedInvVO.getDocTypeCd())) {
					if (existedInvVO.getDocTypeCd().equals(SalesConstant.DOC_TYPE_CD_PAX_STMT)) {
						sendRedirect(URL_SALES_PAX_STATEMENT.concat("?invId=").concat(idInv));
					} else {
						sendRedirect(URL_SALES_INV.concat("?invId=").concat(idInv));
					}
				} else {
					sendRedirect(URL_SALES_PAX_STATEMENT.concat("?invId=").concat(idInv));
				}
//					        if (DatesUtils.isDateLessOrEqual(existedInvVO.getCreatedDate(), sdf.parse("2024-12-31"))) {
//					        	sendRedirect(URL_SALES_INV.concat("?invId=").concat(idInv));
//					        } else {
//					        	sendRedirect(URL_SALES_PAX_STATEMENT.concat("?invId=").concat(idInv));
//					        }
			} else {
				sendRedirect(URL_SALES_PAX_STATEMENT.concat("?invId=").concat(idInv));
			}
						
//			sendRedirect(URL_SALES_INV.concat("?invId=").concat(idInv));
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	class LazyOnlineBookingPmntDataModel extends LazyDataModel<OnlineBookingPaymentVO> implements Serializable {
		private static final long serialVersionUID = 1L;
		TrackingLogUtils trackingLogUtils = new TrackingLogUtils(this.getClass());

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<OnlineBookingPaymentVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				trackingLogUtils.startLogs();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				setRowCount(onlineBookingBO.getOnlineBookingPaymentListSize(params));
				if (0 < getRowCount()) return onlineBookingBO.getOnlineBookingPaymentList(params);
				
			} catch (Throwable t) {
				errorResult(t);
			} finally {
				trackingLogUtils.endLogs("LazyOnlineBookingPmntDataModel");
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
	
	class LazyInvoiceDataModel extends LazyDataModel<InvoiceVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<InvoiceVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("companyId", getSessionInfoBean().getCompanyVO().getId());
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
//				params.put("type", "I");
				params.put("type", FunctionUtils.generateArrayFromStr(SalesConstant.DOC_TYPE_CD_INVC));
				setRowCount(invoiceBO.getInvoiceListSize(params));
				if (0 < getRowCount()) return invoiceBO.getInvoiceList(params);
				
			} catch (Throwable t) {
				errorResult(t);
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

	public OnlineBookingPaymentVO getOnlineBookingPaymentVO() {
		return onlineBookingPaymentVO;
	}

	public void setOnlineBookingPaymentVO(OnlineBookingPaymentVO onlineBookingPaymentVO) {
		this.onlineBookingPaymentVO = onlineBookingPaymentVO;
	}

	public List<IpayConfigVO> getIpayConfigVOList() {
		return ipayConfigVOList;
	}

	public void setIpayConfigVOList(List<IpayConfigVO> ipayConfigVOList) {
		this.ipayConfigVOList = ipayConfigVOList;
	}

	public LazyDataModel<InvoiceVO> getLazyDMInvoiceVO() {
		return lazyDMInvoiceVO;
	}

	public void setLazyDMInvoiceVO(LazyDataModel<InvoiceVO> lazyDMInvoiceVO) {
		this.lazyDMInvoiceVO = lazyDMInvoiceVO;
	}

	public LazyDataModel<OnlineBookingPaymentVO> getLazyDMOnlineBookingPayment() {
		return lazyDMOnlineBookingPayment;
	}

	public void setLazyDMOnlineBookingPayment(LazyDataModel<OnlineBookingPaymentVO> lazyDMOnlineBookingPayment) {
		this.lazyDMOnlineBookingPayment = lazyDMOnlineBookingPayment;
	}

	/**
	 * @return the prefixValue
	 */
	public String getPrefixValue() {
		return prefixValue;
	}
}
