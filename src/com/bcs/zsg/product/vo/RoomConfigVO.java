package com.bcs.zsg.product.vo;

import java.util.List;

import com.bcs.zsg.core.vo.BaseVO;

public class RoomConfigVO extends BaseVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private Integer twnQty;
	private Integer sglQty;
	private Integer ctwQty;
	private Integer cwbQty;
	private Integer cnbQty;
	private Integer inftQty;
	
	private String tourFare;
	private String codeDesc;
	private List<String> groupList;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getTwnQty() {
		return twnQty;
	}
	public void setTwnQty(Integer twnQty) {
		this.twnQty = twnQty;
	}
	public Integer getCtwQty() {
		return ctwQty;
	}
	public void setCtwQty(Integer ctwQty) {
		this.ctwQty = ctwQty;
	}
	public Integer getCwbQty() {
		return cwbQty;
	}
	public void setCwbQty(Integer cwbQty) {
		this.cwbQty = cwbQty;
	}
	public Integer getCnbQty() {
		return cnbQty;
	}
	public void setCnbQty(Integer cnbQty) {
		this.cnbQty = cnbQty;
	}
	public Integer getInftQty() {
		return inftQty;
	}
	public void setInftQty(Integer inftQty) {
		this.inftQty = inftQty;
	}
	public Integer getSglQty() {
		return sglQty;
	}
	public void setSglQty(Integer sglQty) {
		this.sglQty = sglQty;
	}
	public List<String> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<String> groupList) {
		this.groupList = groupList;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public String getTourFare() {
		return tourFare;
	}
	public void setTourFare(String tourFare) {
		this.tourFare = tourFare;
	}
	
}
