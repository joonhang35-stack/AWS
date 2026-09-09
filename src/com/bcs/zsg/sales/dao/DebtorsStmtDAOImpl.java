package com.bcs.zsg.sales.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.primefaces.model.SortOrder;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.sales.vo.DebtorsStmtVO;

public class DebtorsStmtDAOImpl extends BaseHibernateDAO implements DebtorsStmtDAO {

	@SuppressWarnings("unchecked")
	@Override
	public int getDebtorsListSize(Map<String, Object> params) throws BusinessException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select count(i.id) from invoice i ");
		sb.append("left join(select sum(ip.amount) as 'amtPaid',ip.id_inv as 'idInv' from invoice_pmnt ip where ip.status_cd='A' group by idInv) a on i.id=a.idInv ");
		sb.append("left join(select group_concat(eo.code separator ',') as 'eo_num', eoi.id_inv as 'idinv' from ex_order eo,");
		sb.append("ex_order_inv eoi where eo.id=eoi.id_eo and eo.id_company=:idCompany group by eoi.id_inv) b on b.idinv=i.id ");
		sb.append("left join customer cm on i.id_customer=cm.id ");
		sb.append("left join person p on p.id=cm.id_pc ");
		sb.append("where i.status_cd not in ('VD','CC') and i.balance!=0.00 and i.id_company=:idCompany ");
	
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		String eoFilter = null;
		if(filters.containsKey("eoNo")) {
			eoFilter = filters.get("eoNo");
			filters.remove("eoNo");
		}
		
		if (!filters.isEmpty()) {
			sb.append("and ");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("custCode".equals(entry.getKey())) sb.append("cm.code like '%").append(entry.getValue()).append("%'");
				else if ("custName".equals(entry.getKey())) sb.append("concat(p.last_name, ' ', p.first_name) like '%").append(entry.getValue()).append("%'");
				else if ("invoiceCode".equals(entry.getKey())) sb.append("i.code like '%").append(entry.getValue()).append("%'");
				else if ("attnTo".equals(entry.getKey())) sb.append("i.attn_to like '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
			}
			sb.append("");
		}
		
