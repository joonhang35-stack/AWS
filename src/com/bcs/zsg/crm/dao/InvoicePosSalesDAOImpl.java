package com.bcs.zsg.crm.dao;

import java.math.BigInteger;
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
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import com.bcs.zsg.common.helper.CRMCommonConstant;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.crm.vo.InvoicePosSalesItemVO;
import com.bcs.zsg.crm.vo.InvoicePosSalesVO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.CorAddressVO;
import com.bcs.zsg.purchase.vo.CorContactVO;
import com.bcs.zsg.purchase.vo.CorporateVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.sales.vo.CustomerVO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.sales.vo.PersonComplicationVO;
import com.bcs.zsg.sales.vo.PersonContactVO;
import com.bcs.zsg.sales.vo.PersonEmailVO;

public class InvoicePosSalesDAOImpl extends BaseHibernateDAO implements InvoicePosSalesDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public InvoicePosSalesVO getInvoicePosSales(InvoiceVO invoiceVO) throws BusinessException {
		Criteria criteria = createCriteria(InvoicePosSalesVO.class);
		criteria.add(Restrictions.eq("idInvoice", invoiceVO.getId()));
		criteria.add(Restrictions.eq("idCompany", invoiceVO.getCompanyId()));
		criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
		criteria.setMaxResults(1);
		return (InvoicePosSalesVO) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public InvoicePosSalesVO getInvoicePosSalesExist(InvoiceVO invoiceVO) throws BusinessException {
		Criteria criteria = createCriteria(InvoicePosSalesVO.class);
		criteria.add(Restrictions.eq("idInvoice", invoiceVO.getId()));
		criteria.add(Restrictions.eq("idCompany", invoiceVO.getCompanyId()));
		criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
		criteria.setMaxResults(1);
		return (InvoicePosSalesVO) criteria.uniqueResult();
	}
	
	@Override
	public List<InvoicePosSalesVO> getInvoicePosSalesList() throws BusinessException {
		Criteria criteria = createCriteria(InvoicePosSalesVO.class);
		criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
		criteria.add(Restrictions.eq("postingStatus", CRMCommonConstant.CRM_INV_POSTING_PENDING));
		criteria.setMaxResults(1000);
		List<InvoicePosSalesVO> ls = criteria.list();
		return ls;
	}
	
	@Override
	public List<InvoicePosSalesItemVO> getInvoicePosSalesItemList(Long idPosSales) throws BusinessException {
		Criteria criteria = createCriteria(InvoicePosSalesItemVO.class);
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.add(Restrictions.eq("idPosSales", idPosSales));
		List<InvoicePosSalesItemVO> ls = criteria.list();
		return ls;
	}
	
	@Override
	public CustomerVO getCustomerVO(Long idCustomer) throws BusinessException {
		
		Criteria criteria = createCriteria(CustomerVO.class);
		criteria.add(Restrictions.eq("id", idCustomer));
		CustomerVO vo = (CustomerVO) criteria.uniqueResult();
		
		vo.setBillAddressVO(new AddressVO());
		vo.setCorBillAddressVO(new CorAddressVO());
		vo.setPersonContactList(new ArrayList<PersonContactVO>());
		vo.setCorContactList(new ArrayList<CorContactVO>());
		vo.setPersonComplicationList(new ArrayList<PersonComplicationVO>());
		vo.setAddressList(new ArrayList<AddressVO>());
		vo.setCorAddressList(new ArrayList<CorAddressVO>());
		
		if (vo.getPcTypeCd().equals("C")) {
			criteria = createCriteria(CorporateVO.class);
			criteria.add(Restrictions.eq("id", vo.getCorporateId()));
			CorporateVO corporateVO = (CorporateVO) criteria.uniqueResult();
			vo.setCorporateVO(corporateVO);
			
			criteria = createCriteria(PersonVO.class);
			criteria.add(Restrictions.eq("id", vo.getPersCorpId()));
			PersonVO personVO = (PersonVO) criteria.uniqueResult();
			if (personVO != null) { 
				personVO.setSalutation(StringUtils.equals(personVO.getSalutation(), LookupItemConstant.SALUTATION_EMPTY_CD) ? "" : personVO.getSalutation());
				vo.setPersonVO(personVO); 
			}
			
			criteria = createCriteria(CorAddressVO.class);
			criteria.add(Restrictions.eq("corporateId", vo.getCorporateVO().getId()));
			criteria.add(Restrictions.eq("addrType", CommonConstant.BILL_ADDR));
			criteria.setMaxResults(1);
			CorAddressVO corAddressVO = (CorAddressVO) criteria.uniqueResult();
			if (corAddressVO != null)vo.setCorBillAddressVO(corAddressVO);
			
			criteria = createCriteria(CorContactVO.class);
			criteria.add(Restrictions.eq("corporateId", vo.getCorporateVO().getId()));
			criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
			List<CorContactVO> contactList = criteria.list();
			if (CollectionUtils.isNotEmpty(contactList)) vo.setCorContactList(contactList);
			
		} else {
			criteria = createCriteria(PersonVO.class);
			criteria.add(Restrictions.eq("id", vo.getPersCorpId()));
			PersonVO personVO = (PersonVO) criteria.uniqueResult();
			if (personVO != null) {
				personVO.setSalutation(StringUtils.equals(personVO.getSalutation(), LookupItemConstant.SALUTATION_EMPTY_CD) ? "" : personVO.getSalutation());
				vo.setPersonVO(personVO); 
			}
			
			criteria = createCriteria(AddressVO.class);
			criteria.add(Restrictions.eq("personId", vo.getPersonVO().getId()));
			criteria.add(Restrictions.eq("addrType", CommonConstant.BILL_ADDR));
			AddressVO addressVO = (AddressVO) criteria.uniqueResult();
			if (addressVO != null) vo.setBillAddressVO(addressVO);
			
			criteria = createCriteria(PersonContactVO.class);
			criteria.add(Restrictions.eq("personId", vo.getPersonVO().getId()));
			criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
			List<PersonContactVO> contactList = criteria.list();
			if (CollectionUtils.isNotEmpty(contactList)) vo.setPersonContactList(contactList);
			
		}
		
		criteria = createCriteria(PersonEmailVO.class);
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.add(Restrictions.eq("idPerson", vo.getPersonVO().getId()));
		
		List<PersonEmailVO> emailList = criteria.list();
		if (CollectionUtils.isNotEmpty(emailList)) vo.getPersonVO().setEmailList(emailList);
		
		return vo;
	}

