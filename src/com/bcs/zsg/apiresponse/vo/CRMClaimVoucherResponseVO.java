package com.bcs.zsg.apiresponse.vo;

public class CRMClaimVoucherResponseVO {
	
	private String status;
    private String timestamp;
    private String message;
    private String redemptionId;
    private String updateOn;
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRedemptionId() {
		return redemptionId;
	}
	public void setRedemptionId(String redemptionId) {
		this.redemptionId = redemptionId;
	}
	public String getUpdateOn() {
		return updateOn;
	}
	public void setUpdateOn(String updateOn) {
		this.updateOn = updateOn;
	}
}
