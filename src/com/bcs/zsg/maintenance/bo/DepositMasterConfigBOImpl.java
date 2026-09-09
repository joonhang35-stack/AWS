package com.bcs.zsg.maintenance.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.DepositMasterConfigService;
import com.bcs.zsg.maintenance.vo.DepositMasterConfigVO;

public class DepositMasterConfigBOImpl implements DepositMasterConfigBO {

	@Autowired
	private DepositMasterConfigService depositMasterConfigService;
	
	@Override
	public List<DepositMasterConfigVO> getDepositMasterConfigVOList() throws BusinessException {
		return depositMasterConfigService.getDepositMasterConfigVOList();
	}

	@Override
	public void saveDepositMasterConfig(List<DepositMasterConfigVO> depositMasterConfigList)
			throws BusinessException {
		depositMasterConfigService.saveDepositMasterConfig(depositMasterConfigList);
	}

}
