package com.bcs.zsg.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.HotelService;
import com.bcs.zsg.product.vo.HotelAddressVO;
import com.bcs.zsg.product.vo.HotelContactVO;
import com.bcs.zsg.product.vo.HotelVO;

public class HotelBOImpl implements HotelBO {

	@Autowired
	private HotelService hotelService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.HotelDAO#getHotelById(java.lang.Long)
	 */
	@Override
	public HotelVO getHotelById(Long id) throws BusinessException {
		return hotelService.getHotelById(id);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.HotelBO#addHotel(com.bcs.zsg.product.vo.HotelVO)
	 */
	@Override
	public void addHotel(HotelVO hotelVO) throws BusinessException {
		hotelService.addHotel(hotelVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.HotelBO#updHotel(com.bcs.zsg.product.vo.HotelVO)
	 */
	@Override
	public void updHotel(HotelVO hotelVO) throws BusinessException {
		hotelService.updHotel(hotelVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.HotelBO#updContactList(java.util.List, java.util.List)
	 */
	@Override
	public void updContactList(List<HotelContactVO> addContList, List<HotelContactVO> updContList, List<HotelContactVO> delContList) throws BusinessException {
		hotelService.updContactList(addContList, updContList, delContList);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.HotelBO#delHotel(com.bcs.zsg.product.vo.HotelVO)
	 */
	@Override
	public void delHotel(HotelVO hotelVO) throws BusinessException {
		hotelService.delHotel(hotelVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.HotelBO#getHotelList()
	 */
	@Override
	public List<HotelVO> getHotelList() throws BusinessException {
		return getHotelList(null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.HotelBO#getHotelList(java.lang.Long)
	 */
	@Override
	public List<HotelVO> getHotelList(Long idRegion) throws BusinessException {
		return hotelService.getHotelList(idRegion);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.HotelBO#getHotelAddrList(java.lang.Long)
	 */
	@Override
	public List<HotelAddressVO> getHotelAddrList(Long id) throws BusinessException {
		return hotelService.getHotelAddrList(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.HotelBO#getHotelContList(java.lang.Long)
	 */
	@Override
	public List<HotelContactVO> getHotelContList(Long id) throws BusinessException {
		return hotelService.getHotelContList(id);
	}

}
