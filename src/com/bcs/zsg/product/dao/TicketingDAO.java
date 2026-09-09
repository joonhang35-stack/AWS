package com.bcs.zsg.product.dao;

import java.util.List;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.AirlineVO;
import com.bcs.zsg.product.vo.TicketingVO;
import com.bcs.zsg.product.vo.TourDepartureViewVO;

public interface TicketingDAO extends BaseDAO {

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<AirlineVO> getTicketingList() throws BusinessException;

	/**
	 * 
	 * @param idAirline
	 * @return
	 * @throws BusinessException
	 */
	public List<TourDepartureViewVO> getTourDepViewList(Long idAirline) throws BusinessException;

	/**
	 * 
	 * @param idAirline
	 * @return
	 * @throws BusinessException
	 */
	public List<TicketingVO> getTicketingListByAirline(Long idAirline) throws BusinessException;

	/**
	 * 
	 * @param employeeVO 
	 * @return
	 * @throws BusinessException
	 */
	public List<TourDepartureViewVO> getTourCodeList(EmployeeVO employeeVO) throws BusinessException;


}
