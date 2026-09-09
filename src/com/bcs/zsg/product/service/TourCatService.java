package com.bcs.zsg.product.service;

import java.util.List;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.TourCatVO;
import com.bcs.zsg.product.vo.TourCatViewVO;

public interface TourCatService {

	/**
	 * 
	 * @param searchParamVO 
	 * @return
	 * @throws BusinessException
	 */
	public List<TourCatViewVO> getTourCatViewList(SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * 
	 * @param tourCatVO
	 * @throws BusinessException
	 */
	public void addTourCat(TourCatVO tourCatVO) throws BusinessException;

	/**
	 * 
	 * @param tourCatVO
	 * @throws BusinessException
	 */
	public void updTourCat(TourCatVO tourCatVO) throws BusinessException;

	/**
	 * 
	 * @param tourCatVO
	 * @throws BusinessException
	 */
	public void delTourCat(TourCatVO tourCatVO) throws BusinessException;

}
