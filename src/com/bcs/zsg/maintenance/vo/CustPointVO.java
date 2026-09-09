package com.bcs.zsg.maintenance.vo;

import java.util.List;

import com.bcs.zsg.core.vo.BaseVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustPointVO extends BaseVO{

	private static final long serialVersionUID = 1L;
	
	@Expose
	@SerializedName("customerId")
	private Integer customerId;
	@Expose
	@SerializedName("level")
	private String level;
	@Expose
	@SerializedName("finalizedPoint")
	private Integer finalizedPoint;
	@Expose
	@SerializedName("pendingPoint")
	private Integer pendingPoint;
	@Expose
	@SerializedName("availableVoucherList")
	private List<CustVoucherVO> availableVoucherList;
	@Expose
	@SerializedName("voucherList")
	private List<CustVoucherVO> voucherList;
	
	private Integer typeId;
	private Integer quantity;
	private String timestamp;
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Integer getFinalizedPoint() {
		return finalizedPoint;
	}
	public void setFinalizedPoint(Integer finalizedPoint) {
		this.finalizedPoint = finalizedPoint;
	}
	public Integer getPendingPoint() {
		return pendingPoint;
	}
	public void setPendingPoint(Integer pendingPoint) {
		this.pendingPoint = pendingPoint;
	}
	public List<CustVoucherVO> getAvailableVoucherList() {
		return availableVoucherList;
	}
	public void setAvailableVoucherList(List<CustVoucherVO> availableVoucherList) {
		this.availableVoucherList = availableVoucherList;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public List<CustVoucherVO> getVoucherList() {
		return voucherList;
	}
	public void setVoucherList(List<CustVoucherVO> voucherList) {
		this.voucherList = voucherList;
	}

}
