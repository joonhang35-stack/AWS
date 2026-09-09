package com.bcs.zsg.maintenance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.ForeignExRateVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public class ForeignExRateDAOImpl extends BaseHibernateDAO implements ForeignExRateDAO 
{

	@SuppressWarnings("unchecked")
	@Override
	public List<ForeignExRateVO> getForeignExRateList()
			throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria=createCriteria(ForeignExRateVO.class);
		criteria.addOrder(Order.asc("code"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CountryVO> getCountryList() throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria=createCriteria(CountryVO.class);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	

}