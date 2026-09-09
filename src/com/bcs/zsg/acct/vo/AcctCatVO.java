package com.bcs.zsg.acct.vo;

import java.util.List;
import java.util.Set;

import com.bcs.zsg.core.vo.BaseVO;

public class AcctCatVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String desc;
	private List<AcctViewVO> acctViewList;
	private List<AcctSubCatVO> acctSubCatList;
	private Set<AcctSubCatVO> acctSubCatSet;

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
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the acctViewList
	 */
	public List<AcctViewVO> getAcctViewList() {
		return acctViewList;
	}

	/**
	 * @param acctViewList the acctViewList to set
	 */
	public void setAcctViewList(List<AcctViewVO> acctViewList) {
		this.acctViewList = acctViewList;
	}

	/**
	 * @return the acctSubCatList
	 */
	public List<AcctSubCatVO> getAcctSubCatList() {
		return acctSubCatList;
	}

	/**
	 * @param acctSubCatList the acctSubCatList to set
	 */
	public void setAcctSubCatList(List<AcctSubCatVO> acctSubCatList) {
		this.acctSubCatList = acctSubCatList;
	}

	/**
	 * @return the acctSubCatSet
	 */
	public Set<AcctSubCatVO> getAcctSubCatSet() {
		return acctSubCatSet;
	}

	/**
	 * @param acctSubCatSet the acctSubCatSet to set
	 */
	public void setAcctSubCatSet(Set<AcctSubCatVO> acctSubCatSet) {
		this.acctSubCatSet = acctSubCatSet;
	}
}
