package com.bcs.zsg.db.bterp.dao.journal;

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

import com.bcs.zsg.acct.vo.JournalVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.purchase.vo.ExOrderBillVO;

public class JournalDAOImpl extends BaseHibernateDAO implements JournalDAO {

	private Map<String, String> filters;
	
	@Override
	public int getListSizeJournal(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT COUNT(DISTINCT jour.id) ")
			.append(" FROM journal jour left join tour_dep tp ON jour.id_tour_dep = tp.id")
			.append(" LEFT JOIN ex_order_bill b ON b.id_journal = jour.id")
			.append(" LEFT JOIN supplier s ON s.id = b.id_supplier")
			.append(" LEFT JOIN person p ON p.id = s.id_person")
			.append(" LEFT JOIN corporate c ON c.id_person = p.id")
			.append(" WHERE jour.id_company = ").append(params.get("idCompany"));
		
		sb = genListFilter(sb, params);
		
//		System.out.println(sb.toString());

		Query query = createSQLQuery(sb.toString());
		
		if (params.get("typeCdList") != null)	query.setParameterList("typeCdList", (String[]) params.get("typeCdList"));

		return ((BigInteger) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	private StringBuilder genListFilter(StringBuilder _sb, Map<String, Object> params) {
		
		if (params.get("typeCdList") != null)	_sb.append(" and jour.type_cd in :typeCdList ");
		
		filters = (Map<String, String>) params.get("filters");
		
		if (!filters.isEmpty()) {
			_sb.append(" AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
					_sb = filterListJournal(it.next(), _sb);
				
				if (filters.size() > 1 && it.hasNext()) _sb.append(" AND ");
			}
			_sb.append(") ");
		}
		return _sb;
	}
	
	private StringBuilder filterListJournal(Entry<String, String> entry, StringBuilder _sb) {
		if ("sysNo".equals(entry.getKey())) _sb.append("jour.sys_no LIKE '%").append(entry.getValue()).append("%'");
		else if ("journalDt".equals(entry.getKey())) _sb.append("date_format(jour.dt_journal, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
		else if ("totalAmt".equals(entry.getKey())) _sb.append("jour.total_amt LIKE '%").append(entry.getValue()).append("%'");
		else if ("reason".equals(entry.getKey())) _sb.append("jour.reason LIKE '%").append(entry.getValue()).append("%'");
		else if ("tourCd".equals(entry.getKey())) _sb.append("tp.code LIKE '%").append(entry.getValue()).append("%'");
		else if ("exOrderBillVO.code".equals(entry.getKey())) _sb.append("b.code LIKE '%").append(entry.getValue()).append("%'");
		else if ("exOrderBillVO.billDt".equals(entry.getKey())) _sb.append("DATE_FORMAT(b.dt_bill, '%d-%b-%Y') LIKE '%").append(entry.getValue()).append("%'");
		else if ("exOrderBillVO.supplierName".equals(entry.getKey())) _sb.append("case when c.name <> '' then c.name else concat_ws(' ', p.last_name, p.first_name) end LIKE '%").append(entry.getValue()).append("%'");
		
		return _sb;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JournalVO> getListJournal(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT jour.id, jour.id_company, jour.id_tour_dep, jour.id_dpt, jour.dt_journal, jour.sys_no, jour.total_amt, ")
	    	.append("jour.reason, jour.reference, jour.dt_created, jour.created_by, jour.dt_upd, jour.upd_by, tp.code, ")
//	    	.append(" GROUP_CONCAT(DISTINCT b.code ORDER BY b.code SEPARATOR ', ') AS billIDs, ")
//	    	.append(" GROUP_CONCAT(DISTINCT DATE_FORMAT(b.dt_bill, '%d-%b-%Y') ORDER BY b.dt_bill SEPARATOR ', ') AS billDates, ")
	    	.append(" b.code as 'billID', b.dt_bill, ")
//	    	.append(" group_concat(distinct case when c.name <> '' then c.name else concat_ws(' ', p.last_name, p.first_name) end separator ',') as 'billSuppliers', ")
	    	.append(" case when c.name <> '' then c.name else concat_ws(' ', p.last_name, p.first_name) end, ")
	    	.append(" jour.type_cd, jour.id_bill ")
			.append(" FROM journal jour left join tour_dep tp ON jour.id_tour_dep = tp.id")
//			.append(" LEFT JOIN ex_order_bill b ON b.id_journal = jour.id")
			.append(" LEFT JOIN ex_order_bill b ON jour.id_bill = b.id")
			.append(" LEFT JOIN supplier s ON s.id = b.id_supplier")
			.append(" LEFT JOIN person p ON p.id = s.id_person")
			.append(" LEFT JOIN corporate c ON c.id_person = p.id")
//			.append(" left join ex_order_bill b on j.id = b.id_journal LEFT JOIN supplier s on s.id = b.id_supplier") // assume journal - ex_order_bill is one to one
			.append(" WHERE jour.id_company = ").append(params.get("idCompany"));
		
		sb = genListFilter(sb, params);
		
		sb.append(" GROUP BY jour.id ");
		sb.append(" ORDER BY ");
		
//		String sortDirection = "";
//		
//		if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) 
//			sortDirection = " ASC";
//		else 
//			sortDirection = " DESC";
//		
//		sb.append("jour.id").append(sortDirection);
		
		String sortField = (String) params.get("sortField");
		
		if (sortField == null) sb.append("cast(jour.sys_no as decimal) desc");
		else {
			if ("sysNo".equals(sortField)) sb.append("cast(jour.sys_no as decimal)");
			else if ("journalDt".equals(sortField)) sb.append("jour.dt_journal");
			
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" asc");
			else sb.append(" desc");
		}
		
//		System.out.println(sb.toString());
		
		Query query = createSQLQuery(sb.toString());
		query.setFirstResult((int) params.get("first"));
		query.setMaxResults((int) params.get("pageSize"));
		
		if (params.get("typeCdList") != null)	query.setParameterList("typeCdList", (String[]) params.get("typeCdList"));
		
		List<Object> queryResultList = query.list();
		List<JournalVO> returnResultList = new ArrayList<JournalVO>();

		for (Iterator<Object> it = queryResultList.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			JournalVO vo = new JournalVO();
			if (row[0] != null) vo.setId(((BigInteger) row[0]).longValue());
	        if (row[1] != null) vo.setCompanyId(((BigInteger)row[1]).longValue());
	        if (row[2] != null) vo.setIdTourDep(((BigInteger)row[2]).longValue());
	        if (row[3] != null) vo.setIdDpt((String) row[3]);
	        if (row[4] != null) vo.setJournalDt((Date) row[4]);
	        if (row[5] != null) vo.setSysNo((String) row[5]);
	        if (row[6] != null) vo.setTotalAmt(Double.parseDouble(row[6].toString()));
	        if (row[7] != null) vo.setReason((String) row[7]);
	        if (row[8] != null) vo.setReference((String) row[8]);
			if (row[9] != null) vo.setCreatedDate((Date) row[9]);
			if (row[10] != null) vo.setCreatedBy((String) row[10]);
			if (row[11] != null) vo.setUpdatedDate((Date) row[11]);
			if (row[12] != null) vo.setUpdatedBy((String) row[12]);
			if (row[13] != null) vo.setTourCd((String) row[13]);
			
//			if (row[14] != null) vo.setBillIDs((String) row[14]);
//			if (row[15] != null) vo.setBillDates((String) row[15]);
//			if (row[16] != null) vo.setBillSuppliers((String) row[16]);
			vo.setExOrderBillVO(new ExOrderBillVO());
			if (row[14] != null) vo.getExOrderBillVO().setCode((String) row[14]);
			if (row[15] != null) vo.getExOrderBillVO().setBillDt((Date) row[9]);
			if (row[16] != null) vo.getExOrderBillVO().setSupplierName((String) row[16]);
			
			if (row[17] != null) vo.setTypeCd((String) row[17]);
			if (row[18] != null) vo.setIdBill(((BigInteger) row[18]).longValue());

			returnResultList.add(vo);
		}
		
		return returnResultList;
	}
	
	@Override
	public JournalVO getJournal(JournalVO journalVO) throws BusinessException {

		Criteria criteria = getSession().createCriteria(JournalVO.class);
		criteria.add(Restrictions.eq("id", journalVO.getId()));
		criteria.add(Restrictions.eq("companyId", journalVO.getCompanyId()));
		criteria.add(Restrictions.eq("sysNo", journalVO.getSysNo()));
		JournalVO resultVO = (JournalVO) criteria.uniqueResult();
		getSession().flush();
		return resultVO;
	}
	
	@Override
	public JournalVO getJournalById(Long idJournal, Long idCompany) throws BusinessException {

		Criteria criteria = getSession().createCriteria(JournalVO.class);
		criteria.add(Restrictions.eq("id", idJournal));
		criteria.add(Restrictions.eq("companyId", idCompany));
		JournalVO resultVO = (JournalVO) criteria.uniqueResult();
		getSession().flush();
		return resultVO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JournalVO> getJournalListByBillId(Long idBill, Long idCompany) throws BusinessException {

		Criteria criteria = createCriteria(JournalVO.class);
		criteria.add(Restrictions.eq("idBill", idBill));
		criteria.add(Restrictions.eq("companyId", idCompany));
		return criteria.list();
	}
	
	@Override
	public JournalVO getJournalByPaxStmtId(Long idPaxStmt, Long idCompany) throws BusinessException {
		Criteria criteria = createCriteria(JournalVO.class);
		criteria.add(Restrictions.eq("idPs", idPaxStmt));
		criteria.add(Restrictions.eq("companyId", idCompany));
		JournalVO resultVO = (JournalVO) criteria.uniqueResult();
		return resultVO;
	}
}
