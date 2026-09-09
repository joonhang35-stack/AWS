package com.bcs.zsg.acct.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class EInvoiceAccessTokenVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idCompany;
	private String accessToken;
	private Date dateValid;
	private Integer expiresIn;
	private String scope;
	private String tokenType;
	
	public Long getIdCompany() {
		return idCompany;
	}
	
	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getDateValid() {
		return dateValid;
	}

	public void setDateValid(Date dateValid) {
		this.dateValid = dateValid;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
}
