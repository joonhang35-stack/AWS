package com.bcs.zsg.company.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.company.service.CompanyService;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class CompanyBOImpl implements CompanyBO {

	@Autowired
	private CompanyService companyService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.company.bo.CompanyBO#getCompanyList(java.lang.String)
	 */
	@Override
	public List<CompanyVO> getCompanyList(String secUser) throws BusinessException {
		return companyService.getCompanyList(secUser);
	}

}
