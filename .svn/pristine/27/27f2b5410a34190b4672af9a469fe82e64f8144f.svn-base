package com.bcs.zsg.scheduler;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.bcs.zsg.acct.bo.ChartOfAcctBO;
import com.bcs.zsg.acct.vo.EInvoiceSchedulerLogVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;

public class BaseScheduler extends AppBackingBean {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient ChartOfAcctBO chartOfAcctBO;
	
	public BaseScheduler() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public void resetForm() {}
	
	protected EInvoiceSchedulerLogVO createLog(Long idCompany, String schedulerName, Date dtExec) throws BusinessException {
		EInvoiceSchedulerLogVO logVO = new EInvoiceSchedulerLogVO();
		logVO.setIdCompany(idCompany);
		logVO.setSchedulerName(schedulerName);
		logVO.setDtExec(new Date());
		logVO.setDocCnt(0);
		logVO.setRemarks("");
		logVO.setStatusCode(CommonConstant.EMAIL_PMNT_PENDING);
		chartOfAcctBO.insertVO(logVO);
		
		return logVO;
	}
	
	protected EInvoiceSchedulerLogVO createLog(Long idCompany, String schedulerName) throws BusinessException {
		return createLog(idCompany, schedulerName, new Date());
	}
	
	protected void updateLog(EInvoiceSchedulerLogVO logVO, Integer execTime, Integer docCnt, String remarks, String statusCd) throws BusinessException {
		if (execTime != null)	logVO.setExecTime(execTime);
		if (docCnt != null)		logVO.setDocCnt(docCnt);
		if (StringUtils.isNotBlank(remarks))	logVO.setRemarks(remarks);
		if (StringUtils.isNotBlank(statusCd))	logVO.setStatusCode(statusCd);
		chartOfAcctBO.updateVO(logVO);
	}

	protected void updateLogVO(EInvoiceSchedulerLogVO logVO, Integer docCnt, String remarks) {
		if (docCnt != null)	logVO.setDocCnt(logVO.getDocCnt() + docCnt);
		if (StringUtils.isNotBlank(remarks))	logVO.setRemarks(logVO.getRemarks() + remarks);
	}
}