		SearchParamVO searchParamVO = (SearchParamVO) params.get("searchParam");
		if (searchParamVO.getObj2() !=null) {
			SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
				sb.append("and date(i.dt_inv) between '").append(dateFmt.format(searchParamVO.getFromDate())).
				append("' and '").append(dateFmt.format(searchParamVO.getToDate())).append("' ");
		}
		if (searchParamVO.getObj3() != null) {
			sb.append("and concat(p.last_name, ' ', p.first_name) like '%").append(searchParamVO.getObj3()).append("%' ");
		}
		if(searchParamVO.getObj1()!=null)
		{
			if ("current".equals(searchParamVO.getObj1())) sb.append("and date(i.inv_due)>=curdate() ");
			else if("30_days".equals(searchParamVO.getObj1())) sb.append("and date(i.inv_due)>=DATE_SUB(curdate(),INTERVAL 30 day) AND  date(i.inv_due)<curdate() ");
			else if("60_days".equals(searchParamVO.getObj1())) sb.append("and date(i.inv_due)>=DATE_SUB(curdate(),INTERVAL 60 day) AND date(i.inv_due)<DATE_SUB(curdate(),INTERVAL 30 day) ");
			else if("90_days".equals(searchParamVO.getObj1())) sb.append("and date(i.inv_due)>=DATE_SUB(curdate(),INTERVAL 90 day) AND date(i.inv_due)<DATE_SUB(curdate(),INTERVAL 60 day) ");
			else if("120_days".equals(searchParamVO.getObj1())) sb.append("and date(i.inv_due)>=DATE_SUB(curdate(),INTERVAL 120 day) AND date(i.inv_due)<DATE_SUB(curdate(),INTERVAL 90 day) ");
			else if("over_120_days".equals(searchParamVO.getObj1())) sb.append("and date(i.inv_due)<DATE_SUB(curdate(),INTERVAL 120 day) ");
		}	
		sb.append("group by i.id ");
		if (eoFilter != null) {
			sb.append(" having group_concat(b.eo_num separator ',') like '%").append(eoFilter).append("%'");
			filters.put("eoNo", eoFilter);
		}
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("companyId"));
		List<Object> objList = query.list();
		return objList.size();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DebtorsStmtVO> getDebtorsList(Map<String, Object> params)
			throws BusinessException {
		// TODO Auto-generated method stub
		String filteredCustCode=null;
		String filteredCustName=null;
		String filteredInvCode=null;
		String filteredEoNo=null;
		String filteredAttnTo=null;
		StringBuilder sb = new StringBuilder();
		sb.append("select cm.code as 'custCd',");
		sb.append("i.code as invCd,");
		sb.append("i.dt_inv as invDt,");
		sb.append("b.eo_num as EoNo,");
		sb.append("i.attn_to as invAttn,");
		sb.append("i.amount as invAmt,");
		sb.append("a.amtPaid as amtpaid,");
		sb.append("i.balance as invBal,");
		sb.append("i.inv_due as invDue,");
		sb.append("p.first_name as 'firstName',");
		sb.append("p.last_name as 'lastName',");
		sb.append("p.salutation_cd as 'salutationCd' ");
		sb.append("from invoice i ");
		sb.append("left join(select sum(ip.amount) as 'amtPaid',ip.id_inv as 'idInv' from invoice_pmnt ip where ip.status_cd='A' group by idInv) a on i.id=a.idInv ");
		sb.append("left join(select group_concat(eo.code separator ',') as 'eo_num', eoi.id_inv as 'idinv' from ex_order eo,");
		sb.append("ex_order_inv eoi where eo.id=eoi.id_eo and eo.id_company=:idCompany group by eoi.id_inv) b on b.idinv=i.id ");
		sb.append("left join customer cm on i.id_customer=cm.id ");
		sb.append("left join person p on p.id=cm.id_pc ");
		sb.append("where i.status_cd not in ('VD','CC') and i.balance!=0.00 and i.id_company=:idCompany ");
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		String eoFilter = null;
				
		
		if(filters.containsKey("eoNo")) {
			eoFilter = filters.get("eoNo");
			filters.remove("eoNo");
			
		}
		if (!filters.isEmpty()) {
			sb.append("and ");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("custCode".equals(entry.getKey())){
					filteredCustCode=entry.getValue();
					sb.append("cm.code like '%").append(entry.getValue()).append("%'");
				}
				else if ("custName".equals(entry.getKey())){
					filteredCustName=entry.getValue();
					sb.append("concat(p.last_name, ' ', p.first_name) like '%").append(entry.getValue()).append("%'");
				}
				else if ("invoiceCode".equals(entry.getKey())) {
					filteredInvCode=entry.getValue();
					sb.append("i.code like '%").append(entry.getValue()).append("%'");
				}
				else if ("eoNo".equals(entry.getKey())){
					filteredEoNo=entry.getValue();
					eoFilter = entry.getValue();
				}
				else if ("attnTo".equals(entry.getKey())){
					filteredAttnTo=entry.getValue();
					sb.append("i.attn_to like '%").append(entry.getValue()).append("%'");
				}
				if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
				
			}
			sb.append("");
		}
		SearchParamVO searchParamVO = (SearchParamVO) params.get("searchParam");
		if (searchParamVO.getObj2() !=null) {
			SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
			sb.append("and date(i.dt_inv) between '").append(dateFmt.format(searchParamVO.getFromDate())).
				append("' and '").append(dateFmt.format(searchParamVO.getToDate())).append("' ");
		}
		if (searchParamVO.getObj3() != null) {
			sb.append("and concat(p.last_name, ' ', p.first_name) like '%").append(searchParamVO.getObj3()).append("%'");
		}
		if(searchParamVO.getObj1()!=null)
		{
			if ("current".equals(searchParamVO.getObj1())) sb.append("and date(i.inv_due)>=curdate() ");
			else if("30_days".equals(searchParamVO.getObj1())) sb.append("and date(i.inv_due)>=DATE_SUB(curdate(),INTERVAL 30 day) AND  date(i.inv_due)<curdate() ");
			else if("60_days".equals(searchParamVO.getObj1())) sb.append("and date(i.inv_due)>=DATE_SUB(curdate(),INTERVAL 60 day) AND date(i.inv_due)<DATE_SUB(curdate(),INTERVAL 30 day) ");
			else if("90_days".equals(searchParamVO.getObj1())) sb.append("and date(i.inv_due)>=DATE_SUB(curdate(),INTERVAL 90 day) AND date(i.inv_due)<DATE_SUB(curdate(),INTERVAL 60 day) ");
			else if("120_days".equals(searchParamVO.getObj1())) sb.append("and date(i.inv_due)>=DATE_SUB(curdate(),INTERVAL 120 day) AND date(i.inv_due)<DATE_SUB(curdate(),INTERVAL 90 day) ");
			else if("over_120_days".equals(searchParamVO.getObj1())) sb.append("and date(i.inv_due)<DATE_SUB(curdate(),INTERVAL 120 day) ");
		}	
		sb.append("group by i.id ");
		if (eoFilter != null){
			sb.append(" having group_concat(b.eo_num separator ',') like '%").append(eoFilter).append("%' ");
			filters.put("eoNo", eoFilter);
		}
	
		
		sb.append("order by ");
		String sortField = (String) params.get("sortField");
		if (sortField == null){
			if (filters.isEmpty()) {
			sb.append(" invDt desc , invCd desc ");
			}
			if(filteredCustCode!=null)
			{ 
				sb.append("case when custCd like '").append(filteredCustCode).append("%' then custCd ")
				.append("else concat('{0} ', custCd) end");
			}
			if(filteredCustName!=null)
			{ 
				sb.append("case when concat(lastName, ' ', firstName) like '").append(filteredCustName).append("%' then custCd ")
				.append("else concat('{0} ', concat(lastName, ' ', firstName)) end");
			}
			if(filteredInvCode!=null)
			{ 
				sb.append("case when invCd like '").append(filteredInvCode).append("%' then invCd ")
				.append("else concat('{0} ', invCd) end");
			}
			if(eoFilter!=null)
			{ 
				 sb.append("case when b.eo_num like '").append(eoFilter).append("%' then b.eo_num else concat('{0}', b.eo_num) end");
				
			}
			if(filteredAttnTo!=null)
			{ 
				sb.append("case when invAttn like '").append(filteredAttnTo).append("%' then invAttn ")
				.append("else concat('{0} ', invAttn) end");
			}
		}
		else {
			
			if ("invoiceCode".equals(sortField)) sb.append("cast(invCd as decimal)");
			else if ("eoNo".equals(sortField)) sb.append("EoNo");
			else if ("attnTo".equals(sortField)) sb.append("invAttn");
			else if ("invAmt".equals(sortField)) sb.append("invAmt");
			else if ("invPaid".equals(sortField)) sb.append("amtpaid");
			else if ("invDue".equals(sortField)) sb.append("invBal");
			else if ("invoiceDt".equals(sortField)) sb.append("invDt");
			else if ("dueDt".equals(sortField)) sb.append("invDue");
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" asc");
			else sb.append(" desc");
		}
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("companyId"));
		query.setFirstResult((Integer) params.get("first"));
		query.setMaxResults((Integer) params.get("pageSize"));
		List<Object> results = query.list();
		List<DebtorsStmtVO> debitorStmtList = new ArrayList<DebtorsStmtVO>();
		int rowNumber = (int) params.get("first");
		
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			DebtorsStmtVO vo=new DebtorsStmtVO();
			
			vo.setRowNumber(++rowNumber);
			if (row[0] != null) vo.setCustCode((String) row[0]);
			if (row[1] != null) vo.setInvoiceCode((String) row[1]);
			if (row[2] != null) vo.setInvoiceDt((Date) row[2]);
			if (row[3] != null) vo.setEoNo((String) row[3]);
			if (row[4] != null) vo.setAttnTo((String) row[4]);
			if (row[5] != null) vo.setInvAmt(Double.parseDouble(row[5].toString()));
			if (row[6] == null){vo.setInvPaid(0.00);}else{ vo.setInvPaid(Double.parseDouble(row[6].toString()));}
			if (row[7] != null) vo.setInvDue(Double.parseDouble(row[7].toString()));
			if (row[8] != null) vo.setDueDt((Date) row[8]);
			vo.setCustName(row[9].toString() + " " + row[10].toString());   
			vo.setCustSalutation(row[11].toString());
			debitorStmtList.add(vo);
		}
		return debitorStmtList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DebtorsStmtVO> getDebtorStmtList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		String invPrefix = LookupItemUtils.getInvPrefix(idCompany);
		String psPrefix = LookupItemUtils.getPsPrefix(idCompany);
		
		sb.append("select a.* from ( ").
			append("select ").
				append("cm.code as custCode, cm.pc_type_cd as pcTypeCd, ").
				append("concat(UPPER(ps.salutation_cd), ' ', ps.last_name, ' ', ps.first_name) as custName, cm.corporate_name as companyName, ").
				append("i.dt_inv as invoiceDt, 'Invoice' as transType, ").
				append("case when i.doc_type_cd = 'P' then concat('" + psPrefix + " ', i.ps_no) else concat('" + invPrefix + " ', i.code) end as invoiceCode, '' as docNo, '' as refNo, ").
				append("i.amount - (select case when sum(ip.amount) is null then 0 else sum(ip.amount) end from invoice_pmnt ip where ip.id_inv = i.id and ip.status_cd = :statusCd and date(ip.dt_pmnt) < date(:fromDate)) as debit, null as credit, ").
				append("concat('" + psPrefix + " ', i.ps_no) as psNo, i.doc_type_cd as docTypeCd ").
			append("from invoice i, customer cm, person ps ").
			append("where i.id_company = :idCompany and i.id_customer=cm.id and cm.id_pc=ps.id ").
				append("and i.status_cd != 'VD' ").
				append("and i.doc_type_cd in ('I', 'P') ").
				append("and ifnull(i.doc_type_status, '') not in ('CV','SP','CL') ").
