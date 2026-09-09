package com.bcs.zsg.product.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.CruiseCabinService;
import com.bcs.zsg.product.vo.CruiseCabinVO;

public class CruiseCabinBOImpl implements CruiseCabinBO {

	@Autowired
	private CruiseCabinService cruiseCabinService;
	
	@Override
	public List<CruiseCabinVO> getCabinList(Map<String, Object> params) throws BusinessException {
		return cruiseCabinService.getCabinList(params);
	}

	@Override
	public void save(CruiseCabinVO cruiseCabinVO) throws BusinessException {
		if (cruiseCabinVO.getId() == null) {
			cruiseCabinService.addCabin(cruiseCabinVO);
		} else cruiseCabinService.updCabin(cruiseCabinVO);
	}

	@Override
	public void delete(CruiseCabinVO cruiseCabinVO) throws BusinessException {
		cruiseCabinService.delCabin(cruiseCabinVO);
	}

}
