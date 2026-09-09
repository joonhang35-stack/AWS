package com.bcs.zsg.maintenance.vo;

import java.util.Date;
import java.util.List;

import com.bcs.zsg.core.vo.BaseVO;

public class POSUploadSalesVO extends BaseVO {

	private static final long serialVersionUID = 1L;
	
	private double totalAmount;
	private Date uploadDate;
	private String uploadStatus;
	private boolean isTest;
	
	private List<POSUploadSalesTransVO> posUploadSalesTransVOList;
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getUploadStatus() {
		return uploadStatus;
	}
	
	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public List<POSUploadSalesTransVO> getPosUploadSalesTransVOList() {
		return posUploadSalesTransVOList;
	}

	public void setPosUploadSalesTransVOList(List<POSUploadSalesTransVO> posUploadSalesTransVOList) {
		this.posUploadSalesTransVOList = posUploadSalesTransVOList;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public boolean getIsTest() {
		return isTest;
	}

	public void setIsTest(boolean isTest) {
		this.isTest = isTest;
	}

}
