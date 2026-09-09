package com.bcs.zsg.einvoice.vo;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class ApiGetSubmissionResponseVO {
	
	private String submissionUid;
	private int documentCount;
	private Date dateTimeReceived;
	private String overallStatus;
	private List<ApiDocumentSummaryVO> documentSummary;
	private ApiGetSubmissionErrorVO error;
	
	// not API return value, just want to record the response code
	private int responseCode;
	
	public String getErrMsg() {
		if (error != null) {
			if (StringUtils.isNotBlank(error.getMessage())) {
				System.out.println("if (StringUtils.isNotBlank(error.getMessage())) {");
				return error.getMessage();
			} else {
				if (CollectionUtils.isEmpty(error.getDetails())) {
					return "Error details are empty or null. No additional error information available.";
				} else {
					String res = "";
					for (ApiGetSubmissionErrorVO errVO : error.getDetails()) {
						res += errVO.getMessage();
					}
					return res;
				}
			}
		} else {
			return "Error object is null. No error information was provided by the API.";
		}
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public ApiGetSubmissionErrorVO getError() {
		return error;
	}
	public void setError(ApiGetSubmissionErrorVO error) {
		this.error = error;
	}
	public String getSubmissionUid() {
		return submissionUid;
	}
	public void setSubmissionUid(String submissionUid) {
		this.submissionUid = submissionUid;
	}
	public int getDocumentCount() {
		return documentCount;
	}
	public void setDocumentCount(int documentCount) {
		this.documentCount = documentCount;
	}
	public Date getDateTimeReceived() {
		return dateTimeReceived;
	}
	public void setDateTimeReceived(Date dateTimeReceived) {
		this.dateTimeReceived = dateTimeReceived;
	}
	public String getOverallStatus() {
		return overallStatus;
	}
	public void setOverallStatus(String overallStatus) {
		this.overallStatus = overallStatus;
	}
	public List<ApiDocumentSummaryVO> getDocumentSummary() {
		return documentSummary;
	}
	public void setDocumentSummary(List<ApiDocumentSummaryVO> documentSummary) {
		this.documentSummary = documentSummary;
	}

}
