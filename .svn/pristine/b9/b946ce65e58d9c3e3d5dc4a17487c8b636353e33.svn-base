package com.bcs.zsg.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.CruiseService;
import com.bcs.zsg.product.service.TourPackageService;
import com.bcs.zsg.product.vo.CruiseScheduleChargeVO;
import com.bcs.zsg.product.vo.CruiseScheduleItemVO;
import com.bcs.zsg.product.vo.CruiseScheduleVO;
import com.bcs.zsg.product.vo.CruiseVO;
import com.bcs.zsg.product.vo.TourDepartureVO;

public class CruiseBOImpl implements CruiseBO {

	@Autowired
	private CruiseService cruiseService;
	@Autowired
	private TourPackageService tourPkgService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.CruiseBO#getCruiseList()
	 */
	@Override
	public List<CruiseVO> getCruiseList() throws BusinessException {
		return cruiseService.getCruiseList();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.CruiseBO#addCruise(com.bcs.zsg.product.vo.CruiseVO)
	 */
	@Override
	public void addCruise(CruiseVO cruiseVO) throws BusinessException {
		cruiseService.addCruise(cruiseVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.CruiseBO#updCruise(com.bcs.zsg.product.vo.CruiseVO)
	 */
	@Override
	public void updCruise(CruiseVO cruiseVO) throws BusinessException {
		cruiseService.updAriline(cruiseVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.CruiseBO#delCruise(com.bcs.zsg.product.vo.CruiseVO)
	 */
	@Override
	public void delCruise(CruiseVO cruiseVO) throws BusinessException {
		cruiseService.delCruise(cruiseVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.CruiseBO#addCruiseSchedule(com.bcs.zsg.product.vo.CruiseScheduleVO)
	 */
	@Override
	public void addCruiseSchedule(CruiseScheduleVO cruiseScheduleVO) throws BusinessException {
		cruiseService.addCruiseSchedule(cruiseScheduleVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.CruiseBO#delCruiseSchedule(com.bcs.zsg.product.vo.CruiseScheduleVO)
	 */
	@Override
	public void delCruiseSchedule(CruiseScheduleVO cruiseScheduleVO) throws BusinessException {
		cruiseService.delCruiseSchedule(cruiseScheduleVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.CruiseBO#addCruiseScheduleItem(com.bcs.zsg.product.vo.CruiseScheduleItemVO)
	 */
	@Override
	public void addCruiseScheduleItem(CruiseScheduleItemVO cruiseScheduleItemVO) throws BusinessException {
		cruiseService.addCruiseScheduleItem(cruiseScheduleItemVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.CruiseBO#updCruiseScheduleItem(com.bcs.zsg.product.vo.CruiseScheduleItemVO)
	 */
	@Override
	public void updCruiseScheduleItem(CruiseScheduleItemVO cruiseScheduleItemVO) throws BusinessException {
		cruiseService.updCruiseScheduleItem(cruiseScheduleItemVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.CruiseBO#updCruiseScheduleCharge(com.bcs.zsg.product.vo.CruiseScheduleVO, java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void updCruiseScheduleCharge(CruiseScheduleVO cruiseScheduleVO, List<CruiseScheduleChargeVO> addItemChargeList, List<CruiseScheduleChargeVO> updItemChargeList,
			List<CruiseScheduleChargeVO> delItemChargeList) throws BusinessException {
		cruiseService.updCruiseScheduleCharge(addItemChargeList, updItemChargeList, delItemChargeList);
		tourPkgService.updTourDepItemsValue(cruiseScheduleVO, addItemChargeList, updItemChargeList, delItemChargeList);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.CruiseBO#getCruiseScheduleList(java.lang.Long)
	 */
	@Override
	public List<CruiseScheduleVO> getCruiseScheduleList(Long idCruise) throws BusinessException {
		return cruiseService.getCruiseScheduleList(idCruise);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.CruiseBO#getCruiseScheduleItemList(java.lang.Long)
	 */
	@Override
	public List<CruiseScheduleChargeVO> getCruiseScheduleChargeList(Long idCruiseSchedule) throws BusinessException {
		return cruiseService.getCruiseScheduleChargeList(idCruiseSchedule);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.CruiseBO#getCruise(java.lang.Long)
	 */
	@Override
	public CruiseVO getCruise(Long idCruise) throws BusinessException {
		return cruiseService.getCruise(idCruise);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.CruiseBO#getCruiseSchedule(com.bcs.zsg.product.vo.TourDepartureVO)
	 */
	@Override
	public CruiseScheduleVO getCruiseSchedule(TourDepartureVO tourDepVO) throws BusinessException {
		return cruiseService.getCruiseSchedule(tourDepVO);
	}

	@Override
	public List<CruiseScheduleVO> getCruiseScheduleListByDesc(Long id, String scheduleSearch) throws BusinessException {
		return cruiseService.getCruiseScheduleListByDesc(id, scheduleSearch);
	}

	@Override
	public void updCruiseSchedule(CruiseScheduleVO cruiseScheduleVO) throws BusinessException {
		cruiseService.updCruiseSchedule(cruiseScheduleVO);
		//tourPkgService.updTourDepItemsValue(cruiseScheduleVO, null, null, null);
	}

	@Override
	public void delCruiseScheduleItem(CruiseScheduleItemVO cruiseScheduleItemVO) throws BusinessException {
		cruiseService.delCruiseScheduleItem(cruiseScheduleItemVO);
	}

	@Override
	public List<CruiseVO> getCruiseListByTypeCode(String typeCode) throws BusinessException {
		return cruiseService.getCruiseListByTypeCode(typeCode);
	}

	@Override
	public CruiseScheduleVO getCruiseScheduleWithName(TourDepartureVO tourDepVO) throws BusinessException {
		return cruiseService.getCruiseScheduleWithName(tourDepVO);
	}

}
