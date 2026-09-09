package com.bcs.zsg.product.service;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.CruiseScheduleChargeVO;
import com.bcs.zsg.product.vo.CruiseScheduleItemVO;
import com.bcs.zsg.product.vo.CruiseScheduleVO;
import com.bcs.zsg.product.vo.CruiseVO;
import com.bcs.zsg.product.vo.TourDepartureVO;

public interface CruiseService {

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<CruiseVO> getCruiseList() throws BusinessException;

	/**
	 * 
	 * @param cruiseVO
	 * @throws BusinessException
	 */
	public void addCruise(CruiseVO cruiseVO) throws BusinessException;

	/**
	 * 
	 * @param cruiseVO
	 * @throws BusinessException
	 */
	public void updAriline(CruiseVO cruiseVO) throws BusinessException;

	/**
	 * 
	 * @param cruiseVO
	 * @throws BusinessException
	 */
	public void delCruise(CruiseVO cruiseVO) throws BusinessException;

	/**
	 * 
	 * @param cruiseScheduleVO
	 * @throws BusinessException
	 */
	public void addCruiseSchedule(CruiseScheduleVO cruiseScheduleVO) throws BusinessException;

	/**
	 * 
	 * @param cruiseScheduleVO
	 * @throws BusinessException
	 */
	public void delCruiseSchedule(CruiseScheduleVO cruiseScheduleVO) throws BusinessException;

	/**
	 * 
	 * @param cruiseScheduleItemVO
	 * @throws BusinessException
	 */
	public void addCruiseScheduleItem(CruiseScheduleItemVO cruiseScheduleItemVO) throws BusinessException;

	/**
	 * 
	 * @param cruiseScheduleItemVO
	 * @throws BusinessException
	 */
	public void updCruiseScheduleItem(CruiseScheduleItemVO cruiseScheduleItemVO) throws BusinessException;

	/**
	 * 
	 * @param addItemChargeList
	 * @param updItemChargeList
	 * @param delItemChargeList
	 * @throws BusinessException
	 */
	public void updCruiseScheduleCharge(List<CruiseScheduleChargeVO> addItemChargeList,List<CruiseScheduleChargeVO> updItemChargeList,
			List<CruiseScheduleChargeVO> delItemChargeList) throws BusinessException;

	/**
	 * 
	 * @param idCruise
	 * @return
	 * @throws BusinessException
	 */
	public List<CruiseScheduleVO> getCruiseScheduleList(Long idCruise) throws BusinessException;

	/**
	 * 
	 * @param idCruiseSchedule
	 * @return
	 * @throws BusinessException
	 */
	public List<CruiseScheduleChargeVO> getCruiseScheduleChargeList(Long idCruiseSchedule) throws BusinessException;

	/**
	 * 
	 * @param idCruise
	 * @return
	 * @throws BusinessException
	 */
	public CruiseVO getCruise(Long idCruise) throws BusinessException;

	/**
	 * 
	 * @param tourDepVO
	 * @return
	 * @throws BusinessException
	 */
	public CruiseScheduleVO getCruiseSchedule(TourDepartureVO tourDepVO) throws BusinessException;

	/**
	 * 
	 * @param id
	 * @param scheduleSearch
	 * @return
	 * @throws BusinessException
	 */
	public List<CruiseScheduleVO> getCruiseScheduleListByDesc(Long id, String scheduleSearch) throws BusinessException;

	/**
	 * 
	 * @param cruiseScheduleVO
	 * @throws BusinessException
	 */
	public void updCruiseSchedule(CruiseScheduleVO cruiseScheduleVO) throws BusinessException;

	/**
	 * 
	 * @param cruiseScheduleItemVO
	 * @throws BusinessException
	 */
	public void delCruiseScheduleItem(CruiseScheduleItemVO cruiseScheduleItemVO) throws BusinessException;
	
	public List<CruiseVO> getCruiseListByTypeCode(String typeCode) throws BusinessException;
	
	public CruiseScheduleVO getCruiseScheduleWithName(TourDepartureVO tourDepVO) throws BusinessException;

}
