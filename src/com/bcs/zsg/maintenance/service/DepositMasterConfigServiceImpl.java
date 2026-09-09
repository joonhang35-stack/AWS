package com.bcs.zsg.maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.dao.DepositMasterConfigDAO;
import com.bcs.zsg.maintenance.vo.DepositMasterConfigVO;

public class DepositMasterConfigServiceImpl implements DepositMasterConfigService {

	@Autowired
	private DepositMasterConfigDAO depositMasterConfigDAO;
	
	@Override
	public List<DepositMasterConfigVO> getDepositMasterConfigVOList() throws BusinessException {
		return depositMasterConfigDAO.getDepositMasterConfigVOList();
	}

	@Override
	public void saveDepositMasterConfig(List<DepositMasterConfigVO> depositMasterConfigList) throws BusinessException {
		for (DepositMasterConfigVO vo : depositMasterConfigList) {
			if (vo.getId() == null) {
				vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
				depositMasterConfigDAO.insert(vo);
			} else depositMasterConfigDAO.update(vo);
		}
	}

}
