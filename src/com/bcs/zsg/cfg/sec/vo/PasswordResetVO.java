package com.bcs.zsg.cfg.sec.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class PasswordResetVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private String emailAddress;
	private String loginId;
	private String encodedPassword;
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getEncodedPassword() {
		return encodedPassword;
	}
	
	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}
}
