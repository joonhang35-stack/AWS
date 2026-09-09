package com.bcs.zsg.maintenance.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.VisibleListingConfigVO;

public class VisibleListingConfigDAOImpl extends BaseHibernateDAO implements VisibleListingConfigDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<VisibleListingConfigVO> getVisibleListingConfigList(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT vlc.id, vlc.cat_cd, vlc.listing_type, vlc.id_role, sr.role_name");
		sb.append(" FROM visible_listing_config vlc ");
		sb.append(" LEFT JOIN sec_role sr on sr.uuid = vlc.id_role ");
		sb.append(" WHERE status_cd='" + BaseConstant.STATUS_ACTIVE + "'");
		Query query = createSQLQuery(sb.toString());

		List<Object> results = query.list();
		List<VisibleListingConfigVO> visibleListingConfigVOList = new ArrayList<VisibleListingConfigVO>();

		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			VisibleListingConfigVO vo = new VisibleListingConfigVO();
			vo.setId(((BigInteger) row[0]).longValue()); 
			vo.setCatCd((String) row[1]);   
			vo.setListingType((String) row[2]);   
			vo.setIdRole((String) row[3]);
			vo.setRoleName((String) row[4]);
			
			visibleListingConfigVOList.add(vo);
		}
		return visibleListingConfigVOList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VisibleListingConfigVO> getVisibleListingConfigListGroupByCatCdListingType(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT GROUP_CONCAT(vlc.id) AS id, vlc.cat_cd, li.description, vlc.listing_type, vlc.type");
		sb.append(", GROUP_CONCAT(if(vlc.type='role', vlc.id_role, null)) AS id_role, GROUP_CONCAT(sr.role_name SEPARATOR ', ') AS role_name");
		sb.append(", GROUP_CONCAT(if(vlc.type='department', vlc.dept_code, null)) AS department, GROUP_CONCAT(li2.description SEPARATOR ', ') AS dept_desc");
		sb.append(", vlc.status_cd, vlc.dt_created, vlc.created_by, vlc.dt_upd, vlc.upd_by");
		sb.append(" FROM visible_listing_config vlc ");
		sb.append(" LEFT JOIN sec_role sr on sr.uuid = vlc.id_role ");
		sb.append(" LEFT JOIN lookup_item li on li.code = vlc.cat_cd and li.lookup_cat_cd = vlc.listing_type ");
		sb.append(" LEFT JOIN lookup_item li2 on li2.code = vlc.dept_code and li2.lookup_cat_cd = 'dept_type' ");
		sb.append(" WHERE vlc.status_cd='" + BaseConstant.STATUS_ACTIVE + "' "); 
		if (params != null && !params.isEmpty()) {
			if (params.get("listingType") != null)
			sb.append(" AND vlc.listing_type='" + params.get("listingType") + "' "); 
		}
		sb.append("GROUP BY cat_cd, listing_type ");
		Query query = createSQLQuery(sb.toString());
		
		List<Object> results = query.list();
		List<VisibleListingConfigVO> visibleListingConfigVOList = new ArrayList<VisibleListingConfigVO>();

		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			VisibleListingConfigVO vo = new VisibleListingConfigVO();
			vo.setIdListStr((String) row[0]); 
			vo.setCatCd((String) row[1]);   
			vo.setCatDesc((String) row[2]);
			vo.setListingType((String) row[3]);
			vo.setType((String) row[4]);
			vo.setIdRoleListStr((String) row[5]);
			vo.setRoleName((String) row[6]);
			vo.setDepartmentListStr((String) row[7]);
			vo.setDepartmentName((String) row[8]);
			vo.setStatusCode((String) row[9]);
			vo.setCreatedDate((Date) row[10]);
			vo.setCreatedBy((String) row[11]);
			vo.setUpdatedDate((Date) row[12]);
			vo.setUpdatedBy((String) row[13]);
			
			visibleListingConfigVOList.add(vo);
		}
		return visibleListingConfigVOList;
	}
	
	@Override
	public boolean isCatCodeAndListingTypeDuplicate(VisibleListingConfigVO vo) throws BusinessException {
		Criteria criteria = createCriteria(VisibleListingConfigVO.class);
		criteria.add(Restrictions.eq("catCd", vo.getCatCd()));
		criteria.add(Restrictions.eq("listingType", vo.getListingType()));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		
		if (vo.getIdList() != null) {
			List<Long> tmpIdList = new ArrayList<>();
			for (String id : vo.getIdList()) {
				tmpIdList.add(Long.parseLong(id));
			}
			criteria.add(Restrictions.not(Restrictions.in("id", tmpIdList)));
		}
		criteria.setMaxResults(1);
		return (criteria.uniqueResult() == null) ? false : true;
	}
	
	@Override
	public void deleteByListID(List<Long> listID) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE VisibleListingConfigVO SET dt_upd = NOW(), status_cd = :statusCd ");
		sb.append("WHERE id IN (:idList) ");
		Query query = createQuery(sb.toString());
		query.setString("statusCd", BaseConstant.STATUS_DELETED);
		query.setParameterList("idList", listID);
		query.executeUpdate();
	}
	
	
}
