package com.bcs.zsg.common.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.maintenance.vo.GlobalConfigVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;

public class LookupItemDAOImpl extends BaseHibernateDAO implements LookupItemDAO {

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.dao.LookupItemDAO#getLookupItemList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LookupItemVO> getLookupItemList(String category) {
		Criteria criteria = createCriteria(LookupItemVO.class);
		criteria.add(Restrictions.eq("lookupCatCd", category));
		criteria.addOrder(Order.asc("seqNo"));
		criteria.addOrder(Order.asc("description"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.dao.LookupItemDAO#getGlobalConfigList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GlobalConfigVO> getGlobalConfigList() {
		Criteria criteria = createCriteria(GlobalConfigVO.class);
		criteria.addOrder(Order.asc("seq"));
		return criteria.list();
	}

}
