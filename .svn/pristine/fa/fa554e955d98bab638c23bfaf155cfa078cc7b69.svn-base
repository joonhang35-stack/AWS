package com.bcs.zsg.maintenance.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.POSUploadSalesVO;

public class POSUploadSalesDAOImpl extends BaseHibernateDAO implements POSUploadSalesDAO {
	
	@Override
	public int getPOSUploadSalesListCount(Map<String, Object> params) throws BusinessException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Criteria criteria = createCriteria(POSUploadSalesVO.class);
		if (params.get("uploadDate") != null)
			try {
				criteria.add(Restrictions.eq("uploadDate", sdf.parse(params.get("uploadDate").toString())));
			} catch (ParseException e) {}
		if (params.get("uploadStatus") != null) criteria.add(Restrictions.eq("uploadStatus", params.get("uploadStatus")));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return criteria.list().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<POSUploadSalesVO> getPOSUploadSalesList(Map<String, Object> params) throws BusinessException {
		Criteria criteria = createCriteria(POSUploadSalesVO.class);
		if (params.get("uploadDate") != null) criteria.add(Restrictions.eq("uploadDate", params.get("uploadDate")));
		if (params.get("uploadStatus") != null) criteria.add(Restrictions.eq("uploadStatus", params.get("uploadStatus")));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.setFirstResult((Integer) params.get("first"));
		criteria.setMaxResults((Integer) params.get("pageSize"));
		criteria.addOrder(Order.desc("uploadDate"));
		
		return criteria.list();
	}
	
	@Override
	public POSUploadSalesVO getPOSUploadSales(Map<String, Object> params) throws BusinessException {
		Criteria criteria = createCriteria(POSUploadSalesVO.class);
		if (params.get("uploadDate") != null) criteria.add(Restrictions.eq("uploadDate", params.get("uploadDate")));
		if (params.get("uploadStatus") != null) criteria.add(Restrictions.eq("uploadStatus", params.get("uploadStatus")));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.addOrder(Order.desc("updatedDate"));
		criteria.setMaxResults(1);
		
		return (POSUploadSalesVO) criteria.uniqueResult();
	}
	
	@Override
	public void insertPOSUploadSales(POSUploadSalesVO vo) throws BusinessException {
		vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
		if (vo.getCreatedDate() == null) vo.setCreatedDate(new Date());
		if (vo.getCreatedBy() == null) vo.setCreatedBy("SYSTEM");
		vo.setUpdatedDate(new Date());
		vo.setUpdatedBy("SYSTEM");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO pos_upload_sales (total_amount, upload_date, upload_status, is_test, status_cd, dt_created, created_by, dt_upd, upd_by) ");
		sb.append("VALUES ('" + vo.getTotalAmount() 
			+ "', DATE('" + sdf.format(vo.getUploadDate()) 
			+ "'), '" + vo.getUploadStatus() + "', " 
			+ (vo.getIsTest() ? 1 : 0) 
			+ ", '" + vo.getStatusCode() 
			+ "', DATE('" + sdf.format(vo.getCreatedDate()) 
			+ "'), '" + vo.getCreatedBy() 
			+ "', DATE('" + sdf.format(vo.getUpdatedDate()) + "'), '" 
			+ vo.getUpdatedBy() + "')");
		
		Query query = createSQLQuery(sb.toString());
		query.executeUpdate();
	}
	
	@Override
	public void deletePOSUploadSalesTestData() throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE FROM POSUploadSalesVO ");
		sb.append("SET statusCode = :statusCode ");
		sb.append("WHERE isTest = 1 ");

		Query query = createQuery(sb.toString());
		query.setString("statusCode", BaseConstant.STATUS_DELETED);
		query.executeUpdate();
	}
}
