package com.bcs.zsg.company.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class CompanyDAOImpl extends BaseHibernateDAO implements CompanyDAO {

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.company.dao.CompanyDAO#getCompanyList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyVO> getCompanyList(String secUser) throws BusinessException {
		Query query = createSQLQuery("SELECT id_company FROM employee WHERE u_sec_user = :secUser AND status_cd = :statusCode");
		query.setString("secUser", secUser);
		query.setString("statusCode", BaseConstant.STATUS_ACTIVE);
		List<Object> results = query.list();
		List<Long> companyIdList = new ArrayList<Long>();
		
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			BigInteger id = (BigInteger) it.next();
			companyIdList.add(id.longValue());
		}
		
		if (CollectionUtils.isNotEmpty(companyIdList)) {
			Criteria criteria = createCriteria(CompanyVO.class);
			criteria.add(Restrictions.in("id", companyIdList));
			criteria.addOrder(Order.asc("name"));
			return criteria.list();
		}
		return null;
	}

}
