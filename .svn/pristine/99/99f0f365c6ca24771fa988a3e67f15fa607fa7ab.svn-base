package com.bcs.zsg.common.bo;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.common.service.HomeService;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class HomeBOImpl implements HomeBO {

	@Autowired
	private HomeService homeService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.bo.HomeBO#getEmployeeInfo(java.lang.String, java.lang.Long)
	 */
	@Override
	public EmployeeVO getEmployeeInfo(String uuid, Long idCompany) throws BusinessException {
		return homeService.getEmployeeInfo(uuid, idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.bo.HomeBO#getCompanyInfo(java.lang.Long)
	 */
	@Override
	public CompanyVO getCompanyInfo(Long companyId) throws BusinessException {
		return homeService.getCompanyInfo(companyId);
	}

}
