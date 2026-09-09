package com.bcs.zsg.mail.bo;

import java.io.File;
import java.util.List;

import com.bcs.zsg.cfg.sec.vo.PasswordResetVO;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.FeedbackVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.purchase.vo.ExOrderVO;
import com.bcs.zsg.sales.vo.InvoiceEmailPaymentVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public interface EmailingBO {
	
	public void sendForgotPasswordEmail(PasswordResetVO vo, UserVO userVO) throws Exception;
	
	public void sendInvChangesBySpecificPerson(InvoiceVO invoiceVO, String prefixValue, List<String> emailList, List<String> invoiceChangesList, String otherTypeRemark, UserVO userVO) throws BusinessException;
	public void sendInvChangesByDepartment(InvoiceVO invoiceVO, String prefixValue, List<String> departmentList, List<String> invoiceChangesList, String otherTypeRemark, Long companyId, UserVO userVO) throws BusinessException;
	
	public void sendEOChangesBySpecificPerson(ExOrderVO exOrderVO, String prefixValue, List<String> emailList, List<String> eoChangesList, String otherTypeRemark, UserVO userVO) throws BusinessException;
	public void sendEOChangesByDepartment(ExOrderVO exOrderVO, String prefixValue, List<String> departmentList, List<String> eoChangesList, String otherTypeRemark, Long companyId, UserVO userVO) throws BusinessException;
	
	public void sendTourDepNOEmailByIndividual(TourDepartureVO tourDepVO, List<String> emailEmployeeList, String remarks, UserVO userVO) throws BusinessException;
	public void sendTourDepNOEmailByDepartment(TourDepartureVO tourDepVO, List<String> departmentList, String remarks, Long companyId, UserVO userVO) throws BusinessException;
	public void sendTourStatusNotifyInsEmail(TourDepartureVO tourDepVO, UserVO userVO) throws BusinessException;
	
	public void sendEmailPaymentEmail(InvoiceEmailPaymentVO invoiceEmailPaymentVO, UserVO userVO) throws BusinessException;

	public void sendEmailInvoiceEmail(InvoiceVO invoiceVO, UserVO userVO, List<String> emailList, File file, String termsAndConditionFile, String itineraryPath, String pretourChecklistPath) throws Exception;

	public void sendEInvNotifyEmail(InvoiceVO invoiceVO, String toEmailAddr, List<String> ccEmailList) throws Exception;

	public void sendEInvNotifyEmail(String code, String eInvoiceStatus, String statusReason, Long idCompany, String toEmailAddr,
			List<String> ccEmailList) throws Exception;

	public void sendCustomerFeedbackEmail(List<FeedbackVO> feedbackList) throws BusinessException;
}
