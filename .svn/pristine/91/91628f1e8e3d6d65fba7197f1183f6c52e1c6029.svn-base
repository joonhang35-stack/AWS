package com.bcs.zsg.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.TourCatService;
import com.bcs.zsg.product.vo.TourCatVO;
import com.bcs.zsg.product.vo.TourCatViewVO;

public class TourCatBOImpl implements TourCatBO {

	@Autowired
	private TourCatService tourCatService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourCatBO#getTourCatViewList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<TourCatViewVO> getTourCatViewList(SearchParamVO searchParamVO) throws BusinessException {
		return tourCatService.getTourCatViewList(searchParamVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourCatBO#addTourCat(com.bcs.zsg.product.vo.TourCatVO)
	 */
	@Override
	public void addTourCat(TourCatVO tourCatVO) throws BusinessException {
		tourCatService.addTourCat(tourCatVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourCatBO#updTourCat(com.bcs.zsg.product.vo.TourCatVO)
	 */
	@Override
	public void updTourCat(TourCatVO tourCatVO) throws BusinessException {
		tourCatService.updTourCat(tourCatVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourCatBO#delTourCat(com.bcs.zsg.product.vo.TourCatVO)
	 */
	@Override
	public void delTourCat(TourCatVO tourCatVO) throws BusinessException {
		tourCatService.delTourCat(tourCatVO);
	}

}
