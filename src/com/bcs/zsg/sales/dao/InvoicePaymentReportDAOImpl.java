package com.bcs.zsg.sales.dao;

import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.AppSettingBO;
import com.bcs.zsg.maintenance.helper.ConstantAppSetting;
import com.bcs.zsg.maintenance.vo.AppSettingVO;
import com.bcs.zsg.sales.vo.InvoicePaymentReportVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class InvoicePaymentReportDAOImpl extends BaseHibernateDAO implements InvoicePaymentReportDAO{
	
	@Autowired
	private AppSettingBO appSettingBO;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceVO> getInvoicePaymentReportList(Long companyId, Map<String, Object> params) throws BusinessException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		AppSettingVO appSettingVO = appSettingBO.getAppSettingByCode(ConstantAppSetting.MAINT_POS_CONFIG_GL_ACCT_TO_EXCLUDE);
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id, invoiceDt, code, departureDt, tourCd, customerId, typeCd, custSalutation, custName, coName, salerId, salerName, deptCode, deptDesc ");
		sb.append(", idTourCat, tourCatDesc, country, tourPkgName ");
		if (appSettingVO != null && StringUtils.isNotBlank(appSettingVO.getValue())) {
			sb.append(", IF(doc_type_cd = 'C', IF (reference_iv_amt != IF(count_acct_not_exclude > 0, amount - amt_dep, 0.00), 0, -1) * paxCount, paxCount) AS paxCount ");
			sb.append(", IF(count_acct_not_exclude > 0, amount - amt_dep, 0.00) * IF(doc_type_cd = 'C', -1, 1) AS amount ");
			sb.append(", IF(count_acct_not_exclude > 0, amtPaid - amt_dep, 0.00) * IF(doc_type_cd = 'C', -1, 1) AS amtPaid ");
			sb.append(", CASE WHEN count_acct_not_exclude > 0 THEN balance ELSE 0.00 END AS balance ");
		} else {
			sb.append(", IF(doc_type_cd = 'C', IF (reference_iv_amt != amount, 0, -1) * paxCount, paxCount) AS paxCount ");
			sb.append(", amount * IF(doc_type_cd = 'C', -1, 1) AS amount ");
			sb.append(", amtPaid * IF(doc_type_cd = 'C', -1, 1) AS amtPaid ");
			sb.append(", balance ");
		}
		sb.append(", paymentDt, paymentType, refNo, payFor, paymentAmount, issuerName, cashBookCd, cbPrefix, psNo, psDt ");
		sb.append("FROM ( ");
			sb.append("SELECT ");
			sb.append("iv.id, iv.dt_inv AS invoiceDt, concat(if(iv.doc_type_cd = 'C', 'CN', ''), iv.code) AS code, iv.dt_departure AS departureDt, td.code AS tourCd, ");
			sb.append("iv.amount, 0 AS cn_iv_amount, ");
			sb.append("(select case when sum(pmnt.amount) is null then 0 else sum(pmnt.amount) end from invoice_pmnt pmnt ");
			sb.append("where pmnt.id_inv = iv.id and pmnt.status_cd = 'A') as 'amtPaid', ");
			sb.append("iv.balance as balance, ");
			sb.append("iv.id_customer AS customerId, c.pc_type_cd as typeCd, ps.salutation_cd as custSalutation, concat(ps.last_name, ' ', ps.first_name) AS custName, c.corporate_name as coName, ");
			sb.append("iv.id_saler as salerId, usr.user_name as salerName, 0 AS reference_iv_amt, IFNULL(iv_pax.pax_count, 0) AS paxCount, ");
			sb.append("ep.department AS deptCode, li.description AS deptDesc ");
			sb.append(", tc.id AS idTourCat, tc.description AS tourCatDesc, ctry.name as country, CONCAT(tp.name_en, ' ', tp.name_zh, ' ', IFNULL(tp.name_other, '')) as tourPkgName ");
			if (appSettingVO != null && StringUtils.isNotBlank(appSettingVO.getValue())) {
				sb.append(", iv.doc_type_cd ");
				sb.append(", IFNULL((SELECT SUM(amount + tax_amount) AS amount FROM invoice_item inv_item WHERE inv_item.id_acct IN :excludeAcct AND inv_item.status_cd = 'A' AND inv_item.id_inv = iv.id), 0) AS amt_dep ");
				sb.append(", (SELECT COUNT(id) FROM invoice_item inv_item WHERE inv_item.id_acct NOT IN :excludeAcct AND inv_item.status_cd = 'A' AND inv_item.id_inv = iv.id) AS count_acct_not_exclude ");
			}
			sb.append(", ip.dt_pmnt as paymentDt ");
			sb.append(", ip.pmnt_type_cd as paymentType ");
			sb.append(", ip.ref_no as refNo ");
			sb.append(", ip.pmnt_for as payFor ");
			sb.append(", ip.amount as paymentAmount ");
			sb.append(", usr2.user_name AS issuerName ");
			sb.append(", IFNULL(cb.sys_no, '') as cashBookCd ");
			sb.append(", IFNULL(cb.sys_prefix, '') as cbPrefix ");
			sb.append(", ivps.ps_no as psNo, ivps.dt_inv as psDt ");
			
			sb.append("FROM invoice iv ");
			sb.append("LEFT JOIN tour_dep td ON td.id = iv.id_tour_dep ");
			sb.append("LEFT JOIN tour_pkg tp ON tp.id = td.id_tour_pkg ");
			sb.append("LEFT JOIN airline arl on td.id_airline = arl.id ");
			sb.append("LEFT JOIN tour_theme tt ON tt.id = tp.id_tour_theme AND tt.status_cd = 'AC' ");
			sb.append("LEFT JOIN country ctry ON tt.id_country = ctry.id ");
			sb.append("LEFT JOIN tour_cat tc ON tc.id = tt.id_tour_cat AND tc.status_cd = 'AC' ");
			sb.append("LEFT JOIN customer c ON c.id = iv.id_customer ");
			sb.append("LEFT JOIN person ps ON ps.id = c.id_pc ");
			sb.append("LEFT JOIN employee ep ON ep.id = iv.id_saler ");
			sb.append("LEFT JOIN sec_user usr ON usr.uuid = ep.u_sec_user ");
			sb.append("LEFT JOIN lookup_item li ON li.lookup_cat_cd = 'dept_type' AND li.code = ep.department ");
			sb.append("LEFT JOIN (SELECT COUNT(id) AS pax_count, id_inv FROM invoice_pax WHERE status_cd = 'A' GROUP BY id_inv) iv_pax ON iv_pax.id_inv = iv.id AND IF(iv.cat_cd = 'to', (CASE WHEN iv.id_tour_booking IS NOT NULL THEN true ELSE false END), true)");
//			sb.append("INNER JOIN invoice_pmnt iv_pmnt ON iv.id = iv_pmnt.id_inv and iv_pmnt.status_cd = 'A' ");
//			sb.append("LEFT JOIN inv_pmnt_cashbook_link link ON iv_pmnt.id = link.id_inv_pmnt and link.status_cd = 'A' ");
//			sb.append("LEFT JOIN cash_book cb ON cb.id = link.id_cash_book and cb.status_cd = 'A' ");
			
			sb.append("INNER JOIN invoice_pmnt ip ON iv.id = ip.id_inv AND ip.status_cd = 'A' ");
			sb.append("LEFT JOIN invoice_pmnt parent ON ip.id_parent_pmnt IS NOT NULL AND ip.id_parent_pmnt = parent.id ");
			sb.append("LEFT JOIN inv_pmnt_cashbook_link link on link.status_cd = 'A' and  ");
				sb.append("(CASE WHEN parent.id_parent_pmnt IS NOT NULL THEN parent.id_parent_pmnt WHEN ip.id_parent_pmnt IS NOT NULL THEN ip.id_parent_pmnt ELSE ip.id END) = link.id_inv_pmnt ");
			sb.append("LEFT JOIN cash_book cb ON cb.status_cd = 'A' AND link.id_cash_book = cb.id ");
			sb.append("LEFT JOIN (SELECT ps_no, dt_inv FROM invoice WHERE id_company = :idCompany AND doc_type_cd = 'P') ivps on iv.ps_no is not null and iv.ps_no = ivps.ps_no ");
			
			sb.append("LEFT JOIN employee ep2 ON ep2.id = ip.id_issuer ");
			sb.append("LEFT JOIN sec_user usr2 ON usr2.uuid = ep2.u_sec_user ");
			sb.append("WHERE iv.id_company = :idCompany AND iv.status_cd != :statusCdCC AND iv.status_cd != :statusCdVD ");
			sb.append("AND (iv.doc_type_cd = 'I' OR (iv.doc_type_cd = 'P' AND IFNULL(iv.doc_type_status, '') NOT IN ('CV','SP','CL'))) ");
			if (params.get("salesPerson") != null) sb.append("AND iv.id_saler = :salesPerson ");
			if (params.get("idRegionList") != null) 
				sb.append("AND tt.id_tour_cat IN :idRegionList ");
			if	(params.get("idCountryList") != null)
				sb.append("AND tt.id_country IN :idCountryList ");
			if (params.get("dateFrom") != null && params.get("dateTo") != null)
				sb.append("AND iv.dt_inv BETWEEN DATE('" + sdf.format((Date) params.get("dateFrom")) + "') AND DATE('" + sdf.format((Date) params.get("dateTo")) + "') ");
			if (params.get("paymentDateFrom") != null && params.get("paymentDateTo") != null)
				sb.append("AND IF(cb.dt_trans IS NOT NULL, cb.dt_trans, ip.dt_pmnt) BETWEEN DATE('" + sdf.format((Date) params.get("paymentDateFrom")) + "') AND DATE('" + sdf.format((Date) params.get("paymentDateTo")) + "') ");
			
		sb.append(" ) a ");
		sb.append("ORDER BY code");
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("id", LongType.INSTANCE);
		query.addScalar("invoiceDt", DateType.INSTANCE);
		query.addScalar("code");
		query.addScalar("departureDt", DateType.INSTANCE);
		query.addScalar("tourCd");
		query.addScalar("amount", DoubleType.INSTANCE);
		query.addScalar("amtPaid", DoubleType.INSTANCE);
		query.addScalar("balance", DoubleType.INSTANCE);
		query.addScalar("customerId", LongType.INSTANCE);
		query.addScalar("typeCd");
		query.addScalar("custSalutation");
		query.addScalar("custName");
		query.addScalar("coName");
		query.addScalar("salerId", LongType.INSTANCE);
		query.addScalar("salerName");
		query.addScalar("paxCount", IntegerType.INSTANCE);
		query.addScalar("deptCode");
		query.addScalar("deptDesc");
		query.addScalar("idTourCat", LongType.INSTANCE);
		query.addScalar("tourCatDesc");
		query.addScalar("country");
		query.addScalar("tourPkgName");
		
		query.addScalar("paymentDt", DateType.INSTANCE);
		query.addScalar("paymentType");
		query.addScalar("refNo");
		query.addScalar("payFor");
		query.addScalar("paymentAmount", DoubleType.INSTANCE);
		query.addScalar("issuerName");
		query.addScalar("cashBookCd");
		query.addScalar("cbPrefix");
		query.addScalar("psNo");
		query.addScalar("psDt", DateType.INSTANCE);
		
		query.setParameter("idCompany", params.get("idCompany"));
		query.setParameter("statusCdCC", CommonConstant.STATUS_CD_CANCELLED);
		query.setParameter("statusCdVD", CommonConstant.STATUS_CD_VOID);
		if (params.get("salesPerson") != null) query.setParameter("salesPerson", (String) params.get("salesPerson"));
		if (params.get("idRegionList") != null) query.setParameterList("idRegionList", (List<String>) params.get("idRegionList"));
		if (params.get("idCountryList") != null) query.setParameterList("idCountryList", (List<String>) params.get("idCountryList"));
		if (appSettingVO != null && StringUtils.isNotBlank(appSettingVO.getValue())) {
			List<String> excludeAcctList = Arrays.asList(appSettingVO.getValue().split(","));
			query.setParameterList("excludeAcct", excludeAcctList);
		}
		query.setResultTransformer(Transformers.aliasToBean(InvoicePaymentReportVO.class));
		
		
		return query.list();
		
	}
}
