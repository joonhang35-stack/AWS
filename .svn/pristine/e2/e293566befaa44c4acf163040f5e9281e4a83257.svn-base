package com.bcs.zsg.db.bterp.dao.gst;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;
import org.apache.commons.lang.StringUtils;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.db.bterp.vo.gst.GSTGAFVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTMappingStateVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTReportVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTSummaryTaxVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTSummaryVO;
import com.bcs.zsg.db.bterp.vo.gst.TaxCodeVO;
import com.bcs.zsg.gst.helper.TaxCodeType;

public class GSTDAOImpl extends BaseHibernateDAO implements GSTDAO {
	
	@Override
	public TaxCodeVO getTaxCode(Map<String, Object> params) throws BusinessException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT id, UUID, tax_type, sub_type, code, description, rate FROM tax_code ")
			.append(" WHERE tax_type='").append(params.get("taxType")).append("'")
			.append(" AND code='").append(params.get("code")).append("'")
			.append(" AND status_cd='").append(params.get("statusCode")).append("'");
		
		Query query = createSQLQuery(sb.toString());
		Object[] row =  (Object[])query.uniqueResult();
		
		TaxCodeVO vo = new TaxCodeVO();
		vo.setId(((Integer) row[0]).longValue()); 
		vo.setUuid((String) row[1]);
		vo.setTaxType((String) row[2]);
		vo.setSubType((String) row[3]);
		vo.setCode((String) row[4]);
		vo.setDescription((String) row[5]);
		vo.setRate((Float) row[6]);
		
		return vo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaxCodeVO> getTaxCodeList(String taxType, String gstType) throws BusinessException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT id, UUID, tax_type, sub_type, code, description, rate FROM tax_code ")
			.append(" WHERE tax_type='").append(taxType).append("'")
			.append(" AND sub_type='").append(gstType).append("'")
			.append(" AND status_cd='").append(BaseConstant.STATUS_ACTIVE).append("'");
		
		Query query = createSQLQuery(sb.toString());

