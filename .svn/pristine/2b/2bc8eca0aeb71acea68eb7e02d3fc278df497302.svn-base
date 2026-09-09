package com.bcs.zsg.db.bterp.dao.invoicehistory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.primefaces.model.SortOrder;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.history.vo.InvoiceHistoryViewVO;

public class InvoiceHistoryDAOImpl extends BaseHibernateDAO implements InvoiceHistoryDAO {

	private Map<String, String> filters;
	
	@Override
	public int getListSizeHistoryView(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT COUNT(1) ")
			.append(" FROM ( SELECT 1 ")
			.append(" 	FROM invoice_history invHist ")
			.append(" 	WHERE invHist.id_company = ").append(params.get("idCompany"))
			.append(" 		AND invHist.dt_upd between :fromDate AND :toDate");
		
		sb = genListFilter(sb, params, "getListHistoryView");

		sb.append(" GROUP BY invHist.id_hist ")
			.append(" ) A ");
		
//		System.out.println(sb.toString());
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("fromDate", (Date)params.get("fromDate"));
		query.setParameter("toDate", (Date)params.get("toDate"));
		
		return ((BigInteger) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	private StringBuilder genListFilter(StringBuilder _sb, Map<String, Object> params, String callerMethod) {
		filters = (Map<String, String>) params.get("filters");
		
		if (!filters.isEmpty()) {
			_sb.append(" AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				
				if(callerMethod.equals("getListHistoryView"))
					_sb = filterListInvoiceHistoryView(it.next(), _sb);
				
				if (filters.size() > 1 && it.hasNext()) _sb.append(" AND ");
			}
			_sb.append(") ");
		}
		return _sb;
	}

	private StringBuilder filterListInvoiceHistoryView(Entry<String, String> entry, StringBuilder _sb) {
		if ("psNo".equals(entry.getKey())) {
			_sb.append("invHist.ps_no LIKE '%").append(entry.getValue()).append("%'"); //pax statement filter
			
		} else if ("code".equals(entry.getKey())) {
			_sb.append("invHist.code LIKE '%").append(entry.getValue()).append("%'");

		} else if ("updatedBy".equals(entry.getKey())) {
			_sb.append("invHist.upd_by LIKE '%").append(entry.getValue()).append("%'");
			
		} else if ("reason".equals(entry.getKey())) {
			_sb.append("invHist.reason LIKE '%").append(entry.getValue()).append("%'");
			
		} else if ("statusCd".equals(entry.getKey())) {
			_sb.append("invHist.status_cd = '").append(entry.getValue()).append("'");
			
		} else if ("actionCd".equals(entry.getKey())) {
			_sb.append("invHist.action_cd = '").append(entry.getValue()).append("'");
		}
		return _sb;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceHistoryViewVO> getListHistoryView(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT invHist.id, invHist.id_hist, invHist.dt_upd, invHist.dt_inv, invHist.ps_no, invHist.code, ") //add ps_no
			.append("	invHist.reason, invHist.action_cd, invHist.upd_by, invHist.status_cd ")
			.append(" FROM invoice_history invHist ")
			.append(" WHERE invHist.id_company = ").append(params.get("idCompany"))
			.append(" 	AND invHist.dt_upd between :fromDate AND :toDate ");
		
		sb = genListFilter(sb, params, "getListHistoryView");
		sb.append(" GROUP BY invHist.id_hist ");

		sb.append(" ORDER BY ");
		
		String sortField = (String) params.get("sortField");
		String sortDirection = "";
		
		if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) 
			sortDirection = " ASC";
		else 
			sortDirection = " DESC";
		
		//No sort field as the page already contain filtering.
		if (filters.containsKey("code")) {
			sb.append("cast(CASE WHEN invHist.code LIKE '").append(filters.get("code"))
				.append("%' THEN invHist.code ELSE concat('99999', invHist.code) END as decimal)").append(sortDirection).append(", ");
		} 

		sb.append("invHist.id");
		
		sb.append(sortDirection);
		
		Query query = createSQLQuery(sb.toString());
		query.setFirstResult((int) params.get("first"));
		query.setMaxResults((int) params.get("pageSize"));
		query.setParameter("fromDate", (Date)params.get("fromDate"));
		query.setParameter("toDate", (Date)params.get("toDate"));
		
		List<Object> queryResultList = query.list();
		List<InvoiceHistoryViewVO> returnResultList = new ArrayList<InvoiceHistoryViewVO>();

		for (Iterator<Object> it = queryResultList.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			InvoiceHistoryViewVO vo = new InvoiceHistoryViewVO();
			vo.setId(((BigInteger) row[0]).longValue()); 
			vo.setIdHist(((BigInteger) row[1]).longValue()); 
			vo.setUpdatedDate((Date) row[2]);
			vo.setInvoiceDt((Date) row[3]);
			if (row[4] != null) vo.setPsNo((String) row[4]);
			vo.setCode((String) row[5]);
			vo.setReason((String) row[6]);
			vo.setActionCd((String) row[7]);
			vo.setUpdatedBy((String) row[8]);
			vo.setStatusCd((String) row[9]);

			returnResultList.add(vo);
		}
		
		return returnResultList;
	}
	
}
