package com.bcs.zsg.maintenance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.ListingTableViewColumnsVO;

public class ListingTableViewColumnsDAOImpl extends BaseHibernateDAO implements ListingTableViewColumnsDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ListingTableViewColumnsVO> getListingTableViewColumnsList(Long idListingTable) throws BusinessException {
		Criteria criteria = createCriteria(ListingTableViewColumnsVO.class);
		criteria.add(Restrictions.eq("idListingTable", idListingTable));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return criteria.list();
	}
	
	@Override
	public void deleteByIdListingTable(Long idListingTable) throws BusinessException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ListingTableViewColumnsVO");
		sql.append(" SET ");
		sql.append("status_cd = :statusCode ");
		sql.append(" where id_listing_table = :idListingTable");
		
		Query query = createQuery(sql.toString());
		query.setParameter("statusCode", BaseConstant.STATUS_DELETED);
		query.setParameter("idListingTable", idListingTable);
		
		query.executeUpdate();
	}
}
