package com.bcs.zsg.maintenance.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.POSUploadSalesTransVO;

public class POSUploadSalesTransDAOImpl extends BaseHibernateDAO implements POSUploadSalesTransDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public int getPOSUploadSalesTransListCount(Map<String, Object> params) throws BusinessException {
		Criteria criteria = createCriteria(POSUploadSalesTransVO.class);
		
		if (params.get("filters") != null) {
			Map<String, String> filters = (Map<String, String>) params.get("filters");
			if (!filters.isEmpty()) {
				for (Map.Entry<String, String> entryMap : filters.entrySet()) {
					if (StringUtils.equals(entryMap.getKey(), "idPOSUploadSales")) criteria.add(Restrictions.eq("idPOSUploadSales", Long.parseLong(entryMap.getValue())));
					else if (StringUtils.equals(entryMap.getKey(), "idInvoice")) criteria.add(Restrictions.eq("idInvoice", Long.parseLong(entryMap.getValue())));
				}
			}
		}
		
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		
		return criteria.list().size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<POSUploadSalesTransVO> getPOSUploadSalesTransList(Map<String, Object> params) throws BusinessException {
		Criteria criteria = createCriteria(POSUploadSalesTransVO.class);
		
		if (params.get("filters") != null) {
			Map<String, String> filters = (Map<String, String>) params.get("filters");
			if (!filters.isEmpty()) {
				for (Map.Entry<String, String> entryMap : filters.entrySet()) {
					if (StringUtils.equals(entryMap.getKey(), "idPOSUploadSales")) criteria.add(Restrictions.eq("idPOSUploadSales", Long.parseLong(entryMap.getValue())));
					else if (StringUtils.equals(entryMap.getKey(), "idInvoice")) criteria.add(Restrictions.eq("idInvoice", Long.parseLong(entryMap.getValue())));
				}
			}
		}
		
		if (params.get("idPOSUploadSales") != null) criteria.add(Restrictions.eq("idPOSUploadSales", params.get("idPOSUploadSales")));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.setFirstResult((Integer) params.get("first"));
		criteria.setMaxResults((Integer) params.get("pageSize"));
		criteria.addOrder(Order.desc("invoiceDate"));
		
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public POSUploadSalesTransVO getPOSUploadSalesTransVO(Map<String, Object> params) throws BusinessException {
		Criteria criteria = createCriteria(POSUploadSalesTransVO.class);
		
		if (params.get("filters") != null) {
			Map<String, String> filters = (Map<String, String>) params.get("filters");
			if (!filters.isEmpty()) {
				for (Map.Entry<String, String> entryMap : filters.entrySet()) {
					if (StringUtils.equals(entryMap.getKey(), "idPOSUploadSales")) criteria.add(Restrictions.eq("idPOSUploadSales", Long.parseLong(entryMap.getValue())));
					else if (StringUtils.equals(entryMap.getKey(), "idInvoice")) criteria.add(Restrictions.eq("idInvoice", Long.parseLong(entryMap.getValue())));
				}
			}
		}
		
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.setMaxResults(1);
		
		return (POSUploadSalesTransVO) criteria.uniqueResult();
	}
	
	@Override
	public void insertPOSUploadSalesTrans(POSUploadSalesTransVO vo) throws BusinessException {
		vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
		vo.setCreatedDate(new Date());
		vo.setCreatedBy("SYSTEM");
		vo.setUpdatedDate(new Date());
		vo.setUpdatedBy("SYSTEM");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO pos_upload_sales_trans (id_pos_upload_sales, id_invoice, doc_type_cd, invoice_no, sub_total, discount_percent, discount_amount, tax_rate, tax_amount, "
				+ "service_charge_percent, service_charge_amount, grand_total, is_test, is_void, invoice_date, status_cd, dt_created, created_by, dt_upd, upd_by) ");
		sb.append("VALUES ('" + vo.getIdPOSUploadSales() 
			+ "', '" + vo.getIdInvoice() 
			+ "', '" + vo.getDocTypeCd() 
			+ "', '" + vo.getInvoiceNo()
			+ "', '" + vo.getSubTotal()
			+ "', '" + vo.getDiscountPercentage()
			+ "', '" + vo.getDiscountAmount()
			+ "', '" + vo.getTaxRate()
			+ "', '" + vo.getTaxAmount()
			+ "', '" + vo.getServiceChargePercentage()
			+ "', '" + vo.getServiceChargeAmount()
			+ "', '" + vo.getGrandTotal()
			+ "', " + (vo.getIsTest() ? 1 : 0)
			+ ", " + (vo.getIsVoid() ? 1 : 0)
			+ ", DATE('" + sdf.format(vo.getInvoiceDate()) 
			+ "'), '" + vo.getStatusCode()
			+ "', DATE('" + sdf.format(vo.getCreatedDate()) 
			+ "'), '" + vo.getCreatedBy() 
			+ "', DATE('" + sdf.format(vo.getUpdatedDate()) + "'), '" 
			+ vo.getUpdatedBy() + "')");
		
		Query query = createSQLQuery(sb.toString());
		query.executeUpdate();
	}
	
	@Override
	public void deletePOSUploadSalesTransTestData() throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE FROM POSUploadSalesTransVO ");
		sb.append("SET statusCode = :statusCode ");
		sb.append("WHERE isTest = 1 ");

		Query query = createQuery(sb.toString());
		query.setString("statusCode", BaseConstant.STATUS_DELETED);
		query.executeUpdate();
	}

}
