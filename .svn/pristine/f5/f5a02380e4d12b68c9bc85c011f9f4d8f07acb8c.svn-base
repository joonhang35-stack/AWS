package com.bcs.zsg.cfg.sec.web.bean;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.service.UserService;
import com.bcs.zsg.cfg.sec.vo.PasswordResetVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;

public class ForgotPasswordBean extends AppBackingBean {
	
	private static final long serialVersionUID = 1L;

	private PasswordResetVO passwordResetVO;
	
	private boolean isProcessed;
	
	@Autowired
	private transient UserService userService;
	
	@Override
	public void resetForm() {
		isProcessed = false;
		passwordResetVO = new PasswordResetVO();
	}
	
	public void init() {
		resetForm();
	}
	
	public void requestReset() {
		passwordResetVO.setUpdatedDate(new Date());
		
		try {
			userService.processReset(passwordResetVO);
			
			isProcessed = true;
		} catch (BusinessException e) {
			errorResult(e);
		}
	}

	public PasswordResetVO getPasswordResetVO() {
		return passwordResetVO;
	}

	public void setPasswordResetVO(PasswordResetVO passwordResetVO) {
		this.passwordResetVO = passwordResetVO;
	}

	public boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
}
