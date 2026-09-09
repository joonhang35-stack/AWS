package com.bcs.zsg.crm.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.crm.service.CustomerPosInfoService;
import com.bcs.zsg.crm.vo.CustomerPosInfoVO;
import com.bcs.zsg.sales.vo.CustomerVO;


public class CustomerPosInfoBOImpl implements CustomerPosInfoBO {
	@Autowired
	private CustomerPosInfoService customerPosInfoService;
	
	@Override
	public CustomerPosInfoVO getCustomerPosInfo(CustomerVO customerVO) throws BusinessException{
		return customerPosInfoService.getCustomerPosInfo(customerVO);
	}
	@Override
	public List<CustomerPosInfoVO> getCustomerPosInfoList() throws BusinessException {
		return customerPosInfoService.getCustomerPosInfoList();
	}
	@Override
	public void updateCustomerPosInfoStatus(List<CustomerPosInfoVO> customerPosInfoList) throws BusinessException{
		customerPosInfoService.updateCustomerPosInfoStatus(customerPosInfoList);
	}
	@Override
	public void insertCustomerPosInfo(CustomerVO customerVO) throws BusinessException{
		customerPosInfoService.insertCustomerPosInfo(customerVO);
	}
}
