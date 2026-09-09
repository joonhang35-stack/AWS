package com.bcs.zsg.maintenance.bo;

import java.util.List;

import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CustomerProfileUpdateVO;
import com.bcs.zsg.maintenance.vo.SupplierProfileUpdateVO;

public interface CustomerProfileUpdateBO {
	
	public void updateCustomerProfiles(List<CustomerProfileUpdateVO> customerProfileUpdateList, UserVO userVO) throws BusinessException;
	
	public void updateSupplierProfiles(List<SupplierProfileUpdateVO> supplierProfileUpdateList, UserVO userVO) throws BusinessException;

}
