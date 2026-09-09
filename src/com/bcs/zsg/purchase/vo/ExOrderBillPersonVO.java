package com.bcs.zsg.purchase.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class ExOrderBillPersonVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	
	private Long eoBillId;
	private Long idUser;
	private String userName;
	private Double amount;
	
	public Long getEoBillId() {
		return eoBillId;
	}

	public void setEoBillId(Long eoBillId) {
		this.eoBillId = eoBillId;
	}

	public Long getIdUser() {
		return idUser;
	}
	
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
