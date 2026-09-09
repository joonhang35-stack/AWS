package com.bcs.zsg.product.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.RoomTypeVO;

public class RoomTypeDAOImpl extends BaseHibernateDAO implements RoomTypeDAO {

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.HotelDAO#getHotelList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomTypeVO> getRoomTypeList() throws BusinessException {
		Criteria criteria = createCriteria(RoomTypeVO.class);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
 

}
