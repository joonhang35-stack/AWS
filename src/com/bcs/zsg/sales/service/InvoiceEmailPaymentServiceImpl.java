package com.bcs.zsg.sales.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.mail.service.EmailingService;
import com.bcs.zsg.maintenance.helper.ConstantAppSetting;
import com.bcs.zsg.maintenance.service.AppSettingService;
import com.bcs.zsg.maintenance.vo.AppSettingVO;
import com.bcs.zsg.sales.dao.InvoiceEmailPaymentDAO;
import com.bcs.zsg.sales.vo.InvoiceEmailPaymentVO;

public class InvoiceEmailPaymentServiceImpl implements InvoiceEmailPaymentService, Job {

	@Autowired
	private InvoiceEmailPaymentDAO invoiceEmailPaymentDAO;
	
	@Autowired
	private EmailingService emailingService;
	
	@Autowired
	private AppSettingService appSettingService;
	
	@Override
	public int getInvoiceEmailPaymentListSize(Map<String, Object> params) throws BusinessException {
		return invoiceEmailPaymentDAO.getInvoiceEmailPaymentListSize(params);
	}
	
	@Override
	public List<InvoiceEmailPaymentVO> getInvoiceEmailPaymentList(Map<String, Object> params) throws BusinessException {
		return invoiceEmailPaymentDAO.getInvoiceEmailPaymentList(params);
	}

	@Override
	public InvoiceEmailPaymentVO getInvoiceEmailPayment(Map<String, Object> params) throws BusinessException {
		return invoiceEmailPaymentDAO.getInvoiceEmailPayment(params);
	}
	
	@Override
	public void updateInvoiceEmailPayment(InvoiceEmailPaymentVO vo, UserVO userVO) throws BusinessException {
		if (vo.getId() != null) {
			invoiceEmailPaymentDAO.update(vo);
		} else {
			vo.setPaymentStatus(CommonConstant.EMAIL_PMNT_PENDING);
			vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
			
			AppSettingVO appSettingVO = appSettingService.getAppSettingByCode(ConstantAppSetting.MAINT_IPAY88_EXPIRY_DURATION);
			if (appSettingVO != null) {
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(new Date());
			    cal.add(Calendar.HOUR, Integer.parseInt(appSettingVO.getValue()));
				vo.setDtExpiry(cal.getTime());
			}
			invoiceEmailPaymentDAO.insert(vo);
			
			// Send email
			emailingService.sendEmailPaymentEmail(vo, userVO);
		}
	}
	
	@Override
	public void updateInvoiceEmailPaymentBySystem(InvoiceEmailPaymentVO vo) throws BusinessException {
		if (vo.getId() != null) {
			invoiceEmailPaymentDAO.update(vo, "SYSTEM");
		}
	}
	
	@Override
	public void deleteInvoiceEmailPayment(InvoiceEmailPaymentVO vo) throws BusinessException {
		vo.setStatusCode(BaseConstant.STATUS_DELETED);
		invoiceEmailPaymentDAO.update(vo);
	}
	
	// Scheduler Run Task
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//System.out.println("Scheduler Checking On expired email payment request");
		
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			
			if (invoiceEmailPaymentDAO == null)
				SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
			
			List<InvoiceEmailPaymentVO> invoiceEmailPaymentVOList = getInvoiceEmailPaymentList(params);
			
			for (InvoiceEmailPaymentVO vo : invoiceEmailPaymentVOList) {
				if (new Date().after(vo.getDtExpiry()) && !StringUtils.equals(vo.getPaymentStatus(), "S")) {
					vo.setPaymentStatus("E");
					updateInvoiceEmailPaymentBySystem(vo);
				}
			}
		} catch (BusinessException e) {
			
		}
	}
	
	
}
