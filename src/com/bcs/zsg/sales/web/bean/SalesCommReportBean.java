package com.bcs.zsg.sales.web.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.bo.UserBO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.report.SalesCommissionReportVO;
import com.bcs.zsg.product.bo.SalesCommConfigBO;
import com.bcs.zsg.product.vo.SalesCommConfigVO;
import com.bcs.zsg.sales.bo.InvoiceReportBO;

import net.sf.jasperreports.engine.JasperPrint;

public class SalesCommReportBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	protected transient InvoiceReportBO invoiceReportBO;
	@Autowired
	private transient UserBO userBO;
	@Autowired
	private transient SalesCommConfigBO salesCommConfigBO;
	
	private List<SalesCommConfigVO> salesCommConfigList;
	private List<EmployeeViewVO> employeeList;
	private List<String> idDepartmentList;
	private List<String> idStaffDepartmentList;
	private List<String> idOrderSourceList;
	private List<String> idSalesPersonList;
	private List<String> idRefSalesPersonList;
	private boolean hideCreditNote = false;
	
	@Override
	public void resetForm() {
		
	}

	public void init() throws BusinessException {
		try {
			// Initialize search param
			initSearchParam();
			handleCalendar();
			
			loadEmployeeList();
			loadSalesCommConfigList();
			resetForm();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Load employee list
	 */
	public void loadEmployeeList() {
		try {
			employeeList = userBO.getEmployeeViewList(this.getSessionInfoBean().getCompanyVO().getId());
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Load sales commission config list
	 */
	public void loadSalesCommConfigList() {
		try {
			salesCommConfigList = salesCommConfigBO.getSalesCommConfigList();
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleCalendar() {
		try {
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(new Date());
//			// set from date
//			cal.set(Calendar.DAY_OF_MONTH, 1);
//			cal.set(Calendar.HOUR_OF_DAY, 0);
//			cal.set(Calendar.MINUTE, 0);
//			cal.set(Calendar.SECOND, 0);
//			searchParamVO.setObj1(cal.getTime());
//			// set to date
//			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//			cal.set(Calendar.HOUR_OF_DAY, 23);
//			cal.set(Calendar.MINUTE, 59);
//			cal.set(Calendar.SECOND, 59);
//			searchParamVO.setObj2(cal.getTime());
			
			searchParamVO.setObj1(null);
			searchParamVO.setObj2(null);
			searchParamVO.setObj3(null);
			searchParamVO.setObj4(null);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void print(String xlsOrPDf) {
		try {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("title", "Sales Commission Report");
			map.put("invDateFrom", (Date) searchParamVO.getObj1());
			map.put("invDateTo", (Date) searchParamVO.getObj2());
			map.put("isExcel", xlsOrPDf.equals("XLS"));
			
			Map<String, Object> params = new HashMap<>();
			params.put("idCompany", getSessionInfoBean().getCompanyVO().getId());
			params.put("depDateFr", (Date) searchParamVO.getObj1());
			params.put("depDateTo", (Date) searchParamVO.getObj2());
			params.put("dateFrom", (Date) searchParamVO.getObj3());
			params.put("dateTo", (Date) searchParamVO.getObj4());
			params.put("hideCreditNote", hideCreditNote);
			
			if (CollectionUtils.isNotEmpty(idDepartmentList)) 
				params.put("idDepartmentList", idDepartmentList);
			
			if (CollectionUtils.isNotEmpty(idStaffDepartmentList))
				params.put("idStaffDepartmentList", idStaffDepartmentList);
			
			if (CollectionUtils.isNotEmpty(idOrderSourceList))
				params.put("idOrderSourceList", idOrderSourceList);
			
			if (CollectionUtils.isNotEmpty(idOrderSourceList))
				params.put("idOrderSourceList", idOrderSourceList);
			
			if (CollectionUtils.isNotEmpty(idSalesPersonList))
				params.put("idSalesPersonList", idSalesPersonList);
			
			if (CollectionUtils.isNotEmpty(idRefSalesPersonList))
				params.put("idRefSalesPersonList", idRefSalesPersonList);
			
			List<SalesCommissionReportVO> reportList = invoiceReportBO.getSalesCommissionReportList(params);
			
			if (CollectionUtils.isEmpty(reportList)) throw new BusinessException("ERR_NO_RESULT");
			
			String jasperFileName = CommonConstant.JAS_RPT_SALES_COMM;
			String reportName = CommonConstant.PDF_RPT_SALES_COMM;

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(reportList, map, jasperFileName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				//Convert cell type as report (eg: '350.00 to 350.00)
				jasperPrint.setProperty("net.sf.jasperreports.export.xls.detect.cell.type", "true");
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	/*******************
	 * Getter & Setter *
	 *******************/
	
	/**
	 * @return the employeeList
	 */
	public List<EmployeeViewVO> getEmployeeList() {
		return employeeList;
	}

	/**
	 * @param employeeList the employeeList to set
	 */
	public void setEmployeeList(List<EmployeeViewVO> employeeList) {
		this.employeeList = employeeList;
	}

	public List<String> getIdOrderSourceList() {
		return idOrderSourceList;
	}

	public void setIdOrderSourceList(List<String> idOrderSourceList) {
		this.idOrderSourceList = idOrderSourceList;
	}

	public List<String> getIdDepartmentList() {
		return idDepartmentList;
	}

	public void setIdDepartmentList(List<String> idDepartmentList) {
		this.idDepartmentList = idDepartmentList;
	}

	public List<SalesCommConfigVO> getSalesCommConfigList() {
		return salesCommConfigList;
	}

	public void setSalesCommConfigList(List<SalesCommConfigVO> salesCommConfigList) {
		this.salesCommConfigList = salesCommConfigList;
	}

	public List<String> getIdStaffDepartmentList() {
		return idStaffDepartmentList;
	}

	public void setIdStaffDepartmentList(List<String> idStaffDepartmentList) {
		this.idStaffDepartmentList = idStaffDepartmentList;
	}

	public List<String> getIdSalesPersonList() {
		return idSalesPersonList;
	}

	public void setIdSalesPersonList(List<String> idSalesPersonList) {
		this.idSalesPersonList = idSalesPersonList;
	}

	public List<String> getIdRefSalesPersonList() {
		return idRefSalesPersonList;
	}

	public void setIdRefSalesPersonList(List<String> idRefSalesPersonList) {
		this.idRefSalesPersonList = idRefSalesPersonList;
	}

	public boolean isHideCreditNote() {
		return hideCreditNote;
	}

	public void setHideCreditNote(boolean hideCreditNote) {
		this.hideCreditNote = hideCreditNote;
	}
}
