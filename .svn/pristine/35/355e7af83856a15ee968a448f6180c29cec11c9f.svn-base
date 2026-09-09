package com.bcs.zsg.common.helper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.bcs.zsg.core.exception.BusinessException;

public class CalculationUtils {

	/**
	 * 
	 * @param basic
	 * @param percent
	 * @return
	 */
	public static double tourCalculate(double basic, double percent) throws BusinessException {
		// do the rounding
		return Math.round(basic * percent / 100);
	}
	
	public static Integer calculateAgeByIdNo(String idNo) {
		try {
			if (StringUtils.isNotBlank(idNo)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		        Date dob = sdf.parse(idNo);
		        if (idNo.length() >= 6) dob = sdf.parse(idNo.substring(0, 6));

	            // Extract the year, month, and day
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(dob);
	            int year = cal.get(Calendar.YEAR) % 100;
	            int month = cal.get(Calendar.MONTH);
	            int day = cal.get(Calendar.DAY_OF_MONTH);

	            // Set the century based on certain conditions
	            if (year > Calendar.getInstance().get(Calendar.YEAR) % 100) {
	                year += 1900; // Assume it belongs to the 20th century
	            } else {
	                year += 2000; // Assume it belongs to the 21st century
	            }

	            // Set the corrected date of birth
	            cal.set(year, month, day);
	            dob = cal.getTime();
		        
				Calendar cldDOB = Calendar.getInstance();
				cldDOB.setTime(dob);
				Calendar today = Calendar.getInstance();

				Integer age = today.get(Calendar.YEAR) - cldDOB.get(Calendar.YEAR);
				
				if(today.get(Calendar.DAY_OF_YEAR) <= cldDOB.get(Calendar.DAY_OF_YEAR)) {
					age--;
				}
				
				return age;
			}
			return 0;
		} catch (Throwable t) {
			return 0;
		}
	}
	
	public static double bigDecimalSubstitution(double value1, double value2) throws BusinessException {
		BigDecimal temp1 = BigDecimal.valueOf(value1);
		BigDecimal temp2 = BigDecimal.valueOf(value2);
		return temp1.subtract(temp2).doubleValue();
	}
}
