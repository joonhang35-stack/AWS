package com.bcs.zsg.sales.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.primefaces.model.SortOrder;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.SalesSmmryVO;

public class SalesSmmryDAOImpl extends BaseHibernateDAO implements SalesSmmryDAO {

	@SuppressWarnings("unchecked")
	@Override
	public int getStaffSalesSmmryInvListSize(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		/*sb.append("select iv.id_saler, usr.user_name as salerName, count(iv.id_saler) as invCount, ").
			append("sum(iv.amount) as invAmt, ").
			append("sum(p.amount) as 'invPaid', ").
			append("Sum(iv.balance) as balance ").
		append("from invoice iv ").
			append("left join (select pmnt.id_inv, sum(pmnt.amount) as amount from invoice_pmnt pmnt where pmnt.status_cd = 'A' group by pmnt.id_inv) p on iv.id = p.id_inv ").
			append("left join tour_dep tdp on iv.id_tour_dep=tdp.id, employee ep, sec_user usr, customer cm, person ps ").
		append("where iv.id_company=:idCompany and iv.id_saler=ep.id and ").
			append("iv.status_cd!='CC' and iv.status_cd!='VD' and iv.id_saler=ep.id and ").
			append("ep.u_sec_user=usr.uuid and iv.id_customer=cm.id and cm.id_pc=ps.id and iv.doc_type_cd = 'I' ");*/
		sb.append("select iv.id_saler ").
		append("from invoice iv, employee ep, sec_user usr, customer cm, person ps ").
		append("where iv.id_company=:idCompany and iv.id_saler=ep.id and ").
			append("iv.status_cd!='CC' and iv.status_cd!='VD' and iv.id_saler=ep.id and ").
			append("ep.u_sec_user=usr.uuid and iv.id_customer=cm.id and cm.id_pc=ps.id and iv.doc_type_cd = 'I' ");
		
		SearchParamVO searchParamVO = (SearchParamVO) params.get("searchParam");
		if (searchParamVO.getObj2() != null) {
			if (searchParamVO.getFromDate() != null) sb.append("and date(iv.dt_inv) >= date('" + sdf.format((Date)searchParamVO.getFromDate()) + "') ");
			if (searchParamVO.getToDate() != null) sb.append("and date(iv.dt_inv) <= date('" + sdf.format((Date)searchParamVO.getToDate()) + "') ");
		}
		if (searchParamVO.getObj1() != null) sb.append("and iv.id_saler = " + searchParamVO.getObj1() + " ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		
		if (!filters.isEmpty()) {
			sb.append("and (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("salerName".equals(entry.getKey())) sb.append("usr.user_name like '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
			}
			sb.append(") ");
		}
		sb.append("group by iv.id_saler ");
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("companyId"));
		List<Object> objList = query.list();
		return objList.size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesSmmryVO> getStaffSalesSmmryInvList(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		sb.append("select iv.id_saler, usr.user_name as salerName, count(iv.id_saler) as invCount, ").
			append("sum(iv.amount) as invAmt, ").
			append("sum(p.amount) as invPaid, ").
			append("sum(iv.balance) as balance ").
		append("from invoice iv ").
			append("left join (select pmnt.id_inv, sum(pmnt.amount) as amount from invoice_pmnt pmnt where pmnt.status_cd = 'A' group by pmnt.id_inv) p on iv.id = p.id_inv ").
			append(", employee ep, sec_user usr, customer cm, person ps ").
		append("where iv.id_company=:idCompany and iv.id_saler=ep.id and ").
			append("iv.status_cd!='CC' and iv.status_cd!='VD' and iv.id_saler=ep.id and ").
			append("ep.u_sec_user=usr.uuid and iv.id_customer=cm.id and cm.id_pc=ps.id and iv.doc_type_cd = 'I' ");
		
		SearchParamVO searchParamVO = (SearchParamVO) params.get("searchParam");
		if (searchParamVO.getObj2() != null) {
			if (searchParamVO.getFromDate() != null) sb.append("and date(iv.dt_inv) >= date('" + sdf.format((Date)searchParamVO.getFromDate()) + "') ");
			if (searchParamVO.getToDate() != null) sb.append("and date(iv.dt_inv) <= date('" + sdf.format((Date)searchParamVO.getToDate()) + "') ");
		}
		if (searchParamVO.getObj1() != null) sb.append("and iv.id_saler = " + searchParamVO.getObj1() + " ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		
		if (!filters.isEmpty()) {
			sb.append("and (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("salerName".equals(entry.getKey())) sb.append("usr.user_name like '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
			}
			sb.append(") ");
		}
		
		sb.append("group by iv.id_saler ");
		
		sb.append("order by ");
		String sortField = (String) params.get("sortField");
		if (sortField == null) {
			sb.append(" usr.user_name ");
		} else {
			if ("salerName".equals(sortField)) sb.append("usr.user_name");
			if ("invAmt".equals(sortField)) sb.append("sum(iv.amount)");
			if ("invPaid".equals(sortField)) sb.append("sum((select case when sum(pmnt.amount) is null then 0 else sum(pmnt.amount) end from invoice_pmnt pmnt where pmnt.id_inv = iv.id and pmnt.status_cd = 'A'))");
			if ("balance".equals(sortField)) sb.append("sum(iv.balance)");
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" asc");
			else sb.append(" desc");
		}
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("companyId"));
		query.setFirstResult((Integer) params.get("first"));
		query.setMaxResults((Integer) params.get("pageSize"));
		List<Object> results = query.list();
		List<SalesSmmryVO> list = new ArrayList<SalesSmmryVO>();
		int rowNumber = (int) params.get("first");
		
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			SalesSmmryVO vo=new SalesSmmryVO();
			
			vo.setRowNumber(++rowNumber);
			if (row[0] != null) vo.setSalerId(Long.parseLong(row[0].toString()));
			if (row[1] != null) vo.setSalerName((String) row[1]);
			if (row[2] != null) vo.setInvCount(Integer.parseInt(row[2].toString()));
			if (row[3] == null){vo.setInvAmt(0.00);}else{ vo.setInvAmt(Double.parseDouble(row[3].toString()));}
			if (row[4] == null){vo.setInvPaid(0.00);}else{ vo.setInvPaid(Double.parseDouble(row[4].toString()));}
			if (row[5] == null){vo.setBalance(0.00);}else{ vo.setBalance(Double.parseDouble(row[5].toString()));}
			list.add(vo);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalesSmmryVO> getStaffSalesSmmryInvDetailList(Long companyId, Long salerId, SearchParamVO searchParamVO) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		sb.append("select iv.id as id, iv.code as code, iv.dt_inv as invoiceDt, ").
			append("iv.dt_departure as departureDt, iv.amount as invAmt, ").
			append("p.amount as invPaid, ").
			append("iv.balance as balance, ").
			append("iv.id_tour_booking as tourDepId, ifnull(tdp.code, '') as tourCd, iv.id_customer as customerId, ").
			append("usr.user_name as salerName, iv.id_saler as salerId, cm.pc_type_cd as typeCd, ").
			append("concat(ps.last_name, ' ', ps.first_name) as custName, ").
			append("ps.salutation_cd as custSalutation, cm.corporate_name as coName ").
		append("from invoice iv ").
			append("left join (select pmnt.id_inv, sum(pmnt.amount) as amount from invoice_pmnt pmnt where pmnt.status_cd = 'A' group by pmnt.id_inv) p on iv.id = p.id_inv ").
			append("left join tour_dep tdp on iv.id_tour_dep=tdp.id, employee ep, sec_user usr, customer cm, person ps ").
		append("where iv.id_company=:idCompany and iv.id_saler=ep.id and ").
			append("iv.status_cd!=:statusCdCC and iv.status_cd!=:statusCdVD and iv.id_saler=ep.id and ").
			append("ep.u_sec_user=usr.uuid and iv.id_customer=cm.id and cm.id_pc=ps.id and iv.doc_type_cd = 'I' ");
		if (searchParamVO.getFromDate() != null) sb.append("and date(iv.dt_inv) >= date('" + sdf.format((Date)searchParamVO.getFromDate()) + "') ");
		if (searchParamVO.getToDate() != null) sb.append("and date(iv.dt_inv) <= date('" + sdf.format((Date)searchParamVO.getToDate()) + "') ");
		if (salerId != null) sb.append("and iv.id_saler = " + salerId + " ");
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("id", LongType.INSTANCE);
		query.addScalar("code");
		query.addScalar("invoiceDt", DateType.INSTANCE);
		query.addScalar("departureDt", DateType.INSTANCE);
		query.addScalar("invAmt", DoubleType.INSTANCE);
		query.addScalar("invPaid", DoubleType.INSTANCE);
		query.addScalar("balance", DoubleType.INSTANCE);
		query.addScalar("customerId", LongType.INSTANCE);
		query.addScalar("tourDepId", LongType.INSTANCE);
		query.addScalar("tourCd");
		query.addScalar("salerId", LongType.INSTANCE);
		query.addScalar("salerName");
		query.addScalar("typeCd");
		query.addScalar("custName");
		query.addScalar("custSalutation");
		query.addScalar("coName");
		query.setParameter("idCompany", companyId);
		query.setParameter("statusCdCC", CommonConstant.STATUS_CD_CANCELLED);
		query.setParameter("statusCdVD", CommonConstant.STATUS_CD_VOID);
		query.setResultTransformer(Transformers.aliasToBean(SalesSmmryVO.class));
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int getStaffSalesSmmryBookingListSize(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		sb.append("select b.id_employee ").
		append("from tour_booking b, employee e, sec_user u, customer c, person p ").
		append("where b.id_company = :idCompany and b.id_cust = c.id and c.id_pc = p.id and b.id_employee = e.id and e.u_sec_user = u.uuid ");
	
		SearchParamVO searchParamVO = (SearchParamVO) params.get("searchParam");
		if (searchParamVO.getObj2() != null) {
			if (searchParamVO.getFromDate() != null) sb.append("and date(b.dt_created) >= date('" + sdf.format((Date)searchParamVO.getFromDate()) + "') ");
			if (searchParamVO.getToDate() != null) sb.append("and date(b.dt_created) <= date('" + sdf.format((Date)searchParamVO.getToDate()) + "') ");
		}
		if (searchParamVO.getObj1() != null) sb.append("and b.id_employee = " + searchParamVO.getObj1() + " ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		if (!filters.isEmpty()) {
			sb.append("AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("salerName".equals(entry.getKey())) sb.append("u.user_name LIKE '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" AND ");
			}
			sb.append(") ");
		}
		
		sb.append("group by b.id_employee ");
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("companyId"));
		List<Object> objList = query.list();
		return objList.size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesSmmryVO> getStaffSalesSmmryBookingList(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		sb.append("select b.id, b.id_employee, u.user_name AS salerName, ").
			append("sum(if(b.pmnt_status_cd = 'KIV', 1, 0)) as kiv, ").
			append("sum(if(b.pmnt_status_cd = 'DEP', 1, 0)) as depPaid, ").
			append("sum(if(b.pmnt_status_cd = 'FULL', 1, 0)) as paidinfull, ").
			append("sum(if(b.pmnt_status_cd = 'KIVEXP', 1, 0)) as expired, ").
			append("sum(if(b.status_cd IN ('CC','VD'), 1, 0)) as cancelled ").
			append("from tour_booking b, employee e, sec_user u, customer c, person p ").
			append("where b.id_company = :idCompany and b.id_cust = c.id and c.id_pc = p.id and b.id_employee = e.id and e.u_sec_user = u.uuid ");
		
		SearchParamVO searchParamVO = (SearchParamVO) params.get("searchParam");
		if (searchParamVO.getObj2() != null) {
			if (searchParamVO.getFromDate() != null) sb.append("and date(b.dt_created) >= date('" + sdf.format((Date)searchParamVO.getFromDate()) + "') ");
			if (searchParamVO.getToDate() != null) sb.append("and date(b.dt_created) <= date('" + sdf.format((Date)searchParamVO.getToDate()) + "') ");
		}
		if (searchParamVO.getObj1() != null) sb.append("and b.id_employee = " + searchParamVO.getObj1() + " ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		
		if (!filters.isEmpty()) {
			sb.append("AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("salerName".equals(entry.getKey())) sb.append("u.user_name LIKE '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" AND ");
			}
			sb.append(") ");
		}
		
		sb.append("group by b.id_employee ");
		
		sb.append("order by ");
		String sortField = (String) params.get("sortField");
		if (sortField == null) {
			sb.append(" u.user_name ");
		} else {
			if ("salerName".equals(sortField)) sb.append("u.user_name");
			if ("kiv".equals(sortField)) sb.append("sum(if(b.pmnt_status_cd = 'KIV', 1, 0))");
			if ("depPaid".equals(sortField)) sb.append("sum(if(b.pmnt_status_cd = 'DEP', 1, 0))");
			if ("paidinfull".equals(sortField)) sb.append("sum(if(b.pmnt_status_cd = 'FULL', 1, 0))");
			if ("expired".equals(sortField)) sb.append("sum(if(b.pmnt_status_cd = 'KIVEXP', 1, 0)) ");
			if ("cancelled".equals(sortField)) sb.append("sum(if(b.status_cd IN ('CC','VD'), 1, 0))");
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" asc");
			else sb.append(" desc");
		}
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("companyId"));
		query.setFirstResult((Integer) params.get("first"));
		query.setMaxResults((Integer) params.get("pageSize"));
		List<Object> objList = query.list();
		List<SalesSmmryVO> list = new ArrayList<SalesSmmryVO>();
		int rowNumber = (int) params.get("first");
		
		if (CollectionUtils.isNotEmpty(objList)) {
			for (Iterator<Object> it = objList.iterator() ; it.hasNext() ;) {
				Object[] row = (Object[]) it.next();
				SalesSmmryVO vo = new SalesSmmryVO();
				vo.setRowNumber(++rowNumber);
				vo.setId(((BigInteger) row[0]).longValue());
				vo.setSalerId(((BigInteger) row[1]).longValue());
				vo.setSalerName((String) row[2]);
				vo.setKiv(Integer.parseInt(row[3].toString()));
				vo.setDepPaid(Integer.parseInt(row[4].toString()));
				vo.setPaidinfull(Integer.parseInt(row[5].toString()));
				vo.setExpired(Integer.parseInt(row[6].toString()));
				vo.setCancelled(Integer.parseInt(row[7].toString()));
				list.add(vo);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalesSmmryVO> getStaffSalesSmmryBookingDetailList(Long companyId, Long salerId, SearchParamVO searchParamVO) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		sb.append("SELECT b.id AS 'id', b.id_company, b.pmnt_status_cd AS 'pmntStatusCd', b.dt_created AS 'createdDate', b.dt_exp AS 'dtExp', d.code AS 'tourCode', ").
		append("u.user_name AS 'staff', p.salutation_cd, p.last_name, p.first_name, b.status_cd AS 'statusCode' ").
		append("from tour_booking b, tour_dep d, employee e, sec_user u, customer c, person p ").
		append("where b.id_company = :idCompany AND b.id_tour_dep = d.id and b.id_cust = c.id and c.id_pc = p.id and b.id_employee = e.id and e.u_sec_user = u.uuid ");
		if (searchParamVO.getFromDate() != null) sb.append("and date(b.dt_created) >= date('" + sdf.format((Date)searchParamVO.getFromDate()) + "') ");
		if (searchParamVO.getToDate() != null) sb.append("and date(b.dt_created) <= date('" + sdf.format((Date)searchParamVO.getToDate()) + "') ");
		if (salerId != null) sb.append("and b.id_employee = " + salerId + " ");
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.setParameter("idCompany", companyId);
		List<Object> objList = query.list();
		List<SalesSmmryVO> list = new ArrayList<SalesSmmryVO>();
		
		if (CollectionUtils.isNotEmpty(objList)) {
			for (Iterator<Object> it = objList.iterator() ; it.hasNext() ;) {
				Object[] row = (Object[]) it.next();
				SalesSmmryVO vo = new SalesSmmryVO();
				vo.setId(((BigInteger) row[0]).longValue());
				vo.setIdCompany(((BigInteger) row[1]).longValue());
				vo.setPmntStatusCd((String) row[2]);
				vo.setCreatedDate((Date) row[3]);
				vo.setDtExp((Date) row[4]);
				vo.setTourCode((String) row[5]);
				vo.setSalerName((String) row[6]);
				vo.setSalutationCd((String) row[7]);
				vo.setLastName((String) row[8]);
				vo.setFirstName((String) row[9]);
				vo.setStatusCode((String) row[10]);
				list.add(vo);
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int getRegionSalesSmmryInvListSize(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SearchParamVO searchParamVO = (SearchParamVO) params.get("searchParam");
		
		sb.append("select tdp.id_region ").
		append("from invoice iv, ").
		append("( ").
			append("select tt.id as id_region, tt.description as region, td.id ").
			append("from tour_dep td, tour_pkg tp, tour_theme tt ").
			append("where td.status_cd = 'AC' and tp.status_cd = 'AC' and tt.status_cd = 'AC' and ").
			append("td.id_tour_pkg = tp.id and tp.id_tour_theme = tt.id ");
		if (searchParamVO.getObj3() != null) sb.append("and tt.id_parent = " + searchParamVO.getObj3() + " ");	
		sb.append(") tdp ").
		append("where iv.id_company=:idCompany and ").
			append("iv.status_cd!='CC' and iv.status_cd!='VD' and iv.id_tour_dep = tdp.id and iv.doc_type_cd = 'I' ");

		if (searchParamVO.getFromDate() != null) sb.append("and date(iv.dt_inv) >= date('" + sdf.format((Date)searchParamVO.getFromDate()) + "') ");
		if (searchParamVO.getToDate() != null) sb.append("and date(iv.dt_inv) <= date('" + sdf.format((Date)searchParamVO.getToDate()) + "') ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		
		if (!filters.isEmpty()) {
			sb.append("and (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("regionName".equals(entry.getKey())) sb.append("tdp.region like '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
			}
			sb.append(") ");
		}
		sb.append("group by tdp.id_region ");
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("companyId"));
		List<Object> objList = query.list();
		
		return objList.size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesSmmryVO> getRegionSalesSmmryInvList(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SearchParamVO searchParamVO = (SearchParamVO) params.get("searchParam");
		
		sb.append("select tdp.id_region, tdp.region, count(iv.id) as invCount, ").
			append("sum(iv.amount) as invAmt, ").
			append("sum(p.amount) as invPaid, ").
			append("sum(iv.balance) as balance ").
		append("from invoice iv ").
			append("left join (select pmnt.id_inv, sum(pmnt.amount) as amount from invoice_pmnt pmnt where pmnt.status_cd = 'A' group by pmnt.id_inv) p on iv.id = p.id_inv, ").
		append("( ").
			append("select tt.id as id_region, tt.description as region, td.id ").
			append("from tour_dep td, tour_pkg tp, tour_theme tt ").
			append("where td.status_cd = 'AC' and tp.status_cd = 'AC' and tt.status_cd = 'AC' and ").
			append("td.id_tour_pkg = tp.id and tp.id_tour_theme = tt.id ");
		if (searchParamVO.getObj3() != null) sb.append("and tt.id_parent = " + searchParamVO.getObj3() + " ");		
		sb.append(") tdp ").
		append("where iv.id_company=:idCompany and ").
			append("iv.status_cd!='CC' and iv.status_cd!='VD' and iv.id_tour_dep = tdp.id and iv.doc_type_cd = 'I' ");

		if (searchParamVO.getFromDate() != null) sb.append("and date(iv.dt_inv) >= date('" + sdf.format((Date)searchParamVO.getFromDate()) + "') ");
		if (searchParamVO.getToDate() != null) sb.append("and date(iv.dt_inv) <= date('" + sdf.format((Date)searchParamVO.getToDate()) + "') ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		
		if (!filters.isEmpty()) {
			sb.append("and (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("regionName".equals(entry.getKey())) sb.append("tdp.region like '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
			}
			sb.append(") ");
		}
		
		sb.append("group by tdp.id_region ");
		
		sb.append("order by ");
		String sortField = (String) params.get("sortField");
		if (sortField == null) {
			sb.append(" tdp.region ");
		} else {
			if ("regionName".equals(sortField)) sb.append("tdp.region");
			if ("invAmt".equals(sortField)) sb.append("sum(iv.amount)");
			if ("invPaid".equals(sortField)) sb.append("sum((select case when sum(pmnt.amount) is null then 0 else sum(pmnt.amount) end from invoice_pmnt pmnt where pmnt.id_inv = iv.id and pmnt.status_cd = 'A'))");
			if ("balance".equals(sortField)) sb.append("sum(iv.balance)");
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" asc");
			else sb.append(" desc");
		}
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("companyId"));
		query.setFirstResult((Integer) params.get("first"));
		query.setMaxResults((Integer) params.get("pageSize"));
		List<Object> results = query.list();
		List<SalesSmmryVO> list = new ArrayList<SalesSmmryVO>();
		int rowNumber = (int) params.get("first");
		
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			SalesSmmryVO vo=new SalesSmmryVO();
			
			vo.setRowNumber(++rowNumber);
			if (row[0] != null) vo.setRegionId(Long.parseLong(row[0].toString()));
			if (row[1] != null) vo.setRegionName((String) row[1]);
			if (row[2] != null) vo.setInvCount(Integer.parseInt(row[2].toString()));
			if (row[3] == null){vo.setInvAmt(0.00);}else{ vo.setInvAmt(Double.parseDouble(row[3].toString()));}
			if (row[4] == null){vo.setInvPaid(0.00);}else{ vo.setInvPaid(Double.parseDouble(row[4].toString()));}
			if (row[5] == null){vo.setBalance(0.00);}else{ vo.setBalance(Double.parseDouble(row[5].toString()));}
			list.add(vo);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesSmmryVO> getRegionSalesSmmryInvDetailList(Long companyId, Long regionId, SearchParamVO searchParamVO) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		sb.append("select iv.id as id, iv.code as code, iv.dt_inv as invoiceDt, ").
			append("iv.dt_departure as departureDt, iv.amount as invAmt, ").
			append("p.amount as invPaid, ").
			append("iv.balance as balance, ").
			append("iv.id_tour_booking as tourDepId, ifnull(tdp.code, '') as tourCd, iv.id_customer as customerId, ").
			append("usr.user_name as salerName, iv.id_saler as salerId, cm.pc_type_cd as typeCd, ").
			append("concat(ps.last_name, ' ', ps.first_name) as custName, ").
			append("ps.salutation_cd as custSalutation, cm.corporate_name as coName, ").
			append("td.region as regionName ").
		append("from invoice iv ").
			append("left join (select pmnt.id_inv, sum(pmnt.amount) as amount from invoice_pmnt pmnt where pmnt.status_cd = 'A' group by pmnt.id_inv) p on iv.id = p.id_inv ").
			append("left join tour_dep tdp on iv.id_tour_dep=tdp.id, employee ep, sec_user usr, customer cm, person ps, ").
		append("( ").
		append("select  td.id, tt.description as region ").
		append("from tour_dep td, tour_pkg tp, tour_theme tt ").
		append("where td.status_cd = 'AC' and tp.status_cd = 'AC' and tt.status_cd = 'AC' and ").
		append("td.id_tour_pkg = tp.id and tp.id_tour_theme = tt.id ");
		if (regionId != null) sb.append("and tp.id_tour_theme = " + regionId + " ");		
		sb.append(") td ").
		append("where iv.id_company=:idCompany and ").
			append("iv.status_cd!=:statusCdCC and iv.status_cd!=:statusCdVD and iv.id_saler=ep.id and iv.id_tour_dep = td.id and  ").
			append("ep.u_sec_user=usr.uuid and iv.id_customer=cm.id and cm.id_pc=ps.id and iv.doc_type_cd = 'I' ");
		if (searchParamVO.getFromDate() != null) sb.append("and date(iv.dt_inv) >= date('" + sdf.format((Date)searchParamVO.getFromDate()) + "') ");
		if (searchParamVO.getToDate() != null) sb.append("and date(iv.dt_inv) <= date('" + sdf.format((Date)searchParamVO.getToDate()) + "') ");
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("id", LongType.INSTANCE);
		query.addScalar("code");
		query.addScalar("invoiceDt", DateType.INSTANCE);
		query.addScalar("departureDt", DateType.INSTANCE);
		query.addScalar("invAmt", DoubleType.INSTANCE);
		query.addScalar("invPaid", DoubleType.INSTANCE);
		query.addScalar("balance", DoubleType.INSTANCE);
		query.addScalar("customerId", LongType.INSTANCE);
		query.addScalar("tourDepId", LongType.INSTANCE);
		query.addScalar("tourCd");
		query.addScalar("salerId", LongType.INSTANCE);
		query.addScalar("salerName");
		query.addScalar("typeCd");
		query.addScalar("custName");
		query.addScalar("custSalutation");
		query.addScalar("coName");
		query.addScalar("regionName");
		query.setParameter("idCompany", companyId);
		query.setParameter("statusCdCC", CommonConstant.STATUS_CD_CANCELLED);
		query.setParameter("statusCdVD", CommonConstant.STATUS_CD_VOID);
		query.setResultTransformer(Transformers.aliasToBean(SalesSmmryVO.class));
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int getRegionSalesSmmryBookingListSize(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SearchParamVO searchParamVO = (SearchParamVO) params.get("searchParam");
		
		sb.append("select b.id_employee ").
		append("from tour_booking b, employee e, sec_user u, customer c, person p, ").
		append("( ").
			append("select tt.id as id_region, tt.description as region, td.id ").
			append("from tour_dep td, tour_pkg tp, tour_theme tt ").
			append("where td.status_cd = 'AC' and tp.status_cd = 'AC' and tt.status_cd = 'AC' and ").
			append("td.id_tour_pkg = tp.id and tp.id_tour_theme = tt.id ");
		if (searchParamVO.getObj3() != null) sb.append("and tt.id_parent = " + searchParamVO.getObj3() + " ");		
		sb.append(") tdp ").
		append("where b.id_company = :idCompany AND b.id_tour_dep = tdp.id and b.id_cust = c.id and c.id_pc = p.id and b.id_employee = e.id and e.u_sec_user = u.uuid ");
		if (searchParamVO.getFromDate() != null) sb.append("and date(b.dt_created) >= date('" + sdf.format((Date)searchParamVO.getFromDate()) + "') ");
		if (searchParamVO.getToDate() != null) sb.append("and date(b.dt_created) <= date('" + sdf.format((Date)searchParamVO.getToDate()) + "') ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		if (!filters.isEmpty()) {
			sb.append("AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("regionName".equals(entry.getKey())) sb.append("tdp.region LIKE '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" AND ");
			}
			sb.append(") ");
		}
		
		sb.append("group by tdp.id_region ");
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("companyId"));
		List<Object> objList = query.list();
		return objList.size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesSmmryVO> getRegionSalesSmmryBookingList(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SearchParamVO searchParamVO = (SearchParamVO) params.get("searchParam");
		
		sb.append("select b.id, tdp.id_region, tdp.region, ").
			append("sum(if(b.pmnt_status_cd = 'KIV', 1, 0)) as kiv, ").
			append("sum(if(b.pmnt_status_cd = 'DEP', 1, 0)) as depPaid, ").
			append("sum(if(b.pmnt_status_cd = 'FULL', 1, 0)) as paidinfull, ").
			append("sum(if(b.pmnt_status_cd = 'KIVEXP', 1, 0)) as expired, ").
			append("sum(if(b.status_cd IN ('CC','VD'), 1, 0)) as cancelled ").
		append("from tour_booking b, employee e, sec_user u, customer c, person p, ").
		append("( ").
			append("select tt.id as id_region, tt.description as region, td.id ").
			append("from tour_dep td, tour_pkg tp, tour_theme tt ").
			append("where td.status_cd = 'AC' and tp.status_cd = 'AC' and tt.status_cd = 'AC' and ").
			append("td.id_tour_pkg = tp.id and tp.id_tour_theme = tt.id ");
		if (searchParamVO.getObj3() != null) sb.append("and tt.id_parent = " + searchParamVO.getObj3() + " ");		
		sb.append(") tdp ").
		append("where b.id_company = :idCompany AND b.id_tour_dep = tdp.id and b.id_cust = c.id and c.id_pc = p.id and b.id_employee = e.id and e.u_sec_user = u.uuid ");
		if (searchParamVO.getFromDate() != null) sb.append("and date(b.dt_created) >= date('" + sdf.format((Date)searchParamVO.getFromDate()) + "') ");
		if (searchParamVO.getToDate() != null) sb.append("and date(b.dt_created) <= date('" + sdf.format((Date)searchParamVO.getToDate()) + "') ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		
		if (!filters.isEmpty()) {
			sb.append("AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("regionName".equals(entry.getKey())) sb.append("tdp.region LIKE '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" AND ");
			}
			sb.append(") ");
		}
		
		sb.append("group by tdp.id_region ");
		
		sb.append("order by ");
		String sortField = (String) params.get("sortField");
		if (sortField == null) {
			sb.append(" tdp.region ");
		} else {
			if ("regionName".equals(sortField)) sb.append("tdp.region");
			if ("kiv".equals(sortField)) sb.append("sum(if(b.pmnt_status_cd = 'KIV', 1, 0))");
			if ("depPaid".equals(sortField)) sb.append("sum(if(b.pmnt_status_cd = 'DEP', 1, 0))");
			if ("paidinfull".equals(sortField)) sb.append("sum(if(b.pmnt_status_cd = 'FULL', 1, 0))");
			if ("expired".equals(sortField)) sb.append("sum(if(b.pmnt_status_cd = 'KIVEXP', 1, 0)) ");
			if ("cancelled".equals(sortField)) sb.append("sum(if(b.status_cd IN ('CC','VD'), 1, 0))");
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" asc");
			else sb.append(" desc");
		}
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("companyId"));
		query.setFirstResult((Integer) params.get("first"));
		query.setMaxResults((Integer) params.get("pageSize"));
		List<Object> objList = query.list();
		List<SalesSmmryVO> list = new ArrayList<SalesSmmryVO>();
		int rowNumber = (int) params.get("first");
		
		if (CollectionUtils.isNotEmpty(objList)) {
			for (Iterator<Object> it = objList.iterator() ; it.hasNext() ;) {
				Object[] row = (Object[]) it.next();
				SalesSmmryVO vo = new SalesSmmryVO();
				vo.setRowNumber(++rowNumber);
				vo.setId(((BigInteger) row[0]).longValue());
				vo.setRegionId(((BigInteger) row[1]).longValue());
				vo.setRegionName((String) row[2]);
				vo.setKiv(Integer.parseInt(row[3].toString()));
				vo.setDepPaid(Integer.parseInt(row[4].toString()));
				vo.setPaidinfull(Integer.parseInt(row[5].toString()));
				vo.setExpired(Integer.parseInt(row[6].toString()));
				vo.setCancelled(Integer.parseInt(row[7].toString()));
				list.add(vo);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalesSmmryVO> getRegionSalesSmmryBookingDetailList(Long companyId, Long regionId, SearchParamVO searchParamVO) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		sb.append("SELECT b.id AS 'id', b.id_company, b.pmnt_status_cd AS 'pmntStatusCd', b.dt_created AS 'createdDate', b.dt_exp AS 'dtExp', td.code AS 'tourCode', ").
		append("u.user_name AS 'staff', p.salutation_cd, p.last_name, p.first_name, b.status_cd AS 'statusCode', td.region ").
		append("from tour_booking b, employee e, sec_user u, customer c, person p, ").
		append("( ").
			append("select td.id, tt.description as region, td.code ").
			append("from tour_dep td, tour_pkg tp, tour_theme tt ").
			append("where td.status_cd = 'AC' and tp.status_cd = 'AC' and tt.status_cd = 'AC' and ").
			append("td.id_tour_pkg = tp.id and tp.id_tour_theme = tt.id ");
		if (regionId != null) sb.append("and tp.id_tour_theme = " + regionId + " ");		
		sb.append(") td ").
		append("where b.id_company = :idCompany AND b.id_tour_dep = td.id and b.id_cust = c.id and c.id_pc = p.id and b.id_employee = e.id and e.u_sec_user = u.uuid ");
		if (searchParamVO.getFromDate() != null) sb.append("and date(b.dt_created) >= date('" + sdf.format((Date)searchParamVO.getFromDate()) + "') ");
		if (searchParamVO.getToDate() != null) sb.append("and date(b.dt_created) <= date('" + sdf.format((Date)searchParamVO.getToDate()) + "') ");
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.setParameter("idCompany", companyId);
		List<Object> objList = query.list();
		List<SalesSmmryVO> list = new ArrayList<SalesSmmryVO>();
		
		if (CollectionUtils.isNotEmpty(objList)) {
			for (Iterator<Object> it = objList.iterator() ; it.hasNext() ;) {
				Object[] row = (Object[]) it.next();
				SalesSmmryVO vo = new SalesSmmryVO();
				vo.setId(((BigInteger) row[0]).longValue());
				vo.setIdCompany(((BigInteger) row[1]).longValue());
				vo.setPmntStatusCd((String) row[2]);
				vo.setCreatedDate((Date) row[3]);
				vo.setDtExp((Date) row[4]);
				vo.setTourCode((String) row[5]);
				vo.setSalerName((String) row[6]);
				vo.setSalutationCd((String) row[7]);
				vo.setLastName((String) row[8]);
				vo.setFirstName((String) row[9]);
				vo.setStatusCode((String) row[10]);
				vo.setRegionName((String) row[11]);
				list.add(vo);
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesSmmryVO> getSalesSmmryByMonthsList(Long companyId) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("select YEAR(iv.dt_inv), MONTHNAME(STR_TO_DATE(MONTH(iv.dt_inv), '%m')), count(iv.id_saler) as invCount, ").
			append("sum(iv.amount) as invAmt, ").
			append("sum(p.amount) as invPaid, ").
			append("sum(iv.balance) as balance ").
		append("from invoice iv ").
			append("left join (select pmnt.id_inv, sum(pmnt.amount) as amount from invoice_pmnt pmnt where pmnt.status_cd = 'A' group by pmnt.id_inv) p on iv.id = p.id_inv ").
			append("left join tour_dep tdp on iv.id_tour_dep=tdp.id, employee ep, sec_user usr, customer cm, person ps ").
		append("where iv.id_company=:idCompany and iv.id_saler=ep.id and ").
			append("iv.status_cd!='CC' and iv.status_cd!='VD' and iv.id_saler=ep.id and ").
			append("ep.u_sec_user=usr.uuid and iv.id_customer=cm.id and cm.id_pc=ps.id and iv.doc_type_cd = 'I' ").
		append("group by YEAR(iv.dt_inv), MONTH(iv.dt_inv) ").
		append("order by YEAR(iv.dt_inv) desc, MONTH(iv.dt_inv) desc ");
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.setParameter("idCompany", companyId);
		List<Object> objList = query.list();
		List<SalesSmmryVO> list = new ArrayList<SalesSmmryVO>();
		
		if (CollectionUtils.isNotEmpty(objList)) {
			for (Iterator<Object> it = objList.iterator(); it.hasNext();) {
				Object[] row = (Object[]) it.next();
				SalesSmmryVO vo = new SalesSmmryVO();
				vo.setYears(row[0].toString());
				vo.setMonths((String) row[1]);
				vo.setInvCount(Integer.parseInt(row[2].toString()));
				vo.setInvAmt(Double.parseDouble(row[3].toString()));
				vo.setInvPaid(Double.parseDouble(row[4].toString()));
				vo.setBalance(Double.parseDouble(row[5].toString()));
				list.add(vo);
			}
		}
		return list;
	}
}
