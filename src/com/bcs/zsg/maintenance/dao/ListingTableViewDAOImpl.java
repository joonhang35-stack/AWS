package com.bcs.zsg.maintenance.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.ListingTableViewVO;

public class ListingTableViewDAOImpl extends BaseHibernateDAO implements ListingTableViewDAO {
	
	@Override
	public ListingTableViewVO getListingTableViewByListingType(String listingType, Long idUser) throws BusinessException {
		Criteria criteria = createCriteria(ListingTableViewVO.class);
		criteria.add(Restrictions.eq("listingType", listingType));
		criteria.add(Restrictions.eq("idUser", idUser));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return (ListingTableViewVO) criteria.uniqueResult();
	}
}
