package com.bcs.zsg.sales.web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.bo.TourCatBO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.service.InvoiceAndExchangeOrderService;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderVO;
import com.bcs.zsg.product.vo.TourCatViewVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.sales.service.InvoiceItemReportUsageService;

import net.sf.jasperreports.engine.JasperPrint;

public class InvoiceItemReportUsageBean extends AppBackingBean {

	private static final long serialVersionUID = 1L;

	@Autowired
	private transient InvoiceItemReportUsageService invoiceItemReportUsageService;

	@Autowired
	private transient InvoiceAndExchangeOrderService invoiceAndExchangeOrderService;

	@Autowired
	private transient TourCatBO tourCatBO;

	@Autowired
	private transient TourPackageBO tourPackageBO;

	private List<String> selectedItemCodes = new ArrayList<String>();
	private List<String> selectedStatusCds = new ArrayList<String>();
	private List<String> selectedInvCatCds = new ArrayList<String>();
	private List<String> selectedOrderSourceCds = new ArrayList<String>();
	private List<String> selectedRegionIds = new ArrayList<String>();
	private List<String> selectedCountryIds = new ArrayList<String>();

	private List<InvoiceAndExchangeOrderVO> invoiceItemList;
	private List<TourCatViewVO> tourCatViewVOList;
	private List<TourThemeVO> tourCountryVOList;
	private List<SelectItem> statusList;
	private String exportFileName;

	@Override
	public void resetForm() {
		searchParamVO = new SearchParamVO();
		searchParamVO.setFromDate(new Date());
		searchParamVO.setToDate(new Date());
		if (selectedItemCodes != null) {
			selectedItemCodes.clear();
		} else {
			selectedItemCodes = new ArrayList<String>();
		}
		if (selectedStatusCds != null) {
			selectedStatusCds.clear();
		} else {
			selectedStatusCds = new ArrayList<String>();
		}
		if (selectedInvCatCds != null) {
			selectedInvCatCds.clear();
		} else {
			selectedInvCatCds = new ArrayList<String>();
		}
		if (selectedOrderSourceCds != null) {
			selectedOrderSourceCds.clear();
		} else {
			selectedOrderSourceCds = new ArrayList<String>();
		}
		if (selectedRegionIds != null) {
			selectedRegionIds.clear();
		} else {
			selectedRegionIds = new ArrayList<String>();
		}
		if (selectedCountryIds != null) {
			selectedCountryIds.clear();
		} else {
			selectedCountryIds = new ArrayList<String>();
		}
		updateExportFileName();
	}

	public void updateExportFileName() {
		exportFileName = "Invoice_Item_Code" + new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	public void init() throws BusinessException {
		try {
			resetForm();
			loadInvoiceItemList();
			loadRegionList();
			loadCountryList();
			initStatusList();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void loadInvoiceItemList() {
		try {
			Long companyId = this.getSessionInfoBean().getCompanyVO().getId();
			invoiceItemList = invoiceAndExchangeOrderService.getInvoiceAndExchangeOrderList(companyId, null);
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void loadRegionList() {
		try {
			SearchParamVO vo = new SearchParamVO();
			vo.setObj1(ProductConstant.TYPE_TOUR);
			tourCatViewVOList = tourCatBO.getTourCatViewList(vo);
		} catch (BusinessException e) {
			errorResult(e);
		}
	}

	public void loadCountryList() {
		try {
			SearchParamVO vo = new SearchParamVO();
			vo.setObj3("seqNo");
			tourCountryVOList = tourPackageBO.getTourThemeList(vo, null);
		} catch (BusinessException e) {
			errorResult(e);
		}
	}

	public void initStatusList() {
		try {
			statusList = invoiceItemReportUsageService.getStatusList();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void printReportExcel() {
		try {
			Long companyId = this.getSessionInfoBean().getCompanyVO().getId();
			List<AcctViewVO> exportList = invoiceItemReportUsageService.getInvoiceItemReportUsageList(
					companyId, selectedItemCodes, searchParamVO.getFromDate(), searchParamVO.getToDate(),
					selectedStatusCds, selectedInvCatCds, selectedOrderSourceCds, selectedRegionIds, selectedCountryIds);

			if (CollectionUtils.isEmpty(exportList)) {
				throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);
			}

			updateExportFileName();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", exportList);
			map.put("companyName", getSessionInfoBean().getCompanyVO().getName());
			map.put("fromDate", searchParamVO.getFromDate());
			map.put("toDate", searchParamVO.getToDate());

			String jasperFileName = CommonConstant.JAS_RPT_INVOICE_ITEM_USAGE;
			String reportName = exportFileName;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(exportList, map, jasperFileName);
			ReportUtils.printReportExcel(jasperPrint, reportName);

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public List<String> getSelectedItemCodes() {
		return selectedItemCodes;
	}

	public void setSelectedItemCodes(List<String> selectedItemCodes) {
		this.selectedItemCodes = selectedItemCodes;
	}

	public List<String> getSelectedStatusCds() {
		return selectedStatusCds;
	}

	public void setSelectedStatusCds(List<String> selectedStatusCds) {
		this.selectedStatusCds = selectedStatusCds;
	}

	public List<String> getSelectedInvCatCds() {
		return selectedInvCatCds;
	}

	public void setSelectedInvCatCds(List<String> selectedInvCatCds) {
		this.selectedInvCatCds = selectedInvCatCds;
	}

	public List<String> getSelectedOrderSourceCds() {
		return selectedOrderSourceCds;
	}

	public void setSelectedOrderSourceCds(List<String> selectedOrderSourceCds) {
		this.selectedOrderSourceCds = selectedOrderSourceCds;
	}

	public List<String> getSelectedRegionIds() {
		return selectedRegionIds;
	}

	public void setSelectedRegionIds(List<String> selectedRegionIds) {
		this.selectedRegionIds = selectedRegionIds;
	}

	public List<String> getSelectedCountryIds() {
		return selectedCountryIds;
	}

	public void setSelectedCountryIds(List<String> selectedCountryIds) {
		this.selectedCountryIds = selectedCountryIds;
	}

	public List<InvoiceAndExchangeOrderVO> getInvoiceItemList() {
		return invoiceItemList;
	}

	public void setInvoiceItemList(List<InvoiceAndExchangeOrderVO> invoiceItemList) {
		this.invoiceItemList = invoiceItemList;
	}

	public List<TourCatViewVO> getTourCatViewVOList() {
		return tourCatViewVOList;
	}

	public void setTourCatViewVOList(List<TourCatViewVO> tourCatViewVOList) {
		this.tourCatViewVOList = tourCatViewVOList;
	}

	public List<TourThemeVO> getTourCountryVOList() {
		return tourCountryVOList;
	}

	public void setTourCountryVOList(List<TourThemeVO> tourCountryVOList) {
		this.tourCountryVOList = tourCountryVOList;
	}

	public List<SelectItem> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<SelectItem> statusList) {
		this.statusList = statusList;
	}

	public String getExportFileName() {
		return exportFileName;
	}

	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}
}