//				append("and i.doc_type_cd = 'I' ").
				append("and i.id_customer = :custId ").
				append("and date(i.dt_inv) < date(:fromDate) ").
				append("and i.amount - (select case when sum(ip.amount) is null then 0 else sum(ip.amount) end from invoice_pmnt ip where ip.id_inv = i.id and ip.status_cd = :statusCd and date(ip.dt_pmnt) < date(:fromDate)) != 0 ").
			append("union all ").
			append("select ").
				append("cm.code as custCode, cm.pc_type_cd as pcTypeCd, ").
				append("concat(UPPER(ps.salutation_cd), ' ', ps.last_name, ' ', ps.first_name) as custName, cm.corporate_name as companyName, ").
				append("i.dt_inv as invoiceDt, 'Invoice' as transType, ").
				append("case when i.doc_type_cd = 'P' then concat('" + psPrefix + " ', i.ps_no) else concat('" + invPrefix + " ', i.code) end as invoiceCode, '' as docNo, '' as refNo, ").
				append("i.amount as debit, null as credit, ").
				append("concat('" + psPrefix + " ', i.ps_no) as psNo, i.doc_type_cd as docTypeCd ").
			append("from invoice i, customer cm, person ps ").
			append("where i.id_company = :idCompany and i.id_customer=cm.id and cm.id_pc=ps.id ").
				append("and i.status_cd != 'VD' ").
				append("and i.doc_type_cd in ('I', 'P') ").
				append("and ifnull(i.doc_type_status, '') not in ('CV','SP','CL') ").
