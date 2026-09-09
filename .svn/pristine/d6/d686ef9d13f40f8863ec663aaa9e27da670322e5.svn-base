package com.bcs.zsg.crm.sec.vo;

import java.io.Serializable;

import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.PersonVO;

public class KeycloakUserAttributeVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private PersonVO personVO;
	private AddressVO billAddrVO;
	private AddressVO mailAddrVO;
	private String identity;
	private String nationality;
	
	public KeycloakUserAttributeVO() {
		init();
	}
	
	// init
	public void init() {
		personVO = new PersonVO();
		billAddrVO = new AddressVO();
		mailAddrVO = new AddressVO();
	}
	
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public PersonVO getPersonVO() {
		return personVO;
	}
	public void setPersonVO(PersonVO personVO) {
		this.personVO = personVO;
	}
	public AddressVO getMailAddrVO() {
		return mailAddrVO;
	}
	public void setMailAddrVO(AddressVO mailAddrVO) {
		this.mailAddrVO = mailAddrVO;
	}
	public AddressVO getBillAddrVO() {
		return billAddrVO;
	}
	public void setBillAddrVO(AddressVO billAddrVO) {
		this.billAddrVO = billAddrVO;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}

}
