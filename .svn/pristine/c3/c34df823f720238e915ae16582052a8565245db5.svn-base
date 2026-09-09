package com.bcs.zsg.product.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.AirlineVO;
import com.bcs.zsg.product.vo.TicketingVO;
import com.bcs.zsg.product.vo.TourDepartureViewVO;
import com.bcs.zsg.product.vo.TourPackageVO;

public class TicketingDAOImpl extends BaseHibernateDAO implements TicketingDAO {

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.TicketingDAO#getTicketingList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AirlineVO> getTicketingList() throws BusinessException {
		Query query = createSQLQuery("SELECT A.ID, A.code, A.description, A.dt_created, A.created_by, A.dt_upd, A.upd_by " 
						+ " FROM airline A, ticketing T WHERE A.id = T.id_airline AND T.status_cd = :statusCode GROUP BY A.code ORDER BY A.description");
		
		query.setString("statusCode", CommonConstant.STATUS_CD_ACTIVE); 
		
		List<Object> results = query.list();
		List<AirlineVO> ticketingList = new ArrayList<AirlineVO>();
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			AirlineVO vo = new AirlineVO();
			vo.setId(((BigInteger) row[0]).longValue());
			vo.setCode((String) row[1]);
			vo.setDesc((String) row[2]);
			ticketingList.add(vo);
		}
		return ticketingList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.TicketingDAO#getTourDepViewList(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TourDepartureViewVO> getTourDepViewList(Long idAirline) throws BusinessException {
		Query query = createSQLQuery("SELECT id FROM tour_pkg WHERE type_cd = :typeCode");
		query.setString("typeCode", ProductConstant.TYPE_TICKETING);
		List<BigInteger> results = query.list();
		if (CollectionUtils.isNotEmpty(results)) {
			List<Long> ids = new ArrayList<Long>();
			for (Iterator<BigInteger> it = results.iterator() ; it.hasNext() ;) {
				ids.add(((BigInteger) it.next()).longValue());
			}
			
			Criteria criteria = createCriteria(TourDepartureViewVO.class);
			criteria.add(Restrictions.eq("idAirline", idAirline));
			criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
			criteria.add(Restrictions.in("tourPkgVO.id", ids));
			criteria.add(Restrictions.gt("dtDep", new Date()));
			criteria.addOrder(Order.asc("dtDep"));
			return criteria.list();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.TicketingDAO#getTicketingListByAirline(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TicketingVO> getTicketingListByAirline(Long idAirline) throws BusinessException {
		Criteria criteria = createCriteria(TicketingVO.class);
		criteria.add(Restrictions.eq("idAirline", idAirline));
		criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.TicketingDAO#getTourCodeList(com.bcs.zsg.cfg.sec.vo.EmployeeVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TourDepartureViewVO> getTourCodeList(EmployeeVO employeeVO) throws BusinessException {
		Criteria criteria = createCriteria(TourPackageVO.class);
		criteria.add(Restrictions.eq("typeCd", ProductConstant.TYPE_TICKETING));
		criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
		if (employeeVO.getDepartment().contains("tick")) criteria.add(Restrictions.like("reserved3", "%tick%"));
		else criteria.add(Restrictions.not(Restrictions.like("reserved3", "%tick%")));
		List<TourPackageVO> list = criteria.list();
		
		if (CollectionUtils.isNotEmpty(list)) {
			criteria = createCriteria(TourDepartureViewVO.class);
			criteria.add(Restrictions.in("tourPkgVO", list));
			criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
			return criteria.list();
		}
		return null;
	}
}