		List<Object> results = query.list();
		List<TaxCodeVO> voList = new ArrayList<TaxCodeVO>();
		int i = 1;
		
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			TaxCodeVO vo = new TaxCodeVO();
			vo.setSeqNo(i);
			vo.setId(((Integer) row[0]).longValue()); 
			vo.setUuid((String) row[1]);
			vo.setTaxType((String) row[2]);
			vo.setSubType((String) row[3]);
			vo.setCode((String) row[4]);
			vo.setDescription((String) row[5]);
			vo.setRate((Float) row[6]);
			voList.add(vo);
			i++;
			
		}
		
		return voList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getNonClaimableTaxList() throws BusinessException {
		
		// required to free pool connection (You Yuan)
		Session session = this.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT id, UUID, tax_type, sub_type, code, description, rate  FROM tax_code ")
				.append(" WHERE tax_type = '").append(TaxCodeType.GST.getValue()).append("'")
				.append(" AND status_cd = '").append(BaseConstant.STATUS_ACTIVE).append("'")
				.append(" AND type_cd = 'N'");
			
			Query query = session.createSQLQuery(sb.toString());

			List<Object> results = query.list();
			List<String> voList = new ArrayList<String>();
			
			for (Iterator<Object> it = results.iterator(); it.hasNext();) {
				Object[] row = (Object[]) it.next();
				voList.add((String) row[4]);
			}
			
			return voList;
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GSTSummaryVO> getMonthlySummaryList(Map<String, Object> params) throws BusinessException {

		Criteria criteria = createCriteria(GSTSummaryVO.class);
		criteria = updateRestriction(criteria, params);
		criteria.addOrder(Order.desc("dateFrom"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GSTSummaryTaxVO> getTaxSummaryVOList(Map<String, Object> params) throws BusinessException {
		Criteria criteria = createCriteria(GSTSummaryTaxVO.class);
		criteria = updateRestriction(criteria, params);
		return criteria.list();
	}
	
	private Criteria updateRestriction(Criteria criteria, Map<String, Object> params) {

		if(params.containsKey("idCompany"))
			criteria.add(Restrictions.eq("idCompany", params.get("idCompany")));

		if(params.containsKey("dateFrom"))
			criteria.add(Restrictions.ge("dateFrom", params.get("dateFrom")));

		if(params.containsKey("dateTo"))
			criteria.add(Restrictions.le("dateTo", params.get("dateTo")));

		if(params.containsKey("idGSTSummary"))
			criteria.add(Restrictions.eq("idGSTSummary", params.get("idGSTSummary")));

		if(params.containsKey("statusCode"))
			criteria.add(Restrictions.eq("statusCode", params.get("statusCode")));

		if(params.containsKey("dateBetween"))
			criteria.add(Restrictions.sqlRestriction("'".concat(new SimpleDateFormat("yyyy-MM-dd").format(params.get("dateBetween"))).concat("' BETWEEN date_from AND date_to")));
		
		if(params.containsKey("dateFromEq"))
			criteria.add(Restrictions.eq("dateFrom", params.get("dateFromEq")));

		if(params.containsKey("dateToEq"))
			criteria.add(Restrictions.eq("dateTo", params.get("dateToEq")));
		
		return criteria;
	}
	
	@Override
	public GSTSummaryVO getMonthSummary(Map<String, Object> params) throws BusinessException {

		Criteria criteria = createCriteria(GSTSummaryVO.class);
		criteria = updateRestriction(criteria, params);
		return (GSTSummaryVO) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaxCodeVO> getTaxSummaryList(Map<String, Object> params, boolean includeCurrentMonth) throws BusinessException {
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT A.date_from, date_from AS dateFrom, date_to AS dateTo, ")
			.append("tc.code, tc.sub_type AS subType, A.amount AS totalAmount, A.rate, A.tax_amount AS taxAmount ")
			.append("FROM tax_code tc INNER JOIN ( ")
			.append("	SELECT date_from, date_to, tax_code, SUM(IFNULL(amount, 0.00)) AS amount, IFNULL(rate, 0.00) AS rate, ")
			.append("		SUM(IFNULL(tax_amount, 0.00)) AS tax_amount ")
			.append("	FROM ( ");
		
		if(params.containsKey("summaryId")) {
			sb.append(" SELECT 'SUMMARY' AS ID, gs.date_from, gs.date_to, gst.tax_code, gst.amount, gst.rate, gst.tax_amount ")
				.append("FROM gst_summary_tax gst ")
				.append("LEFT JOIN gst_summary gs ON gs.id = gst.id_gst_summary ")
				.append("WHERE gst.id_gst_summary IN (:summaryId)");
		
			if(includeCurrentMonth)
				sb.append(" UNION ");
		}
		
		if(includeCurrentMonth) {
			//ALL
			sb.append("SELECT 'ACCT' AS ID, DATE(date_from) AS date_from, DATE(date_to) AS date_to, tax_code, SUM(amount) AS amount, tax_rate as rate, ")
				.append(" SUM(tax_amount) AS tax_amount ")
				.append("FROM (SELECT ")
				/*.append("		CASE WHEN acctTrans.credit != 0.00 THEN acctTrans.credit ELSE acctTrans.debit END AS amount, ")
				.append("		acctTrans.tax_code, acctTrans.tax_rate, ")
				.append("		CASE WHEN (acctTrans.tax_code = 'AJS' OR acctTrans.tax_code = 'AJP') THEN ")
				.append("				CASE WHEN acctTrans.debit != 0.00 THEN -1 * acctTrans.tax_amount ")
				.append("					WHEN acctTrans.credit != 0.00 THEN acctTrans.tax_amount END ")
				.append("			ELSE acctTrans.tax_amount END AS tax_amount")*/
				
				.append("DATE_FORMAT(acctTrans.dt_trans, '%Y-%m-01') AS date_from, ")
				.append("LAST_DAY(acctTrans.dt_trans) AS date_to, ")
				.append(" CASE ")
				.append("WHEN acctTrans.sys_cd = 'purc_bill' THEN ")
					//.append("ROUND(acctTrans.debit - acctTrans.credit, 2) ")
					.append("CASE ")
						.append("WHEN ROUND(acctTrans.debit - acctTrans.credit, 2) < 0.00 AND tc.sub_type = 'OUT' THEN ")
							.append("IF(ROUND(acctTrans.debit - acctTrans.credit, 2) = 0.00, 0.00, ROUND(acctTrans.debit - acctTrans.credit, 2) * -1) ")
						.append("ELSE ")
							.append("ROUND(acctTrans.debit - acctTrans.credit, 2) ")
					.append("END ")
				.append("WHEN acctTrans.sys_cd = 'bank_deps' THEN ")
					.append("ROUND(acctTrans.credit - acctTrans.debit, 2) ")
				.append("WHEN acctTrans.sys_cd = 'crdt_note' THEN ")
					.append("CASE WHEN tc.sub_type = 'OUT' THEN ")
						.append("ROUND(acctTrans.credit - acctTrans.debit, 2) ")
					.append("ELSE ")
						.append("ROUND(acctTrans.debit - acctTrans.credit , 2) ")
					.append("END ")
				.append("WHEN acctTrans.sys_cd = 'bank_pmnt' and DATE(acctTrans.dt_trans) >= DATE(:bpDate) THEN ")
					.append("ROUND(acctTrans.debit - acctTrans.credit, 2) ")
				.append("ELSE ")
					.append("CASE WHEN acctTrans.credit != 0.00 THEN acctTrans.credit ELSE acctTrans.debit END ")
				.append("END AS amount, ")
				.append("acctTrans.tax_code, acctTrans.tax_rate, ")
				.append("CASE WHEN (acctTrans.tax_code = 'AJS' OR acctTrans.tax_code = 'AJP') THEN ")
					.append("CASE WHEN acctTrans.debit != 0.00 THEN -1 * acctTrans.tax_amount WHEN acctTrans.credit != 0.00 THEN acctTrans.tax_amount END ")
				.append("ELSE ")
					.append("CASE WHEN acctTrans.sys_cd = 'purc_bill' THEN ")
						//.append("IF(ROUND(acctTrans.debit - acctTrans.credit, 2) < 0.00, IF(acctTrans.tax_amount = 0.00, 0.00, acctTrans.tax_amount * -1), acctTrans.tax_amount) ")
						.append("CASE ")
							.append("WHEN ROUND(acctTrans.debit - acctTrans.credit, 2) < 0.00 AND tc.sub_type = 'OUT' THEN ")
								.append("acctTrans.tax_amount ")
							.append("ELSE ")
								.append("IF(ROUND(acctTrans.debit - acctTrans.credit, 2) < 0.00, IF(acctTrans.tax_amount = 0.00, 0.00, acctTrans.tax_amount * -1), acctTrans.tax_amount) ")
						.append("END ")
					.append("WHEN acctTrans.sys_cd = 'crdt_note' THEN ")
						.append("CASE WHEN tc.sub_type = 'OUT' THEN ")
							.append("IF(ROUND(acctTrans.credit - acctTrans.debit, 2) < 0.00, IF(acctTrans.tax_amount = 0.00, 0.00, acctTrans.tax_amount * -1), acctTrans.tax_amount) ")
						.append("ELSE ")
							.append("IF(ROUND(acctTrans.debit - acctTrans.credit, 2) < 0.00, IF(acctTrans.tax_amount = 0.00, 0.00, acctTrans.tax_amount * -1), acctTrans.tax_amount) ")
						.append("END ")
					.append("ELSE ")
						.append("acctTrans.tax_amount ")
					.append("END ")
				.append("END AS tax_amount ")
				
				.append("	FROM account_trans acctTrans LEFT JOIN tax_code tc ON tc.tax_type = 'GST' AND acctTrans.tax_code = tc.code AND tc.status_cd = :statusCd ")
				.append("	WHERE acctTrans.id_company= :idCompany AND acctTrans.dt_trans BETWEEN :dateFrom AND :dateTo ")
				.append("			AND acctTrans.status_cd = 'A' AND IFNULL(tax_code, '') != '' ")
				.append(") ACCT GROUP BY date_from, date_to, tax_code, rate ");
		}
		
		sb.append("	) B GROUP BY date_from, date_to, tax_code, rate ")
			.append("	) A ON A.tax_code = tc.code ")
			.append(" WHERE tc.tax_type =:taxType ")
			.append(" ORDER BY date_from, date_to, tc.sub_type, tc.code ASC");
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		if(params.containsKey("summaryId"))
			query.setParameterList("summaryId", (List<Long>) params.get("summaryId"));
		
		if(includeCurrentMonth) {
			query.setParameter("idCompany", params.get("idCompany"));
			query.setParameter("dateFrom", params.get("dateFrom"));
			query.setParameter("dateTo", params.get("dateTo"));
			query.setParameter("bpDate", params.get("bpDate"));
			query.setParameter("statusCd", BaseConstant.STATUS_ACTIVE);
		}
		query.setParameter("taxType", params.get("taxType"));
		
		query.addScalar("dateFrom");
		query.addScalar("dateTo");
		query.addScalar("code");
		query.addScalar("subType");
		query.addScalar("totalAmount", DoubleType.INSTANCE);
		query.addScalar("rate", FloatType.INSTANCE);
		query.addScalar("taxAmount", DoubleType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(TaxCodeVO.class));
		
		return query.list();
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.db.bterp.dao.gst.GSTDAO#getGSTTaxList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GSTReportVO> getGSTTaxList(Long idCompany, SearchParamVO searchParamVO, String type) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT ").
			append("a.dt_trans as transDt, c.prefix_id as prefix, a.sys_cd as sysCd, a.sys_no as sysNo, a.source, a.destination, ").
			append("a.tax_code as taxCode, t.description as taxDesc, a.tax_rate as taxRate, ");
			
			if (type.equals("OUTPUT") || type.equals("OUTPUT_SMMRY") ||
				type.equals("INPUT") || type.equals("INPUT_SMMRY")) {
				sb.append("SUM(CASE ").
				append("WHEN a.sys_cd = 'purc_bill' THEN ").
					//append("ROUND(a.debit - a.credit, 2) ").
					append("CASE ").
						append("WHEN ROUND(a.debit - a.credit, 2) < 0.00 AND t.sub_type = 'OUT' THEN ").
							append("IF(ROUND(a.debit - a.credit, 2) = 0.00, 0.00, ROUND(a.debit - a.credit, 2) * -1) ").
						append("ELSE ").
							append("ROUND(a.debit - a.credit, 2) ").
					append("END ").
				append("WHEN a.sys_cd = 'bank_deps' THEN ").
					append("ROUND(a.credit - a.debit, 2) ").
				append("WHEN a.sys_cd = 'crdt_note' THEN ").
					append("CASE WHEN t.sub_type = 'OUT' THEN ").
						append("ROUND(a.credit - a.debit, 2) ").
					append("ELSE ").
						append("ROUND(a.debit - a.credit, 2) ").
					append("END ").
				append("WHEN a.sys_cd = 'bank_pmnt' and DATE(a.dt_trans) >= DATE(:bpDate) THEN ").
					append("ROUND(a.debit - a.credit, 2) ").
				append("ELSE ").
					append("CASE WHEN a.credit != 0.00 THEN a.credit ELSE a.debit END ").
				append("END) AS amount, ").
				
				append("SUM(CASE WHEN (a.tax_code = 'AJS' OR a.tax_code = 'AJP') THEN ").
					append("CASE WHEN a.debit != 0.00 THEN -1 * a.tax_amount WHEN a.credit != 0.00 THEN a.tax_amount END ").
				append("ELSE ").
					append("CASE WHEN a.sys_cd = 'purc_bill' THEN ").
						//append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
						append("CASE ").
							append("WHEN ROUND(a.debit - a.credit, 2) < 0.00 AND t.sub_type = 'OUT' THEN ").
								append("a.tax_amount ").
							append("ELSE ").
								append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
						append("END ").
					append("WHEN a.sys_cd = 'crdt_note' THEN ").
						append("CASE WHEN t.sub_type = 'OUT' THEN ").
							append("IF(ROUND(a.credit - a.debit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount * -1) ").
						append("ELSE ").
							append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
						append("END ").
					append("ELSE ").
						append("a.tax_amount ").
					append("END ").
				append("END) AS taxAmt, ").
				
				append("SUM(CASE ").
				append("WHEN a.sys_cd = 'purc_bill' THEN ").
					//append("ROUND(a.debit - a.credit, 2) ").
					append("CASE ").
						append("WHEN ROUND(a.debit - a.credit, 2) < 0.00 AND t.sub_type = 'OUT' THEN ").
							append("IF(ROUND(a.debit - a.credit, 2) = 0.00, 0.00, ROUND(a.debit - a.credit, 2) * -1) ").
						append("ELSE ").
							append("ROUND(a.debit - a.credit, 2) ").
					append("END ").
				append("WHEN a.sys_cd = 'bank_deps' THEN ").
					append("ROUND(a.credit - a.debit, 2) ").
				append("WHEN a.sys_cd = 'crdt_note' THEN ").
					append("CASE WHEN t.sub_type = 'OUT' THEN ").
						append("ROUND(a.credit - a.debit, 2) ").
					append("ELSE ").
						append("ROUND(a.debit - a.credit, 2) ").
					append("END ").
				append("WHEN a.sys_cd = 'bank_pmnt' and DATE(a.dt_trans) >= DATE(:bpDate) THEN ").
					append("ROUND(a.debit - a.credit, 2) ").
				append("ELSE ").
					append("CASE WHEN a.credit != 0.00 THEN a.credit ELSE a.debit END ").
				append("END ").
				append("+ ").
				append("CASE WHEN (a.tax_code = 'AJS' OR a.tax_code = 'AJP') THEN ").
					append("CASE WHEN a.debit != 0.00 THEN -1 * a.tax_amount WHEN a.credit != 0.00 THEN a.tax_amount END ").
				append("ELSE ").
					append("CASE WHEN a.sys_cd = 'purc_bill' THEN ").
						//append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
						append("CASE ").
							append("WHEN ROUND(a.debit - a.credit, 2) < 0.00 AND t.sub_type = 'OUT' THEN ").
								append("a.tax_amount ").
							append("ELSE ").
								append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
						append("END ").
					append("WHEN a.sys_cd = 'crdt_note' THEN ").
						append("CASE WHEN t.sub_type = 'OUT' THEN ").
							append("IF(ROUND(a.credit - a.debit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount * -1) ").
						append("ELSE ").
							append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
						append("END ").
					append("ELSE ").
						append("a.tax_amount ").
					append("END ").
				append("END) AS taxIncAmt, ");
			} else if (type.equals("OUTPUT_ALL") || type.equals("INPUT_ALL")) {
				sb.append("CASE ").
				append("WHEN a.sys_cd = 'purc_bill' THEN ").
					//append("ROUND(a.debit - a.credit, 2) ").
					append("CASE ").
						append("WHEN ROUND(a.debit - a.credit, 2) < 0.00 AND t.sub_type = 'OUT' THEN ").
							append("IF(ROUND(a.debit - a.credit, 2) = 0.00, 0.00, ROUND(a.debit - a.credit, 2) * -1) ").
						append("ELSE ").
							append("ROUND(a.debit - a.credit, 2) ").
					append("END ").
				append("WHEN a.sys_cd = 'bank_deps' THEN ").
					append("ROUND(a.credit - a.debit, 2) ").
				append("WHEN a.sys_cd = 'crdt_note' THEN ").
					append("CASE WHEN t.sub_type = 'OUT' THEN ").
						append("ROUND(a.credit - a.debit, 2) ").
					append("ELSE ").
						append("ROUND(a.debit - a.credit, 2) ").
					append("END ").
				append("WHEN a.sys_cd = 'bank_pmnt' and DATE(a.dt_trans) >= DATE(:bpDate) THEN ").
					append("ROUND(a.debit - a.credit, 2) ").
				append("ELSE ").
					append("CASE WHEN a.credit != 0.00 THEN a.credit ELSE a.debit END ").
				append("END AS amount, ").
				
				append("CASE WHEN (a.tax_code = 'AJS' OR a.tax_code = 'AJP') THEN ").
					append("CASE WHEN a.debit != 0.00 THEN -1 * a.tax_amount WHEN a.credit != 0.00 THEN a.tax_amount END ").
				append("ELSE ").
					append("CASE WHEN a.sys_cd = 'purc_bill' THEN ").
						//append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
						append("CASE ").
							append("WHEN ROUND(a.debit - a.credit, 2) < 0.00 AND t.sub_type = 'OUT' THEN ").
								append("a.tax_amount ").
							append("ELSE ").
								append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
						append("END ").
					append("WHEN a.sys_cd = 'crdt_note' THEN ").
						append("CASE WHEN t.sub_type = 'OUT' THEN ").
							append("IF(ROUND(a.credit - a.debit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount * -1) ").
						append("ELSE ").
							append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
						append("END ").
					append("ELSE ").
						append("a.tax_amount ").
					append("END ").
				append("END AS taxAmt, ").
				
				append("CASE ").
				append("WHEN a.sys_cd = 'purc_bill' THEN ").
					//append("ROUND(a.debit - a.credit, 2) ").
					append("CASE ").
						append("WHEN ROUND(a.debit - a.credit, 2) < 0.00 AND t.sub_type = 'OUT' THEN ").
							append("IF(ROUND(a.debit - a.credit, 2) = 0.00, 0.00, ROUND(a.debit - a.credit, 2) * -1) ").
						append("ELSE ").
							append("ROUND(a.debit - a.credit, 2) ").
					append("END ").
				append("WHEN a.sys_cd = 'bank_deps' THEN ").
					append("ROUND(a.credit - a.debit, 2) ").
				append("WHEN a.sys_cd = 'crdt_note' THEN ").
					append("CASE WHEN t.sub_type = 'OUT' THEN ").
						append("ROUND(a.credit - a.debit, 2) ").
					append("ELSE ").
						append("ROUND(a.debit - a.credit, 2) ").
					append("END ").
				append("WHEN a.sys_cd = 'bank_pmnt' and DATE(a.dt_trans) >= DATE(:bpDate) THEN ").
					append("ROUND(a.debit - a.credit, 2) ").
				append("ELSE ").
					append("CASE WHEN a.credit != 0.00 THEN a.credit ELSE a.debit END ").
				append("END ").
				append("+ ").
				append("CASE WHEN (a.tax_code = 'AJS' OR a.tax_code = 'AJP') THEN ").
					append("CASE WHEN a.debit != 0.00 THEN -1 * a.tax_amount WHEN a.credit != 0.00 THEN a.tax_amount END ").
				append("ELSE ").
					append("CASE WHEN a.sys_cd = 'purc_bill' THEN ").
						//append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
						append("CASE ").
							append("WHEN ROUND(a.debit - a.credit, 2) < 0.00 AND t.sub_type = 'OUT' THEN ").
								append("a.tax_amount ").
							append("ELSE ").
								append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
						append("END ").
					append("WHEN a.sys_cd = 'crdt_note' THEN ").
					// Credit note negative value the gst should be negative also
						append("CASE WHEN t.sub_type = 'OUT' THEN ").
							append("IF(ROUND(a.credit - a.debit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount * -1) ").
						append("ELSE ").
							append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
						append("END ").
					append("ELSE ").
						append("a.tax_amount ").
					append("END ").
				append("END AS taxIncAmt, ");
			}
			
			sb.append("CASE ").
				append("WHEN a.sys_cd = 'purc_bill' THEN ex1.bp_code ").
				append("WHEN a.sys_cd = 'bank_pmnt' THEN ex2.pb_code ").
				append("WHEN a.sys_cd = 'crdt_note' THEN ex3.cn_code ").
				append("ELSE '' ").
			append("END as exCode ").
			
		append("FROM account_trans a LEFT JOIN sys_num_conf c on a.id_company = c.id_company and a.sys_cd = c.code ").
		append("LEFT JOIN (select b.code as sys_no, group_concat(bp.code separator ', ') as bp_code ").
			append("FROM ex_order_bill b, ex_order_bill_pmnt bp ").
			append("WHERE b.id = bp.id_eo_bill and bp.status_cd = 'A' and b.id_company = :idCompany and b.status_cd not in ('CC', 'VD') ").
			append("GROUP BY b.id) ex1 on a.sys_cd = 'purc_bill' and a.sys_no = ex1.sys_no ").
		append("LEFT JOIN (SELECT group_concat(b.code separator ', ') as pb_code, bp.code as sys_no ").
			append("FROM ex_order_bill b, ex_order_bill_pmnt bp ").
			append("WHERE b.id = bp.id_eo_bill and bp.status_cd = 'A' and b.id_company = :idCompany and b.status_cd not in ('CC', 'VD') ").
			append("GROUP BY bp.code) ex2 on a.sys_cd = 'bank_pmnt' and a.sys_no = ex2.sys_no ").
		append("LEFT JOIN ( ").
			append("SELECT i.code as sys_no, i.cn_inv_no as cn_code from invoice i where doc_type_cd = 'C' and i.id_company = :idCompany and i.status_cd not in ('CC', 'VD') ").
		append(") ex3 on a.sys_cd = 'crdt_note' and a.sys_no = ex3.sys_no, ").
		append("tax_code t ").
		append("WHERE a.id_company= :idCompany AND a.dt_trans BETWEEN date(:frDate) AND date(:toDate) AND a.status_cd = :statusCd AND IFNULL(a.tax_code, '') != '' ");
		if (type.equals("OUTPUT") || type.equals("OUTPUT_SMMRY") || type.equals("OUTPUT_ALL")) {
			sb.append("and t.tax_type = 'GST' and t.sub_type = 'OUT' and a.tax_code = t.code and t.status_cd = :statusCd ");
		} else if (type.equals("INPUT") || type.equals("INPUT_SMMRY") || type.equals("INPUT_ALL")) {
			sb.append("and t.tax_type = 'GST' and t.sub_type = 'IN' and a.tax_code = t.code and t.status_cd = :statusCd ");
		}
		if (StringUtils.isNotEmpty((String) searchParamVO.getObj1())) {
			sb.append("and a.tax_code = :taxCode ");
		}
		// Group By
		if (type.equals("OUTPUT") || type.equals("INPUT")) {
			sb.append("GROUP BY a.tax_code, a.sys_cd, a.sys_no ");
		} else if (type.equals("OUTPUT_SMMRY") || type.equals("INPUT_SMMRY")) {
			sb.append("GROUP BY a.tax_code ");
		}
		//Order By
		if (type.equals("OUTPUT") || type.equals("INPUT")) {
			sb.append("ORDER BY a.tax_code, a.sys_cd, cast(a.sys_no as decimal) ");
		} else if (type.equals("OUTPUT_ALL") || type.equals("INPUT_ALL")) {
			sb.append("ORDER BY a.tax_code, a.sys_cd, cast(a.sys_no as decimal) ");
		} else if (type.equals("OUTPUT_SMMRY") || type.equals("INPUT_SMMRY")) {
			sb.append("ORDER BY a.tax_code ");
		}

		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("transDt", DateType.INSTANCE);
		query.addScalar("prefix");
		query.addScalar("sysNo");
		query.addScalar("sysCd");
		query.addScalar("source");
		query.addScalar("destination");
		query.addScalar("amount", DoubleType.INSTANCE);
		query.addScalar("taxCode");
		query.addScalar("taxDesc");
		query.addScalar("taxRate", FloatType.INSTANCE);
		query.addScalar("taxAmt", DoubleType.INSTANCE);
		query.addScalar("taxIncAmt", DoubleType.INSTANCE);
		query.addScalar("exCode");
		if (StringUtils.isNotEmpty((String) searchParamVO.getObj1())) {
			query.setParameter("taxCode", searchParamVO.getObj1());
		}
		query.setParameter("frDate", (Date) searchParamVO.getFromDate()); // Alway start from selected year 1st day
		query.setParameter("toDate", (Date) searchParamVO.getToDate());
		query.setParameter("bpDate", (Date) searchParamVO.getObj7());
		query.setParameter("idCompany", idCompany);
		query.setParameter("statusCd", BaseConstant.STATUS_ACTIVE);
		query.setResultTransformer(Transformers.aliasToBean(GSTReportVO.class));
		return query.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.db.bterp.dao.gst.GSTDAO#getGSTSmmryAcqInputTaxList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GSTReportVO> getGSTSmmryAcqInputTaxList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT ").
			append("t.code as taxCode, t.description as taxDesc, ").
			append("ifnull(a.amount, 0) as amount, ifnull(a.taxAmt,0) as taxamt, t.claimable, ").
			append("CASE WHEN t.code = 'AJP' THEN 'Adjustments' ELSE 'Standard Rate' END AS exCode ").
		append("FROM tax_code t LEFT JOIN ( ").
			append("SELECT ").
				append("ac.tax_code, ac.description, ac.tax_rate, ").
				append("SUM(CASE ").
				append("WHEN ac.sys_cd = 'purc_bill' THEN ").
					//append("ROUND(debit - credit, 2) ").
					append("CASE ").
						append("WHEN ROUND(ac.debit - ac.credit, 2) < 0.00 AND tc.sub_type = 'OUT' THEN ").
							append("IF(ROUND(ac.debit - ac.credit, 2) = 0.00, 0.00, ROUND(ac.debit - ac.credit, 2) * -1) ").
						append("ELSE ").
							append("ROUND(ac.debit - ac.credit, 2) ").
					append("END ").
				append("WHEN ac.sys_cd = 'bank_deps' THEN ").
					append("ROUND(credit - debit, 2) ").
				append("WHEN ac.sys_cd = 'crdt_note' THEN ").
					append("CASE WHEN tc.sub_type = 'OUT' THEN ").
						append("ROUND(credit - debit, 2) ").
					append("ELSE ").
						append("ROUND(debit - credit, 2) ").
					append("END ").
				append("WHEN ac.sys_cd = 'bank_pmnt' and DATE(ac.dt_trans) >= DATE(:bpDate) THEN ").
					append("ROUND(ac.debit - ac.credit, 2) ").
				append("ELSE ").
					append("CASE WHEN ac.credit != 0.00 THEN ac.credit ELSE ac.debit END ").
				append("END) AS amount, ").
				
				append("SUM(CASE WHEN (ac.tax_code = 'AJS' OR ac.tax_code = 'AJP') THEN ").
					append("CASE WHEN ac.debit != 0.00 THEN -1 * ac.tax_amount WHEN ac.credit != 0.00 THEN ac.tax_amount END ").
				append("ELSE ").
					append("CASE WHEN ac.sys_cd = 'purc_bill' THEN ").
						//append("IF(ROUND(debit - credit, 2) < 0.00, IF(tax_amount = 0.00, 0.00, tax_amount * -1), tax_amount) ").
						append("CASE ").
							append("WHEN ROUND(ac.debit - ac.credit, 2) < 0.00 AND tc.sub_type = 'OUT' THEN ").
								append("ac.tax_amount ").
							append("ELSE ").
								append("IF(ROUND(ac.debit - ac.credit, 2) < 0.00, IF(ac.tax_amount = 0.00, 0.00, ac.tax_amount * -1), ac.tax_amount) ").
						append("END ").
					append("WHEN ac.sys_cd = 'crdt_note' THEN ").
						append("CASE WHEN tc.sub_type = 'OUT' THEN ").
							append("IF(ROUND(ac.credit - ac.debit, 2) < 0.00, IF(ac.tax_amount = 0.00, 0.00, ac.tax_amount * -1), ac.tax_amount) ").
						append("ELSE ").
							append("IF(ROUND(ac.debit - ac.credit, 2) < 0.00, IF(ac.tax_amount = 0.00, 0.00, ac.tax_amount * -1), ac.tax_amount) ").
						append("END ").
					append("ELSE ").
						append("ac.tax_amount ").
					append("END ").
				append("END) AS taxAmt ").
		append("FROM account_trans ac LEFT JOIN tax_code tc ON tc.tax_type = 'GST' AND ac.tax_code = tc.code AND tc.status_cd = :statusCd ").
		append("WHERE ac.id_company= :idCompany AND ac.dt_trans BETWEEN date(:frDate) AND date(:toDate) AND ac.status_cd = :statusCd AND IFNULL(ac.tax_code, '') != '' ").
		append("GROUP BY ac.tax_code) a ON t.code = a.tax_code ").
		append("WHERE t.tax_type = 'GST' and t.sub_type = 'IN' and t.status_cd = :statusCd ").
		append("ORDER BY t.seq_no ");
			
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("taxCode");
		query.addScalar("taxDesc");
		query.addScalar("amount", DoubleType.INSTANCE);
		query.addScalar("taxAmt", DoubleType.INSTANCE);
		query.addScalar("claimable", IntegerType.INSTANCE);
		query.addScalar("exCode"); // type of 'Standard Rate' or 'Adjustments'
		query.setParameter("frDate", (Date) searchParamVO.getFromDate());
		query.setParameter("toDate", (Date) searchParamVO.getToDate());
		query.setParameter("bpDate", (Date) searchParamVO.getObj7());
		query.setParameter("idCompany", idCompany);
		query.setParameter("statusCd", BaseConstant.STATUS_ACTIVE);
		query.setResultTransformer(Transformers.aliasToBean(GSTReportVO.class));
		return query.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.db.bterp.dao.gst.GSTDAO#getGSTYearlyList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GSTReportVO> getGSTYearlyList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		SimpleDateFormat format = new SimpleDateFormat("MMM");
		Calendar calFr = Calendar.getInstance();
		Calendar calTo = Calendar.getInstance();
		calFr.setTime((Date) searchParamVO.getFromDate());
		calTo.setTime((Date) searchParamVO.getToDate());
		calFr.set(Calendar.DAY_OF_MONTH, 1);
		calTo.set(Calendar.DAY_OF_MONTH, calTo.getActualMaximum(Calendar.DATE));
		
		String strFr = "";
		String strTo = "";
		sb.append("SELECT t.seq_no, t.claimable, t.sub_type AS subType, a.tax_code AS taxCode, a.tax_rate AS taxRate ");
		for (int i = 0; i < 12; i++) {
			Date date = calFr.getTime();
			
			sb.append(",'" + format.format(date) + "\\' " + calFr.get(Calendar.YEAR)  + "' AS monthDesc" + (i + 1) + ", ");
			if (calFr.get(Calendar.MONTH) < 10) {
				strFr = calFr.get(Calendar.YEAR) + "0" + calFr.get(Calendar.MONTH);
			} else {
				strFr = calFr.get(Calendar.YEAR) + "" + calFr.get(Calendar.MONTH);
			}
			if (calTo.get(Calendar.MONTH) < 10) {
				strTo = calTo.get(Calendar.YEAR)  + "0" + calTo.get(Calendar.MONTH);
			} else {
				strTo = calTo.get(Calendar.YEAR) + "" + calTo.get(Calendar.MONTH);
			}
			if (Integer.parseInt(strFr) <= Integer.parseInt(strTo)) {
				sb.append("SUM(CASE WHEN YEAR(dt_trans) = '" + calFr.get(Calendar.YEAR) + "' AND MONTH(dt_trans) = '" + (calFr.get(Calendar.MONTH) + 1) + "' THEN ").
					append("CASE ").
					append("WHEN a.sys_cd = 'purc_bill' THEN ").
						//append("ROUND(a.debit - a.credit, 2) ").
						append("CASE ").
							append("WHEN ROUND(a.debit - a.credit, 2) < 0.00 AND t.sub_type = 'OUT' THEN ").
								append("IF(ROUND(a.debit - a.credit, 2) = 0.00, 0.00, ROUND(a.debit - a.credit, 2) * -1) ").
							append("ELSE ").
								append("ROUND(a.debit - a.credit, 2) ").
						append("END ").
					append("WHEN a.sys_cd = 'bank_deps' THEN ").
						append("ROUND(a.credit - a.debit, 2) ").
					append("WHEN a.sys_cd = 'crdt_note' THEN ").
						append("CASE WHEN t.sub_type = 'OUT' THEN ").
							append("ROUND(a.credit - a.debit, 2) ").
						append("ELSE ").
							append("ROUND(a.debit - a.credit, 2) ").
						append("END ").
					append("WHEN a.sys_cd = 'bank_pmnt' and DATE(a.dt_trans) >= DATE(:bpDate) THEN ").
						append("ROUND(a.debit - a.credit, 2) ").
					append("ELSE ").
						append("CASE WHEN a.credit != 0.00 THEN a.credit ELSE a.debit END ").
					append("END ").
				append("ELSE 0.00 END) AS amount" + (i + 1) + ", ").
				
				append("SUM(CASE WHEN YEAR(dt_trans) = '" + calFr.get(Calendar.YEAR) + "' AND MONTH(dt_trans) = '" + (calFr.get(Calendar.MONTH) + 1) + "' THEN ").
					append("CASE WHEN (a.tax_code = 'AJS' OR a.tax_code = 'AJP') THEN ").
						append("CASE WHEN a.debit != 0.00 THEN -1 * a.tax_amount WHEN a.credit != 0.00 THEN a.tax_amount END ").
					append("ELSE ").
						append("CASE WHEN a.sys_cd = 'purc_bill' THEN ").
							//append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
							append("CASE ").
								append("WHEN ROUND(a.debit - a.credit, 2) < 0.00 AND t.sub_type = 'OUT' THEN ").
									append("a.tax_amount ").
								append("ELSE ").
									append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
							append("END ").
						append("WHEN sys_cd = 'crdt_note' THEN ").
							append("CASE WHEN t.sub_type = 'OUT' THEN ").
								append("IF(ROUND(a.credit - a.debit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
							append("ELSE ").
								append("IF(ROUND(a.debit - a.credit, 2) < 0.00, IF(a.tax_amount = 0.00, 0.00, a.tax_amount * -1), a.tax_amount) ").
							append("END ").
						append("ELSE ").
							append("a.tax_amount ").
						append("END ").
					append("END ").
				append("ELSE 0.00 END) AS taxAmt" + (i + 1) + " ");
			} else {
				sb.append("0.00 AS amount" + (i + 1) + ", ").
				append("0.00 AS taxAmt" + (i + 1) + " ");
			}
			
			calFr.add(Calendar.MONTH, 1);
		}
				
		sb.append("FROM account_trans a, tax_code t ").
		append("WHERE a.id_company= :idCompany AND a.dt_trans BETWEEN date(:frDate) AND date(:toDate) AND a.status_cd = :statusCd AND IFNULL(a.tax_code, '') != '' ").
		append("and t.tax_type = 'GST' and a.tax_code = t.code and t.status_cd = :statusCd ").
		append("group by t.sub_type, a.tax_code ").
		append("order by FIELD(t.sub_type, 'OUT', 'IN'), t.seq_no ");
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("taxCode");
		query.addScalar("taxRate", FloatType.INSTANCE);
		query.addScalar("subType");
		query.addScalar("claimable", IntegerType.INSTANCE);
		query.addScalar("monthDesc1");
		query.addScalar("amount1", DoubleType.INSTANCE);
		query.addScalar("taxAmt1", DoubleType.INSTANCE);
		query.addScalar("monthDesc2");
		query.addScalar("amount2", DoubleType.INSTANCE);
		query.addScalar("taxAmt2", DoubleType.INSTANCE);
		query.addScalar("monthDesc3");
		query.addScalar("amount3", DoubleType.INSTANCE);
		query.addScalar("taxAmt3", DoubleType.INSTANCE);
		query.addScalar("monthDesc4");
		query.addScalar("amount4", DoubleType.INSTANCE);
		query.addScalar("taxAmt4", DoubleType.INSTANCE);
		query.addScalar("monthDesc5");
		query.addScalar("amount5", DoubleType.INSTANCE);
		query.addScalar("taxAmt5", DoubleType.INSTANCE);
		query.addScalar("monthDesc6");
		query.addScalar("amount6", DoubleType.INSTANCE);
		query.addScalar("taxAmt6", DoubleType.INSTANCE);
		query.addScalar("monthDesc7");
		query.addScalar("amount7", DoubleType.INSTANCE);
		query.addScalar("taxAmt7", DoubleType.INSTANCE);
		query.addScalar("monthDesc8");
		query.addScalar("amount8", DoubleType.INSTANCE);
		query.addScalar("taxAmt8", DoubleType.INSTANCE);
		query.addScalar("monthDesc9");
		query.addScalar("amount9", DoubleType.INSTANCE);
		query.addScalar("taxAmt9", DoubleType.INSTANCE);
		query.addScalar("monthDesc10");
		query.addScalar("amount10", DoubleType.INSTANCE);
		query.addScalar("taxAmt10", DoubleType.INSTANCE);
		query.addScalar("monthDesc11");
		query.addScalar("amount11", DoubleType.INSTANCE);
		query.addScalar("taxAmt11", DoubleType.INSTANCE);
		query.addScalar("monthDesc12");
		query.addScalar("amount12", DoubleType.INSTANCE);
		query.addScalar("taxAmt12", DoubleType.INSTANCE);
		query.setParameter("frDate", (Date) searchParamVO.getFromDate());
		query.setParameter("toDate", (Date) searchParamVO.getToDate());
		query.setParameter("bpDate", (Date) searchParamVO.getObj7());
		query.setParameter("idCompany", idCompany);
		query.setParameter("statusCd", BaseConstant.STATUS_ACTIVE);
		query.setResultTransformer(Transformers.aliasToBean(GSTReportVO.class));
		return query.list();
	}
	
	@Override
	public double getTaxCodeTotal(Map<String, Object> params) throws BusinessException {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT IFNULL(SUM(IFNULL(amount, 0.00)), 0.00) AS amount ")
			.append("FROM ( ")
			.append("	SELECT CASE WHEN credit != 0.00 THEN credit ELSE debit END AS amount ")
			.append("	FROM account_trans acctTran ")
			.append("	WHERE acctTran.id_company = :idCompany ")
			.append("		AND acctTran.dt_trans BETWEEN date(:dateFrom) AND date(:dateTo) ")
			.append("		AND acctTran.status_cd = :statusCd ")
			.append("		AND acctTran.id_acct IN ( ")
			.append("			SELECT id FROM account acct ")
			.append("			WHERE acct.id_company = acctTran.id_company AND status_cd = :statusCd ")
			.append("				AND acct.id_acct_sub_cat =  ")
			.append("					(SELECT DISTINCT id FROM account_sub_cat acctSC ")
			.append("					WHERE acct.id_company = acctSC.id_company ")
			.append("						AND acctSC.code = :accountType AND status_cd = :statusCd) ")
			.append("					) ")
			.append(" ) A ");
			
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		
		query.setParameter("idCompany", params.get("idCompany"));
		query.setParameter("dateFrom", params.get("dateFrom"));
		query.setParameter("dateTo", params.get("dateTo"));
		query.setParameter("accountType", params.get("accountType"));
		query.setParameter("statusCd", params.get("statusCode"));
		
		query.addScalar("amount", DoubleType.INSTANCE);
		
		return (double) query.uniqueResult();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.db.bterp.dao.gst.GSTDAO#getSupplyList(java.lang.Long, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GSTGAFVO> getSupplyList(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat cdf = new SimpleDateFormat("yyyy-MM-dd");
		
		sb.append("select ").
			append("'S' as recordId, ").
			append("case when c.pc_type_cd = 'C' then o.name when c.pc_type_cd = 'P' then CONCAT_WS(' ', p.salutation_cd, p.last_name, p.first_name) end as custName, ").
			append("'' as custBRN, iv.dt_inv as invoiceDt, iv.code as invoiceNo, '' as expDecNo, ").
			append("concat(@row_num /*'*/:=/*'*/ IF(@prev_value=iv.code, @row_num + 1, 1), '') AS lineNo, ").
			append("ii.description, (ii.amount + ii.tax_amount) as amount, ii.tax_amount as taxAmount, ii.tax_code as taxCode, ").
			append("'MALAYSIA' as country, 'MYR' as curCode, 0.00 as curAmount, 0.00 as curTaxAmount, ").
			append("@prev_value /*'*/:=/*'*/ iv.code ").
		append("from invoice iv ").
			append("inner join customer c on c.id = iv.id_customer left join person p on (c.pc_type_cd = 'P' and c.id_pc = p.id) ").
			append("left join corporate o on (c.pc_type_cd = 'C' AND c.id_corporate = o.id ) ").
			append("left join person p2 on (c.pc_type_cd = 'C' and o.id_person = p2.id), ").
			append("invoice_item ii, ").
			append("(select @row_num /*'*/:=/*'*/ 1) x, ").
			append("(select @prev_value /*'*/:=/*'*/ '') y ").
		append("where iv.id_company = :idCompany and iv.doc_type_cd = 'I' and ii.status_cd = :statusCd ").
			append("and iv.id = ii.id_inv ").
			append("and iv.status_cd != 'VD' ").
			append("and date(iv.dt_inv) >= date('" + cdf.format(dateFrom) + "') ").
			append("and date(iv.dt_inv) <= date('" + cdf.format(dateTo) + "') ").
		append("order by cast(iv.code as decimal) ");
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("recordId");
		query.addScalar("custName");
		query.addScalar("custBRN");
		query.addScalar("invoiceDt", DateType.INSTANCE);
		query.addScalar("invoiceNo");
		query.addScalar("expDecNo");
		query.addScalar("lineNo");
		query.addScalar("description");
		query.addScalar("amount", DoubleType.INSTANCE);
		query.addScalar("taxAmount", DoubleType.INSTANCE);
		query.addScalar("taxCode");
		query.addScalar("country");
		query.addScalar("curCode");
		query.addScalar("curAmount", DoubleType.INSTANCE);
		query.addScalar("curTaxAmount", DoubleType.INSTANCE);
		query.setParameter("idCompany", idCompany);
		query.setParameter("statusCd", BaseConstant.STATUS_ACTIVE);
		query.setResultTransformer(Transformers.aliasToBean(GSTGAFVO.class));
		return query.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.db.bterp.dao.gst.GSTDAO#getPurchasesList(java.lang.Long, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GSTGAFVO> getPurchasesList(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat cdf = new SimpleDateFormat("yyyy-MM-dd");
		
		sb.append("select ").
			append("'P' as recordId, c.name as supplierName, '' as supplierBRN, '' as supplierGSTNo, b.dt_bill as invoiceDt, b.dt_bill as postingDt, b.code as invoiceNo, '' as impDecNo, ").
			append("concat(@row_num /*'*/:=/*'*/ IF(@prev_value=b.code, @row_num + 1, 1), '') AS lineNo, ").
			append("a.description, a.amount as amount, a.tax_amount as taxAmount, a.tax_code as taxCode, ").
			append("'MYR' as curCode, 0.00 as curAmount, 0.00 as curTaxAmount, ").
			append("@prev_value /*'*/:=/*'*/ b.code ").
			append("FROM ex_order_bill b ").
				append("left join supplier s on s.id = b.id_supplier ").
				append("left join person p on p.id = s.id_person ").
				append("left join corporate c on c.id_person = p.id, ").
			append("(select t.sys_no, if(ifnull(ac.sub_code, '') != '', concat(ac.description, ', ', ac.sub_description), ac.description) as description, ").
				append("if(t.debit > 0, t.debit, if(t.credit > 0, -t.credit, t.credit)) + t.tax_amount as amount, ").
				append("t.tax_amount, t.tax_code ").
			append("from account_trans t ").
			append("left join account ac on ac.id_company = :idCompany and t.id_acct = ac.id ").
			append("where t.id_company = :idCompany and t.sys_cd = 'purc_bill' and t.id_ref is not null ").
			append("order by t.id desc) a, ").
			append("(select @row_num /*'*/:=/*'*/ 1) x, ").
			append("(select @prev_value /*'*/:=/*'*/ '') y ").
		append("where ").
			append("b.id_company = :idCompany and b.code = a.sys_no and b.status_cd not in ('CC', 'VD') ").
			append("and date(b.dt_bill) >= date('" + cdf.format(dateFrom) + "') ").
			append("and date(b.dt_bill) <= date('" + cdf.format(dateTo) + "') ").
		append("order by cast(b.code as decimal)");
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("recordId");
		query.addScalar("supplierName");
		query.addScalar("supplierBRN");
		query.addScalar("supplierGSTNo");
		query.addScalar("invoiceDt", DateType.INSTANCE);
		query.addScalar("postingDt", DateType.INSTANCE);
		query.addScalar("invoiceNo");
		query.addScalar("impDecNo");
		query.addScalar("lineNo");
		query.addScalar("description");
		query.addScalar("amount", DoubleType.INSTANCE);
		query.addScalar("taxAmount", DoubleType.INSTANCE);
		query.addScalar("taxCode");
		query.addScalar("curCode");
		query.addScalar("curAmount", DoubleType.INSTANCE);
		query.addScalar("curTaxAmount", DoubleType.INSTANCE);
		query.setParameter("idCompany", idCompany);
		query.setResultTransformer(Transformers.aliasToBean(GSTGAFVO.class));
		return query.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.db.bterp.dao.gst.GSTDAO#getLedgerList(java.lang.Long, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GSTGAFVO> getLedgerList(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat cdf = new SimpleDateFormat("yyyy-MM-dd");
		
		sb.append("select t.id, t.id_acct, 'L' as recordId, t.dt_trans as transDt, ").
			append("if(ifnull(a.sub_code, '') != '', concat(a.code, '-', a.sub_code), a.code) as acctCd, ").
			append("case ").
				append("when ac.code in ('I', 'X', 'P', 'OI') then 'PL' ").
				append("when ac.code in ('A', 'L', 'E') then 'BS' ").
				append("else '' ").
			append("end as acctType, ").
			append("if(ifnull(a.sub_code, '') != '', concat(a.description, ', ', a.sub_description), a.description) as acctDesc, ").
			append("t.description as transDesc, '' as entityName, concat(t.sys_cd, '-', t.sys_no) as transId, ").
			append("t.sys_no as sourceDocId, t.sys_cd as sourceType, t.debit, t.credit, 0.00 as balance ").
		append("from account_trans t ").
			append("left join account a on a.id_company = :idCompany and t.id_acct = a.id ").
			append("left join account_cat ac on ac.id = a.id_acct_cat ").
		append("where t.id_company = 1 and t.status_cd = :statusCd ").
		append("and date(t.dt_trans) >= date('" + cdf.format(dateFrom) + "') ").
		append("and date(t.dt_trans) <= date('" + cdf.format(dateTo) + "') ").
		append("order by cast(a.code as decimal), cast(a.sub_code as decimal), t.dt_trans");

		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("recordId");
		query.addScalar("transDt", DateType.INSTANCE);
		query.addScalar("acctCd");
		query.addScalar("acctType");
		query.addScalar("acctDesc");
		query.addScalar("transDesc");
		query.addScalar("entityName");
		query.addScalar("transId");
		query.addScalar("sourceDocId");
		query.addScalar("sourceType");
		query.addScalar("debit", DoubleType.INSTANCE);
		query.addScalar("credit", DoubleType.INSTANCE);
		query.addScalar("balance", DoubleType.INSTANCE);
		query.setParameter("idCompany", idCompany);
		query.setParameter("statusCd", BaseConstant.STATUS_ACTIVE);
		query.setResultTransformer(Transformers.aliasToBean(GSTGAFVO.class));
		return query.list();
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.db.bterp.dao.gst.GSTDAO#getGSTMappingStateList(java.util.Date)
	 */
	@Override
	public GSTMappingStateVO getGSTMappingState(Date dateTo, String gstField) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT dt_mapping AS mappingDt, gst_field AS GSTField, description, tax_code AS taxCode ").
			append("FROM tax_code_mapping_state ").
			append("WHERE DATE(dt_mapping) <= DATE(:dateTo) AND status_cd = :statusCd ").
			append("AND dt_mapping = (SELECT max(dt_mapping) FROM tax_code_mapping_state WHERE DATE(dt_mapping) <= DATE(:dateTo)) ").
			append("AND gst_field = :gstField ");
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.setParameter("dateTo", dateTo);
		query.setParameter("gstField", gstField);
		query.setParameter("statusCd", BaseConstant.STATUS_ACTIVE);
		
		Object[] result = (Object[]) query.uniqueResult();
		GSTMappingStateVO gstMappingStateVO = new GSTMappingStateVO();
		gstMappingStateVO.setMappingDt((Date) result[0]);
		gstMappingStateVO.setGSTField((String) result[1]);
		gstMappingStateVO.setDescription((String) result[2]);
		gstMappingStateVO.setTaxCode((String) result[3]);
		
		return gstMappingStateVO;
	}
}
