package com.bcs.zsg.sales.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.mail.bo.EmailingBO;
import com.bcs.zsg.maintenance.bo.IpayConfigBO;
import com.bcs.zsg.maintenance.bo.SystemNumberGenerationBO;
import com.bcs.zsg.maintenance.vo.IpayConfigVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.sales.bo.InvoiceBO;
import com.bcs.zsg.sales.bo.InvoiceEmailPaymentBO;
import com.bcs.zsg.sales.helper.SalesConstant;
import com.bcs.zsg.sales.vo.InvoiceEmailPaymentVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

import net.sf.jasperreports.engine.JasperPrint;

public class InvoiceEmailPaymentBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient InvoiceEmailPaymentBO invoiceEmailPaymentBO;
	@Autowired
	private transient IpayConfigBO ipayConfigBO;
	@Autowired
	private transient EmailingBO emailingBO;
	@Autowired
	private transient InvoiceBO invoiceBO;
	@Autowired
	private transient SystemNumberGenerationBO systemNumberGenerationBO;
	
	private LazyDataModel<InvoiceEmailPaymentVO> lazyDMInvoiceEmailPayment;
	private LazyDataModel<InvoiceVO> lazyDMInvoiceVO;
	
	private List<IpayConfigVO> ipayConfigVOList;
	
	private InvoiceEmailPaymentVO invoiceEmailPaymentVO;
	
	private Map<String, Object> printFilter;
	
	private final String URL_SALES_INV = "/app/sales/inv";
	private final String URL_SALES_PAX_STATEMENT = "/app/sales/paxStatement";
	
	@Override
	public void resetForm() {
		
	}
	
	public void init() {
		loadInvoiceEmailPaymentList();
	}
	
	public void loadInvoiceEmailPaymentList() {
		lazyDMInvoiceEmailPayment = new LazyInvEmailPmntDataModel();
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
	
	public void navToDetail(InvoiceEmailPaymentVO vo) {
		if (vo != null) {
			invoiceEmailPaymentVO = vo;
		} else {
			invoiceEmailPaymentVO = new InvoiceEmailPaymentVO();
		}
	}
	
	public void save() {
		try {
			invoiceEmailPaymentBO.updateInvoiceEmailPayment(invoiceEmailPaymentVO, getSessionInfo().getUserVO());
			successResult();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void delete() {
		try {
			invoiceEmailPaymentBO.deleteInvoiceEmailPayment(invoiceEmailPaymentVO);
			loadInvoiceEmailPaymentList();
			successResult();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void resendEmail() {
		try {
			emailingBO.sendEmailPaymentEmail(invoiceEmailPaymentVO, getSessionInfo().getUserVO());
			successResult();
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void handleInvNoSelect(SelectEvent event) throws BusinessException{
		InvoiceVO vo = (InvoiceVO) event.getObject();
		
		InvoiceVO tempVO = invoiceBO.getInvoiceById(vo.getId());
		SystemNumberGenerationVO invSNGVO = systemNumberGenerationBO.getSystemNumberGeneration(CommonConstant.SYS_NUM_CD_INVC, tempVO.getCompanyId());
		
		invoiceEmailPaymentVO.setIdInvoiceAdminCharges(tempVO.getId());
		invoiceEmailPaymentVO.setPrefixAdminCharges(invSNGVO.getPrefixid());
		invoiceEmailPaymentVO.setInvoiceAdminChargesNo(tempVO.getCode());
	}
	
	public void handleIpayConfigSelect(SelectEvent event) {
		IpayConfigVO ipayConfigVO = (IpayConfigVO) event.getObject();
		invoiceEmailPaymentVO.setIdIpayConfig(ipayConfigVO.getId());
		invoiceEmailPaymentVO.setMerchantCode(ipayConfigVO.getMerchantCode());
		invoiceEmailPaymentVO.setMerchantKey(ipayConfigVO.getMerchantKey());
		invoiceEmailPaymentVO.setIpayConfigComment(ipayConfigVO.getComment());
		invoiceEmailPaymentVO.setAdminChargesPercentage(ipayConfigVO.getAdminChargesPercentage());
		handleAdminChargesPercentageChange();
	}
	
	public void handleAdminChargesPercentageChange() {
		invoiceEmailPaymentVO.setAdminCharges(invoiceEmailPaymentVO.getPaymentAmt().multiply(invoiceEmailPaymentVO.getAdminChargesPercentage()).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP));
		calculateTotalPaymentAmt();
	}
	
	public void calculateTotalPaymentAmt() {
		invoiceEmailPaymentVO.setTotalPaymentAmt(invoiceEmailPaymentVO.getPaymentAmt().add(invoiceEmailPaymentVO.getAdminCharges()));
	}
	
	public void removeInvoiceAdminCharges() {
		invoiceEmailPaymentVO.setIdInvoiceAdminCharges(null);
		invoiceEmailPaymentVO.setPrefixAdminCharges(null);
		invoiceEmailPaymentVO.setInvoiceAdminChargesNo(null);
	}
	
	public void redirectToInvoice(InvoiceEmailPaymentVO vo, String type) {
		try {
			String idInv = String.valueOf(vo.getIdInvoice());
			if ("charges".equals(type)) idInv = String.valueOf(vo.getIdInvoiceAdminCharges());
			
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
//		        if (DatesUtils.isDateLessOrEqual(existedInvVO.getCreatedDate(), sdf.parse("2024-12-31"))) {
//		        	sendRedirect(URL_SALES_INV.concat("?invId=").concat(idInv));
//		        } else {
//		        	sendRedirect(URL_SALES_PAX_STATEMENT.concat("?invId=").concat(idInv));
//		        }
			} else {
				sendRedirect(URL_SALES_PAX_STATEMENT.concat("?invId=").concat(idInv));
			}
//			sendRedirect(URL_SALES_INV.concat("?invId=").concat(idInv));
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void printInvEmailPmnt(String type) {
		try {
			printFilter.put("print", "Y");
			List<InvoiceEmailPaymentVO> eplList = invoiceEmailPaymentBO.getInvoiceEmailPaymentList(printFilter);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("eplList", eplList);
			
			JasperPrint jasperPrint = ReportUtils.getJasperPrint(map, CommonConstant.JAS_RPT_INVOICE_EMAIL_PAYMENT);
			//ReportUtils.printReport(jasperPrint, CommonConstant.PDF_RPT_INVOICE_EMAIL_PAYMENT);
			
			if ("PDF".equals(type)) {
				ReportUtils.printReport(jasperPrint, CommonConstant.PDF_RPT_INVOICE_EMAIL_PAYMENT);
			} else { // excel
				ReportUtils.printReportExcel(jasperPrint, CommonConstant.PDF_RPT_INVOICE_EMAIL_PAYMENT);
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	class LazyInvEmailPmntDataModel extends LazyDataModel<InvoiceEmailPaymentVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<InvoiceEmailPaymentVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				params.put("type", "I");
				params.put("print", null);
				
				printFilter = params;
				
				setRowCount(invoiceEmailPaymentBO.getInvoiceEmailPaymentListSize(params));
				if (0 < getRowCount()) return invoiceEmailPaymentBO.getInvoiceEmailPaymentList(params);
				
			} catch (Throwable t) {
				errorResult(t);
			}
			return new ArrayList<InvoiceEmailPaymentVO>();
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
			
			return new ArrayList<InvoiceVO>();
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

	public InvoiceEmailPaymentVO getInvoiceEmailPaymentVO() {
		return invoiceEmailPaymentVO;
	}

	public void setInvoiceEmailPaymentVO(InvoiceEmailPaymentVO invoiceEmailPaymentVO) {
		this.invoiceEmailPaymentVO = invoiceEmailPaymentVO;
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

	public LazyDataModel<InvoiceEmailPaymentVO> getLazyDMInvoiceEmailPayment() {
		return lazyDMInvoiceEmailPayment;
	}

	public void setLazyDMInvoiceEmailPayment(LazyDataModel<InvoiceEmailPaymentVO> lazyDMInvoiceEmailPayment) {
		this.lazyDMInvoiceEmailPayment = lazyDMInvoiceEmailPayment;
	}
}
