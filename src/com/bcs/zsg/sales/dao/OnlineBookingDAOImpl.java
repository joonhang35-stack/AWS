package com.bcs.zsg.sales.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.OnlineBookingCustomerVO;
import com.bcs.zsg.sales.vo.OnlineBookingPassengerVO;
import com.bcs.zsg.sales.vo.OnlineBookingPaymentVO;

public class OnlineBookingDAOImpl extends BaseHibernateDAO implements OnlineBookingDAO {

	@Override
	public int getOnlineBookingPaymentListSize(Map<String, Object> params) throws BusinessException {
//		Criteria criteria = createCriteria(OnlineBookingPaymentVO.class);
//		return criteria.list().size();
		List<Object> results = commonQuery(params, true);
		return results.size();
	}

	@Override
	public List<OnlineBookingPaymentVO> getOnlineBookingPaymentList(Map<String, Object> params) throws BusinessException {
		List<Object> results = commonQuery(params, false);
		List<OnlineBookingPaymentVO> onlineBookingPaymentVOList = new ArrayList<OnlineBookingPaymentVO>();

		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			OnlineBookingPaymentVO vo = new OnlineBookingPaymentVO();
			vo.setId(((Integer) row[0]).longValue()); 
			vo.setIdIpayConfig(((BigInteger) row[1]).longValue());
			if (row[2] != null) vo.setIdInvoice(((BigInteger) row[2]).longValue()); 
			if (row[3] != null) vo.setIdInvoiceAdminCharges(((BigInteger) row[3]).longValue()); 
			vo.setEmail((String) row[4]);
			vo.setPaymentAmt((BigDecimal) row[5]);
			vo.setAdminChargesPercentage((BigDecimal) row[6]);
			vo.setAdminCharges((BigDecimal) row[7]);
			vo.setTotalPaymentAmt((BigDecimal) row[8]);
			vo.setPaymentStatus((String) row[9]);
			if (row[10] != null) vo.setPrefix((String) row[10]);
			if (row[11] != null) vo.setPrefixAdminCharges((String) row[11]);
			if (row[12] != null) vo.setTransId((String) row[12]);
			if (row[13] != null) vo.setAuthCode((String) row[13]);
			if (row[14] != null) vo.setCcName((String) row[14]);
			if (row[15] != null) vo.setCcNo((String) row[15]);
			if (row[16] != null) vo.setsBankname((String) row[16]);
			if (row[17] != null) vo.setsCountry((String) row[17]);
			vo.setStatusCd((String) row[18]);
			vo.setCreatedDate((Date) row[19]);
			vo.setCreatedBy((String) row[20]);
			vo.setUpdatedDate((Date) row[21]);
			vo.setUpdatedBy((String) row[22]);
			if (row[23] != null) vo.setInvoiceNo((String) row[23]);
			if (row[24] != null) vo.setInvoiceAdminChargesNo((String) row[24]);
			if (row[25] != null) vo.setInvoiceStatus((String) row[25]);
			vo.setMerchantCode((String) row[26]);
			vo.setMerchantKey((String) row[27]);
			vo.setIpayConfigComment((String) row[28]);
			if (row[29] != null) vo.setDtExpiry((Date) row[29]);
			if (row[30] != null) vo.setIdBooking(((BigInteger) row[30]).longValue());
			if (row[31] != null) vo.setDocTypeCd((String) row[31]);

			onlineBookingPaymentVOList.add(vo);
		}

