package com.bcs.zsg.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.dao.TourCatDAO;
import com.bcs.zsg.product.vo.TourCatVO;
import com.bcs.zsg.product.vo.TourCatViewVO;

public class TourCatServiceImpl implements TourCatService {

	@Autowired
	private TourCatDAO tourCatDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.TourCatService#getTourCatViewList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<TourCatViewVO> getTourCatViewList(SearchParamVO searchParamVO) throws BusinessException {
		return tourCatDAO.getTourCatViewList(searchParamVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.TourCatService#addTourCat(com.bcs.zsg.product.vo.TourCatVO)
	 */
	@Override
	public void addTourCat(TourCatVO tourCatVO) throws BusinessException {
		tourCatDAO.insert(tourCatVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.TourCatService#updTourCat(com.bcs.zsg.product.vo.TourCatVO)
	 */
	@Override
	public void updTourCat(TourCatVO tourCatVO) throws BusinessException {
		tourCatDAO.update(tourCatVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.TourCatService#delTourCat(com.bcs.zsg.product.vo.TourCatVO)
	 */
	@Override
	public void delTourCat(TourCatVO tourCatVO) throws BusinessException {
		if (tourCatDAO.isTourCatUsed(tourCatVO.getId())) throw new BusinessException(CommonErrConstant.ERR_TOUR_CAT_USED);
		tourCatVO.setStatusCode(CommonConstant.STATUS_CD_INACTIVE);
		tourCatDAO.update(tourCatVO);
		//tourCatDAO.delete(tourCatVO);
	}

}
