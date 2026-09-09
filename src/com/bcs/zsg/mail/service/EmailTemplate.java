package com.bcs.zsg.mail.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.mail.helper.ConstantEmailTemplate;
import com.bcs.zsg.mail.helper.EmailProperties;

import freemarker.template.Configuration;

public class EmailTemplate {
	
	@Autowired
	private Configuration freemarkerConfiguration;
	
	/**
	 * Process the template mapping with data using velocity template
	 * @param emailTemplate Object of ConstantEmailTemplate
	 * @param templateMap Mapping data to map with
	 * @throws BusinessException
	 */
	public String processVMMapping(String emailTemplate, Map<String, ?> templateMap, String footerTemplate) throws BusinessException {
        try {
		    String baseUrl = EmailProperties.MAIL_URL;
        	
    		Map<String, String> baseTemplateMap = new HashMap<>();
    		baseTemplateMap.put("bgColor", "#7c51a1");
    		if (templateMap != null) {
    			if (StringUtils.isNotEmpty((String) templateMap.get("bgColor"))) {
    				baseTemplateMap.put("bgColor", (String) templateMap.get("bgColor"));
    			}
    		}
    		
    		baseTemplateMap.put("logoStyle", "height: 42.69px; width: 120px;"); // AWS logo image size
    		baseTemplateMap.put("logo", EmailProperties.MAIL_LOGO);            // AWS logo image path
    		
    		if (CommonConstant.SYS_APP_CODE_UWS.equals(CommonConstant.SYS_APP_CODE)) {
    			baseTemplateMap.put("logoStyle", "height: 71px; width: 250px;");            // UBINGO logo image size
        		baseTemplateMap.put("logo", baseUrl.concat(EmailProperties.MAIL_LOGO_UWS)); // UBINGO logo image path
    		}
    		
    		baseTemplateMap.put("contentBody", FreeMarkerTemplateUtils
    								.processTemplateIntoString(freemarkerConfiguration.getTemplate(emailTemplate), templateMap));
    		baseTemplateMap.put("contentFooter", 
    				templateMap.containsKey(ConstantEmailTemplate.FOOTER_CONTENT_CUSTOM) ? 
    						(String) templateMap.get(ConstantEmailTemplate.FOOTER_CONTENT_CUSTOM)
							: FreeMarkerTemplateUtils.processTemplateIntoString(
     											freemarkerConfiguration.getTemplate(footerTemplate), templateMap));
        		
			return FreeMarkerTemplateUtils.processTemplateIntoString(
						freemarkerConfiguration.getTemplate(ConstantEmailTemplate.BASE_TEMPLATE), baseTemplateMap);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	public String processVMMapping_round_corner(String emailTemplate, Map<String, ?> templateMap) throws BusinessException {
        try {
		    String baseUrl = EmailProperties.MAIL_URL;
        	
    		Map<String, String> baseTemplateMap = new HashMap<>();
    		baseTemplateMap.put("bgColor", "#7c51a1");
    		if (templateMap != null) {
    			if (StringUtils.isNotEmpty((String) templateMap.get("bgColor"))) {
    				baseTemplateMap.put("bgColor", (String) templateMap.get("bgColor"));
    			}
    		}
    		
    		baseTemplateMap.put("logoStyle", "height: 42.69px; width: 120px;"); // AWS logo image size
    		baseTemplateMap.put("logo", EmailProperties.MAIL_LOGO);          // AWS logo image path
    		if (CommonConstant.SYS_APP_CODE_UWS.equals(CommonConstant.SYS_APP_CODE)) {
    			baseTemplateMap.put("logoStyle", "height: 71px; width: 250px;");            // UBINGO logo image size
        		baseTemplateMap.put("logo", baseUrl.concat(EmailProperties.MAIL_LOGO_UWS)); // UBINGO logo image path
    		}
    		baseTemplateMap.put("contentBody", FreeMarkerTemplateUtils
    								.processTemplateIntoString(freemarkerConfiguration.getTemplate(emailTemplate), templateMap));
        		
			String fullEmail = FreeMarkerTemplateUtils.processTemplateIntoString(
						freemarkerConfiguration.getTemplate(ConstantEmailTemplate.BASE_TEMPLATE_ROUND_CORNER), baseTemplateMap);
			//System.out.println(fullEmail);
			return fullEmail;
			
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	public String processVMMappingWithoutBase(String emailTemplate, Map<String, ?> templateMap) throws BusinessException {
		try {
			return FreeMarkerTemplateUtils.processTemplateIntoString(
						freemarkerConfiguration.getTemplate(emailTemplate), templateMap);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
