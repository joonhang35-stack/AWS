package com.bcs.zsg.company.bo;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public interface CompanyBO {

	/**
	 * 
	 * @param secUser
	 * @return
	 * @throws BusinessException
	 */
	public List<CompanyVO> getCompanyList(String secUser) throws BusinessException;

}
