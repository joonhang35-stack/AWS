package com.bcs.zsg.mail.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.mail.helper.ConstantEmailTemplate;

@Service("emailTemplate")
public class AppEmailTemplate extends EmailTemplate {
	
	public String processEmailMappingFooterDoNotReply(String emailTemplate, Map<String, ?> templateMap) throws BusinessException {
		return super.processVMMapping(emailTemplate, templateMap, ConstantEmailTemplate.BASE_FOOTER_DONOTREPLY);
	}

	public String processEmailMappingFooterAuthorizedRequest(String emailTemplate, Map<String, ?> templateMap) throws BusinessException {
		return super.processVMMapping(emailTemplate, templateMap, ConstantEmailTemplate.BASE_FOOTER_AUTHORIZEDREQUEST);
	}

	public String processEmailMappingFooterCompanyAddress(String emailTemplate, Map<String, ?> templateMap) throws BusinessException {
		return super.processVMMapping(emailTemplate, templateMap, ConstantEmailTemplate.BASE_FOOTER_COMPANY_ADDRESS);
	}

	public String processEmailMappingFooterCustom(String emailTemplate, Map<String, ?> templateMap) throws BusinessException {
		return super.processVMMapping(emailTemplate, templateMap, null);
	}
	
	public String processEmailMapping_round_corner(String emailTemplate, Map<String, ?> templateMap) throws BusinessException {
		return super.processVMMapping_round_corner(emailTemplate, templateMap);
	}
	
	public String processEmailMappingWithoutBase(String emailTemplate, Map<String, ?> templateMap) throws BusinessException {
		return super.processVMMappingWithoutBase(emailTemplate, templateMap);
	}
}