//				append("and i.doc_type_cd = 'I' ").
				append("and i.id_customer = :custId ").
				append("and date(i.dt_inv) between date(:fromDate) and date(:toDate) ").
			append("union all ").
			append("select ").
				append("cm.code as custCode, cm.pc_type_cd as pcTypeCd, ").
				append("concat(UPPER(ps.salutation_cd), ' ', ps.last_name, ' ', ps.first_name) as custName, cm.corporate_name as companyName, ").
				append("ip.dt_pmnt as invoiceDt, 'Payment' as transType, ").
				append("case when i.doc_type_cd = 'P' then concat('" + psPrefix + " ', i.ps_no) else concat('" + invPrefix + " ', i.code) end as invoiceCode, ip.ref_no as docNo, ip.code as refNo, ").
				append("null as debit, ip.amount as credit, ").
				append("concat('" + psPrefix + " ', i.ps_no) as psNo, i.doc_type_cd as docTypeCd ").
			append("from invoice i, invoice_pmnt ip, customer cm, person ps ").
			append("where i.id=ip.id_inv and ip.status_cd = :statusCd and i.id_company = :idCompany and i.id_customer=cm.id and cm.id_pc=ps.id ").
				append("and i.status_cd != 'VD' ").
				append("and i.doc_type_cd in ('I', 'P') ").
				append("and ifnull(i.doc_type_status, '') not in ('CV','SP','CL') ").
