package com.bcs.zsg.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.RoomTypeService; 
import com.bcs.zsg.product.vo.RoomTypeVO;

public class RoomTypeBOImpl implements RoomTypeBO {

	@Autowired
	private RoomTypeService roomTypeService;
   
  /*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.HotelBO#getHotelContList(java.lang.Long)
	 */
	@Override
	public List<RoomTypeVO> getRoomTypeList() throws BusinessException { 
		return roomTypeService.getRoomTypeList();
	}

}
