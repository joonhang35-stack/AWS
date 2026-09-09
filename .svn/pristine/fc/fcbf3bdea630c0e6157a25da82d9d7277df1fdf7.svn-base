package com.bcs.zsg.db.bterp.dao.airline;

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
import com.bcs.zsg.product.vo.AirlineScheduleChargeVO;
import com.bcs.zsg.product.vo.AirlineScheduleItemVO;
import com.bcs.zsg.product.vo.AirlineScheduleVO;
import com.bcs.zsg.product.vo.AirlineVO;

public class AirlineDAOImpl extends BaseHibernateDAO implements AirlineDAO {

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.AirlineDAO#isAirlineScheduleExisted(java.lang.Long)
	 */
	@Override
	public boolean isAirlineScheduleExisted(Long id) throws BusinessException {
		Criteria criteria = createCriteria(AirlineScheduleVO.class);
		criteria.add(Restrictions.eq("idAirline", id));
		return (CollectionUtils.isNotEmpty(criteria.list())) ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.AirlineDAO#isAirlineScheduleExisted(com.bcs.zsg.product.vo.AirlineScheduleVO)
	 */
	@Override
	public boolean isAirlineScheduleExisted(AirlineScheduleVO airlineScheduleVO) throws BusinessException {
		Criteria criteria = createCriteria(AirlineScheduleVO.class);
		criteria.add(Restrictions.eq("idAirline", airlineScheduleVO.getIdAirline()));
		criteria.add(Restrictions.eq("desc", airlineScheduleVO.getDesc()));
		criteria.setMaxResults(1);
		return (criteria.uniqueResult() != null) ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.AirlineDAO#getAirlinelist()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AirlineVO> getAirlinelist() throws BusinessException {
		Criteria criteria = createCriteria(AirlineVO.class);
		criteria.addOrder(Order.asc("desc"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.AirlineDAO#getAirlineScheduleList(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AirlineScheduleVO> getAirlineScheduleList(Long idAirline) throws BusinessException {
		Criteria criteria = createCriteria(AirlineScheduleVO.class);
		criteria.add(Restrictions.eq("idAirline", idAirline));
		criteria.addOrder(Order.asc("desc"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.AirlineDAO#getAirlineScheduleItemList(java.lang.Long, java.lang.Long[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AirlineScheduleItemVO> getAirlineScheduleItemList(Long idAirlineSchedule, Long[] ids) throws BusinessException {
		Criteria criteria = createCriteria(AirlineScheduleItemVO.class);
		criteria.add(Restrictions.eq("idAirlineSchedule", idAirlineSchedule));
		if (ids != null) criteria.add(Restrictions.in("id", ids));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.AirlineDAO#getAirlineScheduleItemChargeList(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AirlineScheduleChargeVO> getAirlineScheduleChargeList(Long idAirlineSchedule) throws BusinessException {
		Criteria criteria = createCriteria(AirlineScheduleChargeVO.class);
		criteria.add(Restrictions.eq("idAirlineSchedule", idAirlineSchedule));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.AirlineDAO#delAirlineScheduleItemCharge(java.lang.Long)
	 */
	@Override
	public int delAirlineScheduleExtraCharges(Long idAirlineSchedule) throws BusinessException {
		Query query = createSQLQuery("DELETE FROM airline_schedule_charge WHERE id_airline_schedule = :idAirlineSchedule");
		query.setLong("idAirlineSchedule", idAirlineSchedule);
		return query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.AirlineDAO#getAirline(java.lang.Long)
	 */
	@Override
	public AirlineVO getAirline(Long idAirline) throws BusinessException {
		Criteria criteria = createCriteria(AirlineVO.class);
		criteria.add(Restrictions.eq("id", idAirline));
		return (AirlineVO) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.AirlineDAO#getAirlineSchedule(java.lang.Long)
	 */
	@Override
	public AirlineScheduleVO getAirlineSchedule(Long idAirlineSchedule) throws BusinessException {
		Criteria criteria = createCriteria(AirlineScheduleVO.class);
		criteria.add(Restrictions.eq("id", idAirlineSchedule));
		criteria.addOrder(Order.asc("desc"));
		return (AirlineScheduleVO) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.AirlineDAO#getAirlineScheduleListByDesc(java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AirlineScheduleVO> getAirlineScheduleListByDesc(Long id, String scheduleSearch) throws BusinessException {
		Criteria criteria = createCriteria(AirlineScheduleVO.class);
		criteria.add(Restrictions.eq("idAirline", id));
		criteria.add(Restrictions.like("desc", "%" + scheduleSearch + "%"));
		criteria.addOrder(Order.asc("desc"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AirlineVO> getAirlineListByTypeCode(String typeCode) throws BusinessException {
		Criteria criteria = createCriteria(AirlineVO.class);
		criteria.add(Restrictions.eq("typeCode", typeCode));
		criteria.addOrder(Order.asc("code"));
		return criteria.list();
	}

	@Override
	public AirlineScheduleVO getAirlineScheduleWithName(Long idAirlineSchedule) throws BusinessException {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select a1.id,a1.id_airline,a1.description as flightDesc,a1.remarks,a1.dt_schedule,a1.tkt_validity,a1.tipping,a1.ac,a1.visa");
		sb.append(",a1.trvl_ins,a1.deviation,a1.fuel_chd,a1.fuel_adt,a1.apt_chd,a1.apt_adt,a.description as airline");
		sb.append(",a1.status,a1.dt_created,a1.created_by,a1.dt_upd,a1.upd_by");
		sb.append(" from airline_schedule a1, airline a where a1.id_airline=a.id and a1.id = :idAirlineSchedule");
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idAirlineSchedule", idAirlineSchedule);

		List<Object> results = query.list();
		AirlineScheduleVO vo = new AirlineScheduleVO();

		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			vo.setId(((BigInteger) row[0]).longValue()); 
			vo.setIdAirline(((BigInteger) row[1]).longValue()); 
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
			vo.setAirlineName((String) row[15]);
			vo.setStatus(((Byte) row[16]).intValue());
			vo.setCreatedDate((Date) row[17]);
			vo.setCreatedBy((String) row[18]);
			vo.setUpdatedDate((Date) row[19]);
			vo.setUpdatedBy((String) row[20]);
		}

		return vo;
	}

}
