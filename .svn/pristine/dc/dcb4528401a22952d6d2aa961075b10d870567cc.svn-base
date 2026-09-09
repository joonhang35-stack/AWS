package com.bcs.zsg.scheduler;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.bcs.zsg.common.helper.CRMUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.core.helper.BaseContext;

public class PostingCRMSalesScheduler implements Job {

	private static final long serialVersionUID = 1L;
	
//	public PostingCRMSalesScheduler() {
//		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		if (StringUtils.isBlank(BaseContext.getUserFullName())) {
			BaseContext.setUserFullName("Post Sales Scheduler");
		}
		
		TrackingLogUtils trackingLogUtils = new TrackingLogUtils(getClass());
		trackingLogUtils.startLogs();
		
		try {
			CRMUtils crmUtils = new CRMUtils();
			crmUtils.postingSalesInvoice();
//			crmUtils.retrievedSalesInvoice(227280L);
			
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			trackingLogUtils.endLogs("PostingCRMSalesScheduler.execute()");
		}
	}
}
