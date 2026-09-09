package com.bcs.zsg.scheduler;

import java.io.IOException;
import java.net.ConnectException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.bcs.zsg.acct.bo.ChartOfAcctBO;
import com.bcs.zsg.acct.bo.EInvoiceBO;
import com.bcs.zsg.acct.bo.EInvoiceSchedulerBO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.DatesUtils;
import com.bcs.zsg.common.helper.EInvoiceConstant;
import com.bcs.zsg.common.helper.EInvoiceUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseContext;
import com.bcs.zsg.maintenance.bo.AppSettingBO;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.bo.MalaysiaStateBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.product.bo.InvoiceAndExchangeOrderBO;
import com.bcs.zsg.sales.bo.InvoiceBO;
import com.bcs.zsg.sales.helper.SalesConstant;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.web.object.EInvoiceDocument;

public class SubmitEInvoiceScheduler extends AppBackingBean implements Job {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient InvoiceBO invoiceBO;
	@Autowired
	private transient EInvoiceBO eInvoiceBO;
	@Autowired
	private transient EInvoiceSchedulerBO eInvoiceSchedulerBO;
	@Autowired
	private transient InvoiceAndExchangeOrderBO invoiceAndExchangeOrderBO;
	@Autowired
	private transient RegionBO regionBO;
	@Autowired
	private transient MalaysiaStateBO malaysiaStateBO;
	@Autowired
	private transient ChartOfAcctBO chartOfAcctBO;
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	@Autowired
	private transient MessageSource messageSource;
	@Autowired
	private transient AppSettingBO appSettingBO;
	
	public SubmitEInvoiceScheduler() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public void resetForm() {}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		if (StringUtils.isBlank(BaseContext.getUserFullName())) {
			BaseContext.setUserFullName("Scheduler");
		}
		
		System.out.println("SubmitEInvoiceScheduler.execute() @ " + new Date());
		TrackingLogUtils trackingLogUtils = new TrackingLogUtils(getClass());
		trackingLogUtils.startLogs();
		try {
			Map<String, Object> map = generateSearchParams();
			
			List<InvoiceVO> invoiceList = invoiceBO.getInvoiceListToSubmitEInvoice(map, new Date());
//			List<InvoiceVO> invoiceList = invoiceBO.getInvoiceListToSubmitEInvoice(map, getTestingDate());
			System.out.println("CYY invoiceList.size(): " + invoiceList.size());
			System.out.println("CYY getInvoiceListToSubmitEInvoice.size(): " + invoiceList.size());
//			invoiceList.clear();
			
//			printInvoiceList(invoiceList);
			
			eInvoiceSchedulerBO.insertPendingEInvoice(invoiceList);
			
			List<InvoiceVO> resList = processPendingEInvoiceByDate(DatesUtils.getDayTimeStart(new Date()).getTime());
			
//			eInvoiceSchedulerBO.updatePendingEInvoiceStatus(resList);
			
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			trackingLogUtils.endLogs("SubmitEInvoiceScheduler.execute()");
		}
	}
	
	private List<InvoiceVO> processPendingEInvoiceByDate(Date dtProcess) throws NoSuchAlgorithmException, IOException, JSONException, Exception {
		
		List<InvoiceVO> invoiceList = new ArrayList<InvoiceVO>();
		invoiceList = eInvoiceSchedulerBO.getPendingInvoiceForEInvoiceByDate(dtProcess);
		
		if (CollectionUtils.isEmpty(invoiceList)) {
			return new ArrayList<InvoiceVO>();
		}
		
		List<InvoiceVO> resList = new ArrayList<InvoiceVO>();
		
		// process 50 by 50
		int batchSize = 50;
		for (int i = 0; i < invoiceList.size(); i += batchSize) {
		    int end = Math.min(i + batchSize, invoiceList.size());
		    List<InvoiceVO> batch = invoiceList.subList(i, end);
		    processPendingEInvoiceByBatch(batch);
		    resList.addAll(batch);
		    
		    // update pending e invoice status
		    eInvoiceSchedulerBO.updatePendingEInvoiceStatus(batch);
		    
		    // send email to notify fail e-invoice
		    eInvoiceSchedulerBO.notifyNonSubmittedEInvoice(batch);
		}
			
//		printResult(resList);
		
		return resList;
	}
	
	private List<InvoiceVO> processPendingEInvoiceByBatch(List<InvoiceVO> invoiceList) throws NoSuchAlgorithmException, IOException, JSONException, Exception {
		
		EInvoiceUtils eInvoiceUtils = new EInvoiceUtils(eInvoiceBO, invoiceBO, invoiceAndExchangeOrderBO, regionBO, malaysiaStateBO, chartOfAcctBO, corporateProfileBO);
		eInvoiceUtils.setMessageSource(messageSource);
//		CompanyVO companyVO = corporateProfileBO.getCompanyDetails(getSessionInfoBean().getCompanyVO().getId());
		CompanyVO companyVO = corporateProfileBO.getCompanyDetails(1L);
//		if (isEInvSubm) {
			JSONObject submitResponse = null;
			try {
				submitResponse = eInvoiceBO.submitInvoices(invoiceList, companyVO.getId());
			} catch (BusinessException e) {
				e.printStackTrace();
				for (InvoiceVO vo : invoiceList) {
					if (vo.geteInvoiceDocument() == null)	vo.seteInvoiceDocument(new EInvoiceDocument());
					if (StringUtils.isBlank(vo.geteInvoiceDocument().getStatus())) {
						vo.geteInvoiceDocument().setStatus(EInvoiceConstant.E_INV_STATUS_REJECTED);
						vo.geteInvoiceDocument().setDocumentStatusReason("Unknown error. Please contact IT.");
					}
				}
				return invoiceList;
			} catch (ConnectException e) {
				e.printStackTrace();
				
				System.out.println("SubmitEInvoiceScheduler network error: batchSize=" + invoiceList.size() + ", message=" + e.getMessage());
				TrackingLogUtils.printLogs("CYY SubmitEInvoiceScheduler processPendingEInvoiceByBatch ConnectException");
				
				for (InvoiceVO vo : invoiceList) {
					if (vo.geteInvoiceDocument() == null)	vo.seteInvoiceDocument(new EInvoiceDocument());
					if (StringUtils.isBlank(vo.geteInvoiceDocument().getStatus())) {
						vo.geteInvoiceDocument().setStatus(EInvoiceConstant.E_INV_STATUS_REJECTED);
						vo.geteInvoiceDocument().setDocumentStatusReason("Connection timeout.");
					}
				}
				return invoiceList;
			}
			
			try {
				EInvoiceUtils.checkResponseErr(submitResponse);
			} catch (BusinessException e) {
				e.printStackTrace();
				for (InvoiceVO vo : invoiceList) {
					if (vo.geteInvoiceDocument() == null)	vo.seteInvoiceDocument(new EInvoiceDocument());
					if (StringUtils.isBlank(vo.geteInvoiceDocument().getStatus())) {
						vo.geteInvoiceDocument().setStatus(EInvoiceConstant.E_INV_STATUS_REJECTED);
						vo.geteInvoiceDocument().setDocumentStatusReason("Unknown error. Please contact IT.");
					}
				}
				return invoiceList;
			}
			eInvoiceUtils.processSubmitResponseInvoices(submitResponse, invoiceList, companyVO.getId());
//		}
			
		return invoiceList;
	}
	
	private Map<String, Object> generateSearchParams() throws BusinessException, ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		
