package com.bcs.zsg.maintenance.service;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.DepositMasterConfigVO;

public interface DepositMasterConfigService {
	
	public List<DepositMasterConfigVO> getDepositMasterConfigVOList() throws BusinessException;
	
	public void saveDepositMasterConfig(List<DepositMasterConfigVO> depositMasterConfigList) throws BusinessException;
}
