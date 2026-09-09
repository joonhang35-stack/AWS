package com.bcs.zsg.purchase.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class PaxNameVO  extends BaseVO{
	private static final long serialVersionUID = 1L;

	private String paxName;
	private String pnr;
	private String airCode;
	private String ticketNo;
	private Double payableAmt;
	private Integer no;
	private Long exOrderId;
	private Integer seq;
	
	public String getPaxName() {
		return paxName;
	}
	public void setPaxName(String paxName) {
		this.paxName = paxName;
	}
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public String getAirCode() {
		return airCode;
	}
	public void setAirCode(String airCode) {
		this.airCode = airCode;
	}
	public Double getPayableAmt() {
		return payableAmt;
	}
	public void setPayableAmt(Double payableAmt) {
		this.payableAmt = payableAmt;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Long getExOrderId() {
		return exOrderId;
	}
	public void setExOrderId(Long exOrderId) {
		this.exOrderId = exOrderId;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

}
