package com.bcs.zsg.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.service.AccountCodeService;
import com.bcs.zsg.maintenance.vo.AccountCodeConfigVO;
import com.bcs.zsg.product.dao.CruiseCabinDAO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.CruiseCabinVO;

public class CruiseCabinServiceImpl implements CruiseCabinService {

	@Autowired
	private CruiseCabinDAO cruiseCabinDAO;
	@Autowired
	private AccountCodeService accountCodeService;
	
	@Override
	public List<CruiseCabinVO> getCabinList(Map<String, Object> params) throws BusinessException {
		params.put("statusCode", BaseConstant.STATUS_ACTIVE);
		return cruiseCabinDAO.getCabinList(params);
	}

	@Override
	public void addCabin(CruiseCabinVO cruiseCabinVO) throws BusinessException {
		try {
			cruiseCabinVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
			cruiseCabinDAO.insert(cruiseCabinVO);
			
			AccountCodeConfigVO accountCodeConfigVO = new AccountCodeConfigVO();
			accountCodeConfigVO.setCode(cruiseCabinVO.getCode());
			accountCodeConfigVO.setDescription(cruiseCabinVO.getDescription());
			accountCodeConfigVO.setTypeCode(ProductConstant.TYPE_CRUISE);
			accountCodeConfigVO.setGrouping(0);
			accountCodeConfigVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
			cruiseCabinDAO.insert(accountCodeConfigVO);
			
		} catch (Exception e) {
			if (e.getCause().getMessage().contains(CommonErrConstant.EXCEPTION_MSG_CONTAINS_DUPLICATE_ENTRY)
					|| e instanceof DataIntegrityViolationException) {
				throw new BusinessException(CommonErrConstant.ERR_COMMON_DUPLICATE_ENTRY, null, new String[] {cruiseCabinVO.getCode()});
			} else throw e;
		}
	}

	@Override
	public void updCabin(CruiseCabinVO cruiseCabinVO) throws BusinessException {
		try {
			cruiseCabinDAO.update(cruiseCabinVO);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("code", cruiseCabinVO.getCode());
			AccountCodeConfigVO accountCodeConfigVO = accountCodeService.getAccountCodeConfig(params);
			
			if (accountCodeConfigVO == null) {
				accountCodeConfigVO = new AccountCodeConfigVO();
				accountCodeConfigVO.setCode(cruiseCabinVO.getCode());
				accountCodeConfigVO.setDescription(cruiseCabinVO.getDescription());
				accountCodeConfigVO.setTypeCode(ProductConstant.TYPE_CRUISE);
				accountCodeConfigVO.setGrouping(0);
				accountCodeConfigVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
				cruiseCabinDAO.insert(accountCodeConfigVO);
			}
		} catch (Exception e) {
			if (e.getCause().getMessage().contains(CommonErrConstant.EXCEPTION_MSG_CONTAINS_DUPLICATE_ENTRY)
					|| e instanceof DataIntegrityViolationException) {
				throw new BusinessException(CommonErrConstant.ERR_COMMON_DUPLICATE_ENTRY, null, new String[] {cruiseCabinVO.getCode()});
			} else throw e;
		}
	}

	@Override
	public void delCabin(CruiseCabinVO cruiseCabinVO) throws BusinessException {
		cruiseCabinVO.setStatusCode(BaseConstant.STATUS_DELETED);
		updCabin(cruiseCabinVO);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", cruiseCabinVO.getCode());
		params.put("typeCode", "CRUISE");
		AccountCodeConfigVO accountCodeConfigVO = accountCodeService.getAccountCodeConfig(params);
		if (accountCodeConfigVO != null) {
			accountCodeConfigVO.setStatusCode(BaseConstant.STATUS_DELETED);
			cruiseCabinDAO.update(accountCodeConfigVO);
		}
		
	}
}
