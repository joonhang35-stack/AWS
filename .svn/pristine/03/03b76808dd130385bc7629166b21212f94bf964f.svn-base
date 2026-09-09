package com.bcs.zsg.product.dao;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.TourCruiseCabinDiscountVO;
import com.bcs.zsg.product.vo.TourCruiseCabinVO;

public interface TourCruiseCabinDAO extends BaseDAO {

	public List<TourCruiseCabinDiscountVO> getTourCruiseCabinDiscountList(Long idTourCruiseCabin) throws BusinessException;

	public List<TourCruiseCabinVO> getHubTourCruiseCabinList(Long idTourDep, Long idCompany) throws BusinessException;
	
	public List<TourCruiseCabinVO> getTourCruiseCabinList(Long idTourDep, Long idCompany) throws BusinessException;

	public void deleteByTourDep(Long idTourDep) throws BusinessException;

	public boolean isTourCruiseCabinBooked(Long idTourCruiseCabin) throws BusinessException;

	public void updateTourCruiseCabinAcctCode(TourCruiseCabinVO tourCruiseCabinVO) throws BusinessException;

	public void updateByCode(TourCruiseCabinVO tourCruiseCabinVO) throws BusinessException;

	/* ############################ For API - Start ############################ */
	public List<TourCruiseCabinVO> getTourCruiseCabinListForApi(Long idTourDep, Long idCompany) throws BusinessException;
	/* ############################ For API - End   ############################ */
}
