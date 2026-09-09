package com.bcs.zsg.sales.dao;

import java.math.BigDecimal;
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
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.InvoiceEmailPaymentVO;

public class InvoiceEmailPaymentDAOImpl extends BaseHibernateDAO implements InvoiceEmailPaymentDAO {
	
	@Override
	public int getInvoiceEmailPaymentListSize(Map<String, Object> params) throws BusinessException {
		List<Object> results = commonQuery(params, true);
		return results.size();
	}
	
	@Override
	public List<InvoiceEmailPaymentVO> getInvoiceEmailPaymentList(Map<String, Object> params) throws BusinessException {
		List<Object> results = commonQuery(params, false);
		List<InvoiceEmailPaymentVO> invoiceEmailPaymentVOList = new ArrayList<InvoiceEmailPaymentVO>();

		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			InvoiceEmailPaymentVO vo = new InvoiceEmailPaymentVO();
			vo.setId(((BigInteger) row[0]).longValue()); 
			vo.setIdIpayConfig(((BigInteger) row[1]).longValue()); 
			vo.setIdInvoice(((BigInteger) row[2]).longValue()); 
			if (row[3] != null) vo.setIdInvoiceAdminCharges(((BigInteger) row[3]).longValue()); 
			vo.setEmail((String) row[4]);
			vo.setPaymentAmt((BigDecimal) row[5]);
			vo.setAdminChargesPercentage((BigDecimal) row[6]);
			vo.setAdminCharges((BigDecimal) row[7]);
			vo.setTotalPaymentAmt((BigDecimal) row[8]);
			vo.setPaymentStatus((String) row[9]);
			vo.setPrefix((String) row[10]);
			vo.setPrefixAdminCharges((String) row[11]);
			if (row[12] != null) vo.setTransId((String) row[12]);
			if (row[13] != null) vo.setAuthCode((String) row[13]);
			if (row[14] != null) vo.setCcName((String) row[14]);
			if (row[15] != null) vo.setCcNo((String) row[15]);
			if (row[16] != null) vo.setsBankName((String) row[16]);
			if (row[17] != null) vo.setsCountry((String) row[17]);
			vo.setStatusCode((String) row[18]);
			vo.setCreatedDate((Date) row[19]);
			vo.setCreatedBy((String) row[20]);
			vo.setUpdatedDate((Date) row[21]);
			vo.setUpdatedBy((String) row[22]);
			vo.setInvoiceNo((String) row[23]);
			if (row[24] != null) vo.setInvoiceAdminChargesNo((String) row[24]);
			if (row[25] != null) vo.setInvoiceStatus((String) row[25]);
			vo.setMerchantCode((String) row[26]);
			vo.setMerchantKey((String) row[27]);
			vo.setIpayConfigComment((String) row[28]);
			vo.setDtExpiry((Date) row[29]);
			
			invoiceEmailPaymentVOList.add(vo);
		}
		
