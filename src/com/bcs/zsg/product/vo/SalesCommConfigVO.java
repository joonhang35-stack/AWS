package com.bcs.zsg.product.vo;

import java.util.ArrayList;
import java.util.List;

import com.bcs.zsg.core.vo.BaseVO;

public class SalesCommConfigVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private String department;
	private String groupName;
	private String fareRangeDetail;
	
	private List<SalesCommConfigDetailVO> salesCommConfigDetailList = new ArrayList<SalesCommConfigDetailVO>();
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<SalesCommConfigDetailVO> getSalesCommConfigDetailList() {
		return salesCommConfigDetailList;
	}

	public void setSalesCommConfigDetailList(List<SalesCommConfigDetailVO> salesCommConfigDetailList) {
		this.salesCommConfigDetailList = salesCommConfigDetailList;
	}

	public String getFareRangeDetail() {
		return fareRangeDetail;
	}

	public void setFareRangeDetail(String fareRangeDetail) {
		this.fareRangeDetail = fareRangeDetail;
	}

}
