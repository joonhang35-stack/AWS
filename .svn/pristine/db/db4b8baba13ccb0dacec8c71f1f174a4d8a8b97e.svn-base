package com.bcs.zsg.sales.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.dao.tourdep.TourDepDAO;
import com.bcs.zsg.db.bterp.dao.view.monthlyticketing.MonthlyTicketingDAO;

@Service
public class MonthlyTicketingServiceImpl implements MonthlyTicketingService {

	@Autowired
	private MonthlyTicketingDAO monthlyTicketingDAO;

	@Autowired
	private TourDepDAO tourDepDAO;
	
	@Override
	public int getListSizeMonthlyTicket(Map<String, Object> params) throws BusinessException {
		return monthlyTicketingDAO.getListSizeMonthlyTicket(params);
	}

	@Override
	public <T> T getMonthlyTicketGrandTotal(Map<String, Object> params) throws BusinessException {
		return monthlyTicketingDAO.getMonthlyTicketGrandTotal(params);
	}
	
	@Override
	public List<?> getListMonthlyTicket(Map<String, Object> params) throws BusinessException {
		return monthlyTicketingDAO.getListMonthlyTicket(params);
	}

	@Override
	public List<?> getTourCodeList(String invoiceCategory) throws BusinessException {
		return tourDepDAO.getTourDepCodeByInvoiceCategory(invoiceCategory);
	}
}
