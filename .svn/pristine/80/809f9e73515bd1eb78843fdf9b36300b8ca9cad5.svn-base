package com.bcs.zsg.maintenance.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.bcs.zsg.core.vo.BaseVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustVoucherVO extends BaseVO{
	
	private static final long serialVersionUID = 1L;
	@Expose
	@SerializedName("voucherId")
	private Integer voucherId;
	@Expose
	@SerializedName("type")
	private String type;
	@Expose
	@SerializedName("description")
	private String description;
	@Expose
	@SerializedName("value")
	private BigDecimal value;
	@Expose
	@SerializedName("typeId")
	private Integer typeId;
	private boolean redeemConfirm;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getValue() {
		return value.setScale(2, RoundingMode.HALF_UP);
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Integer getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public boolean getRedeemConfirm() {
		return redeemConfirm;
	}
	public void setRedeemConfirm(boolean redeemConfirm) {
		this.redeemConfirm = redeemConfirm;
	}

}
