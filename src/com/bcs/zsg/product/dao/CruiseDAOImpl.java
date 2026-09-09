package com.bcs.zsg.product.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.CruiseScheduleChargeVO;
import com.bcs.zsg.product.vo.CruiseScheduleItemVO;
import com.bcs.zsg.product.vo.CruiseScheduleVO;
import com.bcs.zsg.product.vo.CruiseVO;

public class CruiseDAOImpl extends BaseHibernateDAO implements CruiseDAO {

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.CruiseDAO#isCruiseScheduleExisted(java.lang.Long)
	 */
	@Override
	public boolean isCruiseScheduleExisted(Long id) throws BusinessException {
		Criteria criteria = createCriteria(CruiseScheduleVO.class);
		criteria.add(Restrictions.eq("idCruise", id));
		return (CollectionUtils.isNotEmpty(criteria.list())) ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.CruiseDAO#isCruiseScheduleExisted(com.bcs.zsg.product.vo.CruiseScheduleVO)
	 */
	@Override
	public boolean isCruiseScheduleExisted(CruiseScheduleVO cruiseScheduleVO) throws BusinessException {
		Criteria criteria = createCriteria(CruiseScheduleVO.class);
		criteria.add(Restrictions.eq("idCruise", cruiseScheduleVO.getIdCruise()));
		criteria.add(Restrictions.eq("desc", cruiseScheduleVO.getDesc()));
		criteria.setMaxResults(1);
		return (criteria.uniqueResult() != null) ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.CruiseDAO#getCruiselist()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CruiseVO> getCruiselist() throws BusinessException {
		Criteria criteria = createCriteria(CruiseVO.class);
		criteria.addOrder(Order.asc("desc"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.CruiseDAO#getCruiseScheduleList(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CruiseScheduleVO> getCruiseScheduleList(Long idCruise) throws BusinessException {
		Criteria criteria = createCriteria(CruiseScheduleVO.class);
		criteria.add(Restrictions.eq("idCruise", idCruise));
		criteria.addOrder(Order.asc("desc"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.CruiseDAO#getCruiseScheduleItemList(java.lang.Long, java.lang.Long[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CruiseScheduleItemVO> getCruiseScheduleItemList(Long idCruiseSchedule, Long[] ids) throws BusinessException {
		Criteria criteria = createCriteria(CruiseScheduleItemVO.class);
		criteria.add(Restrictions.eq("idCruiseSchedule", idCruiseSchedule));
		if (ids != null) criteria.add(Restrictions.in("id", ids));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.CruiseDAO#getCruiseScheduleItemChargeList(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CruiseScheduleChargeVO> getCruiseScheduleChargeList(Long idCruiseSchedule) throws BusinessException {
		Criteria criteria = createCriteria(CruiseScheduleChargeVO.class);
		criteria.add(Restrictions.eq("idCruiseSchedule", idCruiseSchedule));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.CruiseDAO#delCruiseScheduleItemCharge(java.lang.Long)
	 */
	@Override
	public int delCruiseScheduleExtraCharges(Long idCruiseSchedule) throws BusinessException {
		Query query = createSQLQuery("DELETE FROM cruise_schedule_charge WHERE id_cruise_schedule = :idCruiseSchedule");
		query.setLong("idCruiseSchedule", idCruiseSchedule);
		return query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.CruiseDAO#getCruise(java.lang.Long)
	 */
	@Override
	public CruiseVO getCruise(Long idCruise) throws BusinessException {
		Criteria criteria = createCriteria(CruiseVO.class);
		criteria.add(Restrictions.eq("id", idCruise));
		return (CruiseVO) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.CruiseDAO#getCruiseSchedule(java.lang.Long)
	 */
	@Override
	public CruiseScheduleVO getCruiseSchedule(Long idCruiseSchedule) throws BusinessException {
		Criteria criteria = createCriteria(CruiseScheduleVO.class);
		criteria.add(Restrictions.eq("id", idCruiseSchedule));
		criteria.addOrder(Order.asc("desc"));
		return (CruiseScheduleVO) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.CruiseDAO#getCruiseScheduleListByDesc(java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CruiseScheduleVO> getCruiseScheduleListByDesc(Long id, String scheduleSearch) throws BusinessException {
		Criteria criteria = createCriteria(CruiseScheduleVO.class);
		criteria.add(Restrictions.eq("idCruise", id));
		criteria.add(Restrictions.like("desc", scheduleSearch + "%"));
		criteria.addOrder(Order.asc("desc"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CruiseVO> getCruiseListByTypeCode(String typeCode) throws BusinessException {
		Criteria criteria = createCriteria(CruiseVO.class);
		criteria.add(Restrictions.eq("typeCode", typeCode));
		criteria.addOrder(Order.asc("code"));
		return criteria.list();
	}

	@Override
	public CruiseScheduleVO getCruiseScheduleWithName(Long idCruiseSchedule) throws BusinessException {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select a1.id,a1.id_cruise,a1.description as flightDesc,a1.remarks,a1.dt_schedule,a1.tkt_validity,a1.tipping,a1.ac,a1.visa");
		sb.append(",a1.trvl_ins,a1.deviation,a1.fuel_chd,a1.fuel_adt,a1.apt_chd,a1.apt_adt,a.description as cruise");
		sb.append(",a1.status,a1.dt_created,a1.created_by,a1.dt_upd,a1.upd_by");
		sb.append(" from cruise_schedule a1, cruise a where a1.id_cruise=a.id and a1.id = :idCruiseSchedule");
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCruiseSchedule", idCruiseSchedule);

		List<Object> results = query.list();
		CruiseScheduleVO vo = new CruiseScheduleVO();

		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			vo.setId(((BigInteger) row[0]).longValue()); 
			vo.setIdCruise(((BigInteger) row[1]).longValue()); 
			vo.setDesc((String) row[2]);
			vo.setRemarks((String) row[3]);
			vo.setDtSchedule((Date) row[4]);
			vo.setTktValidity(((Short) row[5]).intValue());
			vo.setAmtTipping(((Float) row[6]).doubleValue());   
			vo.setAmtAC((Double) row[7]);
			vo.setAmtVisa((Double) row[8]);
			vo.setAmtTrvlIns((Double) row[9]);
			vo.setAmtDeviation((Double) row[10]);
			vo.setAmtFuelChd((Double) row[11]);
			vo.setAmtFuelAdt((Double) row[12]);
			vo.setAmtAptChd((Double) row[13]);
			vo.setAmtAptAdt((Double) row[14]);
			vo.setCruiseName((String) row[15]);
			vo.setStatus(((Byte) row[16]).intValue());
			vo.setCreatedDate((Date) row[17]);
			vo.setCreatedBy((String) row[18]);
			vo.setUpdatedDate((Date) row[19]);
			vo.setUpdatedBy((String) row[20]);
		}

		return vo;
	}

}
