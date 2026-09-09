package com.bcs.zsg.crm.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CRMCommonConstant;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseContext;
import com.bcs.zsg.crm.dao.CustomerPosInfoDAO;
import com.bcs.zsg.crm.vo.CustomerPosInfoVO;
import com.bcs.zsg.sales.vo.CustomerVO;

public class CustomerPosInfoServiceImpl implements CustomerPosInfoService {
	@Autowired
	private CustomerPosInfoDAO customerPosInfoDAO;
	@Override
	public CustomerPosInfoVO getCustomerPosInfo(CustomerVO customerVO) throws BusinessException{
		return customerPosInfoDAO.getCustomerPosInfo(customerVO);
	}
	@Override
	public List<CustomerPosInfoVO> getCustomerPosInfoList() throws BusinessException {
		return customerPosInfoDAO.getCustomerPosInfoList();
	}
	@Override
	public void updateCustomerPosInfoStatus(List<CustomerPosInfoVO> customerPosInfoList) throws BusinessException{
		customerPosInfoDAO.updateCustomerPosInfoStatus(customerPosInfoList);
	}
	@Override
	public void insertCustomerPosInfo(CustomerVO customerVO) throws BusinessException {
		CustomerPosInfoVO vo = new CustomerPosInfoVO();
	    vo.setIdCustomer(customerVO.getId());
	    vo.setIdCompany(customerVO.getCompanyId());
	    vo.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
	    vo.setCreatedDate(new Date());
	    vo.setCreatedBy(StringUtils.isBlank(BaseContext.getUserFullName()) ? BaseContext.getLoginId() : BaseContext.getUserFullName());
	    vo.setUpdatedDate(new Date());
	    vo.setUpdatedBy(StringUtils.isBlank(BaseContext.getUserFullName()) ? BaseContext.getLoginId() : BaseContext.getUserFullName());
	    vo.setPostingStatus(CRMCommonConstant.CRM_INV_POSTING_PENDING); // "P"
	    customerPosInfoDAO.insertCustomerPosInfo(vo);
	}
}
