package com.bcs.zsg.crm.dao;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.crm.vo.CustomerPosInfoVO;
import com.bcs.zsg.sales.vo.CustomerVO;

public interface CustomerPosInfoDAO {
	public CustomerPosInfoVO getCustomerPosInfo(CustomerVO customerVO) throws BusinessException;
	public List<CustomerPosInfoVO> getCustomerPosInfoList() throws BusinessException;
	public void updateCustomerPosInfoStatus(List<CustomerPosInfoVO> customerPosInfoList) throws BusinessException;
	public void insertCustomerPosInfo(CustomerPosInfoVO vo) throws BusinessException;
}
