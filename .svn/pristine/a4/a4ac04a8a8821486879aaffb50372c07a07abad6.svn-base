package com.bcs.zsg.crm.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.core.helper.BaseContext;
import com.bcs.zsg.crm.sec.vo.CRMMembershipVO;

public class CRMMembershipDAOImpl extends BaseHibernateDAO implements CRMMembershipDAO {

	/* (non-Javadoc)
	 * @see com.bcs.zsg.crm.dao.CRMMembershipDAO#getCRMMembership(java.lang.Long)
	 */
	@Override
	public CRMMembershipVO getCRMMembership(Long customerId) throws BusinessException {
		Criteria criteria = createCriteria(CRMMembershipVO.class);
		criteria.add(Restrictions.eq("idCustomer", customerId));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return (CRMMembershipVO) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int getCRMMembershipListSize(Map<String, Object> params) throws BusinessException {
		
		StringBuilder sbFilters = new StringBuilder();
		boolean ljCust = false, ljPerson = false;
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		if (!filters.isEmpty()) {
			sbFilters.append("and (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if (filters.size() > 1 && it.hasNext()) sbFilters.append(" and ");
			}
			sbFilters.append(")");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("select count(cm.id) ");
		sb.append("from crm_membership cm ");
		
		if (ljCust || ljPerson)
			sb.append("left join customer c on cm.id_customer = c.id ");
		if (ljPerson)
			sb.append("left join person ps on c.id_pc = ps.id ");
		
		sb.append("where cm.id_company = :idCompany ");
		if (params.get("statusCd") != null) sb.append("and cm.status_cd = :statusCd ");
		
		if (sbFilters.length() > 0) {
			sb.append(sbFilters);
		}
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("idCompany"));
		if (params.get("statusCd") != null)	query.setParameter("statusCd", params.get("statusCd"));
		return ((BigInteger) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CRMMembershipVO> getCRMMembershipList(Map<String, Object> params) throws BusinessException {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select cm.id, cm.id_company, cm.id_customer, cm.keycloak_id, cm.keycloak_username, cm.email, ");
		sb.append("cm.contact_country_code, cm.contact, cm.member_status_cd, cm.status_cd, cm.dt_created, cm.created_by, ");
		sb.append("cm.dt_upd, cm.upd_by ");
		sb.append("from crm_membership cm ");
		sb.append("left join customer c on cm.id_customer = c.id ");
		sb.append("left join person ps on c.id_pc = ps.id ");
		sb.append("where cm.id_company = :idCompany ");
		
		if (params.get("statusCd") != null) sb.append("and cm.status_cd = :statusCd ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		if (!filters.isEmpty()) {
			sb.append("and (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
			}
			sb.append(")");
		}
		
		sb.append(" order by ");
		String sortField = (String) params.get("sortField");
		
		if (sortField == null) 
			sb.append("cm.id desc");
		else {
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" asc");
			else sb.append(" desc");
		}
		
//		System.out.println("InvoiceDAOImpl.getInvoiceList()");
//		System.out.println(sb.toString());
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("idCompany"));
		if (params.get("statusCd") != null)	query.setParameter("statusCd", params.get("statusCd"));
		if (params.get("first") != null && (Integer) params.get("first") >= 0)	query.setFirstResult((Integer) params.get("first"));
		if (params.get("pageSize") != null && (Integer) params.get("pageSize") >= 0)	query.setMaxResults((Integer) params.get("pageSize"));
		List<Object> results = query.list();
		List<CRMMembershipVO> membershipList = new ArrayList<CRMMembershipVO>();
		
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			CRMMembershipVO vo = new CRMMembershipVO();
			vo.setId(((BigInteger) row[0]).longValue());
			if (row[1] != null)	vo.setIdCompany(((BigInteger) row[1]).longValue()); 
			if (row[2] != null)	vo.setIdCustomer(((BigInteger) row[2]).longValue()); 
			if (row[3] != null)	vo.setKeycloakId((String) row[3]); 
			if (row[4] != null)	vo.setKeycloakUsername((String) row[4]); 
			if (row[5] != null)	vo.setEmail((String) row[5]); 
			if (row[6] != null)	vo.setContactCountryCode((String) row[6]); 
			if (row[7] != null)	vo.setContact((String) row[7]); 
			if (row[8] != null)	vo.setMemberStatusCd(String.valueOf(row[8])); 
			if (row[9] != null)	vo.setStatusCode((String) row[9]); 
			vo.setCreatedDate((Date) row[10]);
			vo.setCreatedBy((String) row[11]);
			vo.setUpdatedDate((Date) row[12]);
			vo.setUpdatedBy((String) row[13]);
			membershipList.add(vo);
		}
		return membershipList;
	}
	
	@Override
	public void updateCRMMembershipStatus(Long idCRMMembership, String statusCd) {
		String updatedBy = BaseContext.getUserFullName();
		if (StringUtils.isBlank(updatedBy)) updatedBy = BaseContext.getLoginId();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" update crm_membership set status_cd = :statusCd, ");
		sb.append(" upd_by = :updatedBy, ");
		sb.append(" dt_upd = now() ");
		
		sb.append(" where id = :idCRMMembership ");
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("statusCd", statusCd);
		query.setParameter("idCRMMembership", idCRMMembership);
		query.setParameter("updatedBy", updatedBy);
		query.executeUpdate();
	}
	
	@Override
	public void unlinkCRMMembership(Long idCRMMembership, Long idCustomer) {
		String updatedBy = BaseContext.getUserFullName();
		if (StringUtils.isBlank(updatedBy)) updatedBy = BaseContext.getLoginId();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" update crm_membership set id_customer = :idCustomer, ");
		sb.append(" upd_by = :updatedBy, ");
		sb.append(" dt_upd = now() ");
		
		sb.append(" where id = :idCRMMembership ");
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCustomer", idCustomer);
		query.setParameter("idCRMMembership", idCRMMembership);
		query.setParameter("updatedBy", updatedBy);
		query.executeUpdate();
	}
}
