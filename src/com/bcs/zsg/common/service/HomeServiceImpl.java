package com.bcs.zsg.common.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.common.dao.HomeDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class HomeServiceImpl implements HomeService {

	@Autowired
	private HomeDAO homeDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.service.HomeService#getEmployeeInfo(java.lang.String, java.lang.Long)
	 */
	@Override
	public EmployeeVO getEmployeeInfo(String uuid, Long idCompany) throws BusinessException {
		return homeDAO.getEmployeeInfo(uuid, idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.service.HomeService#getCompanyInfo(java.lang.Long)
	 */
	@Override
	public CompanyVO getCompanyInfo(Long companyId) throws BusinessException {
		return homeDAO.getCompanyInfo(companyId);
	}

}
