package com.bcs.zsg.acct.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import com.bcs.zsg.acct.vo.PendingEInvoiceVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class EInvoiceSchedulerDAOImpl extends BaseHibernateDAO implements EInvoiceSchedulerDAO {

	@Override
	public PendingEInvoiceVO getPendingEInvoiceVOById(Long id) throws BusinessException {
		Criteria criteria = createCriteria(PendingEInvoiceVO.class);
		criteria.add(Restrictions.eq("id", id));
		return (PendingEInvoiceVO) criteria.uniqueResult();
	}
	
	@Override
	public PendingEInvoiceVO getPendingEInvoiceVO(Long refId, String processStatus, String submitStatus, Date dtProcess) throws BusinessException {
		Criteria criteria = createCriteria(PendingEInvoiceVO.class);
		criteria.add(Restrictions.eq("refId", refId));
		criteria.add(Restrictions.eq("processStatus", processStatus));
		criteria.add(Restrictions.eq("submitStatus", submitStatus));
		criteria.add(Restrictions.eq("dtProcess", dtProcess));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return (PendingEInvoiceVO) criteria.uniqueResult();
	}
	
	@Override
	public List<PendingEInvoiceVO> getPendingEInvoiceList(Date dtProcess) {
		Criteria criteria = createCriteria(PendingEInvoiceVO.class);
		criteria.add(Restrictions.eq("processStatus", CommonConstant.EMAIL_PMNT_PENDING)); 
		criteria.add(Restrictions.eq("submitStatus", CommonConstant.EMAIL_PMNT_PENDING));
		criteria.add(Restrictions.eq("dtProcess", dtProcess));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return criteria.list();
	}
	
	@Override
	public int getPendingEInvoiceListSize(Map<String, Object> params) throws BusinessException { 
		StringBuilder sb = new StringBuilder();
		sb.append("select count(*) ");
		sb.append("from pending_e_invoice pe ");
			sb.append(" LEFT JOIN invoice iv ");
				sb.append(" ON pe.ref_id = iv.id ");
//			sb.append("LEFT JOIN invoice cn_iv ");
//				sb.append("ON iv.id_company = cn_iv.id_company AND iv.doc_type_cd = 'C' AND iv.cn_inv_no IS NOT NULL AND cn_iv.code = iv.cn_inv_no AND cn_iv.doc_type_cd = 'I' ");
//			sb.append("LEFT JOIN invoice cn_ps ");
//				sb.append("ON iv.id_company = cn_ps.id_company AND iv.doc_type_cd = 'C' AND iv.cn_ps_no IS NOT NULL AND cn_ps.ps_no = iv.cn_ps_no AND cn_ps.doc_type_cd = 'P' ");
//			sb.append("LEFT JOIN tour_dep d ");
//				sb.append("ON ifnull(cn_iv.id_tour_dep, cn_ps.id_tour_dep) = d.id, ");
//			sb.append("employee ep, sec_user usr, customer cm, person ps ");
		sb.append("where pe.id_company = :idCompany ");
		
		if (params.get("orderSourceCode") != null) sb.append("and iv.order_cd = :orderSourceCode ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		if (!filters.isEmpty()) {
			sb.append("and (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				
				if ("dtProcess".equals(entry.getKey())) sb.append("date_format(pe.dt_process, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
				else if ("refNo".equals(entry.getKey())) sb.append("pe.ref_no like '%").append(entry.getValue()).append("%'");
				else if ("invoiceVO.invoiceDt".equals(entry.getKey())) sb.append("date_format(iv.dt_inv, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
				else if ("invoiceVO.departureDt".equals(entry.getKey())) sb.append("date_format(iv.dt_departure, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
				else if ("processStatus".equals(entry.getKey())) sb.append("pe.process_status = '").append(entry.getValue()).append("'");
				else if ("submitStatus".equals(entry.getKey())) sb.append("pe.submit_status = '").append(entry.getValue()).append("'");
				else if ("reason".equals(entry.getKey())) sb.append("pe.reason like '%").append(entry.getValue()).append("%'");

				if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
			}
			sb.append(")");
		}
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("idCompany"));
		if (params.get("orderSourceCode") != null) query.setParameter("orderSourceCode", params.get("orderSourceCode"));
		
//		System.out.println("InvoiceDAOImpl.getCreditNoteDisplayList()");
//		System.out.println(sb.toString());
		
		return ((BigInteger) query.uniqueResult()).intValue();
	}
	
	@Override
	public List<PendingEInvoiceVO> getPendingEInvoiceList(Map<String, Object> params) throws BusinessException { 
		StringBuilder sb = new StringBuilder();
		sb.append(" select pe.id, pe.id_company, pe.dt_process, pe.sys_doc_type, pe.sys_prefix, pe.ref_id, pe.ref_no, ");
		sb.append(" pe.document_uuid, pe.submission_uid, pe.process_status, pe.submit_status, pe.reason, pe.status_cd, ");
		sb.append(" iv.dt_departure, iv.dt_inv, iv.cat_cd, ");
		sb.append(" pe.dt_created ");
		sb.append(" from pending_e_invoice pe ");
			sb.append(" LEFT JOIN invoice iv ");
				sb.append(" ON pe.ref_id = iv.id ");
//			sb.append("LEFT JOIN invoice cn_ps ");
//				sb.append("ON iv.id_company = cn_ps.id_company AND iv.doc_type_cd = 'C' AND iv.cn_ps_no IS NOT NULL AND cn_ps.ps_no = iv.cn_ps_no AND cn_ps.doc_type_cd = 'P' ");
//			sb.append("LEFT JOIN tour_dep d ");
//				sb.append("ON ifnull(cn_iv.id_tour_dep, cn_ps.id_tour_dep) = d.id, ");
//			sb.append("employee ep, sec_user usr, customer cm, person ps ");
		sb.append(" where pe.id_company = :idCompany ");
		
		if (params.get("orderSourceCode") != null) sb.append("and iv.order_cd = :orderSourceCode ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		if (!filters.isEmpty()) {
			sb.append("and (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				
				if ("dtProcess".equals(entry.getKey())) sb.append("date_format(pe.dt_process, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
				else if ("refNo".equals(entry.getKey())) sb.append("pe.ref_no like '%").append(entry.getValue()).append("%'");
				else if ("invoiceVO.invoiceDt".equals(entry.getKey())) sb.append("date_format(iv.dt_inv, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
				else if ("invoiceVO.departureDt".equals(entry.getKey())) sb.append("date_format(iv.dt_departure, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
				else if ("processStatus".equals(entry.getKey())) sb.append("pe.process_status = '").append(entry.getValue()).append("'");
				else if ("submitStatus".equals(entry.getKey())) sb.append("pe.submit_status = '").append(entry.getValue()).append("'");
				else if ("reason".equals(entry.getKey())) sb.append("pe.reason like '%").append(entry.getValue()).append("%'");
				
				if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
			}
			sb.append(")");
		}
		
		sb.append(" order by ");
		String sortField = (String) params.get("sortField");
		
		if (sortField == null) 
			sb.append(" pe.dt_created desc ");
		else {
			if ("dtProcess".equals(sortField)) sb.append("pe.dt_process");
			else if ("refNo".equals(sortField)) sb.append("pe.ref_no");
			else if ("invoiceVO.invoiceDt".equals(sortField)) sb.append("iv.dt_inv");
			else if ("invoiceVO.departureDt".equals(sortField)) sb.append("iv.dt_departure");
			else if ("processStatus".equals(sortField)) sb.append("pe.process_status");
			else if ("submitStatus".equals(sortField)) sb.append("pe.submit_status");
			else if ("reason".equals(sortField)) sb.append("pe.reason");
			
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" asc");
			else sb.append(" desc");
		}
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("idCompany"));
		if (params.get("orderSourceCode") != null) query.setParameter("orderSourceCode", params.get("orderSourceCode"));
		if (params.get("first") != null && (Integer) params.get("first") >= 0)	query.setFirstResult((Integer) params.get("first"));
		if (params.get("pageSize") != null && (Integer) params.get("pageSize") >= 0)	query.setMaxResults((Integer) params.get("pageSize"));
		
		System.out.println("EInvoiceSchedulerDAOImpl.getPendingEInvoiceList()");
		System.out.println(sb.toString());
		
		List<Object> results = query.list();
		List<PendingEInvoiceVO> pendingEInvoiceList = new ArrayList<PendingEInvoiceVO>();

		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			PendingEInvoiceVO vo = new PendingEInvoiceVO();
			vo.setId(((BigInteger) row[0]).longValue());
			vo.setIdCompany(((BigInteger) row[1]).longValue());
			if (row[2] != null)	vo.setDtProcess((Date) row[2]);
			if (row[3] != null)	vo.setSysDocType((String) row[3]);
			if (row[4] != null)	vo.setSysPrefix((String) row[4]);
			if (row[5] != null)	vo.setRefId(((BigInteger) row[5]).longValue());
			if (row[6] != null)	vo.setRefNo((String) row[6]);
			if (row[7] != null)	vo.setDocumentUuid((String) row[7]);
			if (row[8] != null)	vo.setSubmissionUid((String) row[8]);
			if (row[9] != null)	vo.setProcessStatus((String) row[9]);
			if (row[10] != null)	vo.setSubmitStatus((String) row[10]);
			if (row[11] != null)	vo.setReason((String) row[11]);
			if (row[12] != null)	vo.setStatusCode((String) row[12]);
			
			vo.setInvoiceVO(new InvoiceVO());
			if (row[13] != null)	vo.getInvoiceVO().setDepartureDt((Date) row[13]);
			if (row[14] != null)	vo.getInvoiceVO().setInvoiceDt((Date) row[14]);
			if (row[15] != null)	vo.getInvoiceVO().setCatCd((String) row[15]);
			if (row[16] != null)	vo.setCreatedDate((Date) row[16]);
			
			pendingEInvoiceList.add(vo);
		}
		return pendingEInvoiceList;
	}
}
