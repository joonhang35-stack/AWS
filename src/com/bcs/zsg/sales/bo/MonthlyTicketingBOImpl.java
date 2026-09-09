package com.bcs.zsg.sales.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.service.MonthlyTicketingService;

public class MonthlyTicketingBOImpl implements MonthlyTicketingBO {

	@Autowired
	private MonthlyTicketingService monthlyTicketingService;
	
	@Override
	public int getListSizeMonthlyTicket(Map<String, Object> params) throws BusinessException {
		return monthlyTicketingService.getListSizeMonthlyTicket(params);
	}
	
	@Override
	public <T> T getMonthlyTicketGrandTotal(Map<String, Object> params) throws BusinessException {
		return monthlyTicketingService.getMonthlyTicketGrandTotal(params);
	}
	
	@Override
	public List<?> getListMonthlyTicket(Map<String, Object> params) throws BusinessException {
		return monthlyTicketingService.getListMonthlyTicket(params);
	}

	@Override
	public List<?> getTourCodeList() throws BusinessException {
		//By Ticketing type
		return monthlyTicketingService.getTourCodeList("ti");
	}
}
