package com.bcs.zsg.common.dao;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public interface HomeDAO extends BaseDAO {

	/**
	 * 
	 * @param uuid
	 * @param idCompany 
	 * @return
	 * @throws BusinessException
	 */
	public EmployeeVO getEmployeeInfo(String uuid, Long idCompany) throws BusinessException;

	/**
	 * 
	 * @param companyId
	 * @return
	 * @throws BusinessException
	 */
	public CompanyVO getCompanyInfo(Long companyId) throws BusinessException;

}
