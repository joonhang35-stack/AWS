package com.bcs.zsg.maintenance.bo;

import static com.bcs.zsg.common.helper.LookupItemConstant.CACHE_KEY_PREFIX;
import static com.bcs.zsg.core.helper.BaseConstant.PAD_SLASH;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.cache.service.CacheService;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.helper.MaintConstant;
import com.bcs.zsg.maintenance.service.GlobalConfigService;
import com.bcs.zsg.maintenance.vo.GlobalConfigVO;

public class GlobalConfigBOImpl implements GlobalConfigBO {
	@Autowired
	private GlobalConfigService globalConfigService;
	
	@Autowired
	protected CacheService cacheService;

	@Override
	public List<GlobalConfigVO> getGlobalConfigList() throws BusinessException {
		// TODO Auto-generated method stub
		return globalConfigService.getGlobalConfigList();
	}

	@Override
	public void updateGlobalConfig(List<GlobalConfigVO> globalConfigList)
			throws BusinessException {
		globalConfigService.updateGlobalConfig(globalConfigList);
		// clear cache lookup item
		cacheService.remove(CACHE_KEY_PREFIX + PAD_SLASH + MaintConstant.GLOBAL_CD_GLOBAL);
	}

	@Override
	public List<GlobalConfigVO> getGlobalConfigDescList()
			throws BusinessException {
		// TODO Auto-generated method stub
		return globalConfigService.getGlobalConfigDescList();
	}

	
}
