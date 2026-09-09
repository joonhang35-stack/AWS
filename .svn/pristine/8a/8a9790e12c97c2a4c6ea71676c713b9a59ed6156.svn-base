package com.bcs.zsg.maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.dao.MalaysiaStateDAO;
import com.bcs.zsg.maintenance.vo.MalaysiaStateVO;

public class MalaysiaStateServiceImpl implements MalaysiaStateService {

	@Autowired
	private MalaysiaStateDAO malaysiaStateDAO;

	@Override
	public MalaysiaStateVO getStateById(Long id) throws BusinessException {
		return malaysiaStateDAO.getStateById(id);
	}

	@Override
	public List<MalaysiaStateVO> getStateList() throws BusinessException {
		return malaysiaStateDAO.getStateList();
	}
	
	@Override
	public MalaysiaStateVO getStateByEInvoiceStateCode(String eInvoiceStateCode) throws BusinessException {
		return malaysiaStateDAO.getStateByEInvoiceStateCode(eInvoiceStateCode);
	}
}
