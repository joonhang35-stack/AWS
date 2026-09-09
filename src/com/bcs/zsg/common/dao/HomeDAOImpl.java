package com.bcs.zsg.common.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class HomeDAOImpl extends BaseHibernateDAO implements HomeDAO {

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.dao.HomeDAO#getEmployeeInfo(java.lang.String)
	 */
	@Override
	public EmployeeVO getEmployeeInfo(String uuid, Long idCompany) throws BusinessException {
		Criteria criteria = createCriteria(EmployeeVO.class);
		criteria.add(Restrictions.eq("secUser", uuid));
		if (idCompany == null) criteria.add(Restrictions.eq("isDefaultComp", true));
		else criteria.add(Restrictions.eq("companyId", idCompany));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.setMaxResults(1);
		return (EmployeeVO) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.dao.HomeDAO#getCompanyInfo(java.lang.Long)
	 */
	@Override
	public CompanyVO getCompanyInfo(Long companyId) throws BusinessException {
		Criteria criteria = createCriteria(CompanyVO.class);
		criteria.add(Restrictions.eq("id", companyId));
		return (CompanyVO) criteria.uniqueResult();
	}

}
