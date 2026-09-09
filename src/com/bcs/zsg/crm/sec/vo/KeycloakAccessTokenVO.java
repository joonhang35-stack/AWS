package com.bcs.zsg.crm.sec.vo;

import com.bcs.zsg.core.vo.BaseVO;
import com.google.gson.annotations.SerializedName;

public class KeycloakAccessTokenVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	
	private String accessToken;
	private int expiresIn;
	@SerializedName("not-before-policy")
	private int notBeforePolicy;
	private int refreshExpiresIn;
	private String scope;
	private String tokenType;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public int getNotBeforePolicy() {
		return notBeforePolicy;
	}
	public void setNotBeforePolicy(int notBeforePolicy) {
		this.notBeforePolicy = notBeforePolicy;
	}
	public int getRefreshExpiresIn() {
		return refreshExpiresIn;
	}
	public void setRefreshExpiresIn(int refreshExpiresIn) {
		this.refreshExpiresIn = refreshExpiresIn;
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
