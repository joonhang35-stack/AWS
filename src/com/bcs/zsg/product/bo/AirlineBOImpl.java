package com.bcs.zsg.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.AirlineService;
import com.bcs.zsg.product.service.TourPackageService;
import com.bcs.zsg.product.vo.AirlineScheduleChargeVO;
import com.bcs.zsg.product.vo.AirlineScheduleItemVO;
import com.bcs.zsg.product.vo.AirlineScheduleVO;
import com.bcs.zsg.product.vo.AirlineVO;
import com.bcs.zsg.product.vo.TourDepartureVO;

public class AirlineBOImpl implements AirlineBO {

	@Autowired
	private AirlineService airlineService;
	@Autowired
	private TourPackageService tourPkgService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.AirlineBO#getAirlineList()
	 */
	@Override
	public List<AirlineVO> getAirlineList() throws BusinessException {
		return airlineService.getAirlineList();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.AirlineBO#addAirline(com.bcs.zsg.product.vo.AirlineVO)
	 */
	@Override
	public void addAirline(AirlineVO airlineVO) throws BusinessException {
		airlineService.addAirline(airlineVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.AirlineBO#updAirline(com.bcs.zsg.product.vo.AirlineVO)
	 */
	@Override
	public void updAirline(AirlineVO airlineVO) throws BusinessException {
		airlineService.updAriline(airlineVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.AirlineBO#delAirline(com.bcs.zsg.product.vo.AirlineVO)
	 */
	@Override
	public void delAirline(AirlineVO airlineVO) throws BusinessException {
		airlineService.delAirline(airlineVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.AirlineBO#addAirlineSchedule(com.bcs.zsg.product.vo.AirlineScheduleVO)
	 */
	@Override
	public void addAirlineSchedule(AirlineScheduleVO airlineScheduleVO) throws BusinessException {
		airlineService.addAirlineSchedule(airlineScheduleVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.AirlineBO#delAirlineSchedule(com.bcs.zsg.product.vo.AirlineScheduleVO)
	 */
	@Override
	public void delAirlineSchedule(AirlineScheduleVO airlineScheduleVO) throws BusinessException {
		airlineService.delAirlineSchedule(airlineScheduleVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.AirlineBO#addAirlineScheduleItem(com.bcs.zsg.product.vo.AirlineScheduleItemVO)
	 */
	@Override
	public void addAirlineScheduleItem(AirlineScheduleItemVO airlineScheduleItemVO) throws BusinessException {
		airlineService.addAirlineScheduleItem(airlineScheduleItemVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.AirlineBO#updAirlineScheduleItem(com.bcs.zsg.product.vo.AirlineScheduleItemVO)
	 */
	@Override
	public void updAirlineScheduleItem(AirlineScheduleItemVO airlineScheduleItemVO) throws BusinessException {
		airlineService.updAirlineScheduleItem(airlineScheduleItemVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.AirlineBO#updAirlineScheduleCharge(com.bcs.zsg.product.vo.AirlineScheduleVO, java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void updAirlineScheduleCharge(AirlineScheduleVO airlineScheduleVO, List<AirlineScheduleChargeVO> addItemChargeList, List<AirlineScheduleChargeVO> updItemChargeList,
			List<AirlineScheduleChargeVO> delItemChargeList) throws BusinessException {
		airlineService.updAirlineScheduleCharge(addItemChargeList, updItemChargeList, delItemChargeList);
		tourPkgService.updTourDepItemsValue(airlineScheduleVO, addItemChargeList, updItemChargeList, delItemChargeList);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.AirlineBO#getAirlineScheduleList(java.lang.Long)
	 */
	@Override
	public List<AirlineScheduleVO> getAirlineScheduleList(Long idAirline) throws BusinessException {
		return airlineService.getAirlineScheduleList(idAirline);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.AirlineBO#getAirlineScheduleItemList(java.lang.Long)
	 */
	@Override
	public List<AirlineScheduleChargeVO> getAirlineScheduleChargeList(Long idAirlineSchedule) throws BusinessException {
		return airlineService.getAirlineScheduleChargeList(idAirlineSchedule);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.AirlineBO#getAirline(java.lang.Long)
	 */
	@Override
	public AirlineVO getAirline(Long idAirline) throws BusinessException {
		return airlineService.getAirline(idAirline);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.AirlineBO#getAirlineSchedule(com.bcs.zsg.product.vo.TourDepartureVO)
	 */
	@Override
	public AirlineScheduleVO getAirlineSchedule(TourDepartureVO tourDepVO) throws BusinessException {
		return airlineService.getAirlineSchedule(tourDepVO);
	}

	@Override
	public List<AirlineScheduleVO> getAirlineScheduleListByDesc(Long id, String scheduleSearch) throws BusinessException {
		return airlineService.getAirlineScheduleListByDesc(id, scheduleSearch);
	}

	@Override
	public void updAirlineSchedule(AirlineScheduleVO airlineScheduleVO) throws BusinessException {
		airlineService.updAirlineSchedule(airlineScheduleVO);
		//tourPkgService.updTourDepItemsValue(airlineScheduleVO, null, null, null);
	}

	@Override
	public void delAirlineScheduleItem(AirlineScheduleItemVO airlineScheduleItemVO) throws BusinessException {
		airlineService.delAirlineScheduleItem(airlineScheduleItemVO);
	}

	@Override
	public List<AirlineVO> getAirlineListByTypeCode(String typeCode) throws BusinessException {
		return airlineService.getAirlineListByTypeCode(typeCode);
	}

	@Override
	public AirlineScheduleVO getAirlineScheduleWithName(TourDepartureVO tourDepVO) throws BusinessException {
		return airlineService.getAirlineScheduleWithName(tourDepVO);
	}
}
