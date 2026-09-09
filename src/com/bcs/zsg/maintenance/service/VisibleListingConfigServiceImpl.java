package com.bcs.zsg.maintenance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.dao.VisibleListingConfigDAO;
import com.bcs.zsg.maintenance.vo.VisibleListingConfigVO;

public class VisibleListingConfigServiceImpl implements VisibleListingConfigService{
	
	@Autowired
	private VisibleListingConfigDAO visibleListingConfigDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.service.getVisibleListingConfigList#getVisibleListingConfigList()
	 */
	@Override
	public List<VisibleListingConfigVO> getVisibleListingConfigList(Map<String, Object> params) throws BusinessException {
		return visibleListingConfigDAO.getVisibleListingConfigList(params);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.service.getVisibleListingConfigList#getVisibleListingConfigListGroupByCatCdListingtype()
	 */
	@Override
	public List<VisibleListingConfigVO> getVisibleListingConfigListGroupByCatCdListingtype(Map<String, Object> params) throws BusinessException {
		return visibleListingConfigDAO.getVisibleListingConfigListGroupByCatCdListingType(params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.service.save#save(VisibleListingConfigVO)
	 */
	@Override
	public void save(VisibleListingConfigVO vo) throws BusinessException {
		vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
		visibleListingConfigDAO.insert(vo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.service.delete#delete(VisibleListingConfigVO)
	 */
	@Override
	public void delete(VisibleListingConfigVO vo) throws BusinessException {
		vo.setStatusCode(BaseConstant.STATUS_DELETED);
		visibleListingConfigDAO.update(vo);
	}
	
	@Override
	public void deleteByListID(List<Long> listID) throws BusinessException {
		visibleListingConfigDAO.deleteByListID(listID);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.service.isCatCodeAndListingTypeDuplicate#isCatCodeAndListingTypeDuplicate(VisibleListingConfigVO)
	 */
	@Override
	public boolean isCatCodeAndListingTypeDuplicate(VisibleListingConfigVO vo) throws BusinessException {
		return visibleListingConfigDAO.isCatCodeAndListingTypeDuplicate(vo);
	}
}