//		params.put("companyId", getSessionInfoBean().getCompanyVO().getId());
		params.put("companyId", 1L); // default company (or loop company?)
		
		params.put("docTypeCd", SalesConstant.DOC_TYPE_CD_INVC); 
//		params.put("eInvoiceStatus", EInvoiceConstant.E_INV_STATUS_NOT_SUBMIT); 
		params.put("eInvoiceStatusList", Arrays.asList(EInvoiceConstant.E_INV_STATUS_NOT_SUBMIT, EInvoiceConstant.E_INV_STATUS_INVALID)); 
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(CommonConstant.STATUS_CD_INVOICED);
		statusList.add(CommonConstant.STATUS_CD_DEPOSIT_PAID);
		statusList.add(CommonConstant.STATUS_CD_FULL_PAYMENT);
		statusList.add(CommonConstant.STATUS_CD_CANCELLED);
		params.put("statusList", statusList);
		
//		Date depDateFr = new Date();
//		Date depDateTo = new Date();
//		depDateFr = DatesUtils.getPreviousDate(depDateTo, 10).getTime();
		
		// testing
//		Date depDateTo = getTestingDate();
//		Date depDateFr = getTestingDate();
//		Date depDateFr = DatesUtils.getPreviousDate(depDateTo, 30).getTime();
		
//		System.out.println("CYY depDateFr: " + depDateFr);
//		System.out.println("CYY depDateTo: " + depDateTo);
		
//		params.put("depDateFr", depDateFr);
//		params.put("depDateTo", depDateTo);
		
//		params.put("invDateFr", depDateFr);
//		params.put("invDateTo", depDateTo);
		
		return params;
	}
	
	// testing functions //
	
	private Date getTestingDate() throws ParseException, BusinessException {
//		String dateStr = appSettingBO.getAppSettingByCode(ConstantAppSetting.MAINT_AUTO_E_INV_CONFIG_DEP_DT).getValue();
//		
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sdf.parse(dateStr);
//        
//        return date;
		return new Date();
	}
	
	private void printInvoiceList(List<InvoiceVO> list) {
		System.out.println("SubmitEInvoiceScheduler.printInvoiceList()");
		for (InvoiceVO vo: list) {
			System.out.println(vo.getCode());
		}
		System.out.println();
	}
	
	private void printResult(List<InvoiceVO> list) {
		System.out.println("SubmitEInvoiceScheduler.printResult()");
		for (InvoiceVO vo: list) {
			System.out.println(vo.getCode() + "\t" + vo.geteInvoiceDocument().getStatus() + "\t" + vo.geteInvoiceDocument().getDocumentStatusReason());
		}
		System.out.println();
	}

}
