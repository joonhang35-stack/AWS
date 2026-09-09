package com.bcs.zsg.maintenance.vo;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;

public class CompanyVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private CompanyContactVO companyContactVO;
	private CompanyAddressVO companyAddressVO;

	private List<CompanyContactVO> companyContactList;
	private List<CompanyNameVO> companyNameList;

	private String name;
	private String shortName;
	private String code;
	private String conumber;
	private String taxIdNo;
	private String email;
	private String slogan;
	private String typecodeaddress;
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String state;
	private String postcode;
	private String typecodecontact;
	private String number;
	private String letterHeadUrl;
	private Long companyid;
	private Long countryid;
	private Long idAcctSales;
	private Long idAcctTrade;
	private Long idAcctSundry;
	private Long idAcctGST;
	private Long idAcctNonClaimableGST;
	private Long idAcctRounding;
	private Long idAcctAccumulatedPL;
	private Long idAcctCurrentPL;
	private String idAcctSalesTemp;
	private String idAcctTradeTemp;
	private String idAcctSundryTemp;
	private String idAcctGSTTemp;
	private String idAcctNonClaimableGSTTemp;
	private String idAcctRoundingTemp;
	private String idAcctAccumulatedPLTemp;
	private String idAcctCurrentPLTemp;

	private boolean addEdit;

	private String bank;
	private String bankAcctNo;
	private String bankAcctNm;
	
	// MyInvois
	private String myInvoisClientId;
	private String myInvoisClientSecret1;
	private String myInvoisClientSecret2;
	
	// Keycloak
	private String keycloakClientId;
	private String keycloakClientSecret;
	
	private String countryName;

	public String getMyInvoisClientId() {
		return myInvoisClientId;
	}

	public void setMyInvoisClientId(String myInvoisClientId) {
		this.myInvoisClientId = myInvoisClientId;
	}
	
	public String getMyInvoisClientSecret() throws BusinessException {
		if (StringUtils.isNotBlank(myInvoisClientSecret1)) {
			return myInvoisClientSecret1;
		} else if (StringUtils.isNotBlank(myInvoisClientSecret2)) {
			return myInvoisClientSecret2;
		}
		
		throw new BusinessException(CommonErrConstant.ERR_E_INVOICE_API_NO_CLIENT_SECRET);
	}

	public String getMyInvoisClientSecret1() {
		return myInvoisClientSecret1;
	}

	public void setMyInvoisClientSecret1(String myInvoisClientSecret1) {
		this.myInvoisClientSecret1 = myInvoisClientSecret1;
	}

	public String getMyInvoisClientSecret2() {
		return myInvoisClientSecret2;
	}

	public void setMyInvoisClientSecret2(String myInvoisClientSecret2) {
		this.myInvoisClientSecret2 = myInvoisClientSecret2;
	}

	public String getKeycloakClientId() {
		return keycloakClientId;
	}

	public void setKeycloakClientId(String keycloakClientId) {
		this.keycloakClientId = keycloakClientId;
	}

	public String getKeycloakClientSecret() {
		return keycloakClientSecret;
	}

	public void setKeycloakClientSecret(String keycloakClientSecret) {
		this.keycloakClientSecret = keycloakClientSecret;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTaxIdNo() {
		return taxIdNo;
	}

	public void setTaxIdNo(String taxIdNo) {
		this.taxIdNo = taxIdNo;
	}

	public String getBank() {
		return bank;
	}

	public String getBankAcctNo() {
		return bankAcctNo;
	}

	public String getBankAcctNm() {
		return bankAcctNm;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}

	public void setBankAcctNm(String bankAcctNm) {
		this.bankAcctNm = bankAcctNm;
	}

	public String getConumber() {
		return conumber;
	}

	public void setConumber(String conumber) {
		this.conumber = conumber;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getTypecodeaddress() {
		return typecodeaddress;
	}

	public void setTypecodeaddress(String typecodeaddress) {
		this.typecodeaddress = typecodeaddress;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
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

	public String getTypecodecontact() {
		return typecodecontact;
	}

	public void setTypecodecontact(String typecodecontact) {
		this.typecodecontact = typecodecontact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public Long getCountryid() {
		return countryid;
	}

	public void setCountryid(Long countryid) {
		this.countryid = countryid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean getAddEdit() {
		return addEdit;
	}

	public void setAddEdit(boolean addEdit) {
		this.addEdit = addEdit;
	}

	public CompanyContactVO getCompanyContactVO() {
		return companyContactVO;
	}

	public void setCompanyContactVO(CompanyContactVO companyContactVO) {
		this.companyContactVO = companyContactVO;
	}

	public CompanyAddressVO getCompanyAddressVO() {
		return companyAddressVO;
	}

	public void setCompanyAddressVO(CompanyAddressVO companyAddressVO) {
		this.companyAddressVO = companyAddressVO;
	}

	public List<CompanyContactVO> getCompanyContactList() {
		return companyContactList;
	}

	public void setCompanyContactList(List<CompanyContactVO> companyContactList) {
		this.companyContactList = companyContactList;
	}

	public Long getIdAcctSales() {
		return idAcctSales;
	}

	public void setIdAcctSales(Long idAcctSales) {
		this.idAcctSales = idAcctSales;
	}

	public Long getIdAcctTrade() {
		return idAcctTrade;
	}

	public void setIdAcctTrade(Long idAcctTrade) {
		this.idAcctTrade = idAcctTrade;
	}

	public Long getIdAcctSundry() {
		return idAcctSundry;
	}

	public void setIdAcctSundry(Long idAcctSundry) {
		this.idAcctSundry = idAcctSundry;
	}

	public String getIdAcctSalesTemp() {
		return idAcctSalesTemp;
	}

	public void setIdAcctSalesTemp(String idAcctSalesTemp) {
		this.idAcctSalesTemp = idAcctSalesTemp;
	}

	public String getIdAcctTradeTemp() {
		return idAcctTradeTemp;
	}

	public void setIdAcctTradeTemp(String idAcctTradeTemp) {
		this.idAcctTradeTemp = idAcctTradeTemp;
	}

	public String getIdAcctSundryTemp() {
		return idAcctSundryTemp;
	}

	public void setIdAcctSundryTemp(String idAcctSundryTemp) {
		this.idAcctSundryTemp = idAcctSundryTemp;
	}

	public Long getIdAcctGST() {
		return idAcctGST;
	}

	public void setIdAcctGST(Long idAcctGST) {
		this.idAcctGST = idAcctGST;
	}

	public Long getIdAcctRounding() {
		return idAcctRounding;
	}

	public void setIdAcctRounding(Long idAcctRounding) {
		this.idAcctRounding = idAcctRounding;
	}

	/**
	 * @return the idAcctAccumulatedPL
	 */
	public Long getIdAcctAccumulatedPL() {
		return idAcctAccumulatedPL;
	}

	/**
	 * @param idAcctAccumulatedPL the idAcctAccumulatedPL to set
	 */
	public void setIdAcctAccumulatedPL(Long idAcctAccumulatedPL) {
		this.idAcctAccumulatedPL = idAcctAccumulatedPL;
	}

	/**
	 * @return the idAcctCurrentPL
	 */
	public Long getIdAcctCurrentPL() {
		return idAcctCurrentPL;
	}

	/**
	 * @param idAcctCurrentPL the idAcctCurrentPL to set
	 */
	public void setIdAcctCurrentPL(Long idAcctCurrentPL) {
		this.idAcctCurrentPL = idAcctCurrentPL;
	}

	public String getIdAcctGSTTemp() {
		return idAcctGSTTemp;
	}

	public void setIdAcctGSTTemp(String idAcctGSTTemp) {
		this.idAcctGSTTemp = idAcctGSTTemp;
	}

	public String getIdAcctRoundingTemp() {
		return idAcctRoundingTemp;
	}

	public void setIdAcctRoundingTemp(String idAcctRoundingTemp) {
		this.idAcctRoundingTemp = idAcctRoundingTemp;
	}

	/**
	 * @return the idAcctAccumulatedPLTemp
	 */
	public String getIdAcctAccumulatedPLTemp() {
		return idAcctAccumulatedPLTemp;
	}

	/**
	 * @param idAcctAccumulatedPLTemp the idAcctAccumulatedPLTemp to set
	 */
	public void setIdAcctAccumulatedPLTemp(String idAcctAccumulatedPLTemp) {
		this.idAcctAccumulatedPLTemp = idAcctAccumulatedPLTemp;
	}

	/**
	 * @return the idAcctCurrentPLTemp
	 */
	public String getIdAcctCurrentPLTemp() {
		return idAcctCurrentPLTemp;
	}

	/**
	 * @param idAcctCurrentPLTemp the idAcctCurrentPLTemp to set
	 */
	public void setIdAcctCurrentPLTemp(String idAcctCurrentPLTemp) {
		this.idAcctCurrentPLTemp = idAcctCurrentPLTemp;
	}

	/**
	 * @return the idAcctNonClaimableGST
	 */
	public Long getIdAcctNonClaimableGST() {
		return idAcctNonClaimableGST;
	}

	/**
	 * @param idAcctNonClaimableGST the idAcctNonClaimableGST to set
	 */
	public void setIdAcctNonClaimableGST(Long idAcctNonClaimableGST) {
		this.idAcctNonClaimableGST = idAcctNonClaimableGST;
	}

	/**
	 * @return the idAcctNonClaimableGSTTemp
	 */
	public String getIdAcctNonClaimableGSTTemp() {
		return idAcctNonClaimableGSTTemp;
	}

	/**
	 * @param idAcctNonClaimableGSTTemp the idAcctNonClaimableGSTTemp to set
	 */
	public void setIdAcctNonClaimableGSTTemp(String idAcctNonClaimableGSTTemp) {
		this.idAcctNonClaimableGSTTemp = idAcctNonClaimableGSTTemp;
	}

	public List<CompanyNameVO> getCompanyNameList() {
		return companyNameList;
	}

	public void setCompanyNameList(List<CompanyNameVO> companyNameList) {
		this.companyNameList = companyNameList;
	}

	public String getLetterHeadUrl() {
		return letterHeadUrl;
	}

	public void setLetterHeadUrl(String letterHeadUrl) {
		this.letterHeadUrl = letterHeadUrl;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
