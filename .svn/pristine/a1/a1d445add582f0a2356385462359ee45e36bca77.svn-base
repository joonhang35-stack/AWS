package com.bcs.zsg.product.vo;

import java.util.Date;
import java.util.List;

import com.bcs.zsg.core.vo.BaseVO;

public class CruiseScheduleVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	private Long idCruise;
	private String desc;
	private Date dtSchedule;
	private Integer status = 0;
	// for default charges setting
	private Double amtAptAdt = 0.0;
	private Double amtAptChd = 0.0;
	private Double amtFuelAdt = 0.0;
	private Double amtFuelChd = 0.0;
	private Double amtTrvlIns = 0.0;
	private Double amtVisa = 0.0;
	private Double amtAC = 0.0;
	private Double amtTipping = 0.0;
	private Double amtDeviation = 0.0;
	private Integer tktValidity;
	private Integer seq;
	private String etd;
	private String eta;
	private List<CruiseScheduleItemVO> ScheduleItemList;
	private List<CruiseScheduleChargeVO> extraItemChargeList;
	// for view purpose
	private CruiseScheduleItemVO[] selectedScheduleItems;

	private Double amtTotalCharge = 0.00;
	private boolean sumAmtApt = false;
	private boolean sumAmtAptAdt = false;
	private boolean sumAmtAptChd = false;
	private boolean sumAmtFuel = false;
	private boolean sumAmtFuelAdt = false;
	private boolean sumAmtFuelChd = false;
	private boolean sumAmtTrvlIns = false;
	private boolean sumAmtVisa = false;
	private boolean sumAmtAC = false;
	private boolean sumAmtTipping = false;
	private boolean sumAmtDeviation = false;
	private String groupingChargeLabel = "Misc. Charges";

	private String Name;

	// add value to amtTotalCharge
	public void addAmtTotalCharge(Double addAmt) {
		amtTotalCharge += addAmt;
	}

	public String getCruiseName() {
		return Name;
	}

	public void setCruiseName(String Name) {
		this.Name = Name;
	}

	public String getGroupingChargeLabel() {
		return groupingChargeLabel;
	}

	public void setGroupingChargeLabel(String groupingChargeLabel) {
		this.groupingChargeLabel = groupingChargeLabel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getAmtTotalCharge() {
		return amtTotalCharge;
	}

	public boolean isSumAmtApt() {
		return sumAmtApt;
	}

	public boolean isSumAmtAptAdt() {
		return sumAmtAptAdt;
	}

	public boolean isSumAmtAptChd() {
		return sumAmtAptChd;
	}

	public boolean isSumAmtFuel() {
		return sumAmtFuel;
	}

	public boolean isSumAmtFuelAdt() {
		return sumAmtFuelAdt;
	}

	public boolean isSumAmtFuelChd() {
		return sumAmtFuelChd;
	}

	public boolean isSumAmtTrvlIns() {
		return sumAmtTrvlIns;
	}

	public boolean isSumAmtVisa() {
		return sumAmtVisa;
	}

	public boolean isSumAmtAC() {
		return sumAmtAC;
	}

	public boolean isSumAmtTipping() {
		return sumAmtTipping;
	}

	public boolean isSumAmtDeviation() {
		return sumAmtDeviation;
	}

	public void setAmtTotalCharge(Double amtTotalCharge) {
		this.amtTotalCharge = amtTotalCharge;
	}

	public void setSumAmtApt(boolean sumAmtApt) {
		this.sumAmtApt = sumAmtApt;
	}

	public void setSumAmtAptAdt(boolean sumAmtAptAdt) {
		this.sumAmtAptAdt = sumAmtAptAdt;
	}

	public void setSumAmtAptChd(boolean sumAmtAptChd) {
		this.sumAmtAptChd = sumAmtAptChd;
	}

	public void setSumAmtFuel(boolean sumAmtFuel) {
		this.sumAmtFuel = sumAmtFuel;
	}

	public void setSumAmtFuelAdt(boolean sumAmtFuelAdt) {
		this.sumAmtFuelAdt = sumAmtFuelAdt;
	}

	public void setSumAmtFuelChd(boolean sumAmtFuelChd) {
		this.sumAmtFuelChd = sumAmtFuelChd;
	}

	public void setSumAmtTrvlIns(boolean sumAmtTrvlIns) {
		this.sumAmtTrvlIns = sumAmtTrvlIns;
	}

	public void setSumAmtVisa(boolean sumAmtVisa) {
		this.sumAmtVisa = sumAmtVisa;
	}

	public void setSumAmtAC(boolean sumAmtAC) {
		this.sumAmtAC = sumAmtAC;
	}

	public void setSumAmtTipping(boolean sumAmtTipping) {
		this.sumAmtTipping = sumAmtTipping;
	}

	public void setSumAmtDeviation(boolean sumAmtDeviation) {
		this.sumAmtDeviation = sumAmtDeviation;
	}

	/**
	 * @return the idCruise
	 */
	public Long getIdCruise() {
		return idCruise;
	}

	/**
	 * @param idCruise the idCruise to set
	 */
	public void setIdCruise(Long idCruise) {
		this.idCruise = idCruise;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the dtSchedule
	 */
	public Date getDtSchedule() {
		return dtSchedule;
	}

	/**
	 * @param dtSchedule the dtSchedule to set
	 */
	public void setDtSchedule(Date dtSchedule) {
		this.dtSchedule = dtSchedule;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the ScheduleItemList
	 */
	public List<CruiseScheduleItemVO> getCruiseScheduleItemList() {
		return ScheduleItemList;
	}

	/**
	 * @param ScheduleItemList the ScheduleItemList to set
	 */
	public void setCruiseScheduleItemList(List<CruiseScheduleItemVO> ScheduleItemList) {
		this.ScheduleItemList = ScheduleItemList;
	}

	public List<CruiseScheduleChargeVO> getExtraItemChargeList() {
		return extraItemChargeList;
	}

	public void setExtraItemChargeList(List<CruiseScheduleChargeVO> extraItemChargeList) {
		this.extraItemChargeList = extraItemChargeList;
	}

	public Double getAmtAptAdt() {
		return amtAptAdt;
	}

	public void setAmtAptAdt(Double amtAptAdt) {
		this.amtAptAdt = amtAptAdt;
	}

	public Double getAmtAptChd() {
		return amtAptChd;
	}

	public void setAmtAptChd(Double amtAptChd) {
		this.amtAptChd = amtAptChd;
	}

	public Double getAmtFuelAdt() {
		return amtFuelAdt;
	}

	public void setAmtFuelAdt(Double amtFuelAdt) {
		this.amtFuelAdt = amtFuelAdt;
	}

	public Double getAmtFuelChd() {
		return amtFuelChd;
	}

	public void setAmtFuelChd(Double amtFuelChd) {
		this.amtFuelChd = amtFuelChd;
	}

	public Double getAmtTrvlIns() {
		return amtTrvlIns;
	}

	public void setAmtTrvlIns(Double amtTrvlIns) {
		this.amtTrvlIns = amtTrvlIns;
	}

	public Double getAmtVisa() {
		return amtVisa;
	}

	public void setAmtVisa(Double amtVisa) {
		this.amtVisa = amtVisa;
	}

	public Double getAmtAC() {
		return amtAC;
	}

	public void setAmtAC(Double amtAC) {
		this.amtAC = amtAC;
	}

	public Double getAmtTipping() {
		return amtTipping;
	}

	public void setAmtTipping(Double amtTipping) {
		this.amtTipping = amtTipping;
	}

	/**
	 * @return the amtDeviation
	 */
	public Double getAmtDeviation() {
		return amtDeviation;
	}

	/**
	 * @param amtDeviation the amtDeviation to set
	 */
	public void setAmtDeviation(Double amtDeviation) {
		this.amtDeviation = amtDeviation;
	}

	/**
	 * @return the tktValidity
	 */
	public Integer getTktValidity() {
		return tktValidity;
	}

	/**
	 * @param tktValidity the tktValidity to set
	 */
	public void setTktValidity(Integer tktValidity) {
		this.tktValidity = tktValidity;
	}

	/**
	 * @return the selectedScheduleitems
	 */
	public CruiseScheduleItemVO[] getSelectedScheduleItems() {
		return selectedScheduleItems;
	}

	/**
	 * @param selectedScheduleitems the selectedScheduleitems to set
	 */
	public void setSelectedScheduleItems(CruiseScheduleItemVO[] selectedScheduleItems) {
		this.selectedScheduleItems = selectedScheduleItems;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getEtd() {
		return etd;
	}

	public void setEtd(String etd) {
		this.etd = etd;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}
}
