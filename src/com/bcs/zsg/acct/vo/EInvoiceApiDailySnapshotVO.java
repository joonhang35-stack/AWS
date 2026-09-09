package com.bcs.zsg.acct.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class EInvoiceApiDailySnapshotVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idCompany;
	private Date dtTrans;
	private String jsonResponse;
	private int totalCount;
	private int pageSize;
	private int pageNo;
	
	public Long getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}
	public Date getDtTrans() {
		return dtTrans;
	}
	public void setDtTrans(Date dtTrans) {
		this.dtTrans = dtTrans;
	}
	public String getJsonResponse() {
		return jsonResponse;
	}
	public void setJsonResponse(String jsonResponse) {
		this.jsonResponse = jsonResponse;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

}
