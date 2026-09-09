package com.bcs.zsg.acct.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.model.SortOrder;

import com.bcs.zsg.acct.vo.PendingReverseJournalVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseContext;

public class PendingJournalDAOImpl extends BaseHibernateDAO implements PendingJournalDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public int getPendingReverseJournalListSize(Map<String, Object> params) throws BusinessException {
		
		// required to free pool connection (You Yuan)
		Session session = this.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(" SELECT count(*) ")
				.append(" FROM pending_reverse_journal j ")
					.append(" LEFT JOIN invoice ps ")
						.append(" ON j.id_ps = ps.id ")
					.append(" LEFT JOIN invoice i ")
						.append(" ON j.id_inv = i.id ")
					.append(" LEFT JOIN journal jour ")
						.append(" ON j.id_journal = jour.id ")
				.append(" WHERE 1=1 ");

			if (params.get("idCompany") != null) sb.append(" AND ps.id_company = :idCompany ");
			if (params.get("statusCd") != null) sb.append(" AND j.status_cd = :statusCd ");
			
			Map<String, String> filters = (Map<String, String>) params.get("filters");
			if (!filters.isEmpty()) {
				sb.append("and (");
				for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
					Entry<String, String> entry = it.next();
					
					if ("psNo".equals(entry.getKey())) sb.append("ps.ps_no like '%").append(entry.getValue()).append("%'");
					else if ("invNo".equals(entry.getKey())) sb.append("i.code like '%").append(entry.getValue()).append("%'");
					else if ("journalNo".equals(entry.getKey())) sb.append("jour.sys_no like '%").append(entry.getValue()).append("%'");
					else if ("statusCode".equals(entry.getKey())) sb.append("j.status_cd = '").append(entry.getValue()).append("'");
					else if ("updatedDate".equals(entry.getKey())) sb.append("date_format(ps.dt_upd, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
					else if ("updatedBy".equals(entry.getKey())) sb.append("ps.upd_by like '%").append(entry.getValue()).append("%'");
					if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
				}
				sb.append(")");
			}
			
			Query query = session.createSQLQuery(sb.toString());
//			if (params.get("first") != null && (Integer) params.get("first") >= 0)			query.setFirstResult((int) params.get("first"));
//			if (params.get("pageSize") != null && (Integer) params.get("pageSize") >= 0)	query.setMaxResults((int) params.get("pageSize"));
			
			if (params.get("idCompany") != null)	query.setParameter("idCompany", params.get("idCompany"));
			if (params.get("statusCd") != null)		query.setParameter("statusCd", params.get("statusCd"));
			
			return ((BigInteger) query.uniqueResult()).intValue();
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PendingReverseJournalVO> getPendingReverseJournalList(Map<String, Object> params) throws BusinessException {
		
		// required to free pool connection (You Yuan)
		Session session = this.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(" SELECT j.id, j.id_ps, j.id_inv, j.id_journal, j.status_cd, ps.id_company, ps.ps_no, i.code, jour.sys_no, ")
				.append(" j.dt_upd, ps.upd_by ")
				.append(" FROM pending_reverse_journal j ")
					.append(" LEFT JOIN invoice ps ")
						.append(" ON j.id_ps = ps.id ")
					.append(" LEFT JOIN invoice i ")
						.append(" ON j.id_inv = i.id ")
					.append(" LEFT JOIN journal jour ")
						.append(" ON j.id_journal = jour.id ")
				.append(" WHERE 1=1 ");

			if (params.get("idCompany") != null) sb.append(" AND ps.id_company = :idCompany ");
			if (params.get("statusCd") != null) sb.append(" AND j.status_cd = :statusCd ");
			
			Map<String, String> filters = (Map<String, String>) params.get("filters");
			if (filters != null && !filters.isEmpty()) {
				sb.append("and (");
				for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
					Entry<String, String> entry = it.next();
					
					if ("psNo".equals(entry.getKey())) sb.append("ps.ps_no like '%").append(entry.getValue()).append("%'");
					else if ("invNo".equals(entry.getKey())) sb.append("i.code like '%").append(entry.getValue()).append("%'");
					else if ("journalNo".equals(entry.getKey())) sb.append("jour.sys_no like '%").append(entry.getValue()).append("%'");
					else if ("statusCode".equals(entry.getKey())) sb.append("j.status_cd = '").append(entry.getValue()).append("'");
					else if ("updatedDate".equals(entry.getKey())) sb.append("date_format(ps.dt_upd, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
					else if ("updatedBy".equals(entry.getKey())) sb.append("ps.upd_by like '%").append(entry.getValue()).append("%'");
					if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
				}
				sb.append(")");
			}
			
			sb.append(" order by ");
			String sortField = (String) params.get("sortField");
			
			if (sortField == null) 
				sb.append(" j.id desc ");
			else {
				if ("psNo".equals(sortField)) sb.append("ps.ps_no");
				else if ("invNo".equals(sortField)) sb.append("i.code");
				else if ("journalNo".equals(sortField)) sb.append("jour.sys_no");
				else if ("statusCode".equals(sortField)) sb.append("j.status_cd");
				else if ("updatedDate".equals(sortField)) sb.append("ps.dt_upd");
				else if ("updatedBy".equals(sortField)) sb.append("ps.upd_by");
				
				if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" asc");
				else sb.append(" desc");
			}
			
//			System.out.println("PendingJournalDAOImpl.getPendingReverseJournalList()");
//			System.out.println(sb.toString());
			
