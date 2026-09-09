package com.bcs.zsg.maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.dao.GlobalConfigDAO;
import com.bcs.zsg.maintenance.vo.GlobalConfigVO;

public class GlobalConfigServiceImpl implements GlobalConfigService {
	@Autowired
	private GlobalConfigDAO globalConfigDAO;

	@Override
	public List<GlobalConfigVO> getGlobalConfigList() throws BusinessException {
		// TODO Auto-generated method stub
		return globalConfigDAO.getGlobalConfigList();
	}

	@Override
	public void updateGlobalConfig(List<GlobalConfigVO> globalConfigList)
			throws BusinessException {
		// TODO Auto-generated method stub
		for(GlobalConfigVO vo : globalConfigList){
			globalConfigDAO.update(vo);
		}
		
	}

	@Override
	public List<GlobalConfigVO> getGlobalConfigDescList()
			throws BusinessException {
		// TODO Auto-generated method stub
		return globalConfigDAO.getGlobalConfigDescList();
	}

	
}



	
	

	

