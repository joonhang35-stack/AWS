package com.bcs.zsg.maintenance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.LookupCategoryVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.LookupItemViewVO;

public class LookUpDAOImpl extends BaseHibernateDAO implements LookUpDAO 
{

	

	@SuppressWarnings("unchecked")
	@Override
	public List<LookupCategoryVO> getLookUpList() throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(LookupCategoryVO.class);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<LookupItemViewVO> getLookUpItemList() throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(LookupItemViewVO.class);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LookupItemViewVO> getSearchLookUpItemViewList(LookupCategoryVO lookupCategoryVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(LookupItemViewVO.class);
		criteria.add(Restrictions.eq("lookupCategoryVO",lookupCategoryVO));
		criteria.addOrder(Order.asc("seqNo"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LookupCategoryVO> getSearchLookUpCategoryList(LookupCategoryVO lookupCategoryVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(LookupCategoryVO.class);
		criteria.add(Restrictions.eq("id",lookupCategoryVO.getId()));
		return (criteria.list());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LookupCategoryVO> getLookUpList1()
			throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(LookupCategoryVO.class);
		criteria.addOrder(Order.asc("description"));
		return criteria.list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<LookupItemVO> getLookUpCodeList(String lookupCatCd)
			throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(LookupItemVO.class);
		criteria.add(Restrictions.eq("lookupCatCd",lookupCatCd));
		return criteria.list();
	}


	@Override
	public List<LookupItemVO> getLookItemList() throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(LookupItemVO.class);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	
	
	
	
}