			Query query = session.createSQLQuery(sb.toString());
			if (params.get("first") != null && (Integer) params.get("first") >= 0)			query.setFirstResult((int) params.get("first"));
			if (params.get("pageSize") != null && (Integer) params.get("pageSize") >= 0)	query.setMaxResults((int) params.get("pageSize"));
			
			if (params.get("idCompany") != null)	query.setParameter("idCompany", params.get("idCompany"));
			if (params.get("statusCd") != null)		query.setParameter("statusCd", params.get("statusCd"));
			
			List<Object> results = query.list();
			List<PendingReverseJournalVO> pendingReverseJournalList = new ArrayList<PendingReverseJournalVO>();

			for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
				Object[] row = (Object[]) it.next();
				PendingReverseJournalVO vo = new PendingReverseJournalVO();
				if (row[0] != null) vo.setId(((BigInteger) row[0]).longValue());
				if (row[1] != null) vo.setIdPs(((BigInteger) row[1]).longValue());
				if (row[2] != null) vo.setIdInv(((BigInteger) row[2]).longValue());
				if (row[3] != null) vo.setIdJournal(((BigInteger) row[3]).longValue());
				if (row[4] != null) vo.setStatusCode((String) row[4]);
		        if (row[5] != null) vo.setIdCompany(((BigInteger) row[5]).longValue());
		        if (row[6] != null) vo.setPsNo((String) row[6]);
		        if (row[7] != null) vo.setInvNo((String) row[7]);
		        if (row[8] != null) vo.setJournalNo((String) row[8]);
		        if (row[9] != null) vo.setUpdatedDate((Date) row[9]);
		        if (row[10] != null) vo.setUpdatedBy((String) row[10]);
		        
				pendingReverseJournalList.add(vo);
			}
			
			return pendingReverseJournalList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}
	
	@Override
	public void updatePendingReverseJournalStatus(Long id, String statusCd, Long idJournal) {
		
		// required to free pool connection (You Yuan)
		Session session = this.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			
			String updatedBy = BaseContext.getUserFullName();
			if (StringUtils.isBlank(updatedBy)) updatedBy = BaseContext.getLoginId();
			if (StringUtils.isBlank(updatedBy)) updatedBy = "SYSTEM";
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(" update pending_reverse_journal set ");
			if (StringUtils.isNotBlank(statusCd))	sb.append(" status_cd = :statusCd, ");
			if (idJournal != null)					sb.append(" id_journal = :idJournal, ");
			
			sb.append(" upd_by = :updatedBy, ");
			sb.append(" dt_upd = now() ");
			
			sb.append(" where id = :id ");
			
			Query query = session.createSQLQuery(sb.toString());
			query.setParameter("id", id);
			if (StringUtils.isNotBlank(statusCd))	query.setParameter("statusCd", statusCd);
			if (idJournal != null)					query.setParameter("idJournal", idJournal);
			query.setParameter("updatedBy", updatedBy);
			query.executeUpdate();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
