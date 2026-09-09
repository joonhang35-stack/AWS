package com.bcs.zsg.product.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.dao.TicketingDAO;
import com.bcs.zsg.product.vo.AirlineVO;
import com.bcs.zsg.product.vo.TourDepartureViewVO;

public class TicketingServiceImpl implements TicketingService {

	@Autowired
	private TicketingDAO ticketingDAO;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.TicketingService#getTicketingList()
	 */
	@Override
	public List<AirlineVO> getTicketingList(AirlineVO airlineVO) throws BusinessException {
		List<AirlineVO> ticketingList = ticketingDAO.getTicketingList();
		if (CollectionUtils.isNotEmpty(ticketingList)) {
			if (airlineVO == null) ticketingList.get(0).setTicketingList(ticketingDAO.getTicketingListByAirline(ticketingList.get(0).getId()));
			else {
				for (AirlineVO vo : ticketingList) {
					if (vo.getId().equals(airlineVO.getId())) {
						vo.setTicketingList(ticketingDAO.getTicketingListByAirline(vo.getId()));
						break;
					}
				}
			}
		}
		return ticketingList;
		
		/*if (CollectionUtils.isNotEmpty(ticketingList)) {
		int flag = 0;
			for (AirlineVO vo : ticketingList) {
				//vo.setTourDepViewList(ticketingDAO.getTourDepViewList(vo.getId()));
				vo.setTicketingList(ticketingDAO.getTicketingListByAirline(vo.getId()));
				if (flag == 0 && vo.getTicketingList() != null && vo.getTicketingList().size() > 0) flag = 1;
			}
			if (flag == 1) return ticketingList;
		}
		return null;*/
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.TicketingService#getTourCodeList(com.bcs.zsg.cfg.sec.vo.EmployeeVO)
	 */
	@Override
	public List<TourDepartureViewVO> getTourCodeList(EmployeeVO employeeVO) throws BusinessException {
		return ticketingDAO.getTourCodeList(employeeVO);
	}

}
