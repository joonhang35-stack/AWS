package com.bcs.zsg.bank.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class BankAddrVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long idBank;
	private Long idCountry;
	private String typeCd;
	private String addr1;
	private String addr2;
	private String addr3;
	private String city;
	private String state;
	private String postCd;

	/**
	 * @return the idBank
	 */
	public Long getIdBank() {
		return idBank;
	}

	/**
	 * @param idBank the idBank to set
	 */
	public void setIdBank(Long idBank) {
		this.idBank = idBank;
	}

	/**
	 * @return the idCountry
	 */
	public Long getIdCountry() {
		return idCountry;
	}

	/**
	 * @param idCountry the idCountry to set
	 */
	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}

	/**
	 * @return the typeCd
	 */
	public String getTypeCd() {
		return typeCd;
	}

	/**
	 * @param typeCd the typeCd to set
	 */
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	/**
	 * @return the addr1
	 */
	public String getAddr1() {
		return addr1;
	}

	/**
	 * @param addr1 the addr1 to set
	 */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	/**
	 * @return the addr2
	 */
	public String getAddr2() {
		return addr2;
	}

	/**
	 * @param addr2 the addr2 to set
	 */
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	/**
	 * @return the addr3
	 */
	public String getAddr3() {
		return addr3;
	}

	/**
	 * @param addr3 the addr3 to set
	 */
	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the postCd
	 */
	public String getPostCd() {
		return postCd;
	}

	/**
	 * @param postCd the postCd to set
	 */
	public void setPostCd(String postCd) {
		this.postCd = postCd;
	}
}