//				append("and i.doc_type_cd = 'I' ").
				append("and i.id_customer = :custId ").
				append("and date(ip.dt_pmnt) between date(:fromDate) and date(:toDate) ").
		append(") a order by a.invoiceDt, cast(a.invoiceCode as decimal), field(a.transType, 'Invoice', 'Payment') ");
		
		System.out.println(sb.toString());
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("custCode");
		query.addScalar("pcTypeCd");
		query.addScalar("custName");
		query.addScalar("companyName");
		query.addScalar("invoiceDt", DateType.INSTANCE);
		query.addScalar("transType");
		query.addScalar("invoiceCode");
		query.addScalar("docNo");
		query.addScalar("refNo");
		
		query.addScalar("debit", DoubleType.INSTANCE);
		query.addScalar("credit", DoubleType.INSTANCE);
		
		query.addScalar("psNo");
		query.addScalar("docTypeCd");
		
		query.setParameter("idCompany", idCompany);
		query.setParameter("custId", searchParamVO.getObj4());
		query.setParameter("fromDate", (Date) searchParamVO.getObj2());
		query.setParameter("toDate", (Date) searchParamVO.getObj3());
		query.setParameter("statusCd", BaseConstant.STATUS_ACTIVE);
		query.setResultTransformer(Transformers.aliasToBean(DebtorsStmtVO.class));
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DebtorsStmtVO getDebtorStmtTtl(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("select ").
		// Beginning Balance
		append("(select ifnull(sum(i.amount),0.00) from invoice i ").
		append("where i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd in ('I', 'P') and ifnull(i.doc_type_status, '') not in ('CV','SP','CL') and i.id_customer = :custId ").
		append("and date(i.dt_inv)<date(:fromDate)) - ").
		append("(select ifnull(sum(ip.amount),0.00) from invoice i, invoice_pmnt ip ").
		append("where i.id=ip.id_inv and ip.status_cd = :statusCd and i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd = 'I' and i.id_customer =  :custId ").
		append("and date(ip.dt_pmnt)<date(:fromDate)) as beginBal, ").
		
		// Current
		append("(select ifnull(sum(i.amount - ").
			append("(select case when sum(ip.amount) is null then 0 else sum(ip.amount) end from invoice_pmnt ip where ip.id_inv = i.id and ip.status_cd = :statusCd ").
			append("and date(ip.dt_pmnt)<=date(:toDate))),0.00) ").
		append("from invoice i ").
		append("where i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd in ('I', 'P') and ifnull(i.doc_type_status, '') not in ('CV','SP','CL') and i.id_customer = :custId ").
			append("and date(i.dt_inv)>=date(date_add(LAST_DAY(date_add(:toDate, interval -1 MONTH)), interval 1 DAY)) ").
			append("and date(i.dt_inv)<=date(:toDate)) as current, ").
		
		// 31-60 Days
		append("(select ifnull(sum(i.amount - ").
			append("(select case when sum(ip.amount) is null then 0 else sum(ip.amount) end from invoice_pmnt ip where ip.id_inv = i.id and ip.status_cd = :statusCd ").
			append("and date(ip.dt_pmnt)<=date(:toDate))),0.00) ").
		append("from invoice i ").
		append("where i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd in ('I', 'P') and ifnull(i.doc_type_status, '') not in ('CV','SP','CL') and i.id_customer = :custId ").
			append("and date(i.dt_inv)>=date(date_add(LAST_DAY(date_add(:toDate, interval -2 MONTH)), interval 1 DAY)) ").
			append("and date(i.dt_inv)<=date(LAST_DAY(date_add(:toDate, interval -1 MONTH)))) as days31_60, ").
			
		// 61-90 Days
		append("(select ifnull(sum(i.amount - ").
			append("(select case when sum(ip.amount) is null then 0 else sum(ip.amount) end from invoice_pmnt ip where ip.id_inv = i.id and ip.status_cd = :statusCd ").
			append("and date(ip.dt_pmnt)<=date(:toDate))),0.00) ").
		append("from invoice i ").
		append("where i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd in ('I', 'P') and ifnull(i.doc_type_status, '') not in ('CV','SP','CL') and i.id_customer = :custId ").
			append("and date(i.dt_inv)>=date(date_add(LAST_DAY(date_add(:toDate, interval -3 MONTH)), interval 1 DAY)) ").
			append("and date(i.dt_inv)<=date(LAST_DAY(date_add(:toDate, interval -2 MONTH)))) as days61_90, ").
		
		// 91-120 Days
		append("(select ifnull(sum(i.amount - ").
			append("(select case when sum(ip.amount) is null then 0 else sum(ip.amount) end from invoice_pmnt ip where ip.id_inv = i.id and ip.status_cd = :statusCd ").
			append("and date(ip.dt_pmnt)<=date(:toDate))),0.00) ").
		append("from invoice i ").
		append("where i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd in ('I', 'P') and ifnull(i.doc_type_status, '') not in ('CV','SP','CL') and i.id_customer = :custId ").
			append("and date(i.dt_inv)>=date(date_add(LAST_DAY(date_add(:toDate, interval -4 MONTH)), interval 1 DAY)) ").
			append("and date(i.dt_inv)<=date(LAST_DAY(date_add(:toDate, interval -3 MONTH)))) as days91_120, ").
			
		// 120 Days After
		append("(select ifnull(sum(i.amount - ").
			append("(select case when sum(ip.amount) is null then 0 else sum(ip.amount) end from invoice_pmnt ip where ip.id_inv = i.id and ip.status_cd = :statusCd ").
			append("and date(ip.dt_pmnt)<=date(:toDate))),0.00) ").
		append("from invoice i ").
		append("where i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd in ('I', 'P') and ifnull(i.doc_type_status, '') not in ('CV','SP','CL') and i.id_customer = :custId ").
			append("and date(i.dt_inv)<=date(LAST_DAY(date_add(:toDate, interval -4 MONTH)))) as days120After ");
		
		/*
		// Current
		append("(select ifnull(sum(i.amount),0.00) from invoice i ").
		append("where i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd = 'I' and i.id_customer = :custId ").
		append("and date(i.dt_inv) between date(:fromDate) and date(:toDate)) - ").
		append("(select ifnull(sum(ip.amount),0.00) from invoice i, invoice_pmnt ip ").
		append("where i.id=ip.id_inv and ip.status_cd = :statusCd and i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd = 'I' and i.id_customer =  :custId ").
		append("and date(ip.dt_pmnt) between date(:fromDate) and date(:toDate)) as current, ").
		
		// 1-30 Days
		//append("(select ifnull(sum(i.amount),0.00) from invoice i ").
		//append("where i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd = 'I' and i.id_customer = :custId ").
		//append("and date(i.dt_inv)>=DATE_SUB(date(:toDate),INTERVAL 30 day) and date(i.dt_inv)<date(:toDate)) - ").
		//append("(select ifnull(sum(ip.amount),0.00) from invoice i, invoice_pmnt ip ").
		//append("where i.id=ip.id_inv and ip.status_cd = :statusCd and i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd = 'I' and i.id_customer =  :custId ").
		//append("and date(ip.dt_pmnt)>=DATE_SUB(date(:toDate),INTERVAL 30 day) and date(ip.dt_pmnt)<date(:toDate)) as days01_30, ").
		
		// 31-60 Days
		append("(select ifnull(sum(i.amount),0.00) from invoice i ").
		append("where i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd = 'I' and i.id_customer = :custId ").
		append("and date(i.dt_inv)>=DATE_SUB(date(:toDate),INTERVAL 60 day) and date(i.dt_inv)<DATE_SUB(date(:toDate),INTERVAL 30 day)) - ").
		append("(select ifnull(sum(ip.amount),0.00) from invoice i, invoice_pmnt ip ").
		append("where i.id=ip.id_inv and ip.status_cd = :statusCd and i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd = 'I' and i.id_customer =  :custId ").
		append("and date(ip.dt_pmnt)>=DATE_SUB(date(:toDate),INTERVAL 60 day) and date(ip.dt_pmnt)<DATE_SUB(date(:toDate),INTERVAL 30 day)) as days31_60, ").
		
		// 61-90 Days
		append("(select ifnull(sum(i.amount),0.00) from invoice i ").
		append("where i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd = 'I' and i.id_customer = :custId ").
		append("and date(i.dt_inv)>=DATE_SUB(date(:toDate),INTERVAL 90 day) AND date(i.dt_inv)<DATE_SUB(date(:toDate),INTERVAL 60 day)) - ").
		append("(select ifnull(sum(ip.amount),0.00) from invoice i, invoice_pmnt ip ").
		append("where i.id=ip.id_inv and ip.status_cd = :statusCd and i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd = 'I' and i.id_customer =  :custId ").
		append("and date(ip.dt_pmnt)>=DATE_SUB(date(:toDate),INTERVAL 90 day) and date(ip.dt_pmnt)<DATE_SUB(date(:toDate),INTERVAL 60 day)) as days61_90, ").
		
		// 91-120 Days
		append("(select ifnull(sum(i.amount),0.00) from invoice i ").
		append("where i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd = 'I' and i.id_customer = :custId ").
		append("and date(i.dt_inv)>=DATE_SUB(date(:toDate),INTERVAL 120 day) and date(i.dt_inv)<DATE_SUB(date(:toDate),INTERVAL 90 day)) - ").
		append("(select ifnull(sum(ip.amount),0.00) from invoice i, invoice_pmnt ip ").
		append("where i.id=ip.id_inv and ip.status_cd = :statusCd and i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd = 'I' and i.id_customer =  :custId ").
		append("and date(ip.dt_pmnt)>=DATE_SUB(date(:toDate),INTERVAL 120 day) and date(ip.dt_pmnt)<DATE_SUB(date(:toDate),INTERVAL 90 day)) as days91_120, ").
		
		// 120 Days After
		append("(select ifnull(sum(i.amount),0.00) from invoice i ").
		append("where i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd = 'I' and i.id_customer = :custId ").
		append("and date(i.dt_inv)<DATE_SUB(date(:toDate),INTERVAL 120 day)) - ").
		append("(select ifnull(sum(ip.amount),0.00) from invoice i, invoice_pmnt ip ").
		append("where i.id=ip.id_inv and ip.status_cd = :statusCd and i.id_company = :idCompany and i.status_cd != 'VD' and i.doc_type_cd = 'I' and i.id_customer =  :custId ").
		append("and date(ip.dt_pmnt)<DATE_SUB(date(:toDate),INTERVAL 120 day)) as days120After ");
		*/
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.setParameter("idCompany", idCompany);
		query.setParameter("custId", searchParamVO.getObj4());
		query.setParameter("fromDate", (Date) searchParamVO.getObj2());
		query.setParameter("toDate", (Date) searchParamVO.getObj3());
		query.setParameter("statusCd", BaseConstant.STATUS_ACTIVE);
		
		Object[] result = (Object[]) query.uniqueResult();
		DebtorsStmtVO debtorsStmtVO = new DebtorsStmtVO();
		debtorsStmtVO.setBeginningBal((Double) result[0]);
		debtorsStmtVO.setCurrent((Double) result[1]);
		debtorsStmtVO.setDays31_60((Double) result[2]);
		debtorsStmtVO.setDays61_90((Double) result[3]);
		debtorsStmtVO.setDays91_120((Double) result[4]);
		debtorsStmtVO.setDays120After((Double) result[5]);
		
		return debtorsStmtVO;
	}
}
