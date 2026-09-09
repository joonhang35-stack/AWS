package com.bcs.zsg.maintenance.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.MalaysiaStateService;
import com.bcs.zsg.maintenance.vo.MalaysiaStateVO;

public class MalaysiaStateBOImpl implements MalaysiaStateBO{
	
	@Autowired
	private MalaysiaStateService malaysiaStateService;

	@Override
	public MalaysiaStateVO getStateById(Long id) throws BusinessException {
		return malaysiaStateService.getStateById(id);
	}

	@Override
	public List<MalaysiaStateVO> getStateList() throws BusinessException {
		return malaysiaStateService.getStateList();
	}

	@Override
	public MalaysiaStateVO getStateByEInvoiceStateCode(String eInvoiceStateCode) throws BusinessException {
		return malaysiaStateService.getStateByEInvoiceStateCode(eInvoiceStateCode);
	}
}
