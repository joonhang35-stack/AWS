package com.bcs.zsg.product.vo;

import java.math.BigDecimal;
import java.util.List;

import com.bcs.zsg.core.vo.BaseVO;
import com.google.gson.annotations.Expose;

public class TourCruiseCabinVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	@Expose
	private Long idBase;
	private Long idTourDep;
	private Long idCompany;
	private Long idAcct;
	@Expose
	private String cruiseCabinCd;
	@Expose
	private String cruiseCabinDesc;
	@Expose
	private int cabinAllotment;
	@Expose
	private int paxPerCabin;
	@Expose
	private double priceTwn;
	@Expose
	private double price3;
	@Expose
	private double price4;
	@Expose
	private double priceSgl;
	@Expose
	private double price3Chd;
	@Expose
	private double price4Chd;
	@Expose
	private double priceInf;
	@Expose
	private String tourStatusCd;
	@Expose
	private Boolean isLock;

	@Expose
	private double discountLvl1;
	@Expose
	private double discountLvl2;
	@Expose
	private int discountLvl1Pax;
	@Expose
	private int discountLvl2Pax;

	private int totalConfirmedCabin;
	private int totalKivCabin;
	private int balanceCabin;

	private List<TourCruiseCabinDiscountVO> cabinDiscountList;

	private Long idTourCruiseCabin;
	@Expose
	private int paxTwn;
	@Expose
	private int pax3;
	@Expose
	private int pax4;
	@Expose
	private int paxSgl;
	@Expose
	private int pax3Chd;
	@Expose
	private int pax4Chd;
	@Expose
	private int paxInf;
	@Expose
	private int ttlCabin;
	private double totalPrice;

	private BigDecimal calcDiscount;

	public double getPrice3Chd() {
		return price3Chd;
	}

	public double getPrice4Chd() {
		return price4Chd;
	}

	public int getPax3Chd() {
		return pax3Chd;
	}

	public int getPax4Chd() {
		return pax4Chd;
	}

	public void setPrice3Chd(double price3Chd) {
		this.price3Chd = price3Chd;
	}

	public void setPrice4Chd(double price4Chd) {
		this.price4Chd = price4Chd;
	}

	public void setPax3Chd(int pax3Chd) {
		this.pax3Chd = pax3Chd;
	}

	public void setPax4Chd(int pax4Chd) {
		this.pax4Chd = pax4Chd;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getBalanceCabin() {
		return balanceCabin;
	}

	public void setBalanceCabin(int balanceCabin) {
		this.balanceCabin = balanceCabin;
	}

	public Long getIdTourDep() {
		return idTourDep;
	}

	public void setIdTourDep(Long idTourDep) {
		this.idTourDep = idTourDep;
	}

	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	public Long getIdAcct() {
		return idAcct;
	}

	public void setIdAcct(Long idAcct) {
		this.idAcct = idAcct;
	}

	public String getCruiseCabinCd() {
		return cruiseCabinCd;
	}

	public void setCruiseCabinCd(String cruiseCabinCd) {
		this.cruiseCabinCd = cruiseCabinCd;
	}

	public int getCabinAllotment() {
		return cabinAllotment;
	}

	public void setCabinAllotment(int cabinAllotment) {
		this.cabinAllotment = cabinAllotment;
	}

	public int getPaxPerCabin() {
		return paxPerCabin;
	}

	public void setPaxPerCabin(int paxPerCabin) {
		this.paxPerCabin = paxPerCabin;
	}

	public double getPriceTwn() {
		return priceTwn;
	}

	public void setPriceTwn(double priceTwn) {
		this.priceTwn = priceTwn;
	}

	public double getPrice3() {
		return price3;
	}

	public void setPrice3(double price3) {
		this.price3 = price3;
	}

	public double getPrice4() {
		return price4;
	}

	public void setPrice4(double price4) {
		this.price4 = price4;
	}

	public double getPriceSgl() {
		return priceSgl;
	}

	public void setPriceSgl(double priceSgl) {
		this.priceSgl = priceSgl;
	}

	public double getPriceInf() {
		return priceInf;
	}

	public void setPriceInf(double priceInf) {
		this.priceInf = priceInf;
	}

	public String getTourStatusCd() {
		return tourStatusCd;
	}

	public void setTourStatusCd(String tourStatusCd) {
		this.tourStatusCd = tourStatusCd;
	}

	public Double getDiscountLvl1() {
		return discountLvl1;
	}

	public void setDiscountLvl1(Double discountLvl1) {
		this.discountLvl1 = discountLvl1;
	}

	public Double getDiscountLvl2() {
		return discountLvl2;
	}

	public void setDiscountLvl2(Double discountLvl2) {
		this.discountLvl2 = discountLvl2;
	}

	public Integer getDiscountLvl1Pax() {
		return discountLvl1Pax;
	}

	public void setDiscountLvl1Pax(Integer discountLvl1Pax) {
		this.discountLvl1Pax = discountLvl1Pax;
	}

	public Integer getDiscountLvl2Pax() {
		return discountLvl2Pax;
	}

	public void setDiscountLvl2Pax(Integer discountLvl2Pax) {
		this.discountLvl2Pax = discountLvl2Pax;
	}

	public int getTotalConfirmedCabin() {
		return totalConfirmedCabin;
	}

	public void setTotalConfirmedCabin(int totalConfirmedCabin) {
		this.totalConfirmedCabin = totalConfirmedCabin;
	}

	public int getTotalKivCabin() {
		return totalKivCabin;
	}

	public void setTotalKivCabin(int totalKivCabin) {
		this.totalKivCabin = totalKivCabin;
	}

	public List<TourCruiseCabinDiscountVO> getCabinDiscountList() {
		return cabinDiscountList;
	}

	public void setCabinDiscountList(List<TourCruiseCabinDiscountVO> cabinDiscountList) {
		this.cabinDiscountList = cabinDiscountList;
	}

	public Long getIdTourCruiseCabin() {
		return idTourCruiseCabin;
	}

	public void setIdTourCruiseCabin(Long idTourCruiseCabin) {
		this.idTourCruiseCabin = idTourCruiseCabin;
	}

	public int getPaxTwn() {
		return paxTwn;
	}

	public void setPaxTwn(int paxTwn) {
		this.paxTwn = paxTwn;
	}

	public int getPax3() {
		return pax3;
	}

	public void setPax3(int pax3) {
		this.pax3 = pax3;
	}

	public int getPax4() {
		return pax4;
	}

	public void setPax4(int pax4) {
		this.pax4 = pax4;
	}

	public int getPaxSgl() {
		return paxSgl;
	}

	public void setPaxSgl(int paxSgl) {
		this.paxSgl = paxSgl;
	}

	public int getPaxInf() {
		return paxInf;
	}

	public void setPaxInf(int paxInf) {
		this.paxInf = paxInf;
	}

	public int getTtlCabin() {
		return ttlCabin;
	}

	public void setTtlCabin(int ttlCabin) {
		this.ttlCabin = ttlCabin;
	}

	public String getCruiseCabinDesc() {
		return cruiseCabinDesc;
	}

	public void setCruiseCabinDesc(String cruiseCabinDesc) {
		this.cruiseCabinDesc = cruiseCabinDesc;
	}

	public BigDecimal getCalcDiscount() {
		return calcDiscount;
	}

	public void setCalcDiscount(BigDecimal calcDiscount) {
		this.calcDiscount = calcDiscount;
	}

	public Long getIdBase() {
		return idBase;
	}

	public void setIdBase(Long idBase) {
		this.idBase = idBase;
	}

	public Boolean getIsLock() {
		return isLock;
	}

	public void setIsLock(Boolean isLock) {
		this.isLock = isLock;
	}

}
