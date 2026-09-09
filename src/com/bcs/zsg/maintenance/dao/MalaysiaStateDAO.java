package com.bcs.zsg.maintenance.dao;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.MalaysiaStateVO;

public interface MalaysiaStateDAO extends BaseDAO {

	public MalaysiaStateVO getStateById(Long id) throws BusinessException;
	
	public List<MalaysiaStateVO> getStateList() throws BusinessException;

	public MalaysiaStateVO getStateByEInvoiceStateCode(String eInvoiceStateCode) throws BusinessException;

	public MalaysiaStateVO getStateByStateCode(String stateCode) throws BusinessException;
	
}
