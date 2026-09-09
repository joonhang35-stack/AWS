package com.bcs.zsg.maintenance.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.VisibleListingConfigVO;

public interface VisibleListingConfigDAO extends BaseDAO {
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<VisibleListingConfigVO> getVisibleListingConfigList(Map<String, Object> params) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<VisibleListingConfigVO> getVisibleListingConfigListGroupByCatCdListingType(Map<String, Object> params) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public boolean isCatCodeAndListingTypeDuplicate(VisibleListingConfigVO vo) throws BusinessException;

	public void deleteByListID(List<Long> listID) throws BusinessException;
}
