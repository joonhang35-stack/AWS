package com.bcs.zsg.db.bterp.dao.airline;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.AirlineScheduleChargeVO;
import com.bcs.zsg.product.vo.AirlineScheduleItemVO;
import com.bcs.zsg.product.vo.AirlineScheduleVO;
import com.bcs.zsg.product.vo.AirlineVO;

public interface AirlineDAO extends BaseDAO {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public boolean isAirlineScheduleExisted(Long id) throws BusinessException;

	/**
	 * 
	 * @param airlineScheduleVO
	 * @return
	 * @throws BusinessException
	 */
	public boolean isAirlineScheduleExisted(AirlineScheduleVO airlineScheduleVO) throws BusinessException;

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<AirlineVO> getAirlinelist() throws BusinessException;

	/**
	 * 
	 * @param idAirline
	 * @return
	 * @throws BusinessException
	 */
	public List<AirlineScheduleVO> getAirlineScheduleList(Long idAirline) throws BusinessException;

	/**
	 * 
	 * @param ids 
	 * @param idAirline
	 * @return
	 * @throws BusinessException
	 */
	public List<AirlineScheduleItemVO> getAirlineScheduleItemList(Long idAirlineSchedule, Long[] ids) throws BusinessException;

	/**
	 * 
	 * @param idAirlineSchedule
	 * @return
	 * @throws BusinessException
	 */
	public List<AirlineScheduleChargeVO> getAirlineScheduleChargeList(Long idAirlineSchedule) throws BusinessException;

	/**
	 * 
	 * @param idAirlineSchedule
	 * @throws BusinessException
	 */
	public int delAirlineScheduleExtraCharges(Long idAirlineSchedule) throws BusinessException;

	/**
	 * 
	 * @param idAirline
	 * @return
	 * @throws BusinessException
	 */
	public AirlineVO getAirline(Long idAirline) throws BusinessException;

	/**
	 * 
	 * @param idAirlineSchedule
	 * @return
	 * @throws BusinessException
	 */
	public AirlineScheduleVO getAirlineSchedule(Long idAirlineSchedule) throws BusinessException;

	/**
	 * 
	 * @param id
	 * @param scheduleSearch
	 * @return
	 * @throws BusinessException
	 */
	public List<AirlineScheduleVO> getAirlineScheduleListByDesc(Long id, String scheduleSearch) throws BusinessException;

	public List<AirlineVO> getAirlineListByTypeCode(String typeCode) throws BusinessException;
	
	public AirlineScheduleVO getAirlineScheduleWithName(Long idAirlineSchedule) throws BusinessException;
}
