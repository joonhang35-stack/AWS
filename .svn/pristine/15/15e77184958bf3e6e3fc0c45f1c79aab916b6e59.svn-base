package com.bcs.zsg.product.dao;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.CruiseScheduleChargeVO;
import com.bcs.zsg.product.vo.CruiseScheduleItemVO;
import com.bcs.zsg.product.vo.CruiseScheduleVO;
import com.bcs.zsg.product.vo.CruiseVO;

public interface CruiseDAO extends BaseDAO {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public boolean isCruiseScheduleExisted(Long id) throws BusinessException;

	/**
	 * 
	 * @param cruiseScheduleVO
	 * @return
	 * @throws BusinessException
	 */
	public boolean isCruiseScheduleExisted(CruiseScheduleVO cruiseScheduleVO) throws BusinessException;

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<CruiseVO> getCruiselist() throws BusinessException;

	/**
	 * 
	 * @param idCruise
	 * @return
	 * @throws BusinessException
	 */
	public List<CruiseScheduleVO> getCruiseScheduleList(Long idCruise) throws BusinessException;

	/**
	 * 
	 * @param ids 
	 * @param idCruise
	 * @return
	 * @throws BusinessException
	 */
	public List<CruiseScheduleItemVO> getCruiseScheduleItemList(Long idCruiseSchedule, Long[] ids) throws BusinessException;

	/**
	 * 
	 * @param idCruiseSchedule
	 * @return
	 * @throws BusinessException
	 */
	public List<CruiseScheduleChargeVO> getCruiseScheduleChargeList(Long idCruiseSchedule) throws BusinessException;

	/**
	 * 
	 * @param idCruiseSchedule
	 * @throws BusinessException
	 */
	public int delCruiseScheduleExtraCharges(Long idCruiseSchedule) throws BusinessException;

	/**
	 * 
	 * @param idCruise
	 * @return
	 * @throws BusinessException
	 */
	public CruiseVO getCruise(Long idCruise) throws BusinessException;

	/**
	 * 
	 * @param idCruiseSchedule
	 * @return
	 * @throws BusinessException
	 */
	public CruiseScheduleVO getCruiseSchedule(Long idCruiseSchedule) throws BusinessException;

	/**
	 * 
	 * @param id
	 * @param scheduleSearch
	 * @return
	 * @throws BusinessException
	 */
	public List<CruiseScheduleVO> getCruiseScheduleListByDesc(Long id, String scheduleSearch) throws BusinessException;

	public List<CruiseVO> getCruiseListByTypeCode(String typeCode) throws BusinessException;
	
	public CruiseScheduleVO getCruiseScheduleWithName(Long idCruiseSchedule) throws BusinessException;
}
