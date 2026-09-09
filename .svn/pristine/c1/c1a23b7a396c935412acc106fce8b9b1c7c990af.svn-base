package com.bcs.zsg.sales.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;

public class InvoiceItemReportUsageDAOImpl extends BaseHibernateDAO implements InvoiceItemReportUsageDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<AcctViewVO> getInvoiceItemReportUsageList(Long companyId, List<String> itemCodes, Date fromDate, Date toDate,
			List<String> statusCds, List<String> invCatCds, List<String> orderSourceCds, List<String> regionIds, List<String> countryIds) throws BusinessException {
		String psPrefix = LookupItemUtils.getPsPrefix((Long) companyId);
		String invPrefix = LookupItemUtils.getInvPrefix((Long) companyId);
		// String cnPrefix = LookupItemUtils.getCnPrefix((Long) companyId);
		
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT ");
		sb.append("  ii.id AS id, ");
		sb.append("  i.doc_type_cd AS docTypeCd, ");
		sb.append("  CASE WHEN i.doc_type_cd = 'P' THEN CONCAT('" + psPrefix + " ', i.code) ");
		sb.append("       WHEN i.doc_type_cd = 'I' THEN CONCAT('" + invPrefix + " ', i.code) ");
		sb.append("       ELSE i.code END AS invoiceNo, ");
		sb.append("CONCAT( '" + psPrefix + " ', i.ps_no) AS psNo, ");
		sb.append("  IFNULL(crd.ref_value, i.status_cd) AS invoiceStatus, ");
		sb.append("  td.code AS tourCd, ");
		sb.append("  ie.type_cd AS itemType, ");
		sb.append("  ii.code AS itemCode, ");
		sb.append("  ii.description AS `desc`, ");
		sb.append("  ii.sub_description AS subDesc, ");
		sb.append("  ii.quantity AS qty, ");
		sb.append("  ii.amount AS amount, ");
		sb.append("  i.dt_inv AS dtInv ");
		sb.append("FROM invoice i ");
		sb.append("INNER JOIN invoice_item ii ON i.id = ii.id_inv ");
		sb.append("LEFT JOIN inv_eo_item ie ON ii.id_inv_eo_item = ie.id ");
		sb.append("LEFT JOIN tour_dep td ON i.id_tour_dep = td.id ");
		sb.append("LEFT JOIN com_ref_data crd ON crd.cat_cd = IF(i.doc_type_cd = 'P', 'PS_STATUS', 'INV_STATUS') ");
		sb.append("  AND crd.ref_cd = IF(i.doc_type_cd = 'P' AND i.doc_type_status IS NOT NULL AND i.doc_type_status != '', i.doc_type_status, i.status_cd) ");
		if (CollectionUtils.isNotEmpty(regionIds) || CollectionUtils.isNotEmpty(countryIds)) {
			sb.append("LEFT JOIN tour_pkg tp ON td.id_tour_pkg = tp.id ");
			sb.append("LEFT JOIN tour_theme tt ON tp.id_tour_theme = tt.id AND tt.status_cd = 'AC' ");
		}
		sb.append("WHERE i.id_company = :companyId ");
		sb.append("  AND ii.status_cd = 'A' ");

		if (CollectionUtils.isNotEmpty(statusCds)) {
			sb.append("  AND (i.status_cd IN (:statusCds) OR i.doc_type_status IN (:statusCds)) ");
		} else {
			sb.append("  AND i.status_cd != 'VD' ");
		}
		if (CollectionUtils.isNotEmpty(invCatCds)) {
			sb.append("  AND i.cat_cd IN (:invCatCds) ");
		}
		if (CollectionUtils.isNotEmpty(orderSourceCds)) {
			sb.append("  AND i.order_cd IN (:orderSourceCds) ");
		}
		if (CollectionUtils.isNotEmpty(regionIds)) {
			sb.append("  AND tt.id_tour_cat IN (:regionIds) ");
		}
		if (CollectionUtils.isNotEmpty(countryIds)) {
			sb.append("  AND tt.id_country IN (:countryIds) ");
		}
		if (CollectionUtils.isNotEmpty(itemCodes)) {
			sb.append("  AND ii.code IN (:itemCodes) ");
		}
		if (fromDate != null) {
			sb.append("  AND DATE(i.dt_inv) >= DATE(:fromDate) ");
		}
		if (toDate != null) {
			sb.append("  AND DATE(i.dt_inv) <= DATE(:toDate) ");
		}

		sb.append("ORDER BY FIELD(i.doc_type_cd, 'I', 'P', 'C'), CAST(i.code AS DECIMAL), CAST(i.ps_no AS DECIMAL), ii.code");

		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("id", LongType.INSTANCE);
		query.addScalar("docTypeCd", StringType.INSTANCE);
		query.addScalar("invoiceNo", StringType.INSTANCE);
		query.addScalar("psNo", StringType.INSTANCE);
		query.addScalar("invoiceStatus", StringType.INSTANCE);
		query.addScalar("tourCd", StringType.INSTANCE);
		query.addScalar("itemType", StringType.INSTANCE);
		query.addScalar("itemCode", StringType.INSTANCE);
		query.addScalar("desc", StringType.INSTANCE);
		query.addScalar("subDesc", StringType.INSTANCE);
		query.addScalar("qty", IntegerType.INSTANCE);
		query.addScalar("amount", DoubleType.INSTANCE);
		query.addScalar("dtInv", DateType.INSTANCE);

		query.setParameter("companyId", companyId);

		if (CollectionUtils.isNotEmpty(statusCds)) {
			query.setParameterList("statusCds", statusCds);
		}
		if (CollectionUtils.isNotEmpty(invCatCds)) {
			query.setParameterList("invCatCds", invCatCds);
		}
		if (CollectionUtils.isNotEmpty(orderSourceCds)) {
			query.setParameterList("orderSourceCds", orderSourceCds);
		}
		if (CollectionUtils.isNotEmpty(regionIds)) {
			List<Long> regionIdLongs = new ArrayList<Long>();
			for (String r : regionIds) {
				if (StringUtils.isNotBlank(r)) {
					regionIdLongs.add(Long.valueOf(r));
				}
			}
			query.setParameterList("regionIds", regionIdLongs);
		}
		if (CollectionUtils.isNotEmpty(countryIds)) {
			List<Long> countryIdLongs = new ArrayList<Long>();
			for (String c : countryIds) {
				if (StringUtils.isNotBlank(c)) {
					countryIdLongs.add(Long.valueOf(c));
				}
			}
			query.setParameterList("countryIds", countryIdLongs);
		}
		if (CollectionUtils.isNotEmpty(itemCodes)) {
			query.setParameterList("itemCodes", itemCodes);
		}
		if (fromDate != null) {
			query.setParameter("fromDate", fromDate);
		}
		if (toDate != null) {
			query.setParameter("toDate", toDate);
		}

		query.setResultTransformer(Transformers.aliasToBean(AcctViewVO.class));
		List<AcctViewVO> result = query.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectItem> getStatusList() throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT ref_cd AS value, ref_value AS label ");
		sb.append("FROM com_ref_data ");
		sb.append("WHERE cat_cd IN ('INV_STATUS', 'PS_STATUS') ");
		sb.append("ORDER BY ref_value");

		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("value", StringType.INSTANCE);
		query.addScalar("label", StringType.INSTANCE);

		List<Object[]> list = query.list();
		List<SelectItem> statusList = new ArrayList<SelectItem>();
		for (Object[] row : list) {
			if (row[0] != null && row[1] != null) {
				statusList.add(new SelectItem(row[0].toString(), row[1].toString()));
			}
		}
		return statusList;
	}
}
