package com.bcs.zsg.product.bo;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.AirlineScheduleChargeVO;
import com.bcs.zsg.product.vo.AirlineScheduleItemVO;
import com.bcs.zsg.product.vo.AirlineScheduleVO;
import com.bcs.zsg.product.vo.AirlineVO;
import com.bcs.zsg.product.vo.TourDepartureVO;

public interface AirlineBO {

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<AirlineVO> getAirlineList() throws BusinessException;

	/**
	 * 
	 * @param airlineVO
	 * @throws BusinessException
	 */
	public void addAirline(AirlineVO airlineVO) throws BusinessException;

	/**
	 * 
	 * @param airlineVO
	 * @throws BueinessException
	 */
	public void updAirline(AirlineVO airlineVO) throws BusinessException;

	/**
	 * 
	 * @param airlineVO
	 * @throws BusinessException
	 */
	public void delAirline(AirlineVO airlineVO) throws BusinessException;

	/**
	 * 
	 * @param airlineScheduleVO
	 * @throws BusinessException
	 */
	public void addAirlineSchedule(AirlineScheduleVO airlineScheduleVO) throws BusinessException;

	/**
	 * 
	 * @param airlineScheduleVO
	 * @throws BusinessException
	 */
	public void delAirlineSchedule(AirlineScheduleVO airlineScheduleVO) throws BusinessException;

	/**
	 * 
	 * @param airlineScheduleItemVO
	 * @throws BusinessException
	 */
	public void addAirlineScheduleItem(AirlineScheduleItemVO airlineScheduleItemVO) throws BusinessException;

	/**
	 * 
	 * @param airlineScheduleItemVO
	 * @throws BusinessException
	 */
	public void updAirlineScheduleItem(AirlineScheduleItemVO airlineScheduleItemVO) throws BusinessException;

	/**
	 * 
	 * @param airlineScheduleVO 
	 * @param addItemChargeList
	 * @param updItemChargeList
	 * @param delItemChargeList
	 * @throws BusinessException
	 */
	public void updAirlineScheduleCharge(AirlineScheduleVO airlineScheduleVO, List<AirlineScheduleChargeVO> addItemChargeList, List<AirlineScheduleChargeVO> updItemChargeList,
			List<AirlineScheduleChargeVO> delItemChargeList) throws BusinessException;

	/**
	 * 
	 * @param idAirline
	 * @return
	 */
	public List<AirlineScheduleVO> getAirlineScheduleList(Long idAirline) throws BusinessException;

	/**
	 * 
	 * @param idAirlineSchedule
	 * @return
	 * @throws BusinessException
	 */
	public List<AirlineScheduleChargeVO> getAirlineScheduleChargeList(Long idAirlineSchedule) throws BusinessException;
	
	/**
	 * 
	 * @param idAirline
	 * @return
	 * @throws BusinessException
	 */
	public AirlineVO getAirline(Long idAirline) throws BusinessException;

	/**
	 * 
	 * @param tourDepVO
	 * @return
	 * @throws BusinessException
	 */
	public AirlineScheduleVO getAirlineSchedule(TourDepartureVO tourDepVO) throws BusinessException;

	/**
	 * 
	 * @param id
	 * @param scheduleSearch
	 * @return
	 * @throws BusinessException
	 */
	public List<AirlineScheduleVO> getAirlineScheduleListByDesc(Long id, String scheduleSearch) throws BusinessException;

	/**
	 * 
	 * @param airlineScheduleVO
	 * @throws BusinessException
	 */
	public void updAirlineSchedule(AirlineScheduleVO airlineScheduleVO) throws BusinessException;

	/**
	 * 
	 * @param airlineScheduleItemVO
	 * @throws BusinessException
	 */
	public void delAirlineScheduleItem(AirlineScheduleItemVO airlineScheduleItemVO) throws BusinessException;

	public List<AirlineVO> getAirlineListByTypeCode(String typeCode) throws BusinessException;
	
	public AirlineScheduleVO getAirlineScheduleWithName(TourDepartureVO tourDepVO) throws BusinessException;
}