	@Override
	public void updateInvoicePosSalesStatus(List<InvoicePosSalesVO> invoicePosSalesList) throws BusinessException {
		Session session = this.getSessionFactory().openSession();
		
		try {
			session.getTransaction().setTimeout(60);
			session.beginTransaction();
			
			for (int i = 0; i < invoicePosSalesList.size(); i++) {
		        session.saveOrUpdate(invoicePosSalesList.get(i));
	
		        if (i % 50 == 0) {
		            session.flush();
		            session.clear();
		        }
		    }
		
			session.getTransaction().commit();
		} catch (Exception e) {
		    if (session.getTransaction() != null) {
		    	session.getTransaction().rollback();
		    }
		    throw e;
		} finally {
		    if (session != null) {
		        session.close();
		    }
		}
	}

//	@Override
//	public List<InvoicePosSalesVO> getInvoicePosSalesList(Map<String, Object> params) throws BusinessException {
//		Criteria criteria = createCriteria(InvoicePosSalesVO.class);
//		criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
//		if(params.containsKey("idCompany")) criteria.add(Restrictions.eq("idCompany", params.get("idCompany")));
//		if(params.containsKey("dateFrom")) criteria.add(Restrictions.ge("createdDate", params.get("dateFrom")));
//		if(params.containsKey("dateTo")) criteria.add(Restrictions.le("createdDate", params.get("dateTo")));
//		
//		List<InvoicePosSalesVO> ls = criteria.list();
//		return ls;
//	}
//
//	@Override
//	public int getInvoicePosSalesListSize(Map<String, Object> params) throws BusinessException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int getInvoicePosSalesListSize(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(ips.id) ").
		append("from invoice_pos_sales ips ").
		append("join (select id, id_pc, id_corporate, pc_type_cd, code from customer where id_company = :idCompany) c on ips.id_customer = c.id ").
		append("left join person p on p.id = c.id_pc ").
		append("left join corporate o on c.pc_type_cd = 'C' and c.id_corporate = o.id ").
		append("where ips.id_company = :idCompany and ips.status_cd = :statusCode ");
		
