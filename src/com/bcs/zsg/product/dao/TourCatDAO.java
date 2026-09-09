package com.bcs.zsg.product.dao;

import java.util.List;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.TourCatViewVO;

public interface TourCatDAO extends BaseDAO {

	/**
	 * 
	 * @param searchParamVO 
	 * @return
	 * @throws BusinessException
	 */
	public List<TourCatViewVO> getTourCatViewList(SearchParamVO searchParamVO) throws BusinessException;

	/**
	 * 
	 * @param idTourCat
	 * @return
	 * @throws BusinessException
	 */
	public boolean isTourCatUsed(Long idTourCat) throws BusinessException;

}
