package com.bcs.zsg.sales.helper;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.sales.vo.BookingChargeItemVO;
import com.bcs.zsg.sales.vo.BookingVO;

public class InvoiceBeanHelper {
	
	@PostConstruct
	public void init() throws BusinessException {
	}
	
	public static void calculateCabin(BookingVO bookingVO, TourDepartureVO tourDepVO) throws BusinessException {
		int totalCabin = 0;
		int totalCabinBook = 0;
		int totalPax = 0;
		int totalHeadCount = 0;

		BigDecimal totalAmt = BigDecimal.ZERO;
		BigDecimal totalDiscount = BigDecimal.ZERO;
		BigDecimal totalPortCharges = BigDecimal.ZERO;

		for (BookingChargeItemVO vo : bookingVO.getBookingChargeItemList()) {
			totalCabin = 0;
			totalPax = 0;

			System.out.println("vo.getPaxTwn(): " + vo.getQuantity());
			System.out.println("vo.getPaxSgl(): " + vo.getQuantitySgl());
			System.out.println("vo.getPax3(): " + vo.getQuantity3());
			System.out.println("vo.getPax4(): " + vo.getQuantity4());
			
			if (ProductConstant.TOUR_DEP_ITM_TYPE_TOUR.equals(vo.getTypeCd())) {
				if (vo.getQuantity() > 0) {
					totalAmt = totalAmt.add(new BigDecimal(String.valueOf(vo.getQuantity()))
							.multiply(new BigDecimal(String.valueOf(vo.getAmount()))));
					totalPortCharges = totalPortCharges.add(new BigDecimal(String.valueOf(vo.getQuantity()))
							.multiply(new BigDecimal(tourDepVO.getMiscAdt())));
					totalPax += vo.getQuantity();
				}
				if (vo.getQuantitySgl() > 0) {
					totalAmt = totalAmt.add(new BigDecimal(String.valueOf(vo.getQuantitySgl()))
							.multiply(new BigDecimal(String.valueOf(vo.getAmountSgl()))));
					totalPortCharges = totalPortCharges.add(new BigDecimal(String.valueOf(vo.getQuantitySgl()))
							.multiply(new BigDecimal(tourDepVO.getMiscAdt())));
					totalPax += vo.getQuantitySgl();
				}
				if (vo.getQuantity3() > 0) {
					totalAmt = totalAmt.add(new BigDecimal(String.valueOf(vo.getQuantity3()))
							.multiply(new BigDecimal(String.valueOf(vo.getAmount3()))));
					totalPortCharges = totalPortCharges.add(
							new BigDecimal(String.valueOf(vo.getQuantity3())).multiply(new BigDecimal(tourDepVO.getMiscAdt())));
					totalPax += vo.getQuantity3();
				}
				if (vo.getQuantity4() > 0) {
					totalAmt = totalAmt.add(new BigDecimal(String.valueOf(vo.getQuantity4()))
							.multiply(new BigDecimal(String.valueOf(vo.getAmount4()))));
					totalPortCharges = totalPortCharges.add(
							new BigDecimal(String.valueOf(vo.getQuantity4())).multiply(new BigDecimal(tourDepVO.getMiscAdt())));
					totalPax += vo.getQuantity4();
				}
				if (vo.getQuantityInf() > 0) {
					totalAmt = totalAmt.add(new BigDecimal(String.valueOf(vo.getQuantityInf()))
							.multiply(new BigDecimal(String.valueOf(vo.getAmountInf()))));
					totalPortCharges = totalPortCharges.add(new BigDecimal(String.valueOf(vo.getQuantityInf()))
							.multiply(new BigDecimal(tourDepVO.getMiscAdt())));
					totalPax += vo.getQuantityInf();
				}

				if (vo.getQuantity() > 0)
					totalCabin += (int) Math.floor(vo.getQuantity() / 2);
				if (vo.getQuantitySgl() > 0)
					totalCabin += vo.getQuantitySgl();
				totalCabinBook += totalCabin;
				totalHeadCount += totalPax;
			}

//			if (vo.getCalcDiscount() != null)
//				totalDiscount = totalDiscount.add(vo.getCalcDiscount());
//			else {
//				if (totalPax > 0) {
//					int remainPax = 0;
//
//					if (vo.getDiscountLvl1() > 0) {
//						if (vo.getDiscountLvl1Pax() > 0) {
//							if (totalPax <= vo.getDiscountLvl1Pax())
//								totalDiscount = totalDiscount
//										.add(new BigDecimal(vo.getDiscountLvl1()).multiply(new BigDecimal(totalPax)));
//							else {
//								remainPax = totalPax - vo.getDiscountLvl1Pax();
//								totalDiscount = totalDiscount.add(new BigDecimal(vo.getDiscountLvl1())
//										.multiply(new BigDecimal(vo.getDiscountLvl1Pax())));
//							}
//						} else
//							remainPax = totalPax;
//					}
//
//					if (remainPax > 0) {
//						if (vo.getDiscountLvl2() > 0 && vo.getDiscountLvl2Pax() > 0) {
//							if (remainPax <= vo.getDiscountLvl2Pax())
//								totalDiscount = totalDiscount
//										.add(new BigDecimal(vo.getDiscountLvl2()).multiply(new BigDecimal(remainPax)));
//							else
//								totalDiscount = totalDiscount.add(new BigDecimal(vo.getDiscountLvl2())
//										.multiply(new BigDecimal(vo.getDiscountLvl2Pax())));
//						}
//					}
//				}
//			}
		}

		double deposit = 0;
		bookingVO.setTotalAmount(totalAmt.add(totalPortCharges).doubleValue());
		bookingVO.setDiscountAmount(totalDiscount.doubleValue());
		bookingVO.setDepositAmount(deposit);
		bookingVO.setAmountPayable(totalAmt.add(totalPortCharges).subtract(totalDiscount).doubleValue());
		bookingVO.setTotalCabin(totalCabinBook);
		bookingVO.setTotalHeadCount(totalHeadCount);
	}

}
