package com.bcs.zsg.maintenance.bo;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.VisibleListingConfigVO;

public interface VisibleListingConfigBO {
	
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
	public List<VisibleListingConfigVO> getVisibleListingConfigListGroupByCatCdListingtype(Map<String, Object> params) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void save(VisibleListingConfigVO vo) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void delete(VisibleListingConfigVO vo) throws BusinessException;
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public boolean isCatCodeAndListingTypeDuplicate(VisibleListingConfigVO vo) throws BusinessException;

	public void deleteByListID(List<Long> listID) throws BusinessException;
}
