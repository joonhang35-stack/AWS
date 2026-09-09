package com.bcs.zsg.sales.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class OnlineBookingCustomerVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long idBooking;
	private String salutationCd;
	private String titleCd;
	private String surname;
	private String givenName;
	private String nickName;
	private String taxIdNo;
	private String sexCd;
	private Long idCountryCd;
	private String countryCd;
	private String contactNo;
	private String nric;
	private String passportNo;
	private String email;
	private Long idCountry;
	private String addressLine1;
	private String addressLine2;
	private String postcode;
	private String state;
	
	private String ecSurname;
	private String ecGivenName;
	private Long ecIdCountryCd;
	private String ecCountryCd;
	private String ecContacts;
	private String ecEmail;
	private String ecRelationship;
	
	public Long getIdBooking() {
		return idBooking;
	}
	public void setIdBooking(Long idBooking) {
		this.idBooking = idBooking;
	}
	public String getSalutationCd() {
		return salutationCd;
	}
	public void setSalutationCd(String salutationCd) {
		this.salutationCd = salutationCd;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSexCd() {
		return sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getNric() {
		return nric;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getIdCountry() {
		return idCountry;
	}
	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEcSurname() {
		return ecSurname;
	}
	public void setEcSurname(String ecSurname) {
		this.ecSurname = ecSurname;
	}
	public String getEcGivenName() {
		return ecGivenName;
	}
	public void setEcGivenName(String ecGivenName) {
		this.ecGivenName = ecGivenName;
	}
	public String getEcContacts() {
		return ecContacts;
	}
	public void setEcContacts(String ecContacts) {
		this.ecContacts = ecContacts;
	}
	public String getEcEmail() {
		return ecEmail;
	}
	public void setEcEmail(String ecEmail) {
		this.ecEmail = ecEmail;
	}
	public String getEcRelationship() {
		return ecRelationship;
	}
	public void setEcRelationship(String ecRelationship) {
		this.ecRelationship = ecRelationship;
	}
	public String getTitleCd() {
		return titleCd;
	}
	public void setTitleCd(String titleCd) {
		this.titleCd = titleCd;
	}
	public String getTaxIdNo() {
		return taxIdNo;
	}
	public void setTaxIdNo(String taxIdNo) {
		this.taxIdNo = taxIdNo;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public Long getIdCountryCd() {
		return idCountryCd;
	}
	public void setIdCountryCd(Long idCountryCd) {
		this.idCountryCd = idCountryCd;
	}
	public String getCountryCd() {
		return countryCd;
	}
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	public Long getEcIdCountryCd() {
		return ecIdCountryCd;
	}
	public void setEcIdCountryCd(Long ecIdCountryCd) {
		this.ecIdCountryCd = ecIdCountryCd;
	}
	public String getEcCountryCd() {
		return ecCountryCd;
	}
	public void setEcCountryCd(String ecCountryCd) {
		this.ecCountryCd = ecCountryCd;
	}
}
