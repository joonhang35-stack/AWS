package com.bcs.zsg.crm.sec.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.bcs.zsg.common.helper.deserializer.GsonKeycloakUserAttrDeserializer;
import com.bcs.zsg.common.helper.deserializer.GsonUnixTimestampDeserializer;
import com.google.gson.annotations.JsonAdapter;

/**
 * https://www.keycloak.org/docs-api/latest/rest-api/index.html#UserRepresentation
 */
public class KeycloakUserVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	@JsonAdapter(GsonUnixTimestampDeserializer.class)
	private Date createdTimestamp;
//	private disableableCredentialTypes;
	private String email;
	private Boolean emailVerified;
	private Boolean enabled;
	private String firstName;
	private String lastName;
	private Boolean totp;
	private String username;
	@JsonAdapter(GsonKeycloakUserAttrDeserializer.class)
	private KeycloakUserAttributeVO attributes;
	
	// password
	private List<KeycloakCredentialRepresentationVO> credentials;
	private transient String password;
	
	public KeycloakUserVO() {
		attributes = new KeycloakUserAttributeVO();
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<KeycloakCredentialRepresentationVO> getCredentials() {
		return credentials;
	}
	public void setCredentials(List<KeycloakCredentialRepresentationVO> credentials) {
		this.credentials = credentials;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Boolean getTotp() {
		return totp;
	}
	public void setTotp(Boolean totp) {
		this.totp = totp;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public KeycloakUserAttributeVO getAttributes() {
		return attributes;
	}
	public void setAttributes(KeycloakUserAttributeVO attributes) {
		this.attributes = attributes;
	}

}
