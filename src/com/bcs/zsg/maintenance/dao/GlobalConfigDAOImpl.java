package com.bcs.zsg.maintenance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.GlobalConfigVO;

public class GlobalConfigDAOImpl extends BaseHibernateDAO implements GlobalConfigDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<GlobalConfigVO> getGlobalConfigList() throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(GlobalConfigVO.class);
		criteria.addOrder(Order.asc("seq"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GlobalConfigVO> getGlobalConfigDescList()
			throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(GlobalConfigVO.class);
		criteria.setProjection(Projections.projectionList().add(Projections.property("description"), "description"));
		return criteria.list();
	}

	
}