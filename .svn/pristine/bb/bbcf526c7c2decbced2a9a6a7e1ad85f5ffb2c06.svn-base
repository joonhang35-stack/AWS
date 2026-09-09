package com.bcs.zsg.crm.service;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.crm.sec.vo.CRMMembershipVO;
import com.bcs.zsg.crm.sec.vo.KeycloakUserVO;
import com.bcs.zsg.sales.vo.CustomerVO;
import com.bcs.zsg.sales.vo.PersonContactVO;

public interface CRMMembershipService {
	public int getCRMMembershipListSize(Map<String, Object> params) throws BusinessException;
	public List<CRMMembershipVO> getCRMMembershipList(Map<String, Object> params) throws BusinessException;
	public void delCRMMembership(CRMMembershipVO vo);
	public void unlinkCRMMembership(CRMMembershipVO vo);
	public CRMMembershipVO getCRMMembership(Long customerId) throws BusinessException;
	public void insertCRMMembership(CustomerVO custVO, PersonContactVO contactVO, String keycloakUserId, KeycloakUserVO userVO) throws BusinessException;
}