		return onlineBookingPaymentVOList;
	}

	@SuppressWarnings("unchecked")
	private List<Object> commonQuery(Map<String, Object> params, boolean isCount) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ob.id, ob.id_ipay_config, ob.id_invoice, ob.id_invoice_admin_charges, ob.email, ");
		sb.append("ob.payment_amt, ob.admin_charges_percentage, ob.admin_charges, ob.total_payment_amt, ob.payment_status, ob.prefix, ob.prefix_admin_charges, ");
		sb.append("ob.trans_id, ob.auth_code, ob.cc_name, ob.cc_no, ob.s_bankname, ob.s_country, ");
		sb.append("ob.status_cd, ob.dt_created, ob.created_by, ob.dt_upd, ob.upd_by, ");
		sb.append("iv.code AS inv_code, iv_ad.code AS inv_admin_charges_code, iv.status_cd AS invoice_status, ");
		sb.append("ip_c.merchant_code, ip_c.merchant_key, ip_c.comment, ob.dt_expiry, ob.id_booking, iv.doc_type_cd as invoice_doc_type_cd ");
		sb.append("FROM online_booking_payment ob ");
		sb.append("LEFT JOIN invoice iv ON iv.id = ob.id_invoice ");
		sb.append("LEFT JOIN invoice iv_ad ON iv_ad.id = ob.id_invoice_admin_charges ");
		sb.append("LEFT JOIN ipay_config ip_c ON ip_c.id = ob.id_ipay_config ");

		sb.append("WHERE ob.status_cd = 'A' ");

		if (params.get("id") != null)
			sb.append("AND ob.id = :id ");

		Map<String, String> filters = (Map<String, String>) params.get("filters");
		if (filters != null && !filters.isEmpty()) {
			sb.append("AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("ipayConfigComment".equals(entry.getKey())) sb.append("ip_c.comment LIKE '%").append(entry.getValue()).append("%'");
				else if ("idBooking".equals(entry.getKey())) sb.append("ob.id_booking LIKE '%").append(entry.getValue()).append("%'");
				else if ("invoiceNo".equals(entry.getKey())) sb.append("iv.code LIKE '%").append(entry.getValue()).append("%'");
				else if ("invoiceAdminChargesNo".equals(entry.getKey())) sb.append("iv_ad.code LIKE '%").append(entry.getValue()).append("%'");
				else if ("email".equals(entry.getKey())) sb.append("ob.email like '%").append(entry.getValue()).append("%'");
				//else if ("paymentStatus".equals(entry.getKey())) sb.append("ob.payment_status like '%").append(entry.getValue()).append("%'");
				else if ("paymentStatus".equals(entry.getKey())) {
					sb.append("CASE ").
						append("WHEN ob.payment_status = 'S' THEN 'Success' ").
						append("WHEN ob.payment_status = 'F' THEN 'Failed' ").
						append("WHEN ob.payment_status = 'P' THEN 'Pending' ").
						append("WHEN ob.payment_status = 'C' THEN 'Cancelled' ").
					append("ELSE '' END like '%").
					append(entry.getValue()).append("%'");
				}
				else if ("transId".equals(entry.getKey())) sb.append("ob.trans_id like '%").append(entry.getValue()).append("%'");
				else if ("updatedBy".equals(entry.getKey())) sb.append("ob.upd_by like '%").append(entry.getValue()).append("%'");
				else if ("updatedDate".equals(entry.getKey())) sb.append("date_format(ob.dt_upd, '%d-%b-%Y') like '%").append(entry.getValue()).append("%'");
			}
			sb.append(")");
		}

		sb.append(" ORDER BY ");
		String sortField = (String) params.get("sortField");

		if (sortField == null) sb.append("ob.dt_created DESC");
		else {
			if ("idInvoice".equals(sortField)) sb.append("iv.code");
			else if ("invoiceAdminChargesNo".equals(sortField)) sb.append("iv_ad.code");
			else if ("email".equals(sortField)) sb.append("ob.email");
			else if ("paymentStatus".equals(sortField)) sb.append("ob.payment_status");
			else if ("transId".equals(sortField)) sb.append("ob.trans_id");
			else if ("updatedBy".equals(sortField)) sb.append("ob.upd_by");
			else if ("updatedDate".equals(sortField)) sb.append("ob.dt_upd");
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" ASC");
			else sb.append(" DESC");
		}

		Query query = createSQLQuery(sb.toString());

		if (params.get("id") != null) {
			query.setParameter("id", params.get("id"));
		}

		if (!isCount) {
			if (params.get("first") != null) {
				query.setFirstResult((Integer) params.get("first"));
				query.setMaxResults((Integer) params.get("pageSize"));
			}
		}

		return query.list();
	}

	@Override
	public OnlineBookingCustomerVO getOnlineBookingCustomerVO(Long idBooking) throws BusinessException {
		Criteria criteria = createCriteria(OnlineBookingCustomerVO.class);
		criteria.add(Restrictions.eq("idBooking", idBooking));
		return (OnlineBookingCustomerVO) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OnlineBookingPaymentVO> getAutogenerateInvoicePaymentList(Map<String, Object> params) {
		Criteria criteria = createCriteria(OnlineBookingPaymentVO.class);
		criteria.add(Restrictions.eq("paymentStatus", params.get("paymentStatus")));
		criteria.add(Restrictions.isNull("idInvoice"));
		return criteria.list();
	}

	@Override
	public OnlineBookingPassengerVO getOnlineBookingPassenger(Map<String, Object> params) throws BusinessException {
		Criteria criteria = createCriteria(OnlineBookingPassengerVO.class);
		if (params.get("idBooking") != null) criteria.add(Restrictions.eq("idBooking", params.get("idBooking")));
		if (params.get("idCustomer") != null) criteria.add(Restrictions.eq("idCustomer", params.get("idCustomer")));
		return (OnlineBookingPassengerVO) criteria.uniqueResult();
	}
}
