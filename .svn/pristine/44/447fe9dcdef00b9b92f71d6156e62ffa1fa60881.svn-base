package com.bcs.zsg.scheduler;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.bcs.zsg.acct.bo.EInvoiceBO;
import com.bcs.zsg.acct.vo.EInvoiceSchedulerLogVO;
import com.bcs.zsg.acct.vo.EInvoiceSubmissionVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.EInvoiceConstant;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.core.helper.BaseContext;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class RefreshEInvoiceScheduler extends BaseScheduler implements Job {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient EInvoiceBO eInvoiceBO;
	@Autowired
	private transient CorporateProfileBO corporateProfileBO;
	
	public RefreshEInvoiceScheduler() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		if (StringUtils.isBlank(BaseContext.getUserFullName())) {
			BaseContext.setUserFullName("Refresh Scheduler");
		}
		
		System.out.println("RefreshEInvoiceScheduler.execute() @ " + new Date());
		TrackingLogUtils trackingLogUtils = new TrackingLogUtils(getClass());
		trackingLogUtils.startLogs();
		try {

			CompanyVO companyVO = corporateProfileBO.getCompanyDetails(1L);
			EInvoiceSchedulerLogVO logVO = createLog(companyVO.getId(), EInvoiceConstant.SCHEDULER_REFRESH);
			
			List<EInvoiceSubmissionVO> list = eInvoiceBO.getEInvoiceSubmissionByStatus(EInvoiceConstant.E_INV_SUB_STATUS_IN_PROGRESS, companyVO.getId());
			
			for (int j = 0; j < Math.min(5, list.size()); j++) {
				EInvoiceSubmissionVO vo = list.get(j);
				eInvoiceBO.getSubmission(vo.getSubmissionUid(), companyVO.getId());
				updateLogVO(logVO, vo.getDocCnt(), vo.getSubmissionUid() + ", ");
			}
			
			updateLog(logVO, trackingLogUtils.getCurrentDiff(), null, null, CommonConstant.EMAIL_PMNT_SUCCESS);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			trackingLogUtils.endLogs("RefreshEInvoiceScheduler.execute()");
		}
	}
}
