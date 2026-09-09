package com.bcs.zsg.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.TourCruiseCabinService;
import com.bcs.zsg.product.vo.TourCruiseCabinDiscountVO;
import com.bcs.zsg.product.vo.TourCruiseCabinVO;

public class TourCruiseCabinBOImpl implements TourCruiseCabinBO {

	@Autowired
	private TourCruiseCabinService tourCruiseCabinService;
	
	@Override
	public List<TourCruiseCabinVO> getTourCruiseCabinList(Long idTourDep, Long idCompany) throws BusinessException {
		return tourCruiseCabinService.getTourCruiseCabinList(idTourDep, idCompany);
	}

	@Override
	public boolean isTourCruiseCabinBooked(Long idTourCruiseCabin) throws BusinessException {
		return tourCruiseCabinService.isTourCruiseCabinBooked(idTourCruiseCabin);
	}

	@Override
	public void updateTourCruiseCabinAcctCode(TourCruiseCabinVO tourCruiseCabinVO) throws BusinessException {
		tourCruiseCabinService.updateTourCruiseCabinAcctCode(tourCruiseCabinVO);
	}
	
	@Override
	public List<TourCruiseCabinDiscountVO> getTourCruiseCabinDiscountList(Long idTourCruiseCabin) throws BusinessException {
		return tourCruiseCabinService.getTourCruiseCabinDiscountList(idTourCruiseCabin);
	}

	/* ############################ For API - Start ############################ */
	@Override
	public List<TourCruiseCabinVO> getTourCruiseCabinListForApi(Long idTourDep, Long idCompany) throws BusinessException {
		return tourCruiseCabinService.getTourCruiseCabinListForApi(idTourDep, idCompany);
	}
	/* ############################ For API - End   ############################ */
}
