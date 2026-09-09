package com.bcs.zsg.product.bo;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.TourCruiseCabinDiscountVO;
import com.bcs.zsg.product.vo.TourCruiseCabinVO;

public interface TourCruiseCabinBO {

	public List<TourCruiseCabinVO> getTourCruiseCabinList(Long idTourDep, Long idCopmany) throws BusinessException;

	public boolean isTourCruiseCabinBooked(Long idTourCruiseCabin) throws BusinessException;

	public void updateTourCruiseCabinAcctCode(TourCruiseCabinVO tourCruiseCabinVO) throws BusinessException;
	
	/* ############################ For API - Start ############################ */
	public List<TourCruiseCabinVO> getTourCruiseCabinListForApi(Long idTourDep, Long idCompany) throws BusinessException;
	/* ############################ For API - End   ############################ */

	public List<TourCruiseCabinDiscountVO> getTourCruiseCabinDiscountList(Long idTourCruiseCabin) throws BusinessException;
	
}
