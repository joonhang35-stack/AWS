package com.bcs.zsg.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.company.dao.CompanyDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDAO companyDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.company.service.CompanyService#getCompanyList(java.lang.String)
	 */
	@Override
	public List<CompanyVO> getCompanyList(String secUser) throws BusinessException {
		return companyDAO.getCompanyList(secUser);
	}

}
