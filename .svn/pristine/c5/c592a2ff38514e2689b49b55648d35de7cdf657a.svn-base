package com.bcs.zsg.maintenance.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.CustomerProfileUpdateService;
import com.bcs.zsg.maintenance.vo.CustomerProfileUpdateVO;
import com.bcs.zsg.maintenance.vo.SupplierProfileUpdateVO;

public class CustomerProfileUpdateBOImpl implements CustomerProfileUpdateBO {
	
	@Autowired
	private CustomerProfileUpdateService customerProfileUpdateService;

	@Override
	public void updateCustomerProfiles(List<CustomerProfileUpdateVO> customerProfileUpdateList, UserVO userVO)
			throws BusinessException {
		customerProfileUpdateService.updateCustomerProfiles(customerProfileUpdateList, userVO);
	}
	
	@Override
	public void updateSupplierProfiles(List<SupplierProfileUpdateVO> supplierProfileUpdateList, UserVO userVO)
			throws BusinessException {
		customerProfileUpdateService.updateSupplierProfiles(supplierProfileUpdateList, userVO);
	}
	
}
