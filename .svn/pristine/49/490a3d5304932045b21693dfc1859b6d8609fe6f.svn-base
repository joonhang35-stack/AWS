package com.bcs.zsg.sales.vo;

import java.util.Date;
import java.util.List;

import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.CorAddressVO;
import com.bcs.zsg.purchase.vo.CorContactVO;
import com.bcs.zsg.purchase.vo.IdentityVO;

public class CustDetailsVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	
	private String crmId;
	private Long custId;
	private Long personId;
	private Long corId;
	private String custType;
	private String contPersonName;
	private String code;
	private Long codeLong;
	private String salutation;	// For separate contPersonName
	private String lastName;	// For separate contPersonName
	private String firstName;	// For separate contPersonName
	private String fullName;	// lastName + firstName
	private String title;
	private String nickName;
	private String companyName;
	private String gstRegNo;
	private String regNo;
	private String sstRegNo;
	private String email;
	private String contact;
	private String countryCode;
	private Integer uPoint;
	private Integer applePoint;
	private Date dob;
	private Integer age;
	private String sex;
	private String taxIdNo;
	private String classes;
	private String race;
	private Long idCountry;
	private Long countryId;
	private Boolean isLock = true;
	private boolean deepCopy;
	private boolean deepCopyFromBill;
	private boolean deepCopyEmail;
	private boolean fullDeepCopyFromBill;
	
	private AddressVO addressVO;
	private AddressVO mailAddressVO;
	private CorAddressVO corAddressVO;
	private IdentityVO nricIdVO = new IdentityVO();
	private IdentityVO passportIdVO = new IdentityVO();
	private PersonContactVO personContactVO = new PersonContactVO();
	
	private List<IdentityVO> identityList;
	private List<PersonContactVO> contactList;
	private List<CorContactVO> corContactList;
	private List<PersonLangVO> personLangList;
	private List<PersonMealVO> personMealList;
	private List<PersonClassVO> personClassList;
	private List<PersonComplicationVO> personComplicationList;
	private List<String> langCdList;
	private List<String> mealCdList;
	private List<String> classCdList;
	private List<String> compliCdList;
	
	// For invoice pax
	private List<PersonLangVO> langList;
	private List<PersonMealVO> mealList;
	private List<PersonAttachmentVO> attachmentList;
	private List<PersonEmailVO> emailList;
	
	private AddUpdDelVO langVOList;
	private AddUpdDelVO mealVOList;
	
	private String languageName;
	private String mealName;
	private String className;
	private String complicationName;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSstRegNo() {
		return sstRegNo;
	}

	public void setSstRegNo(String sstRegNo) {
		this.sstRegNo = sstRegNo;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	/**
	 * @return the custId
	 */
	public Long getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(Long custId) {
		this.custId = custId;
	}

	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * @param custType the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 * @return the contPersonName
	 */
	public String getContPersonName() {
		return contPersonName;
	}

	/**
	 * @param contPersonName the contPersonName to set
	 */
	public void setContPersonName(String contPersonName) {
		this.contPersonName = contPersonName;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the addressVO
	 */
	public AddressVO getAddressVO() {
		return addressVO;
	}

	/**
	 * @param addressVO the addressVO to set
	 */
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}

	/**
	 * @return the corAddressVO
	 */
	public CorAddressVO getCorAddressVO() {
		return corAddressVO;
	}

	/**
	 * @param corAddressVO the corAddressVO to set
	 */
	public void setCorAddressVO(CorAddressVO corAddressVO) {
		this.corAddressVO = corAddressVO;
	}

	/**
	 * @return the identityList
	 */
	public List<IdentityVO> getIdentityList() {
		return identityList;
	}

	/**
	 * @param identityList the identityList to set
	 */
	public void setIdentityList(List<IdentityVO> identityList) {
		this.identityList = identityList;
	}

	/**
	 * @return the contactList
	 */
	public List<PersonContactVO> getContactList() {
		return contactList;
	}

	/**
	 * @param contactList the contactList to set
	 */
	public void setContactList(List<PersonContactVO> contactList) {
		this.contactList = contactList;
	}

	/**
	 * @return the corContactList
	 */
	public List<CorContactVO> getCorContactList() {
		return corContactList;
	}

	/**
	 * @param corContactList the corContactList to set
	 */
	public void setCorContactList(List<CorContactVO> corContactList) {
		this.corContactList = corContactList;
	}

	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * @return the corId
	 */
	public Long getCorId() {
		return corId;
	}

	/**
	 * @param corId the corId to set
	 */
	public void setCorId(Long corId) {
		this.corId = corId;
	}

	/**
	 * @return the salutation
	 */
	public String getSalutation() {
		return salutation;
	}

	/**
	 * @param salutation the salutation to set
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the langList
	 */
	public List<PersonLangVO> getLangList() {
		return langList;
	}

	/**
	 * @param langList the langList to set
	 */
	public void setLangList(List<PersonLangVO> langList) {
		this.langList = langList;
	}

	/**
	 * @return the mealList
	 */
	public List<PersonMealVO> getMealList() {
		return mealList;
	}

	/**
	 * @param mealList the mealList to set
	 */
	public void setMealList(List<PersonMealVO> mealList) {
		this.mealList = mealList;
	}

	/**
	 * @return the langVOList
	 */
	public AddUpdDelVO getLangVOList() {
		return langVOList;
	}

	/**
	 * @param langVOList the langVOList to set
	 */
	public void setLangVOList(AddUpdDelVO langVOList) {
		this.langVOList = langVOList;
	}

	public List<PersonAttachmentVO> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<PersonAttachmentVO> attachmentList) {
		this.attachmentList = attachmentList;
	}

	/**
	 * @return the mealVOList
	 */
	public AddUpdDelVO getMealVOList() {
		return mealVOList;
	}

	/**
	 * @param mealVOList the mealVOList to set
	 */
	public void setMealVOList(AddUpdDelVO mealVOList) {
		this.mealVOList = mealVOList;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
		if (code != null) setCodeLong(Long.parseLong(this.code));
	}

	/**
	 * @return the codeLong
	 */
	public Long getCodeLong() {
		return codeLong;
	}

	/**
	 * @param codeLong the codeLong to set
	 */
	public void setCodeLong(Long codeLong) {
		this.codeLong = codeLong;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getuPoint() {
		return uPoint;
	}

	public void setuPoint(Integer uPoint) {
		this.uPoint = uPoint;
	}

	public Integer getApplePoint() {
		return applePoint;
	}

	public void setApplePoint(Integer applePoint) {
		this.applePoint = applePoint;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}

	public List<PersonEmailVO> getEmailList() {
		return emailList;
	}

	public void setEmailList(List<PersonEmailVO> emailList) {
		this.emailList = emailList;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getComplicationName() {
		return complicationName;
	}

	public void setComplicationName(String complicationName) {
		this.complicationName = complicationName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public IdentityVO getNricIdVO() {
		return nricIdVO;
	}

	public void setNricIdVO(IdentityVO nricIdVO) {
		this.nricIdVO = nricIdVO;
	}

	public IdentityVO getPassportIdVO() {
		return passportIdVO;
	}

	public void setPassportIdVO(IdentityVO passportIdVO) {
		this.passportIdVO = passportIdVO;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Boolean getIsLock() {
		return isLock;
	}

	public void setIsLock(Boolean isLock) {
		this.isLock = isLock;
	}

	public List<PersonLangVO> getPersonLangList() {
		return personLangList;
	}

	public void setPersonLangList(List<PersonLangVO> personLangList) {
		this.personLangList = personLangList;
	}

	public List<PersonMealVO> getPersonMealList() {
		return personMealList;
	}

	public void setPersonMealList(List<PersonMealVO> personMealList) {
		this.personMealList = personMealList;
	}

	public List<PersonClassVO> getPersonClassList() {
		return personClassList;
	}

	public void setPersonClassList(List<PersonClassVO> personClassList) {
		this.personClassList = personClassList;
	}

	public List<PersonComplicationVO> getPersonComplicationList() {
		return personComplicationList;
	}

	public void setPersonComplicationList(List<PersonComplicationVO> personComplicationList) {
		this.personComplicationList = personComplicationList;
	}

	public List<String> getLangCdList() {
		return langCdList;
	}

	public void setLangCdList(List<String> langCdList) {
		this.langCdList = langCdList;
	}

	public List<String> getMealCdList() {
		return mealCdList;
	}

	public void setMealCdList(List<String> mealCdList) {
		this.mealCdList = mealCdList;
	}

	public List<String> getClassCdList() {
		return classCdList;
	}

	public void setClassCdList(List<String> classCdList) {
		this.classCdList = classCdList;
	}

	public List<String> getCompliCdList() {
		return compliCdList;
	}

	public void setCompliCdList(List<String> compliCdList) {
		this.compliCdList = compliCdList;
	}

	public String getTaxIdNo() {
		return taxIdNo;
	}

	public void setTaxIdNo(String taxIdNo) {
		this.taxIdNo = taxIdNo;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public PersonContactVO getPersonContactVO() {
		return personContactVO;
	}

	public void setPersonContactVO(PersonContactVO personContactVO) {
		this.personContactVO = personContactVO;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGstRegNo() {
		return gstRegNo;
	}

	public void setGstRegNo(String gstRegNo) {
		this.gstRegNo = gstRegNo;
	}

	public AddressVO getMailAddressVO() {
		return mailAddressVO;
	}

	public void setMailAddressVO(AddressVO mailAddressVO) {
		this.mailAddressVO = mailAddressVO;
	}

	public boolean isDeepCopy() {
		return deepCopy;
	}

	public void setDeepCopy(boolean deepCopy) {
		this.deepCopy = deepCopy;
	}

	public boolean isDeepCopyFromBill() {
		return deepCopyFromBill;
	}

	public void setDeepCopyFromBill(boolean deepCopyFromBill) {
		this.deepCopyFromBill = deepCopyFromBill;
	}

	public boolean isDeepCopyEmail() {
		return deepCopyEmail;
	}

	public void setDeepCopyEmail(boolean deepCopyEmail) {
		this.deepCopyEmail = deepCopyEmail;
	}

	public boolean isFullDeepCopyFromBill() {
		return fullDeepCopyFromBill;
	}

	public void setFullDeepCopyFromBill(boolean fullDeepCopyFromBill) {
		this.fullDeepCopyFromBill = fullDeepCopyFromBill;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCrmId() {
		return crmId;
	}

	public void setCrmId(String crmId) {
		this.crmId = crmId;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}
}
