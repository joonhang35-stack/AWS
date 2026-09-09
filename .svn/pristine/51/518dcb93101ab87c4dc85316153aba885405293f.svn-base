package com.bcs.zsg.product.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.HotelAddressVO;
import com.bcs.zsg.product.vo.HotelContactVO;
import com.bcs.zsg.product.vo.HotelVO;

public class HotelDAOImpl extends BaseHibernateDAO implements HotelDAO {
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.HotelDAO#getHotelById(java.lang.Long)
	 */
	@Override
	public HotelVO getHotelById(Long id) throws BusinessException {
		Criteria criteria = createCriteria(HotelVO.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.setMaxResults(1);
		return (HotelVO) criteria.uniqueResult();
	}	
	

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.HotelDAO#getHotelList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HotelVO> getHotelList() throws BusinessException {
		Criteria criteria = createCriteria(HotelVO.class);
		criteria.addOrder(Order.asc("name"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.HotelDAO#getHotelList(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HotelVO> getHotelList(Long idRegion) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		 
		
		sb.append("SELECT H.ID, H.star_cd, H.name, H.email, H.website, H.remarks, H.dt_created, ")
			.append("	H.created_by, H.dt_upd, H.upd_by ")
			.append("FROM hotel H, hotel_address A, country C, region R ").
			append("WHERE R.id = :idRegion AND C.id_region = R.id AND A.id_country = C.id AND A.id_hotel = H.id ORDER BY H.name");
		
		Query query = createSQLQuery(sb.toString());
		query.setLong("idRegion", idRegion);
		List<Object> results = query.list();
		List<HotelVO> hotelList = new ArrayList<HotelVO>();
		
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			HotelVO vo = new HotelVO();
			vo.setId(((BigInteger) row[0]).longValue());
			vo.setStarCd((String) row[1]);
			vo.setName((String) row[2]);
			vo.setEmail((String) row[3]);
			vo.setWebsite((String) row[4]);
			vo.setRemarks((String) row[5]);
			hotelList.add(vo);
		}
		
		return hotelList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.HotelDAO#getHotelAddrList(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HotelAddressVO> getHotelAddrList(Long id) throws BusinessException {
		Criteria criteria = createCriteria(HotelAddressVO.class);
		criteria.add(Restrictions.eq("idHotel", id));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.HotelDAO#getHotelContList(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HotelContactVO> getHotelContList(Long id) throws BusinessException {
		Criteria criteria = createCriteria(HotelContactVO.class);
		criteria.add(Restrictions.eq("idHotel", id));
		return criteria.list();
	}

}
