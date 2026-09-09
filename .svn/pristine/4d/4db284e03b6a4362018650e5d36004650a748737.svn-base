package com.bcs.zsg.maintenance.web.bean;

import static com.bcs.zsg.core.helper.BaseConstant.PAD_UNDERSCORE;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.bo.PendingJournalBO;
import com.bcs.zsg.acct.service.PendingJournalServiceImpl;
import com.bcs.zsg.acct.vo.PendingReverseJournalVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.SchedulerUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.security.vo.UserRoleViewVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.AppSettingBO;
import com.bcs.zsg.maintenance.helper.ConstantAppSetting;
import com.bcs.zsg.maintenance.vo.AppSettingVO;
import com.bcs.zsg.sales.helper.SalesConstant;
import com.bcs.zsg.scheduler.helper.EnumJobKey;

public class JournalConfigBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient PendingJournalBO pendingJournalBO;
	@Autowired
	private transient AppSettingBO appSettingBO;
	
	private LazyDataModel<PendingReverseJournalVO> lazyDataModel;
	
	private List<AppSettingVO> appSettingVOList;
	
	private SelectItem[] statusListItems;
	
	private SimpleDateFormat dateFormat;
	
	// config value
	private String isScheduled;
	private Date schedulerTime;
	
	// access control
	private boolean isJournalConfigRole = false;
	
	@Override
	public void resetForm() {
		isScheduled = "";
		schedulerTime = new Date();
		dateFormat = new SimpleDateFormat("HH:mm");
	}
	
	public void init() {
		resetForm();
		
		loadAppSettingData();
		
		try {
			loadAuthority();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
		
		lazyDataModel = new LazyPendingJournalDataModel();
	}
	
	public void loadAppSettingData() {
		try {
			appSettingVOList = appSettingBO.getAppSettingList(ConstantAppSetting.MODULE_MAINT.getValue());
			
			for (AppSettingVO vo : appSettingVOList) {
				if (StringUtils.equals(vo.getModule().concat("_").concat(vo.getCode()), ConstantAppSetting.MAINT_JOURNAL_CONFIG_SCHEDULER_TIME_PROCESS_REVERSE_SALES.getValue())) {
					schedulerTime = dateFormat.parse(vo.getValue());
				} else if (StringUtils.equals(vo.getModule().concat("_").concat(vo.getCode()), ConstantAppSetting.MAINT_REVERSE_SALES_JOURNAL_SCHEDULER.getValue())) {
					isScheduled = vo.getValue();
				}
			}
		} catch (BusinessException | ParseException e) {
			e.printStackTrace();
			errorResult(e);
		}
	}
	
	/**
	 * Load access control
	 * @throws BusinessException
	 */
	private void loadAuthority() throws BusinessException {
		
		List<UserRoleViewVO> userRoleList = this.getUserInfo().getRoleList();
		
		if (CollectionUtils.isNotEmpty(userRoleList)) {
			for (UserRoleViewVO userRoleVO : userRoleList) {
				if (userRoleVO.getRoleCode().equals(SalesConstant.JOURNAL_CONFIG_ROLE)) {
					isJournalConfigRole = true;
				} 
			}
		}
	}
	
	public void saveAppSettingData() {
		try {
			for (AppSettingVO vo : appSettingVOList) {
				if (StringUtils.equals(vo.getModule().concat("_").concat(vo.getCode()), ConstantAppSetting.MAINT_JOURNAL_CONFIG_SCHEDULER_TIME_PROCESS_REVERSE_SALES.getValue())) { 
					vo.setValue(dateFormat.format(schedulerTime));
				} else if (StringUtils.equals(vo.getModule().concat("_").concat(vo.getCode()), ConstantAppSetting.MAINT_REVERSE_SALES_JOURNAL_SCHEDULER.getValue())) {
					vo.setValue(isScheduled);
				}
			}
			
			appSettingBO.update(appSettingVOList);
			
			for (AppSettingVO vo : appSettingVOList) {
				// Scheduler - Reverse Journal
				if (StringUtils.equals(vo.getCode(), ConstantAppSetting.MAINT_JOURNAL_CONFIG_SCHEDULER_TIME_PROCESS_REVERSE_SALES.getValue().split(PAD_UNDERSCORE, 2)[1])) {
	//				String time = vo.getValue();
	//				String timePattern = "";
	//				if (StringUtils.isNotBlank(time)) {
	//					String[] timeSplitList = time.split(":");
	//					timePattern = "0 " + (StringUtils.equals(timeSplitList[1], "00") ? "0" : timeSplitList[1]) + " " + timeSplitList[0] + " 1/1 * ? *";
	//				}
	//				
	//				try {
	//					SchedulerApp.getInstance().removeJob(EnumJobKey.ProcessReverseJournal, TriggerType.Schedule.toString(), "ProcessReverseSalesJournalDailySchedule");
	//					SchedulerApp.getInstance().scheduledJob(EnumJobKey.ProcessReverseJournal, 
	//							TriggerType.Schedule, PendingJournalServiceImpl.class, "ProcessReverseSalesJournalDailySchedule", timePattern);
	//				} catch (Exception e) { 
	//					e.printStackTrace();
	//				}
					
//					SchedulerApp.getInstance().removeJob(EnumJobKey.ScheduleManager, TriggerType.Schedule.toString(), "POSUploadDailySchedule");

					if ("Y".equals(appSettingBO.getAppSettingByCode(ConstantAppSetting.MAINT_REVERSE_SALES_JOURNAL_SCHEDULER).getValue())) {
						SchedulerUtils.scheduleJob(vo.getValue(), EnumJobKey.ProcessReverseJournal, PendingJournalServiceImpl.class, "ProcessReverseSalesJournalDailySchedule");
					} else {
						SchedulerUtils.removeJob(EnumJobKey.ProcessReverseJournal, "ProcessReverseSalesJournalDailySchedule");
					}
					
				}
			}
			
			successResult();
			loadAppSettingData();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void processReverseJournal() {
		TrackingLogUtils trackingLogUtils = new TrackingLogUtils(getClass());
		trackingLogUtils.startLogs();
		try {
			
			PendingJournalServiceImpl journalServiceImpl = new PendingJournalServiceImpl();
			journalServiceImpl.execute(null);
			
			successResult();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("processReversePastInvoice");
		}
	}
	
	private Map<String, Object> generateSearchParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		
//		params.put("idCompany", getSessionInfoBean().getCompanyVO().getId());
		
//		if (StringUtils.isNotBlank(orderSourceCode))	params.put("orderSourceCode", orderSourceCode);
//		if (ArrayUtils.isNotEmpty(paymentStatusList))	params.put("paymentStatusList", paymentStatusList);
//		if (ArrayUtils.isNotEmpty(eInvoiceStatusList))	params.put("eInvoiceStatusList", eInvoiceStatusList);
//		if (fromDate != null)							params.put("fromDate", fromDate);
//		if (toDate != null)								params.put("toDate", toDate);
//		if (departureDateFr != null)					params.put("departureDateFr", departureDateFr);
//		if (departureDateTo != null)					params.put("departureDateTo", departureDateTo);
		
		return params;
	}
	
	/**********************
	 * Lazy loading model *
	 **********************/
	class LazyPendingJournalDataModel extends LazyDataModel<PendingReverseJournalVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<PendingReverseJournalVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
			
			TrackingLogUtils trackingLogUtils = new TrackingLogUtils(getClass());
			
			try {
				
				trackingLogUtils.startLogs();
				
				Map<String, Object> params = generateSearchParams();
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);
				
				setRowCount(pendingJournalBO.getPendingReverseJournalListSize(params));
//				System.out.println("CYY getRowCount(): " + getRowCount());
				
				if (0 < getRowCount()) {
//					invoiceList = invoiceBO.getCreditNoteDisplayList(params);
					return pendingJournalBO.getPendingReverseJournalList(params);
				}
				
			} catch (Throwable t) {
				t.printStackTrace();
				errorResult(t);
			} finally {
				trackingLogUtils.endLogs("LazyPendingJournalDataModel");
			}
			return new ArrayList<PendingReverseJournalVO>();
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
	
	public String getIsScheduled() {
		return isScheduled;
	}

	public void setIsScheduled(String isScheduled) {
		this.isScheduled = isScheduled;
	}

	public Date getSchedulerTime() {
		return schedulerTime;
	}

	public void setSchedulerTime(Date schedulerTime) {
		this.schedulerTime = schedulerTime;
	}

	public List<AppSettingVO> getAppSettingVOList() {
		return appSettingVOList;
	}

	public void setAppSettingVOList(List<AppSettingVO> appSettingVOList) {
		this.appSettingVOList = appSettingVOList;
	}

	public LazyDataModel<PendingReverseJournalVO> getLazyDataModel() {
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<PendingReverseJournalVO> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public boolean getIsJournalConfigRole() {
		return isJournalConfigRole;
	}

	public void setJournalConfigRole(boolean isJournalConfigRole) {
		this.isJournalConfigRole = isJournalConfigRole;
	}
	
	public SelectItem[] getStatusListItems() {
		SelectItem[] options = new SelectItem[4];  
		  
        options[0] = new SelectItem("", "All");  
        options[1] = new SelectItem(CommonConstant.EMAIL_PMNT_PENDING, "Pending");  
        options[2] = new SelectItem(CommonConstant.EMAIL_PMNT_SUCCESS, "Success");  
        options[3] = new SelectItem(CommonConstant.EMAIL_PMNT_FAILED, "Fail");  
        
        return options;
	}
}
