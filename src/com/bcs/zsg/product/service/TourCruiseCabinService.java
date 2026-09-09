package com.bcs.zsg.product.service;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.TourCruiseCabinDiscountVO;
import com.bcs.zsg.product.vo.TourCruiseCabinVO;
import com.bcs.zsg.product.vo.TourDepartureVO;

public interface TourCruiseCabinService {

	public void addTourCruiseCabinList(List<TourCruiseCabinVO> tourCruiseCabinList, TourDepartureVO tourDepVO) throws BusinessException;
	
	public void updTourCruiseCabinList(List<TourCruiseCabinVO> tourCruiseCabinList, TourDepartureVO tourDepVO) throws BusinessException;
	
	public void addTourCruiseCabin(TourCruiseCabinVO tourCruiseCabinVO) throws BusinessException;
	
	public void updTourCruiseCabin(TourCruiseCabinVO tourCruiseCabinVO) throws BusinessException;

	public List<TourCruiseCabinVO> getTourCruiseCabinList(Long idTourDep, Long idCompany) throws BusinessException;

	public boolean isTourCruiseCabinBooked(Long idTourCruiseCabin) throws BusinessException;

	public List<TourCruiseCabinDiscountVO> getTourCruiseCabinDiscountList(Long idTourCruiseCabin) throws BusinessException;

	public void updateTourCruiseCabinAcctCode(TourCruiseCabinVO tourCruiseCabinVO) throws BusinessException;

	/* ############################ For API - Start ############################ */
	public List<TourCruiseCabinVO> getTourCruiseCabinListForApi(Long idTourDep, Long idCompany) throws BusinessException;
	/* ############################ For API - End   ############################ */
}
