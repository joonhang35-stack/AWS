package com.bcs.zsg.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.dao.RoomTypeDAO;
import com.bcs.zsg.product.vo.RoomTypeVO;

public class RoomTypeServiceImpl implements RoomTypeService {

	@Autowired
	private RoomTypeDAO roomTypeDAO;
    
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.HotelService#getHotelList(java.lang.Long)
	 */
	@Override
	public List<RoomTypeVO> getRoomTypeList() throws BusinessException { 
		return roomTypeDAO.getRoomTypeList();
	}
 

}
