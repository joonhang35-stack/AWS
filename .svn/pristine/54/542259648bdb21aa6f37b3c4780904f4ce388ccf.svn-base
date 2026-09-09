package com.bcs.zsg.maintenance.web.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.bo.ChartOfAcctBO;
import com.bcs.zsg.acct.service.AccountService;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.AppSettingBO;
import com.bcs.zsg.maintenance.helper.ConstantAppSetting;
import com.bcs.zsg.maintenance.vo.AppSettingVO;

public class POSUploadConfigBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient AppSettingBO appSettingBO;
	@Autowired
	private transient ChartOfAcctBO chartOfAcctBO;
	@Autowired
	private transient AccountService accountService;
	
	private List<AppSettingVO> appSettingVOList;
	private List<AcctViewVO> acctViewVOList;
	private List<AcctVO> accountExcludeVOList;
	
	private SimpleDateFormat dateFormat;
	
	private String isTest;
	private Date schedulerTime;
	
	@Override
	public void resetForm() {
		isTest = "";
		schedulerTime = new Date();
		dateFormat = new SimpleDateFormat("HH:mm");
	}
	
	public void init() {
		resetForm();
		loadAccountList();
		loadAppSettingData();
	}
	
	public void loadAccountList() {
		try {
			acctViewVOList = chartOfAcctBO.getAcctViewList(getSessionInfoBean().getCompanyVO().getId(), null);
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void loadAppSettingData() {
		try {
			appSettingVOList = appSettingBO.getAppSettingList(ConstantAppSetting.MODULE_MAINT.getValue());
			
			for (AppSettingVO vo : appSettingVOList) {
				if (StringUtils.equals(vo.getModule().concat("_").concat(vo.getCode()), ConstantAppSetting.MAINT_POS_CONFIG_UPLOAD_AS_TEST.getValue())) {
					isTest = vo.getValue();
					
				} else if (StringUtils.equals(vo.getModule().concat("_").concat(vo.getCode()), ConstantAppSetting.MAINT_POS_CONFIG_SCHEDULER_TIME_UPLOAD.getValue())) {
					schedulerTime = dateFormat.parse(vo.getValue());
					
				} else if (StringUtils.equals(vo.getModule().concat("_").concat(vo.getCode()), ConstantAppSetting.MAINT_POS_CONFIG_GL_ACCT_TO_EXCLUDE.getValue())) {
					accountExcludeVOList = new ArrayList<>();
					
					if (StringUtils.isNotBlank(vo.getValue())) {
						List<String> acctExcludeList = Arrays.asList(vo.getValue().split(","));
						
						for (String acctExclude : acctExcludeList) {
							AcctVO acctVO = accountService.getAcctVO(Long.valueOf(acctExclude));
							
							if (acctVO != null) accountExcludeVOList.add(acctVO);
						}
					}
				}
			}
		} catch (BusinessException | ParseException e) {
			errorResult(e);
		}
	}
	
	public void save() {
		try {
			for (AppSettingVO vo : appSettingVOList) {
				if (StringUtils.equals(vo.getModule().concat("_").concat(vo.getCode()), ConstantAppSetting.MAINT_POS_CONFIG_UPLOAD_AS_TEST.getValue())) {
					vo.setValue(isTest);
					
				} else if (StringUtils.equals(vo.getModule().concat("_").concat(vo.getCode()), ConstantAppSetting.MAINT_POS_CONFIG_SCHEDULER_TIME_UPLOAD.getValue())) { 
					vo.setValue(dateFormat.format(schedulerTime));
					
				} else if (StringUtils.equals(vo.getModule().concat("_").concat(vo.getCode()), ConstantAppSetting.MAINT_POS_CONFIG_GL_ACCT_TO_EXCLUDE.getValue())) {
					List<String> tempList = new ArrayList<>();
					for (AcctVO acctVO : accountExcludeVOList) {
						tempList.add(acctVO.getId().toString());
					}
					vo.setValue(StringUtils.join(tempList, ","));
				}
			}
			
			appSettingBO.update(appSettingVOList);
			successResult();
			loadAppSettingData();
			
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public void handleAccountSelect(SelectEvent event) throws BusinessException{
		try {
			AcctVO vo = (AcctVO) event.getObject();
			
			boolean found = false;
			for (AcctVO tempVO : accountExcludeVOList) {
				if(tempVO.getId().toString().equals(vo.getId().toString())) {
					found = true;
					break;
				}
			}
			
			if (!found) {
				accountExcludeVOList.add(vo);
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void removeAccount(AcctVO acctVO) {
		accountExcludeVOList.remove(acctVO);
	}
	
	public String getIsTest() {
		return isTest;
	}

	public void setIsTest(String isTest) {
		this.isTest = isTest;
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

	public List<AcctVO> getAccountExcludeVOList() {
		return accountExcludeVOList;
	}

	public void setAccountExcludeVOList(List<AcctVO> accountExcludeVOList) {
		this.accountExcludeVOList = accountExcludeVOList;
	}

	public List<AcctViewVO> getAcctViewVOList() {
		return acctViewVOList;
	}

	public void setAcctViewVOList(List<AcctViewVO> acctViewVOList) {
		this.acctViewVOList = acctViewVOList;
	}
}
