package com.bcs.zsg.db.bterp.dao.view.monthlyticketing;

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
import com.bcs.zsg.common.helper.LookupItemConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.report.MonthlyTicketingSalesViewVO;

public class MonthlyTicketingDAOImpl extends BaseHibernateDAO implements MonthlyTicketingDAO {

	private Map<String, String> filters;
	
	@Override
	public int getListSizeMonthlyTicket(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT COUNT(1) FROM (")
			.append("SELECT 1 ")
			.append("FROM invoice inv INNER JOIN ( ")
			.append("	tour_dep tdp INNER JOIN tour_pkg tourpkg ")
			.append("		ON tdp.id_tour_pkg = tourpkg.id AND tourpkg.status_cd = 'AC' ")
			.append("	) ON inv.id_tour_dep = tdp.id AND inv.cat_cd = 'ti' ")
			.append("	INNER JOIN invoice_item invItem ON inv.id = invItem.id_inv AND invItem.status_cd = 'A' ")
			.append("	LEFT JOIN airline airflight ON invItem.id_airline = airflight.id ")
			.append("	INNER JOIN employee ep ON inv.id_saler = ep.id ")
			.append("	INNER JOIN sec_user usr ON ep.u_sec_user = usr.uuid ")
			.append(" WHERE inv.id_company = ").append(params.get("idCompany"))
			.append(" AND (inv.doc_type_cd = 'I' ")
			.append(" OR (inv.doc_type_cd = 'P' AND IFNULL(inv.doc_type_status,'') NOT IN ('CV','SP','CL'))) ");
		sb = genListFilter(sb, params);
		
		sb.append(" GROUP BY inv.id ) INV ");

		Query query = createSQLQuery(sb.toString());
		if(params.get("dateMonthYear") != null)
			query.setParameter("dateMonthYear", (Date)params.get("dateMonthYear"));
		return ((BigInteger) query.uniqueResult()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getMonthlyTicketGrandTotal(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT SUM( CASE WHEN invItem.amount < 0.00 THEN 0.00 ELSE invItem.amount END) AS SELL, ")
			.append("	SUM( invItem.quantity * IFNULL(invItem.net_price, 0.00)) AS NETT ")
			.append("FROM invoice inv INNER JOIN ( ")
			.append("	tour_dep tdp INNER JOIN tour_pkg tourpkg ")
			.append("		ON tdp.id_tour_pkg = tourpkg.id AND tourpkg.status_cd = 'AC' ")
			.append("	) ON inv.id_tour_dep = tdp.id AND inv.cat_cd = 'ti' ")
			.append("	INNER JOIN invoice_item invItem ON inv.id = invItem.id_inv AND invItem.status_cd = 'A' ")
			.append("	LEFT JOIN airline airflight ON invItem.id_airline = airflight.id ")
			.append("	INNER JOIN employee ep ON inv.id_saler = ep.id ")
			.append("	INNER JOIN sec_user usr ON ep.u_sec_user = usr.uuid ")
			.append(" WHERE inv.id_company = ").append(params.get("idCompany"))
			.append(" AND (inv.doc_type_cd = 'I' ")
			.append(" OR (inv.doc_type_cd = 'P' AND IFNULL(inv.doc_type_status,'') NOT IN ('CV','SP','CL'))) ");
		sb = genListFilter(sb, params);
		
		Query query = createSQLQuery(sb.toString());
		if(params.get("dateMonthYear") != null)
			query.setParameter("dateMonthYear", (Date)params.get("dateMonthYear"));

		List<Object> queryResultList = query.list();
		MonthlyTicketingSalesViewVO returnResult = new MonthlyTicketingSalesViewVO();
		
		for (Iterator<Object> it = queryResultList.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
	        if (row[0] != null) returnResult.setSellAmount(Double.parseDouble(row[0].toString()));
	        if (row[1] != null) returnResult.setNettAmount(Double.parseDouble(row[1].toString()));
	        
		}


		return (T) returnResult;
	}

	@SuppressWarnings("unchecked")
	private StringBuilder genListFilter(StringBuilder _sb, Map<String, Object> params) {
		filters = (Map<String, String>) params.get("filters");
		
		if(params.get("idSeller") != null)
			_sb.append("	AND inv.id_saler = ").append(params.get("idSeller"));

		if(params.get("idAirline") != null)
			_sb.append("	AND airflight.code LIKE '%").append(params.get("idAirline")).append("%'");

		if(params.get("tourCode") != null)
			_sb.append("	AND tdp.code = '").append(params.get("tourCode")).append("'");

		if(params.get("dateMonthYear") != null)
			_sb.append("	AND YEAR(inv.dt_inv) = YEAR(:dateMonthYear)")
				.append("	AND MONTH(inv.dt_inv) = MONTH(:dateMonthYear)");
		
		if (!filters.isEmpty()) {
			_sb.append(" AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
					_sb = filterListMonthlyTicketing(it.next(), _sb);
				
				if (filters.size() > 1 && it.hasNext()) _sb.append(" AND ");
			}
			_sb.append(") ");
		}
		return _sb;
	}
	
	private StringBuilder filterListMonthlyTicketing(Entry<String, String> entry, StringBuilder _sb) {
		return _sb;
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<MonthlyTicketingSalesViewVO> getListMonthlyTicket(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT INV.id, INV.SELLER_NAME, INV.dt_inv, INV.id_tour_dep, INV.airline_no, ")
//			.append("CASE WHEN INV.doc_type_cd = 'C' THEN CONCAT('CN ', INV.code) ELSE INV.code END AS code, ")
			.append("CASE WHEN INV.doc_type_cd = 'I' THEN INV.code END as code,")
			.append("INV.tour_code, ")
			.append("	(SELECT COUNT(1) FROM invoice_pax invPax WHERE INV.id = invPax.id_inv) AS totalPax, ")
			.append("	INV.SELL, INV.NETT, (INV.SELL - INV.NETT) AS PROFIT, ")
			.append("	concat((case when ps.salutation_cd != '"+ LookupItemConstant.SALUTATION_EMPTY_CD +"' then ps.salutation_cd else '' end), ' ', ps.first_name, ' ', ps.last_name) AS CUST_NAME, ")
			.append("	cust.corporate_name as company_name, ")
//			.append("	(SELECT GROUP_CONCAT(DISTINCT (CASE WHEN ticket_no = '' THEN NULL ELSE ticket_no END) ORDER BY ticket_no SEPARATOR ', ') ")
//			.append("		FROM invoice_pax invPax WHERE INV.id = invPax.id_inv and invPax.status_cd = 'A') AS tickets ")
			.append("(SELECT GROUP_CONCAT(CONCAT(ref_type, ': ', ref_no) SEPARATOR '\n') FROM invoice_pax_ref_no iprn ")
//			.append("JOIN invoice_pax invPax ON iprn.id_inv_pax = invPax.id WHERE invPax.id_inv = INV.id AND invPax.status_cd = 'A' AND iprn.status_cd = 'A') AS ref_no_list ")
			.append("JOIN invoice_pax invPax ON iprn.id_inv_pax = invPax.id WHERE invPax.id_inv = INV.id AND invPax.status_cd = 'A' AND iprn.status_cd = 'A') AS ref_no_list, ")
			.append("INV.ps_no ")

			.append("FROM ( ")
			.append("SELECT inv.id, inv.doc_type_cd, inv.code, inv.ps_no, inv.dt_inv, inv.id_tour_dep, tdp.code AS tour_code, inv.id_customer, usr.user_name as SELLER_NAME, ") 
			.append("	GROUP_CONCAT(DISTINCT (CASE WHEN airflight.code = '' THEN NULL ELSE airflight.code END) ") 
			.append("	 			ORDER BY airflight.code SEPARATOR ', ') AS airline_no, ")
			.append("	SUM( CASE WHEN invItem.amount < 0.00 THEN 0.00 ELSE invItem.amount END) AS SELL, ")
			.append("	SUM( invItem.quantity * IFNULL(invItem.net_price, 0.00)) AS NETT ")
			.append("FROM invoice inv INNER JOIN ( ")
			.append("	tour_dep tdp INNER JOIN tour_pkg tourpkg ")
			.append("		ON tdp.id_tour_pkg = tourpkg.id AND tourpkg.status_cd = 'AC' ")
			.append("	) ON inv.id_tour_dep = tdp.id AND inv.cat_cd = 'ti' ")
			.append("	INNER JOIN invoice_item invItem ON inv.id = invItem.id_inv AND invItem.status_cd = 'A' ")
			.append("	LEFT JOIN airline airflight ON invItem.id_airline = airflight.id ")
			.append("	INNER JOIN employee ep ON inv.id_saler = ep.id ")
			.append("	INNER JOIN sec_user usr ON ep.u_sec_user = usr.uuid ")
			.append("WHERE inv.id_company = ").append(params.get("idCompany")).append(" ")
			.append("and (inv.doc_type_cd = 'I' OR (inv.doc_type_cd = 'P' AND IFNULL(inv.doc_type_status, '') NOT IN ('CV','SP','CL'))) ");
		sb = genListFilter(sb, params);
		
		sb.append(" GROUP BY inv.id ")
			.append(") INV INNER JOIN customer cust ON INV.id_customer = cust.id ")
			.append("	JOIN person ps ON cust.id_pc = ps.id ");
		
		sb.append(" ORDER BY ");

		String sortField = (String) params.get("sortField");
		String sortDirection = "";
		
		if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) 
			sortDirection = " ASC";
		else 
			sortDirection = " DESC";

		if (sortField == null) {
			sb.append("INV.id");
		} else {
			if ("dtInvoice".equals(sortField)) 
				sb.append("INV.dt_inv");
			else if ("invoiceCode".equals(sortField)) {
				sb.append("CAST(INV.code AS decimal)");
			} else if ("salesPerson".equals(sortField)) {
				sb.append("SELLER_NAME");	
			} else if ("airlineNo".equals(sortField)) {
				sb.append("airline_no");
			} else if ("totalPax".equals(sortField)) {
				sb.append("totalPax");
			} else if ("customerName".equals(sortField)) {
				sb.append("CUST_NAME");
			} else if ("companyName".equals(sortField)) {
				sb.append("company_name");
			} else if ("sellAmount".equals(sortField)) {
				sb.append("INV.SELL");
			} else if ("nettAmount".equals(sortField)) {
				sb.append("INV.NETT");
			} else if ("profitAmount".equals(sortField)) {
				sb.append("PROFIT");
//			} else if ("ticketNo".equals(sortField)) {
//				sb.append("tickets");
			} else if ("tourCode".equals(sortField)) {
				sb.append("INV.tour_code");
			} else if ("referenceNoList".equals(sortField)) {
				sb.append("(SELECT GROUP_CONCAT(CONCAT(ref_type, ': ', ref_no) SEPARATOR '\n') FROM invoice_pax_ref_no iprn JOIN invoice_pax invPax ON iprn.id_inv_pax = invPax.id WHERE invPax.id_inv = INV.id AND invPax.status_cd = 'A' AND iprn.status_cd = 'A')");
			} 
			else if ("psNo".equals(sortField)) {
				sb.append("INV.ps_no");
			}

		}
		sb.append(sortDirection);
		
		Query query = createSQLQuery(sb.toString());
		if(params.get("dateMonthYear") != null)	
			query.setParameter("dateMonthYear", (Date)params.get("dateMonthYear"));
		query.setFirstResult((int) params.get("first"));
		query.setMaxResults((int) params.get("pageSize"));
		List<Object> queryResultList = query.list();
		List<MonthlyTicketingSalesViewVO> returnResultList = new ArrayList<MonthlyTicketingSalesViewVO>();
		

		int rowNumber = (int) params.get("first");
		for (Iterator<Object> it = queryResultList.iterator() ; it.hasNext() ;) {

			Object[] row = (Object[]) it.next();
			MonthlyTicketingSalesViewVO vo = new MonthlyTicketingSalesViewVO();
			vo.setRowNumber(++rowNumber);
	        if (row[0] != null) vo.setIdInvoice(((BigInteger)row[0]).longValue());
	        if (row[1] != null) vo.setSalesPerson((String) row[1]);
	        if (row[2] != null) vo.setDtInvoice((Date) row[2]);
	        if (row[3] != null) vo.setIdTourDep(((BigInteger)row[3]).longValue());
	        if (row[4] != null) vo.setAirlineNo((String)row[4]);
	        if (row[5] != null) vo.setInvoiceCode((String)row[5]);
	        if (row[6] != null) vo.setTourCode((String)row[6]);
	        if (row[7] != null) vo.setTotalPax(((BigInteger) row[7]).intValue());
	        if (row[8] != null) vo.setSellAmount(Double.parseDouble(row[8].toString()));
	        if (row[9] != null) vo.setNettAmount(Double.parseDouble(row[9].toString()));
	        if (row[10] != null) vo.setProfitAmount(Double.parseDouble(row[10].toString()));
	        if (row[11] != null) vo.setCustomerName((String) row[11]);
	        if (row[12] != null) vo.setCompanyName((String) row[12]);
//	        if (row[13] != null) vo.setTicketNo((String) row[13]);
	        if (row[13] != null) vo.setReferenceNoList((String) row[13]);
	        String referenceNoList = (String) row[13];
			if (referenceNoList != null && !referenceNoList.isEmpty()) {
		       vo.setFormatedReferenceNoList(referenceNoList.replace("\n", "<br/>")); 
		    }
	        if (row[14] != null) vo.setPsNo((String) row[14]);
	        

			
			returnResultList.add(vo);
			


		}
		
		return returnResultList;
	}
	
}
