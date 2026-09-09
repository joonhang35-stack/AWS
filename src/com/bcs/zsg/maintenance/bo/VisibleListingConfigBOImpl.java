package com.bcs.zsg.maintenance.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.VisibleListingConfigService;
import com.bcs.zsg.maintenance.vo.VisibleListingConfigVO;

public class VisibleListingConfigBOImpl implements VisibleListingConfigBO {
	
	@Autowired
	private VisibleListingConfigService visibleListingConfigService;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.bo.VisibleListingConfigBO#getVisibleListingConfigList()
	 */
	@Override
	public List<VisibleListingConfigVO> getVisibleListingConfigList(Map<String, Object> params) throws BusinessException {
		return visibleListingConfigService.getVisibleListingConfigList(params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.bo.VisibleListingConfigBO#getVisibleListingConfigListGroupByCatCdListingtype()
	 */
	@Override
	public List<VisibleListingConfigVO> getVisibleListingConfigListGroupByCatCdListingtype(Map<String, Object> params) throws BusinessException {
		return visibleListingConfigService.getVisibleListingConfigListGroupByCatCdListingtype(params);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.bo.VisibleListingConfigBO#save()
	 */
	@Override
	public void save(VisibleListingConfigVO vo) throws BusinessException {
		visibleListingConfigService.save(vo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.bo.VisibleListingConfigBO#delete()
	 */
	@Override
	public void delete(VisibleListingConfigVO vo) throws BusinessException {
		if (vo != null && vo.getId() != null)
			visibleListingConfigService.delete(vo);
	}
	
	@Override
	public void deleteByListID(List<Long> listID) throws BusinessException {
			visibleListingConfigService.deleteByListID(listID);
	}

	@Override
	public boolean isCatCodeAndListingTypeDuplicate(VisibleListingConfigVO vo) throws BusinessException {
		return visibleListingConfigService.isCatCodeAndListingTypeDuplicate(vo);
	}
}
