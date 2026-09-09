package com.bcs.zsg.crm.vo;

import java.io.Serializable;

public class PointExpiryVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int points;
    private String expiryDate;
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
}
