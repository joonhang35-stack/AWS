package com.bcs.zsg.crm.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.crm.sec.vo.CRMMembershipVO;

public interface CRMMembershipDAO extends BaseDAO {
	public int getCRMMembershipListSize(Map<String, Object> params) throws BusinessException;
	public List<CRMMembershipVO> getCRMMembershipList(Map<String, Object> params) throws BusinessException;
	public void updateCRMMembershipStatus(Long idCRMMembership, String statusCd);
	public void unlinkCRMMembership(Long idCRMMembership, Long idCustomer);
	public CRMMembershipVO getCRMMembership(Long customerId) throws BusinessException;
}
