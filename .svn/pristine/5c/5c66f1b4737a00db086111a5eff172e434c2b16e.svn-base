package com.bcs.zsg.maintenance.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.mail.service.EmailingService;
import com.bcs.zsg.maintenance.dao.CustomerProfileUpdateDAO;
import com.bcs.zsg.maintenance.helper.ConstantAppSetting;
import com.bcs.zsg.maintenance.vo.AppSettingVO;
import com.bcs.zsg.maintenance.vo.CustomerProfileUpdateVO;
import com.bcs.zsg.maintenance.vo.SupplierProfileUpdateVO;

public class CustomerProfileUpdateServiceImpl implements CustomerProfileUpdateService{
	
	@Autowired
	private CustomerProfileUpdateDAO customerProfileUpdateDAO;
	
	@Autowired
	private AppSettingService appSettingService;
	
	@Autowired
	private EmailingService emailingService;

	@Override
	public void updateCustomerProfiles(List<CustomerProfileUpdateVO> customerProfileUpdateList, UserVO userVO)
			throws BusinessException {
		if (CollectionUtils.isNotEmpty(customerProfileUpdateList)) {
			for (CustomerProfileUpdateVO vo : customerProfileUpdateList) {
				if (vo.getId() != null) {
					customerProfileUpdateDAO.update(vo);
				} else {
					vo.setUpdateStatus(CommonConstant.EMAIL_PMNT_PENDING);
					vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
					
					AppSettingVO appSettingVO = appSettingService.getAppSettingByCode(ConstantAppSetting.MAINT_CUSTOMER_PROFILE_UPDATE_EXPIRY_DURATION);
					if (appSettingVO != null) {
					    Calendar cal = Calendar.getInstance();
					    cal.setTime(new Date());
					    cal.add(Calendar.MINUTE, Integer.parseInt(appSettingVO.getValue()));
					    vo.setDtExpiry(cal.getTime());
					}
					customerProfileUpdateDAO.insert(vo);
					
					// Send email
					emailingService.sendCustomerProfileUpdateEmail(vo, userVO);
				}
			}
		}
	}

	@Override
	public void updateSupplierProfiles(List<SupplierProfileUpdateVO> supplierProfileUpdateList, UserVO userVO)
			throws BusinessException {
		if (CollectionUtils.isNotEmpty(supplierProfileUpdateList)) {
			for (SupplierProfileUpdateVO vo : supplierProfileUpdateList) {
				if (vo.getId() != null) {
					customerProfileUpdateDAO.update(vo);
				} else {
					vo.setUpdateStatus(CommonConstant.EMAIL_PMNT_PENDING);
					vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
					
					AppSettingVO appSettingVO = appSettingService.getAppSettingByCode(ConstantAppSetting.MAINT_SUPPLIER_PROFILE_UPDATE_EXPIRY_DURATION);
					if (appSettingVO != null) {
					    Calendar cal = Calendar.getInstance();
					    cal.setTime(new Date());
					    cal.add(Calendar.MINUTE, Integer.parseInt(appSettingVO.getValue()));
					    vo.setDtExpiry(cal.getTime());
					}
					customerProfileUpdateDAO.insert(vo);
					
					// Send email
					emailingService.sendSupplierProfileUpdateEmail(vo, userVO);
				}
			}
		}
	}
}
