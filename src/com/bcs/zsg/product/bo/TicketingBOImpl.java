package com.bcs.zsg.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.TicketingService;
import com.bcs.zsg.product.vo.AirlineVO;
import com.bcs.zsg.product.vo.TourDepartureViewVO;

public class TicketingBOImpl implements TicketingBO {

	@Autowired
	private TicketingService ticketingService;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TicketingBO#getTicketingList()
	 */
	@Override
	public List<AirlineVO> getTicketingList(AirlineVO airlineVO) throws BusinessException {
		return ticketingService.getTicketingList(airlineVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TicketingBO#getTourCodeList(com.bcs.zsg.cfg.sec.vo.EmployeeVO)
	 */
	@Override
	public List<TourDepartureViewVO> getTourCodeList(EmployeeVO employeeVO) throws BusinessException {
		return ticketingService.getTourCodeList(employeeVO);
	}
}
