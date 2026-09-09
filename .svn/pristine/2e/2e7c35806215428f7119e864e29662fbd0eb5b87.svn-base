package com.bcs.zsg.cfg.sec.web.bean;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.bo.PasswordResetBO;
import com.bcs.zsg.cfg.sec.bo.UserBO;
import com.bcs.zsg.cfg.sec.vo.PasswordResetVO;
import com.bcs.zsg.common.helper.AESEncryption;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.security.vo.ChangePasswordVO;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;

public class ResetPasswordBean extends AppBackingBean {
	
	private static final long serialVersionUID = 1L;
	
	private boolean isResetUrlValid;
	private boolean isSuccess;
	
	private ChangePasswordVO changePasswordVO;
	private PasswordResetVO passwordResetVO;
	private UserVO userVO;
	
	@Autowired
	private transient PasswordResetBO passwordResetBO;
	@Autowired
	private transient UserBO userBO;

	@Override
	public void resetForm() {
		changePasswordVO = new ChangePasswordVO();
		isResetUrlValid = false;
		isSuccess = false;
	}
	
	public void init() {
		resetForm();
		validateResetURL();
	}
	
	public void validateResetURL() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		try {
			String queryString = httpServletRequest.getQueryString() != null ? httpServletRequest.getQueryString() : "";
			if (StringUtils.isBlank(queryString))
				return;
				
			Map<String, String> paramURL = AESEncryption.decryptQueryToMap(queryString);
			
			if (paramURL.get("auth") != null) {
				String encodedPassword = paramURL.get("auth");
				passwordResetVO = passwordResetBO.getPasswordResetVOByEncodedPass(encodedPassword);
				
				// If encoded password not found or empty, means url invalid
				if (passwordResetVO != null) {
					userVO = userBO.getUserVOByLoginId(passwordResetVO.getLoginId());
					
					if (userVO != null) {
						isResetUrlValid = true;
						//passwordResetBO.delete(passwordResetVO);
					}
				}
			}
		} catch (Exception e) {
			errorResult(e);
		}
	}
	
	public void confirmChange() {
		if (!StringUtils.equals(changePasswordVO.getNewPassword(), changePasswordVO.getConfirmPassword())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirm password entered does not matched with new password", ""));
		} else {
			if (userVO != null) {
				userVO.setLoginPassword(changePasswordVO.getNewPassword());
				try {
					userBO.updateUserPassword(userVO);
					passwordResetBO.delete(passwordResetVO);
					isSuccess = true;
				} catch (BusinessException e) {
					errorResult(e);
				}
			}
		}
	}

	public boolean getIsResetUrlValid() {
		return isResetUrlValid;
	}

	public void setIsResetUrlValid(boolean isResetUrlValid) {
		this.isResetUrlValid = isResetUrlValid;
	}

	public ChangePasswordVO getChangePasswordVO() {
		return changePasswordVO;
	}

	public void setChangePasswordVO(ChangePasswordVO changePasswordVO) {
		this.changePasswordVO = changePasswordVO;
	}

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public PasswordResetVO getPasswordResetVO() {
		return passwordResetVO;
	}

	public void setPasswordResetVO(PasswordResetVO passwordResetVO) {
		this.passwordResetVO = passwordResetVO;
	}
}
