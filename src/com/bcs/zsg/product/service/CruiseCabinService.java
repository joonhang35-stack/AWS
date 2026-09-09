package com.bcs.zsg.product.service;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.CruiseCabinVO;

public interface CruiseCabinService {

	public List<CruiseCabinVO> getCabinList(Map<String, Object> params) throws BusinessException;

	public void addCabin(CruiseCabinVO cruiseCabinVO) throws BusinessException;

	public void updCabin(CruiseCabinVO cruiseCabinVO) throws BusinessException;

	public void delCabin(CruiseCabinVO cruiseCabinVO) throws BusinessException;

}
