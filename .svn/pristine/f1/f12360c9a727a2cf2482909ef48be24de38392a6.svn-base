package com.bcs.zsg.product.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.TimestampType;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.core.helper.BaseContext;
import com.bcs.zsg.product.vo.SalesCommConfigDetailVO;
import com.bcs.zsg.product.vo.SalesCommConfigVO;

public class SalesCommConfigDAOImpl extends BaseHibernateDAO implements SalesCommConfigDAO {
	
	
	public SalesCommConfigVO getSalesCommConfig(Long id) throws BusinessException {
		Criteria criteria = createCriteria(SalesCommConfigVO.class);
		criteria.add(Restrictions.eq("id", id));
		return (SalesCommConfigVO) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesCommConfigVO> getSalesCommConfigList() throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("select scc.id, scc.department, scc.group_name as groupName, scc.remarks, scc.status_cd as statusCode, ").
			append("scc.dt_created as createdDate, scc.created_by as createdBy, scc.dt_upd as updatedDate, scc.upd_by as updatedBy, ").
			append("(select ").
				append("group_concat( ").
					append("concat( ").
						append("case when fare_range_type = 'LT' then concat('< ', FORMAT(fr_amt, 0)) ").
						append("when fare_range_type = 'GT' then concat('> ', FORMAT(fr_amt, 0)) ").
						append("else concat(FORMAT(fr_amt, 0), ' - ', FORMAT(to_amt, 0)) end, ").
						
						append("' = ', FORMAT(sp_comm, 0) ").
					append(") ").
				append("ORDER BY FIELD(fare_range_type, 'LT', 'RG', 'GT'), fr_amt SEPARATOR '<br/>') ").
			append("from sales_comm_config_detail ").
			append("where id_sales_comm_conf = scc.id and status_cd = :statusCd ").
			append("group by id_sales_comm_conf ").
			append(") as fareRangeDetail ").
		append("from sales_comm_config scc ").
		append("where scc.status_cd = :statusCd ").
		append("order by scc.group_name, scc.department");
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("id", LongType.INSTANCE);
		query.addScalar("department");
		query.addScalar("groupName");
		query.addScalar("remarks");
		query.addScalar("statusCode");
		query.addScalar("createdDate", TimestampType.INSTANCE);
		query.addScalar("createdBy");
		query.addScalar("updatedDate", TimestampType.INSTANCE);
		query.addScalar("updatedBy");
		query.addScalar("fareRangeDetail");
		query.setParameter("statusCd", BaseConstant.STATUS_ACTIVE);
		query.setResultTransformer(Transformers.aliasToBean(SalesCommConfigVO.class));
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesCommConfigDetailVO> getSalesCommConfigDetailList(Long id) throws BusinessException {
		String hql = "FROM SalesCommConfigDetailVO "
				+ "WHERE id_sales_comm_conf = :idSalesCommConf AND status_cd = :statusCode "
				+ "ORDER BY FIELD(fare_range_type, 'LT', 'RG', 'GT'), fr_amt ";
		Query query = createQuery(hql);
		query.setParameter("idSalesCommConf", id);
		query.setParameter("statusCode", BaseConstant.STATUS_ACTIVE);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesCommConfigDetailVO> getSalesCommConfigDetailListByIdTourPkg(Long idTourPkg) throws BusinessException {
		String hql = "FROM SalesCommConfigDetailVO  "
				+ "WHERE status_cd = :statusCode AND id_sales_comm_conf = "
				+ "(SELECT scc.id FROM TourPackageVO tp, SalesCommConfigVO scc WHERE scc.id = tp.idSalesCommConf AND tp.id = :idTourPkg) "
				+ "ORDER BY FIELD(fare_range_type, 'LT', 'RG', 'GT'), fr_amt ";
		Query query = createQuery(hql);
		query.setParameter("idTourPkg", idTourPkg);
		query.setParameter("statusCode", BaseConstant.STATUS_ACTIVE);
		return query.list();
	}
	
	@Override
	public void terminateDetails(SalesCommConfigVO salesCommConfigVO) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE SalesCommConfigDetailVO SET statusCode = :statusCode, updatedDate = now(), updatedBy = :updatedBy WHERE idSalesCommConf = :idSalesCommConf");
		
		Query query = createQuery(sb.toString());
		query.setString("statusCode", BaseConstant.STATUS_DELETED);
		query.setString("updatedBy", BaseContext.getUserFullName() == null ? BaseContext.getLoginId() : BaseContext.getUserFullName());
		query.setLong("idSalesCommConf", salesCommConfigVO.getId());
		query.executeUpdate();
	}

	@Override
	public void insertSalesCommConfigHistory(Long id, String actionCd, String reason) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		Session session = this.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			
			sb.append("INSERT INTO sales_comm_config_history (id_hist, department, group_name, remarks, status_cd, dt_created, created_by, dt_upd, upd_by, reason, action_cd) ");
			sb.append("(SELECT id, department, group_name, remarks, status_cd, dt_created, created_by, dt_upd, upd_by, :reason, :actionCd ");
			sb.append("FROM sales_comm_config WHERE id = :id) ");
			
			Query query = session.createSQLQuery(sb.toString());
			query.setLong("id", id);
			query.setString("reason", reason);
			query.setString("actionCd", actionCd);
			query.executeUpdate();
			
			BigInteger result = (BigInteger) session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult();
			
			sb = new StringBuilder();
			sb.append("INSERT INTO sales_comm_config_detail_history (id_ref, id_hist, id_sales_comm_conf, fare_range_type, fr_amt, to_amt, sp_comm, ref_sp_comm, ss_comm, hod_comm, ");
				sb.append("op_comm, status_cd, dt_created, created_by, dt_upd, upd_by) ");
			sb.append("(SELECT :idRef, id, id_sales_comm_conf, fare_range_type, fr_amt, to_amt, sp_comm, ref_sp_comm, ss_comm, hod_comm, ");
				sb.append("op_comm, status_cd, dt_created, created_by, dt_upd, upd_by ");
			sb.append("FROM sales_comm_config_detail WHERE id_sales_comm_conf = :id) ");
			
			Query query2 = session.createSQLQuery(sb.toString());
			query2.setLong("idRef", result.longValue());
			query2.setLong("id", id);
			query2.executeUpdate();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
