package com.bcs.zsg.common.helper;

import java.math.BigDecimal;

import com.bcs.zsg.core.exception.BusinessException;

public class CalcUtils {

	public static BigDecimal add(double v1, double v2) throws BusinessException {
		BigDecimal temp1 = BigDecimal.valueOf(v1);
		BigDecimal temp2 = BigDecimal.valueOf(v2);
		return temp1.add(temp2);
	}
	
	public static BigDecimal subtract(double v1, double v2) throws BusinessException {
		BigDecimal temp1 = BigDecimal.valueOf(v1);
		BigDecimal temp2 = BigDecimal.valueOf(v2);
		return temp1.subtract(temp2);
	}
	
	public static BigDecimal multiply(double v1, double v2) throws BusinessException {
		BigDecimal temp1 = BigDecimal.valueOf(v1);
		BigDecimal temp2 = BigDecimal.valueOf(v2);
		return temp1.multiply(temp2);
	}
}
