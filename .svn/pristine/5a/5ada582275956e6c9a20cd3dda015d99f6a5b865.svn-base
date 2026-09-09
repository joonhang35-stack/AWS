package com.bcs.zsg.product.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.RoomingListLandOperatorContVO;
import com.bcs.zsg.product.vo.RoomingListLandOperatorVO;

public class RoomingListLandOperatorDAOImpl extends BaseHibernateDAO implements RoomingListLandOperatorDAO{
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomingListLandOperatorVO> getSupplierList(Long idTourDep)
			throws BusinessException {
		Criteria criteria = getSession().createCriteria(RoomingListLandOperatorVO.class);
		criteria.add(Restrictions.eq("idTourDep", idTourDep));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomingListLandOperatorContVO> getContactList(Long idLandOperator)
			throws BusinessException {
		Criteria criteria = getSession().createCriteria(RoomingListLandOperatorContVO.class);
		criteria.add(Restrictions.eq("idLandOperator", idLandOperator));
		return criteria.list();
	}
}
