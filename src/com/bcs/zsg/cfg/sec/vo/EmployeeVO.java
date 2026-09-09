package com.bcs.zsg.cfg.sec.vo;
import com.bcs.zsg.core.vo.BaseVO;

public class EmployeeVO extends BaseVO{
	private static final long serialVersionUID = 1L;
	
	private String department;
	private String secUser;
	private String employeeName;
	private Long companyId;
	private Long personId;
	private Long Id;
	private Boolean isDefaultComp;
	private Long bakCompanyId;
	private Long customerId;
	private String customerName;
	private String roleType;
	
	// Staff Info
	private String category;
	private String fullName;
	private String idNo;
	private String taxIdNo;
	private String nickName;
	private String msicCode;
	private String addr1;
	private String addr2;
	private String addr3;
	private String city;
	private String state;
	private String postcode;
	private Long countryId;
	private String countryName;
	private String acctNo;
	private String bankName;
	private Boolean isAccessSystem = Boolean.FALSE;
	private Boolean isEditable = Boolean.TRUE;
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSecUser() {
		return secUser;
	}
	public void setSecUser(String secUser) {
		this.secUser = secUser;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the isDefaultComp
	 */
	public Boolean getIsDefaultComp() {
		return isDefaultComp;
	}
	/**
	 * @param isDefaultComp the isDefaultComp to set
	 */
	public void setIsDefaultComp(Boolean isDefaultComp) {
		this.isDefaultComp = isDefaultComp;
	}
	/**
	 * @return the bakCompanyId
	 */
	public Long getBakCompanyId() {
		return bakCompanyId;
	}
	/**
	 * @param bakCompanyId the bakCompanyId to set
	 */
	public void setBakCompanyId(Long bakCompanyId) {
		this.bakCompanyId = bakCompanyId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
	    this.fullName = fullName;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getTaxIdNo() {
		return taxIdNo;
	}
	public void setTaxIdNo(String taxIdNo) {
		this.taxIdNo = taxIdNo;
	}
	public String getMsicCode() {
		return msicCode;
	}
	public void setMsicCode(String msicCode) {
		this.msicCode = msicCode;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
	    this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
	    this.addr2 = addr2;
	}
	public String getAddr3() {
		return addr3;
	}
	public void setAddr3(String addr3) {
	    this.addr3 = addr3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
	    this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
	    this.state = state;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
	    this.postcode = postcode;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getAcctNo() {
		return acctNo;
	}
	
	public void setAcctNo(String acctNo) {
	    this.acctNo = acctNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Boolean getIsAccessSystem() {
		return isAccessSystem;
	}
	public void setIsAccessSystem(Boolean isAccessSystem) {
		this.isAccessSystem = isAccessSystem;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
	    this.nickName = nickName;
	}
	public Boolean getIsEditable() {
		return isEditable;
	}
	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}
}
