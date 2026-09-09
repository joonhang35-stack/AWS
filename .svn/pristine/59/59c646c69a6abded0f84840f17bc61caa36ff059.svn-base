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
import com.bcs.zsg.sales.vo.CounterSalesReportVO;

public class CounterSalesReportDAOImpl extends BaseHibernateDAO implements CounterSalesReportDAO {
	
	@Autowired
	private AppSettingBO appSettingBO;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CounterSalesReportVO> getCounterSalesReportList(Map<String, Object> params) throws BusinessException {
		
		AppSettingVO appSettingVO = appSettingBO.getAppSettingByCode(ConstantAppSetting.MAINT_POS_CONFIG_GL_ACCT_TO_EXCLUDE);
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id, invoiceDt, code, departureDt, tourCd, customerId, typeCd, custSalutation, custName, coName, salerId, salerName, deptCode, deptDesc ");
		if (params.get("orderByRegion") != null) sb.append(", idTourCat, tourCatDesc ");
		sb.append(", IF(doc_type_cd = 'C', IF(id_tour_booking IS NOT NULL, paxCount, IF(raw_reference_iv_amt != raw_iv_amt, 0, -1) * paxCount), paxCount) AS paxCount ");
		if (appSettingVO != null && StringUtils.isNotBlank(appSettingVO.getValue())) {
			sb.append(", IF(count_acct_not_exclude > 0, amount - amt_dep, 0.00) * IF(doc_type_cd = 'C', -1, 1) AS amount ");
			sb.append(", IF(count_acct_not_exclude > 0, amtPaid - amt_dep, 0.00) * IF(doc_type_cd = 'C', -1, 1) AS amtPaid ");
			sb.append(", CASE WHEN count_acct_not_exclude > 0 THEN balance ELSE 0.00 END AS balance ");
		} else {
			sb.append(", amount * IF(doc_type_cd = 'C', -1, 1) AS amount ");
			sb.append(", amtPaid * IF(doc_type_cd = 'C', -1, 1) AS amtPaid ");
			sb.append(", balance ");
		}
		sb.append("FROM ( ");
			sb.append("SELECT ");
			sb.append("iv.id, iv.dt_inv AS invoiceDt, concat(if(iv.doc_type_cd = 'C', 'CN', ''), iv.code) AS code, iv.dt_departure AS departureDt, td.code AS tourCd, ");
			sb.append("iv.amount, 0 AS cn_iv_amount, ");
			sb.append("(select case when sum(pmnt.amount) is null then 0 else sum(pmnt.amount) end from invoice_pmnt pmnt ");
			sb.append("where pmnt.id_inv = iv.id and pmnt.status_cd = 'A') as 'amtPaid', ");
			sb.append("iv.balance as balance, ");
			sb.append("iv.id_customer AS customerId, c.pc_type_cd as typeCd, ps.salutation_cd as custSalutation, concat(ps.last_name, ' ', ps.first_name) AS custName, c.corporate_name as coName, ");
			sb.append("iv.id_saler as salerId, usr.user_name as salerName, iv.id_tour_booking, 0 AS raw_reference_iv_amt, 0 AS raw_iv_amt, ");
			sb.append("(CASE WHEN iv.cat_cd = 'to' THEN IFNULL(bk_pax.quantity, 0) ELSE IFNULL(iv_pax.pax_count, 0) END) AS paxCount, ");
			sb.append("ep.department AS deptCode, li.description AS deptDesc ");
			if (params.get("orderByRegion") != null) sb.append(", tc.id AS idTourCat, tc.description AS tourCatDesc ");
			sb.append(", iv.doc_type_cd ");
			if (appSettingVO != null && StringUtils.isNotBlank(appSettingVO.getValue())) {
				sb.append(", IFNULL((SELECT SUM(amount + tax_amount) AS amount FROM invoice_item inv_item WHERE inv_item.id_acct IN :excludeAcct AND inv_item.status_cd = 'A' AND inv_item.id_inv = iv.id), 0) AS amt_dep ");
				sb.append(", (SELECT COUNT(id) FROM invoice_item inv_item WHERE inv_item.id_acct NOT IN :excludeAcct AND inv_item.status_cd = 'A' AND inv_item.id_inv = iv.id) AS count_acct_not_exclude ");
			}
			sb.append(", null AS cn_inv_no ");
			sb.append("FROM invoice iv ");
			sb.append("LEFT JOIN tour_dep td ON td.id = iv.id_tour_dep ");
			sb.append("LEFT JOIN tour_pkg tp ON tp.id = td.id_tour_pkg ");
			if (params.get("orderByRegion") != null) {
				sb.append("LEFT JOIN tour_theme tt ON tt.id = tp.id_tour_theme AND tt.status_cd = 'AC' ");
				sb.append("LEFT JOIN tour_cat tc ON tc.id = tt.id_tour_cat AND tc.status_cd = 'AC' ");
			}
			sb.append("LEFT JOIN customer c ON c.id = iv.id_customer ");
			sb.append("LEFT JOIN person ps ON ps.id = c.id_pc ");
			sb.append("LEFT JOIN employee ep ON ep.id = iv.id_saler ");
			sb.append("LEFT JOIN sec_user usr ON usr.uuid = ep.u_sec_user ");
			sb.append("LEFT JOIN lookup_item li ON li.lookup_cat_cd = 'dept_type' AND li.code = ep.department ");
			sb.append("LEFT JOIN (SELECT COUNT(id) AS pax_count, id_inv FROM invoice_pax WHERE status_cd = 'A' GROUP BY id_inv) iv_pax ON iv_pax.id_inv = iv.id AND IF(iv.cat_cd = 'to', (CASE WHEN iv.id_tour_booking IS NOT NULL THEN true ELSE false END), true)");
			sb.append("LEFT JOIN ( ");
				sb.append("SELECT tb.id, tb.id_company, tb.`quantity` - ifnull(tbci.quantity, 0) AS quantity ");
				sb.append("FROM tour_booking tb LEFT JOIN tour_booking_charge_item tbci ON tb.id = tbci.id_tour_booking AND tbci.code = 'FT_INFT' ");
			sb.append(") bk_pax ON iv.id_tour_booking = bk_pax.id AND iv.id_company = bk_pax.id_company ");
			sb.append("WHERE iv.status_cd != :statusCdCC AND iv.status_cd != :statusCdVD AND iv.doc_type_cd = 'I' ");
			
			processFiltering(params, sb);
			
			sb.append(" UNION ");
			
			sb.append("SELECT ");
			sb.append("iv.id, iv.dt_inv AS invoiceDt, concat(if(iv.doc_type_cd = 'C', 'CN', ''), iv.code) AS code, iv.dt_departure AS departureDt, td.code AS tourCd, ");
			sb.append("iv.amount, cn_iv.amount AS cn_iv_amount, ");
			sb.append("(select case when sum(pmnt.amount) is null then 0 else sum(pmnt.amount) end from invoice_pmnt pmnt ");
			sb.append("where pmnt.id_inv = iv.id and pmnt.status_cd = 'A') as 'amtPaid', ");
			sb.append("iv.balance as balance, ");
			sb.append("iv.id_customer AS customerId, c.pc_type_cd as typeCd, ps.salutation_cd as custSalutation, concat(ps.last_name, ' ', ps.first_name) AS custName, c.corporate_name as coName, ");
			sb.append("iv.id_saler as salerId, usr.user_name as salerName, ");
			sb.append("cn_iv.id_tour_booking, ");
			sb.append("IFNULL((SELECT SUM(amount + tax_amount) AS amount FROM invoice_item inv_item WHERE inv_item.amount >= 0 AND inv_item.status_cd = 'A' AND inv_item.id_inv = cn_iv.id), 0) AS raw_reference_iv_amt, ");
			sb.append("IFNULL((SELECT SUM(amount + tax_amount) AS amount FROM invoice_item inv_item WHERE inv_item.amount >= 0 AND inv_item.status_cd = 'A' AND inv_item.id_inv = iv.id), 0) AS raw_iv_amt, ");
			sb.append("(IFNULL((CASE WHEN cn_iv.cat_cd = 'to' THEN bk_pax.quantity ELSE iv_pax.pax_count END), 0) * ");
				sb.append("IF((SELECT id_credit_note FROM invoice_pmnt pmnt WHERE pmnt.id_inv = cn_iv.id AND pmnt.status_cd = 'A' AND pmnt_type_cd = 'credit_note' ORDER BY id DESC LIMIT 1) = iv.id, ");
				//sb.append("IF(tb.status_cd = 'CC' || tb.status_cd = 'VD', -1, 0), 0) ");
				sb.append("-1, 0) ");
			sb.append(") AS paxCount, ");
			sb.append("ep.department AS deptCode, li.description AS deptDesc ");
			if (params.get("orderByRegion") != null) sb.append(", tc.id AS idTourCat, tc.description AS tourCatDesc ");
			sb.append(", iv.doc_type_cd ");
			if (appSettingVO != null && StringUtils.isNotBlank(appSettingVO.getValue())) {
				sb.append(", IFNULL((SELECT SUM(amount + tax_amount) AS amount FROM invoice_item inv_item WHERE inv_item.id_acct IN :excludeAcct AND inv_item.status_cd = 'A' AND inv_item.id_inv = iv.id), 0) AS amt_dep ");
				sb.append(", (SELECT COUNT(id) FROM invoice_item inv_item WHERE inv_item.id_acct NOT IN :excludeAcct AND inv_item.status_cd = 'A' AND inv_item.id_inv = iv.id) AS count_acct_not_exclude ");
			}
			sb.append(", iv.cn_inv_no ");
			sb.append("FROM invoice iv ");
			sb.append("INNER JOIN invoice cn_iv ON cn_iv.code = iv.cn_inv_no AND cn_iv.doc_type_cd = 'I' ");
			sb.append("LEFT JOIN tour_booking tb ON tb.id = cn_iv.id_tour_booking ");
			sb.append("LEFT JOIN tour_dep td ON td.id = cn_iv.id_tour_dep ");
			sb.append("LEFT JOIN tour_pkg tp ON tp.id = td.id_tour_pkg ");
			if (params.get("orderByRegion") != null) {
				sb.append("LEFT JOIN tour_theme tt ON tt.id = tp.id_tour_theme AND tt.status_cd = 'AC' ");
				sb.append("LEFT JOIN tour_cat tc ON tc.id = tt.id_tour_cat AND tc.status_cd = 'AC' ");
			}
			sb.append("LEFT JOIN customer c ON c.id = iv.id_customer ");
			sb.append("LEFT JOIN person ps ON ps.id = c.id_pc ");
			sb.append("LEFT JOIN employee ep ON ep.id = iv.id_saler ");
			sb.append("LEFT JOIN sec_user usr ON usr.uuid = ep.u_sec_user ");
			sb.append("LEFT JOIN lookup_item li ON li.lookup_cat_cd = 'dept_type' AND li.code = ep.department ");
			sb.append("LEFT JOIN (SELECT COUNT(id) AS pax_count, id_inv FROM invoice_pax WHERE status_cd = 'A' GROUP BY id_inv) iv_pax ON iv_pax.id_inv = cn_iv.id AND IF(cn_iv.cat_cd = 'to', (CASE WHEN cn_iv.id_tour_booking IS NOT NULL THEN true ELSE false END), true)");
			sb.append("LEFT JOIN ( ");
				sb.append("SELECT tb.id, tb.id_company, tb.`quantity` - ifnull(tbci.quantity, 0) AS quantity ");
				sb.append("FROM tour_booking tb LEFT JOIN tour_booking_charge_item tbci ON tb.id = tbci.id_tour_booking AND tbci.code = 'FT_INFT' ");
			sb.append(") bk_pax ON cn_iv.id_tour_booking = bk_pax.id AND cn_iv.id_company = bk_pax.id_company ");
			sb.append("WHERE iv.status_cd != :statusCdCC AND iv.status_cd != :statusCdVD AND iv.doc_type_cd = 'C' ");
			processFiltering(params, sb);
			
		sb.append(" ) a ");
		
		// Sorting
		if (params.get("orderByRegion") != null) {
			sb.append("ORDER BY idTourCat, invoiceDt");
		} else if (params.get("orderBySalesPerson") != null) {
			sb.append("ORDER BY salerName, invoiceDt");
		} else if (params.get("orderByDepartment") != null) {
			sb.append("ORDER BY deptDesc, invoiceDt");
		} else {
			sb.append("ORDER BY invoiceDt");
		}
		
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
		if (params.get("orderByRegion") != null) {
			query.addScalar("idTourCat", LongType.INSTANCE);
			query.addScalar("tourCatDesc");
		}
		query.setParameter("statusCdCC", CommonConstant.STATUS_CD_CANCELLED);
		query.setParameter("statusCdVD", CommonConstant.STATUS_CD_VOID);
		if (params.get("salesPerson") != null) query.setParameter("salesPerson", (String) params.get("salesPerson"));
		if (params.get("idRegionList") != null) query.setParameterList("idRegionList", (List<String>) params.get("idRegionList"));
		if (params.get("idTourThemeList") != null) query.setParameterList("idTourThemeList", (List<String>) params.get("idTourThemeList"));
		if (params.get("idDepartmentList") != null) query.setParameterList("idDepartmentList", (List<String>) params.get("idDepartmentList"));
		if (appSettingVO != null && StringUtils.isNotBlank(appSettingVO.getValue())) {
			List<String> excludeAcctList = Arrays.asList(appSettingVO.getValue().split(","));
			query.setParameterList("excludeAcct", excludeAcctList);
		}
		query.setResultTransformer(Transformers.aliasToBean(CounterSalesReportVO.class));
		return query.list();
	}
	
	private void processFiltering(Map<String, Object> params, StringBuilder sb) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if (params.get("salesPerson") != null) sb.append("AND iv.id_saler = :salesPerson ");
		if (params.get("idTourThemeList") != null) sb.append("AND tp.id_tour_theme IN :idTourThemeList ");
		if (params.get("idDepartmentList") != null) 
			sb.append("AND ep.department IN :idDepartmentList ");
		else {
			// Remove all record no department
			if (params.get("orderByDepartment") != null) sb.append("AND ep.department IS NOT NULL AND ep.department != '' ");
		}
		
		if (params.get("orderByRegion") != null) {
			if (params.get("idRegionList") != null) 
				sb.append("AND tt.id_tour_cat IN :idRegionList ");
			else {
				// Remove all record no region
				if (params.get("orderByRegion") != null) sb.append("AND tc.id IS NOT NULL AND tc.id != '' ");
			}
		}
		
		if (params.get("dateFrom") != null && params.get("dateTo") != null)
			sb.append("AND iv.dt_inv BETWEEN DATE('" + sdf.format((Date) params.get("dateFrom")) + "') AND DATE('" + sdf.format((Date) params.get("dateTo")) + "') ");
		
	}
}
