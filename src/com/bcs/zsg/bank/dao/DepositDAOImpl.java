package com.bcs.zsg.bank.dao;
 
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.bank.vo.InvPmntCBLinkVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.core.helper.BaseContext;
import com.bcs.zsg.sales.dao.InvoiceDAO;
import com.bcs.zsg.sales.vo.InvoicePaymentAttachmentVO;
import com.bcs.zsg.sales.vo.InvoicePaymentVO;

public class DepositDAOImpl extends BaseHibernateDAO implements DepositDAO {
	
	@Autowired
	private InvoiceDAO invoiceDAO;
	
	private SimpleDateFormat dateFmt = new SimpleDateFormat("yyy-MM-dd");

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.DepositDAO#getAcctTransCashBook(java.lang.String, java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	public AcctTransViewVO getAcctTransCashBook(String sysNo,  String transTypeCd, String sysCode, Long CompId) throws BusinessException {
		Criteria criteria = getSession().createCriteria(AcctTransViewVO.class);
		criteria.add(Restrictions.eq("sysNo", sysNo));
		criteria.add(Restrictions.eq("sysCode",sysCode));
		criteria.add(Restrictions.eq("type",transTypeCd));
		criteria.add(Restrictions.eq("companyId",CompId));
		return (AcctTransViewVO)criteria.uniqueResult();
	}


	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.DepositDAO#getInvoicePaymentListByCashbook(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoicePaymentVO> getInvoicePaymentListByCashbook(Long idCashBook) throws BusinessException{
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT ");
	    sb.append("ip.id, i.id as invId, ip.dt_pmnt, ip.pmnt_type_cd, ip.ref_no, ip.received_from, ip.amount, t.code AS tourcode, ");
	    sb.append("i.id AS invoiceId, i.code AS invoiceNo, '' AS custCode, 0 AS idCust, ip.pmnt_for, 1 AS idBank, '' AS pmntTypeName, ");
	    sb.append("'' AS bankName, ip.dt_upd, ");
	    sb.append("'1' AS upd_hist_list ");
	    sb.append(",(select GROUP_CONCAT(CONCAT(ifnull(ipa.scanned_path, ''),'#,#', ifnull(ipa.file_path, '')) ORDER BY ipa.id SEPARATOR '#-#') ");
	    sb.append("from invoice_pmnt_attachment ipa where ipa.status_cd = 'A' and ip.id = ipa.id_inv_pmnt) AS attachment, ip.upd_by, i.doc_type_cd, i.ps_no, i.doc_type_status ");
	    sb.append("FROM invoice_pmnt ip ");
	    sb.append("LEFT JOIN (SELECT id, code, id_tour_dep, doc_type_cd, ps_no, doc_type_status FROM invoice) i ON ip.id_inv = i.id ");
	    sb.append("LEFT JOIN (SELECT id, code FROM tour_dep) t ON i.id_tour_dep = t.id ");
	    sb.append("JOIN inv_pmnt_cashbook_link l ON ip.id = l.id_inv_pmnt ");
	    sb.append("WHERE l.id_cash_book = ").append(idCashBook).append(" AND l.status_cd = 'A'");
	    
		return MapInvoicePayment(createSQLQuery(sb.toString()).list());
	}

	/*
	 * 
	 * @param query
	 * @return
	 */
	private List<InvoicePaymentVO> MapInvoicePayment(List<Object> query) {
		InvoicePaymentVO vo;
		List<InvoicePaymentVO> ls = new ArrayList<InvoicePaymentVO>();
		
		for (int i = 0; i < query.size(); i++) {
			Object[] row = (Object[]) query.get(i);
			vo = new InvoicePaymentVO();
			if (row[0] != null) vo.setId(((BigInteger) row[0]).longValue()); 
			if (row[1] != null) vo.setInvId(Long.parseLong(row[1].toString())); 
			if (row[2] != null) vo.setPmntDt((Date) row[2]); 
			if (row[3] != null) vo.setPmntTypeCd(((String) row[3])); 
			if (row[4] != null) vo.setRefNo(((String) row[4])); 
			if (row[5] != null) vo.setRecievedFr((String) row[5]); 
			if (row[6] != null) vo.setAmount(Double.parseDouble( row[6].toString())); 
			if (row[7] != null) vo.setTourCode(row[7].toString());
			if (row[8] != null) vo.setInvoiceId(Long.parseLong(row[8].toString()));
			if (row[9] != null) vo.setInvoiceNo(row[9].toString());
			if (row[10] != null) vo.setCode(row[10].toString()); // custCode
			if (row[11] != null) vo.setCustId(Long.parseLong(row[11].toString()));
			if (row[12] != null) vo.setPmntFor(row[12].toString());
			if (row[13] != null) vo.setBankId(Long.parseLong(row[13].toString()));
			if (row[14] != null) vo.setPmntTypeName((String) row[14]);
			if (row[15] != null) vo.setBankName((String) row[15]);
			if (row[16] != null) vo.setCreatedDate((Date) row[16]); // Updated Date
			if (row[17] != null) vo.setInvPmntTooltip((String) row[17]);
			
			// For Add/Edit Deposit - Payment List (Attachment) / Add Invoice Payment List (Button)
			if (StringUtils.isNotEmpty(vo.getInvPmntTooltip()) && "1".equals(vo.getInvPmntTooltip())) {
				if (vo.getInvoiceId() != null && vo.getId() != null) {
					 try {
						vo.setInvPmntTooltip(invoiceDAO.getInvPmntHistTooltip(vo.getInvoiceId(), vo.getId()));
					} catch (BusinessException e) {
						vo.setInvPmntTooltip("");
					}
				}
			}
			
			if (row.length > 18 && row[18] != null) {
				vo.setInvoicePaymentAttachmentList(new ArrayList<InvoicePaymentAttachmentVO>());
				String[] attachment = row[18].toString().split("#-#");
				
				for (int j = 0; j < attachment.length; j++) {
					InvoicePaymentAttachmentVO attVO = new InvoicePaymentAttachmentVO();
					String[] pathNname = attachment[j].split("#,#");
					attVO.setScannedPath(pathNname[0]);
					attVO.setFilePath(pathNname[1]);
					vo.getInvoicePaymentAttachmentList().add(attVO);
				}
			}
			
			if (row.length > 19 && row[19] != null) vo.setUpdatedBy((String) row[19]);
			if (row.length > 20 && row[20] != null) vo.setInvoiceDocTypeCd((String) row[20]);
			if (row.length > 21 && row[21] != null) vo.setPsNo((String) row[21]);
			if (row.length > 22 && row[22] != null) vo.setInvoiceDocTypeStatus((String) row[22]);
			
			ls.add(vo);
		}
		return ls;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.DepositDAO#getInvoicePaymentListNoCashbook(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoicePaymentVO> getInvoicePaymentListNoCashbook(Long CompId) throws BusinessException{
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT ip.id, i.id as invId, ip.dt_pmnt, ip.pmnt_type_cd, ip.ref_no, ip.received_from, ip.amount, i.tourcode, ")
        .append("i.id AS 'invoiceId', i.code AS 'invoiceNo', '' AS custCode, 0 AS 'idCust', ip.pmnt_for, 1 AS 'idBank', ")
        .append("'' AS pmntTypeName, '' AS bankName, ip.dt_upd, ")
        .append("'1' AS upd_hist_list ")
        .append(",(select GROUP_CONCAT(CONCAT(ifnull(ipa.scanned_path, ''),'#,#', ifnull(ipa.file_path, '')) ORDER BY ipa.id SEPARATOR '#-#') ")
        .append("from invoice_pmnt_attachment ipa where ipa.status_cd = 'A' and ip.id = ipa.id_inv_pmnt) AS attachment, ip.upd_by, i.doc_type_cd, i.ps_no, i.doc_type_status ")
        .append("FROM invoice_pmnt ip INNER JOIN (SELECT iv.id, iv.code, t.code AS 'tourcode', iv.doc_type_cd, iv.ps_no, iv.doc_type_status FROM invoice iv ")
        .append("LEFT JOIN (SELECT id, code FROM tour_dep) t ON t.id = iv.id_tour_dep WHERE iv.doc_type_cd in ('I', 'P') ")
        .append("AND iv.id_company = ").append(CompId).append(") i ON ip.id_inv = i.id ")
        .append("WHERE ip.pmnt_type_cd NOT IN ('credit_note', 'rfd', 'jrnl') AND ip.status_cd = 'A' AND ip.hide_deposit = 0 ")
        .append("AND ip.id_parent_pmnt is null ") // to hide payment in split/convert doc
        .append("AND ip.id NOT IN (SELECT l.id_inv_pmnt FROM inv_pmnt_cashbook_link l WHERE l.status_cd = 'A' AND l.id_company = ")
        .append(CompId).append(")");

		return MapInvoicePayment(createSQLQuery(sb.toString()).list());
	}


	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.DepositDAO#getInvoicePaymentListNoCashbook(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoicePaymentVO> getInvoicePaymentListNoCashbook(Long compId, SearchParamVO searchParamVO) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("select ip.id,i.id as invId,ip.dt_pmnt,ip.pmnt_type_cd,ip.ref_no,ip.received_from,ip.amount,i.tourcode,").
		append("i.id as 'invoiceId',i.code as 'invoiceNo',c.code as 'custCode',i.id_customer,ip.pmnt_for, dmc.id_bank, lk.description, b.name, ip.dt_upd, ").
		append("'1' AS upd_hist_list ").
		append(",(select GROUP_CONCAT(CONCAT(ifnull(ipa.scanned_path, ''),'#,#', ifnull(ipa.file_path, '')) ORDER BY ipa.id SEPARATOR '#-#') ").
	    append("from invoice_pmnt_attachment ipa where ipa.status_cd = 'A' and ip.id = ipa.id_inv_pmnt) AS attachment, ip.upd_by, i.doc_type_cd, i.ps_no, i.doc_type_status ").
		append("from invoice_pmnt ip ").
		append("inner join ( ").
		append("select iv.id, iv.id_customer, iv.code, t.code as 'tourcode', iv.doc_type_cd, iv.ps_no, iv.doc_type_status from invoice iv left join (select id, code from tour_dep) t on t.id = iv.id_tour_dep ").
		append("where iv.doc_type_cd in ('I', 'P') and iv.id_company = ").append(compId).append(" ").
		append(") i on ip.id_inv = i.id ").
		append("left join customer c on c.id = i.id_customer ").
		append("left join deposit_master_config dmc on dmc.pmnt_type_cd = ip.pmnt_type_cd ").
		append("left join bank b on b.id = dmc.id_bank ").
		append("left join lookup_item lk on lk.lookup_cat_cd = 'pymt_type' and lk.code = ip.pmnt_type_cd ").
		append("where ip.pmnt_type_cd not in ('credit_note', 'rfd', 'jrnl') ").
		append("AND ip.id_parent_pmnt is null "). // to hide payment in split/convert doc
		append("and ip.status_cd = 'A' and ip.hide_deposit = ").append(searchParamVO.getObj2()).append(" and ip.id not in ").
		append("(select l.id_inv_pmnt from inv_pmnt_cashbook_link l where l.status_cd = 'A' and l.id_company = ").append(compId).append(") ");
		
		if (searchParamVO.getFromDate() != null && searchParamVO.getToDate() != null)
			sb.append(" and ip.dt_pmnt between '").append(dateFmt.format(searchParamVO.getFromDate())).append("' and '").
				append(dateFmt.format(searchParamVO.getToDate())).append("'");
		
		if (searchParamVO.getObj1() != null && StringUtils.isNotEmpty(String.valueOf(searchParamVO.getObj1())))
			sb.append(" and ip.pmnt_type_cd = '").append(String.valueOf(searchParamVO.getObj1())).append("'");
		
		sb.append(" order by ip.dt_pmnt, ip.ref_no, i.id_customer, i.code");
		
		return MapInvoicePayment(createSQLQuery(sb.toString()).list());
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.DepositDAO#addDeposit(com.bcs.zsg.common.vo.AddUpdDelVO, java.lang.String)
	 */
	@Override
	public void addDeposit(AddUpdDelVO invPmntAUDList,String cashBookId,String refNo) throws BusinessException {
		StringBuilder sb;
		if (invPmntAUDList.getUpdList().size() > 0) {
			for (Object vo : invPmntAUDList.getUpdList()) {
				InvoicePaymentVO invoicePaymentVO = (InvoicePaymentVO) vo; 
				sb = new StringBuilder();
				
				sb.append("Update invoice_pmnt set id_inv_pmnt='1',id_cashbook='" +  cashBookId + "', ref_no = if(pmnt_type_cd = 'cheq_rtn', '" + refNo + "', ref_no) where id= '" + invoicePaymentVO.getId() + "'");
				createSQLQuery(sb.toString()).executeUpdate();
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.DepositDAO#updateDeposit(com.bcs.zsg.common.vo.AddUpdDelVO, java.lang.String)
	 */
	@Override
	public void updateDeposit( AddUpdDelVO invPmntAUDList,String cashBookId) throws BusinessException{
		StringBuilder sb;
		InvoicePaymentVO invoicePaymentVO; 
		if (invPmntAUDList.getDelList().size() > 0) {
			for (Object vo : invPmntAUDList.getDelList()) {
				  invoicePaymentVO = (InvoicePaymentVO) vo; 
				sb = new StringBuilder();
				sb.append("Update invoice_pmnt set id_inv_pmnt='0',id_cashbook=null where id= '" + invoicePaymentVO.getId() + "'");
				createSQLQuery(sb.toString()).executeUpdate();
				
			}
		}
		
		if (invPmntAUDList.getUpdList().size() > 0) {
			for (Object vo : invPmntAUDList.getUpdList()) {
				  invoicePaymentVO = (InvoicePaymentVO) vo; 
				sb = new StringBuilder();
				sb.append("Update invoice_pmnt set id_inv_pmnt='1',id_cashbook='" +  cashBookId + "' where id= '" + invoicePaymentVO.getId() + "'");
				createSQLQuery(sb.toString()).executeUpdate();
			}
		}
		 
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.DepositDAO#deleteDeposit(java.lang.String)
	 */
	@Override
	public void deleteDeposit( String cashBookId) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("Update invoice_pmnt set id_inv_pmnt='0',id_cashbook=null, ref_no = '' where id_cashbook= '" + cashBookId + "'");
		createSQLQuery(sb.toString()).executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.DepositDAO#getDepositListSize(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int getDepositListSize(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		/*sb.append("select count(c.id) from cash_book c ").
			append("left join (select i.code as 'code', p.id_cashbook as 'idcashbook' from invoice i, invoice_pmnt p where i.id = p.id_inv and p.status_cd = 'A') a on a.idcashbook = c.id ").
			append("left join (select name,id,id_company from bank) b on b.id=c.id_bank ").
			//append("left join (select sys_no, if((group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) like '%NONE%') = 1,'NO','YES') as tax_code_group from account_trans ").
			//append("	where id_company = :idCompany and sys_cd = :sysCd and status_cd = :statusCd ").
			//append("	and case when sys_cd = 'bank_deps' then type_cd = 'gnrl_deps' end ").
			//append("	group by id_company, sys_cd, sys_no) ac on ac.sys_no = c.sys_no ").
			append("where c.sys_cd = :sysCd  and c.status_cd = :statusCd and b.id_company=:idCompany ");*/
		
		sb.append("select count(c.id) from cash_book c ").
			//append("left join (SELECT group_concat(i.code separator ',') as 'invoices', p.id_cashbook as 'idcashbook' FROM invoice i, invoice_pmnt p WHERE i.id = p.id_inv and p.status_cd = 'A' group by p.id_cashbook) a on a.idcashbook = c.id ").
			append("left join (select name,id,id_company from bank) b on b.id=c.id_bank ").
			append("where c.sys_cd = :sysCd  and c.status_cd = :statusCd and b.id_company=:idCompany ");
		
		SearchParamVO searchParamVO = (SearchParamVO) params.get("searchParam");

		if(searchParamVO.getObj1()!=null){
			if(!searchParamVO.getObj1().toString().equals("All"))
				sb.append("and c.id_bank=:idBank ");
		}	
		if (searchParamVO.getObj2() == null) {
			if(StringUtils.isEmpty((String) searchParamVO.getObj3()) && StringUtils.isEmpty((String) searchParamVO.getObj5())) {
				sb.append("and dt_trans between '").append(dateFmt.format(searchParamVO.getFromDate())).
					append("' and '").append(dateFmt.format(searchParamVO.getToDate())).append("' ");
			}
		}
		if(StringUtils.isNotEmpty((String) searchParamVO.getObj3()) && StringUtils.isNotEmpty((String) searchParamVO.getObj5())) {
			sb.append(" and CAST(c.sys_no as signed) between CAST(:depositNoFrom as signed) and CAST(:depositNoTo as signed) ");
		}
		if(StringUtils.isNotEmpty((String) searchParamVO.getObj3())) sb.append(" and CAST(c.sys_no as signed) = CAST(:depositNoFrom as signed) ");
		if(StringUtils.isNotEmpty((String) searchParamVO.getObj5())) sb.append(" and CAST(c.sys_no as signed) <= CAST(:depositNoTo as signed) ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		
		/*String invoiceFilter = null;
		if(filters.containsKey("invoices")) {
			invoiceFilter = filters.get("invoices");
			filters.remove("invoices");
		}*/
		
		String filterInvoices = null;
		
		if (!filters.isEmpty()) {
			sb.append("AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("sysNo".equals(entry.getKey())) sb.append("c.sys_no LIKE '%").append(entry.getValue()).append("%'");
				else if ("dtTrans".equals(entry.getKey())) sb.append("date_format(c.dt_trans, '%d-%b-%Y') LIKE '%").append(entry.getValue()).append("%'");
				else if ("payee".equals(entry.getKey())) sb.append("c.payee LIKE '%").append(entry.getValue()).append("%'");
				else if ("debit".equals(entry.getKey())) sb.append("c.debit LIKE '").append(entry.getValue()).append("%'");
//				else if ("createdDate".equals(entry.getKey())) sb.append("date_format(c.dt_created, '%d-%b-%Y %H:%i:%s') LIKE '%").append(entry.getValue()).append("%'");
				else if ("refNo".equals(entry.getKey())) sb.append("c.ref_no LIKE '%").append(entry.getValue()).append("%'");
				else if ("remarks".equals(entry.getKey())) sb.append("c.remarks LIKE '%").append(entry.getValue()).append("%'");
				else if ("groupNo".equals(entry.getKey())) sb.append("c.group_no LIKE '%").append(entry.getValue()).append("%'");
				else if ("bankName".equals(entry.getKey())) sb.append("b.name LIKE '%").append(entry.getValue()).append("%'");
				else if ("taxCodeGroup".equals(entry.getKey())) sb.append("c.tax_code_group LIKE '%").append(entry.getValue()).append("%'");
//				else if ("invoices".equals(entry.getKey())) sb.append("c.invoices LIKE '%").append(entry.getValue()).append("%'");
				else if ("invoices".equals(entry.getKey())) {
					sb.append("	(SELECT CONCAT(GROUP_CONCAT(distinct IF(i.doc_type_cd = 'I', CONCAT('AV', i.code),CONCAT('PS', i.ps_no))), ifnull(concat(',', GROUP_CONCAT(IF(ci.doc_type_cd = 'I',CONCAT('AV', ci.code),CONCAT('PS', ci.ps_no)))), ''), ifnull(concat(',', GROUP_CONCAT(IF(cci.doc_type_cd = 'I',CONCAT('AV', cci.code), CONCAT('PS', cci.ps_no)))), '')) FROM inv_pmnt_cashbook_link l LEFT JOIN invoice i ON i.id = l.id_invoice LEFT JOIN invoice_pmnt cp ON cp.id_parent_pmnt = l.id_inv_pmnt LEFT JOIN invoice ci ON ci.id = cp.id_inv LEFT JOIN invoice_pmnt ccp ON ccp.id_parent_pmnt = cp.id LEFT JOIN invoice cci ON cci.id = ccp.id_inv WHERE l.id_cash_book = c.id AND l.status_cd = 'A') ")
						.append(" LIKE '%").append(entry.getValue()).append("%' ");

				}
				//else if ("taxCodeGroup".equals(entry.getKey())) sb.append("ac.tax_code_group LIKE '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" AND ");
			}
			sb.append(") ");
		}
		
		/*sb.append("group by c.id ");
		if (invoiceFilter != null) {
			sb.append("having group_concat(a.code separator ',') like '%").append(invoiceFilter).append("%'");
			filters.put("invoices", invoiceFilter);
		}*/
		
//		System.out.println("DepositDAOImpl.getDepositListSize()");
//		System.out.println(sb.toString());
		
		Query query = createSQLQuery(sb.toString());
		query.setString("sysCd", (String) params.get("typeCd"));
		
		if(searchParamVO.getObj1()!=null){
			if(!searchParamVO.getObj1().toString().equals("All")) 
			{
				query.setString("idBank", (String) searchParamVO.getObj1());
			}
		}
		query.setParameter("idCompany", params.get("companyId"));
		query.setString("statusCd", BaseConstant.STATUS_ACTIVE);
		if(StringUtils.isNotEmpty((String) searchParamVO.getObj3()) && StringUtils.isNotEmpty((String) searchParamVO.getObj5())) {
			query.setString("depositNoFrom",(String) searchParamVO.getObj3());
			query.setString("depositNoTo",(String) searchParamVO.getObj5());
		}
		if(StringUtils.isNotEmpty((String) searchParamVO.getObj3())) query.setString("depositNoFrom",(String) searchParamVO.getObj3());
		if(StringUtils.isNotEmpty((String) searchParamVO.getObj5())) query.setString("depositNoTo",(String) searchParamVO.getObj5());
		return ((Number) query.uniqueResult()).intValue();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.DepositDAO#getDepositList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CashBookVO> getDepositList(Map<String, Object> params) throws BusinessException {
		
		String invPrefix = LookupItemUtils.getInvPrefix((Long) params.get("companyId"));
		String psPrefix = LookupItemUtils.getPsPrefix((Long) params.get("companyId"));
		
		StringBuilder sb = new StringBuilder();
		/*sb.append("SELECT c.id, c.id_bank, c.id_supplier, c.id_customer, c.dt_trans, c.sys_cd, c.sys_prefix, ")
			.append("	c.sys_no, c.ref_no, c.trans_type_cd, c.payee, c.remarks, c.debit, c.credit, c.is_clear, c.is_mark, ")
			.append("	c.dt_clear, c.dt_mark, c.type_cd, c.group_no, c.status_cd, ")
			.append("	c.dt_created, c.created_by, c.dt_upd, c.upd_by, group_concat(a.code separator ',') as 'invoices',b.name, ")
			.append("	c.tax_code_group ")
			//.append("	ac.tax_code_group ")
			.append(" FROM cash_book c ")
			.append("left join (SELECT i.code as 'code', p.id_cashbook as 'idcashbook' FROM invoice i, invoice_pmnt p WHERE i.id = p.id_inv and p.status_cd = 'A') a on a.idcashbook = c.id ").
			append("left join (select name,id,id_company from bank) b on b.id=c.id_bank ").
			//append("left join (select sys_no, if((group_concat(case when tax_code = '' or tax_code is null then 'NONE' else tax_code end) like '%NONE%') = 1,'NO','YES') as tax_code_group from account_trans ").
			//append("	where id_company = :idCompany and sys_cd = :sysCd and status_cd = :statusCd ").
			//append("	and case when sys_cd = 'bank_deps' then type_cd = 'gnrl_deps' end ").
			//append("	group by id_company, sys_cd, sys_no) ac on ac.sys_no = c.sys_no ").
			append("WHERE c.sys_cd = :sysCd  and c.status_cd = :statusCd and b.id_company= :idCompany ");*/
		
		sb.append("SELECT c.id, c.id_bank, c.id_supplier, c.id_customer, c.dt_trans, c.sys_cd, c.sys_prefix, ").
			append("	c.sys_no, c.ref_no, c.trans_type_cd, c.payee, c.remarks, c.debit, c.credit, c.is_clear, c.is_mark, ").
			append("	c.dt_clear, c.dt_mark, c.type_cd, c.group_no, c.status_cd, ").
			append("	c.dt_created, c.created_by, c.dt_upd, c.upd_by, c.invoices, b.name, ").
			append("	c.tax_code_group, ").
			append("	(SELECT CONCAT(GROUP_CONCAT(distinct IF(i.doc_type_cd = 'I', CONCAT('" + invPrefix + "', i.code),CONCAT('" + psPrefix + "', i.ps_no))), ifnull(concat(',', GROUP_CONCAT(IF(ci.doc_type_cd = 'I',CONCAT('" + invPrefix + "', ci.code),CONCAT('" + psPrefix + "', ci.ps_no)))), ''), ifnull(concat(',', GROUP_CONCAT(IF(cci.doc_type_cd = 'I',CONCAT('"+ invPrefix +"', cci.code), CONCAT('" + psPrefix + "', cci.ps_no)))), '')) FROM inv_pmnt_cashbook_link l LEFT JOIN invoice i ON i.id = l.id_invoice LEFT JOIN invoice_pmnt cp ON cp.id_parent_pmnt = l.id_inv_pmnt LEFT JOIN invoice ci ON ci.id = cp.id_inv LEFT JOIN invoice_pmnt ccp ON ccp.id_parent_pmnt = cp.id LEFT JOIN invoice cci ON cci.id = ccp.id_inv WHERE l.id_cash_book = c.id AND l.status_cd = 'A') as linked_invoices ").
//			append("	,(select group_concat(if(i.doc_type_cd = 'I', concat('" + invSNGVO.getPrefixid() + "', i.code), concat('" + psSNGVO.getPrefixid() + "', i.ps_no))) from invoice i left join inv_pmnt_cashbook_link l on i.id = l.id_invoice and l.status_cd = 'A' where l.id_cash_book = c.id) ").
			append(" FROM cash_book c ").
			//append("left join (SELECT group_concat(i.code separator ',') as 'invoices', p.id_cashbook as 'idcashbook' FROM invoice i, invoice_pmnt p WHERE i.id = p.id_inv and p.status_cd = 'A' group by p.id_cashbook) a on a.idcashbook = c.id ").
			append("left join (select name,id,id_company from bank) b on b.id=c.id_bank ").
			append("WHERE c.sys_cd = :sysCd  and c.status_cd = :statusCd and b.id_company= :idCompany ");
		
		SearchParamVO searchParamVO = (SearchParamVO) params.get("searchParam");
		if(searchParamVO.getObj1() != null){
			if(!searchParamVO.getObj1().toString().equals(""))
				sb.append("and c.id_bank=:idBank ");
		}	
		if (searchParamVO.getObj2() == null) {
			if(StringUtils.isEmpty((String) searchParamVO.getObj3()) && StringUtils.isEmpty((String) searchParamVO.getObj5())) {
				SimpleDateFormat dateFmt = new SimpleDateFormat("yyy-MM-dd");
				sb.append("and dt_trans between '").append(dateFmt.format(searchParamVO.getFromDate())).
					append("' and '").append(dateFmt.format(searchParamVO.getToDate())).append("' ");
			}
		}
		if(StringUtils.isNotEmpty((String) searchParamVO.getObj3()) && StringUtils.isNotEmpty((String) searchParamVO.getObj5())) {
			sb.append(" and CAST(c.sys_no as signed) between CAST(:depositNoFrom as signed) and CAST(:depositNoTo as signed) ");
		}
		if(StringUtils.isNotEmpty((String) searchParamVO.getObj3())) sb.append(" and CAST(c.sys_no as signed) = CAST(:depositNoFrom as signed) ");
		if(StringUtils.isNotEmpty((String) searchParamVO.getObj5())) sb.append(" and CAST(c.sys_no as signed) <= CAST(:depositNoTo as signed) ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		//String invoiceFilter = null;
		String filterField = null;
		String filterValue = null;
		
		/*if(filters.containsKey("invoices")) {
			invoiceFilter = filters.get("invoices");
			filters.remove("invoices");
			filterField = "invoices";
			filterValue = invoiceFilter;
		}*/
		
		String filterInvoices = null;
		
		if (!filters.isEmpty()) {
			sb.append("AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("sysNo".equals(entry.getKey())) sb.append("c.sys_no LIKE '%").append(entry.getValue()).append("%'");
				else if ("dtTrans".equals(entry.getKey())) sb.append("date_format(c.dt_trans, '%d-%b-%Y') LIKE '%").append(entry.getValue()).append("%'");
				else if ("payee".equals(entry.getKey())) sb.append("c.payee LIKE '%").append(entry.getValue()).append("%'");
				else if ("debit".equals(entry.getKey())) sb.append("c.debit LIKE '").append(entry.getValue()).append("%'");
//				else if ("createdDate".equals(entry.getKey())) sb.append("date_format(c.dt_created, '%d-%b-%Y %H:%i:%s') LIKE '%").append(entry.getValue()).append("%'");
				else if ("refNo".equals(entry.getKey())) sb.append("c.ref_no LIKE '%").append(entry.getValue()).append("%'");
				else if ("remarks".equals(entry.getKey())) sb.append("c.remarks LIKE '%").append(entry.getValue()).append("%'");
				else if ("groupNo".equals(entry.getKey())) sb.append("c.group_no LIKE '%").append(entry.getValue()).append("%'");
				//else if ("invoices".equals(entry.getKey())) invoiceFilter = entry.getValue();
				else if ("bankName".equals(entry.getKey())) sb.append("b.name LIKE '%").append(entry.getValue()).append("%'");
				else if ("taxCodeGroup".equals(entry.getKey())) sb.append("c.tax_code_group LIKE '%").append(entry.getValue()).append("%'");
//				else if ("invoices".equals(entry.getKey())) sb.append("c.invoices LIKE '%").append(entry.getValue()).append("%'");
				else if ("invoices".equals(entry.getKey())) {
					filterInvoices = entry.getValue();
					sb.append(" 1=1 ");
				}
				//else if ("taxCodeGroup".equals(entry.getKey())) sb.append("ac.tax_code_group LIKE '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
				filterField = entry.getKey();
				filterValue = entry.getValue();
			}
			sb.append(" ) ");
		}
		
		if (StringUtils.isNotBlank(filterInvoices)) {
			sb.append(" having linked_invoices like '%").append(filterInvoices).append("%' ");
		}
		
		/*sb.append("group by c.id ");
		if (invoiceFilter != null) {
			sb.append("having group_concat(a.code separator ',') like '%").append(invoiceFilter).append("%' ");
			filters.put("invoices", invoiceFilter);
		}*/
		sb.append("order by ");
		String sortField = (String) params.get("sortField");
		if (sortField == null) {
			if (filterField == null) { 
				if(searchParamVO.getObj4()!=null) sb.append(" b.name ,");
				sb.append(" cast(c.sys_no as decimal) desc ");
				
			} else {
				if ("sysNo".equals(filterField)) sb.append("case when c.sys_no like '").append(filterValue).append("%' then c.sys_no else concat('{0}', c.sys_no) end");
				else if ("dtTrans".equals(filterField)) sb.append("case when date_format(c.dt_trans, '%d-%b-%Y') like '").append(filterValue).append("%' then c.dt_trans else concat('{0}', c.dt_trans) end");
				else if ("payee".equals(filterField)) sb.append("case when c.payee like '").append(filterValue).append("%' then c.payee else concat('{0}', c.payee) end");
				else if ("debit".equals(filterField)) sb.append("case when c.debit like '").append(filterValue).append("%' then c.debit else concat('{0}', c.debit) end");
//				else if ("createdDate".equals(filterField)) sb.append("case when date_format(c.dt_created, '%d-%b-%Y %H:%i:%s') like '").append(filterValue).append("%' then c.dt_created else concat('{0}', c.dt_created) end");
				else if ("refNo".equals(filterField)) sb.append("case when c.ref_no like '").append(filterValue).append("%' then c.ref_no else concat('{0}', c.ref_no) end");
				else if ("remarks".equals(filterField)) sb.append("case when c.remarks like '").append(filterValue).append("%' then c.remarks else concat('{0}', c.remarks) end");
				else if ("groupNo".equals(filterField)) sb.append("case when c.group_no like '").append(filterValue).append("%' then c.group_no else concat('{0}', c.group_no) end");
				else if ("invoices".equals(filterField)) sb.append("case when c.invoices like '").append(filterValue).append("%' then c.invoices else concat('{0}', c.invoices) end");
				else if ("bankName".equals(filterField)) sb.append("case when b.name like '").append(filterValue).append("%' then b.name else concat('{0}', b.name) end");
				else if ("taxCodeGroup".equals(filterField)) sb.append("case when c.tax_code_group like '").append(filterValue).append("%' then c.tax_code_group else concat('{0}', c.tax_code_group) end");
			}
		} else {
			if ("sysNo".equals(sortField)) sb.append("cast(c.sys_no as decimal)");
			else if ("dtTrans".equals(sortField)) sb.append("c.dt_trans");
			else if ("payee".equals(sortField)) sb.append("c.payee");
			else if ("debit".equals(sortField)) sb.append("c.debit");
//			else if ("createdDate".equals(sortField)) sb.append("c.dt_created");
			else if ("refNo".equals(sortField)) sb.append("c.ref_no");
			else if ("remarks".equals(sortField)) sb.append("c.remarks");
			else if ("groupNo".equals(sortField)) sb.append("c.group_no");
			else if ("invoices".equals(sortField)) sb.append("c.invoices");
			else if ("bankName".equals(sortField)) sb.append("b.name");
			else if ("taxCodeGroup".equals(sortField)) sb.append("c.tax_code_group");
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" asc");
			else sb.append(" desc");
		}
		
//		System.out.println("DepositDAOImpl.getDepositList()");
//		System.out.println(sb.toString());
		
		Query query = createSQLQuery(sb.toString());
		query.setString("sysCd", (String) params.get("typeCd"));
		query.setParameter("idCompany", params.get("companyId"));
		if(searchParamVO.getObj1()!=null){
			if(!searchParamVO.getObj1().toString().equals("All")) query.setString("idBank", (String) searchParamVO.getObj1());
		}
		query.setString("statusCd", BaseConstant.STATUS_ACTIVE);
		if(StringUtils.isNotEmpty((String) searchParamVO.getObj3()) && StringUtils.isNotEmpty((String) searchParamVO.getObj5())) {
			query.setString("depositNoFrom",(String) searchParamVO.getObj3());
			query.setString("depositNoTo",(String) searchParamVO.getObj5());
		}
		if(StringUtils.isNotEmpty((String) searchParamVO.getObj3())) query.setString("depositNoFrom",(String) searchParamVO.getObj3());
		if(StringUtils.isNotEmpty((String) searchParamVO.getObj5())) query.setString("depositNoTo",(String) searchParamVO.getObj5());
		query.setFirstResult((Integer) params.get("first"));
		query.setMaxResults((Integer) params.get("pageSize"));
		List<Object> objList = query.list();
		List<CashBookVO> result = new ArrayList<CashBookVO>();
		if (CollectionUtils.isNotEmpty(objList)) {
			CashBookVO vo;
			for (Iterator<Object> it = objList.iterator() ; it.hasNext() ;) {
				Object[] row = (Object[]) it.next();
				vo = new CashBookVO();
				vo.setId(((BigInteger) row[0]).longValue());
				vo.setIdBank(((BigInteger) row[1]).longValue());
				vo.setIdSupplier(row[2] == null ? null : ((BigInteger) row[2]).longValue());
				vo.setIdCustomer(row[3] == null ? null : ((BigInteger) row[3]).longValue());
				vo.setDtTrans((Date) row[4]);
				vo.setSysCode((String) row[5]);
				vo.setSysPrefix(row[6] == null ? null : (String) row[6]);
				vo.setSysNo((String) row[7]);
				vo.setRefNo((String) row[8]);
				vo.setTransTypeCd((String) row[9]);
				vo.setPayee((String) row[10]);
				vo.setRemarks((String) row[11]);
				vo.setDebit((Double) row[12]);
				vo.setCredit((Double) row[13]);
				vo.setIsClear((Boolean) row[14]);
				vo.setIsMark((Boolean) row[15]);
				vo.setDtClear(row[16] == null ? null : (Date) row[16]);
				vo.setDtMark(row[17] == null ? null : (Date) row[17]);
				vo.setTypeCd(row[18] == null ? null : (String) row[18]);
				vo.setGroupNo(row[19] == null ? null : (String) row[19]);
				vo.setStatusCode((String) row[20]);
				vo.setCreatedDate((Date) row[21]); 
				vo.setCreatedBy((String) row[22]);
				vo.setUpdatedDate((Date) row[23]);
				vo.setUpdatedBy((String) row[24]);
				vo.setInvoices(row[25] == null ? null : (String) row[25]);
				vo.setBankName((String) row[26]);
				vo.setTaxCodeGroup((String) row[27]);
				if (row[28] != null)	vo.setInvoices((String) row[28]);
				result.add(vo);
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.DepositDAO#updateInvoicePmnt(com.bcs.zsg.bank.vo.CashBookVO)
	 */
	@Override
	public void updateInvoicePmnt(CashBookVO cashBookVO) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("Update invoice_pmnt set id_inv_pmnt = null, id_cashbook = null, ref_no = if(pmnt_type_cd = 'cheq_rtn', '', ref_no) where id_cashbook = ").append(cashBookVO.getId());
		createSQLQuery(sb.toString()).executeUpdate();

		if (CollectionUtils.isNotEmpty(cashBookVO.getInvPymtList())) {
			sb = new StringBuilder();
			
			sb.append("Update invoice_pmnt set id_inv_pmnt = '1', id_cashbook = ").append(cashBookVO.getId()).append(", ref_no = if(pmnt_type_cd = 'cheq_rtn', '" + cashBookVO.getRefNo() + "', ref_no) where id in (");
			for (int i = 0 ; i < cashBookVO.getInvPymtList().size() ; i++) {
				sb.append(cashBookVO.getInvPymtList().get(i).getId());
				if (i < cashBookVO.getInvPymtList().size() - 1) sb.append(",");
			}
			sb.append(")");
			createSQLQuery(sb.toString()).executeUpdate();
		}
	}
	
	@Override
	public List<InvPmntCBLinkVO> getInvPmntCBLinkList(Long idCashBook) throws BusinessException {
		Criteria criteria = createCriteria(InvPmntCBLinkVO.class);
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.add(Restrictions.eq("idCashBook", idCashBook));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	@Override
	public void updateInvPmntCBLink(Long idCashBook, Long CompId) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("Update inv_pmnt_cashbook_link set status_cd = '").append(BaseConstant.STATUS_DELETED).append("'").
			append(", dt_upd = now(), upd_by = '").append(BaseContext.getUserFullName() == null ? BaseContext.getLoginId() : BaseContext.getUserFullName()).append("'").
			append(" where id_cash_book = ").append(idCashBook).
			append(" and id_company = ").append(CompId).
			append(" and status_cd = 'A'");
		createSQLQuery(sb.toString()).executeUpdate();
	}
	
	@Override
	public void updateBankDepositCode(String uuid, String code) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE CashBookVO SET sysNo = :code WHERE sysNo = :uuid");
		
		Query query = createQuery(sb.toString());
		query.setString("code", code);
		query.setString("uuid", uuid);
		query.executeUpdate();
		
		sb = new StringBuilder();
		sb.append("UPDATE AcctTransVO SET sysNo = :code, source = REPLACE(source, :uuid, :code) WHERE sysNo = :uuid");
		
		query = createQuery(sb.toString());
		query.setString("code", code);
		query.setString("uuid", uuid);
		query.executeUpdate();
	}
	
	public boolean existsInvPmntCBLink(Long idInvPmnt) {
	    String hql = "SELECT 1 FROM InvPmntCBLinkVO WHERE idInvPmnt = :idInvPmnt AND statusCode = :status ";
	    
	    Query query = createQuery(hql);
	    query.setParameter("idInvPmnt", idInvPmnt);
	    query.setParameter("status", BaseConstant.STATUS_ACTIVE);	    
	    query.setMaxResults(1);
	    
	    return !query.list().isEmpty();
	}
	
	@Override
	public void updateInvoicePaymentHideStatus(String paymentIDs, int hideStatus) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE invoice_pmnt SET hide_deposit = ").append(hideStatus).append(" WHERE id IN (").append(paymentIDs).append(")");
		createSQLQuery(sb.toString()).executeUpdate();
	}
	
	@Override
	public InvPmntCBLinkVO getInvPmntCBLinkByIdInvPmnt(Long idInvPmnt) {
		Criteria criteria = createCriteria(InvPmntCBLinkVO.class);
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.add(Restrictions.eq("idInvPmnt", idInvPmnt));
		return (InvPmntCBLinkVO) criteria.uniqueResult();
	}
}
