package com.bcs.zsg.product.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.CruiseCabinVO;

public class CruiseCabinDAOImpl extends BaseHibernateDAO implements CruiseCabinDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<CruiseCabinVO> getCabinList(Map<String, Object> params) throws BusinessException {
		Criteria criteria = createCriteria(CruiseCabinVO.class);
		for (Entry<String, Object> entry : params.entrySet()) {
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		criteria.addOrder(Order.asc("seq"));
		return criteria.list();
	}

}
