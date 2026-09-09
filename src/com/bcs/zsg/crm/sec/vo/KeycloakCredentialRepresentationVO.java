package com.bcs.zsg.crm.sec.vo;

import java.io.Serializable;

/**
 * https://www.keycloak.org/docs-api/latest/rest-api/index.html#CredentialRepresentation
 */
public class KeycloakCredentialRepresentationVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String type;
	private String value;
	private boolean temporary;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isTemporary() {
		return temporary;
	}
	public void setTemporary(boolean temporary) {
		this.temporary = temporary;
	}

}