		sb.append(getInvoicePosSalesListFilter(params));
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("idCompany"));
		query.setParameter("statusCode", CommonConstant.STATUS_CD_ACTIVE);
		
		if (params.get("dateFrom") != null) query.setParameter("dateFrom", params.get("dateFrom"));
		if (params.get("dateTo") != null) query.setParameter("dateTo", params.get("dateTo"));
		
		return ((BigInteger) query.uniqueResult()).intValue();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.dao.PurchaseEODAO#getEOList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoicePosSalesVO> getInvoicePosSalesList(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("select ips.id, ips.id_customer, ").
			append("c.code, p.salutation_cd, p.last_name, p.first_name, o.name, ").
			append("ips.id_tour_booking, ips.dt_inv, ips.inv_no, ips.ps_no, ips.doc_type_cd, ").
			append("ips.dt_departure, ips.cat_cd, ips.amount, ips.posting_status, ips.remarks, ips.dt_created ").
		append("from invoice_pos_sales ips ").
		append("join (select id, id_pc, id_corporate, pc_type_cd, code from customer where id_company = :idCompany) c on ips.id_customer = c.id ").
		append("left join person p on p.id = c.id_pc ").
		append("left join corporate o on c.pc_type_cd = 'C' and c.id_corporate = o.id ").
		append("where ips.id_company = :idCompany and ips.status_cd = :statusCode ");
		
		sb.append(getInvoicePosSalesListFilter(params));
		sb.append(getInvoicePosSalesListOrder(params));
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCompany", params.get("idCompany"));
		query.setParameter("statusCode", CommonConstant.STATUS_CD_ACTIVE);
		
		if (params.get("dateFrom") != null) query.setParameter("dateFrom", params.get("dateFrom"));
		if (params.get("dateTo") != null) query.setParameter("dateTo", params.get("dateTo"));
		
		query.setFirstResult((Integer) params.get("first"));
		query.setMaxResults((Integer) params.get("pageSize"));
		List<Object> results = query.list();
		List<InvoicePosSalesVO> salesList = new ArrayList<InvoicePosSalesVO>();
		InvoicePosSalesVO vo;
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			vo = new InvoicePosSalesVO();
			vo.setId(((BigInteger) row[0]).longValue());
			vo.setIdCustomer(((BigInteger) row[1]).longValue());
			vo.setCustCd((String) row[2]);
			vo.setCustSalutation((String) row[3]);
			vo.setCustName(row[4].toString() + " " + row[5].toString());
			vo.setCoName((String) row[6]);
			vo.setIdBooking(((BigInteger) row[7]).longValue());
			vo.setInvoiceDt((Date) row[8]);
			vo.setInvNo((String) row[9]);
			vo.setPsNo((String) row[10]);
			vo.setDocTypeCd((String) row[11]);
			vo.setDepartureDt((Date) row[12]);
			vo.setCatCd((String) row[13]);
			vo.setAmount(Double.parseDouble(row[14].toString()));
			vo.setPostingStatus((String) row[15]);
			vo.setRemarks((String) row[16]);
			vo.setCreatedDate((Date) row[17]);
			salesList.add(vo);
		}
		return salesList;
	}
	
	@SuppressWarnings("unchecked")
	public StringBuilder getInvoicePosSalesListFilter(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		if (params.get("dateFrom") != null) sb.append("and date(ips.dt_created) >= date(:dateFrom) ");
		
		if (params.get("dateTo") != null) sb.append("and date(ips.dt_created) <= date(:dateTo) ");
		
		if (params.get("invoiceType") != null && !params.get("invoiceType").equals("ALL")) sb.append("and ips.doc_type_cd = '" + params.get("invoiceType") + "' ");
		
		if (params.get("postingStatus") != null && !params.get("postingStatus").equals("ALL")) sb.append("and ips.posting_status = '" + params.get("postingStatus") + "' ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		if (!filters.isEmpty()) {
			sb.append("and (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("invNo".equals(entry.getKey())) sb.append("ips.inv_no like '").append(entry.getValue()).append("%'");
				else if ("psNo".equals(entry.getKey())) sb.append("ips.ps_no like '").append(entry.getValue()).append("%'");
				else if ("idBooking".equals(entry.getKey())) sb.append("ips.id_tour_booking like '%").append(entry.getValue()).append("%'");
				else if ("invoiceDt".equals(entry.getKey())) sb.append("date_format(ips.dt_inv, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
				else if ("departureDt".equals(entry.getKey())) sb.append("date_format(ips.dt_departure, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
				else if ("amount".equals(entry.getKey())) sb.append("ips.amount like '%").append(entry.getValue()).append("%'");
				else if ("createdDate".equals(entry.getKey())) sb.append("date_format(ips.dt_created, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
				else if ("custCd".equals(entry.getKey())) sb.append("c.code like '").append(entry.getValue()).append("%'");
				else if ("custName".equals(entry.getKey())) sb.append("concat(p.last_name, ' ', p.first_name) like '%").append(entry.getValue()).append("%'");
				else if ("coName".equals(entry.getKey())) sb.append("o.name like '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
			}
			sb.append(")");
		}
		
		return sb;
	}
	
	public StringBuilder getInvoicePosSalesListOrder(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("order by ");
		String sortField = (String) params.get("sortField");
		if (sortField == null) sb.append("ips.dt_created desc");
		else {
			if ("invNo".equals(sortField)) sb.append("cast(ips.inv_no as decimal)");
			if ("psNo".equals(sortField)) sb.append("cast(ips.ps_no as decimal)");
			else if ("psNo".equals(sortField)) sb.append("eo.dt_eo");
			else if ("idBooking".equals(sortField)) sb.append("ips.id_tour_booking");
			else if ("amount".equals(sortField)) sb.append("ips.amount");
			else if ("invoiceDt".equals(sortField)) sb.append("ips.dt_inv");
			else if ("departureDt".equals(sortField)) sb.append("ips.dt_inv");
			else if ("createdDate".equals(sortField)) sb.append("ips.dt_departure");
			else if ("custCd".equals(sortField)) sb.append("cast(c.code as decimal)");
			else if ("custName".equals(sortField)) sb.append("concat(p.last_name, ' ', p.first_name)");
			else if ("coName".equals(sortField)) sb.append("o.name");
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" asc");
			else sb.append(" desc");
		}
		
		return sb;
	}
}