		return invoiceEmailPaymentVOList;
	}
	
	@SuppressWarnings("unchecked")
	private List<Object> commonQuery(Map<String, Object> params, boolean isCount) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ep.id, ep.id_ipay_config, ep.id_invoice, ep.id_invoice_admin_charges, ep.email, ");
		sb.append("ep.payment_amt, ep.admin_charges_percentage, ep.admin_charges, ep.total_payment_amt, ep.payment_status, ep.prefix, ep.prefix_admin_charges, ");
		sb.append("ep.trans_id, ep.auth_code, ep.cc_name, ep.cc_no, ep.s_bankname, ep.s_country, ");
		sb.append("ep.status_cd, ep.dt_created, ep.created_by, ep.dt_update, ep.updated_by, ");
		sb.append("iv.code AS inv_code, iv_ad.code AS inv_admin_charges_code, iv.status_cd AS invoice_status, ");
		sb.append("ip_c.merchant_code, ip_c.merchant_key, ip_c.comment, ep.dt_expiry ");
		sb.append("FROM invoice_email_payment ep ");
		sb.append("LEFT JOIN invoice iv ON iv.id = ep.id_invoice ");
		sb.append("LEFT JOIN invoice iv_ad ON iv_ad.id = ep.id_invoice_admin_charges ");
		sb.append("LEFT JOIN ipay_config ip_c ON ip_c.id = ep.id_ipay_config ");
		
		sb.append("WHERE ep.status_cd = 'A' ");
		
		if (params.get("id") != null)
			sb.append("AND ep.id = :id ");
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		if (filters != null && !filters.isEmpty()) {
			sb.append("AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("merchantCode".equals(entry.getKey())) sb.append("ip_c.merchant_code LIKE '%").append(entry.getValue()).append("%'");
				else if ("ipayConfigComment".equals(entry.getKey())) sb.append("ip_c.comment LIKE '%").append(entry.getValue()).append("%'");
				else if ("invoiceNo".equals(entry.getKey())) sb.append("iv.code LIKE '%").append(entry.getValue()).append("%'");
				else if ("invoiceAdminChargesNo".equals(entry.getKey())) sb.append("iv_ad.code LIKE '%").append(entry.getValue()).append("%'");
				else if ("email".equals(entry.getKey())) sb.append("ep.email like '%").append(entry.getValue()).append("%'");
				else if ("paymentStatus".equals(entry.getKey())) sb.append("ep.payment_status like '").append(entry.getValue().charAt(0)).append("%'");
				else if ("transId".equals(entry.getKey())) sb.append("ep.trans_id like '%").append(entry.getValue()).append("%'");
				else if ("updatedBy".equals(entry.getKey())) sb.append("ep.updated_by like '%").append(entry.getValue()).append("%'");
				else if ("updatedDate".equals(entry.getKey())) sb.append("date_format(ep.dt_update, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
				else if ("invoiceStatus".equals(entry.getKey())) sb.append("iv.status_cd like '").append(entry.getValue().charAt(0)).append("%'");
				else if ("authCode".equals(entry.getKey())) sb.append("ep.auth_code like '%").append(entry.getValue()).append("%'");
				if (it.hasNext()) sb.append(" AND ");
			}
			sb.append(")");
		}
		
		sb.append(" ORDER BY ");
		String sortField = (String) params.get("sortField");
		
		if (sortField == null) sb.append("ep.dt_created DESC");
		else {
			if ("merchantCode".equals(sortField)) sb.append("ip_c.merchant_code");
			else if ("invoiceNo".equals(sortField)) sb.append("iv.code");
			else if ("invoiceAdminChargesNo".equals(sortField)) sb.append("iv_ad.code");
			else if ("email".equals(sortField)) sb.append("ep.email");
			else if ("paymentStatus".equals(sortField)) sb.append("ep.payment_status");
			else if ("transId".equals(sortField)) sb.append("ep.trans_id");
			else if ("updatedBy".equals(sortField)) sb.append("ep.updated_by");
			else if ("updatedDate".equals(sortField)) sb.append("ep.dt_update");
			else if ("invoiceStatus".equals(sortField)) sb.append("iv.status_cd");
			else if ("authCode".equals(sortField)) sb.append("ep.auth_code");
			
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" ASC");
			else sb.append(" DESC");
		}
		
		Query query = createSQLQuery(sb.toString());
		
		if (params.get("id") != null) {
			query.setParameter("id", params.get("id"));
		}
		
		if (!isCount && params.get("print") == null) {
			if (params.get("first") != null) {
				query.setFirstResult((Integer) params.get("first"));
				query.setMaxResults((Integer) params.get("pageSize"));
			}
		}
		
		return query.list();
	}
	
	@Override
	public InvoiceEmailPaymentVO getInvoiceEmailPayment(Map<String, Object> params) throws BusinessException {
		List<InvoiceEmailPaymentVO> tempList = getInvoiceEmailPaymentList(params);
		
		return tempList.size() > 0 ? tempList.get(0) : null;
	}
}
