package com.bcs.zsg.maintenance.vo;

import org.apache.commons.lang3.StringUtils;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.vo.BaseVO;

public class VisibleListingConfigVO extends BaseVO {

	private static final long serialVersionUID = 1L;
	
	private String idListStr;
	private String[] idList;
	private String catCd;
	private String catDesc;
	private String listingType;
	private String type;
	private String idRole;
	private String idRoleListStr;
	private String[] idRoleList;
	private String deptCode;
	private String departmentListStr;
	private String[] departmentList;
	private String roleName;
	private String departmentName;
	
	public String getListingTypeDesc() {
		if (StringUtils.equals(listingType, CommonConstant.LISTING_TYPE_INV_CAT))
			return "Invoice / Pax Statement";
		else if (StringUtils.equals(listingType, CommonConstant.LISTING_TYPE_EO_CAT))
			return "Exchange Order";
		
		return "";
	}
	
	public String[] getIdList() {
		if (StringUtils.isNotBlank(idListStr)) {
			idList = idListStr.split("\\s*,\\s*");
			
			return idList;
		}
		
		return idList;
	}
	
	public String[] getIdRoleList() {
		if (idRoleList == null && StringUtils.isNotBlank(idRoleListStr)) {
			idRoleList = idRoleListStr.split("\\s*,\\s*");
			idRole = idRoleList[0];
			
			return idRoleList;
		}
		
		return idRoleList;
	}
	
	public String getCatCd() {
		return catCd;
	}
	
	public void setCatCd(String catCd) {
		this.catCd = catCd;
	}
	
	public String getListingType() {
		return listingType;
	}
	
	public void setListingType(String listingType) {
		this.listingType = listingType;
	}
	
	public String getIdRole() {
		return idRole;
	}
	
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public String getIdListStr() {
		return idListStr;
	}

	public void setIdListStr(String idListStr) {
		this.idListStr = idListStr;
	}

	public String getIdRoleListStr() {
		return idRoleListStr;
	}

	public void setIdRoleListStr(String idRoleListStr) {
		this.idRoleListStr = idRoleListStr;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	public void setIdRoleList(String[] idRoleList) {
		this.idRoleList = idRoleList;
	}

	public String[] getDepartmentList() {
		if (departmentList == null && StringUtils.isNotBlank(departmentListStr)) {
			departmentList = departmentListStr.split("\\s*,\\s*");
			
			return departmentList;
		}
		return departmentList;
	}

	public void setDepartmentList(String[] departmentList) {
		this.departmentList = departmentList;
	}

	public String getDepartmentListStr() {
		return departmentListStr;
	}

	public void setDepartmentListStr(String departmentListStr) {
		this.departmentListStr = departmentListStr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}
