package com.bcs.zsg.product.vo;

import com.bcs.zsg.core.vo.BaseVO;

public class TourCruiseCabinDiscountVO extends BaseVO {

	private static final long serialVersionUID = 1L;
	
	public static String DISCOUNT_TYPE_TWN = "TWN";
	public static String DISCOUNT_TYPE_SGL = "SGL";
	public static String DISCOUNT_TYPE_PAX3 = "PAX3";
	public static String DISCOUNT_TYPE_PAX4 = "PAX4";
	public static String DISCOUNT_TYPE_INF = "INF";
	
	private Long idCruiseCabin;
	private double discountAmt;
	private int discountPax;
	
	public Long getIdCruiseCabin() {
		return idCruiseCabin;
	}

	public void setIdCruiseCabin(Long idCruiseCabin) {
		this.idCruiseCabin = idCruiseCabin;
	}

	public double getDiscountAmt() {
		return discountAmt;
	}
	
	public void setDiscountAmt(double discountAmt) {
		this.discountAmt = discountAmt;
	}
	
	public int getDiscountPax() {
		return discountPax;
	}
	
	public void setDiscountPax(int discountPax) {
		this.discountPax = discountPax;
	}
}
