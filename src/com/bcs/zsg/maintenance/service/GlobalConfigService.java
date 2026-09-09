package com.bcs.zsg.maintenance.service;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.GlobalConfigVO;

public interface GlobalConfigService {

	public List<GlobalConfigVO> getGlobalConfigList() throws BusinessException;

	public void updateGlobalConfig(List<GlobalConfigVO> globalConfigList) throws BusinessException;

	public List<GlobalConfigVO> getGlobalConfigDescList() throws BusinessException;

	
}
