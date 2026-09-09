

package com.bcs.zsg.sales.web.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JasperPrint;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.LookUpBO;
import com.bcs.zsg.maintenance.bo.SystemNumberGenerationBO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.sales.bo.DebtorsStmtBO;
import com.bcs.zsg.sales.vo.DebtorsStmtVO;

public class DebtorOutstandingBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient DebtorsStmtBO debtorsStmtBO;
	@Autowired
	private transient SystemNumberGenerationBO systemNumberGenerationBO;
	@Autowired
	private transient LookUpBO lookUpBo;
	
	private LazyDataModel<DebtorsStmtVO> ldmDebtorsList;
	private List<DebtorsStmtVO> debtorsOutstandingList;
	private List<LookupItemVO> dueDaysList;
	
	private String prefixValue;
	private String exportFileName;
	
	@Override
	public void resetForm() {
		searchParamVO = new SearchParamVO();
		exportFileName="DebtorsOutstanding";
	
	}

	public void init() throws BusinessException {
		initSearchParam();
		resetForm();
		searchParamVO.setFromDate(new Date());
		searchParamVO.setToDate(searchParamVO.getFromDate());
		
		loadDebtorsOutstanding();
		SystemNumberGenerationVO invSNGVO;
		invSNGVO = systemNumberGenerationBO.getSystemNumberGeneration(CommonConstant.SYS_NUM_CD_INVC, this.getSessionInfoBean().getCompanyVO().getId());
		invSNGVO.setCode(CommonConstant.SYS_NUM_CD_INVC);
		setPrefixValue(invSNGVO.getPrefixid());
		
		
	}
	
	private void loadDebtorsOutstanding() throws BusinessException {
		try {
			ldmDebtorsList=new LazyDebtorsDataModel();
			//resetForm();
			dueDaysList=lookUpBo.getLookUpCodeList("deq_prd");
			
		}catch (Throwable t) {
			errorResult(t);
		}
	}
	
	
	
	/**********************
	 * Lazy loading model *
	 **********************/
	class LazyDebtorsDataModel extends LazyDataModel<DebtorsStmtVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<DebtorsStmtVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("companyId", getSessionInfoBean().getCompanyVO().getId());
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				params.put("searchParam", searchParamVO);
				setRowCount(debtorsStmtBO.getDebtorsListSize(params));
				if (getRowCount() > 0){return debtorsStmtBO.getDebtorsList(params);}
				
			} catch (Throwable t) {
				errorResult(t);
			}
			return null;
		}
		
	}
	
	/**
	 * Search function
	 */
	public void search() {
		try {
			searchParamVO.setObj2("search");
			final DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("debtorsForm:idDebtorOutstandingTable");
			d.setFirst(0);
			loadDebtorsOutstanding();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM");
		    exportFileName = "DebtorsOutstanding_" + dateFormat.format(searchParamVO.getFromDate()) +"_"+dateFormat.format(searchParamVO.getToDate())
		        					+(searchParamVO.getObj1() ==null  ? "" : "_" + (String)searchParamVO.getObj1());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public void printDebtorsOutstandingSalesReport() {
		try {
			debtorsOutstandingList=new ArrayList<DebtorsStmtVO>();

			Map<String, Object> paramsPrint = new HashMap<String, Object>();
			paramsPrint.put("companyId", getSessionInfoBean().getCompanyVO().getId());
			paramsPrint.remove("first");
			paramsPrint.put("first", 0);
			paramsPrint.remove("pageSize");
			paramsPrint.put("pageSize", 10000000);	//Use max pagesize for query
			paramsPrint.put("sortField", null);
			paramsPrint.put("sortOrder", CommonConstant.SORT_ASC);
			paramsPrint.put("filters", new HashMap<String, String>());
			paramsPrint.put("searchParam", searchParamVO);
			debtorsOutstandingList=debtorsStmtBO.getDebtorsList(paramsPrint);
			SimpleDateFormat ftDate = new SimpleDateFormat("MMMMM yyyy");
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("companyName", getSessionInfoBean().getCompanyVO().getName());
			map.put("fromDate", ftDate.format(searchParamVO.getFromDate()));
			map.put("toDate", ftDate.format(searchParamVO.getToDate()));
			JasperPrint jasperPrint = ReportUtils.getJasperPrint(debtorsOutstandingList, map, CommonConstant.JAS_RPT_DEBTORS_OUTSTANDING_SALES);
			ReportUtils.printReport(jasperPrint, CommonConstant.PDF_RPT_DEBTORS_OUTSTANDING_SALES);
			debtorsOutstandingList.clear();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void postProcessXLS(Object document) throws IOException {
    }

	public String getPrefixValue() {
		return prefixValue;
	}

	public void setPrefixValue(String prefixValue) {
		this.prefixValue = prefixValue;
	}


	public String getExportFileName() {
		return exportFileName;
	}

	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}

	public List<DebtorsStmtVO> getDebtorsOutstandingList() {
		return debtorsOutstandingList;
	}

	public void setDebtorsOutstandingList(List<DebtorsStmtVO> debtorsOutstandingList) {
		this.debtorsOutstandingList = debtorsOutstandingList;
	}

	public LazyDataModel<DebtorsStmtVO> getLdmDebtorsList() {
		return ldmDebtorsList;
	}

	public void setLdmDebtorsList(LazyDataModel<DebtorsStmtVO> ldmDebtorsList) {
		this.ldmDebtorsList = ldmDebtorsList;
	}

	public List<LookupItemVO> getDueDaysList() {
		return dueDaysList;
	}

	public void setDueDaysList(List<LookupItemVO> dueDaysList) {
		this.dueDaysList = dueDaysList;
	}
}

