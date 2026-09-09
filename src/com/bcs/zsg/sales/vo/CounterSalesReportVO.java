package com.bcs.zsg.sales.vo;

public class CounterSalesReportVO extends InvoiceVO {

	private static final long serialVersionUID = 1L;
	
	private Integer paxCount;
	private String deptCode;
	private String deptDesc;
	private Long idTourCat;
	private String tourCatDesc;

	public Integer getPaxCount() {
		return paxCount;
	}

	public void setPaxCount(Integer paxCount) {
		this.paxCount = paxCount;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public Long getIdTourCat() {
		return idTourCat;
	}

	public void setIdTourCat(Long idTourCat) {
		this.idTourCat = idTourCat;
	}

	public String getTourCatDesc() {
		return tourCatDesc;
	}

	public void setTourCatDesc(String tourCatDesc) {
		this.tourCatDesc = tourCatDesc;
	}
}
